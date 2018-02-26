package com.boco.eoms.tawStatisticModel.service;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.baseUtil.PageModel;
import com.boco.eoms.tawStatisticModel.model.ModelDefineTree;
import com.boco.eoms.tawStatisticModel.model.TawStatisticModel;



public interface ITawStatisticModelService {
	
	public TawStatisticModel getModelById(String id);
	public void saveOrUpdateModel(TawStatisticModel tawStatisticModel);
	public String listModelConditonCount(String indicateNamequery,String groupTypequery,String indicateValuequery,String userid,String isCommonFlag,String isShareFlag);
	public String listModelRepComditionCount(String share,String common);
	public List<TawStatisticModel> listModelsConditionByPage(PageModel pageModel,String indicateNamequery,String groupTypequery,String indicateValuequery,String userid,String isCommonFlag,String isShareFlag);
	public List<TawStatisticModel> listModelRepComditionCountByPage(PageModel pageModel,String share,String common);
	public List<TawStatisticModel> getModelByTable(String table_Name);
	public void saveModel(ArrayList<ModelDefineTree> tree,TawStatisticModel tawStatisticModel);
	
	public void removeModel(String modelId);
	
	
}