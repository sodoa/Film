package com.xinfan.wxshop.business.entity;

import java.util.Date;

import com.xinfan.wxshop.common.base.BaseEntity;

public class Share extends BaseEntity {
    private Integer shareid;

    private String title;

    private String description;

    private Integer filmid;

    private String headimg;

    private Date createdate;

    private Integer online;

    public Integer getShareid() {
        return shareid;
    }

    public void setShareid(Integer shareid) {
        this.shareid = shareid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getFilmid() {
        return filmid;
    }

    public void setFilmid(Integer filmid) {
        this.filmid = filmid;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }
}