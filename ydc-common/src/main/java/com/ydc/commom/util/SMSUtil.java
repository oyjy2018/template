package com.ydc.commom.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;

/**
 * 
 * 短信接口
 * @author hejiangping
 * @date：2017年9月25日 下午14:47:59
 *
 */
public class SMSUtil {

	private static Logger logger = LogManager.getLogger(SMSUtil.class);
	
	// 一点开发者参数
	public static final String devId = "1769af197f6f425e8306dbff632a41ef";
	public static final String devKey = "c227f7f4ed194706a1ff7f1eb9326a3f";

	/**
	 * 一点租车短信签名
	 */
	public static final String YDZC_SMS_SIGN = "一点租车";

	/**
	 * 一点租车注册短信模板
	 */
	public static final String YDZC = "ydzc";

	/**
	 * 一点租车登录短信模板
	 */
	public static final String YDZC_LOGIN = "ydzclogin";


	/**
	 * 一点租车，有失效时间的验证码
	 */
	public static enum YDZCEnum{
		VALIDATE_CODE_REGISTER(5, "ydzc"), //企业注册短信
		VALIDATE_CODE_LOGIN(6, "ydzclogin"); //企业登录短信


		private static EnumSet<YDZCEnum> validateCodeEnum = EnumSet.allOf(YDZCEnum.class);
		private Integer validateType;
		private String validateCode;

		private YDZCEnum(Integer validateType, String validateCode){
			this.validateCode = validateCode;
			this.validateType = validateType;
		}

		public Integer getValidateType() {
			return validateType;
		}

		public String getValidateCode() {
			return validateCode;
		}

		public static String getValidateCodeByType(Integer validateType){
			Optional<String> optValidateCode =  validateCodeEnum.stream()
					.filter(tempValidateCode -> tempValidateCode.validateType.equals(validateType))
					.map(tempvalidateCode -> tempvalidateCode.validateCode)
					.findAny();
			return optValidateCode.orElse(null);
		}
	}

	// 转码
	public static String encode(String input) throws Exception {
		return URLEncoder.encode(input, "UTF-8");
	}

	// 计算 MD5 值
	public static String md5(String input) throws Exception {
		byte[] digest = MessageDigest.getInstance("MD5").digest(input.getBytes("UTF-8"));
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < digest.length; i++) {
			sb.append(String.format("%02x", Integer.valueOf(digest[i] & 0xFF)));
		}
		return sb.toString();
	}

	// 发起 POST 请求
	public static String post(String url, String data) throws Exception {

		// 打开连接
		HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setUseCaches(false);
		conn.connect();

		// 输出参数
		DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
		dos.writeBytes(data);
		dos.flush();
		dos.close();

		// 读取响应
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String line = br.readLine();
		br.close();

		// 关闭连接
		conn.disconnect();

		return line;

	}

	// 短信发送接口
	public static Map<String, Object> send(String mobilePhone,String code) throws Exception {

		// 参数
		String url = "http://www.xinxinke.com/api/send";
		String smsTemplateCode = "sms_smscode";
		String smsParam = "{\"code\":\"" + code + "\"}";
		String recNum = mobilePhone;
		String sign = md5(devId + devKey + recNum);

		// 拼接
		StringBuffer data = new StringBuffer();
		data.append("").append("dev_id").append("=").append(encode(devId));
		data.append("&").append("sign").append("=").append(encode(sign));
		data.append("&").append("sms_template_code").append("=").append(encode(smsTemplateCode));
		data.append("&").append("sms_param").append("=").append(encode(smsParam));
		data.append("&").append("rec_num").append("=").append(encode(recNum));
		
		// 请求
		String ret = post(url, data.toString());
		Map<String, Object> result = JsonUtil.jsonToMap(ret);
		LoggerUtil.info(logger, "短信发送接口:"+ret);
		return result;

	}
	
	/**
	 *  短信发送接口
	 * @param mobilePhone 手机号
	 * @param smsTemplateCode 短信模板代码
	 * @param smsParam	参数
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> send(String mobilePhone,String smsTemplateCode,String smsParam) throws Exception {
		return send(mobilePhone, smsTemplateCode, smsParam, null);
	}
	
	/**
	 *  短信发送接口
	 * @param mobilePhone 手机号
	 * @param smsTemplateCode 短信模板代码
	 * @param smsParam	参数
	 * @param smsSign 短信签名
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> send(String mobilePhone,String smsTemplateCode,String smsParam, String smsSign) throws Exception {
		LoggerUtil.info(logger, "subject, mobilePhone, smsSign,smsParam,smsTemplateCode", "发送信信短信", mobilePhone, smsSign,smsParam,smsTemplateCode);
		// 参数
		String url = "http://www.xinxinke.com/api/send";
		/*String smsTemplateCode = "sms_smscode";*/
		smsParam = "{\"code\":\"" + smsParam + "\"}";
		String recNum = mobilePhone;
		String sign = md5(devId + devKey + recNum);

		// 拼接
		StringBuffer data = new StringBuffer();
		data.append("").append("dev_id").append("=").append(encode(devId));
		data.append("&").append("sign").append("=").append(encode(sign));
		data.append("&").append("sms_template_code").append("=").append(encode(smsTemplateCode));
		data.append("&").append("sms_param").append("=").append(encode(smsParam));
		data.append("&").append("rec_num").append("=").append(encode(recNum));
		if(smsSign != null){
			data.append("&").append("sms_sign").append("=").append(encode(smsSign));
		}
		
		// 请求
		String ret = post(url, data.toString());
		Map<String, Object> result = JsonUtil.jsonToMap(ret);
		LoggerUtil.info(logger, "短信发送接口:"+ret);
		return result;

	}

	// 短信发送状态报告接口
	public static Map<String, Object> report() throws Exception {

		// 参数
		String url = "http://www.xinxinke.com/api/report";
		String sign = md5(devId + devKey);

		// 拼接
		StringBuffer data = new StringBuffer();
		data.append("").append("dev_id").append("=").append(encode(devId));
		data.append("&").append("sign").append("=").append(encode(sign));
		
		// 请求
		String ret = post(url, data.toString());
		Map<String, Object> result = JsonUtil.jsonToMap(ret);
		LoggerUtil.info(logger, ret);
		return result;

	}

	// 短信接收接口
	public static Map<String, Object> receive() throws Exception {

		// 参数
		String url = "http://www.xinxinke.com/api/receive";
		String sign = md5(devId + devKey);

		// 拼接
		StringBuffer data = new StringBuffer();
		data.append("").append("dev_id").append("=").append(encode(devId));
		data.append("&").append("sign").append("=").append(encode(sign));

		// 请求
		String ret = post(url, data.toString());
		Map<String, Object> result = JsonUtil.jsonToMap(ret);
		LoggerUtil.info(logger, ret);
		return result;

	}

	// 短信模板接口
	public static Map<String, Object> template() throws Exception {

		// 参数
		String url = "http://www.xinxinke.com/api/template";
		String sign = md5(devId + devKey);
		String action = "query";

		// 拼接
		StringBuffer data = new StringBuffer();
		data.append("").append("dev_id").append("=").append(encode(devId));
		data.append("&").append("sign").append("=").append(encode(sign));
		data.append("&").append("action").append("=").append(encode(action));

		// 请求
		String ret = post(url, data.toString());
		Map<String, Object> result = JsonUtil.jsonToMap(ret);
		LoggerUtil.info(logger, ret);
		return result;

	}

	// 帐户信息查询接口
	public static Map<String, Object> account() throws Exception {

		// 参数
		String url = "http://www.xinxinke.com/api/account";
		String sign = md5(devId + devKey);

		// 拼接
		StringBuffer data = new StringBuffer();
		data.append("").append("dev_id").append("=").append(encode(devId));
		data.append("&").append("sign").append("=").append(encode(sign));

		// 请求
		String ret = post(url, data.toString());
		Map<String, Object> result = JsonUtil.jsonToMap(ret);
		LoggerUtil.info(logger, ret);
		return result;

	}

	public static void main(String[] args) throws Exception {
		SMSUtil t = new SMSUtil();
		String code = NumberUtil.getNumberCheckCode(6);
		t.send("13243856965", code);
		// t.report();
		// t.receive();
		// t.template();
		t.account();
	}

}