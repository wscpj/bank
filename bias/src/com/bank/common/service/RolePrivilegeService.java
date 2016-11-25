package com.bank.common.service;

import java.util.List;

import com.bank.common.model.RolePrivilege;

public interface RolePrivilegeService {

    public void deleteRolePrivilege(Integer roleId);

    public List<RolePrivilege> findRolePrivilege(Integer roleId);

    public Boolean addRolePrivilege(List<RolePrivilege> rolePrivileges);

}
