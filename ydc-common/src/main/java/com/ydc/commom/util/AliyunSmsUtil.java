package com.ydc.commom.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.net.UnknownHostException;
import java.util.EnumSet;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 * <p>
 * 备注:Demo工程编码采用UTF-8
 * 国际短信发送请勿参照此DEMO
 */
public class AliyunSmsUtil {
    private static Logger logger = LogManager.getLogger(AliyunSmsUtil.class);

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    //static final String accessKeyId = PropertiesUtil.getValue("aliyun.SMS.accessKeyId");
    //static final String accessKeySecret = PropertiesUtil.getValue("aliyun.SMS.accessKeySecret");

    /**
     * 一点车，有失效时间的验证码
     */
    public static enum ValidateCodeEnum{
        VALIDATE_CODE_REGISTER(1, "SMS_146281245"), //注册短信
        VALIDATE_CODE_PASSWORD(2, "SMS_146281246"), //修改密码短信
        VALIDATE_CODE_LOGIN(3, "SMS_146280847"), //登录短信
        VALIDATE_CODE_PAY(4, "SMS_146600096"),  //订单支付
        VALIDATE_CODE_APPLY(5, "SMS_157065318");  //贷款申请


        private static EnumSet<ValidateCodeEnum> validateCodeEnum = EnumSet.allOf(ValidateCodeEnum.class);
        private Integer validateType;
        private String validateCode;

        private ValidateCodeEnum(Integer validateType, String validateCode){
            this.validateCode = validateCode;
            this.validateType = validateType;
        }

        public Integer getValidateType() {
            return validateType;
        }

        public String getValidateCode() {
            return validateCode;
        }

        public static String getValidateCodeByType(Integer validateType){
            Optional<String> optValidateCode =  validateCodeEnum.stream()
                    .filter(tempValidateCode -> tempValidateCode.validateType.equals(validateType))
                    .map(tempvalidateCode -> tempvalidateCode.validateCode)
                    .findAny();
            return optValidateCode.orElse(null);
        }
    }

    /**
     * 发送短信验证码
     *
     * @param mobilePhone     手机号
     * @param code            验证码
     * @param modelCode       模板代码
     * @param accessKeyId
     * @param accessKeySecret
     * @param signName        签名
     * @return
     * @throws ClientException
     */
    public static SendSmsResponse sendValidateCode(String mobilePhone, String code, String modelCode, String accessKeyId, String accessKeySecret, String signName) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobilePhone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(modelCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        logger.info( "subject:{}, result:{}", "阿里云发送短信结果", sendSmsResponse.getMessage());
        return sendSmsResponse;
    }

    /**
     * 发送例子
     * @return
     * @throws ClientException
     */
    /*public static SendSmsResponse sendSms() throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers("15000000000");
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云通信");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_1000000");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }*/

    /**
     * 查询发送信息例子
     *
     * @return
     * @throws ClientException
     */
    /*public static QuerySendDetailsResponse querySendDetails(String bizId) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber("15000000000");
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;
    }*/
    public static void main(String[] args) throws ClientException, InterruptedException, UnknownHostException {
        String accessKeyId = "LTAIEy9n5lFYpIdy";
        String accessKeySecret = "RuM7nK6KKpaKYWycnW1JqccDVHjqrB";
        sendValidateCode("13243856965", "4569", ValidateCodeEnum.VALIDATE_CODE_LOGIN.getValidateCode(), accessKeyId, accessKeySecret, "联合车简");
        /*String accessKeyId = "LTAIvKg778Ozxw5h";
        String accessKeySecret = "XritJK3xgj6dB8Q37V02hI8AIS6jhT";
    	sendValidateCode("13243856965", "4567", TIMEOUT_VALIDATE_CODE, accessKeyId, accessKeySecret, "一点科技");*/
        //发短信
        /*SendSmsResponse response = sendSms();
       logger.info("短信接口返回的数据----------------");
       logger.info("Code=" + response.getCode());
       logger.info("Message=" + response.getMessage());
       logger.info("RequestId=" + response.getRequestId());
       logger.info("BizId=" + response.getBizId());

        Thread.sleep(3000L);

        //查明细
        if(response.getCode() != null && response.getCode().equals("OK")) {
            QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response.getBizId());
           logger.info("短信明细查询接口返回数据----------------");
           logger.info("Code=" + querySendDetailsResponse.getCode());
           logger.info("Message=" + querySendDetailsResponse.getMessage());
            int i = 0;
            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
            {
               logger.info("SmsSendDetailDTO["+i+"]:");
               logger.info("Content=" + smsSendDetailDTO.getContent());
               logger.info("ErrCode=" + smsSendDetailDTO.getErrCode());
               logger.info("OutId=" + smsSendDetailDTO.getOutId());
               logger.info("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
               logger.info("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
               logger.info("SendDate=" + smsSendDetailDTO.getSendDate());
               logger.info("SendStatus=" + smsSendDetailDTO.getSendStatus());
               logger.info("Template=" + smsSendDetailDTO.getTemplateCode());
            }
           logger.info("TotalCount=" + querySendDetailsResponse.getTotalCount());
           logger.info("RequestId=" + querySendDetailsResponse.getRequestId());
        }*/

    }
}
