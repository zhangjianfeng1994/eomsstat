package com.boco.eoms.tawStatisticModel.controller;

import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.boco.eoms.baseUtil.BaseUtil;
import com.boco.eoms.baseUtil.ComputeUtil;
import com.boco.eoms.baseUtil.ExcelUtil;
import com.boco.eoms.baseUtil.PageModel;
import com.boco.eoms.baseUtil.TimeUtil;
import com.boco.eoms.tawStatisticDetail.model.TawStatisticDetail;
import com.boco.eoms.tawStatisticDetail.service.ITawStatisticDetailService;
import com.boco.eoms.tawStatisticDetailItem.model.TawStatisticDetailItem;
import com.boco.eoms.tawStatisticDetailItem.service.ITawStatisticDetailItemService;
import com.boco.eoms.tawStatisticIndex.model.TawStatisticIndex;
import com.boco.eoms.tawStatisticIndex.service.ITawStatisticIndexService;
import com.boco.eoms.tawStatisticModel.model.ColumnModel;
import com.boco.eoms.tawStatisticModel.model.ComplexDimension;
import com.boco.eoms.tawStatisticModel.model.ComplexGrasp;
import com.boco.eoms.tawStatisticModel.model.ComplexIndicate;
import com.boco.eoms.tawStatisticModel.model.TawStatisticModel;
import com.boco.eoms.tawStatisticModel.service.ITawStatisticModelService;
import com.boco.eoms.tawStatisticModel.util.CoreStatisticEngine;
import com.boco.eoms.tawStatisticModel.util.XmlStatisticGenerateUtil;
import com.boco.eoms.tawStatisticModelItem.model.TawStatisticModelItem;
import com.boco.eoms.tawStatisticModelItem.service.ITawStatisticModelItemService;
import com.boco.eoms.tawStatisticParam.model.ParamCondition;
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


@Controller
@RequestMapping(value="tawStatisticReport")
public class TawStatisticReportController {
	private static final Logger logger = Logger.getLogger(TawStatisticReportController.class);
	
	private static final String INDICATE_OPERATE="0";
	
	@Resource
	private ITawStatisticModelService iTawStatisticModelService;
	@Resource
	private ITawStatisticModelItemService iTawStatisticModelItemService;
	@Resource
	private ITawStatisticIndexService iTawStatisticIndexService;
	@Resource
	private ITawStatisticParamService iTawStatisticParamService;
	@Resource
	private ITawSystemAreaService iTawSystemAreaService;
	@Resource
	private ITawSystemDictTypeService iTawSystemDictTypeService;
	@Resource
	private ITawSystemDeptService iTawSystemDeptService;
	@Resource
	private ITawSystemUserService iTawSystemUserService;
	@Resource
	private ITawSystemSubRoleService iTawSystemSubRoleService;
	@Resource
	private ITawStatisticDetailItemService iTawStatisticDetailItemService;
	@Resource
	private ITawStatisticDetailService iTawStatisticDetailService;
	
	
	
	
	public static Logger getLogger() {
		return logger;
	}
	
	
	/** 
	 *说明：根据报表归属查询所有统计报表&数据筛选
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="getReport.json") 
    public ModelAndView getReport(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String model_id=BaseUtil.nullObject2String(request.getParameter("model_id"));
		String data_screen=BaseUtil.nullObject2String(request.getParameter("data_screen"));
		String beginTime=BaseUtil.nullObject2String(request.getParameter("beginTime"));
		String endTime=BaseUtil.nullObject2String(request.getParameter("endTime"));
		String demensionCondition=BaseUtil.nullObject2String(request.getParameter("demensionCondition"));
		demensionCondition = URLDecoder.decode(demensionCondition);
		String graspDemensionCondition=BaseUtil.nullObject2String(request.getParameter("graspDemensionCondition"));
		graspDemensionCondition = URLDecoder.decode(graspDemensionCondition);
		
//		String indicateId=BaseUtil.nullObject2String(request.getParameter("indicateId"));
//		String indicateValue="";
//		if(!"".equals(indicateId) && !(null==indicateId)){
//			TawStatisticIndex tawStatisticIndex= iTawStatisticIndexService.getIndicateById(indicateId);
//			 indicateValue = tawStatisticIndex.getIndicateValue();
//		}
		String direction=BaseUtil.nullObject2String(request.getParameter("direction"));
		String paramEngStr=BaseUtil.nullObject2String(request.getParameter("paramEng"));
		

		String itemIndexStr="";
		String itemDemensionStr="";
		
		//拼接where条件
		StringBuffer whereStr=new StringBuffer("");
		if(!"".equals(graspDemensionCondition)){
			whereStr.append(graspDemensionCondition);
		}
		if(!"".equals(beginTime)){
			beginTime=beginTime+" 00:00:00";
			whereStr.append(" and sendTime>=to_date('"+beginTime+"','yyyy-MM-dd hh24:mi:ss')");
		}
		if(!"".equals(endTime)){
			endTime=endTime+" 00:00:00";
			whereStr.append(" and sendTime<=to_date('"+endTime+"','yyyy-MM-dd hh24:mi:ss')");
		}
		
		
		List<TawStatisticModelItem> modelItems=null;
		List<TawStatisticModelItem> modelItems_demension=null;
		if(!"".equals(data_screen)){
			//数据筛选后的modelItem
			String[] modelItemStr=data_screen.split(",");
			modelItems=new ArrayList<TawStatisticModelItem>();
			modelItems_demension=new ArrayList<TawStatisticModelItem>();
			//指标不筛选，直接获取所有指标
//			modelItems=iTawStatisticModelItemService.getModelItemByModelIdAndType(model_id, "1");//获取指标
//			for(int i=0;i<modelItems.size();i++){
//				if(i==0){
//					itemIndexStr=modelItems.get(i).getId();
//				}else{
//					itemIndexStr=itemIndexStr+","+modelItems.get(i).getId();
//				}
//			} 
			
			//复合指标构造
			for(int i=0;i<modelItemStr.length;i++){
				TawStatisticModelItem tempItemChoose=iTawStatisticModelItemService.getModelItemById(modelItemStr[i]);
				if("1".equals(tempItemChoose.getType())){
					modelItems.add(tempItemChoose);
					if(itemIndexStr.length()==0){
						itemIndexStr=tempItemChoose.getId();
					}else{
						itemIndexStr=itemIndexStr+","+tempItemChoose.getId();
					}
				}else if("2".equals(tempItemChoose.getType())){
					if(itemDemensionStr.length()==0){
						itemDemensionStr=tempItemChoose.getId();
					}else{
						itemDemensionStr=itemDemensionStr+","+tempItemChoose.getId();
					}
					//维度
					modelItems_demension.add(tempItemChoose);
				}
			}
			
		}else{
			modelItems=iTawStatisticModelItemService.getModelItemByModelIdAndType(model_id, "1");//获取指标
			modelItems_demension=iTawStatisticModelItemService.getModelItemDemensionRootByModelIdAndType(model_id, "2");//获取维度
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
		}
		
		int treeDep=iTawStatisticModelItemService.getLengthOfModelTree(model_id);//获取深度
		
		Map<String,String> indexMap=new HashMap<String,String>();
		//Map<String,String> paramMap=new HashMap<String,String>();
		LinkedHashMap<String, String> paramMap = new LinkedHashMap<String, String>();
		
		List<TawStatisticParam> retDemension=new ArrayList<TawStatisticParam>();
		//获取所有统计维度对
		retDemension=getRetDemensionUtil(modelItems_demension,model_id);
		String dataType="";
		
		Map<String,String> transferMap=new HashMap<String,String>();
		for(int i=0;i<retDemension.size();i++){
			if("dept_system".equals(retDemension.get(i).getEomsParamType())){
				//以部门为维度方式转码
				String demesion_grasp_value="".equals(BaseUtil.null2String(request.getParameter("demesion_grasp_value")))?retDemension.get(i).getParentParamId():BaseUtil.null2String(request.getParameter("demesion_grasp_value"));
				List<TawSystemDept> tempDeptList= iTawSystemDeptService.queryDeptByParentDeptID(demesion_grasp_value);
				
				for(int j=0;j<tempDeptList.size();j++){
					TawSystemDept tawSystemDept=tempDeptList.get(j);
					paramMap.put("paramCode"+j, tawSystemDept.getDeptid());
					paramMap.put("paramColumn"+j, "deptid");
					if("".equals(iTawSystemDeptService.querySQLDeptByParentDeptID(tawSystemDept.getDeptid(),new StringBuffer()))){
						paramMap.put("paramCondition"+j, "and deptid in('"+tawSystemDept.getDeptid()+"')");	
					}else{
						paramMap.put("paramCondition"+j, "and deptid in("+iTawSystemDeptService.querySQLDeptByParentDeptID(tawSystemDept.getDeptid(),new StringBuffer())+")");
					}
				}
			
				dataType="system_demension";			
			}else if("none_system".equals(retDemension.get(i).getEomsParamType())){
				//如果为非系统转码，不作处理
				TawStatisticParam tawStatisticParam = retDemension.get(i);
				paramMap.put("paramValue"+i, BaseUtil.null2String(tawStatisticParam.getParamEng()));
//				if(demensionCondition!=null&&!"".equals(demensionCondition)){
//					whereStr.append(demensionCondition);
//					String[] allQueryCondition = demensionCondition.trim().split("and ");
//					for(int j=0;j<allQueryCondition.length;j++){
//						String queryCondition = allQueryCondition[j];
//						//System.out.println(queryCondition);
//						if(queryCondition!=null&&!"".equals(queryCondition)){
//							String[] paramEngs = queryCondition.trim().split("=");
//							String paramEng = paramEngs[0];
//							String paramEngValue = paramEngs[1];
//							if(paramEng.equals(tawStatisticParam.getParamEng())){
////								if("noneType".equals(tawStatisticParam.getParamCodeType())){
////									whereStr.append(" and "+queryCondition);
////								}else{
////									transferMap = transferDemensionOver(tawStatisticParam.getParentParamId(),tawStatisticParam.getParamCodeType(),transferMap);
////									String area = paramEngValue.substring(1,paramEngValue.length()-1);
////									if(null!=transferMap.get(area)&&!"".equals(transferMap.get(area))){
////										whereStr.append(" and "+paramEng+"='"+transferMap.get(area)+"'");
////									}else{
////										whereStr.append(" and "+queryCondition);
////									}
////								}
//								whereStr.append(" and "+queryCondition);
//							}
//						}
//					}
//				}
			}
		}
		if(demensionCondition!=null&&!"".equals(demensionCondition)){
			whereStr.append(demensionCondition);
		}
		
		List<TawStatisticIndex> retIndex=new ArrayList<TawStatisticIndex>();
		List<ComplexDimension> complexs=new ArrayList<ComplexDimension>();
		List<TawStatisticIndex> complexIndicate=new ArrayList<TawStatisticIndex>();
		Map<String,String> complexIndicateMap=new HashMap<String,String>();
		
		
		
		
		TawStatisticModel tawStatisticModel=iTawStatisticModelService.getModelById(model_id);
		
		
		//获取父id为1的叶子节点
		for(int i=0;i<modelItems.size();i++){
			TawStatisticModelItem tawStatisticModelItem=modelItems.get(i);
			if("1".equals(tawStatisticModelItem.getParentItemId())&&"1".equals(tawStatisticModelItem.getIsLeaf())){
				if(tawStatisticModelItem.getItemId().contains("I")){
					//指标
					TawStatisticIndex tawStatisticIndex= iTawStatisticIndexService.getIndicateById(tawStatisticModelItem.getItemId());
					String aString = tawStatisticIndex.getComplexFlag();
					int aString1 = indexMap.size();
					String aString2 = tawStatisticIndex.getIndicateValue();
					String aString3 = tawStatisticIndex.getCondition();
					if(INDICATE_OPERATE.equals(tawStatisticIndex.getComplexFlag())){
						int tempNum=indexMap.size()/2;
						indexMap.put("indexValue"+tempNum, tawStatisticIndex.getIndicateValue());
						indexMap.put("condition"+tempNum, BaseUtil.null2String(tawStatisticIndex.getCondition()));//{condition0=and status='1', indexValue0=count(*)}
						retIndex.add(tawStatisticIndex);//TawStatisticIndex@264ab70a
						complexIndicateMap.put(tawStatisticIndex.getId(),String.valueOf(tempNum));
					}else{
						
						//复合指标String fir_indicate_no,String end_indicate_no,String operate_indicate
						complexIndicate.add(iTawStatisticIndexService.getIndicateById(tawStatisticModelItem.getItemId()));
					}
					
					
				}else if(tawStatisticModelItem.getItemId().contains("D")){
					//维度
//					TawStatisticParam tawStatisticParam= iTawStatisticParamService.getParamById(tawStatisticModelItem.getItemId());
//					paramMap.put("paramValue"+paramMap.size(), BaseUtil.null2String(tawStatisticParam.getParamEng()));
//					//维度转码map保存
//					retDemension.add(tawStatisticParam);
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
				retData=getDemensionRetData(indexMap,paramMap,tawStatisticModel.getGroupType(),whereStr.toString());	
			}
			
		}else{
			retData=getRetData(indexMap,paramMap,tawStatisticModel.getGroupType(),whereStr.toString(),paramEngStr,direction);//{condition0=and status='1', indexValue0=count(*)},
			retData=getRetCompleIndicateData(retData,retIndex,complexIndicate,complexIndicateMap,String.valueOf(paramMap.size()+indexMap.size()/2),String.valueOf(paramMap.size()));
			
		}
		//{paramValue0=MAINFAULTGENERANTCITY},ccmf_statistic_table
		//and sendTime>=to_date('2016-1-17 00:00:00','yyyy-MM-dd hh24:mi:ss') and sendTime<=to_date('2016-1-18 00:00:00','yyyy-MM-dd hh24:mi:ss')
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
	
		
	   
		
	 //判断统计维度类型
	    retMap.put("dataType", dataType);
		retMap.put("retComplexGrsp", retComplexGrsp);
		retMap.put("retComplexTitle", complexs);
		retMap.put("treeDeep", treeDep);
		complexIndicate.addAll(0,retIndex);
		retMap.put("retIndex", complexIndicate);
		retMap.put("retDemension", retDemension);
		retMap.put("retTransferDimension", retTransferDimension);
		retMap.put("retItemIndexStr", itemIndexStr);
		retMap.put("retItemDemensionStr", itemDemensionStr);
		retMap.put("graspDemensionCondition", graspDemensionCondition);
		retMap.put("demensionCondition", demensionCondition);
		//retMap.put("modelItems", data_screen);
		retMap.put("modelItems", itemDemensionStr+","+itemIndexStr);
		   
		retMap.put("retData", retData);
		mav.addObject("retJson", retMap);
		
		return mav;
	}
	
	public String[][] getRetCompleIndicateData(String[][] retdata,List<TawStatisticIndex> retIndex,List<TawStatisticIndex> complexIndicate,Map<String,String> complexIndicateMap,String retColLen,String demenLen){
		
		String[][] tempData=new String[retdata.length][Integer.parseInt(retColLen)+complexIndicate.size()];
		
		
		for(int i=0;i<retdata.length;i++){
			for(int j=0;j<Integer.parseInt(retColLen)+complexIndicate.size();j++){
				if(j<retdata[i].length){
					if (j>(Integer.parseInt(demenLen)-1)) {
						TawStatisticIndex tawStatisticIndex=retIndex.get(j-Integer.parseInt(demenLen));
						String percentflag = tawStatisticIndex.getPercentSign();
						if("0".equals(percentflag)){
							tempData[i][j]=retdata[i][j];
						}else{
							 Double d=Double.parseDouble(retdata[i][j])*100;
							 DecimalFormat df=new DecimalFormat("##0.00");
							 tempData[i][j] = df.format(d)+"%";
						}
					} else {
						tempData[i][j]=retdata[i][j];
					}
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
	
	

	/** 
	 *说明：根据报表归属查询所有统计报表&数据筛选
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="getReportXml.json") 
    public ModelAndView getReportXml(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		
		
		String beginTime=BaseUtil.nullObject2String(request.getParameter("beginTime"));
		String endTime=BaseUtil.nullObject2String(request.getParameter("endTime"));
		String dimensionCondition=BaseUtil.nullObject2String(request.getParameter("demensionCondition")).replace("%2C", ",");
		String indexChooseStr=BaseUtil.nullObject2String(request.getParameter("indexChooseStr"));
		String model_id=BaseUtil.nullObject2String(request.getParameter("model_id"));
		String reptType=BaseUtil.nullObject2String(request.getParameter("type"));
		
		
		String[] dimensionConditions=null;
		if(dimensionCondition.length()==0){
			dimensionConditions=new String[0];
		}else{
			dimensionConditions=dimensionCondition.split(",");
		}
		TawStatisticModel tawStatisticModel=iTawStatisticModelService.getModelById(model_id);
		XmlStatisticGenerateUtil xmlStatisticGenerateUtil=new XmlStatisticGenerateUtil();
		
		//拼接where条件
		StringBuffer whereStr=new StringBuffer("");

		if(!"".equals(beginTime)){
			beginTime=beginTime+" 00:00:00";
			whereStr.append("and sendTime>=to_date('"+beginTime+"','yyyy-MM-dd hh24:mi:ss')");
		}
		if(!"".equals(endTime)){
			endTime=endTime+" 00:00:00";
			whereStr.append(" and sendTime<=to_date('"+endTime+"','yyyy-MM-dd hh24:mi:ss')");
		}
		
		
		Map<String,String> paramMap=new HashMap<String,String>();
		

		String statisticType=xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName()).element("statistic-demension-codeType").getText();
		String statisticDefaultValue=xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName()).element("statistic-demension-parentCode").getText();
		String demesion_grasp_value="".equals(BaseUtil.null2String(request.getParameter("demesion_grasp_value")))?statisticDefaultValue:BaseUtil.null2String(request.getParameter("demesion_grasp_value"));
		List<ParamCondition> tempChildDemension=new ArrayList<ParamCondition>();
		
		int dataColumnLen=0;
		
		if(statisticType.equals("deptType")){
			List<TawSystemDept> tempDeptList=null;
			if(reptType.equals("repType")){
				tempDeptList=new ArrayList<TawSystemDept>();
				for(int k=0;k<dimensionConditions.length;k++){
					TawSystemDept temp=iTawSystemDeptService.queryById(dimensionConditions[k]);
					tempDeptList.add(temp);
				}
			}else{
				tempDeptList=iTawSystemDeptService.queryDeptByParentDeptID(demesion_grasp_value);
			}	
			int count=0;
			for(int j=0;j<tempDeptList.size();j++){
					if(dimensionCondition.length()==0){
						dataColumnLen=tempDeptList.size();
						TawSystemDept tawSystemDept=tempDeptList.get(j);
						paramMap.put("paramCode"+j, tawSystemDept.getDeptid());
						paramMap.put("paramColumn"+j, statisticType);
						
						if("".equals(iTawSystemDeptService.querySQLDeptByParentDeptID(tawSystemDept.getDeptid(),new StringBuffer()))){
							paramMap.put("paramCondition"+j, "and deptid in('"+tawSystemDept.getDeptid()+"')");	
						}else{
							paramMap.put("paramCondition"+j, "and deptid in("+iTawSystemDeptService.querySQLDeptByParentDeptID(tawSystemDept.getDeptid(),new StringBuffer())+")");
						}
						//新增维度数据
						ParamCondition paramCondition=new ParamCondition();
						paramCondition.setParamText(tawSystemDept.getDeptname());
						paramCondition.setParamCode(tawSystemDept.getDeptid());
						tempChildDemension.add(paramCondition);
					}else{
						boolean flag=true;
						TawSystemDept tawSystemDept=tempDeptList.get(j);
						for(int m=0;m<dimensionConditions.length;m++){
							if(dimensionConditions[m].equals(tawSystemDept.getDeptid())){
								flag=flag&&false;	
							}
						}
						if(false==flag){
							paramMap.put("paramCode"+count, tawSystemDept.getDeptid());
							paramMap.put("paramColumn"+count, statisticType);
							
							if("".equals(iTawSystemDeptService.querySQLDeptByParentDeptID(tawSystemDept.getDeptid(),new StringBuffer()))){
								paramMap.put("paramCondition"+count, "and deptid in('"+tawSystemDept.getDeptid()+"')");	
							}else{
								paramMap.put("paramCondition"+count, "and deptid in("+iTawSystemDeptService.querySQLDeptByParentDeptID(tawSystemDept.getDeptid(),new StringBuffer())+")");
							}
							//新增维度数据
							ParamCondition paramCondition=new ParamCondition();
							paramCondition.setParamText(tawSystemDept.getDeptname());
							paramCondition.setParamCode(tawSystemDept.getDeptid());
							tempChildDemension.add(paramCondition);
							count++;
							dataColumnLen=count;
						}
						
					}
				
			}
			
		}else if(statisticType.equals("dictType")){
			List<TawSystemDictType> tempDictList=null;
			if(reptType.equals("repType")){
				tempDictList=new ArrayList<TawSystemDictType>();
				for(int k=0;k<dimensionConditions.length;k++){
					TawSystemDictType temp=iTawSystemDictTypeService.queryDictTypeById(dimensionConditions[k]);
					tempDictList.add(temp);
				}
			}else{
				tempDictList=iTawSystemDictTypeService.queryDictTypesByParentId(demesion_grasp_value);
			}
			
			
			
			int count=0;
			for(int j=0;j<tempDictList.size();j++){
				if(dimensionCondition.length()==0){
					dataColumnLen=tempDictList.size();
					TawSystemDictType tawSystemDictType=tempDictList.get(j);
					paramMap.put("paramCode"+j, tawSystemDictType.getDictid());
					paramMap.put("paramColumn"+j, "mainnetsortone");
					
					String tempString=iTawSystemDictTypeService.querySQLDeptByParentDeptID(tawSystemDictType.getDictid(),new StringBuffer());
					
					if("".equals(tempString)){
						paramMap.put("paramCondition"+j, "and mainnetsortone in('"+tawSystemDictType.getDictid()+"')");	
					}else{
						paramMap.put("paramCondition"+j, "and mainnetsortone in("+tempString+")");	
					}
					
					ParamCondition paramCondition=new ParamCondition();
					paramCondition.setParamText(tawSystemDictType.getDictname());
					paramCondition.setParamCode(tawSystemDictType.getDictid());
					tempChildDemension.add(paramCondition);
				}else{
					boolean flag=true;
					TawSystemDictType tawSystemDictType=tempDictList.get(j);
					for(int m=0;m<dimensionConditions.length;m++){
						if(dimensionConditions[m].equals(tawSystemDictType.getDictid())){
							flag=flag&&false;	
						}
					}
					if(false==flag){
						paramMap.put("paramCode"+count, tawSystemDictType.getDictid());
						paramMap.put("paramColumn"+count, "mainnetsortone");
						
						String tempString=iTawSystemDictTypeService.querySQLDeptByParentDeptID(tawSystemDictType.getDictid(),new StringBuffer());
						
						if("".equals(tempString)){
							paramMap.put("paramCondition"+count, "and mainnetsortone in('"+tawSystemDictType.getDictid()+"')");	
						}else{
							paramMap.put("paramCondition"+count, "and mainnetsortone in("+tempString+")");	
						}
						
						ParamCondition paramCondition=new ParamCondition();
						paramCondition.setParamText(tawSystemDictType.getDictname());
						paramCondition.setParamCode(tawSystemDictType.getDictid());
						tempChildDemension.add(paramCondition);
						count++;
						dataColumnLen=count;
					}
				}
				
			}
			
		}else if(statisticType.equals("tableType")){
			//获取维度
			String statisticDemsionSQL=xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName()).element("statistic-demension-table-sql").getText();
			String statisticColumn=xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName()).element("statistic-demension-column").getText();
			
			Map<String,String> tempDemensionMap=new CoreStatisticEngine().excuteSQLGetDemesion(statisticDemsionSQL.replaceAll("@begintime@",beginTime.split(" ")[0] ).replaceAll("@endtime@", endTime.split(" ")[0]));
			
			int j=0;
			int count=0;
			for (Map.Entry<String, String> entry : tempDemensionMap.entrySet()) {
				if(dimensionCondition.length()==0){
					dataColumnLen=tempDemensionMap.size();
					paramMap.put("paramCode"+j, entry.getValue());
					paramMap.put("paramColumn"+j, statisticColumn);
					paramMap.put("paramCondition"+j, "and dealsheetuserid='"+entry.getKey()+"'");	
					ParamCondition paramCondition=new ParamCondition();
					paramCondition.setParamText(entry.getValue());
					paramCondition.setParamCode(entry.getKey());
					tempChildDemension.add(paramCondition);
				}else{
					boolean flag=true;
					for(int m=0;m<dimensionConditions.length;m++){
						if(dimensionConditions[m].equals(entry.getValue())){
							flag=flag&&false;	
						}
					}
					if(false==flag){
						paramMap.put("paramCode"+count, entry.getValue());
						paramMap.put("paramColumn"+count, statisticColumn);
						paramMap.put("paramCondition"+count, "and dealsheetuserid='"+entry.getKey()+"'");	
						ParamCondition paramCondition=new ParamCondition();
						paramCondition.setParamText(entry.getValue());
						paramCondition.setParamCode(entry.getKey());
						tempChildDemension.add(paramCondition);
						count++;
						dataColumnLen=count;
					}
				}
				j++;
			}
			
		}else if(statisticType.equals("queryType")){
			
			
		}
		
		//生成标题头  
		List<ComplexDimension> complexDemension=getXmlComplexDimesion(indexChooseStr,tawStatisticModel.getXmlName());
		List<ComplexDimension> complexIndex=getXmlComplexIndexs(indexChooseStr,tawStatisticModel.getXmlName());
		//生成数据
		List<ComplexDimension> complex=new ArrayList<ComplexDimension>();
		complex.addAll(complexDemension);
		complex.addAll(complexIndex);
		
		//合并的数据值
		List<Element> items=xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName()).element("statistic-demension-items").elements("item");
		
		String treeDeep=xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName()).element("statistic-demension-title").elementText("rowspan");
		
		
		//获取列维度的值
		int tempLen=0;
		if(indexChooseStr.length()==0){
			tempLen=Integer.parseInt(xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName()).element("statistic-demension-title").elementText("totalcolumn"));
		}else{
			for(int i=0;i<items.size();i++){
				Element item=items.get(i);
				if(Arrays.asList(indexChooseStr.split(",")).contains(item.elementText("item-no"))){
					tempLen=tempLen+ Integer.parseInt(item.elementText("colspan"));
				}
			}
			tempLen=tempLen+1;
		}	
		
		
		String[][] temp=new String[dataColumnLen][tempLen];
		
		
		Map<String,Object> tempMap= getXmlCombineData(items,paramMap,whereStr.toString(),temp,indexChooseStr);
		String [][] retData=(String[][])tempMap.get("retData");
		List<TawStatisticIndex> retIndex=(List<TawStatisticIndex>)tempMap.get("retIndex");
		List<TawStatisticIndex> retRootIndex=(List<TawStatisticIndex>)tempMap.get("retFirstIndex");
		 //列表维度转化树
		 Map<String,String> retTransferDimension=new HashMap<String,String>();
		 Map<String,String> inputMap=new HashMap<String,String>();
		
		 String paramColumn=xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName()).elementText("statistic-demension-column");
		 String paramCodeType=xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName()).elementText("statistic-demension-codeType");
		 String parentCode=xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName()).elementText("statistic-demension-parentCode"); 
		 
		retTransferDimension=transferDemension(parentCode,paramCodeType,inputMap);
		
		//维度
		List<TawStatisticParam> retDemension=new ArrayList<TawStatisticParam>();
		TawStatisticParam tawStatisticParam=new TawStatisticParam();
		
		tawStatisticParam.setParamName(xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName()).element("statistic-demension-title").elementText("text"));
		tawStatisticParam.setParamEng(xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName()).elementText("statistic-demension-column"));
		tawStatisticParam.setParentParamId(xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName()).elementText("statistic-demension-codeType"));
		tawStatisticParam.setChildrenList(tempChildDemension);
		
		retDemension.add(tawStatisticParam);
		//维度
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		
		
		
		retMap.put("retIndex", retIndex);
		retMap.put("retRootIndex", retRootIndex);
		retMap.put("retDemension", retDemension);
		retMap.put("retTransferDimension", retTransferDimension);
		retMap.put("retComplexTitleDimension", complexDemension);
		retMap.put("retComplexTitleIndexs", complexIndex);
		retMap.put("retComplexTitle", complex);
		//   retComplexTitleDimension  retComplexTitleIndexs
		retMap.put("retData", retData);
		retMap.put("treeDeep", treeDeep);
		mav.addObject("retJson", retMap);
		
		return mav;
		
	}
	
	@RequestMapping(value="getModelItemByModelId.json") 
	public ModelAndView getModelItemByModelId(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		String modelId = BaseUtil.nullObject2String(request.getParameter("modelId"));
		List<TawStatisticModelItem> modelItems =  iTawStatisticModelItemService.getModelItemByModelId(modelId);
		List<TawStatisticModelItem> modelItemList = new ArrayList<TawStatisticModelItem>();
		for (int i = 0; i < modelItems.size(); i++) {
			TawStatisticModelItem modelItem = modelItems.get(i);
			if("1".equals(modelItem.getParentModelItemId())){
				modelItemList.add(modelItem);
			}
		}
		StringBuffer res = new StringBuffer("[{id:\"model_tree_root\",text: \"统计模板树\",value:\"1\",nodes:[");	
		for (int i = 0; i < modelItemList.size(); i++) {
			TawStatisticModelItem modelItem = modelItemList.get(i);
			getParentModelItem(modelItem, res);
			}
		res.append("]}]");
		System.out.println("----->"+res);
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("res", res.toString());
		mav.addObject("retJson", retMap);
		return mav;
	}

	public void getParentModelItem(TawStatisticModelItem modelItem,StringBuffer res) {
		String id = modelItem.getId();
		String itemCode = modelItem.getItemCode();
		String itemId = modelItem.getItemId();
		String isLeaf = modelItem.getIsLeaf();
		String parentItemId = modelItem.getParentItemId();
		String text = "";
		String type = "";
		if(itemId.startsWith("I")){
			text = iTawStatisticIndexService.getIndicateById(itemId).getIndicateName();
			type="1";
		}else{
			text = iTawStatisticParamService.getParamById(itemId).getParamName();
			type="2";
		}
		if("1".equals(isLeaf)){
			int len = res.length();
			if(res.charAt(len-1)=='['){
				res.append("{ id:\""+itemCode+"\",text:\""+text+"\",value:\""+itemId+"\" }");
			}else{
				res.append(",{ id:\""+itemCode+"\",text:\""+text+"\",value:\""+itemId+"\" }");
			}
		if(!"1".equals(parentItemId)){
			res.append("]}");
		}
			
		}else{
			int len = res.length();
			if(res.charAt(len-1)=='['){
				res.append("{ id:\""+itemCode+"\",text:\""+text+"\",value:\""+itemId+"\",nodes:[");
			}else{
				res.append(",{ id:\""+itemCode+"\",text:\""+text+"\",value:\""+itemId+"\",nodes:[");
			}
			
			TawStatisticModelItem modItem = iTawStatisticModelItemService.getModelItemByparentModelItemIdAndType(id,type);
			getParentModelItem(modItem,res);
		}
		
	}
	
	
	public Map<String,Object> getXmlCombineData(List<Element> items,Map<String,String> paramMap,String whereString,String[][] retData,String itemChoose){
		String[][] temp=null;
		int countColumn=0;
		List<TawStatisticIndex> retIndex=new ArrayList<TawStatisticIndex>();
		List<TawStatisticIndex> retFirstIndex=new ArrayList<TawStatisticIndex>();
		
		
		for(int i=0;i<items.size();i++){
			Map<String,String> indexMap=new HashMap<String,String>();
			if(i==0){
				Element item=items.get(i);
				List<Element> indexs=item.elements("index");
				String tableBelong=item.elementText("table-belong");
				TawStatisticIndex firstIndex=new TawStatisticIndex();
				firstIndex.setIndicateName(item.elementText("title"));
				firstIndex.setIndicateValue(item.elementText("item-no"));
				if(Arrays.asList(itemChoose.split(",")).contains(item.elementText("item-no"))||itemChoose.equals("")){
					firstIndex.setIsChecked("1");
					countColumn=countColumn+Integer.parseInt(item.elementText("colspan"));
				}else{
					firstIndex.setIsChecked("0");
				}
				retFirstIndex.add(firstIndex);
				
				for(int j=0;j<indexs.size();j++){
					indexMap.put("indexValue"+j, indexs.get(j).elementText("index-statistic"));
					indexMap.put("condition"+j, indexs.get(j).elementText("index-condition"));
					indexMap.put("indexNo"+j, indexs.get(j).elementText("index-no"));
					indexMap.put("indexRate"+j, indexs.get(j).elementText("index-rate"));
					
					TawStatisticIndex tawStatisticIndex=new TawStatisticIndex();
					tawStatisticIndex.setIndicateName(indexs.get(j).elementText("index-title"));
					tawStatisticIndex.setIndicateValue(indexs.get(j).elementText("index-statistic"));
					tawStatisticIndex.setCondition(indexs.get(j).elementText("index-condition"));
					tawStatisticIndex.setIsGrasp(indexs.get(j).elementText("index-grasp"));
					tawStatisticIndex.setDetailNum(indexs.get(j).elementText("index-grasp-num"));
					tawStatisticIndex.setParentIndicateName(item.elementText("title"));
					if(Arrays.asList(itemChoose.split(",")).contains(item.elementText("item-no"))||itemChoose.equals("")){
						retIndex.add(tawStatisticIndex);
					}
					
					
				}
				temp=getDemensionRetData(indexMap,paramMap,tableBelong,whereString);
				for(int m=0;m<temp.length;m++){
					for(int n=0;n<temp[m].length;n++){
						retData[m][n]=temp[m][n];
					}
					
				}
			}else{
				Element item=items.get(i);
				List<Element> indexs=item.elements("index");
				String tableBelong=item.elementText("table-belong");
				TawStatisticIndex firstIndex=new TawStatisticIndex();
				firstIndex.setIndicateName(item.elementText("title"));
				firstIndex.setIndicateValue(item.elementText("item-no"));
				for(int j=0;j<indexs.size();j++){
					indexMap.put("indexValue"+j, indexs.get(j).elementText("index-statistic"));
					indexMap.put("condition"+j, indexs.get(j).elementText("index-condition"));
					indexMap.put("indexNo"+j, indexs.get(j).elementText("index-no"));
					indexMap.put("indexRate"+j, indexs.get(j).elementText("index-rate"));
					TawStatisticIndex tawStatisticIndex=new TawStatisticIndex();
					tawStatisticIndex.setIndicateName(indexs.get(j).elementText("index-title"));
					tawStatisticIndex.setIndicateValue(indexs.get(j).elementText("index-statistic"));
					tawStatisticIndex.setCondition(indexs.get(j).elementText("index-condition"));
					tawStatisticIndex.setIsGrasp(indexs.get(j).elementText("index-grasp"));
					tawStatisticIndex.setDetailNum(indexs.get(j).elementText("index-grasp-num"));
					tawStatisticIndex.setParentIndicateName(item.elementText("title"));
					if(Arrays.asList(itemChoose.split(",")).contains(item.elementText("item-no"))||itemChoose.equals("")){
						retIndex.add(tawStatisticIndex);
					}
				}
				temp=getDemensionRetData(indexMap,paramMap,tableBelong,whereString);
				
				
				
				
				if(Arrays.asList(itemChoose.split(",")).contains(item.elementText("item-no"))||itemChoose.equals("")){
					for(int m=0;m<temp.length;m++){
						for(int n=1;n<temp[m].length;n++){
							retData[m][n+countColumn]=temp[m][n];
						}
						
					}
					
					
					firstIndex.setIsChecked("1");
					countColumn=countColumn+Integer.parseInt(item.elementText("colspan"));
				}else{
					firstIndex.setIsChecked("0");
				}
				retFirstIndex.add(firstIndex);
			}
		}
		
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		
		retMap.put("retData", retData);
		retMap.put("retIndex", retIndex);
		retMap.put("retFirstIndex", retFirstIndex);
		
		return retMap;
	}
	
	
	public List<ComplexDimension> getXmlComplexDimesion(String itemChoose,String xmlName){
		XmlStatisticGenerateUtil xmlStatisticGenerateUtil=new XmlStatisticGenerateUtil();
		Element root= xmlStatisticGenerateUtil.getRoot(xmlName);
		//添加维度
		ComplexDimension tempDemension=new ComplexDimension();
		//维度
		String treeDeep=root.element("statistic-demension-title").element("rowspan").getText();		
		tempDemension.setTotalLayer(treeDeep);
		tempDemension.setCurrentLayer("1");
		tempDemension.setName(root.element("statistic-demension-title").elementText("text"));
		tempDemension.setRowSpan("2");
		tempDemension.setCloumnSpan("1");
		List<ComplexDimension> complexs=new ArrayList<ComplexDimension>();
		complexs.add(tempDemension);
		
		return complexs;
	}
	
	
	public List<ComplexDimension> getXmlComplexIndexs(String itemChoose,String xmlName){
		XmlStatisticGenerateUtil xmlStatisticGenerateUtil=new XmlStatisticGenerateUtil();
		Element root= xmlStatisticGenerateUtil.getRoot(xmlName);
		
		List<ComplexDimension> complexs=new ArrayList<ComplexDimension>();
		
		String treeDeep=root.element("statistic-demension-title").element("rowspan").getText();	
		//添加所有指标项
		List<Element> indexs=root.element("statistic-demension-items").elements("item");
		
		for(int i=0;i<indexs.size();i++){
			Element item=indexs.get(i);
			List<Element> indexItems=item.elements("index");
			ComplexDimension tempItem=new ComplexDimension();
			
			tempItem.setTotalLayer(treeDeep);
			tempItem.setCurrentLayer("1");
			tempItem.setName(item.element("title").getText());
			tempItem.setRowSpan("1");
			tempItem.setCloumnSpan(String.valueOf(indexItems.size()));
			
			for(int j=0;j<indexItems.size();j++){
				Element indexChild=indexItems.get(j);
				ComplexDimension tempIndexChild=new ComplexDimension();
				tempIndexChild.setTotalLayer(treeDeep);
				tempIndexChild.setCurrentLayer("2");
				tempIndexChild.setName(indexChild.element("index-title").getText());
				tempIndexChild.setRowSpan("1");
				tempIndexChild.setCloumnSpan("1");
				tempItem.getChildDimension().add(tempIndexChild);
				
			}
			
			if(Arrays.asList(itemChoose.split(",")).contains(item.elementText("item-no"))||itemChoose.equals("")){
				complexs.add(tempItem);
			}
			
		}
		return complexs;
	} 
	
	
	
	
	
	/** 
	 *说明：根据报表归属查询所有统计报表&数据筛选---环比
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="getReportMom.json") 
    public ModelAndView getReportMom(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String model_id=BaseUtil.nullObject2String(request.getParameter("model_id"));
		String data_screen=BaseUtil.nullObject2String(request.getParameter("data_screen"));
		String beginTime=BaseUtil.nullObject2String(request.getParameter("beginTime"));
		String endTime=BaseUtil.nullObject2String(request.getParameter("endTime"));
		String report_table_div=BaseUtil.nullObject2String(request.getParameter("report_table_div"));
		String indicateId=BaseUtil.nullObject2String(request.getParameter("paramEng"));
		String direction=BaseUtil.nullObject2String(request.getParameter("direction"));
		
		String itemIndexStr="";
		String itemDemensionStr="";
		String last_beginTime="";
		String last_endTime="";
		//拼接where条件
		StringBuffer whereStr=new StringBuffer("");
		StringBuffer whereStrLast=new StringBuffer("");
		if(!"".equals(beginTime)){
			beginTime=beginTime+" 00:00:00";
			if("dayMomList".equals(report_table_div)){
				last_beginTime=TimeUtil.getLastDay(beginTime);
			}else if("monthMomList".equals(report_table_div)){
				last_beginTime=TimeUtil.getLastMonth(beginTime);	
			}else{
				
			}
			whereStr.append(" and sendTime>=to_date('"+beginTime+"','yyyy-MM-dd hh24:mi:ss')");
			whereStrLast.append(" and sendTime>=to_date('"+last_beginTime+"','yyyy-MM-dd hh24:mi:ss')");
		}
		if(!"".equals(endTime)){
			endTime=endTime+" 00:00:00";
			if("dayMomList".equals(report_table_div)){
				last_endTime=TimeUtil.getLastDay(endTime);
			}else if("monthMomList".equals(report_table_div)){
				last_endTime=TimeUtil.getLastMonth(endTime);	
			}else{
				
			}
			whereStr.append(" and sendTime<=to_date('"+endTime+"','yyyy-MM-dd hh24:mi:ss')");
			whereStrLast.append(" and sendTime<=to_date('"+last_endTime+"','yyyy-MM-dd hh24:mi:ss')");
		}
		
		List<TawStatisticModelItem> modelItems=null;
		List<TawStatisticModelItem> modelItems_demension=null;
		if(!"".equals(data_screen)){
			//数据筛选后的modelItem
			String[] modelItemStr=data_screen.split(",");
			modelItems=new ArrayList<TawStatisticModelItem>();
			modelItems_demension=new ArrayList<TawStatisticModelItem>();
			//复合指标构造
			for(int i=0;i<modelItemStr.length;i++){
				TawStatisticModelItem tempItemChoose=iTawStatisticModelItemService.getModelItemById(modelItemStr[i]);
				if("1".equals(tempItemChoose.getType())){
					modelItems.add(tempItemChoose);
					if(itemIndexStr.length()==0){
						itemIndexStr=tempItemChoose.getId();
					}else{
						itemIndexStr=itemIndexStr+","+modelItems.get(i).getId();
					}
				}else if("2".equals(tempItemChoose.getType())){
					if(itemDemensionStr.length()==0){
						itemDemensionStr=tempItemChoose.getId();
					}else{
						itemDemensionStr=itemDemensionStr+","+tempItemChoose.getId();
					}
					//维度
					modelItems_demension.add(tempItemChoose);
				}
			}
			
		}else{
			modelItems=iTawStatisticModelItemService.getModelItemByModelIdAndType(model_id, "1");
			modelItems_demension=iTawStatisticModelItemService.getModelItemDemensionRootByModelIdAndType(model_id, "2");
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
		}
		
		int treeDep=iTawStatisticModelItemService.getLengthOfModelTree(model_id);
		
		Map<String,String> indexMap=new HashMap<String,String>();
		//Map<String,String> paramMap=new HashMap<String,String>();
		LinkedHashMap<String, String> paramMap = new LinkedHashMap<String, String>();
		
		List<TawStatisticParam> retDemension=new ArrayList<TawStatisticParam>();
		//获取所有统计维度对
		retDemension=getRetDemensionUtil(modelItems_demension,model_id);
		for(int i=0;i<retDemension.size();i++){
			if("dept_system".equals(retDemension.get(i).getEomsParamType())){
				//如果为非系统转码，添加查询条件时间内
				
				whereStr.append(" and "+retDemension.get(i).getParamEng()+" in ("+iTawSystemDeptService.querySQLDeptByParentDeptID("".equals(BaseUtil.nullObject2String(request.getParameter("parentParamCode")))?retDemension.get(i).getParamCode():BaseUtil.nullObject2String(request.getParameter("parentParamCode")),new StringBuffer())+")");
				whereStrLast.append(" and "+retDemension.get(i).getParamEng()+" in ("+iTawSystemDeptService.querySQLDeptByParentDeptID("".equals(BaseUtil.nullObject2String(request.getParameter("parentParamCode")))?retDemension.get(i).getParamCode():BaseUtil.nullObject2String(request.getParameter("parentParamCode")),new StringBuffer())+")");
			}else if("none_system".equals(retDemension.get(i).getEomsParamType())){
				//如果为非系统转码，不作处理
			}
			paramMap.put("paramValue"+i, BaseUtil.null2String(retDemension.get(i).getParamEng()));
		}
		
		
		
		List<TawStatisticIndex> retIndex=new ArrayList<TawStatisticIndex>();
		List<ComplexDimension> complexs=new ArrayList<ComplexDimension>();
		List<TawStatisticIndex> complexIndicate=new ArrayList<TawStatisticIndex>();
		Map<String,String> complexIndicateMap=new HashMap<String,String>();
		
		
		TawStatisticModel tawStatisticModel=iTawStatisticModelService.getModelById(model_id);
		
		
		//获取父id为1的叶子节点
		for(int i=0;i<modelItems.size();i++){
			TawStatisticModelItem tawStatisticModelItem=modelItems.get(i);
			if("1".equals(tawStatisticModelItem.getParentItemId())&&"1".equals(tawStatisticModelItem.getIsLeaf())){
				if(tawStatisticModelItem.getItemId().contains("I")){
					//指标
					TawStatisticIndex tawStatisticIndex= iTawStatisticIndexService.getIndicateById(tawStatisticModelItem.getItemId());
					
					if(INDICATE_OPERATE.equals(tawStatisticIndex.getComplexFlag())){
						int tempNum=indexMap.size()/2;
						indexMap.put("indexValue"+tempNum, tawStatisticIndex.getIndicateValue());
						indexMap.put("condition"+tempNum, BaseUtil.null2String(tawStatisticIndex.getCondition()));//{condition0=and status='1', indexValue0=count(*)}
						retIndex.add(tawStatisticIndex);//TawStatisticIndex@264ab70a
						complexIndicateMap.put(tawStatisticIndex.getId(),String.valueOf(tempNum));
					}else{
						
						//复合指标String fir_indicate_no,String end_indicate_no,String operate_indicate
						complexIndicate.add(iTawStatisticIndexService.getIndicateById(tawStatisticModelItem.getItemId()));
					}
					
					
				}else if(tawStatisticModelItem.getItemId().contains("D")){
					//维度
//					TawStatisticParam tawStatisticParam= iTawStatisticParamService.getParamById(tawStatisticModelItem.getItemId());
//					paramMap.put("paramValue"+paramMap.size(), BaseUtil.null2String(tawStatisticParam.getParamEng()));
//					//维度转码map保存
//					retDemension.add(tawStatisticParam);
				}
			}else if("1".equals(tawStatisticModelItem.getParentItemId())&&"0".equals(tawStatisticModelItem.getIsLeaf())){
			    //带指标维度
				//叶子<--父节点【父类id-对应中间表】<--父节点【父类id-对应中间表】
				//生成复合表头，和指标值添加
				TawStatisticParam tawStatisticParam= iTawStatisticParamService.getParamById(tawStatisticModelItem.getItemId());
				complexs= getRetComplexDimensions(indexMap,tawStatisticModelItem,"",String.valueOf(treeDep),"1",tawStatisticParam.getParentParamId());
			}
		}
		
		String [][] retData=getRetData(indexMap,paramMap,tawStatisticModel.getGroupType(),whereStr.toString(),indicateId,direction);
		String [][] retDataLast=getRetData(indexMap,paramMap,tawStatisticModel.getGroupType(),whereStrLast.toString(),indicateId,direction);
		retData=getRetCompleIndicateData(retData,retIndex,complexIndicate,complexIndicateMap,String.valueOf(paramMap.size()+indexMap.size()/2),String.valueOf(paramMap.size()));
		retDataLast=getRetCompleIndicateData(retDataLast,retIndex,complexIndicate,complexIndicateMap,String.valueOf(paramMap.size()+indexMap.size()/2),String.valueOf(paramMap.size()));
		String [][] retDataNew=new String [retData.length][retData[0].length];
		String [][] retDataLastNew=new String [retData.length][retData[0].length];
		for(int i=0;i<retData.length;i++){
			System.arraycopy(retData[i], 0, retDataNew[i], 0, retData[i].length);
			System.arraycopy(retData[i], 0, retDataLastNew[i], 0, retData[i].length);
		}
		java.text.DecimalFormat df=new java.text.DecimalFormat("#.##");  
		int tempRowNum = 0;
		for(int i=0;i<retData.length;i++){
			//行数据匹配
			boolean dataFlag=false;
			for(int j=0;j<retDataLast.length;j++){
				boolean flag=false;
				for(int condifla=0;condifla<paramMap.size();condifla++){
					String temp="";
					try{
						temp=retDataLast[j][condifla];
					}catch(Exception e){
						temp="-1";
					}
					if(!retData[i][condifla].equals(temp)){
						flag=false;
						break;
					}
					flag=true;
				}
				if(flag){
					dataFlag=true;
					tempRowNum = j;
				}
			}
			
			if(dataFlag){
				for(int j=0;j<retData[i].length;j++){
					if(j>=paramMap.size()){
						retDataLastNew[i][j] = retDataLast[tempRowNum][j];
					}		
				}
			}else{
				for(int j=0;j<retData[i].length;j++){
					if(j>=paramMap.size()){
						retDataLastNew[i][j] = "0";
					}		
				}
			}
			
			for(int j=0;j<retData[i].length;j++){
				if(j>=paramMap.size()){
					String tempEnd=retData[i][j]; 
					if (tempEnd.contains("%")) {
						tempEnd = tempEnd.replace("%", "");
					}
					String tempFir="";
					try{
						tempFir=retDataLastNew[i][j];
						if (tempFir.contains("%")) {
							tempFir = tempFir.replace("%", "");
						}
					}catch(Exception e){
						tempFir="0";
					}
					if("0".equals(tempFir) || "0.00".equals(tempFir) || "0.0".equals(tempFir)){
						retDataNew[i][j]="~";
					}else{
						//retDataNew[i][j]=String.valueOf(df.format(Double.valueOf(tempEnd).doubleValue()/Double.valueOf(tempFir).doubleValue()));
						retDataNew[i][j]=String.valueOf(df.format((Double.valueOf(tempEnd).doubleValue()/Double.valueOf(tempFir).doubleValue()) * 100))+"%";
					}
				}		
			}
			
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
		
		
		retMap.put("retComplexGrsp", retComplexGrsp);
		retMap.put("retComplexTitle", complexs);
		retMap.put("treeDeep", treeDep);
		complexIndicate.addAll(0,retIndex);
		retMap.put("retIndex", complexIndicate);
		retMap.put("retDemension", retDemension);
		retMap.put("retTransferDimension", retTransferDimension);
		retMap.put("retItemIndexStr", itemIndexStr);
		retMap.put("retItemDemensionStr", itemDemensionStr);
		retMap.put("retData", retDataNew);
		mav.addObject("retJson", retMap);
		
		return mav;
	}
	
	
	

	/** 
	 *说明：根据报表归属查询所有统计报表&数据筛选---环比增长率
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="getReportMomRate.json") 
    public ModelAndView getReportMomRate(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String model_id=BaseUtil.nullObject2String(request.getParameter("model_id"));
		String data_screen=BaseUtil.nullObject2String(request.getParameter("data_screen"));
		String beginTime=BaseUtil.nullObject2String(request.getParameter("beginTime"));
		String endTime=BaseUtil.nullObject2String(request.getParameter("endTime"));
		String report_table_div=BaseUtil.nullObject2String(request.getParameter("report_table_div"));
		String indicateId=BaseUtil.nullObject2String(request.getParameter("paramEng"));
		String direction=BaseUtil.nullObject2String(request.getParameter("direction"));
		String itemIndexStr="";
		String itemDemensionStr="";
		String last_beginTime="";
		String last_endTime="";
		//拼接where条件
		StringBuffer whereStr=new StringBuffer("");
		StringBuffer whereStrLast=new StringBuffer("");
		if(!"".equals(beginTime)){
			beginTime=beginTime+" 00:00:00";
			if("dayMomRateList".equals(report_table_div)){
				last_beginTime=TimeUtil.getLastDay(beginTime);
			}else if("monthMomRateList".equals(report_table_div)){
				last_beginTime=TimeUtil.getLastMonth(beginTime);	
			}else{
				
			}
			
			whereStr.append(" and sendTime>=to_date('"+beginTime+"','yyyy-MM-dd hh24:mi:ss')");
			whereStrLast.append(" and sendTime>=to_date('"+last_beginTime+"','yyyy-MM-dd hh24:mi:ss')");
		}
		if(!"".equals(endTime)){
			endTime=endTime+" 00:00:00";
			if("dayMomRateList".equals(report_table_div)){
				last_endTime=TimeUtil.getLastDay(endTime);
			}else if("monthMomRateList".equals(report_table_div)){
				last_endTime=TimeUtil.getLastMonth(endTime);	
			}else{
				
			}
			whereStr.append(" and sendTime<=to_date('"+endTime+"','yyyy-MM-dd hh24:mi:ss')");
			whereStrLast.append(" and sendTime<=to_date('"+last_endTime+"','yyyy-MM-dd hh24:mi:ss')");
		}
		
		List<TawStatisticModelItem> modelItems=null;
		List<TawStatisticModelItem> modelItems_demension=null;
		if(!"".equals(data_screen)){
			//数据筛选后的modelItem
			String[] modelItemStr=data_screen.split(",");
			modelItems=new ArrayList<TawStatisticModelItem>();
			modelItems_demension=new ArrayList<TawStatisticModelItem>();
			//复合指标构造
			for(int i=0;i<modelItemStr.length;i++){
				TawStatisticModelItem tempItemChoose=iTawStatisticModelItemService.getModelItemById(modelItemStr[i]);
				if("1".equals(tempItemChoose.getType())){
					modelItems.add(tempItemChoose);
					if(itemIndexStr.length()==0){
						itemIndexStr=tempItemChoose.getId();
					}else{
						itemIndexStr=itemIndexStr+","+modelItems.get(i).getId();
					}
				}else if("2".equals(tempItemChoose.getType())){
					if(itemDemensionStr.length()==0){
						itemDemensionStr=tempItemChoose.getId();
					}else{
						itemDemensionStr=itemDemensionStr+","+tempItemChoose.getId();
					}
					//维度
					modelItems_demension.add(tempItemChoose);
				}
			}
			
		}else{
			modelItems=iTawStatisticModelItemService.getModelItemByModelIdAndType(model_id, "1");
			modelItems_demension=iTawStatisticModelItemService.getModelItemDemensionRootByModelIdAndType(model_id, "2");
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
		}
		
		int treeDep=iTawStatisticModelItemService.getLengthOfModelTree(model_id);
		
		Map<String,String> indexMap=new HashMap<String,String>();
		//Map<String,String> paramMap=new HashMap<String,String>();
		LinkedHashMap<String, String> paramMap = new LinkedHashMap<String, String>();
		
		List<TawStatisticParam> retDemension=new ArrayList<TawStatisticParam>();
		//获取所有统计维度对
		retDemension=getRetDemensionUtil(modelItems_demension,model_id);
		//String parentParamCode=;
		for(int i=0;i<retDemension.size();i++){
			if("dept_system".equals(retDemension.get(i).getEomsParamType())){
				//如果为非系统转码，添加查询条件时间内
				
				whereStr.append(" and "+retDemension.get(i).getParamEng()+" in ("+iTawSystemDeptService.querySQLDeptByParentDeptID("".equals(BaseUtil.nullObject2String(request.getParameter("parentParamCode")))?retDemension.get(i).getParamCode():BaseUtil.nullObject2String(request.getParameter("parentParamCode")),new StringBuffer())+")");
				whereStrLast.append(" and "+retDemension.get(i).getParamEng()+" in ("+iTawSystemDeptService.querySQLDeptByParentDeptID("".equals(BaseUtil.nullObject2String(request.getParameter("parentParamCode")))?retDemension.get(i).getParamCode():BaseUtil.nullObject2String(request.getParameter("parentParamCode")),new StringBuffer())+")");
			}else if("none_system".equals(retDemension.get(i).getEomsParamType())){
				//如果为非系统转码，不作处理
			}
			paramMap.put("paramValue"+i, BaseUtil.null2String(retDemension.get(i).getParamEng()));
		}
		
		List<TawStatisticIndex> retIndex=new ArrayList<TawStatisticIndex>();
		List<ComplexDimension> complexs=new ArrayList<ComplexDimension>();
		List<TawStatisticIndex> complexIndicate=new ArrayList<TawStatisticIndex>();
		Map<String,String> complexIndicateMap=new HashMap<String,String>();
		
		
		TawStatisticModel tawStatisticModel=iTawStatisticModelService.getModelById(model_id);
		
		
		//获取父id为1的叶子节点
		for(int i=0;i<modelItems.size();i++){
			TawStatisticModelItem tawStatisticModelItem=modelItems.get(i);
			if("1".equals(tawStatisticModelItem.getParentItemId())&&"1".equals(tawStatisticModelItem.getIsLeaf())){
				if(tawStatisticModelItem.getItemId().contains("I")){
					//指标
					TawStatisticIndex tawStatisticIndex= iTawStatisticIndexService.getIndicateById(tawStatisticModelItem.getItemId());
					
					if(INDICATE_OPERATE.equals(tawStatisticIndex.getComplexFlag())){
						int tempNum=indexMap.size()/2;
						indexMap.put("indexValue"+tempNum, tawStatisticIndex.getIndicateValue());
						indexMap.put("condition"+tempNum, BaseUtil.null2String(tawStatisticIndex.getCondition()));//{condition0=and status='1', indexValue0=count(*)}
						retIndex.add(tawStatisticIndex);//TawStatisticIndex@264ab70a
						complexIndicateMap.put(tawStatisticIndex.getId(),String.valueOf(tempNum));
					}else{
						
						//复合指标String fir_indicate_no,String end_indicate_no,String operate_indicate
						complexIndicate.add(iTawStatisticIndexService.getIndicateById(tawStatisticModelItem.getItemId()));
					}
					
					
				}else if(tawStatisticModelItem.getItemId().contains("D")){
					//维度
//							TawStatisticParam tawStatisticParam= iTawStatisticParamService.getParamById(tawStatisticModelItem.getItemId());
//							paramMap.put("paramValue"+paramMap.size(), BaseUtil.null2String(tawStatisticParam.getParamEng()));
//							//维度转码map保存
//							retDemension.add(tawStatisticParam);
				}
			}else if("1".equals(tawStatisticModelItem.getParentItemId())&&"0".equals(tawStatisticModelItem.getIsLeaf())){
			    //带指标维度
				//叶子<--父节点【父类id-对应中间表】<--父节点【父类id-对应中间表】
				//生成复合表头，和指标值添加
				TawStatisticParam tawStatisticParam= iTawStatisticParamService.getParamById(tawStatisticModelItem.getItemId());
				complexs= getRetComplexDimensions(indexMap,tawStatisticModelItem,"",String.valueOf(treeDep),"1",tawStatisticParam.getParentParamId());
			}
		}
		
		String [][] retData=getRetData(indexMap,paramMap,tawStatisticModel.getGroupType(),whereStr.toString(),indicateId,direction);
		String [][] retDataLast=getRetData(indexMap,paramMap,tawStatisticModel.getGroupType(),whereStrLast.toString(),indicateId,direction);
		retData=getRetCompleIndicateData(retData,retIndex,complexIndicate,complexIndicateMap,String.valueOf(paramMap.size()+indexMap.size()/2),String.valueOf(paramMap.size()));
		retDataLast=getRetCompleIndicateData(retDataLast,retIndex,complexIndicate,complexIndicateMap,String.valueOf(paramMap.size()+indexMap.size()/2),String.valueOf(paramMap.size()));
		String [][] retDataNew=new String [retData.length][retData[0].length];
		String [][] retDataLastNew=new String [retData.length][retData[0].length];
		for(int i=0;i<retData.length;i++){
			System.arraycopy(retData[i], 0, retDataNew[i], 0, retData[i].length);
			System.arraycopy(retData[i], 0, retDataLastNew[i], 0, retData[i].length);
		}
		java.text.DecimalFormat df=new java.text.DecimalFormat("#.##");  
		int tempRowNum = 0;
		for(int i=0;i<retData.length;i++){
			//行数据匹配
			boolean dataFlag=false;
			for(int j=0;j<retDataLast.length;j++){
				boolean flag=false;
				for(int condifla=0;condifla<paramMap.size();condifla++){
					String temp="";
					try{
						temp=retDataLast[j][condifla];
					}catch(Exception e){
						temp="-1";
					}
					if(!retData[i][condifla].equals(temp)){
						flag=false;
						break;
					}
					flag=true;
				}
				if(flag){
					dataFlag=true;
					tempRowNum = j;
				}
			}
			
			if(dataFlag){
				for(int j=0;j<retData[i].length;j++){
					if(j>=paramMap.size()){
						retDataLastNew[i][j] = retDataLast[tempRowNum][j];
					}		
				}
			}else{
				for(int j=0;j<retData[i].length;j++){
					if(j>=paramMap.size()){
						retDataLastNew[i][j] = "0";
					}		
				}
			}
			
			for(int j=0;j<retData[i].length;j++){
				if(j>=paramMap.size()){
					String tempEnd=retData[i][j]; 
					if (tempEnd.contains("%")) {
						tempEnd = tempEnd.replace("%", "");
					}
					String tempFir="";
					try{
						tempFir=retDataLastNew[i][j];
						if (tempFir.contains("%")) {
							tempFir = tempFir.replace("%", "");
						}
					}catch(Exception e){
						tempFir="0";
					}
					if("0".equals(tempFir) || "0.00".equals(tempFir) || "0.0".equals(tempFir)){
						retDataNew[i][j]="~";
					}else{
						//retDataNew[i][j]=String.valueOf(df.format((Double.valueOf(tempEnd).doubleValue()-Double.valueOf(tempFir).doubleValue())/(Double.valueOf(tempFir).doubleValue())));
						retDataNew[i][j]=String.valueOf(df.format(((Double.valueOf(tempEnd).doubleValue()-Double.valueOf(tempFir).doubleValue())/(Double.valueOf(tempFir).doubleValue()) * 100)))+"%";
					}
				}		
			}
			
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
	
		
		
		retMap.put("retComplexGrsp", retComplexGrsp);
		retMap.put("retComplexTitle", complexs);
		retMap.put("treeDeep", treeDep);
		complexIndicate.addAll(0,retIndex);
		retMap.put("retIndex", complexIndicate);
		retMap.put("retDemension", retDemension);
		retMap.put("retTransferDimension", retTransferDimension);
		retMap.put("retItemIndexStr", itemIndexStr);
		retMap.put("retItemDemensionStr", itemDemensionStr);
		retMap.put("retData", retDataNew);
		mav.addObject("retJson", retMap);
		
		return mav;
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
	
	
	//维度转码
	public Map<String,String> transferDemension(String parentCode,String type,Map<String,String> inputMap){
		List<TawSystemArea> tempArea=null;
		List<TawSystemDictType> tempDictType=null;
		List<TawSystemDept> tempDeptType=null;
		List<TawSystemUser> tempUserType=null;
		List<TawSystemSubRole> tempSubRoleType=null;
		
		if("areaType".equals(type)){
//			tempArea=iTawSystemAreaService.queryAreasByParentId(parentCode);
//			for(int i=0;i<tempArea.size();i++){
//				TawSystemArea tawSystemArea=tempArea.get(i);
//				if(null!=tawSystemArea.getAreaid()){
//					inputMap.put(tawSystemArea.getAreaid(), tawSystemArea.getAreaname());
//				}
//				if(iTawSystemAreaService.queryAreasByParentId(tawSystemArea.getAreaid()).size()>0){
//					transferDemension(tawSystemArea.getAreaid(),"areaType",inputMap);	
//				}
//			}
			tempArea=iTawSystemAreaService.listAllAreaByParentAreaID(parentCode);
			for(int i=0;i<tempArea.size();i++){
				TawSystemArea tawSystemArea=tempArea.get(i);
				if(null!=tawSystemArea.getAreaid()){
					inputMap.put(tawSystemArea.getAreaid(), tawSystemArea.getAreaname());
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
	
	//维度反转码
		public Map<String,String> transferDemensionOver(String parentCode,String type,Map<String,String> inputMap){
			List<TawSystemArea> tempArea=null;
			List<TawSystemDictType> tempDictType=null;
			List<TawSystemDept> tempDeptType=null;
			List<TawSystemUser> tempUserType=null;
			List<TawSystemSubRole> tempSubRoleType=null;
			
			if("areaType".equals(type)){
//				tempArea=iTawSystemAreaService.queryAreasByParentId(parentCode);
//				for(int i=0;i<tempArea.size();i++){
//					TawSystemArea tawSystemArea=tempArea.get(i);
//					if(null!=tawSystemArea.getAreaid()){
//						inputMap.put(tawSystemArea.getAreaid(), tawSystemArea.getAreaname());
//					}
//					if(iTawSystemAreaService.queryAreasByParentId(tawSystemArea.getAreaid()).size()>0){
//						transferDemension(tawSystemArea.getAreaid(),"areaType",inputMap);	
//					}
//				}
				tempArea=iTawSystemAreaService.listAllAreaByParentAreaID(parentCode);
				for(int i=0;i<tempArea.size();i++){
					TawSystemArea tawSystemArea=tempArea.get(i);
					if(null!=tawSystemArea.getAreaid()){
						inputMap.put(tawSystemArea.getAreaname(), tawSystemArea.getAreaid());
					}
				}
			}else if("dictType".equals(type)){
				tempDictType=iTawSystemDictTypeService.queryAllDictTypesByParentId(parentCode);
				for(int i=0;i<tempDictType.size();i++){
					TawSystemDictType tawSystemDictType=tempDictType.get(i);
					if(null!=tawSystemDictType.getDictid()){
						inputMap.put(tawSystemDictType.getDictname(), tawSystemDictType.getDictid());
					}
				}
			}else if("deptType".equals(type)){
				tempDeptType=iTawSystemDeptService.queryAllDeptByParentDeptID(parentCode);
				for(int i=0;i<tempDeptType.size();i++){
					TawSystemDept tawSystemDept=tempDeptType.get(i);
					if(null!=tawSystemDept.getDeptid()){
						inputMap.put(tawSystemDept.getDeptname(), tawSystemDept.getDeptid());
					}
				}
			}else if("personType".equals(type)){
				tempUserType=iTawSystemUserService.getAllUsers();
				for(int i=0;i<tempUserType.size();i++){
					TawSystemUser tawSystemUser=tempUserType.get(i);
					if(null!=tawSystemUser.getUserid()){
						inputMap.put(tawSystemUser.getUsername(), tawSystemUser.getUserid());
					}
				}
			}else if("roleType".equals(type)){
				tempSubRoleType=iTawSystemSubRoleService.getAllSubRoles();
				for(int i=0;i<tempSubRoleType.size();i++){
					TawSystemSubRole tawSystemSubRole=tempSubRoleType.get(i);
					if(null!=tawSystemSubRole.getId()){
						inputMap.put(tawSystemSubRole.getSubrolename(), tawSystemSubRole.getId());
					}
				}
			}
			return inputMap;
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
		
		//,String indexCompute,String indexComputeReplace
		//abc+5-6/5,csd+5+7         abc-bc,c-s-d
		//String[][] tempRet=coreStatisticEngine.getStatisticSoleCombineRet(index, dimensions, dimensionsValue, queryCondition, groupType, whereString,,);
		return coreStatisticEngine.getStatisticSoleCombineRet(index, dimensions, dimensionsValue, queryCondition, groupType, whereString,rateCompte,indexNo,countValue,rateType);
		
	}
	
	
	
	/**
	 * 
	 *生成统计数据 
	 */
	
	public String[][] getRetData(Map<String,String> indexMap,Map<String,String> paramMap,String groupType,String whereString,String paramEngStr,String direction){
		String[] index=new String[indexMap.size()/2];//[null],[count(*)]
		String[] dimensions=new String[paramMap.size()];//[null],[MAINFAULTGENERANTCITY]
		String[] queryCondition=new String[indexMap.size()/2];//[null],[and status='1']
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
		return coreStatisticEngine.getStatisticCombineRet(index,dimensions,queryCondition,groupType,whereString,paramEngStr,direction);
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



	@RequestMapping(value = "excelExport.json",method= RequestMethod.POST)
	public HSSFWorkbook excelExport(HttpServletRequest request,
			HttpServletResponse response) {
		String demensionStr = request.getParameter("demension_export");
		String indexStr = request.getParameter("index_export");
		String dataStr = request.getParameter("data_export");
		String complexTitleStr = request.getParameter("complexTitle_export");

		
		ArrayList<TawStatisticParam> tawStatisticParam = JSON.parseObject(demensionStr, new TypeReference<ArrayList<TawStatisticParam>>(){});
		ArrayList<TawStatisticIndex> tawStatisticIndex = JSON.parseObject(indexStr, new TypeReference<ArrayList<TawStatisticIndex>>(){});
		ArrayList<ArrayList<String>> data= JSON.parseObject(dataStr,new TypeReference<ArrayList<ArrayList<String>>>(){});
		ArrayList<ComplexDimension> complexDimension = JSON.parseObject(complexTitleStr, new TypeReference<ArrayList<ComplexDimension>>(){});
		
		
		HSSFWorkbook wb=ExcelUtil.generateNewWorkbook();
		HSSFSheet sheet=ExcelUtil.generateNewSheet(wb, "统计报表");
		
		Integer beginRow = 0;
		Integer endRow = (complexDimension==null || complexDimension.size()==0)?0:Integer.parseInt(complexDimension.get(0).getTotalLayer())-1;
		
		for(int i=0;i<=endRow;i++){
			sheet.createRow(i);
		}
		
		Integer beginIndex = 0;
		//纬度表头
		for(int i=0;i<tawStatisticParam.size();i++){
			ExcelUtil.generateStyleRange2(wb,sheet, beginRow, endRow, beginIndex, beginIndex, tawStatisticParam.get(i).getParamName());
			beginIndex++;
		}
		//指标表头
		for(int i=0;i<tawStatisticIndex.size();i++){
			ExcelUtil.generateStyleRange2(wb,sheet, beginRow, endRow, beginIndex, beginIndex, tawStatisticIndex.get(i).getIndicateName());
			beginIndex++;
		}
		//复合表头
		if(!(complexDimension==null || complexDimension.size()==0)){
			generateDimension(complexDimension,wb,sheet,beginIndex);
		}
		int dataBeginRow=endRow+1;
		
		//列表维度转化树
		List<Map<String,String>> retTransferDimensionList=new ArrayList<Map<String,String>>();
		Map<String,String> inputMap=new HashMap<String,String>();
		for(int i=0;i<tawStatisticParam.size();i++){
			TawStatisticParam tawStatisticParamTemp=tawStatisticParam.get(i);
			retTransferDimensionList.add(transferDemension(tawStatisticParamTemp.getParentParamId(),tawStatisticParamTemp.getParamCodeType(),inputMap));
		}
		
		//数据生成
		for(int i=0;i<data.size();i++){
			sheet.createRow(dataBeginRow);
			for(int j=0;j<data.get(i).size();j++){
				if(j<tawStatisticParam.size()){
					//String tempValue=(null==retTransferDimensionList.get(j).get(data.get(i).get(j)))?("【"+data.get(i).get(j)+"】"):retTransferDimensionList.get(j).get(data.get(i).get(j));
					String tempValue=(null==retTransferDimensionList.get(j).get(data.get(i).get(j)))?(""+data.get(i).get(j)+""):(""+retTransferDimensionList.get(j).get(data.get(i).get(j))+"");
					ExcelUtil.generateStyleRange2(wb,sheet, dataBeginRow, dataBeginRow, j, j,tempValue );
				}else{
					//String tempValue= data.get(i).get(j).split("-")[0];
					String tempValue= data.get(i).get(j);
					ExcelUtil.generateStyleRange2(wb,sheet, dataBeginRow, dataBeginRow, j, j, tempValue);
				}
			}
			dataBeginRow++;
		}
		
		
		String exportFileName ="eoms_statistic.xls";
		
		
		final String userAgent = request.getHeader("USER-AGENT");
		String finalFileName = null;
		
		
		
	      try {
	    	  if(null==userAgent){
	  			finalFileName = URLEncoder.encode(exportFileName,"UTF8");//其他浏览器
	  		}else if(userAgent.indexOf("MSIE")>-1){//IE浏览器
	  			finalFileName = URLEncoder.encode(exportFileName,"UTF8");
	  		}else if(userAgent.indexOf("Mozilla")>-1){//google,火狐浏览器
	  			finalFileName = new String(exportFileName.getBytes(), "ISO8859-1");
	  		 }else{
	  			 finalFileName = URLEncoder.encode(exportFileName,"UTF8");//其他浏览器
	  		 }
	  		            
	  		response.reset();
	  		response.setCharacterEncoding("UTF-8");
	  		response.setContentType("application/octet-stream; charset=UTF-8");
	  		response.setHeader("Content-Disposition", "attachment;fileName=\""+ finalFileName+"\"");
			 OutputStream outputStream=response.getOutputStream();
		
			 
	
	
			 //生成EXCEL*******strat
			 wb.write(outputStream);
			 outputStream.close();
			 //生成EXCEL*******end 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		 
		return wb;
	}
	
	
	@RequestMapping(value = "excelExportXml.json",method= RequestMethod.POST)
	public HSSFWorkbook excelExportXml(HttpServletRequest request,
			HttpServletResponse response) {
		String demensionStr = request.getParameter("demension_export");
		String indexStr = request.getParameter("index_export");
		String dataStr = request.getParameter("data_export");
		String complexTitleStr = request.getParameter("complexTitle_export");

		String model_id=BaseUtil.nullObject2String(request.getParameter("model_id"));
		TawStatisticModel tawStatisticModel=iTawStatisticModelService.getModelById(model_id);
		
		ArrayList<TawStatisticParam> tawStatisticParam = JSON.parseObject(demensionStr, new TypeReference<ArrayList<TawStatisticParam>>(){});
		ArrayList<TawStatisticIndex> tawStatisticIndex = JSON.parseObject(indexStr, new TypeReference<ArrayList<TawStatisticIndex>>(){});
		ArrayList<ArrayList<String>> data= JSON.parseObject(dataStr,new TypeReference<ArrayList<ArrayList<String>>>(){});
		ArrayList<ComplexDimension> complexDimension = JSON.parseObject(complexTitleStr, new TypeReference<ArrayList<ComplexDimension>>(){});
		
		
		HSSFWorkbook wb=ExcelUtil.generateNewWorkbook();
		HSSFSheet sheet=ExcelUtil.generateNewSheet(wb, "统计报表");
		
		Integer beginRow = 0;
		Integer endRow = (complexDimension==null || complexDimension.size()==0)?0:Integer.parseInt(complexDimension.get(0).getTotalLayer())-1;
		
		for(int i=0;i<=endRow;i++){
			sheet.createRow(i);
		}
		
		Integer beginIndex = 0;
		//复合表头
		if(!(complexDimension==null || complexDimension.size()==0)){
			generateDimension(complexDimension,wb,sheet,beginIndex);
		}
		int dataBeginRow=endRow+1;
		
		//列表维度转化树
		List<Map<String,String>> retTransferDimensionList=new ArrayList<Map<String,String>>();
		Map<String,String> inputMap=new HashMap<String,String>();
	
		XmlStatisticGenerateUtil xmlStatisticGenerateUtil=new XmlStatisticGenerateUtil();
		
		 String paramCodeType=xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName()).elementText("statistic-demension-codeType");
		 String parentCode=xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName()).elementText("statistic-demension-parentCode"); 
		 
		 inputMap=transferDemension(parentCode,paramCodeType,inputMap);
		
		//数据生成
		for(int i=0;i<data.size();i++){
			sheet.createRow(dataBeginRow);
			for(int j=0;j<data.get(i).size();j++){
				if(j<tawStatisticParam.size()){
					String tempValue=(null==inputMap.get(data.get(i).get(j)))?("【"+data.get(i).get(j)+"】"):inputMap.get(data.get(i).get(j));
					ExcelUtil.generateStyleRange(wb,sheet, dataBeginRow, dataBeginRow, j, j,tempValue );
				}else{
					String tempValue= data.get(i).get(j).split("-")[0];
					ExcelUtil.generateStyleRange(wb,sheet, dataBeginRow, dataBeginRow, j, j, tempValue);
				}
			}
			dataBeginRow++;
		}
		
		
		String exportFileName ="eoms_statistic.xls";
		
		
		final String userAgent = request.getHeader("USER-AGENT");
		String finalFileName = null;
		
		
		
	      try {
	    	  if(null==userAgent){
	  			finalFileName = URLEncoder.encode(exportFileName,"UTF8");//其他浏览器
	  		}else if(userAgent.indexOf("MSIE")>-1){//IE浏览器
	  			finalFileName = URLEncoder.encode(exportFileName,"UTF8");
	  		}else if(userAgent.indexOf("Mozilla")>-1){//google,火狐浏览器
	  			finalFileName = new String(exportFileName.getBytes(), "ISO8859-1");
	  		 }else{
	  			 finalFileName = URLEncoder.encode(exportFileName,"UTF8");//其他浏览器
	  		 }
	  		            
	  		response.reset();
	  		response.setCharacterEncoding("UTF-8");
	  		response.setContentType("application/octet-stream; charset=UTF-8");
	  		response.setHeader("Content-Disposition", "attachment;fileName=\""+ finalFileName+"\"");
			 OutputStream outputStream=response.getOutputStream();
		
			 
	
	
			 //生成EXCEL*******strat
			 wb.write(outputStream);
			 outputStream.close();
			 //生成EXCEL*******end 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		 
		return wb;
	}
	
	
	
	
	
	public void generateDimension(List<ComplexDimension> complexDimension,HSSFWorkbook wb,HSSFSheet sheet,int beginIndex){
		for(int i=0;i<complexDimension.size();i++){
			int tempBeginRow=Integer.valueOf(complexDimension.get(i).getCurrentLayer())-1;
			int tempEndRow=Integer.valueOf(complexDimension.get(i).getCurrentLayer())-1+Integer.parseInt(complexDimension.get(i).getRowSpan())-1;
			int tempBegin= beginIndex;
			int tempEnd=beginIndex+Integer.valueOf(complexDimension.get(i).getCloumnSpan())-1;
			
			ExcelUtil.generateStyleRange2(wb,sheet,tempBeginRow , tempEndRow ,tempBegin , tempEnd, complexDimension.get(i).getName());
			if(tempEndRow<Integer.valueOf(complexDimension.get(i).getTotalLayer())-1){
				generateDimension(complexDimension.get(i).getChildDimension(),wb,sheet,beginIndex);
			}
			beginIndex=tempEnd+1;
		}
		
	}
	
	/** 
	 *说明：数据攥取
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="graspDataPage.json")
	public ModelAndView graspDataPage(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav=new ModelAndView();
		String page=BaseUtil.nullObject2String(request.getParameter("page"));
		String pageSize=BaseUtil.nullObject2String(request.getParameter("pageSize"));
		 //当前页  
        int currentPage = Integer.parseInt((page == null || page == "0"||"".equals(page)) ? "1":page);  
        //每页显示条数  
        int number = Integer.parseInt((pageSize == null || pageSize == "0"||"".equals(pageSize)) ? "15":pageSize);  
		
		String indexId=BaseUtil.nullObject2String(request.getParameter("indexId"));
		String demensionList=BaseUtil.nullObject2String(request.getParameter("demensionList"));
		String dimensionCondition=BaseUtil.nullObject2String(request.getParameter("dimensionCondition"));
		String graspDemensionCondition=BaseUtil.nullObject2String(request.getParameter("graspDemensionCondition"));
		String modelId=BaseUtil.nullObject2String(request.getParameter("modelId"));
		
		String condition=BaseUtil.nullObject2String(request.getParameter("condition"));
		String dataType=BaseUtil.nullObject2String(request.getParameter("dataType"));
		
		String beginTime=BaseUtil.nullObject2String(request.getParameter("begintime"));
		String endTime=BaseUtil.nullObject2String(request.getParameter("endTime"));
		 
		
		
		
		TawStatisticIndex tawStatisticIndex=iTawStatisticIndexService.getIndicateById(indexId);
		
		ArrayList<ColumnModel> columnModels= JSON.parseObject(demensionList,new TypeReference<ArrayList<ColumnModel>>(){});
		StringBuffer sbCondtion=new StringBuffer("");
		
		if(!"".equals(beginTime)){
			beginTime=beginTime+" 00:00:00";
			sbCondtion.append("and sendTime>=to_date('"+beginTime+"','yyyy-MM-dd hh24:mi:ss')");
		}
		if(!"".equals(endTime)){
			endTime=endTime+" 00:00:00";
			sbCondtion.append(" and sendTime<=to_date('"+endTime+"','yyyy-MM-dd hh24:mi:ss')");
		}
		
		
		if(!"".equals(dimensionCondition)){
			sbCondtion.append(" "+dimensionCondition);
		}
		
		if(!"".equals(graspDemensionCondition)){
			sbCondtion.append(" "+graspDemensionCondition);
		}
        
        String totalPageNum="10";
        PageModel pageModel=null;
        
        //获取字段
        TawStatisticModel tawStatisticModel=iTawStatisticModelService.getModelById(modelId);
      	//获取详单字段
      	//List<TawStatisticDetailItem> tempDetailItemList= iTawStatisticDetailItemService.getDetailItemByDetailId(tawStatisticModel.getDetailId());
      	List<TawStatisticDetailItem> tempDetailItemList= iTawStatisticDetailItemService.getDetailItemByDetailId(tawStatisticIndex.getDetailId());
      	TawStatisticDetail tawStatisticDetail=iTawStatisticDetailService.getTawStatisticDetailById(tawStatisticModel.getDetailId());
      	
      	String[] graspColumn=new String[tempDetailItemList.size()];
      	for(int i=0;i<tempDetailItemList.size();i++){
      		TawStatisticDetailItem tawStatisticDetailItem=tempDetailItemList.get(i);
      		graspColumn[i]=tawStatisticDetailItem.getDetailColumnEng();
      	}
      		
      	//queryConditon
      	if("".equals(dataType)){
	        for(int i=0;i<columnModels.size();i++){
	        	ColumnModel temp=columnModels.get(i);
	        	if(!temp.getValue().equals("total")){
	        		if(!"".equals(temp.getValue())){
	        			sbCondtion.append(" and "+temp.getColumn()+"='"+temp.getValue()+"'");
	        		}else{
	        			sbCondtion.append(" and "+temp.getColumn()+" is null");
	        		}
	        	}
//	        	else{
//	        		sbCondtion.append(" and "+temp.getColumn()+" is not null");
//	        	}
	        }
      	}
        
        sbCondtion.append("null".endsWith(BaseUtil.nullObject2String(tawStatisticIndex.getCondition()))?"":" "+BaseUtil.nullObject2String(tawStatisticIndex.getCondition()));
        
        sbCondtion.append(" "+BaseUtil.nullObject2String(condition));
        
        CoreStatisticEngine coreStatisticEngine=new CoreStatisticEngine();
        
        totalPageNum=coreStatisticEngine.getGraspDataArrayCount(graspColumn, sbCondtion.toString(), tawStatisticModel.getGroupType())[0][0];
        pageModel=new PageModel(Integer.parseInt(totalPageNum),number,currentPage);
        String [][] data=coreStatisticEngine.getGraspDataArrayByPage(graspColumn, sbCondtion.toString(), tawStatisticModel.getGroupType(),pageModel);
		//封装分页PageModel
		
		Map<String,Object> retMap=new HashMap<String,Object>();

		retMap.put("rows", data);
		retMap.put("total", pageModel.getTotalPages());
		retMap.put("url", tawStatisticDetail.getUrl());
		retMap.put("pageSize", number);
		retMap.put("currentPageNo", pageModel.getCurrnetPageNum());
		retMap.put("tempDetailItemList", tempDetailItemList);

		mav.addObject("retJson", retMap);
		return mav;
	}
	
	/** 
	 *说明：详单导出
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="detailExcelExport.json",method= RequestMethod.POST)
	public HSSFWorkbook detailExcelExport(HttpServletRequest request,HttpServletResponse response){
		String page=BaseUtil.nullObject2String(request.getParameter("page"));
		String pageSize=BaseUtil.nullObject2String(request.getParameter("pageSize"));
		 //当前页  
        int currentPage = Integer.parseInt((page == null || page == "0"||"".equals(page)) ? "1":page);  
        //每页显示条数  
        int number = Integer.parseInt((pageSize == null || pageSize == "0"||"".equals(pageSize)) ? "15":pageSize);  
		
		String indexId=BaseUtil.nullObject2String(request.getParameter("indexId"));
		String demensionList=BaseUtil.nullObject2String(request.getParameter("demensionList"));
		String dimensionCondition=BaseUtil.nullObject2String(request.getParameter("dimensionCondition"));
		String graspDemensionCondition=BaseUtil.nullObject2String(request.getParameter("graspDemensionCondition"));
		String modelId=BaseUtil.nullObject2String(request.getParameter("modelId"));
		
		String condition=BaseUtil.nullObject2String(request.getParameter("condition"));
		String dataType=BaseUtil.nullObject2String(request.getParameter("dataType"));
		
		String beginTime=BaseUtil.nullObject2String(request.getParameter("begintime"));
		String endTime=BaseUtil.nullObject2String(request.getParameter("endTime"));
		 
		
		
		
		TawStatisticIndex tawStatisticIndex=iTawStatisticIndexService.getIndicateById(indexId);
		
		ArrayList<ColumnModel> columnModels= JSON.parseObject(demensionList,new TypeReference<ArrayList<ColumnModel>>(){});
		StringBuffer sbCondtion=new StringBuffer("");
		
		if(!"".equals(beginTime)){
			beginTime=beginTime+" 00:00:00";
			sbCondtion.append("and sendTime>=to_date('"+beginTime+"','yyyy-MM-dd hh24:mi:ss')");
		}
		if(!"".equals(endTime)){
			endTime=endTime+" 00:00:00";
			sbCondtion.append(" and sendTime<=to_date('"+endTime+"','yyyy-MM-dd hh24:mi:ss')");
		}
		
		
		if(!"".equals(dimensionCondition)){
			sbCondtion.append(" "+dimensionCondition);
		}
		if(!"".equals(graspDemensionCondition)){
			sbCondtion.append(" "+graspDemensionCondition);
		}
        
        String totalPageNum="10";
        PageModel pageModel=null;
        
        //获取字段
        TawStatisticModel tawStatisticModel=iTawStatisticModelService.getModelById(modelId);
      	//获取详单字段
      	//List<TawStatisticDetailItem> tempDetailItemList= iTawStatisticDetailItemService.getDetailItemByDetailId(tawStatisticModel.getDetailId());
        List<TawStatisticDetailItem> tempDetailItemList= iTawStatisticDetailItemService.getDetailItemByDetailId(tawStatisticIndex.getDetailId());
      	TawStatisticDetail tawStatisticDetail=iTawStatisticDetailService.getTawStatisticDetailById(tawStatisticModel.getDetailId());
      	
      	String[] graspColumn=new String[tempDetailItemList.size()];
      	for(int i=0;i<tempDetailItemList.size();i++){
      		TawStatisticDetailItem tawStatisticDetailItem=tempDetailItemList.get(i);
      		graspColumn[i]=tawStatisticDetailItem.getDetailColumnEng();
      	}
      		
      	//queryConditon
      	if("".equals(dataType)){
	        for(int i=0;i<columnModels.size();i++){
	        	ColumnModel temp=columnModels.get(i);
	        	if(!temp.getValue().equals("total")){
	        		if(!"".equals(temp.getValue())){
	        			sbCondtion.append(" and "+temp.getColumn()+"='"+temp.getValue()+"'");
	        		}else{
	        			sbCondtion.append(" and "+temp.getColumn()+" is null");
	        		}
	        	}
//	        	else{
//	        		sbCondtion.append(" and "+temp.getColumn()+" is not null");
//	        	}
	        }
      	}
        
        sbCondtion.append("null".endsWith(BaseUtil.nullObject2String(tawStatisticIndex.getCondition()))?"":" "+BaseUtil.nullObject2String(tawStatisticIndex.getCondition()));
        
        sbCondtion.append(" "+BaseUtil.nullObject2String(condition));
        
        CoreStatisticEngine coreStatisticEngine=new CoreStatisticEngine();
        
        totalPageNum=coreStatisticEngine.getGraspDataArrayCount(graspColumn, sbCondtion.toString(), tawStatisticModel.getGroupType())[0][0];
        pageModel=new PageModel(Integer.parseInt(totalPageNum),Integer.parseInt(totalPageNum),currentPage);
        String [][] data=coreStatisticEngine.getGraspDataArrayByPage(graspColumn, sbCondtion.toString(), tawStatisticModel.getGroupType(),pageModel);
		//封装分页PageModel
		
        HSSFWorkbook wb=ExcelUtil.generateNewWorkbook();
		HSSFSheet sheet=ExcelUtil.generateNewSheet(wb, "统计报表");
        Integer beginRow = 0;
		Integer endRow = 0;
		for(int i=0;i<=endRow;i++){
			sheet.createRow(i);
		}
		Integer beginIndex = 0;
		
		ExcelUtil.generateStyleRange2(wb,sheet, beginRow, endRow, beginIndex, beginIndex, "序号");
		beginIndex++;
		for(int i=1;i<tempDetailItemList.size();i++){
			TawStatisticDetailItem tawStatisticDetailItem = tempDetailItemList.get(i);
			ExcelUtil.generateStyleRange2(wb,sheet, beginRow, endRow, beginIndex, beginIndex, tawStatisticDetailItem.getDetailColumnChina());
			beginIndex++;
		}
		
//		for(TawStatisticDetailItem tawStatisticDetailItem:tempDetailItemList){
//			ExcelUtil.generateStyleRange2(wb,sheet, beginRow, endRow, beginIndex, beginIndex, tawStatisticDetailItem.getDetailColumnChina());
//			beginIndex++;
//		}
		int dataBeginRow=endRow+1;
		
		//数据生成
		for(int i=0;i<data.length;i++){
			sheet.createRow(dataBeginRow);
			for(int j=0;j<data[i].length;j++){
				String tempValue= data[i][j];
				if(j==0){
					ExcelUtil.generateStyleRange2(wb,sheet, dataBeginRow, dataBeginRow, j, j, String.valueOf(i+1));
				}else{
					ExcelUtil.generateStyleRange2(wb,sheet, dataBeginRow, dataBeginRow, j, j, tempValue);
				}
			}
			dataBeginRow++;
		}
		
		String exportFileName ="eoms_statistic_detail.xls";
		
		
		final String userAgent = request.getHeader("USER-AGENT");
		String finalFileName = null;
		
		
		
	    try {
			if(null==userAgent){
				finalFileName = URLEncoder.encode(exportFileName,"UTF8");//其他浏览器
			}else if(userAgent.indexOf("MSIE")>-1){//IE浏览器
				finalFileName = URLEncoder.encode(exportFileName,"UTF8");
			}else if(userAgent.indexOf("Mozilla")>-1){//google,火狐浏览器
				finalFileName = new String(exportFileName.getBytes(), "ISO8859-1");
			}else{
				finalFileName = URLEncoder.encode(exportFileName,"UTF8");//其他浏览器
			}
			            
			response.reset();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;fileName=\""+ finalFileName+"\"");
			OutputStream outputStream=response.getOutputStream();
			
			 
			
			
			//生成EXCEL*******strat
			wb.write(outputStream);
			outputStream.close();
			//生成EXCEL*******end 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wb;
	}
	
	
	/** 
	 *说明：数据攥取
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="graspDataPageXml.json")
	public ModelAndView graspDataPageXml(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav=new ModelAndView();
		String page=BaseUtil.nullObject2String(request.getParameter("page"));
		String pageSize=BaseUtil.nullObject2String(request.getParameter("pageSize"));
		 //当前页  
        int currentPage = Integer.parseInt((page == null || page == "0"||"".equals(page)) ? "1":page);  
        //每页显示条数  
        int number = Integer.parseInt((pageSize == null || pageSize == "0"||"".equals(pageSize)) ? "15":pageSize);  
		
        String grapNum=BaseUtil.nullObject2String(request.getParameter("grapNum"));
		String condition=BaseUtil.nullObject2String(request.getParameter("condition"));
		
		String beginTime=BaseUtil.nullObject2String(request.getParameter("beginTime"));
		String endTime=BaseUtil.nullObject2String(request.getParameter("endTime"));
		
		String model_id=BaseUtil.nullObject2String(request.getParameter("model_id"));
		TawStatisticModel tawStatisticModel=iTawStatisticModelService.getModelById(model_id);
		
		StringBuffer sbCondtion=new StringBuffer("");
		
		if(!"".equals(beginTime)){
			beginTime=beginTime+" 00:00:00";
			sbCondtion.append("and sendTime>=to_date('"+beginTime+"','yyyy-MM-dd hh24:mi:ss')");
		}
		if(!"".equals(endTime)){
			endTime=endTime+" 00:00:00";
			sbCondtion.append(" and sendTime<=to_date('"+endTime+"','yyyy-MM-dd hh24:mi:ss')");
		}
		
        
        String totalPageNum="10";
        PageModel pageModel=null;
        
        
        XmlStatisticGenerateUtil xmlStatisticGenerateUtil=new XmlStatisticGenerateUtil();
        //获取字段
        Element root= xmlStatisticGenerateUtil.getRoot(tawStatisticModel.getXmlName());
        
        List<Element> detailItems =root.element("statistic-details").elements("statistic-detail-item");
        Map<String,TawStatisticDetail> detailMap=new HashMap<String,TawStatisticDetail>();
        TawStatisticDetail temDetail=new TawStatisticDetail();
        //获取详单字段
      	String[] graspColumn=null;
        
        for(int i=0;i<detailItems.size();i++){
        	
        	Element item=detailItems.get(i);
        	if(item.elementText("detail-num").equals(grapNum)){
            	temDetail.setDetailNumName(item.elementText("detail-name"));
            	temDetail.setUrl(item.elementText("detail-url"));
            	temDetail.setGroupType(item.elementText("detail-groupType"));
            	
            	List<Element> childrenColumn=item.element("cloumns").elements("column-item");
            	graspColumn=new String[childrenColumn.size()];
            	
            	for(int j=0;j<childrenColumn.size();j++){
            		graspColumn[j]=childrenColumn.get(j).elementText("column-name");
            		TawStatisticDetailItem tawStatisticDetailItem=new TawStatisticDetailItem();
            		tawStatisticDetailItem.setDetailColumnEng(childrenColumn.get(j).elementText("column-name"));
            		tawStatisticDetailItem.setDetailColumnChina(childrenColumn.get(j).elementText("column-cnname"));
            		
            		temDetail.getTawStatisticDetailItems().add(tawStatisticDetailItem);
            	}
            	detailMap.put(item.elementText("detail-num"), temDetail);
        	}
        	
        }
        	
        
        sbCondtion.append(" "+BaseUtil.nullObject2String(condition));
        
        
        
        
        CoreStatisticEngine coreStatisticEngine=new CoreStatisticEngine();
        totalPageNum=coreStatisticEngine.getGraspDataArrayCount(graspColumn, sbCondtion.toString(), temDetail.getGroupType())[0][0];
        pageModel=new PageModel(Integer.parseInt(totalPageNum),number,currentPage);
        String [][] data=coreStatisticEngine.getGraspDataArrayByPage(graspColumn, sbCondtion.toString(), temDetail.getGroupType(),pageModel);
		//封装分页PageModel
		
		Map<String,Object> retMap=new HashMap<String,Object>();

		retMap.put("rows", data);
		retMap.put("total", pageModel.getTotalPages());
		retMap.put("pageSize", number);
		retMap.put("currentPageNo", pageModel.getCurrnetPageNum());
		retMap.put("tempDetailItemList", temDetail.getTawStatisticDetailItems());
		retMap.put("tempDetail", temDetail);
		mav.addObject("retJson", retMap);
		return mav;
	}
	
	/**
	 * 加载选择树数据
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getTreeDataByType.json")
	public JSONArray getTreeDataByType(HttpServletRequest request,HttpServletResponse response){
		String treeType = BaseUtil.nullObject2String(request.getParameter("treeType"));
		String nodeType = BaseUtil.nullObject2String(request.getParameter("nodeType"));
		String parentId = BaseUtil.nullObject2String(request.getParameter("id"));
		if("".equals(nodeType)){
			nodeType = treeType;
		}
		if("".equals(parentId)){
			parentId = "-1";
		}
		JSONArray jsonArray = new JSONArray();
		if("areaType".equals(treeType)){
			List<TawSystemArea> areaList = iTawSystemAreaService.queryAreasByParentId(parentId);
			for(int i=0;i<areaList.size();i++){
				TawSystemArea tawSystemArea = areaList.get(i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name", tawSystemArea.getAreaname());
				jsonObject.put("id", tawSystemArea.getAreaid());
				jsonObject.put("nodeType", nodeType);
				if("0".equals(String.valueOf(tawSystemArea.getLeaf()))){
					jsonObject.put("isParent", true);
				}else{
					jsonObject.put("isParent", false);
				}
				jsonArray.add(jsonObject);
			}
		}else if("deptType".equals(treeType)){
			List<TawSystemDept> deptList = iTawSystemDeptService.queryDeptByParentDeptID(parentId);
			for(int i=0;i<deptList.size();i++){
				TawSystemDept tawSystemDept = deptList.get(i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name", tawSystemDept.getDeptname());
				jsonObject.put("id", tawSystemDept.getDeptid());
				jsonObject.put("nodeType", nodeType);
				if("0".equals(String.valueOf(tawSystemDept.getLeaf()))){
					jsonObject.put("isParent", true);
				}else{
					jsonObject.put("isParent", false);
				}
				jsonArray.add(jsonObject);
			}
		}else if("personType".equals(treeType)){
			List<TawSystemDept> deptList = iTawSystemDeptService.queryDeptByParentDeptID(parentId);
			for(int i=0;i<deptList.size();i++){
				TawSystemDept tawSystemDept = deptList.get(i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name", tawSystemDept.getDeptname());
				jsonObject.put("id", tawSystemDept.getDeptid());
				jsonObject.put("nodeType", nodeType);
				jsonObject.put("isParent", true);
				jsonArray.add(jsonObject);
			}
			List<TawSystemUser> userList = iTawSystemUserService.getUsersByDeptId(parentId);
			for(int i=0;i<userList.size();i++){
				TawSystemUser tawSystemUser = userList.get(i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name", tawSystemUser.getUsername());
				jsonObject.put("id", tawSystemUser.getUserid());
				jsonObject.put("nodeType", nodeType);
				jsonObject.put("isParent", false);
				jsonArray.add(jsonObject);
			}
		}else if("roleType".equals(treeType)){
			List<TawSystemArea> areaList = iTawSystemAreaService.queryAreasByParentId(parentId);
			for(int i=0;i<areaList.size();i++){
				TawSystemArea tawSystemArea = areaList.get(i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name", tawSystemArea.getAreaname());
				jsonObject.put("id", tawSystemArea.getAreaid());
				jsonObject.put("nodeType", nodeType);
				jsonObject.put("isParent", true);
				jsonArray.add(jsonObject);
			}
			List<TawSystemSubRole> subRoleList = iTawSystemSubRoleService.getSubRolesByAreaId(parentId);
			for(int i=0;i<subRoleList.size();i++){
				TawSystemSubRole tawSystemSubRole = subRoleList.get(i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name", tawSystemSubRole.getSubrolename());
				jsonObject.put("id", tawSystemSubRole.getId());
				jsonObject.put("nodeType", nodeType);
				jsonObject.put("isParent", false);
				jsonArray.add(jsonObject);
			}
		}else if("dictType".equals(treeType)){
			List<TawSystemDictType> dictTypeList = iTawSystemDictTypeService.queryDictTypesByParentId(parentId);
			for(int i=0;i<dictTypeList.size();i++){
				TawSystemDictType tawSystemDictType = dictTypeList.get(i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name", tawSystemDictType.getDictname());
				jsonObject.put("id", tawSystemDictType.getDictid());
				jsonObject.put("nodeType", nodeType);
				if("0".equals(String.valueOf(tawSystemDictType.getLeaf()))){
					jsonObject.put("isParent", true);
				}else{
					jsonObject.put("isParent", false);
				}
				jsonArray.add(jsonObject);
			}
		}
		return jsonArray;
	}
}
