package com.bank.common.service.local;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bank.common.AppContext;
import com.bank.common.base.BaseService;
import com.bank.common.dao.RoleDao;
import com.bank.common.model.Privilege;
import com.bank.common.service.ACLService;
import com.bank.common.service.PrivilegeService;

import common.Logger;


public class ACLServiceImpl extends BaseService implements ACLService {

    /**
     *
     */
    private static final long serialVersionUID = -1660714949772901081L;
    private static final Logger logger = Logger.getLogger(ACLServiceImpl.class);
    private static Map<String, List<Privilege>> rolePrivilegeMap;
    private RoleDao roleDao;
    private PrivilegeService privilegeService;

    public void init() {
        Map<String, List<Privilege>> rolePrivilegeMap = privilegeService.findRolePrivileges();
        ACLServiceImpl.setRolePrivilegeMap(rolePrivilegeMap);
    }

    @Override
    public Boolean isAllow(String privilegeCode) {
        String[] roleCodes = AppContext.getContext().findRoleCodes();
        return isAllow(privilegeCode, roleCodes);
    }

    @Override
    public Boolean isAllow(Integer userId, String privilegeCode) {
        logger.info("isAllow userId:{};privilegeCode:{}" + userId + ":" + privilegeCode);
        Boolean result = null;
        try {
            Integer loginUserId = AppContext.getContext().getUserId();
            if (loginUserId.equals(userId)) {
                return isAllow(privilegeCode, AppContext.getContext().findRoleCodes());
            }
            List<String> codeList = roleDao.findRoleCodes(userId);
            String[] roleCodes = codeList.toArray(new String[codeList.size()]);
            result = isAllow(privilegeCode, roleCodes);
        } catch (Exception e) {
            logger.error("isAllow error" + e);
        }
        return result;
    }

    @Override
    public Boolean isAllow(String privilegeCode, String... roleCodes) {
        logger.info("isAllow privilegeCode:{};roleCodes:{}" + privilegeCode + ":" + roleCodes);
        try {
            if (roleCodes == null) {
                return Boolean.FALSE;
            }
            for (String code : roleCodes) {
                List<Privilege> privileges = rolePrivilegeMap.get(code);
                if(privileges == null || privileges.isEmpty()) {
                    continue;
                }
                for (Privilege privilege : privileges) {
                    if (privilege.getPrivilegeCode().equals(privilegeCode)) {
                        return Boolean.TRUE;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("isAllow error " + e);
        }
        return Boolean.FALSE;
    }

    @Override
    public Set<Privilege> findRolePrivilege(String roleCode, String privilegeType) {
        logger.info("findRolePrivilege roleCode:{};privilegeType:{}" + roleCode + ":" + privilegeType);
        Set<Privilege> privileges = new HashSet<Privilege>();
        Integer userId = AppContext.getContext().getUserId();
        try {
            if (userId == null) {
                return null;
            }
            List<Privilege> allTypePrivileges = rolePrivilegeMap.get(roleCode);
            if (allTypePrivileges == null || allTypePrivileges.isEmpty()) {
                return null;
            }
            for (Privilege privilege : allTypePrivileges) {
                if (privilege.getPrivilegeCode().equals(privilegeType)) {
                    privileges.add(privilege);
                }
            }
        } catch (Exception e) {
            logger.error("findRolePrivilege error" + e);
        }

        return privileges;
    }

    private static void setRolePrivilegeMap(Map<String, List<Privilege>> rolePrivilegeMap) {
        logger.info("setRolePrivilegeMap rolePrivilegeMap:{}" + rolePrivilegeMap);
        try {
            ACLServiceImpl.rolePrivilegeMap = rolePrivilegeMap;
        } catch (Exception e) {
            logger.error("setRolePrivilegeMap error" + e);
        }
    }

    public void setPrivilegeService(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

}
