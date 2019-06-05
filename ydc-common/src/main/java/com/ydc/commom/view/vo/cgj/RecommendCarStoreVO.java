package com.ydc.commom.view.vo.cgj;

import java.io.Serializable;
import java.util.List;

/**
 * 首页推荐门店
 *
 * @author
 * @create 2018-11-09 16:06
 **/
public class RecommendCarStoreVO implements Serializable {
    private static final long serialVersionUID = -1736910827510733132L;

    private String storeRegisterProvince;//省
    private String storeRegisterCity;//市
    private String storeRegisterRegion;//区
    private String detailsAddress;//详细地址

    List<CarStoreVO> carStoreVOList;//门店集合

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

    public String getStoreRegisterRegion() {
        return storeRegisterRegion;
    }

    public void setStoreRegisterRegion(String storeRegisterRegion) {
        this.storeRegisterRegion = storeRegisterRegion;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }

    public List<CarStoreVO> getCarStoreVOList() {
        return carStoreVOList;
    }

    public void setCarStoreVOList(List<CarStoreVO> carStoreVOList) {
        this.carStoreVOList = carStoreVOList;
    }
}
