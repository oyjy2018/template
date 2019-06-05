package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.Attribute;

import java.util.Date;

/**
 * 更新违章处理状态dto
 */
public class RentalViolationUpdateDealStatusDTO {
    @Attribute(name = "违章id",required = true,isNum = true)
    private Integer id;

    //处理状态
    private Integer dealStatus;

    @Attribute(name = "处理人",required = true,isNum = true)
    private Integer dealBy;

    @Attribute(name = "缴费凭证文件地址",required = true,emptyStringVerify = true)
    private String dealChargeFileUrl;

    @Attribute(name = "缴费凭证文件名",required = true,emptyStringVerify = true)
    private String dealChargeFileName;

    //处理时间
    private Date dealCommitTime;

    //操作人
    private Integer dealCommitBy;

    private Integer updateBy;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    public Integer getDealBy() {
        return dealBy;
    }

    public void setDealBy(Integer dealBy) {
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

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDealCommitTime() {
        return dealCommitTime;
    }

    public void setDealCommitTime(Date dealCommitTime) {
        this.dealCommitTime = dealCommitTime;
    }

    public Integer getDealCommitBy() {
        return dealCommitBy;
    }

    public void setDealCommitBy(Integer dealCommitBy) {
        this.dealCommitBy = dealCommitBy;
    }
}
