package com.hongguaninfo.quartz.dao;

import com.hongguaninfo.hgdf.core.base.BaseDao;
import com.hongguaninfo.quartz.entity.QrtzFiredTriggers;
import com.hongguaninfo.quartz.mapper.QrtzFiredTriggersMapper;
import org.springframework.stereotype.Repository;

 /**
 * : qrtz_fired_triggers
 * dao 层
 * @author henry
 */

@SuppressWarnings("unchecked")
@Repository("qrtzFiredTriggersDao")
public class QrtzFiredTriggersDao extends BaseDao<QrtzFiredTriggers, QrtzFiredTriggersMapper, Integer> implements
		QrtzFiredTriggersMapper{

	@Override
	public String getMapperNamesapce() {
		return QrtzFiredTriggersMapper.class.getName().toString();
	}


}