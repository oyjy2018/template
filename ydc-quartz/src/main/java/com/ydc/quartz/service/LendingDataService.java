package com.ydc.quartz.service;

import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.MemberConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.rcs.LendingDataDTO;
import com.ydc.dao.cgj.loan.LendingCustomerDao;
import com.ydc.dao.cgj.user.MemberApplicationDao;
import com.ydc.dao.cgj.user.MemberDao;
import com.ydc.dao.rcs.LendingDataDao;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.MemberApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 放款数据报表
 *
 * @author
 * @create 2018-10-26 14:28
 **/
@Service("lendingDataService")
public class LendingDataService {

    private final Logger logger = LogManager.getLogger(LendingDataService.class);

    @Autowired
    LendingDataDao lendingDataDao;
    @Autowired
    LendingCustomerDao lendingCustomerDao;
    @Autowired
    MemberDao memberDao;
    @Autowired
    MemberApplicationDao memberApplicationDao;

    /**
     * 首次查询放款数据
     */
    public void firstLendingData(){
        List<LendingDataDTO> list = lendingDataDao.getLendingDataByFullScaleTime(null,null);
        logger.info("subject:{},size:{}","首次查询放款数据",(list == null ? null : list.size()));
        if(list == null || list.isEmpty())return;
        this.autoLendingData(list);
        this.insertMemberBatch(list);
    }

    /**
     *
     */
    public void lendingData(){
        Date date = new Date();
        String startTime = DateUtil.formatDate(DateUtil.getAddDate(date,3,-1))+" 00:00:00";
        String endTime = DateUtil.formatDate(date)+" 23:59:59";
        List<LendingDataDTO> list = lendingDataDao.getLendingDataByFullScaleTime(startTime,endTime);
        logger.info("subject:{},size:{}","查询放款数据",(list == null ? null : list.size()));
        if(list == null || list.isEmpty())return;
        this.autoLendingData(list);
        this.insertMemberBatch(list);
    }

    private void autoLendingData(List<LendingDataDTO> list){
        logger.info("subject:{}","开始批量新增放款数据");
        List<LendingDataDTO> newList = new ArrayList<>();
        for(LendingDataDTO ldd : list){
            newList.add(ldd);
            if(newList.size() % 200 == 0){
                lendingCustomerDao.insertLendingCustomerBatch(newList);
                newList.clear();
            }
        }
        if(newList.size() > 0){
            lendingCustomerDao.insertLendingCustomerBatch(newList);
        }
        logger.info("subject:{}","结束批量新增放款数据");
    }

    private void insertMemberBatch(List<LendingDataDTO> list){
        logger.info("subject:{}","开始批量新增会员");
        List<Member> newList = new ArrayList<>();
        Member member;
        Map<Integer,Integer> map = new HashMap<>();
        for(LendingDataDTO ldd : list){
            member = new Member();
            member.setCustomerId(ldd.getCustomerId());
            member.setMemberName(ldd.getCustomerName());
            member.setIdCard(ldd.getIdCard());
            member.setGender(ldd.getGender());
            member.setAge(ldd.getAge());
            member.setMobilePhone(ldd.getMobilePhone());
            member.setSource(ldd.getSource());
            member.setWhetherRealName(ldd.getWhetherRealName());
            member.setEmail(ldd.getEmail());
            member.setWhetherLoan(ldd.getWhetherLoan());
            member.setFirstLoanDate(DateUtil.parseDate(ldd.getFullScaleTime(),DateUtil.DATATIMEF_STR));
            member.setCreateTime(DateUtil.parseDate(ldd.getCreateTime(),DateUtil.DATATIMEF_STR));
            member.setStatus(CodeConstant.NORMAL_STATUS);
            newList.add(member);
            if(newList.size() % 200 == 0){
                memberDao.insertMemberBatch(newList);
                map = newList.stream().filter(p -> p.getId() != null).collect(Collectors.toMap((key->key.getCustomerId()),(value -> value.getId())));
                newList.clear();
            }
        }
        if(newList.size() > 0){
            memberDao.insertMemberBatch(newList);
            map = newList.stream().filter(p -> p.getId() != null).collect(Collectors.toMap((key->key.getCustomerId()),(value -> value.getId())));
        }
        logger.info("subject:{}","结束批量新增会员");
        this.insertMemberApplicationBatch(list,map);
    }

    private void insertMemberApplicationBatch(List<LendingDataDTO> list,Map<Integer,Integer> map){
        logger.info("subject:{},map:{}","开始批量新增Application会员",map.size());
        if(map.isEmpty())return;
        List<MemberApplication> newList = Lists.newArrayList();
        MemberApplication memberApplication;
        Date date = new Date();
//        list.stream().filter(item -> StringUtil.isNotEmpty(map.get(item.getCustomerId()))).collect(Collectors.toList());
        for(LendingDataDTO ldd : list){
            if(StringUtil.isEmpty(map.get(ldd.getCustomerId()))){
                continue;
            }
            memberApplication = new MemberApplication();
            memberApplication.setMemberId(map.get(ldd.getCustomerId()));
            memberApplication.setMemberName(ldd.getCustomerName());
            memberApplication.setMemberPhone(ldd.getMobilePhone());
            memberApplication.setMemberStatus(MemberConstant.MEMBER_NORMAL_STATUS);
            memberApplication.setApplication(MemberConstant.APPLICATION_CGJ);
            memberApplication.setStatus(CodeConstant.NORMAL_STATUS);
            memberApplication.setCreateTime(date);
            memberApplication.setCreateBy(CodeConstant.CREATE_BY);
            newList.add(memberApplication);
            if(newList.size() % 200 == 0){
                memberApplicationDao.insertMemberApplicationBatch(newList);
                newList.clear();
            }
        }
        if(newList.size() > 0){
            memberApplicationDao.insertMemberApplicationBatch(newList);
        }
        logger.info("subject:{}","结束批量新增Application会员");
    }
}
