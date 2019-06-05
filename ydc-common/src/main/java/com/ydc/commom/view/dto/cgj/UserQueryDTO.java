package com.ydc.commom.view.dto.cgj;

import com.ydc.model.cgj.Pagination;
import org.springframework.util.StringUtils;

import java.io.Serializable;
public class UserQueryDTO extends Pagination implements Serializable {

    private String userName;//用户名（姓名）

    private String jobName;//岗位名

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

}
