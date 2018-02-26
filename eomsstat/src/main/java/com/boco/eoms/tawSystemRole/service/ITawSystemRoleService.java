package com.boco.eoms.tawSystemRole.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.tawSystemRole.model.TawSystemRole;

public interface ITawSystemRoleService {
	public List<TawSystemRole> queryAllRoles();
	public List<TawSystemRole> queryRolesByParentId(String parentId);
	public Map<String,String> queryAllRolesMap();
	public Map<String,String> queryRolesByParentIdMap(String parentId);
	public List<Map<String,Object>> queryRoleTreesByParentId(String parentId);
}
