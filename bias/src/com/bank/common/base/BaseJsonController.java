package com.bank.common.base;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;
import com.bank.common.base.JsonMessage.MessageEntry;
import com.bank.common.exception.BusinessException;
import com.bank.common.exception.ValidationException;
import com.bank.common.model.PaginationDTO;
import com.bank.common.util.SpringUtil;


public class BaseJsonController extends BaseController {

    private static Logger log = Logger.getLogger(BaseJsonController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public MessageEntry handleException(Exception e) {
        log.error(e.getMessage(), e);
        if (e instanceof ValidationException) {
            ValidationException validationException = (ValidationException)e;
            return JsonMessage.error(501, validationException.getFieldErrors());
        }

        if (e instanceof BusinessException) {
            return JsonMessage.error(502, e.getMessage());
        }
        return JsonMessage.error(500, SpringUtil.getMessage(AppConstants.KEY_ERROR_UNKNOWN));
    }

    protected MessageEntry getNotLoginMessageEntry() {
        if (isLogin()) {
            return null;
        }
        return JsonMessage.error(AppConstants.ERROR_CODE_NOT_LOGIN, AppConstants.ERROR_MSG_NOT_LOGIN);
    }

    protected PaginationDTO<?> pagination(Integer currentPage, PaginationCallBack<?> paginationCallback) {
        return paginationCallback.execute(currentPage, null);
    }

    protected PaginationDTO<?> pagination(Integer currentPage, Integer pageSize, PaginationCallBack<?> paginationCallBack) {
        return paginationCallBack.execute(currentPage, pageSize);
    }

    protected abstract class PaginationCallBack<T> {
        public PaginationDTO<T> execute(Integer currentPage, Integer pageSize) {
            PaginationDTO<T> paginationDTO = new PaginationDTO<T>();
            AppContext.getContext().addObject(AppConstants.PAGINATION_DTO, paginationDTO);
            paginationDTO.setCurrentPage(currentPage);
            if (pageSize != null) {
                paginationDTO.setPageSize(pageSize);
            }
            List<T> itemList = callBack();
            paginationDTO.setItemList(itemList);
            return paginationDTO;
        }

        abstract public List<T> callBack();
    }
}
