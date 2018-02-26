package com.boco.eoms.tawStatisticDetailItem.dao;

import java.util.List;

import com.boco.eoms.tawStatisticDetailItem.model.TawStatisticDetailItem;

public interface TawStatisticDetailItemMapper {
    int insert(TawStatisticDetailItem record);

    int insertSelective(TawStatisticDetailItem record);
    
    void updateTawStatisticDetailItem(TawStatisticDetailItem record);
    
    void deleteDetailItem(String detailItemId);
    
    void deleteDetailItemByItemid(String detailItemId);
    
    List<TawStatisticDetailItem> getDetailItemByDetailId(String detailItemId);
}