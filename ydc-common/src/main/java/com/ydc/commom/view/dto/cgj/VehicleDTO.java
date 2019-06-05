package com.ydc.commom.view.dto.cgj;

import com.ydc.model.cgj.Pagination;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @author
 * @create 2018-10-30 11:56
 **/
public class VehicleDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = 2564016578201723827L;

    private Integer memberId;
    private Integer vehicleId;

    public VehicleDTO() {
    }

    public VehicleDTO(Integer memberId,Integer vehicleId) {
        this.memberId = memberId;
        this.vehicleId = vehicleId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }
}
