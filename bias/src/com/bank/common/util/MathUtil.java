package com.bank.common.util;

import com.bank.common.AppConstants;


public class MathUtil {

    /**
     *
     * @param divisor
     * @param dividend
     * @return
     */
    public static String percentage(Integer divisor, Integer dividend, Integer precision) {
        if (divisor == null || divisor < 0) {
            return null;
        }
        // TODO for fix bug
        if (dividend != null && dividend == 0) {
            return "" + 0 + AppConstants.PERCENT_SIGH;
        }

        if (dividend == null || dividend <= 0) {
            return null;
        }
        if (precision < 0) {
            return null;
        }
        if (precision == 0) {
            Long result = Math.round((double)(divisor * 100) / dividend);
            return result.toString() + AppConstants.PERCENT_SIGH;
        } else {
            Double dbDivisor = Double.valueOf(divisor.doubleValue());
            Double dbDividend = Double.valueOf(dividend.doubleValue());
            Double result = (dbDivisor * 100) / dbDividend;
            return toFixedOne(result).toString() + AppConstants.PERCENT_SIGH;
        }

    }

    public static String percentage(Integer divisor, Integer dividend) {
        return percentage(divisor, dividend, 0);
    }

    private static Double toFixedOne(Double input) {
        if (input == null) {
            return 0.0;
        }
        return Math.round(input * 10) / 10.0;
    }

    /**
     * trim decimal, like change '10.0' to '10'
     *
     * @param input
     * @return
     */
    private static String trimDecimal(Double input) {
        if (input == null) {
            return "";
        }
        Integer intValue = input.intValue();
        if (input.doubleValue() == intValue.doubleValue()) {
            return intValue.toString();
        }
        return input.toString();
    }

    public static String percentage(Double actual, Double total) {
        String percent = AppConstants.ZERO_STRING;
        if (!actual.isNaN() && !total.isNaN()) {
            Double result = actual * 10 / total;
            percent = trimDecimal(Double.valueOf((toFixedOne(result) * 10)));
        }

        return percent + AppConstants.PERCENT_SIGH;
    }
}
