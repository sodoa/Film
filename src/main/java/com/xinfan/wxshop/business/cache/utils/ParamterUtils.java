package com.xinfan.wxshop.business.cache.utils;

import java.util.Map;

import com.xinfan.wxshop.business.constants.BizConstants;
import com.xinfan.wxshop.business.entity.Config;
import com.xinfan.wxshop.common.cache.CacheHolder;

public class ParamterUtils {

	public static String getString(String key,String defVal) {

		Map<String,Config> cache = (Map) CacheHolder.getInstance()
				.getCacheProvider(BizConstants.PROVIDER_COMMON_CACHE)
				.getAttribute(BizConstants.CACHE_KEY_CONFIGCACHE);

		Config config = cache.get(key.trim().toUpperCase());
		if(config == null){
			return defVal;
		}

		return config.getValue();
	}
	
	public static String getString(String key) {
		return getString(key,"");
	}

}
