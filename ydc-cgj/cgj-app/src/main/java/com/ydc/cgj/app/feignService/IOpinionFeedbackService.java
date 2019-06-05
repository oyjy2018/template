package com.ydc.cgj.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.model.cgj.OpinionFeedback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(value = "service-user")
public interface IOpinionFeedbackService {

    /**
     * 添加意见反馈
     *
     * @param opinionFeedback
     * @return
     */
    @PostMapping(value = "/opinionFeedback/opinionFeedbackAdd", consumes = "application/json")
    Result opinionFeedbackAdd(@RequestBody OpinionFeedback opinionFeedback);
}
