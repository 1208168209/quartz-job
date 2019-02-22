package com.hongguaninfo.quartz.dao;

import com.hongguaninfo.hgdf.core.base.BaseDao;
import com.hongguaninfo.quartz.entity.QrtzSimpleTriggers;
import com.hongguaninfo.quartz.mapper.QrtzSimpleTriggersMapper;
import org.springframework.stereotype.Repository;

 /**
 * : qrtz_simple_triggers
 * dao 层
 * @author henry
 */

@SuppressWarnings("unchecked")
@Repository("qrtzSimpleTriggersDao")
public class QrtzSimpleTriggersDao extends BaseDao<QrtzSimpleTriggers, QrtzSimpleTriggersMapper, Integer> implements
		QrtzSimpleTriggersMapper{

	@Override
	public String getMapperNamesapce() {
		return QrtzSimpleTriggersMapper.class.getName().toString();
	}


}