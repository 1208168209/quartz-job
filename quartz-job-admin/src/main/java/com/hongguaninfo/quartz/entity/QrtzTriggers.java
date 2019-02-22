package com.hongguaninfo.quartz.entity;
import java.util.Date;
import com.hongguaninfo.hgdf.core.base.BaseEntity;

 /**
 * : qrtz_triggers
 * entity 层
 * @author henry
 */

public class QrtzTriggers extends BaseEntity{
	//SCHED_NAME : 
	private String schedName;

	//TRIGGER_NAME : 
	private String triggerName;

	//TRIGGER_GROUP : 
	private String triggerGroup;

	//JOB_NAME : 
	private String jobName;

	//JOB_GROUP : 
	private String jobGroup;

	//DESCRIPTION : 
	private String description;

	//NEXT_FIRE_TIME : 
	private String nextFireTime;

	//PREV_FIRE_TIME : 
	private String prevFireTime;

	//PRIORITY : 
	private Integer priority;

	//TRIGGER_STATE : 
	private String triggerState;

	//TRIGGER_TYPE : 
	private String triggerType;

	//START_TIME : 
	private String startTime;

	//END_TIME : 
	private String endTime;

	//CALENDAR_NAME : 
	private String calendarName;

	//MISFIRE_INSTR : 
	private String misfireInstr;

	//JOB_DATA : 
	private Object jobData;


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
	 * DESCRIPTION
	 */
	public String getDescription () {
		return description;
	}

	/**
	 * DESCRIPTION
	 */
	public void setDescription (String description) {
		this.description = description;
	}
	/**
	 * NEXT_FIRE_TIME
	 */
	public String getNextFireTime () {
		return nextFireTime;
	}

	/**
	 * NEXT_FIRE_TIME
	 */
	public void setNextFireTime (String nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	/**
	 * PREV_FIRE_TIME
	 */
	public String getPrevFireTime () {
		return prevFireTime;
	}

	/**
	 * PREV_FIRE_TIME
	 */
	public void setPrevFireTime (String prevFireTime) {
		this.prevFireTime = prevFireTime;
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
	 * TRIGGER_STATE
	 */
	public String getTriggerState () {
		return triggerState;
	}

	/**
	 * TRIGGER_STATE
	 */
	public void setTriggerState (String triggerState) {
		this.triggerState = triggerState;
	}
	/**
	 * TRIGGER_TYPE
	 */
	public String getTriggerType () {
		return triggerType;
	}

	/**
	 * TRIGGER_TYPE
	 */
	public void setTriggerType (String triggerType) {
		this.triggerType = triggerType;
	}
	/**
	 * START_TIME
	 */
	public String getStartTime () {
		return startTime;
	}

	/**
	 * START_TIME
	 */
	public void setStartTime (String startTime) {
		this.startTime = startTime;
	}
	/**
	 * END_TIME
	 */
	public String getEndTime () {
		return endTime;
	}

	/**
	 * END_TIME
	 */
	public void setEndTime (String endTime) {
		this.endTime = endTime;
	}
	/**
	 * CALENDAR_NAME
	 */
	public String getCalendarName () {
		return calendarName;
	}

	/**
	 * CALENDAR_NAME
	 */
	public void setCalendarName (String calendarName) {
		this.calendarName = calendarName;
	}
	/**
	 * MISFIRE_INSTR
	 */
	public String getMisfireInstr () {
		return misfireInstr;
	}

	/**
	 * MISFIRE_INSTR
	 */
	public void setMisfireInstr (String misfireInstr) {
		this.misfireInstr = misfireInstr;
	}
	/**
	 * JOB_DATA
	 */
	public Object getJobData () {
		return jobData;
	}

	/**
	 * JOB_DATA
	 */
	public void setJobData (Object jobData) {
		this.jobData = jobData;
	}

}
