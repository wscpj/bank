package com.bank.common.service;

import com.bank.common.exception.BusinessException;
import com.bank.common.exception.ValidationException;
import com.bank.common.model.User;

public interface UserService {
    public User login(String userName, String password) throws ValidationException, BusinessException;
    
    Boolean deleteOrgUser(Integer organizationId);
}
