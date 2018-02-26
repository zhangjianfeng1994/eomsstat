package com.boco.eoms.tawStatisticModel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.boco.eoms.baseUtil.BaseUtil;
import com.boco.eoms.tawStatisticIndex.model.TawStatisticIndex;
import com.boco.eoms.tawStatisticIndex.service.ITawStatisticIndexService;
import com.boco.eoms.tawStatisticModel.model.ComplexDimension;
import com.boco.eoms.tawStatisticModel.model.ComplexGrasp;
import com.boco.eoms.tawStatisticModel.model.TawStatisticModel;
import com.boco.eoms.tawStatisticModel.service.ITawStatisticModelService;
import com.boco.eoms.tawStatisticModel.service.ITawStatisticService;
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


@Service
public class TawStatisticService implements ITawStatisticService{
	
	private static final Logger logger = Logger.getLogger(TawStatisticService.class);
	//年粒度标识
	private static final String year_flag="year";
	//月粒度标识
	private static final String month_flag="month";
	//日粒度标识
	private static final String day_flag="day";
	
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
	
	public static Logger getLogger() {
		return logger;
	}



	public String showStatisticResult(String modelId_v,String time_v,String cycle_v,String where_bufer){
		
		logger.info("mobile invoke statistic begin");
		//获取数据
		Map<String,Object> retContent=getInitParam(modelId_v,time_v,cycle_v,where_bufer);
		//数据封装xml
		
		String tempXml=creatOpDetailXml((List<TawStatisticParam>)retContent.get("retDemension"),(List<TawStatisticIndex>)retContent.get("retIndex"),(String [][])retContent.get("retData"),(Map<String,Map<String,String>>)retContent.get("retTransferDimension"));
		
	    logger.info("mobile invoke statistic end");
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
	    	   if(j<retDemension.size()){
	    		 //维度新增
	    		   TawStatisticParam temp=retDemension.get(j);
	    		   Map<String,String> tempMap=transferMap.get(temp.getId());
	    		   Element fieldInfo=recordInfo.addElement("fieldInfo");
	    		   fieldInfo.addElement("fieldId").setText(BaseUtil.nullObject2String(temp.getId()));
	    		   fieldInfo.addElement("fieldCode").setText(BaseUtil.nullObject2String(temp.getParamEng()));
	    		   fieldInfo.addElement("fieldCnName").setText(BaseUtil.nullObject2String(temp.getParamName()));
	    		   fieldInfo.addElement("fieldValue").setText(BaseUtil.nullObject2String(retData[i][j]));
	    		   fieldInfo.addElement("fieldTurnValue").setText(BaseUtil.nullObject2String(tempMap.get(retData[i][j])));
	    	   }else{
	    		 //指标新增
	    		   TawStatisticIndex temp=retIndex.get(j-retDemension.size());
	    		   Element fieldInfo=recordInfo.addElement("fieldInfo");
	    		   fieldInfo.addElement("fieldId").setText(BaseUtil.nullObject2String(temp.getId()));
	    		   fieldInfo.addElement("fieldCode").setText("");
	    		   fieldInfo.addElement("fieldCnName").setText(BaseUtil.nullObject2String(temp.getIndicateName()));
	    		   fieldInfo.addElement("fieldValue").setText(BaseUtil.nullObject2String(retData[i][j]));
	    		   fieldInfo.addElement("fieldTurnValue").setText("1");
	    	   }
    	   }
       }
       
	   logger.info("generate xml:"+document.asXML());
		
	   return document.asXML();
	}
	
	//生成统计数据
	public Map<String,Object> getInitParam(String modelId_v,String time_v,String cycle_v,String where_bufer){
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
		}
		String itemIndexStr="";
		String itemDemensionStr="";
		
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
		
		Map<String,String> indexMap=new HashMap<String,String>();
		Map<String,String> paramMap=new HashMap<String,String>();
		
		List<TawStatisticParam> retDemension=new ArrayList<TawStatisticParam>();
		//获取所有统计维度对
		retDemension=getRetDemensionUtil(modelItems_demension,modelId_v);
		String dataType="";
		
		
		for(int i=0;i<retDemension.size();i++){
			paramMap.put("paramValue"+i, BaseUtil.null2String(retDemension.get(i).getParamEng()));
		}
		
		List<TawStatisticIndex> retIndex=new ArrayList<TawStatisticIndex>();
		List<ComplexDimension> complexs=new ArrayList<ComplexDimension>();
		
		
		TawStatisticModel tawStatisticModel=iTawStatisticModelService.getModelById(modelId_v);
		
		
		//获取父id为1的叶子节点
		for(int i=0;i<modelItems.size();i++){
			TawStatisticModelItem tawStatisticModelItem=modelItems.get(i);
			if("1".equals(tawStatisticModelItem.getParentItemId())&&"1".equals(tawStatisticModelItem.getIsLeaf())){
				if(tawStatisticModelItem.getItemId().contains("I")){
					//指标
					TawStatisticIndex tawStatisticIndex= iTawStatisticIndexService.getIndicateById(tawStatisticModelItem.getItemId());
					int tempNum=indexMap.size()/2;
					indexMap.put("indexValue"+tempNum, tawStatisticIndex.getIndicateValue());
					indexMap.put("condition"+tempNum, BaseUtil.null2String(tawStatisticIndex.getCondition()));
					retIndex.add(tawStatisticIndex);
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
			retData=getRetData(indexMap,paramMap,tawStatisticModel.getGroupType(),where_bufer);
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
	   retMap.put("retIndex", retIndex);
	   retMap.put("retDemension", retDemension);
	   retMap.put("retData", retData);
	   retMap.put("retTransferDimension", retTransferDimension);
	   retMap.put("retItemIndexStr", itemIndexStr);
	   retMap.put("retItemDemensionStr", itemDemensionStr);

	   return retMap;
	}
	
	
	public Map<String,String> transferDemension(String parentCode,String type,Map<String,String> inputMap){
		List<TawSystemArea> tempArea=null;
		List<TawSystemDictType> tempDictType=null;
		List<TawSystemDept> tempDeptType=null;
		
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
			   index[Integer.parseInt(BaseUtil.getNumber(entry.getKey()))]=indexMap.get("indexValue"+BaseUtil.getNumber(entry.getKey()));
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
	
	
	

}
