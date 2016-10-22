package com.bank.common.dao.mybatis;
import java.util.List;
import java.util.Map;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;
import com.bank.common.base.BaseDao;
import com.bank.common.dao.RoleDao;
import com.bank.common.model.PaginationDTO;
import com.bank.common.model.Role;


public class RoleDaoImpl extends BaseDao<Role, Integer> implements RoleDao {

    private static final long serialVersionUID = -2188344611935357973L;

    private static final String CLASS_NAME = Role.class.getName();

    private static final String SQL_ID_GET_BY_ROLE_CODE = ".getByRoleCode";
    private static final String SQL_ID_FIND_ROLE_CODE = ".findRoleCodes";
    private static final String SQL_ID_FIND_ALL_ROLE = ".findAllRole";
    private static final String SQL_ID_FIND_ALL_ROLE_GETCOUNT = ".getCount";
    private static final String SQL_ID_DELETE_ROLE_BYIDS = ".deleteRoleByIds";

    @Override
    public Role findByRoleId(Integer roleId) {
        return getById(roleId);
    }

    @Override
    public Role getByCode(String roleCode) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_BY_ROLE_CODE, roleCode);
    }

    @Override
    public List<String> findRoleCodes(Integer userId) {
        return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_ROLE_CODE, userId);
    }

    @Override
    public Integer getCount(Map<String, Object> map) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_FIND_ALL_ROLE_GETCOUNT, map);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> findAllRole(Map<String, Object> map){
        @SuppressWarnings("unused")
        Map<String, Object> params = getParameterMap();
        PaginationDTO<Role> paginationDTO = (PaginationDTO<Role>) AppContext.getContext().getObject(
                AppConstants.PAGINATION_DTO);
        if (paginationDTO != null) {
            map.putAll(paginationDTO.getParameterMap());
            Integer count = getCount(map);
            paginationDTO.setTotalRowCount(count);
        }
        return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_ALL_ROLE, map);
    }

    @Override
    public void deleteRoleByIds(List<Integer> ids){
        getSqlSession().update(CLASS_NAME + SQL_ID_DELETE_ROLE_BYIDS, ids);
    }
}