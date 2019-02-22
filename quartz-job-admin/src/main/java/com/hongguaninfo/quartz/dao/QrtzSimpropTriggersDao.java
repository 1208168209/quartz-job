package com.hongguaninfo.quartz.dao;

import com.hongguaninfo.hgdf.core.base.BaseDao;
import com.hongguaninfo.quartz.entity.QrtzSimpropTriggers;
import com.hongguaninfo.quartz.mapper.QrtzSimpropTriggersMapper;
import org.springframework.stereotype.Repository;

 /**
 * : qrtz_simprop_triggers
 * dao 层
 * @author henry
 */

@SuppressWarnings("unchecked")
@Repository("qrtzSimpropTriggersDao")
public class QrtzSimpropTriggersDao extends BaseDao<QrtzSimpropTriggers, QrtzSimpropTriggersMapper, Integer> implements
		QrtzSimpropTriggersMapper{

	@Override
	public String getMapperNamesapce() {
		return QrtzSimpropTriggersMapper.class.getName().toString();
	}


}