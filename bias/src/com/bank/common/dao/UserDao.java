package com.bank.common.dao;

import java.util.List;
import java.util.Map;

import com.bank.common.base.IBaseDao;
import com.bank.common.model.User;
import com.bank.common.vo.UserSetRoleVo;


public interface UserDao extends IBaseDao<User, Integer> {

    User getUserByNameAndPassword(String userName, String password);

    Integer getCount();

    List<User> findUsers();

    List<User> searchUsers(Map<String, Object> map);

    void deleteUserByIds(List<Integer> ids);

    User findUserById(Integer id);

    List<UserSetRoleVo> userSetRole(Integer userId);
}
