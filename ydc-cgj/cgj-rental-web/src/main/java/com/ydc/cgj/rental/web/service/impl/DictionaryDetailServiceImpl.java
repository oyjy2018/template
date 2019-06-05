package com.ydc.cgj.rental.web.service.impl;

import com.ydc.cgj.rental.web.feignService.UserFeignService;
import com.ydc.cgj.rental.web.service.DictionaryDetailService;
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


    @Override
    public List<DictionaryDetail> getConfigInfoByParentDictCode(String parentDictCode) {
        return userFeignService.getConfigInfoByParentDictCode(parentDictCode);
    }

    @Override
    public DictionaryDetail getDictionaryDetailByDictKey(String dictKey, String parentDictCode) {
        return userFeignService.getDictionaryDetailByDictKey(dictKey, parentDictCode);
    }

    @Override
    public DictionaryDetail getDictionaryDetailByDictValue(String dictValue, String parentDictCode) {
        return userFeignService.getDictionaryDetailByDictValue(dictValue, parentDictCode);
    }
}
