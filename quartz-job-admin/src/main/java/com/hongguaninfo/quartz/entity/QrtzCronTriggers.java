package com.hongguaninfo.quartz.entity;
import java.util.Date;
import com.hongguaninfo.hgdf.core.base.BaseEntity;

 /**
 * : qrtz_cron_triggers
 * entity 层
 * @author henry
 */

public class QrtzCronTriggers extends BaseEntity{
	//SCHED_NAME : 
	private String schedName;

	//TRIGGER_NAME : 
	private String triggerName;

	//TRIGGER_GROUP : 
	private String triggerGroup;

	//CRON_EXPRESSION : 
	private String cronExpression;

	//TIME_ZONE_ID : 
	private String timeZoneId;


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
	 * CRON_EXPRESSION
	 */
	public String getCronExpression () {
		return cronExpression;
	}

	/**
	 * CRON_EXPRESSION
	 */
	public void setCronExpression (String cronExpression) {
		this.cronExpression = cronExpression;
	}
	/**
	 * TIME_ZONE_ID
	 */
	public String getTimeZoneId () {
		return timeZoneId;
	}

	/**
	 * TIME_ZONE_ID
	 */
	public void setTimeZoneId (String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

}
