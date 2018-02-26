package com.boco.eoms.tawStatisticDetail.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.tawStatisticDetailItem.model.TawStatisticDetailItem;

public class TawStatisticDetail {
    private String id;

    private BigDecimal detailNum;

    private String detailNumName;

    private String groupType;

    private String urlFlag;

    private String url;
    
    private List<TawStatisticDetailItem> tawStatisticDetailItems=new ArrayList<TawStatisticDetailItem>();
    
    
    

    public List<TawStatisticDetailItem> getTawStatisticDetailItems() {
		return tawStatisticDetailItems;
	}

	public void setTawStatisticDetailItems(
			List<TawStatisticDetailItem> tawStatisticDetailItems) {
		this.tawStatisticDetailItems = tawStatisticDetailItems;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getDetailNum() {
        return detailNum;
    }

    public void setDetailNum(BigDecimal detailNum) {
        this.detailNum = detailNum;
    }

    public String getDetailNumName() {
        return detailNumName;
    }

    public void setDetailNumName(String detailNumName) {
        this.detailNumName = detailNumName == null ? null : detailNumName.trim();
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType == null ? null : groupType.trim();
    }

    public String getUrlFlag() {
        return urlFlag;
    }

    public void setUrlFlag(String urlFlag) {
        this.urlFlag = urlFlag == null ? null : urlFlag.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}