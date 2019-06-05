package com.ydc.commom.view.vo.cgj.rental;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ydc.commom.enums.rental.RentalViolationEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class RentalViolationVO {
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
    private String violationTime;

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
    private String violationPenalty;

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
     * 所属门店
     */
    private String store;

    /**
     * 车牌号
     */
    private String carPlate;

    /**
     * 车辆品牌
     */
    private String brand;

    /**
     * 车系
     */
    private String series;

    /**
     * 车型
     */
    private String model;

    /**
     * 出车时间
     */
    private String comeCarTime;

    /**
     * 还车时间
     */
    private String repayCarTime;

    /**
     * 驾车人姓名
     */
    private String coachmanName;

    /**
     * 出库所在门店
     */
    private String comeWarehouseStoreName;

    /**
     * 还车所在门店
     */
    private String repayCarWarehouseStoreName;

    /**
     * 还车操作人姓名
     */
    private String repayCarCoachmanName;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String fileUrl;

    //图片浏览url
    private String viewFileUrl;

    //处理状态
    private String dealStatus;

    //处理人
    private String dealBy;

    //缴费凭证文件地址
    private String dealChargeFileUrl;

    //缴费凭证文件名
    private String dealChargeFileName;

    //缴费凭证文件在线浏览地址
    private String viewDealChargeFileUrl;

    //处理提交时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date  dealCommitTime;

    //处理提交人
    private String dealCommitBy;


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

    public String getViolationTime() {
        return violationTime;
    }

    public void setViolationTime(String violationTime) {
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

    public String getViolationPenalty() {
        return violationPenalty;
    }

    public void setViolationPenalty(String violationPenalty) {
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

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getComeCarTime() {
        return comeCarTime;
    }

    public void setComeCarTime(String comeCarTime) {
        this.comeCarTime = comeCarTime;
    }

    public String getRepayCarTime() {
        return repayCarTime;
    }

    public void setRepayCarTime(String repayCarTime) {
        this.repayCarTime = repayCarTime;
    }

    public String getCoachmanName() {
        return coachmanName;
    }

    public void setCoachmanName(String coachmanName) {
        this.coachmanName = coachmanName;
    }

    public String getComeWarehouseStoreName() {
        return comeWarehouseStoreName;
    }

    public void setComeWarehouseStoreName(String comeWarehouseStoreName) {
        this.comeWarehouseStoreName = comeWarehouseStoreName;
    }

    public String getRepayCarWarehouseStoreName() {
        return repayCarWarehouseStoreName;
    }

    public void setRepayCarWarehouseStoreName(String repayCarWarehouseStoreName) {
        this.repayCarWarehouseStoreName = repayCarWarehouseStoreName;
    }

    public String getRepayCarCoachmanName() {
        return repayCarCoachmanName;
    }

    public void setRepayCarCoachmanName(String repayCarCoachmanName) {
        this.repayCarCoachmanName = repayCarCoachmanName;
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

    public String getViewFileUrl() {
        return viewFileUrl;
    }

    public void setViewFileUrl(String viewFileUrl) {
        this.viewFileUrl = viewFileUrl;
    }


    public String getDealStatus() {
        return this.dealStatus == null?"":RentalViolationEnum.DealStatusEnum.getCodeName(Integer.parseInt(this.dealStatus));
    }

    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getDealBy() {
        return this.dealBy == null?null:RentalViolationEnum.DealByEnum.getCodeName(Integer.parseInt(this.dealBy));
    }

    public void setDealBy(String dealBy) {
        this.dealBy = dealBy;
    }

    public String getDealChargeFileUrl() {
        return dealChargeFileUrl;
    }

    public void setDealChargeFileUrl(String dealChargeFileUrl) {
        this.dealChargeFileUrl = dealChargeFileUrl;
    }

    public String getDealChargeFileName() {
        return dealChargeFileName;
    }

    public void setDealChargeFileName(String dealChargeFileName) {
        this.dealChargeFileName = dealChargeFileName;
    }

    public String getViewDealChargeFileUrl() {
        return viewDealChargeFileUrl;
    }

    public void setViewDealChargeFileUrl(String viewDealChargeFileUrl) {
        this.viewDealChargeFileUrl = viewDealChargeFileUrl;
    }

    public Date getDealCommitTime() {
        return dealCommitTime;
    }

    public void setDealCommitTime(Date dealCommitTime) {
        this.dealCommitTime = dealCommitTime;
    }

    public String getDealCommitBy() {
        return dealCommitBy;
    }

    public void setDealCommitBy(String dealCommitBy) {
        this.dealCommitBy = dealCommitBy;
    }
}
