package com.bank.common.dao;

import java.util.List;
import java.util.Map;

import com.bank.common.base.IBaseDao;
import com.bank.common.model.Log;

public interface LogDao extends IBaseDao<Log, Integer> {

    List<Log> searchLogs(Map<String, Object> map);

    Boolean deleteLogByIds(List<Integer> ids);
}
