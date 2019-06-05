package com.ydc.cgj.rental.company.app.controller;

import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import org.apache.shiro.SecurityUtils;

public class BaseController {

    /**
     * 获取当前登陆用户
     *
     * @return
     */
    public RentalCompanyCustomerVO getUser() {
        return (RentalCompanyCustomerVO) SecurityUtils.getSubject().getPrincipal();
    }

}
