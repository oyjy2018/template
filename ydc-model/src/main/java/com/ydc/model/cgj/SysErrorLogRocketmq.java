package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

public class SysErrorLogRocketmq implements Serializable {
	private static final long serialVersionUID = 5266869740968821069L;
	/**
	 * t_sys_error_log_rocketmq.id
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private Integer id;
	/**
	 * t_sys_error_log_rocketmq.content (发送内容)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private String content;
	/**
	 * t_sys_error_log_rocketmq.topic
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private String topic;
	/**
	 * t_sys_error_log_rocketmq.tag
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private String tag;
	/**
	 * t_sys_error_log_rocketmq.delay_time_level (消息延时等级)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private Integer delayTimeLevel;
	/**
	 * t_sys_error_log_rocketmq.repetition_num (需要重复的次数)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private Integer repetitionNum;
	/**
	 * t_sys_error_log_rocketmq.send_num (已经发送次数)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private Integer sendNum;
	/**
	 * t_sys_error_log_rocketmq.status (状态 0：失败  1：成功)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private Integer status;
	/**
	 * t_sys_error_log_rocketmq.update_by (更新用户)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private Integer updateBy;
	/**
	 * t_sys_error_log_rocketmq.update_time (更新时间)
	 * @ibatorgenerated  2018-11-12 11:18:31
	 */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getDelayTimeLevel() {
		return delayTimeLevel;
	}

	public void setDelayTimeLevel(Integer delayTimeLevel) {
		this.delayTimeLevel = delayTimeLevel;
	}

	public Integer getRepetitionNum() {
		return repetitionNum;
	}

	public void setRepetitionNum(Integer repetitionNum) {
		this.repetitionNum = repetitionNum;
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
}