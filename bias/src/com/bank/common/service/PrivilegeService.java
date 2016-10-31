package com.bank.common.service;

import java.util.List;
import java.util.Map;

import com.bank.common.model.Privilege;

public interface PrivilegeService {
    public Map<String, List<Privilege>> findRolePrivileges();

    public Integer findPrivilegeCount(Map<String, Object> map);

    public List<Privilege> findAllPrivilege(Map<String, Object> map);

    public List<Privilege> findAllPrivilege();

    public Boolean addPrivilege(Privilege privilege);

    public Privilege selectPrivilegeById(Integer id);

    public Boolean updatePrivilege(Privilege privilege);

    public void deletePrivilegeByIds(List<Integer> listId);

    public List<Privilege> findParentPrivileges(Map<String, Object> map);

    /**
     * 查询权限根目录（parent_id = 0）的权限
     *
     * @return Privilege
     */
    public Privilege getRootPrivilege();

    public String findPrivilegeByUserId(Integer userId);

}
