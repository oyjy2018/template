package com.ydc.commom.view.dto.cgj.sys;

import com.ydc.model.annotation.Attribute;

/**
 * @author hejiangping
 * @date 2019/1/12
 */
public class CgjAppVersionQueDTO {
    private Integer id;
    private String platform;    // 客户端平台
    private String version;     // 版本号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
