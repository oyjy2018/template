package com.ydc.service.store.service.impl;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.CommodityConstant;
import com.ydc.commom.view.dto.cgj.CommodityShowDTO;
import com.ydc.commom.view.dto.cgj.StoreReqDTO;
import com.ydc.model.cgj.Commodity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("commodityServiceRedis")
public class CommodityServiceRedisImpl extends CommodityServiceImpl {

    private static Logger logger = LogManager.getLogger(CommodityServiceRedisImpl.class);

    @Override
    public Integer saveOrUpdateCommodity(StoreReqDTO srb) {
        Integer commodityId = super.saveOrUpdateCommodity(srb);
        //更新缓存
        if(commodityId != null) {
            CommodityShowDTO commodityShowDTO = getCommodityDetailById(commodityId);
            RedisUtil.redisHashPut(CommodityConstant.COMMODITY_REDIS_KEY, String.valueOf(commodityId), commodityShowDTO);
        }
        return commodityId;
    }

    @Override
    public int updateTotalInventoryById(Integer commodityId) {
        int result = super.updateTotalInventoryById(commodityId);
        //更新缓存
        new Thread(() -> {
            try {
                Commodity commodity = commodityDao.selectByPrimaryKey(commodityId);
                Object object = RedisUtil.redisHashGet(CommodityConstant.COMMODITY_REDIS_KEY, String.valueOf(commodityId));
                if (commodity != null && object != null) {
                    CommodityShowDTO commodityShowDTO = (CommodityShowDTO) object;
                    commodityShowDTO.setSoldNumber(commodity.getSoldNumber());
                    commodityShowDTO.setTotalInventory(commodity.getTotalInventory());
                    RedisUtil.redisHashPut(CommodityConstant.COMMODITY_REDIS_KEY, String.valueOf(commodityId), commodityShowDTO);
                }
            }catch (Exception e){
                logger.error("更新库存更新缓存异常", e);
            }
        }).start();
        return result;
    }
}
