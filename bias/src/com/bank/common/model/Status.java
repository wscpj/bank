package com.bank.common.model;

public class Status {
	private String statusCode;//状态码   200：操作成功   300：操作失败   301：会话超时
	private String message;//返回信息
	private String navTabId;//返回刷新的navTab
	private String rel;
	private String callbackType;//返回类型       “closeCurrent”：关闭当前dialog    “forward”：当设置为forward时，forwardUrl才起作用
	private String forwardUrl;
	private String confirmMsg;
	public Status() {
		super();
	}
	public Status(String statusCode, String message, String navTabId,
			String rel, String callbackType, String forwardUrl,
			String confirmMsg) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.navTabId = navTabId;
		this.rel = rel;
		this.callbackType = callbackType;
		this.forwardUrl = forwardUrl;
		this.confirmMsg = confirmMsg;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public String getMessage() {
		return message;
	}
	public String getNavTabId() {
		return navTabId;
	}
	public String getRel() {
		return rel;
	}
	public String getCallbackType() {
		return callbackType;
	}
	public String getForwardUrl() {
		return forwardUrl;
	}
	public String getConfirmMsg() {
		return confirmMsg;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}
	
}
