package com.bank.common.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;
import com.bank.common.service.ACLService;
import com.bank.common.util.SpringUtil;


public class PrivilegeInterceptor extends HandlerInterceptorAdapter {

    private final Logger log = Logger.getLogger(PrivilegeInterceptor.class);
    private static final String NOT_ACCESS = "NoAccess";
    //private static final String AJAX_HEAD = "X-Requested-With";
    private static final String BEAN_NAME_PRIVILEGES_MAP = "privilegesMap";

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav) {
        response.addHeader("X-XSS-Protection", "0");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException, ServletException {
        HandlerMethod maControl = (HandlerMethod) handler;
        String className = maControl.getBeanType().getSimpleName();
        Method pmrResolver = maControl.getMethod();
        String methodName = pmrResolver.getName();
        String privilegeCode = className + AppConstants.STRIKE + methodName;
        ACLService aclService = (ACLService) SpringUtil.getBean(AppConstants.BEAN_NAME_ACL_SERVICE);
        @SuppressWarnings("unchecked")
        Map<String, String> privilegesMap = (Map<String, String>) SpringUtil.getBean(BEAN_NAME_PRIVILEGES_MAP);
        String parentPrivileges = privilegesMap.get(privilegeCode);
        if (parentPrivileges == null || parentPrivileges.isEmpty()) {
            return true;
        }
        String[] parentPrivilege = parentPrivileges.split(",");
        boolean isAllow = false;
        HttpSession session = request.getSession();
        String currentRoleCode = (String) session.getAttribute("currentRoleCode");
        for (String privilege : parentPrivilege) {
            if (currentRoleCode != null) {
                isAllow = isAllow || aclService.isAllow(privilege, currentRoleCode);
            } else {
                isAllow = isAllow || aclService.isAllow(privilege);
            }
        }
        if (isAllow) {
            return true;
        }
        log.warn(AppContext.getContext().getUserName() + " is no access to [ privilegeCode=" + privilegeCode + " ]");
        //Admin request is all ajax request
        //if (request.getHeader(AJAX_HEAD) == null) {
        //TODO no access page
        //response.sendRedirect("no access page to do");
        //return false;
        //}
        response.getWriter().print(NOT_ACCESS);
        return false;
    }
}
