package com.boco.eoms.tawSystemDept.service;


import java.util.List;
import java.util.Map;






import com.boco.eoms.tawSystemDept.model.TawSystemDept;

public interface ITawSystemDeptService {
	public List<TawSystemDept> queryTawSystemDeptAll();
	public List<TawSystemDept> queryDeptByParentDeptID(String parentId);
	
	public List<TawSystemDept> queryAllDeptByParentDeptID(String parentId);
	public String querySQLDeptByParentDeptID(String parentId,StringBuffer tempSb);
	
	public Map<String,String> queryAllDeptsMap();
	public Map<String,String> queryDeptsByParentIdMap(String parentId);
	public List<Map<String,Object>> queryDeptTreesByParentId(String parentId);
	public TawSystemDept queryById(String deptid);
}
