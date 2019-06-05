package com.ydc.beans.security;

import com.ydc.commom.secret.RSACryptography;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

//@ControllerAdvice(basePackages = {"com.ydc.cgj.app.controller","com.ydc.cgj.web.controller","com.ydc.cgj.ydhc.app.controller","com.ydc.cgj.ydhc.web.controller"})
public class YDCRequestBodyAdvice implements RequestBodyAdvice {
    private static Logger logger = LogManager.getLogger(YDCRequestBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        try {
            boolean encode = true;
            if (parameter.getMethod().isAnnotationPresent(YDCSecurityParam.class)) {
                //获取注解配置的包含和去除字段
                YDCSecurityParam serializedField = parameter.getMethodAnnotation(YDCSecurityParam.class);
                //入参是否需要解密
                encode = serializedField.inDecode();
            }
            if (encode) {
                logger.info("对方法method :【" + parameter.getMethod().getName() + "】返回数据进行解密");
                return new YDCHttpInputMessage(inputMessage);
            }else{
                return inputMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("对方法method :【" + parameter.getMethod().getName() + "】返回数据进行解密出现异常："+e.getMessage());
            return inputMessage;
        }
    }



    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    class YDCHttpInputMessage implements HttpInputMessage {
        private HttpHeaders headers;

        private InputStream body;

        public YDCHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
            this.headers = inputMessage.getHeaders();
            this.body = IOUtils.toInputStream(RSACryptography.decrypt(easpString(IOUtils.toString(inputMessage.getBody(), "UTF-8"))), "UTF-8");
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }

        /**
         *
         * @param requestData
         * @return
         */
        public String easpString(String requestData){
            if(requestData != null && !requestData.equals("")){
                if(!requestData.startsWith("{\"data\":")){
                    throw new RuntimeException("参数【data】缺失异常！");
                }else{
                    int closeLen = requestData.length()-2;
                    int openLen = "{\"data\":".length()+1;
                    return StringUtils.substring(requestData,openLen,closeLen);
                }
            }
            return "";
        }
    }


}
