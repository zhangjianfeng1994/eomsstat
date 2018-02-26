package com.boco.eoms.tawStatisticModel.model;

public class ComplexIndicate {
	
	
	private String fir_indicate_no;
	
	private String end_indicate_no;
	
	private String operate_indicate;
	
	

	
	public ComplexIndicate(String fir_indicate_no,String end_indicate_no,String operate_indicate){
		super();
		this.end_indicate_no=end_indicate_no;
		this.fir_indicate_no=fir_indicate_no;
		this.operate_indicate=operate_indicate;
		
	}
	

	public String getFir_indicate_no() {
		return fir_indicate_no;
	}

	public void setFir_indicate_no(String fir_indicate_no) {
		this.fir_indicate_no = fir_indicate_no;
	}

	public String getEnd_indicate_no() {
		return end_indicate_no;
	}

	public void setEnd_indicate_no(String end_indicate_no) {
		this.end_indicate_no = end_indicate_no;
	}

	public String getOperate_indicate() {
		return operate_indicate;
	}

	public void setOperate_indicate(String operate_indicate) {
		this.operate_indicate = operate_indicate;
	}
	
	

}
