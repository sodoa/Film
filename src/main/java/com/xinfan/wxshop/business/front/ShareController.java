package com.xinfan.wxshop.business.front;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xinfan.wxshop.business.model.JSONResult;
import com.xinfan.wxshop.business.util.LoginSessionUtils;
import com.xinfan.wxshop.business.util.WeiXinSessionManager;
import com.xinfan.wxshop.business.util.WeiXinShareSign;
import com.xinfan.wxshop.business.util.WxQrcodeUtils;
import com.xinfan.wxshop.business.vo.LoginSession;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.config.FileConfig;
import com.xinfan.wxshop.common.util.JSONUtils;

/**
 * @author huangmin
 * @DATE 2016年8月7日上午10:38:56
 * 
 */
@Controller
@RequestMapping("/share")
public class ShareController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShareController.class);

	@RequestMapping("/index.jspx")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/front/share");
		mv.addObject("random", new Date().getTime());
		
		String wxsid = request.getParameter("wxsid");
		String filmId = "";
		
		if(StringUtils.isNotBlank(wxsid) ){
			mv.addObject("wxsid", wxsid);
			mv.addObject("filmId", filmId);
		}else{
			LoginSession sessionMap = LoginSessionUtils.getCustomerUserSessionMap();
			if (sessionMap == null) {
				mv = new ModelAndView("redirect:/movie/login.jspx?type=2");
			}
		}
		return mv;
	}
	
	@RequestMapping("/visit.jspx")
	public ModelAndView visit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/front/visit");
		mv.addObject("random", new Date().getTime());
		
		String wxsid = request.getParameter("wxsid");
		String filmId = request.getParameter("filmid");
		
		return mv;
	}	
	
	@RequestMapping("/image.html")
	public void distriImage(HttpServletRequest request, HttpServletResponse response) {
		try {
			String wxsid = request.getParameter("wxsid");
			String filmid = request.getParameter("filmid");
			
			Integer customerId = Integer.valueOf(wxsid);
		
			logger.debug(customerId + "");
			byte[] images = WxQrcodeUtils.getQrcode(request, customerId);
			response.getOutputStream().write(images);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping("/sign.html")
	public @ResponseBody
	JSONResult shareSign(HttpServletRequest request,
			HttpServletResponse response) {

		String appid = FileConfig.getInstance().getString("weixin.appid");

		String ticket_token = WeiXinSessionManager.getTicketToken();

		if (ticket_token != null) {

			String url = request.getParameter("url");
			
			logger.debug("share_sign sign url : " + url);
			
			//url = URLEncoder.encode(url);

			Map<String, String> ret = WeiXinShareSign.sign(ticket_token, url);
			logger.debug("share_sign sign data : " + JSONUtils.toJSONString(ret));
			
			
			LoginSession sessionMap = LoginSessionUtils.getCustomerUserSessionMap();
			if (sessionMap != null) {
				
				Integer customerId = sessionMap.getCustomerId();
				if (customerId != null) {
					ret.put("wxsid", String.valueOf(customerId));
				}
			}

			ret.put("appId", appid);

			return JSONResult.success().putValues(ret);

		} else {
			logger.error("ticket_token is null");
			return JSONResult.error("ticket_token is null");
		}

	}
}
