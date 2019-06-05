package com.ydc.model.cgj;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.model.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BRollDetail implements Serializable {
    private static final long serialVersionUID = -7227089111763949109L;
    /**
     * t_b_roll_detail.id (主键)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer id;

    /**
     * t_b_roll_detail.name (名称)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private String name;

    /**
     * t_b_roll_detail.amount (金额)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private BigDecimal amount;

    /**
     * t_b_roll_detail.type (类型（1：洗车券；2：保养券；3：保险券）)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer type;

    /**
     * t_b_roll_detail.code (服务码)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private String code;

    /**
     * t_b_roll_detail.batch_code (批次号)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private String batchCode;

    /**
     * t_b_roll_detail.content (服务内容)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private String content;

    /**
     * 券属性
     */
    private Integer attribute;

    /**
     * t_b_roll_detail.roll_status (优惠券状态（0：未激活；1：已激活；2：已预约；3：已使用；4：已过期）)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer rollStatus;

    /**
     * t_b_roll_detail.valid_time (生效时间)
     * @ibatorgenerated 2018-10-30 15:20:17
     */

    private Date validTime;

    /**
     * t_b_roll_detail.invalid_time (失效时间)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Date invalidTime;

    /**
     * t_b_roll_detail.template_id (模板id)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private String templateId;

    /**
     * c端是否已发放（0：未发放；1：已发放）
     */
    private Integer hasSend;

    /**
     * t_b_roll_detail.status (状态（0：无效；1：有效）)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer status;

    /**
     * t_b_roll_detail.status_ch (券状态中文)
     * @ibatorgenerated 2018-11-08 14:42:53
     */
    private String statusCh;

    /**
     * t_b_roll_detail.create_time (创建时间)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Date createTime;

    /**
     * t_b_roll_detail.create_by (创建人)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer createBy;

    /**
     * t_b_roll_detail.update_time (更新时间)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Date updateTime;

    /**
     * t_b_roll_detail.update_by (更新人)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRollStatus() {
        return rollStatus;
    }

    public void setRollStatus(Integer rollStatus) {
        this.rollStatus = rollStatus;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
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

    public Integer getHasSend() {
        return hasSend;
    }

    public void setHasSend(Integer hasSend) {
        this.hasSend = hasSend;
    }

    public String getStatusCh() {
        return statusCh;
    }

    public void setStatusCh(String statusCh) {
        this.statusCh = statusCh;
    }

    public Integer getAttribute() {
        return attribute;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }
}