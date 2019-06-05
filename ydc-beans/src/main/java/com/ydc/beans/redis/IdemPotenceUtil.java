package com.ydc.beans.redis;

/**
 * 防止重复提交
 *
 * @author
 * @create 2018-12-03 9:50
 **/
public class IdemPotenceUtil {


    /**
     * 防重复提交
     * @param key
     * @param obj
     * @param time
     * @return
     */
    public static boolean preventRepetitionSubmit(String key,String obj,long time){
        if(RedisUtil.exists(key+obj)){
            return true;
        }
        RedisUtil.redisSet(key+obj,obj,time);
        return false;
    }

    /**
     * 防止重复提交
     */
    public enum IdemPotenceEnmu{
        ADD_RENTAL_ORDER("ADD:RENTAL:ORDER:","新增租车订单"),
        COME_CAR("COME:CAR:","出车信息提交"),
        REPAY_CAR("REPAY:CAR:","还车信息提交"),
        FIRST_SETTLEMENT("FIRST:SETTLEMENT:","首次结算"),
        NEXT_SETTLEMENT("NEXT:SETTLEMENT:","非首次结算"),
        RENTAL_ENTERPRISE_ORDER_ADD("RENTAL:ENTERPRISE:ORDER:ADD:","新增企业租车订单"),
        RENTAL_ENTERPRISE_ORDER_CANCEL("RENTAL:ENTERPRISE:ORDER:CANCEL:","外部订单:取消订单"),
        RENTAL_ENTERPRISE_ORDER_REJECT("RENTAL:ENTERPRISE:ORDER:REJECT:","外部订单:拒绝订单"),
        RENTAL_ENTERPRISE_ORDER_NOTARIZE("RENTAL:ENTERPRISE:ORDER:NOTARIZE:","外部订单:确认订单"),
        RENTAL_ENTERPRISE_ORDER_PAYMENT_DEPOSIT("RENTAL:ENTERPRISE:ORDER:PAYMENT:DEPOSIT:","企业租车后台列表:已交保证金"),
        RENTAL_ENTERPRISE_ORDER_REFUND_DEPOSIT("RENTAL:ENTERPRISE:ORDER:REFUND:DEPOSIT:","企业租车后台列表:保证金已退还"),
        RENTAL_ENTERPRISE_ORDER_RENT_PAYMENT("RENTAL:ENTERPRISE:ORDER:RENT:PAYMENT:","企业租车后台列表:录入租金支付信息"),
        RENTAL_ENTERPRISE_ORDER_COME_CAR("RENTAL:ENTERPRISE:ORDER:COME:CAR:","企业租车后台列表:录入出车信息"),
        RENTAL_ENTERPRISE_ORDER_RENT_TRANSFER("RENTAL:ENTERPRISE:ORDER:RENT:TRANSFER:","企业租车后台列表:录入租金转账信息"),
        RENTAL_ENTERPRISE_ORDER_REPAY_CAR("RENTAL:ENTERPRISE:ORDER:REPAY:CAR:","企业租车后台列表:录入还车信息"),
        RENTAL_ENTERPRISE_ORDER_SETTLEMENT("RENTAL:ENTERPRISE:ORDER:SETTLEMENT:","企业租车后台列表:录入结算信息"),
        RENTAL_ENTERPRISE_ORDER_DEPOSIT_REFUND("RENTAL:ENTERPRISE:ORDER:DEPOSIT:REFUND:","企业租车后台列表:录入结算信息");
        private String prefix;
        private String desc;

        IdemPotenceEnmu(String prefix, String desc) {
            this.prefix = prefix;
            this.desc = desc;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
