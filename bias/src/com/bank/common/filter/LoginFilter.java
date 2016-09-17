package com.bank.common.filter;

import java.io.IOException;
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
import com.bank.common.model.Role;
import com.bank.common.model.User;


/*
 *  This class be used for test to mock up a logged user.
 *
 */
public class LoginFilter implements Filter {

    private static final String URL_LOGIN = "page/user/login";
    private static final String URL_LOGOUT = "/page/user/logout";
    private static final String NOT_LOGIN = "NotLogin";
    private static final String SPRIT = "/";
    private static final String AJAX_HEAD = "X-Requested-With";
    private static final String NOT_ADMIN = "notAdmin";
    private static final String URL_UPLOAD = "/admin/upload";

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
        Boolean isLogin = uri.equals(request.getContextPath() + SPRIT) || uri.endsWith(URL_LOGIN) || uri.endsWith(URL_LOGOUT);
        boolean isNotAdmin = true;
        String ss = (String)session.getAttribute(NOT_ADMIN);
        if (session.getAttribute(NOT_ADMIN) != null) {
            isNotAdmin = (Boolean) session.getAttribute(NOT_ADMIN);
        }
        try {
            if (!isLogin && !isNotAdmin) {
                User user = (User) session.getAttribute(AppConstants.USER);
                @SuppressWarnings("unchecked")
                List<Role> userRoles = (List<Role>) session.getAttribute(AppConstants.ROLES);
                if (user == null || userRoles == null || userRoles.isEmpty()) {
                    if (request.getHeader(AJAX_HEAD) == null) {
                        response.sendRedirect(request.getContextPath());
                        return;
                    }
                    response.getWriter().print(NOT_LOGIN);
                    return;
                }
            }
            chain.doFilter(request, servletResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
