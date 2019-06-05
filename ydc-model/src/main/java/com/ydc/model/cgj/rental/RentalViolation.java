package com.ydc.model.cgj.rental;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RentalViolation implements Serializable {
    private static final long serialVersionUID = -7383056197649282396L;
    /**
     * t_rental_violation.id
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private Integer id;

    /**
     * t_rental_violation.car_id (车辆id)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private Integer carId;

    /**
     * t_rental_violation.order_type (订单类型（0：租车单，1：机务单）)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private String orderType;

    /**
     * t_rental_violation.order_id (订单id)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private Integer orderId;

    /**
     * t_rental_violation.dispose_order_id (处理单id)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private Integer disposeOrderId;

    /**
     * t_rental_violation.violation_time (违章发生时间)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private Date violationTime;

    /**
     * t_rental_violation.violation_persion_name (违章人)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private String violationPersionName;

    /**
     * t_rental_violation.violation_persion_id_card (违章人身份证号)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private String violationPersionIdCard;

    /**
     * t_rental_violation.violation_type (违章类型)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private String violationType;

    /**
     * t_rental_violation.violation_penalty (违章罚款)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private BigDecimal violationPenalty;

    /**
     * t_rental_violation.violation_score (违章积分)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private Integer violationScore;

    /**
     * t_rental_violation.violation_at_province_code (违章所在地省code)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private String violationAtProvinceCode;

    /**
     * t_rental_violation.violation_at_province (违章所在地省)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private String violationAtProvince;

    /**
     * t_rental_violation.violation_at_city_code (违章所在地市code)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private String violationAtCityCode;

    /**
     * t_rental_violation.violation_at_city (违章所在地市)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private String violationAtCity;

    /**
     * t_rental_violation.violation_at_district_code (违章所在地县/区code)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private String violationAtDistrictCode;

    /**
     * t_rental_violation.violation_at_district (违章所在地县/区)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private String violationAtDistrict;

    /**
     * t_rental_violation.violation_at_address (违章所在详细地址)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private String violationAtAddress;

    /**
     * t_rental_violation.status 状态（0：无效；1：有效）
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private Integer status;

    /**
     * t_rental_violation.create_time (创建时间)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private Date createTime;

    /**
     * t_rental_violation.create_by (创建人)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private Integer createBy;

    /**
     * t_rental_violation.update_time (修改时间)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private Date updateTime;

    /**
     * t_rental_violation.update_by (修改人)
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    private Integer updateBy;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String fileUrl;

    /**
     * 缩略图文件名
     */
    private String littleFileName;

    /**
     * 缩略图文件路径
     */
    private String littleFileUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDisposeOrderId() {
        return disposeOrderId;
    }

    public void setDisposeOrderId(Integer disposeOrderId) {
        this.disposeOrderId = disposeOrderId;
    }

    public Date getViolationTime() {
        return violationTime;
    }

    public void setViolationTime(Date violationTime) {
        this.violationTime = violationTime;
    }

    public String getViolationPersionName() {
        return violationPersionName;
    }

    public void setViolationPersionName(String violationPersionName) {
        this.violationPersionName = violationPersionName;
    }

    public String getViolationPersionIdCard() {
        return violationPersionIdCard;
    }

    public void setViolationPersionIdCard(String violationPersionIdCard) {
        this.violationPersionIdCard = violationPersionIdCard;
    }

    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    public BigDecimal getViolationPenalty() {
        return violationPenalty;
    }

    public void setViolationPenalty(BigDecimal violationPenalty) {
        this.violationPenalty = violationPenalty;
    }

    public Integer getViolationScore() {
        return violationScore;
    }

    public void setViolationScore(Integer violationScore) {
        this.violationScore = violationScore;
    }

    public String getViolationAtProvinceCode() {
        return violationAtProvinceCode;
    }

    public void setViolationAtProvinceCode(String violationAtProvinceCode) {
        this.violationAtProvinceCode = violationAtProvinceCode;
    }

    public String getViolationAtProvince() {
        return violationAtProvince;
    }

    public void setViolationAtProvince(String violationAtProvince) {
        this.violationAtProvince = violationAtProvince;
    }

    public String getViolationAtCityCode() {
        return violationAtCityCode;
    }

    public void setViolationAtCityCode(String violationAtCityCode) {
        this.violationAtCityCode = violationAtCityCode;
    }

    public String getViolationAtCity() {
        return violationAtCity;
    }

    public void setViolationAtCity(String violationAtCity) {
        this.violationAtCity = violationAtCity;
    }

    public String getViolationAtDistrictCode() {
        return violationAtDistrictCode;
    }

    public void setViolationAtDistrictCode(String violationAtDistrictCode) {
        this.violationAtDistrictCode = violationAtDistrictCode;
    }

    public String getViolationAtDistrict() {
        return violationAtDistrict;
    }

    public void setViolationAtDistrict(String violationAtDistrict) {
        this.violationAtDistrict = violationAtDistrict;
    }

    public String getViolationAtAddress() {
        return violationAtAddress;
    }

    public void setViolationAtAddress(String violationAtAddress) {
        this.violationAtAddress = violationAtAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
}