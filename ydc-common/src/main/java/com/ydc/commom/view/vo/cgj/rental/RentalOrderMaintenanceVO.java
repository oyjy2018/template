package com.ydc.commom.view.vo.cgj.rental;

import com.ydc.model.cgj.rental.RentalOrderMaintenance;

import java.io.Serializable;
import java.util.Map;

/**
 * 机务单展示实体类
 */
public class RentalOrderMaintenanceVO extends RentalOrderMaintenance implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 耗时
     */
    private String consumingTime;

    /**
     * 具体的机务
     */
    private Map<String, Object> maintenanceMap;

    /**
     * 出车时间
     */
    private String comeCarTimeStr;

    /**
     * 还车时间
     */
    private String repayCarTimeStr;

    /**
     * 车辆品牌
     */
    private String brand;

    /**
     * 车系
     */
    private String series;

    /**
     * 车型
     */
    private String model;

    public Map<String, Object> getMaintenanceMap() {
        return maintenanceMap;
    }

    public void setMaintenanceMap(Map<String, Object> maintenanceMap) {
        this.maintenanceMap = maintenanceMap;
    }

    public String getConsumingTime() {
        return consumingTime;
    }

    public void setConsumingTime(String consumingTime) {
        this.consumingTime = consumingTime;
    }

    public String getComeCarTimeStr() {
        return comeCarTimeStr;
    }

    public void setComeCarTimeStr(String comeCarTimeStr) {
        this.comeCarTimeStr = comeCarTimeStr;
    }

    public String getRepayCarTimeStr() {
        return repayCarTimeStr;
    }

    public void setRepayCarTimeStr(String repayCarTimeStr) {
        this.repayCarTimeStr = repayCarTimeStr;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
