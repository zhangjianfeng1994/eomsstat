package com.boco.eoms.tawStatisticModel.util;



import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GroupTypeTransferUtil {
	@SuppressWarnings("rawtypes")
	private static final Class getClassForStatic(){
		return new Object(){
			public Class getClassForStatic(){
				return this.getClass();
			} 
		}.getClassForStatic();
	}
	private static String url=getClassForStatic().getResource("/").getPath().substring(0, getClassForStatic().getResource("/").getPath().indexOf("/classes/"))+"/classes/spring/TableBelong.xml";
	
	public static List<Map<String,String>> getGroupType(){
		Document document = null;
		String groupType = null;
		String groupTypeEng = null;
		List<Map<String,String>> groupTypeList=new ArrayList<Map<String,String>>();
		Map<String, String> groupTypeMap = null;
		try {
			document = read(url);
			Element root=document.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> tableBelongs=root.elements("tableBelong");
			for(int i=0;i<tableBelongs.size();i++){
				groupTypeMap = new HashMap<String, String>();
				
				Element tableBelong=(Element)tableBelongs.get(i);
				groupTypeEng = tableBelong.getText();
				groupType = tableBelong.attributeValue("tableName");
				groupTypeMap.put("CnName",groupType);
				groupTypeMap.put("EndName", groupTypeEng);

				groupTypeList.add(groupTypeMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupTypeList;
	}
	
	//转码
	public static String groupTypeTransfer(String groupTypeattr){
		
		Document document = null;
		String groupType = null;
		try {
			document = read(url);
			Element root=document.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> tableBelongs=root.elements("tableBelong");
			for(int i=0;i<tableBelongs.size();i++){
				Element tableBelong=(Element)tableBelongs.get(i);
				if(groupTypeattr.equals(tableBelong.getText())){
					groupType = tableBelong.attributeValue("tableName");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupType;
	}
	
	//反转码（中文转英文）
	public static String groupTypeTransfer2eng(String groupTypeattr){
			
			Document document = null;
			String groupType = null;
			try {
				document = read(url);
				Element root=document.getRootElement();
				@SuppressWarnings("unchecked")
				List<Element> tableBelongs=root.elements("tableBelong");
				for(int i=0;i<tableBelongs.size();i++){
					Element tableBelong=(Element)tableBelongs.get(i);
					if(groupTypeattr.equals(tableBelong.attributeValue("tableName"))){
						System.out.println(tableBelong.attributeValue("tableName"));
						groupType = tableBelong.getText();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return groupType;
		}
	
	 public static Document read(String fileName) throws MalformedURLException, DocumentException {

	       SAXReader reader = new SAXReader();
	       Document document = reader.read(new File(fileName));
	       return document;
	   }
}
