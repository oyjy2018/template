package com.ydc.dao.cgj.common;

import com.ydc.model.cgj.SysErrorLogHttp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysErrorLogHttpDao {

	/**
	 * 根据主键删除 参数:主键 返回:删除个数
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	int insert(SysErrorLogHttp record);

	/**
	 * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	int insertSelective(SysErrorLogHttp record);

	/**
	 * 根据主键查询 参数:查询条件,主键值 返回:对象
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	SysErrorLogHttp selectByPrimaryKey(Integer id);

	/**
	 * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	int updateByPrimaryKeySelective(SysErrorLogHttp record);

	/**
	 * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	int updateByPrimaryKey(SysErrorLogHttp record);

	/**
	 * 获取需要重试的记录
	 * @return
	 */
	List<SysErrorLogHttp> getReSysErrorLogHttp();

	/**
	 * 更新重试结果
	 * @param id
	 * @param result
	 * @return
	 */
	Integer updateRepResult(@Param(value = "id") Integer id, @Param(value = "result") boolean result);
}