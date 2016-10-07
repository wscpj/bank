package com.bank.common.service;

import java.util.List;

import com.bank.common.model.Role;

public interface RoleService {
	
	public List<Role> findAllRole();
	public Boolean saveRole(Role role);
	public Role findByRoleId(String roleId);
	public Boolean updateRole(Role role);

}
