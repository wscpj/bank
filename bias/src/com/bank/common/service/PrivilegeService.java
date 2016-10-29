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
    
    public StringBuffer getTree(List<Privilege> privileges,Privilege root,String path);
    
    public List<String> getTrees(List<Privilege> list,Privilege rootPrivilege, String path);
    
    public Privilege getRootPrivilege();
}
