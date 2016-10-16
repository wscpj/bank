<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
{
    "statusCode":"${model.statusCode}",
    "message":"${model.message}",
    "navTabId":"${model.navTabId}",
    "rel":"${model.rel}",
    "callbackType":"${model.callbackType}",
    "forwardUrl":"${model.forwardUrl}",
    "confirmMsg":"${model.confirmMsg}"
}
