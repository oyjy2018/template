package com.ydc.commom.view.dto.cgj;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CommodityShowDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private Integer commodityId;
    /**
     * 商品名
     */
    private String commodityTitle;
    /**
     * 商品描述
     */
    private String commodityDescription;
    /**
     * 库存
     */
    private int totalInventory;
    /**
     * 销量
     */
    private int soldNumber;
    /**
     * 虚拟销量
     */
    private int soldNumberSham;
    /**
     * 最低销售金额
     */
    private BigDecimal lowSellPrice;
    /**
     * 最高销售金额
     */
    private BigDecimal highSellPrice;
    /**
     * 最低市场金额
     */
    private BigDecimal lowMarketPrice;
    /**
     * 最高市场金额
     */
    private BigDecimal highMarketPrice;
    /**
     * 首页图片
     */
    private List<Map<String, String>> headPic;
    /**
     * 轮播图片
     */
    private List<Map<String, String>> carouselPic;
    /**
     * 描述图片
     */
    private List<Map<String, String>> describePic;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 供应商名称
     */
    private String supplierName;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityTitle() {
        return commodityTitle;
    }

    public void setCommodityTitle(String commodityTitle) {
        this.commodityTitle = commodityTitle;
    }

    public String getCommodityDescription() {
        return commodityDescription;
    }

    public void setCommodityDescription(String commodityDescription) {
        this.commodityDescription = commodityDescription;
    }

    public int getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(int totalInventory) {
        this.totalInventory = totalInventory;
    }

    public int getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(int soldNumber) {
        this.soldNumber = soldNumber;
    }

    public int getSoldNumberSham() {
        return soldNumberSham;
    }

    public void setSoldNumberSham(int soldNumberSham) {
        this.soldNumberSham = soldNumberSham;
    }

    public BigDecimal getLowSellPrice() {
        return lowSellPrice;
    }

    public void setLowSellPrice(BigDecimal lowSellPrice) {
        this.lowSellPrice = lowSellPrice;
    }

    public BigDecimal getHighSellPrice() {
        return highSellPrice;
    }

    public void setHighSellPrice(BigDecimal highSellPrice) {
        this.highSellPrice = highSellPrice;
    }

    public BigDecimal getLowMarketPrice() {
        return lowMarketPrice;
    }

    public void setLowMarketPrice(BigDecimal lowMarketPrice) {
        this.lowMarketPrice = lowMarketPrice;
    }

    public BigDecimal getHighMarketPrice() {
        return highMarketPrice;
    }

    public void setHighMarketPrice(BigDecimal highMarketPrice) {
        this.highMarketPrice = highMarketPrice;
    }

    public List<Map<String, String>> getHeadPic() {
        return headPic;
    }

    public void setHeadPic(List<Map<String, String>> headPic) {
        this.headPic = headPic;
    }

    public List<Map<String, String>> getCarouselPic() {
        return carouselPic;
    }

    public void setCarouselPic(List<Map<String, String>> carouselPic) {
        this.carouselPic = carouselPic;
    }

    public List<Map<String, String>> getDescribePic() {
        return describePic;
    }

    public void setDescribePic(List<Map<String, String>> describePic) {
        this.describePic = describePic;
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
}
