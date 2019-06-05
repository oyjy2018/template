package com.ydc.service.order.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalAccidentQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalAccidentQueryVO;
import com.ydc.model.cgj.rental.RentalAccident;
import com.ydc.service.order.service.RentalAccidentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accident")
public class RentalAccidentController {

    private final Logger logger = LogManager.getLogger(RentalAccidentController.class);

    @Autowired
    RentalAccidentService rentalAccidentService;

    /**
     *插入事故信息
     * @return
     */
    @PostMapping(value = "/insertRentalAccidentInfo")
    public String insertRentalAccidentInfo(@RequestBody RentalAccident rentalAccident) {
        logger.info("subject:{},rentalAccident:{}","插入事故信息",JSON.toJSONString(rentalAccident));
        if (rentalAccident == null) {
            return Result.failure("事故信息为空").toJSON();
        } else {
            try {
                return rentalAccidentService.insertRentalAccidentInfo(rentalAccident) <= 0 ?  Result.failure("新增失败").toJSON() : Result.success("成功").toJSON();
            } catch (Exception e) {
                logger.error("插入事故信息信息异常，异常数据:{},异常原因：{}",JSON.toJSONString(rentalAccident),e.getMessage());
                return  Result.failure("新增事故信息失败").toJSON();
            }
        }
    }

    /**
     * 根据id查询事故单信息
     * @param id
     * @return
     */
    @PostMapping(value = "/queryRentalAccidentInfoById")
    public Result queryRentalAccidentInfoById(@RequestParam("id") Integer id) {
        Map rentalAccident = null;
        try {
            rentalAccident = rentalAccidentService.queryRentalAccidentInfoById(id);
        } catch (Exception e) {
            Result.failure("根据id查询事故单详情失败");
            logger.error("根据id查询事故信息异常,id:{},异常信息：{}",id,e.getMessage());
        }
        return Result.success(rentalAccident);
    }

    /**
     * 获取事故列表
     * @param rentalAccidentQueryDTO
     * @return
     */
    @PostMapping(value = "/queryRentalAccidentListInfo")
    public Result queryRentalAccidentListInfo(@RequestBody RentalAccidentQueryDTO rentalAccidentQueryDTO){
        logger.info("获取事故列表的传送事故信息为:{}", JSON.toJSONString(rentalAccidentQueryDTO));
        Result result = Result.success();

        List<RentalAccidentQueryVO> list = null;
        try {
            list = rentalAccidentService.queryRentalAccidentListInfo(rentalAccidentQueryDTO);
            Map<String, Object> data = new HashMap<>();
            data.put("rows", list);
            data.put("totalCount", PaginationUtil.pageTotalQuery(list));
            result.setData(data);
        } catch (Exception e) {
            logger.error("获取事故列表信息异常,异常原因:{}",e.getMessage());
            return Result.failure();
        }
        return result;
    }

    /**
     * 编辑更新事故信息
     * @param rentalAccident
     * @return
     */
    @PostMapping(value = "/updateRentalAccidentInfo")
    public String updateRentalAccidentInfo(@RequestBody RentalAccident rentalAccident) {
        logger.info("subject:{},rentalAccident:{}","编辑事故信息", JsonUtil.gsonStr(rentalAccident));
        try{
            if(rentalAccidentService.updateRentalAccidentInfo(rentalAccident) > 0){
                return Result.success("成功").toJSON();
            }else{
                return Result.failure("编辑事故信息失败").toJSON();
            }
        }catch (Exception e){
            logger.error("编辑事故信息异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 软删除事故单
     * @param rentalAccident
     * @return
     */
    @PostMapping(value = "/deleteRentalAccidentInfo")
    public String deleteRentalAccidentInfo(@RequestBody RentalAccident rentalAccident) {
        logger.info("subject:{},rentalAccident:{}","软删除事故单", JsonUtil.gsonStr(rentalAccident));
         return rentalAccidentService.deleteRentalAccidentInfo(rentalAccident.getId()) > 0 ?  Result.success().toJSON():Result.failure().toJSON();
     }

    /**
     * 查询租车订单或机务单信息
     * @param rentalAccident
     * @return
     */
    @PostMapping(value = "/queryRentalOrderOrMaintenance")
    public Result queryRentalOrderOrMaintenance(@RequestBody RentalAccident rentalAccident) {
        Map<String,String> orderOrMaintenanceInfo = null;
        try {
            orderOrMaintenanceInfo = rentalAccidentService.queryRentalOrderOrMaintenance(rentalAccident);
        } catch (Exception e) {
            Result.failure("查询用车信息失败!");
            logger.error("查询用车信息失败异常,失败请求参数为:{},异常信息：{}",JSON.toJSONString(rentalAccident),e.getMessage());
        }
        return Result.success(orderOrMaintenanceInfo);
    }

}
