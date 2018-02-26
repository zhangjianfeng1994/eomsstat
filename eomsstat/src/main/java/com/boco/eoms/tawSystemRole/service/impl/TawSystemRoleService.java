package com.boco.eoms.tawSystemRole.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.boco.eoms.tawSystemRole.dao.TawSystemRoleMapper;
import com.boco.eoms.tawSystemRole.model.TawSystemRole;
import com.boco.eoms.tawSystemRole.service.ITawSystemRoleService;

@Service("iTawSystemRoleService")
public class TawSystemRoleService implements ITawSystemRoleService{
	
	@Resource
	private TawSystemRoleMapper tawSystemRoleMapper;
	public List<TawSystemRole> queryAllRoles() {
		// TODO Auto-generated method stub
		return tawSystemRoleMapper.listTawSystemRoleAll();
	}

	public List<TawSystemRole> queryRolesByParentId(String parentId) {
		// TODO Auto-generated method stub
		return tawSystemRoleMapper.listTawSystemRoleByParentRoleID(parentId);
	}
	public Map<String,String> queryAllRolesMap(){
		List<TawSystemRole> tawSystemRoleList=tawSystemRoleMapper.listTawSystemRoleAll();
		Map<String,String> queryAllRoleMap=new HashMap<String,String>();
		for(int i=0;i<tawSystemRoleList.size();i++){
			TawSystemRole tempTawSystemRole=tawSystemRoleList.get(i);
			queryAllRoleMap.put(tempTawSystemRole.getRoleId().toString(),tempTawSystemRole.getRoleName());
		}
		return queryAllRoleMap;
	}
	
	public Map<String,String> queryRolesByParentIdMap(String parentId){
		List<TawSystemRole> tawSystemRoleList=tawSystemRoleMapper.listTawSystemRoleByParentRoleID(parentId);
		Map<String,String> queryAllRoleMap=new HashMap<String,String>();
		for(int i=0;i<tawSystemRoleList.size();i++){
			TawSystemRole tempTawSystemRole=tawSystemRoleList.get(i);
			queryAllRoleMap.put(tempTawSystemRole.getRoleId().toString(),tempTawSystemRole.getRoleName());
		}
		return queryAllRoleMap;
	}

	public List<Map<String,Object>> queryRoleTreesByParentId(String parentId) {
		List<Map<String,Object>> treeList=new ArrayList<Map<String,Object>>();
		Map<String,Object> treeMap=null;
		List<TawSystemRole> tempRoleList=tawSystemRoleMapper.listTawSystemRoleByParentRoleID(parentId);
		
		for(int i=0;i<tempRoleList.size();i++){
			treeMap=new HashMap<String,Object>();
			TawSystemRole tawSystemRole=(TawSystemRole)tempRoleList.get(i);
			treeMap.put("id", tawSystemRole.getRoleId());
			treeMap.put("text", tawSystemRole.getRoleName());

			if(!"1".equals(tawSystemRole.getLeaf())){
			  treeMap.put("state","closed");
			  treeMap.put("children", queryRoleTreesByParentId(String.valueOf(tawSystemRole.getRoleId())));
			}
			treeList.add(treeMap);
		}
		
		return treeList;
	}
}
