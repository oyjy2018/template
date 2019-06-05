package com.ydc.dao.cgj.sys;


import com.ydc.model.cgj.sys.CommImg;
import org.apache.ibatis.annotations.Param;

public interface CommImgDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    int insert(CommImg record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    int insertSelective(CommImg record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    CommImg selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    int updateByPrimaryKeySelective(CommImg record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-12-26 19:13:38
     */
    int updateByPrimaryKey(CommImg record);

    /**
     * 根据关联ID和类型查图片，一对一的时候用
     * @author: hejiangping
     * @date: 2018/12/28
     */
    CommImg selectByCommIdAndType(@Param("commId") Integer commId,@Param("imgType") Integer imgType);

}