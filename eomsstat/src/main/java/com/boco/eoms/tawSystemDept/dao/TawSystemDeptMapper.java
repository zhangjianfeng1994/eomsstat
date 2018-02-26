package com.boco.eoms.tawSystemDept.dao;

import com.boco.eoms.tawSystemDept.model.TawSystemDept;

import java.math.BigDecimal;
import java.util.List;

public interface TawSystemDeptMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(TawSystemDept record);

    int insertSelective(TawSystemDept record);

    TawSystemDept selectByPrimaryKey(BigDecimal id);
    
    TawSystemDept selectByDeptid(String deptid);

    int updateByPrimaryKeySelective(TawSystemDept record);

    int updateByPrimaryKey(TawSystemDept record);
    
    List<TawSystemDept> listTawSystemDeptAll();
    
    List<TawSystemDept> listDeptByParentDeptID(String parentId);
    
    List<TawSystemDept> listAllDeptByParentDeptID(String parentId);
    
    
}