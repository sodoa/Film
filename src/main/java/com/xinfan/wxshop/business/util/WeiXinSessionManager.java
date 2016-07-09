package com.xinfan.wxshop.business.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xinfan.wxshop.business.pay.weixin.CommonUtil;
import com.xinfan.wxshop.common.config.FileConfig;

public class WeiXinSessionManager {
	
	private final static Logger logger = LoggerFactory
			.getLogger(WeiXinSessionManager.class);
	
	public static Map<String,Map<String,String>> sessions = new HashMap<String,Map<String,String>>();
	
	
	public static String getTicketToken(){

		Map<String,String> token_map = sessions.get("ticket_token_map");
		
		if(token_map == null){
			refreshTicketToken();
		}

		token_map = sessions.get("ticket_token_map");
		
		if(token_map !=null){
			long now = new Date().getTime();
			long timeout = Long.parseLong(token_map.get("timeout"));
			
			if(now >= timeout){
				refreshTicketToken();
				token_map = sessions.get("ticket_token_map");
			}
			return token_map.get("ticket_token");
		}
		
		return null;
		
	}
	
	public static boolean refreshTicketToken(){
		
		String access_token = getAccessToken();
		if(access_token!=null){
			
			String ticketURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
			
			JSONObject ticketJSONObject = CommonUtil.httpsRequest(ticketURL, "GET", null);
			
			if(ticketJSONObject.containsKey("ticket")){
				
				String ticket_expires_in = ticketJSONObject.getString("expires_in");
				String jsapi_ticket = ticketJSONObject.getString("ticket");
				
				Map<String,String> ticket_token_map = new HashMap<String,String>();
				long now = new Date().getTime();
				long timeout = now + Long.parseLong(ticket_expires_in)*1000/2;
				ticket_token_map.put("ticket_token", jsapi_ticket);
				ticket_token_map.put("refresh_time", String.valueOf(now));
				ticket_token_map.put("timeout", String.valueOf(timeout));
				
				sessions.put("ticket_token_map", ticket_token_map);
				
				return true;
	
			}
			else{
				logger.error(ticketJSONObject.toJSONString());
			}
		}
		
		return false;
		
	}
	
	
	public static String getAccessToken(){

		Map<String,String> access_token_map = sessions.get("access_token_map");
		
		if(access_token_map == null){
			refreshAccessToken();
		}

		access_token_map = sessions.get("access_token_map");
		
		if(access_token_map !=null){
			long now = new Date().getTime();
			long timeout = Long.parseLong(access_token_map.get("timeout"));
			
			if(now >= timeout){
				refreshAccessToken();
				access_token_map = sessions.get("access_token_map");
			}
			return access_token_map.get("access_token");
		}
		
		return null;
		
	}
	
	public static boolean refreshAccessToken(){

		Map<String,String> access_token_map = sessions.get("access_token_map");

		String appid = FileConfig.getInstance().getString("weixin.appid");
		String appsecret = FileConfig.getInstance().getString("weixin.appsecret");
		String accessTokenURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret+"";
		
		JSONObject jsonObject = CommonUtil.httpsRequest(accessTokenURL, "GET", null);
		
		if(jsonObject.containsKey("access_token")){
			String access_token = jsonObject.getString("access_token");
			String token_expires_in = jsonObject.getString("expires_in");
			
			access_token_map = new HashMap<String,String>();
			
			long now = new Date().getTime();
			long timeout = now + Long.parseLong(token_expires_in)*1000/2;
			access_token_map.put("access_token", access_token);
			access_token_map.put("refresh_time", String.valueOf(now));
			access_token_map.put("timeout", String.valueOf(timeout));
			sessions.put("access_token_map", access_token_map);
			
			return true;
		}
		else{
			logger.error(jsonObject.toJSONString());
			return false;
		}
	}
	
}
