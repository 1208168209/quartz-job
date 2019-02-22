package com.hongguaninfo.quartz.entity;

import com.hongguaninfo.hgdf.core.utils.DateUtil;
import com.hongguaninfo.hgdf.core.utils.StringUtil;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by chenqinglong on 2019/2/13 0013.
 */
public class JobAndTrigger {
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务所在组
     */
    private String jobGroup;
    /**
     * 运行模式：
     *      BEAN:java内置java类
     *      GLUE_JAVA:java源码
     */
    private String glueType;
    /**
     * 任务类名
     */
    private String jobClassName;
    /**
     * 任务类源码
     */
    private String glueJava;
    /**
     * 触发器名称
     */
    private String triggerName;
    /**
     * 触发器所在组
     */
    private String triggerGroup;
    private BigInteger repeatInterval;
    private BigInteger timesTriggered;
    /**
     * 表达式
     */
    private String cronExpression;
    /**
     * 时区
     */
    private String timeZoneId;
    private String description;

    /**
     * 状态
     */
    private String triggerState;

    /**
     * 创建时间
     */
    private String startTime;

    /**
     * 上次执行时间
     */
    private String prevFireTime;

    /**
     * 下次执行时间
     */
    private String nextFireTime;

    /**
     * 任务名称：查询
     */
    private String jobNameQuery;
    /**
     * 任务所在组：查询
     */
    private String jobGroupQuery;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jOB_NAME) {
        jobName = jOB_NAME;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jOB_GROUP) {
        jobGroup = jOB_GROUP;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jOB_CLASS_NAME) {
        jobClassName = jOB_CLASS_NAME;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String tRIGGER_NAME) {
        triggerName = tRIGGER_NAME;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String tRIGGER_GROUP) {
        triggerGroup = tRIGGER_GROUP;
    }

    public BigInteger getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(BigInteger rEPEAT_INTERVAL) {
        repeatInterval = rEPEAT_INTERVAL;
    }

    public BigInteger getTimesTriggered() {
        return timesTriggered;
    }

    public void setTimesTriggered(BigInteger tIMES_TRIGGERED) {
        timesTriggered = tIMES_TRIGGERED;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cRON_EXPRESSION) {
        cronExpression = cRON_EXPRESSION;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String tIME_ZONE_ID) {
        timeZoneId = tIME_ZONE_ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTriggerState() {
        return triggerState;
    }

    public void setTriggerState(String triggerState) {
        this.triggerState = triggerState;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        if (!StringUtil.isEmpty(startTime)){
            this.startTime = DateUtil.convertDateTimeToString(new Date(Long.parseLong(startTime)));
        }else{
            this.startTime = startTime;
        }
    }

    public String getPrevFireTime() {
        return prevFireTime;
    }

    public void setPrevFireTime(String prevFireTime) {
        if (!StringUtil.isEmpty(prevFireTime)){
            this.prevFireTime = DateUtil.convertDateTimeToString(new Date(Long.parseLong(prevFireTime)));
        }else{
            this.prevFireTime = prevFireTime;
        }
    }

    public String getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(String nextFireTime) {
        if (!StringUtil.isEmpty(nextFireTime)){
            this.nextFireTime = DateUtil.convertDateTimeToString(new Date(Long.parseLong(nextFireTime)));
        }else{
            this.nextFireTime = nextFireTime;
        }
    }

    public String getJobNameQuery() {
        return jobNameQuery;
    }

    public void setJobNameQuery(String jobNameQuery) {
        this.jobNameQuery = jobNameQuery;
    }

    public String getJobGroupQuery() {
        return jobGroupQuery;
    }

    public void setJobGroupQuery(String jobGroupQuery) {
        this.jobGroupQuery = jobGroupQuery;
    }

    public String getGlueJava() {
        return glueJava;
    }

    public void setGlueJava(String glueJava) {
        this.glueJava = glueJava;
    }

    public String getGlueType() {
        return glueType;
    }

    public void setGlueType(String glueType) {
        this.glueType = glueType;
    }
}
