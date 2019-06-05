package com.ydc.cgj.rental.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.rental.web.service.RentalCarCheckService;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCarCheckQueryDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 外部车辆审核
 */
@RestController
@RequestMapping(value = "/rentalCarCheck")
public class RentalCarCheckController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private RentalCarCheckService rentalCarCheckService;

    /**
     * 获取车辆列表查询条件
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getCondition")
    public String getCondition() {
        return rentalCarCheckService.getCondition(WebShiroTokenManager.getUser().getId());
    }

    /**
     * 通过企业名称查询门店列表
     * @param data
     * @return
     */
    @PostMapping(value = "/getStoreNameByCompanyName")
    public String getStoreNameByCompanyName(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        String companyName = dataJSON.getString("companyName");
        if(StringUtil.isEmpty(companyName)) {
            return Result.failure("公司名称不能为空").toJSON();
        }
        return rentalCarCheckService.getStoreNameByCompanyName(companyName);
    }

    /**
     * 根据车品牌查询车系
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getSeriesByBrand")
    public String getSeriesByBrand(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        String brand = dataJSON.getString("brand");
        if (StringUtil.isEmpty(brand)) {
            return Result.failure("品牌不能为空").toJSON();
        }
        return rentalCarCheckService.getSeriesByBrand(brand);
    }

    /**
     * 根据车系查询车型
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getModelBySeries")
    public String getCarModelBySeries(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        String series = dataJSON.getString("series");
        if (StringUtil.isEmpty(series)) {
            return Result.failure("车系不能为空").toJSON();
        }
        return rentalCarCheckService.getModelBySeries(series);
    }

    /**
     * 获取审核车辆列表
     *
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:externalCarCheck:manage:car:view:query"})
    @PostMapping(value = "/getList")
    public String getCarList(@RequestParam("data") String data) {
        RentalCarCheckQueryDTO rentalCarCheckQueryDTO = JSONObject.parseObject(data, RentalCarCheckQueryDTO.class);
        return rentalCarCheckService.getList(rentalCarCheckQueryDTO);
    }

    /**
     * 获取审核车辆详情
     *
     * @param data
     * @return
     */
    // @RequiresPermissions(value = {"rental:externalCarCheck:manage:car:view:detail"})
    @PostMapping(value = "/getDetail")
    public String getCarDetail(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        Integer id = dataJSON.getInteger("id");
        if (id == null) {
            throw new ServiceRuntimeException("车辆id不能为空");
        }
        return rentalCarCheckService.getDetail(id);
    }

    /**
     * 审核车辆
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/check")
    @RequiresPermissions(value = {"rental:externalCarCheck:manage:car:view:check"})
    public String check(@RequestParam("data") String data) {
        return rentalCarCheckService.check(data, WebShiroTokenManager.getUser().getId());
    }

}
