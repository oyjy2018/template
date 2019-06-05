package com.ydc.commom.view.vo.cgj.rental;

/**
 * 外部车辆列表查询条件
 */
public class RentalCarConditionMiddleVO {

    /**
     * 所属企业名称
     */
    private String companyName;
    /**
     * 品牌
     */
    private String brand;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "RentalCarConditionMiddleVO{" +
                ", companyName='" + companyName + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
