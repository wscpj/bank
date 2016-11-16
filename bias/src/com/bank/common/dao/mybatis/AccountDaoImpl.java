package com.bank.common.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;
import com.bank.common.base.BaseDao;
import com.bank.common.dao.AccountDao;
import com.bank.common.model.Account;
import com.bank.common.model.PaginationDTO;
import com.bank.common.model.Role;

public class AccountDaoImpl extends BaseDao<Account, Integer>
        implements AccountDao {

    private static final long serialVersionUID = 3533838170052053738L;

    private static final String CLASS_NAME = Account.class.getName();

    private static final String SQL_ID_GET_BY_ROLE_CODE = ".getByRoleCode";
    private static final String SQL_ID_FIND_ROLE_CODE = ".findRoleCodes";
    private static final String SQL_ID_FIND_ALL_ACCOUNT_BY_PARAMS = ".findAllAccountByParams";
    private static final String SQL_ID_FIND_ALL_ACCOUNT_GETCOUNT = ".getCount";
    private static final String SQL_ID_DELETE_ROLE_BYIDS = ".deleteRoleByIds";
    private static final String SQL_ID_FIND_ALL_ROLE = ".findAllRole";

    @Override
    public Integer getCount(Map<String, Object> map) {
        return getSqlSession()
                .selectOne(CLASS_NAME + SQL_ID_FIND_ALL_ACCOUNT_GETCOUNT, map);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Account> findAllAccountByParams(Map<String, Object> map) {
        Map<String, Object> params = getParameterMap();
        PaginationDTO<Role> paginationDTO = (PaginationDTO<Role>) AppContext
                .getContext().getObject(AppConstants.PAGINATION_DTO);
        if (paginationDTO != null) {
            map.putAll(params);
            Integer count = getCount(map);
            paginationDTO.setTotalRowCount(count);
        }
        return getSqlSession().selectList(
                CLASS_NAME + SQL_ID_FIND_ALL_ACCOUNT_BY_PARAMS, map);
    }

}
