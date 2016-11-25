package com.bank.common.base;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.bank.common.AppConstants;
import com.bank.common.AppContext;
import com.bank.common.model.PaginationDTO;

public abstract class BasePageController extends BaseController {
    protected static final String JSP_MAIN_LAYOUT = "main-layout";
    protected static final String JSP_TEMPLET = "templet";
    protected static final String KEY_MAIN_BLOCK = "mainBlock";
    protected static final String USER_LOGIN = "userLogin";
    protected static final String URL_USER_LOGIN = "/page/user/login";
    protected static final String JSP_NOT_LOGIN_PAGINATION_VIEW = "user/not-login";
    private static final String FROM_URL = "fromUrl";

    private static Logger log = Logger.getLogger(BasePageController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        log.error(e.getMessage(), e);
        ModelAndView modelAndview = new ModelAndView();
        // TODO
        // should redirect to error page
        modelAndview.setViewName(JSP_NOT_LOGIN_PAGINATION_VIEW);
        return modelAndview;
    }

    protected String getRelativeUrl(HttpServletRequest request) {
        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        Integer beginIndex = url.indexOf('/', 1);// ignore context path
        if ("".equals(contextPath) || "/".equals(contextPath)) {
            beginIndex = 0;
        }
        Integer endIndex = url.lastIndexOf('/');
        url = url.substring(beginIndex, endIndex);
        return url;
    }

    protected ModelAndView getNotLoginModelAndView(HttpServletRequest request) {
        if (isLogin()) {
            return null;
        }
        ModelAndView modelAndView = new ModelAndView();
        String url = request.getContextPath() + URL_USER_LOGIN;
        modelAndView.addObject(FROM_URL, request.getRequestURI());
        modelAndView.setView(new RedirectView(url));
        return modelAndView;
    }

    protected ModelAndView getNotLoginViewForAjax() {
        if (isLogin()) {
            return null;
        }
        ModelAndView modelAndView = new ModelAndView(
                JSP_NOT_LOGIN_PAGINATION_VIEW);
        return modelAndView;
    }

    protected ModelAndView pagination(Map<String, Object> paramsMap,
            Integer currentPage, Integer pageSize, HttpServletRequest request,
            String jspPage, PaginationCallBack<?> paginationCallBack) {
        String relativeUrl = getRelativeUrl(request);
        return paginationCallBack.execute(paramsMap, currentPage, pageSize,
                relativeUrl, jspPage);
    }

    protected ModelAndView pagination(Map<String, Object> paramsMap,
            Integer currentPage, Integer pageSize, String relativeUrl,
            String jspPage, PaginationCallBack<?> paginationCallBack) {
        return paginationCallBack.execute(paramsMap, currentPage, pageSize,
                relativeUrl, jspPage);
    }

    protected abstract class PaginationCallBack<T> {

        public ModelAndView execute(Map<String, Object> paramsMap,
                Integer currentPage, Integer pageSize, String relativeUrl,
                String jspPage) {
            PaginationDTO<T> paginationDTO = new PaginationDTO<T>();
            if (currentPage != null) {
                paginationDTO.setCurrentPage(currentPage);
            }
            if (pageSize != null) {
                paginationDTO.setPageSize(pageSize);
            }
            paginationDTO.setRelativeUrl(relativeUrl);
            AppContext.getContext().addObject(AppConstants.PAGINATION_DTO,
                    paginationDTO);
            ModelAndView modelAndView = new ModelAndView(jspPage);
            List<T> itemList = callBack();
            paginationDTO.setItemList(itemList);
            modelAndView.addObject(AppConstants.PAGINATION_DTO, paginationDTO);
            modelAndView.addObject("paramsMap", paramsMap);
            return modelAndView;
        }

        abstract public List<T> callBack();

    }

}
