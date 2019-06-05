package com.ydc.commom.view.vo.cgj.rental;

import com.ydc.commom.enums.common.CommonEnum;
import com.ydc.commom.enums.rental.CommCarEnum;

/**
 * 车辆列表查询
 */
public class CommCarQueryVO {

    /*-----数据库直接返回字段  start-----*/
    private Integer id;

    //门店省份
    private String storeRegisterProvince;

    //门店市
    private String  storeRegisterCity;

    //门店名
    private String storeName;

    //车牌
    private String carPlate;

    //油量
    private Integer oilMass;

    //里程数
    private Integer mileage;

    //品牌
    private String brand;

    //车系
    private String series;

    //车型
    private String model;

    //车等级
    private String carLevel;

    //创建时间
    private String createTime;

    //年检到期时间
    private String asDeadline;

    //交强险到期时间
    private String saliDeadline;

    //商业险到期时间
    private String ciDeadline;

    //启用状态
    private String useStatus;

    //运营状态
    private String operationStatus;
    /*-----数据库直接返回字段  end-----*/

   /*-----数据库直接返回需要处理字段  start-----*/
    //出车类型
    private String turnOutType;

    //车辆来源
    private String source;
    /*-----数据库直接返回需要处理字段  end-----*/


    /*-----根据数据库直接返回字段处理得到的追加字段  start-----*/
    //启用状态
    private String useStatusDesc;

    //运营状态
    private String operationStatusDesc;

    //出车类型
    private boolean enableReturn;
    /*-----根据数据库直接返回字段处理得到的追加字段  end-----*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreRegisterProvince() {
        return storeRegisterProvince;
    }

    public void setStoreRegisterProvince(String storeRegisterProvince) {
        this.storeRegisterProvince = storeRegisterProvince;
    }

    public String getStoreRegisterCity() {
        return storeRegisterCity;
    }

    public void setStoreRegisterCity(String storeRegisterCity) {
        this.storeRegisterCity = storeRegisterCity;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Integer getOilMass() {
        return oilMass;
    }

    public void setOilMass(Integer oilMass) {
        this.oilMass = oilMass;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAsDeadline() {
        return asDeadline;
    }

    public void setAsDeadline(String asDeadline) {
        this.asDeadline = asDeadline;
    }

    public String getSaliDeadline() {
        return saliDeadline;
    }

    public void setSaliDeadline(String saliDeadline) {
        this.saliDeadline = saliDeadline;
    }

    public String getCiDeadline() {
        return ciDeadline;
    }

    public void setCiDeadline(String ciDeadline) {
        this.ciDeadline = ciDeadline;
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
        return CommCarEnum.CommCarTurnOutTypeEnum.transferTurnOutType(this.turnOutType);
    }

    public void setTurnOutType(String turnOutType) {
        this.turnOutType = turnOutType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = CommCarEnum.CommCarSourceEnum.getCodeName(Integer.valueOf(source));
    }

    public String getUseStatusDesc() {
        return CommonEnum.DeleteStatusEnum.getCodeName(Integer.valueOf(this.useStatus));
    }

    public void setUseStatusDesc(String useStatusDesc) {
        this.useStatusDesc = useStatusDesc;
    }

    public String getOperationStatusDesc() {
        return CommCarEnum.CommCarOperationStatusEnum.getCodeName(Integer.valueOf(this.operationStatus));
    }

    public void setOperationStatusDesc(String operationStatusDesc) {
        this.operationStatusDesc = operationStatusDesc;
    }

    public boolean isEnableReturn() {
        return  (this.turnOutType.equals(CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_0.getCode()) || this.turnOutType.equals(CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_8.getCode())) ? false : true;
    }

    public void setEnableReturn(boolean enableReturn) {
        this.enableReturn = enableReturn;
    }
}
