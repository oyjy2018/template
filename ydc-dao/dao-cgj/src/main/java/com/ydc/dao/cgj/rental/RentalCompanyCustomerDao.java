package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.CompanyCustomerDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.model.cgj.rental.RentalCompanyCustomer;

import java.util.List;

public interface RentalCompanyCustomerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RentalCompanyCustomer record);

    int insertSelective(RentalCompanyCustomer record);

    RentalCompanyCustomer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentalCompanyCustomer record);

    int updateByPrimaryKey(RentalCompanyCustomer record);

    /**
     * 获取企业客户列表
     *
     * @param companyCustomerDTO
     * @return
     */
    List<RentalCompanyCustomerVO> getCompanyCustomerList(CompanyCustomerDTO companyCustomerDTO);

    /**
     * 通过企业客户id查询企业客户详情
     *
     * @param id
     * @return
     */
    RentalCompanyCustomerVO getCompanyCustomerById(int id);

    /**
     * 通过条件获取企业待审核列表
     *
     * @param tempCompanyCustomerDTO
     * @return
     */
    List<RentalCompanyCustomerVO> getCompanyAuditedList(CompanyCustomerDTO tempCompanyCustomerDTO);

    /**
     * 通过手机号码查询企业客户信息
     * @param phone
     * @return
     */
    RentalCompanyCustomerVO getCompanyCustomerByMobilePhone(String phone);

    /**
     * 通过企业公司名查询对应数量
     * @param registeredCompanyName
     * @return
     */
    int getCompanyCustomerByCompanyName(String registeredCompanyName);

    /**
     * 个人中心
     * @param id
     * @return
     */
    RentalCompanyCustomerVO personalCenter(int id);
}