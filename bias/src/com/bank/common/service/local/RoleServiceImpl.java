package com.bank.common.service.local;

import java.util.List;
import java.util.Map;

import com.bank.common.base.BaseService;
import com.bank.common.dao.RoleDao;
import com.bank.common.model.Role;
import com.bank.common.service.RoleService;

public class RoleServiceImpl extends BaseService implements RoleService {
	
	private static final long serialVersionUID = -4270844451495902923L;
	
	public RoleDao roleDao;

	@Override
	public List<Role> findAllRole(Map<String, Object> map) {
		
		return roleDao.findAllRole(map);
	}
	@Override
	public Boolean saveRole(Role role){
		return roleDao.saveRole(role);
	}
	@Override
	public Role findByRoleId(Integer roleId){
		return roleDao.findByRoleId(roleId);
	}
	@Override
	public Boolean updateRole(Role role){
		return roleDao.updateRole(role);
	}
	@Override
	public void deleteRoleByIds(List<Integer> ids){
		roleDao.deleteRoleByIds(ids);
	}
	
	
	
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
}
