package com.ydc.service.order.service.impl;

import com.ydc.commom.view.dto.cgj.OrderReqDTO;
import com.ydc.dao.cgj.order.OrderCommodityDao;
import com.ydc.model.cgj.OrderCommodity;
import com.ydc.service.order.service.OrderCommodityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 订单商品表
 *
 * @author gongjin
 * @create 2018-09-08 15:13
 **/
@Service
public class OrderCommodityServiceImpl implements OrderCommodityService {

    private static Logger logger = LogManager.getLogger(OrderCommodityServiceImpl.class);

    @Resource
    OrderCommodityDao orderCommodityDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return orderCommodityDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OrderCommodity record) {
        return orderCommodityDao.insert(record);
    }

    @Override
    public int insertSelective(OrderCommodity record) {
        return orderCommodityDao.insertSelective(record);
    }

    @Override
    public OrderCommodity selectByPrimaryKey(Integer id) {
        return orderCommodityDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderCommodity record) {
        return orderCommodityDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderCommodity record) {
        return orderCommodityDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> getOrderCommodityList(OrderReqDTO orderReqDTO) {
        return orderCommodityDao.getOrderCommodityList(orderReqDTO);
    }

    @Override
    public Map<String, Object> getOrderCommodityListCount(OrderReqDTO orderReqDTO) {
        return orderCommodityDao.getOrderCommodityListCount(orderReqDTO);
    }

    @Override
    public List<OrderCommodity> getOrderCommodityByOrderCommodityIds(String orderCommodityIds) {
        return orderCommodityDao.getOrderCommodityByOrderCommodityIds(orderCommodityIds);
    }
}
