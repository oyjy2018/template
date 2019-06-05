package com.ydc.cgj.bridge.interfaces;


import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.model.cgj.DictionaryDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车300接口
 * @author:hejiangping
 * @date：2019年1月7日
 */
public class CheThreeHundred {

	private static Logger logger = LogManager.getLogger(CheThreeHundred.class);

	//车300-token
	private static final String CHE300_TOKEN = SystemPropertiesConfig.CHE300_TOKEN;
	//车300-VIN识别车型接口
	private static final String CHE300_IDENTIFYMODELBYVIN_URL = SystemPropertiesConfig.CHE300_CARPRICE_URL+"/service/identifyModelByVIN";
	//车300-车型接口
	private static final String CHE300_CARMODELIST_URL = SystemPropertiesConfig.CHE300_CARPRICE_URL+"/service/getCarModelList";
	//车300-车系列表 
	private static final String CHE300_CARSERIESLIST_URL = SystemPropertiesConfig.CHE300_CARPRICE_URL+"/service/getCarSeriesList";
	//车300-品牌列表 
	private static final String CHE300_CARBRANDLIST_URL = SystemPropertiesConfig.CHE300_CARPRICE_URL+"/service/getCarBrandList";
	//车300-城市列表 
	private static final String CHE300_CARALLCITY_URL = SystemPropertiesConfig.CHE300_CARPRICE_URL+"/service/getAllCity";
	//车300-精准估值
	private static final String CHE300_CARUSEDCARPRICE_URL = SystemPropertiesConfig.CHE300_CARPRICE_URL+"/service/getUsedCarPrice";
	//车300-定价接口
	private static final String CHE300_GETUSEDCARPRICEANALYSIS_URL = SystemPropertiesConfig.CHE300_CARPRICE_URL+"/service/eval/getUsedCarPriceAnalysis";

	/**
	 * 车300-VIN识别车型接口
	 * @author:gongjin
	 * @param: vehicleFrameNumber 车架号
	 * @date: 2017年12月20日 上午9:41:33
	 * @return:Map<String,Object>
	 */
	public static Map<String, Object> identifyModelByVIN(String vehicleFrameNumber){
		Map<String, Object> result = new HashMap<>(); //返回map
		try {
			String url = CHE300_IDENTIFYMODELBYVIN_URL + "?vin="+vehicleFrameNumber+"&token="+CHE300_TOKEN;
			logger.info("subject:{},url:{}","车300-VIN识别车型接口请求参数",url);
			String responseParam = UrlHttpUtil.doGet(url);
			result = JsonUtil.jsonToMap(responseParam);
			logger.info("subject,result","车300-VIN识别车型接口响应参数",result);
			if (Integer.valueOf(result.get("status").toString()) == 0) {
				result.put("error_msg", result.get("error_msg"));
				return result;
			}
		} catch (Exception e) {
			logger.error("subject:{},e:{}", "车300-VIN识别车型接口查询异常",e);
		}
		return result;
	}
	/**
	 * 车300-城市列表
	 * @author:gongjin
	 * @param:
	 * @date: 2017年12月20日 上午10:18:50
	 * @return:void
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getAllCity(){
		Map<String, Object> result = new HashMap<>(); //返回map
		List<Map<String, Object>> cityList = new ArrayList<>();
		try {
			String url = CHE300_CARALLCITY_URL + "?token="+CHE300_TOKEN;
			logger.info("subject:{},url:{}","车300-城市列表请求参数",url);
			String responseParam = UrlHttpUtil.doGet(url);
			result = JsonUtil.jsonToMap(responseParam);
			logger.info("subject,result","车300-城市列表接口 响应参数",result);
			if (Integer.valueOf(result.get("status").toString()) == 0) {
				logger.info("subjec", "车300-城市列表接口查询失败");
			}else{
				//将响应参数存入缓存
				cityList = (List<Map<String, Object>>) result.get("city_list");
			}
		} catch (Exception e) {
			logger.error("subject:{},e:{}", "车300-城市列表接口查询异常",e);
		}
		return cityList;
	}
	
	/**
	 * 车300-定价接口 请求参数
	 * @author:gongjin
	 * @param: reqParam 包含参数
	 * @param： modelId 车型ID 		必须
	 * @param：regDate 车辆上牌日期，如2012-01		 必须
	 * @param：makeDate 车辆出厂日期，如2011-09 	可选参数
	 * @param：mile 车辆行驶里程，单位是万公里 		必须
	 * @param：zone 城市ID 	必须
	 * @param：color 车辆颜色（中文），颜色列表：米色，棕色，金色，紫色，巧克力色，黑色，蓝色，灰色，绿色，红色，橙色，白色，香槟色，银色，黄色  	必须
	 * @param：interior 内饰状况（中文），可选列表：优、良、中、差 	必须
	 * @param：surface 漆面状况（中文），可选列表：优、良、中、差 		必须
	 * @param：work_state 工况状况（中文），可选列表：优、良、中、差	 必须
	 * @param：transfer_times 过户次数  	可选参数
	 * @date: 2017年12月20日 上午10:33:16
	 * @return:Map<String,Object>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getUsedCarPriceAnalysis(Map<String, Object> reqParam,List<DictionaryDetail> dictionaryDetails){
		Map<String, Object> result = new HashMap<>(); //返回map
		try {
			String interior = "";
			String surface = "";
			String workState = "";
			String remark1 = null;
			if(StringUtil.isNotEmpty(reqParam.get("evaluationGrade"))){
				interior = reqParam.get("evaluationGrade").toString();
				surface = reqParam.get("evaluationGrade").toString();
				workState = reqParam.get("evaluationGrade").toString();
			}else{
				if (dictionaryDetails != null && !dictionaryDetails.isEmpty()) {
					for (DictionaryDetail dictionaryDetail : dictionaryDetails) {
						remark1 = dictionaryDetail.getRemark1();
						if ("interior".equals(remark1)) {
							interior = dictionaryDetail.getDictValue();
						}else if ("surface".equals(remark1)) {
							surface = dictionaryDetail.getDictValue();
						}else if ("work_state".equals(remark1)) {
							workState = dictionaryDetail.getDictValue();
						}
					}
				}
			}
			//评价等级为中,或良,300车查询接口参数,内饰,颜色,漆面传相应的等级,默认为中
			if(StringUtil.isNotEmpty(reqParam.get("assessmentLevel"))){
				if ("fine".equals(reqParam.get("assessmentLevel"))){
					interior="良";
					surface="良";
					workState="良";
				}else{
					interior="中";
					surface="中";
					workState="中";
				}
			}
			BigDecimal mile = BigDecimal.valueOf(Double.valueOf(reqParam.get("mile").toString())).divide(BigDecimal.valueOf(10000)).setScale(2, BigDecimal.ROUND_DOWN);
			StringBuffer sb = new StringBuffer();
			sb.append("modelId=").append(reqParam.get("modelId"))
			.append("&regDate=").append(reqParam.get("regDate"))
			.append("&mile=").append(mile.toString())
			.append("&zone=").append(reqParam.get("zone"))
			.append("&token=").append(CHE300_TOKEN)
			.append("&color=").append(reqParam.get("color"))
			.append("&interior=").append(interior)
			.append("&surface=").append(surface)
			.append("&work_state=").append(workState);
			if(StringUtil.isNotEmpty(reqParam.get("transfer_times"))) {
				sb.append("&transfer_times=").append(reqParam.get("transfer_times"));
			}
			if (StringUtil.isNotEmpty(reqParam.get("makeDate"))) {
				sb.append("&makeDate=").append(reqParam.get("makeDate"));
			}
			String url = CHE300_GETUSEDCARPRICEANALYSIS_URL + "?"+sb.toString();
			logger.info("subject:{},url:{}","车300-定价接口 请求参数",url);
			String responseParam = UrlHttpUtil.doGet(url);
			result = JsonUtil.jsonToMap(responseParam);
			logger.info("subject:{},result:{}","车300-定价接口 响应参数",result);
			result.put("status", result.get("status"));
			if (Integer.valueOf(result.get("status").toString()) == 0) {
				result.put("error_msg", result.get("error_msg"));
				return result;
			}
			result = (Map<String, Object>) result.get("eval_prices");
		} catch (Exception e) {
			logger.error("subject:{},e:{}", "车300-定价接口查询异常",e);
		}
		return result;
	}
}
