package com.xinfan.wxshop.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xinfan.wxshop.common.sms.YunpianSmsBean;

public class HttpUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(YunpianSmsBean.class);

	public static String httpaddress = "http://localhost:7001/blue/MainService";
	
	public static HttpClient httpclient = new DefaultHttpClient();

	public static JSONObject httpInvokerJson(Map params) {

		List<NameValuePair> formparams = toNameValues(params);
		// 创建默认的httpClient实例.
		HttpPost httppost = new HttpPost(httpaddress);
		
		long start = System.currentTimeMillis();

		try {
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(
					formparams, "UTF-8");
			httppost.setEntity(uefEntity);

			System.out.println("executing request " + httppost.getURI());
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				System.out.println("--------------------------------------");
				String msg = EntityUtils.toString(entity, "UTF-8");
				System.out.println("Response content: " + msg);
				System.out.println("--------------------------------------");
				long end = System.currentTimeMillis();
				System.out.println("cost :" + (end-start)*0.1/100);
				
				return JSONUtils.parseObject(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return new JSONObject();
	}

	public static String httpInvoker(List<NameValuePair> formparams) {

		// 创建默认的httpClient实例.
		HttpClient httpclient = new DefaultHttpClient();
		// 创建httppost
		HttpPost httppost = new HttpPost("http://localhost:7002/ws/service");

		try {
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(
					formparams, "UTF-8");
			httppost.setEntity(uefEntity);

			System.out.println("executing request " + httppost.getURI());
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				System.out.println("--------------------------------------");
				String msg = EntityUtils.toString(entity, "UTF-8");
				System.out.println("Response content: " + msg);
				System.out.println("--------------------------------------");
				return msg;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return null;
	}

	public static List<NameValuePair> toNameValues(Map<String,String> map) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();

		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			String val = String.valueOf(map.get(key));
			formparams.add(new BasicNameValuePair(key, val));
		}
		return formparams;
	}

}
