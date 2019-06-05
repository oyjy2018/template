package com.ydc.service.user.service;

import com.ydc.model.cgj.OpinionFeedback;

public interface OpinionFeedbackService {

    /**
     * 添加意见反馈
     *
     * @param opinionFeedback
     * @return
     */
    Integer insertOpinionFeedback(OpinionFeedback opinionFeedback);
}
