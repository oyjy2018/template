package com.ydc.commom.view.vo.cgj.rentalEnterprise.details;

import com.ydc.model.cgj.rental.RentalCheckCar;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 出车还车信息
 *
 * @author
 * @create 2019-01-05 12:19
 **/
public class PCRentalEnterpriseDetailsCarVO implements Serializable {
    private static final long serialVersionUID = 2044405008419934935L;

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 资源方出车检验图片文件名
     */
    private String resourceSideComeCarImgName;

    /**
     * 资源方出车检验图片路径
     */
    private String resourceSideComeCarImgUrl;

    private String resourceSideComeCarImgBrowse;

    /**
     * 需求方出车检验图片文件名
     */
    private String demandSideComeCarImgName;

    /**
     * 需求方出车检验图片路径
     */
    private String demandSideComeCarImgUrl;

    private String demandSideComeCarImgBrowse;

    /**
     * 资源方还车检验图片文件名
     */
    private String resourceSideRepayCarImgName;

    /**
     * 资源方还车检验图片路径
     */
    private String resourceSideRepayCarImgUrl;

    private String resourceSideRepayCarImgBrowse;

    /**
     * 需求方还车检验图片文件名
     */
    private String demandSideRepayCarImgName;

    /**
     * 需求方还车检验图片路径
     */
    private String demandSideRepayCarImgUrl;

    private String demandSideRepayCarImgBrowse;

    public static List<PCRentalEnterpriseDetailsCarVO> getCarVO(List<RentalCheckCar> rentalCheckCars){
        List<PCRentalEnterpriseDetailsCarVO> rentalEnterpriseDetailsCarVOS = Lists.newArrayList();
        if(rentalCheckCars == null || rentalCheckCars.isEmpty())return rentalEnterpriseDetailsCarVOS;
        return rentalCheckCars.stream().map(item ->{
            PCRentalEnterpriseDetailsCarVO rentalEnterpriseDetailsCarVO = new PCRentalEnterpriseDetailsCarVO();
            rentalEnterpriseDetailsCarVO.setCarNumber(item.getCarNumber());
            rentalEnterpriseDetailsCarVO.setResourceSideComeCarImgName(item.getResourceSideComeCarImgName());
            rentalEnterpriseDetailsCarVO.setResourceSideComeCarImgUrl(item.getResourceSideComeCarImgUrl());
            rentalEnterpriseDetailsCarVO.setResourceSideComeCarImgBrowse(item.getResourceSideComeCarImgBrowse());


            rentalEnterpriseDetailsCarVO.setDemandSideComeCarImgName(item.getDemandSideComeCarImgName());
            rentalEnterpriseDetailsCarVO.setDemandSideComeCarImgUrl(item.getDemandSideComeCarImgUrl());
            rentalEnterpriseDetailsCarVO.setDemandSideComeCarImgBrowse(item.getDemandSideComeCarImgBrowse());

            rentalEnterpriseDetailsCarVO.setResourceSideRepayCarImgName(item.getResourceSideRepayCarImgName());
            rentalEnterpriseDetailsCarVO.setResourceSideRepayCarImgUrl(item.getResourceSideRepayCarImgUrl());
            rentalEnterpriseDetailsCarVO.setResourceSideRepayCarImgBrowse(item.getResourceSideRepayCarImgBrowse());

            rentalEnterpriseDetailsCarVO.setDemandSideRepayCarImgName(item.getDemandSideRepayCarImgName());
            rentalEnterpriseDetailsCarVO.setDemandSideRepayCarImgUrl(item.getDemandSideRepayCarImgUrl());
            rentalEnterpriseDetailsCarVO.setDemandSideRepayCarImgBrowse(item.getDemandSideRepayCarImgBrowse());

            return rentalEnterpriseDetailsCarVO;
        }).collect(Collectors.toList());
    }


    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getResourceSideComeCarImgName() {
        return resourceSideComeCarImgName;
    }

    public void setResourceSideComeCarImgName(String resourceSideComeCarImgName) {
        this.resourceSideComeCarImgName = resourceSideComeCarImgName;
    }

    public String getResourceSideComeCarImgUrl() {
        return resourceSideComeCarImgUrl;
    }

    public void setResourceSideComeCarImgUrl(String resourceSideComeCarImgUrl) {
        this.resourceSideComeCarImgUrl = resourceSideComeCarImgUrl;
    }

    public String getResourceSideComeCarImgBrowse() {
        return resourceSideComeCarImgBrowse;
    }

    public void setResourceSideComeCarImgBrowse(String resourceSideComeCarImgBrowse) {
        this.resourceSideComeCarImgBrowse = resourceSideComeCarImgBrowse;
    }

    public String getDemandSideComeCarImgName() {
        return demandSideComeCarImgName;
    }

    public void setDemandSideComeCarImgName(String demandSideComeCarImgName) {
        this.demandSideComeCarImgName = demandSideComeCarImgName;
    }

    public String getDemandSideComeCarImgUrl() {
        return demandSideComeCarImgUrl;
    }

    public void setDemandSideComeCarImgUrl(String demandSideComeCarImgUrl) {
        this.demandSideComeCarImgUrl = demandSideComeCarImgUrl;
    }

    public String getDemandSideComeCarImgBrowse() {
        return demandSideComeCarImgBrowse;
    }

    public void setDemandSideComeCarImgBrowse(String demandSideComeCarImgBrowse) {
        this.demandSideComeCarImgBrowse = demandSideComeCarImgBrowse;
    }

    public String getResourceSideRepayCarImgName() {
        return resourceSideRepayCarImgName;
    }

    public void setResourceSideRepayCarImgName(String resourceSideRepayCarImgName) {
        this.resourceSideRepayCarImgName = resourceSideRepayCarImgName;
    }

    public String getResourceSideRepayCarImgUrl() {
        return resourceSideRepayCarImgUrl;
    }

    public void setResourceSideRepayCarImgUrl(String resourceSideRepayCarImgUrl) {
        this.resourceSideRepayCarImgUrl = resourceSideRepayCarImgUrl;
    }

    public String getResourceSideRepayCarImgBrowse() {
        return resourceSideRepayCarImgBrowse;
    }

    public void setResourceSideRepayCarImgBrowse(String resourceSideRepayCarImgBrowse) {
        this.resourceSideRepayCarImgBrowse = resourceSideRepayCarImgBrowse;
    }

    public String getDemandSideRepayCarImgName() {
        return demandSideRepayCarImgName;
    }

    public void setDemandSideRepayCarImgName(String demandSideRepayCarImgName) {
        this.demandSideRepayCarImgName = demandSideRepayCarImgName;
    }

    public String getDemandSideRepayCarImgUrl() {
        return demandSideRepayCarImgUrl;
    }

    public void setDemandSideRepayCarImgUrl(String demandSideRepayCarImgUrl) {
        this.demandSideRepayCarImgUrl = demandSideRepayCarImgUrl;
    }

    public String getDemandSideRepayCarImgBrowse() {
        return demandSideRepayCarImgBrowse;
    }

    public void setDemandSideRepayCarImgBrowse(String demandSideRepayCarImgBrowse) {
        this.demandSideRepayCarImgBrowse = demandSideRepayCarImgBrowse;
    }
}
