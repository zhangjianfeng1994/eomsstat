package com.boco.eoms.baseUtil;

public class ColumnModel {
	
	private String columnName;
	private String columnCnName;
	private boolean showAttribute;
	
	
	
	
	public boolean isShowAttribute() {
		return showAttribute;
	}
	public void setShowAttribute(boolean showAttribute) {
		this.showAttribute = showAttribute;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnCnName() {
		return columnCnName;
	}
	public void setColumnCnName(String columnCnName) {
		this.columnCnName = columnCnName;
	}
	

}
