package com.boco.eoms.tawStatisticModel.model;


public class TreeNode {  
	private String id;
	private String pId;
	private String name;
	private String isParent;
	public TreeNode(String id, String pId, String name, String isParent) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.isParent=isParent;
	}
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
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	
    
}
