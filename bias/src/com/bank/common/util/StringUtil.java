package com.bank.common.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.bank.common.AppConstants;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;

public class StringUtil {

    private static final String UNDERLINE = "_";
    private static final Pattern RANGE_PATTERN = Pattern.compile("bytes \\d+-\\d+/\\d+");
    private static final String CONTENT_RANGE_HEADER = "content-range";

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty() || str.equalsIgnoreCase("null");
    }

    /**
     * remove str content(provide by reps parameter.) For example: give a str=aaabbbccc, reps={"bbb","ccc"} then this
     * method return "aaa"
     *
     * @param str
     * @param reps
     * @return
     */
    public static String remove(String str, List<String> reps) {
        for (String s : reps) {
            str = str.replace(s, "");
        }
        return str;
    }

    /**
     * for a List = {"a", "b", "c"} and seperator="," return the result: "a,b,c"
     *
     * @param seperator
     * @param parts
     * @return
     */
    public static String implode(String seperator, Iterator<?> parts) {
        Joiner joiner = Joiner.on(seperator).skipNulls();
        return joiner.join(parts);
    }

    /**
     * If str contains one element of reps, just return true, Else return false.
     *
     * @param str
     * @param reps
     * @return
     */
    @SuppressWarnings("boxing")
    public static boolean contains(String str, Set<String> reps) {
        if (isEmpty(str)) {
            return Boolean.FALSE;
        }
        for (String s : reps) {
            if (str.contains(s)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * if Str start with one element of reps, return true, else return false.
     *
     * @param str
     * @param reps
     * @return
     */
    public static boolean startsWith(String str, List<String> reps) {
        if (isEmpty(str)) {
            return Boolean.FALSE;
        }
        for (String s : reps) {
            if (str.startsWith(s)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Get a uuid(Notice UpperCase)
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    /**
     * Indicate the parameter value is a chinese.
     *
     * @param value
     * @return
     */
    public static boolean isAllChinese(String value) {
        if (isEmpty(value)) {
            return Boolean.FALSE;
        }
        if (value.replaceAll("[\u4e00-\u9fa5]", "").length() == 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Get the ratio of Chinese, if the ratio >= rate(Parameter), return True.
     *
     * @param value
     * @param rate
     * @return
     */
    public static boolean isMostChinese(String value, double rate) {
        if (isEmpty(value)) {
            return Boolean.FALSE;
        }
        double temp = value.replaceAll("[\u4e00-\u9fa5]", "").length();

        double ratio = temp / value.length();
        return ratio >= rate;
    }

    /**
     * This method is used to split big string, it can improve the performance compare to string.split method.
     *
     * @param strSource
     * @param strDiv
     * @return
     */
    public static String[] split(String strSource, String strDiv) {
        int arynum = 0, intIdx = 0, intIdex = 0;
        int divLength = strDiv.length();
        if (strSource.compareTo("") != 0) {
            if (strSource.indexOf(strDiv) != -1) {
                intIdx = strSource.indexOf(strDiv);
                for (int intCount = 1;; intCount++) {
                    if (strSource.indexOf(strDiv, intIdx + divLength) != -1) {
                        intIdx = strSource.indexOf(strDiv, intIdx + divLength);
                        arynum = intCount;
                    } else {
                        arynum += 2;
                        break;
                    }
                }
            } else {
                arynum = 1;
            }
        } else {
            arynum = 0;
        }
        intIdx = 0;
        intIdex = 0;
        String[] returnStr = new String[arynum];
        if (strSource.compareTo("") != 0) {
            if (strSource.indexOf(strDiv) != -1) {
                intIdx = strSource.indexOf(strDiv);
                returnStr[0] = strSource.substring(0, intIdx);
                for (int intCount = 1;; intCount++) {
                    if (strSource.indexOf(strDiv, intIdx + divLength) != -1) {
                        intIdex = strSource.indexOf(strDiv, intIdx + divLength);
                        returnStr[intCount] = strSource.substring(intIdx + divLength, intIdex);
                        intIdx = strSource.indexOf(strDiv, intIdx + divLength);
                    } else {
                        returnStr[intCount] = strSource.substring(intIdx + divLength, strSource.length());
                        break;
                    }
                }
            } else {
                returnStr[0] = strSource.substring(0, strSource.length());
                return returnStr;
            }
        } else {
            return returnStr;
        }
        return returnStr;
    }

    public static String filter(String content) {
        if (isEmpty(content)) {
            return "";
        }
        content = content.replaceAll("&amp;", "&").replaceAll("&lt;", "<").replaceAll("&gt;", ">")
                .replaceAll("&apos;", "\'").replaceAll("&quot;", "\"").replaceAll("&nbsp;", " ")
                .replaceAll("&copy;", "@").replaceAll("&reg;", "?");
        return content;
    }

    /**
     * get the index of the order specified target string in the str
     *
     * @param source
     * @param target
     * @param orderNumber
     * @return
     */
    public static int getIndex(String source, String target, int orderNumber) {
        int i = 0;
        int index = 0;
        while (i < orderNumber) {
            index = source.indexOf(target, index);
            index++;
            i++;
        }
        return index - 1;
    }

    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int length = param.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Use MD5 digest and UTF-8 encoding to encrypt a string.
     *
     * @param str
     * @return
     */
    public static String MD5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes(Charsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            System.exit(-1);
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

    static Pattern pattern = Pattern.compile(AppConstants.PATH_VAR_REGEX);

    /**
     * get the variable and output a set contains the key of this path For exampel path: /people/$people/wife/$wife,
     * Params: {dog:"ddd", people:"aaa", wife:"bbb"} output: /people/aaa/wife/bbb, Params:{dog:"ddd"}
     *
     * @param path
     * @return
     */
    public static String convertPath(String path, Map<String, String> params) {
        Matcher matcher = pattern.matcher(path);
        String newPath = path;
        while (matcher.find()) {
            String key = matcher.group(1);
            if (params != null && params.containsKey(key)) {
                newPath = newPath.replace("$" + key, params.get(key));
            } else {
                throw new IllegalArgumentException("check the path variable.");
            }
            params.remove(key);
        }
        return newPath;
    }

    public static <T> Map<String, String> validateSortNameAndSign(String sortName, String sortSign, Class<T> clz) {
        Map<String, String> sortNameAndSign = new HashMap<String, String>();
        Field[] fields = clz.getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String fieldName = fields[i].getName();
            if (fieldName.equals(sortName)) {
                sortNameAndSign.put(AppConstants.SORT_NAME, sortName);
                break;
            }
        }
        if (sortNameAndSign.get(AppConstants.SORT_NAME) == null) {
            sortNameAndSign.put(AppConstants.SORT_NAME, fields[0].getName());
        }
        if (AppConstants.DESC_ORDER.equalsIgnoreCase(sortSign)) {
            sortNameAndSign.put(AppConstants.SORT_SIGN, AppConstants.DESC_ORDER);
        } else {
            sortNameAndSign.put(AppConstants.SORT_SIGN, AppConstants.ASC_ORDER);
        }
        return sortNameAndSign;
    }

    /**
     *
     * @param req
     * @return
     * @throws IOException
     */
    public static Range parseRange(HttpServletRequest req) throws IOException {
        String range = req.getHeader(CONTENT_RANGE_HEADER);
        Matcher m = RANGE_PATTERN.matcher(range);
        if (m.find()) {
            range = m.group().replace("bytes ", "");
            String[] rangeSize = range.split("/");
            String[] fromTo = rangeSize[0].split("-");
            long from = Long.parseLong(fromTo[0]);
            long to = Long.parseLong(fromTo[1]);
            long size = Long.parseLong(rangeSize[1]);
            return new Range(from, to, size);
        }
        throw new IOException("Illegal Access!");
    }

    public static String getCurrentMonthString() {
        String currentTime = DateTimeUtil.format("yyyy-MM", new Date());
        return currentTime;
    }

    public static String getCurrentDate() {
        Calendar c  = Calendar.getInstance();
        String currentTime = "" + c.get(Calendar.DATE);
        return currentTime;
    }

    /**
     *
     * @param name
     * @param size
     * @return
     * @throws Exception
     */
    public static String generateToken(String name, String size) throws IOException {
        if (name == null || size == null) {
            return "";
        }
        Date currentTime = new Date();
        int code = (name + currentTime.toString()).hashCode();
        try {
            String token = (code > 0 ? "A" : "B") + Math.abs(code) + "_" + size.trim();
            return token;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public static <T> String convertArrayToString(T[] array, String strCon) {
        StringBuffer sb = new StringBuffer();
        String str = "";
        if (array != null && array.length != 0) {
            for (T temp : array) {
                sb.append(temp + strCon);
            }
            str = sb.substring(0, sb.length() - 1);
        }
        return str;
    }

    /**
     * @param text
     * @return
     */
    public final static int getInt(String text) {
        return (int) Float.parseFloat(text);
    }

    public static String getHashFileName(String name) {
        if (name == null) {
            return "";
        }
        int hashName = name.substring(0, name.lastIndexOf(AppConstants.DOT) == -1
                ? name.length() : name.lastIndexOf(AppConstants.DOT)).hashCode();
        String fileSuffix = name.substring(name.lastIndexOf(AppConstants.DOT) == -1
                ? name.length() : name.lastIndexOf(AppConstants.DOT));
        String fileName = hashName + fileSuffix;
        return fileName;
    }

    public static String getSystemEncoding() {
        return System.getProperty(AppConstants.SYSTEM_ENCODING);
    }

    public static String getStorageFileTypeName(String name) {
        Map<String, String> fileTypeMap = new HashMap<String, String>();
        // Video type
        fileTypeMap.put(AppConstants.FILE_SUFFIX_MP4, AppConstants.VIDEO);

        // Compress package type
        fileTypeMap.put(AppConstants.FILE_SUFFIX_RAR, AppConstants.COMPRESSED_PACKAGE);
        fileTypeMap.put(AppConstants.FILE_SUFFIX_ZIP, AppConstants.COMPRESSED_PACKAGE);
        fileTypeMap.put(AppConstants.FILE_SUFFIX_GZ, AppConstants.COMPRESSED_PACKAGE);
        fileTypeMap.put(AppConstants.FILE_SUFFIX_7Z, AppConstants.COMPRESSED_PACKAGE);

        // Picture type
        fileTypeMap.put(AppConstants.FILE_SUFFIX_JPG, AppConstants.PICTURE);
        fileTypeMap.put(AppConstants.FILE_SUFFIX_CAPITAL_JPG, AppConstants.PICTURE);
        fileTypeMap.put(AppConstants.FILE_SUFFIX_JPEG, AppConstants.PICTURE);
        fileTypeMap.put(AppConstants.FILE_SUFFIX_CAPITAL_JPEG, AppConstants.PICTURE);
        fileTypeMap.put(AppConstants.FILE_SUFFIX_PNG, AppConstants.PICTURE);
        fileTypeMap.put(AppConstants.FILE_SUFFIX_CAPITAL_PNG, AppConstants.PICTURE);
        fileTypeMap.put(AppConstants.FILE_SUFFIX_GIF, AppConstants.PICTURE);
        fileTypeMap.put(AppConstants.FILE_SUFFIX_BMP, AppConstants.PICTURE);
        fileTypeMap.put(AppConstants.FILE_SUFFIX_PIC, AppConstants.PICTURE);

        Integer lastIndex = name.lastIndexOf(AppConstants.DOT);
        if (lastIndex.equals(-1)) {
            return null;
        }
        String suffix = name.substring(lastIndex);
        String resourceFileTypeName = fileTypeMap.get(suffix);
        if (resourceFileTypeName == null) {
            // Document type
            return AppConstants.DOCUMENT;
        }
        return resourceFileTypeName;
    }

    public static String excelColIndexToStr(int columnIndex) {
        if (columnIndex <= 0) {
            return null;
        }
        String columnStr = "";
        do {
            if (columnStr.length() > 0) {
                columnIndex--;
            }
            columnStr = ((char) (columnIndex % 26 + 'A')) + columnStr;
            columnIndex = (columnIndex - columnIndex % 26) / 26;
        } while (columnIndex > 0);
        return columnStr;
    }

    /*
     * generate range string
     * @param num
     * @return string
     */
    public static String generateRangeString(Integer num) {
        String rangeString = UUID.randomUUID().toString();
        if (rangeString.length() <= num) {
            return rangeString;
        }
        return rangeString.substring(0,num);
    }
}
