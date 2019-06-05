package com.ydc.service.order.service.impl;

import com.ydc.commom.view.dto.cgj.order.MemberWaterDTO;
import com.ydc.dao.cgj.user.MemberWaterDao;
import com.ydc.service.order.service.MemberWaterService;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MemberWaterServiceImpl implements MemberWaterService {

    @Resource
    private MemberWaterDao memberWaterDao;

    @Override
    public int updateWaterStatus(String payWater, int newWaterStatus, Integer oldWaterStatus, String transactionId) {
        return memberWaterDao.updateWaterStatus(payWater, newWaterStatus, oldWaterStatus, transactionId);
    }
	@Override
    public List<Map<String, Object>> getMemberSaters(MemberWaterDTO memberWaterDTO) {
        return memberWaterDao.getMemberSaters(memberWaterDTO);
    }
}
