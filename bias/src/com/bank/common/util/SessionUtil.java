package com.bank.common.util;

import java.lang.reflect.Method;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;

public class SessionUtil {

    private static Object getSessionInThread() {
        Object session = AppContext.getContext().getObject(AppConstants.APP_CONTEXT_SESSION);
        return session;
    }

    public static void addSession(String key, Object object) {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }
        try {
            Class<?>[] param = new Class[2];
            param[0] = String.class;
            param[1] = Object.class;

            Method method = session.getClass().getMethod("setAttribute", param);

            Object[] objects = new Object[2];
            objects[0] = key;
            objects[1] = object;

            method.invoke(session, objects);
        } catch (Exception e) {
            new RuntimeException(e);
        }
    }

    public static Object getSession(String key) {
        Object session = getSessionInThread();
        if (session == null) {
            return null;
        }
        try {
            Class<?>[] param = new Class[1];
            param[0] = String.class;


            Method method = session.getClass().getMethod("getAttribute", param);

            Object[] objects = new Object[1];
            objects[0] = key;

            Object returnValue = method.invoke(session, objects);
            return returnValue;
        } catch (Exception e) {
            new RuntimeException(e);
        }
        return null;
    }

    public static void removeSession(String key) {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }
        try {
            Class<?>[] param = new Class[1];
            param[0] = String.class;


            Method method = session.getClass().getMethod("removeAttribute", param);

            Object[] objects = new Object[1];
            objects[0] = key;

            method.invoke(session, objects);
        } catch (Exception e) {
            new RuntimeException(e);
        }
    }

    public static void invalidate() {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }
        try {
            Method method = session.getClass().getMethod("invalidate");
            method.invoke(session);

        } catch (Exception e) {
            new RuntimeException(e);
        }
    }


}
