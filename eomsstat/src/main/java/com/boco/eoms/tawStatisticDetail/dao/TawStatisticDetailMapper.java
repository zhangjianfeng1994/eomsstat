package com.boco.eoms.tawStatisticDetail.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.tawStatisticDetail.model.TawStatisticDetail;


public interface TawStatisticDetailMapper {
    int insert(TawStatisticDetail record);

    int insertSelective(TawStatisticDetail record);
    
    String listDetailConditonAllCount(Map<String,Object> conditionMap);
    
	List<TawStatisticDetail> listDetailConditionByPage(Map<String,Object> conditionMap);
	
	void updateByDetail(TawStatisticDetail record);
	
	void deleteDetail(String detailId);
	
	TawStatisticDetail getTawStatisticDetailById(String detailId);
	
	List<TawStatisticDetail> getTawStatisticDetailByGroupType(String groupType);
	
	TawStatisticDetail getTawStatisticDetailByNum(String detailNum);
	
}