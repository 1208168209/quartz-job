package com.hongguaninfo.quartz.entity;
import java.util.Date;
import com.hongguaninfo.hgdf.core.base.BaseEntity;

 /**
 * : qrtz_scheduler_state
 * entity 层
 * @author henry
 */

public class QrtzSchedulerState extends BaseEntity{
	//SCHED_NAME : 
	private String schedName;

	//INSTANCE_NAME : 
	private String instanceName;

	//LAST_CHECKIN_TIME : 
	private String lastCheckinTime;

	//CHECKIN_INTERVAL : 
	private String checkinInterval;


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
	 * LAST_CHECKIN_TIME
	 */
	public String getLastCheckinTime () {
		return lastCheckinTime;
	}

	/**
	 * LAST_CHECKIN_TIME
	 */
	public void setLastCheckinTime (String lastCheckinTime) {
		this.lastCheckinTime = lastCheckinTime;
	}
	/**
	 * CHECKIN_INTERVAL
	 */
	public String getCheckinInterval () {
		return checkinInterval;
	}

	/**
	 * CHECKIN_INTERVAL
	 */
	public void setCheckinInterval (String checkinInterval) {
		this.checkinInterval = checkinInterval;
	}

}
