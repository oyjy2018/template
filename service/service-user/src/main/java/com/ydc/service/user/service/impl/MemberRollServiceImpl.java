package com.ydc.service.user.service.impl;

import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.RollConstant;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.MemberRollDto;
import com.ydc.dao.cgj.loan.LendingCustomerDao;
import com.ydc.dao.cgj.service.BRollDetailDao;
import com.ydc.dao.cgj.user.MemberDao;
import com.ydc.dao.cgj.user.MemberRollDao;
import com.ydc.model.cgj.BRollDetail;
import com.ydc.model.cgj.LendingCustomer;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.MemberRoll;
import com.ydc.service.user.service.MemberRollService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberRollServiceImpl implements MemberRollService {
    Logger logger = LogManager.getLogger(MemberRollServiceImpl.class);

    @Resource
    private MemberRollDao memberRollDao;

    @Resource
    private BRollDetailDao bRollDetailDao;

    @Resource
    private MemberDao memberDao;

    @Resource
    private LendingCustomerDao lendingCustomerDao;

    @Override
    public List<Map<String, Object>> getMemberRollByType(MemberRollDto memberRollDto) {
        return PaginationUtil.paginationQuery(memberRollDto, (tempMemberRollDto) ->
                memberRollDao.getMemberRollByType(tempMemberRollDto));
    }

    @Override
    public List<Map<String, Object>> getBlankRollCount() {
        return bRollDetailDao.getBlankRollCount();
    }

    @Override
    public List<BRollDetail> getRollDetailTypeNum(Integer rollType, int num) {
        return bRollDetailDao.getRollDetailTypeNum(rollType, num);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 6, value = "cgjTransactionManager")
    public List<MemberRoll> sendRoll(List<Map<String, Integer>> memberInfos, List<Map<String, Object>> rollSizeList, Map<String, Object> params) throws Exception {
        List<MemberRoll> result = new ArrayList<>();
        rollSizeList.forEach(map -> {
            Integer rollNum = (Integer) map.get(RollConstant.ROLL_NUM);
            Integer rollType = (Integer) map.get(RollConstant.ROLL_TYPE);
            String orderNo = (String) map.get(RollConstant.ORDER_NO);
            List<BRollDetail> blankRollList;
            synchronized (this) {
                //获取足够多的空券
                blankRollList = bRollDetailDao.getRollDetailTypeNum(rollType, memberInfos.size() * rollNum);
                //将获取到的空券更新状态，避免重复获取
                bRollDetailDao.updateRollDetailHasSend(blankRollList.stream().map(BRollDetail::getCode).collect(Collectors.toList()));
            }

            //可能被其他人获取了空券
            if (blankRollList.size() < memberInfos.size() * rollNum){
                throw new ServiceRuntimeException("派券失败，空券数量不足");
            }
            //生成用户券
            List<MemberRoll> memberRollList = new ArrayList<>(blankRollList.size());
            for (int i = 0; i < blankRollList.size(); i++) {
                Map<String, Integer> memberInfo = memberInfos.get(i % memberInfos.size());
                Member member = memberDao.selectByPrimaryKey(memberInfo.get(RollConstant.MEMBER_ID));
                memberRollList.add(
                        getMemberRolls(member, memberInfo.get(RollConstant.LOAN_ID), orderNo, blankRollList.get(i), params));
            }

            //添加用户券
            memberRollDao.batchInsert(memberRollList);
            result.addAll(memberRollList);
        });

        //更新借款单派券信息
//        List<Integer> loanIds = memberInfos.stream()
//                .map(memberInfo -> memberInfo.get(RollConstant.LOAN_ID))
//                .collect(Collectors.toList());
//        this.updateSendRollInfo(loanIds);
        return result;
    }

    @Override
    public void updateSendRollInfo(List<Integer> loanIds) {
        if (loanIds != null && loanIds.size() > 0) {
            loanIds.forEach(loanId -> {
                LendingCustomer lendingCustomer = lendingCustomerDao.getLendingCustomerByLoanId(loanId);
                Map<String, Object> rollInfo = memberRollDao.getTimeInfoByLoanId(loanId);
                lendingCustomer.setRollNumber(((Long) rollInfo.get("num")).intValue());
                lendingCustomer.setRecentlySendRollTime((Date) rollInfo.get("createTime"));
                if (lendingCustomer.getFirstSendRollTime() == null) {
                    lendingCustomer.setFirstSendRollTime((Date) rollInfo.get("createTime"));
                }
                lendingCustomerDao.updateByPrimaryKey(lendingCustomer);
            });
        }
    }

    /**
     * 生成用户券
     *
     * @param bRollDetail
     * @param params
     * @return
     */
    private MemberRoll getMemberRolls(Member member, Integer loanId, String orderNo, BRollDetail bRollDetail, Map<String, Object> params) {
        MemberRoll memberRoll = new MemberRoll();
        if (member == null) {
            return null;
        }
        Date date = new Date();
        memberRoll.setMemberId(member.getId());
        memberRoll.setMemberName(member.getMemberName());
        memberRoll.setMemberPhone(member.getMobilePhone());
        memberRoll.setRollId(bRollDetail.getId());
        memberRoll.setRollName(bRollDetail.getName());
        memberRoll.setRollCode(bRollDetail.getCode());
        memberRoll.setRollAmount(bRollDetail.getAmount());
        memberRoll.setRollType(bRollDetail.getType());
        memberRoll.setRollContent(bRollDetail.getContent());
        memberRoll.setRollAttribute(bRollDetail.getAttribute());
        memberRoll.setRollStatus(RollConstant.RollStatusEnum.ROLL_STATUS_0.getKey());
        memberRoll.setLoanId(loanId);
        memberRoll.setOrderNo(orderNo);
        memberRoll.setSendTime(date);
        memberRoll.setInvalidTime(getMemberRollInvalid(bRollDetail.getInvalidTime(), params.get("validDays"), params.get("validTime")));
        memberRoll.setSendDescription((String) params.get("sendDescription"));
        memberRoll.setStatus(CodeConstant.DISABLE_STATUS);
        memberRoll.setCreateTime(date);
        memberRoll.setCreateBy((Integer) params.get("userId"));
        memberRoll.setUpdateTime(date);
        memberRoll.setUpdateBy((Integer) params.get("userId"));
        return memberRoll;
    }

    @Override
    public List<Map<String, Object>> getMemberRollList(MemberRollDto memberRollDto) {
        return PaginationUtil.paginationQuery(memberRollDto, (tempMemberRollDto) ->
                memberRollDao.getMemberRollList(tempMemberRollDto));
    }

    @Override
    public Integer updateRollStatus(String rollCode, Integer rollStatus) {
        return memberRollDao.updateRollStatusByStatus(rollCode,null, rollStatus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 6, value = "cgjTransactionManager")
    public List<MemberRoll> sendAssignMemberRoll(List<String> rollCodes, Member member, Map<String, Object> paramMap) throws Exception {
        List<MemberRoll> result = new ArrayList<>(rollCodes.size());
        //更新券发放状态
        if (bRollDetailDao.updateRollDetailHasSend(rollCodes) < rollCodes.size()){
            throw new ServiceRuntimeException("派券失败，更新空券状态异常");
        }

        List<BRollDetail> bRollDetailList = bRollDetailDao.getBlankRollsByCode(rollCodes);
        //生成用户券
        bRollDetailList.parallelStream().forEach(bRollDetail -> result.add(getMemberRolls(member, null, null, bRollDetail, paramMap)));
        //添加用户券
        memberRollDao.batchInsert(result);
        return result;
    }

    @Override
    public Integer getCountMemberRollByType(MemberRollDto memberRollDto) {
        return memberRollDao.getCountMemberRollByType(memberRollDto);
    }

    private Date getMemberRollInvalid(Date blankRollInValid, Object validDays, Object validTime){
        Date memberRollInvalid = null;
        if (validDays != null && !("").equals(validDays)){
            memberRollInvalid = DateUtil.dateAdd(new Date(), Calendar.DAY_OF_MONTH, Integer.parseInt((String)validDays));
        }
        if (validTime != null && !("").equals(validTime)){
            try {
                memberRollInvalid = DateUtil.parseDateAndTime((String) validTime);
            }catch (Exception e){
                logger.error("subject: {}, validTime: {}", "派发券有效期日期转换异常", validTime, e);
            }
        }
        if (blankRollInValid == null){
            blankRollInValid = new Date();
        }
        if (memberRollInvalid == null){
            memberRollInvalid = blankRollInValid;
        }
        return DateUtil.diffTimeInMillis(blankRollInValid, memberRollInvalid) > 0 ? blankRollInValid : memberRollInvalid;
    }

    @Override
    public Integer batchUpdateMemberRollStatus(List<String> rollCodes, Integer status) {
        return memberRollDao.batchUpdateMemberRollStatus(rollCodes, status);
    }
}
