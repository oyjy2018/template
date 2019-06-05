package com.ydc.commom.view.dto.cgj.rental;

import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.util.AnnotationDealUtil;
import com.ydc.model.annotation.IsEmpty;
import com.ydc.model.annotation.IsNum;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 出车订单信息
 *
 * @author
 * @create 2018-11-22 12:27
 **/
public class ComeCarOrderDTO implements Serializable {
    private static final long serialVersionUID = 9133587577297330864L;

    /**
     * 租车订单id
     */
    @IsEmpty(message = "租车订单id不能为空")
    private Integer orderId;
    /**
     * t_rental_order.car_id (车辆id)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "车辆id不能为空")
    private Integer carId;

    /**
     * t_rental_order.car_number (车牌号)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "车牌号不能为空")
    private String carNumber;

    /**
     * t_rental_order.come_car_time (出车时间)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "出车时间不能为空")
    private Date comeCarTime;

    /**
     * t_rental_order.come_warehouse_mileage (出库已行驶里程)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "出库已行驶里程不能为空")
    private String comeWarehouseMileage;

    /**
     * t_rental_order.come_warehouse_oil_amount (出库油量)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "出库油量不能为空")
    @IsNum(message = "还车油量必须是整数")
    private String comeWarehouseOilAmount;

    /**
     * t_rental_order.come_warehouse_store_id (出库所在门店id)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "出库所在门店id不能为空")
    private Integer comeWarehouseStoreId;

    /**
     * t_rental_order.come_warehouse_store_name (出库所在门店)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "出库所在门店不能为空")
    private String comeWarehouseStoreName;

    /**
     * t_rental_order.coachman_id (驾车人id)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "驾驶人id不能为空")
    private Integer coachmanId;

    /**
     * t_rental_order.coachman_name (驾车人姓名)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "驾驶人姓名不能为空")
    private String coachmanName;

    /**
     * t_rental_order.come_car_remark (出车备注)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private String comeCarRemark;

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
     * 租车订单图片集合
     */
    private List<ComeCarOrderImgDTO> comeCarOrderImgDTOS;


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
     * t_rental_order.car_level (车等级)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String carLevel;

    /**
     * t_rental_order.brand (车辆品牌)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String brand;

    /**
     * t_rental_order.series (车系)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String series;

    /**
     * t_rental_order.model (车型)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String model;




    public static void main(String[] args) {
        ComeCarOrderDTO comeCarOrderDTO = new ComeCarOrderDTO();
        comeCarOrderDTO.setCarId(1);
        comeCarOrderDTO.setCarNumber("xxxxxxxxx");
        comeCarOrderDTO.setOrderId(1);
        comeCarOrderDTO.setComeCarRemark("111");
        comeCarOrderDTO.setFlowOneStatus(1);
        comeCarOrderDTO.setComeCarTime(new Date());
        comeCarOrderDTO.setComeWarehouseMileage("111");
        comeCarOrderDTO.setComeWarehouseOilAmount("1.1111");
        List<ComeCarOrderImgDTO> comeCarOrderImgDTOS = Lists.newArrayList();
        ComeCarOrderImgDTO comeCarOrderImgDTO = new ComeCarOrderImgDTO();
        comeCarOrderImgDTO.setFileName("088656ce-05c6-4e68-b0dd-a907db87931c_ydcd.png");
        comeCarOrderImgDTO.setFileUrl("/usr/local/upload/cgj/rc-upload-1542799300424-12");
        comeCarOrderImgDTO.setFileType("png");
        comeCarOrderImgDTOS.add(comeCarOrderImgDTO);
        comeCarOrderImgDTO = new ComeCarOrderImgDTO();
        comeCarOrderImgDTO.setFileName("088656ce-05c6-4e68-b0dd-a907db87931c_ydcd2.png");
        comeCarOrderImgDTO.setFileUrl("/usr/local/upload/cgj/rc-upload-1542799300424-122");
        comeCarOrderImgDTO.setFileType("png2");
        comeCarOrderImgDTOS.add(comeCarOrderImgDTO);
        comeCarOrderDTO.setComeCarOrderImgDTOS(comeCarOrderImgDTOS);

        Map<String, Object> checkResult = AnnotationDealUtil.validate(comeCarOrderDTO);
        System.out.println("结果"+checkResult.get("result")+"111"+checkResult.get("message"));
        /*System.out.println(JSONObject.toJSON(comeCarOrderDTO));
        ComeCarOrderDTO cDTO = JSONObject.parseObject(JSONObject.toJSON(comeCarOrderDTO).toString(), ComeCarOrderDTO.class);
       logger.info(JSONObject.toJSON(cDTO));*/
    }

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

    public Date getComeCarTime() {
        return comeCarTime;
    }

    public void setComeCarTime(Date comeCarTime) {
        this.comeCarTime = comeCarTime;
    }

    public String getComeWarehouseMileage() {
        return comeWarehouseMileage;
    }

    public void setComeWarehouseMileage(String comeWarehouseMileage) {
        this.comeWarehouseMileage = comeWarehouseMileage;
    }

    public String getComeWarehouseOilAmount() {
        return comeWarehouseOilAmount;
    }

    public void setComeWarehouseOilAmount(String comeWarehouseOilAmount) {
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

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ComeCarOrderImgDTO> getComeCarOrderImgDTOS() {
        return comeCarOrderImgDTOS;
    }

    public void setComeCarOrderImgDTOS(List<ComeCarOrderImgDTO> comeCarOrderImgDTOS) {
        this.comeCarOrderImgDTOS = comeCarOrderImgDTOS;
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

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
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
