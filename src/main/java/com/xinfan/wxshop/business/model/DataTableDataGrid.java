package com.xinfan.wxshop.business.model;

import java.util.ArrayList;
import java.util.List;

import com.xinfan.wxshop.business.formater.DataGridFormater;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;

/**
 * 
 * UI要的数据格式封装对象
 * 
 * @author cyp
 * 
 */
public class DataTableDataGrid {

	private List data;

	private int recordsTotal;

	private int recordsFiltered;

	private int draw;

	public DataTableDataGrid() {

	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public DataTableDataGrid(int draw, Pagination page, Object[] columns) {
		this.data = page.getList();
		this.recordsTotal = page.getTotalCount();
		this.recordsFiltered = page.getTotalCount();
		this.draw = draw;

		this.data = new ArrayList();
		for (int i = 0; i < page.getList().size(); i++) {
			DataMap map = (DataMap) page.getList().get(i);

			List item = new ArrayList();

			for (int j = 0; j < columns.length; j++) {

				Object column = columns[j];
				if (column instanceof DataGridFormater) {
					item.add(((DataGridFormater) column).execute(map));
				} else {
					item.add(map.get(column));
				}
			}
			data.add(item);
		}

	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

}
