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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bank.common.AppConstants;
import com.bank.common.base.BasePageController;
import com.bank.common.base.ResultMsg;
import com.bank.common.model.Depositor;
import com.bank.common.model.Role;
import com.bank.common.service.DepositorService;
import com.bank.common.util.StringUtil;

@Controller
@RequestMapping("/page/depositor")
public class DepositorController extends BasePageController {
	private final String LOGIN_JSP = "user/login";
    private final String MAIN_JSP = "user/main";
    private final String LIST_JSP = "depositor/depositorList";
    private final String ADD_JSP = "depositor/addDepositor";
    private final String EDIT_JSP = "depositor/editDepositor";
    private final String ROLE_SET_PRIVILEGE = "role/roleSetPrivilege";

    private final Logger logger = Logger.getLogger(AccountController.class);

    @Autowired
    private DepositorService depositorService;

    @RequestMapping(value = "/search")
    public ModelAndView findRole(HttpServletRequest request,
            @RequestParam(value = "pageNum", defaultValue = "") String pageNum,
            @RequestParam(value = "numPerPage", defaultValue = "") String numPerPage,
            @RequestParam(value = "userName", defaultValue = "") String userName,
            @RequestParam(value = "beginTime", defaultValue = "") String beginTime,
            @RequestParam(value = "endTime", defaultValue = "") String endTime) {

        Integer pageNumInt = "".equals(pageNum) ? 1 : Integer.valueOf(pageNum);
        Integer numPerPageInt = "".equals(numPerPage) ? 10
                : Integer.valueOf(numPerPage);

        // String roleName = request.getParameter("roleName");
        final Map<String, Object> paramsMap = new HashMap<String, Object>();
        // paramsMap.put("roleName", roleName);
        paramsMap.put("beginTime", beginTime);
        paramsMap.put("endTime", endTime);

        return pagination(paramsMap, pageNumInt, numPerPageInt, request,
                LIST_JSP, new PaginationCallBack<Depositor>() {
                    @Override
                    public List<Depositor> callBack() {
                        return depositorService.findAllDepositorByParams(paramsMap);
                    }
                });
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addRole() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ADD_JSP);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultMsg saveRole(@ModelAttribute Depositor depositor,
            HttpServletRequest request, HttpServletResponse response) {
        ResultMsg resultMsg = null;
        try {
            Boolean bl = depositorService.saveDepositor(depositor);

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
    @RequestMapping(value = "update")
    public ResultMsg update(@ModelAttribute Depositor depositor,
            HttpServletRequest request, HttpServletResponse response) {
        ResultMsg resultMsg = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowDate = sf.format(new Date());
            depositor.setUpdatedTime(nowDate);
            Boolean bl = depositorService.updateDepositor(depositor);
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
        Depositor depositor = depositorService.findByDepositorId(id);
        mv.addObject("depositor", depositor);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public ResultMsg deleteRole(HttpServletRequest request,
            @RequestParam(value = "ids", defaultValue = "") String ids) {
        ResultMsg resultMsg = null;
        List<Integer> list = StringUtil.StringToList(ids);
        depositorService.deleteDepositorByIds(list);
        resultMsg = ResultMsg.okMsg();
        resultMsg.setCallbackType(AppConstants.EMPTY);
        return resultMsg;
    }
}
