package com.ydc.model.cgj.car;

import com.ydc.model.util.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * t_comm_car_series
 * @author 
 */
public class CommCarSeries implements Serializable {
    private Integer id;

    /**
     * 品牌
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
    private String gasbagNumber;

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
     * 是否启用
     */
    private Integer hasEnabled;

    /**
     * 主图图片名
     */
    private String mainImgName;

    /**
     * 主图地址
     */
    private String mainImgUrl;

    /**
     * 预览主图地址
     */
    private String viewMainImgUrl;

    private Date createTime;

    private Integer createBy;

    private Date updateTime;

    private Integer updateBy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getGasbagNumber() {
        return gasbagNumber;
    }

    public void setGasbagNumber(String gasbagNumber) {
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

    public Integer getHasEnabled() {
        return hasEnabled;
    }

    public void setHasEnabled(Integer hasEnabled) {
        this.hasEnabled = hasEnabled;
    }

    public String getMainImgName() {
        return mainImgName;
    }

    public void setMainImgName(String mainImgName) {
        this.mainImgName = mainImgName;
    }

    public String getMainImgUrl() {
        return mainImgUrl;
    }

    public void setMainImgUrl(String mainImgUrl) {
        this.mainImgUrl = mainImgUrl;
    }

    public String getViewMainImgUrl() {
        return viewMainImgUrl;
    }

    public void setViewMainImgUrl(String viewMainImgUrl) {
        this.viewMainImgUrl = viewMainImgUrl;
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

    public CommCarSeries(){

    }

    public CommCarSeries(Map<String, String> param){
        this.id = StringUtil.strToInteger(param.get("id"));
        this.brand = param.get("brand");
        this.series = param.get("series");
        this.model = param.get("model");
        this.carLevel = param.get("carLevel");
        this.carStructure = param.get("carStructure");
        this.carSeat = param.get("carSeat");
        this.gearBox = param.get("gearBox");
        this.engine = param.get("engine");
        this.fuelForm = param.get("fuelForm");
        this.fuelLabeling = param.get("fuelLabeling");
        this.tankVolume = StringUtil.strToInteger(param.get("tankVolume"));
        this.outputVolume = StringUtil.strToBigDecimal(param.get("outputVolume"));
        this.emissionsStandard = param.get("emissionsStandard");
        this.drive = param.get("drive");
        this.seatTexture = param.get("seatTexture");
        this.gasbagNumber = param.get("gasbagNumber");
        this.hasGpsNavigation = StringUtil.strToInteger(param.get("hasGpsNavigation"));
        this.hasParkingSensors = StringUtil.strToInteger(param.get("hasParkingSensors"));
        this.soundBox = param.get("soundBox");
        this.mainImgName = param.get("mainImgName");
        this.mainImgUrl = param.get("mainImgUrl");
    }
}