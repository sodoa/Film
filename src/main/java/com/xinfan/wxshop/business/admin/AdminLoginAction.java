package com.xinfan.wxshop.business.admin;

import javax.servlet.http.HttpServletRequest;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xinfan.wxshop.business.util.LoginSessionUtils;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.config.FileConfig;

@Controller
public class AdminLoginAction {
	
	@RequestMapping(method=RequestMethod.GET, value="/m_login.jspx")
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView("/admin/login");
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/admin_login.jspx")
	public ModelAndView login2(){
		ModelAndView mv = new ModelAndView("/admin/login");
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST,value= "/m_login.jspx")
	public ModelAndView login2(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("/admin/login");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String configUsername = FileConfig.getInstance().getString("admin.login.username").trim();
		String configPassword = FileConfig.getInstance().getString("admin.login.password").trim();
		
		if(configUsername.equals(username) && configPassword.equals(password)){
			mv.setViewName("redirect:/admin/index.jspx");
			
			DataMap sessionMap = new DataMap();
			sessionMap.put("username", username);
			
			LoginSessionUtils.setManagerUserSessionMap(sessionMap);
		}
		else{
			mv.addObject("errormsg", "用户名或密码不正确");
		}
		
		return mv;
	}
	
	@RequestMapping("/m_logout.jspx")
	public ModelAndView logout(){
		ModelAndView mv = new ModelAndView("/admin/login");
		LoginSessionUtils.setExpireManagerSessionMap();
		return mv;
	}
	
	
	
}
