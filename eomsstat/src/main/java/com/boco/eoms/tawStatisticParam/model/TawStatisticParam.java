package com.boco.eoms.tawStatisticParam.model;



import java.util.List;

public class TawStatisticParam {


	private String id;

    private String paramName;

    private String paramEng;

    private String paramCode;

    private String paramCodeType;

    private String parentParamId;
    
    private String paramCondition;
    
    private String groupType;
    
    private String modelItemId;
    
	private List<ParamCondition> childrenList;
	
	private TawStatisticParam childrenParam;
	
	private String deleteStatus;
	
	
	private String eomsParamType;
	
	
	
	
    
    




	public String getEomsParamType() {
		return eomsParamType;
	}

	public void setEomsParamType(String eomsParamType) {
		this.eomsParamType = eomsParamType;
	}

	public TawStatisticParam getChildrenParam() {
		return childrenParam;
	}

	public void setChildrenParam(TawStatisticParam childrenParam) {
		this.childrenParam = childrenParam;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public List<ParamCondition> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<ParamCondition> childrenList) {
		this.childrenList = childrenList;
	}

	public String getModelItemId() {
		return modelItemId;
	}

	public void setModelItemId(String modelItemId) {
		this.modelItemId = modelItemId;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getParamCondition() {
		return paramCondition;
	}

	public void setParamCondition(String paramCondition) {
		this.paramCondition = paramCondition;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    public String getParamEng() {
        return paramEng;
    }

    public void setParamEng(String paramEng) {
        this.paramEng = paramEng == null ? null : paramEng.trim();
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    public String getParamCodeType() {
        return paramCodeType;
    }

    public void setParamCodeType(String paramCodeType) {
        this.paramCodeType = paramCodeType == null ? null : paramCodeType.trim();
    }

    public String getParentParamId() {
        return parentParamId;
    }

    public void setParentParamId(String parentParamId) {
        this.parentParamId = parentParamId == null ? null : parentParamId.trim();
    }
}