package com.xinfan.wxshop.business.util;

import com.xinfan.wxshop.business.constants.BizConstants;
import com.xinfan.wxshop.business.vo.LoginSession;
import com.xinfan.wxshop.common.base.DataMap;

public class LoginSessionUtils {

	public static final String CUSTOMER_USER_SESSION_KEY = BizConstants.CUSTOMER_USER_SESSION_KEY;

	public static final String MANAGER_USER_SESSION_KEY = BizConstants.MANAGER_USER_SESSION_KEY;

	public static Object LOCK = new Object();  
	
	public static LoginSession getCustomerUserSessionMap() {
		LoginSession sessionMap = (LoginSession) RequestUtils.getSession()
				.getAttribute(CUSTOMER_USER_SESSION_KEY);
		return sessionMap;
	}

	public static boolean isCustomerLogin() {
		if (getCustomerUserSessionMap() != null) {
			return true;
		}
		return false;
	}

	public static void setCustomerUserSessionMap(LoginSession map) {
		RequestUtils.getSession().setAttribute(CUSTOMER_USER_SESSION_KEY, map);
		
		synchronized (LOCK) {
            Integer count=(Integer)RequestUtils.getSession().getAttribute("count");   
            if(count==null){   
                count=new Integer(1);   
            }else{   
                int co = count.intValue( );   
                count= new Integer(co+1);   
            }   
            System.out.println("当前用户人数："+count);   
            RequestUtils.getSession().setAttribute("count", count);//保存人数   
    	}
	}

	public static void setManagerUserSessionMap(DataMap map) {
		RequestUtils.getSession().setAttribute(MANAGER_USER_SESSION_KEY, map);
	}

	public static void setExpireManagerSessionMap() {
		RequestUtils.getSession().invalidate();
	}

	public static void setExpireCustomerSessionMap() {
		RequestUtils.getSession().invalidate();
	}

	public static DataMap getManagerUserSessionMap() {
		DataMap sessionMap = (DataMap) RequestUtils.getSession().getAttribute(
				MANAGER_USER_SESSION_KEY);
		return sessionMap;
	}

	public static Integer getCustomerIdFromUserSessionMap() {
		LoginSession sessionMap = (LoginSession) RequestUtils.getSession()
				.getAttribute(CUSTOMER_USER_SESSION_KEY);
		return sessionMap.getCustomerId();
	}

}
