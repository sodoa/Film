package com.xinfan.wxshop.business.pay.weixin;

import java.util.Map;

public class WxNotifyTemplate {
	private String template_id;
	private String touser;
	private String url;
	private String topcolor;
	private Map<String, WxTemplateData> data;

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	public Map<String, WxTemplateData> getData() {
		return data;
	}

	public void setData(Map<String, WxTemplateData> data) {
		this.data = data;
	}

}
