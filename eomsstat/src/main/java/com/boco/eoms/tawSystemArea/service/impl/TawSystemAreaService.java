package com.boco.eoms.tawSystemArea.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



import com.boco.eoms.tawSystemArea.dao.TawSystemAreaMapper;
import com.boco.eoms.tawSystemArea.model.TawSystemArea;
import com.boco.eoms.tawSystemArea.service.ITawSystemAreaService;



@Service("iTawSystemAreaService")
public class TawSystemAreaService implements ITawSystemAreaService{
	
	@Resource
	private TawSystemAreaMapper tawSystemAreaMapper;
	
	public TawSystemAreaMapper getTawSystemAreaMapper() {
		return tawSystemAreaMapper;
	}

	public void setTawSystemAreaMapper(TawSystemAreaMapper tawSystemAreaMapper) {
		this.tawSystemAreaMapper = tawSystemAreaMapper;
	}
	

	public List<TawSystemArea> queryAllAreas() {
		// TODO Auto-generated method stub
		return tawSystemAreaMapper.listAllArea();
	}

	public List<TawSystemArea> queryAreasByParentId(String parentId) {
		// TODO Auto-generated method stub
		return tawSystemAreaMapper.listAreaByParentId(parentId);
	}
	
	
	

	public Map<String,String> queryAllAreasMap(){
		List<TawSystemArea> tawSystemAreaList=tawSystemAreaMapper.listAllArea();
		Map<String,String> queryAllAreasMap=new HashMap<String,String>();
		for(int i=0;i<tawSystemAreaList.size();i++){
			TawSystemArea tempTawSystemArea= tawSystemAreaList.get(i);
			queryAllAreasMap.put(tempTawSystemArea.getAreaid(), tempTawSystemArea.getAreaname());
		}
		return queryAllAreasMap;
	};
	
	public Map<String,String> queryAreasByParentIdMap(String parentId){
		List<TawSystemArea> tawSystemAreaList=tawSystemAreaMapper.listAreaByParentId(parentId);
		Map<String,String> queryAllAreasMap=new HashMap<String,String>();
		for(int i=0;i<tawSystemAreaList.size();i++){
			TawSystemArea tempTawSystemArea= tawSystemAreaList.get(i);
			queryAllAreasMap.put(tempTawSystemArea.getAreaid(), tempTawSystemArea.getAreaname());
		}
		return queryAllAreasMap;
	}
	
	public List<Map<String,Object>> queryAreaTreesByParentId(String parentId){
		List<Map<String,Object>> treeList=new ArrayList<Map<String,Object>>();
		Map<String,Object> treeMap=null;
		List<TawSystemArea> tempAreaList=tawSystemAreaMapper.listAreaByParentId(parentId);
		
		for(int i=0;i<tempAreaList.size();i++){
			treeMap=new HashMap<String,Object>();
			TawSystemArea tawSystemArea=(TawSystemArea)tempAreaList.get(i);
			treeMap.put("id", tawSystemArea.getAreaid());
			treeMap.put("text", tawSystemArea.getAreaname());

			if(!"1".equals(tawSystemArea.getLeaf())){
			  treeMap.put("state","closed");
			  treeMap.put("children", queryAreaTreesByParentId(tawSystemArea.getAreaid()));
			}
			treeList.add(treeMap);
		}
		
		return treeList;
	}
	
public TawSystemArea queryAreaById(String areaid){
		
		return tawSystemAreaMapper.queryByAreaId(areaid);
	}

public List<TawSystemArea> listAllAreaByParentAreaID(String parentId) {
	return tawSystemAreaMapper.listAllAreaByParentAreaID(parentId);
}
	

}
