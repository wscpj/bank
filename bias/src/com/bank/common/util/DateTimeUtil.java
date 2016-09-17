package com.bank.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.bank.common.AppConstants;


public class DateTimeUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.FORMMAT_DATE_PATTERN, Locale.US);
    public final static String DATE_FORMATE_SHORTTIME = "yyyy-MM-dd";
    public final static String DATE_FORMATE_LONGTIME = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_FORMATE_WEEK = "EEEE";

    private static List<String> patterns = new ArrayList<String>();

    static {
        patterns.add("E MMM dd HH:mm:ss Z yyyy");
        patterns.add("yyyy-MM-dd HH:mm:ss");
        patterns.add("yyyy-MM-dd");
        patterns.add("yyyy-MM-dd HH:mm");
        patterns.add("yyyy/MM/dd HH:mm:ss");
        patterns.add("yyyy-MM-dd HH:mm:ss.S");
    }

    public static Date parse(String value) {
        if (value == null) {
            return null;
        }
        Date dateObj = null;
        Iterator<String> it = patterns.iterator();
        while (it.hasNext()) {
            try {
                String pattern = it.next();
                sdf.applyPattern(pattern);
                dateObj = sdf.parse(value);
                break;
            } catch (ParseException ex) {
                // ex.printStackTrace();
            }
        }
        return dateObj;

    }

    public static Date parse(String value, String pattern) {
        if (value == null) {
            return null;
        }
        sdf.applyPattern(pattern);
        Date date = null;
        try {
            date = sdf.parse(value);
        } catch (ParseException e) {
            parse(value);
        }
        return date;
    }

    public static String format(String pattern, Date date) {
        if (date == null) {
            return null;
        }
        sdf.applyPattern(pattern);
        String str = sdf.format(date);
        return str;
    }

    public static String showLongDate(String dateString) {
        return format(DATE_FORMATE_LONGTIME, parse(dateString));
    }

    public static String showShortDate(String dateString) {
        return format(DATE_FORMATE_SHORTTIME, parse(dateString));
    }

    public static String formatDateString(String dateString, String pattern) {
        Date formattedDate = null;
        formattedDate = parse(dateString);
        sdf.applyPattern(pattern);
        return sdf.format(formattedDate);
    }

    // How many days differ with this sunday
    private static Integer getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // Get today is which day of the week.
        Integer dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        return 1 - dayOfWeek;
    }

    private static Calendar setMinOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar = setMinOfDay(calendar);
        Date startOfDay = calendar.getTime();
        return startOfDay;
    }

    private static Calendar setMaxOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar;
    }

    /**
     * Get the start date of this week.(It is this Sunday date now.)
     */
    public static Date getStartDateOfWeek(Integer weeks) {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks * 7);

        currentDate = (GregorianCalendar) setMinOfDay(currentDate);
        Date startDate = currentDate.getTime();
        return startDate;
    }

    /**
     * Get the end date of this week.(It is this Saturday date now.)
     */
    public static Date getEndDateOfWeek(Integer weeks) {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks * 7 + 6);

        currentDate = (GregorianCalendar) setMaxOfDay(currentDate);
        Date endDate = currentDate.getTime();
        return endDate;
    }

    /**
     * Get the start date of this month.
     */
    public static Date getStartDateOfMonth(Integer months) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        if (months != 0) {
            calendar.add(Calendar.MONTH, months);
        }
        calendar = setMinOfDay(calendar);
        Date startOfMonth = calendar.getTime();
        return startOfMonth;
    }

    /**
     * Get the end date of this month.
     */
    public static Date getEndDateOfMonth(Integer months) {
        Calendar calendar = Calendar.getInstance();
        if (months == 0) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        if (months != 0) {
            calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, months);// minus one month
            calendar.set(Calendar.DATE, 1);// set date to the first day of this month
            calendar.roll(Calendar.DATE, -1);// roll date, set date to the end day of this month
        }
        calendar = setMaxOfDay(calendar);
        Date endOfMonth = calendar.getTime();
        return endOfMonth;
    }

    public static long minutesBetween(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long startTime = cal.getTimeInMillis();
        cal.setTime(endDate);
        long endTime = cal.getTimeInMillis();
        long betweenMinutes = (endTime - startTime) / (1000 * 60) + 1;
        return betweenMinutes;
    }

    public static long secondsBetween(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long startTime = cal.getTimeInMillis();
        cal.setTime(endDate);
        long endTime = cal.getTimeInMillis();
        long betweenSeconds = (endTime - startTime) / (1000) + 1;
        return betweenSeconds;
    }

    public static Integer daysBetween(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long startTime = cal.getTimeInMillis();
        cal.setTime(endDate);
        long endTime = cal.getTimeInMillis();
        long between_days = (endTime - startTime) / (1000 * 3600 * 24) + 1;

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static Integer daysBetweenExceptWeekend(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long startTime = cal.getTimeInMillis();
        cal.setTime(endDate);
        long endTime = cal.getTimeInMillis();
        long between_days = (endTime - startTime) / (1000 * 3600 * 24) + 1;

        Integer betweenDays = Integer.parseInt(String.valueOf(between_days));
        Integer tempDays = betweenDays;
        for (int i = 0; i < tempDays; i++) {
            Date date = DateTimeUtil.addDays(startDate, i);
            cal.setTime(date);
            Integer dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == 7 || dayOfWeek == 1) {
                // It is Saturday or Sunday
                betweenDays--;
            }
        }
        return betweenDays;
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        Date date2 = cal.getTime();
        return date2;
    }

    public static boolean hasChanged(Date oldOne, Date newOne) {
        if (oldOne == null) {
            if (newOne != null) {
                return true;
            }
            return false;
        }
        if (oldOne.equals(newOne)) {
            return false;
        }
        return true;
    }

    /**
     * Get week of Date
     */
    public static String getWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATE_WEEK);
        String week = sdf.format(date);
        return week;
    }

    /**
     * Get the week index of the date
     */
    public static Integer getWeekIndex(Date date) {
        Calendar dayTime = Calendar.getInstance();
        dayTime.setTime(date);
        return dayTime.get(Calendar.DAY_OF_WEEK);
    }

    /**
     *
     * @param timePoint
     *            example input 80
     * @return output 01:40
     */
    public static String convert(Integer timePoint) {
        if (timePoint == null) {
            return null;
        }
        StringBuffer time = new StringBuffer();
        int hour = timePoint / 3600;
        if (hour > 0) {
            if (hour < 10) {
                time.append("0").append(hour).append(":");
            } else {
                time.append(hour).append(":");
            }
        }
        int minute = timePoint / 60;
        if (minute >= 0) {
            if (minute < 10) {
                time.append("0").append(minute).append(":");
            } else {
                time.append(minute).append(":");
            }
        }
        int second = timePoint % 60;
        if (second >= 0) {
            if (second < 10) {
                time.append("0").append(second);
            } else {
                time.append(second);
            }
        }
        return time.toString();
    }

    public static String convert(Integer timePoint, Boolean ignoreHour) {
        if (!ignoreHour) {
            return convert(timePoint);
        }
        StringBuffer time = new StringBuffer();
        int minute = timePoint / 60;
        if (minute >= 0) {
            if (minute < 10) {
                time.append("0").append(minute).append(":");
            } else {
                time.append(minute).append(":");
            }
        }
        int second = timePoint % 60;
        if (second >= 0) {
            if (second < 10) {
                time.append("0").append(second);
            } else {
                time.append(second);
            }
        }
        return time.toString();
    }

    /**
     *
     * @param time
     */
    public static Integer format(String time) {
        Integer result = 0;
        if (time == null) {
            return null;
        }
        try {
            result = Integer.valueOf(time);
            return result;
        } catch (NumberFormatException e) {
            // if time can not convert to Integer, then execute
            // following code
        }
        String[] times = time.split(":");
        int rate = 1;
        for (int i = times.length - 1; i >= 0; i--) {
            result += Integer.valueOf(times[i]) * rate;
            rate *= 60;
        }
        return result;
    }

    public static Date getOneDayStartTime(Date date) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        return todayStart.getTime();
    }

    public static Date getOneDayEndTime(Date date) {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        return todayEnd.getTime();
    }

    public static Integer getWeekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static Integer getYearOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static Integer weeksBetween(Date fromDate, Date toDate) {
        if (fromDate.before(toDate)) {
            return 0;
        }
        Integer weekNum = (getWeekOfYear(fromDate) - getWeekOfYear(toDate))
                + (getYearOfDate(fromDate) - getYearOfDate(toDate)) * 52;
        return ++weekNum;
    }

    /**
     * Verification date is legal, the date string fromat yyyy-mm-dd.
     * @param dateString
     * @return
     */
    public static Boolean isValidDate(String dateString) {
        sdf.applyPattern(DATE_FORMATE_SHORTTIME);
        sdf.setLenient(false);
        try {
            sdf.parse(dateString);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

}
