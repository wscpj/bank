package com.bank.common.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.beanutils.Converter;

import com.bank.common.util.StringUtil;


public class DateConvert implements Converter {

    private static SimpleDateFormat df = new SimpleDateFormat();
    public final static String DATE_FORMATE_SHOWTIME = "yyyy-MM-dd";
    public final static String DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";

    private static Set<String> patterns = new HashSet<String>();
    static {

        patterns.add("yyyy-MM-dd HH:mm:ss");
        patterns.add("yyyy-MM-dd");
        patterns.add("yyyy-MM-dd HH:mm");
        patterns.add("yyyy/MM/dd HH:mm:ss");
    }

    public DateConvert() {
        super();
    }

    @Override
    public Object convert(@SuppressWarnings("rawtypes") Class type, Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof String) {
            Object dateObj = null;
            Iterator<String> it = patterns.iterator();
            while (it.hasNext()) {
                try {
                    String pattern = it.next();
                    df.applyPattern(pattern);
                    dateObj = df.parse((String) value);
                    break;
                } catch (ParseException ex) {
                    // ex.printStackTrace();
                }
            }
            return dateObj;
        } else {
            return null;
        }
    }

    /**
     * Formate the date
     *
     * @param format
     *            formate string
     * @param date
     *            the date which needed to be formated
     * @return String
     */
    public static String format(String format, Date date) {
        if (date == null) {
            return null;
        }

        String str = new SimpleDateFormat(format).format(date);
        return str;
    }

    public static Date stringToDate(String format, String date) {
        if (StringUtil.isEmpty(format) || StringUtil.isEmpty(date)) {
            return null;
        }
        Date dateNew = null;
        try {
            dateNew = new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            // TODO
        }
        return dateNew;
    }

    /**
     * Shows the time. If the date is current Year, show time like "MM月dd�?. If
     * not, show time like "yyyy-MM-dd".
     *
     * @param date
     *            Date
     * @return String
     */
    public static String showTime(Object date) {
        if (date == null) {
            return null;
        }
        if (date instanceof String) {
            date = stringToDate(DATE_FORMATE, (String) date);
        }
        String pattern = DATE_FORMATE_SHOWTIME;

        String formatDate = new SimpleDateFormat(pattern).format(date);
        String[] formatDateStr = formatDate.split("-");
        int year = Integer.parseInt(formatDateStr[0]);
        int month = Integer.parseInt(formatDateStr[1]);
        int day = Integer.parseInt(formatDateStr[2]);

        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);

        String showTimeSb = "";
        if (year == currentYear) {
            showTimeSb = showTimeSb + month + "月" + day + "日";
        } else {
            showTimeSb = showTimeSb + year + "-" + month + "-" + day;
        }
        return showTimeSb;
    }
}
