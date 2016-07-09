package com.xinfan.wxshop.business.util;

import java.text.SimpleDateFormat;
import java.util.Random;

import com.xinfan.wxshop.business.constants.BizConstants;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.util.TimeUtils;

public class LoginSessionUtils {

	public static final String CUSTOMER_USER_SESSION_KEY = BizConstants.CUSTOMER_USER_SESSION_KEY;

	public static final String MANAGER_USER_SESSION_KEY = BizConstants.MANAGER_USER_SESSION_KEY;

	public static DataMap getCustomerUserSessionMap() {
		DataMap sessionMap = (DataMap) RequestUtils.getSession().getAttribute(CUSTOMER_USER_SESSION_KEY);
		return sessionMap;
	}
	
	public static boolean isCustomerLogin() {
		if(getCustomerUserSessionMap() != null){
			return true;
		}
		return false;
	}

	public static void setCustomerUserSessionMap(DataMap map) {
		RequestUtils.getSession().setAttribute(CUSTOMER_USER_SESSION_KEY, map);
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
		DataMap sessionMap = (DataMap) RequestUtils.getSession().getAttribute(MANAGER_USER_SESSION_KEY);
		return sessionMap;
	}

	public static Integer getCustomerIdFromUserSessionMap() {
		DataMap sessionMap = (DataMap) RequestUtils.getSession().getAttribute(CUSTOMER_USER_SESSION_KEY);
		return sessionMap.getInt("customerId");
	}

	public static String getCustomerSessionId() {
		String sid = String.valueOf(RequestUtils.getSession().getAttribute("#sessionid"));
		if(sid!=null && sid.length()>5){
			return sid;
		}
		String sessionId = RequestUtils.getSession().getId();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String newSid = sessionId+sdf.format(TimeUtils.getCurrentTime()) +new Random().nextInt(9999);
		RequestUtils.getSession().setAttribute("#sessionid", newSid);
		return newSid;
	}
}
