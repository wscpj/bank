package com.bank.common.event;

import java.util.Map;

public interface Observer {
    public void execute(Map<String, Object> params);
    public boolean isAsyn();
}