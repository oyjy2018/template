package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * 我的车辆查询
 */
public class RentalCarListDTO extends Pagination implements Serializable {

    /**
     * 车辆状态 0待审核 1待发布 2己发布 3审核失败 4己出租 5己删除
     */
    private Integer status;
    /**
     * 用户id
     */
    private Integer userId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RentalCarListDTO{" +
                "status=" + status +
                ", userId=" + userId +
                '}';
    }
}
