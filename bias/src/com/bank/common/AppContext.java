package com.bank.common;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.bank.common.dto.UserDTO;
import com.bank.common.model.Role;
import com.bank.common.model.User;


public class AppContext {

    private static final ThreadLocal<AppContext> appContext = new ThreadLocal<AppContext>();
    private final HashMap<String, Object> values = new HashMap<String, Object>();
    private static String contextPath;

    public static AppContext getContext() {
        AppContext context = appContext.get();
        if (context == null) {
            context = new AppContext();
            appContext.set(context);
        }
        return context;
    }

    public static String getContextPath() {
        return contextPath;
    }

    public static void setContextPath(String contextPath) {
        if (AppContext.contextPath == null) {
            AppContext.contextPath = contextPath;
        }
    }

    public void clear() {
        AppContext context = appContext.get();
        if (context != null) {
            context.values.clear();
        }
        context = null;
    }

    public void addObject(String key, Object value) {
        values.put(key, value);
    }

    public Object getObject(String key) {
        return values.get(key);
    }

    public String getUserName() {
        User user = getUser();
        if (user == null) {
            return "";
        }
        return user.getUserName();
    }

    public String getCurrentRoleCode() {
        String currentRoleCode = (String) values.get(AppConstants.CURRENT_ROLE_CODE);
        return currentRoleCode;
    }

    public Integer getUserId() {
        User user = getUser();
        if (user == null) {
            return 0;
        }
        return user.getId();
    }

    public User getUser() {
        User user = (User) values.get(AppConstants.USER);
        return user;
    }

    public Locale getLocale() {
        String locale = (String) values.get(AppConstants.LOCALE);
        if (AppConstants.LOCALE_EN_US.equals(locale)) {
            return Locale.US;
        } else {
            return Locale.CHINA;
        }
    }

    public Integer getOrgId() {
        UserDTO userDTO = (UserDTO) values.get(AppConstants.USER);
        return userDTO.getOrgId();
    }

    public List<Role> findRoles() {
        @SuppressWarnings("unchecked")
        List<Role> roles = (List<Role>) values.get(AppConstants.ROLES);
        return roles;
    }

    public String[] findRoleCodes() {
        List<Role> roles = findRoles();
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        String[] codes = new String[roles.size()];
        for (int i = 0; i < roles.size(); i++) {
            codes[i] = roles.get(i).getCode();
        }
        return codes;
    }
}
