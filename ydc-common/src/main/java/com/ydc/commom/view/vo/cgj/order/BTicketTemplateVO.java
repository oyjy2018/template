package com.ydc.commom.view.vo.cgj.order;

import com.ydc.commom.enums.cgj.BTicketTemplateEnum;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * B端券模板
 *
 * @author
 * @create 2018-12-07 16:21
 **/
public class BTicketTemplateVO implements Serializable {
    private static final long serialVersionUID = -4160170179482655979L;

    private Integer rollTemplateId;
    private String rollName;
    private BigDecimal rollMoney;
    private String rollContent;
    private String rollCodeName;
    private String rollCode;
    private String statusName;
    private Integer status;
    private String rollTypeName;
    private Integer rollType;

    public Integer getRollTemplateId() {
        return rollTemplateId;
    }

    public void setRollTemplateId(Integer rollTemplateId) {
        this.rollTemplateId = rollTemplateId;
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

    public String getRollCodeName() {
        return rollCodeName;
    }

    public void setRollCodeName(String rollCodeName) {
        this.rollCodeName = rollCodeName;
    }

    public String getRollCode() {
        return rollCode;
    }

    public void setRollCode(String rollCode) {
        this.rollCode = rollCode;
    }

    public String getStatusName() {
        return BTicketTemplateEnum.TemplateStatusEnum.getCodeName(this.status);
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRollTypeName() {
        return BTicketTemplateEnum.TemplateTypeEnum.getCodeName(this.rollType);
    }

    public void setRollTypeName(String rollTypeName) {
        this.rollTypeName = rollTypeName;
    }

    public Integer getRollType() {
        return rollType;
    }

    public void setRollType(Integer rollType) {
        this.rollType = rollType;
    }
}
