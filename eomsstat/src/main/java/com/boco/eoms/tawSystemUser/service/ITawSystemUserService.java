package com.boco.eoms.tawSystemUser.service;

import java.util.List;

import com.boco.eoms.tawSystemUser.model.TawSystemUser;

public interface ITawSystemUserService {

	
	public TawSystemUser getUserByuserid(String userid);
	
	//查询所有用户
	public List<TawSystemUser> getAllUsers();
	
	//查询某部门下所有用户
	public List<TawSystemUser> getUsersByDeptId(String deptId);
}
