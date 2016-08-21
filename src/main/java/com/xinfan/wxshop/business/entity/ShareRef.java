package com.xinfan.wxshop.business.entity;

import java.util.Date;

import com.xinfan.wxshop.common.base.BaseEntity;

public class ShareRef extends BaseEntity{
    private Integer refid;

	private Integer filmid;

	private Integer customerid;

	private Date createdate;

	private Integer shareid;


	public Integer getRefid() {
		return refid;
	}

	public void setRefid(Integer refid) {
		this.refid = refid;
	}

	public Integer getFilmid() {
		return filmid;
	}

	public void setFilmid(Integer filmid) {
		this.filmid = filmid;
	}

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Integer getShareid() {
		return shareid;
	}

	public void setShareid(Integer shareid) {
		this.shareid = shareid;
	}
}