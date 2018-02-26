package com.boco.eoms.mobileApply.model.util;



import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.boco.eoms.baseUtil.BaseUtil;


public class OptDetail {
	
	private static final Logger logger = Logger.getLogger(OptDetail.class);
	
	private static XMLInputFactory factory = null;
	private static OptDetail OptDetail = null;
	
	public static OptDetail getInstance()
    {
        if(OptDetail == null)
        {
            factory = XMLInputFactory.newInstance();
            OptDetail = new OptDetail();
        }
        return OptDetail;
    }

	
	public  String createOpDetailXml(List enNameList,List contentList,List analysisList){
		Document dom4jDoc = DocumentHelper.createDocument();		
		//Element opDetailElement = dom4jDoc.addElement("opDetail");
		Element recordInfo = dom4jDoc.addElement("recordInfo");
		for(int i=0;i<enNameList.size();i++){
			addContentXML(recordInfo,BaseUtil.nullObject2String(enNameList.get(i)),contentList.get(i),analysisList);
		}
		String xml = dom4jDoc.asXML();
		String opt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		int index = xml.indexOf(opt);
		if(index>=0){
			xml = xml.substring(index+opt.length());
		}
		return xml;
	}
	
	private static  void addContentXML(Element recordInfo, String name,
			Object object,List analysisList) {
		System.out.println("遍历name值："+name);
		System.out.println("遍历object值："+object);
		Element filedInfo =recordInfo.addElement("fieldInfo");
		Element fieldEnName = filedInfo.addElement("fieldEnName");
		fieldEnName.setText(name);
		Element fieldContent = filedInfo.addElement("fieldContent");
		if(object==null){
			fieldContent.setText("");
		}else{
			if(analysisList.contains(name)){
				fieldContent.addCDATA(object.toString());
			}else{
				fieldContent.setText(object.toString());
			}
			
		}
	}
	
	public Map getOpdetail(String opdetail) throws Exception{
      Map map = new HashMap();
      XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(opdetail));
      String fieldCnName = "";
      String fieldCode = "";
      while(reader.hasNext()) 
        switch(reader.getEventType())
        {
        case 1: // '\001'
            String name = reader.getLocalName();
            if("fieldInfo".equals(name))
                System.out.println("fieldInfo:");
            else
            if("fieldId".equals(name))
            {
                String fieldId = BaseUtil.nullObject2String(reader.getElementText());
                System.out.println("fieldId:" + fieldId);
            } else
            if("fieldCode".equals(name))
            {
                fieldCode = BaseUtil.nullObject2String(reader.getElementText());
                System.out.println("fieldCode:" + fieldCode);
            } else
            if("fieldCnName".equals(name))
            {
                fieldCnName = BaseUtil.nullObject2String(reader.getElementText());
                System.out.println("fieldCnName:" + fieldCnName);
            } else
            if("fieldValue".equals(name))
            {
                String fieldValue = BaseUtil.nullObject2String(reader.getElementText());
                System.out.println("fieldValue:" + fieldValue);
                map.put(fieldCnName, fieldValue);
            } else
            if("fieldTurnValue".equals(name))
            {
                String fieldTurnValue = BaseUtil.nullObject2String(reader.getElementText());
                System.out.println("fieldTurnValue:" + fieldTurnValue);
                if(!"".equals(fieldCode))
                    map.put(fieldCnName + "\u540D\u79F0", fieldTurnValue);
            }
            // fall through

        default:
            reader.next();
            break;
        }
    return map;
}

public List getOpdetailList(String opdetail, Map sheetMap) throws Exception {
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(opdetail));
    List list = new ArrayList();
    Map map = new HashMap();
    String fieldCode = "";
    String fieldCnName = "";
    while(reader.hasNext()) 
        switch(reader.getEventType())
        {
        case 1: // '\001'
            String name = reader.getLocalName();
            if("recordInfo".equals(name))
            {
                if(map != null && !map.isEmpty())
                {
                    Map tmap = new HashMap();
                    tmap.putAll(sheetMap);
                    tmap.putAll(map);
                    list.add(tmap);
                    map = new HashMap();
                }
            } else
            if("fieldId".equals(name))
            {
                String fieldId = BaseUtil.nullObject2String(reader.getElementText());
                System.out.println("fieldId:" + fieldId);
            } else
            if("fieldCode".equals(name))
            {
                fieldCode = BaseUtil.nullObject2String(reader.getElementText());
                System.out.println("fieldCode:" + fieldCode);
            } else
            if("fieldCnName".equals(name))
            {
                fieldCnName = BaseUtil.nullObject2String(reader.getElementText());
                System.out.println("fieldCnName:" + fieldCnName);
            } else
            if("fieldValue".equals(name))
            {
                String fieldValue = BaseUtil.nullObject2String(reader.getElementText());
                System.out.println("fieldValue:" + fieldValue);
                map.put(fieldCnName, fieldValue);
            } else
            if("fieldTurnValue".equals(name))
            {
                String fieldTurnValue = BaseUtil.nullObject2String(reader.getElementText());
                System.out.println("fieldTurnValue:" + fieldTurnValue);
                if(!"".equals(fieldCode))
                    map.put(fieldCnName + "\u540D\u79F0", fieldTurnValue);
            }
            // fall through

        default:
            reader.next();
            break;
        }
    if(map != null && !map.isEmpty())
    {
        Map tmap = new HashMap();
        tmap.putAll(sheetMap);
        tmap.putAll(map);
        list.add(tmap);
    }
    logger.info( "end extra opdetail");
    return list;
}

public List getDetailOpdetailList(String opdetail, Map sheetMap) throws Exception {
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(opdetail));
    List list = new ArrayList();
    Map map = new HashMap();
    String fieldCode = "";
    String fieldCnName = "";
    while(reader.hasNext()) 
        switch(reader.getEventType())
        {
        case 1: // '\001'
            String name = reader.getLocalName();
            if("recordInfo".equals(name))
            {
                if(map != null && !map.isEmpty())
                {
                    Map tmap = new HashMap();
                    tmap.putAll(sheetMap);
                    tmap.putAll(map);
                    list.add(tmap);
                    map = new HashMap();
                }
            } else
            if("fieldCode".equals(name))
            {
                fieldCode = BaseUtil.nullObject2String(reader.getElementText());
                System.out.println("fieldCode:" + fieldCode);
            } else
            if("fieldCnName".equals(name))
            {
                fieldCnName = BaseUtil.nullObject2String(reader.getElementText());
                System.out.println("fieldCnName:" + fieldCnName);
            } else
            if("fieldValue".equals(name))
            {
                String fieldValue = BaseUtil.nullObject2String(reader.getElementText());
                System.out.println("fieldValue:" + fieldValue);
                map.put(fieldCode, fieldValue);   //fieldCode fieldValue 对应的字段和值
            } 
            // fall through

        default:
            reader.next();
            break;
        }
    if(map != null && !map.isEmpty())
    {
        Map tmap = new HashMap();
        tmap.putAll(sheetMap);
        tmap.putAll(map);
        list.add(tmap);
    }
    logger.info( "end extra opdetail");
    return list;
}
}
