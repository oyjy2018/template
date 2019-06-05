package com.ydc.commom.view.vo.cgj.rentalEnterprise;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.enums.rental.RentalSettlementEnum;
import com.ydc.commom.util.JsonUtil;
import com.ydc.model.util.DateUtil;
import org.assertj.core.util.Maps;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @author
 * @create 2019-01-04 14:34
 **/
public class PCRentalEnterpriseOrderVO extends StoreRentalEnterpriseOrderVO implements Serializable {
    private static final long serialVersionUID = 3408744901251630630L;

    /**
     * 预约取车时间
     */
    private Date appointmentFetchCarTimePC;

    /**
     * 预约还车时间
     */
    private Date appointmentRepayCarTimePC;

    /**
     * 保证金状态
     */
    private Integer paymentStatus;

    /**
     * 保证金状态
     */
    private String paymentStatusName;

    private Integer paymentMode;//支付方式 1：芝麻 2：信用卡 3：现金

    private String paymentModeName;


    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("paymentMode",RentalSettlementEnum.STATUS2.getSettleWay());
        jsonObject.put("paymentModeName",RentalSettlementEnum.STATUS2.getSettleWayCH());
        jsonArray.add(jsonObject);
        jsonObject = new JSONObject();
        jsonObject.put("paymentMode",RentalSettlementEnum.STATUS3.getSettleWay());
        jsonObject.put("paymentModeName",RentalSettlementEnum.STATUS3.getSettleWayCH());
        jsonArray.add(jsonObject);
        System.out.println(JsonUtil.gsonStr(jsonArray));
        System.out.println(JsonUtil.gsonStr(Arrays.asList(Maps.newHashMap(RentalSettlementEnum.STATUS2.getSettleWay(),RentalSettlementEnum.STATUS2.getSettleWayCH()),
                Maps.newHashMap(RentalSettlementEnum.STATUS3.getSettleWay(),RentalSettlementEnum.STATUS3.getSettleWayCH()))));
    }

    @JSONField(format = DateUtil.DATAFORMAT_STR)
    public Date getAppointmentFetchCarTimePC() {
        return appointmentFetchCarTimePC;
    }

    public void setAppointmentFetchCarTimePC(Date appointmentFetchCarTimePC) {
        this.appointmentFetchCarTimePC = appointmentFetchCarTimePC;
    }
    @JSONField(format = DateUtil.DATAFORMAT_STR)
    public Date getAppointmentRepayCarTimePC() {
        return appointmentRepayCarTimePC;
    }

    public void setAppointmentRepayCarTimePC(Date appointmentRepayCarTimePC) {
        this.appointmentRepayCarTimePC = appointmentRepayCarTimePC;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatusName() {
        return RentalDepositEnum.PaymentStatus.getPaymentStatusCH(this.paymentStatus);
    }

    public void setPaymentStatusName(String paymentStatusName) {
        this.paymentStatusName = paymentStatusName;
    }

    public Integer getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Integer paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentModeName() {
        return RentalSettlementEnum.getRentalSettlementEnum(this.paymentMode);
    }

    public void setPaymentModeName(String paymentModeName) {
        this.paymentModeName = paymentModeName;
    }
}
