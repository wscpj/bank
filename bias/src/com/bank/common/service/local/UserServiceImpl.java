package com.bank.common.service.local;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bank.common.base.BaseService;
import com.bank.common.dao.UserDao;
import com.bank.common.dao.UserRoleDao;
import com.bank.common.dto.UserSetRoleDTO;
import com.bank.common.exception.BusinessException;
import com.bank.common.exception.ValidationException;
import com.bank.common.model.User;
import com.bank.common.service.UserService;
import com.bank.common.util.StringUtil;

public class UserServiceImpl extends BaseService implements UserService {

    private static final long serialVersionUID = 7440921737614461773L;

    private final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private UserDao userDao;
    
    private UserRoleDao userRoleDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String userName, String password)
            throws ValidationException, BusinessException {

        ValidationException validationException = new ValidationException();
        if (StringUtil.isEmpty(userName)) {
            validationException.addError("erroruserName",
                    "Your name is required!");
        }

        if (StringUtil.isEmpty(password)) {
            validationException.addError("errorpassword",
                    "Your password is required!");
        }

        if (validationException.isError()) {
            logger.info("The parameter can not be null!", validationException);
            throw validationException;
        }

        User user = userDao.getUserByNameAndPassword(userName, password);
        if (user == null) {
            logger.warn("The username or password is error!",
                    new BusinessException(1000, "The user doesn't exist"));
            throw new BusinessException(1000, "用户不存在");
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
    	//删除用户的同时删除用户角色表数据
    	userRoleDao.deleteUserRoleByUserId(id);
        return userDao.delete(id);
    }

    @Override
    public void deleteUserByIds(List<Integer> ids) {
    	//删除用户的同时删除用户角色表数据
    	for(Integer userId: ids){
    		userRoleDao.deleteUserRoleByUserId(userId);
    	}
        userDao.deleteUserByIds(ids);
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<UserSetRoleDTO> userSetRole(Integer userId) {
        return userDao.userSetRole(userId);
    }

	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
    
}
