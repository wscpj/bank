package com.bank.common.dao;

import java.util.List;

import com.bank.common.base.IBaseDao;
import com.bank.common.model.UserRole;

public interface UserRoleDao extends IBaseDao<UserRole, Integer> {

    public List<UserRole> findByUserId(Integer id);

    public void deleteUserRoleByUserId(Integer userId);

    public void deleteUserToleByRoleId(Integer roleId);

}
