package com.ydc.commom.view.dto.cgj.sys;

import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * 首页配置
 * @author hejiangping
 * @date 2018/12/27
 */
public class HomeModuleQueDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String sonModuleId;   // 模块ID
    private Integer showStatus;     // 是否显示（0关，1开）
    private Integer userId;         // 用户ID

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSonModuleId() {
        return sonModuleId;
    }

    public void setSonModuleId(String sonModuleId) {
        this.sonModuleId = sonModuleId;
    }
}
