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
import com.bank.common.util.StringUtil;

public class UserDaoImpl extends BaseDao<User, Integer> implements UserDao {
    private static final String CLASS_NAME = User.class.getName();
    private static final String SQL_ID_USER_GET_USER_BY_NAME_AND_PASSWORD = ".getUserByNameAndPassword";
    private static final String SQL_ID_USER_GET_COUNT = ".getCount";
    private static final String SQL_ID_USER_GET_COUNT_BY_KEYWORDS = ".getCountByKeywords";
    private static final String SQL_ID_USER_FIND_USERS = ".findAll";
    private static final String SQL_ID_USER_SEARCH_USERS = ".searchUsers";

    @Override
    public User getUserByNameAndPassword(String userName, String password) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("userName", userName);
        param.put("password", password);
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_USER_GET_USER_BY_NAME_AND_PASSWORD, param);
    }

    @Override
    public Integer getCount() {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_USER_GET_COUNT);
    }

    private Integer getCountByKeywords(Map params) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_USER_GET_COUNT_BY_KEYWORDS, params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findUsers() {
        Map<String, Object> params = getParameterMap();
        PaginationDTO<User> paginationDTO = (PaginationDTO<User>) AppContext.getContext().getObject(
                AppConstants.PAGINATION_DTO);
        if (paginationDTO != null) {
            Integer count = getCount();
            paginationDTO.setTotalRowCount(count);
        }
        System.out.println(paginationDTO.toString());
        return getSqlSession().selectList(
                CLASS_NAME + SQL_ID_USER_FIND_USERS, params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> searchUsers(String username, String date) {
        Map<String, Object> params = getParameterMap();
        PaginationDTO<User> paginationDTO = (PaginationDTO<User>) AppContext.getContext().getObject(
                AppConstants.PAGINATION_DTO);
        if (paginationDTO != null) {
            if (!StringUtil.isEmpty(username)) {
                params.put("username", username);
            }
            if (!StringUtil.isEmpty(date)) {
                params.put("date", date);
            }
            Integer count = getCountByKeywords(params);
            paginationDTO.setTotalRowCount(count);
        }
        return getSqlSession().selectList(
                CLASS_NAME + SQL_ID_USER_SEARCH_USERS, params);
    }
}
