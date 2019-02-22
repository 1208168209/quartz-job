package com.hongguaninfo.quartz.entity;
import java.util.Date;
import com.hongguaninfo.hgdf.core.base.BaseEntity;

 /**
 * : qrtz_job_details
 * entity 层
 * @author henry
 */

public class QrtzJobDetails extends BaseEntity{
	//SCHED_NAME : 
	private String schedName;

	//JOB_NAME : 
	private String jobName;

	//JOB_GROUP : 
	private String jobGroup;

	//DESCRIPTION : 
	private String description;

	//JOB_CLASS_NAME : 
	private String jobClassName;

	//IS_DURABLE : 
	private String isDurable;

	//IS_NONCONCURRENT : 
	private String isNonconcurrent;

	//IS_UPDATE_DATA : 
	private String isUpdateData;

	//REQUESTS_RECOVERY : 
	private String requestsRecovery;

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
	 * JOB_CLASS_NAME
	 */
	public String getJobClassName () {
		return jobClassName;
	}

	/**
	 * JOB_CLASS_NAME
	 */
	public void setJobClassName (String jobClassName) {
		this.jobClassName = jobClassName;
	}
	/**
	 * IS_DURABLE
	 */
	public String getIsDurable () {
		return isDurable;
	}

	/**
	 * IS_DURABLE
	 */
	public void setIsDurable (String isDurable) {
		this.isDurable = isDurable;
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
	 * IS_UPDATE_DATA
	 */
	public String getIsUpdateData () {
		return isUpdateData;
	}

	/**
	 * IS_UPDATE_DATA
	 */
	public void setIsUpdateData (String isUpdateData) {
		this.isUpdateData = isUpdateData;
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
