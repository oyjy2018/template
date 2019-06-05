package com.ydc.commom.enums.rental;

import java.util.Objects;

public enum RentalDepositEnum {
    ;

    /**
     * 押金状态（授权状态）
     */
    public enum PaymentStatus{

        status1(1,"未授权","待支付", "待支付","未支付")
        ,status2(2,"待退还","待退还", "待退还","已支付")
        ,status3(3,"已退还","已退还", "已退还","已退还")
        ,status4(4,"处理中","处理中", "处理中","处理中")
        ,status9(9,"退还失败","退还失败", "退还失败","退还失败")
        ;

        Integer status;
        String statusCH;
        String statusCHEnterprise;
        String statucCHDemand;
        String dbPaymentStatus;

        PaymentStatus(Integer status, String statusCH, String statusCHEnterprise, String statucCHDemand,String dbPaymentStatus) {
            this.status = status;
            this.statusCH = statusCH;
            this.statusCHEnterprise = statusCHEnterprise;
            this.statucCHDemand = statucCHDemand;
            this.dbPaymentStatus = dbPaymentStatus;
        }

        public static PaymentStatus getPaymentStatusByStatus(Integer status){
            for(PaymentStatus ps: PaymentStatus.values()){
                if(Objects.equals(ps.status, status)) return ps;
            }
            return null;
        }

        public static String getPaymentStatusCH(Integer status){
            PaymentStatus paymentStatus = getPaymentStatusByStatus(status);
            return paymentStatus == null ? "" : paymentStatus.statusCHEnterprise;
        }

        public static String getPaymentStatusCHOfNull(Integer status){
            PaymentStatus paymentStatus = getPaymentStatusByStatus(status);
            return paymentStatus == null ? null : paymentStatus.statusCHEnterprise;
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

        public String getStatusCHEnterprise() {
            return statusCHEnterprise;
        }

        public void setStatusCHEnterprise(String statusCHEnterprise) {
            this.statusCHEnterprise = statusCHEnterprise;
        }

        public String getStatucCHDemand() {
            return statucCHDemand;
        }

        public void setStatucCHDemand(String statucCHDemand) {
            this.statucCHDemand = statucCHDemand;
        }

        public String getDbPaymentStatus() {
            return dbPaymentStatus;
        }

        public void setDbPaymentStatus(String dbPaymentStatus) {
            this.dbPaymentStatus = dbPaymentStatus;
        }
    }


    public enum OrderType{

        STATUS1(1, "普通订单")
        ,STATUS2(2, "企业订单")
        ;

        Integer status;
        String statusCH;

        private OrderType(Integer status, String statusCH){
            this.status = status;
            this.statusCH = statusCH;
        }

        public static OrderType getOrderTypeByStatus(Integer status){
            if(status == null) return null;
            for(OrderType ot:OrderType.values()){
                if(ot.status.intValue() == status.intValue()) return ot;
            }
            return null;
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

    public enum DepositTypeEnum{
        DEPOSIT_TYPE_1(1,"租车冻结"),
        DEPOSIT_TYPE_2(2,"违章冻结"),
        DEPOSIT_TYPE_3(3,"租车结算"),
        DEPOSIT_TYPE_4(4,"违章结算"),;

        DepositTypeEnum(Integer depositType, String depositTypeName) {
            this.depositType = depositType;
            this.depositTypeName = depositTypeName;
        }

        private Integer depositType;
        private String depositTypeName;

        public Integer getDepositType() {
            return depositType;
        }

        public void setDepositType(Integer depositType) {
            this.depositType = depositType;
        }

        public String getDepositTypeName() {
            return depositTypeName;
        }

        public void setDepositTypeName(String depositTypeName) {
            this.depositTypeName = depositTypeName;
        }
    }
}
