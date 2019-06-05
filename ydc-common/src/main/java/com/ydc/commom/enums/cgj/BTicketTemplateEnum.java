package com.ydc.commom.enums.cgj;

import com.ydc.commom.util.StringUtil;

import java.util.EnumSet;
import java.util.Optional;

/**
 * 车管家B端券
 *
 * @author
 * @create 2018-12-07 16:16
 **/
public class BTicketTemplateEnum {
    ;

    public enum TemplateStatusEnum{
        STATUS_1(1,"已激活"),
        STATUS_0(0,"未激活");
        private int code;
        private String name;


        private static EnumSet<TemplateStatusEnum> templateStatusEnum = EnumSet.allOf(TemplateStatusEnum.class);
        TemplateStatusEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getCodeName(Integer code){
            if(StringUtil.isEmpty(code))return "";
            Optional<String> codeName =  templateStatusEnum.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
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

    public enum TemplateTypeEnum{
        TICKET_TYPE_1(1,"洗车劵"),
        TICKET_TYPE_2(2,"保养劵"),
        TICKET_TYPE_3(3,"车险劵");
        private int code;
        private String name;
        private static EnumSet<TemplateTypeEnum> templateTypeEnum = EnumSet.allOf(TemplateTypeEnum.class);

        TemplateTypeEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }
        public static String getCodeName(Integer code){
            if(StringUtil.isEmpty(code))return "";
            Optional<String> codeName =  templateTypeEnum.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
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
