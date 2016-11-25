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

import com.bank.common.AppConstants;
import com.bank.common.base.BasePageController;
import com.bank.common.base.ResultMsg;
import com.bank.common.model.Organization;
import com.bank.common.service.OrgService;
import com.bank.common.util.StringUtil;

@Controller
@RequestMapping("/page/org")
public class OrgController extends BasePageController {
    private final String ADD_JSP = "org/addOrg";
    private final String EDIT_JSP = "org/editOrg";
    private final String LIST_JSP = "org/orgList";

    private final Logger logger = Logger.getLogger(OrgController.class);

    @Autowired
    private OrgService orgService;

    @RequestMapping(value = "/search")
    public ModelAndView findOrgs(
            HttpServletRequest request,
            @RequestParam(value = "pageNum", defaultValue = "") String pageNum,
            @RequestParam(value = "numPerPage", defaultValue = "") String numPerPage,
            @RequestParam(value = "organizationName", defaultValue = "") String organizationName,
            @RequestParam(value = "beginTime", defaultValue = "") String beginTime,
            @RequestParam(value = "endTime", defaultValue = "") String endTime) {

        Integer pageNumInt = "".equals(pageNum) ? 1 : Integer.valueOf(pageNum);
        Integer numPerPageInt = "".equals(numPerPage) ? 10 : Integer
                .valueOf(numPerPage);

        final Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("organizationName", organizationName);
        paramsMap.put("beginTime", beginTime);
        paramsMap.put("endTime", endTime);

        return pagination(paramsMap, pageNumInt, numPerPageInt, request,
                LIST_JSP, new PaginationCallBack<Organization>() {
            @Override
            public List<Organization> callBack() {
                return orgService.searchOrganizations(paramsMap);
            }
        });
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addOrg() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ADD_JSP);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultMsg saveOrg(@ModelAttribute Organization org,
            HttpServletRequest request) {
        ResultMsg resultMsg = null;
        logger.info("saveOrg:" + org.toString());
        try {
            Boolean bl = orgService.addOrganization(org);
            if (bl) {
                resultMsg = ResultMsg.okMsg();
            } else {
                resultMsg = ResultMsg.errorMsg();
            }
        } catch (Exception e) {
            logger.error("saveOrg error:" + e);
        }
        return resultMsg;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(EDIT_JSP);
        Organization org = orgService.getOrgById(id);
        modelAndView.addObject("org", org);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ResultMsg deleteOrgs(HttpServletRequest request,
            @RequestParam(value = "ids", defaultValue = "") String ids) {
        ResultMsg resultMsg = null;
        logger.info("deleteOrgs ids:" + ids);
        try {
            List<Integer> list = StringUtil.StringToList(ids);
            orgService.deleteOrgByIds(list);
            resultMsg = ResultMsg.okMsg();
            resultMsg.setCallbackType(AppConstants.EMPTY);
        } catch (Exception e) {
            logger.error("deleteOrgs error:" + e);
        }
        return resultMsg;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultMsg updateOrg(@ModelAttribute Organization org,
            HttpServletRequest request) {
        ResultMsg resultMsg = null;
        logger.info("updateOrg info:" + org.toString());
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowDate = sf.format(new Date());
            org.setUpdatedTime(nowDate);
            Boolean bl = orgService.updateOrganization(org);
            if (bl) {
                resultMsg = ResultMsg.okMsg();
            } else {
                resultMsg = ResultMsg.errorMsg();
            }
        } catch (Exception e) {
            logger.error("updateOrg error:" + e);
        }
        return resultMsg;
    }
}
