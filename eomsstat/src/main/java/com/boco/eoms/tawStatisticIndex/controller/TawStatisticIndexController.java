package com.boco.eoms.tawStatisticIndex.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boco.eoms.baseUtil.BaseUtil;
import com.boco.eoms.baseUtil.PageModel;
import com.boco.eoms.tawStatisticIndex.model.TawStatisticIndex;
import com.boco.eoms.tawStatisticIndex.service.ITawStatisticIndexService;


@Controller
@RequestMapping(value="tawStatisticIndex") 
public class TawStatisticIndexController {
	
	private static final Logger logger = Logger.getLogger(TawStatisticIndexController.class);
	
	@Resource
	private ITawStatisticIndexService iTawStatisticIndexService;
	
	
	public static Logger getLogger() {
		return logger;
	}
	
	/** 
	 *说明：查询所有指标集合分页呈现
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="indexCollection.json") 
    public ModelAndView indexListCollection(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String page=BaseUtil.nullObject2String(request.getParameter("page"));
		String pageSize=BaseUtil.nullObject2String(request.getParameter("pageSize"));
		
		String indicateNamequery=BaseUtil.nullObject2String(request.getParameter("searchOption"));
		String groupTypequery=BaseUtil.nullObject2String(request.getParameter("groupTypequery"));
		String indicateValuequery=BaseUtil.nullObject2String(request.getParameter("indicateValuequery"));
		
		 //当前页  
        int currentPage = Integer.parseInt((page == null || page == "0") ? "1":page);  
        //每页显示条数  
        int number = Integer.parseInt((pageSize == null || pageSize == "0") ? "15":pageSize);  
        
        String totalPageNum="10";
        PageModel pageModel=null;
		List<TawStatisticIndex> indexCollection=new ArrayList<TawStatisticIndex>();
		if(null!=iTawStatisticIndexService.listIndexConditonCount(indicateNamequery, groupTypequery, indicateValuequery)){
			totalPageNum=iTawStatisticIndexService.listIndexConditonCount(indicateNamequery, groupTypequery, indicateValuequery);
		}
		//封装分页PageModel
		pageModel=new PageModel(Integer.parseInt(totalPageNum),number,currentPage);
		indexCollection= iTawStatisticIndexService.listIndexsConditionByPage(pageModel, indicateNamequery, groupTypequery, indicateValuequery);
		
		
		Map<String,Object> retMap=new HashMap<String,Object>();

		retMap.put("rows", indexCollection);
		retMap.put("total", pageModel.getTotalPages());
		retMap.put("pageSize", number);
		retMap.put("currentPageNo", pageModel.getCurrnetPageNum());

		mav.addObject("retJson", retMap);	 
		return mav;
    }
	
	
	@RequestMapping(value="getIndexById.json") 
    public ModelAndView indexListById(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String indicate_id=BaseUtil.nullObject2String(request.getParameter("indicate_id"));
		TawStatisticIndex tawStatisticIndex = new TawStatisticIndex();
		tawStatisticIndex= iTawStatisticIndexService.getIndicateById(indicate_id);
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		retMap.put("indicateName", tawStatisticIndex.getIndicateName());
		retMap.put("indicateValue", tawStatisticIndex.getIndicateValue());
		retMap.put("indicateDescribe", tawStatisticIndex.getIndicateDescribe());
		retMap.put("complexFlag", tawStatisticIndex.getComplexFlag());
		retMap.put("condition", tawStatisticIndex.getCondition());
		retMap.put("operateFlag", tawStatisticIndex.getOperateFlag());
		
		retMap.put("groupType", tawStatisticIndex.getGroupType());
		retMap.put("detailId", tawStatisticIndex.getDetailId());
		
		retMap.put("indicatorFir_add", tawStatisticIndex.getIndicatorFir_add());	
		retMap.put("indicatorEnd_add", tawStatisticIndex.getIndicatorEnd_add());
		
		retMap.put("percentSign", tawStatisticIndex.getPercentSign());	
		retMap.put("indicateOverValue", tawStatisticIndex.getIndicateOverValue());
		retMap.put("isGrasp", tawStatisticIndex.getIsGrasp());
		mav.addObject("retJson", retMap);	 
		return mav;
    }
	
	
	/** 
	 *说明：指标的新增或跟新
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="indexAdd.json") 
    public ModelAndView indexAdd(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		TawStatisticIndex tawStatisticIndex=getIndexModel(request);
		iTawStatisticIndexService.saveOrUpdateIndex(tawStatisticIndex);

		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		
		mav.addObject("retJson", retMap);
		return mav;
		
	}
	
	
	
	public TawStatisticIndex getIndexModel(HttpServletRequest request){
		
		String submitType=BaseUtil.nullObject2String(request.getParameter("submitType"));
		String id="";
		String indicateName="";
		String indicateValue="";
		String indicateOverValue="";
		String percentSign="";
		String condition="";
		String complexFlag="";
		String groupType="";
		String detailId="";
		String indicatorFir_add="";
		String operateFlag="";
		String indicatorEnd_add="";
		String deleteStauts="";
		String isGrasp="";
		String indicateDescribe="";
		
		if("add".equals(submitType)){
			id=BaseUtil.nullObject2String(request.getParameter("id"));
			indicateName=BaseUtil.nullObject2String(request.getParameter("indicateName"));
			indicateValue=BaseUtil.nullObject2String(request.getParameter("indicateValue"));
			indicateOverValue=BaseUtil.nullObject2String(request.getParameter("indicateOverValue"));
			groupType=BaseUtil.nullObject2String(request.getParameter("groupType_add"));
			detailId=BaseUtil.nullObject2String(request.getParameter("detailId_add"));
			percentSign=BaseUtil.nullObject2String(request.getParameter("percentSign"));
			condition=BaseUtil.nullObject2String(request.getParameter("condition"));
			complexFlag=BaseUtil.nullObject2String(request.getParameter("complexFlag"));
			indicatorFir_add=BaseUtil.nullObject2String(request.getParameter("indicatorFir_add"));
			operateFlag=BaseUtil.nullObject2String(request.getParameter("operateFlag"));
			indicatorEnd_add=BaseUtil.nullObject2String(request.getParameter("indicatorEnd_add"));
			isGrasp=BaseUtil.nullObject2String(request.getParameter("isGrasp"));
			indicateDescribe = BaseUtil.nullObject2String(request.getParameter("indicateDescribe"));
			deleteStauts="1";
			
		}else{
			id=BaseUtil.nullObject2String(request.getParameter("id"));
			indicateName=BaseUtil.nullObject2String(request.getParameter("indicateName"));
			indicateValue=BaseUtil.nullObject2String(request.getParameter("indicateValue"));
			indicateOverValue=BaseUtil.nullObject2String(request.getParameter("indicateOverValue"));
			groupType=BaseUtil.nullObject2String(request.getParameter("groupType_edit"));
			detailId=BaseUtil.nullObject2String(request.getParameter("detailId_edit"));
			percentSign=BaseUtil.nullObject2String(request.getParameter("percentSign_edit"));
			condition=BaseUtil.nullObject2String(request.getParameter("condition_edit"));
			complexFlag=BaseUtil.nullObject2String(request.getParameter("complexFlag_edit"));
			indicatorFir_add=BaseUtil.nullObject2String(request.getParameter("indicatorFir_edit"));
			operateFlag=BaseUtil.nullObject2String(request.getParameter("operateFlag_edit"));
			indicatorEnd_add=BaseUtil.nullObject2String(request.getParameter("indicatorEnd_edit"));
			deleteStauts=BaseUtil.nullObject2String(request.getParameter("delete_edit"));
			isGrasp=BaseUtil.nullObject2String(request.getParameter("isGrasp_edit"));
			indicateDescribe = BaseUtil.nullObject2String(request.getParameter("indicateDescribe"));
		}
		
		
		TawStatisticIndex tawStatisticIndex=new TawStatisticIndex();
		tawStatisticIndex.setId(id);
		tawStatisticIndex.setComplexFlag(complexFlag);
		tawStatisticIndex.setCondition(condition);
		tawStatisticIndex.setGroupType(groupType);
		tawStatisticIndex.setDetailId(detailId);
		tawStatisticIndex.setIndicateName(indicateName);
		tawStatisticIndex.setIndicateOverValue(indicateOverValue);
		tawStatisticIndex.setIndicateValue(indicateValue);
		tawStatisticIndex.setOperateFlag(operateFlag);
		tawStatisticIndex.setPercentSign(percentSign);
		tawStatisticIndex.setIndicatorFir_add(indicatorFir_add);
		tawStatisticIndex.setIndicatorEnd_add(indicatorEnd_add);
		tawStatisticIndex.setDeleteStatus(deleteStauts);
		tawStatisticIndex.setIsGrasp(isGrasp);
		tawStatisticIndex.setIndicateDescribe(indicateDescribe);
		
		
		return tawStatisticIndex;
	}
	
	
	/** 
	 *说明：指标的删除
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="indexRemove.json") 
    public ModelAndView indexRemove(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String indicate_id=BaseUtil.nullObject2String(request.getParameter("indicate_id"));
		
		iTawStatisticIndexService.removeIndex(indicate_id);
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		
		mav.addObject("retJson", retMap);
		return mav;
		
	}
	
	/** 
	 *说明：根据报表归属查询所有指标
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="getIndexByTableName.json") 
    public ModelAndView getIndexByTableName(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String table_Name=BaseUtil.nullObject2String(request.getParameter("table_Name"));
		String tableCNName=BaseUtil.nullObject2String(request.getParameter("tableCNName"));
		
		
		List<TawStatisticIndex> retList=iTawStatisticIndexService.getIndexByTable(table_Name,tableCNName);
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		retMap.put("indexs", retList);
		mav.addObject("retJson", retMap);
		return mav;
	}
	
	
	
	
	
	
	
	
}
