package com.ydc.commom.view.vo.cgj;

import com.ydc.model.cgj.LoanApply;

import java.util.Date;

public class LoanApplyVO extends LoanApply{
    private String username;
    private String userTel;
    private String accptUsername;
    private Date accptTime;
    private String startTime;
    private String endTime;
    private String newAccept;
    private String carCard;
    private String carModel;

    public String getCarCard() {
        return carCard;
    }

    public void setCarCard(String carCard) {
        this.carCard = carCard;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getNewAccept() {
        return newAccept;
    }

    public void setNewAccept(String newAccept) {
        this.newAccept = newAccept;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getAccptUsername() {
        return accptUsername;
    }

    public void setAccptUsername(String accptUsername) {
        this.accptUsername = accptUsername;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public Date getAccptTime() {
        return accptTime;
    }

    public void setAccptTime(Date accptTime) {
        this.accptTime = accptTime;
    }
}
