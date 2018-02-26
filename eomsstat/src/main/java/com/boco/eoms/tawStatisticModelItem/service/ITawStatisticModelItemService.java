package com.boco.eoms.tawStatisticModelItem.service;

import java.util.List;

import com.boco.eoms.tawStatisticModelItem.model.TawStatisticModelItem;

public interface ITawStatisticModelItemService {
	
	public TawStatisticModelItem getModelItemById(String id);
	
	public List<TawStatisticModelItem> getModelItemByModelId(String ModelId);
	
	public List<TawStatisticModelItem> getModelItemByModelIdAndType(String model_id,String type);
	
	public List<TawStatisticModelItem> getModelItemDemensionRootByModelIdAndType(String modelId,String type);
	
	public List<TawStatisticModelItem> getModelItemByParentId(String parentItemId);
	
	public List<TawStatisticModelItem> getModelItemByparentModelItemId(String parentModelItemId);

	public TawStatisticModelItem getModelItemByparentModelItemIdAndType(String parentModelItemId,String type);
	
	public List<TawStatisticModelItem> getModelItemByModelParam(String modelId);
	
	public List<TawStatisticModelItem> getModelItemByModelIndicator(String modelId);
	
	public List<TawStatisticModelItem> getModelItemByModelComplex(String modelId);
	
	public int getLengthOfModelTree(String model_id);

}
