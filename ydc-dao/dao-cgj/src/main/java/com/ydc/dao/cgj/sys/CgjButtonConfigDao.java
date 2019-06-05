package com.ydc.dao.cgj.sys;


import com.ydc.commom.view.dto.cgj.sys.AdvertConfigQueDTO;
import com.ydc.model.cgj.sys.CgjButtonConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CgjButtonConfigDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    int insert(CgjButtonConfig record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    int insertSelective(CgjButtonConfig record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    CgjButtonConfig selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    int updateByPrimaryKeySelective(CgjButtonConfig record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    int updateByPrimaryKey(CgjButtonConfig record);

    /**
     * 查询按钮配置列表
     * @author: hejiangping
     * @date: 2019/1/8
     */
    List<Map<String, Object>> queryButtonConfigList(@Param("advertConfigQueDTO")AdvertConfigQueDTO advertConfigQueDTO);
    /**
     * 修改所有是否开启
     * @author: hejiangping
     * @date: 2019/1/8
     */
    int updateSwitchStatus(@Param("advertConfigQueDTO")AdvertConfigQueDTO advertConfigQueDTO);
    /**
     * 移动端查询按钮配置
     * @author: hejiangping
     * @date: 2019/1/8
     */
    List<Map<String, Object>> queryButtonConfigs(@Param("clientDictKey") String clientDictKey);
}