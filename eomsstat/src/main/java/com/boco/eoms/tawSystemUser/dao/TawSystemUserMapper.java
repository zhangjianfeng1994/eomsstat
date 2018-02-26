package com.boco.eoms.tawSystemUser.dao;

import java.util.List;

import com.boco.eoms.tawSystemUser.model.TawSystemUser;

public interface TawSystemUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(TawSystemUser record);

    int insertSelective(TawSystemUser record);

    TawSystemUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TawSystemUser record);

    int updateByPrimaryKey(TawSystemUser record);
    
    TawSystemUser getUserByUserid(String userid);
    
    List<TawSystemUser> getAllUsers();
    
    List<TawSystemUser> getUsersByDeptId(String deptId);
}