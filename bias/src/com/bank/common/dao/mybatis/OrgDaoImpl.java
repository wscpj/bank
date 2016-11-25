package com.bank.common.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;
import com.bank.common.base.BaseDao;
import com.bank.common.dao.OrgDao;
import com.bank.common.model.Organization;
import com.bank.common.model.PaginationDTO;

public class OrgDaoImpl extends BaseDao<Organization, Integer> implements OrgDao {
    /**
     *
     */
    private static final long serialVersionUID = 5086990557645317290L;

    private static final String CLASS_NAME = Organization.class.getName();
    private static final String SQL_ID_ORG_GET_COUNT_BY_KEYWORDS = ".getCountByKeywords";
    private static final String SQL_ID_ORG_SEARCH_ORGS = ".searchOrgs";
    private static final String SQL_ID_DELETE_ORG_BYIDS = ".deleteOrgByIds";

    @Override
    public List<Organization> searchOrganizations(Map<String, Object> map) {
        Map<String, Object> params = getParameterMap();
        PaginationDTO<Organization> paginationDTO = (PaginationDTO<Organization>) AppContext
                .getContext().getObject(AppConstants.PAGINATION_DTO);
        if (paginationDTO != null) {
            params.putAll(map);
            Integer count = getCountByKeywords(params);
            paginationDTO.setTotalRowCount(count);
        }
        return getSqlSession().selectList(
                CLASS_NAME + SQL_ID_ORG_SEARCH_ORGS, params);
    }
    @Override
    public Boolean deleteOrgByIds(List<Integer> ids) {
        getSqlSession().update(CLASS_NAME + SQL_ID_DELETE_ORG_BYIDS, ids);
        return Boolean.TRUE;
    }

    private Integer getCountByKeywords(Map<String, Object> params) {
        return getSqlSession().selectOne(
                CLASS_NAME + SQL_ID_ORG_GET_COUNT_BY_KEYWORDS, params);
    }

}
