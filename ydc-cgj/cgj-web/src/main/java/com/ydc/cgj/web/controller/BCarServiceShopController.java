package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.web.service.BCarServiceShopService;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.service.CarStoreDTO;
import com.ydc.model.cgj.Pagination;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * B端门店
 *
 * @author
 * @create 2018-10-30 10:29
 **/
@RestController
@RequestMapping(value = "/bCarServiceShop")
public class BCarServiceShopController {

    private static final Logger logger = LogManager.getLogger(BCarServiceShopController.class);

    @Autowired
    BCarServiceShopService bCarServiceShopService;

    /**
     * B端门店列表
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getBStoreList")
    public String getBStoreList(@RequestParam("data") String data) {
        logger.info("subject:{},carStoreDTO:{}", "B端门店列表", data);
        CarStoreDTO carStoreDTO = JSONObject.parseObject(data,CarStoreDTO.class);
        return bCarServiceShopService.getBStoreList(carStoreDTO);
    }

    /**
     * 更新门店是否上架
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/updateStoreWhetherPutaway")
    public String updateStoreWhetherPutaway(@RequestParam("data") String data) {
        logger.info("subject:{},carStoreDTO:{}", "更新门店是否上架", data);
        CarStoreDTO carStoreDTO = JSONObject.parseObject(data,CarStoreDTO.class);
        carStoreDTO.setUpdateBy(WebShiroTokenManager.getUser().getId());
        return bCarServiceShopService.updateStoreWhetherPutaway(carStoreDTO);
    }

    /**
     * 刷新门店
     * @param data
     * @return
     */
    @PostMapping(value = "/refreshCarStoreTemplate")
    public String refreshCarStoreTemplate(@RequestParam("data") String data){
        logger.info("subject:{},carStoreDTO:{}", "刷新门店", data);
        CarStoreDTO carStoreDTO = JSONObject.parseObject(data,CarStoreDTO.class);
        carStoreDTO.setUpdateBy(WebShiroTokenManager.getUser().getId());
        return bCarServiceShopService.refreshCarStoreTemplate(carStoreDTO);
    }

    /**
     * 优先门店列表
     *
     * @param data
     * @return
     */
    @PostMapping("/getPriorityCarStore")
    public String getPriorityCarStore(@RequestParam(value = "data") String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        String storeName = jsonObject.getString("storeName");
        logger.info("获取门店列表, storeName: " + storeName);
        return bCarServiceShopService.getPriorityCarStore(StringUtil.preventInjection(storeName),
                new Pagination(jsonObject.getInteger("page"), jsonObject.getInteger("rows"))).toJSON();
    }

    /**
     * 更新首页推荐（首页显示门店）
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/updatePriorityCarStore", method = RequestMethod.POST)
    public String updatePriorityCarStore(@RequestParam("data") String data) {
        logger.info("subject:{},srd:{}", "商品首页推荐（首页显示商品）", data);
        JSONObject jsonObject = JSON.parseObject(data);
        User user = WebShiroTokenManager.getUser();
        return bCarServiceShopService.updatePriorityCarStore(jsonObject.getString("carStoreIds"),
                CodeConstant.HAD, user == null ? 1 : user.getId()).toJSON();
    }
}
