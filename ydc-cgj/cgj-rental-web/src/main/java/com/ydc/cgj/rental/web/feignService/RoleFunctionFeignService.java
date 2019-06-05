package com.ydc.cgj.rental.web.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.UserInsertDTO;
import com.ydc.commom.view.dto.cgj.UserQueryDTO;
import com.ydc.commom.view.dto.cgj.UserUpdateDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Service
@FeignClient(value = "service-user")
public interface RoleFunctionFeignService {

    /**
     * 获取功能树
     * @param serviceIdentifying
     * @param functionName
     * @return
     */
    @PostMapping(value = "/roleFunction/getFunctionTree")
    String getFunctionTree(@RequestParam("serviceIdentifying") String serviceIdentifying, @RequestParam("functionName") String functionName);
}
