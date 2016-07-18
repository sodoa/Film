package com.xinfan.wxshop.business.front;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.xinfan.wxshop.business.entity.Bill;
import com.xinfan.wxshop.business.pay.weixin.CommonUtil;
import com.xinfan.wxshop.business.pay.weixin.GetWxOrderNo;
import com.xinfan.wxshop.business.pay.weixin.RequestHandler;
import com.xinfan.wxshop.business.pay.weixin.Sha1Util;
import com.xinfan.wxshop.business.pay.weixin.TenpayUtil;
import com.xinfan.wxshop.business.pay.weixin.WxHttpsUtils;
import com.xinfan.wxshop.business.service.BillService;
import com.xinfan.wxshop.business.service.CustomerService;
import com.xinfan.wxshop.business.service.FilmService;
import com.xinfan.wxshop.business.util.LoginSessionUtils;
import com.xinfan.wxshop.business.util.OrderCreaterUtils;
import com.xinfan.wxshop.business.util.RequestUtils;
import com.xinfan.wxshop.common.config.FileConfig;
import com.xinfan.wxshop.common.security.DesUtils;
import com.xinfan.wxshop.common.util.JSONUtils;

/**
 * @author huangmin
 * @DATE 2016年7月12日下午10:07:04
 * 
 */
@Controller
@RequestMapping("/movie")
public class PayController {

	private static final Logger logger = LoggerFactory
			.getLogger(PayController.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private FilmService filmService;
	
	@Autowired
	private BillService billService;
	

	@RequestMapping("/pay_test.jspx")
	public ModelAndView pay_test(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/front/pay");
		return mv;
	}
	
	@RequestMapping("/paying_test.jspx")
	public ModelAndView paying_test(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/front/paying");
		return mv;
	}

	@RequestMapping("/pay.jspx")
	public void weixin1(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String fid = request.getParameter("fid");
		
		//共账号及商户相关参数
		String appid = FileConfig.getInstance().getString("weixin.appid");
		String backUri = FileConfig.getInstance().getDomainUrlString("weixin.payauth.backurl");
		
		Integer customerId = LoginSessionUtils.getCustomerIdFromUserSessionMap();
		String wxId = LoginSessionUtils.getCustomerUserSessionMap().getWx_id();
		
		String orderNo = OrderCreaterUtils.createOrderNo(String.valueOf(customerId));
		Float money =  1f;
		String describe = "VIP电影包月费用";
		
		Bill bill = new Bill();
		bill.setAmount(money.intValue());
		bill.setCustomerId(customerId);
		bill.setPaytime(new Date());
		bill.setWxId(wxId);
		bill.setOrderid(orderNo);
		
		billService.addOrder(bill);
		
		//String backUri = "http://***/topayServlet";
		//授权后要跳转的链接所需的参数一般有会员号，金额，订单号之类，
		//最好自己带上一个加密字符串将金额加上一个自定义的key用MD5签名或者自己写的签名,
		//比如 Sign = %3D%2F%CS% 
		
		String body = "customerId="+customerId+"&orderNo="+orderNo+"&describe="+describe+"&money="+money+"&fid="+fid;
		
		String desPassword = FileConfig.getInstance().getString("weixin.des.password");
		
		logger.debug("weixin_pay_auth despassword :" + desPassword);
		logger.debug("weixin_pay_auth body :" + body);
		
		String decData = Base64.encodeBase64String(DesUtils.encrypt(body.getBytes("UTF-8"), desPassword));
		
		logger.debug("weixin_pay_auth decData :" + decData);
		
		backUri = backUri+"?data="+decData;
		//URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
		backUri =URLEncoder.encode(backUri);
		
		//scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid=" + appid+
				"&redirect_uri=" +
				 backUri+
				"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		
		logger.debug("weixin_pay_auth url :" + url);
		
		response.sendRedirect(url);
	}
	

	@RequestMapping("/createorder.jspx")
	public String weixin2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String code = request.getParameter("code");
		if(code == null || code.length() ==0 || "null".equals(code)){
			return null;
		}
		
		String authData = request.getParameter("data").replaceAll(" ", "+");
		
		logger.debug("weixin pay order  authData orgi : " + request.getParameter("data"));
		logger.debug("weixin pay order  authData : " + authData);
		
		String desPassword = FileConfig.getInstance().getString("weixin.des.password");
		
		logger.debug("desPassword :" + desPassword);
		String decData = new String(DesUtils.decrypt(Base64.decodeBase64(authData), desPassword),"UTF-8");
		
		logger.debug("weixin pay order  decData : " + decData);
		
		Map<String,String[]> paramterMap = RequestUtils.getQueryParamMap(decData);
		
		logger.debug("weixin pay order  data urlmap : " + JSONUtils.toJSONString(request.getParameterMap()));
		logger.debug("weixin pay order  data paramterMap : " + JSONUtils.toJSONString(paramterMap));

		String customerId = paramterMap.get("customerId")[0];
		String orderNo = paramterMap.get("orderNo")[0];
		
		String money = paramterMap.get("money")[0];
		
		String describe = paramterMap.get("describe")[0];
		String fid = paramterMap.get("fid")[0];
		
		
		logger.debug("weixin pay order  code is :" + code);
		
		// 金额转化为分为单位
		float sessionmoney = Float.parseFloat(money);
		String finalmoney = String.format("%.2f", sessionmoney);
		finalmoney = String.valueOf(Integer.parseInt(finalmoney.replace(".", "")));

		// 商户相关资料
		String appid = FileConfig.getInstance().getString("weixin.appid");
		String appsecret = FileConfig.getInstance().getString("weixin.appsecret");
		String partner = FileConfig.getInstance().getString("weixin.mch_id");
		String partnerkey = appsecret;

		String openId = "";
		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ appid + "&secret=" + appsecret + "&code=" + code
				+ "&grant_type=authorization_code";
		
		JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
		
		logger.debug("weixin pay order  openid  : " + JSONUtils.toJSONString(jsonObject));
		
		if (null != jsonObject) {
			openId = jsonObject.getString("openid");
		}

		// 获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String strReq = strTime + strRandom;

		// 商户号
		String mch_id = partner;
		// 子商户号 非必输
		// String sub_mch_id="";
		// 设备号 非必输
		String device_info = "web";
		// 随机数
		String nonce_str = strReq;
		// 商品描述
		// String body = describe;

		// 商品描述根据情况修改
		// 附加数据
		String attach = customerId;
		// 商户订单号
		String out_trade_no = orderNo;
		int intMoney = Integer.parseInt(finalmoney);

		// 总金额以分为单位，不带小数点
		int total_fee = intMoney;
		
		total_fee = 1;
		
		// 订单生成的机器 IP
		String spbill_create_ip = request.getRemoteAddr();
		// 订 单 生 成 时 间 非必输
		// String time_start ="";
		// 订单失效时间 非必输
		// String time_expire = "";
		// 商品标记 非必输
		// String goods_tag = "";

		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = FileConfig.getInstance().getDomainUrlString("weixin.pay.notifyurl");

		String trade_type = "JSAPI";
		String openid = openId;
		// 非必输
		// String product_id = "";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", describe);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", ""+total_fee);
		// packageParams.put("total_fee", "finalmoney");
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openid);

		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init(appid, appsecret, partnerkey);
		
		logger.debug("packageParams : " + JSONUtils.toJSONString(packageParams));

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + describe + "]]></body>" + "<attach>" + attach
				+ "</attach>"
				+ "<out_trade_no>"
				+ out_trade_no
				+ "</out_trade_no>"
				+
				// 金额，这里写的1 分到时修改
				"<total_fee>"
				+ total_fee
				+ "</total_fee>"
				+
				"<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
				+ "<notify_url>" + notify_url + "</notify_url>"
				+ "<trade_type>" + trade_type + "</trade_type>" + "<openid>"
				+ openid + "</openid>" + "</xml>";
		
		System.out.println(xml);
		
		String allParameters = "";
		try {
			allParameters = reqHandler.genPackage(packageParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String prepay_id = "";
		try {
			prepay_id = GetWxOrderNo.getPayNo(createOrderURL, xml);
			if (prepay_id.equals("")) {
				request.setAttribute("msg", "统一支付接口获取预支付订单出错");
				return "redirect:/err.jspx?msg="+"统一支付接口获取预支付订单出错";
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String appid2 = appid;
		String timestamp = Sha1Util.getTimeStamp();
		String nonceStr2 = nonce_str;
		String prepay_id2 = "prepay_id=" + prepay_id;
		String packages = prepay_id2;
		finalpackage.put("appId", appid2);
		finalpackage.put("timeStamp", timestamp);
		finalpackage.put("nonceStr", nonceStr2);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		
		String finalsign = reqHandler.createSign(finalpackage);
		
		String data = "appId=" + appid2 + "&timeStamp="
				+ timestamp + "&nonceStr=" + nonceStr2 + "&package=" + packages
				+ "&sign=" + finalsign+"&signType=MD5&fid="+fid;
		String encData = Base64.encodeBase64String(DesUtils.encrypt(data.getBytes("UTF-8"), desPassword));
		
		encData = URLEncoder.encode(encData);
		
		System.out.println("pay.jsp?data="+encData);
		
		return "redirect:/movie/paying.jspx?data=" + encData;
	}
	
	@RequestMapping("/paying.jspx")
	public ModelAndView weixin3(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		ModelAndView mv = new ModelAndView("/front/paying");
		
		logger.debug("weixin_pay_js data :" + JSONUtils.toJSONString(request.getParameterMap()));
		
		String authData = request.getParameter("data");
		
		String desPassword = FileConfig.getInstance().getString("weixin.des.password");
		String decAuthData = new String(DesUtils.decrypt(Base64.decodeBase64(authData), desPassword));
		
		Map<String,String[]> paramterMap = RequestUtils.getQueryParamMap(decAuthData);
		
		logger.debug("weixin_pay_js paramterMap :" + JSONUtils.toJSONString(paramterMap));
		
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();

		finalpackage.put("appId", paramterMap.get("appId")[0]);
		finalpackage.put("timeStamp", paramterMap.get("timeStamp")[0]);
		finalpackage.put("nonceStr", paramterMap.get("nonceStr")[0]);
		finalpackage.put("package", paramterMap.get("package")[0]);
		finalpackage.put("signType", paramterMap.get("signType")[0]);
		
		String sign = paramterMap.get("sign")[0];
		
		logger.debug("weixin_pay_js sign data :" + JSONUtils.toJSONString(finalpackage));
		
		String appsecret = FileConfig.getInstance().getString("weixin.appsecret");
		
		String serverSign = RequestHandler.createSign(finalpackage, appsecret);
		if(!serverSign.equals(sign)){
			request.setAttribute("ErrorMsg", "签名失败");
			response.sendRedirect("error.html");
			return null;
		}
		
		mv.addObject("appId", paramterMap.get("appId")[0]);
		mv.addObject("timeStamp", paramterMap.get("timeStamp")[0]);
		mv.addObject("nonceStr", paramterMap.get("nonceStr")[0]);
		mv.addObject("packageValue", paramterMap.get("package")[0]);
		mv.addObject("signType", paramterMap.get("signType")[0]);
		mv.addObject("fid", paramterMap.get("fid")[0]);
		mv.addObject("sign", sign);
		
		return mv;
	}


	@RequestMapping("/notify.jspx")
	public void weixin4(HttpServletRequest request,HttpServletResponse response) throws IOException {

		String rt_return_code = "";
		String rt_return_msg = "";
		try {
		
			BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
	        String line = null;
	        
	        StringBuilder sb = new StringBuilder();
	        while((line = br.readLine())!=null){
	            sb.append(line);
	        }
	        
	        SortedMap<String,String> resultMap = WxHttpsUtils.doXMLParse(sb.toString());
	        
	        logger.debug("pay notify data : "+JSONUtils.toJSONString(resultMap));
	        
			String return_code = resultMap.get("return_code");
			String return_msg = resultMap.get("return_msg");
			String result_code = resultMap.get("result_code");
			
			if("SUCCESS".equalsIgnoreCase(return_code) && "SUCCESS".equalsIgnoreCase(result_code)){
				
				checkSign(resultMap);
				
				String orderNo = resultMap.get("out_trade_no");
				
				Date expiryDate  = billService.updateOrderIsPay(orderNo);
				if(expiryDate == null){
					rt_return_code = "FAIL";
					rt_return_msg = "call back : update order error" ;
				}else{
					rt_return_code = "SUCCESS";
					rt_return_msg = "ok";
				}
			}
			else{
				rt_return_code = "FAIL";
				rt_return_msg = "call back : " + return_msg;
			}
			
		} catch (Exception e) {
			rt_return_code = "FAIL";
			rt_return_msg = "EXCEPTION";
		}
		
		StringBuilder buffer = new StringBuilder();
		buffer.append("<xml>");
		buffer.append("<return_code><![CDATA[").append(rt_return_code).append("]]></return_code>");
		buffer.append("<return_msg><![CDATA[").append(rt_return_msg).append("]]></return_msg>");
		buffer.append("</xml>");
		
		ServletOutputStream os = response.getOutputStream();
		os.print(buffer.toString());
		os.flush();
		os.close();
	}
	

	public void checkSign(SortedMap<String, String> resultMap) {
		String sign = resultMap.get("sign");

		String secret = FileConfig.getInstance().getString("weixin.appsecret");

		String calSign = RequestHandler.createSign(resultMap, secret);

		if (!calSign.equals(sign)) {
			throw new RuntimeException("sign check error");
		}
	}
	
}
