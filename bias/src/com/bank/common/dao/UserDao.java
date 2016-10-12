package com.bank.common.dao;

import java.util.List;

import com.bank.common.base.IBaseDao;
import com.bank.common.model.User;

public interface UserDao extends IBaseDao<User, Integer>{
    User getUserByNameAndPassword(String userName,String password);

    Integer getCount();

    List<User> findUsers();

    List<User> searchUsers(String username, String date);
}
