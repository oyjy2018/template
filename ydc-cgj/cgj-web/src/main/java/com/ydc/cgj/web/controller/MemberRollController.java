package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.web.service.MemberRollService;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.view.dto.cgj.BlankRollDto;
import com.ydc.commom.view.dto.cgj.MemberRollDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 用户优惠券controller
 */
@RestController
@RequestMapping(value = "/memberRoll")
public class MemberRollController {

    private final Logger logger = LogManager.getLogger(MemberRollController.class);

    @Autowired
    private MemberRollService memberRollService;

    /**
     * 用户派券
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/sendMemberRoll")
    public String sendMemberRoll(@RequestParam(value = "data") String data) {
        JSONObject jsonObject = JSONObject.parseObject(data);
        String memberInfos = jsonObject.getString("memberInfos"); //用户id和借款单id
        String rollTypes = jsonObject.getString("rollTypes"); //发送券类型
        String params = jsonObject.getString("params"); //派券参数

        //派券校验
        if (memberInfos == null){
            return Result.failure("派券失败，请先选择用户").toJSON();
        }
        if (rollTypes == null){
            return Result.failure("派券失败，请先选择券").toJSON();
        }
        Result checkResult = memberRollService.checkSendingRoll(memberInfos, rollTypes);
        if (ResultConstant.RESULT_CODE_SUCCESS != checkResult.getCode()){
            return checkResult.toJSON();
        }
        //派券
        return memberRollService.sendRoll(memberInfos, rollTypes, params).toJSON();
    }

    /**
     * 用户派发指定券
     * @param data
     * @return
     */
    @PostMapping(value = "/sendAssignMemberRoll")
    public String sendAssignMemberRoll(@RequestParam(value = "data") String data){
        JSONObject jsonObject = JSONObject.parseObject(data);
        List<String> rollCodes = jsonObject.getObject("rollCodes", List.class);
        String params = jsonObject.getString("params"); //派券参数

        //派券校验
        if (rollCodes == null || rollCodes.size() <= 0){
            return Result.failure("派券失败，请先选择优惠券").toJSON();
        }
        //派券
        return memberRollService.sendAssignMemberRoll(rollCodes, params).toJSON();
    }

    /**
     * 更新用户券状态
     * @param data
     * @return
     */
    @PostMapping(value = "/updateRollStatus")
    public String updateRollStatus(@RequestParam(value = "data") String data){
        JSONObject jsonObject = JSONObject.parseObject(data);
        return memberRollService.updateRollStatus(jsonObject.getString("rollCode"),
                jsonObject.getInteger("rollStatus")).toJSON();
    }

    /**
     * 获取用户优惠券列表
     * @param data
     * @return
     */
    @PostMapping(value = "/getMemberRollList")
    public String getMemberRollList(@RequestParam(value = "data") String data){
        MemberRollDto memberRollDto = JSONObject.parseObject(data, MemberRollDto.class);
        memberRollDto.changeEndTime(" 23:59:59");
        return memberRollService.getMemberRollList(memberRollDto).toJSON();
    }

    /**
     * 获取各种空券数量
     * @return
     */
    @GetMapping(value = "/getBlankRollCount")
    public String getBlankRollCount(){
        return memberRollService.getBlankRollCount().toJSON();
    }

    /**
     * 获取空券列表
     * @param data
     * @return
     */
    @PostMapping(value = "/getBlankRollList")
    public String getBlankRollList(@RequestParam(value = "data") String data){
        BlankRollDto blankRollDto = JSONObject.parseObject(data, BlankRollDto.class);
        blankRollDto.changeEndTime(" 23:59:59");
        return memberRollService.getBlankRollList(blankRollDto);
    }

    /**
     * 刷新空券（从B端刷新）
     * @param data
     * @return
     */
    @PostMapping(value = "/flushBlankRolls")
    public String flushBlankRolls(@RequestParam(value = "data") String data){
        JSONObject jsonObject = JSON.parseObject(data);
        List<String> rollCodes = jsonObject.getObject("rollCodes", List.class);

        if (rollCodes == null || rollCodes.size() <= 0){
            return Result.failure("刷新失败，请选择空券").toJSON();
        }
        return memberRollService.flushBlankRolls(rollCodes);
    }
}

