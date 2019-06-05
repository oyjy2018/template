package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.web.service.StoreService;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.BigDecimalUtil;
import com.ydc.commom.util.ParamVaildateUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.*;
import com.ydc.model.cgj.Pagination;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.US_ASCII;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商城
 */
@RestController
@RequestMapping(value = "/store")
public class StoreController {

    private static final Logger logger = LogManager.getLogger(StoreController.class);

    @Autowired
    private StoreService storeService;

    /**
     * 获取商品列表
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/queryCommodityList", method = RequestMethod.POST)
    public String queryCommodityList(@RequestParam("data") String data) {
        logger.info("subject:{},commodityDTO:{}", "获取商品列表", data);
        CommodityDTO commodityDTO = JSONObject.parseObject(data,CommodityDTO.class);
        return storeService.queryCommodityList(commodityDTO.changeDTO());
    }

    /**
     * 获取商品详情
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/queryCommodityDetails", method = RequestMethod.POST)
    public String queryCommodityDetails(@RequestParam("data") String data) {
        logger.info("subject:{},commodityDTO:{}", "获取商品详情", data);
        CommodityDTO commodityDTO = JSONObject.parseObject(data,CommodityDTO.class);
        return storeService.queryCommodityDetails(commodityDTO);
    }

    /**
     * 保存或修改商品信息
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateCommodity", method = RequestMethod.POST)
    public String saveOrUpdateCommodity(@RequestParam("data") String data) {
        logger.info("subject:{},srd:{}", "保存或修改商品信息", data);
        StoreReqDTO srd = JSONObject.parseObject(data,StoreReqDTO.class);
        //校验商品信息
        if (srd.getCommodity() == null) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少商品信息").toJSON();
        }
        Map<String, String> commodity = srd.getCommodity();
        Map vaildMap = ParamVaildateUtil.vaildateMapAndTransfer(commodity, CommoditySaveDTO.class);
        if ("1".equals(vaildMap.get("code"))) {
            return Result.failure(vaildMap.get("message").toString()).toJSON();
        }

        if (srd.getCommodityModels() == null || srd.getCommodityModels().size() == 0) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少商品类型信息").toJSON();
        }
        //校验商品类型
        List<Map<String, String>> comodityModels = srd.getCommodityModels();
        List<String> models = new ArrayList<>();
        for (Map<String, String> commodityModel : comodityModels) {
            vaildMap = ParamVaildateUtil.vaildateMapAndTransfer(commodityModel, CommodityModelSaveDTO.class);
            if ("1".equals(vaildMap.get("code"))) {
                return Result.failure(vaildMap.get("message").toString()).toJSON();
            }
            if (models.contains(commodityModel.get("model"))) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "商品型号重复").toJSON();
            }
            models.add(commodityModel.get("model"));
        }

        //校验商品图片
        if (commodity.get("id") == null || "0".equals(commodity.get("id"))) {
            if (srd.getCommodityImgs() == null || srd.getCommodityImgs().size() == 0) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少商品图片").toJSON();
            }
            List<Map<String, String>> commodityImgs = srd.getCommodityImgs();
            int homeImgNum = 0;
            int pollingImgNum = 0;
            int descriptionImgNum = 0;
            for (Map<String, String> commodityImg : commodityImgs) {
                vaildMap = ParamVaildateUtil.vaildateMapAndTransfer(commodityImg, CommodityImgSaveDTO.class);
                if ("1".equals(vaildMap.get("code"))) {
                    return Result.failure(vaildMap.get("message").toString()).toJSON();
                }

                if ("1".equals(commodityImg.get("imgType"))) {
                    homeImgNum++;
                } else if ("2".equals(commodityImg.get("imgType"))) {
                    pollingImgNum++;
                } else if ("3".equals(commodityImg.get("imgType"))) {
                    descriptionImgNum++;
                }

            }

            if (homeImgNum != 1) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "需要上传首页图片1张，当前上传[" + homeImgNum + "]张！").toJSON();
            }
            if (pollingImgNum == 0 || pollingImgNum > 4) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "需要上传轮播图片1-4张，当前上传[" + homeImgNum + "]张！").toJSON();
            }
        }
        return storeService.saveOrUpdateCommodity(srd);
    }

    /**
     * 商品首推（列表优先）
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/homePageRecommend", method = RequestMethod.POST)
    public String homePageRecommend(@RequestParam("data") String data) {
        logger.info("subject:{},srd:{}", "首页推荐", data);
        StoreReqDTO srd = JSONObject.parseObject(data,StoreReqDTO.class);
        srd.setHasShoutui("1");
        User user = WebShiroTokenManager.getUser();
        if (user != null){
            srd.setUserId(String.valueOf(user.getId()));
            srd.setUserName(user.getUserName());
        }
        return storeService.homePageRecommend(srd);
    }

    /**
     * 更新发布状态
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/updateReleaseStatus", method = RequestMethod.POST)
    public String updateReleaseStatus(@RequestParam("data") String data) {
        logger.info("subject:{},srd:{}", "更新发布状态", data);
        StoreReqDTO srd = JSONObject.parseObject(data,StoreReqDTO.class);
        return storeService.updateReleaseStatus(srd);
    }

    /**
     * 修改库存
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/modifyInventory", method = RequestMethod.POST)
    public String modifyInventory(@RequestParam("data") String data) {
        logger.info("subject:{},srd:{}", "修改库存", data);
        StoreReqDTO srd = JSONObject.parseObject(data,StoreReqDTO.class);
        return storeService.modifyInventory(srd);
    }

    /**
     * 优先商品列表
     *
     * @param data
     * @return
     */
    @PostMapping("/getPriorityCommodityList")
    public String getCommodityList(@RequestParam(value = "data") String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        String title = jsonObject.getString("title");
        logger.info("获取商品列表, title: " + title);
        return storeService.getPriorityCommodityList(StringUtil.preventInjection(title),
                new Pagination(jsonObject.getInteger("page"), jsonObject.getInteger("rows"))).toJSON();
    }

    /**
     * 更新商品首页推荐（首页显示商品）
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/updatePriority", method = RequestMethod.POST)
    public String updatePriority(@RequestParam("data") String data) {
        logger.info("subject:{},srd:{}", "商品首页推荐（首页显示商品）", data);
        JSONObject jsonObject = JSON.parseObject(data);
        User user = WebShiroTokenManager.getUser();
        return storeService.updatePriority(jsonObject.getString("commodityIds"),
                CodeConstant.HAD, user == null ? 1 : user.getId()).toJSON();
    }
}
