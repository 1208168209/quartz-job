package com.hongguaninfo.quartz.entity;
import java.util.Date;
import com.hongguaninfo.hgdf.core.base.BaseEntity;

 /**
 * : qrtz_calendars
 * entity 层
 * @author henry
 */

public class QrtzCalendars extends BaseEntity{
	//SCHED_NAME : 
	private String schedName;

	//CALENDAR_NAME : 
	private String calendarName;

	//CALENDAR : 
	private Object calendar;


	/**
	 * SCHED_NAME
	 */
	public String getSchedName () {
		return schedName;
	}

	/**
	 * SCHED_NAME
	 */
	public void setSchedName (String schedName) {
		this.schedName = schedName;
	}
	/**
	 * CALENDAR_NAME
	 */
	public String getCalendarName () {
		return calendarName;
	}

	/**
	 * CALENDAR_NAME
	 */
	public void setCalendarName (String calendarName) {
		this.calendarName = calendarName;
	}
	/**
	 * CALENDAR
	 */
	public Object getCalendar () {
		return calendar;
	}

	/**
	 * CALENDAR
	 */
	public void setCalendar (Object calendar) {
		this.calendar = calendar;
	}

}
