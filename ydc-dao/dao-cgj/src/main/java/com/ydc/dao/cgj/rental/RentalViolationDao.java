package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.RentalViolationDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalViolationUpdateDealStatusDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalViolationVO;
import com.ydc.model.cgj.rental.RentalViolation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RentalViolationDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    int insert(RentalViolation record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    int insertSelective(RentalViolation record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    RentalViolation selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    int updateByPrimaryKeySelective(RentalViolation record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-21 20:22:26
     */
    int updateByPrimaryKey(RentalViolation record);

    /**
     *通过条件获取违章列表
     * @param rentalViolationDTO
     * @return
     */
    List<RentalViolationVO> getRentalViolationList(RentalViolationDTO rentalViolationDTO);

    /**
     * 通过违章id查询违章详情
     * @param id
     * @return
     */
    RentalViolationVO getRentalViolationById(int id);

    /**
     * 删除违章记录
     * @param id
     * @return
     */
    int updateRentalViolationStatus(@Param("id") int id);

    /**
     * 查询机务单的违章
     *
     * @param id
     * @return
     */
    RentalViolation selectViolationByMaintenanceOrderId(Integer id);

    /**
     * 更新违章处理状态
     * @param rentalViolationUpdateDealStatusDTO
     * @return
     */
    int updateDealStatus(RentalViolationUpdateDealStatusDTO rentalViolationUpdateDealStatusDTO);
}