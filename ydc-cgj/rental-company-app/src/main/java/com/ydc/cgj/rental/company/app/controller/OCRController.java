package com.ydc.cgj.rental.company.app.controller;

import com.ydc.beans.commom.OCRUtil;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.file.FileUtil;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 车商端ocr相关接口
 */
@RestController
@RequestMapping("/ocr")
public class OCRController {

    private final Logger logger = LogManager.getLogger(OCRController.class);

    /**
     * 营业执照识别
     * @param data
     * @return
     */
    @PostMapping("/businessLicense")
    public String businessLicense(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}", "营业执照识别", data);
        String SMSValidateSwitch = SystemPropertiesConfig.ALIYUN_OCR_SWITCH;
        if ("off".equalsIgnoreCase(SMSValidateSwitch)) {
            logger.info("subject:{}", "OCR识别开关未开");
            return Result.failure("OCR识别开关未开").toJSON();
        }
        Map map = JsonUtil.jsonToMap(data);
        if (StringUtil.isEmpty(map.get("fileName"))) {
            return Result.failure("文件名不能为空").toJSON();
        }
        if (StringUtil.isEmpty(map.get("fileUrl"))) {
            return Result.failure("文件路径不能为空").toJSON();
        }
        String fileName = (String) map.get("fileName");
        String fileUrl = (String) map.get("fileUrl");
        try {
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
            String browseFile = FileUtil.getBrowseFile(fileUrl, fileName + fileType);
            logger.info("browseFile:{}",browseFile);
            if (StringUtil.isEmpty(browseFile)) {
                return Result.failure("未找到文件").toJSON();
            }
            //调用识别工具类
            Map<String, Object> businessLicenseInfo = OCRUtil.callOcrBusinessLicense(FileUtil.getBase64Content(FileUtil.getFileStream(browseFile)));
            if (businessLicenseInfo == null || businessLicenseInfo.isEmpty()) {
                return Result.failure("营业执照识别失败").toJSON();
            }
            //验证结果不为true即失败
            if (!"true".equals(businessLicenseInfo.get("reqState"))) {
                return Result.failure(businessLicenseInfo.get("message").toString()).toJSON();
            }
            Map retMap = new HashMap();
            retMap.put("name",businessLicenseInfo.get("name"));
            retMap.put("person",businessLicenseInfo.get("person"));
            retMap.put("regNum",businessLicenseInfo.get("reg_num"));

            logger.info("subject:{},retMap:{}", "营业执照识别成功", JsonUtil.jsonStr(retMap));
            return Result.success(retMap).toJSON();
        }catch (Exception e) {
            logger.error("subject:{},e:{}", "营业执照识别异常", e);
            return Result.failure("营业执照").toJSON();
        }
    }

    /**
     * 车商端行驶证识别
     *
     * @param data
     * @return
     */
    @PostMapping("/vehicle")
    public String vehicle(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}", "行驶证识别", data);
        String SMSValidateSwitch = SystemPropertiesConfig.ALIYUN_OCR_SWITCH;
        if ("off".equalsIgnoreCase(SMSValidateSwitch)) {
            logger.info("subject:{}", "OCR识别开关未开");
            return Result.failure("OCR识别开关未开").toJSON();
        }
        Map map = JsonUtil.jsonToMap(data);
        if (StringUtil.isEmpty(map.get("fileName"))) {
            return Result.failure("文件名不能为空").toJSON();
        }
        if (StringUtil.isEmpty(map.get("fileUrl"))) {
            return Result.failure("文件路径不能为空").toJSON();
        }
        String fileName = (String) map.get("fileName");
        String fileUrl = (String) map.get("fileUrl");
        try {
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
            String browseFile = FileUtil.getBrowseFile(fileUrl, fileName + fileType);
            logger.info("browseFile:{}",browseFile);
            if (StringUtil.isEmpty(browseFile)) {
                return Result.failure("未找到文件").toJSON();
            }
            //默认识别行驶证正面（side为face）
            Map<String, Object> vehicleInfo = OCRUtil.callOcrVehicle(FileUtil.getBase64Content(FileUtil.getFileStream(browseFile)), "face");
            if (vehicleInfo == null || vehicleInfo.isEmpty()) {
                return Result.failure("行驶证识别失败").toJSON();
            }
            //验证结果部位true即失败
            if (!"true".equals(vehicleInfo.get("reqState"))) {
                return Result.failure(vehicleInfo.get("message").toString()).toJSON();
            }
            Map retMap = new HashMap();
            retMap.put("plateNum", vehicleInfo.get("plate_num")); //车牌
            retMap.put("vin", vehicleInfo.get("vin")); //车架号
            String registerDate = (String) vehicleInfo.get("register_date");
            if (StringUtil.isNotEmpty(registerDate) && registerDate.length() == 8) {
                registerDate = registerDate.substring(0, 4) + "-" + registerDate.substring(4, 6) + "-" + registerDate.substring(6, 8);
                retMap.put("plateDate", registerDate); //上牌日期
            }
            logger.info("subject:{},result:{}", "行驶证识别结果", JsonUtil.jsonStr(retMap));
            return Result.success(retMap).toJSON();
        } catch (Exception e) {
            logger.error("subject:{},e:{}", "行驶证识别异常", e);
            return Result.failure("行驶证识别异常").toJSON();
        }
    }

    /**
     * 车商端身份证识别
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/idCard")
    public String idCard(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}", "身份证识别", data);
        // 图片识别开关
        String SMSValidateSwitch = SystemPropertiesConfig.ALIYUN_OCR_SWITCH;
        if ("off".equalsIgnoreCase(SMSValidateSwitch)) {
            logger.info("subject:{}", "OCR识别开关未开");
            return Result.failure("OCR识别开关未开").toJSON();
        }

        Map map = JsonUtil.jsonToMap(data);
        if (StringUtil.isEmpty(map.get("fileName"))) {
            return Result.failure("文件名不能为空").toJSON();
        }
        if (StringUtil.isEmpty(map.get("fileUrl"))) {
            return Result.failure("文件路径不能为空").toJSON();
        }
        if (StringUtil.isEmpty(map.get("side"))) {
            return Result.failure("身份证正反面选项不能为空").toJSON();
        }
        String fileName = (String) map.get("fileName");
        String fileUrl = (String) map.get("fileUrl");
        String side = (String) map.get("side");
        if (!("face".equals(side)) && !("back".equals(side))) {
            return Result.failure("身份证正反面参数错误").toJSON();
        }

        try {
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
            String browseFile = FileUtil.getBrowseFile(fileUrl, fileName + fileType);
            if (StringUtil.isEmpty(browseFile)) {
                return Result.failure("未找到文件").toJSON();
            }
            Map<String, Object> idCardInfo = OCRUtil.callOcrIdcard(FileUtil.getBase64Content(FileUtil.getFileStream(browseFile)), side);
            if (idCardInfo == null) {
                return Result.failure("文件识别失败").toJSON();
            }
            //设置返回结果集
            Map<String, Object> result = new HashMap<>();
            if (!"true".equals(idCardInfo.get("reqState"))) {
                return Result.failure(idCardInfo.get("message").toString()).toJSON();
            }
            if ("face".equals(side)) {
                result.put("memberName", idCardInfo.get("name"));
                result.put("idCard", idCardInfo.get("num"));
                result.put("address", idCardInfo.get("address"));
            } else {
                String endDate = idCardInfo.get("end_date").toString();
                if (StringUtil.isNotEmpty(endDate) && endDate.length() == 8) {
                    endDate = endDate.substring(0, 4) + "-" + endDate.substring(4, 6) + "-" + endDate.substring(6, 8);
                }
                result.put("identityValidTime", endDate);
            }
            logger.info("subject:{},result:{}", "身份证识别结果", JsonUtil.jsonStr(result));
            return Result.success(result).toJSON();
        } catch (Exception e) {
            logger.error("subject:{},e:{}", "身份证识别异常", e);
            return Result.failure("身份证识别异常").toJSON();
        }
    }
}
