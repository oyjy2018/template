package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.util.MapUtil;

import java.io.Serializable;
import java.util.Map;

public class PayRentalDepositRequestDTO implements Serializable {


    private String  operation_id; //第三方流水
    private String  auth_no; //第三方订单号
    private String  sign_type;
    private String  payee_user_id ;//收款人userid
    private String  out_order_no ; //订单号
    private String  auth_app_id ; //付款人appid
    private String  notify_type ;//回调类型
    private String  payer_user_id ;//付款人userId
    private String  version ;
    private String  amount ;//押金金额
    private String  rest_amount ; //剩余金额
    private String  notify_time ;
    private String  status ;//状态
    private String  charset ;
    private String  operation_type ;//操作类型
    private String  total_unfreeze_amount;//解冻金额
    private String  sign ;
    private String  payee_logon_id ;
    private String  gmt_create ;
    private String  total_freeze_amount ;//冻结解冻
    private String  payer_logon_id ;
    private String  out_request_no ;
    private String  app_id ;
    private String  total_pay_amount ;//支付金额
    private String  notify_id ;
    private String  gmt_trans ;

    private Byte depositType;//押金类型   1:表示租车冻结  2：表示违章冻结

    private Byte payment_mode;//支付方式 1：支付宝 ，2：信用卡  3：现金

    private Byte payment_status;//押金状态 1：未支付，2：已支付，3：已退还，9：退还失败





    public PayRentalDepositRequestDTO() {
    }

    public PayRentalDepositRequestDTO(Map<String,String[]> map) {
        this.out_order_no= MapUtil.getMapValueByKey(map,"out_order_no");//订单号
        this.out_request_no=MapUtil.getMapValueByKey(map,"out_request_no");//订单流水号
        this.payee_user_id=MapUtil.getMapValueByKey(map,"payee_user_id");//收款人id
        this.payer_user_id=MapUtil.getMapValueByKey(map,"payer_user_id");//付款人ID
        this.auth_no=MapUtil.getMapValueByKey(map,"auth_no");//支付宝资金授权订单号
        this.operation_id=MapUtil.getMapValueByKey(map,"operation_id");//支付宝资金操作流水号
        this.amount=MapUtil.getMapValueByKey(map,"amount");//冻结金额
        this.rest_amount=MapUtil.getMapValueByKey(map,"rest_amount");//剩余金额
        this.total_unfreeze_amount=MapUtil.getMapValueByKey(map,"total_unfreeze_amount");//解冻金额总额
        this.total_pay_amount=MapUtil.getMapValueByKey(map,"total_pay_amount");//支付金额总额
        this.status=MapUtil.getMapValueByKey(map,"status");//状态
        this.gmt_trans=MapUtil.getMapValueByKey(map,"gmt_trans");//授权资金冻结成功时间
        this.operation_type=MapUtil.getMapValueByKey(map,"operation_type");//操作类型  FREEZE：冻结资金
        this.sign=MapUtil.getMapValueByKey(map,"sign");
    }


    public Byte getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(Byte payment_status) {
        this.payment_status = payment_status;
    }

    public Byte getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(Byte payment_mode) {
        this.payment_mode = payment_mode;
    }

    public Byte getDepositType() {
        return depositType;
    }

    public void setDepositType(Byte depositType) {
        this.depositType = depositType;
    }

    public String getOperation_id() {
        return operation_id;
    }

    public void setOperation_id(String operation_id) {
        this.operation_id = operation_id;
    }

    public String getAuth_no() {
        return auth_no;
    }

    public void setAuth_no(String auth_no) {
        this.auth_no = auth_no;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getPayee_user_id() {
        return payee_user_id;
    }

    public void setPayee_user_id(String payee_user_id) {
        this.payee_user_id = payee_user_id;
    }

    public String getOut_order_no() {
        return out_order_no;
    }

    public void setOut_order_no(String out_order_no) {
        this.out_order_no = out_order_no;
    }

    public String getAuth_app_id() {
        return auth_app_id;
    }

    public void setAuth_app_id(String auth_app_id) {
        this.auth_app_id = auth_app_id;
    }

    public String getNotify_type() {
        return notify_type;
    }

    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }

    public String getPayer_user_id() {
        return payer_user_id;
    }

    public void setPayer_user_id(String payer_user_id) {
        this.payer_user_id = payer_user_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRest_amount() {
        return rest_amount;
    }

    public void setRest_amount(String rest_amount) {
        this.rest_amount = rest_amount;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }

    public String getTotal_unfreeze_amount() {
        return total_unfreeze_amount;
    }

    public void setTotal_unfreeze_amount(String total_unfreeze_amount) {
        this.total_unfreeze_amount = total_unfreeze_amount;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPayee_logon_id() {
        return payee_logon_id;
    }

    public void setPayee_logon_id(String payee_logon_id) {
        this.payee_logon_id = payee_logon_id;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }

    public String getTotal_freeze_amount() {
        return total_freeze_amount;
    }

    public void setTotal_freeze_amount(String total_freeze_amount) {
        this.total_freeze_amount = total_freeze_amount;
    }

    public String getPayer_logon_id() {
        return payer_logon_id;
    }

    public void setPayer_logon_id(String payer_logon_id) {
        this.payer_logon_id = payer_logon_id;
    }

    public String getOut_request_no() {
        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getTotal_pay_amount() {
        return total_pay_amount;
    }

    public void setTotal_pay_amount(String total_pay_amount) {
        this.total_pay_amount = total_pay_amount;
    }

    public String getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }

    public String getGmt_trans() {
        return gmt_trans;
    }

    public void setGmt_trans(String gmt_trans) {
        this.gmt_trans = gmt_trans;
    }

    @Override
    public String toString() {
        return "PayRentalDepositRequestDTO{" +
                "operation_id='" + operation_id + '\'' +
                ", auth_no='" + auth_no + '\'' +
                ", sign_type='" + sign_type + '\'' +
                ", payee_user_id='" + payee_user_id + '\'' +
                ", out_order_no='" + out_order_no + '\'' +
                ", auth_app_id='" + auth_app_id + '\'' +
                ", notify_type='" + notify_type + '\'' +
                ", payer_user_id='" + payer_user_id + '\'' +
                ", version='" + version + '\'' +
                ", amount='" + amount + '\'' +
                ", rest_amount='" + rest_amount + '\'' +
                ", notify_time='" + notify_time + '\'' +
                ", status='" + status + '\'' +
                ", charset='" + charset + '\'' +
                ", operation_type='" + operation_type + '\'' +
                ", total_unfreeze_amount='" + total_unfreeze_amount + '\'' +
                ", sign='" + sign + '\'' +
                ", payee_logon_id='" + payee_logon_id + '\'' +
                ", gmt_create='" + gmt_create + '\'' +
                ", total_freeze_amount='" + total_freeze_amount + '\'' +
                ", payer_logon_id='" + payer_logon_id + '\'' +
                ", out_request_no='" + out_request_no + '\'' +
                ", app_id='" + app_id + '\'' +
                ", total_pay_amount='" + total_pay_amount + '\'' +
                ", notify_id='" + notify_id + '\'' +
                ", gmt_trans='" + gmt_trans + '\'' +
                ", depositType=" + depositType +
                ", payment_mode=" + payment_mode +
                ", payment_status=" + payment_status +
                '}';
    }
}
