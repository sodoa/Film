package com.xinfan.wxshop.business.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xinfan.wxshop.business.constants.BizConstants;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	private String adminLoginUrl;

	private String frontLoginUrl;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();

		if (contextPath.length() > 0) {
			uri = uri.substring(contextPath.length());
		}

		if (contextPath.equals("/")) {
			request.setAttribute("contextpath", "");
		} else {
			request.setAttribute("contextpath", contextPath);
		}

		if (uri.startsWith("/admin")) {

			// 1、请求到登录页面 放行
			if (request.getServletPath().startsWith(adminLoginUrl)) {
				return true;
			}
/*			if(true){
				return true;
			}*/

			// 2、如果用户已经登录 放行
			Map userMap = (Map) request.getSession().getAttribute(BizConstants.MANAGER_USER_SESSION_KEY);

			if (userMap != null) {
				return true;
			}

			// 4、非法请求 即这些请求需要登录后才能访问
			// 重定向到登录页面
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
				response.addHeader("sessionstatus", "timeout");
				response.setStatus(403);
				return false;
			} else {
				response.sendRedirect(request.getContextPath() + "/admin_login.jspx");
				return false;
			}
		} else if (uri.startsWith("/center")) {
			
			// 2、如果用户已经登录 放行
			Map userMap = (Map) request.getSession().getAttribute(BizConstants.CUSTOMER_USER_SESSION_KEY);
			if(userMap != null){
				return true;
			}

			// 4、非法请求 即这些请求需要登录后才能访问
			// 重定向到登录页面
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
				response.addHeader("sessionstatus", "timeout");
				response.setStatus(403);
				return false;
			} else {
				String p = request.getParameter("p");
				if (p != null && p.length() > 0 && !"null".equals(p)) {
					request.getSession(true).setAttribute(BizConstants.SESSION_REDIRECT_VAR_KEY, p);
				}
				response.sendRedirect(request.getContextPath() + frontLoginUrl + ".html?p="+p);
				return false;
			}

		} else {
			return true;
		}

	}

	public String getAdminLoginUrl() {
		return adminLoginUrl;
	}

	public void setAdminLoginUrl(String adminLoginUrl) {
		this.adminLoginUrl = adminLoginUrl;
	}

	public String getFrontLoginUrl() {
		return frontLoginUrl;
	}

	public void setFrontLoginUrl(String frontLoginUrl) {
		this.frontLoginUrl = frontLoginUrl;
	}

}
