package com.ydc.service.car.service;

import com.ydc.commom.view.dto.cgj.RentalViolationDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalViolationUpdateDealStatusDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalViolationVO;

import java.util.List;
import java.util.Map;

public interface RentalViolationService {

    /**
     * 通过条件获取违章列表
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
     * 新增违章单
     * @param rentalViolationVO
     * @return
     */
    int insertRentalViolation(RentalViolationVO rentalViolationVO);

    /**
     * 编辑违章单
     * @param rentalViolationVO
     * @return
     */
    int updateRentalViolation(RentalViolationVO rentalViolationVO);

    /**
     * 删除违章记录
     * @param id
     * @return
     */
    int updateRentalViolationStatus(int id);

    /**
     * 提交违章结算单
     * @param req
     */
    int updateViolationSettlement(Map<String, Object> req);

    /**
     * 更新违章处理状态
     * @param rentalViolationUpdateDealStatusDTO
     * @return
     */
    String updateDealStatus(RentalViolationUpdateDealStatusDTO rentalViolationUpdateDealStatusDTO);
}
