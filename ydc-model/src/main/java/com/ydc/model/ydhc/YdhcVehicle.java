package com.ydc.model.ydhc;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * t_ydhc_vehicle
 * @author 
 */
public class YdhcVehicle implements Serializable {
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
     * 车源类型code
     */
    private String carSourceCode;

    /**
     * 车源类型
     */
    private String carSource;

    /**
     * 外观颜色code
     */
    private String facadeColourCode;

    /**
     * 外观颜色
     */
    private String facadeColour;

    /**
     * 内饰颜色code
     */
    private String interiorColourCode;

    /**
     * 内饰颜色
     */
    private String interiorColour;

    /**
     * 车辆品牌code
     */
    private String brandCode;

    /**
     * 车辆品牌
     */
    private String brand;

    /**
     * 车系code
     */
    private String seriesCode;

    /**
     * 车系
     */
    private String series;

    /**
     * 货期code
     */
    private String deliveryCode;

    /**
     * 货期
     */
    private String delivery;

    /**
     * 车辆所在地省编码
     */
    private String provinceCode;

    /**
     * 车辆所在地省
     */
    private String province;

    /**
     * 车辆所在地市编码
     */
    private String cityCode;

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
     * 商家code
     */
    private String merchantCode;

    /**
     * 商家
     */
    private String merchant;

    /**
     * 描述内容
     */
    private String describe;

    /**
     * 发布状态（0：未发布；1已发布；2：已下架
     */
    private Integer releaseStatus;

    /**
     * 发布时间
     */
    private Date releaseDate;

    /**
     * 发布人
     */
    private String releasePerson;

    /**
     * 发布人ID
     */
    private Integer releasePersonId;

    /**
     * 下架时间
     */
    private Date shelveDate;

    /**
     * 下架人
     */
    private String shelvePerson;

    /**
     * 下架人ID
     */
    private Integer shelvePersonId;

    private Date createTime;

    private Integer createBy;

    private Date updateTime;

    private Integer updateBy;

    private static final long serialVersionUID = 1L;

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

    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleasePerson() {
        return releasePerson;
    }

    public void setReleasePerson(String releasePerson) {
        this.releasePerson = releasePerson;
    }

    public Integer getReleasePersonId() {
        return releasePersonId;
    }

    public void setReleasePersonId(Integer releasePersonId) {
        this.releasePersonId = releasePersonId;
    }

    public Date getShelveDate() {
        return shelveDate;
    }

    public void setShelveDate(Date shelveDate) {
        this.shelveDate = shelveDate;
    }

    public String getShelvePerson() {
        return shelvePerson;
    }

    public void setShelvePerson(String shelvePerson) {
        this.shelvePerson = shelvePerson;
    }

    public Integer getShelvePersonId() {
        return shelvePersonId;
    }

    public void setShelvePersonId(Integer shelvePersonId) {
        this.shelvePersonId = shelvePersonId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public YdhcVehicle(){

    }

    public YdhcVehicle(Map<String, String> param){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.id = StringUtils.isEmpty(param.get("id")) ? null : Integer.valueOf(param.get("id"));
            this.title = param.get("title");
            this.price = StringUtils.isEmpty(param.get("price")) ? null : new BigDecimal(param.get("price"));
            this.carSourceCode = param.get("carSourceCode");
            this.carSource = param.get("carSource");
            this.facadeColourCode = param.get("facadeColourCode");
            this.facadeColour = param.get("facadeColour");
            this.interiorColourCode = param.get("interiorColourCode");
            this.interiorColour = param.get("interiorColour");
            this.brandCode = param.get("brandCode");
            this.brand = param.get("brand");
            this.seriesCode = param.get("seriesCode");
            this.series = param.get("series");
            this.deliveryCode = param.get("deliveryCode");
            this.delivery = param.get("delivery");
            this.provinceCode = param.get("provinceCode");
            this.province = param.get("province");
            this.cityCode = param.get("cityCode");
            this.city = param.get("city");
            this.procedure = param.get("procedure");
            this.config = param.get("config");
            this.merchantCode = param.get("merchantCode");
            this.merchant = param.get("merchant");
            this.describe = param.get("describe");
            this.releaseStatus = StringUtils.isEmpty(param.get("releaseStatus")) ? null : Integer.valueOf(param.get("releaseStatus"));
            this.releaseDate = StringUtils.isEmpty(param.get("releaseDate")) ? null:sdf.parse(param.get("releaseDate"));
            this.releasePerson = param.get("releasePerson");
            this.releasePersonId = StringUtils.isEmpty(param.get("releasePersonId")) ? null : Integer.valueOf(param.get("releasePersonId"));
            this.shelveDate = StringUtils.isEmpty(param.get("shelveDate")) ? null:sdf.parse(param.get("shelveDate"));
            this.shelvePerson = param.get("shelvePerson");
            this.shelvePersonId = StringUtils.isEmpty(param.get("shelvePersonId")) ? null : Integer.valueOf(param.get("shelvePersonId"));
            this.createTime = StringUtils.isEmpty(param.get("createTime")) ? null:sdf.parse(param.get("createTime"));
            this.createBy = StringUtils.isEmpty(param.get("createBy")) ? null : Integer.valueOf(param.get("createBy"));
            this.updateTime = StringUtils.isEmpty(param.get("updateTime")) ? null:sdf.parse(param.get("updateTime"));
            this.updateBy = StringUtils.isEmpty(param.get("updateBy")) ? null : Integer.valueOf(param.get("updateBy"));
        } catch (Exception e) {
            throw new RuntimeException("时间格式化异常");
        }
    }

}