package com.ydc.commom.view.dto.cgj.sys;

import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * @author hejiangping
 * @date 2019/1/2
 */
public class AdvertConfigQueDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String moduleDictKey;   // 功能模块Key
    private Integer switchStatus;     // 是否启用（0关，1开）
    private String clientDictKey;      // 客户端key
    private Integer userId;         // 用户ID
    private String version;      // 版本号

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getModuleDictKey() {
        return moduleDictKey;
    }

    public void setModuleDictKey(String moduleDictKey) {
        this.moduleDictKey = moduleDictKey;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(Integer switchStatus) {
        this.switchStatus = switchStatus;
    }

    public String getClientDictKey() {
        return clientDictKey;
    }

    public void setClientDictKey(String clientDictKey) {
        this.clientDictKey = clientDictKey;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
