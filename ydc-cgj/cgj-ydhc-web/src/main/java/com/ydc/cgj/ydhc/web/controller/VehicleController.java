package com.ydc.cgj.ydhc.web.controller;

import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.ydhc.web.service.VehicleService;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.BigDecimalUtil;
import com.ydc.commom.util.ParamVaildateUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.ydhc.YdhcVehicleDTO;
import com.ydc.commom.view.dto.ydhc.YdhcVehicleImgDTO;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private static Logger logger = LogManager.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;

    /**
     * 修改或保存车辆信息
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/saveOrUpdateVehicle")
    public String saveOrUpdateVehicle(@RequestBody Map<String, Object> req) {
        if (req.get("ydhcVehicle") == null) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆信息").toJSON();
        }
        Map<String, String> ydhcVehicle = (Map<String, String>) req.get("ydhcVehicle");
        Map vaildMap = ParamVaildateUtil.vaildateMapAndTransfer(ydhcVehicle, YdhcVehicleDTO.class);
        if ("1".equals(vaildMap.get("code"))) {
            return Result.failure(vaildMap.get("message").toString()).toJSON();
        }

        List<Map<String, String>> ydhcVehicleImgs = null;
        if (ydhcVehicle.get("id") == null || "0".equals(ydhcVehicle.get("id"))) {
            if (req.get("ydhcVehicleImgs") == null) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片").toJSON();
            }
            ydhcVehicleImgs = (List<Map<String, String>>) req.get("ydhcVehicleImgs");
            if (ydhcVehicleImgs.size() == 0) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片").toJSON();
            }
        }
        if (ydhcVehicleImgs != null) {
            int homeImgNum = 0;
            int describeImgNum = 0;
            for (Map<String, String> ydhcVehicleImg : ydhcVehicleImgs) {
                vaildMap = ParamVaildateUtil.vaildateMapAndTransfer(ydhcVehicleImg, YdhcVehicleImgDTO.class);
                if ("1".equals(vaildMap.get("code"))) {
                    return Result.failure(vaildMap.get("message").toString()).toJSON();
                }

                if ("1".equals(ydhcVehicleImg.get("imgType"))) {
                    homeImgNum++;
                } else if ("2".equals(ydhcVehicleImg.get("imgType"))) {
                    describeImgNum++;
                }

            }

            if (homeImgNum != 1) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "需要上传首页图片1张，当前上传[" + homeImgNum + "]张！").toJSON();
            }
            if (describeImgNum != 8) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "需要上传描述图片8张，当前上传[" + homeImgNum + "]张！").toJSON();
            }
        }
        User user = WebShiroTokenManager.getUser();
        req.put("userId", user.getId());
        req.put("userName", user.getUserName());
        return vehicleService.saveOrUpdateVehicle(req);
    }

    /**
     * 更新发布状态
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/updateReleaseStatus")
    public String updateReleaseStatus(@RequestBody Map<String, String> req) {
        return vehicleService.updateReleaseStatus(req);
    }

    /**
     * 获取车辆信息
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/getVehicleInfo")
    public String getVehicleInfo(@RequestBody Map<String, String> req) {
        return vehicleService.getVehicleInfo(req);
    }

    /**
     * 查询车辆列表信息
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/getYdhcVehicleList")
    public String getVehicleList(@RequestBody Map<String, Object> req) {
        return vehicleService.getYdhcVehicleList(req);
    }

}
