package com.boco.eoms.tawSystemArea.dao;

import com.boco.eoms.tawSystemArea.model.TawSystemArea;
import com.boco.eoms.tawSystemDictType.model.TawSystemDictType;

import java.math.BigDecimal;
import java.util.List;


public interface TawSystemAreaMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(TawSystemArea record);

    int insertSelective(TawSystemArea record);

    TawSystemArea selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(TawSystemArea record);

    int updateByPrimaryKey(TawSystemArea record);
    
    List<TawSystemArea> listAllArea();
    
    List<TawSystemArea> listAreaByParentId(String parentId);
    
    TawSystemArea queryByAreaId(String areaid);
    
    List<TawSystemArea> listAllAreaByParentAreaID(String parentId);
}