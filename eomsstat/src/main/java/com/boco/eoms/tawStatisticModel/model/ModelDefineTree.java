package com.boco.eoms.tawStatisticModel.model;

import java.util.List;

public class ModelDefineTree {
	
	private String id;
	
	private String pId;
	
	private String name;
	
	private List<ModelDefineTree> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ModelDefineTree> getChildren() {
		return children;
	}

	public void setChildren(List<ModelDefineTree> children) {
		this.children = children;
	}
	
	
	

}
