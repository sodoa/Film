/**
 * 
 */
package com.xinfan.wxshop.business.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.xinfan.wxshop.business.constants.BizConstants;
import com.xinfan.wxshop.common.cache.CacheHelper;

/**
 * @author Administrator
 * 
 */
public class AuthTag extends SimpleTagSupport {

	private String funid;

	public void doTag() throws JspException, IOException {
		Map userMap = (Map) this.getJspContext().getAttribute("sessionMap", PageContext.SESSION_SCOPE);	

		this.getJspBody().invoke(null);
	}

	public String getFunid() {
		return funid;
	}

	public void setFunid(String funid) {
		this.funid = funid;
	}

}
