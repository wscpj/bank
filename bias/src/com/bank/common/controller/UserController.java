package com.bank.common.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final String ADD_JSP = "user/addUser";
    private final String EDIT_JSP = "user/editUser";
    private final String LIST_JSP = "user/userList";
    private final String DASHBOARD = "page/user/dashboard";

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
            this.addSession(AppConstants.ROLES, AppConstants.ROLES);
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

    @RequestMapping(value = "/find/{currentPage}", method = RequestMethod.GET)
    public ModelAndView findUser(@PathVariable Integer currentPage,
            HttpServletRequest request) {
        return pagination(currentPage, null,request, LIST_JSP, new PaginationCallBack<User>() {
            @Override
            public List<User> callBack() {
                return userService.findUsers();
            }
        });
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchUser(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "numPerPage") Integer numPerPage,
            @RequestParam(value = "keyword") final String keyword,
            @RequestParam(value = "date") final String date,
            HttpServletRequest request) {
        return pagination(pageNum, numPerPage,request, LIST_JSP, new PaginationCallBack<User>() {
            @Override
            public List<User> callBack() {
                return userService.searchUsers(keyword, date);
            }
        });
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(ADD_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public ModelAndView saveUser() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(ADD_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(EDIT_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView modifyUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(EDIT_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ModelAndView delete(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(EDIT_JSP);
        return modelAndView;
    }
}
