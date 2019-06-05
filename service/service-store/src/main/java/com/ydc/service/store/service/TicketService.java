package com.ydc.service.store.service;

import com.ydc.commom.view.dto.cgj.BlankRollDto;
import com.ydc.commom.view.dto.cgj.service.RollTemplateDTO;
import com.ydc.commom.view.dto.cgj.service.TicketDTO;
import com.ydc.commom.view.vo.cgj.order.BTicketTemplateVO;
import com.ydc.model.cgj.BRollDetail;
import com.ydc.model.cgj.entity.TicketTemplateEntity;

import java.util.List;
import java.util.Map;

/**
 * 券
 *
 * @author
 * @create 2018-11-01 12:21
 **/
public interface TicketService {

    /**
     * 刷新券模板
     * @param list
     * @return
     */
    void saveTicketTemplate(List<TicketTemplateEntity> list);

    /**
     * 获取空劵
     * @param ticketDTO
     * @return
     */
    void saveEmptyTicket(TicketDTO ticketDTO);

    /**
     * 卷模板列表
     * @param rollTemplateDTO
     * @return
     */
    List<BTicketTemplateVO> getTicketTemplateList(RollTemplateDTO rollTemplateDTO);

    /**
     * 批量更新券状态
     * @param rollCodes
     * @param status
     * @return
     */
    Integer batchUpdateTicketStatus(List<String> rollCodes, Integer status);

    /**
     * 获取空券列表
     * @param blankRollDto
     * @return
     */
    List<BRollDetail> getBlankRollList(BlankRollDto blankRollDto);

    /**
     * 刷新空券
     * @param rollCodes
     * @return
     */
    List<BRollDetail> flushBlankRolls(List<String> rollCodes) throws Exception;
}
