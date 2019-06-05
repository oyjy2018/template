package com.ydc.commom.view.vo.cgj.rental;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 结算流水
 *
 * @author
 * @create 2018-11-27 17:28
 **/
public class RentalPayWatercourseVO implements Serializable {
    private static final long serialVersionUID = 489426139684665123L;
    /**
     * t_rental_pay_watercourse.operation_user_name (操作人员姓名)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private String operationUserName;

    /**
     * t_rental_pay_watercourse.operation_time (操作时间)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private String operationTime;

    /**
     * t_rental_pay_watercourse.actual_amount (实付金额)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private BigDecimal actualAmount;

    public String getOperationUserName() {
        return operationUserName;
    }

    public void setOperationUserName(String operationUserName) {
        this.operationUserName = operationUserName;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }
}
