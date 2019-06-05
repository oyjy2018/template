package com.ydc.cgj.rental.company.app.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.model.cgj.rental.RentalCompanyCustomer;

public interface CompanyCustomerService {
    /**
     * 通过手机号码查询企业客户信息
     * @param mobilePhone
     * @return
     */
    RentalCompanyCustomerVO getCompanyCustomerByMobilePhone(String mobilePhone);

    /**
     * 发送验证码
     *
     * @param mobilePhone
     * @param validateType
     * @return
     */
    Result sendValidateCode(String mobilePhone, Integer validateType, Integer application);

    /**
     * 校验验证码
     *
     * @param mobilePhone
     * @param validateType
     * @param validateCode
     * @return
     */
    Result checkValidateCode(String mobilePhone, Integer validateType, String validateCode);

    /**
     * 注册新增
     * @param rentalCompanyCustomer
     * @return
     */
    String addCompanyCustomer(RentalCompanyCustomer rentalCompanyCustomer);

    /**
     * 通过企业客户id查询企业客户详情
     * @param id
     * @return
     */
    String getCompanyCustomerById(Integer id);

    /**
     * 编辑企业客户
     * @param rentalCompanyCustomer
     * @return
     */
    String updateCompanyCustomer(RentalCompanyCustomer rentalCompanyCustomer);
	
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
    String personalCenter(Integer id);
}
