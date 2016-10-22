package com.bank.common.service.local;

import java.util.List;

import com.bank.common.base.BaseService;
import com.bank.common.dao.UserRoleDao;
import com.bank.common.model.UserRole;
import com.bank.common.service.UserRoleService;

public class UserRoleServiceImpl extends BaseService implements UserRoleService {

    /**
     * 
     */
    private static final long serialVersionUID = -6506419383913246083L;

    private UserRoleDao userRoleDao;

    @Override
    public List<UserRole> findByUserId(Integer id) {
        return userRoleDao.findByUserId(id);
    }

    @Override
    public void deleteUserRoleByUserId(Integer userId) {
        userRoleDao.deleteUserRoleByUserId(userId);
    }

    @Override
    public Boolean insertUserRole(UserRole userRole) {
        return userRoleDao.add(userRole);
    }

    public UserRoleDao getUserRoleDao() {
        return userRoleDao;
    }

    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }
}
