/**
 * 
 */
package com.xinfan.wxshop.business.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.xinfan.wxshop.business.cache.utils.DictUtils;

/**
 * @author Administrator
 * 
 */
public class DictLabelTag extends SimpleTagSupport {

	private String type;

	private String value;

	public void doTag() throws JspException, IOException {

		String v = DictUtils.getDictValueName(type, value);

		getJspContext().getOut().print(v);

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
