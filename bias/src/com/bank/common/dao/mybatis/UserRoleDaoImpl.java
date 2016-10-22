package com.bank.common.dao.mybatis;

import java.util.List;

import com.bank.common.base.BaseDao;
import com.bank.common.dao.UserRoleDao;
import com.bank.common.model.UserRole;

public class UserRoleDaoImpl extends BaseDao<UserRole, Integer> implements
        UserRoleDao {

    private static final long serialVersionUID = 4075470957177118844L;

    private static final String CLASS_NAME = UserRole.class.getName();
    private static final String SQL_ID_FIND_USER_BYID = ".findByUserId";
    private static final String SQL_ID_DELETE_USERROLE_BYUSERID = ".deleteByUserId";

    @Override
    public List<UserRole> findByUserId(Integer id) {
        return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_USER_BYID,
                id);
    }

    @Override
    public void deleteUserRoleByUserId(Integer userId) {
        getSqlSession().update(CLASS_NAME + SQL_ID_DELETE_USERROLE_BYUSERID,
                userId);
    }

}
