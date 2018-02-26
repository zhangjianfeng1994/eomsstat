package com.boco.eoms.tawStatisticModel.dao;

import java.util.List;
import java.util.Map;


import com.boco.eoms.tawStatisticModel.model.TawStatisticModel;

public interface TawStatisticModelMapper {
    int insert(TawStatisticModel record);

    int insertSelective(TawStatisticModel record);
    
    TawStatisticModel getModelById(String id);
    
    String listModelConditonAllCount(Map<String,Object> conditionMap);
    
    String listModelRepComditionCount(Map<String,Object> conditionMap);
    
    List<TawStatisticModel> getModel(Map<String,Object> conditionMap);
    
    List<TawStatisticModel> getModelRepComditionCountByPage(Map<String,Object> conditionMap);
    
    List<TawStatisticModel> getModelByTable(String table_Name);
    
    void deleteModel(String modelId);
    
	void updateByModel(TawStatisticModel tawStatisticModel);
}