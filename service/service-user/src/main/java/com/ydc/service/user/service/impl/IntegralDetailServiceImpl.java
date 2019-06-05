package com.ydc.service.user.service.impl;

import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.integral.IntegralDTO;
import com.ydc.commom.view.dto.cgj.integral.IntegralManageDTO;
import com.ydc.commom.view.vo.cgj.order.IntegralDetailVO;
import com.ydc.dao.cgj.user.IntegralDetailDao;
import com.ydc.model.cgj.IntegralDetail;
import com.ydc.service.user.service.IntegralDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 积分明细
 *
 * @author gongjin
 * @create 2018-09-05 11:35
 **/
@Service
public class IntegralDetailServiceImpl implements IntegralDetailService {

    @Autowired
    IntegralDetailDao integralDetailDao;

    @Override
    public Integer insert(IntegralDetail record) {
        return integralDetailDao.insert(record);
    }

    @Override
    public List<IntegralDetailVO> getIntegralDetailList(IntegralManageDTO integralManageDTO) {
        return PaginationUtil.paginationQuery(integralManageDTO ,(tempIntegralManageDTO) -> integralDetailDao.getIntegralDetailList(integralManageDTO));
    }

    @Override
    public List<IntegralDetail> getIntegralDetailByMemberId(Integer memberId) {
        return integralDetailDao.getIntegralDetailByMemberId(memberId);
    }

    @Override
    public List<IntegralDetailVO> h5GetIntegralDetailList(IntegralDTO integralDTO) {
        return PaginationUtil.paginationQuery(integralDTO ,(tempIntegralDTO) -> integralDetailDao.h5GetIntegralDetailList(integralDTO));
    }
}
