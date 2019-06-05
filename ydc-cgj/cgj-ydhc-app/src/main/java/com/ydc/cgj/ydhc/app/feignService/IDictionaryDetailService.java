package com.ydc.cgj.ydhc.app.feignService;

import com.ydc.model.cgj.DictionaryDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(value = "service-user")
public interface IDictionaryDetailService {

    @PostMapping(value = "/dictionaryDetail/getConfigInfoByParentDictCode")
    List<DictionaryDetail> getConfigInfoByParentDictCode(@RequestParam(value = "parentDictCode") String parentDictCode);
}
