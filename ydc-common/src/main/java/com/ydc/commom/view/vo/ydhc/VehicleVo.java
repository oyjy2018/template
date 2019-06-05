package com.ydc.commom.view.vo.ydhc;

import com.ydc.model.cgj.Page;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 车辆查询类
 */
public class VehicleVo {

    /**
     * 标题
     */
    private String title;

    /**
     * 最低价
     */
    private Double minPrice;

    /**
     * 最高价
     */
    private Double maxPrice;

    /**
     * 车源类型
     */
    private String carSourceCode;

    /**
     * 车辆品牌
     */
    private Integer brandCode;

    /**
     * 车系
     */
    private Integer carType;

    /**
     * 价格排序（0：不排序；1：升序；2：降序）
     */
    private Integer isPriceSort;

    /**
     * 分页
     */
    private Page page;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getCarSourceCode() {
        return carSourceCode;
    }

    public void setCarSourceCode(String carSourceCode) {
        this.carSourceCode = carSourceCode;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public Integer getIsPriceSort() {
        return isPriceSort;
    }

    public void setIsPriceSort(Integer isPriceSort) {
        this.isPriceSort = isPriceSort;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Integer getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(Integer brandCode) {
        this.brandCode = brandCode;
    }

    public VehicleVo changePage(){
        if (page != null){
            page = page.changePage();
        }
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
