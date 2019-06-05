package com.ydc.dao.cgj.service;

import com.ydc.commom.view.dto.cgj.service.RollTemplateDTO;
import com.ydc.commom.view.vo.cgj.order.BTicketTemplateVO;
import com.ydc.model.cgj.BRollTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BRollTemplateDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    int insert(BRollTemplate record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    int insertSelective(BRollTemplate record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    BRollTemplate selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    int updateByPrimaryKeySelective(BRollTemplate record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-30 09:46:38
     */
    int updateByPrimaryKey(BRollTemplate record);

    /**
     * 卷模板列表
     * @param rollTemplateDTO
     * @return
     */
    List<BTicketTemplateVO> getTicketTemplateList(@Param("rollTemplateDTO") RollTemplateDTO rollTemplateDTO);

    /**
     * 批量插入卷模板
     * @param list
     */
    void insertRollTemplateBatch(List<BRollTemplate> list);
}