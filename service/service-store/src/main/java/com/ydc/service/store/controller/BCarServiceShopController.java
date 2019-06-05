package com.ydc.service.store.controller;

import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.PositionDTO;
import com.ydc.commom.view.dto.cgj.service.CarStoreDTO;
import com.ydc.commom.view.vo.cgj.CarStoreVO;
import com.ydc.commom.view.vo.cgj.RecommendCarStoreVO;
import com.ydc.commom.view.vo.cgj.order.BStoreListVO;
import com.ydc.model.cgj.BCarServiceShop;
import com.ydc.model.cgj.Pagination;
import com.ydc.service.store.service.BCarServiceShopService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 汽车门店
 *
 * @author
 * @create 2018-11-01 14:36
 **/
@RestController
@RequestMapping(value = "/bCarServiceShop")
public class BCarServiceShopController {

    private static final Logger logger = LogManager.getLogger(BCarServiceShopController.class);


    @Autowired
    BCarServiceShopService bCarServiceShopService;

    /**
     * 批量保存门店
     * @param list
     * @return
     */
    @PostMapping(value = "/insertCarServiceBatch")
    public String insertCarServiceBatch(@RequestBody List<BCarServiceShop> list){
        logger.info("subject:{},list:{}","批量保存门店",JsonUtil.gsonStr(list));
        try{
            bCarServiceShopService.insertCarServiceBatch(list);
            return Result.success("成功").toJSON();
        } catch (Exception e) {
            logger.error("批量保存门店异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * B端门店列表
     * @param carStoreDTO
     * @return
     */
    @PostMapping(value = "/getBStoreList")
    public String getBStoreList(@RequestBody CarStoreDTO carStoreDTO){
        logger.info("subject:{},carStoreDTO:{}","查询B端门店列表",JsonUtil.gsonStr(carStoreDTO));
        try {
            List<BStoreListVO> ret = bCarServiceShopService.getBStoreList(carStoreDTO);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(ret));
            jMap.put("rows", ret);
            return Result.success(jMap).toJSON();
        } catch (Exception e) {
            logger.error("查询B端门店列表异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 更新门店是否上架
     * @param carStoreDTO
     * @return
     */
    @PostMapping(value = "/updateStoreWhetherPutaway")
    public String updateStoreWhetherPutaway(@RequestBody CarStoreDTO carStoreDTO){
        logger.info("subject:{},carStoreDTO:{}","更新门店是否上架",JsonUtil.gsonStr(carStoreDTO));
        try{
            BCarServiceShop bCarServiceShop = bCarServiceShopService.selectByPrimaryKey(carStoreDTO.getStoreId());
            if(bCarServiceShop.getStatus() == 2){
                return Result.failure("门店已处于解约状态").toJSON();
            }
//            if(bCarServiceShop.getWhetherPutaway() == 0){
//                return Result.failure("门店已处于下架").toJSON();
//            }
            return bCarServiceShopService.updateStoreWhetherPutaway(carStoreDTO) <= 0 ? Result.failure("更新失败").toJSON() : Result.success("成功").toJSON();
        }catch (Exception e){
            logger.error("更新门店是否上架异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * H5 门店列表
     * @param positionDTO
     * @return
     */
    @PostMapping(value = "/getH5StoreList")
    public String getH5StoreList(@RequestBody PositionDTO positionDTO){
        logger.info("subject:{},positionDTO:{}","H5 门店列表",JsonUtil.gsonStr(positionDTO));
        try{
            List<CarStoreVO> carStoreVOS = bCarServiceShopService.getH5StoreList(positionDTO);
            logger.info("subject:{},carStoreVOS:{}","H5 门店列表",JsonUtil.gsonStr(carStoreVOS));
            return Result.success(carStoreVOS).toJSON();
        }catch (Exception e){
            logger.error("查询H5 门店列表异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 按销量获取门店
     * @param positionDTO
     * @return
     */
    @PostMapping(value = "/getH5StoreSalesVolumeList")
    public String getH5StoreSalesVolumeList(@RequestBody PositionDTO positionDTO){
        logger.info("subject:{},positionDTO:{}","H5 按销量获取门店",JsonUtil.gsonStr(positionDTO));
        try{
            List<CarStoreVO> carStoreVOS = bCarServiceShopService.getH5StoreSalesVolumeList(positionDTO);
            logger.info("subject:{},carStoreVOS:{}","按销量获取门店",JsonUtil.gsonStr(carStoreVOS));
            return Result.success(carStoreVOS).toJSON();
        }catch (Exception e){
            logger.error("查询H5 按销量获取门店异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 首页推荐
     * @param positionDTO
     * @return
     */
    @PostMapping(value = "/getRecommendCarStore")
    public Result getRecommendCarStore(@RequestBody PositionDTO positionDTO){
        logger.info("subject:{},positionDTO:{}","H5 首页推荐",JsonUtil.gsonStr(positionDTO));
        try{
            RecommendCarStoreVO recommendCarStoreVO = bCarServiceShopService.getRecommendCarStore(positionDTO);
            logger.info("subject:{},recommendCarStoreVO:{}","首页推荐",JsonUtil.gsonStr(recommendCarStoreVO));
            return Result.success(recommendCarStoreVO);
        }catch (Exception e){
            logger.error("查询H5 首页推荐异常",e);
            return Result.failure("首页推荐异常");
        }
    }

    /**
     * 首页门店
     * @param positionDTO
     * @return
     */
    @PostMapping(value = "/getHomePageCarStore")
    public Result getHomePageCarStore(@RequestBody PositionDTO positionDTO){
        logger.info("subject:{},positionDTO:{}","首页门店",JsonUtil.gsonStr(positionDTO));
        try{
            RecommendCarStoreVO recommendCarStoreVO = bCarServiceShopService.getHomePageCarStore(positionDTO);
            logger.info("subject:{},recommendCarStoreVO:{}","首页门店",JsonUtil.gsonStr(recommendCarStoreVO));
            return Result.success(recommendCarStoreVO);
        }catch (Exception e){
            logger.error("查询首页门店异常",e);
            return Result.failure("首页门店异常");
        }
    }

    /**
     * 优先门店列表
     *
     * @param storeName
     * @param pagination
     * @return
     */
    @PostMapping("/getPriorityCarStore")
    public Result getPriorityCarStore(@RequestParam(value = "storeName",required = false) String storeName, @RequestBody Pagination pagination){
        logger.info("subject:{},storeName:{},pagination:{}","优先门店列表",storeName,JsonUtil.gsonStr(pagination));
        try{
            Result result = Result.success();
            List<Map<String, Object>> ret = bCarServiceShopService.getPriorityCarStore(storeName, pagination);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(ret));
            jMap.put("rows",ret);
            result.setData(jMap);
            return result;
        }catch (Exception e){
            logger.error("subject:{},e:{}","获取优先门店列表异常",e);
            return Result.failure("失败");
        }
    }

    /**
     * 门店首页推荐（首页显示商品）
     *
     * @return
     */
    @RequestMapping(value = "/updatePriorityCarStore", method = RequestMethod.POST)
    public Result updatePriorityCarStore(@RequestParam(value = "carStoreIds") String carStoreIds,
                                 @RequestParam(value = "priority") int priority,
                                 @RequestParam(value = "userId") Integer userId) {
        logger.info("subject: {}, carStoreIds: {}, priority: {}, userId: {}", "门店首页推荐（首页显示商品）", carStoreIds, priority, userId);
        try{
            bCarServiceShopService.updatePriorityCarStore(carStoreIds, priority, userId);
            return Result.success("门店首页推荐成功");
        }catch (Exception e){
            logger.error("subject:{},e:{}","门店首页推荐异常",e);
            return Result.failure("门店首页推荐失败");
        }
    }
}
