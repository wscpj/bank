package com.bank.common.listener;

import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bank.common.AppConstants;


public class BiasContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        TimeZone.setDefault(TimeZone.getTimeZone(AppConstants.TIMEZONE_AISA_SHANGHAI));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
