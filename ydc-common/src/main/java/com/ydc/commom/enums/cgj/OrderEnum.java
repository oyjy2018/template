package com.ydc.commom.enums.cgj;

import com.ydc.commom.util.StringUtil;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.EnumSet;
import java.util.Optional;

/**
 * 车管家订单枚举
 *
 * @author
 * @create 2018-12-07 15:55
 **/
public enum OrderEnum {
    ;

    public enum LogisticsStatusEnum{
        LOGISTICS_STATUS_0(0,"待发货"),
        LOGISTICS_STATUS_1(1,"全部发货"),
        LOGISTICS_STATUS_2(2,"部分发货");
        private int code;
        private String name;

        private static EnumSet<LogisticsStatusEnum> logisticsStatusEnum = EnumSet.allOf(LogisticsStatusEnum.class);

        LogisticsStatusEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getCodeName(Integer code){
            if(StringUtil.isEmpty(code))return "";
            Optional<String> codeName =  logisticsStatusEnum.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
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

    public enum OrderStatusEnum{
        ORDER_STATUS_0(0,"创建订单"),
        ORDER_STATUS_1(1,"待付款"),
        ORDER_STATUS_2(2,"待发货"),
        ORDER_STATUS_3(3,"待收货"),
        ORDER_STATUS_4(4,"已收货"),
        ORDER_STATUS_8(8,"订单关闭");
        private int code;
        private String name;

        private static EnumSet<OrderStatusEnum> orderStatusEnum = EnumSet.allOf(OrderStatusEnum.class);
        OrderStatusEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getCodeName(Integer code){
            if(StringUtil.isEmpty(code))return "";
            Optional<String> codeName =  orderStatusEnum.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
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
