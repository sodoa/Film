package com.xinfan.wxshop.business.web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionTimeoutListener
 *
 */
public class SessionTimeoutListener implements HttpSessionListener{

    public void sessionCreated(HttpSessionEvent event) {
    	
    	
    }

    public void sessionDestroyed(HttpSessionEvent event) {
    	
/*    	DataMap sessionMap = (DataMap)event.getSession().getAttribute("sessionMap");
    	if(sessionMap!=null){
    		LoginService LoginService = AppContextHolder.getBean(LoginService.class);
    		LoginService.logout(sessionMap);
    		event.getSession().removeAttribute("sessionMap");
    	}
    	*/
    }
	
}
