package com.boco.eoms.tawStatisticModel.util;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.boco.eoms.baseUtil.BaseUtil;

public class ReportAuthorityControlUtil {
	private static final Logger logger = Logger.getLogger(CoreStatisticEngine.class);
	@SuppressWarnings("rawtypes")
	private static final Class getClassForStatic(){
		return new Object(){
			public Class getClassForStatic(){
				return this.getClass();
			} 
		}.getClassForStatic();
	}
	private static String url=getClassForStatic().getResource("/").getPath().substring(0, getClassForStatic().getResource("/").getPath().indexOf("/classes/"))+"/classes/spring/ReportAuthorityControl.xml";
	
	public  List<String> getReportAuthority(String userid){
		Document document = null;
		List<String> sqlList=new ArrayList<String>();
		try {
			document = read(url);
			//通过document对象获取xml文件的根节点
			Element rootElement = document.getRootElement();
			 //获取根节点下的stat0节点
			@SuppressWarnings("unchecked")
			Element stat0 = rootElement.element("stat0");
			List<String> allsqlList = getAllReportSql(stat0);
			if(allsqlList.size()>0){
				for (int i = 0; i < allsqlList.size(); i++) {
					String sqlString = allsqlList.get(i);
					if (sqlString.equals("areaid")) {
						String sql = "SELECT b.areaid  as areaid FROM taw_system_user a,taw_system_dept b WHERE  a.deptid = b.deptid AND a.deleted =0 AND b.deleted =0 and a.userid= '"+userid+"'"; //获取登录人对应的areaid.
						logger.info("Single authority=======>>statisticSQL:"+sql);
						Map<String,String> deptidMap = excuteSQL(sql);
						String areaid = deptidMap.get("areaid");
						String whereStr = "";
						if (areaid.length() == 3) {
							 whereStr = " and city = '"+areaid+"'";
							sqlList.add(whereStr);
						}else if(areaid.length() == 5){
							 whereStr = " and country = '"+areaid+"'";
							sqlList.add(whereStr);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlList;
	}
	public static Document read(String fileName) throws MalformedURLException, DocumentException {

	       SAXReader reader = new SAXReader();
	       Document document = reader.read(new File(fileName));
	       return document;
	   }
	//获取xml下的所有报表全部限制权限
	@SuppressWarnings("unchecked")
	public static List<String> getAllReportSql(Element element){
		// 当前节点下面子节点迭代器  
        Iterator<Element> it = element.elementIterator();
        List<String> sqlList = new ArrayList<String>();
        while (it.hasNext()) {  
            // 获取某个子节点对象  
            Element e = it.next();  
            // 对子节点进行遍历  获取文本内容的值
            String  sql = e.getText();
            sqlList.add(sql);
        } 
		return sqlList;
	}
	//获取xml下的部分限制权限
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getPartReportSql(Element element){
		// 当前节点下面子节点迭代器  
		Iterator<Element> it = element.elementIterator();
		List<Map<String,String>> sqlList = new ArrayList<Map<String,String>>();
		while (it.hasNext()) {  
			// 获取某个子节点对象  
			Element e = it.next();  
			// 获取当前节点的modelid
			String  modelid = e.attributeValue("modelId");
			Map<String, String> modelMap = new HashMap<String, String>();
			modelMap.put("modelid", modelid);
			// 当前节点下面sql节点遍历
	        Iterator<Element> it1 = e.elementIterator();  
	        // 遍历  
	        while (it1.hasNext()) {  
	            // 获取某个子节点对象  
	            Element sql = it1.next();  
	            // 获取sql
	            String sqlString = sql.getText();
	            modelMap.put("sql", sqlString);
	        } 
	        sqlList.add(modelMap);
		} 
		return sqlList;
	}
	
	private  Map<String,String> excuteSQL(String sql){
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
				retMap.put("areaid", BaseUtil.nullObject2String(rs.getString("areaid")));
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
