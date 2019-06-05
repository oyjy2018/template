package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.Attribute;
import com.ydc.model.cgj.Pagination;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构单查询实体类
 */
public class RentalOrderMaintenanceInsertDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 车辆id
     */
    @Attribute(name = "车辆id",required = true,isNum = true)
    private Integer carId;

    /**
     * 车牌号
     */
    @Attribute(name = "车牌号",required = true,maxLength = 9)
    private String carNumber;

    /**
     * 车辆所属门店
     */
    @Attribute(name = "所属门店id",required = true,isNum = true)
    private Integer carStoreId;

    /**
     * 车辆所属门店
     */
    @Attribute(name = "所属门店门店",required = true)
    private String carStoreName;

    /**
     * 出车时间
     */
    @Attribute(name = "出车时间",required = true,dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String comeCarTime;

    /**
     * 出库里程数
     */
    @Attribute(name = "出库里程数",required = true,maxLength = 8,isNum = true)
    private Integer comeWarehouseMileage;

    /**
     * 出库油量
     */
    @Attribute(name = "出库油量",required = true,maxLength = 3,isNum = true)
    private Integer comeWarehouseOilAmount;

    /**
     * 出库所在门店id
     */
    @Attribute(name = "出库所在门店id",required = true,isNum = true)
    private Integer comeWarehouseStoreId;

    /**
     * 出库所在门店
     */
    @Attribute(name = "出库所在门店",required = true)
    private String comeWarehouseStoreName;

    /**
     * 驾驶人id
     */
    @Attribute(name = "驾驶人id",required = true,isNum = true)
    private Integer comeCarUserId;

    /**
     * 驾驶人
     */
    @Attribute(name = "驾驶人",required = true)
    private String comeCarUserName;

    /**
     * 机务类型
     */
    @Attribute(name = "机务类型",required = true)
    private String maintenanceType;

    /**
     * 出车备注
     */
    private String comeCarRemark;

    /**
     * 是否还车 默认为未还车
     */
    private Byte repayCar = 0;

    /**
     * 创建人
     */
    @Attribute(name = "创建人",required = true,isNum = true)
    private Integer createBy;

    /**
     * 创建时间  默认为当前时间
     */
    private Date createTime = new Date();

    /**
     * 更新人
     */
    private Integer updateBy;

    /**
     * 更新时间  默认为当前时间
     */
    private Date updateTime = new Date();

    /**
     * 状态 默认为1
     */
    private Byte status = 1;

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

    public Integer getCarStoreId() {
        return carStoreId;
    }

    public void setCarStoreId(Integer carStoreId) {
        this.carStoreId = carStoreId;
    }

    public String getCarStoreName() {
        return carStoreName;
    }

    public void setCarStoreName(String carStoreName) {
        this.carStoreName = carStoreName;
    }

    public String getComeCarTime() {
        return comeCarTime;
    }

    public void setComeCarTime(String comeCarTime) {
        this.comeCarTime = comeCarTime;
    }

    public Integer getComeWarehouseMileage() {
        return comeWarehouseMileage;
    }

    public void setComeWarehouseMileage(Integer comeWarehouseMileage) {
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

    public Integer getComeCarUserId() {
        return comeCarUserId;
    }

    public void setComeCarUserId(Integer comeCarUserId) {
        this.comeCarUserId = comeCarUserId;
    }

    public String getComeCarUserName() {
        return comeCarUserName;
    }

    public void setComeCarUserName(String comeCarUserName) {
        this.comeCarUserName = comeCarUserName;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getComeCarRemark() {
        return comeCarRemark;
    }

    public void setComeCarRemark(String comeCarRemark) {
        this.comeCarRemark = comeCarRemark;
    }

    public Byte getRepayCar() {
        return repayCar;
    }

    public void setRepayCar(Byte repayCar) {
        this.repayCar = repayCar;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
