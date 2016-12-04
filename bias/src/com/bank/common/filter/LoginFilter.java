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

import org.apache.log4j.Logger;

import com.bank.common.AppConstants;
import com.bank.common.model.User;


/*
 *  This class be used for test to mock up a logged user.
 *
 */
public class LoginFilter implements Filter {

    private static final String URL_LOGIN = "page/user/login";
    private static final String URL_LOGOUT = "/page/user/logout";
    private static final String SPRIT = "/";
    private static final String AJAX_HEAD = "X-Requested-With";
    private static final String TIMEOUT_URL = "/page/user/timeout";
    private static final String LOGIN_DIALOG = "loginDialog";
    private static final Logger logger = Logger.getLogger(LoginFilter.class);
    public LoginFilter() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        Boolean isLogin = uri.equals(request.getContextPath() + SPRIT) || uri.endsWith(URL_LOGIN) || uri.endsWith(URL_LOGOUT)
                || uri.endsWith(TIMEOUT_URL);
        try {
            User user = (User) session.getAttribute(AppConstants.USER);
            if (!isLogin) {
                if (user == null) {
                    if (request.getHeader(AJAX_HEAD) == null) {
                        response.sendRedirect(request.getContextPath());
                        return;
                    } else {
                        response.sendRedirect(request.getContextPath() + TIMEOUT_URL);
                        return;
                    }
                }
            }
            chain.doFilter(request, servletResponse);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (ServletException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
