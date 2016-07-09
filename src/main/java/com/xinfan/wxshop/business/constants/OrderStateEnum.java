package com.xinfan.wxshop.business.constants;

public enum OrderStateEnum {
	UNPAY("未支付", 0), PAYED("待出货", 1), SHIPPED("待收货", 2), COMMENT("已评价", 3);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private OrderStateEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (OrderStateEnum c : OrderStateEnum.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	// get set 方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
