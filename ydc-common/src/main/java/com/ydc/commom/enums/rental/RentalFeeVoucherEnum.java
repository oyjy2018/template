package com.ydc.commom.enums.rental;

import java.util.Arrays;

public enum RentalFeeVoucherEnum {
    ;

    public enum VoucherType{

        STATUS1(1, "平台收取租金")
        ,STATUS2(2, "租金转移至资源方")
        ,STATUS3(3, "押金退还至平台")
        ,STATUS4(4, "押金退还至需求方")
        ;

        Integer status;
        String statusCH;

        VoucherType(Integer status, String statusCH){
            this.status = status;
            this.statusCH = statusCH;
        }

        public static String getVoucherCH(Integer status){
            return Arrays.asList(VoucherType.values()).stream().filter(item ->item.status.equals(status)).map(item ->item.statusCH).findAny().orElse("");
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
