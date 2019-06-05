package com.ydc.commom.view.dto.ydhc;

import com.ydc.model.annotation.Attribute;

public class YdhcVehicleDTO {
    @Attribute(name = "车辆ID", isNum = true)
    private Integer id;
    @Attribute(name = "车辆标题", required = true, maxLength = 60)
    private String title;
    @Attribute(name = "车辆价格", required = true, isDigit = true, intLength = 9, decimalLength = 2)
    private String price;
    @Attribute(name = "车源类型code", required = true)
    private String carSourceCode;
    @Attribute(name = "车源类型", required = true)
    private String carSource;
    @Attribute(name = "外观颜色code", required = true)
    private String facadeColourCode;
    @Attribute(name = "外观颜色", required = true)
    private String facadeColour;
    @Attribute(name = "内饰颜色code", required = true)
    private String interiorColourCode;
    @Attribute(name = "内饰颜色", required = true)
    private String interiorColour;
    @Attribute(name = "车辆品牌code", required = true)
    private String brandCode;
    @Attribute(name = "车辆品牌", required = true)
    private String brand;
    @Attribute(name = "车系code", required = true)
    private String seriesCode;
    @Attribute(name = "车系", required = true)
    private String series;
    @Attribute(name = "货期code", required = true)
    private String deliveryCode;
    @Attribute(name = "货期", required = true)
    private String delivery;
    @Attribute(name = "车辆所在地省编码", required = true)
    private String provinceCode;
    @Attribute(name = "车辆所在地省", required = true)
    private String province;
    @Attribute(name = "车辆所在地市编码", required = true)
    private String cityCode;
    @Attribute(name = "车辆所在地市", required = true)
    private String city;
    @Attribute(name = "手续", required = true)
    private String procedure;
    @Attribute(name = "配置信息", required = true, maxLength = 600)
    private String config;
    @Attribute(name = "商家code", required = true)
    private String merchantCode;
    @Attribute(name = "商家", required = true)
    private String merchant;
    @Attribute(name = "描述内容", required = true, maxLength = 600)
    private String describe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCarSourceCode() {
        return carSourceCode;
    }

    public void setCarSourceCode(String carSourceCode) {
        this.carSourceCode = carSourceCode;
    }

    public String getCarSource() {
        return carSource;
    }

    public void setCarSource(String carSource) {
        this.carSource = carSource;
    }

    public String getFacadeColourCode() {
        return facadeColourCode;
    }

    public void setFacadeColourCode(String facadeColourCode) {
        this.facadeColourCode = facadeColourCode;
    }

    public String getFacadeColour() {
        return facadeColour;
    }

    public void setFacadeColour(String facadeColour) {
        this.facadeColour = facadeColour;
    }

    public String getInteriorColourCode() {
        return interiorColourCode;
    }

    public void setInteriorColourCode(String interiorColourCode) {
        this.interiorColourCode = interiorColourCode;
    }

    public String getInteriorColour() {
        return interiorColour;
    }

    public void setInteriorColour(String interiorColour) {
        this.interiorColour = interiorColour;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
