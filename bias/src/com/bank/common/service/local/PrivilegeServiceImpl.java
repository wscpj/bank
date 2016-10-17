package com.bank.common.service.local;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bank.common.base.BaseService;
import com.bank.common.dao.PrivilegeDao;
import com.bank.common.dto.PrivilegeDTO;
import com.bank.common.model.Privilege;
import com.bank.common.service.PrivilegeService;

public class PrivilegeServiceImpl extends BaseService implements PrivilegeService {

	private static final long serialVersionUID = -319207069173729558L;
	
	private PrivilegeDao privilegeDao;

    public void setPrivilegeDao(PrivilegeDao privilegeDao) {
        this.privilegeDao = privilegeDao;
    }

    @Override
    public Map<String, List<Privilege>> findRolePrivileges() {
        List<PrivilegeDTO> privilegeDTOs = privilegeDao.findRolePrivileges();
        Map<String, List<Privilege>> rolePrivileges = new HashMap<String, List<Privilege>>();
        for (PrivilegeDTO privilegeDTO : privilegeDTOs) {
            Privilege privilege = (Privilege) copyObject(privilegeDTO, Privilege.class);
            privilege.setCreatedTime(privilegeDTO.getCreatedTime());
            if (rolePrivileges.get(privilegeDTO.getRoleCode()) == null) {
                List<Privilege> privileges = new ArrayList<Privilege>();
                rolePrivileges.put(privilegeDTO.getRoleCode(), privileges);
            }
            rolePrivileges.get(privilegeDTO.getRoleCode()).add(privilege);
        }
        return rolePrivileges;
    }
    
    @Override
    public Integer findPrivilegeCount(Map<String, Object> map){
    	return privilegeDao.findPrivilegeCount(map);
    }
    @Override
    public List<Privilege> findAllPrivilege(Map<String, Object> map){
    	return privilegeDao.findAllPrivilege(map);
    }
}
