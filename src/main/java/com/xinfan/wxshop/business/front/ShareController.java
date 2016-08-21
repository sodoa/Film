package com.xinfan.wxshop.business.front;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xinfan.wxshop.business.entity.Film;
import com.xinfan.wxshop.business.entity.ShareRef;
import com.xinfan.wxshop.business.model.JSONResult;
import com.xinfan.wxshop.business.service.ShareRefService;
import com.xinfan.wxshop.business.util.LoginSessionUtils;
import com.xinfan.wxshop.business.util.WeiXinSessionManager;
import com.xinfan.wxshop.business.util.WeiXinShareSign;
import com.xinfan.wxshop.business.util.WxQrcodeUtils;
import com.xinfan.wxshop.business.vo.LoginSession;
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
	
	@Autowired
	private ShareRefService ShareRefService;

	@RequestMapping("/index.jspx")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/front/share");
		mv.addObject("random", new Date().getTime());
		
		String refid = request.getParameter("refid");
		String from = request.getParameter("from");
		
		if (from == null || from.trim().length() == 0 || "null".equals(from)) {
			
			LoginSession sessionMap = LoginSessionUtils.getCustomerUserSessionMap();
			if (sessionMap == null) {
				mv = new ModelAndView("redirect:/movie/login.jspx?type=2");
				return mv;
			}
			
			String filmId = "";
			Integer customerId= LoginSessionUtils.getCustomerIdFromUserSessionMap();
			
			mv.addObject("customerId",customerId);
			mv.addObject("filmId", filmId);
			mv.addObject("from", "1");
			
			return mv;
			
		} else {
			ShareRef ref = ShareRefService.get(Integer.parseInt(refid));
			if (ref != null) {
				Film film = null;
				mv.addObject("film", film);
				mv.addObject("ref", ref);
				mv.addObject("refid", ref.getRefid());
				mv.addObject("from", "2");
				return mv;
			}
			
			return this.toError("影片不存在！");
		}
		
	}
	
	@RequestMapping("/image.html")
	public void distriImage(HttpServletRequest request, HttpServletResponse response) {
		try {
			String refidStr = request.getParameter("refid");
			Integer refid = Integer.valueOf(refidStr);
		
			logger.debug("image refid : "+refid + "");
			byte[] images = WxQrcodeUtils.getQrcode(request, refid);
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
			
			String filmid = request.getParameter("filmid");
			Integer customerId = LoginSessionUtils.getCustomerIdFromUserSessionMap();
			
			ShareRef ref = new ShareRef();
			ref.setCustomerid(customerId);
			ref.setFilmid(Integer.parseInt(filmid));
			
			int shareRefId = ShareRefService.insert(ref);

			String url = request.getParameter("url");
			
			logger.debug("share_sign sign url : " + url);
			
			//url = URLEncoder.encode(url);

			Map<String, String> ret = WeiXinShareSign.sign(ticket_token, url);
			logger.debug("share_sign sign data : " + JSONUtils.toJSONString(ret));
			
			ret.put("refid", String.valueOf(shareRefId));
			ret.put("appId", appid);

			return JSONResult.success().putValues(ret);

		} else {
			logger.error("ticket_token is null");
			return JSONResult.error("ticket_token is null");
		}
		
	}
}
