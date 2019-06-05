package com.ydc.cgj.app.feignService;

import com.ydc.commom.view.dto.cgj.integral.IntegralDTO;
import com.ydc.model.cgj.DictionaryDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * 用户属性操作
 *
 * @author gongjin
 * @create 2018-09-07 13:55
 **/
@Service
@FeignClient(value = "service-user")
public interface UserFeignService {


    /**
     * 我的积分
     * @param memberId
     * @return
     */
    @PostMapping(value = "/h5integral/getMyIntegral")
    String getMyIntegral(@RequestParam(value = "memberId") Integer memberId);

    /**
     * 我的积分-明细（收入和支出）
     * @return
     */
    @PostMapping(value = "/h5integral/getIntegralDetail")
    String getIntegralDetail(@RequestBody IntegralDTO integralDTO);


    /**
     * 获取数据字典子集
     * @param parentDictCode
     * @return
     */
    @PostMapping(value = "/dictionaryDetail/getConfigInfoByParentDictCode")
    List<DictionaryDetail> getConfigInfoByParentDictCode(@RequestParam(value = "parentDictCode") String parentDictCode);

    /**
     * parentDictCode和dictKey
     * @param parentDictCode
     * @return
     */
    @PostMapping(value = "/dictionaryDetail/getDictionaryDetailByDictKey")
    DictionaryDetail getDictionaryDetailByDictKey(@RequestParam(value = "dictKey") String dictKey,@RequestParam(value = "parentDictCode") String parentDictCode);


    /**
     * parentDictCode和dictValue
     * @param parentDictCode
     * @return
     */
    @PostMapping(value = "/dictionaryDetail/getDictionaryDetailByDictValue")
    DictionaryDetail getDictionaryDetailByDictValue(@RequestParam(value = "dictValue") String dictValue,@RequestParam(value = "parentDictCode") String parentDictCode);

    /**
     * 获取H5配置
     * @return
     */
    @PostMapping(value = "/dictionaryDetail/getH5Config")
    Map<String,List<DictionaryDetail>> getH5Config();
}
