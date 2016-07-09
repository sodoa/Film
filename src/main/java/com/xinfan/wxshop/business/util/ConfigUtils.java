package com.xinfan.wxshop.business.util;

import java.util.Map;

import com.xinfan.wxshop.business.constants.BizConstants;
import com.xinfan.wxshop.business.entity.Config;
import com.xinfan.wxshop.common.cache.CacheHelper;

/**
 * 
 * 本地参数帮助类
 * 
 * @author cyp
 * 
 */
public class ConfigUtils {

	/**
	 * 获取参数配置值
	 * 
	 * @param configId
	 * @param softType
	 * @return
	 */
	public static String getValue(String configId) {
		Map configs = (Map) CacheHelper.getInstance().getCacheObject(BizConstants.PROVIDER_COMMON_CACHE, BizConstants.CACHE_KEY_CONFIGCACHE);

		String key = configId.toUpperCase();
		Config item = (Config) configs.get(key);
		if (item != null) {
			return item.getValue() == null ? "" : item.getValue();
		}

		return "";
	}

	/**
	 * 获取参数配置值
	 * 
	 * @param configId
	 * @param softType
	 * @param defaultValue
	 * @return
	 */
	public static String getValue(String configId, String defaultValue) {
		String value = getValue(configId);
		return value == null ? defaultValue : value;
	}

	/**
	 * 获取参数配置的显示名称
	 * 
	 * @param configId
	 * @param softType
	 * @return
	 */
	public static String getName(String configId) {
		Map configs = (Map) CacheHelper.getInstance().getCacheObject(BizConstants.PROVIDER_COMMON_CACHE, BizConstants.CACHE_KEY_CONFIGCACHE);

		String key = configId.toUpperCase();
		Config item = (Config) configs.get(key);
		if (item != null) {
			return item.getName() == null ? "" : item.getName();
		}

		return "";
	}

	/**
	 * 获取参数配置的显示名称
	 * 
	 * @param configId
	 * @param softType
	 * @return
	 */
	public static String getConfigLabel(String configId, String defaultValue) {
		String value = getName(configId);
		return value == null ? defaultValue : value;
	}
}
