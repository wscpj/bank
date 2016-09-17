package com.bank.common.base;

import com.bank.common.AppContext;
import com.bank.common.dto.UserDTO;
import com.bank.common.util.SessionUtil;


public abstract class BaseController {

    protected Integer getUserId() {
        return AppContext.getContext().getUserId();
    }

    protected UserDTO getUser() {
        return AppContext.getContext().getUser();
    }

    protected Boolean isLogin() {
        return (null != AppContext.getContext().getUser());
    }

    protected void addSession(String key, Object object) {
        SessionUtil.addSession(key, object);
    }

    protected void getSession(String key) {
        SessionUtil.getSession(key);
    }

    protected void removeSession(String key) {
        SessionUtil.removeSession(key);
    }

    protected void invalidate(String key) {
        SessionUtil.invalidate();
    }
}
