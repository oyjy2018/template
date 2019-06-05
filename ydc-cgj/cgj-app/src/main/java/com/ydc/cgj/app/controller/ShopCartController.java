package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.app.common.SubjectUtil;
import com.ydc.cgj.app.service.ShopCartService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.NumberUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.model.cgj.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ShopCartController {


    @Autowired
    private ShopCartService shopCartService;

    /**
     * 查看 用户购物车
     * 购物车展示规则：  分来源展示，按照添加的顺序排序，最新添加的排在最上面
     * 失效的排在最下面
     */
    @GetMapping("/shopCart")
    public String getShopCart() {
        Member member = SubjectUtil.getMember();
        if (null == member) {
            return Result.failure("用户未登陆").toJSON();
        }
        Result result = shopCartService.getMemberShopCart(member.getId());
        if (null != result) {
            return result.toJSON();
        }
        return Result.failure("更新失败").toJSON();
    }

    /**
     * 商品加入购物
     *
     * @return
     */
    @PostMapping("/shopCart/add")
    public String addShopCart(@RequestParam("data") String data) {
        Integer modelId,commodityId,number;
        try {
            JSONObject jsonObject=JSON.parseObject(data);
            modelId=jsonObject.getInteger("modelId");
            commodityId=jsonObject.getInteger("commodityId");
            number=jsonObject.getInteger("number");
        }catch (Exception e){
            return Result.failure("参数错误").toJSON();
        }

        if (StringUtil.isEmpty(modelId) || StringUtil.isEmpty(commodityId) ||
                StringUtil.isEmpty(number)) {
            return Result.failure("参数不能为空").toJSON();
        }
        if (!NumberUtil.hasPositiveNumber(number)) {
            return Result.failure("参数错误").toJSON();
        }
        Member member = SubjectUtil.getMember();
        if (null == member) {
            return Result.failure("用户未登陆").toJSON();
        }
        Integer memberId = member.getId();
        Result result = shopCartService.addMemberShopCart(memberId, modelId, commodityId, number);
        if (null == result) {
            return Result.failure("添加失败").toJSON();
        }
        return result.toJSON();
    }

    /**
     * 更新购物车商品数量
     */
    @ResponseBody
    @PostMapping(value = "/shopCart")
    public String updateShopCart(@RequestParam("data") String data) {
        Integer id,number;
        try {
            JSONObject jsonObject=JSON.parseObject(data);
            id=jsonObject.getInteger("id");
            number=jsonObject.getInteger("number");
        }catch (Exception e){
            return Result.failure("参数错误").toJSON();
        }

        if (StringUtil.isEmpty(id) || StringUtil.isEmpty(number)) {
            return Result.failure("参数不能为空").toJSON();
        }
        if (!NumberUtil.hasPositiveNumber(number)) {
            return Result.failure("参数错误").toJSON();
        }
        Member member = SubjectUtil.getMember();
        if (null == member) {
            return Result.failure("用户未登陆").toJSON();
        }
        Result result = shopCartService.updateMemberShopCart(id, member.getId(), number);
        return result.toJSON();
    }

    /**
     * 购物车 商品删除
     */
    @ResponseBody
    @RequestMapping(value = "/shopCart", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public String deleteShopCart(@RequestParam("data") String data) {
        List<Integer> ids ;
        try {
            JSONObject jsonObject=JSON.parseObject(data);
            ids = (List<Integer>) jsonObject.get("ids");

        }catch (Exception e){
            return Result.failure("参数错误").toJSON();
        }

        Member member = SubjectUtil.getMember();
        if (null == member) {
            return Result.failure("用户未登陆").toJSON();
        }
        Result result = shopCartService.deleteMemberShopCart(ids, member.getId());
        return result.toJSON();
    }


}
