package com.ydc.commom.view.vo.cgj.rental;

/**
 * 车型+车系
 */
public class RentalCarSeriesVo {

    /**
     * 车型
     */
    private String model;
    /**
     * 车系id
     */
    private String seriesId;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    @Override
    public String toString() {
        return "RentalCarSeriesVo{" +
                "model='" + model + '\'' +
                ", seriesId='" + seriesId + '\'' +
                '}';
    }
}
