package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.Attribute;

public class CommCarSeriesDTO {

    /**
     * 品牌
     */
    @Attribute(name = "品牌", required = true)
    private String brand;

    /**
     * 车系
     */
    @Attribute(name = "车系", required = true)
    private String series;

    /**
     * 车型
     */
    @Attribute(name = "车型", required = true)
    private String model;

    /**
     * 车等级
     */
    @Attribute(name = "车等级", required = true)
    private String carLevel;

    /**
     * 车结构
     */
    @Attribute(name = "车结构", required = true)
    private String carStructure;

    /**
     * 车座位数
     */
    @Attribute(name = "车座位数", required = true)
    private String carSeat;

    /**
     * 变速箱
     */
    @Attribute(name = "变速箱", required = true)
    private String gearBox;

    /**
     * 发动机
     */
    @Attribute(name = "发动机", required = true)
    private String engine;

    /**
     * 燃料形式
     */
    @Attribute(name = "燃料形式", required = true)
    private String fuelForm;

    /**
     * 燃料标号
     */
    @Attribute(name = "燃料标号", required = true)
    private String fuelLabeling;

    /**
     * 油箱容积L
     */
    @Attribute(name = "油箱容积", required = true, isDigit = true, intLength = 3)
    private String tankVolume;

    /**
     * 排放量
     */
    @Attribute(name = "排放量", required = true, isDigit = true, intLength = 1, decimalLength = 1)
    private String outputVolume;

    /**
     * 排放标准
     */
    @Attribute(name = "排放标准", required = true)
    private String emissionsStandard;

    /**
     * 驱动
     */
    @Attribute(name = "驱动", required = true)
    private String drive;

    /**
     * 座椅材质
     */
    @Attribute(name = "座椅材质", required = true)
    private String seatTexture;

    /**
     * 气囊数
     */
    @Attribute(name = "气囊数", required = true)
    private String gasbagNumber;

    /**
     * 是否有GPS导航
     */
    @Attribute(name = "是否有GPS导航", required = true, isNum = true, length = 1)
    private String hasGpsNavigation;

    /**
     * 是否有倒车雷达
     */
    @Attribute(name = "是否有倒车雷达", required = true, isNum = true, length = 1)
    private String hasParkingSensors;

    /**
     * 音箱
     */
    @Attribute(name = "音箱", required = true)
    private String soundBox;

    /**
     * 主图图片名
     */
    @Attribute(name = "主图图片名", required = true)
    private String mainImgName;

    /**
     * 主图地址
     */
    @Attribute(name = "主图地址", required = true)
    private String mainImgUrl;

    /**
     * 操作人
     */
    @Attribute(name = "操作人")
    private String userId;

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

    public void setTankVolume(String tankVolume) {
        this.tankVolume = tankVolume;
    }

    public String getOutputVolume() {
        return outputVolume;
    }

    public void setOutputVolume(String outputVolume) {
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

    public String getHasGpsNavigation() {
        return hasGpsNavigation;
    }

    public void setHasGpsNavigation(String hasGpsNavigation) {
        this.hasGpsNavigation = hasGpsNavigation;
    }

    public String getHasParkingSensors() {
        return hasParkingSensors;
    }

    public void setHasParkingSensors(String hasParkingSensors) {
        this.hasParkingSensors = hasParkingSensors;
    }

    public String getSoundBox() {
        return soundBox;
    }

    public void setSoundBox(String soundBox) {
        this.soundBox = soundBox;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
