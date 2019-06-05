package com.ydc.commom.constant;

import java.util.Calendar;
import java.util.EnumSet;

public class RollConstant {

    /**
     * 券类型
     */
    public static final String ROLL_TYPE = "rollType";

    /**
     * 券数量
     */
    public static final String ROLL_NUM = "rollNum";

    /**
     * 会员id
     */
    public static final String MEMBER_ID = "memberId";

    /**
     * 会员手机号码
     */
    public static final String MEMBER_PHONE = "memberPhone";

    /**
     * 借款单id
     */
    public static final String LOAN_ID = "loanId";

    /**
     * 订单号
     */
    public static final String ORDER_NO = "orderNo";

    /**
     * 券状态enum
     */
    public enum RollStatusEnum {
        ROLL_STATUS_0(0, "未使用"),
        ROLL_STATUS_1(1, "冻结/使用中"),
        ROLL_STATUS_2(2, "已使用"),
        ROLL_STATUS_3(3, "过期");

        private Integer key;
        private String value;
        private static EnumSet<RollStatusEnum> rollStatusEnum = EnumSet.allOf(RollStatusEnum.class);

        RollStatusEnum(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public static String getValueByKey(Integer rollStatus) {
            return rollStatusEnum.stream()
                    .filter(rollStatusTitle -> rollStatusTitle.key.equals(rollStatus))
                    .findAny().get().value;
        }
    }

    /**
     * 券类型enum
     */
    public enum RollTypeEnum {
        ROLL_TYPE_1(1, "洗车券"),
        ROLL_TYPE_2(2, "保养券"),
        ROLL_TYPE_3(3, "车险券");

        private Integer key;
        private String value;
        private static EnumSet<RollTypeEnum> rollTypeEnum = EnumSet.allOf(RollTypeEnum.class);
        RollTypeEnum(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public static String getValueByKey(Integer rollType) {
            return rollTypeEnum.stream()
                    .filter(rollTypeTitle -> rollTypeTitle.key.equals(rollType))
                    .findAny().get().value;
        }

    }

    /**
     * 券类型与订单title的enum
     */
    public enum RollTypeTitleEnum {
        TYPE_TITLE_1(1, "标准洗车"),
        TYPE_TITLE_2(2, "常规保养"),
        TYPE_TITLE_3(3, "普通车险");

        private static EnumSet<RollTypeTitleEnum> rollTypeTitleEnum = EnumSet.allOf(RollTypeTitleEnum.class);
        private Integer rollType;
        private String title;

        public Integer getRollType() {
            return rollType;
        }

        public String getTitle() {
            return title;
        }

        RollTypeTitleEnum(Integer rollType, String title) {
            this.rollType = rollType;
            this.title = title;
        }

        public static String getTitleByType(Integer rollType) {
            return rollTypeTitleEnum.stream()
                    .filter(rollTypeTitle -> rollTypeTitle.rollType.equals(rollType))
                    .findAny().get().title;
        }
    }

    /**
     * 预约订单
     */
    public static final Integer APPOINT_TYPE_1 = 1;

    /**
     * 扫码订单
     */
    public static final Integer APPOINT_TYPE_2 = 2;

    /**
     * 订单状态enum
     */
    public enum AppointStatusEnum {
        APPOINT_STATUS_ENUM_1(1, "预约中"),
        APPOINT_STATUS_ENUM_2(2, "待服务"),
        APPOINT_STATUS_ENUM_3(3, "已完成"),
        APPOINT_STATUS_ENUM_4(4, "预约关闭"),
        APPOINT_STATUS_ENUM_8(8, "创建失败");

        private Integer key;
        private String value;

        AppointStatusEnum(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    public enum AppointRollStatusEnum{
        APPOINT_ROLL_STATUS_ENUM_0(0, "未使用"),
        APPOINT_ROLL_STATUS_ENUM_1(1, "使用中"),
        APPOINT_ROLL_STATUS_ENUM_2(2, "使用完成"),
        APPOINT_ROLL_STATUS_ENUM_3(3, "退回");

        private Integer key;
        private String value;

        AppointRollStatusEnum(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    //券商品code
    public static final String ROLL_COMMODITY_CODE = "ROLL_10001";
    //洗车券code
    public static final String WASH_ROLL_CODE = "WASH_ROLL_10001";
    //保养券code
    public static final String MAINTAIN_ROLL_CODE = "MAINTAIN_ROLL_10001";
}
