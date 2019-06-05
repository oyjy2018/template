package com.ydc.cgj.ydhc.app.controller;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.cgj.ydhc.app.service.DictionaryDetailService;
import com.ydc.cgj.ydhc.app.service.VehicleService;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.vo.ydhc.VehicleVo;
import com.ydc.model.cgj.DictionaryDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/vehicle")
public class VehicleController {
    private final Logger logger = LogManager.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private DictionaryDetailService dictionaryDetailService;

    /**
     * 获取车辆列表
     *
     * @param vehicleVo
     * @return
     */
    @GetMapping(value = "/getVehicleList")
    public String getVehicleList(VehicleVo vehicleVo) {
        logger.info("获取车辆列表, vehicleVo: {}", vehicleVo);
        vehicleVo.setTitle(StringUtil.preventInjection(vehicleVo.getTitle()));
        return vehicleService.getVehicleList(vehicleVo.changePage()).toJSON();
    }

    /**
     * 获取车辆详情
     *
     * @param vehicleId
     * @return
     */
    @GetMapping(value = "/getVehicleDetail")
    public String getVehicleDetail(Integer vehicleId) {
        logger.info("获取车辆详情, vehicleId: {}", vehicleId);
        return vehicleService.getVehicleDetail(vehicleId).toJSON();
    }

    /**
     * 获取车辆查询的配置
     *
     * @return
     */
    @GetMapping(value = "/getVehicleConfig")
    public String getVehicleConfig() {
        Result result = Result.success();
        Map<String, Object> map = new HashMap<>();
        //获取车辆来源配置
        List<DictionaryDetail> carSourceList = DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_CAR_SOURCE_CONFIG)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_CAR_SOURCE_CONFIG));
        if (carSourceList != null && carSourceList.size() > 0) {
            carSourceList = carSourceList.stream()
                    .map(dictionaryDetail -> {
                        DictionaryDetail tempDictionaryDetail = new DictionaryDetail();
                        tempDictionaryDetail.setDictKey(dictionaryDetail.getDictKey());
                        tempDictionaryDetail.setDictValue(dictionaryDetail.getDictValue());
                        return tempDictionaryDetail;
                    })
                    .collect(Collectors.toList());
        }
        //获取车辆品牌车系配置
        List<DictionaryDetail> carBrandList = DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_CAR_BRAND_CONFIG)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_CAR_BRAND_CONFIG));
        if (carBrandList != null && carBrandList.size() > 0) {
            carBrandList = carBrandList.stream()
                    .map(dictionaryDetail -> {
                        DictionaryDetail tempDictionaryDetail = new DictionaryDetail();
                        tempDictionaryDetail.setDictKey(dictionaryDetail.getDictKey());
                        tempDictionaryDetail.setDictValue(dictionaryDetail.getDictValue());
                        tempDictionaryDetail.setRemark1(dictionaryDetail.getRemark1());
                        tempDictionaryDetail.setRemark2(dictionaryDetail.getRemark2());
                        tempDictionaryDetail.setRemark3(dictionaryDetail.getRemark3());
                        tempDictionaryDetail.setRemark4(dictionaryDetail.getRemark4());
                        tempDictionaryDetail.setSort(dictionaryDetail.getSort());
                        return tempDictionaryDetail;
                    })
                    .collect(Collectors.toList());
        }
        map.put("carSourceList", carSourceList);
        map.put("carBrandList", carBrandList);
        result.setData(map);
        return result.toJSON();
    }

    /**
     * 获取图片路径
     *
     * @param filePath
     * @param fileName
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getFileUrl")
    public String getFileUrl(String filePath, String fileName) throws Exception {
        return FileUtil.getBrowseFile(filePath, fileName);
    }
}
