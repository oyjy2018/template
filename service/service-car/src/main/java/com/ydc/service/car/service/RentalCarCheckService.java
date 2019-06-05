package com.ydc.service.car.service;

import com.ydc.commom.view.dto.cgj.rental.RentalCarCheckQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarCheckQueryDetailVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarCheckQueryVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarConditionVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarQueryDetailVO;

import java.util.List;

public interface RentalCarCheckService {

    /**
     * 查询外部车辆列表
     * @param rentalCarCheckQueryDTO
     * @return
     */
    List<RentalCarCheckQueryVO> getList(RentalCarCheckQueryDTO rentalCarCheckQueryDTO);

    /**
     * 查询外部车辆查询条件
     * @param
     * @return
     */
    RentalCarConditionVO getCondition();

    /**
     * 查询外部车辆详情
     * @param id
     * @return
     */
    RentalCarCheckQueryDetailVO getCheckDetail(Integer id) throws Exception;

    /**
     * 根据品牌查询车系
     * @param brand
     * @return
     */
    List<String> getSeriesByBrand(String brand);

    /**
     * 根据车系查询车型
     * @param series
     * @return
     */
    List<String> getModelBySeries(String series);

    /**
     * 审核
     * @param checkResult
     * @return
     */
    String check(String checkResult, Integer userId);

    /**
     * 通过企业名称查询门店列表
     * @param companyName
     * @return
     */
    List<String> getStoreNameByCompanyName(String companyName);
}
