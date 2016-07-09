package com.xinfan.wxshop.business.entity;

import java.util.Date;

public class Searchkey {
    private Integer searchkeyId;

    private String word;

    private Integer times;

    private Date createTime;

    private Date updateTime;

    public Integer getSearchkeyId() {
        return searchkeyId;
    }

    public void setSearchkeyId(Integer searchkeyId) {
        this.searchkeyId = searchkeyId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}