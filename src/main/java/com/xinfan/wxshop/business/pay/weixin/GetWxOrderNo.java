package com.xinfan.wxshop.business.pay.weixin;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xinfan.wxshop.common.util.JSONUtils;

public class GetWxOrderNo {

	private static final Logger logger = LoggerFactory.getLogger(GetWxOrderNo.class);

	public static String getPayNo(String url, String xmlParam) {

		String prepay_id = "";
		try {

			Map<String, String> dataMap = WxHttpsUtils.SSLPostXmlWithResult(url, xmlParam);

			if (!dataMap.containsKey("return_code")) {
				return prepay_id;
			}

			String return_code = (String) dataMap.get("return_code");
			String return_msg = (String) dataMap.get("return_msg");

			if (logger.isDebugEnabled()) {
				logger.debug(JSONUtils.toJSONString(dataMap));
			}

			if ("SUCCESS".equalsIgnoreCase(return_code)) {
				prepay_id = (String) dataMap.get("prepay_id");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return prepay_id;
	}

}