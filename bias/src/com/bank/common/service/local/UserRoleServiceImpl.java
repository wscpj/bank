package com.bank.common.service.local;

import java.util.List;

import org.apache.log4j.Logger;

import com.bank.common.base.BaseService;
import com.bank.common.dao.UserRoleDao;
import com.bank.common.model.UserRole;
import com.bank.common.service.UserRoleService;

public class UserRoleServiceImpl extends BaseService implements UserRoleService {

    /**
     *
     */
    private static final long serialVersionUID = -6506419383913246083L;
    private static final Logger logger = Logger.getLogger(UserRoleServiceImpl.class);
    private UserRoleDao userRoleDao;

    @Override
    public List<UserRole> findByUserId(Integer id) {
        logger.info("findByUserId id:{}" + id);
        List<UserRole> userRole = null;
        try {
            userRole = userRoleDao.findByUserId(id);
        } catch(Exception e) {
            logger.error("findByUserId error" + e);
        }
        return userRole;
    }

    @Override
    public void deleteUserRoleByUserId(Integer userId) {
        logger.info("deleteUserRoleByUserId userId:{}" + userId);
        try {
            userRoleDao.deleteUserRoleByUserId(userId);
        } catch(Exception e) {
            logger.error("deleteUserRoleByUserId error" + e);
        }
    }

    @Override
    public Boolean insertUserRole(UserRole userRole) {
        logger.info("insertUserRole userRole:{}" + userRole);
        try {
            userRoleDao.add(userRole);
        } catch(Exception e) {
            logger.error("insertUserRole error" + e);
        }
        return Boolean.TRUE;
    }

    public UserRoleDao getUserRoleDao() {
        return userRoleDao;
    }

    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }
}
