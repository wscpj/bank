package com.bank.common.service.local;

import com.bank.common.base.BaseService;
import com.bank.common.dao.RolePrivilegeDao;
import com.bank.common.service.RolePrivilegeService;

public class RolePrivilegeServiceImpl extends BaseService implements RolePrivilegeService{

	private static final long serialVersionUID = -8008997482221753722L;
	
	private RolePrivilegeDao rolePrivilegeDao;
	
	public void deleteRolePrivilege(Integer roleId){
		rolePrivilegeDao.delete(roleId);
	}

	public RolePrivilegeDao getRolePrivilegeDao() {
		return rolePrivilegeDao;
	}

	public void setRolePrivilegeDao(RolePrivilegeDao rolePrivilegeDao) {
		this.rolePrivilegeDao = rolePrivilegeDao;
	}

	
}
