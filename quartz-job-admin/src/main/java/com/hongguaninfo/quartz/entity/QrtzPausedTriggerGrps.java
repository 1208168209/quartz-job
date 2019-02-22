package com.hongguaninfo.quartz.entity;
import java.util.Date;
import com.hongguaninfo.hgdf.core.base.BaseEntity;

 /**
 * : qrtz_paused_trigger_grps
 * entity 层
 * @author henry
 */

public class QrtzPausedTriggerGrps extends BaseEntity{
	//SCHED_NAME : 
	private String schedName;

	//TRIGGER_GROUP : 
	private String triggerGroup;


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

}
