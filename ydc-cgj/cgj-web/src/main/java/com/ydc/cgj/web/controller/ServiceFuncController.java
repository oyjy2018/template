package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.web.service.ServiceFuncService;
import com.ydc.commom.view.dto.cgj.sys.DictionaryDetailDTO;
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
     * 新增服务功能
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/insertServiceFunc")
    public String insertServiceFunc(@RequestParam("data") String data) {
        logger.info("subject:{},serviceFuncDTO:{}", "新增服务功能", data);
        DictionaryDetailDTO dictionaryDetailDTO = JSONObject.parseObject(data,DictionaryDetailDTO.class);
        return serviceFuncService.insertServiceFunc(dictionaryDetailDTO);
    }

    /**
     * 更新服务功能
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/updateServiceFunc")
    public String updateServiceFunc(@RequestParam("data") String data) {
        logger.info("subject:{},serviceFuncDTO:{}", "更新服务功能", data);
        DictionaryDetailDTO dictionaryDetailDTO = JSONObject.parseObject(data,DictionaryDetailDTO.class);
        return serviceFuncService.updateServiceFunc(dictionaryDetailDTO);
    }

    /**
     * 删除服务功能
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/daleteServiceFunc")
    public String daleteServiceFunc(@RequestParam("id") Integer id) {
        logger.info("subject:{},serviceFuncDTO:{}", "删除服务功能", id);
        return serviceFuncService.daleteServiceFunc(id);
    }

    /**
     * 查询所有的服务功能列表，用于pc端配置
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/searchAllServiceFunc")
    public String searchAllServiceFunc(@RequestParam("data") String data) {
        logger.info("subject:{},serviceFuncDTO:{}", "查询服务功能列表", data);
        DictionaryDetail dictionaryDetail;
        if("''".equals(data) || "null".equals(data)){
            dictionaryDetail = new DictionaryDetail();
        }else {
            dictionaryDetail = JSONObject.parseObject(data, DictionaryDetail.class);
        }
        return serviceFuncService.searchAllServiceFunc(dictionaryDetail);
    }

    @PostMapping(value = "/getEnumList")
    public String getEnumList(@RequestParam("data") String data) {
        logger.info("subject:{},serviceFuncDTO:{}", "查询服务功能列表", data);

        return serviceFuncService.getEnumList();
    }
    /**
     * 查询有效的服务功能列表，用于H5或者APP端展示
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/searchServiceFunc")
    public String searchServiceFunc(@RequestParam("data") String data) {
        logger.info("subject:{},serviceFuncDTO:{}", "查询服务功能列表", data);
        DictionaryDetail dictionaryDetail = JSONObject.parseObject(data,DictionaryDetail.class);
        return serviceFuncService.searchServiceFunc(dictionaryDetail);
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
