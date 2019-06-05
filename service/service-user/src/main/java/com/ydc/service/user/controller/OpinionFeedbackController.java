package com.ydc.service.user.controller;

import com.ydc.commom.result.Result;
import com.ydc.model.cgj.OpinionFeedback;
import com.ydc.service.user.service.OpinionFeedbackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/opinionFeedback")
public class OpinionFeedbackController {
    private Logger logger = LogManager.getLogger(OpinionFeedbackController.class);

    @Autowired
    private OpinionFeedbackService opinionFeedbackService;

    /**
     * 添加意见反馈
     * @param opinionFeedback
     * @return
     */
    @PostMapping("/opinionFeedbackAdd")
    public Result opinionFeedbackAdd(@RequestBody OpinionFeedback opinionFeedback){
        logger.info("添加意见反馈, opinionFeedback: " + opinionFeedback);
        return opinionFeedbackService.insertOpinionFeedback(opinionFeedback) <= 0 ? Result.failure() : Result.success();
    }
}
