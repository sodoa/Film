package com.xinfan.wxshop.common.context;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * 
 * 容器上下文持有类
 * 
 * @author cyp
 *
 */
public class AppContextHolder {
	
	private static ApplicationContext context;
	
	private static Object lock = new Object(); 
	
	public static Object getBean(String name){
		if (context == null) {
			context = ContextLoader.getCurrentWebApplicationContext();
		}
		
		if(context == null){
			return null;
		}
		
		return context.getBean(name);
	}
	
	public static <T> T getBean(Class<T> requiredType){
		if (context == null) {
			context = ContextLoader.getCurrentWebApplicationContext();
		}
		
		if(context == null){
			return null;
		}
		
		return context.getBean(requiredType);
	}
	
	public static <T> Map<String, T> getBeansOfType(Class<T> type){
		return context.getBeansOfType(type);
	}

	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext context) {
		synchronized (lock) {
			AppContextHolder.context = context;
		}
	}
	
}
