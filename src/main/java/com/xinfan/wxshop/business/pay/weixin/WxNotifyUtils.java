package com.xinfan.wxshop.business.pay.weixin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xinfan.wxshop.business.util.DateHelper;
import com.xinfan.wxshop.business.util.ThreadUtils;
import com.xinfan.wxshop.business.util.WeiXinSessionManager;
import com.xinfan.wxshop.common.config.FileConfig;
import com.xinfan.wxshop.common.util.JSONUtils;

public class WxNotifyUtils {

	public static final String url = "https://api.weixin.qq.com/cgi-bin/message/template/send";

	public static final String click_url = FileConfig.getInstance().getString("weixin.messager.clickurl");

	protected static final Logger logger = LoggerFactory.getLogger(WxNotifyUtils.class);

	public static void customerDownlineJoinNotify(final String openid, final String wx, final String level1) {

		try {

			ThreadUtils.run(new Thread() {

				@Override
				public void run() {
					Map json = new HashMap();
					json.put("template_id", "zj8AMWJCAgCXfFMltfPgXwt62izSfydRQJp2yz57UtM");
					json.put("touser", openid);
					json.put("url", click_url);

					Map<String, WxTemplateData> data = new HashMap<String, WxTemplateData>();
					WxTemplateData firstData = new WxTemplateData("您有新的下级入队", "#173177");
					data.put("first", firstData);

					WxTemplateData keyword1Data = new WxTemplateData(wx, "#173177");
					data.put("keyword1", keyword1Data);

					WxTemplateData keyword2Data = new WxTemplateData(DateHelper.formatFull(new Date()), "#173177");
					data.put("keyword2", keyword2Data);
					// keyword3
					WxTemplateData keyword3Data = new WxTemplateData(wx, "#173177");
					data.put("keyword3", keyword3Data);

					WxTemplateData remarkData = new WxTemplateData("第" + level1 + "级分销", "#173177");
					data.put("remark", remarkData);

					json.put("data", data);

					String jsonString = JSONUtils.toJSONString(json);

					if (logger.isDebugEnabled()) {
						logger.debug("WxNotifyUtils:" + jsonString);
					}

					// remark

					String tokenUrl = url + "?access_token=" + WeiXinSessionManager.getAccessToken();
					JSONObject jsonResult = CommonUtil.httpsRequest(tokenUrl, "POST", jsonString);
					if (logger.isDebugEnabled()) {
						if (jsonResult != null) {
							logger.debug("WxNotifyUtils:" + jsonResult.toString());
						}
					}
				}
			});

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	public static void customerPointsJoinNotify(final String openid, final String wx, final String money, final String totalMoney, final String level1) {

		ThreadUtils.run(new Thread() {

			@Override
			public void run() {
				Map json = new HashMap();
				json.put("template_id", "1GQMmWWR0Bh9gzNMdW3nMvubvO-Q9h1UvYno7nF-_0I");
				json.put("touser", openid);
				json.put("url", click_url);

				Map<String, WxTemplateData> data = new HashMap<String, WxTemplateData>();
				WxTemplateData firstData = new WxTemplateData("亲爱的会员，您的会员卡有新积分到账", "#173177");
				data.put("first", firstData);

				WxTemplateData keyword1Data = new WxTemplateData(wx, "#173177");
				data.put("keyword1", keyword1Data);

				WxTemplateData keyword2Data = new WxTemplateData(DateHelper.formatFull(new Date()), "#173177");
				data.put("keyword2", keyword2Data);
				// keyword3
				WxTemplateData keyword3Data = new WxTemplateData("分红积分", "#173177");
				data.put("keyword3", keyword3Data);

				WxTemplateData keyword4Data = new WxTemplateData(money+" 元", "#173177");
				
				data.put("keyword4", keyword4Data);

				WxTemplateData keyword5Data = new WxTemplateData(totalMoney+" 元", "#173177");
				data.put("keyword5", keyword5Data);

				WxTemplateData remarkData = new WxTemplateData("第" + level1 + "级分销，来至微信" + wx, "#173177");
				data.put("remark", remarkData);

				json.put("data", data);

				String jsonString = JSONUtils.toJSONString(json);

				if (logger.isDebugEnabled()) {
					logger.debug("WxNotifyUtils:" + jsonString);
				}

				// remark

				String tokenUrl = url + "?access_token=" + WeiXinSessionManager.getAccessToken();
				JSONObject jsonResult = CommonUtil.httpsRequest(tokenUrl, "POST", jsonString);
				if (logger.isDebugEnabled()) {
					if (jsonResult != null) {
						logger.debug("WxNotifyUtils:" + jsonResult.toString());
					}
				}
			}
		});

	}
	
	public static void customerShareJoinNotify(final String openid, final String wx, final Date expirydate) {

		ThreadUtils.run(new Thread() {

			@Override
			public void run() {
				Map json = new HashMap();
				json.put("template_id", "1GQMmWWR0Bh9gzNMdW3nMvubvO-Q9h1UvYno7nF-_0I");
				json.put("touser", openid);
				json.put("url", click_url);

				Map<String, WxTemplateData> data = new HashMap<String, WxTemplateData>();
				WxTemplateData firstData = new WxTemplateData("亲爱的会员，有新的会员通过您的分享加入我们", "#173177");
				data.put("first", firstData);

				WxTemplateData keyword1Data = new WxTemplateData(wx, "#173177");
				data.put("keyword1", keyword1Data);

				WxTemplateData keyword2Data = new WxTemplateData(DateHelper.formatFull(new Date()), "#173177");
				data.put("keyword2", keyword2Data);
				// keyword3
				WxTemplateData keyword3Data = new WxTemplateData("你的到期日期为"+ DateHelper.formatFull(expirydate), "#173177");
				data.put("keyword3", keyword3Data);

				WxTemplateData remarkData = new WxTemplateData("来至微信" + wx +"的加入", "#173177");
				data.put("remark", remarkData);

				json.put("data", data);

				String jsonString = JSONUtils.toJSONString(json);

				if (logger.isDebugEnabled()) {
					logger.debug("WxNotifyUtils:" + jsonString);
				}

				// remark

				String tokenUrl = url + "?access_token=" + WeiXinSessionManager.getAccessToken();
				JSONObject jsonResult = CommonUtil.httpsRequest(tokenUrl, "POST", jsonString);
				if (logger.isDebugEnabled()) {
					if (jsonResult != null) {
						logger.debug("WxNotifyUtils:" + jsonResult.toString());
					}
				}
			}
		});

	}

}
