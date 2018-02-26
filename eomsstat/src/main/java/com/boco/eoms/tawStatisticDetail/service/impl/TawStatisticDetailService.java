package com.boco.eoms.tawStatisticDetail.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.eoms.baseUtil.PageModel;
import com.boco.eoms.baseUtil.UUIDGenerator;
import com.boco.eoms.tawStatisticDetail.dao.TawStatisticDetailMapper;
import com.boco.eoms.tawStatisticDetail.model.TawStatisticDetail;
import com.boco.eoms.tawStatisticDetail.service.ITawStatisticDetailService;
import com.boco.eoms.tawStatisticDetailItem.dao.TawStatisticDetailItemMapper;
import com.boco.eoms.tawStatisticDetailItem.model.TawStatisticDetailItem;

@Service("iTawStatisticDetailService")
public class TawStatisticDetailService implements ITawStatisticDetailService{

	
	@Resource 
	private TawStatisticDetailMapper tawStatisticDetailMapper;
	@Resource
	private TawStatisticDetailItemMapper tawStatisticDetailItemMapper;
	
	public String listDetailConditonCount(String detailNamequery,
			String groupTypequery, String detailValuequery) {
		// TODO Auto-generated method stub
		
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("detailNamequery", detailNamequery);
		conditionMap.put("groupTypequery", groupTypequery);
		conditionMap.put("detailValuequery", detailValuequery);
		
		return tawStatisticDetailMapper.listDetailConditonAllCount(conditionMap);
	}

	public List<TawStatisticDetail> listDetailConditionByPage(
			PageModel pageModel, String detailNamequery,
			String groupTypequery, String detailValuequery) {
		// TODO Auto-generated method stub
		
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("detailNamequery", detailNamequery);
		conditionMap.put("groupTypequery", groupTypequery);
		conditionMap.put("detailValuequery", detailValuequery);
		
		conditionMap.put("endPageNum", pageModel.getEndPageNum());
		conditionMap.put("begainPageNum", pageModel.getBegainPageNum());
		
		return tawStatisticDetailMapper.listDetailConditionByPage(conditionMap);
	}
	
	public void saveOrUpdate(TawStatisticDetail tawStatisticDetail,List<TawStatisticDetailItem> tawStatisticDetailItemList){
		if("".equals(tawStatisticDetail.getId())||null==tawStatisticDetail.getId()){
			//鏂板璇﹀崟
			tawStatisticDetail.setId(UUIDGenerator.generate());
			tawStatisticDetailMapper.insert(tawStatisticDetail);
			//鏂板璇﹀崟椤�
			for(int i=0;i<tawStatisticDetailItemList.size();i++){
				TawStatisticDetailItem tawStatisticDetailItem=tawStatisticDetailItemList.get(i);
				tawStatisticDetailItem.setId(UUIDGenerator.generate());
				tawStatisticDetailItem.setDetailId(tawStatisticDetail.getId());
				tawStatisticDetailItemMapper.insert(tawStatisticDetailItem);
			}
		}else{
			tawStatisticDetailMapper.updateByDetail(tawStatisticDetail);
			tawStatisticDetailItemMapper.deleteDetailItemByItemid(tawStatisticDetail.getId());
			for(int i=0;i<tawStatisticDetailItemList.size();i++){
				TawStatisticDetailItem tawStatisticDetailItem=tawStatisticDetailItemList.get(i);
				tawStatisticDetailItem.setId(UUIDGenerator.generate());
				tawStatisticDetailItem.setDetailId(tawStatisticDetail.getId());
				tawStatisticDetailItemMapper.insert(tawStatisticDetailItem);
			}
			
		}
	}
	
	public void removeDetail(String detailId){
		
		tawStatisticDetailMapper.deleteDetail(detailId);
		
		tawStatisticDetailItemMapper.deleteDetailItemByItemid(detailId);
	}
	
	public TawStatisticDetail getTawStatisticDetailById(String detailId){
		
		TawStatisticDetail tempTawStatisticDetail=tawStatisticDetailMapper.getTawStatisticDetailById(detailId);
		if(tawStatisticDetailItemMapper.getDetailItemByDetailId(detailId).size()!=0){
			tempTawStatisticDetail.setTawStatisticDetailItems(tawStatisticDetailItemMapper.getDetailItemByDetailId(detailId));
		}
		
		return tempTawStatisticDetail;
	}
	
	public List<TawStatisticDetail> getTawStatisticDetailByGroupType(String groupType){
		return tawStatisticDetailMapper.getTawStatisticDetailByGroupType(groupType);
	}
	
	public TawStatisticDetail getTawStatisticDetailByDetailNum(String detailNum){
			
			
			return tawStatisticDetailMapper.getTawStatisticDetailByNum(detailNum);
		}

}
