package com.xinfan.wxshop.common.context;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.xinfan.wxshop.business.constants.BizConstants;
import com.xinfan.wxshop.business.util.LoginSessionUtils;
import com.xinfan.wxshop.business.util.RequestUtils;
import com.xinfan.wxshop.business.vo.LoginSession;

/**
 * @author huangmin
 * @DATE 2016年7月19日上午12:09:00
 * 
 */
public class CountLineListener implements HttpSessionListener{   
	 
	private static Object LOCK = new Object();  
	
    /*********** 
    * 创建session时调用 
    */   
    public void sessionCreated(HttpSessionEvent event) {   
    	
  
    }   
  
    /************ 
    * 销毁session时调用 
    */   
    public void sessionDestroyed(HttpSessionEvent event) {  
    	synchronized (LOCK) {
    		
    		LoginSession sessionMap = (LoginSession) RequestUtils.getSession()
    				.getAttribute(BizConstants.CUSTOMER_USER_SESSION_KEY);
    		if(sessionMap!=null){
        		System.out.println("销毁session......");   
    	        ServletContext context=event.getSession().getServletContext();   
    	        Integer count=(Integer)context.getAttribute("count");   
    	        int co=count.intValue();   
    	        count=new Integer(co-1);   
    	        context.setAttribute("count", count);   
    	        System.out.println("当前用户人数："+count);
    	        
        		event.getSession().removeAttribute(BizConstants.CUSTOMER_USER_SESSION_KEY);
        	}
	        

    	}
       
   }   
  
}  