package com.bank.common.service;

import java.util.List;

import com.bank.common.model.UserRole;

public interface UserRoleService {

    public List<UserRole> findByUserId(Integer id);

    public void deleteUserRoleByUserId(Integer userId);

    public Boolean insertUserRole(UserRole userRole);

}
