package com.ydc.commom.view.vo.cgj.sys;


import java.io.Serializable;
import java.util.Date;

/**
 * 车圈配置
 * 
 * @author wcyong
 * 
 * @date 2018-12-29
 */
public class CgjCarzoneCfgVO implements Serializable{
    private static final long serialVersionUID = -6864969031776515162L;
    private Integer id;

    /**
     * 模块
     */
    private String dictName;

    /**
     * 文章类别
     */
    private String dictValue;
    /**
     * 文章类别
     */
    private String remark3;

    /**
     * 文章标题
     */
    private String title;
    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 是否推荐  0- 否   1- 是
     */
    private Integer recommend;

    /**
     * 文章跳转链接
     */
    private String articleUrl;

    /**
     * t_cgj_comm_img.little_file_url (缩略图文件路径)
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    private String littleFileUrl;

    /**
     * 是否显示  0- 否  1- 是
     */
    private Integer show;

    /**
     * 图片id
     */
    private Integer imgId;


    /**
     * t_cgj_comm_img.file_url (文件路径)
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    private String fileUrl;

    public CgjCarzoneCfgVO(Integer id, String dictName, String dictValue, String title, Date createTime, Integer recommend, String articleUrl, Integer show, String fileUrl , String remark3,String littleFileUrl ,Integer imgId) {
        this.id = id;
        this.dictName = dictName;
        this.dictValue = dictValue;
        this.title = title;
        this.createTime = createTime;
        this.recommend = recommend;
        this.articleUrl = articleUrl;
        this.show = show;
        this.fileUrl = fileUrl;
        this.remark3 = remark3;
        this.littleFileUrl = littleFileUrl;
        this.imgId = imgId;
    }

    public CgjCarzoneCfgVO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        this.articleUrl = articleUrl;
    }

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getLittleFileUrl() {
        return littleFileUrl;
    }

    public void setLittleFileUrl(String littleFileUrl) {
        this.littleFileUrl = littleFileUrl;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }
}