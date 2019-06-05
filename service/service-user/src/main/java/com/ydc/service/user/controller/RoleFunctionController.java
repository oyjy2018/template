package com.ydc.service.user.controller;

import com.ydc.service.user.service.RoleFunctionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色功能（菜单）相关
 */
@RestController
@RequestMapping("/roleFunction")
public class RoleFunctionController {

    private Logger logger = LogManager.getLogger(RoleFunctionController.class);

    @Autowired
    private RoleFunctionService roleFunctionService;
    /**
     * 获取功能树
     * @param serviceIdentifying
     * @param functionName
     * @return
     */
    @PostMapping(value = "/getFunctionTree")
    public String getFunctionTree(@RequestParam String serviceIdentifying, @RequestParam(value = "functionName",required = false) String functionName){
        logger.info("subject:{},serviceIdentifying:{},functionName:{}","获取功能树",serviceIdentifying,functionName);
        return roleFunctionService.getFunctionTree(serviceIdentifying,functionName);
    }
}
