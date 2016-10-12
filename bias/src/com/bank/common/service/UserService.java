package com.bank.common.service;

import java.util.List;

import com.bank.common.exception.BusinessException;
import com.bank.common.exception.ValidationException;
import com.bank.common.model.User;

public interface UserService {
    User login(String userName, String password) throws ValidationException, BusinessException;

    Boolean deleteOrgUser(Integer organizationId);

    List<User> findUsers();

    List<User> searchUsers(String username, String date);

    Boolean addUser(User user);

    Boolean updateUser(User user);

    Boolean deleteUser(Integer id);
}
