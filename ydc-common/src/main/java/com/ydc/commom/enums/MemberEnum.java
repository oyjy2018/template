package com.ydc.commom.enums;

import java.util.Objects;

public enum MemberEnum {
    ;

    /**
     * 身份实名状态
     */
    public enum WhetherRealNameStatus{

        status0(0, "否")
        ,status1(1, "是")
        ,status2(2, "认证中")
        ;

        private Integer status;
        private String statusCH;

        private WhetherRealNameStatus(Integer status, String statusCH){
            this.status = status;
            this.statusCH = statusCH;
        }

        public static WhetherRealNameStatus getWhetherRealNameStatusByStatus(Integer status){
            for(WhetherRealNameStatus wrns: WhetherRealNameStatus.values()){
                if(Objects.equals(wrns.status, status)) return wrns;
            }
            return null;
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
