package com.ydc.service.user.service.impl;

import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.integral.IntegralManageDTO;
import com.ydc.dao.cgj.user.IntegralDao;
import com.ydc.dao.cgj.user.IntegralDetailDao;
import com.ydc.dao.cgj.user.MemberDao;
import com.ydc.model.cgj.Integral;
import com.ydc.model.cgj.IntegralDetail;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.entity.IntegralEntity;
import com.ydc.service.user.service.IntegralService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 积分
 *
 * @author gongjin
 * @create 2018-09-04 19:11
 **/
@Service
public class IntegralServiceImpl implements IntegralService {

    private static final Logger logger = LogManager.getLogger(IntegralService.class);

    @Autowired
    IntegralDao integralDao;
    @Autowired
    MemberDao memberDao;
    @Autowired
    IntegralDetailDao integralDetailDao;

    @Override
    public List<Map<String, Object>> getIntegralList(IntegralManageDTO integralManageDTO) {
        return PaginationUtil.paginationQuery(integralManageDTO ,(tempIntegralManageDTO) -> integralDao.getIntegralList(integralManageDTO));
    }

    @Override
    public Integer saveOrUpdateIntegral(Map<String, Object> seMap) {
        return integralDao.saveOrUpdateIntegral(seMap);
    }

    @Override
    public Integer updateIntegral(Map<String, Object> seMap) {
        return integralDao.updateIntegral(seMap);
    }

    @Override
    public Integer insert(Integral record) {
        return integralDao.insert(record);
    }

    @Override
    public Integral selectByPrimaryKey(Integer id) {
        return integralDao.selectByPrimaryKey(id);
    }

    @Transactional(value = "cgjTransactionManager",rollbackFor = Exception.class)
    @Override
    public void logicIntergralRecharge(IntegralManageDTO integralManageDTO) throws Exception {
            Date date = new Date();
            Integer memberId = integralManageDTO.getMemberId();
            Member member = memberDao.selectByPrimaryKey(memberId);
            Integral integral = getIntegralByMumberId(memberId);
            DecimalFormat df = new DecimalFormat("0.00");
            BigDecimal usableIntegral = integralManageDTO.getUsableIntegral();
            BigDecimal changeIntegralBalance = usableIntegral;
            if(usableIntegral.compareTo(BigDecimal.valueOf(0)) != 1){
                throw new ServiceRuntimeException("充值积分不能小于等于0");
            }
            Integer operatorId = integralManageDTO.getOperatorId();
            if (integral == null) {
                //新增积分表
                integral = new Integral();
                integral.setMemberId(member.getId());
                integral.setUsableIntegral(usableIntegral);
                integral.setVersion(1);
                integral.setCreateTime(date);
                integral.setCreateBy(operatorId);
                insert(integral);
            } else {
                changeIntegralBalance = changeIntegralBalance.add(integral.getUsableIntegral());
                Map<String, Object> param = new HashMap<>();
                param.put("usableIntegral", usableIntegral);
                param.put("updateBy", operatorId);
                param.put("memberId", memberId);
                saveOrUpdateIntegral(param);
            }
            //新增积分明细表
            IntegralDetail integralDetail = new IntegralDetail();
            integralDetail.setMemberId(member.getId());
            integralDetail.setMemberName(member.getMemberName());
            integralDetail.setMobilePhone(member.getMobilePhone());
            integralDetail.setPayTime(date);
            integralDetail.setIntegralPay(new StringBuffer().append("+").append(df.format(usableIntegral)).toString());
            integralDetail.setPayType(0);
            integralDetail.setChangeIntegralBalance(changeIntegralBalance);
            integralDetail.setIntegralTypeAcquire(Integer.valueOf(integralManageDTO.getIntegralTypeAcquire()));
            integralDetail.setCreateTime(date);
            integralDetail.setCreateBy(operatorId);
            integralDetailDao.insert(integralDetail);
    }

    @Override
    public Integral getIntegralByMumberId(Integer memberId) {
        return integralDao.getIntegralByMumberId(memberId);
    }

    @Override
    public Integer updateByPrimaryKey(Integral record) {
        return integralDao.updateByPrimaryKey(record);
    }

    @Transactional(value = "cgjTransactionManager",rollbackFor = Exception.class)
    @Override
    public void batchRecharge(List<IntegralEntity> list) {
        Date date = new Date();
        List<Integral> insertList = new ArrayList<>();
        List<Integral> updateList = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.00");
        List<IntegralDetail> integralDetailList = new ArrayList<>();
        Member member = null;
        for(IntegralEntity in : list){
            BigDecimal changeIntegralBalance = new BigDecimal(0);
            member = memberDao.getMemberByIdAndmobilePhone(in.getMemberId(),in.getMobilePhone());
            if(member == null)
                throw new ServiceRuntimeException("使用会员id:"+in.getMemberId()+"，会员手机号码:"+in.getMobilePhone()+"查询信息不存在");
            Integral integral = getIntegralByMumberId(in.getMemberId());
            if (integral == null){
                changeIntegralBalance = changeIntegralBalance.add(in.getUsableIntegral());
                integral = new Integral();
                integral.setMemberId(in.getMemberId());
                integral.setUsableIntegral(in.getUsableIntegral());
                integral.setVersion(1);
                integral.setCreateTime(date);
                integral.setCreateBy(in.getOperatorId());
                insertList.add(integral);
            }else{
                changeIntegralBalance = changeIntegralBalance.add(in.getUsableIntegral().add(integral.getUsableIntegral()));
                integral.setUsableIntegral(in.getUsableIntegral());
                integral.setUpdateBy(in.getOperatorId());
                updateList.add(integral);
            }
            IntegralDetail integralDetail = new IntegralDetail();
            integralDetail.setMemberId(in.getMemberId());
            integralDetail.setMemberName(member.getMobilePhone());
            integralDetail.setMobilePhone(in.getMobilePhone());
            integralDetail.setPayTime(date);
            integralDetail.setIntegralPay(new StringBuffer().append("+").append(df.format(in.getUsableIntegral())).toString());
            integralDetail.setPayType(0);//收支类型(0：获取；1：消耗)
            integralDetail.setChangeIntegralBalance(changeIntegralBalance);
            integralDetail.setIntegralTypeAcquire(in.getIntegralTypeAcquire());
            integralDetail.setCreateTime(date);
            integralDetail.setCreateBy(in.getOperatorId());
            integralDetailList.add(integralDetail);
        }
        if(!insertList.isEmpty()){
            logger.info("批量新增积分"+insertList);
            insertBatch(insertList);
        }
        if(!updateList.isEmpty()){
            logger.info("批量更新积分"+updateList);
            updateBatch(updateList);
        }
        if(!integralDetailList.isEmpty()){
            logger.info("批量新增积分明细"+integralDetailList);
            integralDetailDao.insertBatch(integralDetailList);
        }
    }

    @Override
    public void insertBatch(List<Integral> list) {
        integralDao.insertBatch(list);
    }

    @Override
    public void updateBatch(List<Integral> list) {
        integralDao.updateBatch(list);
    }
}
