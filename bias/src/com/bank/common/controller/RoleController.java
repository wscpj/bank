package com.bank.common.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bank.common.AppConstants;
import com.bank.common.base.BasePageController;
import com.bank.common.base.ResultMsg;
import com.bank.common.model.Role;
import com.bank.common.service.PrivilegeService;
import com.bank.common.service.RoleService;
import com.bank.common.service.UserService;
import com.bank.common.util.StringUtil;

@Controller
@RequestMapping("/page/role")
public class RoleController extends BasePageController {
    private final String LOGIN_JSP = "user/login";
    private final String MAIN_JSP = "user/main";
    private final String LIST_JSP = "role/roleList";
    private final String ADD_JSP = "role/addRole";
    private final String EDIT_JSP = "role/editRole";
    private final String ROLE_SET_PRIVILEGE = "role/roleSetPrivilege";

    private final Logger logger = Logger.getLogger(RoleController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PrivilegeService privilegeService;

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

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addRole() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ADD_JSP);
        return mv;
    }

    @RequestMapping(value = "/roleSetPrivilege/{id}", method = RequestMethod.GET)
    public ModelAndView roleSetPrivilege(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ROLE_SET_PRIVILEGE);
        String tree = roleService.roleSetPrivilegeBulidTree();
        mv.addObject("tree", tree);
        return mv;
    }

    @RequestMapping(value = "/saveRoleSetPrivilege", method = RequestMethod.POST)
    public ModelAndView saveRoleSetPrivilege(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String[] a = request.getParameterValues("name");
        mv.setViewName(ROLE_SET_PRIVILEGE);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultMsg saveRole(@ModelAttribute Role role,
            HttpServletRequest request, HttpServletResponse response) {
        ResultMsg resultMsg = null;
        try {
            Boolean bl = roleService.saveRole(role);

            if (bl) {
                resultMsg = ResultMsg.okMsg();
            } else {
                resultMsg = ResultMsg.errorMsg();
            }
        } catch (Exception e) {
            logger.error("save role error", e);
        }
        return resultMsg;
    }

    @RequestMapping(value = "edit/{id}")
    public ModelAndView editRole(@PathVariable Integer id,
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(EDIT_JSP);
        Role role = roleService.findByRoleId(id);
        mv.addObject("role", role);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "updateRole")
    public ResultMsg updateRole(@ModelAttribute Role role,
            HttpServletRequest request, HttpServletResponse response) {
        ResultMsg resultMsg = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowDate = sf.format(new Date());
            role.setUpdatedTime(nowDate);
            Boolean bl = roleService.updateRole(role);
            if (bl) {
                resultMsg = ResultMsg.okMsg();
            } else {
                resultMsg = ResultMsg.errorMsg();
            }
        } catch (Exception e) {
            logger.error("save role error", e);
        }
        return resultMsg;
    }

    @ResponseBody
    @RequestMapping(value = "deleteRole")
    public ResultMsg deleteRole(HttpServletRequest request,
            HttpServletResponse response) {
        ResultMsg resultMsg = null;
        String ids = request.getParameter("ids");
        List<Integer> list = StringUtil.StringToList(ids);
        roleService.deleteRoleByIds(list);
        resultMsg = ResultMsg.okMsg();
        resultMsg.setCallbackType(AppConstants.EMPTY);
        return resultMsg;
    }

    @RequestMapping(value = "/search")
    public ModelAndView findRole(HttpServletRequest request) {

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
                LIST_JSP, new PaginationCallBack<Role>() {
                    @Override
                    public List<Role> callBack() {
                        return roleService.findAllRoleByParams(paramsMap);
                    }
                });
    }

}
