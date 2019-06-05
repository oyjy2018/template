package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典明细表
 */
public class DictionaryDetail implements Serializable {
    private static final long serialVersionUID = -6406237348267337147L;
    /**
     * t_cfg_dictionary_detail.id
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer id;

    /**
     * t_cfg_dictionary_detail.dict_key (字典key)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String dictKey;

    /**
     * t_cfg_dictionary_detail.dict_value (字典value)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String dictValue;

    /**
     * t_cfg_dictionary_detail.parent_dict_code (父字典编号)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String parentDictCode;

    /**
     * t_cfg_dictionary_detail.remark1 (备注1)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String remark1;

    /**
     * t_cfg_dictionary_detail.remark2 (备注2)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String remark2;

    /**
     * t_cfg_dictionary_detail.remark3 (备注3)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String remark3;

    /**
     * t_cfg_dictionary_detail.remark4 (备注4)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String remark4;

    /**
     * t_cfg_dictionary_detail.remark5 (备注5)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String remark5;

    /**
     * t_cfg_dictionary_detail.remark6 (备注6)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String remark6;

    /**
     * t_cfg_dictionary_detail.remark7 (备注7)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String remark7;

    /**
     * t_cfg_dictionary_detail.remark8 (备注8)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String remark8;

    /**
     * t_cfg_dictionary_detail.remark9 (备注9)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String remark9;

    /**
     * t_cfg_dictionary_detail.remark10 (备注10)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String remark10;

    /**
     * t_cfg_dictionary_detail.status (状态（1：正常；0：禁用）)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String status;

    /**
     * t_cfg_dictionary_detail.sort (排序)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer sort;

    /**
     * t_cfg_dictionary_detail.create_time (创建时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Date createTime;

    /**
     * t_cfg_dictionary_detail.create_by (创建人)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer createBy;

    /**
     * t_cfg_dictionary_detail.update_time (修改时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Date updateTime;

    /**
     * t_cfg_dictionary_detail.update_by (修改人)
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

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getParentDictCode() {
        return parentDictCode;
    }

    public void setParentDictCode(String parentDictCode) {
        this.parentDictCode = parentDictCode;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

    public String getRemark5() {
        return remark5;
    }

    public void setRemark5(String remark5) {
        this.remark5 = remark5;
    }

    public String getRemark6() {
        return remark6;
    }

    public void setRemark6(String remark6) {
        this.remark6 = remark6;
    }

    public String getRemark7() {
        return remark7;
    }

    public void setRemark7(String remark7) {
        this.remark7 = remark7;
    }

    public String getRemark8() {
        return remark8;
    }

    public void setRemark8(String remark8) {
        this.remark8 = remark8;
    }

    public String getRemark9() {
        return remark9;
    }

    public void setRemark9(String remark9) {
        this.remark9 = remark9;
    }

    public String getRemark10() {
        return remark10;
    }

    public void setRemark10(String remark10) {
        this.remark10 = remark10;
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