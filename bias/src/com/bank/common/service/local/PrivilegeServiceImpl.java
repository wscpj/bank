package com.bank.common.service.local;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

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
    private static final Logger logger = Logger.getLogger(PrivilegeServiceImpl.class);
    private PrivilegeDao privilegeDao;

    public void setPrivilegeDao(PrivilegeDao privilegeDao) {
        this.privilegeDao = privilegeDao;
    }

    @Override
    public Map<String, List<Privilege>> findRolePrivileges() {
        Map<String, List<Privilege>> rolePrivileges = new HashMap<String, List<Privilege>>();
        logger.info("logging findRolePrivileges parametes:{}");
        try {
            List<PrivilegeDTO> privilegeDTOs = privilegeDao.findRolePrivileges();
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
        } catch (Exception e) {
            logger.error("findRolePrivileges error" + e);
        }

        return rolePrivileges;
    }

    @Override
    public Integer findPrivilegeCount(Map<String, Object> map) {
        Integer result = null;
        logger.info("findPrivilegeCount map : {}" + map);
        try {
            result = privilegeDao.findPrivilegeCount(map);
        } catch (Exception e) {
            logger.error("findPrivilegeCount error " + e);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Privilege> findAllPrivilege(Map<String, Object> map) {
        return privilegeDao.findAllPrivilege(map);
    }

    @Override
    public List<Privilege> findAllPrivilege() {
        List<Privilege> result = null;
        logger.info("findAllPrivilege paramters : {}");
        try {
            result = privilegeDao.findAllPrivilege();
        } catch (Exception e) {
            logger.error("findAllPrivilege error " + e);
        }
        return result;
    }

    @Override
    public Boolean addPrivilege(Privilege privilege) {
        logger.info("addPrivilege privilege : {}" + privilege);
        try {
            privilegeDao.add(privilege);
        } catch (Exception e) {
            logger.error("findAllPrivilege error " + e);
        }
        return Boolean.TRUE;
    }

    @Override
    public Privilege selectPrivilegeById(Integer id) {
        logger.info("selectPrivilegeById id : {}" + id);
        Privilege result = null;
        try {
            result = privilegeDao.getPrivilegeById(id);
        } catch (Exception e) {
            logger.error("selectPrivilegeById error " + e);
        }
        return result;
    }

    @Override
    public Boolean updatePrivilege(Privilege privilege) {
        logger.info("updatePrivilege privilege : {}" + privilege);
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowDate = sf.format(new Date());
            privilege.setUpdatedTime(nowDate);
            privilegeDao.update(privilege);
        } catch (Exception e) {
            logger.error("updatePrivilege error " + e);
        }
        return Boolean.TRUE;

    }

    @Override
    public void deletePrivilegeByIds(List<Integer> listId) {
        logger.info("deletePrivilegeByIds listId : {}" + listId);
        try {
            privilegeDao.deletePrivilegeByIds(listId);
        } catch (Exception e) {
            logger.error("deletePrivilegeByIds error " + e);
        }
    }

    @Override
    public List<Privilege> findParentPrivileges(Map<String, Object> paramMap) {
        List<Privilege> secondPrivileges = null;
        logger.info("findParentPrivileges paramMap : {}" + paramMap);
        try {
            Privilege rootPrivilege = privilegeDao.getRootPrivilege();
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
        } catch (Exception e) {
            logger.error("findParentPrivileges error" + e);
        }
        return secondPrivileges;
    }

    private String buildTree(List<Privilege> userPrivileges, Privilege rootPrivilege,Integer userId) {
        StringBuffer tree = new StringBuffer();
        if (rootPrivilege != null) {
            if (userPrivileges != null && userPrivileges.size() > 0) {
                tree.append("<ul class=\"tree\">");
                for (Privilege pl : userPrivileges) {
                    if (pl.getParentId() == rootPrivilege.getId()) {
                        tree.append("<li><a>"
                                + "<i class=\"fa fa-sitemap fa-lg fa-fw\"></i>" + pl.getDisplayName() + "</a>");
                        tree.append(this.build(pl,userId));
                        tree.append("</li>");
                    }
                }
                tree.append("</ul>");
            }
        }
        return tree.toString();
    }

    public String build(Privilege privilege,Integer userId) {
        StringBuffer html = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(AppConstants.PARENT_ID, privilege.getId());
        paramMap.put(AppConstants.USER_ID, userId);
        List<Privilege> list = privilegeDao.findUserRolePrivilegeByParentId(paramMap);
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
                html.append(build(pl,userId));
                html.append("</li>");
            }
            html.append("</ul>");
        }
        return html.toString();
    }

    @Override
    public Privilege getRootPrivilege() {
        logger.info("getRootPrivilege paramters : {}");
        Privilege result = null;
        try {
            result = privilegeDao.getRootPrivilege();
        } catch (Exception e) {
            logger.error("getRootPrivilege error" + e);
        }
        return result;
    }

    @Override
    public String findPrivilegeByUserId(Integer userId) {
        logger.info("findPrivilegeByUserId userId : {}" + userId);
        String trees = null;
        try {
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
            trees = this.buildTree(privileges, rootPrivilege,userId);
        } catch (Exception e) {
            logger.error("findPrivilegeByUserId error" + e);
        }

        return trees;
    }
}
