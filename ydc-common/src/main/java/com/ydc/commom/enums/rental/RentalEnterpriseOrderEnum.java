package com.ydc.commom.enums.rental;

import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseOrderDTO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.QueryStatusVO;

import java.util.*;
import java.util.stream.Collectors;

public enum  RentalEnterpriseOrderEnum {
    ;

    /**
     * 取消方
     */
    public enum CancelSide{

        STATUS1(1, "需求方")
        ,STATUS2(2, "门店")
        ;

        private Integer status;
        private String statusCH;

        CancelSide(Integer status, String statusCH){
            this.status = status;
            this.statusCH = statusCH;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public static CancelSide getCancelSideByStatus(Integer status){
            if(status == null) return null;
            
            for(CancelSide cs: CancelSide.values()){
                if(cs.status.intValue() == status.intValue()) return cs;
            }
            return null;
        }
    }


    public enum QueryStatusEnum{
        STATUS10(10, "待资源方确认",  1, 0, 0, 0)
        ,STATUS20(20, "待需求方预授权", 1, 100, 0, 0)
        ,STATUS20_1(20, "待需求方预授权", 1, 100, 1, 0)
        ,STATUS20_99(20, "待需求方预授权", 1, 100, 99, 0)
        ,STATUS30(30, "待需求方付租金", 1, 100, 100, 0)
        ,STATUS40(40, "待出车", 1, 100, 100, 50)
        ,STATUS50(50, "待转账至资源方", 1, 100, 100, 98)
        ,STATUS60(60, "待还车",  2, 0, 0, 0)
        ,STATUS70(70, "待结算",  3, 0, 0, 0)
        ,STATUS80(80, "待退还押金",  4, 0, 0, 0)
        ,STATUS90(90, "已取消", 98, 0, 0, 0)
        ,STATUS100(100, "已拒绝", 99, 0, 0, 0)
        ,STATUS110(110, "已完成",  100, 0, 0, 0)
        ;
        Integer status;
        String describeStore;   //门店与后台状态
        Integer realStatus;
        Integer flowOneStatus;
        Integer flowTwoStatus;
        Integer flowThreeStatus;


        public static List<QueryStatusVO> getPCQueryRentalStatusParam(){
            return Arrays.asList(
                    new QueryStatusVO(0,"全部"),
                    new QueryStatusVO(QueryStatusEnum.STATUS10.status,QueryStatusEnum.STATUS10.describeStore),
                    new QueryStatusVO(QueryStatusEnum.STATUS20.status,QueryStatusEnum.STATUS20.describeStore),
                    new QueryStatusVO(QueryStatusEnum.STATUS30.status,QueryStatusEnum.STATUS30.describeStore),
                    new QueryStatusVO(QueryStatusEnum.STATUS40.status,QueryStatusEnum.STATUS40.describeStore),
                    new QueryStatusVO(QueryStatusEnum.STATUS50.status,QueryStatusEnum.STATUS50.describeStore),
                    new QueryStatusVO(QueryStatusEnum.STATUS60.status,QueryStatusEnum.STATUS60.describeStore),
                    new QueryStatusVO(QueryStatusEnum.STATUS70.status,QueryStatusEnum.STATUS70.describeStore),
                    new QueryStatusVO(QueryStatusEnum.STATUS80.status,QueryStatusEnum.STATUS80.describeStore),
                    new QueryStatusVO(QueryStatusEnum.STATUS110.status,QueryStatusEnum.STATUS110.describeStore));
        }


        public static void queryStatusSet(RentalEnterpriseOrderDTO dto){
            List<QueryStatusEnum> queryStatusList = Arrays.asList(QueryStatusEnum.values());
            queryStatusList = queryStatusList.stream()
                    .filter(item -> item.status == dto.getStatus()).collect(Collectors.toList());
            if(queryStatusList == null || queryStatusList.isEmpty())return;

            dto.setRealStatus(queryStatusList.get(0).realStatus);

            Set<Integer> flowOneStatus = new HashSet<>();
            Set<Integer> flowTwoStatus = new HashSet<>();
            Set<Integer> flowThreeStatus = new HashSet<>();
            queryStatusList.stream().forEach(item ->{
                flowOneStatus.add(item.flowOneStatus);
                flowTwoStatus.add(item.flowTwoStatus);
                flowThreeStatus.add(item.flowThreeStatus);
            });
            dto.setFlowOneStatus(flowOneStatus);
            dto.setFlowTwoStatus(flowTwoStatus);
            dto.setFlowThreeStatus(flowThreeStatus);
        }



        public static void main(String[] args) {
            RentalEnterpriseOrderDTO dto = new RentalEnterpriseOrderDTO();
            dto.setStatus(20);
            queryStatusSet(dto);
            System.out.println(JsonUtil.gsonStr(dto));
            System.out.println(JsonUtil.gsonStr(getPCQueryRentalStatusParam()));
        }



        QueryStatusEnum(Integer status, String describeStore, Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus) {
            this.status = status;
            this.describeStore = describeStore;
            this.realStatus = realStatus;
            this.flowOneStatus = flowOneStatus;
            this.flowTwoStatus = flowTwoStatus;
            this.flowThreeStatus = flowThreeStatus;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getDescribeStore() {
            return describeStore;
        }

        public void setDescribeStore(String describeStore) {
            this.describeStore = describeStore;
        }

        public Integer getRealStatus() {
            return realStatus;
        }

        public void setRealStatus(Integer realStatus) {
            this.realStatus = realStatus;
        }

        public Integer getFlowOneStatus() {
            return flowOneStatus;
        }

        public void setFlowOneStatus(Integer flowOneStatus) {
            this.flowOneStatus = flowOneStatus;
        }

        public Integer getFlowTwoStatus() {
            return flowTwoStatus;
        }

        public void setFlowTwoStatus(Integer flowTwoStatus) {
            this.flowTwoStatus = flowTwoStatus;
        }

        public Integer getFlowThreeStatus() {
            return flowThreeStatus;
        }

        public void setFlowThreeStatus(Integer flowThreeStatus) {
            this.flowThreeStatus = flowThreeStatus;
        }
    }


    public enum OrderStatus{

        STATUS10(10, "待资源方确认", "待确认", "待确认", 1, 0, 0, 0)
        ,STATUS20(20, "待需求方预授权", "待保证金预授权", "待保证金预授权", 1, 100, 0, 0)
        ,STATUS20_1(20, "待需求方预授权", "待保证金预授权", "待保证金预授权", 1, 100, 1, 0)
        ,STATUS20_99(20, "待需求方预授权", "待保证金预授权", "待保证金预授权", 1, 100, 99, 0)
        ,STATUS30(30, "待需求方付租金", "待付租金", "待出车", 1, 100, 100, 0)
        ,STATUS40(40, "待出车", "待出车", "待出车", 1, 100, 100, 50)
        ,STATUS50(50, "待转账至资源方", "待还车", "待收租金", 1, 100, 100, 98)
        ,STATUS60(60, "待还车", "待还车", "待还车", 2, 0, 0, 0)
        ,STATUS70(70, "待结算", "待结算", "待结算", 3, 0, 0, 0)
        ,STATUS80(80, "待退还押金", "待退还押金", "待退还押金", 4, 0, 0, 0)
        ,STATUS90(90, "已取消", "已取消", "已取消", 98, 0, 0, 0)
        ,STATUS100(100, "已拒绝", "已拒绝", "已拒绝", 99, 0, 0, 0)
        ,STATUS110(110, "已完成", "已完成", "已完成", 100, 0, 0, 0)
        ;

        Integer status;
        String describeStore;   //门店与后台状态
        String describeDemand;  //需求方状态
        String describeResource;    //资源方状态
        Integer realStatus;
        Integer flowOneStatus;
        Integer flowTwoStatus;
        Integer flowThreeStatus;

        OrderStatus(Integer status, String describeStore, String describeDemand, String describeResource, Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            this.status = status;
            this.describeStore = describeStore;
            this.describeDemand = describeDemand;
            this.describeResource = describeResource;
            this.realStatus = realStatus;
            this.flowOneStatus = flowOneStatus;
            this.flowTwoStatus = flowTwoStatus;
            this.flowThreeStatus = flowThreeStatus;
        }


        public static Optional<OrderStatus> getStatusStoreEnum(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            return Arrays.stream(OrderStatus.values()).
                    filter(item -> Objects.equals(item.getRealStatus(),realStatus)
                            && Objects.equals(item.getFlowOneStatus(),flowOneStatus)
                            && Objects.equals(item.getFlowTwoStatus(),flowTwoStatus)
                            && Objects.equals(item.getFlowThreeStatus(),flowThreeStatus)).findAny();
        }
        /**
         * 返回描述
         * @param realStatus
         * @param flowOneStatus
         * @param flowTwoStatus
         * @param flowThreeStatus
         * @return
         */
        public static String getDescribeStore(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            Optional<OrderStatus> optional = getStatusStoreEnum(realStatus,flowOneStatus,flowTwoStatus,flowThreeStatus);
            return optional.isPresent() ? optional.get().describeStore : "";
        }

        /**
         * 返回自定义状态
         * @param realStatus
         * @param flowOneStatus
         * @param flowTwoStatus
         * @param flowThreeStatus
         * @return
         */
        public static Integer getStatus(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            Optional<OrderStatus> optional = getStatusStoreEnum(realStatus,flowOneStatus,flowTwoStatus,flowThreeStatus);
            return optional.isPresent() ? optional.get().status : null;
        }





        /**
         * 取消订单
         * @param realStatus
         * @param flowOneStatus
         * @param flowTwoStatus
         * @param flowThreeStatus
         * @return
         */
        public static Result cancelRentalEnterpriseOrder(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
//            if(OrderStatus.STATUS10.getRealStatus() != realStatus){
//                return Result.failure("【"+getDescribeStore(realStatus,flowOneStatus,flowTwoStatus,flowThreeStatus)+"】环节不可以取消订单");
//            }
//            if(Objects.equals(OrderStatus.STATUS50.realStatus,realStatus)
//                    && Objects.equals(OrderStatus.STATUS50.flowOneStatus,flowOneStatus)
//                    && Objects.equals(OrderStatus.STATUS50.flowTwoStatus,flowTwoStatus)
//                    && Objects.equals(OrderStatus.STATUS50.flowThreeStatus,flowThreeStatus)){
//                return Result.failure("【"+OrderStatus.STATUS50.describeStore+"】环节不可以取消订单");
//            }
//
//            if(Objects.equals(OrderStatus.STATUS40.realStatus,realStatus)
//                    && Objects.equals(OrderStatus.STATUS40.flowOneStatus,flowOneStatus)
//                    && Objects.equals(OrderStatus.STATUS40.flowTwoStatus,flowTwoStatus)
//                    && Objects.equals(OrderStatus.STATUS40.flowThreeStatus,flowThreeStatus)){
//                return Result.failure("【"+OrderStatus.STATUS40.describeStore+"】环节不可以取消订单");
//            }
//
//            if(Objects.equals(OrderStatus.STATUS30.realStatus,realStatus)
//                    && Objects.equals(OrderStatus.STATUS30.flowOneStatus,flowOneStatus)
//                    && Objects.equals(OrderStatus.STATUS30.flowTwoStatus,flowTwoStatus)
//                    && Objects.equals(OrderStatus.STATUS30.flowThreeStatus,flowThreeStatus)){
//                return Result.failure("【"+OrderStatus.STATUS30.describeStore+"】环节不可以取消订单");
//            }


            if(Objects.equals(OrderStatus.STATUS20_99.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS20_99.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS20_99.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS20_99.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }

            if(Objects.equals(OrderStatus.STATUS20_1.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS20_1.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS20_1.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS20_1.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }

            if(Objects.equals(OrderStatus.STATUS20.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS20.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS20.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS20.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }
            return Result.failure("【"+getDescribeStore(realStatus,flowOneStatus,flowTwoStatus,flowThreeStatus)+"】环节不可以取消订单");
        }

        /**
         * 拒绝订单
         * @param realStatus
         * @param flowOneStatus
         * @param flowTwoStatus
         * @param flowThreeStatus
         * @return
         */
        public static Result rejectRentalEnterpriseOrder(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            if(Objects.equals(OrderStatus.STATUS10.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS10.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS10.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS10.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }
            return Result.failure("【"+getDescribeStore(realStatus,flowOneStatus,flowTwoStatus,flowThreeStatus)+"】环节不可以拒绝订单");
        }


        /**
         * 确认订单
         * @param realStatus
         * @param flowOneStatus
         * @param flowTwoStatus
         * @param flowThreeStatus
         * @return
         */
        public static Result notarizeRentalEnterpriseOrder(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            if(Objects.equals(OrderStatus.STATUS10.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS10.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS10.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS10.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }
            return Result.failure("【"+getDescribeStore(realStatus,flowOneStatus,flowTwoStatus,flowThreeStatus)+"】环节不可以确认订单");
        }

        /**
         * 缴纳保证金
         * @param realStatus
         * @param flowOneStatus
         * @param flowTwoStatus
         * @param flowThreeStatus
         * @return
         */
        public static Result paymentDeposit(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            if(Objects.equals(OrderStatus.STATUS20.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS20.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS20.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS20.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }

            if(Objects.equals(OrderStatus.STATUS20_1.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS20_1.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS20_1.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS20_1.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }

            if(Objects.equals(OrderStatus.STATUS20_99.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS20_99.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS20_99.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS20_99.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }
            return Result.failure("【"+getDescribeStore(realStatus,flowOneStatus,flowTwoStatus,flowThreeStatus)+"】环节不可以缴纳保证金");
        }

        /**
         * 企业租车后台列表:上传订单资料
         * @param realStatus
         * @param flowOneStatus
         * @param flowTwoStatus
         * @param flowThreeStatus
         * @return
         */
        public static Result insertPCRentalEnterpriseOrderFile(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            if(Objects.equals(OrderStatus.STATUS50.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS50.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS50.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS50.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }

            if(Objects.equals(OrderStatus.STATUS40.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS40.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS40.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS40.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }

            if(Objects.equals(OrderStatus.STATUS30.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS30.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS30.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS30.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }

            if(Objects.equals(OrderStatus.STATUS60.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS60.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS60.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS60.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }

            if(Objects.equals(OrderStatus.STATUS70.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS70.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS70.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS70.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }

            if(Objects.equals(OrderStatus.STATUS80.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS80.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS80.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS80.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }
            if(Objects.equals(OrderStatus.STATUS110.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS110.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS110.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS110.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }
            return Result.failure("【"+getDescribeStore(realStatus,flowOneStatus,flowTwoStatus,flowThreeStatus)+"】环节不可以上传订单资料");
        }

        /**
         * 企业租车后台列表:录入租金支付信息
         * @param realStatus
         * @param flowOneStatus
         * @param flowTwoStatus
         * @param flowThreeStatus
         * @return
         */
        public static Result insertRentPayment(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            if(Objects.equals(OrderStatus.STATUS30.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS30.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS30.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS30.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }
            return Result.failure("【"+getDescribeStore(realStatus,flowOneStatus,flowTwoStatus,flowThreeStatus)+"】环节不可以录入租金支付信息");
        }

        /**
         * 企业租车后台列表:录入出车信息
         * @param realStatus
         * @param flowOneStatus
         * @param flowTwoStatus
         * @param flowThreeStatus
         * @return
         */
        public static Result insertRentalCheckComeCar(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            if(Objects.equals(OrderStatus.STATUS40.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS40.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS40.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS40.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }
            return Result.failure("【"+getDescribeStore(realStatus,flowOneStatus,flowTwoStatus,flowThreeStatus)+"】环节不可以录入出车信息");
        }


        /**
         * 企业租车后台列表:录入租金转账信息
         * @param realStatus
         * @param flowOneStatus
         * @param flowTwoStatus
         * @param flowThreeStatus
         * @return
         */
        public static Result insertRentTransfer(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            if(Objects.equals(OrderStatus.STATUS50.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS50.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS50.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS50.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }
            return Result.failure("【"+getDescribeStore(realStatus,flowOneStatus,flowTwoStatus,flowThreeStatus)+"】环节不可以录入租金转账信息");
        }

        /**
         * 企业租车后台列表:录入还车信息
         * @param realStatus
         * @param flowOneStatus
         * @param flowTwoStatus
         * @param flowThreeStatus
         * @return
         */
        public static Result insertRentalCheckRepayCar(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            if(Objects.equals(OrderStatus.STATUS60.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS60.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS60.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS60.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }
            return Result.failure("【"+getDescribeStore(realStatus,flowOneStatus,flowTwoStatus,flowThreeStatus)+"】环节不可以录入还车信息");
        }

        /**
         * 企业租车后台列表:录入结算信息
         * @param realStatus
         * @param flowOneStatus
         * @param flowTwoStatus
         * @param flowThreeStatus
         * @return
         */
        public static Result insertEnterpriseSettlement(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            if(Objects.equals(OrderStatus.STATUS70.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS70.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS70.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS70.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }
            return Result.failure("【"+getDescribeStore(realStatus,flowOneStatus,flowTwoStatus,flowThreeStatus)+"】环节不可以录入结算信息");
        }

        /**
         * 企业租车后台列表:录入押金退还信息
         * @param realStatus
         * @param flowOneStatus
         * @param flowTwoStatus
         * @param flowThreeStatus
         * @return
         */
        public static Result insertDepositRefund(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            if(Objects.equals(OrderStatus.STATUS80.realStatus,realStatus)
                    && Objects.equals(OrderStatus.STATUS80.flowOneStatus,flowOneStatus)
                    && Objects.equals(OrderStatus.STATUS80.flowTwoStatus,flowTwoStatus)
                    && Objects.equals(OrderStatus.STATUS80.flowThreeStatus,flowThreeStatus)){
                return Result.success();
            }
            return Result.failure("【"+getDescribeStore(realStatus,flowOneStatus,flowTwoStatus,flowThreeStatus)+"】环节不可以录入押金退还信息");
        }

        /**
         * 根据订单真实状态获取枚举（枚举含有订单前端状态）
         * @param realStatus
         * @param flowOneStatus
         * @param flowTwoStatus
         * @param flowThreeStatus
         * @return
         */
        public static Optional<OrderStatus> getOrderStatus(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            return Arrays.stream(OrderStatus.values())
                    .filter(os -> Objects.equals(os.realStatus, realStatus)
                            && Objects.equals(os.flowOneStatus, flowOneStatus)
                            && Objects.equals(os.flowTwoStatus, flowTwoStatus)
                            && Objects.equals(os.flowThreeStatus, flowThreeStatus))
                    .findAny();
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getDescribeStore() {
            return describeStore;
        }

        public void setDescribeStore(String describeStore) {
            this.describeStore = describeStore;
        }

        public String getDescribeDemand() {
            return describeDemand;
        }

        public void setDescribeDemand(String describeDemand) {
            this.describeDemand = describeDemand;
        }

        public String getDescribeResource() {
            return describeResource;
        }

        public void setDescribeResource(String describeResource) {
            this.describeResource = describeResource;
        }

        public Integer getRealStatus() {
            return realStatus;
        }

        public void setRealStatus(Integer realStatus) {
            this.realStatus = realStatus;
        }

        public Integer getFlowOneStatus() {
            return flowOneStatus;
        }

        public void setFlowOneStatus(Integer flowOneStatus) {
            this.flowOneStatus = flowOneStatus;
        }

        public Integer getFlowTwoStatus() {
            return flowTwoStatus;
        }

        public void setFlowTwoStatus(Integer flowTwoStatus) {
            this.flowTwoStatus = flowTwoStatus;
        }

        public Integer getFlowThreeStatus() {
            return flowThreeStatus;
        }

        public void setFlowThreeStatus(Integer flowThreeStatus) {
            this.flowThreeStatus = flowThreeStatus;
        }
    }
}
