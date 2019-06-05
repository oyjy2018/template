package com.ydc.beans.config;

import com.ydc.beans.commom.EnvironmentFactory;

/***
 * 获取system.properties 配置
 */
public class SystemPropertiesConfig {

    //车管家登录start
    public static String CGJ_REDIRECT_URL_FAILURE = EnvironmentFactory.getProperty("cgj.redirect.url.failure");

    public static String CGJ_REDIRECT_URL_SUCCEED = EnvironmentFactory.getProperty("cgj.redirect.url.succeed");

    public static String CGJ_DD_REDIRECTURI = EnvironmentFactory.getProperty("cgj.dd.redirectUri");
    //车管家登录end

    //租车登录start
    public static String RENTAL_REDIRECT_URL_FAILURE = EnvironmentFactory.getProperty("rental.redirect.url.failure");

    public static String RENTAL_REDIRECT_URL_SUCCEED = EnvironmentFactory.getProperty("rental.redirect.url.succeed");

    public static String RENTAL_DD_REDIRECTURI = EnvironmentFactory.getProperty("rental.dd.redirectUri");
    //租车登录end

    //一点好车登录start
    public static String YDHC_REDIRECT_URL_FAILURE = EnvironmentFactory.getProperty("ydhc.redirect.url.failure");

    public static String YDHC_REDIRECT_URL_SUCCEED = EnvironmentFactory.getProperty("ydhc.redirect.url.succeed");

    public static String YDHC_DD_REDIRECTURI = EnvironmentFactory.getProperty("ydhc.dd.redirectUri");
    //一点好车登录end




    public static String SYS_XINGNENG = EnvironmentFactory.getProperty("sys.xingneng");

    public static String FILE_SERVICE_PUBLIC_URL = EnvironmentFactory.getProperty("file.service.public.url");

    public static String CGJ_WEB_SERVICE_URL = EnvironmentFactory.getProperty("cgj.web.service.url");

    public static String WEIXIN_APPID = EnvironmentFactory.getProperty("weixin.appid");

    public static String WEIXIN_SECRET = EnvironmentFactory.getProperty("weixin.secret");

    public static String SPI_API_SERVICE_URL = EnvironmentFactory.getProperty("spi.api.service.url");

    public static String B_SERVICE_URL = EnvironmentFactory.getProperty("b.service.url");

    public static String SELF_BRIDGE_SERVICE_URL = EnvironmentFactory.getProperty("self.bridge.service.url");


    public static String ORDERNO_MERCHANT_KEY_PREFIX = EnvironmentFactory.getProperty("orderNo.merchant.key.prefix");
    public static String ORDERNO_MERCHANT_NO_PREFIX = EnvironmentFactory.getProperty("orderNo.merchant.no.prefix");
    public static String ORDERNO_MERCHANT_NO_SUFFIX = EnvironmentFactory.getProperty("orderNo.merchant.no.suffix");
    public static String ORDERNO_MEMBER_KEY_PREFIX = EnvironmentFactory.getProperty("orderNo.member.key.prefix");

    public static String ORDERNO_MEMBER_NO_PREFIX = EnvironmentFactory.getProperty("orderNo.member.no.prefix");
    public static String ORDERNO_MEMBER_NO_SUFFIX = EnvironmentFactory.getProperty("orderNo.member.no.suffix");
    public static String ORDERNO_PAYWATER_KEY_PREFIX = EnvironmentFactory.getProperty("orderNo.payWater.key.prefix");
    public static String ORDERNO_PAYWATER_NO_PREFIX = EnvironmentFactory.getProperty("orderNo.payWater.no.prefix");
    public static String ORDERNO_PAYWATER_NO_SUFFIX = EnvironmentFactory.getProperty("orderNo.payWater.no.suffix");
    public static String ORDERNO_APPOINTMENT_KEY_PREFIX = EnvironmentFactory.getProperty("orderNo.appointment.key.prefix");
    public static String ORDERNO_APPOINTMENT_NO_PREFIX = EnvironmentFactory.getProperty("orderNo.appointment.no.prefix");
    public static String ORDERNO_APPOINTMENT_NO_SUFFIX = EnvironmentFactory.getProperty("orderNo.appointment.no.suffix");

    //阿里云:OCR:start
    public static String ALIYUN_APPKEY = EnvironmentFactory.getProperty("aliyun.appKey");
    public static String ALIYUN_APPSECRET = EnvironmentFactory.getProperty("aliyun.appSecret");
    public static String ALIYUN_APPCODE = EnvironmentFactory.getProperty("aliyun.appCode");
    public static String ALIYUN_OCR_IDCARD_HOST = EnvironmentFactory.getProperty("aliyun.OCR.idCard.host");
    public static String ALIYUN_OCR_IDCARD_PATH = EnvironmentFactory.getProperty("aliyun.OCR.idCard.path");
    public static String ALIYUN_OCR_BUSINESSLICENSE_HOST = EnvironmentFactory.getProperty("aliyun.OCR.businessLicense.host");
    public static String ALIYUN_OCR_BUSINESSLICENSE_PATH = EnvironmentFactory.getProperty("aliyun.OCR.businessLicense.path");
    public static String ALIYUN_OCR_VEHICLE_HOST = EnvironmentFactory.getProperty("aliyun.OCR.vehicle.host");
    public static String ALIYUN_OCR_VEHICLE_PATH = EnvironmentFactory.getProperty("aliyun.OCR.vehicle.path");
    public static String ALIYUN_OCR_SWITCH = EnvironmentFactory.getProperty("aliyun.OCR.switch");//阿里云图片识别开关,on开 off关
    //阿里云:OCR:end



    //天秤start
    public static String NEW_TIANCHENG_API_URL = EnvironmentFactory.getProperty("new.tiancheng.api.url");//新天秤API地址
    public static String NEW_TIANCHENG_ID = EnvironmentFactory.getProperty("new.tiancheng.id");//组织编码
    public static String NEW_TIANCHENG_API_USERNAME = EnvironmentFactory.getProperty("new.tiancheng.api.userName"); //用户名
    public static String NEW_TIANCHENG_API_PASSWORD = EnvironmentFactory.getProperty("new.tiancheng.api.password");//密码明文
    public static String NEW_TIANCHENG_JMMY = EnvironmentFactory.getProperty("new.tiancheng.jmmy");//加密密钥
    public static String NEW_TIANCHENG_ORGNAME = EnvironmentFactory.getProperty("new.tiancheng.orgName"); //机构名
    public static String NEW_TIANCHENG_SWITCH = EnvironmentFactory.getProperty("new.tiancheng.switch");//是否调用天秤配置开关:on开 off关
    //天秤end

    //阿里云:失信名单:start
    public static String ALIYUN_CRACKCREDIT_APPCODE = EnvironmentFactory.getProperty("aliyun.crackCredit.appCode");
    public static String ALIYUN_CRACKCREDIT_SWITCH = EnvironmentFactory.getProperty("aliyun.crackCredit.switch");//阿里云失效名单接口配置开关:on开 off关
    //阿里云:失信名单end

    //阿里当面资金授权 start
    public static String  ALIPAY_SERVER_URL=EnvironmentFactory.getProperty("alipay.server.url");
    public static String ALIPAY_APP_ID=EnvironmentFactory.getProperty("alipay.app.id");
    public static String  ALIPAY_PRIVATE_KEY=EnvironmentFactory.getProperty("alipay.private.key");
    public static String ALIPAY_PUBLIC_KEY=EnvironmentFactory.getProperty("alipay.public.key");
    public static String ALIPAY_NOTIFY_URL=EnvironmentFactory.getProperty("alipay.notify.url");
    public static String ALIPAY_RETURN_URL=EnvironmentFactory.getProperty("alipay.return.url");
    public static String ALIPAY_PAYEE_USER_ID=EnvironmentFactory.getProperty("alipay.payee.user.id");
    //阿里当面资金授权 end
    //加解密开关
    public final static String ENCRYPT_SWITCH=EnvironmentFactory.getProperty("encrypt.switch");

    //微信登录
    public static String WEIXIN_LOGIN_APPID = EnvironmentFactory.getProperty("weixin.login.appid");
    public static String WEIXIN_LOGIN_SECRET = EnvironmentFactory.getProperty("weixin.login.secret");

    //bridge 地址
    public static String BRIDGE_URL=EnvironmentFactory.getProperty("bridge.url");

    public final  static  String  publicKeyString="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWnqcqa1YnrRbnm/5/E2m0MiVtMX8GY5gs/cgY3xrm1pxLC99/CtkEVrjfIBeNVYwgqTDmkNrSyBlV4JRiQNmVt7gC/k2JTHoUkWECJ54AL/qvnlslUx/5SgZste7d4LF2Ztb+fCDmeBgt3assn6h5YQt8f5ir36YwSa/jHEhgYQIDAQAB";

    public final  static  String  privateKeyString="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJaepyprVietFueb/n8TabQyJW0xfwZjmCz9yBjfGubWnEsL338K2QRWuN8gF41VjCCpMOaQ2tLIGVXglGJA2ZW3uAL+TYlMehSRYQInngAv+q+eWyVTH/lKBmy17t3gsXZm1v58IOZ4GC3dqyyfqHlhC3x/mKvfpjBJr+McSGBhAgMBAAECgYASK317DovMKYNT2WuwJMVhqZL+QzRRrv4Sn2LeLTvvkQA1xW7mwoY/kb6nnBV7RVCsN/zgM6VSPDViOL9twuj8iVShytMAFZVpz61sbQ4LytiX5VG7CLagVHPliTDXrCE/PS/GnCHG+q+a40eXOzGMk+YVECKeD65L23B/EPdBgQJBAMgFfItytBU93CsihmEb+m4tlO53jD2CQIEgArZsC98cJDrJRkuatr0/dqCtATG0pvDuTfA62N+1GQ1VI8XDgJkCQQDAxcmflDbKpbxIOENaaBc2rqSATUbCR57pA58hsUpuF8Ub1JtfuOxMeZiDTsfNQVkomLbwhKym0bJYxekPuZMJAkAflR4xNo9Qu0/ET9NAChbkxrW5YY73N1XQDanf+/dz5xGhZp5+2ZL5Fsg4XpjNrGSfrkEIKYKM8B9gh4z6cQAhAkEAiHk+NaCbbboJ4uMaI93/WTIoucB7sL09rl9nAFZgiDKB1CMfYJQtjjKjm4phf1mdQlVjZkG3bV6Vsa1jQ0Ow6QJAWk3svCrhdZVpKoEM4cxkR2znNmRPndoJPZZPVyryvhgutO+h9sqRN5FpkIHlrRM0uKQoPVs4VeBMQcFtlvjV8Q==";

    public final  static  String  CGJ_VIOLATION_RECORD_URL=EnvironmentFactory.getProperty("bridge.cgjviolation.url");
    public final  static  String  THIRD_VIOLATION_RECORD_URL=EnvironmentFactory.getProperty("third.cgjviolation.url");
    public final  static  String  THIRD_VIOLATION_APPKEY=EnvironmentFactory.getProperty("third.cgjviolation.APPKEY");
    //车300-token
    public static final String CHE300_TOKEN = EnvironmentFactory.getProperty("che300.token");
    //#车300-车辆估值接口地址
    public static final String CHE300_CARPRICE_URL = EnvironmentFactory.getProperty("che300.carPrice.url");
    //车300-维保和保险-商户id
    public static final String CHE300_WXANDBX_ID = EnvironmentFactory.getProperty("che300.wxAndBx.id");
    //车300-维保和保险-token
    public static final String CHE300_WXANDBX_TOKEN = EnvironmentFactory.getProperty("che300.wxAndBx.token");
    //#车300-维保和保险接口地址
    public static final String CHE300_WXANDBX_URL = EnvironmentFactory.getProperty("che300.wxAndBx.url");
    //微信支付配置
    public final static String WEIXIN_PAY_APPID = EnvironmentFactory.getProperty("weixin.pay.appid");
    public final static String WEIXIN_PAY_NOTIFYURL = EnvironmentFactory.getProperty("weixin.pay.notifyUrl");
    public final static String WEIXIN_PAY_SECRET = EnvironmentFactory.getProperty("weixin.pay.secret");
    public final static String WEIXIN_PAY_OPENIDNOTIFYURL = EnvironmentFactory.getProperty("weixin.pay.openIdNotifyUrl");
    public final static String PAY_SEND_ROLL_URL = EnvironmentFactory.getProperty("pay.send.roll.url");
    public final static String WEIXIN_PAY_MCHID = EnvironmentFactory.getProperty("weixin.pay.mchid");
    public final static String WEIXIN_PAY_APIKEY = EnvironmentFactory.getProperty("weixin.pay.apikey");
    //APP内支付
    public final static String WEIXIN_PAY_OPEN_APPID = EnvironmentFactory.getProperty("weixin.pay.open.appid");

    //mongodb服务器地址
    public final static String MONGODB_CGJ_URI = EnvironmentFactory.getProperty("mongodb.cgj.uri");


   // public final  static  String  publicKeyString="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWnqcqa1YnrRbnm/5/E2m0MiVtMX8GY5gs/cgY3xrm1pxLC99/CtkEVrjfIBeNVYwgqTDmkNrSyBlV4JRiQNmVt7gC/k2JTHoUkWECJ54AL/qvnlslUx/5SgZste7d4LF2Ztb+fCDmeBgt3assn6h5YQt8f5ir36YwSa/jHEhgYQIDAQAB";

   // public final  static  String  privateKeyString="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJaepyprVietFueb/n8TabQyJW0xfwZjmCz9yBjfGubWnEsL338K2QRWuN8gF41VjCCpMOaQ2tLIGVXglGJA2ZW3uAL+TYlMehSRYQInngAv+q+eWyVTH/lKBmy17t3gsXZm1v58IOZ4GC3dqyyfqHlhC3x/mKvfpjBJr+McSGBhAgMBAAECgYASK317DovMKYNT2WuwJMVhqZL+QzRRrv4Sn2LeLTvvkQA1xW7mwoY/kb6nnBV7RVCsN/zgM6VSPDViOL9twuj8iVShytMAFZVpz61sbQ4LytiX5VG7CLagVHPliTDXrCE/PS/GnCHG+q+a40eXOzGMk+YVECKeD65L23B/EPdBgQJBAMgFfItytBU93CsihmEb+m4tlO53jD2CQIEgArZsC98cJDrJRkuatr0/dqCtATG0pvDuTfA62N+1GQ1VI8XDgJkCQQDAxcmflDbKpbxIOENaaBc2rqSATUbCR57pA58hsUpuF8Ub1JtfuOxMeZiDTsfNQVkomLbwhKym0bJYxekPuZMJAkAflR4xNo9Qu0/ET9NAChbkxrW5YY73N1XQDanf+/dz5xGhZp5+2ZL5Fsg4XpjNrGSfrkEIKYKM8B9gh4z6cQAhAkEAiHk+NaCbbboJ4uMaI93/WTIoucB7sL09rl9nAFZgiDKB1CMfYJQtjjKjm4phf1mdQlVjZkG3bV6Vsa1jQ0Ow6QJAWk3svCrhdZVpKoEM4cxkR2znNmRPndoJPZZPVyryvhgutO+h9sqRN5FpkIHlrRM0uKQoPVs4VeBMQcFtlvjV8Q==";


//    public final  static  String  publicKeyString="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1EjFA0+nr4Rn7ouVXOKW9o5BjkuQ6VKEHUI49r5JUlcoAKje5I7YlZ5CB6Ij8ccFGE47WlY8vhRcJ0tYtOT3NmQNBemVmf0oWFOIVvAfipjXrRh6dWr6euIeC+3YGf2w2QnvAA4KBvWsbrEUxrYPJ3+QlgaGo8UuJlwChml+XXQIDAQAB";
//
//    public final  static  String  privateKeyString="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALUSMUDT6evhGfui5Vc4pb2jkGOS5DpUoQdQjj2vklSVygAqN7kjtiVnkIHoiPxxwUYTjtaVjy+FFwnS1i05Pc2ZA0F6ZWZ/ShYU4hW8B+KmNetGHp1avp64h4L7dgZ/bDZCe8ADgoG9axusRTGtg8nf5CWBoajxS4mXAKGaX5ddAgMBAAECgYAZaFAY9KNiQdfPg3AC6SP4lDbyge4ccTuXNyp+ZWB3Ff2eQE5JYEVywjF4yRRPt/aSyrPEUIAi/t3Ytwjul5WgQtqTj7/an6G/1tI2lcBSr1SjCv5yQTE1WsEOLWCqhC5JIWiAJgD2G/EBAKxvyZ73i88sTwrpXnNIxcAKxud5UQJBAPZ7B+0q7XTSA5CbA0hvaWIteawOTI3vxLkgB7bhgmA4dM4LffZaB+/GKCdfTYHRWUiG6fihe9kt6STNaO3S3aMCQQC8EHQO+HxlUo+pTQvFeGRXUN5K2bfPt9eMiE39rj3Vr+CkYtozbrx6fQ+IiFeWEta8XnRDbUpXkZEM9S7ngQb/AkEA3uNix5WKJZnEn3+N+HLHWYDGBlDn6XJo8v+D+lzzCRAEkE7UGXJQuwL2TKRh8oLdujhGXA+rbzmVGzKlMGr/AQJBAJipgozkiZZ2fsggEv4pzMpUix6Xve6QfhlGTIkxeGCOdPz8gcRekAGeo41hoDfi8w3WXuqdPUv4jpbGMV0cEGECQHcA2FnJ07dIB8pUDmpkyT2+jlfbJnAIBTtVPRuHnTvBPdZjhFxZfDg7pp1g362jk2BAivR/l809c/hN+AKDydo=";
//


}
