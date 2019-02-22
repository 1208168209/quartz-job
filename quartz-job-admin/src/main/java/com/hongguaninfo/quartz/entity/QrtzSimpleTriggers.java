package com.hongguaninfo.quartz.entity;
import java.util.Date;
import com.hongguaninfo.hgdf.core.base.BaseEntity;

 /**
 * : qrtz_simple_triggers
 * entity 层
 * @author henry
 */

public class QrtzSimpleTriggers extends BaseEntity{
	//SCHED_NAME : 
	private String schedName;

	//TRIGGER_NAME : 
	private String triggerName;

	//TRIGGER_GROUP : 
	private String triggerGroup;

	//REPEAT_COUNT : 
	private String repeatCount;

	//REPEAT_INTERVAL : 
	private String repeatInterval;

	//TIMES_TRIGGERED : 
	private String timesTriggered;


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
	 * REPEAT_COUNT
	 */
	public String getRepeatCount () {
		return repeatCount;
	}

	/**
	 * REPEAT_COUNT
	 */
	public void setRepeatCount (String repeatCount) {
		this.repeatCount = repeatCount;
	}
	/**
	 * REPEAT_INTERVAL
	 */
	public String getRepeatInterval () {
		return repeatInterval;
	}

	/**
	 * REPEAT_INTERVAL
	 */
	public void setRepeatInterval (String repeatInterval) {
		this.repeatInterval = repeatInterval;
	}
	/**
	 * TIMES_TRIGGERED
	 */
	public String getTimesTriggered () {
		return timesTriggered;
	}

	/**
	 * TIMES_TRIGGERED
	 */
	public void setTimesTriggered (String timesTriggered) {
		this.timesTriggered = timesTriggered;
	}

}
