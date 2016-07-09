package com.xinfan.wxshop.common.dialect;

public class MysqlDialect extends Dialect {
	
	@Override
	public boolean supportsLimit() {
		return true;
	}
	
	@Override
	public boolean supportsLimitOffset() {
		return true;
	}
	
	@Override
	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}
		
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		pagingSelect.append("select * from (  ");
		pagingSelect.append(sql);
		int endInt = Integer.parseInt(offsetPlaceholder) + + + Integer.parseInt(limitPlaceholder);
		pagingSelect.append(" ) _t limit " + offset + "," + endInt);
		
		if (isForUpdate) {
			pagingSelect.append(" for update");
		}
		
		return pagingSelect.toString();
	}
	
	public String getCountSql(String sql)
	{
		sql = sql.trim();
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
		}

		StringBuffer countSelect = new StringBuffer(sql.length() + 100);
		countSelect.append("select count(*) from ( ");
		countSelect.append(sql);
		countSelect.append(" ) _t ");

		return countSelect.toString();
	}
	
}
