package com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultTypeByAreaSrv;

import java.util.List;

public class StatisticsFaultTypeByAreaSrvResponse implements java.io.Serializable{
	private String serviceFlag;
	private String serviceMessage;
	private String StatisticsFaultTypeByAreaSrvInfo;
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
	public String getStatisticsFaultTypeByAreaSrvInfo() {
		return StatisticsFaultTypeByAreaSrvInfo;
	}
	public void setStatisticsFaultTypeByAreaSrvInfo(String statisticsFaultTypeByAreaSrvInfo) {
		StatisticsFaultTypeByAreaSrvInfo = statisticsFaultTypeByAreaSrvInfo;
	}
}
