package com.ydc.model.cgj;

import java.util.Date;

/**
 * 借款申请
 * 
 * @author wangcan
 * 
 * @date 2018-12-29
 */
public class LoanApply {
    /**
     * id,唯一标识,自增，主键
     */
    private Integer id;

    /**
     * 登录用户id
     */
    private Integer memberId;

    /**
     * 登录用户
     */
    private String memberUsername;

    /**
     * 定位城市
     */
    private String city;

    /**
     * 申请姓名
     */
    private String applyName;

    /**
     * 申请手机
     */
    private String applyPhone;

    /**
     * 申请产品类型(1、车主贷；2、车抵贷；3、我要出租；4、我要租车；5、购车分期；6、我要买车；7、我要卖车)
     */
    private Integer applyProductType;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 有效状态（0：无效；1：有效）
     */
    private Integer deleteStatus;

    /**
     * 受理状态（1-待受理）
     */
    private Integer acceptStatus;


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

    public LoanApply(Integer id, Integer memberId,String memberUsername, String city, String applyName, String applyPhone, Integer applyProductType, Date applyTime, Integer deleteStatus, Integer acceptStatus, Date createTime, Integer createBy, Date updateTime, Integer updateBy) {
        this.id = id;
        this.memberId = memberId;
        this.memberUsername = memberUsername;
        this.city = city;
        this.applyName = applyName;
        this.applyPhone = applyPhone;
        this.applyProductType = applyProductType;
        this.applyTime = applyTime;
        this.deleteStatus = deleteStatus;
        this.acceptStatus = acceptStatus;
        this.createTime = createTime;
        this.createBy = createBy;
        this.updateTime = updateTime;
        this.updateBy = updateBy;
    }

    public LoanApply() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public String getUsername() {
        return memberUsername;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName == null ? null : applyName.trim();
    }

    public String getApplyPhone() {
        return applyPhone;
    }

    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone == null ? null : applyPhone.trim();
    }

    public Integer getApplyProductType() {
        return applyProductType;
    }

    public void setApplyProductType(Integer applyProductType) {
        this.applyProductType = applyProductType == null ? null : applyProductType;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(Integer acceptStatus) {
        this.acceptStatus = acceptStatus;
    }
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
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
}