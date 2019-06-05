package com.ydc.dao.cgj.common;

import com.ydc.model.cgj.TaskDetails;

import java.util.List;

public interface TaskDetailsDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    int insert(TaskDetails record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    int insertSelective(TaskDetails record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    TaskDetails selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    int updateByPrimaryKeySelective(TaskDetails record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    int updateByPrimaryKey(TaskDetails record);

    /**
     * 获取有效的定时器任务
     * @return
     */
    List<TaskDetails> getValidTaskDetail();
}