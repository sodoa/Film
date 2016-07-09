package com.xinfan.wxshop.common.dialect;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.xinfan.wxshop.common.page.Pagination;

/**
 * 数据库分页拦截器
 * 
 * @author chenyupeng
 * 
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class,
		ResultHandler.class }) })
public class PaginationInterceptor implements Interceptor {
	private static int MAPPED_STATEMENT_INDEX = 0;
	private static int PARAMETER_INDEX = 1;
	private static int ROWBOUNDS_INDEX = 2;
	// private static int RESULT_HANDLER_INDEX = 3;
	private Dialect dialect = new MysqlDialect();

	public Object intercept(Invocation invocation) throws Throwable {
		Object obj = invocation.getArgs()[ROWBOUNDS_INDEX];
		if (obj != null && Pagination.class.getName().equals(obj.getClass().getName())) {
			processCountIntercept(invocation);
			return processIntercept(invocation);
		}

		return invocation.proceed();
	}

	private void processCountIntercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
		Object[] queryArgs = invocation.getArgs();
		MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		Object parameter = queryArgs[PARAMETER_INDEX];
		RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];

		BoundSql newBoundSql = newCountBoundSql(ms, parameter);

		List<ResultMap> newResultMap = newCountResultMap(ms);

		MappedStatement newMs = copyFromMappedStatement(ms, newBoundSql, newResultMap);
		RowBounds newRowBounds = RowBounds.DEFAULT;

		Object statement = queryArgs[MAPPED_STATEMENT_INDEX];

		queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
		queryArgs[ROWBOUNDS_INDEX] = newRowBounds;
		processCountInvoke(invocation, rowBounds);

		queryArgs[ROWBOUNDS_INDEX] = rowBounds;
		queryArgs[MAPPED_STATEMENT_INDEX] = statement;
	}

	private BoundSql newCountBoundSql(MappedStatement ms, Object parameter) {

		BoundSql boundSql = ms.getBoundSql(parameter);
		String sql = boundSql.getSql().trim();
		String countSql = dialect.getCountSql(sql);

		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
		return newBoundSql;
	}

	private List<ResultMap> newCountResultMap(MappedStatement ms) {
		List<ResultMap> newResultMap = new ArrayList<ResultMap>();
		List<ResultMapping> newResultMapping = new ArrayList<ResultMapping>();

		ResultMap newMap = new ResultMap.Builder(ms.getConfiguration(), ms.getId(), Integer.class, newResultMapping).build();
		newResultMap.add(newMap);
		return newResultMap;
	}

	private void processCountInvoke(Invocation invocation, RowBounds rowBounds) throws InvocationTargetException, IllegalAccessException {

		List list = (List) invocation.proceed();

		int countAll = (Integer) list.get(0);

		calculatePaginationParameter(rowBounds, countAll);
	}

	private void calculatePaginationParameter(RowBounds rowBounds, int countAll) {
		int allPage = 0;
		Pagination page = (Pagination)rowBounds;
		page.setTotalCount(countAll);
	}

	private MappedStatement copyFromMappedStatement(MappedStatement ms, BoundSql newBoundSql, List<ResultMap> newResultMap) {
		Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), new BoundSqlSqlSource(newBoundSql),
				ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		// builder.keyProperty(ms.getKeyProperty());
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(newResultMap);
		builder.cache(ms.getCache());

		MappedStatement newMs = builder.build();
		return newMs;
	}

	private Object processIntercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
		Object[] queryArgs = invocation.getArgs();
		MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		Object parameter = queryArgs[PARAMETER_INDEX];
		Pagination rowBounds = (Pagination) queryArgs[ROWBOUNDS_INDEX];
		int offset = (rowBounds.getPageNo()- 1) * rowBounds.getPageSize();
		int limit = rowBounds.getPageSize();

		BoundSql boundSql = ms.getBoundSql(parameter);
		String sql = boundSql.getSql().trim();
		sql = dialect.getLimitString(sql, offset, limit);
		offset = RowBounds.NO_ROW_OFFSET;
		limit = RowBounds.NO_ROW_LIMIT;

		queryArgs[ROWBOUNDS_INDEX] = new RowBounds(offset, limit);
		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
		MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
		queryArgs[MAPPED_STATEMENT_INDEX] = newMs;

		return invocation.proceed();
	}

	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
		Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		// builder.keyProperty(ms.getKeyProperty());
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.cache(ms.getCache());
		MappedStatement newMs = builder.build();
		return newMs;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}

	@Override
	public void setProperties(Properties properties) {

	}

	public Dialect getDialect() {
		return dialect;
	}

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}

}
