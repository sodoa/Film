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

import com.xinfan.wxshop.business.entity.Customer;
import com.xinfan.wxshop.business.entity.Film;
import com.xinfan.wxshop.business.service.CustomerService;
import com.xinfan.wxshop.business.service.FilmService;

/**
 * @author huangmin
 * @DATE 2016年7月12日下午10:07:04
 * 
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private FilmService filmService;

	
	/**
	 *  不过期--->跳转到电影页面
	 */
	@RequestMapping("/see.jspx")
	public ModelAndView waitOrder(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/front/film.jsp");
		
		String wx_id = (String) request.getAttribute("wx_id");
		
		Customer customer =customerService.getByWeixinId(wx_id);
		logger.debug("###########" + customer.getExpirydate().toString());
		//检查是否有过期 
		if(new Date().after(customer.getExpirydate())  ){
			//过期-->微信支付 
			mv.setViewName("/front/pay");
			
			
		}else{
			String state = request.getParameter("state");
			if(StringUtils.isNotBlank(state)){
				Film film = filmService.getFilm(Integer.valueOf(state));
				if(null != film) {
					mv.setViewName("/front/movie");
					mv.addObject("film", film);
					
				}else{
					mv.setViewName("/front/err");
				}
				
			}else{
				mv.setViewName("/front/err");
			}
			
			logger.debug( "###########" + state);
		}
		
		
		return mv;
	}
	
}
