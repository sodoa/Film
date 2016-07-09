package com.xinfan.wxshop.common.sms;


public interface SmsService {
	/**
	 * 发送注册短信验证码
	 * @param mobile
	 * @param validCode
	 * @return
	 */
	public void sendRegisterValidSms(String mobile,String validCode);
	/**
	 * 发送修改密码短信验证码
	 * @param mobile
	 * @param validCode
	 * @return
	 */
	public void sendChangePwdValidSms(String mobile,String validCode);
	
	
	
	public void sendOderPaySms(String mobile,String message);
	
	public void sendOrderConfirmMsg(final String mobile, final String message);
}
