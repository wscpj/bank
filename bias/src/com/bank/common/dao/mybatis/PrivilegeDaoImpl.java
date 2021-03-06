package com.bank.common.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;
import com.bank.common.base.BaseDao;
import com.bank.common.dao.PrivilegeDao;
import com.bank.common.dto.PrivilegeDTO;
import com.bank.common.model.PaginationDTO;
import com.bank.common.model.Privilege;

public class PrivilegeDaoImpl extends BaseDao<Privilege, Integer> implements
PrivilegeDao {

    private static final long serialVersionUID = -2578792430404200927L;

    private static final String CLASS_NAME = Privilege.class.getName();
    private static final String SQL_ID_FIND_ROLE_PRIVILEGES = ".findRolePrivileges";
    private static final String SQL_ID_FIND_ALL_PRIVILEGE_GETCOUNT = ".getCount";
    private static final String SQL_ID_FIND_ALL_PRIVILEGE_BYPARAMS = ".findAllPrivilegeByParams";
    private static final String SQL_ID_FIND_ALL_PRIVILEGE = ".findAllPrivilege";
    private static final String SQL_ID_UPDATE_PRIVILEGE = ".deletePrivilegeByIds";
    private static final String SQL_ID_FIND_PRIVILEGES_BY_TREE = ".findPrivilegesByTree";
    private static final String SQL_ID_FIND_PRIVILEGES_BY_PARENTID = ".findPrivilegesByParentId";
    private static final String SQL_ID_GET_ROOT_PRIVILEGE = ".getRootPrivilege";
    private static final String SQL_ID_GET_PRIVILEGE_BY_ID = ".getPrivilegeById";
    private static final String SQL_ID_FIND_PRIVILEGE_BY_USER_ID = ".findPrivilegesByUserId";
    private static final String SQL_ID_FIND_USER_ROLES_PRIVILEGE_BY_PARENT_ID = ".findUserRolesPrivilegesByParentId";

    @Override
    public List<PrivilegeDTO> findRolePrivileges() {
        List<PrivilegeDTO> result = getSqlSession().selectList(
                CLASS_NAME + SQL_ID_FIND_ROLE_PRIVILEGES);
        return result;
    }

    @Override
    public Integer findPrivilegeCount(Map<String, Object> map) {
        return getSqlSession().selectOne(
                CLASS_NAME + SQL_ID_FIND_ALL_PRIVILEGE_GETCOUNT, map);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Privilege> findAllPrivilege(Map<String, Object> map) {
        PaginationDTO<Privilege> paginationDTO = (PaginationDTO<Privilege>) AppContext
                .getContext().getObject(AppConstants.PAGINATION_DTO);
        Map<String, Object> params = getParameterMap();
        if (paginationDTO != null) {
            map.putAll(params);
            Integer count = findPrivilegeCount(map);
            paginationDTO.setTotalRowCount(count);
        }
        return getSqlSession().selectList(
                CLASS_NAME + SQL_ID_FIND_ALL_PRIVILEGE_BYPARAMS, map);
    }

    @Override
    public List<Privilege> findAllPrivilege() {
        return getSqlSession().selectList(
                CLASS_NAME + SQL_ID_FIND_ALL_PRIVILEGE);
    }

    @Override
    public void deletePrivilegeByIds(List<Integer> listId) {
        getSqlSession().update(CLASS_NAME + SQL_ID_UPDATE_PRIVILEGE, listId);
    }

    @Override
    public List<Privilege> findPrivilegeByTree() {
        return getSqlSession().selectList(
                CLASS_NAME + SQL_ID_FIND_PRIVILEGES_BY_TREE);
    }

    @Override
    public List<Privilege> findPrivilegesByParentId(Map<String, Object> paramMap) {
        return getSqlSession().selectList(
                CLASS_NAME + SQL_ID_FIND_PRIVILEGES_BY_PARENTID, paramMap);
    }

    @Override
    public Privilege getRootPrivilege() {

        return getSqlSession()
                .selectOne(CLASS_NAME + SQL_ID_GET_ROOT_PRIVILEGE);
    }

    @Override
    public Privilege getPrivilegeById(Integer id) {
        return getSqlSession().selectOne(
                CLASS_NAME + SQL_ID_GET_PRIVILEGE_BY_ID, id);
    }

    @Override
    public List<Privilege> findPrivilegeByUserId(Map<String, Object> paramMap) {
        return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_PRIVILEGE_BY_USER_ID, paramMap);
    }

    @Override
    public List<Privilege> findUserRolePrivilegeByParentId(Map<String, Object> paramMap) {
        return getSqlSession().selectList(
                CLASS_NAME + SQL_ID_FIND_USER_ROLES_PRIVILEGE_BY_PARENT_ID, paramMap);
    }
}
