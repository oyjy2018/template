package com.ydc.quartz.service;

import com.ydc.beans.commom.CarShopCommon;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.service.CarStoreDTO;
import com.ydc.dao.cgj.service.BCarServiceShopDao;
import com.ydc.model.cgj.BCarServiceShop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author
 * @create 2019-01-21 11:29
 **/
@Service("refreshCarStoreService")
public class RefreshCarStoreService {

    private static final Logger logger = LogManager.getLogger(RefreshCarStoreService.class);

    @Autowired
    BCarServiceShopDao bCarServiceShopDao;

    @Transactional(value = "cgjTransactionManager")
    public void job() throws Exception{
        CarStoreDTO carStoreDTO = new CarStoreDTO();
        carStoreDTO.setUpdateBy(1);
        Result result = CarShopCommon.getStoreList(carStoreDTO);
        if(ResultConstant.RESULT_CODE_FAILURE == result.getCode()){
            logger.info("subject:{},message:{}","自动获取汽车门店",result.getMessage());
        }
        List<BCarServiceShop> list = (List<BCarServiceShop>)result.getData();
        bCarServiceShopDao.insertCarServiceBatch(list);
        //更新门店状态
        List<String> storeCodes = Lists.newArrayList();
        list.forEach(item->{
            storeCodes.add(item.getStoreCode());
        });
        logger.info("subject:{},storeCodes:{},size:{}","门店code集合",JsonUtil.gsonStr(storeCodes),(storeCodes== null || storeCodes.isEmpty()) ? 0 : storeCodes.size());
        if(storeCodes== null || storeCodes.isEmpty())return;
        bCarServiceShopDao.batchUpdateCarShopStatus(storeCodes,carStoreDTO.getUpdateBy());
    }
}
