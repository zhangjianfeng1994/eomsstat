package com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByAreaAndNetSrv;

public class StatisticsFaultByAreaAndNetSrvResponse implements java.io.Serializable{
	private String serviceFlag;
	private String serviceMessage;
	private String StatisticsFaultByAreaAndNetSrvInfo;
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
	public String getStatisticsFaultByAreaAndNetSrvInfo() {
		return StatisticsFaultByAreaAndNetSrvInfo;
	}
	public void setStatisticsFaultByAreaAndNetSrvInfo(
			String statisticsFaultByAreaAndNetSrvInfo) {
		StatisticsFaultByAreaAndNetSrvInfo = statisticsFaultByAreaAndNetSrvInfo;
	}	
}
