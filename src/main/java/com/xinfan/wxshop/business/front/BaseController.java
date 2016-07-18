package com.xinfan.wxshop.business.front;

import org.springframework.web.servlet.ModelAndView;

public class BaseController {

	public ModelAndView toError(String msg) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", msg);
		mv.setViewName("/front/err");
		return mv;
	}
}
