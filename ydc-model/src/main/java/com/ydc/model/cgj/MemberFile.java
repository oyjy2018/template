package com.ydc.model.cgj;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

public class MemberFile implements Serializable {
    private static final long serialVersionUID = -368153213123308472L;
    /**
     * t_member_file.id (id)
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    private Integer id;

    /**
     * t_member_file.member_id (会员id)
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    private Integer memberId;

    /**
     * t_member_file.file_name (文件名)
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    private String fileName;

    /**
     * t_member_file.file_url (文件路径)
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    private String fileUrl;

    /**
     * t_member_file.file_type (文件类型)
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    private String fileType;

    /**
     * t_member_file.type (类别（1：身份证头像面；2：身份证国徽面；3：驾驶证正面；4：驾驶证反面）)
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    private Integer type;

    /**
     * t_member_file.status (有效状态（0：无效；1：有效）)
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    private Integer status;

    /**
     * t_member_file.create_time (创建时间)
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    private Date createTime;

    /**
     * t_member_file.create_by (创建人)
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    private Integer createBy;

    /**
     * t_member_file.update_time (更新时间)
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    private Date updateTime;

    /**
     * t_member_file.update_by (更新人)
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    private Integer updateBy;

    /**
     * 前端可见url
     *
     */
    private String viewFileUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getViewFileUrl() {
        return viewFileUrl;
    }

    public void setViewFileUrl(String viewFileUrl) {
        this.viewFileUrl = viewFileUrl;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}