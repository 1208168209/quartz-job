package com.hongguaninfo.quartz.dao;

import com.hongguaninfo.hgdf.core.base.BaseDao;
import com.hongguaninfo.quartz.entity.JobAndTrigger;
import com.hongguaninfo.quartz.entity.QrtzJobDetails;
import com.hongguaninfo.quartz.mapper.QrtzJobDetailsMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * : qrtz_job_details
 * dao 层
 * @author henry
 */

@SuppressWarnings("unchecked")
@Repository("qrtzJobDetailsDao")
public class QrtzJobDetailsDao extends BaseDao<QrtzJobDetails, QrtzJobDetailsMapper, Integer> implements
		QrtzJobDetailsMapper{

	@Override
	public String getMapperNamesapce() {
		return QrtzJobDetailsMapper.class.getName().toString();
	}


	 public List<JobAndTrigger> getJobAndTriggerList(JobAndTrigger jobAndTrigger) {
		 return getSqlSession().selectList(getMapperNamesapce() + ".getJobAndTriggerList", jobAndTrigger);
	 }
 }