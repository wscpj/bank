package com.bank.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bank.common.base.BasePageController;
import com.bank.common.model.Account;
import com.bank.common.service.AccountService;

@Controller
@RequestMapping("/page/account")
public class AccountController extends BasePageController {
    private final String LOGIN_JSP = "user/login";
    private final String MAIN_JSP = "user/main";
    private final String LIST_JSP = "account/accountList";
    private final String ADD_JSP = "role/addRole";
    private final String EDIT_JSP = "role/editRole";
    private final String ROLE_SET_PRIVILEGE = "role/roleSetPrivilege";

    private final Logger logger = Logger.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/search")
    public ModelAndView findRole(HttpServletRequest request,
            @RequestParam(value = "pageNum", defaultValue = "") String pageNum,
            @RequestParam(value = "numPerPage", defaultValue = "") String numPerPage,
            @RequestParam(value = "userName", defaultValue = "") String userName,
            @RequestParam(value = "beginTime", defaultValue = "") String beginTime,
            @RequestParam(value = "endTime", defaultValue = "") String endTime) {

        Integer pageNumInt = "".equals(pageNum) ? 1 : Integer.valueOf(pageNum);
        Integer numPerPageInt = "".equals(numPerPage) ? 10
                : Integer.valueOf(numPerPage);

        // String roleName = request.getParameter("roleName");
        final Map<String, Object> paramsMap = new HashMap<String, Object>();
        // paramsMap.put("roleName", roleName);
        paramsMap.put("beginTime", beginTime);
        paramsMap.put("endTime", endTime);

        return pagination(paramsMap, pageNumInt, numPerPageInt, request,
                LIST_JSP, new PaginationCallBack<Account>() {
                    @Override
                    public List<Account> callBack() {
                        return accountService.findAllAccountByParams(paramsMap);
                    }
                });
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

}
