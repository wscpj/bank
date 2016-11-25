package com.bank.common.dao.mybatis;

import java.util.List;

import com.bank.common.base.BaseDao;
import com.bank.common.dao.RolePrivilegeDao;
import com.bank.common.model.RolePrivilege;

public class RolePrivilegeDaoImpl extends BaseDao<RolePrivilege, Integer>  implements RolePrivilegeDao{

    private static final long serialVersionUID = 1865788097257487268L;
    private static final String CLASS_NAME = RolePrivilege.class.getName();
    private static final String SQL_ID_FIND_ROLE_PRIVILEGE_BY_ROLE_ID = ".findRolePrivilegeByRoleId";
    private static final String SQL_ID_ADD_ROLE_PRIVILEGE = ".addRolePrivilege";
    @Override
    public List<RolePrivilege> findRolePrivilege(Integer roleId) {
        return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_ROLE_PRIVILEGE_BY_ROLE_ID, roleId);
    }

    @Override
    public Boolean addRolePrivilege(List<RolePrivilege> rolePrivileges) {
        getSqlSession().insert(CLASS_NAME + SQL_ID_ADD_ROLE_PRIVILEGE, rolePrivileges);
        return Boolean.TRUE;
    }

}
