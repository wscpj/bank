package com.bank.common.convert;

public class TimeConvert {

    /**
     *
     * @param timePoint
     *            example input 80
     * @return output 01:20:00
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
        int minute = (timePoint - hour * 3600) / 60;
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

}
