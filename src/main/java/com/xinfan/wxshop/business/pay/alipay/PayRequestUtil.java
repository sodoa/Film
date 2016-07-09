package com.xinfan.wxshop.business.pay.alipay;

import java.util.HashMap;
import java.util.Map;

public class PayRequestUtil {

	public static String alipayPc(OrderPay pay){
		
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_email", AlipayConfig.seller_email);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", "1");//商品购买
		sParaTemp.put("notify_url", pay.getNotify_url());
		sParaTemp.put("return_url", pay.getReturn_url());
		sParaTemp.put("out_trade_no", pay.getOrderNo());
		sParaTemp.put("subject", "购买"+pay.getOrderName());
		sParaTemp.put("total_fee", String.valueOf(pay.getFee()));
		//sParaTemp.put("body", "购买"+serviceName+",次数："+order.getQuantity()+",共计:"+order.getAmount()+" 元。");
		//sParaTemp.put("show_url", show_url);
		//sParaTemp.put("anti_phishing_key", "");
		sParaTemp.put("exter_invoke_ip", pay.getUserIp());
		
		return AlipaySubmit.buildRequest(sParaTemp,"get","确认");
	}
}
