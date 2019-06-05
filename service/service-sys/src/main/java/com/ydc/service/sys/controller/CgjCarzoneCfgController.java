package com.ydc.service.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.file.FileUtil;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgQueDTO;
import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgSaveDTO;
import com.ydc.commom.view.vo.cgj.sys.CgjCarzoneCfgVO;
import com.ydc.model.cgj.CgjCarzoneCfg;
import com.ydc.model.cgj.Pagination;
import com.ydc.model.cgj.sys.CommImg;
import com.ydc.service.sys.service.CgjCarzoneCfgService;
import com.ydc.service.sys.service.CommImgService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车圈配置
 */
@RestController
@RequestMapping(value = "/carzone")
public class CgjCarzoneCfgController {

    private static final Logger logger = LogManager.getLogger(CgjCarzoneCfgController.class);

    @Autowired
    private CgjCarzoneCfgService cgjCarzoneCfgService;

    @Autowired
    private CommImgService commImgService;

    /**
     * 获取商品列表
     *
     * @param cgjCarzoneCfgQueDTO
     * @return
     */
    @RequestMapping(value = "/queryCarzoneCfgList", method = RequestMethod.POST)
    public String queryCarzoneCfgList(@RequestBody CgjCarzoneCfgQueDTO cgjCarzoneCfgQueDTO) {
        logger.info("subject:{},cgjCarzoneCfgQueDTO:{}", "获取车圈配置列表", cgjCarzoneCfgQueDTO);
        Pagination pagination = cgjCarzoneCfgQueDTO.changePage();
        cgjCarzoneCfgQueDTO.setPage(pagination.getPage());
        cgjCarzoneCfgQueDTO.setRows(pagination.getRows());
        List<Map<String,Object>> carzoneCfgList = cgjCarzoneCfgService.queryCarzoneCfgList(cgjCarzoneCfgQueDTO);
        int count = cgjCarzoneCfgService.queryCarzoneCfgCount(cgjCarzoneCfgQueDTO);
        for(Map<String, Object> img:carzoneCfgList){
            try {
                if(StringUtil.isNotEmpty(img.get("fileName"))){
                    img.put("fileUrl", FileUtil.getBrowseFile(img.get("fileUrl").toString(),img.get("fileName").toString()));
                    img.put("littleFileUrl",FileUtil.getBrowseFile(img.get("littleFileUrl").toString(),img.get("littleFileName").toString()));
                }else{
                    img.put("fileUrl","");
                    img.put("littleFileUrl","");
                }
            } catch (Exception e) {
                logger.error("subject:{},e:{}","获取图片地址异常",e);
            }
        }
        Map<String, Object> jMap = new HashMap<>();
        jMap.put("totalCount", count);
        jMap.put("rows",carzoneCfgList);
        return Result.success(jMap).toJSON();
    }

    /**
     * 获取车圈配置详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryCarzoneCfgDetails", method = RequestMethod.POST)
    public String queryCarzoneCfgDetails(@RequestBody Integer id) {
        logger.info("subject:{},cgjCarzoneCfgQueDTO:{}", "获取车圈配置详情", id);
        Map<String,Object> carzoneCfgDetails = cgjCarzoneCfgService.queryCarzoneCfgDetails(id);
        if (carzoneCfgDetails==null) {
            return Result.failure("该条数据不存在，请刷新后重试").toJSON();
        }
        if(StringUtil.isNotEmpty(carzoneCfgDetails.get("fileName"))){
            try {
                carzoneCfgDetails.put("fileUrl",FileUtil.getBrowseFile(carzoneCfgDetails.get("fileUrl").toString(),carzoneCfgDetails.get("fileName").toString()));
                carzoneCfgDetails.put("littleFileUrl",FileUtil.getBrowseFile(carzoneCfgDetails.get("littleFileUrl").toString(),carzoneCfgDetails.get("littleFileName").toString()));
            } catch (Exception e) {
                logger.error("subject:{},e:{}","获取图片地址异常",e);
            }
        }
        return Result.success(carzoneCfgDetails).toJSON();
    }

    /**
     * 保存或修改车圈配置信息
     *
     * @param cgjCarzoneCfgSaveDTO
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateCarzoneCfg", method = RequestMethod.POST)
    public String saveOrUpdateCarzoneCfg(@RequestBody CgjCarzoneCfgSaveDTO cgjCarzoneCfgSaveDTO) {
        logger.info("subject:{},cgjCarzoneCfgSaveDTO:{}", "保存或修改商品信息", cgjCarzoneCfgSaveDTO);
        CgjCarzoneCfg cgjCarzoneCfg = cgjCarzoneCfgSaveDTO.getCgjCarzoneCfg();
        CommImg commImg = cgjCarzoneCfgSaveDTO.getCommImg();
        try {
            //校验车圈配置信息
            if (cgjCarzoneCfg == null) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车圈配置参数").toJSON();
            }
            if (cgjCarzoneCfg.getTitle() == null) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少文章标题").toJSON();
            }
            if (cgjCarzoneCfg.getArticleUrl() == null) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少跳转链接").toJSON();
            }
            if (commImg == null) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少文章图标").toJSON();
            }
            if (cgjCarzoneCfg.getId()==null) {
                //新增数据
                cgjCarzoneCfgService.insert(cgjCarzoneCfg);
                commImg.setCommId(cgjCarzoneCfg.getId());
                commImgService.insert(commImg);

            }else{
                //更新数据
                CgjCarzoneCfg carzoneCfg = cgjCarzoneCfgService.selectByPrimaryKey(cgjCarzoneCfg.getId());
                if (carzoneCfg==null) {
                    return Result.failure("该条数据不存在，请刷新后重试").toJSON();
                }
                cgjCarzoneCfgService.updateByPrimaryKeySelective(cgjCarzoneCfg);
                commImgService.updateByPrimaryKeySelective(commImg);
            }
        } catch (Exception e) {
            logger.error("添加或更新异常",e);
            return Result.failure("添加或更新失败，请稍后尝试").toJSON();
        }
        return Result.success("成功").toJSON();
    }

    /**
     * 删除车圈配置详情
     *
     * @param cgjCarzoneCfg
     * @return
     */
    @RequestMapping(value = "/deleteCarzoneCfg", method = RequestMethod.POST)
    public String deleteCarzoneCfg(@RequestBody CgjCarzoneCfg cgjCarzoneCfg) {
        logger.info("subject:{},cgjCarzoneCfgQueDTO:{}", "删除车圈配置", cgjCarzoneCfg);
        try {
            CgjCarzoneCfg carzoneCfg = cgjCarzoneCfgService.selectByPrimaryKey(cgjCarzoneCfg.getId());
            if (carzoneCfg==null) {
                return Result.failure("该条数据不存在，请刷新后重试").toJSON();
            }
           cgjCarzoneCfgService.deleteCarzoneCfg(cgjCarzoneCfg);
        } catch (Exception e) {
            logger.error("删除车圈配置异常",e);
            return Result.failure("删除车圈配置失败，请稍后尝试").toJSON();
        }
        return Result.success("删除成功").toJSON();
    }
    /**
     * 更改是否展示状态
     *
     * @param cgjCarzoneCfg
     * @return
     */
    @RequestMapping(value = "/updateShowStatus", method = RequestMethod.POST)
    public String updateShowStatus(@RequestBody CgjCarzoneCfg cgjCarzoneCfg) {
        logger.info("subject:{},cgjCarzoneCfgQueDTO:{}", "更改是否展示状态", cgjCarzoneCfg);
        try {
            CgjCarzoneCfg carzoneCfg = cgjCarzoneCfgService.selectByPrimaryKey(cgjCarzoneCfg.getId());
            if (carzoneCfg==null) {
                return Result.failure("该条数据不存在，请刷新后重试").toJSON();
            }
            cgjCarzoneCfgService.updateShowStatus(cgjCarzoneCfg);
        } catch (Exception e) {
            logger.error("更改是否展示状态异常",e);
            return Result.failure("更改是否展示状态失败，请稍后尝试").toJSON();
        }
        return Result.success("更改是否展示状态成功").toJSON();
    }
}
