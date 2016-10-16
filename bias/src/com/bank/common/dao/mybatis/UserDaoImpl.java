package com.bank.common.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;
import com.bank.common.base.BaseDao;
import com.bank.common.dao.UserDao;
import com.bank.common.model.PaginationDTO;
import com.bank.common.model.User;

public class UserDaoImpl extends BaseDao<User, Integer> implements UserDao {
    private static final String CLASS_NAME = User.class.getName();
    private static final String SQL_ID_USER_GET_USER_BY_NAME_AND_PASSWORD = ".getUserByNameAndPassword";
    private static final String SQL_ID_USER_GET_COUNT = ".getCount";
    private static final String SQL_ID_USER_GET_COUNT_BY_KEYWORDS = ".getCountByKeywords";
    private static final String SQL_ID_USER_FIND_USERS = ".findAll";
    private static final String SQL_ID_USER_SEARCH_USERS = ".searchUsers";
    private static final String SQL_ID_DELETE_USER_BYIDS = ".deleteUserByIds";
    private static final String SQL_ID_FIND_USER_BYID = ".findById";

    @Override
    public User getUserByNameAndPassword(String userName, String password) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("userName", userName);
        param.put("password", password);
        return getSqlSession().selectOne(
                CLASS_NAME + SQL_ID_USER_GET_USER_BY_NAME_AND_PASSWORD, param);
    }

    @Override
    public Integer getCount() {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_USER_GET_COUNT);
    }

    private Integer getCountByKeywords(Map<String, Object> params) {
        return getSqlSession().selectOne(
                CLASS_NAME + SQL_ID_USER_GET_COUNT_BY_KEYWORDS, params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findUsers() {
        Map<String, Object> params = getParameterMap();
        PaginationDTO<User> paginationDTO = (PaginationDTO<User>) AppContext
                .getContext().getObject(AppConstants.PAGINATION_DTO);
        if (paginationDTO != null) {
            Integer count = getCount();
            paginationDTO.setTotalRowCount(count);
        }
        System.out.println(paginationDTO.toString());
        return getSqlSession().selectList(CLASS_NAME + SQL_ID_USER_FIND_USERS);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> searchUsers(Map<String, Object> map) {
        Map<String, Object> params = getParameterMap();
        PaginationDTO<User> paginationDTO = (PaginationDTO<User>) AppContext
                .getContext().getObject(AppConstants.PAGINATION_DTO);
        if (paginationDTO != null) {
            params.putAll(map);
            Integer count = getCountByKeywords(params);
            paginationDTO.setTotalRowCount(count);
        }
        return getSqlSession().selectList(
                CLASS_NAME + SQL_ID_USER_SEARCH_USERS, params);
    }

    @Override
    public void deleteUserByIds(List<Integer> ids) {
        getSqlSession().update(CLASS_NAME + SQL_ID_DELETE_USER_BYIDS, ids);
    }

    @Override
    public User findUserById(Integer id) {
        return getSqlSession()
                .selectOne(CLASS_NAME + SQL_ID_FIND_USER_BYID, id);
    }
}
