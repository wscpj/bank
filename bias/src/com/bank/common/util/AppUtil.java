package com.bank.common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.bank.common.AppConstants;
import com.bank.common.event.EventManager;


public final class AppUtil {
    private static final String RESOURCE_NAME = "bias.properties";
    private static Properties property = null;

    public static void init() throws IOException {
        Properties property = PropertiesLoaderUtils.loadAllProperties(RESOURCE_NAME);
        AppUtil.setProperty(property);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(AppConstants.PROPERTY, property);
        EventManager.dispatchEvent(AppConstants.EVENT_INIT_PROPERTY, params);
    }

    public static void setProperty(Properties property) {
        AppUtil.property = property;
    }

    public static String getPropertyValue(String key) {
        return (String) property.get(key);
    }
}
