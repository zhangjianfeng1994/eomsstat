package com.boco.eoms.tawSystemDictType.dao;

import com.boco.eoms.tawSystemDictType.model.TawSystemDictType;

import java.math.BigDecimal;
import java.util.List;

public interface TawSystemDictTypeMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(TawSystemDictType record);

    int insertSelective(TawSystemDictType record);

    TawSystemDictType selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(TawSystemDictType record);

    int updateByPrimaryKey(TawSystemDictType record);
    
    List<TawSystemDictType> listTawSystemDictTypeAll();
    
    List<TawSystemDictType> listAreaByParentDictTypeID(String parentId);
    
    List<TawSystemDictType> listAllDictByParentDictID(String parentId);
    
    TawSystemDictType listByDictId(String dictid);
}