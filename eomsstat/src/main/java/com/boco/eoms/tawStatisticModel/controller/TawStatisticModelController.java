package com.boco.eoms.tawStatisticModel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.boco.eoms.baseUtil.BaseUtil;
import com.boco.eoms.baseUtil.PageModel;
import com.boco.eoms.baseUtil.UUIDGenerator;
import com.boco.eoms.tawStatisticDetail.service.ITawStatisticDetailService;
import com.boco.eoms.tawStatisticIndex.model.TawStatisticIndex;
import com.boco.eoms.tawStatisticModel.model.ModelDefineTree;
import com.boco.eoms.tawStatisticModel.model.TawStatisticModel;
import com.boco.eoms.tawStatisticModel.model.TreeNode;
import com.boco.eoms.tawStatisticModel.service.ITawStatisticModelService;
import com.boco.eoms.tawStatisticModel.util.GroupTypeTransferUtil;
import com.boco.eoms.tawStatisticModel.util.XmlStatisticGenerateUtil;
import com.boco.eoms.tawSystemDept.model.TawSystemDept;
import com.boco.eoms.tawSystemDept.service.ITawSystemDeptService;
import com.boco.eoms.tawSystemDictType.model.TawSystemDictType;
import com.boco.eoms.tawSystemDictType.service.ITawSystemDictTypeService;
import com.boco.eoms.tawSystemUser.model.TawSystemUser;
import com.boco.eoms.tawSystemUser.service.ITawSystemUserService;

@Controller
@RequestMapping(value="tawStatisticModel")
public class TawStatisticModelController {
	private static final Logger logger = Logger.getLogger(TawStatisticModelController.class);
	
	@Resource
	private ITawStatisticModelService iTawStatisticModelService;
	@Resource
	private ITawStatisticDetailService iTawStatisticDetailService;
	@Resource
	private ITawSystemDeptService iTawSystemDeptService;
	@Resource
	private ITawSystemDictTypeService iTawSystemDictTypeService;
	@Resource
	private ITawSystemUserService iTawSystemUserService;
	
	
	public static Logger getLogger() {
		return logger;
	}
	
	
	/**
	 *妯℃澘鏌ヨ
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="modelCollection.json")
    public ModelAndView modelCollection(HttpServletRequest request,HttpServletResponse response) { 
		ModelAndView mav=new ModelAndView();
		String page=BaseUtil.nullObject2String(request.getParameter("page"));
		String pageSize=BaseUtil.nullObject2String(request.getParameter("pageSize"));
		                                                                          
		String indicateNamequery=BaseUtil.nullObject2String(request.getParameter("searchOption"));
		String groupTypequery=BaseUtil.nullObject2String(request.getParameter("groupTypequery"));
		String indicateValuequery=BaseUtil.nullObject2String(request.getParameter("indicateValuequery"));
		String userid=BaseUtil.nullObject2String(request.getParameter("userid"));
		
		 //瑜版挸澧犳い锟�
        int currentPage = Integer.parseInt((page == null || page == "0") ? "1":page);  
        //濮ｅ繘銆夐弰鍓с仛閺夆剝鏆� 
        int number = Integer.parseInt((pageSize == null || pageSize == "0") ? "15":pageSize); 
        
        String totalPageNum="10";
        PageModel pageModel=null;
        
        List<TawStatisticModel> modelCollection=new ArrayList<TawStatisticModel>();
        if("".equals(userid)){
        	if(null!=iTawStatisticModelService.listModelConditonCount(indicateNamequery, groupTypequery, indicateValuequery,"","","")){
    			totalPageNum=iTawStatisticModelService.listModelConditonCount(indicateNamequery, groupTypequery, indicateValuequery,"","","");
    		}
    		//鐏忎浇顥栭崚鍡涖�PageModel
    		pageModel=new PageModel(Integer.parseInt(totalPageNum),number,currentPage);
    		modelCollection= iTawStatisticModelService.listModelsConditionByPage(pageModel, indicateNamequery, groupTypequery, indicateValuequery,"","","");
        }else{
        	if(null!=iTawStatisticModelService.listModelConditonCount(indicateNamequery, groupTypequery, indicateValuequery,userid,"","")){
    			totalPageNum=iTawStatisticModelService.listModelConditonCount(indicateNamequery, groupTypequery, indicateValuequery,userid,"","");
    		}
    		//鐏忎浇顥栭崚鍡涖�PageModel
    		pageModel=new PageModel(Integer.parseInt(totalPageNum),number,currentPage);
    		modelCollection= iTawStatisticModelService.listModelsConditionByPage(pageModel, indicateNamequery, groupTypequery, indicateValuequery,userid,"","");
        }
		
		Map<String,Object> retMap=new HashMap<String,Object>();

		//Map<String,TawStatisticModel> retMap=new HashMap<String,TawStatisticModel>();
		//retMap.put("tawStatisticModel", tawStatisticModel);
		
		retMap.put("rows", modelCollection);
		retMap.put("total", pageModel.getTotalPages());
		retMap.put("pageSize", number);
		retMap.put("currentPageNo", pageModel.getCurrnetPageNum());
		mav.addObject("retJson", retMap);
		return mav;
    }
	
	
	/**
	 *鍒嗕韩妯℃澘鍖烘煡璇�-鎴戠殑妯℃澘鍒嗕韩
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="modelCollectionShare.json")
    public ModelAndView modelCollectionShare(HttpServletRequest request,HttpServletResponse response) { 
		ModelAndView mav=new ModelAndView();
		
		String page=BaseUtil.nullObject2String(request.getParameter("page"));
		String pageSize=BaseUtil.nullObject2String(request.getParameter("pageSize"));
		
		String indicateNamequery=BaseUtil.nullObject2String(request.getParameter("searchOption"));
		String groupTypequery=BaseUtil.nullObject2String(request.getParameter("groupTypequery"));
		String indicateValuequery=BaseUtil.nullObject2String(request.getParameter("indicateValuequery"));
		String currentUserId=BaseUtil.nullObject2String(request.getParameter("currentUserId"));
		
		 //褰撳墠椤�
        int currentPage = Integer.parseInt((page == null || page == "0") ? "1":page);  
        //椤甸潰璁板綍鏉℃暟
        int number = Integer.parseInt((pageSize == null || pageSize == "0") ? "15":pageSize); 
        
        String totalPageNum="10";
        PageModel pageModel=null;
        
        List<TawStatisticModel> modelCollection=new ArrayList<TawStatisticModel>();
        if("".equals(currentUserId)){
            if(null!=iTawStatisticModelService.listModelConditonCount(indicateNamequery, groupTypequery, indicateValuequery,"","","1")){
    			totalPageNum=iTawStatisticModelService.listModelConditonCount(indicateNamequery, groupTypequery, indicateValuequery,"","","1");
    		}
    		//鍒嗛〉model PageModel
    		pageModel=new PageModel(Integer.parseInt(totalPageNum),number,currentPage);
    		modelCollection= iTawStatisticModelService.listModelsConditionByPage(pageModel, indicateNamequery, groupTypequery, indicateValuequery,"","","1");
        }else{
            if(null!=iTawStatisticModelService.listModelConditonCount(indicateNamequery, groupTypequery, indicateValuequery,currentUserId,"","1")){
    			totalPageNum=iTawStatisticModelService.listModelConditonCount(indicateNamequery, groupTypequery, indicateValuequery,currentUserId,"","1");
    		}
    		//鍒嗛〉model PageModel
    		pageModel=new PageModel(Integer.parseInt(totalPageNum),number,currentPage);
    		modelCollection= iTawStatisticModelService.listModelsConditionByPage(pageModel, indicateNamequery, groupTypequery, indicateValuequery,currentUserId,"","1");
        }
        
		
		Map<String,Object> retMap=new HashMap<String,Object>();

		
		retMap.put("rows", modelCollection);
		retMap.put("total", pageModel.getTotalPages());
		retMap.put("pageSize", number);
		retMap.put("currentPageNo", pageModel.getCurrnetPageNum());
		mav.addObject("retJson", retMap);
		return mav;
    }
	
	
	/**
	 *鍏憡妯℃澘鏌ヨ
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="modelCollectionCommonRep.json")
    public ModelAndView modelCollectionCommonRep(HttpServletRequest request,HttpServletResponse response) { 
		ModelAndView mav=new ModelAndView();
		
		String page=BaseUtil.nullObject2String(request.getParameter("page"));
		String pageSize=BaseUtil.nullObject2String(request.getParameter("pageSize"));
		
		String indicateNamequery=BaseUtil.nullObject2String(request.getParameter("searchOption"));
		String groupTypequery=BaseUtil.nullObject2String(request.getParameter("groupTypequery"));
		String indicateValuequery=BaseUtil.nullObject2String(request.getParameter("indicateValuequery"));
		
		 //褰撳墠椤�
        int currentPage = Integer.parseInt((page == null || page == "0") ? "1":page);  
        //椤甸潰璁板綍鏉℃暟
        int number = Integer.parseInt((pageSize == null || pageSize == "0") ? "15":pageSize); 
        
        String totalPageNum="10";
        PageModel pageModel=null;
        
        List<TawStatisticModel> modelCollection=new ArrayList<TawStatisticModel>();
        if(null!=iTawStatisticModelService.listModelConditonCount(indicateNamequery, groupTypequery, indicateValuequery,"","1","")){
			totalPageNum=iTawStatisticModelService.listModelConditonCount(indicateNamequery, groupTypequery, indicateValuequery,"","1","");
		}
		//鍒嗛〉model PageModel
		pageModel=new PageModel(Integer.parseInt(totalPageNum),number,currentPage);
		modelCollection= iTawStatisticModelService.listModelsConditionByPage(pageModel, indicateNamequery, groupTypequery, indicateValuequery,"","1","");
		
		Map<String,Object> retMap=new HashMap<String,Object>();

		//Map<String,TawStatisticModel> retMap=new HashMap<String,TawStatisticModel>();
		//retMap.put("tawStatisticModel", tawStatisticModel);
		
		retMap.put("rows", modelCollection);
		retMap.put("total", pageModel.getTotalPages());
		retMap.put("pageSize", number);
		retMap.put("currentPageNo", pageModel.getCurrnetPageNum());
		mav.addObject("retJson", retMap);
		return mav;
    }
	
	
	/**
	 *鎴戠殑鎶ヨ〃妯℃澘鏌ヨ
	 * @param request
	 * @param response
	 * @return
	 */
	
	
	@RequestMapping(value="modelCollectionMyReport.json") 
    public ModelAndView indexListById(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		
		String page=BaseUtil.nullObject2String(request.getParameter("page"));
		String pageSize=BaseUtil.nullObject2String(request.getParameter("pageSize"));
		
		String indicateNamequery=BaseUtil.nullObject2String(request.getParameter("searchOption"));
		String groupTypequery=BaseUtil.nullObject2String(request.getParameter("groupTypequery"));
		String indicateValuequery=BaseUtil.nullObject2String(request.getParameter("indicateValuequery"));
		
		String userid=BaseUtil.nullObject2String(request.getParameter("userid"));
		
		 //褰撳墠椤�
        int currentPage = Integer.parseInt((page == null || page == "0") ? "1":page);  
        //椤甸潰璁板綍鏉℃暟
        int number = Integer.parseInt((pageSize == null || pageSize == "0") ? "15":pageSize); 
        
        String totalPageNum="10";
        PageModel pageModel=null;
        
        List<TawStatisticModel> modelCollection=new ArrayList<TawStatisticModel>();
        if(null!=iTawStatisticModelService.listModelConditonCount(indicateNamequery, groupTypequery, indicateValuequery,userid,"1","")){
			totalPageNum=iTawStatisticModelService.listModelConditonCount(indicateNamequery, groupTypequery, indicateValuequery,userid,"1","");
		}
		//鍒嗛〉model PageModel
		pageModel=new PageModel(Integer.parseInt(totalPageNum),number,currentPage);
		modelCollection= iTawStatisticModelService.listModelsConditionByPage(pageModel, indicateNamequery, groupTypequery, indicateValuequery,userid,"1","");
		
		Map<String,Object> retMap=new HashMap<String,Object>();

		//Map<String,TawStatisticModel> retMap=new HashMap<String,TawStatisticModel>();
		//retMap.put("tawStatisticModel", tawStatisticModel);
		
		retMap.put("rows", modelCollection);
		retMap.put("total", pageModel.getTotalPages());
		retMap.put("pageSize", number);
		retMap.put("currentPageNo", pageModel.getCurrnetPageNum());
		mav.addObject("retJson", retMap);
		return mav;
    }
	
	
	/** 
	 *璇存槑锛氭牴鎹姤琛ㄥ綊灞炴煡璇㈡墍鏈夋寚鏍�
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="getModelByTableName.json") 
    public ModelAndView getModelByTableName(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String table_Name=request.getParameter("table_Name");
		
		List<TawStatisticModel> retList=iTawStatisticModelService.getModelByTable(table_Name);
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		retMap.put("indexs", retList);
		mav.addObject("retJson", retMap);
		return mav;
	}
	
	
	/** 
	 *璇存槑锛氭姤琛ㄥ綊灞�
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="getTableBelongMap.json") 
    public ModelAndView getTableBelongMap(HttpServletRequest request,HttpServletResponse response) { 
		
		String modelId=BaseUtil.nullObject2String(request.getParameter("modelId"));
		
		ModelAndView mav=new ModelAndView();
		List<Map<String,String>> table_belong=GroupTypeTransferUtil.getGroupType();
		
		Integer index=null;
		if(!"".equals(modelId) && modelId!=null){
			//获取指定模板的groupType值
			String groupType = iTawStatisticModelService.getModelById(modelId).getGroupType();
			for (int i = 0; i < table_belong.size(); i++) {
				Map<String,String> t_belong = table_belong.get(i);
				if(t_belong.get("EndName").equals(groupType)){
					 index = new Integer(i);
				}
			}
		}
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		retMap.put("tableBelong", table_belong);
		retMap.put("index", index);
		
		mav.addObject("retJson", retMap);
		return mav;
    } 
	
	/** 
	 *璇存槑锛氭姤琛ㄦā鏉夸繚瀛�
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="saveModel.json") 
    public ModelAndView saveModel(HttpServletRequest request,HttpServletResponse response) { 
		ModelAndView mav=new ModelAndView();
		
		String modelItemStr=BaseUtil.nullObject2String(request.getParameter("modelItemStr"));
		String modelCycle=BaseUtil.nullObject2String(request.getParameter("modelCycle"));
		String modelIsShareFlag=BaseUtil.nullObject2String(request.getParameter("modelIsShareFlag"));
		String modelName=BaseUtil.nullObject2String(request.getParameter("modelName"));
		String modelGroupType=BaseUtil.nullObject2String(request.getParameter("modelGroupType"));
		String detailId=BaseUtil.nullObject2String(request.getParameter("detailId"));
		String computeRule=BaseUtil.nullObject2String(request.getParameter("computeRule"));
		String oldModelId=BaseUtil.nullObject2String(request.getParameter("oldModelId"));
		String userid="".equals(BaseUtil.nullObject2String(request.getParameter("userid")))?"admin":BaseUtil.nullObject2String(request.getParameter("userid"));
		
		logger.info("jsonString:"+modelItemStr);
		
		TawStatisticModel tawStatisticModel=new TawStatisticModel();
		
		tawStatisticModel.setId(UUIDGenerator.generate());
		tawStatisticModel.setCycle(modelCycle);
		tawStatisticModel.setIsCommonFlag("0");
		tawStatisticModel.setIsShareFlag(modelIsShareFlag);
		tawStatisticModel.setModelName(modelName);
		tawStatisticModel.setGroupType(modelGroupType);
		tawStatisticModel.setDetailId(detailId);
		tawStatisticModel.setComputeRule(computeRule);
		tawStatisticModel.setUserid(userid);
		tawStatisticModel.setModelType("auto");;
		
		ArrayList<ModelDefineTree> modelDefineTree = JSON.parseObject(modelItemStr, new TypeReference<ArrayList<ModelDefineTree>>(){}); 
		iTawStatisticModelService.saveModel(modelDefineTree,tawStatisticModel);
		
		if(!"".equals(oldModelId)){
			iTawStatisticModelService.removeModel(oldModelId);
		}
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		retMap.put("modelId",tawStatisticModel.getId());
	
		mav.addObject("retJson", retMap);
		return mav;
		
    } 
	
	/** 
	 *璇存槑锛氭姤琛ㄦā鏉跨紪杈�
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="editModel.json") 
    public ModelAndView editModel(HttpServletRequest request,HttpServletResponse response) { 
		ModelAndView mav=new ModelAndView();
		
		String modelId=request.getParameter("modelId");
		String modelCycle=request.getParameter("modelCycle");
		String modelIsCommonFlag=request.getParameter("modelIsCommonFlag");
		String modelIsShareFlag=request.getParameter("modelIsShareFlag");
		String modelName=request.getParameter("modelName");
	
		
		TawStatisticModel tawStatisticModel= iTawStatisticModelService.getModelById(modelId);
		
		tawStatisticModel.setCycle(modelCycle);
		tawStatisticModel.setIsCommonFlag(modelIsCommonFlag);
		tawStatisticModel.setIsShareFlag(modelIsShareFlag);
		tawStatisticModel.setModelName(modelName);
		
		iTawStatisticModelService.saveOrUpdateModel(tawStatisticModel);
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		retMap.put("modelId",tawStatisticModel.getId());
	
		mav.addObject("retJson", retMap);
		return mav;
		
    } 
	
	
	public TawStatisticModel getModel(HttpServletRequest request){
		String id=BaseUtil.nullObject2String(request.getParameter("id"));
		String modelName=BaseUtil.nullObject2String(request.getParameter("modelName"));
		String cycle=request.getParameter("cycle");
		String isShareFlag=request.getParameter("isShareFlag");
		String isCommonFlag=request.getParameter("isCommonFlag");
		String groupType=request.getParameter("groupType");

		TawStatisticModel tawStatisticModel=new TawStatisticModel();
		tawStatisticModel.setId(id);
		tawStatisticModel.setModelName(modelName);
		tawStatisticModel.setCycle(cycle);
		tawStatisticModel.setIsShareFlag(isShareFlag);
		tawStatisticModel.setIsCommonFlag(isCommonFlag);
		tawStatisticModel.setGroupType(groupType);
		return tawStatisticModel;
	}
	
	
	/** 
	 *璇存槑锛氭寚鏍囩殑鍒犻櫎
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="modelRemove.json") 
    public ModelAndView indexRemove(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String model_id=request.getParameter("model_id");
		
		iTawStatisticModelService.removeModel(model_id);
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		mav.addObject("retJson", retMap);
		return mav;
	}
	
	
	
	@RequestMapping(value="getModelById.json") 
    public ModelAndView getModelById(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String model_id=request.getParameter("model_id");
		
		TawStatisticModel tawStatisticModel= iTawStatisticModelService.getModelById(model_id);
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		retMap.put("tawStatisticModel", tawStatisticModel);
		
		mav.addObject("retJson", retMap);
		
		return mav;
	}
	/** 
	 *璇存槑锛氭ā鏉垮叕鍛婂尯
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="modelShareOut.json") 
	public ModelAndView modelShareOut(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String model_id=request.getParameter("model_id");
		
		TawStatisticModel tawStatisticModel= iTawStatisticModelService.getModelById(model_id);
		tawStatisticModel.setIsCommonFlag("1");
		
		iTawStatisticModelService.saveOrUpdateModel(tawStatisticModel);
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		mav.addObject("retJson", retMap);
		return mav;
	}
	
	
	
	/** 
	 *璇存槑锛氭ā鏉垮彇娑堝叕鍛�
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="modelShareIn.json") 
	public ModelAndView modelShareIn(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String model_id=request.getParameter("model_id");
		
		TawStatisticModel tawStatisticModel= iTawStatisticModelService.getModelById(model_id);
		tawStatisticModel.setIsCommonFlag("0");
		
		iTawStatisticModelService.saveOrUpdateModel(tawStatisticModel);
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		mav.addObject("retJson", retMap);
		return mav;
	}
	
	/** 
	 *璇存槑锛氭垜鐨勫垎浜�
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="myModelShare.json") 
	public ModelAndView myModelShare(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String model_id=request.getParameter("model_id");
		
		TawStatisticModel tawStatisticModel= iTawStatisticModelService.getModelById(model_id);
		tawStatisticModel.setIsShareFlag("1");
		
		iTawStatisticModelService.saveOrUpdateModel(tawStatisticModel);
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		mav.addObject("retJson", retMap);
		return mav;
	}
	
	/** 
	 *璇存槑锛氬彇娑堟垜鐨勫垎浜�
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="quitMyModelShare.json") 
	public ModelAndView quitMyModelShare(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String model_id=request.getParameter("model_id");
		
		TawStatisticModel tawStatisticModel= iTawStatisticModelService.getModelById(model_id);
		tawStatisticModel.setIsShareFlag("0");
		
		iTawStatisticModelService.saveOrUpdateModel(tawStatisticModel);
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		mav.addObject("retJson", retMap);
		return mav;
	}
	
	/** 
	 *璇存槑锛氭牴鎹ā鏉縤d鑾峰彇妯℃澘鍊�
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getModelIndicateById.json") 
    public ModelAndView getModelIndicateById(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String model_id=request.getParameter("modelid");
		List<TawStatisticIndex> indexs=new ArrayList<TawStatisticIndex>();
		
		
		TawStatisticModel tawStatisticModel= iTawStatisticModelService.getModelById(model_id);
		
		String xmlName=tawStatisticModel.getXmlName();
		
		XmlStatisticGenerateUtil xmlStatisticGenerateUtil=new XmlStatisticGenerateUtil();
		
		Element root=xmlStatisticGenerateUtil.getRoot(xmlName);
		
		List<Element> items=root.element("statistic-demension-items").elements("item");
		String root_demensions=root.elementText("statistic-demension-parentCode");
		
		
		for(int i=0;i<items.size();i++){
			TawStatisticIndex tawStatisticIndex=new TawStatisticIndex();
			tawStatisticIndex.setId(items.get(i).elementText("item-no"));
			tawStatisticIndex.setIndicateName(items.get(i).elementText("title"));
			indexs.add(tawStatisticIndex);
		}
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		retMap.put("indexs", indexs);
		retMap.put("tawStatisticModel", tawStatisticModel);
		retMap.put("rootDemensions", root_demensions);
		
		mav.addObject("retJson", retMap);
		return mav;
	}
	
	
	
	/**
	 * 鏍规嵁id杩斿洖鎸囨爣鏍戝瓙鑺傜偣鐨刯son鎴栭〉闈�
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value="index_sub_tree.json")
	@ResponseBody
	public List<TreeNode>  index_tree_json(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav=new ModelAndView();
		String pid=request.getParameter("pid");
		String defaultPid="".equals(BaseUtil.null2String(request.getParameter("defaultRoot")))?"-1":BaseUtil.null2String(request.getParameter("defaultRoot"));
		
		pid="".equals(BaseUtil.nullObject2String(pid))?defaultPid:pid;
		List<TawSystemDept> depts= iTawSystemDeptService.queryDeptByParentDeptID(pid);
		
		
		List<TreeNode> list=new ArrayList<TreeNode>();
		
		for(int i=0;i<depts.size();i++){
			TawSystemDept tawSystemDept=(TawSystemDept)depts.get(i);
			TreeNode treeNode=new TreeNode(tawSystemDept.getDeptid(),tawSystemDept.getParentdeptid(),tawSystemDept.getDeptname(),"true");
			list.add(treeNode);
		}
		mav.addObject(list);
		
		return list;
	}
	
	
	
	/**
	 * 鏍规嵁id杩斿洖鎸囨爣鏍戝瓙鑺傜偣鐨刯son鎴栭〉闈�
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value="index_sub_tree_dict.json")
	@ResponseBody
	public List<TreeNode>  index_tree_json_dict(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav=new ModelAndView();
		String pid=request.getParameter("pid");
		String defaultPid="".equals(BaseUtil.null2String(request.getParameter("defaultRoot")))?"-1":BaseUtil.null2String(request.getParameter("defaultRoot"));
		
		
		pid="".equals(BaseUtil.nullObject2String(pid))?defaultPid:pid;
		List<TawSystemDictType> dicts= iTawSystemDictTypeService.queryDictTypesByParentId(pid);
		
		List<TreeNode> list=new ArrayList<TreeNode>();
		
		for(int i=0;i<dicts.size();i++){
			TawSystemDictType tawSystemDictType=(TawSystemDictType)dicts.get(i);
			TreeNode treeNode=new TreeNode(tawSystemDictType.getDictid(),tawSystemDictType.getParentdictid(),tawSystemDictType.getDictname(),"true");
			list.add(treeNode);
		}
		mav.addObject(list);
		
		return list;
	}
	
	
	/**
	 * 首页登录session
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value="loginStatisticPlat") 
    public ModelAndView loginStatisticPlat(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) {
		
		//TawSystemUser tawSystemUser=iTawSystemUserService.getUserByuserid(BaseUtil.null2String(request.getParameter("userid")));
		//httpSession.setAttribute("tawSystemUser", tawSystemUser);  
		//ModelAndView mav=new ModelAndView();
		//return "views/statisticplat/main_frame_bootrap";
		ModelAndView   mav=new ModelAndView("statisticplat/main_frame_bootrap");
		return mav;
	}
	
	
	
	
}
