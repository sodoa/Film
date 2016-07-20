package com.xinfan.wxshop.business.front;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinfan.wxshop.business.util.IpCache;

@Controller
@RequestMapping("/ip")
public class IpController {

	@RequestMapping("/set.jspx")
	public void set(HttpServletRequest request,HttpServletResponse response) {

		String ip = request.getParameter("ip");
		if (ip != null && ip.length() > 0) {
			IpCache.cIp = ip;
		}
		
		try {
			response.getOutputStream().println("yes : " + IpCache.cIp);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("/get.jspx")
	public String get(HttpServletRequest request) {
		return IpCache.cIp;
	}
}