package com.boco.eoms.tawStatisticModel.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.boco.eoms.baseUtil.BaseUtil;
import com.boco.eoms.baseUtil.PageModel;
import com.boco.eoms.tawStatisticDetail.model.TawStatisticDetail;
import com.boco.eoms.tawStatisticDetail.service.ITawStatisticDetailService;
import com.boco.eoms.tawStatisticDetailItem.model.TawStatisticDetailItem;
import com.boco.eoms.tawStatisticDetailItem.service.ITawStatisticDetailItemService;
import com.boco.eoms.tawStatisticIndex.service.ITawStatisticIndexService;
import com.boco.eoms.tawStatisticModel.model.ColumnModel;
import com.boco.eoms.tawStatisticModel.service.ITawStatisticModelService;
import com.boco.eoms.tawStatisticModel.service.ITawStisticDetailSrv;
import com.boco.eoms.tawStatisticModel.util.CoreStatisticEngine;

@Service("iTawStisticDetailSrv")
public class TawStatisticDetailSrv implements ITawStisticDetailSrv{
	
	private static final Logger logger = Logger.getLogger(TawStatisticDetailSrv.class);
	//年粒度标识
	private static final String year_flag="year";
	//月粒度标识
	private static final String month_flag="month";
	//日粒度标识
	private static final String day_flag="day";
	
	@Resource
	private ITawStatisticModelService iTawStatisticModelService;
	@Resource
	private ITawStatisticDetailItemService iTawStatisticDetailItemService;
	@Resource
	private ITawStatisticDetailService iTawStatisticDetailService;
	@Resource
	private ITawStatisticIndexService iTawStatisticIndexService;
	
	
	public static Logger getLogger() {
		return logger;
	}


	//详单方法
	public String getDetailPageXml(String modelId_v,String detailpage_v,String time_v,String where_bufer,String reserve) throws ParseException{
		logger.info("mobile invoke statistic detail begin");
		String xml= getInitParam(detailpage_v,time_v,where_bufer);
		logger.info("mobile invoke statistic detail end");
		return xml;
	}
	
	
	//生成xml
	public String creatOpDetailXml(String [][] retData,List<ColumnModel> columns){
		   /** 建立document对象 */
	       Document document = DocumentHelper.createDocument();
	       Element recordInfos=document.addElement("recordInfos");
	       //生成xml
	       for(int i=0;i<retData.length;i++){
	    	   Element recordInfo=recordInfos.addElement("recordInfo");
	    	   for(int j=0;j<retData[i].length;j++){
	    		   ColumnModel tempColumn=columns.get(j);
		    		//指标新增
		    		Element fieldInfo=recordInfo.addElement("fieldInfo");
		    		fieldInfo.addElement("fieldId").setText("");
		    		fieldInfo.addElement("fieldCode").setText(tempColumn.getValue());
		    		fieldInfo.addElement("fieldCnName").setText(tempColumn.getColumn());
		    		fieldInfo.addElement("fieldValue").setText(retData[i][j]);
		    		fieldInfo.addElement("fieldTurnValue").setText("");
	    	   }
	       }
		   logger.info("generate xml:"+document.asXML());
			
		   return document.asXML();
		}
	
	
	//数据生成
	public String getInitParam(String detailpage_v,String time_v,String where_bufer) throws ParseException{
		//获取数据
		
		StringBuffer sbCondtion=new StringBuffer("");
		
		String startTime="";
		String endTime="";
		if(year_flag.equals("")){
			startTime=time_v.substring(0,3)+" 00:00:00";
			endTime=time_v.substring(0,3)+" 23:59:59";
		}else if(month_flag.equals("")){
			startTime=time_v.substring(0,3)+" 00:00:00";
			endTime=time_v.substring(0,3)+" 23:59:59";
		}else if(day_flag.equals("")){
			startTime=time_v+" 00:00:00";
			endTime=time_v+" 23:59:59";
		}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date=sdf.parse(time_v); //转化为时间类型
			Date date1=new Date(date.getTime()-7*24*60*60*1000);  //取前七天的时间
			startTime=sdf.format(date1)+" 00:00:00";
			endTime=time_v+" 23:59:59";
			
			if(!"".equals(startTime)){
				where_bufer+=" and sendTime>=to_date('"+startTime+"','yyyy-MM-dd hh24:mi:ss')";
			}
			if(!"".equals(endTime)){
				where_bufer+=" and sendTime<=to_date('"+endTime+"','yyyy-MM-dd hh24:mi:ss')";
			}
		
		 
        //获取详单
        TawStatisticDetail tawStatisticDetail= iTawStatisticDetailService.getTawStatisticDetailByDetailNum(detailpage_v);
        
        List<TawStatisticDetailItem> tawStatisticDetailItems=iTawStatisticDetailItemService.getDetailItemByDetailId(tawStatisticDetail.getId());
        //获取详单字段
      	
      	String[] graspColumn=new String[tawStatisticDetailItems.size()];
      	
      	ColumnModel columnModel=null;
      	List<ColumnModel> columns=new ArrayList<ColumnModel>();
      	for(int i=0;i<tawStatisticDetailItems.size();i++){
      		columnModel=new ColumnModel();
      		TawStatisticDetailItem tawStatisticDetailItem=tawStatisticDetailItems.get(i);
      		graspColumn[i]=tawStatisticDetailItem.getDetailColumnEng();
      		columnModel.setColumn(tawStatisticDetailItem.getDetailColumnChina());
      		columnModel.setValue(tawStatisticDetailItem.getDetailColumnEng());
      		columns.add(columnModel);
      	}
        
        sbCondtion.append(" "+BaseUtil.nullObject2String(where_bufer));		
		CoreStatisticEngine coreStatisticEngine=new CoreStatisticEngine();
	        
		String totalPageNum=coreStatisticEngine.getGraspDataArrayCount(graspColumn, sbCondtion.toString(), tawStatisticDetail.getGroupType())[0][0];
	 
	    String [][] data=coreStatisticEngine.getGraspDataArrayByPage(graspColumn, sbCondtion.toString(), tawStatisticDetail.getGroupType(),new PageModel(Integer.parseInt(totalPageNum),Integer.parseInt(totalPageNum),1));
	    
		return creatOpDetailXml(data,columns);
	}
}
