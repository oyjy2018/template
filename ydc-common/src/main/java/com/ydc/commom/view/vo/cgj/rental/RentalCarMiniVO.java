package com.ydc.commom.view.vo.cgj.rental;

/**
 * 车辆列表信息
 */
public class RentalCarMiniVO {
    /**
     * 车辆id
     */
    private Integer carId;
    /**
     * 车牌号
     */
    private String carPlate;
    /**
     * 车辆状态 0待审核 1待发布 2己发布 3审核失败 4己出租
     */
    private Integer status;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "carId=" + carId +
                ", carPlate='" + carPlate + '\'' +
                ", status=" + status +
                '}';
    }
}