package com.ydc.service.store.controller;

import com.github.pagehelper.PageInfo;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.BlankRollDto;
import com.ydc.commom.view.dto.cgj.service.RollTemplateDTO;
import com.ydc.commom.view.dto.cgj.service.TicketDTO;
import com.ydc.commom.view.vo.cgj.order.BTicketTemplateVO;
import com.ydc.model.cgj.BRollDetail;
import com.ydc.model.cgj.entity.TicketTemplateEntity;
import com.ydc.service.store.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 券
 *
 * @author
 * @create 2018-11-01 12:15
 **/
@RestController
@RequestMapping(value = "/ticket")
public class TicketController {

    private static final Logger logger = LogManager.getLogger(TicketController.class);

    @Autowired
    TicketService ticketService;

    /**
     * 卷模板列表
     * @param rollTemplateDTO
     * @return
     */
    @PostMapping(value = "/getTicketTemplateList")
    public String getTicketTemplateList(@RequestBody RollTemplateDTO rollTemplateDTO){
        logger.info("subject:{},rollTemplateDTO:{}","查询卷模板列表",JsonUtil.gsonStr(rollTemplateDTO));
        try {
            List<BTicketTemplateVO> ret = ticketService.getTicketTemplateList(rollTemplateDTO);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(ret));
            jMap.put("rows", ret);
            return Result.success(jMap).toJSON();
        } catch (Exception e) {
            logger.error("查询卷模板列表异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     *  刷新券模板
      * @param list
     * @return
     */
    @PostMapping(value = "/saveTicketTemplate")
    public String saveTicketTemplate(@RequestBody List<TicketTemplateEntity> list){
         logger.info("subject:{},list:{}","刷新券模板",JsonUtil.gsonStr(list));
         try{
             ticketService.saveTicketTemplate(list);
             return Result.success("成功").toJSON();
         } catch (Exception e) {
             logger.error("刷新券模板异常",e);
             return Result.failure().toJSON();
         }
    }

    /**
     * 获取空劵
     * @param ticketDTO
     * @return
     */
    @PostMapping(value = "/saveEmptyTicket")
    public String saveEmptyTicket(@RequestBody TicketDTO ticketDTO){
        logger.info("subject:{},ticketDTO:{}","获取空劵",JsonUtil.gsonStr(ticketDTO));
        try{
            ticketService.saveEmptyTicket(ticketDTO);
            return Result.success("成功").toJSON();
        } catch (Exception e) {
            logger.error("刷新券模板异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 批量更新空券状态
     * @param rollCodes
     * @param status
     * @return
     */
    @PostMapping(value = "/batchUpdateTicketStatus")
    public Result batchUpdateTicketStatus(@RequestParam(value = "rollCodes") List<String> rollCodes, @RequestParam(value = "status") Integer status){
        return ticketService.batchUpdateTicketStatus(rollCodes, status) > 0 ? Result.success() : Result.failure();
    }

    /**
     * 获取空券列表
     * @param blankRollDto
     * @return
     */
    @PostMapping(value = "/getBlankRollList")
    public String getBlankRollList(@RequestBody BlankRollDto blankRollDto){
        Result result = Result.success();
        List<BRollDetail> list = ticketService.getBlankRollList(blankRollDto);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", list);
        data.put("totalCount", PaginationUtil.pageTotalQuery(list));
        result.setData(data);
        return result.toJSON();
    }

    /**
     * 刷新空券
     * @param rollCodes
     * @return
     */
    @PostMapping(value = "/flushBlankRolls")
    public String flushBlankRolls(@RequestParam(value = "rollCodes") List<String> rollCodes){
        Result result = Result.success();
        try {
            result.setData(ticketService.flushBlankRolls(rollCodes));
        }catch (ServiceRuntimeException serviceException){
            return Result.failure(serviceException.getMessage()).toJSON();
        }catch (Exception e) {
            logger.error("从B端刷新空券异常", e);
            return Result.failure("请稍后重试").toJSON();
        }
        return result.toJSON();
    }
}
