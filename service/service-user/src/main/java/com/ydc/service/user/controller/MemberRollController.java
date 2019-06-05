package com.ydc.service.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.MemberRollDto;
import com.ydc.service.user.mq.service.ThirdPartySendMessageService;
import com.ydc.commom.constant.MemberConstant;
import com.ydc.commom.constant.RollConstant;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.MemberRoll;
import com.ydc.service.user.service.MemberRollService;
import com.ydc.service.user.service.MemberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/memberRoll")
public class MemberRollController {
    private final Logger logger = LogManager.getLogger(MemberRollController.class);

    @Autowired
    private MemberRollService memberRollService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ThirdPartySendMessageService thirdPartySendMessageService;



    /**
     * 获取用户券列表
     *
     * @param memberRollDto
     * @return
     */
    @PostMapping(value = "/getMemberRoll")
    public Result getMemberRoll(@RequestBody MemberRollDto memberRollDto) {
        Result result = Result.success();
        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> list = memberRollService.getMemberRollByType(memberRollDto);
        if (list != null && list.size() > 0){
            list.parallelStream()
                    .forEach(map -> map.put("rollName", RollConstant.RollTypeEnum.getValueByKey((Integer) map.get("rollType"))));
        }
        data.put("rows", list);
        memberRollDto.setRollStatus(RollConstant.RollStatusEnum.ROLL_STATUS_0.getKey());
        data.put("totalCount",memberRollService.getCountMemberRollByType(memberRollDto));
        result.setData(data);
        return result;
    }

    /**
     * 获取空券数量
     *
     * @return
     */
    @GetMapping(value = "/getBlankRollCount")
    public Result getBlankRollCount() {
        Result result = Result.success();
        result.setData(memberRollService.getBlankRollCount());
        return result;
    }

    /**
     * 派券校验
     *
     * @param memberInfos
     * @param rollTypes
     * @return
     */
    @GetMapping(value = "/checkSendingRoll")
    public Result checkSendingRoll(@RequestParam(value = "memberInfos") String memberInfos,
                                   @RequestParam(value = "rollTypes") String rollTypes) {
        logger.info("批量派发券校验, memberInfos: {}; rollTypes: {}", memberInfos, rollTypes);
        List<Map<String, Integer>> memberInfoList = JsonUtil.parseToListMap(memberInfos);
        List<Map<String, Integer>> rollTypeList = JsonUtil.parseToListMap(rollTypes);

        //获取每种空券的数量
        List<Map<String, Object>> blankRolls = memberRollService.getBlankRollCount();
        if (blankRolls == null || blankRolls.size() <= 0) {
            return Result.failure("派券失败，空券数量不足");
        }

        //校验用户是否注册
        for (Map<String, Integer> map : memberInfoList) {
            Result<String> checkResult = checkMember(memberService.selectByPrimaryKey(map.get(RollConstant.MEMBER_ID)));
            if (checkResult.getCode() != ResultConstant.RESULT_CODE_SUCCESS){
                return Result.failure("派券失败，借款单：" + map.get(RollConstant.LOAN_ID) + checkResult.getData());
            }
        }

        Map<String, Object> blankRollMap = new HashMap<>();
        blankRolls.forEach(map ->
                blankRollMap.put(String.valueOf(map.get(RollConstant.ROLL_TYPE)), map.get(RollConstant.ROLL_NUM)));
        //校验每一种空券数量是否小于派券数据
        for (Map<String, Integer> map : rollTypeList) {
            String rollType = String.valueOf(map.get(RollConstant.ROLL_TYPE));
            Integer rollNum = map.get(RollConstant.ROLL_NUM);
            Long blankRollNum = (Long) blankRollMap.get(rollType);
            if (blankRollNum == null || blankRollNum <= 0) {
                return Result.failure("派券失败，空券数量不足");
            }
            if (blankRollNum < rollNum * memberInfoList.size()) {
                return Result.failure("派券失败，空券数量不足");
            }
        }
        return Result.success();
    }

    /**
     * 用户派券
     *
     * @param memberInfos
     * @param rollTypes
     * @param params
     * @return
     */
    @PostMapping(value = "/sendRoll")
    public Result sendRoll(@RequestParam(value = "memberInfos") String memberInfos,
                           @RequestParam(value = "rollTypes") String rollTypes,
                           @RequestParam(value = "params") String params) {
        logger.info("批量用户派券, memberInfos: {}; rollTypes: {}; params: {}", memberInfos, rollTypes, params);
        List<Map<String, Integer>> memberInfoList = JSON.parseObject(memberInfos, new TypeReference<List<Map<String, Integer>>>(){});
        List<Map<String, Object>> rollTypeList = JSON.parseObject(rollTypes, new TypeReference<List<Map<String, Object>>>(){});
        Map<String, Object> paramMap = JSON.parseObject(params, Map.class);

        List<MemberRoll> memberRollList;
        try {
            memberRollList = memberRollService.sendRoll(memberInfoList, rollTypeList, paramMap);
        } catch (ServiceRuntimeException serviceException) {
            return Result.failure(serviceException.getMessage());
        } catch (Exception e) {
            logger.error("用户派券异常", e);
            return Result.failure("用户派券异常");
        }
        //通知B端派券
        sendSendIngRollMessage(memberRollList, memberInfoList.parallelStream().
                map(memberInfo -> memberInfo.get(RollConstant.LOAN_ID)).collect(Collectors.toList()), null);
        return Result.success();
    }

    /**
     * 获取用户优惠券列表
     *
     * @param memberRollDto
     * @return
     */
    @PostMapping(value = "/getMemberRollList")
    public Result getMemberRollList(@RequestBody MemberRollDto memberRollDto) {
        Result result = Result.success();
        List<Map<String, Object>> list = memberRollService.getMemberRollList(memberRollDto);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", list);
        data.put("totalCount", PaginationUtil.pageTotalQuery(list));
        result.setData(data);
        return result;
    }

    /**
     * 更新券状态
     *
     * @param rollCode
     * @param rollStatus
     * @return
     */
    @PostMapping(value = "/updateRollStatus")
    public Result updateRollStatus(@RequestParam(value = "rollCode") String rollCode,
                                   @RequestParam(value = "rollStatus") Integer rollStatus) {
        logger.info("更新券状态, rollCode: {}; rollStatus: {}", rollCode, rollStatus);
        Integer result = memberRollService.updateRollStatus(rollCode, rollStatus);
        if (result == null || result <= 0) {
            return Result.failure("更新失败");
        }
        //通知B端更新券状态
        thirdPartySendMessageService.sendUpdateRollStatusMessage(rollCode, rollStatus);
        return Result.success();
    }

    /**
     * 用户派发指定券
     * @param rollCodes
     * @param params
     * @return
     */
    @PostMapping(value = "/sendAssignMemberRoll")
    public Result sendAssignMemberRoll(@RequestParam(value = "rollCodes") List<String> rollCodes,
                                       @RequestParam(value = "params") String params){
        logger.info("指定用户派发券, rollCodes: {}; params:{}", rollCodes, params);
        Map<String, Object> paramMap = JSON.parseObject(params, Map.class);
        Member member = memberService.getMemberByMobilePhone((String) paramMap.get("mobilePhone"));
        Result<String> checkResult = checkMember(member);
        if (checkResult.getCode() != ResultConstant.RESULT_CODE_SUCCESS){
            return Result.failure("派券失败, " + checkResult.getMessage());
        }

        List<MemberRoll> memberRollList;
        try {
            memberRollList = memberRollService.sendAssignMemberRoll(rollCodes, member, paramMap);
        } catch (ServiceRuntimeException serviceException) {
            return Result.failure(serviceException.getMessage());
        } catch (Exception e) {
            logger.error("用户派券异常", e);
            return Result.failure("用户派券异常");
        }
        //通知B端派券
        sendSendIngRollMessage(memberRollList, null, null);
        return Result.success();
    }

    //校验用户
    @SuppressWarnings("unchecked")
    private Result<String> checkMember(Member member){
        if (member == null){
            return Result.failure("用户未注册");
        }
        if (member.getStatus() == MemberConstant.MEMBER_LOGOFF_STATUS){
            return Result.failure("用户已注销");
        }
        if (member.getStatus() == MemberConstant.MEMBER_LOCKED_STATUS){
            return Result.failure("用户已锁定");
        }
        return Result.success();
    }

    //通知B端派券
    private void sendSendIngRollMessage(List<MemberRoll> memberRollList, List<Integer> loanIds, List<String> orderNos){
        if (memberRollList != null && memberRollList.size() > 0) {
            thirdPartySendMessageService.sendSendingMemberRollMessage(
                    memberRollList.stream()
                            .map(memberRoll -> {
                                Map<String, Object> map = new HashMap<>();
                                map.put("couponNo", memberRoll.getRollCode());
                                map.put("telno", memberRoll.getMemberPhone());
                                map.put("startTime", DateUtil.getSecondTimeStamp(new Date()));
                                map.put("name", memberRoll.getMemberName() == null ? "" : memberRoll.getMemberName());
                                map.put("endTime", DateUtil.getSecondTimeStamp(memberRoll.getInvalidTime()));
                                return map;
                            }).collect(Collectors.toList()), loanIds, orderNos);
        }
    }

    /**
     * 批量更新用户券状态（是否有效）
     * @param rollCodes
     * @param status
     * @return
     */
    @PostMapping(value = "/batchUpdateMemberRollStatus")
    public Result batchUpdateMemberRollStatus(@RequestParam(value = "rollCodes") List<String> rollCodes,
                                              @RequestParam(value = "status") Integer status,
                                              @RequestParam(value = "loanIds", required = false) List<Integer> loanIds){
        logger.info("subject: {}, rollCodes: {}, loanIds: {}", "B端回调更新用户券状态和借款单派券信息", rollCodes, loanIds);
        //更新用户券状态
        Integer result = memberRollService.batchUpdateMemberRollStatus(rollCodes, status);
        //更新借款单派券信息
        if (result != null && result > 0 && loanIds != null && loanIds.size() > 0){
            memberRollService.updateSendRollInfo(loanIds);
        }
        return Result.success();
    }

    /**
     * 用户派券
     *
     * @param memberInfos
     * @param rollTypes
     * @param params
     * @return
     */
    @PostMapping(value = "/bridgeSendRoll")
    public Result bridgeSendRoll(@RequestParam(value = "memberInfos") String memberInfos,
                           @RequestParam(value = "rollTypes") String rollTypes,
                           @RequestParam(value = "params") String params) {
        logger.info("批量用户派券, memberInfos: {}; rollTypes: {}; params: {}", memberInfos, rollTypes, params);
        List<Map<String, Integer>> memberInfoList = JSON.parseObject(memberInfos, new TypeReference<List<Map<String, Integer>>>(){});
        List<Map<String, Object>> rollTypeList = JSON.parseObject(rollTypes, new TypeReference<List<Map<String, Object>>>(){});
        Map<String, Object> paramMap = JSON.parseObject(params, Map.class);

        List<MemberRoll> memberRollList;
        try {
            memberRollList = memberRollService.sendRoll(memberInfoList, rollTypeList, paramMap);
        } catch (ServiceRuntimeException serviceException) {
            logger.error("用户派券异常", serviceException);
            return Result.failure(serviceException.getMessage());
        } catch (Exception e) {
            logger.error("用户派券异常", e);
            return Result.failure("用户派券异常");
        }
        //通知B端派券
        sendSendIngRollMessage(memberRollList,
                memberInfoList.parallelStream().map(memberInfo -> memberInfo.get(RollConstant.LOAN_ID)).collect(Collectors.toList()),
                rollTypeList.parallelStream().map(rollType -> (String) rollType.get(RollConstant.ORDER_NO)).collect(Collectors.toList()));
        return Result.success();
    }
}
