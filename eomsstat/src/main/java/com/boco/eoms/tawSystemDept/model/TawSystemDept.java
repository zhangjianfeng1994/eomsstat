package com.boco.eoms.tawSystemDept.model;

import java.math.BigDecimal;
import java.util.List;

public class TawSystemDept {
    private BigDecimal id;

    private String deleted;

    private String deptemail;

    private String deptfax;

    private String deptid;

    private String deptmanager;

    private String deptmobile;

    private String deptname;

    private String deptphone;

    private String depttype;

    private String operremoteip;

    private String opertime;

    private String operuserid;

    private BigDecimal ordercode;

    private String parentdeptid;

    private BigDecimal regionflag;

    private String remark;

    private String tmporarybegintime;

    private String tmporarymanager;

    private String tmporarystoptime;

    private String updatetime;

    private String leaf;

    private String areaid;

    private String linkid;

    private String parentlinkid;

    private String tmpdeptid;

    private String ispartners;

    private String isdaiweiroot;
    
    private List<TawSystemDept> childrenDept;
    
    
    

    public List<TawSystemDept> getChildrenDept() {
		return childrenDept;
	}

	public void setChildrenDept(List<TawSystemDept> childrenDept) {
		this.childrenDept = childrenDept;
	}

	public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

    public String getDeptemail() {
        return deptemail;
    }

    public void setDeptemail(String deptemail) {
        this.deptemail = deptemail == null ? null : deptemail.trim();
    }

    public String getDeptfax() {
        return deptfax;
    }

    public void setDeptfax(String deptfax) {
        this.deptfax = deptfax == null ? null : deptfax.trim();
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid == null ? null : deptid.trim();
    }

    public String getDeptmanager() {
        return deptmanager;
    }

    public void setDeptmanager(String deptmanager) {
        this.deptmanager = deptmanager == null ? null : deptmanager.trim();
    }

    public String getDeptmobile() {
        return deptmobile;
    }

    public void setDeptmobile(String deptmobile) {
        this.deptmobile = deptmobile == null ? null : deptmobile.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public String getDeptphone() {
        return deptphone;
    }

    public void setDeptphone(String deptphone) {
        this.deptphone = deptphone == null ? null : deptphone.trim();
    }

    public String getDepttype() {
        return depttype;
    }

    public void setDepttype(String depttype) {
        this.depttype = depttype == null ? null : depttype.trim();
    }

    public String getOperremoteip() {
        return operremoteip;
    }

    public void setOperremoteip(String operremoteip) {
        this.operremoteip = operremoteip == null ? null : operremoteip.trim();
    }

    public String getOpertime() {
        return opertime;
    }

    public void setOpertime(String opertime) {
        this.opertime = opertime == null ? null : opertime.trim();
    }

    public String getOperuserid() {
        return operuserid;
    }

    public void setOperuserid(String operuserid) {
        this.operuserid = operuserid == null ? null : operuserid.trim();
    }

    public BigDecimal getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(BigDecimal ordercode) {
        this.ordercode = ordercode;
    }

    public String getParentdeptid() {
        return parentdeptid;
    }

    public void setParentdeptid(String parentdeptid) {
        this.parentdeptid = parentdeptid == null ? null : parentdeptid.trim();
    }

    public BigDecimal getRegionflag() {
        return regionflag;
    }

    public void setRegionflag(BigDecimal regionflag) {
        this.regionflag = regionflag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getTmporarybegintime() {
        return tmporarybegintime;
    }

    public void setTmporarybegintime(String tmporarybegintime) {
        this.tmporarybegintime = tmporarybegintime == null ? null : tmporarybegintime.trim();
    }

    public String getTmporarymanager() {
        return tmporarymanager;
    }

    public void setTmporarymanager(String tmporarymanager) {
        this.tmporarymanager = tmporarymanager == null ? null : tmporarymanager.trim();
    }

    public String getTmporarystoptime() {
        return tmporarystoptime;
    }

    public void setTmporarystoptime(String tmporarystoptime) {
        this.tmporarystoptime = tmporarystoptime == null ? null : tmporarystoptime.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf == null ? null : leaf.trim();
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getLinkid() {
        return linkid;
    }

    public void setLinkid(String linkid) {
        this.linkid = linkid == null ? null : linkid.trim();
    }

    public String getParentlinkid() {
        return parentlinkid;
    }

    public void setParentlinkid(String parentlinkid) {
        this.parentlinkid = parentlinkid == null ? null : parentlinkid.trim();
    }

    public String getTmpdeptid() {
        return tmpdeptid;
    }

    public void setTmpdeptid(String tmpdeptid) {
        this.tmpdeptid = tmpdeptid == null ? null : tmpdeptid.trim();
    }

    public String getIspartners() {
        return ispartners;
    }

    public void setIspartners(String ispartners) {
        this.ispartners = ispartners == null ? null : ispartners.trim();
    }

    public String getIsdaiweiroot() {
        return isdaiweiroot;
    }

    public void setIsdaiweiroot(String isdaiweiroot) {
        this.isdaiweiroot = isdaiweiroot == null ? null : isdaiweiroot.trim();
    }
}