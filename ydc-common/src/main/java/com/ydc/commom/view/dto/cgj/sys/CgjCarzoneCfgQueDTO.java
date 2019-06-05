package com.ydc.commom.view.dto.cgj.sys;

import com.ydc.model.cgj.Pagination;

import java.io.Serializable;
import java.util.Date;

/**
 * 车圈配置
 * 
 * @author songzhipeng
 * 
 * @date 2018-12-29
 */
public class CgjCarzoneCfgQueDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = -1670751555425861832L;
    private Integer id;

    /**
     * 文章所属类型key
     */
    private String dictKey;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否推荐  0- 否   1- 是
     */
    private Integer recommend;

    /**
     * 文章跳转链接
     */
    private String articleUrl;

    /**
     * 是否显示  0- 否  1- 是
     */
    private Integer show;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 修改人
     */
    private Integer updateBy;

    /**
     * 有效状态（0无效 1有效）
     */
    private Integer status;

    public CgjCarzoneCfgQueDTO(Integer id, String dictKey, String title, Date createTime, Date updateTime, Integer recommend, String articleUrl, Integer show, Integer createBy, Integer updateBy , Integer status) {
        this.id = id;
        this.dictKey = dictKey;
        this.title = title;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.recommend = recommend;
        this.articleUrl = articleUrl;
        this.show = show;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.status = status;
    }

    public CgjCarzoneCfgQueDTO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey == null ? null : dictKey.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl == null ? null : articleUrl.trim();
    }

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}