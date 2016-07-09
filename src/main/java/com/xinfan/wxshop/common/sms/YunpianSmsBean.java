package com.xinfan.wxshop.common.sms;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xinfan.wxshop.common.config.ParamtersFileConfig;
import com.xinfan.wxshop.common.util.HttpClientUtils;

public class YunpianSmsBean {

	private static final Logger logger = LoggerFactory.getLogger(YunpianSmsBean.class);

	public static final long REGISTER_TPL_ID;

	public static final long ORDER_TPL_ID;

	public static final long CHANGE_PASSWORD_TPL_ID;
	
	public static final long ORDER_CONFIRM_TPL_ID;
	
	/**
	 * 服务http地址
	 */
	private static String BASE_URI = "http://yunpian.com";
	/**
	 * 服务版本号
	 */
	private static String VERSION = "v1";
	/**
	 * 查账户信息的http地址
	 */
	private static String URI_GET_USER_INFO = BASE_URI + "/" + VERSION + "/user/get.json";
	/**
	 * 通用发送接口的http地址
	 */
	private static String URI_SEND_SMS = BASE_URI + "/" + VERSION + "/sms/send.json";
	/**
	 * 模板发送接口的http地址
	 */
	private static String URI_TPL_SEND_SMS = BASE_URI + "/" + VERSION + "/sms/tpl_send.json";

	private static final String AIP_KEY;

	static {
		REGISTER_TPL_ID = ParamtersFileConfig.getInstance().getLong("yunpian_sms_register_tpl_id");
		ORDER_TPL_ID = ParamtersFileConfig.getInstance().getLong("yunpian_sms_order_tpl_id");
		CHANGE_PASSWORD_TPL_ID = ParamtersFileConfig.getInstance().getLong("yunpian_sms_change_password_tpl_id");
		AIP_KEY = ParamtersFileConfig.getInstance().getString("yunpian_sms_apikey");
		ORDER_CONFIRM_TPL_ID = ParamtersFileConfig.getInstance().getLong("yunpian_sms_order_confirm_tpl_id");
		
	}

	/**
	 * 发短信
	 * 
	 * @param apikey
	 *            apikey
	 * @param text
	 *            　短信内容　
	 * @param mobile
	 *            　接受的手机号
	 * @return json格式字符串
	 * @throws IOException
	 */
	public static String sendSms(String text, String mobile) throws IOException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("apikey", AIP_KEY);
		paramMap.put("text", text);
		paramMap.put("mobile", mobile);

		// 发送请求
		HttpResponse httpresponse = HttpClientUtils.post(URI_SEND_SMS, paramMap);
		// 获取返回数据
		HttpEntity entity = httpresponse.getEntity();
		return EntityUtils.toString(entity);
	}

	/**
	 * 通过模板发送短信
	 * 
	 * @param apikey
	 *            apikey
	 * @param tpl_id
	 *            　模板id
	 * @param tpl_value
	 *            　模板变量值　
	 * @param mobile
	 *            　接受的手机号
	 * @return json格式字符串
	 * @throws IOException
	 */
	public static String tplSendSms(long tpl_id, String tpl_value, String mobile) throws IOException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("apikey", AIP_KEY);
		paramMap.put("tpl_id", String.valueOf(tpl_id));
		paramMap.put("tpl_value", tpl_value);
		paramMap.put("mobile", mobile);
		// 发送请求
		HttpResponse httpresponse = HttpClientUtils.post(URI_TPL_SEND_SMS, paramMap);
		// 获取返回数据
		HttpEntity entity = httpresponse.getEntity();
		return EntityUtils.toString(entity);
	}

}
