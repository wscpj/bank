package com.bank.common.service.local;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bank.common.base.BaseService;
import com.bank.common.dao.UserDao;
import com.bank.common.exception.BusinessException;
import com.bank.common.exception.ValidationException;
import com.bank.common.model.User;
import com.bank.common.service.UserService;
import com.bank.common.util.StringUtil;

public class UserServiceImpl extends BaseService implements UserService {
    private final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String userName, String password) throws ValidationException, BusinessException{

        ValidationException validationException = new ValidationException();
        if (StringUtil.isEmpty(userName)) {
            validationException.addError("erroruserName", "Your name is required!");
        }

        if (StringUtil.isEmpty(password)) {
            validationException.addError("errorpassword", "Your password is required!");
        }

        if (validationException.isError()) {
            logger.info("The parameter can not be null!", validationException);
            throw validationException;
        }

        User user = userDao.getUserByNameAndPassword(userName,password);

        if (user == null) {
            logger.warn("The username or password is error!", new BusinessException(1000, "The user doesn't exist"));
            throw new BusinessException(1000, "用户不存在");
        }

        if (!password.equals(user.getPassword())) {
            logger.warn("The username or password is error!", new BusinessException(1001, "The password is error"));
            throw new BusinessException(1001, "密码错误");
        }
        return user;
    }

    @Override
    public Boolean deleteOrgUser(Integer organizationId) {
        return Boolean.TRUE;
    }

    @Override
    public List<User> findUsers() {
        return userDao.findUsers();
    }

    @Override
    public List<User> searchUsers(Map<String, Object> map) {
        return userDao.searchUsers(map);
    }

    @Override
    public Boolean addUser(User user) {
        return userDao.add(user);
    }

    @Override
    public Boolean updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public Boolean deleteUser(Integer id) {
        return userDao.delete(id);
    }
    
    @Override
    public void deleteUserByIds(List<Integer> ids){
        userDao.deleteUserByIds(ids);
    }
}
