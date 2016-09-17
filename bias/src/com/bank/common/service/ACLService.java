package com.bank.common.service;

import java.util.Set;

import com.bank.common.model.Privilege;


/**
 * Access Control List Service
 *
 */
public interface ACLService {

    Boolean isAllow(String privilegeCode);

    Boolean isAllow(Integer userId, String privilegeCode);

    Boolean isAllow(String privilegeCode, String...roleCodes);

    Set<Privilege> findRolePrivilege(String roleCode, String privilegeTypeMenu);
}
