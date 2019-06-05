package com.ydc.commom.enums.cgj;

import com.ydc.commom.util.StringUtil;

import java.util.EnumSet;
import java.util.Optional;

/**
 * 车管家积分
 *
 * @author
 * @create 2018-12-07 16:30
 **/
public enum IntegralDetailEnum {
    ;

    public enum PayTypeEnum{
        PAY_TYPE_0(0,"获取"),
        PAY_TYPE_1(1,"消耗");
        private int code;
        private String name;

        private static EnumSet<PayTypeEnum> payTypeEnum = EnumSet.allOf(PayTypeEnum.class);


        PayTypeEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }
        public static String getCodeName(Integer code){
            if(StringUtil.isEmpty(code))return "";
            Optional<String> codeName =  payTypeEnum.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
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

    public enum IntegralTypeAcquireEnum{
        INTEGRAL_TYPE_ACQUIRE_0(0,"车主贷款"),
        INTEGRAL_TYPE_ACQUIRE_1(1,"每日签到"),
        INTEGRAL_TYPE_ACQUIRE_2(2,"意见采纳");
        private int code;
        private String name;

        private static EnumSet<IntegralTypeAcquireEnum> integralTypeAcquireEnum = EnumSet.allOf(IntegralTypeAcquireEnum.class);


        IntegralTypeAcquireEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }
        public static String getCodeName(Integer code){
            if(StringUtil.isEmpty(code))return "";
            Optional<String> codeName =  integralTypeAcquireEnum.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
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

    public enum IntegralTypeConsumeEnum{
        integral_type_consume_0(0,"商品兑换"),
        integral_type_consume_1(1,"积分抽奖"),
        integral_type_consume_2(2,"服务充值"),
        integral_type_consume_3(3,"服务查询");
        private int code;
        private String name;

        private static EnumSet<IntegralTypeConsumeEnum> integralTypeConsumeEnum = EnumSet.allOf(IntegralTypeConsumeEnum.class);


        IntegralTypeConsumeEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }
        public static String getCodeName(Integer code){
            if(StringUtil.isEmpty(code))return "";
            Optional<String> codeName =  integralTypeConsumeEnum.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
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
