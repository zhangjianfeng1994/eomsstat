package com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultStatusByCountrySrv;

import java.util.List;

public class StatisticsFaultStatusByCountrySrvResponse implements java.io.Serializable{
	private String serviceFlag;
	private String serviceMessage;
	private String StatisticsFaultStatusByCountrySrvInfo;
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
	public String getStatisticsFaultStatusByCountrySrvInfo() {
		return StatisticsFaultStatusByCountrySrvInfo;
	}
	public void setStatisticsFaultStatusByCountrySrvInfo(String statisticsFaultStatusByCountrySrvInfo) {
		StatisticsFaultStatusByCountrySrvInfo = statisticsFaultStatusByCountrySrvInfo;
	}
}
