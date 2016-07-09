package com.xinfan.wxshop.business.pay.weixin;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xinfan.wxshop.business.util.WeiXinSessionManager;
import com.xinfan.wxshop.common.config.ClassPathFileConfig;

public class MenuManger {

	private static final Logger logger = LoggerFactory.getLogger(MenuManger.class);

	private static String create_url = "https://api.weixin.qq.com/cgi-bin/menu/create";// ?access_token=ACCESS_TOKEN

	private static String delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete";// ?access_token=ACCESS_TOKEN

	public static String createMenu() {

		String msg = null;

		try {
			String menuJson = FileUtils.readFileToString(ClassPathFileConfig.getClassPathFile("/config/weixin_menu.json"));
			logger.info("weixin menu json : " + menuJson);

			String accessToken = WeiXinSessionManager.getAccessToken();

			StringBuffer bufferUrl = new StringBuffer(create_url);
			bufferUrl.append("?").append("access_token=").append(accessToken);
			JSONObject result = CommonUtil.httpsRequest(bufferUrl.toString(), "POST", menuJson);

			if (result != null) {
				logger.debug(result.toString());
				String errcode = result.getString("errcode");
				String errmsg = result.getString("errmsg");

				msg = errmsg;
			}

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			msg = e.getMessage();
		}

		return msg;
	}

	public static String deleteMenu() {
		String msg = null;

		String accessToken = WeiXinSessionManager.getAccessToken();

		StringBuffer bufferUrl = new StringBuffer(delete_url);
		bufferUrl.append("?").append("access_token=").append(accessToken);

		JSONObject result = CommonUtil.httpsRequest(bufferUrl.toString(), "POST", "");

		if (result != null) {
			logger.debug(result.toString());
			String errmsg = result.getString("errmsg");
			msg = errmsg;
		}

		return msg;

	}

	public static String getSubscribeContent() {

		String msg = null;

		try {
			String menuJson = FileUtils.readFileToString(ClassPathFileConfig.getClassPathFile("/config/weixin_subscribe.json"));
			logger.info("weixin menu json : " + menuJson);
			msg = menuJson;

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			msg = e.getMessage();
		}

		return msg;
	}
}
