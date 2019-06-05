package com.ydc.cgj.app.feignService;


import com.ydc.commom.view.dto.cgj.sys.CgjViolationRecordQueDTO;
import com.ydc.model.cgj.CgjViolationRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@FeignClient(value = "service-sys")
public interface CgjViolationRecordFeignService {


    @PostMapping(value = "/violationRecord/insert")
    int insert(CgjViolationRecord record);

    @PostMapping(value = "/violationRecord/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CgjViolationRecord record);

    @GetMapping(value = "/violationRecord/selectOneRecord")
    CgjViolationRecord selectOneRecord(CgjViolationRecordQueDTO cgjViolationRecordQueDTO);
}
