package com.bank.common.service.local;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;
import com.bank.common.base.BaseService;
import com.bank.common.dao.PrivilegeDao;
import com.bank.common.dto.PrivilegeDTO;
import com.bank.common.model.PaginationDTO;
import com.bank.common.model.Privilege;
import com.bank.common.model.Role;
import com.bank.common.service.PrivilegeService;

public class PrivilegeServiceImpl extends BaseService implements
        PrivilegeService {

    private static final long serialVersionUID = -319207069173729558L;

    private PrivilegeDao privilegeDao;

    public void setPrivilegeDao(PrivilegeDao privilegeDao) {
        this.privilegeDao = privilegeDao;
    }

    @Override
    public Map<String, List<Privilege>> findRolePrivileges() {
        List<PrivilegeDTO> privilegeDTOs = privilegeDao.findRolePrivileges();
        Map<String, List<Privilege>> rolePrivileges = new HashMap<String, List<Privilege>>();
        for (PrivilegeDTO privilegeDTO : privilegeDTOs) {
            Privilege privilege = (Privilege) copyObject(privilegeDTO,
                    Privilege.class);
            privilege.setCreatedTime(privilegeDTO.getCreatedTime());
            if (rolePrivileges.get(privilegeDTO.getRoleCode()) == null) {
                List<Privilege> privileges = new ArrayList<Privilege>();
                rolePrivileges.put(privilegeDTO.getRoleCode(), privileges);
            }
            rolePrivileges.get(privilegeDTO.getRoleCode()).add(privilege);
        }
        return rolePrivileges;
    }

    @Override
    public Integer findPrivilegeCount(Map<String, Object> map) {
        return privilegeDao.findPrivilegeCount(map);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Privilege> findAllPrivilege(Map<String, Object> map) {
        PaginationDTO<Role> paginationDTO = (PaginationDTO<Role>) AppContext
                .getContext().getObject(AppConstants.PAGINATION_DTO);
        if (paginationDTO != null) {
            map.putAll(paginationDTO.getParameterMap());
            Integer count = findPrivilegeCount(map);
            paginationDTO.setTotalRowCount(count);
        }
        return privilegeDao.findAllPrivilege(map);
    }

    @Override
    public List<Privilege> findAllPrivilege() {
        return privilegeDao.findAllPrivilege();
    }

    @Override
    public Boolean addPrivilege(Privilege privilege) {
        return privilegeDao.add(privilege);
    }

    @Override
    public Privilege selectPrivilegeById(Integer id) {
        return privilegeDao.getById(id);
    }

    @Override
    public Boolean updatePrivilege(Privilege privilege) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = sf.format(new Date());
        privilege.setUpdatedTime(nowDate);
        return privilegeDao.update(privilege);
    }

    @Override
    public void deletePrivilegeByIds(List<Integer> listId) {
        privilegeDao.deletePrivilegeByIds(listId);
    }

    @Override
    public List<Privilege> findParentPrivileges(Map<String, Object> paramMap) {
        Privilege rootPrivilege = privilegeDao.getRootPrivilege();
        paramMap.put("parentId", rootPrivilege.getId());
        PaginationDTO<Privilege> paginationDTO = (PaginationDTO<Privilege>) AppContext
                .getContext().getObject(AppConstants.PAGINATION_DTO);
        if (paginationDTO != null) {
            paginationDTO.getParameterMap().putAll(paramMap);
            Integer count = findPrivilegeCount(paginationDTO.getParameterMap());
            paginationDTO.setTotalRowCount(count);
            paginationDTO.getParameterMap().put("offset",
                    paginationDTO.getOffset());
            paginationDTO.getParameterMap().put("rowCount",
                    paginationDTO.getRowCount());
        }
        System.out.println(paginationDTO.getParameterMap());
        List<Privilege> secondPrivileges = privilegeDao
                .findPrivilegesByParentId(paginationDTO.getParameterMap());
        return secondPrivileges;
    }

    @Override
    public List<String> getTrees(List<Privilege> list, Privilege rootPrivilege,
            String path) {
        List<String> trees = new ArrayList<String>();
        for (Privilege privilege : list) {
            if (privilege.getParentId().intValue() == rootPrivilege.getId()
                    .intValue()) {
                trees.add(getTree(list, privilege, path).toString());
            }
        }
        return trees;
    }

    // 树形菜单的数据准备
    @Override
    public StringBuffer getTree(List<Privilege> privileges, Privilege root,
            String path) {
        StringBuffer sb = new StringBuffer();
        sb.append("<ul><li><a href='" + path + root.getUrl() + "' tname="
                + root.getId() + ", tvalue=" + root.getId()
                + "target='navTab' rel='w_table'>" + root.getDisplayName()
                + "</a>");
        for (Privilege privge : privileges) {
            if (privge.getParentId().intValue() != 0
                    && privge.getParentId().equals(root.getId())) {
                sb.append(getTree(privileges, privge, path));
            }
        }
        sb.append("</li></ul>");
        return sb;
    }

    @Override
    public Privilege getRootPrivilege() {
        return privilegeDao.getRootPrivilege();
    }
}
