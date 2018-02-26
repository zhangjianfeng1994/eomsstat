package com.boco.eoms.tawStatisticModelItem.model;

public class TawStatisticModelItem {
    private String id;

    private String modelId;

    private String parentItemId;

    private String type;

    private String itemId;
    
    private String isLeaf;
    
    private String itemCode;
    
    private String parentModelItemId;
    
    
    
    
    
    

    public String getParentModelItemId() {
		return parentModelItemId;
	}

	public void setParentModelItemId(String parentModelItemId) {
		this.parentModelItemId = parentModelItemId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId == null ? null : modelId.trim();
    }

    public String getParentItemId() {
        return parentItemId;
    }

    public void setParentItemId(String parentItemId) {
        this.parentItemId = parentItemId == null ? null : parentItemId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }
}