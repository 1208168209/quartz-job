package com.hongguaninfo.quartz.entity;
import com.hongguaninfo.hgdf.core.base.BaseEntity;

 /**
 * : qrtz_locks
 * entity 层
 * @author henry
 */

public class QrtzLocks extends BaseEntity{
	//SCHED_NAME : 
	private String schedName;

	//LOCK_NAME : 
	private String lockName;


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
	 * LOCK_NAME
	 */
	public String getLockName () {
		return lockName;
	}

	/**
	 * LOCK_NAME
	 */
	public void setLockName (String lockName) {
		this.lockName = lockName;
	}

}
