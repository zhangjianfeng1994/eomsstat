package com.boco.eoms.tawSystemDictType.service;

import java.util.List;
import java.util.Map;


import com.boco.eoms.tawSystemDictType.model.TawSystemDictType;

public interface ITawSystemDictTypeService {
	public List<TawSystemDictType> queryAllDictType();
	public List<TawSystemDictType> queryDictTypesByParentId(String parentId);
	public List<TawSystemDictType>queryAllDictTypesByParentId(String parentId);
	
	public Map<String,String> queryAllDictTypesMap();
	public Map<String,String> queryDictTypesByParentIdMap(String parentId);
	public List<TawSystemDictType> queryDictTypeTreesByParentId(String parentId);
	
	public String querySQLDeptByParentDeptID(String parentId,StringBuffer tempSb);
	
	public TawSystemDictType queryDictTypeById(String dictId);
	
}
