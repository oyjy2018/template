package com.ydc.commom.view.vo.cgj.rental;

import com.ydc.model.cgj.rental.RentalAccidentMaintenance;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 事故维修VO
 */
public class RentalAccidentMaintenanceVO extends RentalAccidentMaintenance implements Serializable {
    private static final long serialVersionUID = 1L;

    private String maintenanceTimeStr;

    private List<Map<String, Object>> rentalAccidentList;

    public List<Map<String, Object>> getRentalAccidentList() {
        return rentalAccidentList;
    }

    public void setRentalAccidentList(List<Map<String, Object>> rentalAccidentList) {
        this.rentalAccidentList = rentalAccidentList;
    }

    public String getMaintenanceTimeStr() {
        return maintenanceTimeStr;
    }

    public void setMaintenanceTimeStr(String maintenanceTimeStr) {
        this.maintenanceTimeStr = maintenanceTimeStr;
    }
}
