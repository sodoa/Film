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
public class CurrentTimeTag extends SimpleTagSupport {
	
	
	private String format;

	public void doTag() throws JspException, IOException {
		
		if(format == null || format.trim().length()==0){
			format = "yyyy-MM-dd hh:mm:ss";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		String str = sdf.format(date);
		JspWriter out = getJspContext().getOut();
		out.print(str);
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
