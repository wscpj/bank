package com.bank.common.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;
import com.bank.common.base.BasePageController;
import com.bank.common.base.ResultMsg;
import com.bank.common.exception.BusinessException;
import com.bank.common.exception.ValidationException;
import com.bank.common.model.Log;
import com.bank.common.model.Role;
import com.bank.common.model.User;
import com.bank.common.service.LogService;
import com.bank.common.service.RoleService;
import com.bank.common.service.UserService;
import com.bank.common.util.RequestUtil;
import com.bank.common.util.StringUtil;

@Controller
@RequestMapping("/page/user")
public class UserController extends BasePageController {
    private final String LOGIN_JSP = "user/login";
    private final String MAIN_JSP = "user/main";
    private final String ADD_JSP = "user/addUser";
    private final String EDIT_JSP = "user/editUser";
    private final String LIST_JSP = "user/userList";
    private final String DASHBOARD = "page/user/dashboard";
    private final String USERSETROLE = "user/userSetRole";

    private final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;
    @Autowired
    private RoleService roleService;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(LOGIN_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public ModelAndView exit() {
        ModelAndView modelAndView = new ModelAndView();
        this.invalidate(null);
        modelAndView.setViewName(LOGIN_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(MAIN_JSP);
        return modelAndView;
    }
    
    @RequestMapping(value = "/userSetRole/{id}", method = RequestMethod.GET)
    public ModelAndView userSetRole() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(USERSETROLE);
        List<Role> roleList = roleService.findAllRole();
        modelAndView.addObject("roleList", roleList);
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView Login(
            HttpServletRequest request,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "password", defaultValue = "") String password) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            User user = null;
            user = userService.login(name, password);
            user.setPassword(null);
            Log log = new Log();
            log.setUserName(name);
            log.setRoleName(AppConstants.EMPTY);
            log.setIp(RequestUtil.getIpAddr(request));
            logService.addLog(log);
            this.addSession(AppConstants.USER, user);
            this.addSession(AppConstants.ROLES, AppConstants.ROLES);
            modelAndView.setView(new RedirectView(AppContext.getContextPath()
                    + "/" + DASHBOARD));

        } catch (ValidationException validationException) {
            Map<String, String> errorFilds = validationException
                    .getFieldErrors();
            modelAndView.addObject(AppConstants.ERROR_FILDS, errorFilds);
            modelAndView.setViewName(LOGIN_JSP);
            logger.info("The parameter is error!", validationException);

        } catch (BusinessException businessException) {
            modelAndView.addObject(AppConstants.MESSAGE,
                    businessException.getErrorMessage() + "  ["
                            + businessException.getCode() + "]");
            modelAndView.addObject(AppConstants.VISIBILITY, "visible");
            modelAndView.setViewName(LOGIN_JSP);
            logger.warn("The username or password is error!", businessException);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/search")
    public ModelAndView findRole(HttpServletRequest request) {

        String pageNum = request.getParameter("pageNum");
        String numPerPage = request.getParameter("numPerPage");
        Integer pageNumInt = pageNum == null ? 1 : Integer.valueOf(pageNum);
        Integer numPerPageInt = numPerPage == null ? 10 : Integer
                .valueOf(numPerPage);

        String userName = request.getParameter("userName");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        final Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userName", userName);
        paramsMap.put("beginTime", beginTime);
        paramsMap.put("endTime", endTime);

        return pagination(paramsMap, pageNumInt, numPerPageInt, request,
                LIST_JSP, new PaginationCallBack<User>() {
            @Override
            public List<User> callBack() {
                return userService.searchUsers(paramsMap);
            }
        });
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(ADD_JSP);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultMsg saveUser(@ModelAttribute User user,
            HttpServletRequest request) {
        ResultMsg resultMsg = null;

        Boolean bl = userService.addUser(user);
        if (bl) {
            resultMsg = ResultMsg.okMsg();
        } else {
            resultMsg = ResultMsg.errorMsg();
        }
        return resultMsg;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(EDIT_JSP);
        User user = userService.findUserById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ResultMsg delete(HttpServletRequest request) {
        ResultMsg resultMsg = null;
        String ids = request.getParameter("ids");
        List<Integer> list = StringUtil.StringToList(ids);
        userService.deleteUserByIds(list);
        resultMsg = ResultMsg.okMsg();
        resultMsg.setCallbackType(AppConstants.EMPTY);
        return resultMsg;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultMsg updateUser(@ModelAttribute User user,
            HttpServletRequest request) {
        ResultMsg resultMsg = null;

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = sf.format(new Date());
        user.setUpdatedTime(nowDate);
        Boolean bl = userService.updateUser(user);
        if (bl) {
            resultMsg = ResultMsg.okMsg();
        } else {
            resultMsg = ResultMsg.errorMsg();
        }
        return resultMsg;
    }
}
