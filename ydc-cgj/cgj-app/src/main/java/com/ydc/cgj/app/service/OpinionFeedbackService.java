package com.ydc.cgj.app.service;

import com.ydc.commom.result.Result;
import com.ydc.model.cgj.OpinionFeedback;

public interface OpinionFeedbackService {

    /**
     * 添加意见反馈
     *
     * @param opinionFeedback
     * @return
     */
    Result opinionFeedbackAdd(OpinionFeedback opinionFeedback);
}
