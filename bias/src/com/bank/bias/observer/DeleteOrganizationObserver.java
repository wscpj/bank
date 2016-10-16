package com.bank.bias.observer;

import java.util.Map;

import com.bank.common.AppConstants;
import com.bank.common.event.AbstractObserver;
import com.bank.common.service.UserService;



public class DeleteOrganizationObserver extends AbstractObserver {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(Map<String, Object> params) {
        userService.deleteOrgUser((Integer) params.get(AppConstants.ORGANIZATION_ID));
    }

}
