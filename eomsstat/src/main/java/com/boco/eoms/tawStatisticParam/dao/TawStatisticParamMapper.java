package com.boco.eoms.tawStatisticParam.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.tawStatisticParam.model.TawStatisticParam;

public interface TawStatisticParamMapper {
    int insert(TawStatisticParam record);

    int insertSelective(TawStatisticParam record);
    String listParamConditionAllCount(Map<String,Object> conditionMap);
    
	List<TawStatisticParam> listParamsConditionByPage(Map<String,Object> conditionMap);
	
	void deleteParam(String id);
	
	TawStatisticParam getParamById(String id);
	void updateByParam(TawStatisticParam tawStatisticParam);
	
	List<TawStatisticParam> getParamByTable(Map<String,String> conditionMap);
}