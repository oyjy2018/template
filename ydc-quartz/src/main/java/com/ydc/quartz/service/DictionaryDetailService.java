package com.ydc.quartz.service;

import com.ydc.beans.mq.cache.service.CacheSendMessageService;
import com.ydc.commom.util.JsonUtil;
import com.ydc.dao.cgj.common.DictionaryDetailDao;
import com.ydc.model.cgj.DictionaryDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 * @create 2018-10-23 13:58
 **/
@Service
public class DictionaryDetailService {

    private static final Logger logger = LogManager.getLogger(DictionaryDetailService.class);

    @Autowired
    DictionaryDetailDao dictionaryDetailDao;

    @Resource
    CacheSendMessageService cacheSendMessageService;

    public List<DictionaryDetail> getConfigInfoByParentDictCode(String parentDictCode) {
        List<DictionaryDetail> dictionaryDetails = dictionaryDetailDao.getConfigInfoByParentDictCode(parentDictCode);
        logger.info("subject:{},parentDictCode:{},dictionaryDetails:{}","缓存数据查询db",parentDictCode,JsonUtil.gsonStr(dictionaryDetails));
        cacheSendMessageService.setCacheMessage(dictionaryDetails,parentDictCode);
        return dictionaryDetails;
    }

    public DictionaryDetail getDictionaryDetailByDictKey(String dictKey, String parentDictCode) {
        DictionaryDetail dictionaryDetail = dictionaryDetailDao.getDictionaryDetailByDictKey(dictKey,parentDictCode);
        logger.info("subject:{},dictKey：{},parentDictCode:{},dictionaryDetails:{}","缓存数据查询db",dictKey,parentDictCode,JsonUtil.gsonStr(dictionaryDetail));
        cacheSendMessageService.setCacheMessage(dictionaryDetail,parentDictCode);
        return dictionaryDetail;
    }

    public DictionaryDetail getDictionaryDetailByDictValue(String dictValue, String parentDictCode) {
        DictionaryDetail dictionaryDetail = dictionaryDetailDao.getDictionaryDetailByDictValue(dictValue,parentDictCode);
        logger.info("subject:{},dictValue：{},parentDictCode:{},dictionaryDetails:{}","缓存数据查询db",dictValue,parentDictCode,JsonUtil.gsonStr(dictionaryDetail));
        cacheSendMessageService.setCacheMessage(dictionaryDetail,parentDictCode);
        return dictionaryDetail;
    }
}
