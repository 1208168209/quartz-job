package com.hongguaninfo.quartz.entity;
import java.util.Date;
import com.hongguaninfo.hgdf.core.base.BaseEntity;

 /**
 * : qrtz_fired_triggers
 * entity 层
 * @author henry
 */

public class QrtzFiredTriggers extends BaseEntity{
	//SCHED_NAME : 
	private String schedName;

	//ENTRY_ID : 
	private String entryId;

	//TRIGGER_NAME : 
	private String triggerName;

	//TRIGGER_GROUP : 
	private String triggerGroup;

	//INSTANCE_NAME : 
	private String instanceName;

	//FIRED_TIME : 
	private String firedTime;

	//SCHED_TIME : 
	private String schedTime;

	//PRIORITY : 
	private Integer priority;

	//STATE : 
	private String state;

	//JOB_NAME : 
	private String jobName;

	//JOB_GROUP : 
	private String jobGroup;

	//IS_NONCONCURRENT : 
	private String isNonconcurrent;

	//REQUESTS_RECOVERY : 
	private String requestsRecovery;


	/**
	 * SCHED_NAME
	 */
	public String getSchedName () {
		return schedName;
	}

	/**
	 * SCHED_NAME
	 */
	public void setSchedName (String schedName) {
		this.schedName = schedName;
	}
	/**
	 * ENTRY_ID
	 */
	public String getEntryId () {
		return entryId;
	}

	/**
	 * ENTRY_ID
	 */
	public void setEntryId (String entryId) {
		this.entryId = entryId;
	}
	/**
	 * TRIGGER_NAME
	 */
	public String getTriggerName () {
		return triggerName;
	}

	/**
	 * TRIGGER_NAME
	 */
	public void setTriggerName (String triggerName) {
		this.triggerName = triggerName;
	}
	/**
	 * TRIGGER_GROUP
	 */
	public String getTriggerGroup () {
		return triggerGroup;
	}

	/**
	 * TRIGGER_GROUP
	 */
	public void setTriggerGroup (String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}
	/**
	 * INSTANCE_NAME
	 */
	public String getInstanceName () {
		return instanceName;
	}

	/**
	 * INSTANCE_NAME
	 */
	public void setInstanceName (String instanceName) {
		this.instanceName = instanceName;
	}
	/**
	 * FIRED_TIME
	 */
	public String getFiredTime () {
		return firedTime;
	}

	/**
	 * FIRED_TIME
	 */
	public void setFiredTime (String firedTime) {
		this.firedTime = firedTime;
	}
	/**
	 * SCHED_TIME
	 */
	public String getSchedTime () {
		return schedTime;
	}

	/**
	 * SCHED_TIME
	 */
	public void setSchedTime (String schedTime) {
		this.schedTime = schedTime;
	}
	/**
	 * PRIORITY
	 */
	public Integer getPriority () {
		return priority;
	}

	/**
	 * PRIORITY
	 */
	public void setPriority (Integer priority) {
		this.priority = priority;
	}
	/**
	 * STATE
	 */
	public String getState () {
		return state;
	}

	/**
	 * STATE
	 */
	public void setState (String state) {
		this.state = state;
	}
	/**
	 * JOB_NAME
	 */
	public String getJobName () {
		return jobName;
	}

	/**
	 * JOB_NAME
	 */
	public void setJobName (String jobName) {
		this.jobName = jobName;
	}
	/**
	 * JOB_GROUP
	 */
	public String getJobGroup () {
		return jobGroup;
	}

	/**
	 * JOB_GROUP
	 */
	public void setJobGroup (String jobGroup) {
		this.jobGroup = jobGroup;
	}
	/**
	 * IS_NONCONCURRENT
	 */
	public String getIsNonconcurrent () {
		return isNonconcurrent;
	}

	/**
	 * IS_NONCONCURRENT
	 */
	public void setIsNonconcurrent (String isNonconcurrent) {
		this.isNonconcurrent = isNonconcurrent;
	}
	/**
	 * REQUESTS_RECOVERY
	 */
	public String getRequestsRecovery () {
		return requestsRecovery;
	}

	/**
	 * REQUESTS_RECOVERY
	 */
	public void setRequestsRecovery (String requestsRecovery) {
		this.requestsRecovery = requestsRecovery;
	}

}
