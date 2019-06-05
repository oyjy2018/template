package com.ydc.service.ydhc.user.service;

import com.ydc.model.cgj.DictionaryDetail;

import java.util.List;
import java.util.Map;

/**
 * 数据字典明细
 *
 * @author gongjin
 * @create 2018-09-04 11:31
 **/
public interface DictionaryDetailService {

    /**
     * 根据parent_dict_code查询对应的信息
     *
     * @author: dailongting
     * @param: parentDictCode 字典parent_dict_code
     * @date: 2017年6月23日
     * @return: List<DictionaryDetail>
     */
    List<DictionaryDetail> getConfigInfoByParentDictCode(String parentDictCode);

    /**
     * 根据dictKey查询对应父类型下的字典信息
     *
     * @param dictKey
     * @param parentDictCode
     * @return
     */
    DictionaryDetail getDictionaryDetailByDictKey(String dictKey, String parentDictCode);

    /**
     * parentDictCode和dictValue
     * @param parentDictCode
     * @param dictValue
     * @return
     */
    DictionaryDetail getDictionaryDetailByDictValue(String dictValue, String parentDictCode);

    /**
     * 获取H5配置信息
     * @return
     */
    Map<String,List<DictionaryDetail>> getH5Config();
}
