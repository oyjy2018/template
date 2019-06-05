package com.ydc.cgj.rental.web.service.impl;


import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.rental.web.feignService.StoreFeignService;
import com.ydc.cgj.rental.web.service.RentalStoreService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalStoreDTO;
import com.ydc.model.cgj.User;
import com.ydc.model.cgj.rental.RentalStore;
import com.ydc.commom.util.AnnotationDealUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;

/**
 * @author
 * @create 2018-11-16 19:08
 **/
@Service
public class RentalStoreServiceImpl implements RentalStoreService {

    private static final Logger logger = LogManager.getLogger(RentalStoreService.class);

    @Autowired
    StoreFeignService storeFeignService;

    @Override
    public String insert(RentalStore record) {
        if(record == null) return Result.failure("新增门店为空").toJSON();
        User user = WebShiroTokenManager.getUser();
        record.setCreateBy(user.getId());
        record.setCreateTime(new Date());
        record.setStatus(1);
        Map<String, Object> checkResult = AnnotationDealUtil.validate(record);
        logger.info("subject:{},e:{}","校验参数",checkResult);
        if(checkResult.get("result").equals(false)){
            return Result.failure(checkResult.get("message").toString()).toJSON();
        }
        return storeFeignService.insert(record);
    }

    @Override
    public String updateRentalStore(RentalStore record) {
        if(record == null) return Result.failure("更新门店为空").toJSON();
        if(StringUtil.isEmpty(record.getId()))return Result.failure("门店id为空").toJSON();
        User user = WebShiroTokenManager.getUser();
        record.setUpdateBy(user.getId());
        Map<String, Object> checkResult = AnnotationDealUtil.validate(record);
        logger.info("subject:{},e:{}","校验参数",checkResult);
        if(checkResult.get("result").equals(false)){
            return Result.failure(checkResult.get("message").toString()).toJSON();
        }
        return storeFeignService.updateRentalStore(record);
    }

    @Override
    public String getRentalStoreByStoreId(Integer storeId) {
        return storeFeignService.getRentalStoreByStoreId(storeId);
    }

    @Override
    public String getRentalStoreList(RentalStoreDTO rentalStoreDTO) {
        return storeFeignService.getRentalStoreList(rentalStoreDTO);
    }

    @Override
    public String updateRentalStoreStatus(RentalStoreDTO rentalStoreDTO) {
        if(rentalStoreDTO == null) return Result.failure("更新门店为空").toJSON();
        if(StringUtil.isEmpty(rentalStoreDTO.getStoreId()))return Result.failure("门店id为空").toJSON();
        User user = WebShiroTokenManager.getUser();
        rentalStoreDTO.setUserId(user.getId());
        return storeFeignService.updateRentalStoreStatus(rentalStoreDTO);
    }

    /**
     * 获取所有门店
     * @return
     */
    @Override
    public String getAllRentalStore() {
        return storeFeignService.getAllRentalStore();
    }

    /**
     * 获取门店树结构
     * @return
     */
    @Override
    public String getStoreTree() {
        return storeFeignService.getStoreTree();
    }

}
