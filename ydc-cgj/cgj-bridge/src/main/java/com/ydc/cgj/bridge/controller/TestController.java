package com.ydc.cgj.bridge.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.bridge.service.MemberAppointmentService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.appointment.BOrderResponseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private MemberAppointmentService memberAppointmentService;

    @PostMapping(value = "/update")
    public String update(@RequestBody String data){
        Result result=Result.failure("cuw");
       // BOrderResponseDTO bOrderResponseDTO = JSONObject.parseObject(data, BOrderResponseDTO.class);
        JSONObject jsonObject = JSON.parseObject(data);
        BOrderResponseDTO bOrderResponseDTO = JSONObject.parseObject(jsonObject.get("data").toString(),BOrderResponseDTO.class);

        memberAppointmentService.updateAppointStatusByBAppointStatus(bOrderResponseDTO,new Integer(1));
       logger.info(result.toJSON());

        return  result.toJSON();
    }

}
