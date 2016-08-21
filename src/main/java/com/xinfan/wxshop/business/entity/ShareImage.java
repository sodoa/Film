package com.xinfan.wxshop.business.entity;

import com.xinfan.wxshop.common.base.BaseEntity;

public class ShareImage extends BaseEntity {
    private Integer imageid;

    private Integer shareid;

    private String imageurl;

    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }

    public Integer getShareid() {
        return shareid;
    }

    public void setShareid(Integer shareid) {
        this.shareid = shareid;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl == null ? null : imageurl.trim();
    }
}