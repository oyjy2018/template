package com.ydc.commom.view.dto.cgj.order;

import com.ydc.model.cgj.OrderCommodity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderCommodityDTO  implements Serializable {

    private Integer id;//商品购物车ID

    private Integer commodityId;//商品ID

    private Integer modelId;//商品模型ID

    private String title;//商品名称

    private String  supplierCode;//供应商编码

    private String supplierName;//供应商名称

    private String model;//商品模型

    private BigDecimal sellPrice;//出售价格

    private BigDecimal marketPrice;// 市场价格

    private Integer inventory;//实际库存

    private Integer commodityNum;//已选数量

    private Integer commodityImgId;//主图图片id

    private String homeFileName;

    private String homeFileUrl;

    private String homeFileType;

    private String homeLittleFileName;

    private String homeLittleFileUrl;


    public OrderCommodity createOrderCommodity(){
        OrderCommodity orderCommodity=new OrderCommodity();

        orderCommodity.setCommodityId(getCommodityId());
        orderCommodity.setCommodityName(getTitle());
        orderCommodity.setCommodityModelName(getModel());
        orderCommodity.setCommodityModelId(getModelId());
        orderCommodity.setSupplierCode(getSupplierCode());
        orderCommodity.setSupplierName(getSupplierName());
        orderCommodity.setSinglePrice(getSellPrice());
        orderCommodity.setHomeFileName(getHomeFileName());
        orderCommodity.setHomeFileType(getHomeFileType());
        orderCommodity.setHomePageImgId(getCommodityImgId());
        orderCommodity.setHomeFileUrl(getHomeFileUrl());
        orderCommodity.setHomeLittleFileName(getHomeLittleFileName());
        orderCommodity.setHomeLittleFileUrl(getHomeLittleFileUrl());
        orderCommodity.setCreateTime(new Date());
        orderCommodity.setUpdateTime(new Date());
        return orderCommodity;
    }


    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(Integer commodityNum) {
        this.commodityNum = commodityNum;
    }

    public Integer getCommodityImgId() {
        return commodityImgId;
    }

    public void setCommodityImgId(Integer commodityImgId) {
        this.commodityImgId = commodityImgId;
    }

    public String getHomeFileName() {
        return homeFileName;
    }

    public void setHomeFileName(String homeFileName) {
        this.homeFileName = homeFileName;
    }

    public String getHomeFileUrl() {
        return homeFileUrl;
    }

    public void setHomeFileUrl(String homeFileUrl) {
        this.homeFileUrl = homeFileUrl;
    }

    public String getHomeFileType() {
        return homeFileType;
    }

    public void setHomeFileType(String homeFileType) {
        this.homeFileType = homeFileType;
    }

    public String getHomeLittleFileName() {
        return homeLittleFileName;
    }

    public void setHomeLittleFileName(String homeLittleFileName) {
        this.homeLittleFileName = homeLittleFileName;
    }

    public String getHomeLittleFileUrl() {
        return homeLittleFileUrl;
    }

    public void setHomeLittleFileUrl(String homeLittleFileUrl) {
        this.homeLittleFileUrl = homeLittleFileUrl;
    }
}
