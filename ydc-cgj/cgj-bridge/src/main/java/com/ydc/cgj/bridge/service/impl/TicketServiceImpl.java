package com.ydc.cgj.bridge.service.impl;

import com.ydc.cgj.bridge.feignService.TicketFeignService;
import com.ydc.cgj.bridge.service.TicketService;
import com.ydc.commom.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketFeignService ticketFeignService;

    @Override
    public Result batchUpdateBlankRollStatus(List<String> rollCodes, Integer status) {
        return ticketFeignService.batchUpdateBlankRollStatus(rollCodes, status);
    }
}
