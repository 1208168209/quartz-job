package com.hongguaninfo.quartz.dao;

import com.hongguaninfo.hgdf.core.base.BaseDao;
import com.hongguaninfo.quartz.entity.QrtzSchedulerState;
import com.hongguaninfo.quartz.mapper.QrtzSchedulerStateMapper;
import org.springframework.stereotype.Repository;

 /**
 * : qrtz_scheduler_state
 * dao 层
 * @author henry
 */

@SuppressWarnings("unchecked")
@Repository("qrtzSchedulerStateDao")
public class QrtzSchedulerStateDao extends BaseDao<QrtzSchedulerState, QrtzSchedulerStateMapper, Integer> implements
		QrtzSchedulerStateMapper{

	@Override
	public String getMapperNamesapce() {
		return QrtzSchedulerStateMapper.class.getName().toString();
	}


}