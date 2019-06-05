package com.ydc.quartz.service;

import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.OrderConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.dao.cgj.order.OrderCommodityDao;
import com.ydc.dao.cgj.order.OrderDao;
import com.ydc.dao.cgj.store.CommodityDao;
import com.ydc.dao.cgj.store.CommodityModelDao;
import com.ydc.dao.cgj.store.StoreShopCartCommodityDao;
import com.ydc.model.cgj.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 自动关闭30分钟内未支付的订单
 *
 * @author gongjin
 * @create 2018-09-18 9:45
 **/
@Service("autoCloseOrderService")
public class AutoCloseOrderService {

    private static final Logger logger = LogManager.getLogger(AutoCloseOrderService.class);

    @Autowired
    OrderDao orderDao;
    @Autowired
    DictionaryDetailService dictionaryDetailService;
    @Autowired
    OrderCommodityDao orderCommodityDao;
    @Autowired
    CommodityDao commodityDao;
    @Autowired
    StoreShopCartCommodityDao storeShopCartCommodityDao;
    @Autowired
    CommodityModelDao commodityModelDao;

    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    public void job() throws Exception{
        Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_KEY_ZDGBDD, DictionaryConstant.DICT_PARENT_DICT_CODE_MQDELAYTIMELEVEL)
                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_KEY_ZDGBDD, DictionaryConstant.DICT_PARENT_DICT_CODE_MQDELAYTIMELEVEL)));
        List<Order> orderList = orderDao.getOrderByStatus(OrderConstant.ORDER_STATUS_UNPAID);
        logger.info("subject:自动关闭未支付的订单,size:"+(orderList == null ? 0 : orderList.size())+",dic:"+dic.get().getDictValue());
        if(orderList == null || orderList.isEmpty())return;
        List<Integer> orders = new ArrayList<>();
        for(Order or : orderList){
            if(DateUtil.diffDays(DateUtil.parseDate(or.getCreateTime(),DateUtil.DATAFORMAT_STR),DateUtil.parseDate(new Date(),DateUtil.DATAFORMAT_STR)) > 0 ||
                    DateUtil.getApartMinute(DateUtil.parseDate(or.getCreateTime(),DateUtil.DATATIMEF_STR),DateUtil.parseDate(new Date(),DateUtil.DATATIMEF_STR)) > Integer.parseInt(dic.get().getRemark1())){
                orders.add(or.getId());
            }
        }
        if(orders.isEmpty())return;
        List<Integer> opList = new ArrayList<>();
        for(Integer in : orders){
            opList.add(in);
            if(opList.size() % 100 == 0){
                logger.info("subject:{},opList:{}","自动关闭未支付的订单",JsonUtil.gsonStr(opList));
                orderDao.updateBatch(opList,OrderConstant.ORDER_STATUS_UNPAID);
                opList.clear();
            }
        }
        if(opList.size() > 0){
            logger.info("subject:{},opList:{}","自动关闭未支付的订单",JsonUtil.gsonStr(opList));
            orderDao.updateBatch(opList,OrderConstant.ORDER_STATUS_UNPAID);
        }
        //处理库存
        for(Integer in : orders){
            List<OrderCommodity> orderCommodityList = orderCommodityDao.getOrderCommodityByOrderId(in);
            for(OrderCommodity orc : orderCommodityList){
                //更新商品模型表
                if (!updateCommodityModelNum(orc.getCommodityModelId(), orc.getCommodityModelNumber())) {
                    //回滚
                    logger.error("更新商品模型表失败");
                    throw new NullPointerException();
                }
                //更新商品表
                updateCommodityNum(orc.getCommodityId(), orc.getCommodityModelNumber());
            }
        }
    }
    /**
     *
     * @param commodityModelId
     * @param number
     * @return
     */
    private boolean updateCommodityModelNum(Integer commodityModelId, Integer number){
        CommodityModel commodityModel= commodityModelDao.selectByPrimaryKey(commodityModelId);
        if ( null==commodityModel ||null==commodityModel.getInventory() || commodityModel.getInventory().intValue()<0){
            return false;
        }
        Integer soldNumber=commodityModel.getSoldNumber(),
                inventory=commodityModel.getInventory();
        if ( StringUtil.isEmpty(soldNumber)){
            soldNumber=0;
        }
        inventory=inventory.intValue()+number.intValue();
        soldNumber=soldNumber-number.intValue();

        commodityModel.setInventory(inventory);
        commodityModel.setSoldNumber(soldNumber);
        int count=commodityModelDao.updateByIdAndVersion(commodityModel);
        if (count==1){
            return true;
        }
        return false;
    }

    /**
     *
     * @param commodityId
     * @param number
     * @return
     */
    private boolean updateCommodityNum(Integer commodityId, Integer number){
        //更新商品主表
        Commodity commodity=commodityDao.selectByPrimaryKey(commodityId);
        if (commodity == null)return false;
        Integer soldNumber=commodity.getSoldNumber(),
                totalInventory=commodity.getTotalInventory();

        if (StringUtil.isEmpty(soldNumber)){
            soldNumber=0;
        }
        if (StringUtil.isEmpty(totalInventory)){
            totalInventory=0;
        }
        totalInventory=totalInventory.intValue()+number.intValue();
        soldNumber=soldNumber.intValue()-number.intValue();
        commodity.setTotalInventory(totalInventory);
        commodity.setSoldNumber(soldNumber);
        commodity.setUpdateTime(new Date());
        if (commodityDao.updateByPrimaryKeySelective(commodity)==1){
            return true;
        }
        return false;
    }

}
