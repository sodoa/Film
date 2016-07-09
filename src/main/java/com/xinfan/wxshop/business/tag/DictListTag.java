/**
 * 
 */
package com.xinfan.wxshop.business.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.xinfan.wxshop.business.cache.utils.DictUtils;

/**
 * @author Administrator
 * 
 */
public class DictListTag extends SimpleTagSupport {

	private String type;

	public void doTag() throws JspException, IOException {

		List list = DictUtils.getDictList(type);

		getJspContext().setAttribute("list", list);
		getJspBody().invoke(null);

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
