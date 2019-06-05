package com.ydc.commom.view.vo.cgj.rental;

import java.util.List;
import java.util.Map;

/**
 * 外部车辆列表查询条件
 */
public class RentalCarConditionVO {

    /**
     * 所属企业列表
     */
    private List<String> companyNameList;
    /**
     * 品牌列表
     */
    private List<String> brandList;

    public List<String> getCompanyNameList() {
        return companyNameList;
    }

    public void setCompanyNameList(List<String> companyNameList) {
        this.companyNameList = companyNameList;
    }

    public List<String> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<String> brandList) {
        this.brandList = brandList;
    }

    @Override
    public String toString() {
        return "RentalCarConditionVO{" +
                "companyNameList=" + companyNameList +
                ", brandList=" + brandList +
                '}';
    }
}
