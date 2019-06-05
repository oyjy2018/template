package com.ydc.commom.enums.rental;

/**
 * 租车订单状态：4：结算
 *
 * @author
 * @create 2018-11-28 14:11
 **/
public enum RentalOrderStatusFourEnum {

    ;

    public enum RentalOrderFlowOneStatusEnum{
        FLOW_ONE_STATUS_0(0,"未结算","结算状态"),
        FLOW_ONE_STATUS_1(1,"部分结算","结算状态"),
        FLOW_ONE_STATUS_100(100,"全部结算成功","结算状态");

        RentalOrderFlowOneStatusEnum( int flowOneStatus, String flowOneStatusDescribe, String function) {
            this.flowOneStatus = flowOneStatus;
            this.flowOneStatusDescribe = flowOneStatusDescribe;
            this.function = function;
        }

        private int flowOneStatus;
        private String flowOneStatusDescribe;
        private String function;

        public int getFlowOneStatus() {
            return flowOneStatus;
        }

        public void setFlowOneStatus(int flowOneStatus) {
            this.flowOneStatus = flowOneStatus;
        }

        public String getFlowOneStatusDescribe() {
            return flowOneStatusDescribe;
        }

        public void setFlowOneStatusDescribe(String flowOneStatusDescribe) {
            this.flowOneStatusDescribe = flowOneStatusDescribe;
        }

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }
    }
}
