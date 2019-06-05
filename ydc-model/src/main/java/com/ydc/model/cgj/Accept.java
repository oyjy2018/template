package com.ydc.model.cgj;

import java.util.Date;

/**
 * 借款受理详情表
 * 
 * @author wangcan
 * 
 * @date 2018-12-29
 */
public class Accept {
    /**
     * id,唯一标识,自增，主键
     */
    private Integer id;

    /**
     * 借款申请id
     */
    private Integer loanApplyId;

    /**
     * 受理人用户id
     */
    private Integer acceptUserId;

    /**
     * 受理人
     */
    private String  acceptUsername;


    /**
     * 受理状态
     */
    private Integer acceptStatus;

    /**
     * 受理时间
     */
    private Date acceptTime;

    /**
     * 受理详情
     */
    private String acceptRemark;

    /**
     * 有效状态（0：无效；1：有效）
     */
    private Integer deleteStatus;

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

    public Accept(Integer id, Integer loanApplyId, Integer acceptUserId,String  acceptUsername, Integer acceptStatus, Date acceptTime, String acceptRemark, Integer deleteStatus, Date createTime, Integer createBy, Date updateTime, Integer updateBy) {
        this.id = id;
        this.loanApplyId = loanApplyId;
        this.acceptUserId = acceptUserId;
        this.acceptUsername = acceptUsername;
        this.acceptStatus = acceptStatus;
        this.acceptTime = acceptTime;
        this.acceptRemark = acceptRemark;
        this.deleteStatus = deleteStatus;
        this.createTime = createTime;
        this.createBy = createBy;
        this.updateTime = updateTime;
        this.updateBy = updateBy;
    }

    public Accept() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoanApplyId() {
        return loanApplyId;
    }

    public void setLoanApplyId(Integer loanApplyId) {
        this.loanApplyId = loanApplyId;
    }

    public Integer getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(Integer acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    public String getAcceptUsername() {
        return acceptUsername;
    }

    public void setAcceptUsername(String acceptUsername) {
        this.acceptUsername = acceptUsername;
    }

    public Integer getAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(Integer acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getAcceptRemark() {
        return acceptRemark;
    }

    public void setAcceptRemark(String acceptRemark) {
        this.acceptRemark = acceptRemark == null ? null : acceptRemark.trim();
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