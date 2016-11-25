package com.bank.common.dao;

import java.util.List;

import com.bank.common.base.IBaseDao;
import com.bank.common.model.RolePrivilege;

public interface RolePrivilegeDao extends IBaseDao<RolePrivilege, Integer>{

    public List<RolePrivilege> findRolePrivilege(Integer roleId);

    public Boolean addRolePrivilege(List<RolePrivilege> rolePrivileges);
}
