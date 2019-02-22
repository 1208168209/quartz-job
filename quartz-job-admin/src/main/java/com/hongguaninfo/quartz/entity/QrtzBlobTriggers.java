package com.hongguaninfo.quartz.entity;
import java.util.Date;
import com.hongguaninfo.hgdf.core.base.BaseEntity;

 /**
 * : qrtz_blob_triggers
 * entity 层
 * @author henry
 */

public class QrtzBlobTriggers extends BaseEntity{
	//SCHED_NAME : 
	private String schedName;

	//TRIGGER_NAME : 
	private String triggerName;

	//TRIGGER_GROUP : 
	private String triggerGroup;

	//BLOB_DATA : 
	private Object blobData;


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
	 * BLOB_DATA
	 */
	public Object getBlobData () {
		return blobData;
	}

	/**
	 * BLOB_DATA
	 */
	public void setBlobData (Object blobData) {
		this.blobData = blobData;
	}

}
