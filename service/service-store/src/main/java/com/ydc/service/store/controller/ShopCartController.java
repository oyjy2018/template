package com.ydc.service.store.controller;

import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.shopCart.MemberShopCartDTO;
import com.ydc.service.store.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 购物车
 */
@RestController
public class ShopCartController {
    @Autowired
    private ShopCartService shopCartService;

    /**
     * 查看 用户购物车
     * 购物车展示规则：  分来源展示，按照添加的顺序排序，最新添加的排在最上面
     * 失效的排在最下面
     */
    @GetMapping("/shopCart/{memberId}")
    @ResponseBody
    public Result getShopCart(@PathVariable(name = "memberId") Integer memberId) {
        if (StringUtil.isEmpty(memberId)) {
            return Result.failure("参数不能为空");
        }
        MemberShopCartDTO memberShopCartDTO = shopCartService.getMemberShopCartDTO(memberId);
        return Result.success(memberShopCartDTO);
    }

    @GetMapping("/shopCart/count/{memberId}")
    @ResponseBody
    public Result getShopCartCount(@PathVariable(name = "memberId") Integer memberId) {
        if (StringUtil.isEmpty(memberId)) {
            return Result.failure("参数不能为空");
        }
        MemberShopCartDTO memberShopCartDTO = shopCartService.getMemberShopCartDTO(memberId);
        return Result.success(memberShopCartDTO);
    }



    /**
     * 添加商品到购物车
     */
    @PostMapping("/shopCart/add")
    @ResponseBody
    public Result addShopCart(@RequestParam("memberId") Integer memberId,
                              @RequestParam("modelId") Integer modelId,
                              @RequestParam("commodityId") Integer commodityId,
                              @RequestParam("number") Integer number) {
        if (StringUtil.isEmpty(memberId) || StringUtil.isEmpty(modelId) ||
                StringUtil.isEmpty(commodityId) || StringUtil.isEmpty(number)) {
            return Result.failure("参数不能为空");
        }
        return shopCartService.addMemberShopCart(memberId, commodityId, modelId, number, "+");

    }


    /**
     * 购物车 更新商品
     */
    @PostMapping("/shopCart")
    public Result updateShopCartNum(@RequestParam("id") Integer id,
                                    @RequestParam("memberId") Integer memberId,
                                    @RequestParam("number") Integer number) {
        if (StringUtil.isEmpty(memberId) || StringUtil.isEmpty(id) || StringUtil.isEmpty(number)) {
            return Result.failure("参数不能为空");
        }
        return shopCartService.updateMemberShopCartNumber(id, memberId, number);

    }


    /**
     * 购物车 商品删除
     */
    @DeleteMapping("/shopCart")
    @ResponseBody
    public Result deleteShopCart(@RequestBody List<Integer> ids,
                                 @RequestParam("memberId") Integer memberId) {
        if (StringUtil.isEmpty(ids) || StringUtil.isEmpty(memberId)) {
            return Result.failure("参数不能为空");
        }
        try {
            shopCartService.deleteMemberShopCart(memberId, ids);
            return Result.success();
        }catch (ServiceRuntimeException e){
            return Result.failure(e.getMessage());
        }

    }


}
