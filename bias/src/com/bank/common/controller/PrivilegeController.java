package com.bank.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bank.common.base.BasePageController;
import com.bank.common.dao.PrivilegeDao;
import com.bank.common.model.Privilege;
import com.bank.common.service.UserService;

@Controller
@RequestMapping("/page/privilege")
public class PrivilegeController extends BasePageController {
    
    private final Logger logger = Logger.getLogger(PrivilegeController.class);
    private final String LOGIN_JSP = "user/login";
    private final String LIST_JSP = "privilege/privilegeList";

    @Autowired
    private UserService userService;
    @Autowired
    private PrivilegeDao privilegeDao;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(LOGIN_JSP);
        return modelAndView;
    }
    
    @RequestMapping(value = "/search")
    public ModelAndView findPrivilege(HttpServletRequest request) {

        String pageNum = request.getParameter("pageNum");
        String numPerPage = request.getParameter("numPerPage");
        Integer pageNumInt = pageNum == null ? 1 : Integer.valueOf(pageNum);
        Integer numPerPageInt = numPerPage == null ? 10 : Integer
                .valueOf(numPerPage);

        String roleName = request.getParameter("roleName");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        final Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("roleName", roleName);
        paramsMap.put("beginTime", beginTime);
        paramsMap.put("endTime", endTime);

        return pagination(paramsMap, pageNumInt, numPerPageInt, request,
                LIST_JSP, new PaginationCallBack<Privilege>() {
                    @Override
                    public List<Privilege> callBack() {
                        return privilegeDao.findAllPrivilege(paramsMap);
                    }
                });
    }

}
