package com.boco.eoms.tawSystemSubRole.service;

import java.util.List;

import com.boco.eoms.tawSystemSubRole.model.TawSystemSubRole;

public interface ITawSystemSubRoleService {
	
	 TawSystemSubRole queryBySubRoleId(String roleId);
	 
	//查询所有子角色
    List<TawSystemSubRole> getAllSubRoles();
    
    //查询某地市下所有子角色
    List<TawSystemSubRole> getSubRolesByAreaId(String areaId);
}
