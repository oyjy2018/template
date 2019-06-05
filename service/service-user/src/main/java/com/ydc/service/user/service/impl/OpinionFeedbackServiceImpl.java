package com.ydc.service.user.service.impl;

import com.ydc.dao.cgj.order.OpinionFeedbackDao;
import com.ydc.model.cgj.OpinionFeedback;
import com.ydc.service.user.service.OpinionFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpinionFeedbackServiceImpl implements OpinionFeedbackService {

    @Autowired
    private OpinionFeedbackDao opinionFeedbackDao;

    @Override
    public Integer insertOpinionFeedback(OpinionFeedback opinionFeedback) {
        return opinionFeedbackDao.insert(opinionFeedback);
    }
}
