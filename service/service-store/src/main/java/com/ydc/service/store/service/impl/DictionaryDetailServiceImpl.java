package com.ydc.service.store.service.impl;

import com.ydc.beans.mq.cache.service.CacheSendMessageService;
import com.ydc.dao.cgj.common.DictionaryDetailDao;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.service.store.service.DictionaryDetailService;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 * @create 2018-10-22 16:31
 **/
@Service
public class DictionaryDetailServiceImpl implements DictionaryDetailService {

    @Resource
    CacheSendMessageService cacheSendMessageService;

    @Resource
    DictionaryDetailDao dictionaryDetailDao;

    @Override
    public List<DictionaryDetail> getConfigInfoByParentDictCode(String parentDictCode) {
        List<DictionaryDetail> dictionaryDetails = dictionaryDetailDao.getConfigInfoByParentDictCode(parentDictCode);
        cacheSendMessageService.setCacheMessage(dictionaryDetails,parentDictCode);
        return dictionaryDetails;
    }

    @Override
    public DictionaryDetail getDictionaryDetailByDictKey(String dictKey, String parentDictCode) {
        DictionaryDetail dictionaryDetail = dictionaryDetailDao.getDictionaryDetailByDictKey(dictKey,parentDictCode);
        List<DictionaryDetail> dictionaryDetails = Lists.newArrayList();
        dictionaryDetails.add(dictionaryDetail);
        cacheSendMessageService.setCacheMessage(dictionaryDetails,parentDictCode);
        return dictionaryDetail;
    }

    @Override
    public DictionaryDetail getDictionaryDetailByDictValue(String dictValue, String parentDictCode) {
        DictionaryDetail dictionaryDetail = dictionaryDetailDao.getDictionaryDetailByDictValue(dictValue,parentDictCode);
        List<DictionaryDetail> dictionaryDetails = Lists.newArrayList();
        dictionaryDetails.add(dictionaryDetail);
        cacheSendMessageService.setCacheMessage(dictionaryDetails,parentDictCode);
        return dictionaryDetail;
    }
}
