package com.ydc.model.cgj;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

public class SysErrorLogHttp implements Serializable {
	private static final long serialVersionUID = -3950080443248393474L;
	/**
	 * t_sys_error_log_http.id
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private Integer id;
	/**
	 * t_sys_error_log_http.url (url)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private String url;
	/**
	 * t_sys_error_log_http.request_param (请求参数)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private String requestParam;
	/**
	 * t_sys_error_log_http.param_type (请求参数类型  json 或者 map)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private String paramType;
	/**
	 * t_sys_error_log_http.request_method (请求方法)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private String requestMethod;
	/**
	 * t_sys_error_log_http.repetition_num (需要重复的次数)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private Integer repetitionNum;
	/**
	 * t_sys_error_log_http.header_map (请求头)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private String headerMap;
	/**
	 * t_sys_error_log_http.call_back_class_name (回调类名)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private String callBackClassName;

    /**
     * 回调地址
     */
	private String callBackUrl;
	/**
	 * t_sys_error_log_http.send_num (已经发送次数)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private Integer sendNum;
	/**
	 * t_sys_error_log_http.status (状态 0：失败  1：成功)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private Integer status;
	/**
	 * t_sys_error_log_http.update_by (更新用户)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private Integer updateBy;
	/**
	 * t_sys_error_log_http.update_time (更新时间)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public Integer getRepetitionNum() {
		return repetitionNum;
	}

	public void setRepetitionNum(Integer repetitionNum) {
		this.repetitionNum = repetitionNum;
	}

	public String getHeaderMap() {
		return headerMap;
	}

	public void setHeaderMap(String headerMap) {
		this.headerMap = headerMap;
	}

	public String getCallBackClassName() {
		return callBackClassName;
	}

	public void setCallBackClassName(String callBackClassName) {
		this.callBackClassName = callBackClassName;
	}

	public Integer getSendNum() {
		return sendNum;
	}

	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    public static SysErrorLogHttp getErrorLogHttp(){
		SysErrorLogHttp sysErrorLogHttp = new SysErrorLogHttp();
		sysErrorLogHttp.setSendNum(0);
		sysErrorLogHttp.setStatus(1);
		sysErrorLogHttp.setUpdateBy(1);
		sysErrorLogHttp.setUpdateTime(new Date());
		return sysErrorLogHttp;
	}

	//对B端的调用异常重试对象
	public static SysErrorLogHttp getBPartyErrorLogHttp(String url, String paramMapStr, String callBackUrl ){
		SysErrorLogHttp sysErrorLogHttp = getErrorLogHttp();
		sysErrorLogHttp.setRequestMethod("post");
		sysErrorLogHttp.setParamType("map");
		sysErrorLogHttp.setRepetitionNum(3);
		sysErrorLogHttp.setUrl(url);
		sysErrorLogHttp.setRequestParam(paramMapStr);
//		sysErrorLogHttp.setCallBackClassName(callBackStr);
        sysErrorLogHttp.setCallBackUrl(callBackUrl);
		return sysErrorLogHttp;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}