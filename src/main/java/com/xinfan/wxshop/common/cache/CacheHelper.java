package com.xinfan.wxshop.common.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.xinfan.wxshop.common.context.AppContextHolder;

public class CacheHelper {

	private static CacheHelper instance;

	private static Object lock = new Object();

	private static final Map<String, CacheLoader> loaderMap = Collections.synchronizedMap(new HashMap());

	private void init() {

		Map<String, CacheLoader> loaders = AppContextHolder.getBeansOfType(CacheLoader.class);

		if (loaders != null) {
			Iterator it = loaders.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				CacheLoader value = loaders.get(key);
				loaderMap.put(value.getCacheKey(), value);
				value.load();
			}
		}
	}
	
	public static CacheHelper getInstance(){

		if (instance == null) {
			synchronized (lock) {
				instance = new CacheHelper();
				instance.init();
			}
		}
		
		return instance;
	}

	public  Object getCacheObject(String cacheName, String key) {


		CacheProvider provider = CacheHolder.getInstance().getCacheProvider(cacheName);
		Object obj = provider.getAttribute(key);
		if (obj == null) {
			// 缓存项与对应加载器之间有对应约束关系
			CacheLoader loader = loaderMap.get(key);

			if (loader == null) {
				throw new IllegalArgumentException("key " + key + " 对应缓存加载器为空，请确认代码使用是否正确");
			}

			synchronized (lock) {
				loader.load();
			}

			obj = provider.getAttribute(key);
		}

		return obj;
	}

	public  boolean refresh(String key) {

		CacheLoader loader = loaderMap.get(key);
		if (loader != null) {
			synchronized (lock) {
				return loader.refresh();
			}
		}

		return false;
	}

}
