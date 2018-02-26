package com.boco.eoms.tawStatisticIndex.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.tawStatisticIndex.model.TawStatisticIndex;

public interface TawStatisticIndexMapper {
	
    int insert(TawStatisticIndex record);
    
    void deleteIndex(String id);
    
    int insertSelective(TawStatisticIndex record);
    
    String listIndexConditonAllCount(Map<String,Object> conditionMap);
    
	List<TawStatisticIndex> listIndexsConditionByPage(Map<String,Object> conditionMap);
	
	void updateByIndex(TawStatisticIndex tawStatisticIndex);
	
	TawStatisticIndex getIndicateById(String id);
	
	List<TawStatisticIndex> getIndexByTable(Map<String,String> conditionMap);
	
	
}