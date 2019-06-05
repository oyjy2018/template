package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.app.service.BCarServiceShopService;
import com.ydc.cgj.app.service.CommodityModelService;
import com.ydc.cgj.app.service.CommodityService;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.PositionDTO;
import com.ydc.model.cgj.Pagination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品 Controller
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController {
    private final Logger logger = LogManager.getLogger(CommodityController.class);

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private CommodityModelService commodityModelService;

    @Autowired
    BCarServiceShopService bCarServiceShopService;

    /**
     * 获取商品列表
     *
     * @param data
     * @return
     */
    @GetMapping("/getCommodityList")
    public String getCommodityList(@RequestParam(value = "data") String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        String title = jsonObject.getString("title");
        logger.info("获取商品列表, title: " + title);
        return commodityService.getCommodityList(StringUtil.preventInjection(title),
                new Pagination(jsonObject.getInteger("page"), jsonObject.getInteger("rows"))).toJSON();
    }

    /**
     * 获取商品详情
     *
     * @param data
     * @return
     */
    @GetMapping("/getCommodityDetail")
    public String getCommodityDetail(@RequestParam(value = "data") String data) {
        Integer commodityId = JSON.parseObject(data).getInteger("commodityId");
        logger.info("获取商品详情, commodityId: " + commodityId);
        return commodityService.getCommodityDetail(commodityId).toJSON();
    }

    /**
     * 获取商品型号列表
     *
     * @param data
     * @return
     */
    @GetMapping("/getCommodityModelList")
    public String getCommodityModelList(@RequestParam(value = "data") String data) {
        Integer commodityId = JSON.parseObject(data).getInteger("commodityId");
        logger.info("获取商品型号列表, commodityId: " + commodityId);
        return commodityModelService.getCommodityModelList(commodityId).toJSON();
    }

    /**
     * 获取首页商品和门店数据
     * @param data
     * @return
     */
    @PostMapping("/getHomeCommodity")
    public String getHomeCommodity(@RequestParam(value = "data") String data, HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        JSONObject jsonObject = JSON.parseObject(data);
        Result commodityResult = commodityService.getHomePageCommodityList(jsonObject.getInteger("commodityNum"));
        if (ResultConstant.RESULT_CODE_SUCCESS == commodityResult.getCode()){
            resultMap.put("commodity", commodityResult.getData());
        }
        Result bCarServiceShopResult = bCarServiceShopService.getHomePageCarStore(JSONObject.parseObject(data,PositionDTO.class), request);
        if (ResultConstant.RESULT_CODE_SUCCESS == bCarServiceShopResult.getCode()){
            resultMap.put("bCarServiceShop", bCarServiceShopResult.getData());
        }
        Result result = Result.success();
        result.setData(resultMap);
        return result.toJSON();
    }

    /**
     * 获取券商品
     * @param data
     * @return
     */
    @GetMapping("/getRollCommodity")
    public String getRollCommodity(@RequestParam(value = "data") String data){
        String sonClassifyCode = JSON.parseObject(data).getString("sonClassifyCode");
        logger.info("获取券商品, sonClassifyCode: " + sonClassifyCode);
        return commodityService.getRollCommodity(sonClassifyCode).toJSON();
    }
}
