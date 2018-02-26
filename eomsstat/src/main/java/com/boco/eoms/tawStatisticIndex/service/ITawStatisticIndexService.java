package com.boco.eoms.tawStatisticIndex.service;

import java.util.List;

import com.boco.eoms.baseUtil.PageModel;
import com.boco.eoms.tawStatisticIndex.model.TawStatisticIndex;

public interface ITawStatisticIndexService {
	public String listIndexConditonCount(String indicateNamequery,String groupTypequery,String indicateValuequery);
	public List<TawStatisticIndex> listIndexsConditionByPage(PageModel pageModel,String indicateNamequery,String groupTypequery,String indicateValuequery);
	public void saveOrUpdateIndex(TawStatisticIndex tawStatisticIndex);
	public void removeIndex(String indicateId);
	public List<TawStatisticIndex> getIndexByTable(String tableName,String tableCNName);
	public TawStatisticIndex getIndicateById(String id);
	
	
}
