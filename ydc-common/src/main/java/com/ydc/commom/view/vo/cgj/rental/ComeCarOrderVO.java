package com.ydc.commom.view.vo.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.ComeCarOrderImgDTO;

import java.io.Serializable;
import java.util.List;

/**
 * 出车门店信息
 *
 * @author
 * @create 2018-11-23 15:46
 **/
public class ComeCarOrderVO implements Serializable {
    private static final long serialVersionUID = -2686502833315858962L;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * t_rental_order.car_id (车辆id)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private Integer carId;
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * t_rental_order.come_car_time (出车时间)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private String comeCarTime;

    /**
     * t_rental_order.come_warehouse_mileage (出库已行驶里程)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private String comeWarehouseMileage;

    /**
     * t_rental_order.come_warehouse_oil_amount (出库油量)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private Integer comeWarehouseOilAmount;

    /**
     * t_rental_order.come_warehouse_store_id (出库所在门店id)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private Integer comeWarehouseStoreId;

    /**
     * t_rental_order.come_warehouse_store_name (出库所在门店)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private String comeWarehouseStoreName;

    /**
     * t_rental_order.coachman_id (驾车人id)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private Integer coachmanId;

    /**
     * t_rental_order.coachman_name (驾车人姓名)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private String coachmanName;

    /**
     * t_rental_order.come_car_remark (出车备注)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private String comeCarRemark;

    /**
     * 出车图片集合
     */
    List<ComeCarOrderImgDTO> comeCarOrderImgDTOS;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getComeCarTime() {
        return comeCarTime;
    }

    public void setComeCarTime(String comeCarTime) {
        this.comeCarTime = comeCarTime;
    }

    public String getComeWarehouseMileage() {
        return comeWarehouseMileage;
    }

    public void setComeWarehouseMileage(String comeWarehouseMileage) {
        this.comeWarehouseMileage = comeWarehouseMileage;
    }

    public Integer getComeWarehouseOilAmount() {
        return comeWarehouseOilAmount;
    }

    public void setComeWarehouseOilAmount(Integer comeWarehouseOilAmount) {
        this.comeWarehouseOilAmount = comeWarehouseOilAmount;
    }

    public Integer getComeWarehouseStoreId() {
        return comeWarehouseStoreId;
    }

    public void setComeWarehouseStoreId(Integer comeWarehouseStoreId) {
        this.comeWarehouseStoreId = comeWarehouseStoreId;
    }

    public String getComeWarehouseStoreName() {
        return comeWarehouseStoreName;
    }

    public void setComeWarehouseStoreName(String comeWarehouseStoreName) {
        this.comeWarehouseStoreName = comeWarehouseStoreName;
    }

    public Integer getCoachmanId() {
        return coachmanId;
    }

    public void setCoachmanId(Integer coachmanId) {
        this.coachmanId = coachmanId;
    }

    public String getCoachmanName() {
        return coachmanName;
    }

    public void setCoachmanName(String coachmanName) {
        this.coachmanName = coachmanName;
    }

    public String getComeCarRemark() {
        return comeCarRemark;
    }

    public void setComeCarRemark(String comeCarRemark) {
        this.comeCarRemark = comeCarRemark;
    }

    public List<ComeCarOrderImgDTO> getComeCarOrderImgDTOS() {
        return comeCarOrderImgDTOS;
    }

    public void setComeCarOrderImgDTOS(List<ComeCarOrderImgDTO> comeCarOrderImgDTOS) {
        this.comeCarOrderImgDTOS = comeCarOrderImgDTOS;
    }
}
