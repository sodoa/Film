package com.xinfan.wxshop.business.model;


public interface DataGridFormater {
	public String execute(Object row);

	public String getColumnName();
}
