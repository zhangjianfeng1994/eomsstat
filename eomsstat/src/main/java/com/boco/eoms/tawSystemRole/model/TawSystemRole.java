package com.boco.eoms.tawSystemRole.model;

import java.math.BigDecimal;

public class TawSystemRole {
    private BigDecimal roleId;

    private BigDecimal deleted;

    private String deptId;

    private BigDecimal levelId;

    private BigDecimal limitCount;

    private String notes;

    private BigDecimal parentId;

    private String roleName;

    private BigDecimal roletypeId;

    private String singleId;

    private String structureFlag;

    private BigDecimal titleId;

    private BigDecimal workflowFlag;

    private BigDecimal leaf;

    private BigDecimal postid;

    public BigDecimal getRoleId() {
        return roleId;
    }

    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }

    public BigDecimal getDeleted() {
        return deleted;
    }

    public void setDeleted(BigDecimal deleted) {
        this.deleted = deleted;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public BigDecimal getLevelId() {
        return levelId;
    }

    public void setLevelId(BigDecimal levelId) {
        this.levelId = levelId;
    }

    public BigDecimal getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(BigDecimal limitCount) {
        this.limitCount = limitCount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public BigDecimal getParentId() {
        return parentId;
    }

    public void setParentId(BigDecimal parentId) {
        this.parentId = parentId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public BigDecimal getRoletypeId() {
        return roletypeId;
    }

    public void setRoletypeId(BigDecimal roletypeId) {
        this.roletypeId = roletypeId;
    }

    public String getSingleId() {
        return singleId;
    }

    public void setSingleId(String singleId) {
        this.singleId = singleId == null ? null : singleId.trim();
    }

    public String getStructureFlag() {
        return structureFlag;
    }

    public void setStructureFlag(String structureFlag) {
        this.structureFlag = structureFlag == null ? null : structureFlag.trim();
    }

    public BigDecimal getTitleId() {
        return titleId;
    }

    public void setTitleId(BigDecimal titleId) {
        this.titleId = titleId;
    }

    public BigDecimal getWorkflowFlag() {
        return workflowFlag;
    }

    public void setWorkflowFlag(BigDecimal workflowFlag) {
        this.workflowFlag = workflowFlag;
    }

    public BigDecimal getLeaf() {
        return leaf;
    }

    public void setLeaf(BigDecimal leaf) {
        this.leaf = leaf;
    }

    public BigDecimal getPostid() {
        return postid;
    }

    public void setPostid(BigDecimal postid) {
        this.postid = postid;
    }
}