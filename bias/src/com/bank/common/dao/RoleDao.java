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
    List<Role> find(Integer userId);

    Role getByCode(String roleCode);

    List<String> findRoleCodes(Integer userId);
}
