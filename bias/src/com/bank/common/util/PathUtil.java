package com.bank.common.util;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;

public class PathUtil {
    public static String getFullPath(String path) {
        if (path ==null) {
            path = "";
        }
        String urlPrefix = AppConstants.APP_URL_PREFIX;
        if (!StringUtil.isEmpty(urlPrefix)) {
            urlPrefix += "/";
        }
        return AppContext.getContextPath() + "/" + urlPrefix + path;
    }
}
