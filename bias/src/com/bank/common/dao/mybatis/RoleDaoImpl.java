package com.bank.common.dao.mybatis;
import java.util.List;

import com.bank.common.base.BaseDao;
import com.bank.common.dao.RoleDao;
import com.bank.common.model.Role;


public class RoleDaoImpl extends BaseDao<Role, Integer> implements RoleDao {
    private static final String CLASS_NAME = Role.class.getName();

    private static final String SQL_ID_ROLE_FIND_BY_USER_ID = ".findByUserId";
    private static final String SQL_ID_GET_BY_ROLE_CODE = ".getByRoleCode";
    private static final String SQL_ID_FIND_ROLE_CODE = ".findRoleCodes";

    @Override
    public List<Role> find(Integer userId) {
        return findByQueryId(SQL_ID_ROLE_FIND_BY_USER_ID, userId);
    }

    @Override
    public Role getByCode(String roleCode) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_BY_ROLE_CODE, roleCode);
    }

    @Override
    public List<String> findRoleCodes(Integer userId) {
        return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_ROLE_CODE, userId);
    }
}