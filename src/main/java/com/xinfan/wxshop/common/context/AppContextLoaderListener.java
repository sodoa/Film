package com.xinfan.wxshop.common.context;

import javax.servlet.ServletContextEvent;

import com.xinfan.wxshop.common.cache.CacheHelper;

/**
 * 
 * 定制由web启动的空器加载类
 * 
 * @author cyp
 *
 */
public class AppContextLoaderListener extends
		org.springframework.web.context.ContextLoaderListener {
	
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		//加载完成通知上下文持上类保存 
		
		String contextPath = event.getServletContext().getContextPath();
		event.getServletContext().setAttribute("contextpath", contextPath);
		
		AppContextHolder.setContext(getCurrentWebApplicationContext());
		
		CacheHelper.getInstance();
	}
}
