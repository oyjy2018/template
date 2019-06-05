package com.ydc.cgj.bridge.controller;

import com.ydc.beans.commom.OCRUtil;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.file.FileUtil;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

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
     * 车商端行驶证识别
     *
     * @param data
     * @return
     */
    @PostMapping("/vehicle")
    public String vehicle(@RequestBody String data) {
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
            if (StringUtil.isEmpty(browseFile)) {
                return Result.failure("未找到文件").toJSON();
            }
            //默认识别行驶证正面（side为face）
            Map<String, Object> vehicleInfo = OCRUtil.callOcrVehicle(FileUtil.getBase64Content(FileUtil.getFileStream(browseFile)),"face");
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

}
