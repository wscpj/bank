package com.bank.common.service.local;

import java.util.List;
import java.util.Map;

import com.bank.common.base.BaseService;
import com.bank.common.dao.RoleDao;
import com.bank.common.dao.RolePrivilegeDao;
import com.bank.common.model.Role;
import com.bank.common.service.RoleService;

public class RoleServiceImpl extends BaseService implements RoleService {

    private static final long serialVersionUID = -4270844451495902923L;

    public RoleDao roleDao;
    
    public RolePrivilegeDao rolePrivilegeDao;

    @Override
    public List<Role> findAllRoleByParams(Map<String, Object> map) {

        return roleDao.findAllRoleByParams(map);
    }

    @Override
    public List<Role> findAllRole() {
        return roleDao.findAllRole();
    }

    @Override
    public Boolean saveRole(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Role findByRoleId(Integer roleId) {
        return roleDao.findByRoleId(roleId);
    }

    @Override
    public Boolean updateRole(Role role) {
        return roleDao.update(role);
    }

    @Override
    public void deleteRoleByIds(List<Integer> ids) {
    	//删除角色同时删除角色权限映射表
    	for(Integer id: ids){
    		rolePrivilegeDao.delete(id);
    	}
        roleDao.deleteRoleByIds(ids);
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

}
