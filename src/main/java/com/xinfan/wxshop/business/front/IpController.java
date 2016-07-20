package com.xinfan.wxshop.business.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinfan.wxshop.business.util.IpCache;

@Controller
@RequestMapping("/ip")
public class IpController {

	@RequestMapping("/set.jspx")
	public String set(HttpServletRequest request) {

		String ip = request.getParameter("ip");
		if (ip != null && ip.length() > 0) {
			IpCache.cIp = ip;
		}

		return "yes";
	}

	@RequestMapping("/get.jspx")
	public String get(HttpServletRequest request) {
		return IpCache.cIp;
	}
}