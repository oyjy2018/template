package com.ydc.dao.cgj.common;

import com.ydc.model.cgj.SysErrorLogRocketmq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysErrorLogRocketmqDao {

	/**
	 * 根据主键删除 参数:主键 返回:删除个数
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	int insert(SysErrorLogRocketmq record);

	/**
	 * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	int insertSelective(SysErrorLogRocketmq record);

	/**
	 * 根据主键查询 参数:查询条件,主键值 返回:对象
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	SysErrorLogRocketmq selectByPrimaryKey(Integer id);

	/**
	 * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	int updateByPrimaryKeySelective(SysErrorLogRocketmq record);

	/**
	 * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	int updateByPrimaryKey(SysErrorLogRocketmq record);

	/**
	 * 获取待处理消息
	 * @return
	 */
	List<SysErrorLogRocketmq> getPendingSysErrorLogRockeMq();

	/**
	 * 消息提供执行失败，更新数据
	 * @param id
	 * @return
	 */
	int updateSendNum(@Param("id") Integer id);

	/**
	 * 消息提供执行成功，修改状态
	 * @param id
	 * @return
	 */
	int updateSucceed(@Param("id") Integer id);
}