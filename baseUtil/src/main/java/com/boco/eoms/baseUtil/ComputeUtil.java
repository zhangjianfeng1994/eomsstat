package com.boco.eoms.baseUtil;

import java.text.DecimalFormat;

public class ComputeUtil {
	
	private final static  String PERCENT_FLAF="0";
	
	public static String simpleCompute(String fir,String end,String operation,String percentflag){
		
		String ret="0";
		if(fir!=null&&end!=null){
			if("+".equals(operation)){
				if(PERCENT_FLAF.equals(percentflag)){
					ret=getPoint2NoPer(String.valueOf(Double.parseDouble(fir)+Double.parseDouble(end)));
				}else{
					ret=getPoint2Per(String.valueOf(Double.parseDouble(fir)+Double.parseDouble(end)));
				}
			}else if("-".equals(operation)){
				if(PERCENT_FLAF.equals(percentflag)){
					ret=getPoint2NoPer(String.valueOf(Double.parseDouble(fir)-Double.parseDouble(end)));
				}else{
					ret=getPoint2Per(String.valueOf(Double.parseDouble(fir)-Double.parseDouble(end)));
				}
			}else if("*".equals(operation)){
				if(PERCENT_FLAF.equals(percentflag)){
					ret=getPoint2NoPer(String.valueOf(Double.parseDouble(fir)*Double.parseDouble(end)));
				}else{
					ret=getPoint2Per(String.valueOf(Double.parseDouble(fir)*Double.parseDouble(end)));
				}
			}else if("/".equals(operation)){
				if(PERCENT_FLAF.equals(percentflag)){
					ret=getPoint2NoPer(String.valueOf(Double.parseDouble(fir)/Double.parseDouble(end)));
				}else{
					ret=getPoint2Per(String.valueOf(Double.parseDouble(fir)/Double.parseDouble(end)));
				}
			}
		}
		return ret;
	}
	
	  public static String getPoint2Per(String dataString){
		   if("NaN".equals(dataString)){
			   dataString="0";
		   }
		   
		   Double d=Double.parseDouble(dataString)*100;
		   DecimalFormat df=new DecimalFormat("##0.00");
		   
		   return df.format(d)+"%";
		   
		   
	   }
	   
	   public static String getPoint2NoPer(String dataString){
		   if("NaN".equals(dataString)){
			   dataString="0";
		   }
		   
		   Double d=Double.parseDouble(dataString);
		   DecimalFormat df=new DecimalFormat("##0.00");
		   
		   return df.format(d);
		   
		   
	   }
	   

}
