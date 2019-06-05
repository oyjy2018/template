package com.ydc.cgj.app.service.impl;

import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.cgj.app.common.SubjectUtil;
import com.ydc.cgj.app.feignService.CgjViolationRecordDetailFeignService;
import com.ydc.cgj.app.feignService.CgjViolationRecordFeignService;
import com.ydc.cgj.app.service.CgjViolationRecordService;
import com.ydc.commom.result.Result;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.sys.CgjViolationRecordQueDTO;
import com.ydc.commom.view.vo.cgj.sys.CgjViolationRecordVO;
import com.ydc.model.cgj.CgjViolationRecord;
import com.ydc.model.cgj.Pagination;
import com.ydc.model.cgj.sys.CgjViolationRecordDetail;
import com.ydc.model.mongodb.common.ViolationRecordLog;
import com.ydc.mongodb.cgj.dao.MongoDbDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
@Service(value = "violationRecordServiceImpl")
public class CgjViolationRecordServiceImpl implements CgjViolationRecordService {

    private final Logger logger = LogManager.getLogger(CgjViolationRecordServiceImpl.class);

    //public static final String URL = "http://127.0.0.1:14000/bridge-violation/getRecordList";
    @Autowired
    CgjViolationRecordFeignService cgjViolationRecordFeignService;
    @Autowired
    CgjViolationRecordDetailFeignService cgjViolationRecordDetailFeignService;
    @Resource
    MongoDbDao mongoDbDao;

    @Override
    public String getRecordList(String data) {
        CgjViolationRecordQueDTO cgjViolationRecordQueDTO = JsonUtil.jsonToBean(data,CgjViolationRecordQueDTO.class);
        String result = null;
        CgjViolationRecordVO vioRecDetailVO = null;
        //Map<String, Object> requestParam = getRequestParam(cgjViolationRecordQueDTO);
        Map<String, Object> jMap = new HashMap<>();
        try {
            //去数据库查询
            Integer memberId = SubjectUtil.getMember().getId();
//            Integer memberId = 007;
            CgjViolationRecord record = cgjViolationRecordFeignService.selectOneRecord(cgjViolationRecordQueDTO);
            CgjViolationRecordQueDTO cgjViolationRecordQueDTOTemp = new CgjViolationRecordQueDTO();
            Date nowDate = new Date();
            //如果存在数据
            if (record != null) {
                //违章详情列表(状态为未处理的)
                cgjViolationRecordQueDTOTemp.setRecordId(record.getId());
                List<CgjViolationRecordDetail> recordDetailList = cgjViolationRecordDetailFeignService.selectRecordDetailListByRecordId(cgjViolationRecordQueDTOTemp);
                Date checkTime = record.getCheckTime();
                int hoursDiff = DateUtil.getHoursDiff(checkTime, nowDate);
                if (hoursDiff != -1) {
                    if (hoursDiff >= 24) {//距离上次查询时间大于24个小时则重新查询第三方接口数据并更新数据库
                        result = UrlHttpUtil.doPost(SystemPropertiesConfig.CGJ_VIOLATION_RECORD_URL, data);
                        JSONObject json = JSONObject.fromObject(result);
                        //查询失败
                        if (json.getInt("status") != 0) {
                            /**
                             201	交管局为空
                             202	交管局不存在
                             203	车牌前缀为空
                             204	车牌前缀有误
                             205	车牌号为空
                             206	车牌号有误
                             207	发动机号为空
                             208	发动机号有误（扣次数）
                             209	车架号为空
                             210	车架号有误（扣次数）
                             211	登记证书号为空
                             212	登记证书号有误
                             216	其他为空
                             217	有违章，详情请到交管网查询
                             220	交管局服务器错误
                             230	没有信息
                             */
                            if (json.getInt("status") == 217||json.getInt("status") == 220||json.getInt("status") == 230) {
                                logger.info("subject:{}","违章查询未查询到的请求参数："+data+"第三方接口返回信息:"+ json.getString("msg"));
                                return Result.success("暂未查到数据，请稍后重试。").toJSON();
                            }
                            logger.error("subject:{}", "提供的车辆信息有误。");
                            return Result.success("对不起，您提供的车辆信息有误，请核对后重新填写。").toJSON();
                        } else {
                            JSONObject resultarr = json.optJSONObject("result");
                            if (resultarr != null) {
                                //更新违章数据
                                CgjViolationRecord cgjViolationRecord = combineVioRecModel(resultarr);
                                cgjViolationRecord.setId(record.getId());
                                cgjViolationRecord.setCheckTime(nowDate);
                                cgjViolationRecord.setUpdateTime(nowDate);
                                cgjViolationRecord.setUpdateBy(memberId);
                                cgjViolationRecordFeignService.updateByPrimaryKeySelective(cgjViolationRecord);
                                List<CgjViolationRecordDetail> recordDetailListResult = new ArrayList<>();
                                if (resultarr.opt("list") != null) {
                                    JSONArray list = resultarr.optJSONArray("list");
                                    for (int j = 0; j < list.size(); j++) {
                                        JSONObject list_obj = (JSONObject) list.opt(j);
                                        if (list_obj != null) {
                                            CgjViolationRecordDetail violationRecordDetail = combineVioRecDetailModel(list_obj);
                                            recordDetailListResult.add(violationRecordDetail);
                                        }
                                    }
                                    if (recordDetailList != null && recordDetailList.size() > 0) {
                                        if (recordDetailListResult != null && recordDetailListResult.size() > 0) {
                                            List<CgjViolationRecordDetail> recordDetailListTemp = new ArrayList<>();
                                            List<CgjViolationRecordDetail> recordDetailListRresultTemp = new ArrayList<>();
                                            //更新数据+更新状态+新增数据
                                            for (CgjViolationRecordDetail cgjViolationRecordDetail : recordDetailList) {
                                                for (CgjViolationRecordDetail cgjViolationRecordDetailResult : recordDetailListResult) {
                                                    //将与数据库中相同的违章详情记录删除
                                                    if (cgjViolationRecordDetail.getIllegalid().equals(cgjViolationRecordDetailResult.getIllegalid())&&cgjViolationRecordDetail.getDealStatus()==0) {

                                                        recordDetailListTemp.add(cgjViolationRecordDetail);
                                                        recordDetailListRresultTemp.add(cgjViolationRecordDetailResult);
                                                    }
                                                    if (cgjViolationRecordDetail.getDealStatus()==1) {
                                                        recordDetailListTemp.add(cgjViolationRecordDetail);
                                                    }
                                                }
                                            }
                                            recordDetailList.removeAll(recordDetailListTemp);
                                            recordDetailListResult.removeAll(recordDetailListRresultTemp);
                                            //如果list中还有数据则证明是违章已处理，相应的数据做处理状态更新
                                            if (recordDetailList!=null&&recordDetailList.size()>0) {
                                                for (CgjViolationRecordDetail cgjViolationRecordDetail : recordDetailList) {
                                                    //更新状态为已处理
                                                    updateDealDownStatus(cgjViolationRecordDetail,memberId);
                                                }
                                            }
                                            //如果listResult中还有数据则证明是新的违章
                                            if (recordDetailListResult!=null&&recordDetailListResult.size()>0) {
                                                //新增数据
                                                for (CgjViolationRecordDetail cgjViolationRecordDetail : recordDetailListResult) {
                                                    addNewViolationRecordDetail(cgjViolationRecordDetail,record.getId(),memberId);

                                                }
                                            }
                                        } else {
                                            //更新状态为已处理
                                            for (CgjViolationRecordDetail cgjViolationRecordDetail : recordDetailList) {
                                                updateDealDownStatus(cgjViolationRecordDetail,memberId);
                                            }
                                        }
                                    } else {
                                        if (recordDetailListResult != null && recordDetailListResult.size() > 0) {
                                            //新增数据
                                            for (CgjViolationRecordDetail cgjViolationRecordDetail : recordDetailListResult) {
                                                addNewViolationRecordDetail(cgjViolationRecordDetail,record.getId(),memberId);
                                            }
                                        }
                                    }
                                } else {
                                    //更新状态为已处理
                                    if (recordDetailList!=null&&recordDetailList.size()>0) {
                                        for (CgjViolationRecordDetail cgjViolationRecordDetail : recordDetailList) {
                                            updateDealDownStatus(cgjViolationRecordDetail,memberId);
                                        }
                                    }
                                }
                            } else {
                                //第三方接口返回null（正常情况下无论有无违章都会返回数据）
                                return Result.success("暂未查到数据，请稍后重试。").toJSON();
                            }
                        }
                    }
                } else {
                    logger.error("subject:{}", "时间计算错误");
                    return Result.failure("暂未查到数据，请稍后重试。").toJSON();
                }
            } else {
                //如果不存在数据
                result = UrlHttpUtil.doPost(SystemPropertiesConfig.CGJ_VIOLATION_RECORD_URL, data);
                JSONObject json = JSONObject.fromObject(result);
                
                ViolationRecordLog violationRecordLog = new ViolationRecordLog();
                Long id=System.currentTimeMillis();
                violationRecordLog.setId(id);
                violationRecordLog.setRequestData(data);
                violationRecordLog.setResultData(result);
                violationRecordLog.setCreateBy(memberId.toString());
                violationRecordLog.setCreateTime(new Date());
                //查询失败
                if (json.getInt("status") != 0) {
                    violationRecordLog.setSuccess(false);
                    mongoDbDao.save(violationRecordLog);
                    /**
                     201	交管局为空
                     202	交管局不存在
                     203	车牌前缀为空
                     204	车牌前缀有误
                     205	车牌号为空
                     206	车牌号有误
                     207	发动机号为空
                     208	发动机号有误（扣次数）
                     209	车架号为空
                     210	车架号有误（扣次数）
                     211	登记证书号为空
                     212	登记证书号有误
                     216	其他为空
                     217	有违章，详情请到交管网查询
                     220	交管局服务器错误
                     230	没有信息
                     */
                    if (json.getInt("status") == 217||json.getInt("status") == 220||json.getInt("status") == 230) {
                        logger.info("subject:{}","违章查询未查询到的请求参数："+data+"第三方接口返回信息:"+ json.getString("msg"));
                        return Result.success("暂未查到数据，请稍后重试。").toJSON();
                    }
                    logger.error("subject:{}", "提供的车辆信息有误");
                    return Result.success("对不起，您提供的车辆信息有误，请核对后重新填写。").toJSON();
                } else {
                    violationRecordLog.setSuccess(true);
                    mongoDbDao.save(violationRecordLog);
                    JSONObject resultarr = json.optJSONObject("result");
                    if (resultarr != null) {
                        //存入数据库
                        CgjViolationRecord cgjViolationRecord = combineVioRecModel(resultarr);
                        addNewViolationRecord(cgjViolationRecord,cgjViolationRecordQueDTO,memberId);
                        if (resultarr.opt("list") != null) {
                            JSONArray list = resultarr.optJSONArray("list");
                            CgjViolationRecord recordTemp = cgjViolationRecordFeignService.selectOneRecord(cgjViolationRecordQueDTO);
                            for (int j = 0; j < list.size(); j++) {
                                JSONObject list_obj = (JSONObject) list.opt(j);
                                if (list_obj != null) {
                                    CgjViolationRecordDetail violationRecordDetail = combineVioRecDetailModel(list_obj);
                                    addNewViolationRecordDetail(violationRecordDetail,recordTemp.getId(),memberId);
                                }
                            }
                        }
                    } else {
                        return Result.success("暂未查到数据，请稍后重试。").toJSON();
                    }
                }
            }

            //到数据库查询数据并返回给前端
            CgjViolationRecord recordResult = cgjViolationRecordFeignService.selectOneRecord(cgjViolationRecordQueDTO);
            cgjViolationRecordQueDTOTemp.setRecordId(recordResult.getId());
            if (cgjViolationRecordQueDTO.getDealStatus()!=null) {
                cgjViolationRecordQueDTOTemp.setDealStatus(cgjViolationRecordQueDTO.getDealStatus());
            }
            List<CgjViolationRecordDetail> recordDetaiResultlCountList = cgjViolationRecordDetailFeignService.selectRecordDetailListByRecordId(cgjViolationRecordQueDTOTemp);
            Pagination pagination = cgjViolationRecordQueDTO.changePage();
            cgjViolationRecordQueDTOTemp.setPage(pagination.getPage());
            cgjViolationRecordQueDTOTemp.setRows(pagination.getRows());
            List<CgjViolationRecordDetail> recordDetaiResultlList = cgjViolationRecordDetailFeignService.selectRecordDetailListByRecordId(cgjViolationRecordQueDTOTemp);
            jMap.put("cgjViolationRecord",recordResult);
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(recordDetaiResultlCountList));
            jMap.put("rows",recordDetaiResultlList);
            //vioRecDetailVO = combineVioRecDetailVO(recordResult, recordDetaiResultlList);
        } catch (Exception e) {
            logger.error("subject:{},e:{}", "违章查询异常", e);
            return Result.failure("暂未查到数据，请稍后重试。").toJSON();
        }

        return Result.success(jMap).toJSON();
    }


    private CgjViolationRecord combineVioRecModel(JSONObject resultarr) {
        CgjViolationRecord violationRecord = new CgjViolationRecord();
        violationRecord.setLsprefix(resultarr.getString("lsprefix"));
        violationRecord.setLsnum(resultarr.getString("lsnum"));
        violationRecord.setCarorg(resultarr.getString("carorg"));
        if (!StringUtil.isBlank(resultarr.getString("usercarid"))) {
            violationRecord.setUsercarid(Integer.parseInt(resultarr.getString("usercarid")));
        }
        violationRecord.setCount(resultarr.getInt("count"));
        violationRecord.setTotalprice(resultarr.getString("totalprice"));
        violationRecord.setTotalscore(resultarr.getString("totalscore"));
        return violationRecord;
    }

    private CgjViolationRecordDetail combineVioRecDetailModel(JSONObject list_obj) {
        CgjViolationRecordDetail violationRecordDetail = new CgjViolationRecordDetail();
        violationRecordDetail.setTime(list_obj.getString("time"));
        violationRecordDetail.setAddress(list_obj.getString("address"));
        violationRecordDetail.setContent(list_obj.getString("content"));
        violationRecordDetail.setLegalnum(list_obj.getString("legalnum"));
        violationRecordDetail.setPrice(list_obj.getString("price"));
        violationRecordDetail.setScore(list_obj.getString("score"));
        violationRecordDetail.setAgency(list_obj.getString("agency"));
        violationRecordDetail.setIllegalid(list_obj.getInt("illegalid"));
        if (list_obj.containsKey("number")) {
            violationRecordDetail.setNumber(list_obj.getString("number"));
        }
        if (list_obj.containsKey("province")) {
            violationRecordDetail.setProvince(list_obj.getString("province"));
        }
        if (list_obj.containsKey("city")) {
            violationRecordDetail.setCity(list_obj.getString("city"));
        }
        if (list_obj.containsKey("town")) {
            violationRecordDetail.setTown(list_obj.getString("town"));
        }
        if (list_obj.containsKey("lat")&&list_obj.containsKey("lng")) {
            violationRecordDetail.setLat(list_obj.getString("lat"));
            violationRecordDetail.setLng(list_obj.getString("lng"));
        }
        violationRecordDetail.setCanhandle(list_obj.getInt("canhandle"));
        if (list_obj.containsKey("handlefee")) {
            violationRecordDetail.setHandlefee(list_obj.getString("handlefee"));
        }
        return violationRecordDetail;
    }

    //更新状态为已处理状态
    private void updateDealDownStatus(CgjViolationRecordDetail cgjViolationRecordDetail,Integer memberId){
        cgjViolationRecordDetail.setUpdateTime(new Date());
        cgjViolationRecordDetail.setUpdateBy(memberId);
        cgjViolationRecordDetail.setDealStatus(1);
        cgjViolationRecordDetailFeignService.updateDealStatusById(cgjViolationRecordDetail);
    }


    private void addNewViolationRecord(CgjViolationRecord cgjViolationRecord,CgjViolationRecordQueDTO cgjViolationRecordQueDTO,Integer memberId) {
        cgjViolationRecord.setCreateTime(new Date());
        cgjViolationRecord.setUpdateTime(new Date());
        cgjViolationRecord.setCheckTime(new Date());
        cgjViolationRecord.setLstype(cgjViolationRecordQueDTO.getLstype());
        cgjViolationRecord.setFrameno(cgjViolationRecordQueDTO.getFrameno());
        cgjViolationRecord.setEngineno(cgjViolationRecordQueDTO.getEngineno());
        cgjViolationRecord.setMobile(cgjViolationRecordQueDTO.getMobile());
        cgjViolationRecord.setCreateBy(memberId);
        cgjViolationRecord.setUpdateBy(memberId);
        cgjViolationRecordFeignService.insert(cgjViolationRecord);
    }
    //新增新的违章详情
    private void addNewViolationRecordDetail(CgjViolationRecordDetail cgjViolationRecordDetail ,Integer recordId ,Integer memberId){
        cgjViolationRecordDetail.setRecordId(recordId);
        cgjViolationRecordDetail.setDealStatus(0);//默认未处理;
        cgjViolationRecordDetail.setCreateTime(new Date());
        cgjViolationRecordDetail.setUpdateTime(new Date());
        cgjViolationRecordDetail.setCreateBy(memberId);
        cgjViolationRecordDetail.setUpdateBy(memberId);
        cgjViolationRecordDetailFeignService.insert(cgjViolationRecordDetail);
    }
    /*private CgjViolationRecordVO combineVioRecDetailVO(CgjViolationRecord recordResult, List<CgjViolationRecordDetail> recordDetaiResultlList) {
        CgjViolationRecordVO cgjViolationRecordVO = new CgjViolationRecordVO();
        cgjViolationRecordVO.setCarorg(recordResult.getCarorg());
        cgjViolationRecordVO.setCount(recordResult.getCount());
        cgjViolationRecordVO.setLsnum(recordResult.getLsnum());
        cgjViolationRecordVO.setLsprefix(recordResult.getLsprefix());
        cgjViolationRecordVO.setTotalprice(recordResult.getTotalprice());
        cgjViolationRecordVO.setTotalscore(recordResult.getTotalscore());
        cgjViolationRecordVO.setUsercarid(recordResult.getUsercarid());
        if (recordDetaiResultlList != null && recordDetaiResultlList.size() > 0) {
            cgjViolationRecordVO.setViolationStatusMsg("");
            cgjViolationRecordVO.setList(recordDetaiResultlList);
        } else {
            cgjViolationRecordVO.setViolationStatusMsg("暂无违章记录");
        }

        return cgjViolationRecordVO;
    }*/
}
