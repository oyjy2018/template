package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典表
 */
public class Dictionary implements Serializable {
    private static final long serialVersionUID = 6206902381427350791L;
    /**
     * t_cfg_dictionary.id
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer id;

    /**
     * t_cfg_dictionary.dict_name (字典名称)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String dictName;

    /**
     * t_cfg_dictionary.dict_code (字典编号)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String dictCode;

    /**
     * t_cfg_dictionary.remark (备注)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String remark;

    /**
     * t_cfg_dictionary.status (状态（1：正常；0：禁用）)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String status;

    /**
     * t_cfg_dictionary.sort (排序)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer sort;

    /**
     * t_cfg_dictionary.create_time (创建时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Date createTime;

    /**
     * t_cfg_dictionary.create_by (创建人)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer createBy;

    /**
     * t_cfg_dictionary.update_time (修改时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Date updateTime;

    /**
     * t_cfg_dictionary.update_by (修改人)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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