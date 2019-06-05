package com.ydc.cgj.rental.company.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.alipay.AlipayTradePayResponseDTO;
import com.ydc.commom.view.dto.cgj.rental.ManualFreezeRentalDepositDTO;
import com.ydc.commom.view.dto.cgj.rental.PayRentalDepositRequestDTO;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.cgj.rental.RentalPayWatercourse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Service
@FeignClient(value = "service-order")
public interface DepositFeignService {

    /**
     * 新增租赁押金
     * @param rentalDeposit
     * @return
     */
    @PostMapping(value = "/deposit/addRentalDeposit")
    public String addRentalDeposit(@RequestBody RentalDeposit rentalDeposit);

}
