package com.bank.common.service.local;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bank.common.base.BaseService;
import com.bank.common.dao.LogDao;
import com.bank.common.model.Log;
import com.bank.common.service.LogService;

public class LogServiceImpl extends BaseService implements LogService {
    /**
     *
     */
    private static final long serialVersionUID = -8648289660180264572L;
    @SuppressWarnings("unused")
    private final Logger logger = Logger.getLogger(LogServiceImpl.class);
    private LogDao logDao;

    public void setLogDao(LogDao logDao) {
        this.logDao = logDao;
    }

    @Override
    public List<Log> searchLogs(Map<String, Object> map) {
        return logDao.searchLogs(map);
    }

    @Override
    public Boolean addLog(Log log) {
        return logDao.add(log);
    }

    @Override
    public Boolean deleteLogByIds(List<Integer> ids) {
        return logDao.deleteLogByIds(ids);
    }

}
