package com.xinfan.wxshop.common.cache;

import java.io.Serializable;

public class CacheData {

	private Serializable data;

	private int exp;

	public Serializable getData() {
		return data;
	}

	public void setData(Serializable data) {
		this.data = data;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

}
