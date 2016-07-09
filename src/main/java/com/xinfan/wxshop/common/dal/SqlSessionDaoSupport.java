/**
 * 
 */
package com.xinfan.wxshop.common.dal;

import static org.springframework.util.Assert.notNull;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.support.DaoSupport;

/**
 * @author Administrator
 * 
 */
public abstract class SqlSessionDaoSupport extends DaoSupport {

	private SqlSession sqlSession;

	private boolean externalSqlSession;
	
	private String namespace;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		if (!this.externalSqlSession) {
			this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
		}
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSession = sqlSessionTemplate;
		this.externalSqlSession = true;
	}

	/**
	 * Users should use this method to get a SqlSession to call its statement
	 * methods This is SqlSession is managed by spring. Users should not
	 * commit/rollback/close it because it will be automatically done.
	 * 
	 * @return Spring managed thread safe SqlSession
	 */
	public SqlSession getSqlSession() {
		return this.sqlSession;
	}

	/**
	 * {@inheritDoc}
	 */
	protected void checkDaoConfig() {
		notNull(this.sqlSession, "Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required");
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	

	public String wrapCommand(String command) {
		return this.namespace + "." + command;
	}
}