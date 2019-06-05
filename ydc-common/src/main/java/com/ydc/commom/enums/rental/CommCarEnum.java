package com.ydc.commom.enums.rental;

import com.ydc.commom.util.StringUtil;

import java.util.Arrays;
import java.util.Optional;

/**
 * 车辆相关属性枚举
 */
public enum CommCarEnum {
    ;
    //运营状态枚举
    public enum CommCarOperationStatusEnum{
        OPERATION_STATUS_0( 0, "待检"), //运营状态：待检
        OPERATION_STATUS_1( 1, "待租"), //运营状态：待租
        OPERATION_STATUS_2( 2, "已租"), //运营状态：已租
        ;

        CommCarOperationStatusEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }
        private Integer code;
        private String name;

        public static String getCodeName(int code){
            Optional<String> codeName = Arrays.stream(CommCarOperationStatusEnum.values()).filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
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

    //出车类型枚举
    public enum CommCarTurnOutTypeEnum{
        TURN_OUT_TYPE_0( "0", "未出车"), //出车类型：未出车
        TURN_OUT_TYPE_1( "1", "洗车"), //出车类型：洗车
        TURN_OUT_TYPE_2( "2", "加油"), //出车类型：加油
        TURN_OUT_TYPE_3( "3", "维修保养"), //出车类型：维修保养
        TURN_OUT_TYPE_4( "4", "事故维修"), //出车类型：事故维修
        TURN_OUT_TYPE_5( "5", "调度"), //出车类型：调度
        TURN_OUT_TYPE_6( "6", "公务"), //出车类型：公务
        TURN_OUT_TYPE_7( "7", "其他"), //出车类型：其他
        TURN_OUT_TYPE_8( "8", "租车"); //出车类型：租车

        private String code;
        private String name;

        CommCarTurnOutTypeEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getCodeName(String code){
            Optional<String> codeName = Arrays.stream(CommCarTurnOutTypeEnum.values()).filter(item -> item.getCode().equals(code)).map(item -> item.getName()).findAny();
            return codeName.orElse("");
        }

        //翻译出车类型
        public static String transferTurnOutType(String turnOutType) {
            if (StringUtil.isEmpty(turnOutType)) {
                return "";
            }
            String[] turnOutTypeArray = turnOutType.split(",");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < turnOutTypeArray.length; i++) {
                sb.append(getCodeName(turnOutTypeArray[i]));
                if (i != turnOutTypeArray.length - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    //车辆来源枚举
    public enum CommCarSourceEnum{
        SOURCE_0(0, "自购车"), //车辆来源：自购车
        SOURCE_1(1, "二手车"); //车辆来源：二手车
        private Integer code;
        private String name;

        CommCarSourceEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getCodeName(int code){
            Optional<String> codeName = Arrays.stream(CommCarSourceEnum.values()).filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
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

    //外部车辆状态枚举
    //车辆状态 0待审核 1待发布 2己发布 3审核失败 4己出租 5己删除
    public enum RentalCarStatusEnum {
        TO_CHECK(0, "待审核"),
        TO_PUBLISH(1, "待发布"),
        PUBLISH_SUCCESS(2, "己发布"),
        CHECK_FAIL(3, "审核失败"),
        RENT(4, "己出租"),
        DELETE(5, "己删除");
        private Integer code;
        private String name;

        RentalCarStatusEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getCodeName(int code){
            Optional<String> codeName = Arrays.stream(RentalCarStatusEnum.values()).filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
            return codeName.orElse("");
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum RentalCarCheckResultEnum {
        PASS(0, "通过"),
        UN_PASS(1, "退回");
        private int code;
        private String name;

        RentalCarCheckResultEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getCodeName(int code){
            Optional<String> codeName = Arrays.stream(RentalCarStatusEnum.values()).filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
            return codeName.orElse("");
        }

        public int getCode() {
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

    public enum RentalCarCheckStatusEnum {
        TO_CHECK(0, "未审核"),
        CHECKED(1, "己审核");
        private int code;
        private String name;

        RentalCarCheckStatusEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getCodeName(int code){
            Optional<String> codeName = Arrays.stream(RentalCarStatusEnum.values()).filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
            return codeName.orElse("");
        }

        public int getCode() {
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
