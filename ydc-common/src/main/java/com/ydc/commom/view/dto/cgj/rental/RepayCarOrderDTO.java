package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.IsEmpty;
import com.ydc.model.annotation.IsNum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 还车订单信息
 *
 * @author
 * @create 2018-11-22 12:30
 **/
public class RepayCarOrderDTO implements Serializable {
    private static final long serialVersionUID = -2592467932903785130L;
    /**
     * 租车订单id
     */
    @IsEmpty(message = "租车订单id不能为空")
    private Integer orderId;

    /**
     * t_rental_order.repay_car_time (还车时间)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "还车时间不能为空")
    private Date repayCarTime;

    /**
     * t_rental_order.repay_car_mileage (还车已行驶里程)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "还车已行驶里程不能为空")
    private String repayCarMileage;

    /**
     * t_rental_order.repay_car_oil_amount (还车油量)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "还车油量不能为空")
    @IsNum(message = "还车油量必须是整数")
    private String repayCarOilAmount;

    /**
     * t_rental_order.repay_car_warehouse_store_id (还车所在门店id)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "还车所在门店id不能为空")
    private Integer repayCarWarehouseStoreId;

    /**
     * t_rental_order.repay_car_warehouse_store_name (还车所在门店)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "还车所在门店不能为空")
    private String repayCarWarehouseStoreName;

    /**
     * t_rental_order.repay_car_coachman_id (还车操作人id)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "还车操作人id不能为空")
    private Integer repayCarCoachmanId;

    /**
     * t_rental_order.repay_car_coachman_name (还车操作人姓名)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "还车操作人姓名不能为空")
    private String repayCarCoachmanName;

    /**
     * 还车备注
     */
    private String repayCarRemark;

    /**
     * t_rental_order.status (状态（1：未确认-待风控；2：已确认-待风控；3：风控通过-待租车预授权；4：已租车预授权-待出车；5：出车成功-待还车；6：已还车-待结算；7：已还车-已结算；98：已取消；99：已拒绝）)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private Integer status;


    /**
     * t_rental_order.update_by (修改人)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private Integer updateBy;

    /**
     * 流程状态一
     */
    private Integer flowOneStatus;


    /**
     * t_rental_order.flow_two_status (流程状态二)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer flowTwoStatus;

    /**
     * t_rental_order.flow_three_status (流程状态三)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer flowThreeStatus;

    /**
     * 租车订单图片集合
     */
    private List<RepayCarOrderImgDTO> repayCarOrderImgDTOS;

    /**
     * 车辆id
     */
    private Integer carId;



    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getRepayCarTime() {
        return repayCarTime;
    }

    public void setRepayCarTime(Date repayCarTime) {
        this.repayCarTime = repayCarTime;
    }

    public String getRepayCarMileage() {
        return repayCarMileage;
    }

    public void setRepayCarMileage(String repayCarMileage) {
        this.repayCarMileage = repayCarMileage;
    }

    public String getRepayCarOilAmount() {
        return repayCarOilAmount;
    }

    public void setRepayCarOilAmount(String repayCarOilAmount) {
        this.repayCarOilAmount = repayCarOilAmount;
    }

    public Integer getRepayCarWarehouseStoreId() {
        return repayCarWarehouseStoreId;
    }

    public void setRepayCarWarehouseStoreId(Integer repayCarWarehouseStoreId) {
        this.repayCarWarehouseStoreId = repayCarWarehouseStoreId;
    }

    public String getRepayCarWarehouseStoreName() {
        return repayCarWarehouseStoreName;
    }

    public void setRepayCarWarehouseStoreName(String repayCarWarehouseStoreName) {
        this.repayCarWarehouseStoreName = repayCarWarehouseStoreName;
    }

    public Integer getRepayCarCoachmanId() {
        return repayCarCoachmanId;
    }

    public void setRepayCarCoachmanId(Integer repayCarCoachmanId) {
        this.repayCarCoachmanId = repayCarCoachmanId;
    }

    public String getRepayCarCoachmanName() {
        return repayCarCoachmanName;
    }

    public void setRepayCarCoachmanName(String repayCarCoachmanName) {
        this.repayCarCoachmanName = repayCarCoachmanName;
    }

    public String getRepayCarRemark() {
        return repayCarRemark;
    }

    public void setRepayCarRemark(String repayCarRemark) {
        this.repayCarRemark = repayCarRemark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getFlowOneStatus() {
        return flowOneStatus;
    }

    public void setFlowOneStatus(Integer flowOneStatus) {
        this.flowOneStatus = flowOneStatus;
    }

    public Integer getFlowTwoStatus() {
        return flowTwoStatus;
    }

    public void setFlowTwoStatus(Integer flowTwoStatus) {
        this.flowTwoStatus = flowTwoStatus;
    }

    public Integer getFlowThreeStatus() {
        return flowThreeStatus;
    }

    public void setFlowThreeStatus(Integer flowThreeStatus) {
        this.flowThreeStatus = flowThreeStatus;
    }

    public List<RepayCarOrderImgDTO> getRepayCarOrderImgDTOS() {
        return repayCarOrderImgDTOS;
    }

    public void setRepayCarOrderImgDTOS(List<RepayCarOrderImgDTO> repayCarOrderImgDTOS) {
        this.repayCarOrderImgDTOS = repayCarOrderImgDTOS;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
}
