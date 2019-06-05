package com.ydc.beans.filter;


import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.beans.security.rsa.RSA2Util;
import com.ydc.beans.security.rsa.RSAUtil2;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.StringUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class YDCHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final Logger logger = LogManager.getLogger(YDCHttpServletRequestWrapper.class);

    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_DELETE = "DELETE";


    private Map<String, String[]> params = new HashMap<String, String[]>();

    public YDCHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        logger.info("开始解析请求");
        //替换参数
        this.params.putAll(request.getParameterMap());
        String requestURI = WebUtils.getPathWithinApplication(request);
        logger.info("请求uri:" + requestURI);

        String method = request.getMethod();
        logger.info("请求方式:" + method + " ContentType:" + request.getContentType());
        try {
            if (METHOD_POST.equals(method)) {
                doPost(request);
            } else if (METHOD_GET.equals(method)) {
                //  doGet(request);
            } else if (METHOD_PUT.equals(method)) {
                doPost(request);
            } else if (METHOD_DELETE.equals(method)) {
                // doGet(request);
            }
        } catch (ServletException e) {
            logger.error("拦截异常",e);
        } catch (IOException e) {
            logger.error("拦截异常",e);
        }

        if (StringUtil.isNotEmpty(SystemPropertiesConfig.ENCRYPT_SWITCH)  && "true".equals(SystemPropertiesConfig.ENCRYPT_SWITCH)){
            //解密参数
            String[] data= this.params.get("data");
            Object obj = request.getHeader("security");
            if ((obj == null || !("1".equals(obj))) && StringUtil.isNotEmpty(data)){
                try {
                    logger.info("subject:{},data:{}","解密前参数",data[0]);
                   // String[] dataValues=new String[]{new String(RSAUtil2.privateDecode(RedisUtil.redisGet(RedisConstant.RAS_PRIVATE_KEY).toString(),Base64.decodeBase64(data[0])))};
                    String[] dataValues=new String[]{RSA2Util.rsaDecrypt(data[0])};
                    this.params.put("data",dataValues);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("subject:{},data:{},e:{}","解密异常",data[0],e);
                    throw new ServiceRuntimeException("请稍后重试",ResultConstant.RESULT_CODE_PUBLICKEY_INVALID);
                }
            }
        }

        logger.info("subject:{},data:{}","解密参数",this.params.get("data"));
        Iterator entries = this.params.entrySet().iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            String[] values = (String[]) entry.getValue();
            stringBuilder.append(key + ": " + values[0]);
        }
        if (stringBuilder != null && stringBuilder.length() > 0) {
            logger.info("请求参数 " + stringBuilder.toString());
        }
    }

 /*   private void doGet(HttpServletRequest request)
            throws ServletException, IOException {
        Enumeration em = request.getParameterNames();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement(); //参数名称
            String value = request.getParameter(name); //根据参数名称获取到参数值
            this.addParameter(name, value);
        }

    }*/

    private void doPost(HttpServletRequest request)
            throws ServletException, IOException {
        putData(request);
    }

    private void putData(HttpServletRequest request) {
        try (BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"))) {
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
            if (null != jsonObject && null != jsonObject.getString("data")) {
                logger.info(" putData data:" + jsonObject.getString("data"));
                this.addParameter("data", jsonObject.getString("data"));
            }
        } catch (Exception e) {
            logger.error("subject:{},e:{}","请求参数封装异常",e);
        }
    }

    //重载一个构造方法
    public YDCHttpServletRequestWrapper(HttpServletRequest request, Map<String, Object> extendParams) {
        this(request);
        addAllParameters(extendParams);//这里将扩展参数写入参数表
    }

    @Override
    public String getParameter(String name) {//重写getParameter，代表参数从当前类中的map获取
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    public String[] getParameterValues(String name) {//同上
        return params.get(name);
    }

    public void addAllParameters(Map<String, Object> otherParams) {//增加多个参数
        for (Map.Entry<String, Object> entry : otherParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }


    public void addParameter(String name, Object value) {//增加参数
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }

}
