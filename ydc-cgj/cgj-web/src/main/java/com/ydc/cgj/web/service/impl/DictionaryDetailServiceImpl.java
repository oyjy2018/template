package com.ydc.cgj.web.service.impl;

import com.ydc.cgj.web.feignService.UserFeignService;
import com.ydc.cgj.web.service.DictionaryDetailService;
import com.ydc.cgj.web.service.ServiceFuncService;
import com.ydc.commom.view.dto.cgj.sys.DictionaryDetailDTO;
import com.ydc.model.cgj.DictionaryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @create 2018-10-22 16:31
 **/
@Service
public class DictionaryDetailServiceImpl implements DictionaryDetailService {

    @Autowired
    UserFeignService userFeignService;
    @Autowired
    ServiceFuncService serviceFuncService;

    @Override
//    @RedisCache
    public List<DictionaryDetail> getConfigInfoByParentDictCode(String parentDictCode) {
        List<DictionaryDetail> dictionaryDetails = userFeignService.getConfigInfoByParentDictCode(parentDictCode);
        return dictionaryDetails;
    }

    @Override
//    @RedisCache
    public DictionaryDetail getDictionaryDetailByDictKey(String dictKey, String parentDictCode) {
        DictionaryDetail dictionaryDetail = userFeignService.getDictionaryDetailByDictKey(dictKey, parentDictCode);
        return dictionaryDetail;
    }

    @Override
    public DictionaryDetail getDictionaryDetailByDictValue(String dictValue, String parentDictCode) {
        DictionaryDetail dictionaryDetail = userFeignService.getDictionaryDetailByDictValue(dictValue, parentDictCode);
        return dictionaryDetail;
    }

    @Override
    public String insertDictionaryDatail(DictionaryDetailDTO dictionaryDetail) {
        String result = userFeignService.insertDictionaryDatail(dictionaryDetail);
        // 更新服务缓存
        serviceFuncService.writeRedis();
        return result;
    }

    @Override
    public String updateDictionaryDatail(DictionaryDetailDTO dictionaryDetail) {
        String result = userFeignService.updateDictionaryDatail(dictionaryDetail);
        // 更新服务缓存
        serviceFuncService.writeRedis();
        return result;
    }

    @Override
    public String getDictionaryDatail(DictionaryDetail dictionaryDetail) {
        return userFeignService.getDictionaryDatail(dictionaryDetail);
    }

    @Override
    public String delDictionaryDatail(DictionaryDetail dictionaryDetail) {
        String result = userFeignService.delDictionaryDatail(dictionaryDetail);
        // 更新服务缓存
        serviceFuncService.writeRedis();
        return result;
    }
}
