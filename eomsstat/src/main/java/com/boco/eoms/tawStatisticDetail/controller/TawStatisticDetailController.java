package com.boco.eoms.tawStatisticDetail.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boco.eoms.baseUtil.BaseUtil;
import com.boco.eoms.baseUtil.PageModel;
import com.boco.eoms.tawStatisticDetail.model.TawStatisticDetail;
import com.boco.eoms.tawStatisticDetail.service.ITawStatisticDetailService;
import com.boco.eoms.tawStatisticDetailItem.model.TawStatisticDetailItem;
import com.boco.eoms.tawStatisticDetailItem.service.ITawStatisticDetailItemService;





@Controller
@RequestMapping(value="tawStatisticDetail") 
public class TawStatisticDetailController {
	
	private static final Logger logger = Logger.getLogger(TawStatisticDetailController.class);

	public static Logger getLogger() {
		return logger;
	}
	
	
	@Resource
	private ITawStatisticDetailService iTawStatisticDetailService;
	@Resource
	private ITawStatisticDetailItemService iTawStatisticDetailItemService;
	
	/** 
	 *说明：查询所有详单集合分页呈现
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="detailCollection.json") 
    public ModelAndView detailListCollection(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String page=BaseUtil.nullObject2String(request.getParameter("page"));
		String pageSize=BaseUtil.nullObject2String(request.getParameter("pageSize"));
		
		String detailNamequery=BaseUtil.nullObject2String(request.getParameter("searchOption"));
		String groupTypequery=BaseUtil.nullObject2String(request.getParameter("groupTypequery"));
		String detailValuequery=BaseUtil.nullObject2String(request.getParameter("detailValuequery"));
		
		 //当前页  
        int currentPage = Integer.parseInt((page == null || page == "0") ? "1":page);  
        //每页显示条数  
        int number = Integer.parseInt((pageSize == null || pageSize == "0") ? "15":pageSize);  
        
        String totalPageNum="10";
        PageModel pageModel=null;
		List<TawStatisticDetail> detailCollection=new ArrayList<TawStatisticDetail>();
		if(null!=iTawStatisticDetailService.listDetailConditonCount(detailNamequery, groupTypequery, detailValuequery)){
			totalPageNum=iTawStatisticDetailService.listDetailConditonCount(detailNamequery, groupTypequery, detailValuequery);
		}
		//封装分页PageModel
		pageModel=new PageModel(Integer.parseInt(totalPageNum),number,currentPage);
		detailCollection= iTawStatisticDetailService.listDetailConditionByPage(pageModel, detailNamequery, groupTypequery, detailValuequery);
		
		
		Map<String,Object> retMap=new HashMap<String,Object>();

		retMap.put("rows", detailCollection);
		retMap.put("total", pageModel.getTotalPages());
		retMap.put("pageSize", number);
		retMap.put("currentPageNo", pageModel.getCurrnetPageNum());

		mav.addObject("retJson", retMap);	 
		return mav;
    }
	
	
	
	
	/** 
	 *说明：查询所有详单集合分页呈现
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="detailAdd.json") 
    public ModelAndView detailAdd(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		
		Map<String,Object> tempMap=getDetail(request);
		TawStatisticDetail tawStatisticDetail=(TawStatisticDetail)tempMap.get("tawStatisticDetail");
		List<TawStatisticDetailItem> tawStatisticDetailItemList=(List<TawStatisticDetailItem>)tempMap.get("tawStatisticDetailItemList");
		iTawStatisticDetailService.saveOrUpdate(tawStatisticDetail,tawStatisticDetailItemList);
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		mav.addObject("retJson", retMap);
		return mav;
	}
	
	public Map<String,Object> getDetail(HttpServletRequest request){
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		String submitType=BaseUtil.nullObject2String(request.getParameter("submitType"));
		
		String id="";
		BigDecimal detailNum=new BigDecimal(0);
		String detailNumName="";
		String groupType="";
		String urlFlag="";
		String url="";
		String add_detailColumnEng_text="";
		String [] add_detailColumnEng=null;
		String add_detailColumnChina_text="";
		String [] add_detailColumnChina=null;
		
		
		TawStatisticDetail tawStatisticDetail=new TawStatisticDetail();
		List<TawStatisticDetailItem> tawStatisticDetailItemList=new ArrayList<TawStatisticDetailItem>();
		
		
		if("add".equals(submitType)){
			id=BaseUtil.nullObject2String(request.getParameter("id"));
			detailNumName=BaseUtil.nullObject2String(request.getParameter("detailNumName"));
			groupType=BaseUtil.nullObject2String(request.getParameter("groupType_add"));
			urlFlag=BaseUtil.nullObject2String(request.getParameter("urlFlag"));
			url=BaseUtil.nullObject2String(request.getParameter("url"));
			add_detailColumnEng_text=BaseUtil.nullObject2String(request.getParameter("add_detailColumnEng_text"));
			add_detailColumnChina_text=BaseUtil.nullObject2String(request.getParameter("add_detailColumnChina_text"));
			
			add_detailColumnEng=add_detailColumnEng_text.split(",");
			add_detailColumnChina=add_detailColumnChina_text.split(",");
			TawStatisticDetailItem tawStatisticDetailItem=null;
			for(int i=0;i<add_detailColumnEng.length;i++){
				tawStatisticDetailItem=new TawStatisticDetailItem();
				tawStatisticDetailItem.setId("");
				tawStatisticDetailItem.setDetailColumnEng(add_detailColumnEng[i]);
				tawStatisticDetailItem.setDetailColumnChina(add_detailColumnChina[i]);
				tawStatisticDetailItem.setTransferCode(String.valueOf(i));
				tawStatisticDetailItemList.add(tawStatisticDetailItem);
			}
			
		}else{
			id=BaseUtil.nullObject2String(request.getParameter("id"));
			detailNumName=BaseUtil.nullObject2String(request.getParameter("detailNumName"));
			groupType=BaseUtil.nullObject2String(request.getParameter("groupType_edit"));
			urlFlag=BaseUtil.nullObject2String(request.getParameter("urlFlag"));
			url=BaseUtil.nullObject2String(request.getParameter("url"));
			
			add_detailColumnEng_text=BaseUtil.nullObject2String(request.getParameter("edit_detailColumnEng_text"));
			add_detailColumnChina_text=BaseUtil.nullObject2String(request.getParameter("edit_detailColumnChina_text"));
			add_detailColumnEng=add_detailColumnEng_text.split(",");
			add_detailColumnChina=add_detailColumnChina_text.split(",");
			TawStatisticDetailItem tawStatisticDetailItem=null;
			for(int i=0;i<add_detailColumnEng.length;i++){
				tawStatisticDetailItem=new TawStatisticDetailItem();
				tawStatisticDetailItem.setId("");
				tawStatisticDetailItem.setDetailColumnEng(add_detailColumnEng[i]);
				tawStatisticDetailItem.setDetailColumnChina(add_detailColumnChina[i]);
				tawStatisticDetailItem.setTransferCode(String.valueOf(i));
				tawStatisticDetailItemList.add(tawStatisticDetailItem);
			}
		}
		
		
		
		tawStatisticDetail.setId(id);
		tawStatisticDetail.setDetailNumName(detailNumName);
		tawStatisticDetail.setDetailNum(detailNum);
		tawStatisticDetail.setGroupType(groupType);
		tawStatisticDetail.setUrl(url);
		tawStatisticDetail.setUrlFlag(urlFlag);
		
		retMap.put("tawStatisticDetail", tawStatisticDetail);
		retMap.put("tawStatisticDetailItemList", tawStatisticDetailItemList);
		
		return retMap;
	}
	
	/** 
	 *说明：详单的删除
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="detailRemove.json") 
    public ModelAndView indexRemove(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String detail_id=BaseUtil.nullObject2String(request.getParameter("detail_id"));
		
		iTawStatisticDetailService.removeDetail(detail_id);
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("statuts", "success");
		
		mav.addObject("retJson", retMap);
		return mav;
		
	}
	
	/** 
	 *说明：详单的删除
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="getDetailById.json") 
    public ModelAndView getDetailById(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		String detail_id=BaseUtil.nullObject2String(request.getParameter("detail_id"));
		
		TawStatisticDetail tawStatisticDetail=iTawStatisticDetailService.getTawStatisticDetailById(detail_id);
		
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("tawStatisticDetail", tawStatisticDetail);
		retMap.put("statuts", "success");
		mav.addObject("retJson", retMap);
		return mav;
		
	}
	
	@RequestMapping(value="getTawStatisticDetailByGroupType.json") 
	public ModelAndView getTawStatisticDetailByGroupType(HttpServletRequest request,HttpServletResponse response){
		
		ModelAndView mav=new ModelAndView();
		String groupType=BaseUtil.nullObject2String(request.getParameter("groupType"));
		
		List<TawStatisticDetail> tawStatisticDetailList =iTawStatisticDetailService.getTawStatisticDetailByGroupType(groupType);
		
		
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("tawStatisticDetailList", tawStatisticDetailList);
		retMap.put("statuts", "success");
		mav.addObject("retJson", retMap);
		return mav;
	}
	
}
