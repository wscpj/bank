package com.bank.common.service;

import java.util.List;
import java.util.Map;

import com.bank.common.model.Role;

public interface RoleService {
	
	public List<Role> findAllRoleByParams(Map<String, Object> map);
	public List<Role> findAllRole();
	public Boolean saveRole(Role role);
	public Role findByRoleId(Integer roleId);
	public Boolean updateRole(Role role);
	public void deleteRoleByIds(List<Integer> ids);

}
