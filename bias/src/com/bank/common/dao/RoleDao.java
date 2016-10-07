package com.bank.common.dao;

import java.util.List;

import com.bank.common.model.Role;


public interface RoleDao {
	/**
     * Find roles of user by userId
     *
     * @param userId
     *            id of user
     * @return collection of roles
     */
    Role findByRoleId(String roleId);

    Role getByCode(String roleCode);

    List<String> findRoleCodes(Integer userId);
    
    List<Role> findAllRole();
    
    Integer getCount();
    
    Boolean saveRole(Role role);
    Boolean updateRole(Role role);
}
