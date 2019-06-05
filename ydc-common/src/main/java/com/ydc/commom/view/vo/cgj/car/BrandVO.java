package com.ydc.commom.view.vo.cgj.car;

import java.io.Serializable;
import java.util.Optional;

/**
 * 车辆
 *
 * @author
 * @create 2018-12-13 13:34
 **/
public class BrandVO implements Serializable {
    private static final long serialVersionUID = -850745339772122692L;

    private Integer id;
    private String brand;//品牌
    private String label;//标签
    private String icon;//图标路径

    public static void main(String[] args) {
        Optional<String> obj = Optional.ofNullable("11");
        System.out.println(obj.orElseGet(()->"22"));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
