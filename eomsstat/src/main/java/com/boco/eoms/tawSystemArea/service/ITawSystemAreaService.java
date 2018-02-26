package com.boco.eoms.tawSystemArea.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.tawSystemArea.model.TawSystemArea;

public interface ITawSystemAreaService {
	public List<TawSystemArea> queryAllAreas();
	public List<TawSystemArea> queryAreasByParentId(String parentId);
	public Map<String,String> queryAllAreasMap();
	public Map<String,String> queryAreasByParentIdMap(String parentId);
	public List<Map<String,Object>> queryAreaTreesByParentId(String parentId);
	
	public TawSystemArea queryAreaById(String areaid);
	
	public List<TawSystemArea> listAllAreaByParentAreaID(String parentId);
}
