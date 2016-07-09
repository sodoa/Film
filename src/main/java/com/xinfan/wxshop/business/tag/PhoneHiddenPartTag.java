/**
 * 
 */
package com.xinfan.wxshop.business.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author Administrator
 * 
 */
public class PhoneHiddenPartTag extends SimpleTagSupport {

	private String value;

	public void doTag() throws JspException, IOException {

		String v = "";

		if (value != null && value.length() >= 3) {
			v = "***"+value.substring(value.length() - 3, value.length());
		}

		getJspContext().getOut().print(v);

	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
