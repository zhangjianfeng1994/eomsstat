package com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultListInfoSrv;

public class StatisticsFaultListInfoSrvResponse implements java.io.Serializable{
	private String serviceFlag;
	private String serviceMessage;
	private String StatisticsFaultListInfo;
	public String getServiceFlag() {
		return serviceFlag;
	}
	public void setServiceFlag(String serviceFlag) {
		this.serviceFlag = serviceFlag;
	}
	public String getServiceMessage() {
		return serviceMessage;
	}
	public void setServiceMessage(String serviceMessage) {
		this.serviceMessage = serviceMessage;
	}
	public String getStatisticsFaultListInfo() {
		return StatisticsFaultListInfo;
	}
	public void setStatisticsFaultListInfo(String statisticsFaultListInfo) {
		StatisticsFaultListInfo = statisticsFaultListInfo;
	}

}
