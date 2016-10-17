package com.bank.common.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bank.common.AppConstants;
import com.bank.common.base.BasePageController;
import com.bank.common.base.ResultMsg;
import com.bank.common.service.BackupService;

@Controller
@RequestMapping("/page/backup")
public class BackupController extends BasePageController {
    private final Logger logger = Logger.getLogger(BackupController.class);

    @Autowired
    private BackupService backupService;

    @RequestMapping(value = "/database", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg backup() {
        Boolean result = null;
        try {
            result= backupService.backupDataBase();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        ResultMsg resultMsg = null;
        if (result) {
            resultMsg = ResultMsg.okMsg();
        } else {
            resultMsg = ResultMsg.errorMsg();
        }
        resultMsg.setCallbackType(AppConstants.EMPTY);
        return resultMsg;
    }

}
