package com.boco.eoms.tawStatisticParam.service;

import java.util.List;

import com.boco.eoms.baseUtil.PageModel;
import com.boco.eoms.tawStatisticParam.model.TawStatisticParam;

public interface ITawStatisticParamService {
	public String listParamConditonCount(String indicateNamequery,String groupTypequery,String indicateValuequery);
	public List<TawStatisticParam> listParamsConditionByPage(PageModel pageModel,String indicateNamequery,String groupTypequery,String indicateValuequery);
	public void saveOrUpdateParam(TawStatisticParam tawStatisticParam);
	public void removeParam(String paramId);
	public List<TawStatisticParam> getParamByTable(String tableName,String tableCNName);
	public  TawStatisticParam getParamById(String id);
}
