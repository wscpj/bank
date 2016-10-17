package com.bank.common.service;

import java.util.List;
import java.util.Map;

import com.bank.common.model.Privilege;

public interface PrivilegeService {
	public Map<String, List<Privilege>> findRolePrivileges();
	public Integer findPrivilegeCount(Map<String, Object> map);
	public List<Privilege> findAllPrivilege(Map<String, Object> map);
}
