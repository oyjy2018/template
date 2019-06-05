package com.ydc.model.ydhc;

import com.ydc.model.annotation.Attribute;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * t_ydhc_vehicle_used
 *
 * @author
 */
public class YdhcVehicleUsed implements Serializable {
    private static Logger logger = LogManager.getLogger(YdhcVehicleUsed.class);

    @Attribute(name = "车辆id")
    private Integer id;

    /**
     * 车辆标题
     */
    @Attribute(name = "车辆标题",required = true, emptyStringVerify = true,maxLength = 60)
    private String title;

    /**
     * 车主姓名
     */
    @Attribute(name = "车主姓名",required = true, emptyStringVerify = true)
    private String carOwnerName;

    /**
     * 车架号
     */
    @Attribute(name = "车架号",required = true, emptyStringVerify = true,maxLength = 30)
    private String vin;

    /**
     * 车牌号
     */
    @Attribute(name = "车牌号",required = true, emptyStringVerify = true,maxLength = 8)
    private String carPlate;


    /**
     * 车辆品牌code
     */
    @Attribute(name = "车辆品牌code",required = true, emptyStringVerify = true)
    private String brandCode;

    /**
     * 车辆品牌
     */
    @Attribute(name = "车辆品牌",required = true, emptyStringVerify = true)
    private String brand;

    /**
     * 车系code
     */
    @Attribute(name = "车系code",required = true, emptyStringVerify = true)
    private String seriesCode;

    /**
     * 车系
     */
    @Attribute(name = "车系",required = true, emptyStringVerify = true)
    private String series;

    /**
     * 车版本code
     */
    @Attribute(name = "车版本code",required = true, emptyStringVerify = true)
    private String carVersionCode;

    /**
     * 车版本
     */
    @Attribute(name = "车版本",required = true, emptyStringVerify = true)
    private String carVersion;

    /**
     * 车型code
     */
    @Attribute(name = "车型code",required = true, emptyStringVerify = true)
    private String carTypeCode;

    /**
     * 车型（SUV,面包车之类的）
     */
    @Attribute(name = "车型",required = true, emptyStringVerify = true)
    private String carType;

    /**
     * 变速箱code
     */
    @Attribute(name = "变速箱code",required = true, emptyStringVerify = true)
    private String gearboxCode;

    /**
     * 变速箱
     */
    @Attribute(name = "变速箱code",required = true, emptyStringVerify = true)
    private String gearbox;

    /**
     * 上牌日期
     */
    @Attribute(name = "上牌日期",required = true)
    private Date upPlateDate;

    /**
     * 表显里程
     */
    @Attribute(name = "表显里程",required = true,emptyStringVerify = true,isDigit = true,maxLength = 8)
    private BigDecimal mileage;

    /**
     * 排放量
     */
    @Attribute(name = "排放量",required = true,emptyStringVerify = true,isDigit = true,maxLength = 4)
    private BigDecimal emissions;

    /**
     * 发动机code
     */
    private String engineCode;

    /**
     * 发动机
     */
    private String engine;

    /**
     * 排放标准code
     */
    private String emissionsStandardCode;

    /**
     * 排放标准
     */
    private String emissionsStandard;

    /**
     * 燃料类型code
     */
    @Attribute(name = "燃料类型code",required = true, emptyStringVerify = true)
    private String fuelTypeCode;

    /**
     * 燃料类型
     */
    @Attribute(name = "燃料类型",required = true, emptyStringVerify = true)
    private String fuelType;

    /**
     * 座位数code
     */
    @Attribute(name = "座位数code",required = true, emptyStringVerify = true)
    private String seatingsCode;

    /**
     * 座位数
     */
    @Attribute(name = "座位数",required = true, emptyStringVerify = true)
    private String seatings;

    /**
     * 厂商类型code
     */
    private String manufacturerTypeCode;

    /**
     * 厂商类型
     */
    private String manufacturerType;

    /**
     * 国别code
     */
    private String countryCode;

    /**
     * 国别
     */
    private String country;

    /**
     * 驱动code
     */
    @Attribute(name = "驱动code",required = true, emptyStringVerify = true)
    private String driveCode;

    /**
     * 驱动
     */
    @Attribute(name = "驱动",required = true, emptyStringVerify = true)
    private String drive;

    /**
     * 车牌所在省code
     */
    @Attribute(name = "车牌所在省code",required = true, emptyStringVerify = true)
    private String provinceCode;

    /**
     * 车牌所在省
     */
    @Attribute(name = "车牌所在省",required = true, emptyStringVerify = true)
    private String province;

    /**
     * 车牌所在市code
     */
    @Attribute(name = "车牌所在市code",required = true, emptyStringVerify = true)
    private String cityCode;

    /**
     * 车牌所在市
     */
    @Attribute(name = "车牌所在市",required = true, emptyStringVerify = true)
    private String city;


    /**
     * 外观颜色code
     */
    private String facadeColourCode;

    /**
     * 外观颜色
     */
    private String facadeColour;

    /**
     * 内饰颜色code
     */
    private String interiorColourCode;

    /**
     * 内饰颜色
     */
    private String interiorColour;

    /**
     * 能否过户
     */
    @Attribute(name = "能否过户",required = true, emptyStringVerify = true)
    private String isTransfer;

    /**
     * 年检到期日期
     */
    @Attribute(name = "年检到期日期",required = true, emptyStringVerify = true)
    private Date asDeadline;

    /**
     * 交强险到期日期
     */
    @Attribute(name = "年检到期日期",required = true, emptyStringVerify = true)
    private Date saliDeadline;

    /**
     * 商业险到期日期
     */
    private Date ciDeadline;

    /**
     * 看车地点省编码
     */
    @Attribute(name = "看车地点省编码",required = true, emptyStringVerify = true)
    private String lookCarProvinceCode;

    /**
     * 看车地点省
     */
    @Attribute(name = "看车地点省",required = true, emptyStringVerify = true)
    private String lookCarProvince;

    /**
     * 看车地点市编码
     */
    @Attribute(name = "看车地点市编码",required = true, emptyStringVerify = true)
    private String lookCarCityCode;

    /**
     * 看车地点市
     */
    @Attribute(name = "看车地点市",required = true, emptyStringVerify = true)
    private String lookCarCity;

    /**
     * 看车地点县/区编码
     */
    @Attribute(name = "看车地点县/区编码",required = true, emptyStringVerify = true)
    private String lookCarRegionCode;

    /**
     * 看车地点县/区
     */
    @Attribute(name = "看车地点县/区",required = true, emptyStringVerify = true)
    private String lookCarRegion;

    /**
     * 看车地址
     */
    @Attribute(name = "看车地址",required = true, emptyStringVerify = true,maxLength = 50)
    private String lookCarAddress;

    /**
     * 配置信息
     */
    @Attribute(name = "配置信息",required = true, emptyStringVerify = true,maxLength = 600)
    private String config;

    /**
     * 检测日期
     */
    private Date examineDate;

    /**
     * 检测报告
     */
    private String examineReport;

    /**
     * 商家code
     */
    private String merchantCode;

    /**
     * 商家
     */
    private String merchant;

    /**
     * 售价
     */
    @Attribute(name = "售价",required = true, emptyStringVerify = true,isDigit = true,maxLength = 13)
    private BigDecimal price;

    /**
     * 新车含税价
     */
    @Attribute(name = "新车含税价",required = true, emptyStringVerify = true,isDigit = true,maxLength = 13)
    private BigDecimal newCarPrice;

    /**
     * 是否是模板
     */
    private String isTemplate;

    /**
     * 发布状态（0：未发布；1已发布；2：已下架
     */
    private Integer releaseStatus;

    /**
     * 发布时间
     */
    private Date releaseDate;

    /**
     * 发布人
     */
    private String releasePerson;

    /**
     * 发布人ID
     */
    private Integer releasePersonId;

    /**
     * 下架时间
     */
    private Date shelveDate;

    /**
     * 下架人
     */
    private String shelvePerson;

    /**
     * 下架人ID
     */
    private Integer shelvePersonId;

    private Date createTime;

    private Integer createBy;

    private Date updateTime;

    private Integer updateBy;

    private static final long serialVersionUID = 1L;

    public YdhcVehicleUsed() {
    }

    ;

    public YdhcVehicleUsed(Map<String, String> param) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.id = StringUtils.isEmpty(param.get("id")) ? null : Integer.valueOf(param.get("id"));
            this.title = param.get("title");
            this.carOwnerName = param.get("carOwnerName");
            this.vin = param.get("vin");
            this.carPlate = param.get("carPlate");
            this.brandCode = String.valueOf(param.get("brandCode"));
            this.brand = param.get("brand");
            this.seriesCode =  String.valueOf(param.get("seriesCode"));
            this.series = param.get("series");
            this.carVersionCode =  String.valueOf(param.get("carVersionCode"));
            this.carVersion = param.get("carVersion");
            this.carTypeCode = param.get("carTypeCode");
            this.carType = param.get("carType");
            this.gearboxCode = param.get("gearboxCode");
            this.gearbox = param.get("gearbox");
            this.upPlateDate = StringUtils.isEmpty(param.get("upPlateDate")) ? null : sdf.parse(param.get("upPlateDate"));
            this.mileage = StringUtils.isEmpty(String.valueOf(param.get("mileage"))) ? null : new BigDecimal(String.valueOf(param.get("mileage")));
            this.emissions = StringUtils.isEmpty(String.valueOf(param.get("emissions"))) ? null : new BigDecimal(String.valueOf(param.get("emissions")));
            this.engineCode = param.get("engineCode");
            this.engine = param.get("engine");
            this.emissionsStandardCode = param.get("emissionsStandardCode");
            this.emissionsStandard = param.get("emissionsStandard");
            this.fuelTypeCode = param.get("fuelTypeCode");
            this.fuelType = param.get("fuelType");
            this.seatingsCode = param.get("seatingsCode");
            this.seatings = param.get("seatings");
            this.manufacturerTypeCode = param.get("manufacturerTypeCode");
            this.manufacturerType = param.get("manufacturerType");
            this.countryCode = param.get("countryCode");
            this.country = param.get("country");
            this.driveCode = param.get("driveCode");
            this.drive = param.get("drive");
            this.provinceCode = param.get("provinceCode");
            this.province = param.get("province");
            this.cityCode = param.get("cityCode");
            this.city = param.get("city");
            this.isTransfer = param.get("isTransfer");
            this.facadeColourCode = param.get("facadeColourCode");
            this.facadeColour = param.get("facadeColour");
            this.interiorColourCode = param.get("interiorColourCode");
            this.interiorColour = param.get("interiorColour");
            this.asDeadline = StringUtils.isEmpty(param.get("asDeadline")) ? null : sdf.parse(param.get("asDeadline"));
            this.saliDeadline = StringUtils.isEmpty(param.get("saliDeadline")) ? null : sdf.parse(param.get("saliDeadline"));
            this.ciDeadline = StringUtils.isEmpty(param.get("ciDeadline")) ? null : sdf.parse(param.get("ciDeadline"));
            this.lookCarProvinceCode = param.get("lookCarProvinceCode");
            this.lookCarProvince = param.get("lookCarProvince");
            this.lookCarCityCode = param.get("lookCarCityCode");
            this.lookCarCity = param.get("lookCarCity");
            this.lookCarRegionCode = param.get("lookCarRegionCode");
            this.lookCarRegion = param.get("lookCarRegion");
            this.lookCarAddress = param.get("lookCarAddress");
            this.config = param.get("config");
            this.examineDate = StringUtils.isEmpty(param.get("examineDate")) ? null : sdf.parse(param.get("examineDate"));
            this.examineReport = param.get("examineReport");
            this.merchantCode = param.get("merchantCode");
            this.merchant = param.get("merchant");
            this.price = StringUtils.isEmpty(param.get("price")) ? null : new BigDecimal(param.get("price"));
            this.newCarPrice = StringUtils.isEmpty(param.get("newCarPrice")) ? null : new BigDecimal(param.get("newCarPrice"));
            this.isTemplate = StringUtils.isEmpty(param.get("isTemplate")) ? "false" : param.get("isTemplate"); //是否是模板  默认是false

            this.releaseStatus = StringUtils.isEmpty(param.get("releaseStatus")) ? null : Integer.valueOf(param.get("releaseStatus"));
            this.releaseDate = StringUtils.isEmpty(param.get("releaseDate")) ? null : sdf.parse(param.get("releaseDate"));
            this.releasePerson = param.get("releasePerson");
            this.releasePersonId = StringUtils.isEmpty(param.get("releasePersonId")) ? null : Integer.valueOf(param.get("releasePersonId"));
            this.shelveDate = StringUtils.isEmpty(param.get("shelveDate")) ? null : sdf.parse(param.get("shelveDate"));
            this.shelvePerson = param.get("shelvePerson");
            this.shelvePersonId = StringUtils.isEmpty(param.get("shelvePersonId")) ? null : Integer.valueOf(param.get("shelvePersonId"));
            this.createTime = StringUtils.isEmpty(param.get("createTime")) ? null : sdf.parse(param.get("createTime"));
            this.createBy = StringUtils.isEmpty(param.get("createBy")) ? null : Integer.valueOf(param.get("createBy"));
            this.updateTime = StringUtils.isEmpty(param.get("updateTime")) ? null : sdf.parse(param.get("updateTime"));
            this.updateBy = StringUtils.isEmpty(param.get("updateBy")) ? null : Integer.valueOf(param.get("updateBy"));
        } catch (Exception e) {
            throw new RuntimeException("时间格式化异常");
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCarOwnerName() {
        return carOwnerName;
    }

    public void setCarOwnerName(String carOwnerName) {
        this.carOwnerName = carOwnerName;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getCarVersionCode() {
        return carVersionCode;
    }

    public void setCarVersionCode(String carVersionCode) {
        this.carVersionCode = carVersionCode;
    }

    public String getCarVersion() {
        return carVersion;
    }

    public void setCarVersion(String carVersion) {
        this.carVersion = carVersion;
    }

    public String getCarTypeCode() {
        return carTypeCode;
    }

    public void setCarTypeCode(String carTypeCode) {
        this.carTypeCode = carTypeCode;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getGearboxCode() {
        return gearboxCode;
    }

    public void setGearboxCode(String gearboxCode) {
        this.gearboxCode = gearboxCode;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public Date getUpPlateDate() {
        return upPlateDate;
    }

    public void setUpPlateDate(Date upPlateDate) {
        this.upPlateDate = upPlateDate;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getEmissions() {
        return emissions;
    }

    public void setEmissions(BigDecimal emissions) {
        this.emissions = emissions;
    }

    public String getEngineCode() {
        return engineCode;
    }

    public void setEngineCode(String engineCode) {
        this.engineCode = engineCode;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getEmissionsStandardCode() {
        return emissionsStandardCode;
    }

    public void setEmissionsStandardCode(String emissionsStandardCode) {
        this.emissionsStandardCode = emissionsStandardCode;
    }

    public String getEmissionsStandard() {
        return emissionsStandard;
    }

    public void setEmissionsStandard(String emissionsStandard) {
        this.emissionsStandard = emissionsStandard;
    }

    public String getFuelTypeCode() {
        return fuelTypeCode;
    }

    public void setFuelTypeCode(String fuelTypeCode) {
        this.fuelTypeCode = fuelTypeCode;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getSeatingsCode() {
        return seatingsCode;
    }

    public void setSeatingsCode(String seatingsCode) {
        this.seatingsCode = seatingsCode;
    }

    public String getSeatings() {
        return seatings;
    }

    public void setSeatings(String seatings) {
        this.seatings = seatings;
    }

    public String getManufacturerTypeCode() {
        return manufacturerTypeCode;
    }

    public void setManufacturerTypeCode(String manufacturerTypeCode) {
        this.manufacturerTypeCode = manufacturerTypeCode;
    }

    public String getManufacturerType() {
        return manufacturerType;
    }

    public void setManufacturerType(String manufacturerType) {
        this.manufacturerType = manufacturerType;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDriveCode() {
        return driveCode;
    }

    public void setDriveCode(String driveCode) {
        this.driveCode = driveCode;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFacadeColourCode() {
        return facadeColourCode;
    }

    public void setFacadeColourCode(String facadeColourCode) {
        this.facadeColourCode = facadeColourCode;
    }

    public String getFacadeColour() {
        return facadeColour;
    }

    public void setFacadeColour(String facadeColour) {
        this.facadeColour = facadeColour;
    }

    public String getInteriorColourCode() {
        return interiorColourCode;
    }

    public void setInteriorColourCode(String interiorColourCode) {
        this.interiorColourCode = interiorColourCode;
    }

    public String getInteriorColour() {
        return interiorColour;
    }

    public void setInteriorColour(String interiorColour) {
        this.interiorColour = interiorColour;
    }

    public String getIsTransfer() {
        return isTransfer;
    }

    public void setIsTransfer(String isTransfer) {
        this.isTransfer = isTransfer;
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

    public String getLookCarProvinceCode() {
        return lookCarProvinceCode;
    }

    public void setLookCarProvinceCode(String lookCarProvinceCode) {
        this.lookCarProvinceCode = lookCarProvinceCode;
    }

    public String getLookCarProvince() {
        return lookCarProvince;
    }

    public void setLookCarProvince(String lookCarProvince) {
        this.lookCarProvince = lookCarProvince;
    }

    public String getLookCarCityCode() {
        return lookCarCityCode;
    }

    public void setLookCarCityCode(String lookCarCityCode) {
        this.lookCarCityCode = lookCarCityCode;
    }

    public String getLookCarCity() {
        return lookCarCity;
    }

    public void setLookCarCity(String lookCarCity) {
        this.lookCarCity = lookCarCity;
    }

    public String getLookCarRegionCode() {
        return lookCarRegionCode;
    }

    public void setLookCarRegionCode(String lookCarRegionCode) {
        this.lookCarRegionCode = lookCarRegionCode;
    }

    public String getLookCarRegion() {
        return lookCarRegion;
    }

    public void setLookCarRegion(String lookCarRegion) {
        this.lookCarRegion = lookCarRegion;
    }

    public String getLookCarAddress() {
        return lookCarAddress;
    }

    public void setLookCarAddress(String lookCarAddress) {
        this.lookCarAddress = lookCarAddress;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public Date getExamineDate() {
        return examineDate;
    }

    public void setExamineDate(Date examineDate) {
        this.examineDate = examineDate;
    }

    public String getExamineReport() {
        return examineReport;
    }

    public void setExamineReport(String examineReport) {
        this.examineReport = examineReport;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getNewCarPrice() {
        return newCarPrice;
    }

    public void setNewCarPrice(BigDecimal newCarPrice) {
        this.newCarPrice = newCarPrice;
    }

    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public String getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(String isTemplate) {
        this.isTemplate = isTemplate;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleasePerson() {
        return releasePerson;
    }

    public void setReleasePerson(String releasePerson) {
        this.releasePerson = releasePerson;
    }

    public Integer getReleasePersonId() {
        return releasePersonId;
    }

    public void setReleasePersonId(Integer releasePersonId) {
        this.releasePersonId = releasePersonId;
    }

    public Date getShelveDate() {
        return shelveDate;
    }

    public void setShelveDate(Date shelveDate) {
        this.shelveDate = shelveDate;
    }

    public String getShelvePerson() {
        return shelvePerson;
    }

    public void setShelvePerson(String shelvePerson) {
        this.shelvePerson = shelvePerson;
    }

    public Integer getShelvePersonId() {
        return shelvePersonId;
    }

    public void setShelvePersonId(Integer shelvePersonId) {
        this.shelvePersonId = shelvePersonId;
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
}