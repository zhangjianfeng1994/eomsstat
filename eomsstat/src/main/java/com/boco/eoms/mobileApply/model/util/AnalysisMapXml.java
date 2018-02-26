package com.boco.eoms.mobileApply.model.util;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//import org.apache.xpath.XPathAPI;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.boco.eoms.baseUtil.BaseUtil;
import com.boco.eoms.tawStatisticModel.util.ApplicationContextHolder;
import com.boco.eoms.tawSystemArea.model.TawSystemArea;
import com.boco.eoms.tawSystemArea.service.ITawSystemAreaService;
import com.boco.eoms.tawSystemDept.service.ITawSystemDeptService;
import com.boco.eoms.tawSystemDictType.model.TawSystemDictType;
import com.boco.eoms.tawSystemDictType.service.ITawSystemDictTypeService;
import com.boco.eoms.tawSystemSubRole.model.TawSystemSubRole;
import com.boco.eoms.tawSystemSubRole.service.ITawSystemSubRoleService;
import com.boco.eoms.tawSystemUser.model.TawSystemUser;
import com.boco.eoms.tawSystemUser.service.ITawSystemUserService;


public class AnalysisMapXml {
	
	/**
	 * 查询结果存入map中，key值从配置文件中读取
	 * @param inquiryResultMap 
	 * @param filePath 本地字段与接口字段对应的xml配置文件路径
	 * @param nodePath 配置文件中所需对应的标签
	 * */
	public  Map getColumnName(String filePath,String nodePath,Map inquiryResultMap){
		Map returnMap = new HashMap();
		SAXBuilder dc=new SAXBuilder();
		try {
			Document doc = dc.build(new File(filePath));
			Element element = doc.getRootElement();
			element = element.getChild(nodePath);
			List list = element.getChildren();
			for(int i=0;i<list.size();i++){
				Element node = (Element)list.get(i);
				String columnName = node.getAttribute("columnName").getValue();
				System.out.println("columnName from anlysis "+columnName);
				Object obj=inquiryResultMap.get(columnName);
				System.out.println("遍历realObj结果："+obj);
				if(obj instanceof Date){
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String dealString=df.format(obj);
					obj=dealString;
				}
				returnMap.put(columnName,BaseUtil.nullObject2String(obj));
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnMap;
	}
	
	/**
	 * Map转换为xml
	 * @param objectMap
	 * @param filePath 本地字段与接口字段对应的xml配置文件路径
	 * @param nodePath 配置文件中所需对应的标签
	 * */

	public String getXmlFromMap(Map objectMap,String filePath,String nodePath) throws Exception{
		List enNameList = new ArrayList();
		List contentList = new ArrayList();
		try{
			ITawSystemUserService userBO = (ITawSystemUserService)ApplicationContextHolder.getInstance().getBean("iTawSystemUserService");
			ITawSystemDictTypeService dictMgr= (ITawSystemDictTypeService)ApplicationContextHolder.getInstance().getBean("iTawSystemDictTypeService");
			ITawSystemSubRoleService subroleBO = (ITawSystemSubRoleService)ApplicationContextHolder.getInstance().getBean("iTawSystemSubRoleService");
			ITawSystemDeptService deptBO=(ITawSystemDeptService)ApplicationContextHolder.getInstance().getBean("iTawSystemDeptService");
			
			SAXBuilder dc=new SAXBuilder();
			Document doc = dc.build(new File(filePath));
			List analysisList = new ArrayList();
			Element element = doc.getRootElement();
			element = element.getChild(nodePath);
			
			List list = element.getChildren();
			for(int i=0;i<list.size();i++){
				Element node = (Element)list.get(i);
				String interfaceEnName = node.getAttribute("interfaceEnName").getValue();
				String columnName = node.getAttribute("columnName").getValue();
				String value = "";
				if(columnName.length()>0){
					Object obj = objectMap.get(columnName);
					if (obj != null && obj.getClass().isArray()) {
						obj = ((Object[]) obj)[0];
					}
					value = BaseUtil.nullObject2String(obj); 	
				}
				if(value.length()<=0)
					value = node.getAttribute("defauleValue").getValue();
	
				if(value.length()>0){	
					if(node.getAttribute("dictNodePath")!=null){//需要从xml文件转换数据字典
						String dictNodePath = node.getAttribute("dictNodePath").getValue();
						System.out.println("dictNodePath="+dictNodePath);
						value = this.getDictIdByInterfaceCode(dictNodePath, value);
					}
					if(node.getAttribute("type")!=null){
						String type = node.getAttribute("type").getValue();
						System.out.println("type="+type);
						if("dept".equalsIgnoreCase(type)){	//获取部门名称
							value =deptBO.queryById(value).getDeptname();
						}else if("user".equalsIgnoreCase(type)){	//获取人员
							TawSystemUser user = userBO.getUserByuserid(value);
							value = user.getUsername();
						}else if("dict".equalsIgnoreCase(type)){	//获取字典
							try{
								TawSystemDictType dict = dictMgr.queryDictTypeById(value);
								System.out.println("value from dict before"+value);
								if(dict!=null&&!"".equals(dict.getDictname()))
									value = dict.getDictname();
								System.out.println("value from dict after"+value);
							}catch (Exception e) {
								System.out.println("获取字典值出错,出错字典值dict="+value);
							}
						}else if("userAndRole".equals(type)&&!"role".equals(value)){
							TawSystemUser user = userBO.getUserByuserid(value);
							
							//######################根据userid获取subrole#################################################
							TawSystemSubRole subrole = subroleBO.queryBySubRoleId(value); //subroleBO.getTawSystemSubRole(value);
							if(null!=user.getUsername()&&null!=user.getId()){
								value = user.getUsername();
							}else if(subrole!=null&&null!=subrole.getSubrolename()&&null!=subrole.getId()){
								value = subrole.getSubrolename();
							}else if(null!=deptBO.queryById(value).getDeptname()&& !"".equals(deptBO.queryById(value).getDeptname())){
								value = deptBO.queryById(value).getDeptname();
							}else {
								value = value;
							}
							System.out.println("value from userAndRole after"+value);
						}else if("area".equals(type)&&!"role".equals(value)){
							ITawSystemAreaService areaBO = (ITawSystemAreaService)ApplicationContextHolder.getInstance().getBean("ItawSystemAreaService");
							TawSystemArea area = areaBO.queryAreaById(value);
							if(area != null && area.getAreaname() != null){
								value = area.getAreaname();
							}
							System.out.println("value from area after"+value);
						}else if("dictForPath".equals(type)){
							String nodeName=XmlManage.getFile("/config/faultSheetOperation.xml").getProperty("interfaceType."+columnName);
							value=getDictNameForDictId(filePath,nodeName,value);
							System.out.println("value from dictForPath after"+value);
						}
					}
					if(node.getAttribute("analysis")!=null){//判断内容是否需要xml解析
						String analysis = node.getAttribute("analysis").getValue();
						System.out.println("analysis="+analysis);
						if("no".equals(analysis)){
							analysisList.add(interfaceEnName);
						}
					}
					if(node.getAttribute("dictPath")!=null){
						String dictPath = node.getAttribute("dictPath").getValue();
						System.out.println("dictPath="+dictPath);
						value = this.getDictNameByDictId(dictPath, value);
					}
				}
				enNameList.add(interfaceEnName);
				contentList.add(value);
				System.out.println("遍历interfaceEnName结果："+interfaceEnName);
				System.out.println("遍历value结果："+value);
			}
			OptDetail optDetail = new OptDetail();
			String opDetail = optDetail.createOpDetailXml(enNameList,
					contentList,analysisList);
			return opDetail;
		}catch(Exception err){
			err.printStackTrace();
			throw new Exception("生成xml出错："+err.getMessage());
		}
	}
	
	
	
	public String getDictIdByInterfaceCode(String nodePath,String code) throws Exception{
		String filePath = BaseUtil.getFilePathForUrl("classpath:spring/crm-config.xml");
		return this.getDictIdByInterfaceCode(filePath, nodePath, code);
	}
	
	public String getDictNameByDictId (String dictPath,String code) throws Exception{
		String codeName ="";
		String filePath = BaseUtil.getFilePathForUrl("classpath:config/"+dictPath);
		SAXBuilder dc=new SAXBuilder();
		Document doc = dc.build(new File(filePath));
		Element element = doc.getRootElement();
		if(element==null){
			System.out.println(dictPath+" not find");
			return "";
		}
		List list = element.getChildren();
		for(int i=0;i<list.size();i++){
			Element node = (Element)list.get(i);
			String id = node.getAttribute("id").getValue();
			if("mainOperateType".equals(id)){
				List itList = node.getChildren();
				for(int j =0;j<itList.size();j++){
					Element childNode = (Element)itList.get(j);
					String childId = childNode.getAttribute("id").getValue();
					if(childId.equals(code)){
						codeName = code+"||"+childNode.getAttribute("name").getValue();
					}
				}
			}
		}
		return codeName;
	}
	
	//通过指定路径获取字典id的name值，用于字典值各省不同的情况通过配置文件配置，例如：获取变更类型mainChangeType的字典值
	public String getDictNameForDictId (String dictPath,String nodePath,String code) throws Exception{
		String codeName ="";
		SAXBuilder dc=new SAXBuilder();
		Document doc = dc.build(new File(dictPath));
		Element element = doc.getRootElement();
		element = element.getChild(nodePath);
		List list = element.getChildren();
		for(int i=0;i<list.size();i++){
			Element childNode = (Element)list.get(i);
			String childId = childNode.getAttribute("id").getValue();
			if(childId.equals(code)){
				codeName = childNode.getAttribute("name").getValue();
			}
		}
		return codeName;
	}
	
	/**
	 * 通过业务类型查找对应的字典
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String getDictIdByInterfaceCode(String filePath,String nodePath,String code) throws Exception{
		SAXBuilder dc=new SAXBuilder();
		Document doc = dc.build(new File(filePath));
		
		Element element = doc.getRootElement();
		element = element.getChild("dict");
		element = element.getChild(nodePath);
		if(element==null){
			System.out.println("dict."+nodePath+" not find");
			return "";
		}
		
		List list = element.getChildren();
		for(int i=0;i<list.size();i++){
			Element node = (Element)list.get(i);
			String dict = node.getAttribute("dictid").getValue();
			String interfacecode = node.getAttribute("interfacecode").getValue();
			if(interfacecode.equals(code))
				return dict;
		}
		return "";
	}

//	/**
//	 * 解析附件
//	 * @param xmlDoc
//	 * @return LinkedHashMap, 包括附件名、附件的下载url、附件的长度
//	 * @throws Exception
//	 */
//	public static LinkedHashMap xmlAttachRefElements(String xmlDoc) {
//		LinkedHashMap map = new LinkedHashMap();
//		// 创建一个新的字符串
//		StringReader read = new StringReader(xmlDoc);
//		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
//		InputSource source = new InputSource(read);
//		// 创建一个新的SAXBuilder
//		SAXBuilder sb = new SAXBuilder();
//		try {
//			// 通过输入源构造一个Document
//			Document doc = sb.build(source);
//			// 取的根元素
//			Element root = doc.getRootElement();
//			if (root.getContentSize() > 0) {
//				// 得到根元素所有子元素的集合
//				List firstList = root.getChildren();
//				// 获得XML中的命名空间（XML中未定义可不写）
//				Namespace ns = root.getNamespace();
//				Element et = null;
//				for (int i = 0; i < firstList.size(); i++) {
//					et = (Element) firstList.get(i);// 循环依次得到子元素
//					map.put(String.valueOf(i), et.getChild("attachName", ns).getText()+ ";" + et.getChild("attachURL", ns).getText() + ";" + et.getChild("attachLength", ns).getText());
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return map;
//	}
//	
//	/**
//	 * 解析xml
//	 * @param xmlDoc
//	 * @return LinkedHashMap
//	 * @throws Exception
//	 */
//	public static LinkedHashMap xmlElements(String xmlDoc) {
//		LinkedHashMap map = new LinkedHashMap();
//		// 创建一个新的字符串
//		StringReader read = new StringReader(xmlDoc);
//		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
//		InputSource source = new InputSource(read);
//		// 创建一个新的SAXBuilder
//		SAXBuilder sb = new SAXBuilder();
//		try {
//			// 通过输入源构造一个Document
//			Document doc = sb.build(source);
//			// 取的根元素
//			Element root = doc.getRootElement();
//			System.out.println(root.getName());// 输出根元素的名称
//			// 得到根元素所有子元素的集合
//			List node = root.getChildren();
//			// 获得XML中的命名空间（XML中未定义可不写）
//			Namespace ns = root.getNamespace();
//			Element et = null;
//			for (int i = 0; i < node.size(); i++) {
//				et = (Element) node.get(i);// 循环依次得到子元素
//				List nodeChild = et.getChildren();
//				LinkedHashMap mapChild = new LinkedHashMap();
//				for (int j = 0; j < nodeChild.size(); j++) {
//					Element xet = (Element) nodeChild.get(j);
//					mapChild.put(xet.getChild("fieldEnName", ns).getText(),xet.getChild("fieldContent", ns).getText());
//					//System.out.println(xet.getChild("fieldEnName", ns).getText());
//				}
//				map.put(String.valueOf(i), mapChild);
//			}
//		} catch (Exception e) {
//			// TODO 自动生成 catch 块
//			e.printStackTrace();
//		}
//		return map;
//	}
//	
//	public static List getAttachRefFromXml(String xmlDoc)
//	{
//		if (xmlDoc == null || xmlDoc.length() == 0)
//			return null;
//		List resultlist = new ArrayList();
//		NodeList UDSObjectList = null;
//		Node UDSObject = null;
//		try
//		{
//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//			dbf.setNamespaceAware(true);
//			if (xmlDoc.indexOf("?xml") <= 0)
//				xmlDoc = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" + xmlDoc;
//			DocumentBuilder dc = dbf.newDocumentBuilder();
//			org.w3c.dom.Document doc = dc.parse(new InputSource(new StringReader(xmlDoc)));
//			String xpath = "//attachRef/attachInfo";
//			UDSObjectList = XPathAPI.selectNodeList(doc, xpath);
//			if (UDSObjectList.getLength() > 0)
//			{
//				for (int i = 0; i < UDSObjectList.getLength(); i++)
//				{
//					UDSObject = UDSObjectList.item(i);
//					AttachRef attach = new AttachRef();
//					xpath = "attachName";
//					attach.setAttachName(XPathAPI.selectSingleNode(UDSObject, xpath).getFirstChild().getNodeValue());
//					xpath = "attachURL";
//					attach.setAttachURL(XPathAPI.selectSingleNode(UDSObject, xpath).getFirstChild().getNodeValue());
//					xpath = "attachLength";
//					attach.setAttachLength(XPathAPI.selectSingleNode(UDSObject, xpath).getFirstChild().getNodeValue());
//					resultlist.add(attach);
//				}
//
//			}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		return resultlist;
//	}
}
