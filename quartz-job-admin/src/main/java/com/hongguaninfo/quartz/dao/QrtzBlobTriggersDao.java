package com.hongguaninfo.quartz.dao;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.core.base.BaseDao;
import com.hongguaninfo.quartz.entity.QrtzBlobTriggers;
import com.hongguaninfo.quartz.mapper.QrtzBlobTriggersMapper;

 /**
 * : qrtz_blob_triggers
 * dao 层
 * @author henry
 */

@SuppressWarnings("unchecked")
@Repository("qrtzBlobTriggersDao")
public class QrtzBlobTriggersDao extends BaseDao<QrtzBlobTriggers, QrtzBlobTriggersMapper, Integer> implements
		QrtzBlobTriggersMapper{

	@Override
	public String getMapperNamesapce() {
		return QrtzBlobTriggersMapper.class.getName().toString();
	}


}