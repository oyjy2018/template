package com.ydc.dao.cgj.sys;


import com.ydc.commom.view.dto.cgj.sys.AdvertConfigQueDTO;
import com.ydc.model.cgj.sys.AdvertConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdvertConfigDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    int insert(AdvertConfig record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    int insertSelective(AdvertConfig record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    AdvertConfig selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    int updateByPrimaryKeySelective(AdvertConfig record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    int updateByPrimaryKey(AdvertConfig record);

    /**
     * 查询广告配置列表
     * @author: hejiangping
     * @date: 2019/1/2
     */
    List<Map<String, Object>> queryAdvertConfigList(@Param("advertConfigQueDTO") AdvertConfigQueDTO advertConfigQueDTO);
    /**
     * 获取广告配置详情
     * @author: hejiangping
     * @date: 2019/1/2
     */
    Map<String,Object> getAdvertConfigDetail(Integer id);
    /**
     * 查询广告
     * @author: hejiangping
     * @date: 2019/1/3
     */
    List<Map<String, Object>> queryAdverts(@Param("clientDictKey") String clientDictKey,@Param("moduleDictKey") String moduleDictKey);
}