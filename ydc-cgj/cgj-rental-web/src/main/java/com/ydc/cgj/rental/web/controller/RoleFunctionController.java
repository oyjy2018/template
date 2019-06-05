package com.ydc.cgj.rental.web.controller;

import com.ydc.cgj.rental.web.service.RoleFunctionService;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 角色功能相关
 */
@RestController
@RequestMapping("/roleFunction")
public class RoleFunctionController {

    private Logger logger = LogManager.getLogger(RoleFunctionController.class);

    @Autowired
    private RoleFunctionService roleFunctionService;

    //租车服务标识
    private static final String SERVICE_IDENTIFYING = "RENTAL";

    /**
     * 获取功能树
     * @param data
     * @return
     */
    @PostMapping("/getFunctionTree")
    public String getFunctionTree(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","获取功能树",data);
        Map dataMap = JsonUtil.jsonToMap(data);
        //功能名称 可以为空 为空时查询全部树结构
        String functionName = (String) dataMap.get("functionName");
        logger.info("functionName:{}",functionName);

        return roleFunctionService.getFunctionTree(SERVICE_IDENTIFYING,functionName);
    }
}
