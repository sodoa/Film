package com.xinfan.wxshop.business.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xinfan.wxshop.business.constants.BizConstants;
import com.xinfan.wxshop.business.util.RequestUtils;
import com.xinfan.wxshop.business.vo.LoginSession;
import com.xinfan.wxshop.common.context.OnlineManager;

@Controller
public class OnlineAction {
	
	@RequestMapping("/admin/online.jspx")
	public ModelAndView online(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("/admin/online");
		mv.addObject("count", OnlineManager.getInstance().get());
		return mv;
	}
	
	@RequestMapping("/admin/setonline.jspx")
	public ModelAndView setonline(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("/admin/online");
		
		LoginSession session = new LoginSession();
		RequestUtils.getSession().setAttribute(BizConstants.CUSTOMER_USER_SESSION_KEY, session);
		
		 Integer count= OnlineManager.getInstance().get();
         
         mv.addObject("count", count);
 		return mv;
	}
	
}
