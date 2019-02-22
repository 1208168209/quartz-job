package com.hongguaninfo.quartz.dao;

import com.hongguaninfo.hgdf.core.base.BaseDao;
import com.hongguaninfo.quartz.entity.QrtzTriggers;
import com.hongguaninfo.quartz.mapper.QrtzTriggersMapper;
import org.springframework.stereotype.Repository;

 /**
 * : qrtz_triggers
 * dao 层
 * @author henry
 */

@SuppressWarnings("unchecked")
@Repository("qrtzTriggersDao")
public class QrtzTriggersDao extends BaseDao<QrtzTriggers, QrtzTriggersMapper, Integer> implements
		QrtzTriggersMapper{

	@Override
	public String getMapperNamesapce() {
		return QrtzTriggersMapper.class.getName().toString();
	}


}