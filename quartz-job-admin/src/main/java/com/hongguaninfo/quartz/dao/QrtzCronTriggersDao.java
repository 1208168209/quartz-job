package com.hongguaninfo.quartz.dao;

import com.hongguaninfo.hgdf.core.base.BaseDao;
import com.hongguaninfo.quartz.entity.QrtzCronTriggers;
import com.hongguaninfo.quartz.mapper.QrtzCronTriggersMapper;
import org.springframework.stereotype.Repository;

 /**
 * : qrtz_cron_triggers
 * dao 层
 * @author henry
 */

@SuppressWarnings("unchecked")
@Repository("qrtzCronTriggersDao")
public class QrtzCronTriggersDao extends BaseDao<QrtzCronTriggers, QrtzCronTriggersMapper, Integer> implements
		QrtzCronTriggersMapper{

	@Override
	public String getMapperNamesapce() {
		return QrtzCronTriggersMapper.class.getName().toString();
	}


}