package com.bank.common.service;

import java.util.List;
import java.util.Map;

import com.bank.common.model.Log;

public interface LogService {

    List<Log> searchLogs(Map<String, Object> map);

    Boolean addLog(Log log);


    Boolean deleteLogByIds(List<Integer> ids);

}
