package com.boco.eoms.tawStatisticIndex.model;


public class TawStatisticIndex {
    private String id;

    private String indicateName;

    private String indicateValue;
    
    private String indicateOverValue;
    
    private String percentSign;
    
    private String condition;

    private String complexFlag;

    private String groupType;

	private String indicatorFir_add="";
	
	private String operateFlag="";
	
	private String indicatorEnd_add="";
	
	private String modelItemId;
	
	private String deleteStatus;
	
	private String isGrasp;
	
	private String indicateDescribe;
	
	
	private String detailNum;
	
	private String isChecked;
	
	
	private String parentIndicateName;
	
	private String detailId;
	
	
	
	
	
	
	
	
	
	
	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getIndicateDescribe() {
		return indicateDescribe;
	}

	public void setIndicateDescribe(String indicateDescribe) {
		this.indicateDescribe = indicateDescribe;
	}

	

	public String getParentIndicateName() {
		return parentIndicateName;
	}

	public void setParentIndicateName(String parentIndicateName) {
		this.parentIndicateName = parentIndicateName;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	public String getDetailNum() {
		return detailNum;
	}

	public void setDetailNum(String detailNum) {
		this.detailNum = detailNum;
	}

	public String getIsGrasp() {
		return isGrasp;
	}

	public void setIsGrasp(String isGrasp) {
		this.isGrasp = isGrasp;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getModelItemId() {
		return modelItemId;
	}

	public void setModelItemId(String modelItemId) {
		this.modelItemId = modelItemId;
	}

	public String getIndicatorFir_add() {
		return indicatorFir_add;
	}

	public void setIndicatorFir_add(String indicatorFir_add) {
		this.indicatorFir_add = indicatorFir_add;
	}

	public String getOperateFlag() {
		return operateFlag;
	}

	public void setOperateFlag(String operateFlag) {
		this.operateFlag = operateFlag;
	}

	public String getIndicatorEnd_add() {
		return indicatorEnd_add;
	}

	public void setIndicatorEnd_add(String indicatorEnd_add) {
		this.indicatorEnd_add = indicatorEnd_add;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIndicateName() {
        return indicateName;
    }

    public void setIndicateName(String indicateName) {
        this.indicateName = indicateName == null ? null : indicateName.trim();
    }

    public String getIndicateValue() {
        return indicateValue;
    }

    public void setIndicateValue(String indicateValue) {
        this.indicateValue = indicateValue == null ? null : indicateValue.trim();
    }

    public String getComplexFlag() {
        return complexFlag;
    }

    public void setComplexFlag(String complexFlag) {
        this.complexFlag = complexFlag == null ? null : complexFlag.trim();
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition == null ? null : condition.trim();
    }


    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType == null ? null : groupType.trim();
    }


    public String getPercentSign() {
        return percentSign;
    }

    public void setPercentSign(String percentSign) {
        this.percentSign = percentSign == null ? null : percentSign.trim();
    }

    public String getIndicateOverValue() {
        return indicateOverValue;
    }

    public void setIndicateOverValue(String indicateOverValue) {
        this.indicateOverValue = indicateOverValue == null ? null : indicateOverValue.trim();
    }
}