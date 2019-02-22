package com.hongguaninfo.hgdf.core.utils;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @ClassName: DateUtil
 * @Description: 日期工具类
 * @author henry
 * @date 2014-2-10 下午6:39:01
 * 
 */
public class DateUtil {

    private static final Log LOG = LogFactory.getLog(DateUtil.class);

    private static final String DATEPATTERN = "yyyy-MM-dd";

    private static final String DATEPATTERN_YYYYMMdd = "yyyyMMdd";

    private static final String DATETIMEPATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String TIMEPATTERN = "HH:mm";

    private final static DateFormat FORMAT_YYYYMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

    public final static DateFormat FORMAT_HHMMSS = new SimpleDateFormat("HHmmss");

    public final static DateFormat FORMAT_HH = new SimpleDateFormat("HH");

    /**
     * Return 缺省的日期格式 (yyyy/MM/dd)
     * 
     * @return 在页面中显示的日期格式
     */
    public static String getDATEPATTERN() {
        return DATEPATTERN;
    }

    /**
     * 根据日期格式，返回日期按DATEPATTERN格式转换后的字符串
     * 
     * @param aDate
     *            日期对象
     * @return 格式化后的日期的页面显示字符串
     */
    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(DATEPATTERN);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * 根据日期格式，返回日期按DATEPATTERN格式转换后的字符串
     * 
     * @param aDate
     *            日期对象
     * @return 格式化后的日期的页面显示字符串
     */
    public static final String getDateTime(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(DATETIMEPATTERN);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * 按照日期格式，将字符串解析为日期对象
     * 
     * @param aMask
     *            输入字符串的格式
     * @param strDate
     *            一个按aMask格式排列的日期的字符串描述
     * @return Date 对象
     * @see SimpleDateFormat
     */
    public static final Date convertStringToDate(String aMask, String strDate) {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        if (LOG.isDebugEnabled()) {
            LOG.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            LOG.error("ParseException: " + pe);
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format: yyyy/MM/dd HH:MM
     * a
     *
     * @param theTime
     *            the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(TIMEPATTERN, theTime);
    }

    /**
     * This method returns the current date in the format: yyyy-MM-dd
     *
     * @return the current date
     * @throws ParseException
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(DATEPATTERN);

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * This method generates a string representation of a date's date/time in
     * the format you specify on input
     *
     * @param aMask
     *            the date pattern the string is in
     * @param aDate
     *            a date object
     * @return a formatted string representation of the date
     *
     * @see SimpleDateFormat
     */
    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            LOG.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * 根据日期格式，返回日期按DATEPATTERN格式转换后的字符串
     * 
     * @param aDate
     * @return
     */
    public static final String convertDateToString(Date aDate) {
        return getDateTime(DATEPATTERN, aDate);
    }

    /**
     * 根据日期格式，返回日期按DATETIMEPATTERN格式转换后的字符串
     * 
     * @param aDate
     * @return
     */
    public static final String convertDateTimeToString(Date aDate) {
        return getDateTime(DATETIMEPATTERN, aDate);
    }

    /**
     * 按照日期格式，将字符串解析为日期对象
     * 
     * @param strDate
     *            (格式 yyyyMMdd)
     * @return
     * 
     * @throws ParseException
     */
    public static Date convertYYYYMMDDToDate(String strDate) {
        Date aDate = null;
        if (LOG.isDebugEnabled()) {
            LOG.debug("converting date with pattern: " + DATEPATTERN_YYYYMMdd);
        }
        aDate = convertStringToDate(DATEPATTERN_YYYYMMdd, strDate);
        return aDate;
    }

    /**
     * 按照日期格式，将字符串解析为日期对象
     * 
     * @param strDate
     *            (格式 yyyy-MM-dd)
     * @return
     * 
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate) {
        Date aDate = null;

        if (LOG.isDebugEnabled()) {
            LOG.debug("converting date with pattern: " + DATEPATTERN);
        }

        aDate = convertStringToDate(DATEPATTERN, strDate);

        return aDate;
    }

    /**
     * 按照日期格式，将字符串解析为日期对象
     * 
     * @param strDate
     *            (格式 yyyy-MM-dd HH:mm:ss)
     * @return
     * 
     * @throws ParseException
     */
    public static Date convertTimeStringToDate(String strDate) {
        Date aDate = null;

        if (LOG.isDebugEnabled()) {
            LOG.debug("converting date with pattern: " + DATETIMEPATTERN);
        }

        aDate = convertStringToDate(DATETIMEPATTERN, strDate);

        return aDate;
    }

    /**
     * 时间相加
     * 
     * @param date
     * @param day
     * @return
     */
    public static Date dateAdd(Date date, int day) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    /**
     * 获取两个日期之间的天数
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static long dateDiffer(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / (1000 * 3600 * 24);
    }

    /**
     * 得到两个日期之间的天数差，包括开始和结束日期(如：beginCalender=2007-10-01，endCalendar=2007-10-20
     * 结果为：20)
     * 
     * @param beginCalender
     *            开始日期(小的)
     * @param endCalendar
     *            结束日期(大的)
     * @return
     */
    public static Long getDifferenceDays(Date beginDay, Date endDay) {
        Calendar beginCalender = Calendar.getInstance();
        beginCalender.setTime(beginDay);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDay);

        Long days = (endCalendar.getTimeInMillis() - beginCalender.getTimeInMillis()) / (24 * 60 * 60 * 1000);
        days = days + 1;
        return days;
    }

    public static Date getFirstDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date getLastDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 返回本月最后一天
     * 
     * @param date
     * @return
     */
    public static String getLastDayStr(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        DateFormat format = new SimpleDateFormat(DATEPATTERN_YYYYMMdd);
        return format.format(cal.getTime());
    }

    /**
     * 返回日期String格式 yyyyMMdd
     * 
     * @param date
     * @return
     */
    public static String getDateStr(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        DateFormat format = new SimpleDateFormat(DATEPATTERN_YYYYMMdd);
        return format.format(cal.getTime());
    }

    /**
     * 返回本月第一天
     * 
     * @param date
     *            日期
     * @param month
     *            0 当月 1下月 -1 上月 以此类推
     * @return
     */
    public static String getFirstDateStr(int month, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, month);
        DateFormat format = new SimpleDateFormat(DATEPATTERN_YYYYMMdd);
        return format.format(cal.getTime());
    }

    /**
     * 得到当前时间,格式为yyyyMMddHHmmss
     * 
     * @return
     */
    public static String getyyyyMMddHHmmssCurDate() {
        return FORMAT_YYYYMMddHHmmss.format(new Date());
    }

    /**
     * date转换成calendar，只精确到yyyyMMdd
     * 
     * @throws ParseException
     */
    public static Calendar getyyyyMMDDCalendar(Date date) throws ParseException {
        SimpleDateFormat dataFormat = new SimpleDateFormat(DATEPATTERN_YYYYMMdd);
        dataFormat.parse(dataFormat.format(date));
        return dataFormat.getCalendar();
    }

    /**
     * 获取日期毫秒数(精确到天)
     * 
     * @throws ParseException
     */
    public static long getyyyyMMDDTimeInMillis(Date date) throws ParseException {
        return getyyyyMMDDCalendar(date).getTimeInMillis();
    }

    /**
     * 获取日期的HHmmss
     * 
     * @param date
     * @return
     */
    public static long getFormatHHmmss(Date date) {
        return Long.valueOf(FORMAT_HHMMSS.format(date));
    }

    /**
     * 获取日期的HH
     * 
     * @param date
     * @return
     */
    public static long getFormatHH(Date date) {
        return Long.valueOf(FORMAT_HH.format(date));
    }

    /**
     * 时间是否在<code>days</code>内
     * 
     * @param old
     * @param days
     * @return
     */
    public static boolean isDaysInterval(Date old, int days) {
        Calendar now = Calendar.getInstance();
        return (now.getTimeInMillis() - old.getTime()) <= (1000L * 3600 * 24 * days);
    }

    /**
     * 得到当前日期后的N天的日期
     * 
     * @param days
     *            天数
     * @return
     */
    public static Date getBackDaysOfNow(int days) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(now.getTimeInMillis() + 1000L * 3600 * 24 * days);
        return now.getTime();
    }

    public static void main(String[] args) {
        LOG.info(getLastDayStr(new Date()));
        LOG.info(getFirstDateStr(-1, new Date()));
        LOG.info(getDateStr(new Date()));
    }

    public static Date getBackDaysOfDay(Date date, int days) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.setTimeInMillis(day.getTimeInMillis() + 1000L * 3600 * 24 * days);
        return day.getTime();
    }

    public static Date getBeginOfDay(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 返回两个日期之间的所有日期
     * @param dateFirst
     * @param dateSecond
     * @return
     */
    public static ArrayList<String> getBetweenDays(String dateFirst, String dateSecond) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> retList = new ArrayList<String>();
        try {
            Date dateOne = dateFormat.parse(dateFirst);
            Date dateTwo = dateFormat.parse(dateSecond);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateOne);

            while (calendar.getTime().before(dateTwo)) {
                retList.add(dateFormat.format(calendar.getTime()));
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        } catch (ParseException e) {
            LOG.error("ParseException: " + e);
        }
        return retList;
    }

    /**
     * 返回两个日期之间的所有日期
     * @param dateFirst
     * @param dateSecond
     * @return
     */
    public static ArrayList<Date> getBetweenDays(Date dateFirst, Date dateSecond) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Date> retList = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFirst);

        while (calendar.getTime().before(dateSecond)) {
            retList.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return retList;
    }
}
