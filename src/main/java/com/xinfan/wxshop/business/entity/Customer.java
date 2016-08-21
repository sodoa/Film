package com.xinfan.wxshop.business.entity;

import java.util.Date;

import com.xinfan.wxshop.common.base.BaseEntity;

public class Customer {
    private Integer customerId;

	private String displayname;

	private String account;

	private String pwd;

	private Integer sex;

	private String wxId;

	private Date regdate;

	private Date expirydate;

	private String longitude;

	private String latitude;

	private Integer regType;

	private Integer state;

	private Integer shareuser;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname == null ? null : displayname.trim();
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId == null ? null : wxId.trim();
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude == null ? null : longitude.trim();
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude == null ? null : latitude.trim();
	}

	public Integer getRegType() {
		return regType;
	}

	public void setRegType(Integer regType) {
		this.regType = regType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getShareuser() {
		return shareuser;
	}

	public void setShareuser(Integer shareuser) {
		this.shareuser = shareuser;
	}
}