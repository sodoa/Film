package com.xinfan.wxshop.business.vo;

import java.util.Date;

import com.xinfan.wxshop.common.base.BaseEntity;

public class GoodsVsLimitVO extends BaseEntity{
    private Integer goodsId;

    private String goodsName;

    private String goodsLname;

    private String goodsDes;

    private Integer goodsStatus;

    private String thumbnailUrl;

    private Float orginPrices;

    private Float discount;

    private Float finalPrices;

    private String unit;

    private Date releaseDate;

    private Integer hot;

    private Integer burst;

    private Integer sort;

    private String typeLevel1;

    private String typeLevel2;

    private Integer sellcount;

    private Integer fashion;

    private String fashionTemplate;

    private String keywords;

    private String supplier;

    private String goodsArea;

    private String summary;

    private String themeType;
    
    private Integer totalAmount;

    private Integer sellAmount;

    private Integer buyLimit;

    private Date timeLimit;

    private Integer limitType;

    public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getSellAmount() {
		return sellAmount;
	}

	public void setSellAmount(Integer sellAmount) {
		this.sellAmount = sellAmount;
	}

	public Integer getBuyLimit() {
		return buyLimit;
	}

	public void setBuyLimit(Integer buyLimit) {
		this.buyLimit = buyLimit;
	}

	public Date getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Date timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getLimitType() {
		return limitType;
	}

	public void setLimitType(Integer limitType) {
		this.limitType = limitType;
	}

	public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsLname() {
        return goodsLname;
    }

    public void setGoodsLname(String goodsLname) {
        this.goodsLname = goodsLname == null ? null : goodsLname.trim();
    }

    public String getGoodsDes() {
        return goodsDes;
    }

    public void setGoodsDes(String goodsDes) {
        this.goodsDes = goodsDes == null ? null : goodsDes.trim();
    }

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl == null ? null : thumbnailUrl.trim();
    }

    public Float getOrginPrices() {
        return orginPrices;
    }

    public void setOrginPrices(Float orginPrices) {
        this.orginPrices = orginPrices;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getFinalPrices() {
        return finalPrices;
    }

    public void setFinalPrices(Float finalPrices) {
        this.finalPrices = finalPrices;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getBurst() {
        return burst;
    }

    public void setBurst(Integer burst) {
        this.burst = burst;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTypeLevel1() {
        return typeLevel1;
    }

    public void setTypeLevel1(String typeLevel1) {
        this.typeLevel1 = typeLevel1 == null ? null : typeLevel1.trim();
    }

    public String getTypeLevel2() {
        return typeLevel2;
    }

    public void setTypeLevel2(String typeLevel2) {
        this.typeLevel2 = typeLevel2 == null ? null : typeLevel2.trim();
    }

    public Integer getSellcount() {
        return sellcount;
    }

    public void setSellcount(Integer sellcount) {
        this.sellcount = sellcount;
    }

    public Integer getFashion() {
        return fashion;
    }

    public void setFashion(Integer fashion) {
        this.fashion = fashion;
    }

    public String getFashionTemplate() {
        return fashionTemplate;
    }

    public void setFashionTemplate(String fashionTemplate) {
        this.fashionTemplate = fashionTemplate == null ? null : fashionTemplate.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public String getGoodsArea() {
        return goodsArea;
    }

    public void setGoodsArea(String goodsArea) {
        this.goodsArea = goodsArea == null ? null : goodsArea.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getThemeType() {
        return themeType;
    }

    public void setThemeType(String themeType) {
        this.themeType = themeType == null ? null : themeType.trim();
    }
}