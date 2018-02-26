package com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultTypeByCountrySrv;

import java.util.List;

public class StatisticsFaultTypeByCountrySrvResponse implements java.io.Serializable{
	private String serviceFlag;
	private String serviceMessage;
	private String StatisticsFaultTypeByCountrySrvInfo;
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
	public String getStatisticsFaultTypeByCountrySrvInfo() {
		return StatisticsFaultTypeByCountrySrvInfo;
	}
	public void setStatisticsFaultTypeByCountrySrvInfo(String statisticsFaultTypeByCountrySrvInfo) {
		StatisticsFaultTypeByCountrySrvInfo = statisticsFaultTypeByCountrySrvInfo;
	}
}
