package com.ydc.cgj.web.service;

import com.ydc.commom.view.dto.cgj.service.RollTemplateDTO;
import com.ydc.commom.view.dto.cgj.service.TicketDTO;

/**
 * @author
 * @create 2018-10-31 15:26
 **/
public interface BTicketTemplateService {

    /**
     * 派卷模板列表
     *
     * @param rollTemplateDTO
     * @return
     */
    String getTicketTemplateList(RollTemplateDTO rollTemplateDTO);


    /**
     * 刷新券模板
     * @return
     */
    String refreshTicketTemplate(RollTemplateDTO rollTemplateDTO);

    /**
     * 获取空劵
     * @param ticketDTO
     * @return
     */
    String getEmptyTicket(TicketDTO ticketDTO);
}
