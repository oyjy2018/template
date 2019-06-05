package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

/**
 * t_cfg_file
 * @author 
 */
public class CfgFile implements Serializable {
    private Integer id;

    /**
     * 文件目录名
     */
    private String fileDirName;

    /**
     * 文件编码
     */
    private String fileCode;

    /**
     * 父文件编码
     */
    private String parentFileCode;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 层级
     */
    private Byte hierarchy;

    /**
     * 是否展示图片（0 否  1 是）
     */
    private Integer isDisplayImage;

    /**
     * 示例图数量
     */
    private Integer exampleNumber;

    /**
     * 指定标准照片数量
     */
    private Integer numbers;

    /**
     * 状态（1：正常；0：禁用）
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private Integer updateBy;

    private static final long serialVersionUID = 1L;

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

    public Integer getIsDisplayImage() {
        return isDisplayImage;
    }

    public void setIsDisplayImage(Integer isDisplayImage) {
        this.isDisplayImage = isDisplayImage;
    }

    public Integer getExampleNumber() {
        return exampleNumber;
    }

    public void setExampleNumber(Integer exampleNumber) {
        this.exampleNumber = exampleNumber;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
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