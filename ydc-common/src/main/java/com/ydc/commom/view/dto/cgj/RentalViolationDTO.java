package com.ydc.commom.view.dto.cgj;

import com.ydc.model.cgj.Pagination;

public class RentalViolationDTO extends Pagination {

    /**
     * 违章ID
     */
    private Integer id;

    /**
     *用车单id
     */
    private Integer orderId;

    /**
     * 车牌号
     */
    private String carPlate;

    /**
     * 所属门店
     */
    private Integer store;

    /**
     * 违章时间
     */
    private String violationStartTime;

    /**
     * 违章时间
     */
    private String violationEndTime;


    public RentalViolationDTO changeEndTime(String time){
        if (this.violationEndTime != null && !("").equals(this.violationEndTime)){
            this.violationEndTime = this.violationEndTime + time;
        }
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public String getViolationStartTime() {
        return violationStartTime;
    }

    public void setViolationStartTime(String violationStartTime) {
        this.violationStartTime = violationStartTime;
    }

    public String getViolationEndTime() {
        return violationEndTime;
    }

    public void setViolationEndTime(String violationEndTime) {
        this.violationEndTime = violationEndTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
