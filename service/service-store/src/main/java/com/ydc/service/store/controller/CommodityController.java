package com.ydc.service.store.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.file.FileUtil;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.CommodityDTO;
import com.ydc.commom.view.dto.cgj.StoreReqDTO;
import com.ydc.model.cgj.*;
import com.ydc.service.store.service.CommodityImgService;
import com.ydc.service.store.service.CommodityModelService;
import com.ydc.service.store.service.CommodityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commodity")
public class CommodityController {
    private static Logger logger = LogManager.getLogger(CommodityController.class);

    @Resource(name = "commodityServiceRedis")
    private CommodityService commodityService;
    @Autowired
    private CommodityModelService commodityModelService;
    @Autowired
    private CommodityImgService commodityImgService;

    /**
     * 获取商品列表
     * @param commodityDTO
     * @return
     */
    @RequestMapping(value = "/queryCommodityList", method = RequestMethod.POST)
    public String queryCommodityList(@RequestBody CommodityDTO commodityDTO){
        logger.info("获取商品列表,param:"+JsonUtil.gsonStr(commodityDTO));
        try{
            List<Map<String, Object>> ret = commodityService.getCommodityList(commodityDTO);
            Map<String, Object> retCount = commodityService.getCommodityListCount(commodityDTO);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", retCount.get("cnt"));
            jMap.put("rows",ret);
            return Result.success(jMap).toJSON();
        }catch (Exception e){
            logger.error("subject:{},e:{}","获取商品列表异常",e);
            return Result.failure("失败").toJSON();
        }
    }

    /**
     * 获取商品详情
     * @param commodityDTO
     * @return
     */
    @RequestMapping(value = "/queryCommodityDetails", method = RequestMethod.POST)
    public String queryCommodityDetails(@RequestBody CommodityDTO commodityDTO){
        logger.info("获取商品详情,param:"+JsonUtil.gsonStr(commodityDTO));
        try{
            Commodity commodity = commodityService.selectByPrimaryKey(commodityDTO.getCommodityId());
            List<CommodityModel> modelList = commodityModelService.selectByCommodityId(commodityDTO.getCommodityId());
            List<CommodityImg> imgList = commodityImgService.selectByCommodityId(commodityDTO.getCommodityId());
            for(CommodityImg img:imgList){
                try {
                    img.setViewFileUrl(FileUtil.getBrowseFile(img.getFileUrl(),img.getFileName()));
                } catch (Exception e) {
                    logger.error("subject:{},e:{}","获取商品图片地址异常",e);
                }
            }
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("commodity",commodity);
            jMap.put("modelList",modelList);
            jMap.put("imgList",imgList);
            return Result.success(jMap).toJSON();
        }catch (Exception e){
            logger.error("subject:{},e:{}","获取商品详情异常",e);
            return Result.failure("失败").toJSON();
        }
    }

    /**
     * 保存或修改商品信息
     * @param srd
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateCommodity", method = RequestMethod.POST)
    public String saveOrUpdateCommodity(@RequestBody StoreReqDTO srd){
        logger.info("保存或修改商品信息,param:"+JsonUtil.gsonStr(srd));
        try{
            Commodity existCommodity = commodityService.selectByTitle(srd.getCommodity().get("title"));
            if(existCommodity != null){
                Integer commodityId = null;
                if(srd.getCommodity().get("id") == null || "".equals(srd.getCommodity().get("id"))){
                    commodityId = 0;
                }else{
                    commodityId = Integer.valueOf(srd.getCommodity().get("id"));
                }
                if(commodityId.intValue() != existCommodity.getId().intValue())
                    return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "商品标题已存在").toJSON();
            }
            commodityService.saveOrUpdateCommodity(srd);
            return Result.success("保存成功").toJSON();
        }catch (Exception e){
            logger.error("subject:{},e:{}","保存或修改商品信息异常",e);
            return Result.failure("失败").toJSON();
        }
    }

    /**
     * 首页推荐
     * @param srd
     * @return
     */
    @RequestMapping(value = "/homePageRecommend", method = RequestMethod.POST)
    public String homePageRecommend(@RequestBody StoreReqDTO srd){
        logger.info("首页推荐,param:"+JsonUtil.gsonStr(srd));
        try{
            commodityService.updateHasShoutuiByIds(srd);
            return Result.success("首推成功").toJSON();
        }catch (Exception e){
            logger.error("subject:{},e:{}","首页推荐异常",e);
            return Result.failure("失败").toJSON();
        }
    }

    /**
     * 更新发布状态
     * @param srd
     * @return
     */
    @RequestMapping(value = "/updateReleaseStatus", method = RequestMethod.POST)
    public String updateReleaseStatus(@RequestBody StoreReqDTO srd){
        logger.info("更新发布状态,param:"+JsonUtil.gsonStr(srd));
        try{
            Result res = commodityService.updateReleaseStatusByIds(srd);
            if(res.getCode() == ResultConstant.FUNCTION_CODE_FAILURE){
                return Result.failure(res.getMessage()).toJSON();
            }
            return Result.success("更新发布状态成功").toJSON();
        }catch (Exception e){
            logger.error("subject:{},e:{}","更新发布状态异常",e);
            return Result.failure("失败").toJSON();
        }
    }

    /**
     * 修改库存
     * @param srd
     * @return
     */
    @RequestMapping(value = "/modifyInventory", method = RequestMethod.POST)
    public String modifyInventory(@RequestBody StoreReqDTO srd){
        logger.info("修改库存,param:"+JsonUtil.gsonStr(srd));
        try{
            commodityModelService.modifyInventory(srd);
            CommodityModel commodityModel = commodityModelService.selectByPrimaryKey(Integer.valueOf(srd.getCommodityModelId()));
            commodityService.updateTotalInventoryById(commodityModel.getCommodityId());
            return Result.success("修改库存成功").toJSON();
        }catch (Exception e){
            logger.error("subject:{},e:{}","修改库存异常",e);
            return Result.failure("失败").toJSON();
        }
    }

    /**
     * 获取商品列表
     *
     * @param title
     * @param pagination
     * @return
     */
    @PostMapping("/getCommodityList")
    public Result getCommodityList(@RequestParam(value = "title",required = false) String title, @RequestBody Pagination pagination){
        logger.info("subject:{},title:{},pagination:{}","获取商品列表",title,JsonUtil.gsonStr(pagination));
        try{
            Result result = Result.success();
            result.setData(commodityService.getCommodityList(title, pagination));
            return result;
        }catch (Exception e){
            logger.error("subject:{},e:{}","获取商品列表异常",e);
            return Result.failure("失败");
        }
    }

    /**
     * 获取首页商品列表
     * @param commodityNum
     * @return
     */
    @PostMapping("/getHomePageCommodityList")
    public Result getHomePageCommodityList(@RequestParam("commodityNum") int commodityNum){
        logger.info("subject:{},commodityNum:{}","获取首页商品列表",commodityNum);
        try{
            Result result = Result.success();
            result.setData(commodityService.getHomePageCommodityList(commodityNum));
            return result;
        }catch (Exception e){
            logger.error("subject:{},e:{}","获取商品列表异常",e);
            return Result.failure("失败");
        }
    }

    /**
     * 获取商品详情
     *
     * @param commodityId
     * @return
     */
    @PostMapping("/getCommodityDetail")
    public Result getCommodityDetail(Integer commodityId){
        logger.info("subject:{},commodityId:{}","获取商品详情",commodityId);
        try{
            Result result = Result.success();
            result.setData(commodityService.getCommodityDetailRedis(commodityId));
            return result;
        }catch (Exception e){
            logger.error("subject:{},e:{}","获取商品详情异常",e);
            return Result.failure("失败");
        }
    }

    /**
     * 获取商品型号列表
     * @param commodityId
     * @return
     */
    @PostMapping("/getCommodityModelList")
    public Result getCommodityModelList(Integer commodityId){
        logger.info("subject:{},commodityId:{}","获取商品型号列表",commodityId);
        try{
            Result result = Result.success();
            result.setData(commodityModelService.getCommodityModelList(commodityId));
            return result;
        }catch (Exception e){
            logger.error("subject:{},e:{}","获取商品型号列表异常",e);
            return Result.failure("失败");
        }
    }

    /**
     * 优先商品列表
     *
     * @param title
     * @param pagination
     * @return
     */
    @PostMapping("/getPriorityCommodityList")
    public Result getPriorityCommodityList(@RequestParam(value = "title",required = false) String title, @RequestBody Pagination pagination){
        logger.info("subject:{},title:{},pagination:{}","优先商品列表",title,JsonUtil.gsonStr(pagination));
        try{
            Result result = Result.success();
            List<Map<String, Object>> ret = commodityService.getPriorityCommodityList(title, pagination);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(ret));
            jMap.put("rows",ret);
            result.setData(jMap);
            return result;
        }catch (Exception e){
            logger.error("subject:{},e:{}","获取优先商品列表异常",e);
            return Result.failure("失败");
        }
    }

    /**
     * 商品首页推荐（首页显示商品）
     *
     * @return
     */
    @RequestMapping(value = "/updatePriority", method = RequestMethod.POST)
    public Result updatePriority(@RequestParam(value = "commodityIds") String commodityIds,
                                 @RequestParam(value = "priority") int priority,
                                 @RequestParam(value = "userId") Integer userId) {
        logger.info("subject: {}, commodityIds: {}, priority: {}, userId: {}", "商品首页推荐（首页显示商品）", commodityIds, priority, userId);
        try{
            commodityService.updatePriority(commodityIds, priority, userId);
            return Result.success("商品首页推荐成功");
        }catch (Exception e){
            logger.error("subject:{},e:{}","商品首页推荐异常",e);
            return Result.failure("商品首页推荐失败");
        }
    }


    /**
     * 获取券商品
     * @param sonClassifyCode
     * @return
     */
    @PostMapping("/getRollCommodity")
    public Result getRollCommodity(String sonClassifyCode){
        logger.info("subject:{},sonClassifyCode:{}","获取券商品",sonClassifyCode);
        return Result.success(commodityService.getRollCommodity(sonClassifyCode));
    }
}
