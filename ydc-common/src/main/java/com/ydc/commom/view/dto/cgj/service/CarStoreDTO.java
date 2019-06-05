package com.ydc.commom.view.dto.cgj.service;

import com.ydc.model.cgj.Pagination;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * B 端门店
 * @author
 * @create 2018-10-30 10:05
 **/
public class CarStoreDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = -470865875501344951L;

    private Integer storeId;//门店ID
    private String storeName;//门店名称
    private Integer whetherPutaway;//是否上架（0：否；1：是）
    private Integer updateBy;//更新人
    private Integer status;//状态（1：合作；0：解约）
    private String servicePhone;//客服电话
    public CarStoreDTO() {
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getWhetherPutaway() {
        return whetherPutaway;
    }

    public void setWhetherPutaway(Integer whetherPutaway) {
        this.whetherPutaway = whetherPutaway;
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

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }
}
