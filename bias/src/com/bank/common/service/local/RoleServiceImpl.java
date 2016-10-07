package com.bank.common.service.local;

import java.util.List;

import com.bank.common.base.BaseService;
import com.bank.common.dao.RoleDao;
import com.bank.common.model.Role;
import com.bank.common.service.RoleService;

public class RoleServiceImpl extends BaseService implements RoleService {
	
	public RoleDao roleDao;

	@Override
	public List<Role> findAllRole() {
		
		return roleDao.findAllRole();
	}
	@Override
	public Boolean saveRole(Role role){
		return roleDao.saveRole(role);
	}
	@Override
	public Role findByRoleId(String roleId){
		return roleDao.findByRoleId(roleId);
	}
	@Override
	public Boolean updateRole(Role role){
		return roleDao.updateRole(role);
	}
	
	
	
	
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
}
