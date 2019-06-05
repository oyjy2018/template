package com.ydc.commom.enums.cgj;

import com.ydc.commom.util.StringUtil;

import java.util.EnumSet;
import java.util.Optional;

/**
 * 会员预约表
 *
 * @author
 * @create 2018-12-14 17:09
 **/
public enum MemberAppointmentEnum {

    ;
    public enum AppointmentType{
        TYPE_1(1,"预约订单"),
        TYPE_2(2,"扫码订单");
        private Integer code;
        private String name;

        private static EnumSet<AppointmentType> appointmentType = EnumSet.allOf(AppointmentType.class);

        AppointmentType(Integer code, String name) {
            this.code = code;
            this.name = name;
        }
        public static String getCodeName(Integer code){
            if(StringUtil.isEmpty(code))return "";
            Optional<String> codeName =  appointmentType.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
            return codeName.orElse("");
        }
        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum AppointmentAppointStatus{
        STATUS_1(1,"预约中"),
        STATUS_2(2,"待服务"),
        STATUS_3(3,"已完成"),
        STATUS_4(4,"预约关闭");
        private Integer code;
        private String name;

        private static EnumSet<AppointmentAppointStatus> appointmentAppointStatus = EnumSet.allOf(AppointmentAppointStatus.class);

        AppointmentAppointStatus(Integer code, String name) {
            this.code = code;
            this.name = name;
        }
        public static String getCodeName(Integer code){
            if(StringUtil.isEmpty(code))return "";
            Optional<String> codeName =  appointmentAppointStatus.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
            return codeName.orElse("");
        }
        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum AppointmentProcessStatus{
        STATUS_0(0,"过程结束"),
        STATUS_1(1,"过程中");
        private Integer code;
        private String name;

        private static EnumSet<AppointmentProcessStatus> appointmentProcessStatus = EnumSet.allOf(AppointmentProcessStatus.class);

        AppointmentProcessStatus(Integer code, String name) {
            this.code = code;
            this.name = name;
        }
        public static String getCodeName(Integer code){
            if(StringUtil.isEmpty(code))return "";
            Optional<String> codeName =  appointmentProcessStatus.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
            return codeName.orElse("");
        }
        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum AppointmentBAppointStatus{
        STATUS_0(0,"预约成功，待门店确认"),
        STATUS_1(1,"门店受理"),
        STATUS_2(2,"门店不受理"),
        STATUS_3(3,"确认到店"),
        STATUS_4(4,"取消预约(门店未受理，车主反馈需要取消)"),
        STATUS_5(5,"确认消费"),
        STATUS_6(6,"过期"),
        STATUS_7(7,"车主未到店");
        private Integer code;
        private String name;

        private static EnumSet<AppointmentBAppointStatus> appointmentBAppointStatus = EnumSet.allOf(AppointmentBAppointStatus.class);

        AppointmentBAppointStatus(Integer code, String name) {
            this.code = code;
            this.name = name;
        }
        public static String getCodeName(Integer code){
            if(StringUtil.isEmpty(code))return "";
            Optional<String> codeName =  appointmentBAppointStatus.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
            return codeName.orElse("");
        }
        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
