package com.ydc.model.cgj;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 意见反馈表
 */
public class OpinionFeedback implements Serializable {
    private static final long serialVersionUID = 3771587402097349813L;
    /**
     * t_opinion_feedback.id (意见id)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Integer id;

    /**
     * t_opinion_feedback.user_id (提出人id)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Integer userId;

    /**
     * t_opinion_feedback.user_name (提出人姓名)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String userName;

    /**
     * t_opinion_feedback.opinion_type (意见分类)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String opinionType;

    /**
     * t_opinion_feedback.opinion_content (意见反馈内容)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String opinionContent;

    /**
     * t_opinion_feedback.contact_way (联系方式)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String contactWay;

    /**
     * t_opinion_feedback.source (来源（1：H5，2：APP）)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String source;

    /**
     * t_opinion_feedback.process_status (状态)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String processStatus;

    /**
     * t_opinion_feedback.process_username (处理人)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String processUsername;

    /**
     * t_opinion_feedback.process_time (处理时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Date processTime;

    /**
     * t_opinion_feedback.status (状态（1：正常；0：禁用）)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Byte status;

    /**
     * t_opinion_feedback.create_time (创建时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Date createTime;

    /**
     * t_opinion_feedback.update_time (更新时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOpinionType() {
        return opinionType;
    }

    public void setOpinionType(String opinionType) {
        this.opinionType = opinionType;
    }

    public String getOpinionContent() {
        return opinionContent;
    }

    public void setOpinionContent(String opinionContent) {
        this.opinionContent = opinionContent;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getProcessUsername() {
        return processUsername;
    }

    public void setProcessUsername(String processUsername) {
        this.processUsername = processUsername;
    }

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}