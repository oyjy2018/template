package com.ydc.cgj.rental.company.app.common;

import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.model.cgj.Member;
import org.apache.shiro.SecurityUtils;

/**
 * shiro subject工具类
 */
public class CompanyUtil {

    /**
     * 获取用户对象
     *
     * @return
     */
    public static RentalCompanyCustomerVO getCompanyCustomer() {
        return (RentalCompanyCustomerVO) SecurityUtils.getSubject().getPrincipal();
    }

}
