package com.ydc.cgj.app.service.impl;

import com.ydc.cgj.app.feignService.IOpinionFeedbackService;
import com.ydc.cgj.app.service.OpinionFeedbackService;
import com.ydc.commom.result.Result;
import com.ydc.model.cgj.OpinionFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpinionFeedbackServiceImpl implements OpinionFeedbackService {

    @Autowired
    private IOpinionFeedbackService opinionFeedbackService;

    @Override
    public Result opinionFeedbackAdd(OpinionFeedback opinionFeedback) {
        return opinionFeedbackService.opinionFeedbackAdd(opinionFeedback);
    }
}
