package com.boco.eoms.tawStatisticModel.model;

import java.util.List;

import com.boco.eoms.tawStatisticDetail.model.TawStatisticDetail;

public class TawStatisticModel {
    private String id;

    private String modelName;

    private String cycle;

    private String isShareFlag;

    private String isCommonFlag;
    
    private String groupType;
    
    private String detailId;
    
    private String computeRule;
    
    private String userid;
    
    private String modelType;
    
    private String xmlName;
    
    private String chooseBefore;
    
    
    
    
    
    public String getChooseBefore() {
		return chooseBefore;
	}

	public void setChooseBefore(String chooseBefore) {
		this.chooseBefore = chooseBefore;
	}

	public String getXmlName() {
		return xmlName;
	}

	public void setXmlName(String xmlName) {
		this.xmlName = xmlName;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	private List<TawStatisticDetail> tawStatisticDetailList;
    
    
    
    
    
 

	public String getComputeRule() {
		return computeRule;
	}

	public void setComputeRule(String computeRule) {
		this.computeRule = computeRule;
	}

	public List<TawStatisticDetail> getTawStatisticDetailList() {
		return tawStatisticDetailList;
	}

	public void setTawStatisticDetailList(
			List<TawStatisticDetail> tawStatisticDetailList) {
		this.tawStatisticDetailList = tawStatisticDetailList;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle == null ? null : cycle.trim();
    }

    public String getIsShareFlag() {
        return isShareFlag;
    }

    public void setIsShareFlag(String isShareFlag) {
        this.isShareFlag = isShareFlag == null ? null : isShareFlag.trim();
    }

    public String getIsCommonFlag() {
        return isCommonFlag;
    }

    public void setIsCommonFlag(String isCommonFlag) {
        this.isCommonFlag = isCommonFlag == null ? null : isCommonFlag.trim();
    }
}