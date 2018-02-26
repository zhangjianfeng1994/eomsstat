package com.boco.eoms.mobileApply.service.statisticssheet;

import org.apache.log4j.Logger;

import com.boco.eoms.mobileApply.model.base.MsgHeader;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByAreaAndCountrySrv.StatisticsFaultByAreaAndCountrySrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByAreaAndCountrySrv.StatisticsFaultByAreaAndCountrySrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByAreaAndNetSrv.StatisticsFaultByAreaAndNetSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByAreaAndNetSrv.StatisticsFaultByAreaAndNetSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByAreaAndNetTwoSrv.StatisticsFaultByAreaAndNetTwoSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByAreaAndNetTwoSrv.StatisticsFaultByAreaAndNetTwoSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByAreaSrv.StatisticsFaultByAreaSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByAreaSrv.StatisticsFaultByAreaSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByNetAndAreaSrv.StatisticsFaultByNetAndAreaSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByNetAndAreaSrv.StatisticsFaultByNetAndAreaSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByNetAndCountrySrv.StatisticsFaultByNetAndCountrySrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByNetAndCountrySrv.StatisticsFaultByNetAndCountrySrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByNetSrv.StatisticsFaultByNetSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByNetSrv.StatisticsFaultByNetSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByNetTwoAndCountrySrv.StatisticsFaultByNetTwoAndCountrySrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultByNetTwoAndCountrySrv.StatisticsFaultByNetTwoAndCountrySrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultDealRateByAreaSrv.StatisticsFaultDealRateByAreaSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultDealRateByAreaSrv.StatisticsFaultDealRateByAreaSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultDealRateByCountrySrv.StatisticsFaultDealRateByCountrySrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultDealRateByCountrySrv.StatisticsFaultDealRateByCountrySrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultListInfoSrv.StatisticsFaultListInfoSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultListInfoSrv.StatisticsFaultListInfoSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultLockStation2GByAreaSrv.StatisticsFaultLockStation2GByAreaSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultLockStation2GByAreaSrv.StatisticsFaultLockStation2GByAreaSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultLockStation2GByCountrySrv.StatisticsFaultLockStation2GByCountrySrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultLockStation2GByCountrySrv.StatisticsFaultLockStation2GByCountrySrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultLockStation3GByAreaSrv.StatisticsFaultLockStation3GByAreaSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultLockStation3GByAreaSrv.StatisticsFaultLockStation3GByAreaSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultLockStation3GByCountrySrv.StatisticsFaultLockStation3GByCountrySrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultLockStation3GByCountrySrv.StatisticsFaultLockStation3GByCountrySrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultLockStation4GByAreaSrv.StatisticsFaultLockStation4GByAreaSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultLockStation4GByAreaSrv.StatisticsFaultLockStation4GByAreaSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultLockStation4GByCountrySrv.StatisticsFaultLockStation4GByCountrySrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultLockStation4GByCountrySrv.StatisticsFaultLockStation4GByCountrySrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultLockStationListInfoSrv.StatisticsFaultLockStationListInfoSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultLockStationListInfoSrv.StatisticsFaultLockStationListInfoSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutService2GByAreaSrv.StatisticsFaultOutService2GByAreaSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutService2GByAreaSrv.StatisticsFaultOutService2GByAreaSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutService2GByCountrySrv.StatisticsFaultOutService2GByCountrySrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutService2GByCountrySrv.StatisticsFaultOutService2GByCountrySrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutService3GByAreaSrv.StatisticsFaultOutService3GByAreaSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutService3GByAreaSrv.StatisticsFaultOutService3GByAreaSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutService3GByCountrySrv.StatisticsFaultOutService3GByCountrySrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutService3GByCountrySrv.StatisticsFaultOutService3GByCountrySrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutService4GByAreaSrv.StatisticsFaultOutService4GByAreaSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutService4GByAreaSrv.StatisticsFaultOutService4GByAreaSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutService4GByCountrySrv.StatisticsFaultOutService4GByCountrySrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutService4GByCountrySrv.StatisticsFaultOutService4GByCountrySrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutServiceListInfoSrv.StatisticsFaultOutServiceListInfoSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutServiceListInfoSrv.StatisticsFaultOutServiceListInfoSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutServiceRRUByAreaSrv.StatisticsFaultOutServiceRRUByAreaSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutServiceRRUByAreaSrv.StatisticsFaultOutServiceRRUByAreaSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutServiceRRUByCountrySrv.StatisticsFaultOutServiceRRUByCountrySrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutServiceRRUByCountrySrv.StatisticsFaultOutServiceRRUByCountrySrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutServiceWLANByAreaSrv.StatisticsFaultOutServiceWLANByAreaSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutServiceWLANByAreaSrv.StatisticsFaultOutServiceWLANByAreaSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutServiceWLANByCountrySrv.StatisticsFaultOutServiceWLANByCountrySrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultOutServiceWLANByCountrySrv.StatisticsFaultOutServiceWLANByCountrySrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultPowerCutByAreaSrv.StatisticsFaultPowerCutByAreaSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultPowerCutByAreaSrv.StatisticsFaultPowerCutByAreaSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultPowerCutByCountrySrv.StatisticsFaultPowerCutByCountrySrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultPowerCutByCountrySrv.StatisticsFaultPowerCutByCountrySrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultPowerCutListInfoSrv.StatisticsFaultPowerCutListInfoSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultPowerCutListInfoSrv.StatisticsFaultPowerCutListInfoSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultStatusByAreaSrv.StatisticsFaultStatusByAreaSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultStatusByAreaSrv.StatisticsFaultStatusByAreaSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultStatusByCountrySrv.StatisticsFaultStatusByCountrySrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultStatusByCountrySrv.StatisticsFaultStatusByCountrySrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultTypeByAreaSrv.StatisticsFaultTypeByAreaSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultTypeByAreaSrv.StatisticsFaultTypeByAreaSrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultTypeByCountrySrv.StatisticsFaultTypeByCountrySrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsFaultTypeByCountrySrv.StatisticsFaultTypeByCountrySrvResponse;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsNewFaultListInfoSrv.StatisticsNewFaultListInfoSrvRequest;
import com.boco.eoms.mobileApply.model.statisticssheet.statisticsNewFaultListInfoSrv.StatisticsNewFaultListInfoSrvResponse;
import com.boco.eoms.mobileApply.util.StatisticsSheetInquiry;







public class StatisticsSheetProvideSrv
{

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(StatisticsSheetProvideSrv.class);
    public StatisticsSheetProvideSrv()
    {
    }

    public StatisticsFaultByAreaSrvResponse statisticsFaultByAreaSrv(MsgHeader msgHeader, StatisticsFaultByAreaSrvRequest statisticsFaultByAreaSrvRequest)
    {
        StatisticsFaultByAreaSrvResponse statisticsFaultByAreaSrvResponse = new StatisticsFaultByAreaSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        
        statisticsFaultByAreaSrvResponse = util.statisticsFaultByAreaSrv(msgHeader, statisticsFaultByAreaSrvRequest);
        return statisticsFaultByAreaSrvResponse;
    }

    
    public StatisticsFaultByAreaAndCountrySrvResponse statisticsFaultByAreaAndCountrySrv(MsgHeader msgHeader, StatisticsFaultByAreaAndCountrySrvRequest statisticsFaultByAreaAndCountrySrvRequest)
    {
        StatisticsFaultByAreaAndCountrySrvResponse statisticsFaultByAreaAndCountrySrvResponse = new StatisticsFaultByAreaAndCountrySrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultByAreaAndCountrySrvResponse = util.statisticsFaultByAreaAndCountrySrv(msgHeader, statisticsFaultByAreaAndCountrySrvRequest);
        return statisticsFaultByAreaAndCountrySrvResponse;
    }

    public StatisticsFaultByAreaAndNetSrvResponse StatisticsFaultByAreaAndNetSrv(MsgHeader msgHeader, StatisticsFaultByAreaAndNetSrvRequest statisticsFaultByAreaAndNetSrvRequest)
    {
        StatisticsFaultByAreaAndNetSrvResponse statisticsFaultByAreaAndNetSrvResponse = new StatisticsFaultByAreaAndNetSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultByAreaAndNetSrvResponse = util.statisticsFaultByAreaAndNetSrv(msgHeader, statisticsFaultByAreaAndNetSrvRequest);
        return statisticsFaultByAreaAndNetSrvResponse;
    }
   
    public StatisticsFaultByAreaAndNetTwoSrvResponse statisticsFaultByAreaAndNetTwoSrv(MsgHeader msgHeader, StatisticsFaultByAreaAndNetTwoSrvRequest statisticsFaultByAreaAndNetTwoSrvRequest)
    {
        StatisticsFaultByAreaAndNetTwoSrvResponse statisticsFaultByAreaAndNetTwoSrvResponse = new StatisticsFaultByAreaAndNetTwoSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultByAreaAndNetTwoSrvResponse = util.statisticsFaultByAreaAndNetTwoSrv(msgHeader, statisticsFaultByAreaAndNetTwoSrvRequest);
        return statisticsFaultByAreaAndNetTwoSrvResponse;
    }

    public StatisticsFaultByNetSrvResponse statisticsFaultByNetSrv(MsgHeader msgHeader, StatisticsFaultByNetSrvRequest statisticsFaultByNetSrvRequest)
    {
        StatisticsFaultByNetSrvResponse statisticsFaultByNetSrvResponse = new StatisticsFaultByNetSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultByNetSrvResponse = util.statisticsFaultByNetSrv(msgHeader, statisticsFaultByNetSrvRequest);
        return statisticsFaultByNetSrvResponse;
    }

    public StatisticsFaultByNetAndAreaSrvResponse statisticsFaultByNetAndAreaSrv(MsgHeader msgHeader, StatisticsFaultByNetAndAreaSrvRequest statisticsFaultByNetAndAreaSrvRequest)
    {
        StatisticsFaultByNetAndAreaSrvResponse statisticsFaultByNetAndAreaSrvResponse = new StatisticsFaultByNetAndAreaSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultByNetAndAreaSrvResponse = util.statisticsFaultByNetAndAreaSrv(msgHeader, statisticsFaultByNetAndAreaSrvRequest);
        return statisticsFaultByNetAndAreaSrvResponse;
    }

    public StatisticsFaultByNetAndCountrySrvResponse statisticsFaultByNetAndCountrySrv(MsgHeader msgHeader, StatisticsFaultByNetAndCountrySrvRequest statisticsFaultByNetAndCountrySrvRequest)
    {
        StatisticsFaultByNetAndCountrySrvResponse statisticsFaultByNetAndCountrySrvResponse = new StatisticsFaultByNetAndCountrySrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultByNetAndCountrySrvResponse = util.statisticsFaultByNetAndCountrySrv(msgHeader, statisticsFaultByNetAndCountrySrvRequest);
        return statisticsFaultByNetAndCountrySrvResponse;
    }

    public StatisticsFaultByNetTwoAndCountrySrvResponse statisticsFaultByNetTwoAndCountrySrv(MsgHeader msgHeader, StatisticsFaultByNetTwoAndCountrySrvRequest statisticsFaultByNetTwoAndCountrySrvRequest)
    {
        StatisticsFaultByNetTwoAndCountrySrvResponse statisticsFaultByNetTwoAndCountrySrvResponse = new StatisticsFaultByNetTwoAndCountrySrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultByNetTwoAndCountrySrvResponse = util.statisticsFaultByNetTwoAndCountrySrv(msgHeader, statisticsFaultByNetTwoAndCountrySrvRequest);
        return statisticsFaultByNetTwoAndCountrySrvResponse;
    }

    public StatisticsFaultOutService2GByAreaSrvResponse statisticsFaultOutService2GByAreaSrv(MsgHeader msgHeader, StatisticsFaultOutService2GByAreaSrvRequest statisticsFaultOutService2GByAreaSrvRequest)
    {
        StatisticsFaultOutService2GByAreaSrvResponse statisticsFaultOutService2GByAreaSrvResponse = new StatisticsFaultOutService2GByAreaSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultOutService2GByAreaSrvResponse = util.statisticsFaultOutService2GByAreaSrv(msgHeader, statisticsFaultOutService2GByAreaSrvRequest);
        return statisticsFaultOutService2GByAreaSrvResponse;
    }

    public StatisticsFaultOutService2GByCountrySrvResponse statisticsFaultOutService2GByCountrySrv(MsgHeader msgHeader, StatisticsFaultOutService2GByCountrySrvRequest statisticsFaultOutService2GByCountrySrvRequest)
    {
        StatisticsFaultOutService2GByCountrySrvResponse statisticsFaultOutService2GByCountrySrvResponse = new StatisticsFaultOutService2GByCountrySrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultOutService2GByCountrySrvResponse = util.statisticsFaultOutService2GByCountrySrv(msgHeader, statisticsFaultOutService2GByCountrySrvRequest);
        return statisticsFaultOutService2GByCountrySrvResponse;
    }

    public StatisticsFaultOutService3GByAreaSrvResponse statisticsFaultOutService3GByAreaSrv(MsgHeader msgHeader, StatisticsFaultOutService3GByAreaSrvRequest statisticsFaultOutService3GByAreaSrvRequest)
    {
        StatisticsFaultOutService3GByAreaSrvResponse statisticsFaultOutService3GByAreaSrvResponse = new StatisticsFaultOutService3GByAreaSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultOutService3GByAreaSrvResponse = util.statisticsFaultOutService3GByAreaSrv(msgHeader, statisticsFaultOutService3GByAreaSrvRequest);
        return statisticsFaultOutService3GByAreaSrvResponse;
    }

    public StatisticsFaultOutService3GByCountrySrvResponse statisticsFaultOutService3GByCountrySrv(MsgHeader msgHeader, StatisticsFaultOutService3GByCountrySrvRequest statisticsFaultOutService3GByCountrySrvRequest)
    {
        StatisticsFaultOutService3GByCountrySrvResponse statisticsFaultOutService3GByCountrySrvResponse = new StatisticsFaultOutService3GByCountrySrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultOutService3GByCountrySrvResponse = util.statisticsFaultOutService3GByCountrySrv(msgHeader, statisticsFaultOutService3GByCountrySrvRequest);
        return statisticsFaultOutService3GByCountrySrvResponse;
    }

    public StatisticsFaultOutServiceRRUByAreaSrvResponse statisticsFaultOutServiceRRUByAreaSrv(MsgHeader msgHeader, StatisticsFaultOutServiceRRUByAreaSrvRequest statisticsFaultOutServiceRRUByAreaSrvRequest)
    {
        StatisticsFaultOutServiceRRUByAreaSrvResponse statisticsFaultOutServiceRRUByAreaSrvResponse = new StatisticsFaultOutServiceRRUByAreaSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultOutServiceRRUByAreaSrvResponse = util.statisticsFaultOutServiceRRUByAreaSrv(msgHeader, statisticsFaultOutServiceRRUByAreaSrvRequest);
        return statisticsFaultOutServiceRRUByAreaSrvResponse;
    }

    public StatisticsFaultOutServiceRRUByCountrySrvResponse statisticsFaultOutServiceRRUByCountrySrv(MsgHeader msgHeader, StatisticsFaultOutServiceRRUByCountrySrvRequest statisticsFaultOutServiceRRUByCountrySrvRequest)
    {
        StatisticsFaultOutServiceRRUByCountrySrvResponse statisticsFaultOutServiceRRUByCountrySrvResponse = new StatisticsFaultOutServiceRRUByCountrySrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultOutServiceRRUByCountrySrvResponse = util.statisticsFaultOutServiceRRUByCountrySrv(msgHeader, statisticsFaultOutServiceRRUByCountrySrvRequest);
        return statisticsFaultOutServiceRRUByCountrySrvResponse;
    }

    public StatisticsFaultOutService4GByAreaSrvResponse statisticsFaultOutService4GByAreaSrv(MsgHeader msgHeader, StatisticsFaultOutService4GByAreaSrvRequest statisticsFaultOutService4GByAreaSrvRequest)
    {
        StatisticsFaultOutService4GByAreaSrvResponse statisticsFaultOutService4GByAreaSrvResponse = new StatisticsFaultOutService4GByAreaSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultOutService4GByAreaSrvResponse = util.statisticsFaultOutService4GByAreaSrv(msgHeader, statisticsFaultOutService4GByAreaSrvRequest);
        return statisticsFaultOutService4GByAreaSrvResponse;
    }

    public StatisticsFaultOutService4GByCountrySrvResponse statisticsFaultOutService4GByCountrySrv(MsgHeader msgHeader, StatisticsFaultOutService4GByCountrySrvRequest statisticsFaultOutService4GByCountrySrvRequest)
    {
        StatisticsFaultOutService4GByCountrySrvResponse statisticsFaultOutService4GByCountrySrvResponse = new StatisticsFaultOutService4GByCountrySrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultOutService4GByCountrySrvResponse = util.statisticsFaultOutService4GByCountrySrv(msgHeader, statisticsFaultOutService4GByCountrySrvRequest);
        return statisticsFaultOutService4GByCountrySrvResponse;
    }

    public StatisticsFaultOutServiceWLANByAreaSrvResponse statisticsFaultOutServiceWLANByAreaSrv(MsgHeader msgHeader, StatisticsFaultOutServiceWLANByAreaSrvRequest statisticsFaultOutServiceWLANByAreaSrvRequest)
    {
        StatisticsFaultOutServiceWLANByAreaSrvResponse statisticsFaultOutServiceWLANByAreaSrvResponse = new StatisticsFaultOutServiceWLANByAreaSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultOutServiceWLANByAreaSrvResponse = util.statisticsFaultOutServiceWLANByAreaSrv(msgHeader, statisticsFaultOutServiceWLANByAreaSrvRequest);
        return statisticsFaultOutServiceWLANByAreaSrvResponse;
    }

    public StatisticsFaultOutServiceWLANByCountrySrvResponse statisticsFaultOutServiceWLANByCountrySrv(MsgHeader msgHeader, StatisticsFaultOutServiceWLANByCountrySrvRequest statisticsFaultOutServiceWLANByCountrySrvRequest)
    {
    	System.out.println("statisticsFaultOutServiceWLANByCountrySrv start");
    	StatisticsFaultOutServiceWLANByCountrySrvResponse statisticsFaultOutServiceWLANByCountrySrvResponse = new StatisticsFaultOutServiceWLANByCountrySrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultOutServiceWLANByCountrySrvResponse = util.statisticsFaultOutServiceWLANByCountrySrv(msgHeader, statisticsFaultOutServiceWLANByCountrySrvRequest);
        System.out.println("statisticsFaultOutServiceWLANByCountrySrv end");
        return statisticsFaultOutServiceWLANByCountrySrvResponse;
    }

    public StatisticsFaultPowerCutByAreaSrvResponse statisticsFaultPowerCutByAreaSrv(MsgHeader msgHeader, StatisticsFaultPowerCutByAreaSrvRequest statisticsFaultPowerCutByAreaSrvRequest)
    {
        StatisticsFaultPowerCutByAreaSrvResponse statisticsFaultPowerCutByAreaSrvResponse = new StatisticsFaultPowerCutByAreaSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultPowerCutByAreaSrvResponse = util.statisticsFaultPowerCutByAreaSrv(msgHeader, statisticsFaultPowerCutByAreaSrvRequest);
        return statisticsFaultPowerCutByAreaSrvResponse;
    }

    public StatisticsFaultPowerCutByCountrySrvResponse statisticsFaultPowerCutByCountrySrv(MsgHeader msgHeader, StatisticsFaultPowerCutByCountrySrvRequest statisticsFaultPowerCutByCountrySrvRequest)
    {
        StatisticsFaultPowerCutByCountrySrvResponse statisticsFaultPowerCutByCountrySrvResponse = new StatisticsFaultPowerCutByCountrySrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultPowerCutByCountrySrvResponse = util.statisticsFaultPowerCutByCountrySrv(msgHeader, statisticsFaultPowerCutByCountrySrvRequest);
        return statisticsFaultPowerCutByCountrySrvResponse;
    }

    public StatisticsFaultLockStation2GByAreaSrvResponse statisticsFaultLockStation2GByAreaSrv(MsgHeader msgHeader, StatisticsFaultLockStation2GByAreaSrvRequest statisticsFaultLockStation2GByAreaSrvRequest)
    {
        StatisticsFaultLockStation2GByAreaSrvResponse statisticsFaultLockStation2GByAreaSrvResponse = new StatisticsFaultLockStation2GByAreaSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultLockStation2GByAreaSrvResponse = util.statisticsFaultLockStation2GByAreaSrv(msgHeader, statisticsFaultLockStation2GByAreaSrvRequest);
        return statisticsFaultLockStation2GByAreaSrvResponse;
    }

    public StatisticsFaultLockStation2GByCountrySrvResponse statisticsFaultLockStation2GByCountrySrv(MsgHeader msgHeader, StatisticsFaultLockStation2GByCountrySrvRequest statisticsFaultLockStation2GByCountrySrvRequest)
    {
        StatisticsFaultLockStation2GByCountrySrvResponse statisticsFaultLockStation2GByCountrySrvResponse = new StatisticsFaultLockStation2GByCountrySrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultLockStation2GByCountrySrvResponse = util.statisticsFaultLockStation2GByCountrySrv(msgHeader, statisticsFaultLockStation2GByCountrySrvRequest);
        return statisticsFaultLockStation2GByCountrySrvResponse;
    }

    public StatisticsFaultLockStation3GByAreaSrvResponse statisticsFaultLockStation3GByAreaSrv(MsgHeader msgHeader, StatisticsFaultLockStation3GByAreaSrvRequest statisticsFaultLockStation3GByAreaSrvRequest)
    {
        StatisticsFaultLockStation3GByAreaSrvResponse statisticsFaultLockStation3GByAreaSrvResponse = new StatisticsFaultLockStation3GByAreaSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultLockStation3GByAreaSrvResponse = util.statisticsFaultLockStation3GByAreaSrv(msgHeader, statisticsFaultLockStation3GByAreaSrvRequest);
        return statisticsFaultLockStation3GByAreaSrvResponse;
    }

    public StatisticsFaultLockStation3GByCountrySrvResponse statisticsFaultLockStation3GByCountrySrv(MsgHeader msgHeader, StatisticsFaultLockStation3GByCountrySrvRequest statisticsFaultLockStation3GByCountrySrvRequest)
    {
        StatisticsFaultLockStation3GByCountrySrvResponse statisticsFaultLockStation3GByCountrySrvResponse = new StatisticsFaultLockStation3GByCountrySrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultLockStation3GByCountrySrvResponse = util.statisticsFaultLockStation3GByCountrySrv(msgHeader, statisticsFaultLockStation3GByCountrySrvRequest);
        return statisticsFaultLockStation3GByCountrySrvResponse;
    }

    public StatisticsFaultLockStation4GByAreaSrvResponse statisticsFaultLockStation4GByAreaSrv(MsgHeader msgHeader, StatisticsFaultLockStation4GByAreaSrvRequest statisticsFaultLockStation4GByAreaSrvRequest)
    {
        StatisticsFaultLockStation4GByAreaSrvResponse statisticsFaultLockStation4GByAreaSrvResponse = new StatisticsFaultLockStation4GByAreaSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultLockStation4GByAreaSrvResponse = util.statisticsFaultLockStation4GByAreaSrv(msgHeader, statisticsFaultLockStation4GByAreaSrvRequest);
        return statisticsFaultLockStation4GByAreaSrvResponse;
    }

    public StatisticsFaultLockStation4GByCountrySrvResponse statisticsFaultLockStation4GByCountrySrv(MsgHeader msgHeader, StatisticsFaultLockStation4GByCountrySrvRequest statisticsFaultLockStation4GByCountrySrvRequest)
    {
        StatisticsFaultLockStation4GByCountrySrvResponse statisticsFaultLockStation4GByCountrySrvResponse = new StatisticsFaultLockStation4GByCountrySrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultLockStation4GByCountrySrvResponse = util.statisticsFaultLockStation4GByCountrySrv(msgHeader, statisticsFaultLockStation4GByCountrySrvRequest);
        return statisticsFaultLockStation4GByCountrySrvResponse;
    }

    public StatisticsFaultListInfoSrvResponse statisticsFaultListInfoSrv(MsgHeader msgHeader, StatisticsFaultListInfoSrvRequest statisticsFaultListInfoSrvRequest)
    {
        StatisticsFaultListInfoSrvResponse statisticsFaultListInfoSrvResponse = new StatisticsFaultListInfoSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultListInfoSrvResponse = util.statisticsFaultListInfoSrv(msgHeader, statisticsFaultListInfoSrvRequest);
        return statisticsFaultListInfoSrvResponse;
    }

    public StatisticsFaultOutServiceListInfoSrvResponse statisticsFaultOutServiceListInfoSrv(MsgHeader msgHeader, StatisticsFaultOutServiceListInfoSrvRequest statisticsFaultOutServiceListInfoSrvRequest)
    {
        StatisticsFaultOutServiceListInfoSrvResponse statisticsFaultOutServiceListInfoSrvResponse = new StatisticsFaultOutServiceListInfoSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultOutServiceListInfoSrvResponse = util.statisticsFaultOutServiceListInfoSrv(msgHeader, statisticsFaultOutServiceListInfoSrvRequest);
        return statisticsFaultOutServiceListInfoSrvResponse;
    }

    public StatisticsFaultPowerCutListInfoSrvResponse statisticsFaultPowerCutListInfoSrv(MsgHeader msgHeader, StatisticsFaultPowerCutListInfoSrvRequest statisticsFaultPowerCutListInfoSrvRequest)
    {
        StatisticsFaultPowerCutListInfoSrvResponse statisticsFaultPowerCutListInfoSrvResponse = new StatisticsFaultPowerCutListInfoSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultPowerCutListInfoSrvResponse = util.statisticsFaultPowerCutListInfoSrv(msgHeader, statisticsFaultPowerCutListInfoSrvRequest);
        return statisticsFaultPowerCutListInfoSrvResponse;
    }

    public StatisticsFaultLockStationListInfoSrvResponse statisticsFaultLockStationListInfoSrv(MsgHeader msgHeader, StatisticsFaultLockStationListInfoSrvRequest statisticsFaultLockStationListInfoSrvRequest)
    {
        StatisticsFaultLockStationListInfoSrvResponse statisticsFaultLockStationListInfoSrvResponse = new StatisticsFaultLockStationListInfoSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultLockStationListInfoSrvResponse = util.statisticsFaultLockStationListInfoSrv(msgHeader, statisticsFaultLockStationListInfoSrvRequest);
        return statisticsFaultLockStationListInfoSrvResponse;
    }
    
    //新添加start
    public StatisticsFaultStatusByAreaSrvResponse statisticsFaultStatusByAreaSrv(MsgHeader msgHeader,StatisticsFaultStatusByAreaSrvRequest statisticsFaultStatusByAreaSrvRequest){
    	StatisticsFaultStatusByAreaSrvResponse statisticsFaultStatusByAreaSrvResponse = new StatisticsFaultStatusByAreaSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultStatusByAreaSrvResponse = util.statisticsFaultStatusByAreaSrv(msgHeader, statisticsFaultStatusByAreaSrvRequest);
        return statisticsFaultStatusByAreaSrvResponse;
    }
	public StatisticsFaultStatusByCountrySrvResponse statisticsFaultStatusByCountrySrv(MsgHeader msgHeader,StatisticsFaultStatusByCountrySrvRequest statisticsFaultStatusByCountrySrvRequest){
		StatisticsFaultStatusByCountrySrvResponse statisticsFaultStatusByCountrySrvResponse = new StatisticsFaultStatusByCountrySrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultStatusByCountrySrvResponse = util.statisticsFaultStatusByCountrySrv(msgHeader, statisticsFaultStatusByCountrySrvRequest);
        return statisticsFaultStatusByCountrySrvResponse;
	}
	public StatisticsFaultTypeByAreaSrvResponse statisticsFaultTypeByAreaSrv(MsgHeader msgHeader,StatisticsFaultTypeByAreaSrvRequest statisticsFaultTypeByAreaSrvRequest){
		StatisticsFaultTypeByAreaSrvResponse statisticsFaultTypeByAreaSrvResponse = new StatisticsFaultTypeByAreaSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultTypeByAreaSrvResponse = util.statisticsFaultTypeByAreaSrv(msgHeader, statisticsFaultTypeByAreaSrvRequest);
        return statisticsFaultTypeByAreaSrvResponse;
	}
	public StatisticsFaultTypeByCountrySrvResponse statisticsFaultTypeByCountrySrv(MsgHeader msgHeader,StatisticsFaultTypeByCountrySrvRequest statisticsFaultTypeByCountrySrvRequest){
		StatisticsFaultTypeByCountrySrvResponse statisticsFaultTypeByCountrySrvResponse = new StatisticsFaultTypeByCountrySrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultTypeByCountrySrvResponse = util.statisticsFaultTypeByCountrySrv(msgHeader, statisticsFaultTypeByCountrySrvRequest);
        return statisticsFaultTypeByCountrySrvResponse;
	}
	public StatisticsFaultDealRateByAreaSrvResponse statisticsFaultDealRateByAreaSrv(MsgHeader msgHeader,StatisticsFaultDealRateByAreaSrvRequest statisticsFaultDealRateByAreaSrvRequest){
		StatisticsFaultDealRateByAreaSrvResponse statisticsFaultDealRateByAreaSrvResponse = new StatisticsFaultDealRateByAreaSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultDealRateByAreaSrvResponse = util.statisticsFaultDealRateByAreaSrv(msgHeader, statisticsFaultDealRateByAreaSrvRequest);
        return statisticsFaultDealRateByAreaSrvResponse;
	}
	public StatisticsFaultDealRateByCountrySrvResponse statisticsFaultDealRateByCountrySrv(MsgHeader msgHeader,StatisticsFaultDealRateByCountrySrvRequest statisticsFaultDealRateByCountrySrvRequest){
		StatisticsFaultDealRateByCountrySrvResponse statisticsFaultDealRateByCountrySrvResponse = new StatisticsFaultDealRateByCountrySrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsFaultDealRateByCountrySrvResponse = util.statisticsFaultDealRateByCountrySrv(msgHeader, statisticsFaultDealRateByCountrySrvRequest);
        return statisticsFaultDealRateByCountrySrvResponse;
	}
	
	public StatisticsNewFaultListInfoSrvResponse statisticsNewFaultListInfoSrv(MsgHeader msgHeader,StatisticsNewFaultListInfoSrvRequest statisticsNewFaultListInfoSrvRequest){
		StatisticsNewFaultListInfoSrvResponse statisticsNewFaultListInfoSrvResponse = new StatisticsNewFaultListInfoSrvResponse();
        StatisticsSheetInquiry util = new StatisticsSheetInquiry();
        statisticsNewFaultListInfoSrvResponse = util.statisticsNewFaultListInfoSrv(msgHeader, statisticsNewFaultListInfoSrvRequest);
        return statisticsNewFaultListInfoSrvResponse;
	}
	//新添加end

}