package com.ydc.commom.enums.cgj;

import com.ydc.commom.constant.cgj.MemberAppointmentConstant;

/**
 *  根据B端订单状态获取对应的本地订单状态
 */
public enum BOrderStatusEnum {

    ORDER_STATUS_0(MemberAppointmentConstant.B_APPOINT_STATUS_SUCCESS_APPOINTMENT, MemberAppointmentConstant.APPOINT_STATUS_APPOINTMENT),//预约中：  0-预约成功，待门店确认；
    ORDER_STATUS_1(MemberAppointmentConstant.B_APPOINT_STATUS_STORE_ACCEPT,MemberAppointmentConstant.APPOINT_STATUS_WAIT_SERVICE),//待服务:  1-门店受理；
    ORDER_STATUS_2(MemberAppointmentConstant.B_APPOINT_STATUS_STORE_NOT_ACCEPT,MemberAppointmentConstant.APPOINT_STATUS_CLOSE), //预约关闭:  2-门店不受理；
    ORDER_STATUS_3(MemberAppointmentConstant.B_APPOINT_STATUS_ARRIVE_STORE,MemberAppointmentConstant.APPOINT_STATUS_WAIT_SERVICE),//待服务:  3-确认到店
    ORDER_STATUS_4(MemberAppointmentConstant.B_APPOINT_STATUS_CANCEL_APPOINTMENT,MemberAppointmentConstant.APPOINT_STATUS_CLOSE),//预约关闭:  4-取消预约(门店已受理，车主反馈需要取消)
    ORDER_STATUS_5(MemberAppointmentConstant.B_APPOINT_STATUS_SUCCESS_CONSUMPTION,MemberAppointmentConstant.APPOINT_STATUS_FINISH),//已服务:  5-确认消费；
    ORDER_STATUS_6(MemberAppointmentConstant.B_APPOINT_STATUS_OVERDUE,MemberAppointmentConstant.APPOINT_STATUS_CLOSE),//预约关闭: 6-过期;
    ORDER_STATUS_7(MemberAppointmentConstant.B_APPOINT_STATUS_NOT_ARRIVE_STORE,MemberAppointmentConstant.APPOINT_STATUS_CLOSE);//预约关闭:  7-车主未到店';

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

    public static void main(String[] args) {
        System.out.println(getValueByKey(4));
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
