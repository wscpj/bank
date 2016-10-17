package com.bank.common.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.bank.common.base.BaseDao;
import com.bank.common.dao.PrivilegeDao;
import com.bank.common.dto.PrivilegeDTO;
import com.bank.common.model.Privilege;


public class PrivilegeDaoImpl extends BaseDao<Privilege, Integer> implements PrivilegeDao {


	private static final long serialVersionUID = -2578792430404200927L;
	
	private static final String CLASS_NAME = Privilege.class.getName();
    private static final String SQL_ID_FIND_ROLE_PRIVILEGES = ".findRolePrivileges";
    private static final String SQL_ID_FIND_ALL_PRIVILEGE_GETCOUNT = ".getCount";
    private static final String SQL_ID_FIND_ALL_PRIVILEGE = ".findAllPrivilege";

    @Override
    public List<PrivilegeDTO> findRolePrivileges() {
        List<PrivilegeDTO> result = getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_ROLE_PRIVILEGES);
        return result;
    }
    @Override
    public Integer findPrivilegeCount(Map<String, Object> map){
    	return getSqlSession().selectOne(CLASS_NAME + SQL_ID_FIND_ALL_PRIVILEGE_GETCOUNT, map);
    }
    @Override
    public List<Privilege> findAllPrivilege(Map<String, Object> map){
    	return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_ALL_PRIVILEGE, map);
    }
}
