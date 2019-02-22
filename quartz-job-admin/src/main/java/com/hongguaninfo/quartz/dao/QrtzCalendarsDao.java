package com.hongguaninfo.quartz.dao;

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.core.base.BaseDao;
import com.hongguaninfo.quartz.entity.QrtzCalendars;
import com.hongguaninfo.quartz.mapper.QrtzCalendarsMapper;

 /**
 * : qrtz_calendars
 * dao 层
 * @author henry
 */

@SuppressWarnings("unchecked")
@Repository("qrtzCalendarsDao")
public class QrtzCalendarsDao extends BaseDao<QrtzCalendars, QrtzCalendarsMapper, Integer> implements
		QrtzCalendarsMapper{

	@Override
	public String getMapperNamesapce() {
		return QrtzCalendarsMapper.class.getName().toString();
	}


}