package com.ydc.commom.view.vo.cgj.order;

import java.io.Serializable;

/**
 * 订单物流
 *
 * @author gongjin
 * @create 2018-09-18 15:47
 **/
public class OrderLogisticsVO implements Serializable {
    private static final long serialVersionUID = 4864187722539007057L;

    private String logisticsCompanyName;    //快递名称
    private String logisticsCompanyCode;    //快递code
    private String logisticsOrder;  //快递单号
    private String logisticsUrl;    //快递请求地址

    public String getLogisticsCompanyName() {
        return logisticsCompanyName;
    }

    public void setLogisticsCompanyName(String logisticsCompanyName) {
        this.logisticsCompanyName = logisticsCompanyName;
    }

    public String getLogisticsCompanyCode() {
        return logisticsCompanyCode;
    }

    public void setLogisticsCompanyCode(String logisticsCompanyCode) {
        this.logisticsCompanyCode = logisticsCompanyCode;
    }

    public String getLogisticsOrder() {
        return logisticsOrder;
    }

    public void setLogisticsOrder(String logisticsOrder) {
        this.logisticsOrder = logisticsOrder;
    }

    public String getLogisticsUrl() {
        return logisticsUrl;
    }

    public void setLogisticsUrl(String logisticsUrl) {
        this.logisticsUrl = logisticsUrl;
    }
}
