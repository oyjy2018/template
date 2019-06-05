package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * 机构单查询实体类
 */
public class RentalOrderMaintenanceDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 机务单id
     */
    private Integer maintenanceOrderId;

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 车辆所属门店
     */
    private Integer carStoreId;

    /**
     * 机务单状态
     */
    private Integer maintenanceOrderStatus;

    /**
     * 驾驶人
     */
    private Integer carUserId;

    public Integer getMaintenanceOrderId() {
        return maintenanceOrderId;
    }

    public void setMaintenanceOrderId(Integer maintenanceOrderId) {
        this.maintenanceOrderId = maintenanceOrderId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getCarStoreId() {
        return carStoreId;
    }

    public void setCarStoreId(Integer carStoreId) {
        this.carStoreId = carStoreId;
    }

    public Integer getMaintenanceOrderStatus() {
        return maintenanceOrderStatus;
    }

    public void setMaintenanceOrderStatus(Integer maintenanceOrderStatus) {
        this.maintenanceOrderStatus = maintenanceOrderStatus;
    }

    public Integer getCarUserId() {
        return carUserId;
    }

    public void setCarUserId(Integer carUserId) {
        this.carUserId = carUserId;
    }
}
