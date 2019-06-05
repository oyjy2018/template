package com.ydc.commom.view.vo.cgj.rentalEnterprise;

import java.io.Serializable;

/**
 * @author
 * @create 2019-01-07 11:54
 **/
public class QueryStatusVO implements Serializable {
    private static final long serialVersionUID = 525355626105254629L;

    private Integer status;
    private String statusCH;

    public QueryStatusVO(Integer status, String statusCH) {
        this.status = status;
        this.statusCH = statusCH;
    }

    public QueryStatusVO() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusCH() {
        return statusCH;
    }

    public void setStatusCH(String statusCH) {
        this.statusCH = statusCH;
    }
}
