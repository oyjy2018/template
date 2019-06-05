package com.ydc.dao.cgj.common;

import com.ydc.model.cgj.DictionaryDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictionaryDetailDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int insert(DictionaryDetail record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int insertSelective(DictionaryDetail record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    DictionaryDetail selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int updateByPrimaryKeySelective(DictionaryDetail record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int updateByPrimaryKey(DictionaryDetail record);

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
     * 查询所有有效的字典数据信息
     *
     * @return
     */
    List<DictionaryDetail> findDictionaryDetail();

    /**
     * 根据dictKey查询对应父类型下的字典信息
     *
     * @param dictKey
     * @param parentDictCode
     * @return
     */
    DictionaryDetail getDictionaryDetailByDictKey(@Param("dictKey") String dictKey, @Param("parentDictCode") String parentDictCode);

    /**
     * 根据dictValue查询对应父类型下的字典信息
     * @param dictValue
     * @param parentDictCode
     * @return
     */
    DictionaryDetail getDictionaryDetailByDictValue(@Param("dictValue") String dictValue, @Param("parentDictCode") String parentDictCode);

    /**
     * 获取H5配置信息
     * @return
     */
    List<DictionaryDetail> getH5Config();
}