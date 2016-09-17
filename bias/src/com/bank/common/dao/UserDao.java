package com.bank.common.dao;

import com.bank.common.model.User;

public interface UserDao {
    public User getUserByName(String userName);
}
