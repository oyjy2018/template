package com.ydc.quartz.service;

import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.dao.cgj.common.DictionaryDetailDao;
import com.ydc.dao.cgj.order.OrderLogisticsDao;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.quartz.task.AutoNotarizeReceivingJob;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 确认收货（X天后，系统自动确认收货）
 * @author gongjin
 * @create 2018-09-10 15:54
 **/
@Service(value = "autoNotarizeReceivingService")
public class AutoNotarizeReceivingService {

    private static final Logger logger = LogManager.getLogger(AutoNotarizeReceivingJob.class);


    @Autowired
    OrderLogisticsDao orderLogisticsDao;
    @Autowired
    DictionaryDetailService dictionaryDetailService;

    public void job(){
        List<Map<String,Object>> list = orderLogisticsDao.getOrderLogisticsTask();
        logger.info("subject:确认收货（X天后，系统自动确认收货,size:"+(list == null ? 0 : list.size()));
        if(list == null || list.isEmpty())return;
        Date data = new Date();
        List<Map<String,Object>> newList = new ArrayList<>();
        Map<String,Object> newMap = null;
        Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_KEY_ZDQRSH, DictionaryConstant.DICT_PARENT_DICT_CODE_MQDELAYTIMELEVEL)
                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_KEY_ZDQRSH, DictionaryConstant.DICT_PARENT_DICT_CODE_MQDELAYTIMELEVEL)));
        int orderMaxDay = Integer.valueOf(dic.get().getRemark1()).intValue();
        for(Map<String,Object> map : list){
            if(DateUtil.diffDays(DateUtil.parseDate(map.get("deliveryTime").toString()),DateUtil.parseDate(data,DateUtil.DATAFORMAT_STR)) > orderMaxDay){
                newMap = new HashMap<>();
                newMap.put("orderId",map.get("orderId"));
                newList.add(newMap);
            }
        }
        logger.info("subject:确认收货（X天后，系统自动确认收货,newsize:"+newList.size());
        if(newList == null || newList.isEmpty())return;
        List<Map<String,Object>> sqlList = new ArrayList<>();
        for(Map<String,Object> map : newList){
            sqlList.add(map);
            if(sqlList.size() % 100 == 0){
                orderLogisticsDao.batchUpdateOrderLogistics(sqlList);
                sqlList.clear();
            }
        }
        if(sqlList.size() > 0){
            orderLogisticsDao.batchUpdateOrderLogistics(sqlList);
        }
    }
}
