/**
 * 
 */
package com.xinfan.wxshop.business.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.xinfan.wxshop.business.util.LoginSessionUtils;
import com.xinfan.wxshop.common.base.DataMap;

/**
 * @author Administrator
 * 
 */
public class WXCurrentCustomerIdTag extends SimpleTagSupport {

	public void doTag() throws JspException, IOException {
		
		Integer customerId = 0;
		
		DataMap userMap = (DataMap) this.getJspContext().getAttribute(LoginSessionUtils.CUSTOMER_USER_SESSION_KEY, PageContext.SESSION_SCOPE);	
		if(userMap!=null){
			customerId = userMap.getInt("customerId");
		}
		
		StringBuilder scripts = new StringBuilder();
		
		scripts.append("<script type=\"text/javascript\">");
		scripts.append("var wxsid = '").append(customerId).append("';");
		scripts.append("function getShareId(){");
		scripts.append("return ").append(customerId).append(";");
		scripts.append("}");
		scripts.append("</script>");
		
		JspWriter out = getJspContext().getOut();
		out.print(scripts.toString());
		
	}

}
