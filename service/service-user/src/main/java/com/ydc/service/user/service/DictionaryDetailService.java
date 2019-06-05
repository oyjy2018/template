package com.ydc.service.user.service;

import com.ydc.commom.view.dto.cgj.sys.DictionaryDetailDTO;
import com.ydc.commom.view.dto.cgj.sys.ServiceFuncDTO;
import com.ydc.commom.view.vo.cgj.DictionaryDedailVO;
import com.ydc.model.cgj.DictionaryDetail;
import org.apache.ibatis.annotations.Param;

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
     * 根据dictValue查询对应父类型下的字典信息
     * @param dictValue
     * @param parentDictCode
     * @return
     */
    DictionaryDetail getDictionaryDetailByDictValue(String dictValue, String parentDictCode);

    /**
     * 获取H5配置信息
     * @return
     */
    Map<String,List<DictionaryDetail>> getH5Config();

    /**
     * 保存字典明细表数据
     * @return
     */
    Integer insertDictionaryDatail(DictionaryDetailDTO dictionaryDetailDTO);


    /**
     * 更新字典明细表数据
     * @return
     */
    Integer updateDictionaryDatail(DictionaryDetailDTO dictionaryDetailDTO);

    /**
     * 查询字典明细表数据
     * @return
     */
    DictionaryDedailVO getDictionaryDatail(DictionaryDetail dictionaryDetail);

    /**
     * 删除字典明细表数据
     * @return
     */
    Integer delDictionaryDatail(DictionaryDetail dictionaryDetail);
}
