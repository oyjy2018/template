package com.ydc.commom.constant;

import java.util.EnumSet;

public class MemberConstant {

    //用户状态正常
    public static final int MEMBER_NORMAL_STATUS = 1;
    //用户状态注销
    public static final int MEMBER_LOGOFF_STATUS = 2;
    //用户状态锁定
    public static final int MEMBER_LOCKED_STATUS = 3;

    //应用---车管家
    public static final int APPLICATION_CGJ = 1;
    //应用---租车
    public static final int APPLICATION_RENTAL = 2;

    //身份证头像面
    public static final int MEMBER_FILE_TYPE_1 = 1;
    //身份证国徽面
    public static final int MEMBER_FILE_TYPE_2 = 2;
    //驾驶证正面
    public static final int MEMBER_FILE_TYPE_3 = 3;
    //驾驶证反面
    public static final int MEMBER_FILE_TYPE_4 = 4;

    /**
     * 是否实名（1：是）
     */
    public static final int WHETHER_REAL_NAME_1 = 1;

    /**
     * 是否实名（0：否；）
     */
    public static final int WHETHER_REAL_NAME_0 = 0;

    /**
     * 是否实名（2：认证中）
     */
    public static final int WHETHER_REAL_NAME_2 = 2;

    /**
     * 身份证认证
     */
    public static final int IDENTITY_CERTIFICATE = 1;

    /**
     * 驾驶证认证
     */
    public static final int DRIVERS_LICENSE_CERTIFICATE = 2;

    /**
     * 身份认证有效期为长期
     */
    public static final String CERTIFICATE_FOREVER_VALID_TIME = "长期";

    //ocr识别正面
    public static final String OCR_FACE = "face";
    //ocr识别反面
    public static final String OCR_SIDE = "side";

    public enum OCRFileTypeEnum{
        FILE_TYPE_FACE(MEMBER_FILE_TYPE_1, OCR_FACE),
        FILE_TYPE_SIDE(MEMBER_FILE_TYPE_2, OCR_SIDE);

        private static EnumSet<OCRFileTypeEnum> ocrFileTypeEnums = EnumSet.allOf(OCRFileTypeEnum.class);
        private Integer fileType;
        private String ocrType;
        private OCRFileTypeEnum(Integer fileType, String ocrType){
            this.fileType = fileType;
            this.ocrType = ocrType;
        }
        public Integer getFileType() {
            return fileType;
        }
        public String getOcrType() {
            return ocrType;
        }

        public static OCRFileTypeEnum getOCRFileTypeEnumByKey(final Integer fileType) {
            return ocrFileTypeEnums.parallelStream()
                    .filter(ocrFileTypeEnum -> ocrFileTypeEnum.fileType.equals(fileType))
                    .findAny().orElse(null);
        }

        public static String getOCRType(final Integer fileType){
            OCRFileTypeEnum ocrFileTypeEnum = getOCRFileTypeEnumByKey(fileType);
            return ocrFileTypeEnum == null ? null : ocrFileTypeEnum.ocrType;
        }
    }

    //用户openid
    public static final String WEIXIN_OPENID = "openid";
    //微信unionid
    public static final String WEIXIN_UNIONID = "unionid";
    //微信昵称
    public static final String WEIXIN_NICKNAME = "nickname";
    //微信地址省份
    public static final String WEIXIN_PROVINCE = "province";
    //微信城市
    public static final String WEIXIN_CITY = "city";
}
