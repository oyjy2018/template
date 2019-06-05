package com.ydc.cgj.ydhc.web.service.impl;

import com.ydc.cgj.ydhc.web.feignService.UserFeignService;
import com.ydc.cgj.ydhc.web.service.DictionaryDetailService;
import com.ydc.model.cgj.DictionaryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @create 2018-10-23 9:30
 **/
@Service("dictionaryDetailService")
public class DictionaryDetailServiceImpl implements DictionaryDetailService {

    @Autowired
    UserFeignService userFeignService;

    @Override
    public List<DictionaryDetail> getConfigInfoByParentDictCode(String parentDictCode) {
        List<DictionaryDetail> dictionaryDetails = userFeignService.getConfigInfoByParentDictCode(parentDictCode);
        return dictionaryDetails;
    }

    @Override
    public DictionaryDetail getDictionaryDetailByDictKey(String dictKey, String parentDictCode) {
        DictionaryDetail dictionaryDetail = userFeignService.getDictionaryDetailByDictKey(dictKey, parentDictCode);
        return dictionaryDetail;
    }

    @Override
    public DictionaryDetail getDictionaryDetailByDictValue(String dictValue, String parentDictCode) {
        DictionaryDetail dictionaryDetail = userFeignService.getDictionaryDetailByDictValue(dictValue, parentDictCode);
        return dictionaryDetail;
    }
}
