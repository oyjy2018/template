package com.ydc.cgj.web.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.MemberRollDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Service
@FeignClient(value = "service-user")
public interface MemberRollFeignService {

    /**
     * 获取多种空白券的数量
     *
     * @return
     */
    @GetMapping(value = "/memberRoll/getBlankRollCount")
    Result getBlankRollCount();

    /**
     * 派券检验
     *
     * @param memberInfos
     * @param rollTypes
     * @return
     */
    @GetMapping(value = "/memberRoll/checkSendingRoll")
    Result checkSendingRoll(@RequestParam(value = "memberInfos") String memberInfos,
                            @RequestParam(value = "rollTypes") String rollTypes);

    /**
     * 派券
     *
     * @param memberInfos
     * @param rollTypes
     * @return
     */
    @PostMapping(value = "/memberRoll/sendRoll")
    Result sendRoll(@RequestParam(value = "memberInfos") String memberInfos,
                    @RequestParam(value = "rollTypes") String rollTypes,
                    @RequestParam(value = "params") String params);

    /**
     * 更新用户券状态
     *
     * @param rollCode
     * @param rollStatus
     * @return
     */
    @PostMapping(value = "/memberRoll/updateRollStatus")
    Result updateRollStatus(@RequestParam(value = "rollCode") String rollCode,
                            @RequestParam(value = "rollStatus") Integer rollStatus);

    /**
     * 获取用户优惠券列表
     *
     * @param memberRollDto
     * @return
     */
    @PostMapping(value = "/memberRoll/getMemberRollList")
    Result getMemberRollList(@RequestBody MemberRollDto memberRollDto);

    /**
     * 派发指定券
     * @param rollCodes
     * @param params
     * @return
     */
    @PostMapping(value = "/memberRoll/sendAssignMemberRoll")
    Result sendAssignMemberRoll(@RequestParam(value = "rollCodes") List<String> rollCodes,
                                @RequestParam(value = "params") String params);
}
