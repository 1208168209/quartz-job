package com.hongguaninfo.quartz.dao;

import com.hongguaninfo.hgdf.core.base.BaseDao;
import com.hongguaninfo.quartz.entity.QrtzLocks;
import com.hongguaninfo.quartz.mapper.QrtzLocksMapper;
import org.springframework.stereotype.Repository;

 /**
 * : qrtz_locks
 * dao 层
 * @author henry
 */

@SuppressWarnings("unchecked")
@Repository("qrtzLocksDao")
public class QrtzLocksDao extends BaseDao<QrtzLocks, QrtzLocksMapper, Integer> implements
		QrtzLocksMapper{

	@Override
	public String getMapperNamesapce() {
		return QrtzLocksMapper.class.getName().toString();
	}


}