/**
 * 
 */
package com.xinfan.wxshop.business.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.xinfan.wxshop.business.cache.utils.DictUtils;
import com.xinfan.wxshop.business.entity.Dict;

/**
 * @author Administrator
 * 
 */
public class DictOptionTag extends SimpleTagSupport {

	private String type;

	private String value;

	public void doTag() throws JspException, IOException {

		List<Dict> list = DictUtils.getDictList(type);
		StringBuilder out = new StringBuilder();

		for (Dict type : list) {

			out.append("<option ");
			out.append((type.getVvalue().equals(value) ? "selected=\"selected\"" : "") + " value=\"" + type.getVvalue() + "\">");
			out.append(type.getVname());
			out.append("</option>");
		}

		getJspContext().getOut().print(out.toString());

	}
	
	
	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
