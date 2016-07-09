package com.xinfan.wxshop.business.formater;

import org.apache.commons.lang.StringUtils;

import com.xinfan.wxshop.business.constants.OrderStateEnum;
import com.xinfan.wxshop.common.base.DataMap;

public class OrderStateDataGridFormater implements DataGridFormater {

	private String columnName;

	public OrderStateDataGridFormater(String columnName) {
		this.columnName = columnName;
	}

	@Override
	public String execute(Object row) {

		DataMap rowData = (DataMap) row;
		String state = rowData.getString(this.getColumnName());

		if (StringUtils.isNotEmpty(state)) {
			return OrderStateEnum.getName(Integer.parseInt(state));
		}

		return null;
	}

	@Override
	public String getColumnName() {
		return columnName;
	}

}
