package com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByAreaAndCountrySrv;

public class StatisticsFaultByAreaAndCountrySrvResponse implements java.io.Serializable{
	private String serviceFlag;
	private String serviceMessage;
	private String StatisticsFaultByAreaAndCountryInfo;
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
	public String getStatisticsFaultByAreaAndCountryInfo() {
		return StatisticsFaultByAreaAndCountryInfo;
	}
	public void setStatisticsFaultByAreaAndCountryInfo(
			String statisticsFaultByAreaAndCountryInfo) {
		StatisticsFaultByAreaAndCountryInfo = statisticsFaultByAreaAndCountryInfo;
	}
	
}
