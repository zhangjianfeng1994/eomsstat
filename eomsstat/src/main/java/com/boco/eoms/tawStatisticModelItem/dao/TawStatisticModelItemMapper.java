package com.boco.eoms.tawStatisticModelItem.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.tawStatisticModelItem.model.TawStatisticModelItem;

public interface TawStatisticModelItemMapper {
    int insert(TawStatisticModelItem record);

    int insertSelective(TawStatisticModelItem record);
    
	List<TawStatisticModelItem> getModelItemByModelId(String modelId);
	
	List<TawStatisticModelItem> getModelItemByModelIdAndType(Map<String,String> conditionMap);
	
	List<TawStatisticModelItem> getModelItemDemensionRootByModelIdAndType(Map<String,String> conditionMap);
	
	List<TawStatisticModelItem> listByParentId(String parentItemId);
	
	List<TawStatisticModelItem> listByparentModelItemId(String parentModelItemId);
	
	List<TawStatisticModelItem> listByparentModelItemIdAndType(Map<String,String> conditionMap);
	
	List<TawStatisticModelItem> getModelItemByModelParam(String modelId);
	
	List<TawStatisticModelItem> getModelItemByModelIndicator(String modelId);
	
	List<TawStatisticModelItem> getModelItemByModelComplex(String modelId);
	
	TawStatisticModelItem getModelItemById(String id);
	
	int getLengthOfModelTree(String model_id);
}