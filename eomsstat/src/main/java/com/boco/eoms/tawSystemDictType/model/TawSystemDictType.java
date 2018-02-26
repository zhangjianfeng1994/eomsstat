package com.boco.eoms.tawSystemDictType.model;

import java.math.BigDecimal;
import java.util.List;

public class TawSystemDictType {
    private BigDecimal id;

    private String dictname;

    private BigDecimal moduleid;

    private String modulename;

    private String dictremark;

    private String dictid;

    private String parentdictid;

    private BigDecimal systype;

    private String dictcode;

    private String leaf;
    
    private List<TawSystemDictType> childrenDictType;
    
    

    public List<TawSystemDictType> getChildrenDictType() {
		return childrenDictType;
	}

	public void setChildrenDictType(List<TawSystemDictType> childrenDictType) {
		this.childrenDictType = childrenDictType;
	}

	public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDictname() {
        return dictname;
    }

    public void setDictname(String dictname) {
        this.dictname = dictname == null ? null : dictname.trim();
    }

    public BigDecimal getModuleid() {
        return moduleid;
    }

    public void setModuleid(BigDecimal moduleid) {
        this.moduleid = moduleid;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
    }

    public String getDictremark() {
        return dictremark;
    }

    public void setDictremark(String dictremark) {
        this.dictremark = dictremark == null ? null : dictremark.trim();
    }

    public String getDictid() {
        return dictid;
    }

    public void setDictid(String dictid) {
        this.dictid = dictid == null ? null : dictid.trim();
    }

    public String getParentdictid() {
        return parentdictid;
    }

    public void setParentdictid(String parentdictid) {
        this.parentdictid = parentdictid == null ? null : parentdictid.trim();
    }

    public BigDecimal getSystype() {
        return systype;
    }

    public void setSystype(BigDecimal systype) {
        this.systype = systype;
    }

    public String getDictcode() {
        return dictcode;
    }

    public void setDictcode(String dictcode) {
        this.dictcode = dictcode == null ? null : dictcode.trim();
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf == null ? null : leaf.trim();
    }
}