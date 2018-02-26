package com.boco.eoms.tawSystemSubRole.dao;

import java.util.List;

import com.boco.eoms.tawSystemSubRole.model.TawSystemSubRole;

public interface TawSystemSubRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(TawSystemSubRole record);

    int insertSelective(TawSystemSubRole record);

    TawSystemSubRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TawSystemSubRole record);

    int updateByPrimaryKey(TawSystemSubRole record);
    
    TawSystemSubRole queryBySubRoleId(String roleId);
    
    //查询所有子角色
    List<TawSystemSubRole> getAllSubRoles();
    
    //查询某地市下所有子角色
    List<TawSystemSubRole> getSubRolesByAreaId(String areaId);
}