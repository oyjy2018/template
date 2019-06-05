package com.ydc.commom.enums.rental;

import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;



/**
 * 租车订单状态：1：备车
 *
 * @author
 * @create 2018-11-24 17:57
 **/
public enum RentalOrderStatusOneEnum {
    ;



    public enum RentalOrderFlowOneStatusEnum{
        FLOW_ONE_STATUS_0(0,"身份认未认证","身份认证和风控认证"),
        FLOW_ONE_STATUS_9(9,"身份认证失败","身份认证和风控认证"),
        FLOW_ONE_STATUS_10(10,"身份证成功","身份认证和风控认证"),
        FLOW_ONE_STATUS_96(96,"风控认证失败","身份认证和风控认证"),
        FLOW_ONE_STATUS_100(100,"风控认证成功","身份认证和风控认证");

        RentalOrderFlowOneStatusEnum( int flowOneStatus, String flowOneStatusDescribe, String function) {
            this.flowOneStatus = flowOneStatus;
            this.flowOneStatusDescribe = flowOneStatusDescribe;
            this.function = function;
        }

        private int flowOneStatus;
        private String flowOneStatusDescribe;
        private String function;

        public static Result getResult(Integer flowOneStatus){
            if(StringUtil.isEmpty(flowOneStatus)){
                return Result.failure("身份认证和风控认证状态为空");
            }
            if(RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_0.flowOneStatus == flowOneStatus.intValue()){
                return Result.failure(RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_0.flowOneStatusDescribe);
            }else if(RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_9.flowOneStatus == flowOneStatus.intValue()){
                return Result.failure(RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_9.flowOneStatusDescribe);
            }else if(RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_96.flowOneStatus == flowOneStatus.intValue()){
                return Result.failure(RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_96.flowOneStatusDescribe);
            }else if(RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_100.flowOneStatus != flowOneStatus.intValue()){
                return Result.failure("风控未认证");
            }
            return Result.success();
        }

        public static void main(String[] args) {
            System.out.println(JsonUtil.gsonStr(getResult(0)));
        }

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


    public enum RentalOrderFlowTwoStatusEnum{
        FLOW_TWO_STATUS_0(0,"授权未认证","押金预授权"),
        FLOW_TWO_STATUS_1(1,"授权认证中","押金预授权"),
        FLOW_TWO_STATUS_99(99,"授权认证失败","押金预授权"),
        FLOW_TWO_STATUS_100(100,"授权认证成功","押金预授权");



        RentalOrderFlowTwoStatusEnum(int flowTwoStatus, String flowTwoStatusDescribe, String function) {
            this.flowTwoStatus = flowTwoStatus;
            this.flowTwoStatusDescribe = flowTwoStatusDescribe;
            this.function = function;
        }

        public static Result getResult(Integer flowTwoStatus){
            if(StringUtil.isEmpty(flowTwoStatus)){
                return Result.failure("授权状态为空");
            }
            if(RentalOrderFlowTwoStatusEnum.FLOW_TWO_STATUS_0.flowTwoStatus == flowTwoStatus.intValue()){
                return Result.failure(RentalOrderFlowTwoStatusEnum.FLOW_TWO_STATUS_0.flowTwoStatusDescribe);
            }else if(RentalOrderFlowTwoStatusEnum.FLOW_TWO_STATUS_1.flowTwoStatus == flowTwoStatus.intValue()){
                return Result.failure(RentalOrderFlowTwoStatusEnum.FLOW_TWO_STATUS_1.flowTwoStatusDescribe);
            }else if(RentalOrderFlowTwoStatusEnum.FLOW_TWO_STATUS_99.flowTwoStatus == flowTwoStatus.intValue()){
                return Result.failure(RentalOrderFlowTwoStatusEnum.FLOW_TWO_STATUS_99.flowTwoStatusDescribe);
            }
            return Result.success();
        }

        private int flowTwoStatus;
        private String flowTwoStatusDescribe;
        private String function;

        public int getFlowTwoStatus() {
            return flowTwoStatus;
        }

        public void setFlowTwoStatus(int flowTwoStatus) {
            this.flowTwoStatus = flowTwoStatus;
        }

        public String getFlowTwoStatusDescribe() {
            return flowTwoStatusDescribe;
        }

        public void setFlowTwoStatusDescribe(String flowTwoStatusDescribe) {
            this.flowTwoStatusDescribe = flowTwoStatusDescribe;
        }

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }
    }


    public enum RentalOrderFlowThreeStatusEnum{
        FLOW_THREE_STATUS_0(0,"未认证","暂留"),
        ;

        RentalOrderFlowThreeStatusEnum(int flowThreeStatus, String flowThreeStatusDescribe, String function) {
            this.flowThreeStatus = flowThreeStatus;
            this.flowThreeStatusDescribe = flowThreeStatusDescribe;
            this.function = function;
        }

        private int flowThreeStatus;
        private String flowThreeStatusDescribe;
        private String function;

        public int getFlowThreeStatus() {
            return flowThreeStatus;
        }

        public void setFlowThreeStatus(int flowThreeStatus) {
            this.flowThreeStatus = flowThreeStatus;
        }

        public String getFlowThreeStatusDescribe() {
            return flowThreeStatusDescribe;
        }

        public void setFlowThreeStatusDescribe(String flowThreeStatusDescribe) {
            this.flowThreeStatusDescribe = flowThreeStatusDescribe;
        }

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }
    }
}
