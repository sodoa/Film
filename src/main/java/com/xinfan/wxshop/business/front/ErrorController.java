package com.xinfan.wxshop.business.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ErrorController {

	@RequestMapping("/err.jspx")
	public ModelAndView err(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/front/err");

		String msg = request.getParameter("msg");

		mv.addObject("msg", msg);

		return mv;
	}

}