package com.example.demo.Common.utils;


import java.util.ArrayList;
import java.util.List;

/**
 * Class desc:
 * <p>
 * Created by sunzhihao on 2020-02-23
 */

public class SaveToWareHouseAndPublishReqDto{
        private String platformSkuId;
        private String skuDetailId;
        private Integer categoryId;
        private String skuName;
        private String stockNum;
        private List attributFristObj;
        private Integer bail;
        private String mainImg;
        private List<String> wareImgs;
        private String description;
        private List<String> propStr;
        private Integer auctionType;
        private String initialPrice;
        private Integer delayPeriod;
        private Integer cycleType;
        private String startTime;
        private String endTime;
        private String businessCode;
        private PropertiesJson propertiesJson;
        private String durationTime;
        private String reservedPrice;
        private String jdPrice;

    public String getPlatformSkuId() {
        return platformSkuId;
    }

    public void setPlatformSkuId(String platformSkuId) {
        this.platformSkuId = platformSkuId;
    }

    public String getSkuDetailId() {
        return skuDetailId;
    }

    public void setSkuDetailId(String skuDetailId) {
        this.skuDetailId = skuDetailId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getStockNum() {
        return stockNum;
    }

    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
    }

    public List getAttributFristObj() {
        return attributFristObj;
    }

    public void setAttributFristObj(List attributFristObj) {
        this.attributFristObj = attributFristObj;
    }

    public Integer getBail() {
        return bail;
    }

    public void setBail(Integer bail) {
        this.bail = bail;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public List<String> getWareImgs() {
        return wareImgs;
    }

    public void setWareImgs(List<String> wareImgs) {
        this.wareImgs = wareImgs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPropStr() {
        return propStr;
    }

    public void setPropStr(List<String> propStr) {
        this.propStr = propStr;
    }

    public Integer getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(Integer auctionType) {
        this.auctionType = auctionType;
    }

    public String getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(String initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Integer getDelayPeriod() {
        return delayPeriod;
    }

    public void setDelayPeriod(Integer delayPeriod) {
        this.delayPeriod = delayPeriod;
    }

    public Integer getCycleType() {
        return cycleType;
    }

    public void setCycleType(Integer cycleType) {
        this.cycleType = cycleType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public PropertiesJson getPropertiesJson() {
        return propertiesJson;
    }

    public void setPropertiesJson(PropertiesJson propertiesJson) {
        this.propertiesJson = propertiesJson;
    }

    public String getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(String durationTime) {
        this.durationTime = durationTime;
    }

    public String getReservedPrice() {
        return reservedPrice;
    }

    public void setReservedPrice(String reservedPrice) {
        this.reservedPrice = reservedPrice;
    }

    public String getJdPrice() {
        return jdPrice;
    }

    public void setJdPrice(String jdPrice) {
        this.jdPrice = jdPrice;
    }
}
