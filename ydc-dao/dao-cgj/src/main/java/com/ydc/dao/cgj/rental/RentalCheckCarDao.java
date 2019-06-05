package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.RentalCheckCarDTO;
import com.ydc.model.cgj.rental.RentalCheckCar;

import java.util.List;

public interface RentalCheckCarDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RentalCheckCar record);

    int insertSelective(RentalCheckCar record);

    RentalCheckCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentalCheckCar record);

    int updateByPrimaryKey(RentalCheckCar record);

    /**
     * 企业租车订单查询出车信息
     * @param dto
     * @return
     */
    List<RentalCheckCar> getRentalCheckCar(RentalCheckCarDTO dto);

    /**
     * 批量新增
     * @param list
     */
    void batchInsert(List<RentalCheckCarDTO> list);

    /**
     * 批量更新
     * @param list
     */
    void batchUpdate(List<RentalCheckCarDTO> list);
}