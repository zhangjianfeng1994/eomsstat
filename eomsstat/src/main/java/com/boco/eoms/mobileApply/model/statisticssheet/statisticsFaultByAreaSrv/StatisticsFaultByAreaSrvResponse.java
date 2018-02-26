package com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByAreaSrv;

public class StatisticsFaultByAreaSrvResponse implements java.io.Serializable{
	private String serviceFlag;
	private String serviceMessage;
	private String StatisticsFaultByAreaInfo;
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
	public String getStatisticsFaultByAreaInfo() {
		return StatisticsFaultByAreaInfo;
	}
	public void setStatisticsFaultByAreaInfo(String statisticsFaultByAreaInfo) {
		StatisticsFaultByAreaInfo = statisticsFaultByAreaInfo;
	}

}
