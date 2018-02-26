package com.boco.eoms.tawStatisticModel.mobileService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.boco.eoms.baseUtil.BaseUtil;
import com.boco.eoms.baseUtil.ComputeUtil;
import com.boco.eoms.tawStatisticIndex.model.TawStatisticIndex;
import com.boco.eoms.tawStatisticIndex.service.ITawStatisticIndexService;
import com.boco.eoms.tawStatisticModel.model.ComplexDimension;
import com.boco.eoms.tawStatisticModel.model.ComplexGrasp;
import com.boco.eoms.tawStatisticModel.model.TawStatisticModel;
import com.boco.eoms.tawStatisticModel.service.ITawStatisticModelService;
import com.boco.eoms.tawStatisticModel.util.CoreStatisticEngine;
import com.boco.eoms.tawStatisticModelItem.model.TawStatisticModelItem;
import com.boco.eoms.tawStatisticModelItem.service.ITawStatisticModelItemService;
import com.boco.eoms.tawStatisticParam.model.TawStatisticParam;
import com.boco.eoms.tawStatisticParam.service.ITawStatisticParamService;
import com.boco.eoms.tawSystemArea.model.TawSystemArea;
import com.boco.eoms.tawSystemArea.service.ITawSystemAreaService;
import com.boco.eoms.tawSystemDept.model.TawSystemDept;
import com.boco.eoms.tawSystemDept.service.ITawSystemDeptService;
import com.boco.eoms.tawSystemDictType.model.TawSystemDictType;
import com.boco.eoms.tawSystemDictType.service.ITawSystemDictTypeService;
import com.boco.eoms.tawSystemSubRole.model.TawSystemSubRole;
import com.boco.eoms.tawSystemSubRole.service.ITawSystemSubRoleService;
import com.boco.eoms.tawSystemUser.model.TawSystemUser;
import com.boco.eoms.tawSystemUser.service.ITawSystemUserService;


@Service("iTawStatisticService")
public class TawStatisticService {
	
	private static final Logger logger = Logger.getLogger(TawStatisticService.class);
	//年粒度标识
	private static final String year_flag="year";
	//月粒度标识
	private static final String month_flag="month";
	//日粒度标识
	private static final String day_flag="day";
	//周粒度标识
	private static final String week_flag="week";
	
	@Resource
	private ITawStatisticModelService iTawStatisticModelService;
	@Resource
	private ITawStatisticModelItemService iTawStatisticModelItemService;
	@Resource
	private ITawStatisticParamService iTawStatisticParamService;
	@Resource
	private ITawSystemDeptService iTawSystemDeptService;
	@Resource
	private ITawStatisticIndexService iTawStatisticIndexService;
	@Resource
	private ITawSystemAreaService iTawSystemAreaService;
	@Resource
	private ITawSystemDictTypeService iTawSystemDictTypeService;
	@Resource
	private ITawSystemUserService iTawSystemUserService;
	@Resource
	private ITawSystemSubRoleService iTawSystemSubRoleService;
	
	public static Logger getLogger() {
		return logger;
	}



	public String showStatisticResult(String modelId_v,String time_v,String cycle_v,String where_bufer) throws ParseException{
		
		System.out.println("mobile invoke statistic begin");
		
		System.out.println("--------------->>>>time_v"+time_v);
		System.out.println("modelId------------------------------>"+modelId_v);
		//获取数据
		Map<String,Object> retContent=getInitParam(modelId_v,time_v,cycle_v,where_bufer);
		//数据封装xml
		
		String tempXml=creatOpDetailXml((List<TawStatisticParam>)retContent.get("retDemension"),(List<TawStatisticIndex>)retContent.get("retIndex"),(String [][])retContent.get("retData"),(Map<String,Map<String,String>>)retContent.get("retTransferDimension"));
		

		
		System.out.println("mobile invoke statistic end");
		return tempXml;
	}
	
	//生成xml
	public String creatOpDetailXml(List<TawStatisticParam> retDemension,List<TawStatisticIndex> retIndex,String [][] retData,Map<String,Map<String,String>> transferMap){
		 /** 建立document对象 */
       Document document = DocumentHelper.createDocument();//<?xml version="1.0" encoding="UTF-8"?> 
       Element recordInfos=document.addElement("recordInfos");
      
       //生成xml
       for(int i=0;i<retData.length;i++){
    	   Element recordInfo=recordInfos.addElement("recordInfo");
    	   for(int j=0;j<retData[i].length;j++){
    		   System.out.println("xmli"+i+"--"+"xmlj"+j+":"+document.asXML());
	    	   if(j<retDemension.size()){
	    		 //维度新增
	    		   TawStatisticParam temp=retDemension.get(j);
	    		   Map<String,String> tempMap=transferMap.get(temp.getId());
	    		   Element fieldInfo=recordInfo.addElement("fieldInfo");
	    		   fieldInfo.addElement("fieldId").setText(temp.getId());
	    		   fieldInfo.addElement("fieldCode").setText(temp.getParamEng());
	    		   fieldInfo.addElement("fieldCnName").setText(temp.getParamName());
	    		   fieldInfo.addElement("fieldValue").setText(retData[i][j]);
	    		   fieldInfo.addElement("fieldTurnValue").setText(BaseUtil.null2String(tempMap.get(retData[i][j])));
	    		   //System.out.println("data"+i+"-"+j+"-------------------->"+temp.getParamName()+"------------------------"+retData[i][j]);
	    	   }else{
	    		 //指标新增
	    		   TawStatisticIndex temp=retIndex.get(j-retDemension.size());
	    		   Element fieldInfo=recordInfo.addElement("fieldInfo");
	    		   fieldInfo.addElement("fieldId").setText(temp.getId());
	    		   fieldInfo.addElement("fieldCode").setText("");
	    		   fieldInfo.addElement("fieldCnName").setText(temp.getIndicateName());
	    		   fieldInfo.addElement("fieldValue").setText(retData[i][j]);
	    		   fieldInfo.addElement("fieldTurnValue").setText("1");
	    		   //System.out.println("data"+i+"-"+j+"-------------------->"+temp.getIndicateName()+"------------------------"+retData[i][j]);
	    	   }
	    	   //logger.info("xmlj"+j+":"+document.asXML());
	    	   //System.out.println("xmlj"+j+":"+document.asXML());
    	   }
    	   //logger.info("xmli"+i+":"+document.asXML());
    	   //System.out.println("xmli"+i+":"+document.asXML());
       }
       
	   //logger.info("generate xml:"+document.asXML());
       System.out.println("generate xml:"+document.asXML());
		
	   return document.asXML();
	}
	
	//生成统计数据
	public Map<String,Object> getInitParam(String modelId_v,String time_v,String cycle_v,String where_bufer) throws ParseException{
		//获取数据
		String startTime="";
		String endTime="";
		if(year_flag.equals(cycle_v)){
			startTime=time_v.substring(0,3)+" 00:00:00";
			endTime=time_v.substring(0,3)+" 23:59:59";
		}else if(month_flag.equals(cycle_v)){
			startTime=time_v.substring(0,3)+" 00:00:00";
			endTime=time_v.substring(0,3)+" 23:59:59";
		}else if(day_flag.equals(cycle_v)){
			startTime=time_v+" 00:00:00";
			endTime=time_v+" 23:59:59";
		}else if(week_flag.equals(cycle_v)){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date=sdf.parse(time_v); //转化为时间类型
			Date date1=new Date(date.getTime()-7*24*60*60*1000);  //取前七天的时间
			startTime=sdf.format(date1)+" 00:00:00";
			endTime=time_v+" 23:59:59";
		}
		String itemIndexStr="";
		String itemDemensionStr="";
		
		
		if(!"".equals(startTime)){
			where_bufer+=" and sendTime>=to_date('"+startTime+"','yyyy-MM-dd hh24:mi:ss')";
		}
		if(!"".equals(endTime)){
			where_bufer+=" and sendTime<=to_date('"+endTime+"','yyyy-MM-dd hh24:mi:ss')";
		}
				
				
		
		List<TawStatisticModelItem> modelItems=null;
		List<TawStatisticModelItem> modelItems_demension=null;
		modelItems=iTawStatisticModelItemService.getModelItemByModelIdAndType(modelId_v, "1");
		modelItems_demension=iTawStatisticModelItemService.getModelItemDemensionRootByModelIdAndType(modelId_v, "2");
		for(int i=0;i<modelItems.size();i++){
			if(i==0){
				itemIndexStr=modelItems.get(i).getId();
			}else{
				itemIndexStr=itemIndexStr+","+modelItems.get(i).getId();
			}
		}
		for(int i=0;i<modelItems_demension.size();i++){
			if(i==0){
				itemDemensionStr=modelItems_demension.get(i).getId();
			}else{
				itemDemensionStr=itemDemensionStr+","+modelItems_demension.get(i).getId();
			}
		}

		int treeDep=iTawStatisticModelItemService.getLengthOfModelTree(modelId_v);
		
		Map<String,String> indexMap=new LinkedHashMap<String,String>();
		Map<String,String> paramMap=new LinkedHashMap<String,String>();
		
		List<TawStatisticParam> retDemension=new ArrayList<TawStatisticParam>();
		//获取所有统计维度对
		retDemension=getRetDemensionUtil(modelItems_demension,modelId_v);
		String dataType="";
		
		
		for(int i=0;i<retDemension.size();i++){
			paramMap.put("paramValue"+i, BaseUtil.null2String(retDemension.get(i).getParamEng()));
		}
		
		List<TawStatisticIndex> retIndex=new ArrayList<TawStatisticIndex>();
		List<ComplexDimension> complexs=new ArrayList<ComplexDimension>();
		
		Map<String,String> complexIndicateMap=new HashMap<String,String>();
		List<TawStatisticIndex> complexIndicate=new ArrayList<TawStatisticIndex>();
		
		
		TawStatisticModel tawStatisticModel=iTawStatisticModelService.getModelById(modelId_v);
		
		
		//获取父id为1的叶子节点
		for(int i=0;i<modelItems.size();i++){
			TawStatisticModelItem tawStatisticModelItem=modelItems.get(i);
			if("1".equals(tawStatisticModelItem.getParentItemId())&&"1".equals(tawStatisticModelItem.getIsLeaf())){
				if(tawStatisticModelItem.getItemId().contains("I")){
					//指标
					TawStatisticIndex tawStatisticIndex= iTawStatisticIndexService.getIndicateById(tawStatisticModelItem.getItemId());
					if("0".equals(tawStatisticIndex.getComplexFlag())){
						int tempNum=indexMap.size()/2;
						indexMap.put("indexValue"+tempNum, tawStatisticIndex.getIndicateValue());
						indexMap.put("condition"+tempNum, BaseUtil.null2String(tawStatisticIndex.getCondition()));//{condition0=and status='1', indexValue0=count(*)}
						retIndex.add(tawStatisticIndex);//TawStatisticIndex@264ab70a
						complexIndicateMap.put(tawStatisticIndex.getId(),String.valueOf(tempNum));
					}else{
						
						//复合指标String fir_indicate_no,String end_indicate_no,String operate_indicate
						complexIndicate.add(iTawStatisticIndexService.getIndicateById(tawStatisticModelItem.getItemId()));
					}
					
//					int tempNum=indexMap.size()/2;
//					indexMap.put("indexValue"+tempNum, tawStatisticIndex.getIndicateValue());
//					indexMap.put("condition"+tempNum, BaseUtil.null2String(tawStatisticIndex.getCondition()));
//					retIndex.add(tawStatisticIndex);
				}
			}else if("1".equals(tawStatisticModelItem.getParentItemId())&&"0".equals(tawStatisticModelItem.getIsLeaf())){
			    //带指标维度
				//叶子<--父节点【父类id-对应中间表】<--父节点【父类id-对应中间表】
				//生成复合表头，和指标值添加
				TawStatisticParam tawStatisticParam= iTawStatisticParamService.getParamById(tawStatisticModelItem.getItemId());
				complexs= getRetComplexDimensions(indexMap,tawStatisticModelItem,"",String.valueOf(treeDep),"1",tawStatisticParam.getParentParamId());
			}
		}
		
		String [][] retData=new String[0][0];
		if("system_demension".equals(dataType)){
			//
			if(paramMap.size()>0){
				retData=getDemensionRetData(indexMap,paramMap,tawStatisticModel.getGroupType(),where_bufer);	
			}
		}else{
			Set<Entry<String, String>> set = indexMap.entrySet();
			for(Entry<String, String> entry:set){
				String key = entry.getKey();  
	            String value = entry.getValue();  
	            System.out.println("指标map遍历结果-------------------------->"+key + " : " + value);
			}
			retData=getRetData(indexMap,paramMap,tawStatisticModel.getGroupType(),where_bufer);
			retData=getRetCompleIndicateData(retData,complexIndicate,complexIndicateMap,String.valueOf(paramMap.size()+indexMap.size()/2),String.valueOf(paramMap.size()));
		}
		
		//攥取转化
	   
	   Map<String,String> countMap=new HashMap<String,String>();
	   countMap.put("count", "1");
	   List<ComplexGrasp> retComplexGrsp=new ArrayList<ComplexGrasp>();
	   retComplexGrsp(countMap,complexs,retComplexGrsp);
	   
	   //列表维度转化树
	   Map<String,Map<String,String>> retTransferDimension=new HashMap<String,Map<String,String>>();
	   Map<String,String> inputMap=new HashMap<String,String>();
	   for(int i=0;i<retDemension.size();i++){
		   TawStatisticParam tawStatisticParam=retDemension.get(i);
		   retTransferDimension.put(tawStatisticParam.getId(),transferDemension(tawStatisticParam.getParentParamId(),tawStatisticParam.getParamCodeType(),inputMap));
	   }
	   
	   
	   Map<String,Object> retMap=new HashMap<String,Object>();
	   
	   retMap.put("dataType", dataType);
	   retMap.put("retComplexGrsp", retComplexGrsp);
	   retMap.put("retComplexTitle", complexs);
	   retMap.put("treeDeep", treeDep);
	   //retMap.put("retIndex", retIndex);
	   complexIndicate.addAll(0,retIndex);
	   retMap.put("retIndex", complexIndicate);
	   retMap.put("retDemension", retDemension);
	   retMap.put("retData", retData);
	   retMap.put("retTransferDimension", retTransferDimension);
	   retMap.put("retItemIndexStr", itemIndexStr);
	   retMap.put("retItemDemensionStr", itemDemensionStr);

	   return retMap;
	}
	
	public String[][] getRetCompleIndicateData(String[][] retdata,List<TawStatisticIndex> complexIndicate,Map<String,String> complexIndicateMap,String retColLen,String demenLen){
		
		String[][] tempData=new String[retdata.length][Integer.parseInt(retColLen)+complexIndicate.size()];
		
		
		for(int i=0;i<retdata.length;i++){
			for(int j=0;j<Integer.parseInt(retColLen)+complexIndicate.size();j++){
				if(j<retdata[i].length){
					tempData[i][j]=retdata[i][j];
				}else{
					TawStatisticIndex tawStatisticIndex=complexIndicate.get(j-Integer.parseInt(retColLen));
					if((null!=complexIndicateMap.get(tawStatisticIndex.getIndicatorFir_add()))&&(null!=complexIndicateMap.get(tawStatisticIndex.getIndicatorEnd_add()))){
						tempData[i][j]=ComputeUtil.simpleCompute(retdata[i][Integer.parseInt(demenLen)+Integer.parseInt(complexIndicateMap.get(tawStatisticIndex.getIndicatorFir_add()))],retdata[i][Integer.parseInt(demenLen)+Integer.parseInt(complexIndicateMap.get(tawStatisticIndex.getIndicatorEnd_add()))], tawStatisticIndex.getOperateFlag(),tawStatisticIndex.getPercentSign());
					}else{
						tempData[i][j]="0";
					}
					
					
				}
				
			}
			
			
		}
		
		return tempData; 
	}
	
	
	public Map<String,String> transferDemension(String parentCode,String type,Map<String,String> inputMap){
		List<TawSystemArea> tempArea=null;
		List<TawSystemDictType> tempDictType=null;
		List<TawSystemDept> tempDeptType=null;
		List<TawSystemUser> tempUserType=null;
		List<TawSystemSubRole> tempSubRoleType=null;
		
		if("areaType".equals(type)){
			
			tempArea=iTawSystemAreaService.queryAreasByParentId(parentCode);
			for(int i=0;i<tempArea.size();i++){
				TawSystemArea tawSystemArea=tempArea.get(i);
				if(null!=tawSystemArea.getAreaid()){
					inputMap.put(tawSystemArea.getAreaid(), tawSystemArea.getAreaname());
				}
				if(iTawSystemAreaService.queryAreasByParentId(tawSystemArea.getAreaid()).size()>0){
					transferDemension(tawSystemArea.getAreaid(),"areaType",inputMap);	
				}
			}
		}else if("dictType".equals(type)){
			tempDictType=iTawSystemDictTypeService.queryAllDictTypesByParentId(parentCode);
			for(int i=0;i<tempDictType.size();i++){
				TawSystemDictType tawSystemDictType=tempDictType.get(i);
				if(null!=tawSystemDictType.getDictid()){
					inputMap.put(tawSystemDictType.getDictid(), tawSystemDictType.getDictname());
				}
			}
		}else if("deptType".equals(type)){
			tempDeptType=iTawSystemDeptService.queryAllDeptByParentDeptID(parentCode);
			for(int i=0;i<tempDeptType.size();i++){
				TawSystemDept tawSystemDept=tempDeptType.get(i);
				if(null!=tawSystemDept.getDeptid()){
					inputMap.put(tawSystemDept.getDeptid(), tawSystemDept.getDeptname());
				}
			}
		}else if("personType".equals(type)){
			tempUserType=iTawSystemUserService.getAllUsers();
			for(int i=0;i<tempUserType.size();i++){
				TawSystemUser tawSystemUser=tempUserType.get(i);
				if(null!=tawSystemUser.getUserid()){
					inputMap.put(tawSystemUser.getUserid(), tawSystemUser.getUsername());
				}
			}
		}else if("roleType".equals(type)){
			tempSubRoleType=iTawSystemSubRoleService.getAllSubRoles();
			for(int i=0;i<tempSubRoleType.size();i++){
				TawSystemSubRole tawSystemSubRole=tempSubRoleType.get(i);
				if(null!=tawSystemSubRole.getId()){
					inputMap.put(tawSystemSubRole.getId(), tawSystemSubRole.getSubrolename());
				}
			}
		}
		return inputMap;
	}
	
	/**
	 * 
	 *生成统计数据 
	 */
	
	public String[][] getRetData(Map<String,String> indexMap,Map<String,String> paramMap,String groupType,String whereString){
		String[] index=new String[indexMap.size()/2];
		String[] dimensions=new String[paramMap.size()];
		String[] queryCondition=new String[indexMap.size()/2];
		//遍历map封装数据
		Iterator<Map.Entry<String, String>> it_index = indexMap.entrySet().iterator();
		int count=0;
		while (it_index.hasNext()) {
			 Map.Entry<String, String> entry = it_index.next();
			 if(entry.getKey().indexOf("indexValue")>-1){
			   index[Integer.parseInt(BaseUtil.getNumber(entry.getKey()))]=indexMap.get("indexValue"+BaseUtil.getNumber(entry.getKey()));  ///
			   queryCondition[Integer.parseInt(BaseUtil.getNumber(entry.getKey()))]=BaseUtil.null2String( indexMap.get("condition"+BaseUtil.null2String(BaseUtil.getNumber(entry.getKey()))));
			   count++;
		   }
		  }
		
		Iterator<Map.Entry<String, String>> it_param = paramMap.entrySet().iterator();
		int count_param=0;
		while (it_param.hasNext()) {
		   Map.Entry<String, String> entry = it_param.next();
		   dimensions[count_param]=entry.getValue();
		   count_param++;
		  }
		
		CoreStatisticEngine coreStatisticEngine=new CoreStatisticEngine();
		for(String indicateValue:index){
            System.out.println("指标数组遍历结果-------------------------->"+indicateValue);
		}
		return coreStatisticEngine.getStatisticCombineRet(index,dimensions,queryCondition,groupType,whereString);
	}
	
	/**
	 * 
	 *生成系统维度统计数据 
	 */
	public String[][] getDemensionRetData(Map<String,String> indexMap,Map<String,String> paramMap,String groupType,String whereString){
		String[] index=new String[indexMap.size()/4];
		String[] dimensions=new String[paramMap.size()/3];
		String[] dimensionsValue=new String[paramMap.size()/3];
		String[] queryCondition=new String[paramMap.size()/3];
		String rateCompte="";
		String indexNo="";
		int countValue=0;
		//返回字符串
		String rateType="";
		
		//遍历map封装数据
		Iterator<Map.Entry<String, String>> it_index = indexMap.entrySet().iterator();
		while (it_index.hasNext()) {
			Map.Entry<String, String> entry = it_index.next();
			
			//指标算法判断
			if(entry.getKey().indexOf("indexRate")>-1){
				String rateFlag=indexMap.get("indexRate"+BaseUtil.getNumber(entry.getKey()));
				if(!"0".equals(rateFlag)){
					if(rateCompte.length()==0){
						rateCompte=indexMap.get("indexNo"+BaseUtil.getNumber(entry.getKey()));
						rateType=rateFlag;
					}else{
						rateCompte=rateCompte+","+indexMap.get("indexNo"+BaseUtil.getNumber(entry.getKey()));
						rateType=rateType+","+rateFlag;
					}
				}else{
					countValue++;
					index[Integer.parseInt(BaseUtil.getNumber(entry.getKey()))]=indexMap.get("indexValue"+BaseUtil.getNumber(entry.getKey()));
					if(indexNo.length()==0){
						indexNo=indexMap.get("indexNo"+BaseUtil.getNumber(entry.getKey()));
					}else{
						indexNo=indexNo+","+indexMap.get("indexNo"+BaseUtil.getNumber(entry.getKey()));
					}
				}	
			}
		}
		
		Iterator<Map.Entry<String, String>> it_param = paramMap.entrySet().iterator();
		while (it_param.hasNext()) {
			Map.Entry<String, String> entry = it_param.next();
			if(entry.getKey().indexOf("paramCode")>-1){
				dimensions[Integer.parseInt(BaseUtil.getNumber(entry.getKey()))]=paramMap.get("paramColumn"+BaseUtil.getNumber(entry.getKey()));
				dimensionsValue[Integer.parseInt(BaseUtil.getNumber(entry.getKey()))]=paramMap.get("paramCode"+BaseUtil.getNumber(entry.getKey()));
				queryCondition[Integer.parseInt(BaseUtil.getNumber(entry.getKey()))]=paramMap.get("paramCondition"+BaseUtil.getNumber(entry.getKey()));
			}
		}
		
		//封装
		
		CoreStatisticEngine coreStatisticEngine=new CoreStatisticEngine();
		
		return coreStatisticEngine.getStatisticSoleCombineRet(index, dimensions, dimensionsValue, queryCondition, groupType, whereString,rateCompte,indexNo,countValue,rateType);
		
	}
	
	public List<ComplexGrasp> retComplexGrsp(Map<String,String> countMap,List<ComplexDimension> complexs,List<ComplexGrasp> complexGrasps){
		
		for(int i=0;i<complexs.size();i++){
			ComplexDimension tempComplexDimension=complexs.get(i);
			
			if(!"".equals(tempComplexDimension.getIndexId())){
				//指标
				ComplexGrasp tempComplexGrasp=new ComplexGrasp();
				tempComplexGrasp.setIndexId(tempComplexDimension.getIndexId());
				
				
				tempComplexGrasp.setCountNum(countMap.get("count"));
				tempComplexGrasp.setCondition(tempComplexDimension.getCondition());
				complexGrasps.add(tempComplexGrasp);
				
				countMap.put("count", String.valueOf(Integer.valueOf(countMap.get("count"))+1));
			}else{
				//维度
				retComplexGrsp(countMap,tempComplexDimension.getChildDimension(),complexGrasps);
			}
		}
		
		return complexGrasps;
	}
	
	
	public List<TawStatisticParam> getRetDemensionUtil(List<TawStatisticModelItem> modelItems_demension,String modelid){
		
		List<TawStatisticParam> retParam=new ArrayList<TawStatisticParam>();
		TawStatisticParam tempTawStatisticParam=null;
		TawStatisticParam tempChildTawStatisticParam=null;
		for(int i=0;i<modelItems_demension.size();i++){
			TawStatisticModelItem tawStatisticModelItem=modelItems_demension.get(i);
			tempTawStatisticParam=iTawStatisticParamService.getParamById(tawStatisticModelItem.getItemId());
			tempTawStatisticParam.setModelItemId(tawStatisticModelItem.getId());
			tempChildTawStatisticParam=getChildTawStatisticParam(tawStatisticModelItem.getId(),modelid);
			if(null!=tempChildTawStatisticParam){
				tempTawStatisticParam.setChildrenParam(tempChildTawStatisticParam);
			}
			
			retParam.add(tempTawStatisticParam);	
		}
		return retParam;
	}
	
	public TawStatisticParam getChildTawStatisticParam(String modelItemId,String modelId){
		TawStatisticParam tempTawStatisticParam=null;
		
		TawStatisticModelItem tempChildStatisticParam=iTawStatisticModelItemService.getModelItemByparentModelItemIdAndType(modelItemId, "2");
		
		if(null!=tempChildStatisticParam){
			tempTawStatisticParam=iTawStatisticParamService.getParamById(tempChildStatisticParam.getItemId());
			tempTawStatisticParam.setModelItemId(tempChildStatisticParam.getId());
			tempTawStatisticParam.setChildrenParam(getChildTawStatisticParam(tempChildStatisticParam.getId(),modelId));
		}
		return tempTawStatisticParam;
	}
	
	public List<ComplexDimension> getRetComplexDimensions(Map<String,String> indexMap,TawStatisticModelItem tawStatisticModelItem,String condition,String totalLayer,String parentItemId,String parentCodeId){
			
			List<ComplexDimension> complexDimensions=new ArrayList<ComplexDimension>();
			
			if("1".equals(tawStatisticModelItem.getIsLeaf())){
				//为叶子节点
				TawStatisticIndex tawStatisticIndex= iTawStatisticIndexService.getIndicateById(tawStatisticModelItem.getItemId());
				//添加指标进入指标map
				int tempNum=indexMap.size()/2;
				indexMap.put("indexValue"+tempNum, tawStatisticIndex.getIndicateValue());
				indexMap.put("condition"+tempNum,BaseUtil.null2String(condition)+tawStatisticIndex.getCondition());
				ComplexDimension tempComplex=new ComplexDimension();
				
				tempComplex.setName(tawStatisticIndex.getIndicateName());
				tempComplex.setColumnName(tawStatisticIndex.getIndicateValue());
				tempComplex.setCurrentLayer(totalLayer);
				tempComplex.setTotalLayer(totalLayer);
				tempComplex.setCloumnSpan("1");
				tempComplex.setRowSpan("1");
				tempComplex.setTotalLayer(totalLayer);
				tempComplex.setCondition(condition);
				tempComplex.setIndexId(tawStatisticIndex.getId());
				complexDimensions.add(tempComplex);
				
			}else{
				if(parentItemId.equals(tawStatisticModelItem.getItemId())){
					//判断是否为类别三：递归获取当前子类
					//parentCodeId
					//List<TawStatisticModelItem> childrenModelItems= iTawStatisticModelItemService.getModelItemByParentId(tawStatisticModelItem.getItemId());
					List<TawStatisticModelItem> childrenModelItems= iTawStatisticModelItemService.getModelItemByparentModelItemId(tawStatisticModelItem.getId());
					TawStatisticParam tawStatisticParam= iTawStatisticParamService.getParamById(tawStatisticModelItem.getItemId());
					
					List<TawSystemArea> areas=null;
					List<TawSystemDept> depts=null;
					List<TawSystemDictType> dictTypes=null;
					ComplexDimension tempComplex=null;
					List<ComplexDimension> temChildList=null;
					
					//获取维度下所有值
					if("areaType".equals(tawStatisticParam.getParamCodeType())){
						areas= iTawSystemAreaService.queryAreasByParentId(parentCodeId);
						for(int i=0;i<areas.size();i++){
							temChildList=new ArrayList<ComplexDimension>();
							TawSystemArea temp=areas.get(i);
							tempComplex=new ComplexDimension();
							tempComplex.setName(temp.getAreaname());
							
							tempComplex.setCondition(condition+"and "+tawStatisticParam.getParamEng()+"='"+temp.getAreaid()+"'");
							
							tempComplex.setColumnName(tawStatisticParam.getParamEng());
							tempComplex.setCurrentLayer(String.valueOf(tawStatisticModelItem.getItemCode().length()/2));
							tempComplex.setTotalLayer(totalLayer);
							//子节点元素
							for(int m=0;m<childrenModelItems.size();m++){
								temChildList.addAll(getRetComplexDimensions(indexMap,childrenModelItems.get(m),condition+"and "+tawStatisticParam.getParamEng()+"='"+temp.getAreaid()+"'",totalLayer,tawStatisticModelItem.getItemId(),temp.getAreaid()));						
							}
							
							tempComplex.setChildDimension(temChildList);
							
							if(temChildList.size()>0){
								tempComplex.setRowSpan("1");
								int totalColumnSpan=0;
								for(int j=0;j<temChildList.size();j++){
									totalColumnSpan+=Integer.parseInt(temChildList.get(j).getCloumnSpan());
								}
								
								tempComplex.setCloumnSpan(String.valueOf(totalColumnSpan));
							}else{
								tempComplex.setRowSpan(String.valueOf(Integer.valueOf(totalLayer)-Integer.valueOf(tempComplex.getCurrentLayer())));
								tempComplex.setCloumnSpan("1");
							}
							tempComplex.setIndexId("");
							complexDimensions.add(tempComplex);
		
						}
						
					}else if("deptType".equals(tawStatisticParam.getParamCodeType())){
						depts=  iTawSystemDeptService.queryDeptByParentDeptID(parentCodeId);
						for(int i=0;i<depts.size();i++){
							temChildList=new ArrayList<ComplexDimension>();
							TawSystemDept temp=depts.get(i);
							tempComplex=new ComplexDimension();
							tempComplex.setName(temp.getDeptname());
							
							tempComplex.setCondition(condition+"and "+tawStatisticParam.getParamEng()+"='"+temp.getDeptid()+"'");
							
							tempComplex.setColumnName(tawStatisticParam.getParamEng());
							tempComplex.setCurrentLayer(String.valueOf(tawStatisticModelItem.getItemCode().length()/2));
							tempComplex.setTotalLayer(totalLayer);
							//子节点元素
							for(int m=0;m<childrenModelItems.size();m++){
								temChildList.addAll(getRetComplexDimensions(indexMap,childrenModelItems.get(m),condition+"and "+tawStatisticParam.getParamEng()+"='"+temp.getDeptid()+"'",totalLayer,tawStatisticModelItem.getItemId(),temp.getDeptid()));						
							}
							
							tempComplex.setChildDimension(temChildList);
							
							
							if(temChildList.size()>0){
								tempComplex.setRowSpan("1");
								int totalColumnSpan=0;
								for(int j=0;j<temChildList.size();j++){
									totalColumnSpan+=Integer.parseInt(temChildList.get(j).getCloumnSpan());
								}
								tempComplex.setCloumnSpan(String.valueOf(totalColumnSpan));
							}else{
								tempComplex.setRowSpan(String.valueOf(Integer.valueOf(totalLayer)-Integer.valueOf(tempComplex.getCurrentLayer())));
								tempComplex.setCloumnSpan("1");
							}
							tempComplex.setIndexId("");
							complexDimensions.add(tempComplex);
		
						}
						
					}else if("dictType".equals(tawStatisticParam.getParamCodeType())){
						dictTypes= iTawSystemDictTypeService.queryDictTypesByParentId(parentCodeId);
						for(int i=0;i<dictTypes.size();i++){
							temChildList=new ArrayList<ComplexDimension>();
							TawSystemDictType temp=dictTypes.get(i);
							tempComplex=new ComplexDimension();
							tempComplex.setName(temp.getDictname());
							
							tempComplex.setCondition(condition+"and "+tawStatisticParam.getParamEng()+"='"+temp.getDictid()+"'");
							
							tempComplex.setColumnName(tawStatisticParam.getParamEng());
							tempComplex.setCurrentLayer(String.valueOf(tawStatisticModelItem.getItemCode().length()/2));
							tempComplex.setTotalLayer(totalLayer);
							//子节点元素
							for(int m=0;m<childrenModelItems.size();m++){
								List<ComplexDimension> tempCM=getRetComplexDimensions(indexMap,childrenModelItems.get(m),condition+"and "+tawStatisticParam.getParamEng()+"='"+temp.getDictid()+"'",totalLayer,tawStatisticModelItem.getItemId(),temp.getDictid());
								temChildList.addAll(tempCM);						
							}
							tempComplex.setChildDimension(temChildList);
							
							
							
							if(temChildList.size()>0){
								tempComplex.setRowSpan("1");
								int totalColumnSpan=0;
								for(int j=0;j<temChildList.size();j++){
									totalColumnSpan+=Integer.parseInt(temChildList.get(j).getCloumnSpan());
								}
								tempComplex.setCloumnSpan(String.valueOf(totalColumnSpan));
							}else{
								tempComplex.setRowSpan(String.valueOf(Integer.valueOf(totalLayer)-Integer.valueOf(tempComplex.getCurrentLayer())));
								tempComplex.setCloumnSpan("1");
							}
							tempComplex.setIndexId("");
							complexDimensions.add(tempComplex);
							
						}
						
					}else if("noneType".equals(tawStatisticParam.getParamCodeType())){
						tempComplex=new ComplexDimension();
						tempComplex.setName(tawStatisticParam.getParamName());
						tempComplex.setCondition(condition+tawStatisticParam.getParamCondition());
						
						tempComplex.setColumnName(tawStatisticParam.getParamEng());
						tempComplex.setCurrentLayer(String.valueOf(tawStatisticModelItem.getItemCode().length()/2));
						tempComplex.setTotalLayer(totalLayer);
						//子节点元素
						for(int m=0;m<childrenModelItems.size();m++){
							List<ComplexDimension> tempCM=getRetComplexDimensions(indexMap,childrenModelItems.get(m),condition+tawStatisticParam.getParamCondition(),totalLayer,tawStatisticModelItem.getItemId(),parentCodeId);
							
							temChildList.addAll(tempCM);						
						}
						tempComplex.setChildDimension(temChildList);
						
						if(temChildList.size()>0){
							tempComplex.setRowSpan("1");
							int totalColumnSpan=0;
							for(int j=0;j<temChildList.size();j++){
								totalColumnSpan+=Integer.parseInt(temChildList.get(j).getCloumnSpan());
							}
							
							tempComplex.setCloumnSpan(String.valueOf(totalColumnSpan));
						}else{
							tempComplex.setRowSpan(String.valueOf(Integer.valueOf(totalLayer)-Integer.valueOf(tempComplex.getCurrentLayer())));
							tempComplex.setCloumnSpan("1");
						}
						tempComplex.setIndexId("");
						complexDimensions.add(tempComplex);
						
					}
				}else{
					//判断是否为类别二：不同的子类别
					//获取当前item下的所有子item
					//List<TawStatisticModelItem> childrenModelItems= iTawStatisticModelItemService.getModelItemByParentId(tawStatisticModelItem.getItemId());
					List<TawStatisticModelItem> childrenModelItems= iTawStatisticModelItemService.getModelItemByparentModelItemId(tawStatisticModelItem.getId());
					TawStatisticParam tawStatisticParam= iTawStatisticParamService.getParamById(tawStatisticModelItem.getItemId());
					
					List<TawSystemArea> areas=null;
					List<TawSystemDept> depts=null;
					List<TawSystemDictType> dictTypes=null;
					ComplexDimension tempComplex=null;
					List<ComplexDimension> temChildList=null;
					
					//获取维度下所有值
					if("areaType".equals(tawStatisticParam.getParamCodeType())){
						areas= iTawSystemAreaService.queryAreasByParentId(tawStatisticParam.getParentParamId());
						for(int i=0;i<areas.size();i++){
							temChildList=new ArrayList<ComplexDimension>();
							TawSystemArea temp=areas.get(i);
							tempComplex=new ComplexDimension();
							tempComplex.setName(temp.getAreaname());
							
							tempComplex.setCondition(condition+"and "+tawStatisticParam.getParamEng()+"='"+temp.getAreaid()+"'");
							
							tempComplex.setColumnName(tawStatisticParam.getParamEng());
							tempComplex.setCurrentLayer(String.valueOf(tawStatisticModelItem.getItemCode().length()/2));
							tempComplex.setTotalLayer(totalLayer);
							//子节点元素
							for(int m=0;m<childrenModelItems.size();m++){
								temChildList.addAll(getRetComplexDimensions(indexMap,childrenModelItems.get(m),condition+"and "+tawStatisticParam.getParamEng()+"='"+temp.getAreaid()+"'",totalLayer,tawStatisticModelItem.getItemId(),temp.getAreaid()));						
							}
							
							tempComplex.setChildDimension(temChildList);
							
							if(temChildList.size()>0){
								tempComplex.setRowSpan("1");
								int totalColumnSpan=0;
								for(int j=0;j<temChildList.size();j++){
									totalColumnSpan+=Integer.parseInt(temChildList.get(j).getCloumnSpan());
								}
								
								tempComplex.setCloumnSpan(String.valueOf(totalColumnSpan));
							}else{
								tempComplex.setRowSpan(String.valueOf(Integer.valueOf(totalLayer)-Integer.valueOf(tempComplex.getCurrentLayer())));
								tempComplex.setCloumnSpan("1");
							}
							tempComplex.setIndexId("");
							complexDimensions.add(tempComplex);
		
						}
						
					}else if("deptType".equals(tawStatisticParam.getParamCodeType())){
						depts=  iTawSystemDeptService.queryDeptByParentDeptID(tawStatisticParam.getParentParamId());
						for(int i=0;i<depts.size();i++){
							temChildList=new ArrayList<ComplexDimension>();
							TawSystemDept temp=depts.get(i);
							tempComplex=new ComplexDimension();
							tempComplex.setName(temp.getDeptname());
							
							tempComplex.setCondition(condition+"and "+tawStatisticParam.getParamEng()+"='"+temp.getDeptid()+"'");
							
							tempComplex.setColumnName(tawStatisticParam.getParamEng());
							tempComplex.setCurrentLayer(String.valueOf(tawStatisticModelItem.getItemCode().length()/2));
							tempComplex.setTotalLayer(totalLayer);
							//子节点元素
							for(int m=0;m<childrenModelItems.size();m++){
								temChildList.addAll(getRetComplexDimensions(indexMap,childrenModelItems.get(m),condition+"and "+tawStatisticParam.getParamEng()+"='"+temp.getDeptid()+"'",totalLayer,tawStatisticModelItem.getItemId(),temp.getDeptid()));						
							}
							
							tempComplex.setChildDimension(temChildList);
							
							
							if(temChildList.size()>0){
								tempComplex.setRowSpan("1");
								int totalColumnSpan=0;
								for(int j=0;j<temChildList.size();j++){
									totalColumnSpan+=Integer.parseInt(temChildList.get(j).getCloumnSpan());
								}
								
								tempComplex.setCloumnSpan(String.valueOf(totalColumnSpan));
							}else{
								tempComplex.setRowSpan(String.valueOf(Integer.valueOf(totalLayer)-Integer.valueOf(tempComplex.getCurrentLayer())));
								tempComplex.setCloumnSpan("1");
							}
							tempComplex.setIndexId("");
							complexDimensions.add(tempComplex);
		
						}
						
					}else if("dictType".equals(tawStatisticParam.getParamCodeType())){
						dictTypes= iTawSystemDictTypeService.queryDictTypesByParentId(tawStatisticParam.getParentParamId());
						for(int i=0;i<dictTypes.size();i++){
							temChildList=new ArrayList<ComplexDimension>();
							TawSystemDictType temp=dictTypes.get(i);
							tempComplex=new ComplexDimension();
							tempComplex.setName(temp.getDictname());
							
							tempComplex.setCondition(condition+"and "+tawStatisticParam.getParamEng()+"='"+temp.getDictid()+"'");
							
							tempComplex.setColumnName(tawStatisticParam.getParamEng());
							tempComplex.setCurrentLayer(String.valueOf(tawStatisticModelItem.getItemCode().length()/2));
							tempComplex.setTotalLayer(totalLayer);
							//子节点元素
							for(int m=0;m<childrenModelItems.size();m++){
								
								List<ComplexDimension> tempCM=getRetComplexDimensions(indexMap,childrenModelItems.get(m),condition+"and "+tawStatisticParam.getParamEng()+"='"+temp.getDictid()+"'",totalLayer,tawStatisticModelItem.getItemId(),temp.getDictid());
								temChildList.addAll(tempCM);						
							}
							
							tempComplex.setChildDimension(temChildList);
							
							
							
							if(temChildList.size()>0){
								tempComplex.setRowSpan("1");
								int totalColumnSpan=0;
								for(int j=0;j<temChildList.size();j++){
									totalColumnSpan+=Integer.parseInt(temChildList.get(j).getCloumnSpan());
								}
								
								tempComplex.setCloumnSpan(String.valueOf(totalColumnSpan));
							}else{
								tempComplex.setRowSpan(String.valueOf(Integer.valueOf(totalLayer)-Integer.valueOf(tempComplex.getCurrentLayer())));
								tempComplex.setCloumnSpan("1");
							}
							tempComplex.setIndexId("");
							complexDimensions.add(tempComplex);
							
						}
						
					}else if("noneType".equals(tawStatisticParam.getParamCodeType())){
						temChildList=new ArrayList<ComplexDimension>();
						tempComplex=new ComplexDimension();
						tempComplex.setName(tawStatisticParam.getParamName());
						tempComplex.setCondition(condition+tawStatisticParam.getParamCondition());
						
						tempComplex.setColumnName(tawStatisticParam.getParamEng());
						tempComplex.setCurrentLayer(String.valueOf(tawStatisticModelItem.getItemCode().length()/2));
						tempComplex.setTotalLayer(totalLayer);
						//子节点元素
						for(int m=0;m<childrenModelItems.size();m++){
							List<ComplexDimension> tempCM=getRetComplexDimensions(indexMap,childrenModelItems.get(m),condition+tawStatisticParam.getParamCondition(),totalLayer,tawStatisticModelItem.getItemId(),parentCodeId);
							temChildList.addAll(tempCM);						
						}
						tempComplex.setChildDimension(temChildList);
						
						if(temChildList.size()>0){
							tempComplex.setRowSpan("1");
							int totalColumnSpan=0;
							for(int j=0;j<temChildList.size();j++){
								totalColumnSpan+=Integer.parseInt(temChildList.get(j).getCloumnSpan());
							}
							
							tempComplex.setCloumnSpan(String.valueOf(totalColumnSpan));
						}else{
							tempComplex.setRowSpan(String.valueOf(Integer.valueOf(totalLayer)-Integer.valueOf(tempComplex.getCurrentLayer())));
							tempComplex.setCloumnSpan("1");
						}
						tempComplex.setIndexId("");
						complexDimensions.add(tempComplex);
						
					}
				}
			
			}
			return complexDimensions;
		}
	
	
	public static void main(String[] args) {
		Document document = DocumentHelper.createDocument();//<?xml version="1.0" encoding="UTF-8"?> 
	    Element recordInfos=document.addElement("recordInfos");
	    for(int j=0;j<5;j++){
	    	Element recordInfo=recordInfos.addElement("recordInfo");
		    for(int i=0;i<5;i++){
		    	if(i<3){
		    		Element fieldInfo=recordInfo.addElement("fieldInfo");
		    	    fieldInfo.addElement("fieldId").setText("1");
		    	    fieldInfo.addElement("fieldCode").setText("2");
		    	    fieldInfo.addElement("fieldCnName").setText("3");
		    	    fieldInfo.addElement("fieldValue").setText("4");
		    	    fieldInfo.addElement("fieldTurnValue").setText("5");
		    	}else{
		    		Element fieldInfo=recordInfo.addElement("fieldInfo");
		    	    fieldInfo.addElement("fieldId").setText("11");
		    	    fieldInfo.addElement("fieldCode").setText("22");
		    	    fieldInfo.addElement("fieldCnName").setText("33");
		    	    fieldInfo.addElement("fieldValue").setText("44");
		    	    fieldInfo.addElement("fieldTurnValue").setText("55");
		    	}
		    }
	    }
	    
	    
	    System.out.println(document.asXML());
	}

}
