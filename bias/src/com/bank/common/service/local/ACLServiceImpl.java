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


public class ACLServiceImpl extends BaseService implements ACLService {

    /**
     *
     */
    private static final long serialVersionUID = -1660714949772901081L;
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
        Integer loginUserId = AppContext.getContext().getUserId();
        if (loginUserId.equals(userId)) {
            return isAllow(privilegeCode, AppContext.getContext().findRoleCodes());
        }
        List<String> codeList = roleDao.findRoleCodes(userId);
        String[] roleCodes = codeList.toArray(new String[codeList.size()]);
        return isAllow(privilegeCode, roleCodes);
    }

    @Override
    public Boolean isAllow(String privilegeCode, String... roleCodes) {
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
        return Boolean.FALSE;
    }

    @Override
    public Set<Privilege> findRolePrivilege(String roleCode, String privilegeType) {
        Integer userId = AppContext.getContext().getUserId();
        if (userId == null) {
            return null;
        }
        Set<Privilege> privileges = new HashSet<Privilege>();
        List<Privilege> allTypePrivileges = rolePrivilegeMap.get(roleCode);
        if (allTypePrivileges == null || allTypePrivileges.isEmpty()) {
            return null;
        }
        for (Privilege privilege : allTypePrivileges) {
            if (privilege.getPrivilegeCode().equals(privilegeType)) {
                privileges.add(privilege);
            }
        }
        return privileges;
    }

    private static void setRolePrivilegeMap(Map<String, List<Privilege>> rolePrivilegeMap) {
        ACLServiceImpl.rolePrivilegeMap = rolePrivilegeMap;
    }

    public void setPrivilegeService(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

}
