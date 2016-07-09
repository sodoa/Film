package com.xinfan.wxshop.common.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 缓存持有类
 * 
 * @author cyp
 *
 */
public class CacheHolder {
	
	private static CacheHolder instance;
	
	private Map<String,CacheProvider> providers = new HashMap<String,CacheProvider>();
	
	private CacheHolder(){
	}
	
	public static CacheHolder getInstance(){
		if(instance == null){
			instance = new CacheHolder();
		}
		return instance;
	}
	
	public synchronized void addCacheProvider(String name,CacheProvider provider){
		providers.put(name, provider);
	}
	
	public synchronized void removeCacheProvider(String name){
		providers.remove(name);
	}

	public CacheProvider getCacheProvider(String name){
		return providers.get(name);
	}
	
}
