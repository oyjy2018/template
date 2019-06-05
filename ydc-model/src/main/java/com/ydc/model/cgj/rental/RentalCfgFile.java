package com.ydc.model.cgj.rental;

import java.io.Serializable;
import java.util.Date;

public class RentalCfgFile implements Serializable {
    private static final long serialVersionUID = -6198736626426039197L;
    /**
     * t_rental_cfg_file.id
     * @ibatorgenerated 2018-11-24 08:52:01
     */
    private Integer id;

    /**
     * t_rental_cfg_file.file_dir_name (文件目录名)
     * @ibatorgenerated 2018-11-24 08:52:01
     */
    private String fileDirName;

    /**
     * t_rental_cfg_file.file_code (文件编码)
     * @ibatorgenerated 2018-11-24 08:52:01
     */
    private String fileCode;

    /**
     * t_rental_cfg_file.parent_file_code (父文件编码)
     * @ibatorgenerated 2018-11-24 08:52:01
     */
    private String parentFileCode;

    /**
     * t_rental_cfg_file.sort (排序)
     * @ibatorgenerated 2018-11-24 08:52:01
     */
    private Integer sort;

    /**
     * t_rental_cfg_file.hierarchy (层级)
     * @ibatorgenerated 2018-11-24 08:52:01
     */
    private Byte hierarchy;

    /**
     * t_rental_cfg_file.status (状态（1：正常；0：禁用）)
     * @ibatorgenerated 2018-11-24 08:52:01
     */
    private String status;

    /**
     * t_rental_cfg_file.create_time (创建时间)
     * @ibatorgenerated 2018-11-24 08:52:01
     */
    private Date createTime;

    /**
     * t_rental_cfg_file.create_by (创建人)
     * @ibatorgenerated 2018-11-24 08:52:01
     */
    private Integer createBy;

    /**
     * t_rental_cfg_file.update_time (修改时间)
     * @ibatorgenerated 2018-11-24 08:52:01
     */
    private Date updateTime;

    /**
     * t_rental_cfg_file.update_by (修改人)
     * @ibatorgenerated 2018-11-24 08:52:01
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileDirName() {
        return fileDirName;
    }

    public void setFileDirName(String fileDirName) {
        this.fileDirName = fileDirName;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getParentFileCode() {
        return parentFileCode;
    }

    public void setParentFileCode(String parentFileCode) {
        this.parentFileCode = parentFileCode;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Byte getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Byte hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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