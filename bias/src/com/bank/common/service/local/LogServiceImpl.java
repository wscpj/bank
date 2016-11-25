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
        logger.info("searchLogs map:{}" + map);
        List<Log> result = null;
        try {
            result = logDao.searchLogs(map);
        } catch(Exception e) {
            logger.error("searchLogs error" + e);
        }
        return result;
    }

    @Override
    public Boolean addLog(Log log) {
        logger.info("addLog log:{}" + log);
        Boolean result = null;
        try {
            result = logDao.add(log);
        } catch(Exception e) {
            logger.error("addLog error" + e);
        }
        return result;
    }

    @Override
    public Boolean deleteLogByIds(List<Integer> ids) {
        logger.info("deleteLogByIds ids:{}" + ids);
        Boolean result = null;
        try {
            result = logDao.deleteLogByIds(ids);
        } catch(Exception e) {
            logger.error("deleteLogByIds error" + e);
        }
        return result;
    }

}
