package com.boco.eoms.tawSystemArea.model;

import java.math.BigDecimal;
import java.util.List;

public class TawSystemArea {
    private BigDecimal id;

    private String areaid;

    private String areaname;

    private BigDecimal leaf;

    private String remark;

    private String parentareaid;

    private BigDecimal ordercode;

    private String areacode;

    private String capital;
    
    private List<TawSystemArea> childrenArea;
    
    

    public List<TawSystemArea> getChildrenArea() {
		return childrenArea;
	}

	public void setChildrenArea(List<TawSystemArea> childrenArea) {
		this.childrenArea = childrenArea;
	}

	public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public BigDecimal getLeaf() {
        return leaf;
    }

    public void setLeaf(BigDecimal leaf) {
        this.leaf = leaf;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getParentareaid() {
        return parentareaid;
    }

    public void setParentareaid(String parentareaid) {
        this.parentareaid = parentareaid == null ? null : parentareaid.trim();
    }

    public BigDecimal getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(BigDecimal ordercode) {
        this.ordercode = ordercode;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital == null ? null : capital.trim();
    }
}