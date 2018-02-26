package com.boco.eoms.tawStatisticDetailItem.service;

import java.util.List;

import com.boco.eoms.tawStatisticDetailItem.model.TawStatisticDetailItem;

public interface ITawStatisticDetailItemService {
	
	
	public void saveOrUpdate(TawStatisticDetailItem tawStatisticDetailItem);
	
	public List<TawStatisticDetailItem> getDetailItemByDetailId(String detailItemId);
}
