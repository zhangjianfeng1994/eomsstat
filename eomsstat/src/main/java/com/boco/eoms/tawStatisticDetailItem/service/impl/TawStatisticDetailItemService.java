package com.boco.eoms.tawStatisticDetailItem.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.eoms.baseUtil.UUIDGenerator;
import com.boco.eoms.tawStatisticDetailItem.dao.TawStatisticDetailItemMapper;
import com.boco.eoms.tawStatisticDetailItem.model.TawStatisticDetailItem;
import com.boco.eoms.tawStatisticDetailItem.service.ITawStatisticDetailItemService;

@Service
public class TawStatisticDetailItemService implements ITawStatisticDetailItemService{
	
	@Resource 
	private TawStatisticDetailItemMapper tawStatisticDetailItemMapper;
	
	public void saveOrUpdate(TawStatisticDetailItem tawStatisticDetailItem){
		if("".equals(tawStatisticDetailItem.getId())||null==tawStatisticDetailItem.getId()){
			tawStatisticDetailItem.setId(UUIDGenerator.generate());
			tawStatisticDetailItemMapper.insert(tawStatisticDetailItem);
		}else{
			tawStatisticDetailItemMapper.updateTawStatisticDetailItem(tawStatisticDetailItem);
		}
	}
	
	public List<TawStatisticDetailItem> getDetailItemByDetailId(String detailItemId){
		return tawStatisticDetailItemMapper.getDetailItemByDetailId(detailItemId);
	}

}
