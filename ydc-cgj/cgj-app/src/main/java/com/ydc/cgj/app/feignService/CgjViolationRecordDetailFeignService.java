package com.ydc.cgj.app.feignService;


import com.ydc.commom.view.dto.cgj.sys.CgjViolationRecordQueDTO;
import com.ydc.model.cgj.sys.CgjViolationRecordDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@FeignClient(value = "service-sys")
public interface CgjViolationRecordDetailFeignService {

    @PostMapping(value = "/violationRecordDetail/insert")
    int insert(CgjViolationRecordDetail record);

    @PostMapping(value = "/violationRecordDetail/getRecordDetailListByRecordId")
    List<CgjViolationRecordDetail> selectRecordDetailListByRecordId(CgjViolationRecordQueDTO cgjViolationRecordQueDTO);

    @PostMapping(value = "/violationRecordDetail/updateDealStatusById")
    int updateDealStatusById(CgjViolationRecordDetail cgjViolationRecordDetail);

}
