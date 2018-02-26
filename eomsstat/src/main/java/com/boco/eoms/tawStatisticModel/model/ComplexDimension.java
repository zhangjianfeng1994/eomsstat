package com.boco.eoms.tawStatisticModel.model;

import java.util.ArrayList;
import java.util.List;

public class ComplexDimension {
	
	private String name;
	private String condition;
	private String columnName;
	private String totalLayer;
	private String currentLayer;
	private String rowSpan;
	private String cloumnSpan;
	private String indexId;
	

	
	
	
	
	
	public String getIndexId() {
		return indexId;
	}
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
	public String getTotalLayer() {
		return totalLayer;
	}
	public void setTotalLayer(String totalLayer) {
		this.totalLayer = totalLayer;
	}
	public String getCurrentLayer() {
		return currentLayer;
	}
	public void setCurrentLayer(String currentLayer) {
		this.currentLayer = currentLayer;
	}
	public String getRowSpan() {
		return rowSpan;
	}
	public void setRowSpan(String rowSpan) {
		this.rowSpan = rowSpan;
	}
	public String getCloumnSpan() {
		return cloumnSpan;
	}
	public void setCloumnSpan(String cloumnSpan) {
		this.cloumnSpan = cloumnSpan;
	}
	
	
	
	private List<ComplexDimension> childDimension=new ArrayList<ComplexDimension>();
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public List<ComplexDimension> getChildDimension() {
		return childDimension;
	}
	public void setChildDimension(List<ComplexDimension> childDimension) {
		this.childDimension = childDimension;
	}
	
	
	
	

}
