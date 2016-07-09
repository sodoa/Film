package com.xinfan.wxshop.business.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 通用Ajax请求结果简单封装
 * 
 * @author cyp
 * 
 */
public class JSONResult extends HashMap {

	private static final long serialVersionUID = -2175294726118087275L;

	public JSONResult() {

	}
	
	public JSONResult putValues(Map values) {
		putAll(values);
		return this;
	}
	
	public JSONResult putValue(String key,Object value) {
		put(key, value);
		return this;
	}

	public JSONResult(int result) {
		this.putResult(result);
		this.putMessage("");
	}

	public JSONResult(int result, String message) {
		this.putResult(result);
		this.putMessage(message);
	}

	public JSONResult putResult(int result) {
		this.put("result", result);
		return this;
	}
	

	public JSONResult putResult(int result,String msg) {
		this.put("result", result);
		this.put("message", msg);
		return this;
	}

	public JSONResult putMessage(String message) {
		this.put("message", message);
		return this;
	}

	public static JSONResult success() {
		return new JSONResult().putResult(0).putMessage("操作成功");
	}

	public static JSONResult error() {
		return new JSONResult().putResult(1).putMessage("操作失败");
	}
	
	public static JSONResult error(String msg) {
		return new JSONResult().putResult(1).putMessage(msg);
	}
}
