/**
 * 
 */
package com.xinfan.wxshop.business.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author Administrator
 * 
 */
public class DateFormatTag extends SimpleTagSupport {

	private Date time;

	private String format;

	public void doTag() throws JspException, IOException {

		if (format == null || format.trim().length() == 0) {
			format = "yyyy-MM-dd hh:mm:ss";
		}

		String str = "";
		if (time != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = time;
			str = sdf.format(date);
		}
		JspWriter out = getJspContext().getOut();
		out.print(str);
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
