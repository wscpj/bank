package com.bank.common.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;
import com.bank.common.base.BasePageController;
import com.bank.common.exception.BusinessException;
import com.bank.common.exception.ValidationException;
import com.bank.common.model.User;
import com.bank.common.service.UserService;

@Controller
@RequestMapping("/page/user")
public class UserController extends BasePageController {
    private final String LOGIN_JSP = "user/login";
    private final String MAIN_JSP = "user/main";
    private final String DASHBOARD = "page/user/dashboard";
    private final String USER_MANAGE = "/WEB-INF/page/system/user/user.jsp";
    //private final String LOGIN_PAGE = "user/login";

    private final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(LOGIN_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(MAIN_JSP);
        return modelAndView;
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView Login(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "password", defaultValue = "") String password
            ){

        ModelAndView modelAndView = new ModelAndView();

        try {
            User user = null;
            user = userService.login(name, password);
            user.setPassword(null);
            this.addSession(AppConstants.USER, user);
            modelAndView.setView(new RedirectView(AppContext.getContextPath() + "/" + DASHBOARD));

        } catch (ValidationException validationException) {
            Map<String, String> errorFilds = validationException.getFieldErrors();
            modelAndView.addObject(AppConstants.ERROR_FILDS, errorFilds);
            modelAndView.setViewName(LOGIN_JSP);
            logger.info("The parameter is error!", validationException);

        } catch (BusinessException businessException) {
            modelAndView.addObject(AppConstants.MESSAGE, businessException.getErrorMessage() + "  [" + businessException.getCode() + "]");
            modelAndView.addObject(AppConstants.VISIBILITY, "visible");
            modelAndView.setViewName(LOGIN_JSP);
            logger.warn("The username or password is error!", businessException);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/userManage", method = RequestMethod.GET)
    public ModelAndView searchUser() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(USER_MANAGE);
        return modelAndView;
    }

    //    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    //    public ModelAndView logout() {
    //        ModelAndView modelAndView = new ModelAndView();
    //        this.removeSession(Constants.USER);
    //        modelAndView.setView(this.getRedirectView(LOGIN_PAGE));
    //
    //        logger.info("The user logout the system!");
    //
    //        return modelAndView;
    //    }
}
