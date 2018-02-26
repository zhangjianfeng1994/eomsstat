package com.boco.eoms.tawStatisticIndex.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.eoms.baseUtil.PageModel;
import com.boco.eoms.baseUtil.TimeUtil;
import com.boco.eoms.tawStatisticIndex.dao.TawStatisticIndexMapper;
import com.boco.eoms.tawStatisticIndex.model.TawStatisticIndex;
import com.boco.eoms.tawStatisticIndex.service.ITawStatisticIndexService;

@Service
public class TawStatisticIndexService implements ITawStatisticIndexService{

	@Resource
	private TawStatisticIndexMapper tawStatisticIndexMapper;
	
	

	public String listIndexConditonCount(String indicateNamequery,
			String groupTypequery, String indicateValuequery) {
		// TODO Auto-generated method stub
		
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("indicateNamequery", indicateNamequery);
		conditionMap.put("groupTypequery", groupTypequery);
		conditionMap.put("indicateValuequery", indicateValuequery);
		
		return tawStatisticIndexMapper.listIndexConditonAllCount(conditionMap);
	}

	public List<TawStatisticIndex> listIndexsConditionByPage(
			PageModel pageModel, String indicateNamequery,
			String groupTypequery, String indicateValuequery) {
		// TODO Auto-generated method stub
		
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("indicateNamequery", indicateNamequery);
		conditionMap.put("groupTypequery", groupTypequery);
		conditionMap.put("indicateValuequery", indicateValuequery);
		
		conditionMap.put("endPageNum", pageModel.getEndPageNum());
		conditionMap.put("begainPageNum", pageModel.getBegainPageNum());
		
		return tawStatisticIndexMapper.listIndexsConditionByPage(conditionMap);
	}

	public void saveOrUpdateIndex(TawStatisticIndex tawStatisticIndex) {
		// TODO Auto-generated method stub
		if(tawStatisticIndex.getId()==null||"".equals(tawStatisticIndex.getId())){
			tawStatisticIndex.setId("I"+TimeUtil.getCurrentTimePure());
			tawStatisticIndexMapper.insert(tawStatisticIndex);
		}else{
			tawStatisticIndexMapper.updateByIndex(tawStatisticIndex);
		}
	}

	public void removeIndex(String indicateId) {
		// TODO Auto-generated method stub
		tawStatisticIndexMapper.deleteIndex(indicateId);
		
	}

	public List<TawStatisticIndex> getIndexByTable(String tableName,String tableCNName) {
		// TODO Auto-generated method stub
		
		Map<String,String> conditionMap=new HashMap<String,String>();
		conditionMap.put("tableName", tableName);
		conditionMap.put("tableCNName", tableCNName);
		return tawStatisticIndexMapper.getIndexByTable(conditionMap);
	}
	
	public TawStatisticIndex getIndicateById(String id){
		return tawStatisticIndexMapper.getIndicateById(id);
	}
	

}
