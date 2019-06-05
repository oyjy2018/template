package com.ydc.service.loan.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctc.wstx.util.DataUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.LoanApplyConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.vo.cgj.LoanApplyVO;
import com.ydc.dao.cgj.loan.LoanApplyDao;
import com.ydc.dao.cgj.service.AcceptApplyDao;
import com.ydc.dao.cgj.user.MemberDao;
import com.ydc.dao.cgj.user.VehicleDao;
import com.ydc.model.cgj.Accept;
import com.ydc.model.cgj.LoanApply;
import com.ydc.model.cgj.Member;

import com.ydc.model.cgj.Vehicle;
import com.ydc.service.loan.service.LoanApplyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoanApplyServiceImpl implements LoanApplyService {
    private static Logger logger = LogManager.getLogger(LoanApplyServiceImpl.class);
    @Autowired
    LoanApplyDao loanApplyDao;
    @Autowired
    AcceptApplyDao acceptApplyDao;
    @Autowired
    MemberDao memberDao;
    @Autowired
    VehicleDao vehicleDao;


    @Override
    public String getApplyNum(String data) {
        JSONObject json = JSONObject.parseObject(data);
        Integer appLyProuctType = 0;
        Integer applyNum;
        Integer num = 0;
        if(json.containsKey("appLyProuctType") && StringUtil.isNotEmpty(json.get("appLyProuctType"))){
            num = (Integer) RedisUtil.redisGet(RedisConstant.CGJ_APPLY_SHOW_NUM + appLyProuctType);
            if(num == null){
                appLyProuctType = Integer.valueOf(json.get("appLyProuctType").toString());
                // 类型100以上为redis缓存数据，不从数据库读取
                if(appLyProuctType >= 100){
                    // 车辆估价使用数量
                    applyNum = (Integer) RedisUtil.redisGet(RedisConstant.CGJ_APPLY_NUM + appLyProuctType);
                    if(applyNum == null){
                        applyNum = 0;
                    }
                }else{
                    applyNum = loanApplyDao.getApplyNumByProductType(appLyProuctType);
                }
                // 放到缓存
                RedisUtil.redisSet(RedisConstant.CGJ_APPLY_NUM + appLyProuctType,applyNum,null);
                num = applyNum+1000;
                // 虚拟值
                RedisUtil.redisSet(RedisConstant.CGJ_APPLY_SHOW_NUM + appLyProuctType,num,null);
            }
        }
        return num+"";
    }

    @Override
    public Integer submitApplication(String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("mobilePhone");
        String applyName = jsonObject.getString("applyName");
        Integer applyType = jsonObject.getInteger("applyType");
        String city = jsonObject.getString("city");
        LoanApply loanApply = new LoanApply();
        loanApply.setApplyName(applyName);
        loanApply.setApplyPhone(mobilePhone);
        loanApply.setApplyProductType(applyType);
        loanApply.setCity(city);
        loanApply.setMemberId(Integer.valueOf(jsonObject.get("memberId").toString()));
        if(StringUtil.isNotEmpty(jsonObject.get("username"))){
            loanApply.setMemberUsername(jsonObject.get("username").toString());
        }
        loanApply.setApplyTime(DateUtil.getNewTimestamp());
        loanApply.setCreateTime(DateUtil.getNewTimestamp());
        loanApply.setCreateBy(Integer.valueOf(jsonObject.get("memberId").toString()));
        loanApply.setDeleteStatus(1);
        loanApply.setAcceptStatus(1);
        int id = loanApplyDao.insert(loanApply);
        // 把数量存到缓存
        Integer applyNum = loanApplyDao.getApplyNumByProductType(applyType);
        RedisUtil.redisSet(RedisConstant.CGJ_APPLY_NUM + applyType,applyNum,null);
        Integer num = (Integer) RedisUtil.redisGet(RedisConstant.CGJ_APPLY_SHOW_NUM + applyType);
        if(num == null){
            num = 1000;
        }
        num = num + 1;
        // 虚拟值
        RedisUtil.redisSet(RedisConstant.CGJ_APPLY_SHOW_NUM + applyType,num,null);
        return id;
    }

    @Override
    public Integer insertApplyAppr(String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        Accept accept = new Accept();
        accept.setLoanApplyId(jsonObject.getInteger("loanApplyId"));
        accept.setAcceptRemark(jsonObject.getString("acceptRemark"));
        accept.setAcceptStatus(jsonObject.getInteger("acceptStatus"));
        accept.setAcceptUserId(Integer.valueOf(jsonObject.get("userId").toString()));
        accept.setCreateBy(Integer.valueOf(jsonObject.get("userId").toString()));
        accept.setAcceptTime(DateUtil.getNewTimestamp());
        accept.setCreateTime(DateUtil.getNewTimestamp());
        accept.setAcceptUsername(jsonObject.getString("accptUsername"));
        LoanApply loanApply = new LoanApply();
        loanApply.setId(jsonObject.getInteger("loanApplyId"));
        loanApply.setAcceptStatus(jsonObject.getInteger("acceptStatus"));
        loanApply.setUpdateBy(Integer.valueOf(jsonObject.get("userId").toString()));
        loanApply.setUpdateTime(DateUtil.getNewTimestamp());
        loanApplyDao.updateByPrimaryKeySelective(loanApply);
        return acceptApplyDao.insert(accept);
    }

    @Override
    public String getApplyApprList(String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        Integer loanApplyId = jsonObject.getInteger("loanApplyId");
        List<Accept> accepts =  acceptApplyDao.getApplyApprList(loanApplyId);
        return Result.success(accepts).toJSON();
    }

    @Override
    public String getApplyList(LoanApplyVO loanApplyVO) {
        Map<String,Object> map = new HashMap<>();
        List<LoanApplyVO> loanApplyVOS = new ArrayList<>();
        if(StringUtil.isNotEmpty(loanApplyVO.getAcceptStatus())){
            map.put("acceptStatus",loanApplyVO.getAcceptStatus());
        }
        if(StringUtil.isNotEmpty(loanApplyVO.getApplyPhone())){
            map.put("applyPhone",loanApplyVO.getApplyPhone());
        }


        if(StringUtil.isNotEmpty(loanApplyVO.getUserTel())){
           Member men = memberDao.getMemberByMobilePhone(loanApplyVO.getUserTel());
           if(men != null) {
               map.put("memberId", men.getId());

           }else{
               return Result.success(loanApplyVOS).toJSON();
           }
        }

        if(StringUtil.isNotEmpty(loanApplyVO.getStartTime())){
            map.put("startTime",loanApplyVO.getStartTime() +" 00:00:00");

        }
        if(StringUtil.isNotEmpty(loanApplyVO.getEndTime())){
            map.put("endTime",loanApplyVO.getEndTime() +" 23:59:59");
        }
        List<LoanApply> loanApplies = new ArrayList<>();
        if(StringUtil.isNotEmpty(loanApplyVO.getApplyProductType())){
            if(loanApplyVO.getApplyProductType() == 1 || loanApplyVO.getApplyProductType() == 2){
                map.put("applyProductType",1);
                List<LoanApply> loanAppli = loanApplyDao.getApplyList(map);
                map.put("applyProductType",2);
                List<LoanApply> loanAppl = loanApplyDao.getApplyList(map);
                loanApplies.addAll(loanAppl);
                loanApplies.addAll(loanAppli);
                Collections.sort(loanApplies, new Comparator<LoanApply>() {
                    @Override
                    public int compare(LoanApply o1, LoanApply o2) {
                        int diff = o1.getId() - o2.getId();
                        if (diff > 0) {
                            return -1;
                        }else if (diff < 0) {
                            return 1;
                        }
                            return 0; //相等为0
                    }
                });
            }else{
                map.put("applyProductType",loanApplyVO.getApplyProductType());
                loanApplies = loanApplyDao.getApplyList(map);
            }
        }else{
            map.put("applyProductType",1);
            loanApplies = loanApplyDao.getApplyList(map);
        }


        if(loanApplies != null && loanApplies.size() > 0){

            for( LoanApply loanApply : loanApplies){
                LoanApplyVO applyVO = new LoanApplyVO();

                applyVO.setId(loanApply.getId());
                applyVO.setMemberId(loanApply.getMemberId());
                Member member = memberDao.selectByPrimaryKey(loanApply.getMemberId());
                if (member != null) {
                    applyVO.setUserTel(member.getMobilePhone());
                }
                applyVO.setUsername(loanApply.getUsername());
                applyVO.setCity(loanApply.getCity());
                applyVO.setApplyName(loanApply.getApplyName());
                applyVO.setApplyPhone(loanApply.getApplyPhone());
                applyVO.setApplyProductType(loanApply.getApplyProductType());
                applyVO.setApplyTime(DateUtil.parseDate(loanApply.getApplyTime(),DateUtil.DATATIMEF_STR));
                applyVO.setAcceptStatus(loanApply.getAcceptStatus());
               List<Accept> accepts = acceptApplyDao.getApplyApprList(loanApply.getId());
               if(accepts != null && accepts.size() > 0){
                   Accept accept = accepts.get(0);
                    applyVO.setAccptUsername(accept.getAcceptUsername());
                    applyVO.setAccptTime(DateUtil.parseDate(accept.getAcceptTime(),DateUtil.DATATIMEF_STR));
                    applyVO.setNewAccept(accept.getAcceptRemark());
                    applyVO.setAcceptStatus(accept.getAcceptStatus());
               }
                Vehicle vehicle = vehicleDao.getVehicleByMemberId(loanApply.getMemberId());
               if(vehicle != null){
                   applyVO.setCarCard(vehicle.getCarPlate());

                   applyVO.setCarModel(vehicle.getBrand() + vehicle.getSeries() + vehicle.getCarVersion());
               }
                loanApplyVOS.add(applyVO);
            }
        }
        return Result.success(loanApplyVOS).toJSON();
    }

}
