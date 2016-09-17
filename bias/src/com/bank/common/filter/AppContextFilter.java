package com.bank.common.filter;

import java.io.IOException;

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
import com.bank.common.model.User;

public class AppContextFilter implements Filter {

    public AppContextFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String uri = request.getRequestURI();
        String requestedUri = uri.substring(request.getContextPath().length() + 1);
        AppContext.setContextPath(request.getContextPath());

        AppContext appContext = AppContext.getContext();
        appContext.addObject(AppConstants.APP_CONTEXT_REQUEST, request);
        appContext.addObject(AppConstants.APP_CONTEXT_RESPONSE, response);

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(AppConstants.USER);
        appContext.addObject(AppConstants.USER, user);
        appContext.addObject(AppConstants.APP_CONTEXT_SESSION, session);

        try {
            filterChain.doFilter(request, response);
        }  finally {
            appContext.clear();
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}
