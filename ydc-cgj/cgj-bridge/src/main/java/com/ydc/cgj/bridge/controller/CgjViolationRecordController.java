package com.ydc.cgj.bridge.controller;

import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.sys.CgjViolationRecordQueDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 违章查询
 */
@RestController
@RequestMapping(value = "/bridge-violation")
public class CgjViolationRecordController {
    private final Logger logger = LogManager.getLogger(CgjViolationRecordController.class);
    /**
     * 获取违章记录列表
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getRecordList")
    public String getRecordList(@RequestBody String data) {
        CgjViolationRecordQueDTO cgjViolationRecordQueDTO = JsonUtil.jsonToBean(data,CgjViolationRecordQueDTO.class);
        String result = null;
        try {
            if (cgjViolationRecordQueDTO.getLsprefix() == null||
                cgjViolationRecordQueDTO.getLsnum() == null||
                cgjViolationRecordQueDTO.getFrameno() == null||
                cgjViolationRecordQueDTO.getEngineno() == null) {
                return null;
            }
            Map<String, Object> requestParam = getRequestParam(cgjViolationRecordQueDTO);
            result = UrlHttpUtil.doGet(SystemPropertiesConfig.THIRD_VIOLATION_RECORD_URL, requestParam);
        } catch (Exception e) {
            logger.error("subject:{}", "调用违章记录第三方接口失败");
            return null;
        }
        return result;
    }


    private Map<String, Object> getRequestParam(CgjViolationRecordQueDTO cgjViolationRecordQueDTO) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appkey", SystemPropertiesConfig.THIRD_VIOLATION_APPKEY);
        paramMap.put("carorg", cgjViolationRecordQueDTO.getCarorg());
        paramMap.put("engineno", cgjViolationRecordQueDTO.getEngineno());
        paramMap.put("frameno", cgjViolationRecordQueDTO.getFrameno());
        paramMap.put("lsnum", cgjViolationRecordQueDTO.getLsnum());
        paramMap.put("lsprefix", cgjViolationRecordQueDTO.getLsprefix());
        String lstype = cgjViolationRecordQueDTO.getLstype();
        if (null == lstype) {
            //车辆类型默认小车02
            paramMap.put("lstype", "02");
        } else {
            paramMap.put("lstype", lstype);
        }
        paramMap.put("mobile", cgjViolationRecordQueDTO.getMobile());
        Integer iscity = cgjViolationRecordQueDTO.getIscity();
        if (null == iscity) {
            //是否返回城市 1返回 默认0不返回 不一定100%返回结果，准确度90% town、lat、lng仅供参考
            paramMap.put("iscity", 0);
        } else {
            paramMap.put("iscity", iscity);
        }
        return paramMap;
    }
}
