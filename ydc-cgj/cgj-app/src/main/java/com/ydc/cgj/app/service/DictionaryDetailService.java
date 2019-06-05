package com.ydc.cgj.app.service;

import com.ydc.model.cgj.DictionaryDetail;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2018-10-22 17:10
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


    /**
     * 获取H5配置
     *
     * @return
     */
    Map<String, List<DictionaryDetail>> getH5Config(String key);
}
