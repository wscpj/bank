package com.bank.common.dao;

import java.util.List;
import java.util.Map;

import com.bank.common.base.IBaseDao;
import com.bank.common.dto.PrivilegeDTO;
import com.bank.common.model.Privilege;

public interface PrivilegeDao extends IBaseDao<Privilege, Integer> {

    public List<PrivilegeDTO> findRolePrivileges();

    public Integer findPrivilegeCount(Map<String, Object> map);

    public List<Privilege> findAllPrivilege(Map<String, Object> map);

    public List<Privilege> findAllPrivilege();

    public Privilege getPrivilegeById(Integer id);

    public void deletePrivilegeByIds(List<Integer> listId);

    public List<Privilege> findPrivilegeByTree();

    public List<Privilege> findPrivilegesByParentId(Map<String, Object> paramMap);

    public Privilege getRootPrivilege();

    public List<Privilege> findPrivilegeByUserId(Map<String, Object> paramMap);

    public List<Privilege> findUserRolePrivilegeByParentId(Map<String, Object> paramMap);
}
