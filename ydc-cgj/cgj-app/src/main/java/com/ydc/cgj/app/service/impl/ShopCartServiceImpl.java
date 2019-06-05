package com.ydc.cgj.app.service.impl;

import com.ydc.cgj.app.feignService.IShopCartFeignService;
import com.ydc.cgj.app.service.ShopCartService;
import com.ydc.commom.result.Result;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Resource
    private IShopCartFeignService iShopCartFeignService;

    @Override
    public Result getMemberShopCart(Integer memberId) {
        return iShopCartFeignService.getMemberShopCart(memberId);
    }

    @Override
    public Result addMemberShopCart(Integer memberId, Integer modelId, Integer commodityId, Integer number) {
        return iShopCartFeignService.addMemberShopCart(memberId, modelId, commodityId, number);
    }

    @Override
    public Result updateMemberShopCart(Integer id, Integer memberId, Integer number) {
        return iShopCartFeignService.updateMemberShopCart(id, memberId, number);
    }

    @Override
    public Result deleteMemberShopCart(List<Integer> ids, Integer memberId) {
        return iShopCartFeignService.deleteMemberShopCart(ids, memberId);
    }
}
