package com.ydc.commom.view.dto.ydhc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 车辆展示类
 */
public class VehicleDto {
    private Integer id;

    /**
     * 车辆标题
     */
    private String title;

    /**
     * 车辆价格
     */
    private BigDecimal price;

    /**
     * 车源类型
     */
    private String carSource;

    /**
     * 车源类型code
     */
    private String carSourceCode;

    /**
     * 内饰颜色
     */
    private String interiorColour;

    /**
     * 外观颜色
     */
    private String facadeColour;

    /**
     * 车辆品牌
     */
    private String brand;

    /**
     * 货期
     */
    private String delivery;

    /**
     * 车辆所在地省
     */
    private String province;

    /**
     * 车辆所在地市
     */
    private String city;

    /**
     * 手续
     */
    private String procedure;

    /**
     * 配置信息
     */
    private String config;

    /**
     * 商家
     */
    private String merchant;

    /**
     * 主图（缩略图和正常图）
     */
    private List<Map<String, String>> mainPic;

    /**
     * 描述图（缩略图和正常图）
     */
    private List<Map<String, String>> descriptionPic;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCarSourceCode() {
        return carSourceCode;
    }

    public void setCarSourceCode(String carSourceCode) {
        this.carSourceCode = carSourceCode;
    }

    public String getFacadeColour() {
        return facadeColour;
    }

    public void setFacadeColour(String facadeColour) {
        this.facadeColour = facadeColour;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public String getMerchant(){return merchant;}

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public List<Map<String, String>> getMainPic() {
        return mainPic;
    }

    public void setMainPic(List<Map<String, String>> mainPic) {
        this.mainPic = mainPic;
    }

    public List<Map<String, String>> getDescriptionPic() {
        return descriptionPic;
    }

    public void setDescriptionPic(List<Map<String, String>> descriptionPic) {
        this.descriptionPic = descriptionPic;
    }

    public String getCarSource() {
        return carSource;
    }

    public void setCarSource(String carSource) {
        this.carSource = carSource;
    }

    public String getInteriorColour() {
        return interiorColour;
    }

    public void setInteriorColour(String interiorColour) {
        this.interiorColour = interiorColour;
    }
}