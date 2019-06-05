package com.ydc.commom.view.dto.cgj.sys;

import com.ydc.model.annotation.Attribute;

/**
 * @author hejiangping
 * @date 2018/12/28
 */
public class HomeModuleSaveDTO {
    private Integer id;
    @Attribute(name = "子模块ID", isNum = true)
    private Integer sonModuleId;    // 子模块ID
    @Attribute(name = "子模块名称", required = true)
    private String sonModule;       // 子模块名称
    @Attribute(name = "功能名称", required = true)
    private String functionName;    // 功能名称
    private String remark;          // 备注
    @Attribute(name = "跳转链接", required = true)
    private String jumpUrl;         // 跳转链接
    @Attribute(name = "是否显示", isNum = true)
    private Integer showStatus;     // 是否显示（0关，1开）
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

    public Integer getSonModuleId() {
        return sonModuleId;
    }

    public void setSonModuleId(Integer sonModuleId) {
        this.sonModuleId = sonModuleId;
    }

    public String getSonModule() {
        return sonModule;
    }

    public void setSonModule(String sonModule) {
        this.sonModule = sonModule;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
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
