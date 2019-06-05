package com.ydc.cgj.rental.web.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(value = "service-user")
public interface MemberApplicationFeignService {

    /**
     * 获取客户列表
     * @param memberDTO
     * @return
     */
    @PostMapping(value = "/memberApplication/getMemberApplicationList")
    Result getMemberApplicationList(@RequestBody MemberDTO memberDTO);

    /**
     * 编辑客戶資料
     * @param memberApplicationVO
     * @return
     */
    @PostMapping(value = "/memberApplication/updateMemberApplication")
    String updateMemberApplication(@RequestBody MemberApplicationVO memberApplicationVO);

    /**
     * 后台-新增会员
     *
     * @param memberApplicationVO
     * @return
     */
    @PostMapping(value = "/member/insertMember")
    String insertMember(@RequestBody MemberApplicationVO memberApplicationVO);

    /**
     * 更新会员状态
     *
     * @param memberDTO
     * @return
     */
    @PostMapping(value = "/member/updateMemberStatus")
    String updateMemberStatus(@RequestBody MemberDTO memberDTO);

    /**
     * 通过memberId查询客户资料
     * @param memberId
     * @return
     */
    @PostMapping(value = "/memberApplication/getMemberApplicationById/{memberId}")
    Result getMemberApplicationById(@PathVariable("memberId") int memberId);
}
