package com.boco.eoms.tawStatisticModel.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.boco.eoms.baseUtil.BaseUtil;
import com.boco.eoms.baseUtil.PageModel;





public class CoreStatisticEngine {
	/**
	 * create by lijin 2015-01-06 
	 * param indexs,dimensions,queryCondition
	 */
	
	private static final Logger logger = Logger.getLogger(CoreStatisticEngine.class);
	
	public static Logger getLogger() {
		return logger;
	}
	
	public String getStatisticContent(String [] indexs,String[] dimensions,String queryCondition){
		
		//step1: generate sql
		//String genenrateSQL=generateStatisticSQL(indexs,dimensions,queryCondition);
		//System.out.println("==>>statisticSQL:"+genenrateSQL);
		
		//step2: excute sql ,then get the result tranform to jsonString 
		//List retList=excuteSQL(genenrateSQL);
		

		return "";
	}
	
	/**
	 * 说明：根据指标和维度组合成sql其中维度呈现作为显示字段
	 * 统计sql类型1
	 * 产生sql类型1:select deptId,count(deptid),count(sex),count(deleted) from taw_system_user where sex='1' group by deptid,sex,deleted 
	 **/
	public String generateStatisticSQL(String [] indexs,String[] dimensions,String queryCondition,String tableName){
		//step1:generate sql and get the statistic content
		StringBuffer statisticSql=new StringBuffer("select ");

		//step2:use indexs to combine the sql 
		if(indexs.length==0){
			statisticSql.append("* ");
		}else{
			for(int i=0;i<dimensions.length;i++){
				if(i==0){
					statisticSql.append(dimensions[i]);
				}else{
					statisticSql.append(","+dimensions[i]);
				}
			}
			
			for(int i=0;i<indexs.length;i++){
				statisticSql.append(","+indexs[i]);
			}
		}
		//step3:use table to combine the sql 
		if(tableName.length()==0){
			statisticSql.append(" from dual");
		}else{
			statisticSql.append(" from "+tableName);
		}
		//step4:use queryCondition to combine the sql 
		if(queryCondition.length()==0){
			statisticSql.append(" where 1=1 and ");
		}else{
			statisticSql.append(" where 1=1 and "+queryCondition);
		}
		//step5:use dimensions to combine the sql 
		if(dimensions.length>0){
			for(int i=0;i<dimensions.length;i++){
				if(i==0){
					statisticSql.append("group by "+dimensions[i]);
				}else{
					statisticSql.append(","+dimensions[i]);
				}
			}
			
		}
		return statisticSql.toString();
	}
	
	
	/**
	 * 说明：HN部门统计
	 * 统计sql类型2
	 * 产生sql类型2:
	 * select '-1' dept,count(deptid) from taw_system_user where sex='1' 
	 * 将sql再根据维度进行组合
	 **/
	public String generateSoleStatisticSQL(String[] index,String dimensions,String dimensionsValue,String queryCondition,String tableName,String whereStr){
		//step1:generate sql and get the statistic content
		StringBuffer statisticSql=new StringBuffer("");
		statisticSql.append("select '"+dimensionsValue+"' as "+dimensions);
		for(int i=0;i<index.length;i++){
			if(null!=index[i]){
				statisticSql.append(","+index[i]);
			}
		}
		statisticSql.append(" from "+tableName+" where 1=1 "+queryCondition+whereStr);
		return statisticSql.toString();
	}
	
	
	/**
	 * 说明：根据指标和维度组合成sql，其中显示的指标具有相同的维度。
	 * 统计sql类型2
	 * 产生sql类型2:
	 * select count(deptid) from taw_system_user where sex='1' group by deptid,sex
	 * select count(sex) from taw_system_user where sex='1' group by deptid,sex
	 * select count(deleted) from taw_system_user where sex='1' group by deptid,sex
	 * 将sql再根据维度进行组合
	 **/
	public String generateSingleStatisticSQL(String index,String[] dimensions,String queryCondition,String tableName,String whereStr,String paramEngStr,String direction){
		//step1:generate sql and get the statistic content
		StringBuffer statisticSql=new StringBuffer("select ");

		//step2:use indexs to combine the sql 
		for(int i=0;i<dimensions.length;i++){
			if(i==0){
				statisticSql.append(dimensions[i]);
			}else{
				statisticSql.append(","+dimensions[i]);
			}
		}
		if("".equals(index)){
			statisticSql.append(",* ");
		}else{
			statisticSql.append(","+index);
		}
		//step3:use table to combine the sql 
		if("".equals(tableName)){
			statisticSql.append(" from dual");
		}else{
			statisticSql.append(" from "+tableName);
		}
		//step4:use queryCondition to combine the sql 
		if("".equals(queryCondition)){
			statisticSql.append(" where 1=1 ");
		}else{
			statisticSql.append(" where 1=1 "+queryCondition);
		}
		//where String
		if(!"".equals(whereStr)){
			statisticSql.append(whereStr);//select MAINFAULTGENERANTCITY,count(*) from ccmf_statistic_table where 1=1 and sendTime>=to_date('2016-1-17 00:00:00','yyyy-MM-dd hh24:mi:ss') and sendTime<=to_date('2016-1-18 00:00:00','yyyy-MM-dd hh24:mi:ss')
		}
		
		
//		for(int i=0;i<dimensions.length;i++){
//			statisticSql.append(" and "+dimensions[i]+" is not null ");//select MAINFAULTGENERANTCITY,count(*) from ccmf_statistic_table where 1=1 and sendTime>=to_date('2016-1-17 00:00:00','yyyy-MM-dd hh24:mi:ss') and sendTime<=to_date('2016-1-18 00:00:00','yyyy-MM-dd hh24:mi:ss') and MAINFAULTGENERANTCITY is not null 
//		}
		
		//step5:use dimensions to combine the sql 
		if(dimensions.length==1){
			statisticSql.append(" group by rollup("+dimensions[0]+")");
			//select MAINFAULTGENERANTCITY,count(*) from ccmf_statistic_table where 1=1 and sendTime>=to_date('2016-1-17 00:00:00','yyyy-MM-dd hh24:mi:ss') and sendTime<=to_date('2016-1-18 00:00:00','yyyy-MM-dd hh24:mi:ss') and MAINFAULTGENERANTCITY is not null  group by MAINFAULTGENERANTCITY
			//statisticSql.append(" group by "+dimensions[0]+"");
		}else if(dimensions.length>1){
			for(int i=0;i<dimensions.length;i++){
				if(i==0){
					//statisticSql.append(" group by "+dimensions[i]);
					statisticSql.append(" group by rollup(("+dimensions[i]);
				}else{
					if(dimensions.length==(i+1)){
						//statisticSql.append(","+dimensions[i]+"");
						statisticSql.append(","+dimensions[i]+"))");
					}else{
						statisticSql.append(","+dimensions[i]);
					}
				}
			}
		}
		//statisticSql.append(" order by "+index+"desc ");
		if("down".equals(direction) && !"".equals(paramEngStr)){
			statisticSql.append(" order by "+paramEngStr+" desc ");
			for(int i=0;i<dimensions.length;i++){
				if(!dimensions[i].equals(paramEngStr)){
					statisticSql.append(","+dimensions[i]+" desc");
				}
			}
			statisticSql.append(","+index+" desc ");
		}else if("up".equals(direction) && !"".equals(paramEngStr)){
			statisticSql.append(" order by "+paramEngStr+" asc ");
			for(int i=0;i<dimensions.length;i++){
				if(!dimensions[i].equals(paramEngStr)){
					statisticSql.append(","+dimensions[i]+" asc");
				}
			}
		}
		
		
		//step5:use dimensions to orderby the sql
//		if(dimensions.length==1){
//			//statisticSql.append(" group by rollup("+dimensions[0]+")");
//			statisticSql.append(" order by "+dimensions[0]+"");//select MAINFAULTGENERANTCITY,count(*) from ccmf_statistic_table where 1=1 and sendTime>=to_date('2016-1-17 00:00:00','yyyy-MM-dd hh24:mi:ss') and sendTime<=to_date('2016-1-18 00:00:00','yyyy-MM-dd hh24:mi:ss') and MAINFAULTGENERANTCITY is not null  group by MAINFAULTGENERANTCITY order by MAINFAULTGENERANTCITY
//		}else if(dimensions.length>1){
//			for(int i=0;i<dimensions.length;i++){
//				if(i==0){
//					statisticSql.append(" order by "+dimensions[i]);
//					//statisticSql.append(" group by rollup(("+dimensions[i]);
//				}else{
//					if(dimensions.length==(i+1)){
//						statisticSql.append(","+dimensions[i]+"");
//						//statisticSql.append(","+dimensions[i]+"))");
//					}else{
//						statisticSql.append(","+dimensions[i]);
//					}
//				}
//			}
//		}
//		
		
		
		return statisticSql.toString();
	}
	
	/**
	 * 说明：根据指标和维度组合成sql，其中显示的指标具有相同的维度。
	 * 统计sql类型2
	 * 产生sql类型2:
	 * select count(deptid) from taw_system_user where sex='1' group by deptid,sex
	 * select count(sex) from taw_system_user where sex='1' group by deptid,sex
	 * select count(deleted) from taw_system_user where sex='1' group by deptid,sex
	 * 将sql再根据维度进行组合
	 **/
	public String generateSingleNoTotalStatisticSQL(String index,String[] dimensions,String queryCondition,String tableName){
		//step1:generate sql and get the statistic content
		StringBuffer statisticSql=new StringBuffer("select ");

		//step2:use indexs to combine the sql 
		for(int i=0;i<dimensions.length;i++){
			if(i==0){
				statisticSql.append(dimensions[i]);
			}else{
				statisticSql.append(","+dimensions[i]);
			}
		}
		if("".equals(index)){
			statisticSql.append(",* ");
		}else{
			statisticSql.append(","+index);
		}
		//step3:use table to combine the sql 
		if("".equals(tableName)){
			statisticSql.append(" from dual");
		}else{
			statisticSql.append(" from "+tableName);
		}
		//step4:use queryCondition to combine the sql 
		if("".equals(queryCondition)){
			statisticSql.append(" where 1=1 ");
		}else{
			statisticSql.append(" where 1=1 "+queryCondition);
		}
		//step5:use dimensions to combine the sql 

			for(int i=0;i<dimensions.length;i++){
				if(i==0){
					statisticSql.append(" group by "+dimensions[i]);
				}else{
					statisticSql.append(","+dimensions[i]);
				}
			}
		
		return statisticSql.toString();
	}
	
	/**
	 * 说明：将单个统计结果集组合成统计结果集，并返回List 
	 * 统计sql类型2
	 * 产生sql类型2:
	 * select count(deptid) from taw_system_user where sex='1' group by deptid,sex
	 * select count(sex) from taw_system_user where sex='1' group by deptid,sex
	 * select count(deleted) from taw_system_user where sex='1' group by deptid,sex
	 * 将sql再根据维度进行组合
	 **/
	public String [][] getStatisticCombineRet(String[] index,String[] dimensions,String[] queryCondition,String tableName,String whereStr,String paramEngStr,String direction){
			String genenrateSQL="";
			String[][] tempArray=null;
			if(index.length==1){
				//单个指标
				genenrateSQL=generateSingleStatisticSQL(index[0],dimensions,queryCondition[0],tableName,whereStr,paramEngStr,direction);
		    	logger.info("Single indicate=======>>statisticSQL:"+genenrateSQL);
		    	String[][] ret=excuteSQL(genenrateSQL,direction);
//		    	for(int i=0;i<ret.length;i++){
//					for(int j=0;j<dimensions.length;j++){
//						if(i==ret.length-1){
//							ret[i][j]="total";
//						}
//					}
//				}
		    	return ret;
			}else{
				//多个指标,值组合处理
				//step1:罗列所有的维度
				if(!"".equals(paramEngStr)&&!(null==paramEngStr)){
					genenrateSQL=generateSingleStatisticSQL(index[0],dimensions,queryCondition[0],tableName,whereStr,paramEngStr,direction);
				}else{
					genenrateSQL=generateSingleStatisticSQL(index[0],dimensions,queryCondition[0],tableName,whereStr,paramEngStr,direction);
				}
		    	logger.info("DimensionTable==>>statisticSQL:"+genenrateSQL);
		    	String[][] ret=excuteSQL(genenrateSQL,direction);
		    	try{
		    		tempArray=new String[ret.length][(ret[0].length+index.length-1)];
		    	}catch(Exception e){
		    		tempArray=new String[0][0];
		    	}
				for(int i=0;i<tempArray.length;i++){
					for(int j=0;j<dimensions.length;j++){
						tempArray[i][j]=ret[i][j];
//						if(i==ret.length-1){
//							tempArray[i][j]="total";
//						}else{
//							tempArray[i][j]=ret[i][j];
//						}
					}
				}
				
				//step2:指标根据复合维度将值放入维度表
				
				for(int i=0;i<index.length;i++){
					genenrateSQL=generateSingleStatisticSQL(index[i],dimensions,queryCondition[i],tableName,whereStr,paramEngStr,direction);
					logger.info("combineSQL==>>statisticSQL:"+genenrateSQL);
					Map retMap= excuteSQLRetMap(genenrateSQL,String.valueOf(dimensions.length),direction);
					
					for(int j=0;j<tempArray.length;j++){
						String tempDimen="";
						for(int m=0;m<dimensions.length;m++){
							if(m==0){
								tempDimen=tempArray[j][m];
							}else{
								tempDimen=tempDimen+"≌"+tempArray[j][m];
							}
						}
						//System.out.println("tempDimen:"+tempDimen);
						//System.out.println("result:"+retMap.get(tempDimen));
						tempArray[j][(dimensions.length+i)]=BaseUtil.nullObject2String(retMap.get(tempDimen),"0");
					}
				}
				
			
				return tempArray;
			}
		}
	
	
	
	/**
	 * 说明：将单个统计结果集组合成统计结果集，并返回List 
	 * 统计sql类型2
	 * 产生sql类型2:
	 * select '-1' dept,count(deptid),sum(ssss),avg(mmmmmm) from taw_system_user where sex='1'
	 * select '104' dept,count(deptid),sum(ssss),avg(mmmmmm) from taw_system_user where sex='1'
	 * select '110' dept,count(deptid),sum(ssss),avg(mmmmmm) from taw_system_user where sex='1'
	 * 将sql再根据维度进行组合
	 **/
	public String [][] getStatisticSoleCombineRet(String[] index,String[] dimensions,String[] dimensionsValue,String[] queryCondition,String tableName,String whereStr,String indexCompute,String indexComputeReplace,int countValue,String rateType){
		//indexCompute   指标算法
		//indexComputeReplace 指标算法替换串
		ScriptEngineManager manager = new ScriptEngineManager();  
        ScriptEngine engine = manager.getEngineByName("js");
        String [] indexComputeReplaces=indexComputeReplace.split(",");
        int indexComputesLen=indexCompute.length()==0?0:indexCompute.split(",").length;
        String [] indexComputes= indexCompute.split(",");
		String [] rateTypes=rateType.split(",");
		
		
		String[][] tempArray=new String[dimensions.length][countValue+indexComputesLen+1];
		
		
		
		String tempSQL="";
		for(int i=0;i<dimensions.length;i++){
			tempSQL=generateSoleStatisticSQL(index,dimensions[i],dimensionsValue[i],queryCondition[i],tableName,whereStr);
			System.out.println("soleCombineSQL==>>statisticSQL:"+tempSQL);
			String[][] ret=excuteSQL(tempSQL);
			if(ret.length>0){
				for(int j=0;j<ret[0].length+indexComputesLen;j++){
					if(j<ret[0].length){
						if(j==0){
							tempArray[i][j]=ret[0][j];
						}else{
							tempArray[i][j]=ret[0][j]+"-"+queryCondition[i];
						}
					}else{
						String tempExcute=indexComputes[j-ret[0].length];
						boolean zeroFlag=false;
						//String [] replaces=indexComputeReplaces[j-ret[0].length].split("-");
						for(int m=0;m<indexComputeReplaces.length;m++){
							String tempComputeValue=tempArray[i][Integer.parseInt(BaseUtil.getNumber(indexComputeReplaces[m]))];
							if(tempExcute.lastIndexOf(indexComputeReplaces[m])>tempExcute.indexOf("/")){
								if("0".equals(tempComputeValue.split("-")[0])){
									zeroFlag=true;
								}
							}
							
							engine.put(indexComputeReplaces[m],tempComputeValue.split("-")[0]);  
						}
						try {
							if(zeroFlag){
								if("2".equals(rateTypes[j-ret[0].length])){
									tempArray[i][j]=BaseUtil.getPoint2NoPer(engine.eval(tempExcute).toString());
								}else if("1".equals(rateTypes[j-ret[0].length])){
									tempArray[i][j]=BaseUtil.getPoint2Per("1".toString());
								}else{
									tempArray[i][j]=BaseUtil.getPoint2Per("0".toString());
								}
							}else{
								if("2".equals(rateTypes[j-ret[0].length])){
									tempArray[i][j]=BaseUtil.getPoint2NoPer(engine.eval(tempExcute).toString());
								}else if("1".equals(rateTypes[j-ret[0].length])){
									tempArray[i][j]=BaseUtil.getPoint2Per(engine.eval(tempExcute).toString());
								}else{
									tempArray[i][j]=BaseUtil.getPoint2Per("0".toString());
								}
							}
						} catch (ScriptException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
				}
			}
		}
		
		//数据修改
		return tempArray;
	}
	
	/**
	 * 说明：根据指标和维度组合成sql，其中显示的指标具有相同的维度。
	 * 统计sql类型2
	 * 产生sql类型2:
	 * select count(deptid) from taw_system_user where sex='1' group by deptid,sex order by desc/asc
	 * select count(sex) from taw_system_user where sex='1' group by deptid,sex order by desc/asc
	 * select count(deleted) from taw_system_user where sex='1' group by deptid,sex order by desc/asc
	 * 将sql再根据维度进行组合
	 **/
	public String generateSingleStatisticSQL(String index,String[] dimensions,String queryCondition,String tableName,String whereStr){
		//step1:generate sql and get the statistic content
		StringBuffer statisticSql=new StringBuffer("select ");

		//step2:use indexs to combine the sql 
		for(int i=0;i<dimensions.length;i++){
			if(i==0){
				statisticSql.append(dimensions[i]);
			}else{
				statisticSql.append(","+dimensions[i]);
			}
		}
		if("".equals(index)){
			statisticSql.append(",* ");
		}else{
			statisticSql.append(","+index);
		}
		//step3:use table to combine the sql 
		if("".equals(tableName)){
			statisticSql.append(" from dual");
		}else{
			statisticSql.append(" from "+tableName);
		}
		//step4:use queryCondition to combine the sql 
		if("".equals(queryCondition)){
			statisticSql.append(" where 1=1 ");
		}else{
			statisticSql.append(" where 1=1 "+queryCondition);
		}
		//where String
		if(!"".equals(whereStr)){
			statisticSql.append(whereStr);
		}
		
		
		for(int i=0;i<dimensions.length;i++){
			statisticSql.append(" and "+dimensions[i]+" is not null ");
		}
		
		//step5:use dimensions to combine the sql 
		if(dimensions.length==1){
			//statisticSql.append(" group by rollup("+dimensions[0]+")");
			statisticSql.append(" group by "+dimensions[0]+"");
		}else if(dimensions.length>1){
			for(int i=0;i<dimensions.length;i++){
				if(i==0){
					statisticSql.append(" group by "+dimensions[i]);
					//statisticSql.append(" group by rollup(("+dimensions[i]);
				}else{
					if(dimensions.length==(i+1)){
						statisticSql.append(","+dimensions[i]+"");
						//statisticSql.append(","+dimensions[i]+"))");
					}else{
						statisticSql.append(","+dimensions[i]);
					}
				}
			}
		}
		
		//step5:use dimensions to orderby the sql
		if(dimensions.length==1){
			//statisticSql.append(" group by rollup("+dimensions[0]+")");
			statisticSql.append(" order by "+dimensions[0]+"");
		}else if(dimensions.length>1){
			for(int i=0;i<dimensions.length;i++){
				if(i==0){
					statisticSql.append(" order by "+dimensions[i]);
					//statisticSql.append(" group by rollup(("+dimensions[i]);
				}else{
					if(dimensions.length==(i+1)){
						statisticSql.append(","+dimensions[i]+"");
						//statisticSql.append(","+dimensions[i]+"))");
					}else{
						statisticSql.append(","+dimensions[i]);
					}
				}
			}
		}
		
		return statisticSql.toString();
	}
	
	
	/**
	 * 说明：将单个统计结果集组合成统计结果集，并返回List 
	 * 统计sql类型2
	 * 产生sql类型2:
	 * select count(deptid) from taw_system_user where sex='1' group by deptid,sex
	 * select count(sex) from taw_system_user where sex='1' group by deptid,sex
	 * select count(deleted) from taw_system_user where sex='1' group by deptid,sex
	 * 将sql再根据维度进行组合
	 * 手机接口
	 **/
	public String [][] getStatisticCombineRet(String[] index,String[] dimensions,String[] queryCondition,String tableName,String whereStr){
			String genenrateSQL="";
			String[][] tempArray=null;
			if(index.length==1){
				//单个指标
				genenrateSQL=generateSingleStatisticSQL(index[0],dimensions,"",tableName,whereStr);
		    	System.out.println("Single indicate==>>statisticSQL:"+genenrateSQL);
		    	String[][] ret=excuteSQL(genenrateSQL);
		    	return ret;
			}else{
				//多个指标,值组合处理
				//step1:罗列所有的维度
				genenrateSQL=generateSingleStatisticSQL(index[0],dimensions,"",tableName,whereStr);
				System.out.println("DimensionTable==>>statisticSQL:"+genenrateSQL);
		    	String[][] ret=excuteSQL(genenrateSQL);
		    	try{
		    		tempArray=new String[ret.length][(ret[0].length+index.length-1)];
		    	}catch(Exception e){
		    		tempArray=new String[0][0];
		    	}
				for(int i=0;i<tempArray.length;i++){
					for(int j=0;j<dimensions.length;j++){
						tempArray[i][j]=ret[i][j];
					}
				}
				//step2:指标根据复合维度将值放入维度表   加指标条件，queryConditon
				
				
				for(int i=0;i<index.length;i++){
					genenrateSQL=generateSingleStatisticSQL(index[i],dimensions,queryCondition[i],tableName,whereStr);
					System.out.println("combineSQL==>>statisticSQL:"+genenrateSQL);
					Map retMap= excuteSQLRetMap(genenrateSQL,String.valueOf(dimensions.length));
					
					for(int j=0;j<tempArray.length;j++){
						String tempDimen="";
						for(int m=0;m<dimensions.length;m++){
							if(m==0){
								tempDimen=tempArray[j][m];
							}else{
								tempDimen=tempDimen+"≌"+tempArray[j][m];
							}
						}
						tempArray[j][(dimensions.length+i)]=BaseUtil.nullObject2String(retMap.get(tempDimen),"0");
					}
				}
				return tempArray;
			}
		}
	
	
	/**
	 * 说明：将单个统计结果集组合成统计结果集，并返回List 
	 * 统计sql类型2
	 * 产生sql类型2:
	 * select count(deptid) from taw_system_user where sex='1' group by deptid,sex
	 * select count(sex) from taw_system_user where sex='1' group by deptid,sex
	 * select count(deleted) from taw_system_user where sex='1' group by deptid,sex
	 * 将sql再根据维度进行组合
	 **/
	public String [][] getStatisticCombineRetNoTotal(String[] index,String[] dimensions,String[] queryCondition,String tableName,String whereStr,String indicateId,String direction ){
		String genenrateSQL="";
		String[][] tempArray=null;
		if(index.length==1){
			//单个指标
			genenrateSQL=generateSingleNoTotalStatisticSQL(index[0],dimensions,"",tableName);
	    	logger.info("Single indicate==>>statisticSQL:"+genenrateSQL);
	    	String[][] ret=excuteSQL(genenrateSQL);
	    	return ret;
		}else{
			//多个指标,值组合处理
			//step1:罗列所有的维度
			genenrateSQL=generateSingleStatisticSQL(index[0],dimensions,"",tableName,whereStr,indicateId,direction);
	    	logger.info("DimensionTable==>>statisticSQL:"+genenrateSQL);
	    	String[][] ret=excuteSQL(genenrateSQL);
	    	try{
	    		tempArray=new String[ret.length][(ret[0].length+index.length-1)];
	    	}catch(Exception e){
	    		tempArray=new String[0][0];
	    	}
			for(int i=0;i<tempArray.length;i++){
				for(int j=0;j<dimensions.length;j++){
					tempArray[i][j]=ret[i][j];
				}
			}
			
			//step2:指标根据复合维度将值放入维度表
			
			for(int i=0;i<index.length;i++){
				genenrateSQL=generateSingleNoTotalStatisticSQL(index[i],dimensions,"",tableName);
				logger.info("combineSQL==>>statisticSQL:"+genenrateSQL);
				Map retMap= excuteSQLRetMap(genenrateSQL,String.valueOf(dimensions.length));
				
				for(int j=0;j<tempArray.length;j++){
					String tempDimen="";
					for(int m=0;m<dimensions.length;m++){
						if(m==0){
							tempDimen=tempArray[j][m];
						}else{
							tempDimen=tempDimen+"-"+tempArray[j][m];
						}
					}
					tempArray[j][(dimensions.length+i)]=BaseUtil.nullObject2String(retMap.get(tempDimen),"0");
				}
			}
			
		
			return tempArray;
		}	
			
	}
	
	
	/**
	 * 说明：详单页面查询
	 * 统计sql类型2
	 * 产生sql类型2:
	 * select 维度字段，详单字段 from taw_system_user where 1=1 group by 维度
	 * 将sql再根据维度进行组合
	 **/
	
	public String [][] getGraspDataArray(String[] graspColumn,String queryCondition,String tableName){
		//step1:generate sql and get the statistic content
		StringBuffer statisticSql=new StringBuffer("select ");

		//step2:use indexs to combine the sql 
		for(int i=0;i<graspColumn.length;i++){
			if(i==0){
				statisticSql.append(graspColumn[i]);
			}else{
				statisticSql.append(","+graspColumn[i]);
			}
		}
		
		//step3:use table to combine the sql 
		if("".equals(tableName)){
			statisticSql.append(" from dual");
		}else{
			statisticSql.append(" from "+tableName);
		}
		//step4:use queryCondition to combine the sql 
		if("".equals(queryCondition)){
			statisticSql.append(" where 1=1 ");
		}else{
			statisticSql.append(" where 1=1 "+queryCondition);
		}
		
		logger.info("====>datagraspSQL:"+statisticSql.toString());
		return excuteSQL(statisticSql.toString());
	}
	
	/**
	 * 说明：详单页面查询
	 * 统计sql类型2
	 * 产生sql类型2:
	 * select 维度字段，详单字段 from taw_system_user where 1=1 group by 维度
	 * 将sql再根据维度进行组合
	 **/
	
	public String [][] getGraspDataArrayCount(String[] graspColumn,String queryCondition,String tableName){
		//step1:generate sql and get the statistic content
		StringBuffer statisticSql=new StringBuffer("select count(*) from (select ");

		//step2:use indexs to combine the sql 
		for(int i=0;i<graspColumn.length;i++){
			if(i==0){
				statisticSql.append(graspColumn[i]);
			}else{
				statisticSql.append(","+graspColumn[i]);
			}
		}
		
		//step3:use table to combine the sql 
		if("".equals(tableName)){
			statisticSql.append(" from dual");
		}else{
			statisticSql.append(" from "+tableName);
		}
		//step4:use queryCondition to combine the sql 
		if("".equals(queryCondition)){
			statisticSql.append(" where 1=1 ");
		}else{
			statisticSql.append(" where 1=1 "+queryCondition);
		}
		statisticSql.append(")");
		
		logger.info("====>datagraspSQL:"+statisticSql.toString());
		return excuteSQL(statisticSql.toString());
	}
	
	/**
	 * 说明：详单页面查询分页 显示 
	 * 统计sql类型2
	 * 产生sql类型2:
	 * select 维度字段，详单字段 from taw_system_user where 1=1 group by 维度
	 * 将sql再根据维度进行组合
	 * 手机接口
	 **/
	
	public String [][] getGraspDataArrayByPage(String[] graspColumn,String queryCondition,String tableName,PageModel pageModel){
		//step1:generate sql and get the statistic content
		StringBuffer statisticSql=new StringBuffer("select ");
        StringBuffer coulmn=new StringBuffer("");
		//step2:use indexs to combine the sql 
		for(int i=0;i<graspColumn.length;i++){
			if(i==0){
				statisticSql.append(graspColumn[i]);
				coulmn.append(graspColumn[i]);
			}else{
				statisticSql.append(","+graspColumn[i]);
				coulmn.append(","+graspColumn[i]);
			}
		}
		statisticSql.append(",ROWNUM num");
		//step3:use table to combine the sql 
		if("".equals(tableName)){
			statisticSql.append(" from dual");
		}else{
			statisticSql.append(" from "+tableName);
		}
		//step4:use queryCondition to combine the sql 
		if("".equals(queryCondition)){
			statisticSql.append(" where 1=1 ");
		}else{
			statisticSql.append(" where 1=1 "+queryCondition);
		}
		//logger.info("====>datagraspSQL1:"+statisticSql.toString());
		
		String tempbeginsql="select "+coulmn.toString()+" from (";
		String tempendsql=") temp where num <= " + pageModel.endPageNum+" and num >="+pageModel.begainPageNum;
		
		
		logger.info("====>datagraspSQL2:"+tempbeginsql+statisticSql.toString()+tempendsql);
		
		
		return excuteSQL1(tempbeginsql+statisticSql.toString()+tempendsql);
	}

	
	public String[][] excuteSQL(String sql){
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs=null;
		DataSource ds = (DataSource)ApplicationContextHolder.getInstance().getBean("dataSource");//获取系统数据源
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			rs.last();
			int rowsnum=rs.getRow();
			rs.beforeFirst();
			ResultSetMetaData rsmd = rs.getMetaData() ; 
			int columnCount = rsmd.getColumnCount();
			String [][]resultArray=new String[rowsnum][columnCount];
			int countrow=0;
			while (rs.next()) {
				for (int i = 1; i <=columnCount; i++) {
				     Object obj = rs.getObject(i);
				     if(null==obj){
				    	 if(countrow<rowsnum-1){
				    		 resultArray[countrow][i-1]="";
				    	 }else{
				    		 resultArray[countrow][i-1]="合计";
				    	 }
				     }else{
				    	 resultArray[countrow][i-1]=obj.toString();
				     }
				 }
				countrow++;
			}
			return resultArray;
			
		} catch (SQLException e) {
			closeDBConnection(rs,stmt,conn);
			logger.info("===>>corestatisticEngine  connect error!!!!!!");
			e.printStackTrace();
		}finally{
			closeDBConnection(rs,stmt,conn);

		}
		return null;
	}
	
public String[][] excuteSQL1(String sql){
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs=null;
		DataSource ds = (DataSource)ApplicationContextHolder.getInstance().getBean("dataSource");//获取系统数据源
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			rs.last();
			int rowsnum=rs.getRow();
			rs.beforeFirst();
			ResultSetMetaData rsmd = rs.getMetaData() ; 
			int columnCount = rsmd.getColumnCount();
			String [][]resultArray=new String[rowsnum][columnCount];
			int countrow=0;
			while (rs.next()) {
				for (int i = 1; i <=columnCount; i++) {
				     Object obj = rs.getObject(i);
				     if(null==obj){
				    	 resultArray[countrow][i-1]="";
				     }else{
				    	 resultArray[countrow][i-1]=obj.toString();
				     }
				 }
				countrow++;
			}
			return resultArray;
			
		} catch (SQLException e) {
			closeDBConnection(rs,stmt,conn);
			logger.info("===>>corestatisticEngine  connect error!!!!!!");
			e.printStackTrace();
		}finally{
			closeDBConnection(rs,stmt,conn);

		}
		return null;
	}
	
public String[][] excuteSQL(String sql, String direction){
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs=null;
		DataSource ds = (DataSource)ApplicationContextHolder.getInstance().getBean("dataSource");//获取系统数据源
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			rs.last();
			int rowsnum=rs.getRow();
			rs.beforeFirst();
			ResultSetMetaData rsmd = rs.getMetaData() ; 
			int columnCount = rsmd.getColumnCount();
			String [][]resultArray=new String[rowsnum][columnCount];
			int countrow=0;
			while (rs.next()) {
				for (int i = 1; i <=columnCount; i++) {
				     Object obj = rs.getObject(i);
				     if(null==obj){
				    	 if("down".equals(direction)){
				    		 if(countrow>=1){
					    		 resultArray[countrow][i-1]="";
					    	 }else{
					    		 resultArray[countrow][i-1]="合计";
					    	 }
				    	 }else{
				    		 if(countrow<rowsnum-1){
					    		 resultArray[countrow][i-1]="";
					    	 }else{
					    		 resultArray[countrow][i-1]="合计";
					    	 }
				    	 }
				     }else{
				    	 resultArray[countrow][i-1]=obj.toString();
				     }
				 }
				countrow++;
			}
			return resultArray;
			
		} catch (SQLException e) {
			closeDBConnection(rs,stmt,conn);
			logger.info("===>>corestatisticEngine  connect error!!!!!!");
			e.printStackTrace();
		}finally{
			closeDBConnection(rs,stmt,conn);

		}
		return null;
	}
	
	
	public Map excuteSQLRetMap(String sql,String dimensions){
			
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs=null;
			List retList=new ArrayList();
			DataSource ds = (DataSource)ApplicationContextHolder.getInstance().getBean("dataSource");//获取系统数据源
			
			try {
				conn = ds.getConnection();
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs = stmt.executeQuery(sql);
				rs.last();
				int rowsnum=rs.getRow();
				rs.beforeFirst();
				ResultSetMetaData rsmd = rs.getMetaData() ; 
				int columnCount = rsmd.getColumnCount();
				//String [][]resultArray=new String[rowsnum][columnCount];
				Map retMap=new LinkedHashMap();
				int countrow=0;
				//System.out.println("length:"+rowsnum);
				while (rs.next()) {
					String tempDimen="";
					for(int i=1;i<=Integer.parseInt(dimensions);i++){
						if(i==1){
							if(null==rs.getObject(i)){
								if(countrow<rowsnum-1){
									tempDimen="";
								}else{
									tempDimen="合计";
								}
						     }else{
						    	 tempDimen=rs.getObject(i).toString();
						     }
						}else{
							if(null==rs.getObject(i)){
								if(countrow<rowsnum-1){
									tempDimen=tempDimen+"≌"+"";
								}else{
									tempDimen=tempDimen+"≌"+"合计";
								}
						     }else{
						    	 tempDimen=tempDimen+"≌"+BaseUtil.nullObject2String(rs.getObject(i).toString());
						     }
							
						}
					}
					retMap.put(tempDimen, BaseUtil.nullObject2String(rs.getObject(Integer.parseInt(dimensions)+1).toString()));
					
					countrow++;
					//System.out.println("countrow:"+countrow);
				}
				return retMap;
				
			} catch (SQLException e) {
				closeDBConnection(rs,stmt,conn);
				logger.info("===>>corestatisticEngine  connect error!!!!!!");
				e.printStackTrace();
			}finally{
				closeDBConnection(rs,stmt,conn);
	
			}
			return null;
		}
	
	public Map excuteSQLRetMap(String sql,String dimensions,String direction){
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs=null;
		List retList=new ArrayList();
		DataSource ds = (DataSource)ApplicationContextHolder.getInstance().getBean("dataSource");//获取系统数据源
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			rs.last();
			int rowsnum=rs.getRow();
			rs.beforeFirst();
			ResultSetMetaData rsmd = rs.getMetaData() ; 
			int columnCount = rsmd.getColumnCount();
			//String [][]resultArray=new String[rowsnum][columnCount];
			Map retMap=new LinkedHashMap();
			int countrow=0;
			//System.out.println("length:"+rowsnum);
			while (rs.next()) {
				String tempDimen="";
				for(int i=1;i<=Integer.parseInt(dimensions);i++){
					if(i==1){
						if(null==rs.getObject(i)){
							if("down".equals(direction)){
								if(countrow>=1){
									tempDimen="";
								}else{
									tempDimen="合计";
								}
							}else{
								if(countrow<rowsnum-1){
									tempDimen="";
								}else{
									tempDimen="合计";
								}
							}
					     }else{
					    	 tempDimen=rs.getObject(i).toString();
					     }
					}else{
						if(null==rs.getObject(i)){
							if("down".equals(direction)){
								if(countrow>=1){
									tempDimen=tempDimen+"≌"+"";
								}else{
									tempDimen=tempDimen+"≌"+"合计";
								}
							}else{
								if(countrow<rowsnum-1){
									tempDimen=tempDimen+"≌"+"";
								}else{
									tempDimen=tempDimen+"≌"+"合计";
								}
							}
					     }else{
					    	 tempDimen=tempDimen+"≌"+BaseUtil.nullObject2String(rs.getObject(i));
					     }
						
					}
				}
				retMap.put(tempDimen, BaseUtil.nullObject2String(rs.getObject(Integer.parseInt(dimensions)+1)));
				
				countrow++;
				//System.out.println("countrow:"+countrow);
			}
			return retMap;
			
		} catch (SQLException e) {
			closeDBConnection(rs,stmt,conn);
			logger.info("===>>corestatisticEngine  connect error!!!!!!");
			e.printStackTrace();
		}finally{
			closeDBConnection(rs,stmt,conn);

		}
		return null;
	}
	
	
	public Map<String,String> excuteSQLGetDemesion(String sql){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs=null;
		Map<String,String> retMap=new HashMap<String,String>();
		
		DataSource ds = (DataSource)ApplicationContextHolder.getInstance().getBean("dataSource");//获取系统数据源
		
		try {
			
			conn = ds.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			rs.beforeFirst();
			while (rs.next()) {
				retMap.put(BaseUtil.nullObject2String(rs.getObject(1).toString()), BaseUtil.nullObject2String(rs.getObject(2).toString()));
			}
			return retMap;
		} catch (SQLException e) {
			closeDBConnection(rs,stmt,conn);
			logger.info("===>>corestatisticEngine  connect error!!!!!!");
			e.printStackTrace();
		}finally{
			closeDBConnection(rs,stmt,conn);
	
		}
		return null;
	}
	
	private boolean closeDBConnection(ResultSet rs,Statement stmt,Connection conn)
	{
		boolean secssed = true;
		if(rs != null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				secssed = false;
				e.printStackTrace();
			}
			rs = null;
		}

		if(stmt != null)
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				secssed = false;
				e.printStackTrace();
			}
			stmt = null;
		}
		
		if(conn != null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				secssed = false;
				e.printStackTrace();
			}
			conn = null;
		}
		
		return secssed;
	}


	
}
