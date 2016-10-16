package com.bank.common.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bank.common.base.BasePageController;
import com.bank.common.service.UserService;

@Controller
@RequestMapping("/page/privilege")
public class PrivilegeController extends BasePageController {
    private final String LOGIN_JSP = "user/login";
    private final Logger logger = Logger.getLogger(PrivilegeController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(LOGIN_JSP);
        return modelAndView;
    }

}
