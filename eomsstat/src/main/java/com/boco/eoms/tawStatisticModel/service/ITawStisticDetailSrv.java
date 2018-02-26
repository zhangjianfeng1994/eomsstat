package com.boco.eoms.tawStatisticModel.service;

import java.text.ParseException;

public interface ITawStisticDetailSrv {
	
	public String getDetailPageXml(String modelId_v,String detailpage_v,String time_v,String where_bufer,String reserve) throws ParseException;
}
