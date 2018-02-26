package com.boco.eoms.tawStatisticModel.util;

import java.io.File;
import java.net.MalformedURLException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlStatisticGenerateUtil {
	private String url=getClassForStatic().getResource("/").getPath().substring(0, getClassForStatic().getResource("/").getPath().indexOf("/classes/"))+"/classes/spring/";
	
	//statistic-plat-sql-config
	@SuppressWarnings("rawtypes")
	private final Class getClassForStatic(){
		return new Object(){
			public Class getClassForStatic(){
				return this.getClass();
			} 
		}.getClassForStatic();
	}
	
	
	 public Document read(String fileName) throws MalformedURLException, DocumentException {

	       SAXReader reader = new SAXReader();
	       Document document = reader.read(new File(fileName));
	       return document;
	   }
	 
	 public Element getRoot(String xmlName){
		
		 Element element=null;
		 try {
			element=read(url+xmlName+".xml").getRootElement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 return element;
	 }
}
