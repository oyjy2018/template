package com.ydc.service.loan.controller;


import com.ydc.service.loan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;

    @ResponseBody
    @RequestMapping("/user")
    public Object queryUserList() {
        return orderService.queryUserList();
    }

}
