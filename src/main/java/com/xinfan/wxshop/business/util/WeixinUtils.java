package com.xinfan.wxshop.business.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.xinfan.wxshop.business.pay.weixin.CommonUtil;
import com.xinfan.wxshop.business.vo.OAuthInfo;
import com.xinfan.wxshop.business.vo.UserInfo;

public class WeixinUtils {

	private static final Logger logger = LoggerFactory.getLogger(WeixinUtils.class);

	public static OAuthInfo getOAuthOpenId(String appid, String secret, String code) {
		OAuthInfo oAuthInfo = null;

		StringBuilder url = new StringBuilder();
		url.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=").append(appid);
		url.append("&secret=").append(secret);
		url.append("&code=").append(code);
		url.append("&grant_type=").append("authorization_code");

		JSONObject jsonObject = CommonUtil.httpsRequest(url.toString(), "GET", null);

		if (null != jsonObject) {
			try {
				oAuthInfo = new OAuthInfo();
				oAuthInfo.setAccessToken(jsonObject.getString("access_token"));
				oAuthInfo.setExpiresIn(jsonObject.getIntValue("expires_in"));
				oAuthInfo.setRefreshToken(jsonObject.getString("refresh_token"));
				oAuthInfo.setOpenId(jsonObject.getString("openid"));
				oAuthInfo.setScope(jsonObject.getString("scope"));
			} catch (JSONException e) {
				oAuthInfo = null;
				logger.error("网页授权获取openId失败 errcode:{} errmsg:{}", jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return oAuthInfo;
	}

	public static UserInfo getUserInfo(String accessToken, String openId) {

		StringBuilder url = new StringBuilder();
		url.append("https://api.weixin.qq.com/sns/userinfo?access_token=").append(accessToken);
		url.append("&openid=").append(openId);
		url.append("&lang=zh_CN");

		JSONObject jsonObject = CommonUtil.httpsRequest(url.toString(), "GET", null);

		if (jsonObject != null) {
			String errcode = jsonObject.getString("errcode");
			String errmsg = jsonObject.getString("errmsg");
			if (errcode != null && errcode.trim().length() == 0) {
				logger.error("auto login getUserInfo error :" + errcode + "," + errmsg);
				return null;
			}

			String openid = jsonObject.getString("openid");
			String nickname = jsonObject.getString("nickname");
			String sex = jsonObject.getString("sex");
			String province = jsonObject.getString("province");
			String city = jsonObject.getString("city");
			String country = jsonObject.getString("country");
			String headimgurl = jsonObject.getString("headimgurl");

			UserInfo userInfo = new UserInfo();
			userInfo.setOpenid(openid);
			userInfo.setNickname(nickname);
			userInfo.setSex(sex);
			userInfo.setCountry(country);
			userInfo.setCity(city);
			userInfo.setHeadimgurl(headimgurl);
			userInfo.setProvince(province);

			return userInfo;
		}

		return null;
	}

	public static Integer getScanEventFromId(String eventKey) {

		if (eventKey == null || eventKey.length() == 0) {
			return null;
		}

		try {
			String fromId = eventKey.replaceAll("qrscene_", "");
			int intId = Integer.parseInt(fromId);
			return intId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
