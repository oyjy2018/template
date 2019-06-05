package com.ydc.commom.enums.common;

import com.ydc.commom.util.StringUtil;

import java.util.EnumSet;
import java.util.Optional;

/**
 * 公共枚举
 *
 * @author
 * @create 2018-12-06 15:02
 **/
public enum CommonEnum {
    ;
    public enum DeleteStatusEnum{
        STATUS_0(0,"禁用"),
        STATUS_1(1,"启用");

        private Integer code;
        private String name;

        private static EnumSet<DeleteStatusEnum> deleteStatusEnum = EnumSet.allOf(DeleteStatusEnum.class);

        DeleteStatusEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getCodeName(Integer code){
            if(StringUtil.isEmpty(code))return "";
            Optional<String> codeName =  deleteStatusEnum.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
            return codeName.orElse("");
        }

        public Integer getCode() {
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

    public enum WhetherEnum{
        WHETHER_0(0,"否"),
        WHETHER_1(1,"是");

        private int code;
        private String name;

        private static EnumSet<WhetherEnum> whetherEnum = EnumSet.allOf(WhetherEnum.class);

        WhetherEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getCodeName(Integer code){
            if(StringUtil.isEmpty(code))return "";
            Optional<String> codeName = whetherEnum.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
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

    public enum ValidEnum{
        VALID_0(0,"无效"),
        VALID_1(1,"有效");
        private int code;
        private String name;


        private static EnumSet<ValidEnum> validEnum = EnumSet.allOf(ValidEnum.class);
        ValidEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getCodeName(int code){
            if(StringUtil.isEmpty(code))return "";
            Optional<String> codeName =  validEnum.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
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

    public enum MemberStatusEnum{
        MEMBER_STATUS_1(1,"正常"),
        MEMBER_STATUS_2(2,"注销"),
        MEMBER_STATUS_3(3,"锁定");
        private int code;
        private String name;

        private static EnumSet<MemberStatusEnum> memberStatusEnum = EnumSet.allOf(MemberStatusEnum.class);


        MemberStatusEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }
        public static String getCodeName(int code){
            if(StringUtil.isEmpty(code))return "";
            Optional<String> codeName =  memberStatusEnum.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
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
}
