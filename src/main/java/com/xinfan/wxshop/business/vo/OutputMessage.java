package com.xinfan.wxshop.business.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xinfan.wxshop.business.util.XStreamCDATA;

@XStreamAlias("xml")
public class OutputMessage {

	@XStreamAlias("ToUserName")
	@XStreamCDATA
	private String ToUserName;

	@XStreamAlias("FromUserName")
	@XStreamCDATA
	private String FromUserName;

	@XStreamAlias("CreateTime")
	private Long CreateTime;

	@XStreamAlias("MsgType")
	@XStreamCDATA
	private String MsgType = "text";

	@XStreamAlias("Content")
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

}
