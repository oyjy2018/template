package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.RentalCarCheckQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarCheckQueryDetailVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarCheckQueryVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarConditionMiddleVO;
import com.ydc.model.cgj.rental.RentalCarCheck;

import java.util.List;
import java.util.Map;

public interface RentalCarCheckDao {

    /**
     * 查询外部车列表
     * @param rentalCarCheckQueryDTO
     * @return
     */
    List<RentalCarCheckQueryVO> getList(RentalCarCheckQueryDTO rentalCarCheckQueryDTO);

    /**
     * 查询车辆详情
     * @param id
     * @return
     */
    RentalCarCheckQueryDetailVO getDetail(Integer id);

    /**
     * 查询外部车查询条件
     * @param
     * @return
     */
    List<RentalCarConditionMiddleVO> getCondition();

    /**
     * 通过企业名称查询门店列表
     * @param companyName
     * @return
     */
    List<String> getStoreNameByCompanyName(String companyName);

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
     * 全部拒绝
     * @param checkId
     */
    void denyAll(Integer checkId);

    /**
     * 单个拒绝更新中间表
     * @param params
     */
    void updateCheckDetail(Map<String, Object> params);

    /**
     * 新增审核中间表
     * @param params
     */
    void insertMiddleTable(Map<String, Object> params);

    int deleteByPrimaryKey(Integer id);

    int insert(RentalCarCheck record);

    int insertSelective(RentalCarCheck record);

    RentalCarCheck selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentalCarCheck record);

    int updateByPrimaryKey(RentalCarCheck record);

}