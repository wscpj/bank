package com.bank.common.service.local;

import java.util.List;

import com.bank.common.base.BaseService;
import com.bank.common.dao.RolePrivilegeDao;
import com.bank.common.model.RolePrivilege;
import com.bank.common.service.RolePrivilegeService;

public class RolePrivilegeServiceImpl extends BaseService implements RolePrivilegeService{

    private static final long serialVersionUID = -8008997482221753722L;

    private RolePrivilegeDao rolePrivilegeDao;

    @Override
    public void deleteRolePrivilege(Integer roleId){
        rolePrivilegeDao.delete(roleId);
    }

    public RolePrivilegeDao getRolePrivilegeDao() {
        return rolePrivilegeDao;
    }

    public void setRolePrivilegeDao(RolePrivilegeDao rolePrivilegeDao) {
        this.rolePrivilegeDao = rolePrivilegeDao;
    }

    @Override
    public List<RolePrivilege> findRolePrivilege(Integer roleId) {
        return rolePrivilegeDao.findRolePrivilege(roleId);
    }

    @Override
    public Boolean addRolePrivilege(List<RolePrivilege> rolePrivileges) {
        return rolePrivilegeDao.addRolePrivilege(rolePrivileges);
    }


}
