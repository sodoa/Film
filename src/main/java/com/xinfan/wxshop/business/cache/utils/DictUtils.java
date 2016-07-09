package com.xinfan.wxshop.business.cache.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.xinfan.wxshop.business.constants.BizConstants;
import com.xinfan.wxshop.business.entity.Dict;
import com.xinfan.wxshop.common.cache.CacheHolder;

public class DictUtils {

	public static List<Dict> getDictList(String dtype) {

		Map cache = (Map) CacheHolder.getInstance().getCacheProvider(BizConstants.PROVIDER_COMMON_CACHE).getAttribute(BizConstants.CACHE_KEY_DICT_CACHE);

		List<Dict> list = (ArrayList<Dict>) cache.get(dtype.toUpperCase());

		if (list != null) {
			return list;
		}

		return Collections.EMPTY_LIST;
	}

	public static String getDictValueName(String dtype, String vvalue) {
		List<Dict> list = getDictList(dtype);
		if (list != null && !list.isEmpty()) {

			for (Dict item : list) {
				if (item.getVvalue() != null && item.getVvalue().equals(vvalue)) {
					return item.getVname();
				}
			}
		}

		return "";
	}

}
