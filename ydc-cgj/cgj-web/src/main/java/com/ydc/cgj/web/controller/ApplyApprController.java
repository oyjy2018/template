package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.web.service.ApplyApprService;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.vo.cgj.LoanApplyVO;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("applyAppr")
public class ApplyApprController {
    protected static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ApplyApprController.class);

    @Autowired
    ApplyApprService applyApprService;

    @PostMapping(value = "/insertApplyAppr")
    public String insertApplyAppr(@RequestParam("data") String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        if (!jsonObject.containsKey("loanApplyId") || StringUtil.isEmpty(jsonObject.get("loanApplyId"))
                || !jsonObject.containsKey("acceptStatus") || StringUtil.isEmpty(jsonObject.get("acceptStatus"))) {
            return Result.failure("请查看参数是否都有填").toJSON();
        }
        User user = WebShiroTokenManager.getUser();
        if(user == null){
            return Result.success("请登录").toJSON();
        }
        logger.info("受理申请审批人登录信息 id :"+ user.getId()+ "  username:" + user.getUserName());
        jsonObject.put("userId", user.getId());
        jsonObject.put("accptUsername", user.getUserName());
//        jsonObject.put("userId", "1");
//        jsonObject.put("accptUsername", "12");
        logger.info("受理申请审批人登录信息 id :"+ jsonObject.get("userId")+ "  username:" + jsonObject.get("accptUsername"));
        data = jsonObject.toString();
        return applyApprService.insertApplyAppr(data);
    }

    @PostMapping(value = "/getApplyApprList")
    public String getApplyApprList(@RequestParam("data") String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        if (!jsonObject.containsKey("loanApplyId") || StringUtil.isEmpty(jsonObject.get("loanApplyId"))
                ) {
            return Result.failure("请查看参数").toJSON();
        }
        return applyApprService.getApplyApprList(data);
    }


    @PostMapping(value = "/getApplyList")
    public String getApplyList(@RequestParam("data") String data) {
        logger.info("subject:{},getApplyList:{}", "查询服务功能列表", data);
        LoanApplyVO loanApplyVO = JSONObject.parseObject(data,LoanApplyVO.class);
        return applyApprService.getApplyList(loanApplyVO);
    }

}


