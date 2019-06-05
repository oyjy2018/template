package com.ydc.commom.constant;

/**
 * redis常量
 */
public class RedisConstant {

    /**
     * 短信验证码key
     */
    public static final String VALIDATECODE_KEY = "validateCode_key:";

    /**
     * 校验标识的key
     */
    public static final String SIGNVALIDATECODE_SIGN = "signValidateCode_sign:";

    /**
     * H5配置key
     */
    public static final String H5_CONFIG_KEY = "cgj:h5_config_key";

    /**
     * 车管家密码错误key
     */
    public static final String CGJ_PASSWORD_FAULT_KEY = "CGJ:PASSWORD_FAULT_KEY:";

    /**
     * 车管家密码错误key
     */
    public static final String RENTAL_PASSWORD_FAULT_KEY = "RENTAL:PASSWORD_FAULT_KEY:";

    /**
     * 地址配置key
     */
    public static final String REGION_CONFIG_KEY = "region_config_key:";

    /**
     * 一点好车短信验证码key
     */
    public static final String YDHC_VALIDATECODE_KEY = "ydhc:validateCode_key:";

    /**
     * 一点好车车品牌配置key
     */
    public static final String YDHC_CAR_BRAND_KEY = "ydhc:car_brand_key";

    /**
     * 一点好车车系配置key
     */
    public static final String YDHC_CAR_SERIES_KEY = "ydhc:car_series_key";

    /**
     * 违章类型--机动车通行
     */
    public static final String VIOLATION_TYPE_MOTOR_VEHICLE = "violation_type_motor_vehicle";

    /**
     * 违章类型--高速公路特别规定
     */
    public static final String VIOLATION_TYPE_SPECIAL_PROVISIONS = "violation_type_special_provisions";

    /**
     * 违章类型--其他规定
     */
    public static final String VIOLATION_TYPE_OTHER_PROVISIONS = "violation_type_other_provisions";

    /***************************************************秘钥 start**********************************************************************/


    /**
     * 私钥
     */
    public static final String RAS_PRIVATE_KEY = "RAS:KEY:PRIVATE";

    /**
     * 公钥
     */
    public static final String RAS_PUBLIC_KEY = "RAS:KEY:PUBLIC";



    /***************************************************秘钥 end**********************************************************************/

    /***************************************************车辆配置 start**********************************************************************/

    /**
     * 车辆配置key(车品牌)
     */
    public static final String CAR_BRAND_KEY = "CAR:BRAND";

    /**
     * 车辆配置key（车型）
     */
    public static final String CAR_SERIES_KEY = "CAR:SERIES";

    /**
     * 车辆配置key（车系）
     */
    public static final String CAR_MODEL_KEY = "CAR:MODEL";

    /***************************************************车辆配置 start**********************************************************************/

    /***************************************************车管APP缓存 start**********************************************************************/

    /**
     * 车管家公共版本缓存
     */
    public static final String CGJ_COMMON_VERSION = "CGJ:COMMON:VERSION";
    /**
     * 广告缓存:APP
     */
    public static final String CGJ_APP_ADVERT_VERSION = "CGJ:APP:ADVERT:VERSION";
    /**
     * 广告缓存:H5
     */
    public static final String CGJ_H5_ADVERT_VERSION = "CGJ:H5:ADVERT:VERSION";
    /**
     * 广告缓存:小程序
     */
    public static final String CGJ_MINIPROGRAM_ADVERT_VERSION = "CGJ:MINIPROGRAM:ADVERT:VERSION";
    /**
     * 服务缓存
     */
    public static final String CGJ_SERVICE_VERSION = "CGJ:SERVICE:VERSION";
    /**
     * 功能版本类型,1：广告；2：服务
     */
    public static final Integer CGJ_VERSION_TYPE_1 = 1;
    public static final Integer CGJ_VERSION_TYPE_2 = 2;
    /**
     * 车300城市列表缓存key
     */
    public static final String CHE300_CITY_LIST = "che300_city_list";
    /**
     * 按钮缓存:APP
     */
    public static final String CGJ_APP_BUTTON = "CGJ:APP:BUTTON";
    /**
     * 按钮缓存:H5
     */
    public static final String CGJ_H5_BUTTON = "CGJ:H5:BUTTON";
    /**
     * 按钮缓存:小程序
     */
    public static final String CGJ_MINIPROGRAM_BUTTON = "CGJ:MINIPROGRAM:BUTTON";
    /**
     * 指引页
     */
    public static final String CGJ_APP_STARTUP_PAGE = "CGJ:APP:STARTUP:PAGE";
    /**
     * 一点车ios最新版本信息
     */
    public static final String CGJ_APP_IOS_VERSION = "CGJ:APP:IOS:VERSION";
    /**
     * 一点车android最新版本信息
     */
    public static final String CGJ_APP_ANDROID_VERSION = "CGJ:APP:ANDROID:VERSION";
    /**
     * 申请人数缓存,1、车主贷；2、车抵贷；3、我要出租；4、我要租车；5、购车分期；6、我要买车；7、我要卖车；100、车辆估价
     */
    public static final String CGJ_APPLY_NUM = "CGJ:APPLY:NUM";
    /**
     * 虚拟申请人数缓存,1、车主贷；2、车抵贷；3、我要出租；4、我要租车；5、购车分期；6、我要买车；7、我要卖车；100、车辆估价
     */
    public static final String CGJ_APPLY_SHOW_NUM = "CGJ:APPLY:SHOW:NUM";

    /***************************************************车管家APP缓存 end**********************************************************************/

    /**
     * 用户openid的key
     */
    public static final String MEMBER_OPENID_KEY = "MEMBER:OPENID:";

    /***************************************************节假日配置 start**********************************************************************/

    public static final String RENTAL_HOLIDAY_KEY = "RENTAL:HOLIDAY:KEY";

    /***************************************************节假日配置 start**********************************************************************/
}
