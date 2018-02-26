package com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultStatusByAreaSrv;

import java.util.List;

public class StatisticsFaultStatusByAreaSrvResponse implements java.io.Serializable{
	private String serviceFlag;
	private String serviceMessage;
	private String StatisticsFaultStatusByAreaSrvInfo;
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
	public String getStatisticsFaultStatusByAreaSrvInfo() {
		return StatisticsFaultStatusByAreaSrvInfo;
	}
	public void setStatisticsFaultStatusByAreaSrvInfo(String statisticsFaultStatusByAreaSrvInfo) {
		StatisticsFaultStatusByAreaSrvInfo = statisticsFaultStatusByAreaSrvInfo;
	}
}
