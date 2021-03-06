package com.bank.common.controller;

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
import com.bank.common.model.Privilege;
import com.bank.common.service.PrivilegeService;
import com.bank.common.util.StringUtil;

@Controller
@RequestMapping("/page/privilege")
public class PrivilegeController extends BasePageController {

    @SuppressWarnings("unused")
    private final Logger logger = Logger.getLogger(PrivilegeController.class);
    private final String LIST_JSP = "privilege/privilegeList";
    private final String ADD_JSP = "privilege/addPrivilege";
    private final String EDIT_JSP = "privilege/editPrivilege";
    private final String PARENT_PRIVILEGTE_JSP = "privilege/parentPrivilege";

    @Autowired
    private PrivilegeService privilegeService;

    @RequestMapping(value = "/search")
    public ModelAndView findPrivilege(HttpServletRequest request) {
        logger.info("findPrivilege parameters : {} " );
        ModelAndView result = null;
        try {
            String pageNum = request.getParameter("pageNum");
            String numPerPage = request.getParameter("numPerPage");
            Integer pageNumInt = pageNum == null ? 1 : Integer.valueOf(pageNum);
            Integer numPerPageInt = numPerPage == null ? 10 : Integer
                    .valueOf(numPerPage);

            String displayName = request.getParameter("displayName");
            String beginTime = request.getParameter("beginTime");
            String endTime = request.getParameter("endTime");
            final Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("displayName", displayName);
            paramsMap.put("beginTime", beginTime);
            paramsMap.put("endTime", endTime);

            result = pagination(paramsMap, pageNumInt, numPerPageInt, request,
                    LIST_JSP, new PaginationCallBack<Privilege>() {
                @Override
                public List<Privilege> callBack() {
                    return privilegeService.findAllPrivilege(paramsMap);
                }
            });
        } catch (Exception e) {
            logger.info("findPrivilege error" + e);
        }
        return result;

    }

    @RequestMapping(value = "/addPrivilege", method = RequestMethod.GET)
    public ModelAndView addPrivilege() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ADD_JSP);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/savePrivilege", method = RequestMethod.POST)
    public ResultMsg savePrivilege(@ModelAttribute Privilege privilege) {
        logger.info("savePrivilege privilege:{}" + privilege);
        ResultMsg resulMsg = null;
        try {
            Boolean bl = privilegeService.addPrivilege(privilege);
            if (bl) {
                resulMsg = ResultMsg.okMsg();
            } else {
                resulMsg = ResultMsg.errorMsg();
            }
        } catch (Exception e) {
            logger.info("savePrivilege error" + e);
        }
        return resulMsg;
    }

    @RequestMapping(value = "/editPrivilege/{id}")
    public ModelAndView editPrivilege(@PathVariable Integer id) {
        logger.info("editPrivilege id:{}" + id);
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName(EDIT_JSP);
            Privilege privilege = privilegeService.selectPrivilegeById(id);
            mv.addObject("privilege", privilege);
        } catch (Exception e) {
            logger.error("editPrivilege error" + e);
        }
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/updatePrivilege")
    public ResultMsg updatePrivilege(@ModelAttribute Privilege privilege) {
        logger.info("updatePrivilege privilege : {}" + privilege);
        ResultMsg resulMsg = null;
        try {
            Boolean bl = privilegeService.updatePrivilege(privilege);
            if (bl) {
                resulMsg = ResultMsg.okMsg();
            } else {
                resulMsg = ResultMsg.errorMsg();
            }
        } catch (Exception e) {
            logger.error("updatePrivilege error" + e);
        }
        return resulMsg;
    }

    @ResponseBody
    @RequestMapping(value = "deletePrivilege")
    public ResultMsg deletePrivilege(HttpServletRequest request,
            HttpServletResponse response) {
        logger.info("deletePrivilege parameters:{}");
        ResultMsg resultMsg = null;
        try {
            String ids = request.getParameter("ids");
            List<Integer> list = StringUtil.StringToList(ids);
            privilegeService.deletePrivilegeByIds(list);
            resultMsg = ResultMsg.okMsg();
            resultMsg.setCallbackType(AppConstants.EMPTY);
        } catch (Exception e) {
            logger.error("deletePrivilege error" + e);
        }
        return resultMsg;
    }

    @RequestMapping(value = "/searchParent")
    public ModelAndView searchParentPrivilege(
            @RequestParam(value = "pageNum", defaultValue = "") Integer pageNum,
            @RequestParam(value = "numPerPage", defaultValue = "") Integer numPerPage,
            @RequestParam(value = "displayName", defaultValue = "") String displayName
            ) {
        logger.info("searchParentPrivilege pageNum:{};numPerPage:{};displayName:{}" + pageNum + ":" + numPerPage + ":" + displayName);
        ModelAndView result = null;
        try {
            Integer pageNumInt = pageNum == null ? 1 : Integer.valueOf(pageNum);
            Integer numPerPageInt = numPerPage == null ? 5 : Integer
                    .valueOf(numPerPage);
            final Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("displayName", displayName);

            result =  pagination(paramsMap, pageNumInt, numPerPageInt, AppConstants.EMPTY,
                    PARENT_PRIVILEGTE_JSP, new PaginationCallBack<Privilege>() {
                @Override
                public List<Privilege> callBack() {
                    return privilegeService.findParentPrivileges(paramsMap);
                }
            });
        } catch (Exception e) {
            logger.error("searchParentPrivilege error" + e);
        }
        return result;
    }
}
