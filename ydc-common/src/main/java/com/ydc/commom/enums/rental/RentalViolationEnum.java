package com.ydc.commom.enums.rental;

import java.util.Arrays;
import java.util.Optional;

/**
 *违章相关枚举
 * @author
 * @create 2018-11-23 17:56
 **/
public enum RentalViolationEnum {
    ;
    //处理状态枚举
    public enum DealStatusEnum {
        DEAL_STATUS_0(0,"未处理"),
        DEAL_STATUS_1(1,"已处理")
        ;
        private Integer code;
        private String name;

        DealStatusEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getCodeName(int code){
            Optional<String> codeName = Arrays.stream(RentalViolationEnum.DealStatusEnum.values()).filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
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
    //处理状态枚举
    public enum DealByEnum {
        DEAL_STATUS_0(0,"客户"),
        DEAL_STATUS_1(1,"员工")
        ;
        private Integer code;
        private String name;

        DealByEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getCodeName(int code){
            Optional<String> codeName = Arrays.stream(RentalViolationEnum.DealByEnum.values()).filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
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
