package com.ydc.commom.view.vo.cgj.car;

import java.io.Serializable;

/**
 * 车型
 *
 * @author
 * @create 2018-12-13 13:38
 **/
public class ModelVO implements Serializable {
    private static final long serialVersionUID = 4075373054920945814L;

    private Integer id;
    private String model;//车版本（车型）
    private String series;//所属车系
    private String type;//款型


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
