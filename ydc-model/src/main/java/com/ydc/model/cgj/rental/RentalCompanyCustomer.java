package com.ydc.model.cgj.rental;

import java.io.Serializable;
import java.util.Date;

/**
 * t_rental_company_customer
 * @author 
 */
public class RentalCompanyCustomer implements Serializable {
    private Integer id;

    /**
     * 注册手机
     */
    private String registeredPhone;

    /**
     * 注册公司名
     */
    private String registeredCompanyName;

    /**
     * 个人头像
     */
    private String personalPortrait;

    /**
     * 认证日期
     */
    private Date authenticationDate;

    /**
     * 营业执照
     */
    private String license;

    /**
     * 公司全称
     */
    private String companyName;

    /**
     * 注册公司所在地址
     */
    private String registeredCompanyAddress;

    /**
     * 公司简称
     */
    private String companyAbbreviation;

    /**
     * 统一信用代码
     */
    private String creditCode;

    /**
     * 法人姓名
     */
    private String legalPerson;

    /**
     * 身份证正面照片
     */
    private String idCardFrontImage;

    /**
     * 身份证背面照片
     */
    private String idCardBackImage;

    /**
     * 法人身份证号
     */
    private String idCardNo;

    /**
     * 联系人
     */
    private String name;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 公司车台数
     */
    private Integer carNum;

    /**
     * 公司所在省
     */
    private String companyProvince;

    /**
     * 公司所在市
     */
    private String companyCity;

    /**
     * 公司所在区
     */
    private String companyArea;

    /**
     * 公司详细地址
     */
    private String companyAddress;

    /**
     * 企业认证状态（0：未认证；1：待审核；2：已认证；3：审核退回）
     */
    private Byte authenticationStatus;

    /**
     * 状态（0：禁用；1：启用）
     */
    private Byte deleteStatus;

    /**
     * 审核备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private Integer updateBy;

    /**
     * 历史访问城市
     */
    private String historyCities;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegisteredPhone() {
        return registeredPhone;
    }

    public void setRegisteredPhone(String registeredPhone) {
        this.registeredPhone = registeredPhone;
    }

    public String getRegisteredCompanyName() {
        return registeredCompanyName;
    }

    public void setRegisteredCompanyName(String registeredCompanyName) {
        this.registeredCompanyName = registeredCompanyName;
    }

    public String getPersonalPortrait() {
        return personalPortrait;
    }

    public void setPersonalPortrait(String personalPortrait) {
        this.personalPortrait = personalPortrait;
    }

    public Date getAuthenticationDate() {
        return authenticationDate;
    }

    public void setAuthenticationDate(Date authenticationDate) {
        this.authenticationDate = authenticationDate;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegisteredCompanyAddress() {
        return registeredCompanyAddress;
    }

    public void setRegisteredCompanyAddress(String registeredCompanyAddress) {
        this.registeredCompanyAddress = registeredCompanyAddress;
    }

    public String getCompanyAbbreviation() {
        return companyAbbreviation;
    }

    public void setCompanyAbbreviation(String companyAbbreviation) {
        this.companyAbbreviation = companyAbbreviation;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getIdCardFrontImage() {
        return idCardFrontImage;
    }

    public void setIdCardFrontImage(String idCardFrontImage) {
        this.idCardFrontImage = idCardFrontImage;
    }

    public String getIdCardBackImage() {
        return idCardBackImage;
    }

    public void setIdCardBackImage(String idCardBackImage) {
        this.idCardBackImage = idCardBackImage;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }

    public String getCompanyProvince() {
        return companyProvince;
    }

    public void setCompanyProvince(String companyProvince) {
        this.companyProvince = companyProvince;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(String companyArea) {
        this.companyArea = companyArea;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Byte getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(Byte authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Byte deleteStatus) {
        this.deleteStatus = deleteStatus;
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

    public String getHistoryCities() {
        return historyCities;
    }

    public void setHistoryCities(String historyCities) {
        this.historyCities = historyCities;
    }
}