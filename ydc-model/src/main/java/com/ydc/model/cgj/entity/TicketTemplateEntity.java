package com.ydc.model.cgj.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 券模板
 *
 * @author
 * @create 2018-11-01 12:09
 **/
public class TicketTemplateEntity implements Serializable {

    private static final long serialVersionUID = -7514723775446326601L;
    private String ticketCode;//券模板编号
    private String ticketName;//券名
    private BigDecimal rollMoney;//劵金额
    private String rollContent;//劵服务内容
    private Integer rollType;//劵类型（1：洗车劵；2：保养劵；3：车险劵）
    private Integer status;//是否激活（1：已激活；2：未激活）
    private String channel;//渠道
    private Integer updateBy;//操作人

    public TicketTemplateEntity() {
    }

    public TicketTemplateEntity(String ticketCode, String ticketName, BigDecimal rollMoney, String rollContent, Integer rollType, Integer status, String channel, Integer updateBy) {
        this.ticketCode = ticketCode;
        this.ticketName = ticketName;
        this.rollMoney = rollMoney;
        this.rollContent = rollContent;
        this.rollType = rollType;
        this.status = status;
        this.channel = channel;
        this.updateBy = updateBy;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
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

    public Integer getRollType() {
        return rollType;
    }

    public void setRollType(Integer rollType) {
        this.rollType = rollType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}
