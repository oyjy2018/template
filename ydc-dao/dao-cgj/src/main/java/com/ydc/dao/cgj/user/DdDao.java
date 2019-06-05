package com.ydc.dao.cgj.user;

import com.ydc.model.cgj.Dd;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DdDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    int insert(Dd record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    int insertSelective(Dd record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    Dd selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    int updateByPrimaryKeySelective(Dd record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 11:05:48
     */
    int updateByPrimaryKey(Dd record);

    /**
     * 查询钉钉有效配置
     *
     * @return
     */
    List<Dd> getDdConfig();

    /**
     * 根据服务标识查询钉钉配置
     * @param serviceIdentifying
     * @return
     */
    Dd getDdConfigByServiceIdentifying(@Param("serviceIdentifying") String serviceIdentifying);
}