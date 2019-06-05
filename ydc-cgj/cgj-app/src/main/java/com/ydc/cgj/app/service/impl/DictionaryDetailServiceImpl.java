package com.ydc.cgj.app.service.impl;

import com.ydc.beans.mq.cache.service.CacheSendMessageService;
import com.ydc.cgj.app.feignService.UserFeignService;
import com.ydc.cgj.app.service.DictionaryDetailService;
import com.ydc.model.cgj.DictionaryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2018-10-22 17:11
 **/
@Service("dictionaryDetailService")
public class DictionaryDetailServiceImpl implements DictionaryDetailService {

    @Autowired
    UserFeignService userFeignService;
    @Resource
    CacheSendMessageService cacheSendMessageService;


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

    @Override
    public Map<String, List<DictionaryDetail>> getH5Config(String key) {
        Map<String, List<DictionaryDetail>> map = userFeignService.getH5Config();
        cacheSendMessageService.setCacheMessage(map, key);
        return map;
    }
}
