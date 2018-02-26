package com.boco.eoms.tawSystemDept.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;





import com.boco.eoms.tawSystemDept.dao.TawSystemDeptMapper;
import com.boco.eoms.tawSystemDept.model.TawSystemDept;
import com.boco.eoms.tawSystemDept.service.ITawSystemDeptService;

@Service("iTawSystemDeptService")
public class TawSystemDeptService implements ITawSystemDeptService{
	
	@Resource
	private TawSystemDeptMapper tawSystemDeptMapper;

	public List<TawSystemDept> queryTawSystemDeptAll() {
		// TODO Auto-generated method stub
		return tawSystemDeptMapper.listTawSystemDeptAll();
	}


	public List<TawSystemDept> queryDeptByParentDeptID(String parentId) {
		// TODO Auto-generated method stub
		return tawSystemDeptMapper.listDeptByParentDeptID(parentId);
	}
	
	public List<TawSystemDept> queryAllDeptByParentDeptID(String parentId){
		
		return tawSystemDeptMapper.listAllDeptByParentDeptID(parentId);
	}
	
	
	public String querySQLDeptByParentDeptID(String parentId,StringBuffer tempSb){
		List<TawSystemDept> temp=tawSystemDeptMapper.listAllDeptByParentDeptID(parentId);
		for(int i=0;i<temp.size();i++){
			TawSystemDept tawSystemDept=temp.get(i);
			if(tempSb.length()==0){
				tempSb.append("'"+tawSystemDept.getDeptid()+"'");
			}else{
				tempSb.append(",'"+tawSystemDept.getDeptid()+"'");
			}
		}
		return tempSb.toString();
	}
	
	public Map<String,String> queryAllDeptsMap(){
		List<TawSystemDept> tawSystemDeptList=tawSystemDeptMapper.listTawSystemDeptAll();
		Map<String,String> queryAllDeptMap=new HashMap<String,String>();
		for(int i=0;i<tawSystemDeptList.size();i++){
			TawSystemDept tempTawSystemDept=tawSystemDeptList.get(i);
			queryAllDeptMap.put(tempTawSystemDept.getDeptid().toString(), tempTawSystemDept.getDeptname());
		}
		return queryAllDeptMap;
	}
	
	public Map<String,String> queryDeptsByParentIdMap(String parentId){
		List<TawSystemDept> tawSystemDeptList=tawSystemDeptMapper.listDeptByParentDeptID(parentId);
		Map<String,String> queryAllDeptMap=new HashMap<String,String>();
		for(int i=0;i<tawSystemDeptList.size();i++){
			TawSystemDept tempTawSystemDept=tawSystemDeptList.get(i);
			queryAllDeptMap.put(tempTawSystemDept.getDeptid(), tempTawSystemDept.getDeptname());
		}
		return queryAllDeptMap;
	}
	
	public List<Map<String,Object>> queryDeptTreesByParentId(String parentId){
		List<Map<String,Object>> treeList=new ArrayList<Map<String,Object>>();
		Map<String,Object> treeMap=null;
		List<TawSystemDept> tempDeptList=tawSystemDeptMapper.listDeptByParentDeptID(parentId);
		
		for(int i=0;i<tempDeptList.size();i++){
			treeMap=new HashMap<String,Object>();
			TawSystemDept tawSystemDept=(TawSystemDept)tempDeptList.get(i);
			treeMap.put("id", tawSystemDept.getAreaid());
			treeMap.put("text", tawSystemDept.getDeptname());
			
			if(!"1".equals(tawSystemDept.getLeaf())){
			  treeMap.put("state","closed");
			  treeMap.put("children", queryDeptTreesByParentId(tawSystemDept.getAreaid()));
			}
			treeList.add(treeMap);
		}
		
		return treeList;
	}
	
	public TawSystemDept queryById(String deptid){
		
		return tawSystemDeptMapper.selectByDeptid(deptid);
	}
}
