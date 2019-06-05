package com.ydc.model.ydhc;

import java.io.Serializable;
import java.util.Date;

/**
 * t_ydhc_cfg_dd
 * @author 
 */
public class YdhcDd implements Serializable {
    private Integer id;

    /**
     * CorpID
     */
    private String ddCorpId;

    /**
     * CorpSecret
     */
    private String ddCorpSecret;

    /**
     * SSOSecret
     */
    private String ddSsoSecret;

    /**
     * appId
     */
    private String ddAppid;

    /**
     * appSecret
     */
    private String ddAppsecret;

    /**
     * 钉钉企业应用id
     */
    private String ddAgentid;

    /**
     * 钉钉登陆回调地址
     */
    private String redirectUri;

    /**
     * 状态（1：正常；0：禁用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDdCorpId() {
        return ddCorpId;
    }

    public void setDdCorpId(String ddCorpId) {
        this.ddCorpId = ddCorpId;
    }

    public String getDdCorpSecret() {
        return ddCorpSecret;
    }

    public void setDdCorpSecret(String ddCorpSecret) {
        this.ddCorpSecret = ddCorpSecret;
    }

    public String getDdSsoSecret() {
        return ddSsoSecret;
    }

    public void setDdSsoSecret(String ddSsoSecret) {
        this.ddSsoSecret = ddSsoSecret;
    }

    public String getDdAppid() {
        return ddAppid;
    }

    public void setDdAppid(String ddAppid) {
        this.ddAppid = ddAppid;
    }

    public String getDdAppsecret() {
        return ddAppsecret;
    }

    public void setDdAppsecret(String ddAppsecret) {
        this.ddAppsecret = ddAppsecret;
    }

    public String getDdAgentid() {
        return ddAgentid;
    }

    public void setDdAgentid(String ddAgentid) {
        this.ddAgentid = ddAgentid;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
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