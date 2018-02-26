package com.boco.eoms.tawStatisticModel.controller;



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
import com.boco.eoms.tawStatisticIndex.model.TawStatisticIndex;
import com.boco.eoms.tawStatisticIndex.service.ITawStatisticIndexService;
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

@Controller
@RequestMapping(value="tawStatisticDataScreen")
public class TawStatisticDataScreenController {
	private static final Logger logger = Logger.getLogger(TawStatisticDataScreenController.class);
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
	
	public static Logger getLogger() {
		return logger;
	}
	
	
	/**
	 * 根据模板id获取模板
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getModelInfoById.json") 
    public ModelAndView getModelInfoById(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String model_id=BaseUtil.nullObject2String(request.getParameter("model_id"));
		String modelItemsStr=BaseUtil.nullObject2String(request.getParameter("modelItems"));
		List<TawStatisticIndex> indexList=new ArrayList<TawStatisticIndex>();
		List<TawStatisticParam> paramList=new ArrayList<TawStatisticParam>();
		//List<TawStatisticParam> compleList=new ArrayList<TawStatisticParam>();
		if(!"".equals(modelItemsStr)){
			String[] modelItems=modelItemsStr.split(",");
			for(int i=0;i<modelItems.length;i++){
				TawStatisticModelItem tempItemChoose=iTawStatisticModelItemService.getModelItemById(modelItems[i]);
				if("1".equals(tempItemChoose.getType())){
					TawStatisticIndex tawStatisticIndex=iTawStatisticIndexService.getIndicateById(tempItemChoose.getItemId());
					tawStatisticIndex.setModelItemId(tempItemChoose.getId());
					indexList.add(tawStatisticIndex);
				}else if("2".equals(tempItemChoose.getType())){
					TawStatisticParam tawStatisticParam=iTawStatisticParamService.getParamById(tempItemChoose.getItemId());
					tawStatisticParam.setModelItemId(tempItemChoose.getId());
					//tawStatisticParam.setChildrenList(transferMap(tawStatisticParam.getParamCode(),tawStatisticParam.getParamCodeType()));
					paramList.add(tawStatisticParam);
				}
			}
			
		}else{
			List<TawStatisticModelItem> modelItems_indicate=iTawStatisticModelItemService.getModelItemByModelIdAndType(model_id, "1");//获取指标
			List<TawStatisticModelItem> modelItems_demension=iTawStatisticModelItemService.getModelItemDemensionRootByModelIdAndType(model_id, "2");//获取维度
			for(int i=0;i<modelItems_indicate.size();i++){
				TawStatisticIndex tawStatisticIndex=iTawStatisticIndexService.getIndicateById(modelItems_indicate.get(i).getItemId());
				tawStatisticIndex.setModelItemId(modelItems_indicate.get(i).getId());
				indexList.add(tawStatisticIndex);
			}
			
			for(int i=0;i<modelItems_demension.size();i++){
				TawStatisticParam tawStatisticParam=iTawStatisticParamService.getParamById(modelItems_demension.get(i).getItemId());
				tawStatisticParam.setModelItemId(modelItems_demension.get(i).getId());
				//tawStatisticParam.setChildrenList(transferMap(tawStatisticParam.getParamCode(),tawStatisticParam.getParamCodeType()));
				paramList.add(tawStatisticParam);
			}
		}
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("indexList", indexList);
		retMap.put("paramList", paramList);
		//retMap.put("compleList", compleList);
			
		mav.addObject("retJson", retMap); 
		return mav;
    }
	
	
	/**
	 * 
	 *字典值转码
	 */
	public List<ParamCondition> transferMap(String parentCodeId,String type){
		List<ParamCondition> children=new ArrayList<ParamCondition>();
		ParamCondition paramCondition=null;
		if("deptType".equals(type)){
			List<TawSystemDept> tempDeptList=iTawSystemDeptService.queryDeptByParentDeptID(parentCodeId);
			for(int i=0;i<tempDeptList.size();i++){
				paramCondition=new ParamCondition();
				TawSystemDept tempDept=tempDeptList.get(i);
				paramCondition.setParamCode(tempDept.getDeptid());
				paramCondition.setParamText(tempDept.getDeptname());
				children.add(paramCondition);
				
			}
		}else if("dictType".equals(type)){
			List<TawSystemDictType> tempDictTypeList=iTawSystemDictTypeService.queryDictTypesByParentId(parentCodeId);
			for(int i=0;i<tempDictTypeList.size();i++){
				paramCondition=new ParamCondition();
				TawSystemDictType tempDict=tempDictTypeList.get(i);
				
				paramCondition.setParamCode(tempDict.getDictid());
				paramCondition.setParamText(tempDict.getDictname());
				children.add(paramCondition);
			}
		}else if("areaType".equals(type)){
			List<TawSystemArea> tempAreaList=iTawSystemAreaService.queryAreasByParentId(parentCodeId);
			for(int i=0;i<tempAreaList.size();i++){
				paramCondition=new ParamCondition();
				TawSystemArea tempArea=tempAreaList.get(i);
				paramCondition.setParamCode(tempArea.getAreaid());
				paramCondition.setParamText(tempArea.getAreaname());
				children.add(paramCondition);
			}
		}else if("noneType".equals(type)){}
		
		return children;
	}
	
	
}
