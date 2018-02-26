package com.boco.eoms.mobileApply.model.base;

import java.util.Date;


public class MsgHeader {
		
	private String serSupplier;
	private String serCaller;
	private String callerPwd;
	private Date callTime;
	private String opUserId;
	private String opUserName;
	private Date opTime;
	private String proviceCode;
	private int pageSize;
	private int currentPageIndex;
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPageIndex() {
		return currentPageIndex;
	}
	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}
	public String getSerSupplier() {
		return serSupplier;
	}
	public void setSerSupplier(String serSupplier) {
		this.serSupplier = serSupplier;
	}
	public String getSerCaller() {
		return serCaller;
	}
	public void setSerCaller(String serCaller) {
		this.serCaller = serCaller;
	}
	public String getCallerPwd() {
		return callerPwd;
	}
	public void setCallerPwd(String callerPwd) {
		this.callerPwd = callerPwd;
	}
	public String getOpUserId() {
		return opUserId;
	}
	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}
	public String getOpUserName() {
		return opUserName;
	}
	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}
	public Date getCallTime() {
		return callTime;
	}
	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	public String getProviceCode() {
		return proviceCode;
	}
	public void setProviceCode(String proviceCode) {
		this.proviceCode = proviceCode;
	}

}
