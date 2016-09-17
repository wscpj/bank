package com.bank.bias.observer;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.bank.common.AppConstants;
import com.bank.common.event.AbstractObserver;
import com.bank.common.util.AppUtil;



public class InitPropertyObserver extends AbstractObserver {
    private static Logger log = Logger.getLogger(InitPropertyObserver.class);
    private static final String RESOURCE_NAME = "bias.properties";

    @Override
    public void execute(Map<String, Object> params) {
        Properties property = (Properties) params.get(AppConstants.PROPERTY);
        Properties biasProperty = null;
        try {
            biasProperty = PropertiesLoaderUtils.loadAllProperties(RESOURCE_NAME);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        property.putAll(biasProperty);
        AppUtil.setProperty(property);
    }

}