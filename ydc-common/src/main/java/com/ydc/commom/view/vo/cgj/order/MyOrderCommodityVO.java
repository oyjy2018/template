package com.ydc.commom.view.vo.cgj.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author gongjin
 * @create 2018-09-15 17:15
 **/
public class MyOrderCommodityVO implements Serializable {
    private static final long serialVersionUID = -2715239948125481262L;

    private Integer commodityId;    //商品id
    private String commodityName;   //商品名称
    private Integer commodityModelId;   //商品模型id
    private String commodityModelName;  //商品型号
    private Integer commodityModelNumber;   //商品数量
    private BigDecimal singlePrice; //商品单价
    private BigDecimal sellPrice;   //所需价格（积分）
    private String browseHomeLittleFileUrl; //浏览首页缩率图url
    private String homeLittleFileName;  //首页缩略图名称
    private String browseHomeFileUrl;   //浏览首页图片url
    private String homeFileName;    //首页图片名称
    private Integer homePageImgId;//首页图片id


    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getCommodityModelId() {
        return commodityModelId;
    }

    public void setCommodityModelId(Integer commodityModelId) {
        this.commodityModelId = commodityModelId;
    }

    public String getCommodityModelName() {
        return commodityModelName;
    }

    public void setCommodityModelName(String commodityModelName) {
        this.commodityModelName = commodityModelName;
    }

    public Integer getCommodityModelNumber() {
        return commodityModelNumber;
    }

    public void setCommodityModelNumber(Integer commodityModelNumber) {
        this.commodityModelNumber = commodityModelNumber;
    }

    public BigDecimal getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(BigDecimal singlePrice) {
        this.singlePrice = singlePrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getBrowseHomeLittleFileUrl() {
        return browseHomeLittleFileUrl;
    }

    public void setBrowseHomeLittleFileUrl(String browseHomeLittleFileUrl) {
        this.browseHomeLittleFileUrl = browseHomeLittleFileUrl;
    }

    public String getBrowseHomeFileUrl() {
        return browseHomeFileUrl;
    }

    public void setBrowseHomeFileUrl(String browseHomeFileUrl) {
        this.browseHomeFileUrl = browseHomeFileUrl;
    }

    public Integer getHomePageImgId() {
        return homePageImgId;
    }

    public void setHomePageImgId(Integer homePageImgId) {
        this.homePageImgId = homePageImgId;
    }

    public String getHomeLittleFileName() {
        return homeLittleFileName;
    }

    public void setHomeLittleFileName(String homeLittleFileName) {
        this.homeLittleFileName = homeLittleFileName;
    }

    public String getHomeFileName() {
        return homeFileName;
    }

    public void setHomeFileName(String homeFileName) {
        this.homeFileName = homeFileName;
    }
}
