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
public class GoodsThemeOptionTag extends SimpleTagSupport {

	private String theme;

	private String value;

	public void doTag() throws JspException, IOException {

		String dict_key = "goods_theme_type";

		List<Dict> list = DictUtils.getDictList(dict_key);
		StringBuilder out = new StringBuilder();

		for (Dict type : list) {

			if ("2".equals(theme)) {
				if (type.getVvalue().length() == 2) {
					out.append("<option ");
					out.append((type.getVvalue().equals(value) ? "selected=\"selected\"" : "") + " value=\"" + type.getVvalue() + "\">");
					out.append(type.getVname());
					out.append("</option>");
				}
			} else {
				if (type.getVvalue().length() == 1) {
					out.append("<option ");
					out.append((type.getVvalue().equals(value) ? "selected=\"selected\"" : "") + " value=\"" + type.getVvalue() + "\">");
					out.append(type.getVname());
					out.append("</option>");
				}
			}
		}

		getJspContext().getOut().print(out.toString());

	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
