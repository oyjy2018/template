package com.ydc.commom.enums.rental;

import java.util.Arrays;

/**
 * 押金结算方式
 *
 * @author
 * @create 2019-01-05 13:14
 **/
public enum RentalSettlementEnum {
    STATUS1(1,"芝麻","押金"),STATUS2(2,"信用卡","信用卡"),STATUS3(3,"转账","转账");

    private Integer settleWay;
    private String settleWayCH;
    private String settleWatPC;

    RentalSettlementEnum(Integer settleWay, String settleWayCH,String settleWatPC) {
        this.settleWay = settleWay;
        this.settleWayCH = settleWayCH;
        this.settleWatPC = settleWatPC;
    }

    public static String getRentalSettlementEnum(Integer settleWay){
        return Arrays.asList(RentalSettlementEnum.values()).stream().filter(item -> item.getSettleWay() == settleWay).map(item -> item.settleWatPC).findAny().orElse("");
    }

    public static String getRentalSettlementEnumOfNull(Integer settleWay){
        return Arrays.asList(RentalSettlementEnum.values()).stream().filter(item -> item.getSettleWay() == settleWay).map(item -> item.settleWatPC).findAny().orElse(null);
    }


    public Integer getSettleWay() {
        return settleWay;
    }

    public void setSettleWay(Integer settleWay) {
        this.settleWay = settleWay;
    }

    public String getSettleWayCH() {
        return settleWayCH;
    }

    public void setSettleWayCH(String settleWayCH) {
        this.settleWayCH = settleWayCH;
    }

    public String getSettleWatPC() {
        return settleWatPC;
    }

    public void setSettleWatPC(String settleWatPC) {
        this.settleWatPC = settleWatPC;
    }
}
