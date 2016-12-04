package com.bank.common.base;

public class ResultMsg {

    private Integer statusCode;// 状态码 200：操作成功 300：操作失败 301：会话超时
    private String message;// 返回信息
    private String navTabId;// 返回刷新的navTab
    private String rel;
    private String callbackType;// 返回类型 “closeCurrent”：关闭当前dialog
    private String forwardUrl;// “forward”：当设置为forward时，forwardUrl才起作用
    private String confirmMsg;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNavTabId() {
        return navTabId;
    }

    public void setNavTabId(String navTabId) {
        this.navTabId = navTabId;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(String callbackType) {
        this.callbackType = callbackType;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }

    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    public String getConfirmMsg() {
        return confirmMsg;
    }

    public void setConfirmMsg(String confirmMsg) {
        this.confirmMsg = confirmMsg;
    }

    public ResultMsg() {
        super();
    }

    public static ResultMsg okMsg() {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setStatusCode(200);
        resultMsg.setMessage("操作成功");
        resultMsg.setNavTabId("w_table");
        resultMsg.setCallbackType("closeCurrent");

        return resultMsg;
    }

    public static ResultMsg timeoutMsg() {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setStatusCode(301);
        resultMsg.setMessage("会话超时，请重新登录。");
        resultMsg.setNavTabId("");
        resultMsg.setCallbackType("");
        resultMsg.setForwardUrl("");
        return resultMsg;
    }


    public static ResultMsg errorMsg() {

        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setStatusCode(300);
        resultMsg.setMessage("操作失败");
        resultMsg.setNavTabId("w_table");
        resultMsg.setCallbackType("closeCurrent");

        return resultMsg;
    }

}
