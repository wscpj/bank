package com.bank.common.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;
import com.bank.common.model.Role;
import com.bank.common.model.User;
import com.bank.common.util.AppUtil;
import com.bank.common.util.StringUtil;

import common.Logger;

public class SessionFilter implements Filter {

    private static Logger log = Logger.getLogger(SessionFilter.class);

    public SessionFilter() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
        HttpServletRequest request = (HttpServletRequest)servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Set encoding method
        try {
            request.setCharacterEncoding(AppConstants.UTF_ENCODE);
        } catch (UnsupportedEncodingException e1) {
            throw new RuntimeException(e1);
        }
        response.setCharacterEncoding(AppConstants.UTF_ENCODE);

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(AppConstants.USER);
        @SuppressWarnings("unchecked")
        List<Role> roles = (List<Role>)session.getAttribute(AppConstants.ROLES);
        String currentRoleCode = (String) session.getAttribute(AppConstants.CURRENT_ROLE_CODE);
        AppContext appContext = AppContext.getContext();
        if (user != null) {
            appContext.addObject(AppConstants.USER, user);
            appContext.addObject(AppConstants.ROLES, roles);
            appContext.addObject(AppConstants.CURRENT_ROLE_CODE, currentRoleCode);
        }

        // Internationalization
        String locale = request.getParameter(AppConstants.LOCALE);
        if (StringUtil.isEmpty(locale)) {
            locale = (String) session.getAttribute(AppConstants.LOCALE);
            if (StringUtil.isEmpty(locale)) {
                locale = AppConstants.LOCALE_ZH_CN;
            }
        } else if (AppConstants.LOCALE_EN_US.equals(locale)) {
            // empty here
        } else {
            locale = AppConstants.LOCALE_ZH_CN;
        }
        session.setAttribute(AppConstants.LOCALE, locale);
        appContext.addObject(AppConstants.LOCALE, locale);

        // put staticURL into request for being called in JSP
        request.setAttribute(AppConstants.STATIC_URL, AppUtil.getPropertyValue(AppConstants.STATIC_URL));
        request.setAttribute(AppConstants.SH_DFS_URL, AppUtil.getPropertyValue(AppConstants.SH_DFS_URL));
        String uri =  request.getRequestURI();
        String requestedUri = uri.substring(request.getContextPath().length() + 1);
        appContext.addObject(AppConstants.REQUESTED_URI, requestedUri);
        try {
            chain.doFilter(request, response);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (ServletException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            appContext.clear();
        }

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
