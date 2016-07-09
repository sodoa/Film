package com.xinfan.wxshop.business.entity;

import com.xinfan.wxshop.common.base.BaseEntity;

public class DictKey extends BaseEntity {
    private String dtype;

    private String vvalue;

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype == null ? null : dtype.trim();
    }

    public String getVvalue() {
        return vvalue;
    }

    public void setVvalue(String vvalue) {
        this.vvalue = vvalue == null ? null : vvalue.trim();
    }
}