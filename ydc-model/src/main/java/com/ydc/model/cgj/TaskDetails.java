package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时器详情表
 */
public class TaskDetails implements Serializable {
    private static final long serialVersionUID = -7016968727185408776L;
    /**
     * t_task_details.id
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    private Integer id;

    /**
     * t_task_details.task_id (任务ID)
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    private String taskId;

    /**
     * t_task_details.task_detail (任务描述)
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    private String taskDetail;

    /**
     * t_task_details.obj_address (任务地址)
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    private String objAddress;

    /**
     * t_task_details.task_cron (任务cron表达式)
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    private String taskCron;

    /**
     * t_task_details.run_status (运行状态（1：运行；0：停止）)
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    private String runStatus;

    /**
     * t_task_details.status (状态（1：正常；0：禁用）)
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    private String status;

    /**
     * t_task_details.create_time (创建时间)
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    private Date createTime;

    /**
     * t_task_details.create_by (创建人)
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    private Integer createBy;

    /**
     * t_task_details.update_time (修改时间)
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    private Date updateTime;

    /**
     * t_task_details.update_by (修改人)
     * @ibatorgenerated 2018-09-10 17:38:01
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskDetail() {
        return taskDetail;
    }

    public void setTaskDetail(String taskDetail) {
        this.taskDetail = taskDetail;
    }

    public String getObjAddress() {
        return objAddress;
    }

    public void setObjAddress(String objAddress) {
        this.objAddress = objAddress;
    }

    public String getTaskCron() {
        return taskCron;
    }

    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron;
    }

    public String getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(String runStatus) {
        this.runStatus = runStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}