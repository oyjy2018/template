package com.ydc.commom.enums.rental;

import com.ydc.commom.util.StringUtil;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 租车订单
 *
 * @author
 * @create 2018-12-07 15:23
 **/
public enum RentalOrderEnum {
    STATUS_4_0(4,0,"已还车-待结算"),
    STATUS_4_1(4,1,"已还车-部分结算"),
    STATUS_4_100(4,100,"已还车-已结算");

    private int status;
    private int flowOneStatus;
    private String describe;



    RentalOrderEnum(int status, int flowOneStatus, String describe) {
        this.status = status;
        this.flowOneStatus = flowOneStatus;
        this.describe = describe;
    }

    public static String getDescribe(int code,Integer flowOneStatus){
        if(code == RentalOrderEnum.STATUS_4_0.status && (StringUtil.isEmpty(flowOneStatus) || flowOneStatus == RentalOrderEnum.STATUS_4_0.flowOneStatus)){
            return RentalOrderEnum.STATUS_4_0.describe;
        }
        if(code == RentalOrderEnum.STATUS_4_0.status && flowOneStatus == RentalOrderEnum.STATUS_4_1.flowOneStatus){
            return RentalOrderEnum.STATUS_4_1.describe;
        }
        if(code == RentalOrderEnum.STATUS_4_0.status && flowOneStatus == RentalOrderEnum.STATUS_4_100.flowOneStatus){
            return RentalOrderEnum.STATUS_4_100.describe;
        }
        return RentalOrderEnum.STATUS_4_0.describe;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFlowOneStatus() {
        return flowOneStatus;
    }

    public void setFlowOneStatus(int flowOneStatus) {
        this.flowOneStatus = flowOneStatus;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


    /**
     * 前端订单状态（请求查询用）
     */
    public enum StatusReqEnum {

        status10("10","1","0","0","0")
        ,status15("15","1","9","0","0")
        ,status20("20","1","10,96","0","0")
        ,status30("30","1","100","0,1","0")
        ,status40("40","1","100","100","0")
        ,status50("50","2","0","0","0")
        ,status60("60","3","0","0","0")
        ,status70("70","4","1","0","0")
        ,status80("80","4","100","0","0")
        ,status90("90","98","0","0","0")
        ,status100("100","99","0","0","0")
        ,status110("110","100","0","0","0")
        ;

        String status;
        String realStatus;
        String flowOneStatus;
        String flowTwoStatus;
        String flowThreeStatus;

        private StatusReqEnum(String status,String realStatus,String flowOneStatus,String flowTwoStatus,String flowThreeStatus){
            this.status = status;
            this.realStatus = realStatus;
            this.flowOneStatus = flowOneStatus;
            this.flowTwoStatus = flowTwoStatus;
            this.flowThreeStatus = flowThreeStatus;
        }

        public static StatusReqEnum getEnumByStatus(String status){
            for(StatusReqEnum rose: StatusReqEnum.values()){
                if(rose.status.equals(status))
                    return rose;
            }
            return null;
        }

        public Map<String, Object> getSearchParamMap(){
            Map<String, Object> map = new HashMap<>();
            map.put("status",this.realStatus);
            map.put("flowOneStatus",this.flowOneStatus);
            map.put("flowTwoStatus",this.flowTwoStatus);
            return map;
        }

        public Map<String, Object> getUpdateParamMap(){
            Map<String, Object> map = new HashMap<>();
            map.put("status",this.realStatus);
            if(this.flowOneStatus != null && this.flowOneStatus.indexOf(",") != -1){
                map.put("flowOneStatus",this.flowOneStatus.substring(0,this.flowOneStatus.indexOf(",")));
            }else{
                map.put("flowOneStatus",this.flowOneStatus);
            }
            if(this.flowTwoStatus != null && this.flowTwoStatus.indexOf(",") != -1){
                map.put("flowTwoStatus",this.flowTwoStatus.substring(0,this.flowTwoStatus.indexOf(",")));
            }else{
                map.put("flowTwoStatus",this.flowTwoStatus);
            }
            if(this.flowThreeStatus != null && this.flowThreeStatus.indexOf(",") != -1){
                map.put("flowThreeStatus",this.flowThreeStatus.substring(0,this.flowThreeStatus.indexOf(",")));
            }else{
                map.put("flowThreeStatus",this.flowThreeStatus);
            }
            return map;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRealStatus() {
            return realStatus;
        }

        public void setRealStatus(String realStatus) {
            this.realStatus = realStatus;
        }

        public String getFlowOneStatus() {
            return flowOneStatus;
        }

        public void setFlowOneStatus(String flowOneStatus) {
            this.flowOneStatus = flowOneStatus;
        }

        public String getFlowTwoStatus() {
            return flowTwoStatus;
        }

        public void setFlowTwoStatus(String flowTwoStatus) {
            this.flowTwoStatus = flowTwoStatus;
        }

        public String getFlowThreeStatus() {
            return flowThreeStatus;
        }

        public void setFlowThreeStatus(String flowThreeStatus) {
            this.flowThreeStatus = flowThreeStatus;
        }
    }

    /**
     * 订单状态
     */
    public enum StatusStoreEnum{

        STATUS10(10,"未确认-待风控","待确认",1,0,0,0)
        ,STATUS15(15,"未确认-身份认证失败","待确认",1,9,0,0)
        ,STATUS20_10(20,"已确认-待风控","待确认",1,10,0,0)
        ,STATUS20_96(20,"已确认-待风控","待确认",1,96,0,0)
        ,STATUS30_0(30,"风控通过-待租车预授权","待租车预授权",1,100,0,0)
        ,STATUS30_1(30,"风控通过-待租车预授权","待租车预授权",1,100,1,0)
        ,STATUS40(40,"已租车预授权-待出车","待取车",1,100,100,0)
        ,STATUS50(50,"出车成功-待还车","待还车",2,0,0,0)
        ,STATUS60(60,"已还车-待结算","已还车",3,0,0,0)
        ,STATUS70(70,"已还车-部分结算","已还车",4,1,0,0)
        ,STATUS80(80,"已还车-已结算","已还车",4,100,0,0)
        ,STATUS90(90,"已取消","已取消",98,0,0,0)
        ,STATUS100(100,"已拒绝","已拒绝",99,0,0,0)
        ,STATUS110(110,"订单结束","订单结束",100,0,0,0)
        ;

        Integer status;
        String describe;
        String describeAPPC;
        Integer realStatus;
        Integer flowOneStatus;
        Integer flowTwoStatus;
        Integer flowThreeStatus;

        private StatusStoreEnum(Integer status, String describe, String describeAPPC, Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            this.status = status;
            this.describe = describe;
            this.describeAPPC = describeAPPC;
            this.realStatus = realStatus;
            this.flowOneStatus = flowOneStatus;
            this.flowTwoStatus = flowTwoStatus;
            this.flowThreeStatus = flowThreeStatus;
        }

        /**
         * 根据订单真实状态获取枚举（枚举含有订单前端状态）
         * @param realStatus
         * @param flowOneStatus
         * @param flowTwoStatus
         * @param flowThreeStatus
         * @return
         */
        public static StatusStoreEnum getStatusStoreEnum(Integer realStatus, Integer flowOneStatus, Integer flowTwoStatus, Integer flowThreeStatus){
            for(StatusStoreEnum sse:StatusStoreEnum.values()){
                if(Objects.equals(sse.realStatus, realStatus)
                    && Objects.equals(sse.flowOneStatus, flowOneStatus)
                    && Objects.equals(sse.flowTwoStatus, flowTwoStatus)
                    && Objects.equals(sse.flowThreeStatus, flowThreeStatus)){
                    return sse;
                }
            }
            return null;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getDescribeAPPC() {
            return describeAPPC;
        }

        public void setDescribeAPPC(String describeAPPC) {
            this.describeAPPC = describeAPPC;
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

    /**
     * 取车方式
     */
    public enum FetchCarMode{
        FetchCarMode1(1, "到店取车")
        ,FetchCarMode0(2, "送车上门")
        ;

        Integer status;
        String statusCH;

        private FetchCarMode(Integer status, String statusCH){
            this.status = status;
            this.statusCH = statusCH;
        }

        public static FetchCarMode getFetchCarModeByStatus(Integer status){
            for(FetchCarMode fcm: FetchCarMode.values()){
                if(Objects.equals(fcm.status, status)) return fcm;
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

    /**
     * 还车方式
     */
    public enum RepayCarMode{
        FetchCarMode1(1, "到店还车")
        ,FetchCarMode0(2, "送车上门")
        ;

        Integer status;
        String statusCH;

        RepayCarMode(Integer status, String statusCH){
            this.status = status;
            this.statusCH = statusCH;
        }

        public static RepayCarMode getRepayCarModeByStatus(Integer status){
            for(RepayCarMode rcm: RepayCarMode.values()){
                if(Objects.equals(rcm.status, status)) return rcm;
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
}
