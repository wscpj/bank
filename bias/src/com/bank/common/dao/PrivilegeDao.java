package com.bank.common.dao;
import java.util.List;

import com.bank.common.base.IBaseDao;
import com.bank.common.dto.PrivilegeDTO;
import com.bank.common.model.Privilege;


public interface PrivilegeDao extends IBaseDao<Privilege, Integer> {

    List<PrivilegeDTO> findRolePrivileges();
}
