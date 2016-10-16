package com.bank.common.service;

import java.util.List;
import java.util.Map;

import com.bank.common.model.Privilege;

public interface PrivilegeService {
	Map<String, List<Privilege>> findRolePrivileges();
}
