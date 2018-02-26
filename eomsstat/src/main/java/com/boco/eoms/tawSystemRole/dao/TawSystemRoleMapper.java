package com.boco.eoms.tawSystemRole.dao;

import com.boco.eoms.tawSystemRole.model.TawSystemRole;

import java.math.BigDecimal;
import java.util.List;

public interface TawSystemRoleMapper {
    int deleteByPrimaryKey(BigDecimal roleId);

    int insert(TawSystemRole record);

    int insertSelective(TawSystemRole record);

    TawSystemRole selectByPrimaryKey(BigDecimal roleId);

    int updateByPrimaryKeySelective(TawSystemRole record);

    int updateByPrimaryKey(TawSystemRole record);
    
    List<TawSystemRole> listTawSystemRoleAll();
    
    List<TawSystemRole> listTawSystemRoleByParentRoleID(String parentRoleID);
    
    
}