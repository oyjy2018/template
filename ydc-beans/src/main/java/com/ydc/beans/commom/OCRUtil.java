package com.ydc.beans.commom;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.file.FileUtil;
import com.ydc.commom.constant.MemberConstant;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.util.AliyunHttpUtil;
import com.ydc.model.cgj.MemberFile;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OCR识别
 *
 * @author
 * @create 2018-11-19 16:43
 **/
public class OCRUtil {
    private static final Logger logger = LogManager.getLogger(OCRUtil.class);

//    	private static final String aliyun_appkey = "24679764";
//	private static final String aliyun_appSecret = "02815055fe90fd0f755b9a821620944a";
//	private static final String aliyun_appCode = "9b2712eafde445a0b5fc5bf5954ebd51";

    /**
     * 身份证识别
     *
     * @param imgBase64
     * @return "address"    : "浙江省杭州市余杭区文一西路969号",   #地址信息
     * "config_str" : "{\\\"side\\\":\\\"face\\\"}",                #配置信息，同输入configure
     * "face_rect":{
     * "angle": -90,
     * "center":{
     * "x" : 952,
     * "y" : 325.5
     * },
     * "size":{
     * "height":181.99,
     * "width":164.99
     * }
     * },                          #人脸位置，center表示人脸矩形中心坐标，size表示人脸矩形长宽，angle表示矩形顺时针旋转的度数。
     * "name" : "张三",                                  #姓名
     * "nationality": "汉"， #民族
     * "num" : "1234567890",                             #身份证号
     * "sex" : "男",                                     #性别
     * "birth" : "20000101",                             #出生日期
     * "nationality" : "汉",                             #民族
     * "success" : true                                  #识别结果，true表示成功，false表示失败
     */
    public static Map<String, Object> callOcrIdcard(String imgBase64, String side) {
        Map<String, Object> identityInfo = null;
        if (StringUtil.isBlank(imgBase64)) {
            identityInfo = new HashMap<>();
            identityInfo.put("reqState", "true");
            identityInfo.put("message", "图片Base码不能为空");
            return identityInfo;
        }
        String host = SystemPropertiesConfig.ALIYUN_OCR_IDCARD_HOST;
        String path = SystemPropertiesConfig.ALIYUN_OCR_IDCARD_PATH;
        String appCode = SystemPropertiesConfig.ALIYUN_APPCODE;
//        String appCode = d253afeacea14852b7df6de9b04510e6;
//        String host = "http://dm-51.data.aliyun.com";
//        String path = "/rest/160601/ocr/ocr_idcard.json";
        String method = "POST";
        //请根据线上文档修改configure字段
        JSONObject configObj = new JSONObject();
        configObj.put("side", side);//身份证正反面类型:face/back
        String config_str = configObj.toString();
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE
        headers.put("Authorization", "APPCODE "+appCode);
        Map<String, String> querys = new HashMap<String, String>();
        // 拼装请求body的json字符串
        JSONObject requestObj = new JSONObject();
        requestObj.put("image", imgBase64);
        if (config_str.length() > 0) {
            requestObj.put("configure", config_str);
        }
        String bodys = requestObj.toString();
        logger.info("请求参数:{}", bodys);
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            Long beforeMillis = System.currentTimeMillis();
            HttpResponse response = AliyunHttpUtil.doPost(host, path, method, headers, querys, bodys);
            logger.info("call spend :{} ms",(System.currentTimeMillis()-beforeMillis));
            int stat = response.getStatusLine().getStatusCode();
            logger.info("身份证识别请求响应状态码：" + stat);
            if (stat != 200) {
                identityInfo = new HashMap<>();
                identityInfo.put("reqState", "false");
                identityInfo.put("code", stat);
                identityInfo.put("message", "身份证识别失败");
            } else {
                String res = EntityUtils.toString(response.getEntity());
                identityInfo = JsonUtil.jsonToMap(res);
                identityInfo.put("reqState", "true");
                logger.info("身份证识别成功请求响应参数：" + res);
            }
        } catch (Exception e) {
            logger.error("subject:{},e:{}", "身份证识别接口访问异常", e);
            identityInfo = new HashMap<String, Object>();
            identityInfo.put("reqState", "false");
            identityInfo.put("message", "身份证识别接口访问异常：" + e.getMessage());
        }
        return identityInfo;
    }

    /**
     * ocr身份证识别，和上面的方法作用是一样的，返回类型不一样
     *
     * @param base64File
     * @param side
     * @return
     */
    public static String doOcrIdCard(String base64File, String side) throws Exception {
        logger.info("subject: {}, side: {}", "OCR身份证识别", side);
        JSONObject configObj = new JSONObject();
        configObj.put("side", side);

        final String url = "http://dm-51.data.aliyun.com/rest/160601/ocr/ocr_idcard.json";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + SystemPropertiesConfig.ALIYUN_APPCODE);
        JSONObject body = new JSONObject();
        body.put("image", base64File);
        body.put("configure", configObj.toString());
        return UrlHttpUtil.doPost(url, body.toString(), headers);
    }

    /**
     * ocr身份证识别
     *
     * @param memberFile
     * @return
     * @throws Exception
     */
    public static String doOcrIdCard(MemberFile memberFile) throws Exception {
        logger.info("subject: {}, memberFile: {}", "ocr身份证识别", memberFile);
        if (memberFile == null) {
            return null;
        }
        // 图片识别开关
        if ("off".equalsIgnoreCase(SystemPropertiesConfig.ALIYUN_OCR_SWITCH)) {
            logger.info("subject:{}", "OCR身份证识别关闭");
            return null;
        }

        String browseFile = FileUtil.getBrowseFile(memberFile.getFileUrl(), memberFile.getFileName());
        String ocrResponse = doOcrIdCard(FileUtil.getBase64Content(FileUtil.getFileStream(browseFile)),
                MemberConstant.OCRFileTypeEnum.getOCRType(memberFile.getType()));
        logger.info("subject: {}, response: {}", "ocr身份识别结果", ocrResponse);
        return ocrResponse;
    }

    /**
     * 营业执照识别
     *
     * @param imgBase64
     * @return {
     * "config_str" : "null\n", #配置字符串信息
     * "angle" : float, #输入图片的角度（顺时针旋转），［0， 90， 180，270］
     * "reg_num" : string, #注册号，没有识别出来时返回"FailInRecognition"
     * "name" : string, #公司名称，没有识别出来时返回"FailInRecognition"
     * "type" : string, #公司类型，没有识别出来时返回"FailInRecognition"
     * "person" : string, #公司法人，没有识别出来时返回"FailInRecognition"
     * "establish_date": string, #公司注册日期(例：证件上为"2014年04月16日"，算法返回"20140416")
     * "valid_period": string, #公司营业期限终止日期(例：证件上为"2014年04月16日至2034年04月15日"，算法返回"20340415")
     * #当前算法将日期格式统一为输出为"年月日"(如"20391130"),并将"长期"表示为"29991231"，若证件上没有营业期限，则默认其为"长期",返回"29991231"。
     * "address" : string, #公司地址，没有识别出来时返回"FailInRecognition"
     * "capital" : string, #注册资本，没有识别出来时返回"FailInRecognition"
     * "business": string, #经营范围，没有识别出来时返回"FailInRecognition"
     * "emblem" : string, #国徽位置［top,left,height,width］，没有识别出来时返回"FailInDetection"
     * "title" : string, #标题位置［top,left,height,width］，没有识别出来时返回"FailInDetection"
     * "stamp" : string, #印章位置［top,left,height,width］，没有识别出来时返回"FailInDetection"
     * "qrcode" : string, #二维码位置［top,left,height,width］，没有识别出来时返回"FailInDetection"
     * "success" : bool, #识别成功与否 true/false
     * "request_id": string
     * }
     */
    public static Map<String, Object> callOcrBusinessLicense(String imgBase64) {
        Map<String, Object> businessLicenseInfo = null;
        if (StringUtil.isBlank(imgBase64)) {
            businessLicenseInfo = new HashMap<>();
            businessLicenseInfo.put("reqState", "false");
            businessLicenseInfo.put("message", "图片Base码为空");
            return businessLicenseInfo;
        }

        String host = SystemPropertiesConfig.ALIYUN_OCR_BUSINESSLICENSE_HOST;
        String path = SystemPropertiesConfig.ALIYUN_OCR_BUSINESSLICENSE_PATH;
        String method = "POST";
        String appCode = SystemPropertiesConfig.ALIYUN_APPCODE;
//      String host = "https://dm-58.data.aliyun.com";
//      String path = "/rest/160601/ocr/ocr_business_license.json";
//      String appCode = "d253afeacea14852b7df6de9b04510e6";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appCode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        //请求参数querys  为空
        Map<String, String> querys = new HashMap<String, String>();

        // 拼装请求参数bodys的json字符串
        JSONObject requestObj = new JSONObject();
        requestObj.put("image", imgBase64);
        String bodys = requestObj.toString();
        //String bodys = "{\"image\":\"对图片内容进行Base64编码\"}";
        // logger.info("请求参数:" + bodys);
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            Long beforeMillis = System.currentTimeMillis();
            HttpResponse response = AliyunHttpUtil.doPost(host, path, method, headers, querys, bodys);
            logger.info("call spend :{} ms",(System.currentTimeMillis()-beforeMillis));
            logger.info("response.toString()=" + response.toString());
            //获取response的body
            int stat = response.getStatusLine().getStatusCode();
            logger.info("营业执照识别请求响应状态码：" + stat);
            if (stat != 200) {
                businessLicenseInfo = new HashMap<>();
                businessLicenseInfo.put("reqState", "false");
                businessLicenseInfo.put("code", stat);
                businessLicenseInfo.put("message", "营业执照识别请求失败");
            } else {
                String res = EntityUtils.toString(response.getEntity());
                businessLicenseInfo = JsonUtil.jsonToMap(res);
                logger.info("success = " + businessLicenseInfo.get("success"));
                if ("true".equals(businessLicenseInfo.get("success").toString())) {
                    businessLicenseInfo.put("reqState", "true");
                } else {
                    businessLicenseInfo.put("reqState", "false");
                    businessLicenseInfo.put("code", stat);
                    businessLicenseInfo.put("message", "营业执照识别失败");
                }
                logger.info("营业执照识别成功请求响应参数：" + res);
            }
        } catch (Exception e) {
            logger.error("subject:{},e:{}", "营业执照识别接口访问异常", e);
            businessLicenseInfo = new HashMap<String, Object>();
            businessLicenseInfo.put("reqState", "false");
            businessLicenseInfo.put("message", "营业执照识别接口访问异常：" + e.getMessage());
        }
        return businessLicenseInfo;
    }

    /**
     * 行驶证识别
     *
     * @param imgBase64
     * @param side
     * @return 正面
     * {
     * "config_str": "null\n",         #配置字符串信息
     * "plate_num": "沪A0M084",        #车牌号码
     * "vehicle_type":"小型轿车",       #车辆类型
     * "owner": "张三",                #所有人名称
     * "use_character":"出租转非",      #使用性质
     * "addr":"浙江省宁波市江东区丁街88弄", #地址
     * "model":"桑塔纳牌SVW7180LE1",    #品牌型号
     * "vin" : "LSVFF66R8C2116280",     #车辆识别代号
     * "engine_num" : "416098",        #发动机号码
     * "register_date":"20121127",       #注册日期
     * "issue_date":"20130708",        #发证日期
     * "request_id": "84701974fb983158_20160526100112",               #请求对应的唯一表示
     * "success": true                 #识别成功与否 true/false
     * }
     * <p>
     * 反面
     * {
     * "config_str":"{\"side\": \"back\" }",  #配置字符串信息
     * "appproved_passenger_capacity":"5人",    #核定载人数
     * "approved_load":"",                      #核定载质量
     * "file_no":"530100001466",                #档案编号
     * "gross_mass":"2000kg",                   #总质量
     * "inspection_record":"检验有效期至2014年09月云A(01)",  #检验记录
     * "overall_dimension":"4945x1845x1480mm",   #外廓尺寸
     * "traction_mass":"",                       #准牵引总质量
     * "unladen_mass":"1505kg"                   #整备质量
     * "plate_num":"云AD8V02",                   #号牌号码
     * "success":true,              #识别成功与否 true/false
     * "request_id":"20180131144149_c440540b20a4dc079a10680ff60b2d2a" #请求对应的唯一表示
     * }
     */
    public static Map<String, Object> callOcrVehicle(String imgBase64, String side) {

        //返回结果
        Map<String, Object> vehicleInfo = new HashMap<>();

        if (StringUtil.isBlank(imgBase64)) {
            vehicleInfo.put("reqState", "false");
            vehicleInfo.put("message", "图片Base码为空");
            return vehicleInfo;
        }

        String host = SystemPropertiesConfig.ALIYUN_OCR_VEHICLE_HOST;
        String path = SystemPropertiesConfig.ALIYUN_OCR_VEHICLE_PATH;
        String appCode = SystemPropertiesConfig.ALIYUN_APPCODE;
        //String host = "https://dm-53.data.aliyun.com";
       // String path = "/rest/160601/ocr/ocr_vehicle.json";
        //String appCode = "d253afeacea14852b7df6de9b04510e6";
        //Boolean is_old_format = true;//如果文档的输入中含有inputs字段，设置为True， 否则设置为False

        String method = "POST";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appCode);

        //请求参数querys  为空
        Map<String, String> querys = new HashMap<String, String>();

        // 拼装请求body的json字符串
        JSONObject requestObj = new JSONObject();
        //请根据线上文档修改configure字段
        JSONObject configObj = new JSONObject();
        configObj.put("side", side);
        requestObj.put("configure", configObj.toString());

        requestObj.put("image", imgBase64);

        String bodys = requestObj.toString();


        try {
            Long beforeMillis = System.currentTimeMillis();
            HttpResponse response = AliyunHttpUtil.doPost(host, path, method, headers, querys, bodys);
            logger.info("call spend :{} ms",(System.currentTimeMillis()-beforeMillis));
            logger.info("response.toString()=" + response.toString());
            //获取response的body
            int stat = response.getStatusLine().getStatusCode();
            logger.info("行驶证识别请求响应状态码：" + stat);
            if (stat != 200) {
                vehicleInfo = new HashMap<>();
                vehicleInfo.put("reqState", "false");
                vehicleInfo.put("code", stat);
                vehicleInfo.put("message", "行驶证识别接口请求失败");
            } else {
                String res = EntityUtils.toString(response.getEntity());
                vehicleInfo = JsonUtil.jsonToMap(res);
                logger.info("success = " + vehicleInfo.get("success"));
                if ("true".equals(vehicleInfo.get("success").toString())) {
                    vehicleInfo.put("reqState", "true");
                } else {
                    vehicleInfo.put("reqState", "false");
                    vehicleInfo.put("code", stat);
                    vehicleInfo.put("message", "行驶证识别失败");
                }
                logger.info("行驶证识别成功请求响应参数：" + res);
            }
        } catch (Exception e) {
            logger.error("subject:{},e:{}", "行驶证识别接口访问异常",  e.getMessage());
            vehicleInfo = new HashMap<String, Object>();
            vehicleInfo.put("reqState", "false");
            vehicleInfo.put("message", "行驶证识别接口请求异常" );
        }
        return vehicleInfo;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("F:\\sfzb1.jpg");
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        //logger.info(new BASE64Encoder().encode(buffer));
        Map map = callOcrIdcard(new BASE64Encoder().encode(buffer),"back");
        //callOcrVehicle(new BASE64Encoder().encode(buffer), "face");
//        String response = doOcrIdCard(new BASE64Encoder().encode(buffer),"face");
//        logger.info(response);
//        Map<String, Object> map = JsonUtil.jsonToMap(response);
//       logger.info(map.get("birth"));
    }
}
