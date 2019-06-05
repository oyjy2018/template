package com.ydc.commom.view.vo.cgj;

import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.MemberFile;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * 客户信息表
 */
public class MemberApplicationVO extends Member implements Serializable {
    private static final long serialVersionUID = -5774210985647962880L;

    /**
     * t_member_application.member_login_name (客户登录名)
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    private String memberLoginName;

    /**
     *客户状态
     */
    private Integer  memberStatus;

    /**
     * t_member_application.application (应用系统（1：车管家；2：租车）)
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    private Integer application;

    /**
     * 驾驶证姓名
     */
    private String driversLicenseName;

    /**
     * 客户文件信息
     */
    private List<MemberFile> fileList;

    /**
     * 认证有效期String
     */
    private String identityValidTimeStr;

    public String getMemberLoginName() {
        return memberLoginName;
    }

    public void setMemberLoginName(String memberLoginName) {
        this.memberLoginName = memberLoginName;
    }

    public Integer getApplication() {
        return application;
    }

    public void setApplication(Integer application) {
        this.application = application;
    }

    /**客户状态（1：正常；2：注销；3：锁定）**/
    public Integer getMemberStatus() {
        return memberStatus;
    }

    /**客户状态（1：正常；2：注销；3：锁定）**/
    public void setMemberStatus(Integer memberStatus) {
        this.memberStatus = memberStatus;
    }

    public List<MemberFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<MemberFile> fileList) {
        this.fileList = fileList;
    }

    public String getDriversLicenseName() {
        return driversLicenseName;
    }

    public void setDriversLicenseName(String driversLicenseName) {
        this.driversLicenseName = driversLicenseName;
    }

    public String getIdentityValidTimeStr() {
        return identityValidTimeStr;
    }

    public void setIdentityValidTimeStr(String identityValidTimeStr) {
        this.identityValidTimeStr = identityValidTimeStr;
    }

    /**
     * 为了使用shiro-redis
     * @return
     */
    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
