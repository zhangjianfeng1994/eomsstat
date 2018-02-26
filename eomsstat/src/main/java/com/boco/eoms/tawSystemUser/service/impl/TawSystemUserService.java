package com.boco.eoms.tawSystemUser.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;




import com.boco.eoms.tawSystemUser.dao.TawSystemUserMapper;
import com.boco.eoms.tawSystemUser.model.TawSystemUser;
import com.boco.eoms.tawSystemUser.service.ITawSystemUserService;


@Service("iTawSystemUserService")
public class TawSystemUserService implements ITawSystemUserService{
	
	
	@Resource
	private TawSystemUserMapper tawSystemUserMapper;
	
	
	public TawSystemUser getUserByuserid(String userid){
		
		return tawSystemUserMapper.getUserByUserid(userid);
	}


	public List<TawSystemUser> getAllUsers() {
		return tawSystemUserMapper.getAllUsers();
	}


	public List<TawSystemUser> getUsersByDeptId(String deptId) {
		return tawSystemUserMapper.getUsersByDeptId(deptId);
	}

}
