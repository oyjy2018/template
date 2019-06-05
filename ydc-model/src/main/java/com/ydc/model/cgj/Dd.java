package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

/**
 * 钉钉配置表
 */
public class Dd implements Serializable {

    private static final long serialVersionUID = 6581855768852645706L;
    /**
     * t_cfg_dd.id
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    private Integer id;

    /**
     * t_cfg_dd.dd_corp_id (CorpID)
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    private String ddCorpId;

    /**
     * t_cfg_dd.dd_corp_secret (CorpSecret)
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    private String ddCorpSecret;

    /**
     * t_cfg_dd.dd_sso_secret (SSOSecret)
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    private String ddSsoSecret;

    /**
     * t_cfg_dd.dd_appId (appId)
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    private String ddAppid;

    /**
     * t_cfg_dd.dd_appSecret (appSecret)
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    private String ddAppsecret;

    /**
     * t_cfg_dd.dd_agentid (钉钉企业应用id)
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    private String ddAgentid;

    /**
     * t_cfg_dd.redirect_uri (钉钉登陆回调地址)
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    private String redirectUri;

    /**
     * t_cfg_dd.service_identifying (服务标识)
     * @ibatorgenerated 2018-11-16 15:06:56
     */
    private String serviceIdentifying;

    /**
     * t_cfg_dd.status (状态（1：正常；0：禁用）)
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    private Integer status;

    /**
     * t_cfg_dd.create_time (创建时间)
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    private Date createTime;

    /**
     * t_cfg_dd.create_by (创建人)
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    private Integer createBy;

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

    public  String getServiceIdentifying() {
        return this.serviceIdentifying;
    }

    public void setServiceIdentifying(String serviceIdentifying) {
        this.serviceIdentifying = serviceIdentifying;
    }

    @Override
    public String toString() {
        return "Dd{" +
                "id=" + id +
                ", ddCorpId='" + ddCorpId + '\'' +
                ", ddCorpSecret='" + ddCorpSecret + '\'' +
                ", ddSsoSecret='" + ddSsoSecret + '\'' +
                ", ddAppid='" + ddAppid + '\'' +
                ", ddAppsecret='" + ddAppsecret + '\'' +
                ", ddAgentid='" + ddAgentid + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                '}';
    }
}