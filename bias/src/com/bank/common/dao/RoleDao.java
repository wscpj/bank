package com.bank.common.dao;

import java.util.List;
import java.util.Map;

import com.bank.common.base.IBaseDao;
import com.bank.common.model.Role;

public interface RoleDao extends IBaseDao<Role, Integer> {
    /**
     * Find roles of user by userId
     * 
     * @param userId
     *            id of user
     * @return collection of roles
     */
    public Role findByRoleId(Integer roleId);

    public Role getByCode(String roleCode);

    public List<String> findRoleCodes(Integer userId);

    public List<Role> findAllRoleByParams(Map<String, Object> map);

    public Integer getCount(Map<String, Object> map);

    public void deleteRoleByIds(List<Integer> ids);

    public List<Role> findAllRole();

}
