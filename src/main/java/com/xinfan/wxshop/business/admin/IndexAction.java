package com.xinfan.wxshop.business.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexAction {
	
	@RequestMapping("/admin/index.jspx")
	public ModelAndView mindex(){
		ModelAndView mv = new ModelAndView("/admin/index");
		return mv;
	}
	
	@RequestMapping("/admin/welcome.jspx")
	public ModelAndView welcome(){
		ModelAndView mv = new ModelAndView("/admin/welcome");
		return mv;
	}
}
