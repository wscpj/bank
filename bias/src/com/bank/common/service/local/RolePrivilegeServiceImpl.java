package com.bank.common.service.local;

import java.util.List;

import org.apache.log4j.Logger;

import com.bank.common.base.BaseService;
import com.bank.common.dao.RolePrivilegeDao;
import com.bank.common.model.RolePrivilege;
import com.bank.common.service.RolePrivilegeService;

public class RolePrivilegeServiceImpl extends BaseService implements RolePrivilegeService{

    private static final long serialVersionUID = -8008997482221753722L;
    private static final Logger logger = Logger.getLogger(RolePrivilegeServiceImpl.class);
    private RolePrivilegeDao rolePrivilegeDao;

    @Override
    public void deleteRolePrivilege(Integer roleId){
        logger.info("deleteRolePrivilege roleid:{}" + roleId);
        try {
            rolePrivilegeDao.delete(roleId);
        } catch(Exception e) {
            logger.error("deleteRolePrivilege error" + e);
        }

    }

    public RolePrivilegeDao getRolePrivilegeDao() {
        return rolePrivilegeDao;
    }

    public void setRolePrivilegeDao(RolePrivilegeDao rolePrivilegeDao) {
        this.rolePrivilegeDao = rolePrivilegeDao;
    }

    @Override
    public List<RolePrivilege> findRolePrivilege(Integer roleId) {
        logger.info("findRolePrivilege roleId:{}" + roleId);
        List<RolePrivilege> rolePrivilege = null;
        try {
            rolePrivilege = rolePrivilegeDao.findRolePrivilege(roleId);
        } catch(Exception e) {
            logger.error("findRolePrivilege error" + e);
        }
        return rolePrivilege;
    }

    @Override
    public Boolean addRolePrivilege(List<RolePrivilege> rolePrivileges) {
        logger.info("addRolePrivilege rolePrivileges:{}" + rolePrivileges);
        try {
            rolePrivilegeDao.addRolePrivilege(rolePrivileges);
        } catch(Exception e) {
            logger.error("addRolePrivilege error" + e);
        }
        return Boolean.TRUE;
    }


}
