package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.commom.result.Result;
import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * 我的车辆查询条件
 */
public class RentalCarMyListDTO extends Pagination implements Serializable {
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 品牌
     */
    private String model;
    /**
     * 车等级
     */
    private String carLevel;
    /**
     * 车辆状态 0待审核 1待发布 2己发布 3审核失败 4己出租 5己删除 -1全部
     */
    private Integer status;
    /**
     * 企业id
     */
    private Integer companyId;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "RentalCarMyListDTO{" +
                "storeName='" + storeName + '\'' +
                ", model='" + model + '\'' +
                ", carLevel='" + carLevel + '\'' +
                ", status=" + status +
                ", companyId=" + companyId +
                '}';
    }
}
