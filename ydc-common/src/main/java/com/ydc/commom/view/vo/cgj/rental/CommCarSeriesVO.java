package com.ydc.commom.view.vo.cgj.rental;

import com.ydc.commom.enums.common.CommonEnum;
import com.ydc.commom.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

public class CommCarSeriesVO {

    private Integer commCarSeriesId;

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
    private String tankVolume;

    /**
     * 排放量
     */
    private String outputVolume;

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
    private String hasGpsNavigation;

    /**
     * 是否有倒车雷达
     */
    private String hasParkingSensors;

    /**
     * 音箱
     */
    private String soundBox;

    /**
     * 是否启用
     */
    private String hasEnabled;

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

    /**
     * 创建时间
     */
    private String createTime;

    public Integer getCommCarSeriesId() {
        return commCarSeriesId;
    }

    public void setCommCarSeriesId(Integer commCarSeriesId) {
        this.commCarSeriesId = commCarSeriesId;
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

    public String getTankVolume() {
        return tankVolume;
    }

    public void setTankVolume(Integer tankVolume) {
        if(tankVolume == null){
            this.tankVolume = "";
        }else{
            this.tankVolume = tankVolume.toString() + "/L";
        }
    }

    public String getOutputVolume() {
        return outputVolume;
    }

    public void setOutputVolume(BigDecimal outputVolume) {
        if(outputVolume == null){
            this.outputVolume = "";
        }else{
            this.outputVolume = outputVolume.toString() + "/L";
        }
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

    public String getHasGpsNavigation() {
        return hasGpsNavigation;
    }

    public void setHasGpsNavigation(Integer hasGpsNavigation) {
        this.hasGpsNavigation = CommonEnum.WhetherEnum.getCodeName(hasGpsNavigation);
    }

    public String getHasParkingSensors() {
        return hasParkingSensors;
    }

    public void setHasParkingSensors(Integer hasParkingSensors) {
        if(hasParkingSensors == null){
            this.hasParkingSensors = "";
        }else{
            this.hasParkingSensors = CommonEnum.WhetherEnum.getCodeName(hasParkingSensors);
        }
    }

    public String getSoundBox() {
        return soundBox;
    }

    public void setSoundBox(String soundBox) {
        this.soundBox = soundBox;
    }

    public String getHasEnabled() {
        return hasEnabled;
    }

    public void setHasEnabled(Integer hasEnabled) {
        if(hasEnabled == null){
            this.hasEnabled = "";
        }else{
            this.hasEnabled = CommonEnum.DeleteStatusEnum.getCodeName(hasEnabled);
        }
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = DateUtil.formatDateAndTime(createTime);
    }
}
