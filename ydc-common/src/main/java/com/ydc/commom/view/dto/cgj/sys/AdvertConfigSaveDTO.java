package com.ydc.commom.view.dto.cgj.sys;

import com.ydc.model.annotation.Attribute;

/**
 * @author hejiangping
 * @date 2019/1/2
 */
public class AdvertConfigSaveDTO {
    private Integer id;
    @Attribute(name = "客户端", required = true)
    private String clientDictKey;   // 客户端
    @Attribute(name = "功能模块", required = true)
    private String moduleDictKey;   // 功能模块
    @Attribute(name = "广告投放链接", required = true)
    private String jumpUrl;         // 广告投放链接
    @Attribute(name = "活动标题", required = true)
    private String title;           // 活动标题
    private String remark;          // 活动描述
    @Attribute(name = "是否启用", required = true)
    private Integer switchStatus;   // 是否启用（0关，1开）
    private Integer sort;           // 排序
    private String fileName;        // 文件名
    private String fileUrl;         // 文件路径
    private String littleFileName;  // 缩略图文件名
    private String littleFileUrl;   // 缩略图文件路径
    private String fileType;        // 文件类型
    private Integer userId;         // 用户ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientDictKey() {
        return clientDictKey;
    }

    public void setClientDictKey(String clientDictKey) {
        this.clientDictKey = clientDictKey;
    }

    public String getModuleDictKey() {
        return moduleDictKey;
    }

    public void setModuleDictKey(String moduleDictKey) {
        this.moduleDictKey = moduleDictKey;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(Integer switchStatus) {
        this.switchStatus = switchStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
