package com.ydc.commom.view.dto.cgj.shopCart;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车商品信息表
 */
public class ShopCartCommodityDTO implements Serializable {

    private Integer id;//商品购物车ID

    private Integer commodityId;//商品ID

    private Integer commodityModelId;//商品模型ID

    private String title;//商品名称

    private String  supplierCode;//供应商编码

    private String supplierName;//供应商名称

    private String imgType;//图片类型

    private String fileName; //图片名称

    private String fileUrl;//图片URL

    private String littleFileName;//缩略图名称

    private String littleFileUrl;//缩略图url

    private String model;//商品模型

    private BigDecimal sellPrice;//出售价格

    private BigDecimal marketPrice;// 市场价格

    private Integer inventory;//实际库存

    private Integer commodityNum;//已选数量

    private Date createTime;//创建时间


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

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {

        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getLittleFileName() {
        return littleFileName;
    }

    public void setLittleFileName(String littleFileName) {
        this.littleFileName = littleFileName;
    }

    public String getLittleFileUrl() {
        return littleFileUrl;
    }

    public void setLittleFileUrl(String littleFileUrl) {
        this.littleFileUrl = littleFileUrl;
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

    public Integer getCommodityModelId() {
        return commodityModelId;
    }

    public void setCommodityModelId(Integer commodityModelId) {
        this.commodityModelId = commodityModelId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(Integer commodityNum) {
        this.commodityNum = commodityNum;
    }
}
