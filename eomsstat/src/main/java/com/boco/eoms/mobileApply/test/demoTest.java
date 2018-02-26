package com.boco.eoms.mobileApply.test;

import com.boco.eoms.mobileApply.model.base.MsgHeader;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByAreaSrv.StatisticsFaultByAreaSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByAreaSrv.StatisticsFaultByAreaSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsNewFaultListInfoSrv.StatisticsNewFaultListInfoSrvResponse;

public class demoTest {
	
	
	public static void main(String [] args){
		
		MsgHeader arg0=new MsgHeader();
		StatisticsFaultByAreaSrvRequest arg1=new StatisticsFaultByAreaSrvRequest();
		
		
		//StatisticsFaultByAreaSrv statisticsFaultByAreaSrv = new StatisticsFaultByAreaSrv();
	    StatisticsFaultByAreaSrvResponse statisticsFaultByAreaSrvResponse = new StatisticsFaultByAreaSrvResponse();
	    try {
	     // statisticsFaultByAreaSrvResponse = statisticsFaultByAreaSrv.statisticsFaultByAreaSrv(arg0, arg1);
	    } catch (Exception e) {
	      statisticsFaultByAreaSrvResponse.setServiceFlag("FALSE");
	      statisticsFaultByAreaSrvResponse.setServiceMessage("服务器异常，请稍后重试！");
	      e.printStackTrace();
	    }
		
		
		
	}
	
	

}
