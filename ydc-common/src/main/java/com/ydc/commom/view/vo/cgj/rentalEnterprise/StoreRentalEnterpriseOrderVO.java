package com.ydc.commom.view.vo.cgj.rentalEnterprise;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.commom.enums.rental.RentalEnterpriseOrderEnum;
import com.ydc.commom.enums.rental.RentalOrderEnum;
import com.ydc.commom.result.Result;
import com.ydc.model.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 企业租车订单
 *
 * @author
 * @create 2019-01-02 18:00
 **/
public class StoreRentalEnterpriseOrderVO implements Serializable {


    private static final long serialVersionUID = 7004224132773295176L;

    private Integer orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 资源信息记录ID
     */
    private Integer resourceInfoId;

    /**
     * 需求方ID
     */
    private Integer demandSideId;

    /**
     * 需求方
     */
    private String demandSide;

    /**
     * 需求方电话
     */
    private String demandSidePhone;

    /**
     * 资源方ID
     */
    private Integer resourceSideId;

    /**
     * 资源方
     */
    private String resourceSide;

    /**
     * 资源方电话
     */
    private String resourceSidePhone;

    /**
     * 车等级
     */
    private String carLevel;

    /**
     * 车品牌
     */
    private String carBrand;

    /**
     * 车系
     */
    private String carSeries;

    /**
     * 车型
     */
    private String carModel;

    /**
     * 车结构
     */
    private String carStructure;

    /**
     * 车座位数
     */
    private String carSeat;

    /**
     * 车型图片名称
     */
    private String carModelImgName;

    /**
     * 车型图片地址
     */
    private String carModelImgUrl;

    /**
     * 车型图片浏览
     */
    private String carModelImgBrowse;

    /**
     * 保证金额度
     */
    private BigDecimal marginLimit;

    /**
     * 预约取车时间
     */
    private Date appointmentFetchCarTime;

    /**
     * 预约还车时间
     */
    private Date appointmentRepayCarTime;

    /**
     * 取车方式（1：到店取车；2：送车上门）
     */
    private Integer fetchCarMode;

    /**
     * 取车方式名
     */
    private String fetchCarModeName;

    /**
     * 还车方式（1：到店还车；2：送车上门）
     */
    private Integer repayCarMode;

    /**
     * 还车方式名
     */
    private String repayCarModeName;

    /**
     * 车辆门店ID
     */
    private Integer carStoreId;

    /**
     * 车辆门店
     */
    private String carStoreName;

    /**
     * 租车门店地址
     */
    private String carStoreAddress;
    /**
     * 预定数量
     */
    private Integer reserveCount;

    /**
     * 预约取车地址省编码
     */
    private Integer appointmentFetchCarProvinceCode;

    /**
     * 预约取车地址省
     */
    private String appointmentFetchCarProvince;

    /**
     * 预约取车地址市编码
     */
    private Integer appointmentFetchCarCityCode;

    /**
     * 预约取车地址市
     */
    private String appointmentFetchCarCity;

    /**
     * 预约取车地址区编码
     */
    private Integer appointmentFetchCarRegionCode;

    /**
     * 预约取车地址区
     */
    private String appointmentFetchCarRegion;

    /**
     * 预约取车详情地址
     */
    private String appointmentFetchCarDetailsAddress;

    /**
     * 预约还车地址省编码
     */
    private Integer appointmentRepayCarProvinceCode;

    /**
     * 预约还车地址省
     */
    private String appointmentRepayCarProvince;

    /**
     * 预约还车地址市编码
     */
    private Integer appointmentRepayCarCityCode;

    /**
     * 预约还车地址市
     */
    private String appointmentRepayCarCity;

    /**
     * 预约还车地址区编码
     */
    private Integer appointmentRepayCarRegionCode;

    /**
     * 预约还车地址区
     */
    private String appointmentRepayCarRegion;

    /**
     * 预约还车详情地址
     */
    private String appointmentRepayCarDetailsAddress;

    /**
     * 出车时间
     */
    private Date comeCarTime;

    /**
     * 出车操作人ID
     */
    private Integer comeCarPersonId;

    /**
     * 出车操作人
     */
    private String comeCarPersonName;

    /**
     * 还车时间
     */
    private Date repayCarTime;

    /**
     * 还车操作人ID
     */
    private Integer repayCarPersonId;

    /**
     * 还车操作人
     */
    private String repayCarPersonName;

    /**
     * 车辆租赁费
     */
    private BigDecimal carRentalFee;

    private String carRentalFeeFormat;

    /**
     * 基础服务费
     */
    private BigDecimal baseServiceFee;

    private String baseServiceFeeFormat;

    /**
     * 押金
     */
    private BigDecimal cashPledge;

    private String cashPledgeFormat;

    private BigDecimal totalFee;//押金总额

    /**
     * 订单状态（1：备车；2：出车；3：还车；4：结算；98：已取消；99：已拒绝；100：成功）
     */
    private Integer status;

    /**
     * 状态名
     */
    private String statusName;

    /**
     * 前端控制状态
     */
    private Integer desStatus;

    /**
     * 流程状态一（100等于成功；0等于初始状态）
     */
    private Integer flowOneStatus;

    /**
     * 流程状态二（100等于成功；0等于初始状态）
     */
    private Integer flowTwoStatus;

    /**
     * 流程状态三（100等于成功；0等于初始状态）
     */
    private Integer flowThreeStatus;

    /**
     * 关闭之前的状态
     */
    private Integer closeBeforeStatus;

    /**
     * 取消方
     */
    private Integer cancelSide;

    /**
     * 取消操作人ID
     */
    private Integer cancelPersonId;

    /**
     * 取消操作人
     */
    private String cancelPerson;

    /**
     * 拒绝操作人ID
     */
    private Integer refusePersonId;

    /**
     * 拒绝操作人
     */
    private String refusePerson;

    /**
     * 创建时间
     */
    private Date createTime;

    //关闭时间
    private Date closeDate;

    /**
     * 预授权金额
     */
    private String preAuthorization;

    public static void main(String[] args) {
        List<StoreRentalEnterpriseOrderVO> storeRentalEnterpriseOrderVOS = new ArrayList<>();
        StoreRentalEnterpriseOrderVO storeRentalEnterpriseOrderVO = new StoreRentalEnterpriseOrderVO();
        storeRentalEnterpriseOrderVO.resourceSidePhone = "18811879947";
        storeRentalEnterpriseOrderVO.demandSidePhone = "15464654";
        storeRentalEnterpriseOrderVO.fetchCarMode = 1;
        storeRentalEnterpriseOrderVO.repayCarMode = 1;
        storeRentalEnterpriseOrderVO.status = 1;
        storeRentalEnterpriseOrderVO.flowOneStatus = 0;
        storeRentalEnterpriseOrderVO.flowTwoStatus = 0;
        storeRentalEnterpriseOrderVO.flowThreeStatus = 0;
        storeRentalEnterpriseOrderVO.appointmentRepayCarTime = DateUtil.parseDate("2019-01-30 02:00:00",DateUtil.DATATIMEF_STR);
        storeRentalEnterpriseOrderVO.appointmentFetchCarTime = DateUtil.parseDate("2019-01-23 00:00:00",DateUtil.DATATIMEF_STR);
        storeRentalEnterpriseOrderVOS.add(storeRentalEnterpriseOrderVO);
        storeRentalEnterpriseOrderVO = new StoreRentalEnterpriseOrderVO();
        storeRentalEnterpriseOrderVO.resourceSidePhone = "18811879947";
        storeRentalEnterpriseOrderVO.demandSidePhone = "15464654";
        storeRentalEnterpriseOrderVO.fetchCarMode = 1;
        storeRentalEnterpriseOrderVO.repayCarMode = 1;
        storeRentalEnterpriseOrderVO.status = 1;
        storeRentalEnterpriseOrderVO.flowOneStatus = 0;
        storeRentalEnterpriseOrderVO.flowTwoStatus = 0;
        storeRentalEnterpriseOrderVO.flowThreeStatus = 0;
        storeRentalEnterpriseOrderVO.appointmentRepayCarTime = DateUtil.parseDate("2019-01-30 03:00:00",DateUtil.DATATIMEF_STR);
        storeRentalEnterpriseOrderVO.appointmentFetchCarTime = DateUtil.parseDate("2019-01-23 01:00:00",DateUtil.DATATIMEF_STR);
        storeRentalEnterpriseOrderVOS.add(storeRentalEnterpriseOrderVO);

        System.out.println(Result.success(storeRentalEnterpriseOrderVOS).toJSON());
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getDemandSideId() {
        return demandSideId;
    }

    public void setDemandSideId(Integer demandSideId) {
        this.demandSideId = demandSideId;
    }

    public String getDemandSide() {
        return demandSide;
    }

    public void setDemandSide(String demandSide) {
        this.demandSide = demandSide;
    }

    public Integer getResourceSideId() {
        return resourceSideId;
    }

    public void setResourceSideId(Integer resourceSideId) {
        this.resourceSideId = resourceSideId;
    }

    public String getResourceSide() {
        return resourceSide;
    }

    public void setResourceSide(String resourceSide) {
        this.resourceSide = resourceSide;
    }


    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarStructure() {
        return carStructure;
    }

    public void setCarStructure(String carStructure) {
        this.carStructure = carStructure;
    }

    public String getCarSeat() {
        return carSeat;
    }

    public void setCarSeat(String carSeat) {
        this.carSeat = carSeat;
    }

    public String getCarModelImgName() {
        return carModelImgName;
    }

    public void setCarModelImgName(String carModelImgName) {
        this.carModelImgName = carModelImgName;
    }

    public String getCarModelImgUrl() {
        return carModelImgUrl;
    }

    public void setCarModelImgUrl(String carModelImgUrl) {
        this.carModelImgUrl = carModelImgUrl;
    }

    @JSONField(format = "MM月dd日 HH时")
    public Date getAppointmentFetchCarTime() {
        return appointmentFetchCarTime;
    }

    public void setAppointmentFetchCarTime(Date appointmentFetchCarTime) {
        this.appointmentFetchCarTime = appointmentFetchCarTime;
    }

    @JSONField(format = "MM月dd日 HH时")
    public Date getAppointmentRepayCarTime() {
        return appointmentRepayCarTime;
    }

    public void setAppointmentRepayCarTime(Date appointmentRepayCarTime) {
        this.appointmentRepayCarTime = appointmentRepayCarTime;
    }

    public Integer getFetchCarMode() {
        return fetchCarMode;
    }

    public void setFetchCarMode(Integer fetchCarMode) {
        this.fetchCarMode = fetchCarMode;
    }

    public Integer getRepayCarMode() {
        return repayCarMode;
    }

    public void setRepayCarMode(Integer repayCarMode) {
        this.repayCarMode = repayCarMode;
    }

    public Integer getCarStoreId() {
        return carStoreId;
    }

    public void setCarStoreId(Integer carStoreId) {
        this.carStoreId = carStoreId;
    }

    public String getCarStoreName() {
        return carStoreName;
    }

    public void setCarStoreName(String carStoreName) {
        this.carStoreName = carStoreName;
    }

    public Integer getReserveCount() {
        return reserveCount;
    }

    public void setReserveCount(Integer reserveCount) {
        this.reserveCount = reserveCount;
    }

    public Integer getAppointmentFetchCarProvinceCode() {
        return appointmentFetchCarProvinceCode;
    }

    public void setAppointmentFetchCarProvinceCode(Integer appointmentFetchCarProvinceCode) {
        this.appointmentFetchCarProvinceCode = appointmentFetchCarProvinceCode;
    }

    public String getAppointmentFetchCarProvince() {
        return appointmentFetchCarProvince;
    }

    public void setAppointmentFetchCarProvince(String appointmentFetchCarProvince) {
        this.appointmentFetchCarProvince = appointmentFetchCarProvince;
    }

    public Integer getAppointmentFetchCarCityCode() {
        return appointmentFetchCarCityCode;
    }

    public void setAppointmentFetchCarCityCode(Integer appointmentFetchCarCityCode) {
        this.appointmentFetchCarCityCode = appointmentFetchCarCityCode;
    }

    public String getAppointmentFetchCarCity() {
        return appointmentFetchCarCity;
    }

    public void setAppointmentFetchCarCity(String appointmentFetchCarCity) {
        this.appointmentFetchCarCity = appointmentFetchCarCity;
    }

    public Integer getAppointmentFetchCarRegionCode() {
        return appointmentFetchCarRegionCode;
    }

    public void setAppointmentFetchCarRegionCode(Integer appointmentFetchCarRegionCode) {
        this.appointmentFetchCarRegionCode = appointmentFetchCarRegionCode;
    }

    public String getAppointmentFetchCarRegion() {
        return appointmentFetchCarRegion;
    }

    public void setAppointmentFetchCarRegion(String appointmentFetchCarRegion) {
        this.appointmentFetchCarRegion = appointmentFetchCarRegion;
    }

    public String getAppointmentFetchCarDetailsAddress() {
        return appointmentFetchCarDetailsAddress;
    }

    public void setAppointmentFetchCarDetailsAddress(String appointmentFetchCarDetailsAddress) {
        this.appointmentFetchCarDetailsAddress = appointmentFetchCarDetailsAddress;
    }

    public Integer getAppointmentRepayCarProvinceCode() {
        return appointmentRepayCarProvinceCode;
    }

    public void setAppointmentRepayCarProvinceCode(Integer appointmentRepayCarProvinceCode) {
        this.appointmentRepayCarProvinceCode = appointmentRepayCarProvinceCode;
    }

    public String getAppointmentRepayCarProvince() {
        return appointmentRepayCarProvince;
    }

    public void setAppointmentRepayCarProvince(String appointmentRepayCarProvince) {
        this.appointmentRepayCarProvince = appointmentRepayCarProvince;
    }

    public Integer getAppointmentRepayCarCityCode() {
        return appointmentRepayCarCityCode;
    }

    public void setAppointmentRepayCarCityCode(Integer appointmentRepayCarCityCode) {
        this.appointmentRepayCarCityCode = appointmentRepayCarCityCode;
    }

    public String getAppointmentRepayCarCity() {
        return appointmentRepayCarCity;
    }

    public void setAppointmentRepayCarCity(String appointmentRepayCarCity) {
        this.appointmentRepayCarCity = appointmentRepayCarCity;
    }

    public Integer getAppointmentRepayCarRegionCode() {
        return appointmentRepayCarRegionCode;
    }

    public void setAppointmentRepayCarRegionCode(Integer appointmentRepayCarRegionCode) {
        this.appointmentRepayCarRegionCode = appointmentRepayCarRegionCode;
    }

    public String getAppointmentRepayCarRegion() {
        return appointmentRepayCarRegion;
    }

    public void setAppointmentRepayCarRegion(String appointmentRepayCarRegion) {
        this.appointmentRepayCarRegion = appointmentRepayCarRegion;
    }

    public String getAppointmentRepayCarDetailsAddress() {
        return appointmentRepayCarDetailsAddress;
    }

    public void setAppointmentRepayCarDetailsAddress(String appointmentRepayCarDetailsAddress) {
        this.appointmentRepayCarDetailsAddress = appointmentRepayCarDetailsAddress;
    }

    @JSONField(format = DateUtil.DATATIMEF_STR)
    public Date getComeCarTime() {
        return comeCarTime;
    }

    public void setComeCarTime(Date comeCarTime) {
        this.comeCarTime = comeCarTime;
    }

    public Integer getComeCarPersonId() {
        return comeCarPersonId;
    }

    public void setComeCarPersonId(Integer comeCarPersonId) {
        this.comeCarPersonId = comeCarPersonId;
    }

    public String getComeCarPersonName() {
        return comeCarPersonName;
    }

    public void setComeCarPersonName(String comeCarPersonName) {
        this.comeCarPersonName = comeCarPersonName;
    }

    @JSONField(format = DateUtil.DATATIMEF_STR)
    public Date getRepayCarTime() {
        return repayCarTime;
    }

    public void setRepayCarTime(Date repayCarTime) {
        this.repayCarTime = repayCarTime;
    }

    public Integer getRepayCarPersonId() {
        return repayCarPersonId;
    }

    public void setRepayCarPersonId(Integer repayCarPersonId) {
        this.repayCarPersonId = repayCarPersonId;
    }

    public String getRepayCarPersonName() {
        return repayCarPersonName;
    }

    public void setRepayCarPersonName(String repayCarPersonName) {
        this.repayCarPersonName = repayCarPersonName;
    }

    public BigDecimal getCarRentalFee() {
        return carRentalFee;
    }

    public void setCarRentalFee(BigDecimal carRentalFee) {
        this.carRentalFee = carRentalFee;
    }

    public BigDecimal getBaseServiceFee() {
        return baseServiceFee;
    }

    public void setBaseServiceFee(BigDecimal baseServiceFee) {
        this.baseServiceFee = baseServiceFee;
    }

    public BigDecimal getCashPledge() {
        return cashPledge;
    }

    public void setCashPledge(BigDecimal cashPledge) {
        this.cashPledge = cashPledge;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFlowOneStatus() {
        return flowOneStatus;
    }

    public void setFlowOneStatus(Integer flowOneStatus) {
        this.flowOneStatus = flowOneStatus;
    }

    public Integer getFlowTwoStatus() {
        return flowTwoStatus;
    }

    public void setFlowTwoStatus(Integer flowTwoStatus) {
        this.flowTwoStatus = flowTwoStatus;
    }

    public Integer getFlowThreeStatus() {
        return flowThreeStatus;
    }

    public void setFlowThreeStatus(Integer flowThreeStatus) {
        this.flowThreeStatus = flowThreeStatus;
    }

    public String getDemandSidePhone() {
        return demandSidePhone;
    }

    public void setDemandSidePhone(String demandSidePhone) {
        this.demandSidePhone = demandSidePhone;
    }

    public String getFetchCarModeName() {
        return RentalOrderEnum.FetchCarMode.getFetchCarModeByStatus(this.fetchCarMode).getStatusCH();
    }

    public void setFetchCarModeName(String fetchCarModeName) {
        this.fetchCarModeName = fetchCarModeName;
    }

    public String getRepayCarModeName() {
        return RentalOrderEnum.RepayCarMode.getRepayCarModeByStatus(this.repayCarMode).getStatusCH();
    }

    public void setRepayCarModeName(String repayCarModeName) {
        this.repayCarModeName = repayCarModeName;
    }

    public String getCarStoreAddress() {
        return carStoreAddress;
    }

    public void setCarStoreAddress(String carStoreAddress) {
        this.carStoreAddress = carStoreAddress;
    }

    public String getStatusName() {
       return RentalEnterpriseOrderEnum.OrderStatus.getDescribeStore(this.status,this.flowOneStatus,this.flowTwoStatus,this.flowThreeStatus);
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getCloseBeforeStatus() {
        return closeBeforeStatus;
    }

    public void setCloseBeforeStatus(Integer closeBeforeStatus) {
        this.closeBeforeStatus = closeBeforeStatus;
    }

    public Integer getCancelSide() {
        return cancelSide;
    }

    public void setCancelSide(Integer cancelSide) {
        this.cancelSide = cancelSide;
    }

    public Integer getCancelPersonId() {
        return cancelPersonId;
    }

    public void setCancelPersonId(Integer cancelPersonId) {
        this.cancelPersonId = cancelPersonId;
    }

    public String getCancelPerson() {
        return cancelPerson;
    }

    public void setCancelPerson(String cancelPerson) {
        this.cancelPerson = cancelPerson;
    }

    public Integer getRefusePersonId() {
        return refusePersonId;
    }

    public void setRefusePersonId(Integer refusePersonId) {
        this.refusePersonId = refusePersonId;
    }

    public String getRefusePerson() {
        return refusePerson;
    }

    public void setRefusePerson(String refusePerson) {
        this.refusePerson = refusePerson;
    }

    public Integer getDesStatus() {
        return RentalEnterpriseOrderEnum.OrderStatus.getStatus(this.status,this.flowOneStatus,this.flowTwoStatus,this.flowThreeStatus);
    }

    public void setDesStatus(Integer desStatus) {
        this.desStatus = desStatus;
    }

    public BigDecimal getMarginLimit() {
        return marginLimit;
    }

    public void setMarginLimit(BigDecimal marginLimit) {
        this.marginLimit = marginLimit;
    }

    public String getResourceSidePhone() {
        return resourceSidePhone;
    }

    public void setResourceSidePhone(String resourceSidePhone) {
        this.resourceSidePhone = resourceSidePhone;
    }
    @JSONField(format = DateUtil.DATATIMEF_STR)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCarModelImgBrowse() {
        return carModelImgBrowse;
    }

    public void setCarModelImgBrowse(String carModelImgBrowse) {
        this.carModelImgBrowse = carModelImgBrowse;
    }

    public Integer getResourceInfoId() {
        return resourceInfoId;
    }

    public void setResourceInfoId(Integer resourceInfoId) {
        this.resourceInfoId = resourceInfoId;
    }

    public String getCarRentalFeeFormat() {
        return carRentalFeeFormat;
    }

    public void setCarRentalFeeFormat(String carRentalFeeFormat) {
        this.carRentalFeeFormat = carRentalFeeFormat;
    }

    public String getBaseServiceFeeFormat() {
        return baseServiceFeeFormat;
    }

    public void setBaseServiceFeeFormat(String baseServiceFeeFormat) {
        this.baseServiceFeeFormat = baseServiceFeeFormat;
    }

    public String getCashPledgeFormat() {
        return cashPledgeFormat;
    }

    public void setCashPledgeFormat(String cashPledgeFormat) {
        this.cashPledgeFormat = cashPledgeFormat;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    @JSONField(format = DateUtil.DATATIMEF_STR)
    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public String getPreAuthorization() {
        return preAuthorization;
    }

    public void setPreAuthorization(String preAuthorization) {
        this.preAuthorization = preAuthorization;
    }
}
