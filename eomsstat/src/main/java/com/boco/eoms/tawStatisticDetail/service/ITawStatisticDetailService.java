package com.boco.eoms.tawStatisticDetail.service;

import java.util.List;

import com.boco.eoms.baseUtil.PageModel;
import com.boco.eoms.tawStatisticDetail.model.TawStatisticDetail;
import com.boco.eoms.tawStatisticDetailItem.model.TawStatisticDetailItem;


public interface ITawStatisticDetailService {
	
	public List<TawStatisticDetail> listDetailConditionByPage(
			PageModel pageModel, String detailNamequery,
			String groupTypequery, String detailValuequery) ;
	
	public String listDetailConditonCount(String detailNamequery,
			String groupTypequery, String detailValuequery);
	
	public void saveOrUpdate(TawStatisticDetail tawStatisticDetail, List<TawStatisticDetailItem> tawStatisticDetailItemList);
	
	public void removeDetail(String detailId);
	
	public TawStatisticDetail getTawStatisticDetailById(String detailId);
	
	public List<TawStatisticDetail> getTawStatisticDetailByGroupType(String groupType);
	public TawStatisticDetail getTawStatisticDetailByDetailNum(String detailNum);
}
