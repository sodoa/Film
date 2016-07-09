package com.xinfan.wxshop.business.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xinfan.wxshop.business.constants.BizConstants;
import com.xinfan.wxshop.business.dao.ConfigDao;
import com.xinfan.wxshop.business.entity.Config;
import com.xinfan.wxshop.common.cache.CacheHolder;
import com.xinfan.wxshop.common.cache.CacheLoader;
import com.xinfan.wxshop.common.context.AppContextHolder;

/**
 * 
 * 閰嶇疆鍙傛暟鍔犺浇鍣�
 * 
 * @author cyp
 * 
 */
public class ConfigCacheLoader extends CacheLoader {

	@Override
	public boolean load() {

		HashMap maps = new HashMap();
		ConfigDao ConfigDao = AppContextHolder.getBean(ConfigDao.class);

		List<Config> list = (ArrayList) ConfigDao.getList();
		for (int i = 0; i < list.size(); i++) {
			Config config = list.get(i);
			String key = config.getId();
			maps.put(key.toUpperCase(), config);
		}

		CacheHolder.getInstance().getCacheProvider(BizConstants.PROVIDER_COMMON_CACHE).setAttribute(this.getCacheKey(), maps, -1);

		return true;
	}

	@Override
	public boolean refresh() {
		return load();
	}

}
