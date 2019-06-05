package com.ydc.commom.view.vo.cgj.rental;

import java.util.List;

/**
 * 外部详情车辆查询
 */
public class RentalCarCheckQueryDetailVO extends RentalCarCheckQueryVO {

    /**
     * 门店地址
     */
    private String storeAddress;
    /**
     * 车型照片真实地址
     */
    private String modelImg;
    /**
     * 车型照片名称
     */
    private String modelImgName;
    /**
     * 车型照片地址
     */
    private String modelImgUrl;
    /**
     * 车座位数
     */
    private Integer carSeat;
    /**
     * 车结构
     */
    private String carStructure;
    /**
     * 备注
     */
    private String remark;
    /**
     * 车辆列表
     */
    private List<RentalCarCheckMiniDetailVo> carList;

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getModelImg() {
        return modelImg;
    }

    public void setModelImg(String modelImg) {
        this.modelImg = modelImg;
    }

    public String getModelImgName() {
        return modelImgName;
    }

    public void setModelImgName(String modelImgName) {
        this.modelImgName = modelImgName;
    }

    public String getModelImgUrl() {
        return modelImgUrl;
    }

    public void setModelImgUrl(String modelImgUrl) {
        this.modelImgUrl = modelImgUrl;
    }

    public Integer getCarSeat() {
        return carSeat;
    }

    public void setCarSeat(Integer carSeat) {
        this.carSeat = carSeat;
    }

    public String getCarStructure() {
        return carStructure;
    }

    public void setCarStructure(String carStructure) {
        this.carStructure = carStructure;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<RentalCarCheckMiniDetailVo> getCarList() {
        return carList;
    }

    public void setCarList(List<RentalCarCheckMiniDetailVo> carList) {
        this.carList = carList;
    }

    @Override
    public String toString() {
        return "RentalCarCheckQueryDetailVO{" +
                "storeAddress='" + storeAddress + '\'' +
                ", modelImg='" + modelImg + '\'' +
                ", modelImgName='" + modelImgName + '\'' +
                ", modelImgUrl='" + modelImgUrl + '\'' +
                ", carSeat=" + carSeat +
                ", carStructure='" + carStructure + '\'' +
                ", remark='" + remark + '\'' +
                ", carList=" + carList +
                '}';
    }
}
