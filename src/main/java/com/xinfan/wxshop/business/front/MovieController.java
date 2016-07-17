package com.xinfan.wxshop.business.front;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xinfan.wxshop.business.entity.Film;
import com.xinfan.wxshop.business.service.CustomerService;
import com.xinfan.wxshop.business.service.FilmService;
import com.xinfan.wxshop.business.util.LoginSessionUtils;
import com.xinfan.wxshop.business.vo.LoginSession;

/**
 * @author huangmin
 * @DATE 2016年7月12日下午10:07:04
 * 
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

	private static final Logger logger = LoggerFactory
			.getLogger(MovieController.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private FilmService filmService;

	/**
	 * 不过期--->跳转到电影页面
	 */
	@RequestMapping("/see.jspx")
	public ModelAndView see(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		LoginSession sessionMap = LoginSessionUtils.getCustomerUserSessionMap();
		String fid = request.getParameter("fid");
		String msg = "";

		if (sessionMap == null) {
			mv.setViewName("redirect:/movie/login.jspx?fid=" + fid);
			return mv;
		} else {
			logger.debug("###########" + sessionMap.getExpiryDate());
			// 检查是否有过期
			if (new Date().after(sessionMap.getExpiryDate())) {
				// 过期-->微信支付
				mv.addObject("fid", fid);
				mv.setViewName("/front/pay");

			} else {
				if (StringUtils.isNotBlank(fid)) {
					Film film = filmService.getFilm(Integer.valueOf(fid));
					if (null != film) {
						mv.setViewName("/front/movie");
						mv.addObject("film", film);
					} else {
						msg = "没有找到对应的电影";
						mv.addObject("msg", msg);
						mv.setViewName("/front/err");
					}
				} else {
					msg = "没有找到对应的电影";
					mv.addObject("msg", msg);
					mv.setViewName("/front/err");
				}
				logger.debug("###########" + fid);
			}

			return mv;
		}

	}

	@RequestMapping("/movie.jspx")
	public ModelAndView movie(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/front/movie");

		String fid = request.getParameter("fid");
		if (StringUtils.isNotBlank(fid)) {
			Film film = filmService.getFilm(Integer.valueOf(fid));
			if (null != film) {
				mv.setViewName("/front/movie");
				mv.addObject("film", film);

			} else {
				mv.setViewName("/front/err");
			}

		} else {
			mv.setViewName("/front/err");
		}

		logger.debug("###########" + fid);

		return mv;
	}
}
