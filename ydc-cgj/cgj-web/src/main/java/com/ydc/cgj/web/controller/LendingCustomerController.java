package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.web.service.LendingCustomerService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.LendingCustomerDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 放款客户
 *
 * @author
 * @create 2018-10-31 15:05
 **/
@RestController
@RequestMapping(value = "/lendingCustomer")
public class LendingCustomerController {

    private static final Logger logger = LogManager.getLogger(LendingCustomerController.class);

    @Autowired
    LendingCustomerService lendingCustomerService;

    /**
     * 放款客户列表
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getLendingCustomerList")
    public String getLendingCustomerList(@RequestParam("data") String data) {
        logger.info("subject:{},lendingCustomerDTO:{}", "放款派卷列表", data);
        LendingCustomerDTO lendingCustomerDTO = JSONObject.parseObject(data,LendingCustomerDTO.class);
        return lendingCustomerService.getLendingCustomerList(lendingCustomerDTO.changeDTO());
    }

    /**
     * 用户派完券
     * @param data
     * @return
     */
    @PostMapping(value = "/updateCustomerRollOver")
    public String updateCustomerRollOver(@RequestParam("data") String data){
        JSONObject jsonObject = JSONObject.parseObject(data);
        List<Integer> loanIds = JSON.parseArray(jsonObject.getString("loanIds"), Integer.class);

        if (loanIds == null || loanIds.size() <= 0){
            return Result.success().toJSON();
        }
        return lendingCustomerService.updateCustomerRollOver(loanIds).toJSON();
    }
}
