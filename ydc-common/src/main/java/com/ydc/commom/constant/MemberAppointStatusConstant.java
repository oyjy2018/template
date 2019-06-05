package com.ydc.commom.constant;

public class MemberAppointStatusConstant {


    /************    t_member_appointment 字段： appoint_status      _start*****************/
    public static final int APPOINT_STATUS_APPOINTMENT = 1;//预约中

    public static final int APPOINT_STATUS_WAIT_SERVICE = 2;//待服务


    public static final int APPOINT_STATUS_FINISH = 3;//已完成

    public static final int APPOINT_STATUS_CLOSE = 4;//关闭
    /************    t_member_appointment 字段： appoint_status      _end*****************/

    /************    t_member_appointment_roll 字段： roll_status      _start*****************/
    public static final int ROLL_STATUS_UNCONFIRMED= 0;//待确认

    public static final int ROLL_STATUS_CAN_USED = 1;//未使用

    public static final int ROLL_STATUS_USED = 2;//已使用

    public static final int ROLL_STATUS_SEND_BACK = 3;//已退回
    /************    t_member_appointment_roll 字段： roll_status      _end*****************/


    /************    t_member_appointment_roll 字段： process_status      _start*****************/
    public static final int PROCESS_STATUS_SUCCESSS= 0;//成功

    public static final int PROCESS_STATUS_PROCESSING = 1;//处理中
    /************    t_member_appointment_roll 字段： process_status      _end*****************/


    public static final int ROLL_STATUS_OVERDUE  = 3;//过期



    /************    t_member_appointment 字段： b_appoint_status      _start*****************/

    public static  final  int B_APPOINT_STATUS_SUCCESS_APPOINTMENT=0;//预约成功，待门店确认

    public static  final  int B_APPOINT_STATUS_STORE_ACCEPT=1;//门店受理

    public static  final  int B_APPOINT_STATUS_STORE_NOT_ACCEPT=2;//门店不受理

    public static  final  int B_APPOINT_STATUS_ARRIVE_STORE=3;//确认到店

    public static  final  int B_APPOINT_STATUS_CANCEL_APPOINTMENT=4;//取消预约(门店已受理，车主反馈需要取消)

    public static  final  int B_APPOINT_STATUS_SUCCESS_CONSUMPTION =5;//确认消费

    public static  final  int B_APPOINT_STATUS_OVERDUE=6;//过期

    public static  final  int B_APPOINT_STATUS_NOT_ARRIVE_STORE=7;//车主未到店

    /************    t_member_appointment 字段： b_appoint_status      _end*****************/

    /**
     *   产品给的   c端预约的订单状态 对应的B端的订单状态
     *
     *    预约中：  0-预约成功，待门店确认；
     *
     *   待服务:  1-门店受理；3-确认到店；
     *
     *
     *  已服务:  5-确认消费；
     *
     * 预约关闭:  2-门店不受理；4-取消预约(门店已受理，车主反馈需要取消)；6-过期；7-车主未到店';
     *
     *
     */







}
