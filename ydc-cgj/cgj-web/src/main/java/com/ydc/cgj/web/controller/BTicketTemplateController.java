package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.web.service.BTicketTemplateService;
import com.ydc.commom.view.dto.cgj.service.RollTemplateDTO;
import com.ydc.commom.view.dto.cgj.service.TicketDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 券模板
 * @author
 * @create 2018-10-31 15:24
 **/
@RestController
@RequestMapping(value = "/bTicketTemplate")
public class BTicketTemplateController {

    private static final Logger logger = LogManager.getLogger(BTicketTemplateController.class);


    @Autowired
    BTicketTemplateService bTicketTemplateService;

    /**
     * 卷模板列表
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getTicketTemplateList")
    public String getTicketTemplateList(@RequestParam("data") String data) {
        logger.info("subject:{},rollTemplateDTO:{}", "卷模板列表", data);
        RollTemplateDTO rollTemplateDTO = JSONObject.parseObject(data,RollTemplateDTO.class);
        return bTicketTemplateService.getTicketTemplateList(rollTemplateDTO);
    }

    /**
     * 刷新券模板
     * @return
     */
    @PostMapping(value = "/refreshTicketTemplate")
    public String refreshTicketTemplate(@RequestParam("data") String data){
        logger.info("subject:{},rollTemplateDTO:{}","刷新券模板",data);
        RollTemplateDTO rollTemplateDTO = JSONObject.parseObject(data,RollTemplateDTO.class);
        rollTemplateDTO.setUpdateBy(WebShiroTokenManager.getUser().getId());
        return bTicketTemplateService.refreshTicketTemplate(rollTemplateDTO);
    }

    /**
     * 获取空劵
     * @return
     */
    @PostMapping(value = "/getEmptyTicket")
    public String getEmptyTicket(@RequestParam("data") String data){
        logger.info("subject:{},ticketDTO:{}","获取空劵",data);
        TicketDTO ticketDTO = JSONObject.parseObject(data,TicketDTO.class);
        ticketDTO.setUpdateBy(WebShiroTokenManager.getUser().getId());
        return bTicketTemplateService.getEmptyTicket(ticketDTO);
    }
}

