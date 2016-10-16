package com.bank.common.dao.mybatis;

import java.util.List;

import com.bank.common.base.BaseDao;
import com.bank.common.dao.PrivilegeDao;
import com.bank.common.dto.PrivilegeDTO;
import com.bank.common.model.Privilege;


public class PrivilegeDaoImpl extends BaseDao<Privilege, Integer> implements PrivilegeDao {


    private static final String CLASS_NAME = Privilege.class.getName();
    private static final String SQL_ID_FIND_ROLE_PRIVILEGES = ".findRolePrivileges";

    @Override
    public List<PrivilegeDTO> findRolePrivileges() {
        List<PrivilegeDTO> result = getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_ROLE_PRIVILEGES);
        return result;
    }
}
