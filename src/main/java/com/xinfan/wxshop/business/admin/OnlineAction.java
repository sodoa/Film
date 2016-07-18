package com.xinfan.wxshop.business.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OnlineAction {
	
	@RequestMapping("/admin/online.jspx")
	public ModelAndView online(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("/admin/online");
		Integer count=(Integer)request.getSession().getServletContext().getAttribute("count"); 
		mv.addObject("count", count);
		return mv;
	}
	
}
