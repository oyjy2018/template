package com.ydc.cgj.ydhc.web.service;

import com.ydc.model.cgj.DictionaryDetail;

import java.util.List;

/**
 * @author
 * @create 2018-10-23 9:29
 **/
public interface DictionaryDetailService {

    /**
     * 获取数据字典子集
     *
     * @param parentDictCode
     * @return
     */
    List<DictionaryDetail> getConfigInfoByParentDictCode(String parentDictCode);


    /**
     * parentDictCode和dictKey
     *
     * @param parentDictCode
     * @param dictKey
     * @return
     */
    DictionaryDetail getDictionaryDetailByDictKey(String dictKey, String parentDictCode);


    /**
     * parentDictCode和dictValue
     *
     * @param parentDictCode
     * @param dictValue
     * @return
     */
    DictionaryDetail getDictionaryDetailByDictValue(String dictValue, String parentDictCode);
}
