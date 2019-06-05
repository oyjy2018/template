package com.ydc.cgj.web.feignService;

import com.ydc.commom.view.dto.cgj.service.RollTemplateDTO;
import com.ydc.commom.view.dto.cgj.service.TicketDTO;
import com.ydc.model.cgj.entity.TicketTemplateEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

/**
 * @author
 * @create 2018-11-01 10:51
 **/
@Service
@FeignClient(value = "service-store")
public interface ITicketTemplateFeignService {

    /**
     * 刷新券模板
     * @return
     */
    @PostMapping(value = "/ticket/saveTicketTemplate")
    String saveTicketTemplate(@RequestBody List<TicketTemplateEntity> list);

    /**
     * 获取空劵
     * @param ticketDTO
     * @return
     */
    @PostMapping(value = "/ticket/saveEmptyTicket")
    String saveEmptyTicket(@RequestBody TicketDTO ticketDTO);

    /**
     * 派卷模板列表
     *
     * @param rollTemplateDTO
     * @return
     */
    @PostMapping(value = "/ticket/getTicketTemplateList")
    String getTicketTemplateList(@RequestBody RollTemplateDTO rollTemplateDTO);
}
