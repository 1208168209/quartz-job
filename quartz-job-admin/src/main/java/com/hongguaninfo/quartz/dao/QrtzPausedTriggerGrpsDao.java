package com.hongguaninfo.quartz.dao;

import com.hongguaninfo.hgdf.core.base.BaseDao;
import com.hongguaninfo.quartz.entity.QrtzPausedTriggerGrps;
import com.hongguaninfo.quartz.mapper.QrtzPausedTriggerGrpsMapper;
import org.springframework.stereotype.Repository;

 /**
 * : qrtz_paused_trigger_grps
 * dao 层
 * @author henry
 */

@SuppressWarnings("unchecked")
@Repository("qrtzPausedTriggerGrpsDao")
public class QrtzPausedTriggerGrpsDao extends BaseDao<QrtzPausedTriggerGrps, QrtzPausedTriggerGrpsMapper, Integer> implements
		QrtzPausedTriggerGrpsMapper{

	@Override
	public String getMapperNamesapce() {
		return QrtzPausedTriggerGrpsMapper.class.getName().toString();
	}


}