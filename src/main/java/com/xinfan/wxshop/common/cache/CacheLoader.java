package com.xinfan.wxshop.common.cache;

public abstract class CacheLoader {
	
	private String cacheKey;

	public abstract boolean load();

	public abstract boolean refresh();

	public String getCacheKey() {
		return this.cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}

}
