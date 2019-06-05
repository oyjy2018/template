package com.ydc.dao.cgj.sys;


import com.ydc.commom.view.dto.cgj.sys.HomeModuleQueDTO;
import com.ydc.model.cgj.sys.HomeModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HomeModuleDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    int insert(HomeModule record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    int insertSelective(HomeModule record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    HomeModule selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    int updateByPrimaryKeySelective(HomeModule record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    int updateByPrimaryKey(HomeModule record);
    
    /**
     * 查询首页配置列表
     * @author: hejiangping
     * @date: 2018/12/27
     */
    List<Map<String, Object>> getHomeModuleList(@Param("homeModuleQueDTO") HomeModuleQueDTO homeModuleQueDTO);

    /**
     * 获取首页配置详情
     * @author: hejiangping
     * @date: 2019/1/2
     */
    Map<String,Object> getHomeModuleDetail(Integer id);

    /**
     * 查询首页模块
     * @author: hejiangping
     * @date: 2019/1/3
     */
    List<Map<String, Object>> queryHomeModules(@Param("sonModuleId") Integer sonModuleId);
}