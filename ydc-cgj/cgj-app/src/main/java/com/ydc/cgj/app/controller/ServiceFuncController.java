package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.app.service.ServiceFuncService;
import com.ydc.commom.view.dto.cgj.sys.ServiceFuncDTO;
import com.ydc.model.cgj.DictionaryDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/serviceFunc")
public class ServiceFuncController {

    private static final Logger logger = LogManager.getLogger(ServiceFuncController.class);

    @Autowired
    ServiceFuncService serviceFuncService;


    /**
     * 查询有效的服务功能列表，用于H5或者APP端展示
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/searchServiceFunc")
    public String searchServiceFunc(@RequestParam("data") String data) {
        logger.info("subject:{},serviceFuncDTO:{}", "查询服务功能列表", data);
        return serviceFuncService.searchServiceFunc(data);
    }

    /**
     * 查询服务及产品 用于首页展示
     *
     * @return
     */
    @PostMapping(value = "/searchServiceShowHome")
    public String searchServiceFuncShowHome() {
        System.out.print("开始");
        return serviceFuncService.searchServiceFuncShowHome();
    }
}
