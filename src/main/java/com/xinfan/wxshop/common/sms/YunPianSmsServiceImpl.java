package com.xinfan.wxshop.common.sms;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xinfan.wxshop.business.cache.utils.ParamterUtils;
/**
 * 云片短信http接口
 * @author jiangyx
 * @since 2013-12-1
 */
public class YunPianSmsServiceImpl implements SmsService{
	private static final Logger logger = LoggerFactory.getLogger(YunpianSmsBean.class);
	
	@Override
	public void sendRegisterValidSms(final String mobile,final String validCode) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String rs = YunpianSmsBean.tplSendSms(YunpianSmsBean.REGISTER_TPL_ID, "#code#="+validCode, mobile);
					JSONObject json = JSONObject.parseObject(rs);
					if(json != null && "0".equals(json.getString("code"))){
						logger.info("发送注册验证码成功,"+rs);
					}else{
						logger.error("发送注册验证码失败,"+rs);
					}
				} catch (IOException e) {
					logger.error("发送注册短信异常",e);
				}
			}
		}).start();
		
	}
	
	@Override
	public void sendChangePwdValidSms(final String mobile,final String validCode) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					String rs = YunpianSmsBean.tplSendSms(YunpianSmsBean.CHANGE_PASSWORD_TPL_ID, "#code#="+validCode, mobile);
					JSONObject json = JSONObject.parseObject(rs);
					if(json != null && "0".equals(json.getString("code"))){
						logger.info("发送修改密码验证码成功,"+rs);
					}else{
						logger.error("发送修改密码验证码失败,"+rs);
					}
				} catch (IOException e) {
					logger.error("发送修改密码短信异常",e);
				}
			}
		}).start();
	}

	@Override
	public void sendOderPaySms(final String mobile, final String message) {
		
		String ifOpen = ParamterUtils.getString("order_msg_tip", "1");
		if("1".equals(ifOpen)){
		
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						String rs = YunpianSmsBean.tplSendSms(YunpianSmsBean.ORDER_TPL_ID, "#code#="+message, mobile);
						JSONObject json = JSONObject.parseObject(rs);
						if(json != null && "0".equals(json.getString("code"))){
							logger.info("发送定单提示成功,"+rs);
						}else{
							logger.error("发送定单提示失败,"+rs);
						}
					} catch (IOException e) {
						logger.error("发送注册短信异常",e);
					}
				}
			}).start();
		
		}
	}
	

	@Override
	public void sendOrderConfirmMsg(final String mobile, final String message) {
		
		String ifOpen = ParamterUtils.getString("order_msg_tip", "1");
		if("1".equals(ifOpen)){
		
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						String rs = YunpianSmsBean.tplSendSms(YunpianSmsBean.ORDER_CONFIRM_TPL_ID, "#code#="+message, mobile);
						JSONObject json = JSONObject.parseObject(rs);
						if(json != null && "0".equals(json.getString("code"))){
							logger.info("发送定单确认信息成功,"+rs);
						}else{
							logger.error("发送定单确认信息失败,"+rs);
						}
					} catch (IOException e) {
						logger.error("发送定单确认信息异常",e);
					}
				}
			}).start();
		
		}
	}	
}
