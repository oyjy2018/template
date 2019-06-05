package com.ydc.commom.enums;

import com.ydc.commom.constant.MemberAppointStatusConstant;

/**
 *  根据B端订单状态获取对应的本地订单状态
 */
public enum BOrderStatusEnum {

    ORDER_STATUS_0(MemberAppointStatusConstant.B_APPOINT_STATUS_SUCCESS_APPOINTMENT, MemberAppointStatusConstant.APPOINT_STATUS_APPOINTMENT),//预约中：  0-预约成功，待门店确认；
    ORDER_STATUS_1(MemberAppointStatusConstant.B_APPOINT_STATUS_STORE_ACCEPT,MemberAppointStatusConstant.APPOINT_STATUS_WAIT_SERVICE),//待服务:  1-门店受理；
    ORDER_STATUS_2(MemberAppointStatusConstant.B_APPOINT_STATUS_STORE_NOT_ACCEPT,MemberAppointStatusConstant.APPOINT_STATUS_CLOSE), //预约关闭:  2-门店不受理；
    ORDER_STATUS_3(MemberAppointStatusConstant.B_APPOINT_STATUS_ARRIVE_STORE,MemberAppointStatusConstant.APPOINT_STATUS_WAIT_SERVICE),//待服务:  3-确认到店
    ORDER_STATUS_4(MemberAppointStatusConstant.B_APPOINT_STATUS_CANCEL_APPOINTMENT,MemberAppointStatusConstant.APPOINT_STATUS_CLOSE),//预约关闭:  4-取消预约(门店已受理，车主反馈需要取消)
    ORDER_STATUS_5(MemberAppointStatusConstant.B_APPOINT_STATUS_SUCCESS_CONSUMPTION,MemberAppointStatusConstant.APPOINT_STATUS_FINISH),//已服务:  5-确认消费；
    ORDER_STATUS_6(MemberAppointStatusConstant.B_APPOINT_STATUS_OVERDUE,MemberAppointStatusConstant.APPOINT_STATUS_CLOSE),//预约关闭: 6-过期;
    ORDER_STATUS_7(MemberAppointStatusConstant.B_APPOINT_STATUS_NOT_ARRIVE_STORE,MemberAppointStatusConstant.APPOINT_STATUS_CLOSE);//预约关闭:  7-车主未到店';

    private int key;
    private int value;

    public  static int getValueByKey(Integer key){
        if (key==null){
            return  -1;
        }
        BOrderStatusEnum[] bOrderStatusEnums=values();
        for (BOrderStatusEnum bOrderStatusEnum:bOrderStatusEnums){
            if (bOrderStatusEnum.key==key.intValue()){
                return bOrderStatusEnum.value;
            }
        }
        return -1;
    }

    BOrderStatusEnum(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
