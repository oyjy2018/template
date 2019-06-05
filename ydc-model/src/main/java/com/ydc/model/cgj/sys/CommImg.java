package com.ydc.model.cgj.sys;

import java.io.Serializable;
import java.util.Date;

public class CommImg implements Serializable {
    /**
     * t_cgj_comm_img.id
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    private Integer id;

    /**
     * t_cgj_comm_img.comm_id (关联ID)
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    private Integer commId;

    /**
     * t_cgj_comm_img.img_type (图片类型（1：首页配置；2：服务配置；3：车圈配置；4：广告配置）)
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    private Integer imgType;

    /**
     * t_cgj_comm_img.file_name (文件名)
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    private String fileName;

    /**
     * t_cgj_comm_img.file_url (文件路径)
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    private String fileUrl;

    /**
     * t_cgj_comm_img.little_file_name (缩略图文件名)
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    private String littleFileName;

    /**
     * t_cgj_comm_img.little_file_url (缩略图文件路径)
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    private String littleFileUrl;

    /**
     * t_cgj_comm_img.file_type (文件类型)
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    private String fileType;

    /**
     * t_cgj_comm_img.status (有效状态（0：无效；1：有效）)
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    private Integer status;

    /**
     * t_cgj_comm_img.create_time (创建时间)
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    private Date createTime;

    /**
     * t_cgj_comm_img.create_by (创建人)
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    private Integer createBy;

    /**
     * t_cgj_comm_img.update_time (修改时间)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private Date updateTime;

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

    /**
     * t_cgj_comm_img.update_by (修改人)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommId() {
        return commId;
    }

    public void setCommId(Integer commId) {
        this.commId = commId;
    }

    public Integer getImgType() {
        return imgType;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getLittleFileName() {
        return littleFileName;
    }

    public void setLittleFileName(String littleFileName) {
        this.littleFileName = littleFileName;
    }

    public String getLittleFileUrl() {
        return littleFileUrl;
    }

    public void setLittleFileUrl(String littleFileUrl) {
        this.littleFileUrl = littleFileUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
}