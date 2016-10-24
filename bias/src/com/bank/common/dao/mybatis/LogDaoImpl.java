package com.bank.common.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;
import com.bank.common.base.BaseDao;
import com.bank.common.dao.LogDao;
import com.bank.common.model.Log;
import com.bank.common.model.PaginationDTO;

public class LogDaoImpl extends BaseDao<Log, Integer> implements LogDao {
    /**
     *
     */
    private static final long serialVersionUID = -5566147083208420885L;
    private static final String CLASS_NAME = Log.class.getName();
    private static final String SQL_ID_LOG_GET_COUNT_BY_KEYWORDS = ".getCountByKeywords";
    private static final String SQL_ID_LOG_SEARCH_LOGS = ".searchLogs";
    private static final String SQL_ID_DELETE_LOG_BY_IDS = ".deleteLogsByIds";


    private Integer getCountByKeywords(Map<String, Object> params) {
        return getSqlSession().selectOne(
                CLASS_NAME + SQL_ID_LOG_GET_COUNT_BY_KEYWORDS, params);
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Log> searchLogs(Map<String, Object> map) {
        Map<String, Object> params = getParameterMap();
        PaginationDTO<Log> paginationDTO = (PaginationDTO<Log>) AppContext
                .getContext().getObject(AppConstants.PAGINATION_DTO);
        if (paginationDTO != null) {
            params.putAll(map);
            Integer count = getCountByKeywords(params);
            paginationDTO.setTotalRowCount(count);
        }
        return getSqlSession().selectList(
                CLASS_NAME + SQL_ID_LOG_SEARCH_LOGS, params);
    }

    @Override
    public Boolean deleteLogByIds(List<Integer> ids) {
        getSqlSession().update(CLASS_NAME + SQL_ID_DELETE_LOG_BY_IDS, ids);
        return Boolean.TRUE;
    }

}
