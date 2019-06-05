package com.ydc.cgj.web.controller;

import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.cgj.web.service.ImportService;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * excel模板导入
 *
 * @author gongjin
 * @create 2018-09-19 9:55
 **/
@Controller
@RequestMapping(value = "/import")
public class ImportController {

    protected static final Logger logger = LogManager.getLogger(ImportController.class);

    @Autowired
    ImportService importService;


    /**
     * 获取积分模板地址
     *
     * @return
     */
    @PostMapping(value = "/integralTemplate")
    @ResponseBody
    public String integralTemplate() {
        Map<String, Object> map = new HashMap<>();
        logger.info("获取积分模板地址");
        map.put("url", SystemPropertiesConfig.CGJ_WEB_SERVICE_URL + "/excelTemplate/integralImport.xls");
        return Result.success(map).toJSON();
    }


    @GetMapping(value = "/file")
    public String file() {
        return "file";
    }


    /**
     * 会员积分批量导入
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/integralImport")
    @ResponseBody
    public String integralImport(@RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId) {
        String fileName = file.getOriginalFilename();
        try {
            return importService.integralImport(fileName, file, userId);
        } catch (ServiceRuntimeException se) {
            logger.error("会员积分批量导入异常", se);
            return Result.failure(se.getMessage()).toJSON();
        } catch (Exception e) {
            logger.error("会员积分批量导入异常", e);
            return Result.failure("导入异常").toJSON();
        }
    }
}
