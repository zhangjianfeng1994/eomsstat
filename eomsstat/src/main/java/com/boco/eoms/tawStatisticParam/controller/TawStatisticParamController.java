package com.boco.eoms.tawStatisticParam.controller;

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
import com.boco.eoms.tawStatisticParam.model.TawStatisticParam;
import com.boco.eoms.tawStatisticParam.service.ITawStatisticParamService;


@Controller
@RequestMapping(value="tawStatisticParam") 
public class TawStatisticParamController {
	private static final Logger logger = Logger.getLogger(TawStatisticParamController.class);
	
	@Resource
	private ITawStatisticParamService iTawStatisticParamService;
	

	public static Logger getLogger() {
		return logger;
	}
	
	/** 
	 *说明：查询所有指标集合分页呈现
	 * @param request
	 * @param response
	 * @return  http://localhost:8080/eomsstatisticplat/tawStatisticIndex/indexCollection.do
	 */
	
	@RequestMapping(value="paramCollection.json") 
    public ModelAndView paramListCollection(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String page=BaseUtil.nullObject2String(request.getParameter("page"));
		String pageSize=BaseUtil.nullObject2String(request.getParameter("pageSize"));
		
		String indicateNamequery=BaseUtil.nullObject2String(request.getParameter("searchOption"));
		String groupTypequery=BaseUtil.nullObject2String(request.getParameter("groupTypequery"));
		String indicateValuequery=BaseUtil.nullObject2String(request.getParameter("indicateValuequery"));
		
		
		//当前页  
        int currentPage = Integer.parseInt((page == null || page == "0") ? "1":page);  
        //姣忛〉鏄剧ず鏉℃暟  
        int number = Integer.parseInt((pageSize == null || pageSize == "0") ? "15":pageSize);  
        
        String totalPageNum="10";
        PageModel pageModel=null;
		List<TawStatisticParam> paramCollection=new ArrayList<TawStatisticParam>();
		if(null!=iTawStatisticParamService.listParamConditonCount(indicateNamequery, groupTypequery, indicateValuequery)){
			totalPageNum=iTawStatisticParamService.listParamConditonCount(indicateNamequery, groupTypequery, indicateValuequery);
		}
		//灏佽鍒嗛〉PageModel
		pageModel=new PageModel(Integer.parseInt(totalPageNum),number,currentPage);
		paramCollection= iTawStatisticParamService.listParamsConditionByPage(pageModel, indicateNamequery, groupTypequery, indicateValuequery);
		
	

		
		Map<String,Object> retMap=new HashMap<String,Object>();

		retMap.put("rows", paramCollection);
		retMap.put("total", pageModel.getTotalPages());
		retMap.put("pageSize", number);
		retMap.put("currentPageNo", pageModel.getCurrnetPageNum());

		mav.addObject("retJson", retMap);	 
		return mav;
    } 
	@RequestMapping(value="paramRemove.json") 
    public ModelAndView paramRemove(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String param_id=BaseUtil.nullObject2String(request.getParameter("param_id"));
		System.out.println(param_id);
		iTawStatisticParamService.removeParam(param_id);
		System.out.println("contrlloer-------------success");
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		
		mav.addObject("retJson", retMap);
		return mav;
		
	}
	
	@RequestMapping(value="getParamById.json") 
    public ModelAndView indexListById(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String param_id=BaseUtil.nullObject2String(request.getParameter("param_id"));
		TawStatisticParam tawStatisticParam = new TawStatisticParam();
		tawStatisticParam= iTawStatisticParamService.getParamById(param_id);
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		retMap.put("row", tawStatisticParam);		
		mav.addObject("retJson", retMap);	 
		return mav;
    }
	
	
	@RequestMapping(value="paramAdd.json") 
    public ModelAndView indexAdd(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		TawStatisticParam tawStatisticParam=getParamModel(request);
		iTawStatisticParamService.saveOrUpdateParam(tawStatisticParam);

		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		
		mav.addObject("retJson", retMap);
		return mav;
		
	}
	public TawStatisticParam getParamModel(HttpServletRequest request){
		
		String submitType=BaseUtil.nullObject2String(request.getParameter("submitType"));
		String id="";
		String paramName="";
		String paramEng="";
		String groupType="";
		String codeType="";
		String paramCode="";
		String parentParamId="";
		String deleteStatus="";
		String eomsParamType="";
		
		if("add".equals(submitType)){
			id=BaseUtil.nullObject2String(request.getParameter("id"));
			paramName=BaseUtil.nullObject2String(request.getParameter("paramName"));
			paramEng=BaseUtil.nullObject2String(request.getParameter("paramEng"));
			groupType=BaseUtil.nullObject2String(request.getParameter("groupType"));
			codeType=BaseUtil.nullObject2String(request.getParameter("codeType"));
			paramCode=BaseUtil.nullObject2String(request.getParameter("parentParamId"));
			parentParamId=BaseUtil.nullObject2String(request.getParameter("parentParamId"));
			eomsParamType=BaseUtil.nullObject2String(request.getParameter("eomsParamType"));
			deleteStatus="1";
		}else{
			id=BaseUtil.nullObject2String(request.getParameter("id"));
			paramName=BaseUtil.nullObject2String(request.getParameter("paramName"));
			paramEng=BaseUtil.nullObject2String(request.getParameter("paramEng"));
			groupType=BaseUtil.nullObject2String(request.getParameter("groupType_edit"));
			codeType=BaseUtil.nullObject2String(request.getParameter("codeType_edit"));
			paramCode=BaseUtil.nullObject2String(request.getParameter("parentParamId"));
			parentParamId=BaseUtil.nullObject2String(request.getParameter("parentParamId"));
			deleteStatus=BaseUtil.nullObject2String(request.getParameter("delete_edit"));
			eomsParamType=BaseUtil.nullObject2String(request.getParameter("eomsParamType_edit"));
		}
		
		TawStatisticParam tawStatisticParam=new TawStatisticParam();
		tawStatisticParam.setId(id);
		tawStatisticParam.setParamCode(paramCode);
		tawStatisticParam.setParamCodeType(codeType);
		tawStatisticParam.setParamEng(paramEng);
		tawStatisticParam.setParamName(paramName);
		tawStatisticParam.setParentParamId(parentParamId);
		tawStatisticParam.setGroupType(groupType);
		tawStatisticParam.setDeleteStatus(deleteStatus);
		tawStatisticParam.setEomsParamType(eomsParamType);
		return tawStatisticParam;
	}
	
	
	@RequestMapping(value="getParamByTableName.json") 
    public ModelAndView getParamByTableName(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String table_Name=BaseUtil.nullObject2String(request.getParameter("table_Name"));
		String tableCNName=BaseUtil.nullObject2String(request.getParameter("tableCNName"));
		
		List<TawStatisticParam> retList=iTawStatisticParamService.getParamByTable(table_Name,tableCNName);
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		retMap.put("params", retList);
		mav.addObject("retJson", retMap);
		return mav;
	}
	
	
	
	
}
