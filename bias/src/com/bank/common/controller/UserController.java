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
import com.bank.common.dto.UserSetRoleDTO;
import com.bank.common.exception.BusinessException;
import com.bank.common.exception.ValidationException;
import com.bank.common.model.Log;
import com.bank.common.model.User;
import com.bank.common.model.UserRole;
import com.bank.common.service.LogService;
import com.bank.common.service.PrivilegeService;
import com.bank.common.service.RoleService;
import com.bank.common.service.UserRoleService;
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
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private PrivilegeService privilegeService;

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

    @ResponseBody
    @RequestMapping(value = "/saveUserSetRole", method = RequestMethod.POST)
    public ResultMsg saveUserSetRole(
            HttpServletRequest request,
            @RequestParam(value = "userId", defaultValue = "") Integer userId,
            @RequestParam(value = "roleId", defaultValue = "") String[] roleIdArr) {
        ResultMsg resultMsg = null;
        logger.info("saveUserSetRole, userId:{}" + userId + ";roleIdArr:{}"
                + roleIdArr.toString());
        try {
            userRoleService.deleteUserRoleByUserId(userId);

            UserRole userRole = new UserRole();
            for (String id : roleIdArr) {
                userRole.setUserId(userId);
                userRole.setRoleId(StringUtil.isNullToInt(id));
                userRoleService.insertUserRole(userRole);
            }
            resultMsg = ResultMsg.okMsg();
        } catch (Exception e) {
            logger.error("saveUserSetRole error:", e);
            resultMsg = ResultMsg.errorMsg();
        }

        return resultMsg;
    }

    @RequestMapping(value = "/userSetRole/{id}", method = RequestMethod.GET)
    public ModelAndView userSetRole(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(USERSETROLE);
        List<UserSetRoleDTO> list = userService.userSetRole(id);
        modelAndView.addObject("userRoleList", list);
        modelAndView.addObject("userId", id);
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView Login(HttpServletRequest request,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "password", defaultValue = "") String password) {
        ModelAndView modelAndView = new ModelAndView();
        logger.info("name:{}" + name + "password:{}" + password);
        try {
            User user = null;
            user = userService.login(name, password);
            user.setPassword(null);
            Log log = new Log();
            log.setUserName(name);
            log.setRoleName(AppConstants.EMPTY);
            log.setIp(RequestUtil.getIpAddr(request));
            logService.addLog(log);
            String privileges = privilegeService.findPrivilegeByUserId(user
                    .getId());
            this.addSession("trees", privileges);
            this.addSession(AppConstants.USER, user);
            this.addSession(AppConstants.ROLES, AppConstants.ROLES);
            modelAndView.setView(new RedirectView(AppContext.getContextPath()
                    + "/" + DASHBOARD));

        } catch (ValidationException validationException) {
            Map<String, String> errorFilds = validationException
                    .getFieldErrors();
            modelAndView.addObject(AppConstants.ERROR_FILDS, errorFilds);
            modelAndView.setViewName(LOGIN_JSP);
            logger.info("Login error:", validationException);

        } catch (BusinessException businessException) {
            modelAndView.addObject(AppConstants.MESSAGE,
                    businessException.getErrorMessage() + "  ["
                            + businessException.getCode() + "]");
            modelAndView.addObject(AppConstants.VISIBILITY, "visible");
            modelAndView.setViewName(LOGIN_JSP);
            logger.warn("Login error:", businessException);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/search")
    public ModelAndView findRole(
            HttpServletRequest request,
            @RequestParam(value = "pageNum", defaultValue = "") String pageNum,
            @RequestParam(value = "numPerPage", defaultValue = "") String numPerPage,
            @RequestParam(value = "userName", defaultValue = "") String userName,
            @RequestParam(value = "beginTime", defaultValue = "") String beginTime,
            @RequestParam(value = "endTime", defaultValue = "") String endTime) {

        Integer pageNumInt = "".equals(pageNum) ? 1 : Integer.valueOf(pageNum);
        Integer numPerPageInt = "".equals(numPerPage) ? 10 : Integer
                .valueOf(numPerPage);

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
        logger.info("saveUser:" + user.toString());
        try {
            Boolean bl = userService.addUser(user);
            if (bl) {
                resultMsg = ResultMsg.okMsg();
            } else {
                resultMsg = ResultMsg.errorMsg();
            }
        } catch (Exception e) {
            logger.error("saveUser error:" + e);
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
    public ResultMsg delete(HttpServletRequest request,
            @RequestParam(value = "ids", defaultValue = "") String ids) {
        ResultMsg resultMsg = null;
        logger.info("deleteUser userIds:" + ids);
        try {
            List<Integer> list = StringUtil.StringToList(ids);
            userService.deleteUserByIds(list);
            resultMsg = ResultMsg.okMsg();
            resultMsg.setCallbackType(AppConstants.EMPTY);
        } catch (Exception e) {
            logger.error("delete user error:" + e);
        }
        return resultMsg;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultMsg updateUser(@ModelAttribute User user,
            HttpServletRequest request) {
        ResultMsg resultMsg = null;
        logger.info("updateUser info:" + user.toString());
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowDate = sf.format(new Date());
            user.setUpdatedTime(nowDate);
            Boolean bl = userService.updateUser(user);
            if (bl) {
                resultMsg = ResultMsg.okMsg();
            } else {
                resultMsg = ResultMsg.errorMsg();
            }
        } catch (Exception e) {
            logger.error("updateUser error:" + e);
        }
        return resultMsg;
    }
}
