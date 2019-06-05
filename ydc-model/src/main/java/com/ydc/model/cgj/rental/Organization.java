package com.ydc.model.cgj.rental;


import java.io.Serializable;
import java.util.Date;

public class Organization implements Serializable {
    private static final long serialVersionUID = -4091918271100026879L;
    /**
     * t_cfg_organization.id
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private Integer id;

    /**
     * t_cfg_organization.org_name (机构名（营业执照）)
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private String orgName;

    /**
     * t_cfg_organization.org_short_name (机构简称)
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private String orgShortName;

    /**
     * t_cfg_organization.org_number (机构编码)
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private String orgNumber;

    /**
     * t_cfg_organization.hierarchy (层级)
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private Byte hierarchy;

    /**
     * t_cfg_organization.org_address_province (机构地址省)
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private String orgAddressProvince;

    /**
     * t_cfg_organization.org_address_city (机构地址市)
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private String orgAddressCity;

    /**
     * t_cfg_organization.org_address_region (机构地址区)
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private String orgAddressRegion;

    /**
     * t_cfg_organization.org_address (机构地址)
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private String orgAddress;

    /**
     * t_cfg_organization.org_eamil (邮箱)
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private String orgEamil;

    /**
     * t_cfg_organization.status (状态（1：正常；0：禁用）)
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private String status;

    /**
     * t_cfg_organization.sort (排序)
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private Integer sort;

    /**
     * t_cfg_organization.create_time (创建时间)
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private Date createTime;

    /**
     * t_cfg_organization.create_by (创建人)
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private Integer createBy;

    /**
     * t_cfg_organization.update_time (修改时间)
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private Date updateTime;

    /**
     * t_cfg_organization.update_by (修改人)
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgShortName() {
        return orgShortName;
    }

    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName;
    }

    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }

    public Byte getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Byte hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getOrgAddressProvince() {
        return orgAddressProvince;
    }

    public void setOrgAddressProvince(String orgAddressProvince) {
        this.orgAddressProvince = orgAddressProvince;
    }

    public String getOrgAddressCity() {
        return orgAddressCity;
    }

    public void setOrgAddressCity(String orgAddressCity) {
        this.orgAddressCity = orgAddressCity;
    }

    public String getOrgAddressRegion() {
        return orgAddressRegion;
    }

    public void setOrgAddressRegion(String orgAddressRegion) {
        this.orgAddressRegion = orgAddressRegion;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgEamil() {
        return orgEamil;
    }

    public void setOrgEamil(String orgEamil) {
        this.orgEamil = orgEamil;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

}