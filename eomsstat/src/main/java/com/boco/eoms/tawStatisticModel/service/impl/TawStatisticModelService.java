package com.boco.eoms.tawStatisticModel.service.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.eoms.baseUtil.BaseUtil;
import com.boco.eoms.baseUtil.PageModel;
import com.boco.eoms.baseUtil.TimeUtil;
import com.boco.eoms.baseUtil.UUIDGenerator;
import com.boco.eoms.tawStatisticModel.dao.TawStatisticModelMapper;
import com.boco.eoms.tawStatisticModel.model.ModelDefineTree;
import com.boco.eoms.tawStatisticModel.model.TawStatisticModel;
import com.boco.eoms.tawStatisticModel.service.ITawStatisticModelService;
import com.boco.eoms.tawStatisticModelItem.dao.TawStatisticModelItemMapper;
import com.boco.eoms.tawStatisticModelItem.model.TawStatisticModelItem;



@Service
public class TawStatisticModelService implements ITawStatisticModelService{
	
	@Resource
	private TawStatisticModelMapper tawStatisticModelMapper;
	@Resource
	private TawStatisticModelItemMapper tawStatisticModelItemMapper;
	
	public String listModelConditonCount(String indicateNamequery,String groupTypequery,String indicateValuequery,String userid,String isCommonFlag,String isShareFlag){
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("indicateNamequery", indicateNamequery);
		conditionMap.put("groupTypequery", groupTypequery);
		conditionMap.put("indicateValuequery", indicateValuequery);
		conditionMap.put("userid", userid);
		conditionMap.put("isCommonFlag", isCommonFlag);
		conditionMap.put("isShareFlag", isShareFlag);
		
		return tawStatisticModelMapper.listModelConditonAllCount(conditionMap);
	}
	
	public List<TawStatisticModel> listModelsConditionByPage(PageModel pageModel,String indicateNamequery,String groupTypequery,String indicateValuequery,String userid,String isCommonFlag,String isShareFlag){
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("indicateNamequery", indicateNamequery);
		conditionMap.put("groupTypequery", groupTypequery);
		conditionMap.put("indicateValuequery", indicateValuequery);
		conditionMap.put("userid", userid);
		conditionMap.put("isCommonFlag", isCommonFlag);
		conditionMap.put("isShareFlag", isShareFlag);
		
		conditionMap.put("endPageNum", pageModel.getEndPageNum());
		conditionMap.put("begainPageNum", pageModel.getBegainPageNum());
		return tawStatisticModelMapper.getModel(conditionMap);
	}
	
	
	public String listModelRepComditionCount(String share, String common) {
		// TODO Auto-generated method stub
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("share", share);
		conditionMap.put("isCommonFlag", common);
		return tawStatisticModelMapper.listModelRepComditionCount(conditionMap);
	}

	public List<TawStatisticModel> listModelRepComditionCountByPage(
			PageModel pageModel, String share, String common) {
		// TODO Auto-generated method stub
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("share", share);
		conditionMap.put("isCommonFlag", common);
		
		conditionMap.put("endPageNum", pageModel.getEndPageNum());
		conditionMap.put("begainPageNum", pageModel.getBegainPageNum());
		return tawStatisticModelMapper.getModelRepComditionCountByPage(conditionMap);
	}
	
	

	public TawStatisticModel getModelById(String id) {
		// TODO Auto-generated method stub
		return tawStatisticModelMapper.getModelById(id);
	}
	
	public void saveOrUpdateModel(TawStatisticModel tawStatisticModel) {
		// TODO Auto-generated method stub
		if(tawStatisticModel.getId()==null||"".equals(tawStatisticModel.getId())){
			tawStatisticModel.setId("D"+TimeUtil.getCurrentTimePure());
			tawStatisticModelMapper.insert(tawStatisticModel);
		}else{
			tawStatisticModelMapper.updateByModel(tawStatisticModel);
		}
	}
	
	public List<TawStatisticModel> getModelByTable(String table_Name){
		
		
		return tawStatisticModelMapper.getModelByTable(table_Name);
	}
	
	
	public void saveModel(ArrayList<ModelDefineTree> tree,TawStatisticModel tawStatisticModel){
		//保存model
		tawStatisticModelMapper.insert(tawStatisticModel);
		//保存model-item
		saveModelItem(tree,tawStatisticModel.getId(),"1","","1");
		
		
		
	}
	
	
	public void saveModelItem(List<ModelDefineTree> list,String modelId,String parentItemId,String itemCode,String parnetItemModelId){
			for(int i=0;i<list.size();i++){
				ModelDefineTree modelDefineTree=list.get(i);
				
				TawStatisticModelItem tawStatisticModelItem=new TawStatisticModelItem();
				tawStatisticModelItem.setId(UUIDGenerator.generate());
				tawStatisticModelItem.setItemId(modelDefineTree.getId());
				tawStatisticModelItem.setModelId(modelId);
				tawStatisticModelItem.setParentItemId(BaseUtil.nullObject2String(parentItemId));
				tawStatisticModelItem.setParentModelItemId(parnetItemModelId);
				
				if(BaseUtil.nullObject2String(modelDefineTree.getId()).indexOf("I")>=0){
					//维度类型判断
					tawStatisticModelItem.setType("1");
				}else if(BaseUtil.nullObject2String(modelDefineTree.getId()).indexOf("D")>=0){
					if(modelDefineTree.getChildren().size()==0){
						tawStatisticModelItem.setType("2");
					}else{
						if(true==getDemensionType(modelDefineTree.getChildren())){
							tawStatisticModelItem.setType("2");
						}else{
							tawStatisticModelItem.setType("1");
						}
					}
				}
				
				tawStatisticModelItem.setItemCode(itemCode+BaseUtil.transNumber(i+""));
				
				
				if(modelDefineTree.getChildren().size()==0){
					tawStatisticModelItem.setIsLeaf("1");
					
				}else{
					tawStatisticModelItem.setIsLeaf("0");
					saveModelItem(modelDefineTree.getChildren(),modelId,modelDefineTree.getId(),tawStatisticModelItem.getItemCode(),tawStatisticModelItem.getId());
				}
				tawStatisticModelItemMapper.insert(tawStatisticModelItem);
				
			}
	}
	
	/**
	 * 根据所有维度判断维度类型 
	 * 
	 */
	public boolean getDemensionType(List<ModelDefineTree> currentlist){
		
		boolean flag=true;
		
		for(int i=0;i<currentlist.size();i++){
			ModelDefineTree modelDefineTree=currentlist.get(i);
			if(modelDefineTree.getId().indexOf("I")>=0){
				//指标类型1
				flag=flag&&false;
			}else if(modelDefineTree.getId().indexOf("D")>=0){
				if(modelDefineTree.getChildren().size()>0){
					flag=flag&&getDemensionType(modelDefineTree.getChildren()); 
				}else{
					flag=flag&&true; 
				}
			}
		}
		
		return flag;
	}
	
	
	

	public void removeModel(String modelId) {
		// TODO Auto-generated method stub
		tawStatisticModelMapper.deleteModel(modelId);
	}

	
	
	
	
}