package com.ydc.model.cgj;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BRollTemplate implements Serializable {
    private static final long serialVersionUID = 2139982470861356893L;
    /**
     * t_b_roll_template.id
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    private Integer id;

    /**
     * t_b_roll_template.roll_name (劵模板名)
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    private String rollName;

    /**
     * t_b_roll_template.roll_money (劵金额)
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    private BigDecimal rollMoney;

    /**
     * t_b_roll_template.roll_content (劵服务内容)
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    private String rollContent;

    /**
     * t_b_roll_template.roll_code (编号)
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    private String rollCode;

    /**
     * t_b_roll_template.channel (渠道)
     * @ibatorgenerated 2018-11-01 10:35:49
     */
    private String channel;

    /**
     * t_b_roll_template.roll_type (劵类型（1：洗车劵；2：保养劵；3：车险劵）)
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    private Integer rollType;

    /**
     * t_b_roll_template.batch_number (批次号)
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    private String batchNumber;

    /**
     * t_b_roll_template.status (是否派完（1：已激活；2：未激活）)
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    private Integer status;

    /**
     * t_b_roll_template.create_time (创建时间)
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    private Date createTime;

    /**
     * t_b_roll_template.create_by (创建人)
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    private Integer createBy;

    /**
     * t_b_roll_template.update_time (修改时间)
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    private Date updateTime;

    /**
     * t_b_roll_template.update_by (修改人)
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRollName() {
        return rollName;
    }

    public void setRollName(String rollName) {
        this.rollName = rollName;
    }

    public BigDecimal getRollMoney() {
        return rollMoney;
    }

    public void setRollMoney(BigDecimal rollMoney) {
        this.rollMoney = rollMoney;
    }

    public String getRollContent() {
        return rollContent;
    }

    public void setRollContent(String rollContent) {
        this.rollContent = rollContent;
    }

    public String getRollCode() {
        return rollCode;
    }

    public void setRollCode(String rollCode) {
        this.rollCode = rollCode;
    }

    public Integer getRollType() {
        return rollType;
    }

    public void setRollType(Integer rollType) {
        this.rollType = rollType;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}