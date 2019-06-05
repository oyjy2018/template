package com.ydc.commom.enums.common;

public class HolidayEnum {
    ;

    public enum HolidayType{

        STATUS1(1,"节假日")
        ,STATUS2(2,"节假日调班")
        ;

        Integer status;
        String statusCH;

        private HolidayType(Integer status, String statusCH){
            this.status = status;
            this.statusCH = statusCH;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getStatusCH() {
            return statusCH;
        }

        public void setStatusCH(String statusCH) {
            this.statusCH = statusCH;
        }
    }
}
