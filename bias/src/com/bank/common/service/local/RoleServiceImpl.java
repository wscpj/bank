package com.bank.common.service.local;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bank.common.base.BaseService;
import com.bank.common.dao.PrivilegeDao;
import com.bank.common.dao.RoleDao;
import com.bank.common.dao.RolePrivilegeDao;
import com.bank.common.model.Privilege;
import com.bank.common.model.Role;
import com.bank.common.model.RolePrivilege;
import com.bank.common.service.RoleService;

public class RoleServiceImpl extends BaseService implements RoleService {

    private static final long serialVersionUID = -4270844451495902923L;

    public RoleDao roleDao;

    public RolePrivilegeDao rolePrivilegeDao;

    public PrivilegeDao privilegeDao;

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
        // 删除角色同时删除角色权限映射表
        for (Integer id : ids) {
            rolePrivilegeDao.delete(id);
        }
        roleDao.deleteRoleByIds(ids);
    }

    @Override
    public String roleSetPrivilegeBulidTree(Integer roleId) {
        StringBuffer tree = new StringBuffer();
        List<Privilege> list = privilegeDao.findAllPrivilege();
        List<RolePrivilege> rolePrivileges = rolePrivilegeDao.findRolePrivilege(roleId);
        Privilege privilegeRoot = privilegeDao.getRootPrivilege();
        Boolean flag = Boolean.FALSE;
        if (privilegeRoot != null) {
            if (list != null && list.size() > 0) {
                tree.append("<ul class='tree treeFolder treeCheck'>");
                for (Privilege pl : list) {
                    if (pl.getParentId() == privilegeRoot.getId()) {
                        for (RolePrivilege rolePrivilege : rolePrivileges) {
                            if (rolePrivilege.getPrivilegeId() != null && rolePrivilege.getPrivilegeId().intValue() == pl.getId()) {
                                flag = Boolean.TRUE;
                                break;
                            } else {
                                flag = Boolean.FALSE;
                            }
                        }
                        if (flag) {
                            tree.append("<li><a tname='name' tvalue='"+ pl.getId() +"' checked='true'>"
                                    + pl.getDisplayName() + "</a>");
                        } else {
                            tree.append("<li><a tname='name' tvalue='"+ pl.getId() +"' >"
                                    + pl.getDisplayName() + "</a>");
                        }
                        tree.append(this.build(pl, rolePrivileges));
                        tree.append("</li>");
                    }
                }
                tree.append("</ul>");
            }
        }
        return tree.toString();
    }

    public String build(Privilege privilege, List<RolePrivilege> rolePrivileges) {
        StringBuffer html = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parentId", privilege.getId());
        List<Privilege> list = privilegeDao.findPrivilegesByParentId(paramMap);
        Boolean flag = Boolean.FALSE;
        if (list != null && list.size() > 0) {
            html.append("<ul>");
            for (Privilege pl : list) {
                for (RolePrivilege rolePrivilege : rolePrivileges) {
                    if (rolePrivilege.getPrivilegeId() != null && rolePrivilege.getPrivilegeId().intValue() == pl.getId()) {
                        flag = Boolean.TRUE;
                        break;
                    } else {
                        flag = Boolean.FALSE;
                    }
                }
                if (flag) {
                    html.append("<li><a tname='name' tvalue='"+ pl.getId() +"' checked='true'>"
                            + pl.getDisplayName() + "</a>");
                } else {
                    html.append("<li><a tname='name' tvalue='" + pl.getId() + "'>"
                            + pl.getDisplayName() + "</a>");
                }
                html.append(build(pl,rolePrivileges));
                html.append("</li>");
            }
            html.append("</ul>");
        }
        return html.toString();
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public RolePrivilegeDao getRolePrivilegeDao() {
        return rolePrivilegeDao;
    }

    public void setRolePrivilegeDao(RolePrivilegeDao rolePrivilegeDao) {
        this.rolePrivilegeDao = rolePrivilegeDao;
    }

    public PrivilegeDao getPrivilegeDao() {
        return privilegeDao;
    }

    public void setPrivilegeDao(PrivilegeDao privilegeDao) {
        this.privilegeDao = privilegeDao;
    }

}
