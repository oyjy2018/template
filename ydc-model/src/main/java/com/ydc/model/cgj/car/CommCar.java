package com.ydc.model.cgj.car;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * t_comm_car
 * @author 
 */
public class CommCar implements Serializable {
    private Integer id;

    /**
     * 车辆类型（0：自购车，1：二手车）
     */
    private String source;

    /**
     * 所属门店code
     */
    private Integer storeId;

    /**

     * 所属门店
     */
    private String store;

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

    /**
     * 车型
     */
    private Integer modelId;

    /**
     * 产地（0：国产，1：进口）
     */
    private String productionPlace;

    /**
     * 车辆国别
     */
    private String country;

    /**
     * 车牌号
     */
    private String carPlate;

    /**
     * 上牌日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date upPlateDate;

    /**
     * 车架号
     */
    private String vin;

    /**
     * 发动机号
     */
    private String engineNo;

    /**
     * 行驶里程
     */
    private BigDecimal mileage;

    /**
     * 邮箱刻度
     */
    private String oilGaugeScale;

    /**
     * 油量
     */
    private BigDecimal oilMass;

    /**
     * 外观颜色
     */
    private String facadeColour;

    /**
     * 内饰颜色
     */
    private String interiorColour;

    /**
     * 过户次数
     */
    private Integer transferTimes;

    /**
     * 年检到期日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date asDeadline;

    /**
     * 交强险到期日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date saliDeadline;

    /**
     * 商业险到期日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date ciDeadline;

    /**
     * 车等级
     */
    private String carLevel;

    /**
     * 车结构
     */
    private String carStructure;

    /**
     * 车座位数
     */
    private String carSeat;

    /**
     * 变速箱
     */
    private String gearBox;

    /**
     * 发动机
     */
    private String engine;

    /**
     * 燃料形式
     */
    private String fuelForm;

    /**
     * 燃料标号
     */
    private String fuelLabeling;

    /**
     * 油箱容积L
     */
    private Integer tankVolume;

    /**
     * 排放量
     */
    private BigDecimal outputVolume;

    /**
     * 排放标准
     */
    private String emissionsStandard;

    /**
     * 驱动
     */
    private String drive;

    /**
     * 座椅材质
     */
    private String seatTexture;

    /**
     * 气囊数
     */
    private Integer gasbagNumber;

    /**
     * 是否有GPS导航
     */
    private Integer hasGpsNavigation;

    /**
     * 是否有倒车雷达
     */
    private Integer hasParkingSensors;

    /**
     * 音箱
     */
    private String soundBox;

    /**
     * 主图地址
     */
    private String mainImgUrl;

    /**
     * 主图名
     */
    private String mainImgName ;

    /**
     * 启用状态（0：禁用，1：启用）
     */
    private String useStatus;

    /**
     * 车辆运营状态（0：待检，1：待租，2：已租，3：暂停运营）
     */
    private String operationStatus;

    /**
     * 出车类型（0：未出车，1：租车，2：维修，3：保养，4：洗车，5：加油，6：同城调度，7：城际调度，8：公务，9：其他）
     */
    private String turnOutType;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer createBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer updateBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date useStatusUpdateTime;

    private Integer useStatusUpdateBy;

    private static final long serialVersionUID = 1L;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
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

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Date getUpPlateDate() {
        return upPlateDate;
    }

    public void setUpPlateDate(Date upPlateDate) {
        this.upPlateDate = upPlateDate;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public String getOilGaugeScale() {
        return oilGaugeScale;
    }

    public void setOilGaugeScale(String oilGaugeScale) {
        this.oilGaugeScale = oilGaugeScale;
    }

    public BigDecimal getOilMass() {
        return oilMass;
    }

    public void setOilMass(BigDecimal oilMass) {
        this.oilMass = oilMass;
    }

    public String getFacadeColour() {
        return facadeColour;
    }

    public void setFacadeColour(String facadeColour) {
        this.facadeColour = facadeColour;
    }

    public String getInteriorColour() {
        return interiorColour;
    }

    public void setInteriorColour(String interiorColour) {
        this.interiorColour = interiorColour;
    }

    public Integer getTransferTimes() {
        return transferTimes;
    }

    public void setTransferTimes(Integer transferTimes) {
        this.transferTimes = transferTimes;
    }

    public Date getAsDeadline() {
        return asDeadline;
    }

    public void setAsDeadline(Date asDeadline) {
        this.asDeadline = asDeadline;
    }

    public Date getSaliDeadline() {
        return saliDeadline;
    }

    public void setSaliDeadline(Date saliDeadline) {
        this.saliDeadline = saliDeadline;
    }

    public Date getCiDeadline() {
        return ciDeadline;
    }

    public void setCiDeadline(Date ciDeadline) {
        this.ciDeadline = ciDeadline;
    }

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
    }

    public String getCarStructure() {
        return carStructure;
    }

    public void setCarStructure(String carStructure) {
        this.carStructure = carStructure;
    }

    public String getCarSeat() {
        return carSeat;
    }

    public void setCarSeat(String carSeat) {
        this.carSeat = carSeat;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getFuelForm() {
        return fuelForm;
    }

    public void setFuelForm(String fuelForm) {
        this.fuelForm = fuelForm;
    }

    public String getFuelLabeling() {
        return fuelLabeling;
    }

    public void setFuelLabeling(String fuelLabeling) {
        this.fuelLabeling = fuelLabeling;
    }

    public Integer getTankVolume() {
        return tankVolume;
    }

    public void setTankVolume(Integer tankVolume) {
        this.tankVolume = tankVolume;
    }

    public BigDecimal getOutputVolume() {
        return outputVolume;
    }

    public void setOutputVolume(BigDecimal outputVolume) {
        this.outputVolume = outputVolume;
    }

    public String getEmissionsStandard() {
        return emissionsStandard;
    }

    public void setEmissionsStandard(String emissionsStandard) {
        this.emissionsStandard = emissionsStandard;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getSeatTexture() {
        return seatTexture;
    }

    public void setSeatTexture(String seatTexture) {
        this.seatTexture = seatTexture;
    }

    public Integer getGasbagNumber() {
        return gasbagNumber;
    }

    public void setGasbagNumber(Integer gasbagNumber) {
        this.gasbagNumber = gasbagNumber;
    }

    public Integer getHasGpsNavigation() {
        return hasGpsNavigation;
    }

    public void setHasGpsNavigation(Integer hasGpsNavigation) {
        this.hasGpsNavigation = hasGpsNavigation;
    }

    public Integer getHasParkingSensors() {
        return hasParkingSensors;
    }

    public void setHasParkingSensors(Integer hasParkingSensors) {
        this.hasParkingSensors = hasParkingSensors;
    }

    public String getSoundBox() {
        return soundBox;
    }

    public void setSoundBox(String soundBox) {
        this.soundBox = soundBox;
    }

    public String getMainImgUrl() {
        return mainImgUrl;
    }

    public void setMainImgUrl(String mainImgUrl) {
        this.mainImgUrl = mainImgUrl;
    }

    public String getMainImgName() {
        return mainImgName;
    }

    public void setMainImgName(String mainImgName) {
        this.mainImgName = mainImgName;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getTurnOutType() {
        return turnOutType;
    }

    public void setTurnOutType(String turnOutType) {
        this.turnOutType = turnOutType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUseStatusUpdateTime() {
        return useStatusUpdateTime;
    }

    public void setUseStatusUpdateTime(Date useStatusUpdateTime) {
        this.useStatusUpdateTime = useStatusUpdateTime;
    }

    public Integer getUseStatusUpdateBy() {
        return useStatusUpdateBy;
    }

    public void setUseStatusUpdateBy(Integer useStatusUpdateBy) {
        this.useStatusUpdateBy = useStatusUpdateBy;
    }
}