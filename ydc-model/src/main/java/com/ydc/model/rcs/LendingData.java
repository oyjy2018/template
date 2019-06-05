package com.ydc.model.rcs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 放款数据报表
 */
public class LendingData implements Serializable {

    private static final long serialVersionUID = -1501841268724046456L;
    /**
     * t_rp_lending_data.id
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Integer id;

    /**
     * t_rp_lending_data.loan_id (借款ID)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Integer loanId;

    /**
     * t_rp_lending_data.contract_number (合同号)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String contractNumber;

    /**
     * t_rp_lending_data.customer_name (客户名)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String customerName;

    /**
     * t_rp_lending_data.org_id (机构ID)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Integer orgId;

    /**
     * t_rp_lending_data.org_name (机构名)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String orgName;

    /**
     * t_rp_lending_data.loan_product (借款产品(押证不押车:101、押车押证:102、押证不押车-911:103))
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Integer loanProduct;

    /**
     * t_rp_lending_data.loan_nature (借款性质（0：新增；1：结清再贷；2：借新还旧；3：先转等；4：续借）)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Byte loanNature;

    /**
     * t_rp_lending_data.create_source (进件渠道（0：PC，1：钉钉，2小程序，3APP）)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Byte createSource;

    /**
     * t_rp_lending_data.repayment_mode (还款方式(等额等息:8000、先息后本:8001))
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Integer repaymentMode;

    /**
     * t_rp_lending_data.has_second_loan (是否秒贷，1是0否)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String hasSecondLoan;

    /**
     * t_rp_lending_data.loan_period (审核期限)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Byte loanPeriod;

    /**
     * t_rp_lending_data.loan_money (审核金额)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private BigDecimal loanMoney;

    /**
     * t_rp_lending_data.bidding_money (发标金额)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private BigDecimal biddingMoney;

    /**
     * t_rp_lending_data.bidding_platform (发标平台)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String biddingPlatform;

    /**
     * t_rp_lending_data.jkylilv (月利率)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String jkylilv;

    /**
     * t_rp_lending_data.loan_date (借款起始日)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Date loanDate;

    /**
     * t_rp_lending_data.full_scale_time (满标时间)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Date fullScaleTime;

    /**
     * t_rp_lending_data.vehicle_status (车辆状态（0：正常；1：二押；2：有线GPS离线；3：无线GPS离线；4：设备全部离线；5：在线不在位；6：已拖车；7：结清赎车；8：还款放车；9：资产处理；）)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Integer vehicleStatus;

    /**
     * t_rp_lending_data.gender (性别)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String gender;

    /**
     * t_rp_lending_data.age (年龄)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String age;

    /**
     * t_rp_lending_data.marriage_status (婚姻状态)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String marriageStatus;

    /**
     * t_rp_lending_data.work_position (职位)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String workPosition;

    /**
     * t_rp_lending_data.monthly_income (每月收入)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String monthlyIncome;

    /**
     * t_rp_lending_data.is_house (有无房产)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String isHouse;

    /**
     * t_rp_lending_data.buy_type (购买类型（1：一次性；2：按揭）)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String buyType;

    /**
     * t_rp_lending_data.monthly_installment_payment (月供)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private BigDecimal monthlyInstallmentPayment;

    /**
     * t_rp_lending_data.house_property (房产所有权性质)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String houseProperty;

    /**
     * t_rp_lending_data.vehicle_number (车牌号)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String vehicleNumber;

    /**
     * t_rp_lending_data.fist_register_date (初次登记日期)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Date fistRegisterDate;

    /**
     * t_rp_lending_data.last_transfer_date (最后一次转移登记日)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Date lastTransferDate;

    /**
     * t_rp_lending_data.vehicle_type (车辆类型)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String vehicleType;

    /**
     * t_rp_lending_data.vehicle_brand (车辆品牌)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String vehicleBrand;

    /**
     * t_rp_lending_data.vehicle_model (车辆型号)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String vehicleModel;

    /**
     * t_rp_lending_data.vehicle_colour (车辆颜色)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String vehicleColour;

    /**
     * t_rp_lending_data.vehicle_mileage (公里数)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Integer vehicleMileage;

    /**
     * t_rp_lending_data.vehicle_attribution (车辆归属)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String vehicleAttribution;

    /**
     * t_rp_lending_data.vehicle_pledge_count (抵押次数)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Byte vehiclePledgeCount;

    /**
     * t_rp_lending_data.create_time (创建时间)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Date createTime;

    /**
     * t_rp_lending_data.statistics_date (统计时间)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String statisticsDate;

    /**
     * t_rp_lending_data.asset_side (资产端（1：鸿特 2：一点）)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String assetSide;

    /**
     * t_rp_lending_data.create_user_id (录单风控ID)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Integer createUserId;

    /**
     * t_rp_lending_data.create_user_name (录单风控)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String createUserName;

    /**
     * t_rp_lending_data.supervisor_id (跟踪风控ID)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Integer supervisorId;

    /**
     * t_rp_lending_data.supervisor_name (跟踪风控)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String supervisorName;

    /**
     * t_rp_lending_data.customer_manager_id (客户经理ID)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Integer customerManagerId;

    /**
     * t_rp_lending_data.customer_manager_name (客户经理)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String customerManagerName;

    /**
     * t_rp_lending_data.team_manager_id (团队经理ID)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Integer teamManagerId;

    /**
     * t_rp_lending_data.team_manager_name (团队经理)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String teamManagerName;

    /**
     * t_rp_lending_data.appraiser_person_id (评估师人员ID)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Integer appraiserPersonId;

    /**
     * t_rp_lending_data.appraiser_person_name (评估师人员名称)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private String appraiserPersonName;

    /**
     * t_rp_lending_data.update_time (更新时间)
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getLoanProduct() {
        return loanProduct;
    }

    public void setLoanProduct(Integer loanProduct) {
        this.loanProduct = loanProduct;
    }

    public Byte getLoanNature() {
        return loanNature;
    }

    public void setLoanNature(Byte loanNature) {
        this.loanNature = loanNature;
    }

    public Byte getCreateSource() {
        return createSource;
    }

    public void setCreateSource(Byte createSource) {
        this.createSource = createSource;
    }

    public Integer getRepaymentMode() {
        return repaymentMode;
    }

    public void setRepaymentMode(Integer repaymentMode) {
        this.repaymentMode = repaymentMode;
    }

    public String getHasSecondLoan() {
        return hasSecondLoan;
    }

    public void setHasSecondLoan(String hasSecondLoan) {
        this.hasSecondLoan = hasSecondLoan;
    }

    public Byte getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(Byte loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public BigDecimal getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(BigDecimal loanMoney) {
        this.loanMoney = loanMoney;
    }

    public BigDecimal getBiddingMoney() {
        return biddingMoney;
    }

    public void setBiddingMoney(BigDecimal biddingMoney) {
        this.biddingMoney = biddingMoney;
    }

    public String getBiddingPlatform() {
        return biddingPlatform;
    }

    public void setBiddingPlatform(String biddingPlatform) {
        this.biddingPlatform = biddingPlatform;
    }

    public String getJkylilv() {
        return jkylilv;
    }

    public void setJkylilv(String jkylilv) {
        this.jkylilv = jkylilv;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getFullScaleTime() {
        return fullScaleTime;
    }

    public void setFullScaleTime(Date fullScaleTime) {
        this.fullScaleTime = fullScaleTime;
    }

    public Integer getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(Integer vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getIsHouse() {
        return isHouse;
    }

    public void setIsHouse(String isHouse) {
        this.isHouse = isHouse;
    }

    public String getBuyType() {
        return buyType;
    }

    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }

    public BigDecimal getMonthlyInstallmentPayment() {
        return monthlyInstallmentPayment;
    }

    public void setMonthlyInstallmentPayment(BigDecimal monthlyInstallmentPayment) {
        this.monthlyInstallmentPayment = monthlyInstallmentPayment;
    }

    public String getHouseProperty() {
        return houseProperty;
    }

    public void setHouseProperty(String houseProperty) {
        this.houseProperty = houseProperty;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Date getFistRegisterDate() {
        return fistRegisterDate;
    }

    public void setFistRegisterDate(Date fistRegisterDate) {
        this.fistRegisterDate = fistRegisterDate;
    }

    public Date getLastTransferDate() {
        return lastTransferDate;
    }

    public void setLastTransferDate(Date lastTransferDate) {
        this.lastTransferDate = lastTransferDate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleColour() {
        return vehicleColour;
    }

    public void setVehicleColour(String vehicleColour) {
        this.vehicleColour = vehicleColour;
    }

    public Integer getVehicleMileage() {
        return vehicleMileage;
    }

    public void setVehicleMileage(Integer vehicleMileage) {
        this.vehicleMileage = vehicleMileage;
    }

    public String getVehicleAttribution() {
        return vehicleAttribution;
    }

    public void setVehicleAttribution(String vehicleAttribution) {
        this.vehicleAttribution = vehicleAttribution;
    }

    public Byte getVehiclePledgeCount() {
        return vehiclePledgeCount;
    }

    public void setVehiclePledgeCount(Byte vehiclePledgeCount) {
        this.vehiclePledgeCount = vehiclePledgeCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatisticsDate() {
        return statisticsDate;
    }

    public void setStatisticsDate(String statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

    public String getAssetSide() {
        return assetSide;
    }

    public void setAssetSide(String assetSide) {
        this.assetSide = assetSide;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public Integer getCustomerManagerId() {
        return customerManagerId;
    }

    public void setCustomerManagerId(Integer customerManagerId) {
        this.customerManagerId = customerManagerId;
    }

    public String getCustomerManagerName() {
        return customerManagerName;
    }

    public void setCustomerManagerName(String customerManagerName) {
        this.customerManagerName = customerManagerName;
    }

    public Integer getTeamManagerId() {
        return teamManagerId;
    }

    public void setTeamManagerId(Integer teamManagerId) {
        this.teamManagerId = teamManagerId;
    }

    public String getTeamManagerName() {
        return teamManagerName;
    }

    public void setTeamManagerName(String teamManagerName) {
        this.teamManagerName = teamManagerName;
    }

    public Integer getAppraiserPersonId() {
        return appraiserPersonId;
    }

    public void setAppraiserPersonId(Integer appraiserPersonId) {
        this.appraiserPersonId = appraiserPersonId;
    }

    public String getAppraiserPersonName() {
        return appraiserPersonName;
    }

    public void setAppraiserPersonName(String appraiserPersonName) {
        this.appraiserPersonName = appraiserPersonName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}