package com.ydc.service.ydhc.user.service.impl;

import com.ydc.beans.mq.cache.service.CacheSendMessageService;
import com.ydc.commom.util.JsonUtil;
import com.ydc.dao.cgj.common.DictionaryDetailDao;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.service.ydhc.user.service.DictionaryDetailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据字典明细
 *
 * @author gongjin
 * @create 2018-09-04 11:32
 **/
@Service
public class DictionaryDetailServiceImpl implements DictionaryDetailService {

    private static final Logger logger = LogManager.getLogger(DictionaryDetailService.class);

    @Autowired
    DictionaryDetailDao dictionaryDetailDao;

    @Resource
    CacheSendMessageService cacheSendMessageService;


    @Override
    public List<DictionaryDetail> getConfigInfoByParentDictCode(String parentDictCode) {
        List<DictionaryDetail> dictionaryDetails = dictionaryDetailDao.getConfigInfoByParentDictCode(parentDictCode);
        logger.info("subject:{},parentDictCode:{},dictionaryDetails:{}","缓存数据查询db",parentDictCode,JsonUtil.gsonStr(dictionaryDetails));
        cacheSendMessageService.setCacheMessage(dictionaryDetails,parentDictCode);
        return dictionaryDetails;
    }

    @Override
    public DictionaryDetail getDictionaryDetailByDictKey(String dictKey, String parentDictCode) {
        DictionaryDetail dictionaryDetail = dictionaryDetailDao.getDictionaryDetailByDictKey(dictKey,parentDictCode);
        logger.info("subject:{},dictKey：{},parentDictCode:{},dictionaryDetails:{}","缓存数据查询db",dictKey,parentDictCode,JsonUtil.gsonStr(dictionaryDetail));
        List<DictionaryDetail> dictionaryDetails = Lists.newArrayList();
        dictionaryDetails.add(dictionaryDetail);
        cacheSendMessageService.setCacheMessage(dictionaryDetails,parentDictCode);
        return dictionaryDetail;
    }

    @Override
    public DictionaryDetail getDictionaryDetailByDictValue(String dictValue, String parentDictCode) {
        DictionaryDetail dictionaryDetail = dictionaryDetailDao.getDictionaryDetailByDictValue(dictValue,parentDictCode);
        logger.info("subject:{},dictValue：{},parentDictCode:{},dictionaryDetails:{}","缓存数据查询db",dictValue,parentDictCode,JsonUtil.gsonStr(dictionaryDetail));
        List<DictionaryDetail> dictionaryDetails = Lists.newArrayList();
        dictionaryDetails.add(dictionaryDetail);
        cacheSendMessageService.setCacheMessage(dictionaryDetails,parentDictCode);
        return dictionaryDetail;
    }

    @Override
    public Map<String,List<DictionaryDetail>> getH5Config() {
        Map<String, List<DictionaryDetail>> dictionaryMaps =  dictionaryDetailDao.getH5Config().stream().collect(Collectors.groupingBy(DictionaryDetail::getParentDictCode));
        return dictionaryMaps;
    }
}
