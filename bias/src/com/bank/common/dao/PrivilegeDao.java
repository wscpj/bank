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

    public void deletePrivilegeByIds(List<Integer> listId);
}
