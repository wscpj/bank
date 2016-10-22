package com.bank.common.service;

import java.util.List;
import java.util.Map;

import com.bank.common.exception.BusinessException;
import com.bank.common.exception.ValidationException;
import com.bank.common.model.User;
import com.bank.common.vo.UserSetRoleVo;

public interface UserService {
    User login(String userName, String password) throws ValidationException,
            BusinessException;

    Boolean deleteOrgUser(Integer organizationId);

    List<User> findUsers();

    List<User> searchUsers(Map<String, Object> map);

    Boolean addUser(User user);

    Boolean updateUser(User user);

    Boolean deleteUser(Integer id);

    void deleteUserByIds(List<Integer> ids);

    User findUserById(Integer id);

    List<UserSetRoleVo> userSetRole();
}
