package com.ydc.service.order.service.impl;


import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.OrderReqDTO;
import com.ydc.commom.view.vo.cgj.order.OrderLogisticsVO;
import com.ydc.dao.cgj.order.OrderLogisticsDao;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.OrderLogistics;
import com.ydc.service.order.service.DictionaryDetailService;
import com.ydc.service.order.service.OrderLogisticsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单物流
 *
 * @author gongjin
 * @create 2018-09-10 12:08
 **/
@Service
public class OrderLogisticsServiceImpl implements OrderLogisticsService {

    private static Logger logger = LogManager.getLogger(OrderLogisticsService.class);

    @Autowired
    OrderLogisticsDao orderLogisticsDao;
    @Autowired
    DictionaryDetailService dictionaryDetailService;

    @Override
    public List<OrderLogisticsVO> getOrderLogistics(Integer orderId) {
        List<OrderLogistics> orderLogisticsList = orderLogisticsDao.getOrderLogistics(orderId);
        logger.info("查询订单物流:"+JsonUtil.gsonStr(orderLogisticsList));
        List<OrderLogisticsVO> orderLogisticsVOList = new ArrayList<>();
        if(orderLogisticsList == null || orderLogisticsList.isEmpty())return orderLogisticsVOList;
        OrderLogisticsVO orderLogisticsVO;
        for(OrderLogistics orderLogistics : orderLogisticsList){
            orderLogisticsVO = new OrderLogisticsVO();
            DictionaryDetail dictionaryDetail = DictionaryUtil.getDictionaryDetailByDictValue(orderLogistics.getLogisticsCompany(),DictionaryConstant.DICT_CODE_KDWL_CONFIG)
                    .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictValue(orderLogistics.getLogisticsCompany(),DictionaryConstant.DICT_CODE_KDWL_CONFIG));
            logger.info("查询订单物流配置:logisticsCompany:"+orderLogistics.getLogisticsCompany()+";dictionaryDetail:"+JsonUtil.gsonStr(dictionaryDetail));
            orderLogisticsVO.setLogisticsCompanyCode(dictionaryDetail.getDictKey());
            orderLogisticsVO.setLogisticsCompanyName(orderLogistics.getLogisticsCompany());
            orderLogisticsVO.setLogisticsOrder(orderLogistics.getLogisticsOrder());
            if(StringUtil.isNotEmpty(dictionaryDetail.getRemark1())){
                orderLogisticsVO.setLogisticsUrl(String.format(dictionaryDetail.getRemark1(),orderLogistics.getLogisticsOrder(),dictionaryDetail.getDictKey()));
            }
            orderLogisticsVOList.add(orderLogisticsVO);
        }
        return orderLogisticsVOList;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return orderLogisticsDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OrderLogistics record) {
        return orderLogisticsDao.insert(record);
    }

    @Override
    public int insertSelective(OrderLogistics record) {
        return orderLogisticsDao.insertSelective(record);
    }

    @Override
    public OrderLogistics selectByPrimaryKey(Integer id) {
        return orderLogisticsDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderLogistics record) {
        return orderLogisticsDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderLogistics record) {
        return orderLogisticsDao.updateByPrimaryKey(record);
    }

    @Override
    public int insertBySonOrderId(OrderReqDTO ord) {
        Integer orderId = Integer.valueOf(ord.getOrderId());
        Integer userId = Integer.valueOf(ord.getUserId());
        return orderLogisticsDao.insertBySonOrderId(orderId, ord.getLogisticsCompany(), ord.getLogisticsOrder(), userId);
    }

    @Override
    public int insertByOrderCommodityIds(OrderReqDTO ord) {
        Integer userId = Integer.valueOf(ord.getUserId());
        return orderLogisticsDao.insertByOrderCommodityIds(ord.getOrderCommodityIds(), ord.getLogisticsCompany(), ord.getLogisticsOrder(), userId);
    }
}
