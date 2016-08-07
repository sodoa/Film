package com.xinfan.wxshop.business.front;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xinfan.wxshop.business.util.LoginSessionUtils;
import com.xinfan.wxshop.business.util.WxQrcodeUtils;
import com.xinfan.wxshop.business.vo.LoginSession;

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
		LoginSession sessionMap = LoginSessionUtils.getCustomerUserSessionMap();
		if (sessionMap == null) {
			ModelAndView mv = new ModelAndView("redirect:/movie/login.jspx?type=2");
			return mv;
		}else {
			ModelAndView mv = new ModelAndView("/front/share");
			mv.addObject("random", new Date().getTime());
			return mv;
		}
		
	}
	
	@RequestMapping("/image.html")
	public void distriImage(HttpServletRequest request, HttpServletResponse response) {
		try {

			Integer customerId = LoginSessionUtils.getCustomerIdFromUserSessionMap();
			byte[] images = WxQrcodeUtils.getQrcode(request, customerId);
			response.getOutputStream().write(images);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
