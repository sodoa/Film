package com.xinfan.wxshop.common.cache;

public class ExpireCacheHolder {

	public static Object getExpireCacheObject(String key, FitData fitCall) {

		Object t = CacheHolder.getInstance().getCacheProvider("exp_cache").getAttribute(key);
		if (t != null) {
			return t;
		}
		CacheData map = fitCall.refresh();
		CacheHolder.getInstance().getCacheProvider("exp_cache").setAttribute(key, map.getData(), map.getExp());
		return map.getData();
	}

}
