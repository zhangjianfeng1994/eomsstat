package com.boco.eoms.tawStatisticParam.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.eoms.baseUtil.PageModel;
import com.boco.eoms.baseUtil.TimeUtil;
import com.boco.eoms.tawStatisticParam.dao.TawStatisticParamMapper;
import com.boco.eoms.tawStatisticParam.model.TawStatisticParam;
import com.boco.eoms.tawStatisticParam.service.ITawStatisticParamService;

@Service
public class TawStatisticParamServiceImpl implements ITawStatisticParamService{
	
	@Resource
	private TawStatisticParamMapper tawStatisticParamMapper;
	
	

	public String listParamConditonCount(String indicateNamequery,
			String groupTypequery, String indicateValuequery) {
		// TODO Auto-generated method stub
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("indicateNamequery", indicateNamequery);
		conditionMap.put("groupTypequery", groupTypequery);
		conditionMap.put("indicateValuequery", indicateValuequery);

		
		return tawStatisticParamMapper.listParamConditionAllCount(conditionMap);
	}

	public List<TawStatisticParam> listParamsConditionByPage(PageModel pageModel, String indicateNamequery,
			String groupTypequery, String indicateValuequery) {
		// TODO Auto-generated method stub
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("indicateNamequery", indicateNamequery);
		conditionMap.put("groupTypequery", groupTypequery);
		conditionMap.put("indicateValuequery", indicateValuequery);
		
		conditionMap.put("endPageNum", pageModel.getEndPageNum());
		conditionMap.put("begainPageNum", pageModel.getBegainPageNum());

		
		return tawStatisticParamMapper.listParamsConditionByPage(conditionMap);
	}
	public void removeParam(String paramId){
		tawStatisticParamMapper.deleteParam(paramId);
		
	}
	
	public void saveOrUpdateParam(TawStatisticParam tawStatisticParam) {
		// TODO Auto-generated method stub
		if(tawStatisticParam.getId()==null||"".equals(tawStatisticParam.getId())){
			tawStatisticParam.setId("D"+TimeUtil.getCurrentTimePure());
			tawStatisticParamMapper.insert(tawStatisticParam);
		}else{
			tawStatisticParamMapper.updateByParam(tawStatisticParam);
		}
	}

	public List<TawStatisticParam> getParamByTable(String tableName,String tableCNName) {
		// TODO Auto-generated method stub
		
		Map<String,String> conditionMap=new HashMap<String,String>();
		conditionMap.put("tableName", tableName);
		conditionMap.put("tableCNName", tableCNName);
		
		return tawStatisticParamMapper.getParamByTable(conditionMap);
	}

	
	public  TawStatisticParam getParamById(String id){
		
		
		return tawStatisticParamMapper.getParamById(id);
	}
	
	

}
