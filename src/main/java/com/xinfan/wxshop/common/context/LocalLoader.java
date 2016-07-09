package com.xinfan.wxshop.common.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LocalLoader {
	
	public void contextInitialized(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/config/**/*.bean.xml");
		AppContextHolder.setContext(context);
	}
	
}
