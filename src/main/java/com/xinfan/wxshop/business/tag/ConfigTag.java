/**
 * 
 */
package com.xinfan.wxshop.business.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.xinfan.wxshop.business.util.ConfigUtils;

/**
 * @author Administrator
 * 
 */
public class ConfigTag extends SimpleTagSupport {

	private String id;

	public void doTag() throws JspException, IOException {

		if (id != null && id.trim().length() > 0) {
			String value = ConfigUtils.getValue(id.toUpperCase(), "");
			JspWriter out = getJspContext().getOut();
			out.print(value);
		}
		else{
			String value = ConfigUtils.getValue("link_man_size", "");
			if(value == null || value.trim().length()==0){
				value = "1";
			}
			getJspContext().setAttribute("link_man_size", value);
			getJspBody().invoke(null);
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
