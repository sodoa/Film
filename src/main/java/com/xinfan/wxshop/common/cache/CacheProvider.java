package com.xinfan.wxshop.common.cache;

import java.io.Serializable;

/**
 * 
 * 通用缓存接口
 * 
 * @author cyp
 *
 */
public interface CacheProvider {
	
	public Object getAttribute(String root, String name);
	
	public Object getAttribute(String root);

	public void setAttribute(String root, String name, Serializable value, int exp);
	
	public void setAttribute(String root,Serializable value, int exp);

	public void clear(String root);

	public boolean exist(String root);

}
