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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;
import com.bank.common.base.BasePageController;
import com.bank.common.exception.BusinessException;
import com.bank.common.exception.ValidationException;
import com.bank.common.model.Status;
import com.bank.common.model.User;
import com.bank.common.service.UserService;
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
    private final String STATUS_JSP = "common/status";

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

   /* @RequestMapping(value = "/find/{currentPage}", method = RequestMethod.GET)
    public ModelAndView findUser(@PathVariable Integer currentPage,
            HttpServletRequest request) {
    	Map<String, Object> paramsmap = new HashMap<String, Object>();
        return pagination(paramsmap, currentPage, null,request, LIST_JSP, new PaginationCallBack<User>() {
            @Override
            public List<User> callBack() {
                return userService.findUsers(paramsmap);
            }
        });
    }*/

    @RequestMapping(value = "/search", method = RequestMethod.GET)
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
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView findRole1(HttpServletRequest request) {

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
    
    /*@RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchUser(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "numPerPage") Integer numPerPage,
            @RequestParam(value = "keyword") final String keyword,
            @RequestParam(value = "date") final String date,
            HttpServletRequest request) {
    	Map<String, Object> paramsmap = new HashMap<String, Object>();
        return pagination(paramsmap, pageNum, numPerPage,request, LIST_JSP, new PaginationCallBack<User>() {
            @Override
            public List<User> callBack() {
                return userService.searchUsers(keyword, date);
            }
        });
    }*/

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(ADD_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute User user, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(STATUS_JSP);

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowDate = sf.format(new Date());
		user.setCreatedTime(nowDate);
		user.setUpdatedTime(nowDate);
		Boolean bl = userService.addUser(user);
		Status sta = null;
		if (bl) {
			sta = new Status("200", "操作成功", "userManage", "",
					"closeCurrent", "", "");
		} else {
			sta = new Status("300", "操作成功", "userManage", "",
					"closeCurrent", "", "");
		}
		modelAndView.addObject("model", sta);
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

    @RequestMapping(value = "/delete")
    public ModelAndView delete(HttpServletRequest request) {
    	String ids = request.getParameter("ids");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(STATUS_JSP);
        List<Integer> list = StringUtil.StringToList(ids);
        userService.deleteUserByIds(list);
        Status sta = new Status("200", "操作成功", "userManage", "", "forward", "",
				"");
        modelAndView.addObject("model", sta);
        return modelAndView;
    }
}
