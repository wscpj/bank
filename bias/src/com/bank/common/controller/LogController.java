package com.bank.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bank.common.AppConstants;
import com.bank.common.base.BasePageController;
import com.bank.common.base.ResultMsg;
import com.bank.common.model.Log;
import com.bank.common.service.LogService;
import com.bank.common.util.StringUtil;

@Controller
@RequestMapping("/page/log")
public class LogController extends BasePageController {
    private final String LIST_JSP = "log/logList";

    @SuppressWarnings("unused")
    private final Logger logger = Logger.getLogger(LogController.class);

    @Autowired
    private LogService logService;


    @RequestMapping(value = "/search")
    public ModelAndView findRole(
            @RequestParam(value = "pageNum", defaultValue = "") Integer pageNum,
            @RequestParam(value = "numPerPage", defaultValue = "") Integer numPerPage,
            @RequestParam(value = "userName", defaultValue = "") String userName,
            @RequestParam(value = "beginTime", defaultValue = "") String beginTime,
            @RequestParam(value = "endTime", defaultValue = "") String endTime
            ) {

        Integer pageNumInt = pageNum == null ? 1 : Integer.valueOf(pageNum);
        Integer numPerPageInt = numPerPage == null ? 10 : Integer
                .valueOf(numPerPage);

        final Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userName", userName);
        paramsMap.put("beginTime", beginTime);
        paramsMap.put("endTime", endTime);

        return pagination(paramsMap, pageNumInt, numPerPageInt, AppConstants.EMPTY,
                LIST_JSP, new PaginationCallBack<Log>() {
            @Override
            public List<Log> callBack() {
                return logService.searchLogs(paramsMap);
            }
        });
    }


    @ResponseBody
    @RequestMapping(value = "/delete")
    public ResultMsg delete(@RequestParam(value = "ids", defaultValue = "") String ids) {
        ResultMsg resultMsg = null;
        List<Integer> list = StringUtil.StringToList(ids);
        logService.deleteLogByIds(list);
        resultMsg = ResultMsg.okMsg();
        resultMsg.setCallbackType(AppConstants.EMPTY);
        return resultMsg;
    }
}
