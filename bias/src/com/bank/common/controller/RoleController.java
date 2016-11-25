package com.bank.common.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bank.common.AppConstants;
import com.bank.common.base.BasePageController;
import com.bank.common.base.ResultMsg;
import com.bank.common.model.Role;
import com.bank.common.model.RolePrivilege;
import com.bank.common.service.PrivilegeService;
import com.bank.common.service.RolePrivilegeService;
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
    @Autowired
    private RolePrivilegeService rolePrivilegeService;

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
        String tree = roleService.roleSetPrivilegeBulidTree(id);
        mv.addObject("tree", tree);
        mv.addObject("roleId", id);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/saveRoleSetPrivilege", method = RequestMethod.POST)
    public ResultMsg saveRoleSetPrivilege(
            @RequestParam(value = "roleId", defaultValue = "") Integer roleId,
            @RequestParam(value = "privilegeIds", defaultValue = "") String privilegeIds) {
        if (roleId != null) {
            rolePrivilegeService.deleteRolePrivilege(roleId);
        }
        if (!privilegeIds.isEmpty()) {
            List<RolePrivilege> rolePrivileges = new ArrayList<RolePrivilege>();
            String[] ids = privilegeIds.split(",");
            for (String id : ids) {
                RolePrivilege rolePrivilege = new RolePrivilege();
                rolePrivilege.setRoleId(roleId);
                rolePrivilege.setPrivilegeId(Integer.parseInt(id));
                rolePrivileges.add(rolePrivilege);
            }
            rolePrivilegeService.addRolePrivilege(rolePrivileges);
        }
        return ResultMsg.okMsg();
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
            @RequestParam(value = "ids", defaultValue = "") String ids) {
        ResultMsg resultMsg = null;
        List<Integer> list = StringUtil.StringToList(ids);
        roleService.deleteRoleByIds(list);
        resultMsg = ResultMsg.okMsg();
        resultMsg.setCallbackType(AppConstants.EMPTY);
        return resultMsg;
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

        String roleName = request.getParameter("roleName");
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
