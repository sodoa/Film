package com.xinfan.wxshop.common.context;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.xinfan.wxshop.business.constants.BizConstants;
import com.xinfan.wxshop.business.util.RequestUtils;
import com.xinfan.wxshop.business.vo.LoginSession;

/**
 * @author huangmin
 * @DATE 2016年7月19日上午12:09:00
 * 
 */
public class CountLineListener implements HttpSessionListener,HttpSessionAttributeListener{   
	 
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
    		
    		LoginSession sessionMap = (LoginSession) event.getSession().getAttribute("BizConstants.CUSTOMER_USER_SESSION_KEY");
    		if(sessionMap!=null){
        		System.out.println("销毁session......");   
    	        
        		event.getSession().removeAttribute(BizConstants.CUSTOMER_USER_SESSION_KEY);
        	}
	        

    	}
       
   }  
    
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name=event.getName();
        System.out.println("########################name=" + name + ";value=" + event.getValue());
		if (BizConstants.CUSTOMER_USER_SESSION_KEY.equals(name)) {
			synchronized (LOCK) {
				int count = OnlineManager.getInstance().remove();
				
				 System.out.println("########################count" + count);
			}
		}
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		
        String name=event.getName();
        System.out.println("########################name=" + name + ";value=" + event.getValue());
		if (BizConstants.CUSTOMER_USER_SESSION_KEY.equals(name)) {
			synchronized (LOCK) {
				int count = OnlineManager.getInstance().add();
				
				 System.out.println("########################count" + count);
			}
		}
		
	}
  
}  