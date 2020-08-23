package org.com.liurz.iresources.servcie.controller;

import com.github.pagehelper.PageInfo;

public class ResponseVO {

	private String status;

	private Object items;

	private PageInfo pageInfo;

	private String returnCode;

	private String message;

	public static ResponseVO create() {
		ResponseVO responseVO = new ResponseVO();
		responseVO.setStatus("success");
		responseVO.setReturnCode("0");
		return responseVO;
	}

	public static ResponseVO errorResult(String errorMessage) {
		ResponseVO responseVO = new ResponseVO();
		responseVO.setStatus("fail");
		responseVO.setReturnCode("-1");
		return responseVO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
