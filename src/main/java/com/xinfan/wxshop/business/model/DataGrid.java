package com.xinfan.wxshop.business.model;

import java.util.List;

import com.xinfan.wxshop.common.page.Pagination;

/**
 * 
 * UI要的数据格式封装对象
 * 
 * @author cyp
 * 
 */
public class DataGrid {

	private List rows;

	private int total;

	private int page;

	public DataGrid() {

	}

	public DataGrid(Pagination page) {
		this.rows = page.getList();
		this.total = page.getTotalCount();
		this.page = page.getPageNo();
	}

	public DataGrid(List rows, int total) {
		this.rows = rows;
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
