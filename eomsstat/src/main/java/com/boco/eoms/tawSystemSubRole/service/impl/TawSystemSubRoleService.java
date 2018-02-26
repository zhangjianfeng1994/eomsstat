package com.boco.eoms.tawSystemSubRole.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.eoms.tawSystemSubRole.dao.TawSystemSubRoleMapper;
import com.boco.eoms.tawSystemSubRole.model.TawSystemSubRole;
import com.boco.eoms.tawSystemSubRole.service.ITawSystemSubRoleService;


@Service("iTawSystemSubRoleService")
public class TawSystemSubRoleService implements ITawSystemSubRoleService{
	@Resource
	private TawSystemSubRoleMapper tawSystemSubRoleMapper;
	
	 public TawSystemSubRole queryBySubRoleId(String roleId){
		 
		 return tawSystemSubRoleMapper.queryBySubRoleId(roleId);
	 }

	public List<TawSystemSubRole> getAllSubRoles() {
		return tawSystemSubRoleMapper.getAllSubRoles();
	}

	public List<TawSystemSubRole> getSubRolesByAreaId(String areaId) {
		return tawSystemSubRoleMapper.getSubRolesByAreaId(areaId);
	}
}
