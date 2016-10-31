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
        return privilegeDao.getPrivilegeById(id);
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
        List<Privilege> secondPrivileges = null;
        if (rootPrivilege != null) {
            paramMap.put("parentId", rootPrivilege.getId());
            PaginationDTO<Privilege> paginationDTO = (PaginationDTO<Privilege>) AppContext
                    .getContext().getObject(AppConstants.PAGINATION_DTO);
            if (paginationDTO != null) {
                paginationDTO.getParameterMap().putAll(paramMap);
                Integer count = findPrivilegeCount(paginationDTO
                        .getParameterMap());
                if (count.intValue() == 0) {// 把平台根结点加进来
                    count = 1;
                } else {
                    count += 1;
                }
                paginationDTO.setTotalRowCount(count);
                paginationDTO.getParameterMap().put("offset",
                        paginationDTO.getOffset());
                paginationDTO.getParameterMap().put("rowCount",
                        paginationDTO.getRowCount());
            }
            secondPrivileges = privilegeDao
                    .findPrivilegesByParentId(paginationDTO.getParameterMap());
            if (secondPrivileges == null) {
                secondPrivileges = new ArrayList<Privilege>();
            }
            secondPrivileges.add(rootPrivilege);
        }
        return secondPrivileges;
    }

    private String buildTree(List<Privilege> userPrivileges, Privilege rootPrivilege) {
        StringBuffer tree = new StringBuffer();
        List<Privilege> list = privilegeDao.findAllPrivilege();
        if (rootPrivilege != null) {
            if (userPrivileges != null && userPrivileges.size() > 0) {
                tree.append("<ul class=\"tree\">");
                for (Privilege pl : list) {
                    if (pl.getParentId() == rootPrivilege.getId()) {
                        tree.append("<li><a>"
                                + "<i class=\"fa fa-sitemap fa-lg fa-fw\"></i>" + pl.getDisplayName() + "</a>");
                        tree.append(this.build(pl));
                        tree.append("</li>");
                    }
                }
                tree.append("</ul>");
            }
        }
        return tree.toString();
    }

    public String build(Privilege privilege) {
        StringBuffer html = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parentId", privilege.getId());
        List<Privilege> list = privilegeDao.findPrivilegesByParentId(paramMap);
        if (list != null && list.size() > 0) {
            html.append("<ul>");
            for (Privilege pl : list) {
                if (pl.getUrl().endsWith("database")) {
                    html.append("<li><a href=\""+ AppContext.getContextPath() + "/" + pl.getUrl().trim() +"\" target=\"ajaxTodo\" rel=\"w_table\" title=\"确定进行数据库备份吗?\" "+ " >"
                            + "<i class=\"fa fa-user fa-lg fa-fw\"></i>" + pl.getDisplayName() + "</a>");
                } else if(pl.getUrl().endsWith("#")){
                    html.append("<li><a id=\"mid\" href=\""+ AppContext.getContextPath() + "/" + pl.getUrl().trim() +"\" target=\"navTab\" rel=\"w_table\""+" >"
                            + "<i class=\"fa fa-user fa-lg fa-fw\"></i>" +  pl.getDisplayName() + "</a>");
                } else {
                    html.append("<li><a href=\""+ AppContext.getContextPath() + "/" + pl.getUrl().trim() +"\" target=\"navTab\" rel=\"w_table\"" +" >"
                            + "<i class=\"fa fa-user fa-lg fa-fw\"></i>" +  pl.getDisplayName() + "</a>");
                }
                html.append(build(pl));
                html.append("</li>");
            }
            html.append("</ul>");
        }
        return html.toString();
    }

    @Override
    public Privilege getRootPrivilege() {
        return privilegeDao.getRootPrivilege();
    }

    @Override
    public String findPrivilegeByUserId(Integer userId) {
        Privilege rootPrivilege = privilegeDao.getRootPrivilege();
        List<Privilege> privileges = null;
        if (rootPrivilege != null) {
            Map<String, Object> paramMap = new HashMap<String,Object>();
            paramMap.put(AppConstants.USER_ID, userId);
            paramMap.put(AppConstants.PARENT_ID, rootPrivilege.getId());
            privileges = privilegeDao.findPrivilegeByUserId(paramMap);
            if (privileges == null) {
                privileges = new ArrayList<Privilege>();
            }
            privileges.add(rootPrivilege);

        }
        String trees = this.buildTree(privileges, rootPrivilege);
        return trees;
    }
}
