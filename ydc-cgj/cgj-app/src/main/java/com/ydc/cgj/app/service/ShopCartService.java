package com.ydc.cgj.app.service;

import com.ydc.commom.result.Result;
import java.util.List;

public interface ShopCartService {

    /**
     * 查询会员购物车
     *
     * @param memberId
     * @return
     */
    Result getMemberShopCart(Integer memberId);

    /**
     * 、
     * 加入购物车
     *
     * @param memberId
     * @param modelId
     * @param commodityId
     * @param number
     * @return
     */
    Result addMemberShopCart(Integer memberId, Integer modelId, Integer commodityId, Integer number);

    /**
     * 更新购物车数量
     *
     * @param id
     * @param memberId
     * @param number
     * @return
     */
    Result updateMemberShopCart(Integer id, Integer memberId, Integer number);

    /**
     * 删除购物车商品
     *
     * @param ids
     * @param memberId
     * @return
     */
    Result deleteMemberShopCart(List<Integer> ids, Integer memberId);

}
