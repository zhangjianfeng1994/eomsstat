package com.boco.eoms.mobileApply.util;
//com.boco.eoms.tawSystemWebService.model.base.StatisticsSheetInquiry


import java.util.*;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.boco.eoms.baseUtil.BaseUtil;
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
import com.boco.eoms.mobileApply.model.util.AnalysisMapXml;
import com.boco.eoms.mobileApply.model.util.OptDetail;
import com.boco.eoms.mobileApply.model.util.XmlManage;
import com.boco.eoms.tawStatisticDetail.service.ITawStatisticDetailService;
import com.boco.eoms.tawStatisticModel.mobileService.TawStatisticService;
import com.boco.eoms.tawStatisticModel.service.ITawStatisticService;
import com.boco.eoms.tawStatisticModel.service.ITawStisticDetailSrv;
import com.boco.eoms.tawStatisticModel.service.impl.TawStatisticDetailSrv;
import com.boco.eoms.tawStatisticModel.util.ApplicationContextHolder;
import com.boco.eoms.tawSystemDept.model.TawSystemDept;
import com.boco.eoms.tawSystemDept.service.ITawSystemDeptService;
import com.boco.eoms.tawSystemDept.service.impl.TawSystemDeptService;
import com.boco.eoms.tawSystemUser.model.TawSystemUser;
import com.boco.eoms.tawSystemUser.service.ITawSystemUserService;


// Referenced classes of package com.boco.eoms.mobileApply.util:
//            AnalysisMapXml, OptDetail


public class StatisticsSheetInquiry
{
	
	private static final Logger logger = Logger.getLogger(StatisticsSheetInquiry.class);
	

    public StatisticsSheetInquiry()
    {
    }

    public StatisticsFaultByAreaSrvResponse statisticsFaultByAreaSrv(MsgHeader msgHeader, StatisticsFaultByAreaSrvRequest statisticsFaultByAreaSrvRequest)
    {
    	
    	
    	System.out.println("---------------------->>>>>>>>StatisticsFaultByAreaSrv   begin<<<<<<<<<<<<----------------------------");
    	
        StatisticsFaultByAreaSrvResponse response = new StatisticsFaultByAreaSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultByAreaSrvRequest.getStatisticsType());
            if("1".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
        		//获取当前用户所在地市信息
        		String areaCondition="";
        		String operateUserId=BaseUtil.nullObject2String(msgHeader.getOpUserId());
        		
        		
        		ITawSystemUserService tawSystemUserBo=(ITawSystemUserService)ApplicationContextHolder.getInstance().getBean("iTawSystemUserService");
        		
        		
        		TawSystemUser user=tawSystemUserBo.getUserByuserid(operateUserId);
        		if(user!=null){
        			String deptId=BaseUtil.nullObject2String(user.getDeptid());
        			ITawSystemDeptService itawSystemDeptService=(ITawSystemDeptService)ApplicationContextHolder.getInstance().getBean("iTawSystemDeptService");
        			//TawSystemDeptBo.getInstance().getDeptinfobydeptid(deptId,"0");
        			TawSystemDept dept=itawSystemDeptService.queryById(deptId);
        			String areaId=BaseUtil.nullObject2String(dept.getAreaid());
        			if(!"26".equals(areaId)){//26-省公司
        				//areaCondition="AND TODEPTID='" + areaId + "'";
        			}       			
        		}
        		System.out.println( " areaCondition=" + areaCondition+" -------------->>>> date"+date);
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultByArea"));
                
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");//new TawStatisticService();
                               
                String statisticsResult = service.showStatisticResult(modelId, date, "day", areaCondition);
                System.out.println( " statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultByArea"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    System.out.println( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultByAreaInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }
    
 

    public StatisticsFaultByAreaAndCountrySrvResponse statisticsFaultByAreaAndCountrySrv(MsgHeader msgHeader, StatisticsFaultByAreaAndCountrySrvRequest statisticsFaultByAreaAndCountrySrvRequest)
    {
        StatisticsFaultByAreaAndCountrySrvResponse response = new StatisticsFaultByAreaAndCountrySrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultByAreaAndCountrySrvRequest.getStatisticsType());
            if("2".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultByAreaAndCountry"));
               // TawStatisticService service = new TawStatisticService();
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String areaId = BaseUtil.nullObject2String(statisticsFaultByAreaAndCountrySrvRequest.getAreaId());
                
                
                String statisticsResult = service.showStatisticResult(modelId, date, "day", "");
                
                logger.info( " statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultByAreaAndCountry"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultByAreaAndCountryInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }
    
    public StatisticsFaultByAreaAndNetSrvResponse statisticsFaultByAreaAndNetSrv(MsgHeader msgHeader, StatisticsFaultByAreaAndNetSrvRequest statisticsFaultByAreaAndNetSrvRequest)
    {
        StatisticsFaultByAreaAndNetSrvResponse response = new StatisticsFaultByAreaAndNetSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF+\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultByAreaAndNetSrvRequest.getStatisticsType());
            if("3".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultByAreaAndNet"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                
                String areaId = BaseUtil.nullObject2String(statisticsFaultByAreaAndNetSrvRequest.getAreaId());
                String countyId = BaseUtil.nullObject2String(statisticsFaultByAreaAndNetSrvRequest.getCountryId());
                String statisticsResult = service.showStatisticResult(modelId, date, "day", " ");
                logger.info( " statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultByAreaAndNet"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultByAreaAndNetSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF+\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF+\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF+\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }
    
   

    public StatisticsFaultByAreaAndNetTwoSrvResponse statisticsFaultByAreaAndNetTwoSrv(MsgHeader msgHeader, StatisticsFaultByAreaAndNetTwoSrvRequest statisticsFaultByAreaAndNetTwoSrvRequest)
    {
        StatisticsFaultByAreaAndNetTwoSrvResponse response = new StatisticsFaultByAreaAndNetTwoSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF+\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u7F51\u7EDC\u4E8C\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultByAreaAndNetTwoSrvRequest.getStatisticsType());
            if("4".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultByAreaAndNetTwo"));
               
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                
                String areaId = BaseUtil.nullObject2String(statisticsFaultByAreaAndNetTwoSrvRequest.getAreaId());
                String countyId = BaseUtil.nullObject2String(statisticsFaultByAreaAndNetTwoSrvRequest.getCountryId());
                String netTypeId = BaseUtil.nullObject2String(statisticsFaultByAreaAndNetTwoSrvRequest.getNetType1Id());
                String statisticsResult = service.showStatisticResult(modelId, date,"day", " ");
                logger.info(" statisticsResult=" + statisticsResult+"==modelId=="+modelId);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultByAreaAndNetTwo"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultByAreaAndNetTwoSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF+\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u7F51\u7EDC\u4E8C\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF+\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u7F51\u7EDC\u4E8C\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF+\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u7F51\u7EDC\u4E8C\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultByNetSrvResponse statisticsFaultByNetSrv(MsgHeader msgHeader, StatisticsFaultByNetSrvRequest statisticsFaultByNetSrvRequest)
    {
        StatisticsFaultByNetSrvResponse response = new StatisticsFaultByNetSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultByNetSrvRequest.getStatisticsType());
            if("5".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
        		//获取当前用户所在地市信息
        		String areaCondition="";
        		String operateUserId=BaseUtil.nullObject2String(msgHeader.getOpUserId());
        		
        		ITawSystemUserService tawSystemUserBo=(ITawSystemUserService)ApplicationContextHolder.getInstance().getBean("iTawSystemUserService");
        		ITawSystemDeptService itawSystemDeptService=(ITawSystemDeptService)ApplicationContextHolder.getInstance().getBean("iTawSystemDeptService");
        		//ITawSystemUserBo tawSystemUserBo=(ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
        		TawSystemUser user=tawSystemUserBo.getUserByuserid(operateUserId);
        		if(user!=null){
        			String deptId=BaseUtil.nullObject2String(user.getDeptid());
        			
        			TawSystemDept dept=itawSystemDeptService.queryById(deptId); //TawSystemDeptBo.getInstance().getDeptinfobydeptid(deptId,"0");
        			
        			String areaId=BaseUtil.nullObject2String(dept.getAreaid());
        			if(!"26".equals(areaId)){//26-省公司
        				areaCondition=" AND TODEPTID='" + areaId + "'";
        			}
        		}
        		logger.info(" areaCondition=" + areaCondition);
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultByNet"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String statisticsResult = service.showStatisticResult(modelId, date, "day", areaCondition);
                logger.info( " statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultByNet"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultByNetSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultByNetAndAreaSrvResponse statisticsFaultByNetAndAreaSrv(MsgHeader msgHeader, StatisticsFaultByNetAndAreaSrvRequest statisticsFaultByNetAndAreaSrvRequest)
    {
        StatisticsFaultByNetAndAreaSrvResponse response = new StatisticsFaultByNetAndAreaSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultByNetAndAreaSrvRequest.getStatisticsType());
            if("6".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultByNetAndArea"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String netTypeId = BaseUtil.nullObject2String(statisticsFaultByNetAndAreaSrvRequest.getNetType1Id());
                String statisticsResult = service.showStatisticResult(modelId, date, "day", " ");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultByNetAndArea"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultByNetAndAreaSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

  
    public StatisticsFaultByNetAndCountrySrvResponse statisticsFaultByNetAndCountrySrv(MsgHeader msgHeader, StatisticsFaultByNetAndCountrySrvRequest statisticsFaultByNetAndCountrySrvRequest)
    {
        StatisticsFaultByNetAndCountrySrvResponse response = new StatisticsFaultByNetAndCountrySrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultByNetAndCountrySrvRequest.getStatisticsType());
            if("7".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultByNetAndCountry"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String netTypeId = BaseUtil.nullObject2String(statisticsFaultByNetAndCountrySrvRequest.getNetType1Id());
                String areaId = BaseUtil.nullObject2String(statisticsFaultByNetAndCountrySrvRequest.getAreaId());
                String statisticsResult = service.showStatisticResult(modelId, date, "day", " ");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultByNetAndCountry"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultByNetAndCountrySrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultByNetTwoAndCountrySrvResponse statisticsFaultByNetTwoAndCountrySrv(MsgHeader msgHeader, StatisticsFaultByNetTwoAndCountrySrvRequest statisticsFaultByNetTwoAndCountrySrvRequest)
    {
        StatisticsFaultByNetTwoAndCountrySrvResponse response = new StatisticsFaultByNetTwoAndCountrySrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u5730\u5E02+\u533A\u53BF+\u7F51\u7EDC\u4E8C\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultByNetTwoAndCountrySrvRequest.getStatisticsType());
            if("8".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultByNetTwoAndCountry"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String netTypeId = BaseUtil.nullObject2String(statisticsFaultByNetTwoAndCountrySrvRequest.getNetType1Id());
                String areaId = BaseUtil.nullObject2String(statisticsFaultByNetTwoAndCountrySrvRequest.getAreaId());
                String countyId = BaseUtil.nullObject2String(statisticsFaultByNetTwoAndCountrySrvRequest.getCountryId());
                String statisticsResult = service.showStatisticResult(modelId, date, "day", " ");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultByNetTwoAndCountry"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultByNetTwoAndCountrySrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u5730\u5E02+\u533A\u53BF+\u7F51\u7EDC\u4E8C\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u5730\u5E02+\u533A\u53BF+\u7F51\u7EDC\u4E8C\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B+\u5730\u5E02+\u533A\u53BF+\u7F51\u7EDC\u4E8C\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }
   
    public StatisticsFaultOutService2GByAreaSrvResponse statisticsFaultOutService2GByAreaSrv(MsgHeader msgHeader, StatisticsFaultOutService2GByAreaSrvRequest statisticsFaultOutService2GByAreaSrvRequest)
    {
        StatisticsFaultOutService2GByAreaSrvResponse response = new StatisticsFaultOutService2GByAreaSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9000\u670D\u7EDF\u8BA1\u5206\u67902G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultOutService2GByAreaSrvRequest.getStatisticsType());
            if("9".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-07-26";
        		//获取当前用户所在地市信息
        		String areaCondition="";
        		String operateUserId=BaseUtil.nullObject2String(msgHeader.getOpUserId());
        		ITawSystemUserService tawSystemUserBo=(ITawSystemUserService)ApplicationContextHolder.getInstance().getBean("iTawSystemUserService");
        		ITawSystemDeptService itawSystemDeptService=(ITawSystemDeptService)ApplicationContextHolder.getInstance().getBean("iTawSystemDeptService");
        		//ITawSystemUserBo tawSystemUserBo=(ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
        		TawSystemUser user=tawSystemUserBo.getUserByuserid(operateUserId);
        		if(user!=null){
        			String deptId=BaseUtil.nullObject2String(user.getDeptid());
        			//TawSystemDept dept=TawSystemDeptBo.getInstance().getDeptinfobydeptid(deptId,"0");
        			TawSystemDept dept=itawSystemDeptService.queryById(deptId);
        			String areaId=BaseUtil.nullObject2String(dept.getAreaid());
        			if(!"26".equals(areaId)){//26-省公司
        				areaCondition=" AND TODEPTID='" + areaId + "'";
        			}       			
        		}
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutService2GByArea"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String statisticsResult = service.showStatisticResult(modelId, date, "day", "");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultOutService2GByArea"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultOutService2GByAreaSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9000\u670D\u7EDF\u8BA1\u5206\u67902G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9000\u670D\u7EDF\u8BA1\u5206\u67902G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u9000\u670D\u7EDF\u8BA1\u5206\u67902G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultOutService2GByCountrySrvResponse statisticsFaultOutService2GByCountrySrv(MsgHeader msgHeader, StatisticsFaultOutService2GByCountrySrvRequest statisticsFaultOutService2GByCountrySrvRequest)
    {
        StatisticsFaultOutService2GByCountrySrvResponse response = new StatisticsFaultOutService2GByCountrySrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9000\u670D\u7EDF\u8BA1\u5206\u67902G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultOutService2GByCountrySrvRequest.getStatisticsType());
            if("10".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-07-26";
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutService2GByCountry"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String areaId = BaseUtil.nullObject2String(statisticsFaultOutService2GByCountrySrvRequest.getAreaId());
                String statisticsResult = service.showStatisticResult(modelId, date, "day", " ");
                logger.info( " statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultOutService2GByCountry"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultOutService2GByCountrySrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9000\u670D\u7EDF\u8BA1\u5206\u67902G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9000\u670D\u7EDF\u8BA1\u5206\u67902G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u9000\u670D\u7EDF\u8BA1\u5206\u67902G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }
 
    public StatisticsFaultOutService3GByAreaSrvResponse statisticsFaultOutService3GByAreaSrv(MsgHeader msgHeader, StatisticsFaultOutService3GByAreaSrvRequest statisticsFaultOutService3GByAreaSrvRequest)
    {
        StatisticsFaultOutService3GByAreaSrvResponse response = new StatisticsFaultOutService3GByAreaSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9000\u670D\u7EDF\u8BA1\u5206\u67903G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultOutService3GByAreaSrvRequest.getStatisticsType());
            if("11".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-07-26";
        		//获取当前用户所在地市信息
        		String areaCondition="";
        		String operateUserId=BaseUtil.nullObject2String(msgHeader.getOpUserId());
        		ITawSystemUserService tawSystemUserBo=(ITawSystemUserService)ApplicationContextHolder.getInstance().getBean("iTawSystemUserService");
        		ITawSystemDeptService itawSystemDeptService=(ITawSystemDeptService)ApplicationContextHolder.getInstance().getBean("iTawSystemDeptService");
        		//ITawSystemUserBo tawSystemUserBo=(ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
        		TawSystemUser user=tawSystemUserBo.getUserByuserid(operateUserId);
        		if(user!=null){
        			String deptId=BaseUtil.nullObject2String(user.getDeptid());
        			//TawSystemDept dept=TawSystemDeptBo.getInstance().getDeptinfobydeptid(deptId,"0");
        			TawSystemDept dept=itawSystemDeptService.queryById(deptId);
        			String areaId=BaseUtil.nullObject2String(dept.getAreaid());
        			if(!"26".equals(areaId)){//26-省公司
        				areaCondition=" AND TODEPTID='" + areaId + "'";
        			}       			
        		}
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutService3GByArea"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String statisticsResult = service.showStatisticResult(modelId, date, "day", "");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultOutService3GByArea"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultOutService3GByAreaSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9000\u670D\u7EDF\u8BA1\u5206\u67903G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9000\u670D\u7EDF\u8BA1\u5206\u67903G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u9000\u670D\u7EDF\u8BA1\u5206\u67903G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultOutService3GByCountrySrvResponse statisticsFaultOutService3GByCountrySrv(MsgHeader msgHeader, StatisticsFaultOutService3GByCountrySrvRequest statisticsFaultOutService3GByCountrySrvRequest)
    {
        StatisticsFaultOutService3GByCountrySrvResponse response = new StatisticsFaultOutService3GByCountrySrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9000\u670D\u7EDF\u8BA1\u5206\u67903G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultOutService3GByCountrySrvRequest.getStatisticsType());
            if("12".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-07-26";
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutService3GByCountry"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String areaId = BaseUtil.nullObject2String(statisticsFaultOutService3GByCountrySrvRequest.getAreaId());
                String statisticsResult = service.showStatisticResult(modelId, date, "day", " ");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultOutService3GByCountry"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultOutService3GByCountrySrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9000\u670D\u7EDF\u8BA1\u5206\u67903G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9000\u670D\u7EDF\u8BA1\u5206\u67903G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u9000\u670D\u7EDF\u8BA1\u5206\u67903G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultOutServiceRRUByAreaSrvResponse statisticsFaultOutServiceRRUByAreaSrv(MsgHeader msgHeader, StatisticsFaultOutServiceRRUByAreaSrvRequest statisticsFaultOutServiceRRUByAreaSrvRequest)
    {
        StatisticsFaultOutServiceRRUByAreaSrvResponse response = new StatisticsFaultOutServiceRRUByAreaSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9000\u670D\u7EDF\u8BA1\u5206\u6790RRU\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultOutServiceRRUByAreaSrvRequest.getStatisticsType());
            if("13".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-07-26";
        		//获取当前用户所在地市信息
        		String areaCondition="";
        		String operateUserId=BaseUtil.nullObject2String(msgHeader.getOpUserId());
        		ITawSystemUserService tawSystemUserBo=(ITawSystemUserService)ApplicationContextHolder.getInstance().getBean("iTawSystemUserService");
        		ITawSystemDeptService itawSystemDeptService=(ITawSystemDeptService)ApplicationContextHolder.getInstance().getBean("iTawSystemDeptService");
        		//ITawSystemUserBo tawSystemUserBo=(ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
        		TawSystemUser user=tawSystemUserBo.getUserByuserid(operateUserId);
        		if(user!=null){
        			String deptId=BaseUtil.nullObject2String(user.getDeptid());
        			//TawSystemDept dept=TawSystemDeptBo.getInstance().getDeptinfobydeptid(deptId,"0");
        			TawSystemDept dept=itawSystemDeptService.queryById(deptId);
        			String areaId=BaseUtil.nullObject2String(dept.getAreaid());
        			if(!"26".equals(areaId)){//26-省公司
        				areaCondition=" AND TODEPTID='" + areaId + "'";
        			}       			
        		}
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutServiceRRUByArea"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String statisticsResult = service.showStatisticResult(modelId, date, "day", "");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultOutServiceRRUByArea"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultOutServiceRRUByAreaSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9000\u670D\u7EDF\u8BA1\u5206\u6790RRU\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9000\u670D\u7EDF\u8BA1\u5206\u6790RRU\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u9000\u670D\u7EDF\u8BA1\u5206\u6790RRU\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultOutServiceRRUByCountrySrvResponse statisticsFaultOutServiceRRUByCountrySrv(MsgHeader msgHeader, StatisticsFaultOutServiceRRUByCountrySrvRequest statisticsFaultOutServiceRRUByCountrySrvRequest)
    {
        StatisticsFaultOutServiceRRUByCountrySrvResponse response = new StatisticsFaultOutServiceRRUByCountrySrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9000\u670D\u7EDF\u8BA1\u5206\u6790RRU\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultOutServiceRRUByCountrySrvRequest.getStatisticsType());
            if("14".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-07-26";
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutServiceRRUByCountry"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String areaId = BaseUtil.nullObject2String(statisticsFaultOutServiceRRUByCountrySrvRequest.getAreaId());
                String statisticsResult = service.showStatisticResult(modelId, date, "day", " ");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultOutServiceRRUByCountry"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultOutServiceRRUByCountrySrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9000\u670D\u7EDF\u8BA1\u5206\u6790RRU\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9000\u670D\u7EDF\u8BA1\u5206\u6790RRU\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u9000\u670D\u7EDF\u8BA1\u5206\u6790RRU\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultOutService4GByAreaSrvResponse statisticsFaultOutService4GByAreaSrv(MsgHeader msgHeader, StatisticsFaultOutService4GByAreaSrvRequest statisticsFaultOutService4GByAreaSrvRequest)
    {
        StatisticsFaultOutService4GByAreaSrvResponse response = new StatisticsFaultOutService4GByAreaSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9000\u670D\u7EDF\u8BA1\u5206\u67904G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultOutService4GByAreaSrvRequest.getStatisticsType());
            if("15".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-07-26";
        		//获取当前用户所在地市信息
        		String areaCondition="";
        		String operateUserId=BaseUtil.nullObject2String(msgHeader.getOpUserId());
        		ITawSystemUserService tawSystemUserBo=(ITawSystemUserService)ApplicationContextHolder.getInstance().getBean("iTawSystemUserService");
        		ITawSystemDeptService itawSystemDeptService=(ITawSystemDeptService)ApplicationContextHolder.getInstance().getBean("iTawSystemDeptService");
        		//ITawSystemUserBo tawSystemUserBo=(ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
        		TawSystemUser user=tawSystemUserBo.getUserByuserid(operateUserId);
        		if(user!=null){
        			String deptId=BaseUtil.nullObject2String(user.getDeptid());
        			//TawSystemDept dept=TawSystemDeptBo.getInstance().getDeptinfobydeptid(deptId,"0");
        			TawSystemDept dept=itawSystemDeptService.queryById(deptId);
        			String areaId=BaseUtil.nullObject2String(dept.getAreaid());
        			if(!"26".equals(areaId)){//26-省公司
        				areaCondition=" AND TODEPTID='" + areaId + "'";
        			}       			
        		}
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutService4GByArea"));
                //TawStatisticService service = new TawStatisticService();
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String statisticsResult = service.showStatisticResult(modelId, date, "day", "");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultOutService4GByArea"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultOutService4GByAreaSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9000\u670D\u7EDF\u8BA1\u5206\u67904G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9000\u670D\u7EDF\u8BA1\u5206\u67904G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u9000\u670D\u7EDF\u8BA1\u5206\u67904G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultOutService4GByCountrySrvResponse statisticsFaultOutService4GByCountrySrv(MsgHeader msgHeader, StatisticsFaultOutService4GByCountrySrvRequest statisticsFaultOutService4GByCountrySrvRequest)
    {
        StatisticsFaultOutService4GByCountrySrvResponse response = new StatisticsFaultOutService4GByCountrySrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9000\u670D\u7EDF\u8BA1\u5206\u67904G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultOutService4GByCountrySrvRequest.getStatisticsType());
            if("16".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-07-26";
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutService4GByCountry"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String areaId = BaseUtil.nullObject2String(statisticsFaultOutService4GByCountrySrvRequest.getAreaId());
                String statisticsResult = service.showStatisticResult(modelId, date, "day", " ");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultOutService4GByCountry"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultOutService4GByCountrySrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9000\u670D\u7EDF\u8BA1\u5206\u67904G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9000\u670D\u7EDF\u8BA1\u5206\u67904G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u9000\u670D\u7EDF\u8BA1\u5206\u67904G\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultOutServiceWLANByAreaSrvResponse statisticsFaultOutServiceWLANByAreaSrv(MsgHeader msgHeader, StatisticsFaultOutServiceWLANByAreaSrvRequest statisticsFaultOutServiceWLANByAreaSrvRequest)
    {
        StatisticsFaultOutServiceWLANByAreaSrvResponse response = new StatisticsFaultOutServiceWLANByAreaSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9000\u670D\u7EDF\u8BA1\u5206\u6790WLAN\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultOutServiceWLANByAreaSrvRequest.getStatisticsType());
            if("17".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-07-26";
        		//获取当前用户所在地市信息
        		String areaCondition="";
        		String operateUserId=BaseUtil.nullObject2String(msgHeader.getOpUserId());
        		ITawSystemUserService tawSystemUserBo=(ITawSystemUserService)ApplicationContextHolder.getInstance().getBean("iTawSystemUserService");
        		ITawSystemDeptService itawSystemDeptService=(ITawSystemDeptService)ApplicationContextHolder.getInstance().getBean("iTawSystemDeptService");
        		//ITawSystemUserBo tawSystemUserBo=(ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
        		TawSystemUser user=tawSystemUserBo.getUserByuserid(operateUserId);
        		if(user!=null){
        			String deptId=BaseUtil.nullObject2String(user.getDeptid());
        			//TawSystemDept dept=TawSystemDeptBo.getInstance().getDeptinfobydeptid(deptId,"0");
        			TawSystemDept dept=itawSystemDeptService.queryById(deptId);
        			String areaId=BaseUtil.nullObject2String(dept.getAreaid());
        			if(!"26".equals(areaId)){//26-省公司
        				areaCondition=" AND TODEPTID='" + areaId + "'";
        			}       			
        		}
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutServiceWLANByArea"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String statisticsResult = service.showStatisticResult(modelId, date, "day", "");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultOutServiceWLANByArea"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultOutServiceWLANByAreaSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9000\u670D\u7EDF\u8BA1\u5206\u6790WLAN\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9000\u670D\u7EDF\u8BA1\u5206\u6790WLAN\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u9000\u670D\u7EDF\u8BA1\u5206\u6790WLAN\u57FA\u7AD9\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

  
    public StatisticsFaultOutServiceWLANByCountrySrvResponse statisticsFaultOutServiceWLANByCountrySrv(MsgHeader msgHeader, StatisticsFaultOutServiceWLANByCountrySrvRequest statisticsFaultOutServiceWLANByCountrySrvRequest){
        StatisticsFaultOutServiceWLANByCountrySrvResponse response = new StatisticsFaultOutServiceWLANByCountrySrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9000\u670D\u7EDF\u8BA1\u5206\u6790WLAN\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultOutServiceWLANByCountrySrvRequest.getStatisticsType());
            if("18".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-07-26";
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutServiceWLANByCountry"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String areaId = BaseUtil.nullObject2String(statisticsFaultOutServiceWLANByCountrySrvRequest.getAreaId());
                String statisticsResult = service.showStatisticResult(modelId, date, "day", " ");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultOutServiceWLANByCountry"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                System.out.println("statisticsFaultOutServiceWLANByCountrySrv 11");
                response.setServiceFlag(serviceFlag);
                System.out.println("statisticsFaultOutServiceWLANByCountrySrv 12");
                response.setServiceMessage(serviceMessage);
                System.out.println("statisticsFaultOutServiceWLANByCountrySrv 13");
                response.setStatisticsFaultOutServiceWLANByCountrySrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
                System.out.println("statisticsFaultOutServiceWLANByCountrySrv 22");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9000\u670D\u7EDF\u8BA1\u5206\u6790WLAN\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9000\u670D\u7EDF\u8BA1\u5206\u6790WLAN\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("statisticsFaultOutServiceWLANByCountrySrv 33");
        System.out.println("----\u9000\u670D\u7EDF\u8BA1\u5206\u6790WLAN\u57FA\u7AD9\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultPowerCutByAreaSrvResponse statisticsFaultPowerCutByAreaSrv(MsgHeader msgHeader, StatisticsFaultPowerCutByAreaSrvRequest statisticsFaultPowerCutByAreaSrvRequest)
    {
        StatisticsFaultPowerCutByAreaSrvResponse response = new StatisticsFaultPowerCutByAreaSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u505C\u7535\u7EDF\u8BA1\u5206\u6790\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultPowerCutByAreaSrvRequest.getStatisticsType());
            if("19".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
//        		获取当前用户所在地市信息
        		String areaCondition="";
        		String operateUserId=BaseUtil.nullObject2String(msgHeader.getOpUserId());
        		//ITawSystemUserBo tawSystemUserBo=(ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
        		ITawSystemUserService tawSystemUserBo=(ITawSystemUserService)ApplicationContextHolder.getInstance().getBean("iTawSystemUserService");
        		ITawSystemDeptService itawSystemDeptService=(ITawSystemDeptService)ApplicationContextHolder.getInstance().getBean("iTawSystemDeptService");
        		
        		TawSystemUser user=tawSystemUserBo.getUserByuserid(operateUserId);
        		if(user!=null){
        			String deptId=BaseUtil.nullObject2String(user.getDeptid());
        			//TawSystemDept dept=TawSystemDeptBo.getInstance().getDeptinfobydeptid(deptId,"0");
        			TawSystemDept dept=itawSystemDeptService.queryById(deptId);
        			String areaId=BaseUtil.nullObject2String(dept.getAreaid());
        			if(!"26".equals(areaId)){//26-省公司
        				areaCondition=" AND TODEPTID='" + areaId + "'";
        			}       			
        		}
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultPowerCutByArea"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String statisticsResult = service.showStatisticResult(modelId, date, "day", "");
                logger.info( " statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultPowerCutByArea"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultPowerCutByAreaSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u505C\u7535\u7EDF\u8BA1\u5206\u6790\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u505C\u7535\u7EDF\u8BA1\u5206\u6790\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u505C\u7535\u7EDF\u8BA1\u5206\u6790\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }
  
    public StatisticsFaultPowerCutByCountrySrvResponse statisticsFaultPowerCutByCountrySrv(MsgHeader msgHeader, StatisticsFaultPowerCutByCountrySrvRequest statisticsFaultPowerCutByCountrySrvRequest)
    {
        StatisticsFaultPowerCutByCountrySrvResponse response = new StatisticsFaultPowerCutByCountrySrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u505C\u7535\u7EDF\u8BA1\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultPowerCutByCountrySrvRequest.getStatisticsType());
            if("20".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";        		
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultPowerCutByCountry"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String areaId = BaseUtil.nullObject2String(statisticsFaultPowerCutByCountrySrvRequest.getAreaId());
                String statisticsResult = service.showStatisticResult(modelId, date, "day", " ");
                logger.info( " statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultPowerCutByCountry"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultPowerCutByCountrySrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u505C\u7535\u7EDF\u8BA1\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u505C\u7535\u7EDF\u8BA1\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u505C\u7535\u7EDF\u8BA1\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultLockStation2GByAreaSrvResponse statisticsFaultLockStation2GByAreaSrv(MsgHeader msgHeader, StatisticsFaultLockStation2GByAreaSrvRequest statisticsFaultLockStation2GByAreaSrvRequest)
    {
        StatisticsFaultLockStation2GByAreaSrvResponse response = new StatisticsFaultLockStation2GByAreaSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9501\u7AD9\u7EDF\u8BA1\u5206\u67902G\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultLockStation2GByAreaSrvRequest.getStatisticsType());
            if("21".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
//        		获取当前用户所在地市信息
        		String areaCondition="";
        		String operateUserId=BaseUtil.nullObject2String(msgHeader.getOpUserId());
        		//ITawSystemUserBo tawSystemUserBo=(ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
        		ITawSystemUserService tawSystemUserBo=(ITawSystemUserService)ApplicationContextHolder.getInstance().getBean("iTawSystemUserService");
        		ITawSystemDeptService itawSystemDeptService=(ITawSystemDeptService)ApplicationContextHolder.getInstance().getBean("iTawSystemDeptService");
        		TawSystemUser user=tawSystemUserBo.getUserByuserid(operateUserId);
        		if(user!=null){
        			String deptId=BaseUtil.nullObject2String(user.getDeptid());
        			//TawSystemDept dept=TawSystemDeptBo.getInstance().getDeptinfobydeptid(deptId,"0");
        			TawSystemDept dept=itawSystemDeptService.queryById(deptId);
        			String areaId=BaseUtil.nullObject2String(dept.getAreaid());
        			if(!"26".equals(areaId)){//26-省公司
        				areaCondition="AND AREA_id='" + areaId + "'";
        			}       			
        		}
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultLockStation2GByArea"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String statisticsResult = service.showStatisticResult(modelId, date, "day", "");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultLockStation2GByArea"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultLockStation2GByAreaSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9501\u7AD9\u7EDF\u8BA1\u5206\u67902G\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9501\u7AD9\u7EDF\u8BA1\u5206\u67902G\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u9501\u7AD9\u7EDF\u8BA1\u5206\u67902G\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultLockStation2GByCountrySrvResponse statisticsFaultLockStation2GByCountrySrv(MsgHeader msgHeader, StatisticsFaultLockStation2GByCountrySrvRequest statisticsFaultLockStation2GByCountrySrvRequest)
    {
        StatisticsFaultLockStation2GByCountrySrvResponse response = new StatisticsFaultLockStation2GByCountrySrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9501\u7AD9\u7EDF\u8BA1\u5206\u67902G\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultLockStation2GByCountrySrvRequest.getStatisticsType());
            if("22".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                String date = "2014-07-18";
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultLockStation2GByCountry"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String areaId = BaseUtil.nullObject2String(statisticsFaultLockStation2GByCountrySrvRequest.getAreaId());
                String statisticsResult = service.showStatisticResult(modelId, date, "day", " ");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultLockStation2GByCountry"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultLockStation2GByCountrySrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9501\u7AD9\u7EDF\u8BA1\u5206\u67902G\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9501\u7AD9\u7EDF\u8BA1\u5206\u67902G\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u9501\u7AD9\u7EDF\u8BA1\u5206\u67902G\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultLockStation3GByAreaSrvResponse statisticsFaultLockStation3GByAreaSrv(MsgHeader msgHeader, StatisticsFaultLockStation3GByAreaSrvRequest statisticsFaultLockStation3GByAreaSrvRequest)
    {
        StatisticsFaultLockStation3GByAreaSrvResponse response = new StatisticsFaultLockStation3GByAreaSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9501\u7AD9\u7EDF\u8BA1\u5206\u67903G\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultLockStation3GByAreaSrvRequest.getStatisticsType());
            if("23".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
//        		获取当前用户所在地市信息
        		String areaCondition="";
        		String operateUserId=BaseUtil.nullObject2String(msgHeader.getOpUserId());
        		//ITawSystemUserBo tawSystemUserBo=(ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
        		ITawSystemUserService tawSystemUserBo=(ITawSystemUserService)ApplicationContextHolder.getInstance().getBean("iTawSystemUserService");
        		ITawSystemDeptService itawSystemDeptService=(ITawSystemDeptService)ApplicationContextHolder.getInstance().getBean("iTawSystemDeptService");
        		TawSystemUser user=tawSystemUserBo.getUserByuserid(operateUserId);
        		if(user!=null){
        			String deptId=BaseUtil.nullObject2String(user.getDeptid());
        			//TawSystemDept dept=TawSystemDeptBo.getInstance().getDeptinfobydeptid(deptId,"0");
        			TawSystemDept dept=itawSystemDeptService.queryById(deptId);
        			String areaId=BaseUtil.nullObject2String(dept.getAreaid());
        			if(!"26".equals(areaId)){//26-省公司
        				areaCondition="AND AREA_id='" + areaId + "'";
        			}       			
        		}
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultLockStation3GByArea"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String statisticsResult = service.showStatisticResult(modelId, date, "day", "");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultLockStation3GByArea"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultLockStation3GByAreaSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9501\u7AD9\u7EDF\u8BA1\u5206\u67903G\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9501\u7AD9\u7EDF\u8BA1\u5206\u67903G\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u9501\u7AD9\u7EDF\u8BA1\u5206\u67903G\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultLockStation3GByCountrySrvResponse statisticsFaultLockStation3GByCountrySrv(MsgHeader msgHeader, StatisticsFaultLockStation3GByCountrySrvRequest statisticsFaultLockStation3GByCountrySrvRequest)
    {
        StatisticsFaultLockStation3GByCountrySrvResponse response = new StatisticsFaultLockStation3GByCountrySrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9501\u7AD9\u7EDF\u8BA1\u5206\u67903G\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultLockStation3GByCountrySrvRequest.getStatisticsType());
            if("24".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                String date = "2014-07-18";
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultLockStation3GByCountry"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String areaId = BaseUtil.nullObject2String(statisticsFaultLockStation3GByCountrySrvRequest.getAreaId());
                String statisticsResult = service.showStatisticResult(modelId, date, "day", " ");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultLockStation3GByCountry"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultLockStation3GByCountrySrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9501\u7AD9\u7EDF\u8BA1\u5206\u67903G\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9501\u7AD9\u7EDF\u8BA1\u5206\u67903G\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u9501\u7AD9\u7EDF\u8BA1\u5206\u67903G\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultLockStation4GByAreaSrvResponse statisticsFaultLockStation4GByAreaSrv(MsgHeader msgHeader, StatisticsFaultLockStation4GByAreaSrvRequest statisticsFaultLockStation4GByAreaSrvRequest)
    {
        StatisticsFaultLockStation4GByAreaSrvResponse response = new StatisticsFaultLockStation4GByAreaSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9501\u7AD9\u7EDF\u8BA1\u5206\u67904G\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultLockStation4GByAreaSrvRequest.getStatisticsType());
            if("25".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
//        		获取当前用户所在地市信息
        		String areaCondition="";
        		String operateUserId=BaseUtil.nullObject2String(msgHeader.getOpUserId());
        		//ITawSystemUserBo tawSystemUserBo=(ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
        		ITawSystemUserService tawSystemUserBo=(ITawSystemUserService)ApplicationContextHolder.getInstance().getBean("iTawSystemUserService");
        		ITawSystemDeptService itawSystemDeptService=(ITawSystemDeptService)ApplicationContextHolder.getInstance().getBean("iTawSystemDeptService");
        		TawSystemUser user=tawSystemUserBo.getUserByuserid(operateUserId);
        		if(user!=null){
        			String deptId=BaseUtil.nullObject2String(user.getDeptid());
        			//TawSystemDept dept=TawSystemDeptBo.getInstance().getDeptinfobydeptid(deptId,"0");
        			TawSystemDept dept=itawSystemDeptService.queryById(deptId);
        			String areaId=BaseUtil.nullObject2String(dept.getAreaid());
        			if(!"26".equals(areaId)){//26-省公司
        				areaCondition="AND AREA_id='" + areaId + "'";
        			}       			
        		}
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultLockStation4GByArea"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String statisticsResult = service.showStatisticResult(modelId, date, "day", "");
                logger.info( " statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultLockStation4GByArea"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultLockStation4GByAreaSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9501\u7AD9\u7EDF\u8BA1\u5206\u67904G\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9501\u7AD9\u7EDF\u8BA1\u5206\u67904G\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u9501\u7AD9\u7EDF\u8BA1\u5206\u67904G\uFF08\u6309\u5730\u5E02\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultLockStation4GByCountrySrvResponse statisticsFaultLockStation4GByCountrySrv(MsgHeader msgHeader, StatisticsFaultLockStation4GByCountrySrvRequest statisticsFaultLockStation4GByCountrySrvRequest)
    {
        StatisticsFaultLockStation4GByCountrySrvResponse response = new StatisticsFaultLockStation4GByCountrySrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u9501\u7AD9\u7EDF\u8BA1\u5206\u67904G\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultLockStation4GByCountrySrvRequest.getStatisticsType());
            if("26".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                String date = "2014-07-18";
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultLockStation4GByCountry"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String areaId = BaseUtil.nullObject2String(statisticsFaultLockStation4GByCountrySrvRequest.getAreaId());
                String statisticsResult = service.showStatisticResult(modelId, date, "day", " ");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultLockStation4GByCountry"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultLockStation4GByCountrySrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u9501\u7AD9\u7EDF\u8BA1\u5206\u67904G\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u9501\u7AD9\u7EDF\u8BA1\u5206\u67904G\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u9501\u7AD9\u7EDF\u8BA1\u5206\u67904G\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }

    public StatisticsFaultListInfoSrvResponse statisticsFaultListInfoSrv(MsgHeader msgHeader, StatisticsFaultListInfoSrvRequest statisticsFaultListInfoSrvRequest)
    {
    	StatisticsFaultListInfoSrvResponse response = new StatisticsFaultListInfoSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----查询故障区域分析&故障分类分析列表服务调用开始----------");
        try{
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultListInfoSrvRequest.getStatisticsType());
            if("1".equals(statisticsType)||"2".equals(statisticsType)||"3".equals(statisticsType)||"4".equals(statisticsType)||
               "5".equals(statisticsType)||"6".equals(statisticsType)||"7".equals(statisticsType)||"8".equals(statisticsType)){
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
                String modelId="";
                if("1".equals(statisticsType)||"2".equals(statisticsType)||"3".equals(statisticsType)||"4".equals(statisticsType)){//故障区域分析
                	modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultByAreaAndNetTwo"));                   
                }else{//故障分类分析
                	modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultByNetTwoAndCountry"));                                       
                }
                String listModelId=BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultListInfo"));                
                ITawStisticDetailSrv service = (ITawStisticDetailSrv)ApplicationContextHolder.getInstance().getBean("iTawStisticDetailSrv");
                
                //TawStatisticDetailSrv service = new TawStatisticDetailSrv();
                String areaId = BaseUtil.nullObject2String(statisticsFaultListInfoSrvRequest.getAreaId());
                String countryId = BaseUtil.nullObject2String(statisticsFaultListInfoSrvRequest.getCountryId());
                String netType1Id = BaseUtil.nullObject2String(statisticsFaultListInfoSrvRequest.getNetType1Id());
                String netType2Id = BaseUtil.nullObject2String(statisticsFaultListInfoSrvRequest.getNetType2Id()); 
                String whereCondition=" and TODEPTID='" + areaId + "' and MAINFAULTGENERANTCOUNTY='"+countryId+"' and MAINNETSORTONE='"+netType1Id+"' and MAINNETSORTTWO='"+netType2Id+"'";
                if("1".equals(statisticsType)||"5".equals(statisticsType)){//待处理
                	String statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.undo"));                                       
                	if(!"".equals(statisticsCondition)){
                    	whereCondition=whereCondition+" and "+statisticsCondition;              
                    }
                }else if("2".equals(statisticsType)||"6".equals(statisticsType)){//重要
                	String statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.import"));                                       
                	if(!"".equals(statisticsCondition)){
                    	whereCondition=whereCondition+" and "+statisticsCondition;              
                    }
                }else if("3".equals(statisticsType)||"7".equals(statisticsType)){//待质检
                	String statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.uncheck"));                                       
                	if(!"".equals(statisticsCondition)){
                    	whereCondition=whereCondition+" and "+statisticsCondition;              
                    }
                }else if("4".equals(statisticsType)||"8".equals(statisticsType)){//已超时
                	String statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.overtime"));                                       
                	if(!"".equals(statisticsCondition)){
                    	whereCondition=whereCondition+" and "+statisticsCondition;              
                    }
                }               
                String statisticsResult = service.getDetailPageXml(modelId, listModelId,date,whereCondition,"");
                logger.info(" statisticsFaultListInfoSrv statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult)){
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getDetailOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultListInfo"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultListInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("查询故障区域分析&故障分类分析列表服务调用报错,传入的statisticsType不对!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "查询故障区域分析&故障分类分析列表服务调用失败,错误信息为:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----查询故障区域分析&故障分类分析列表服务调用结束----------");
        return response;
    }

    public StatisticsFaultOutServiceListInfoSrvResponse statisticsFaultOutServiceListInfoSrv(MsgHeader msgHeader, StatisticsFaultOutServiceListInfoSrvRequest statisticsFaultOutServiceListInfoSrvRequest)
    {
    	StatisticsFaultOutServiceListInfoSrvResponse response = new StatisticsFaultOutServiceListInfoSrvResponse();
    	String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----查询退服统计分析列表服务调用开始----------");
        try{
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultOutServiceListInfoSrvRequest.getStatisticsType());
            if("9".equals(statisticsType)||"10".equals(statisticsType)||"11".equals(statisticsType)||"12".equals(statisticsType)||"13".equals(statisticsType)){
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-07-26";
                String modelId="";
                if("9".equals(statisticsType)){//退服统计分析2G基站
                	modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutService2GByCountry"));                   
                }else if("10".equals(statisticsType)){//退服统计分析3G基站
                	modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutService3GByCountry"));                                       
                }else if("11".equals(statisticsType)){//退服统计分析RRU基站
                	modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutServiceRRUByCountry"));                                       
                }else if("12".equals(statisticsType)){//退服统计分析4G基站
                	modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutService4GByCountry"));                                       
                }else if("13".equals(statisticsType)){//退服统计分析WLAN基站
                	modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutServiceWLANByCountry"));                                       
                }
                String listModelId=BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultOutServiceListInfo"));                
                //TawStatisticDetailSrv service = new TawStatisticDetailSrv();
                ITawStisticDetailSrv service = (ITawStisticDetailSrv)ApplicationContextHolder.getInstance().getBean("iTawStisticDetailSrv");
                String areaId = BaseUtil.nullObject2String(statisticsFaultOutServiceListInfoSrvRequest.getAreaId());
                String countryId = BaseUtil.nullObject2String(statisticsFaultOutServiceListInfoSrvRequest.getCountryId());
                String whereCondition = "  ARAE_NAME='" + areaId + "' and CNTY_NAME='" + countryId + "'";
				if("9".equals(statisticsType)){//退服统计分析2G基站
                	String statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.outService2G"));                                       
                	if(!"".equals(statisticsCondition)){
                    	whereCondition=whereCondition+" and "+statisticsCondition;              
                    }
                }else if("10".equals(statisticsType)){//退服统计分析3G基站
                	String statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.outService3G"));                                       
                	if(!"".equals(statisticsCondition)){
                    	whereCondition=whereCondition+" and "+statisticsCondition;              
                    }
                }else if("11".equals(statisticsType)){//退服统计分析RRU基站
                	String statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.outServiceRRU"));                                       
                	if(!"".equals(statisticsCondition)){
                    	whereCondition=whereCondition+" and "+statisticsCondition;              
                    }
                }else if("12".equals(statisticsType)){//退服统计分析4G基站
                	String statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.outService4G"));                                       
                	if(!"".equals(statisticsCondition)){
                    	whereCondition=whereCondition+" and "+statisticsCondition;              
                    }
                }else if("13".equals(statisticsType)){//退服统计分析WLAN基站
                	String statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.outServiceWLAN"));                                       
                	if(!"".equals(statisticsCondition)){
                    	whereCondition=whereCondition+" and "+statisticsCondition;              
                    }
                }               
                String statisticsResult = service.getDetailPageXml(modelId, listModelId,date,whereCondition,"");
                logger.info(" statisticsFaultOutServiceListInfoSrv statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult)){
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getDetailOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultOutServiceListInfo"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultOutServiceListInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("查询退服统计分析列表服务调用报错,传入的statisticsType不对!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "查询退服统计分析列表服务调用失败,错误信息为:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----查询退服统计分析列表服务调用结束----------");
    	return response;
    }

    public StatisticsFaultPowerCutListInfoSrvResponse statisticsFaultPowerCutListInfoSrv(MsgHeader msgHeader, StatisticsFaultPowerCutListInfoSrvRequest statisticsFaultPowerCutListInfoSrvRequest)
    {
    	StatisticsFaultPowerCutListInfoSrvResponse response = new StatisticsFaultPowerCutListInfoSrvResponse();
    	String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----查询停电统计分析列表服务调用开始----------");
        try{
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultPowerCutListInfoSrvRequest.getStatisticsType());
            if("14".equals(statisticsType)){
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                String date = "2014-06-28";
                String modelId=BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultPowerCutByCountry"));                   ;
                String listModelId=BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultPowerCutListInfo"));                
                ITawStisticDetailSrv service = (ITawStisticDetailSrv)ApplicationContextHolder.getInstance().getBean("iTawStisticDetailSrv");
                //TawStatisticDetailSrv service = new TawStatisticDetailSrv();
                String areaId = BaseUtil.nullObject2String(statisticsFaultPowerCutListInfoSrvRequest.getAreaId());
                String countryId = BaseUtil.nullObject2String(statisticsFaultPowerCutListInfoSrvRequest.getCountryId());
                String whereCondition=" and TODEPTID='" + areaId + "' and MAINFAULTGENERANTCOUNTY='"+countryId+"'";
                String statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.powercut"));                                       
                if(!"".equals(statisticsCondition)){
                	whereCondition=whereCondition+" and "+statisticsCondition;              
                }
                String statisticsResult = service.getDetailPageXml(modelId, listModelId,date,whereCondition,"");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult)){
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getDetailOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultPowerCutListInfo"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultPowerCutListInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("查询停电统计分析列表服务调用报错,传入的statisticsType不对!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "查询停电统计分析列表服务调用失败,错误信息为:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----查询停电统计分析列表服务调用结束----------");
    	return response;
    }

    public StatisticsFaultLockStationListInfoSrvResponse statisticsFaultLockStationListInfoSrv(MsgHeader msgHeader, StatisticsFaultLockStationListInfoSrvRequest statisticsFaultLockStationListInfoSrvRequest)
    {
    	StatisticsFaultLockStationListInfoSrvResponse response = new StatisticsFaultLockStationListInfoSrvResponse();
    	String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----查询锁站统计分析列表服务调用开始----------");
        try{
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultLockStationListInfoSrvRequest.getStatisticsType());
            if("15".equals(statisticsType)||"16".equals(statisticsType)||"17".equals(statisticsType)){
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                String date = "2014-07-18";
                String modelId="";
                if("15".equals(statisticsType)){//锁站统计分析2G基站
                	modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultLockStation2GByCountry"));                   
                }else if("16".equals(statisticsType)){//锁站统计分析3G基站
                	modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultLockStation3GByCountry"));                                       
                }else if("17".equals(statisticsType)){//锁站统计分析4G基站
                	modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultLockStation4GByCountry"));                                       
                }
                String listModelId="";
                if("15".equals(statisticsType)){//锁站统计分析2G基站
                	listModelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultLockStation2GListInfo"));                    
                }else{
                	listModelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultLockStation3GListInfo"));                                       
                }
                ITawStisticDetailSrv service = (ITawStisticDetailSrv)ApplicationContextHolder.getInstance().getBean("iTawStisticDetailSrv");
                //TawStatisticDetailSrv service = new TawStatisticDetailSrv();
                String areaId = BaseUtil.nullObject2String(statisticsFaultLockStationListInfoSrvRequest.getAreaId());
                String countryId = BaseUtil.nullObject2String(statisticsFaultLockStationListInfoSrvRequest.getCountryId());
                String whereCondition="  AREA_id='" + areaId + "' and CNTY_Id='"+countryId+"'";
                if("15".equals(statisticsType)){//锁站统计分析2G基站
                	String statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.lockstation2G"));                                       
                	if(!"".equals(statisticsCondition)){
                    	whereCondition=whereCondition+" and "+statisticsCondition;              
                    }
                }else if("16".equals(statisticsType)){//锁站统计分析3G基站
                	String statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.lockstation3G"));                                       
                	if(!"".equals(statisticsCondition)){
                    	whereCondition=whereCondition+" and "+statisticsCondition;              
                    }
                }else if("17".equals(statisticsType)){//锁站统计分析4G基站
                	String statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.lockstation4G"));                                       
                	if(!"".equals(statisticsCondition)){
                    	whereCondition=whereCondition+" and "+statisticsCondition;              
                    }
                }             
                String statisticsResult = service.getDetailPageXml(modelId, listModelId,date,whereCondition,"");
                logger.info(" statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult)){
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getDetailOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultLockStationListInfo"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultLockStationListInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("查询锁站统计分析列表服务调用报错,传入的statisticsType不对!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "查询锁站统计分析列表服务调用失败,错误信息为:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----查询锁站统计分析列表服务调用结束----------");
    	return response;
    }
    
    //新添加start
    //故障工单状态统计分析（按地市）服务
    public StatisticsFaultStatusByAreaSrvResponse statisticsFaultStatusByAreaSrv(MsgHeader msgHeader, StatisticsFaultStatusByAreaSrvRequest statisticsFaultStatusByAreaSrvRequest)
    {
    	StatisticsFaultStatusByAreaSrvResponse response = new StatisticsFaultStatusByAreaSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultStatusByAreaSrvRequest.getStatisticsType());
            System.out.println("statisticsType--------------->"+statisticsType);
            if("27".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
        		//获取当前用户所在地市信息
        		String areaCondition="";
        		String operateUserId=BaseUtil.nullObject2String(msgHeader.getOpUserId());
        		
        		ITawSystemUserService tawSystemUserBo=(ITawSystemUserService)ApplicationContextHolder.getInstance().getBean("iTawSystemUserService");
        		ITawSystemDeptService itawSystemDeptService=(ITawSystemDeptService)ApplicationContextHolder.getInstance().getBean("iTawSystemDeptService");
        		//ITawSystemUserBo tawSystemUserBo=(ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
        		TawSystemUser user=tawSystemUserBo.getUserByuserid(operateUserId);
        		if(user!=null){
        			String deptId=BaseUtil.nullObject2String(user.getDeptid());
        			
        			TawSystemDept dept=itawSystemDeptService.queryById(deptId); //TawSystemDeptBo.getInstance().getDeptinfobydeptid(deptId,"0");
        			
        			String areaId=BaseUtil.nullObject2String(dept.getAreaid());
        			if(!"29".equals(areaId)){//29-省公司
        				areaCondition=" AND TODEPTID='" + areaId + "'";
        			}
        		}
        		logger.info(" areaCondition=" + areaCondition);
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultStatusByArea"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String statisticsResult = service.showStatisticResult(modelId, date, "week", areaCondition);//数据来源
                logger.info( " statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultStatusByArea"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultStatusByAreaSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }
    
    //故障工单状态统计分析（按地市+区县）服务
    public StatisticsFaultStatusByCountrySrvResponse statisticsFaultStatusByCountrySrv(MsgHeader msgHeader, StatisticsFaultStatusByCountrySrvRequest statisticsFaultStatusByCountrySrvRequest)
    {
    	StatisticsFaultStatusByCountrySrvResponse response = new StatisticsFaultStatusByCountrySrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultStatusByCountrySrvRequest.getStatisticsType());
            System.out.println("statisticsType--------------->"+statisticsType);
            if("28".equals(statisticsType)||"38".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
                String modelId ="";
               // TawStatisticService service = new TawStatisticService();
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String areaId = BaseUtil.nullObject2String(statisticsFaultStatusByCountrySrvRequest.getAreaId());
               // String countyId = BaseUtil.nullObject2String(statisticsFaultStatusByCountrySrvRequest.getCountyId());
                String whereb="";
                if ("28".equals(statisticsType)) {
                	modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultStatusByCountry"));
                	if(areaId!=null&&!"".equals(areaId)){
                    	whereb=" and todeptid='"+areaId+"' ";
                    }
                }else {
					modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultStatusByGroup"));
					if(areaId!=null&&!"".equals(areaId)){
	                	whereb=" and mainfaultgenerantcounty='"+areaId+"' ";
	                }else{
						whereb=" and mainfaultgenerantcounty is null";
					}
                }
                
                
                String statisticsResult = service.showStatisticResult(modelId, date, "week", whereb);
                
                logger.info( " statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = "";
                    if ("28".equals(statisticsType)) {
                    	nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultStatusByCountry"));
                    }else {
                    	nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultStatusByGroup"));
                    }
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        logger.info("opDetail---------:="  + opDetail);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultStatusByCountrySrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }
    
    //故障类型统计分析（按地市）服务
    public StatisticsFaultTypeByAreaSrvResponse statisticsFaultTypeByAreaSrv(MsgHeader msgHeader, StatisticsFaultTypeByAreaSrvRequest statisticsFaultTypeByAreaSrvRequest)
    {
    	StatisticsFaultTypeByAreaSrvResponse response = new StatisticsFaultTypeByAreaSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultTypeByAreaSrvRequest.getStatisticsType());
            System.out.println("statisticsType--------------->"+statisticsType);
            if("29".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
        		//获取当前用户所在地市信息
        		String areaCondition="";
        		String operateUserId=BaseUtil.nullObject2String(msgHeader.getOpUserId());
        		
        		ITawSystemUserService tawSystemUserBo=(ITawSystemUserService)ApplicationContextHolder.getInstance().getBean("iTawSystemUserService");
        		ITawSystemDeptService itawSystemDeptService=(ITawSystemDeptService)ApplicationContextHolder.getInstance().getBean("iTawSystemDeptService");
        		//ITawSystemUserBo tawSystemUserBo=(ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
        		TawSystemUser user=tawSystemUserBo.getUserByuserid(operateUserId);
        		if(user!=null){
        			String deptId=BaseUtil.nullObject2String(user.getDeptid());
        			
        			TawSystemDept dept=itawSystemDeptService.queryById(deptId); //TawSystemDeptBo.getInstance().getDeptinfobydeptid(deptId,"0");
        			
        			String areaId=BaseUtil.nullObject2String(dept.getAreaid());
        			if(!"29".equals(areaId)){//29-省公司
        				areaCondition=" AND TODEPTID='" + areaId + "'";
        			}
        		}
        		logger.info(" areaCondition=" + areaCondition);
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultTypeByArea"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String statisticsResult = service.showStatisticResult(modelId, date, "week", areaCondition);
                logger.info( " statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultTypeByArea"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultTypeByAreaSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }
    
    //故障类型统计分析（按地市+区县）服务
    public StatisticsFaultTypeByCountrySrvResponse statisticsFaultTypeByCountrySrv(MsgHeader msgHeader, StatisticsFaultTypeByCountrySrvRequest statisticsFaultTypeByCountrySrvRequest)
    {
    	StatisticsFaultTypeByCountrySrvResponse response = new StatisticsFaultTypeByCountrySrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultTypeByCountrySrvRequest.getStatisticsType());
            System.out.println("statisticsType--------------->"+statisticsType);
            if("30".equals(statisticsType)||"39".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
                String modelId = "";
               // TawStatisticService service = new TawStatisticService();
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String areaId = BaseUtil.nullObject2String(statisticsFaultTypeByCountrySrvRequest.getAreaId());
                String whereb="";
                if ("30".equals(statisticsType)) {
                	modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultTypeByCountry"));
                	if(areaId!=null&&!"".equals(areaId)){
                    	whereb=" and todeptid='"+areaId+"' ";
                    }
                }else {
					modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultTypeByGroup"));
					if(areaId!=null&&!"".equals(areaId)){
	                	whereb=" and mainfaultgenerantcounty='"+areaId+"' ";
	                }else{
						whereb=" and mainfaultgenerantcounty is null";
					}
                }
                
                String statisticsResult = service.showStatisticResult(modelId, date, "week", whereb);
                
                logger.info( " statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = "";
                    if ("30".equals(statisticsType)) {
                    	nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultTypeByCountry"));
                    }else {
                    	nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultTypeByGroup"));
                    }
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultTypeByCountrySrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }
    
    //处理及时率统计分析（按地市）服务
    public StatisticsFaultDealRateByAreaSrvResponse statisticsFaultDealRateByAreaSrv(MsgHeader msgHeader, StatisticsFaultDealRateByAreaSrvRequest statisticsFaultDealRateByAreaSrvRequest)
    {
    	StatisticsFaultDealRateByAreaSrvResponse response = new StatisticsFaultDealRateByAreaSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultDealRateByAreaSrvRequest.getStatisticsType());
            if("31".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
        		//获取当前用户所在地市信息
        		String areaCondition="";
        		String operateUserId=BaseUtil.nullObject2String(msgHeader.getOpUserId());
        		
        		ITawSystemUserService tawSystemUserBo=(ITawSystemUserService)ApplicationContextHolder.getInstance().getBean("iTawSystemUserService");
        		ITawSystemDeptService itawSystemDeptService=(ITawSystemDeptService)ApplicationContextHolder.getInstance().getBean("iTawSystemDeptService");
        		//ITawSystemUserBo tawSystemUserBo=(ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
        		TawSystemUser user=tawSystemUserBo.getUserByuserid(operateUserId);
        		if(user!=null){
        			String deptId=BaseUtil.nullObject2String(user.getDeptid());
        			
        			TawSystemDept dept=itawSystemDeptService.queryById(deptId); //TawSystemDeptBo.getInstance().getDeptinfobydeptid(deptId,"0");
        			
        			String areaId=BaseUtil.nullObject2String(dept.getAreaid());
        			if(!"29".equals(areaId)){//7-省公司
        				areaCondition=" AND TODEPTID='" + areaId + "'";
        			}
        		}
        		logger.info(" areaCondition=" + areaCondition);
                String modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultDealRateByArea"));
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String statisticsResult = service.showStatisticResult(modelId, date, "week", areaCondition);
                logger.info( " statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultDealRateByArea"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultDealRateByAreaSrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u6545\u969C\u5206\u7C7B\u5206\u6790\uFF08\u6309\u7F51\u7EDC\u4E00\u7EA7\u5206\u7C7B\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }
    
    //处理及时率统计分析（按地市+区县）服务
    public StatisticsFaultDealRateByCountrySrvResponse statisticsFaultDealRateByCountrySrv(MsgHeader msgHeader, StatisticsFaultDealRateByCountrySrvRequest statisticsFaultDealRateByCountrySrvRequest)
    {
    	StatisticsFaultDealRateByCountrySrvResponse response = new StatisticsFaultDealRateByCountrySrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u5F00\u59CB----------");
        try
        {
            String statisticsType = BaseUtil.nullObject2String(statisticsFaultDealRateByCountrySrvRequest.getStatisticsType());
            if("32".equals(statisticsType)||"40".equals(statisticsType))
            {
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
                String modelId = "";
               // TawStatisticService service = new TawStatisticService();
                TawStatisticService service =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String areaId = BaseUtil.nullObject2String(statisticsFaultDealRateByCountrySrvRequest.getAreaId());
                String whereb="";
                if ("32".equals(statisticsType)) {
                	modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultDealRateByCountry"));
                	if(areaId!=null&&!"".equals(areaId)){
                    	whereb=" and todeptid='"+areaId+"' ";
                    }
                }else {
					modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultDealRateByGroup"));
					if(areaId!=null&&!"".equals(areaId)){
	                	whereb=" and mainfaultgenerantcounty='"+areaId+"' ";
	                }else{
						whereb=" and mainfaultgenerantcounty is null";
					}
                }
                
                String statisticsResult = service.showStatisticResult(modelId, date, "week", whereb);
                
                logger.info( " statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult))
                {
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = "";
                    if ("32".equals(statisticsType)) {
                    	nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultDealRateByCountry"));
                    }else {
                    	nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultDealRateByGroup"));
                    }
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    logger.info( nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
                response.setStatisticsFaultDealRateByCountrySrvInfo("<opDetail>" + opDetailResult + "</opDetail>");
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u62A5\u9519,\u4F20\u5165\u7684statisticsType\u4E0D\u5339\u914D!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528,\u8BE6\u7EC6\u4FE1\u606F\u4E3A:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----\u6545\u969C\u533A\u57DF\u5206\u6790\uFF08\u6309\u5730\u5E02+\u533A\u53BF\uFF09\u670D\u52A1\u8C03\u7528\u7ED3\u675F----------");
        return response;
    }
    
    //查询工单状态、故障类型、处理及时率列表服务 
    public StatisticsNewFaultListInfoSrvResponse statisticsNewFaultListInfoSrv(MsgHeader msgHeader, StatisticsNewFaultListInfoSrvRequest statisticsNewFaultListInfoSrvRequest)
    {
    	StatisticsNewFaultListInfoSrvResponse response = new StatisticsNewFaultListInfoSrvResponse();
        String serviceFlag = "TRUE";
        String serviceMessage = "";
        String opDetail = "";
        String opDetailResult = "";
        System.out.println("-----查询工单状态、故障类型、处理及时率列表服务调用开始----------");
        try{
            String statisticsType = BaseUtil.nullObject2String(statisticsNewFaultListInfoSrvRequest.getStatisticsListType());
            System.out.println("statisticsType--------------->"+statisticsType);
            if("18".equals(statisticsType)||"19".equals(statisticsType)||"20".equals(statisticsType)||"21".equals(statisticsType)||
               "22".equals(statisticsType)||"23".equals(statisticsType)||"24".equals(statisticsType)||"25".equals(statisticsType)||
               "26".equals(statisticsType)||"27".equals(statisticsType)||"28".equals(statisticsType)||"29".equals(statisticsType)||
               "30".equals(statisticsType)||"31".equals(statisticsType)||"32".equals(statisticsType)||"33".equals(statisticsType)||
               "34".equals(statisticsType)||"35".equals(statisticsType)||"36".equals(statisticsType)||"37".equals(statisticsType)){
                AnalysisMapXml mapToXml = new AnalysisMapXml();
                Map resultMap = new HashMap();
                java.util.Date currentDate = new java.util.Date();
        		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        		String date = dateFormat.format(currentDate);
//                String date = "2014-06-28";
//                String modelId="";
//                if("1".equals(statisticsType)||"2".equals(statisticsType)||"3".equals(statisticsType)||"4".equals(statisticsType)){//故障区域分析
//                	modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultByAreaAndNetTwo"));                   
//                }else{//故障分类分析
//                	modelId = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsFaultByNetTwoAndCountry"));                                       
//                }
                String listModelId="";
                if("18".equals(statisticsType)||"19".equals(statisticsType)||"20".equals(statisticsType)||"21".equals(statisticsType)||
	               "22".equals(statisticsType)||"23".equals(statisticsType)||"24".equals(statisticsType)||"25".equals(statisticsType)||
	               "26".equals(statisticsType)||"27".equals(statisticsType)||"28".equals(statisticsType)||"29".equals(statisticsType)||
	               "30".equals(statisticsType)||"31".equals(statisticsType)||"32".equals(statisticsType)||"33".equals(statisticsType)||
	               "34".equals(statisticsType)){
                	listModelId=BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsNewFaultListInfo1"));
                }else{
                	listModelId=BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsNewFaultListInfo2"));
                }
                ITawStisticDetailSrv service = (ITawStisticDetailSrv)ApplicationContextHolder.getInstance().getBean("iTawStisticDetailSrv");
                TawStatisticService service1 =(TawStatisticService)ApplicationContextHolder.getInstance().getBean("iTawStatisticService");
                String areaId = BaseUtil.nullObject2String(statisticsNewFaultListInfoSrvRequest.getAreaId());
                String countryId = BaseUtil.nullObject2String(statisticsNewFaultListInfoSrvRequest.getCountryId());
                String netSortOneId = BaseUtil.nullObject2String(statisticsNewFaultListInfoSrvRequest.getNetSortOneId());
                String netSortOneName = BaseUtil.nullObject2String(statisticsNewFaultListInfoSrvRequest.getNetSortOneName());
                //String whereCondition=" and TODEPTID='" + areaId + "' and MAINFAULTGENERANTCOUNTY='"+countryId+"' and MAINNETSORTONE='"+netSortOneId+"'";
                String whereCondition= "" ;
                if (countryId.equals("")) {
                	 whereCondition=" and MAINFAULTGENERANTCOUNTY='" + areaId + "' and operateroleid is null ";
				}else {
					 whereCondition=" and MAINFAULTGENERANTCOUNTY='" + areaId + "' and operateroleid='"+countryId+"'";
				}
                String statisticsCondition = "";
                
//                String modelId=BaseUtil.nullObject2String(
//            			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsModelType.statisticsTabListInfo"));
                
                if("18".equals(statisticsType)){//工单状态分析工单总量列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.status1"));    
                }else if("19".equals(statisticsType)){//工单状态分析未接单总量列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.status2"));      
                }else if("20".equals(statisticsType)){//工单状态分析未接单未超时列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.status3"));    
                }else if("21".equals(statisticsType)){//工单状态分析未接单将超时（30分钟）列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.status4"));    
                }else if("22".equals(statisticsType)){//工单状态分析未接单已超时列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.status5"));   
                }else if("23".equals(statisticsType)){//工单状态分析已接单总量列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.status6"));    
                }else if("24".equals(statisticsType)){//工单状态分析已接单待回单(修改为未超时)列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.status7"));    
                }else if("25".equals(statisticsType)){//工单状态分析已接单将超时（1小时）列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.status8"));    
                }else if("26".equals(statisticsType)){//工单状态分析已接单回单超时列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.status9"));    
                }else if("27".equals(statisticsType)){//故障类型分析四专业工单总量列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.faultType1"));   
                }else if("28".equals(statisticsType)){//故障类型分析无线专业工单总量列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.faultType2"));     
                }else if("29".equals(statisticsType)){//故障类型分析动环专业工单总量列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.faultType3"));     
                }else if("30".equals(statisticsType)){//故障类型分析传输专业工单总量列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.faultType4"));  
                }else if("31".equals(statisticsType)){//故障类型分析WLAN专业工单总量列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.faultType5"));  
                }else if("32".equals(statisticsType)){//处理及时率分析工单总量列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.faultType6"));  
                }else if("33".equals(statisticsType)){//处理及时率分析接单超时受理量列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.faultType7"));   
                }else if("34".equals(statisticsType)){//处理及时率分析处理超时工单量列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.faultType8"));  
                }else if("35".equals(statisticsType)){//处理及时率分析处理超时工单量列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.dealRate1"));  
                }else if("36".equals(statisticsType)){//处理及时率分析处理超时工单量列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.dealRate2"));  
                }else if("37".equals(statisticsType)){//处理及时率分析处理超时工单量列表
                	statisticsCondition=BaseUtil.nullObject2String(
                			XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.dealRate3"));  
                }  
                
                if(!"".equals(statisticsCondition)){
                	whereCondition=whereCondition+" and "+statisticsCondition;              
                }
                System.out.println(" statisticsFaultListInfoSrv listModelId=" + listModelId);
                String statisticsResult = service.getDetailPageXml("", listModelId,date,whereCondition,"");
                //logger.info(" statisticsFaultListInfoSrv statisticsResult=" + statisticsResult);
                System.out.println(" statisticsFaultListInfoSrv statisticsResult=" + statisticsResult);
                List statisticsResultList = new ArrayList();
                if(!"".equals(statisticsResult)){
                    Map resultMapTemp = new HashMap();
                    statisticsResultList = OptDetail.getInstance().getDetailOpdetailList(statisticsResult, resultMapTemp);
                }
                if(statisticsResultList != null && statisticsResultList.size() > 0)
                {
                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsNewFaultListInfo"));
                    for(int i = 0; i < statisticsResultList.size(); i++)
                    {
                        Map listResultMap = (Map)statisticsResultList.get(i);
                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
                        opDetailResult = opDetailResult + opDetail;
                    }

                    //logger.info(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                    System.out.println(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
                }
                response.setStatisticsNewFaultListInfo("<opDetail>" + opDetailResult + "</opDetail>");
//                opDetail = "";
//                opDetailResult = "";
//                resultMap = new HashMap();
//                
//                String statisticsResult1 = service1.showStatisticResult(modelId, date, "day", whereCondition);
//                //logger.info( " statisticsResult1=" + statisticsResult1);
//                System.out.println(" statisticsResult1=" + statisticsResult1);
//                List statisticsResultList1 = new ArrayList();
//                if(!"".equals(statisticsResult1))
//                {
//                    Map resultMapTemp = new HashMap();
//                    statisticsResultList1 = OptDetail.getInstance().getOpdetailList(statisticsResult1, resultMapTemp);
//                }
//                if(statisticsResultList1 != null && statisticsResultList1.size() > 0)
//                {
//                    String nodeName = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsTabListInfo"));
//                    for(int i = 0; i < statisticsResultList1.size(); i++)
//                    {
//                        Map listResultMap = (Map)statisticsResultList1.get(i);
//                        resultMap = mapToXml.getColumnName(BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
//                        opDetail = mapToXml.getXmlFromMap(resultMap, BaseUtil.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
//                        opDetailResult = opDetailResult + opDetail;
//                    }
//
//                    //logger.info( nodeName + " opDetail1=" + "<opDetail>" + opDetailResult + "</opDetail>");
//                    System.out.println(nodeName + " opDetail1=" + "<opDetail>" + opDetailResult + "</opDetail>");
//                }
//                response.setStatisticsTabListInfo("<opDetail>" + opDetailResult + "</opDetail>");
//                
                response.setServiceFlag(serviceFlag);
                response.setServiceMessage(serviceMessage);
            } else
            {
                response.setServiceFlag("FALSE");
                response.setServiceMessage("查询工单状态、故障类型、处理及时率列表服务调用报错,传入的statisticsType不对!statisticsType=" + statisticsType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            serviceMessage = "查询工单状态、故障类型、处理及时率列表服务调用失败,错误信息为:" + e.getMessage();
            serviceFlag = "FALSE";
            response.setServiceFlag(serviceFlag);
            response.setServiceMessage(serviceMessage);
            return response;
        }
        System.out.println("----查询工单状态、故障类型、处理及时率列表服务调用结束----------");
        return response;
    }
    //新添加end

    public static void main(String args[])
        throws Exception
    {
//    	String statisticsResult = BaseUtil.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("statisticsCondition.powercut"));       
//    	Map sheetMap = new HashMap();
//        List resultList = OptDetail.getInstance().getDetailOpdetailList(statisticsResult, sheetMap);
//        AnalysisMapXml mapToXml = new AnalysisMapXml();
//        Map resultMap = new HashMap();
//        String opDetail = "";
//        String opDetailResult = "";
//        if(resultList != null && resultList.size() > 0)
//        {
//            String nodeName = StaticMethod.nullObject2String(XmlManage.getFile("/spring/statisticsOperation.xml").getProperty("interfaceType.statisticsFaultByAreaAndCountry"));
//            for(int i = 0; i < resultList.size(); i++)
//            {
//                Map listResultMap = (Map)resultList.get(i);
//                resultMap = mapToXml.getColumnName(StaticMethod.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName, listResultMap);
//                opDetail = mapToXml.getXmlFromMap(resultMap, StaticMethod.getFilePathForUrl("classpath:spring/statisticsOperation.xml"), nodeName);
//                opDetailResult = opDetailResult + opDetail;
//            }
//
//            System.out.println(nodeName + " opDetail=" + "<opDetail>" + opDetailResult + "</opDetail>");
//        }
    }
    
   
}