package com.ydc.commom.view.dto.cgj.service;


import com.ydc.model.cgj.BRollDetail;

import java.io.Serializable;
import java.util.List;

/**
 * 券
 *
 * @author
 * @create 2018-11-01 11:08
 **/
public class TicketDTO implements Serializable {
    private static final long serialVersionUID = 7085645826498974786L;

    private Integer ticketId;//券id
    private String ticketCode;//券模板编号
    private String ticketName;//券名
    private Integer ticketNumber;//券数量
    private Integer rollType;//劵类型（1：洗车劵；2：保养劵；3：车险劵）
    private Integer updateBy;//操作人

    private List<BRollDetail> entityList;//券模板

    public TicketDTO() {
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

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public List<BRollDetail> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<BRollDetail> entityList) {
        this.entityList = entityList;
    }

    public Integer getRollType() {
        return rollType;
    }

    public void setRollType(Integer rollType) {
        this.rollType = rollType;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
}
