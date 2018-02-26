package com.boco.eoms.tawSystemDictType.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;








import com.boco.eoms.tawSystemDept.model.TawSystemDept;
import com.boco.eoms.tawSystemDictType.dao.TawSystemDictTypeMapper;
import com.boco.eoms.tawSystemDictType.model.TawSystemDictType;
import com.boco.eoms.tawSystemDictType.service.ITawSystemDictTypeService;

@Service("iTawSystemDictTypeService")
public class TawSystemDictTypeService implements ITawSystemDictTypeService{
	
	@Resource
	private TawSystemDictTypeMapper tawSystemDictTypeMapper;


	public List<TawSystemDictType> queryAllDictType() {
		// TODO Auto-generated method stub
		return tawSystemDictTypeMapper.listTawSystemDictTypeAll();
	}


	public List<TawSystemDictType> queryDictTypesByParentId(String parentId) {
		// TODO Auto-generated method stub
		return tawSystemDictTypeMapper.listAreaByParentDictTypeID(parentId);
	}

	public Map<String,String> queryAllDictTypesMap(){
		List<TawSystemDictType> tawSystemDictTypeList=tawSystemDictTypeMapper.listTawSystemDictTypeAll();
		Map<String,String> queryAllDictTypeMap=new HashMap<String,String>();
		for(int i=0;i<tawSystemDictTypeList.size();i++){
			TawSystemDictType tempTawSystemDictType= tawSystemDictTypeList.get(i);
			queryAllDictTypeMap.put(tempTawSystemDictType.getDictid(),tempTawSystemDictType.getDictname());
		}
		return queryAllDictTypeMap;
	}
	public Map<String,String> queryDictTypesByParentIdMap(String parentId){
		List<TawSystemDictType> tawSystemDictTypeList=tawSystemDictTypeMapper.listAreaByParentDictTypeID(parentId);
		Map<String,String> queryAllDictTypeMap=new HashMap<String,String>();
		for(int i=0;i<tawSystemDictTypeList.size();i++){
			TawSystemDictType tempTawSystemDictType= tawSystemDictTypeList.get(i);
			queryAllDictTypeMap.put(tempTawSystemDictType.getDictid(),tempTawSystemDictType.getDictname());
		}
		return queryAllDictTypeMap;
		
		
	}


	public List queryDictTypeTreesByParentId(String parentId) {
		List treeList=new ArrayList();
		Map treeMap=null;
		List tempDictTypeList=tawSystemDictTypeMapper.listAreaByParentDictTypeID(parentId);
		
		for(int i=0;i<tempDictTypeList.size();i++){
			treeMap=new HashMap();
			TawSystemDictType tawSystemDictType=(TawSystemDictType)tempDictTypeList.get(i);
			treeMap.put("id", tawSystemDictType.getDictid());
			treeMap.put("text", tawSystemDictType.getDictname());

			if(!"1".equals(tawSystemDictType.getLeaf())){
			  treeMap.put("state","closed");
			  treeMap.put("children", queryDictTypeTreesByParentId(tawSystemDictType.getDictid()));
			}
			treeList.add(treeMap);
		}
		
		return treeList;
	}
	
	public List<TawSystemDictType>queryAllDictTypesByParentId(String parentId){
		return tawSystemDictTypeMapper.listAllDictByParentDictID(parentId);
		
	}
	
	public String querySQLDeptByParentDeptID(String parentId,StringBuffer tempSb){
		List<TawSystemDictType> temp=tawSystemDictTypeMapper.listAllDictByParentDictID(parentId);

		for(int i=0;i<temp.size();i++){
			TawSystemDictType tawSystemDictType=temp.get(i);
			if(tempSb.length()==0){
				tempSb.append("'"+tawSystemDictType.getDictid()+"'");
			}else{
				tempSb.append(",'"+tawSystemDictType.getDictid()+"'");
			}
		}
		return tempSb.toString();
		
	}
	
	public TawSystemDictType queryDictTypeById(String dictId){
		return tawSystemDictTypeMapper.listByDictId(dictId);
	}
	
}
