package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * 首页外部车辆详情查询条件
 */
public class RentalCarMainDetailQueryDTO extends Pagination implements Serializable {
    /**
     * 发布id
     */
    private Integer publishId;
    /**
     * 车等级
     */
    private String carLevel;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 车系id
     */
    private Integer seriesId;

    public Integer getPublishId() {
        return publishId;
    }

    public void setPublishId(Integer publishId) {
        this.publishId = publishId;
    }

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    @Override
    public String toString() {
        return "RentalCarMainDetailQueryDTO{" +
                "publishId=" + publishId +
                ", carLevel='" + carLevel + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", seriesId=" + seriesId +
                '}';
    }
}
