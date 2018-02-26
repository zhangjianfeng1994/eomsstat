package com.boco.eoms.tawStatisticModelItem.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.eoms.tawStatisticModelItem.dao.TawStatisticModelItemMapper;
import com.boco.eoms.tawStatisticModelItem.model.TawStatisticModelItem;
import com.boco.eoms.tawStatisticModelItem.service.ITawStatisticModelItemService;

@Service
public class TawStatisticModelItemServiceImpl implements ITawStatisticModelItemService{
	
	
	@Resource
	private TawStatisticModelItemMapper tawStatisticModelItemMapper;
	
	public List<TawStatisticModelItem> getModelItemByModelId(String ModelId){
		
		return tawStatisticModelItemMapper.getModelItemByModelId(ModelId);
	}

	public List<TawStatisticModelItem>  getModelItemByModelIdAndType(String model_id,String type){
		Map<String,String> conditionMap=new HashMap<String,String>();
		
		conditionMap.put("model_id", model_id);
		conditionMap.put("type", type);
		return tawStatisticModelItemMapper.getModelItemByModelIdAndType(conditionMap);
	}
	
	public List<TawStatisticModelItem> getModelItemDemensionRootByModelIdAndType(String modelId,String type){
		Map<String,String> conditionMap=new HashMap<String,String>();
		
		conditionMap.put("model_id", modelId);
		conditionMap.put("type", type);
		
		return tawStatisticModelItemMapper.getModelItemDemensionRootByModelIdAndType(conditionMap);
	}
	
	public List<TawStatisticModelItem> getModelItemByParentId(
			String parentItemId) {
		// TODO Auto-generated method stub
		return tawStatisticModelItemMapper.listByParentId(parentItemId);
	}
	
	public TawStatisticModelItem getModelItemByparentModelItemIdAndType(String parentModelItemId,String type){
		Map<String,String> conditionMap=new HashMap<String,String>();
		
		conditionMap.put("parentModelItemId", parentModelItemId);
		conditionMap.put("type", type);
		
		if(tawStatisticModelItemMapper.listByparentModelItemIdAndType(conditionMap).size()==0){
			return null;
		}else{
			return tawStatisticModelItemMapper.listByparentModelItemIdAndType(conditionMap).get(0);
		}
	}
	
	
	public int getLengthOfModelTree(String model_id){
		
		return tawStatisticModelItemMapper.getLengthOfModelTree(model_id)/2;
	}
	
	public List<TawStatisticModelItem> getModelItemByparentModelItemId(String parentModelItemId){
		return tawStatisticModelItemMapper.listByparentModelItemId(parentModelItemId);
	}
	
	public List<TawStatisticModelItem> getModelItemByModelParam(String modelId){
		return tawStatisticModelItemMapper.getModelItemByModelParam(modelId);
	}

	public List<TawStatisticModelItem> getModelItemByModelIndicator(String modelId){
		return tawStatisticModelItemMapper.getModelItemByModelIndicator(modelId);
	}
	
	public TawStatisticModelItem getModelItemById(String id){
		
		return tawStatisticModelItemMapper.getModelItemById(id);
	}
	
	public List<TawStatisticModelItem> getModelItemByModelComplex(String modelId){
		return tawStatisticModelItemMapper.getModelItemByModelComplex(modelId);
	}
}
