package com.ydc.cgj.rental.web.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.RentalViolationDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalViolationUpdateDealStatusDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalViolationVO;
import java.util.Map;

public interface RentalViolationService {
    /**
     * 通过条件获取违章列表
     * @param rentalViolationDTO
     * @return
     */
    Result getRentalViolationList(RentalViolationDTO rentalViolationDTO);

    /**
     * 通过违章id查询违章详情
     * @param id
     * @return
     */
    Result getRentalViolationById(int id);

    /**
     * 新增违章单
     * @param rentalViolationVO
     * @return
     */
    String insertRentalViolation(RentalViolationVO rentalViolationVO);

    /**
     * 编辑违章单
     * @param rentalViolationVO
     * @return
     */
    String updateRentalViolation(RentalViolationVO rentalViolationVO);

    /**
     * 删除违章记录
     * @param id
     * @return
     */
    String updateRentalViolationStatus(int id);

    /**
     * 获取违章类型
     * @return
     */
    String getViolationType();

    /**
     * 提交违章结算单
     * @param req
     */
    Result updateViolationSettlement(Map<String,Object> req);

    /**
     * 更新违章处理状态
     * @param rentalViolationUpdateDealStatusDTO
     * @return
     */
    String updateRentalViolationDealStatus(RentalViolationUpdateDealStatusDTO rentalViolationUpdateDealStatusDTO);
}
