package com.xinfan.wxshop.business.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xinfan.wxshop.business.constants.BizConstants;
import com.xinfan.wxshop.business.dao.DictDao;
import com.xinfan.wxshop.business.entity.Dict;
import com.xinfan.wxshop.common.cache.CacheHolder;
import com.xinfan.wxshop.common.cache.CacheLoader;
import com.xinfan.wxshop.common.context.AppContextHolder;

/**
 * 
 */
public class DictCacheLoader extends CacheLoader {

	@Override
	public boolean load() {

		HashMap maps = new HashMap();

		DictDao dao = AppContextHolder.getBean(DictDao.class);

		List<Dict> list = (ArrayList) dao.selectAll();
		for (int i = 0; i < list.size(); i++) {
			Dict config = list.get(i);
			String key = config.getDtype();

			List<Dict> values = (List<Dict>) maps.get(key.toUpperCase());
			if (values == null) {
				values = new ArrayList<Dict>();
				values.add(config);
				maps.put(key.toUpperCase(), values);
			} else {
				values.add(config);
			}
		}

		CacheHolder.getInstance().getCacheProvider(BizConstants.PROVIDER_COMMON_CACHE).setAttribute(this.getCacheKey(), maps, -1);

		return true;
	}

	@Override
	public boolean refresh() {
		return load();
	}

}
