package com.xinfan.wxshop.business.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xinfan.wxshop.common.base.DataMap;

/**
 * 
 * BEAN处理帮助类
 * 
 * @author cyp
 *
 */
public class BeanUtils {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(BeanUtils.class);
	
	
	/**
	 * 将pojo对象属性转换成map
	 * 
	 * @param bean
	 * @return
	 */
	public static Map toMap(Object bean) {
		Map hashMap = new HashMap();
		try {
			Class c = bean.getClass();
			Method m[] = c.getDeclaredMethods();
			for (int i = 0; i < m.length; i++) {
				String name = m[i].getName();
				if (name.startsWith("get")) {
					name = name.substring(3);
					hashMap.put(name.toUpperCase(), m[i].invoke(bean, new Object[0]));
				}
			}
		} catch (Throwable e) {
			LOG.error(e.getMessage(), e);
		}
		return hashMap;
	}
	
	public static DataMap toDataMap(Object bean) {
		DataMap hashMap = new DataMap();
		try {
			Class c = bean.getClass();
			Method m[] = c.getMethods();
			for (int i = 0; i < m.length; i++) {
				String name = m[i].getName();
				if (name.startsWith("get")) {
					name = name.substring(3);
					hashMap.put(name, m[i].invoke(bean, new Object[0]));
				}
			}
		} catch (Throwable e) {
			LOG.error(e.getMessage(), e);
		}
		return hashMap;
	}
	
	public static List<DataMap> toDataMapList(List list) {
		List<DataMap> mlist = new ArrayList<DataMap>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				mlist.add(toDataMap(list.get(i)));
			}
		}
		return mlist;
	}
}
