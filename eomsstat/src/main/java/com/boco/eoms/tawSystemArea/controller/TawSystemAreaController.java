package com.boco.eoms.tawSystemArea.controller;


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

import com.boco.eoms.tawSystemArea.model.TawSystemArea;
import com.boco.eoms.tawSystemArea.service.ITawSystemAreaService;

@Controller
public class TawSystemAreaController {
	
	private static final Logger logger = Logger.getLogger(TawSystemAreaController.class);
	@Resource
	private ITawSystemAreaService iTawSystemAreaService;
	
	public static Logger getLogger() {
		return logger;
	}
	/**
	 *说明：地域管理-查询所有
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("/tawSystemArea/queryAllAreas.json")
    public ModelAndView queryAllRoles(HttpServletRequest request,HttpServletResponse response) { 

		ModelAndView mav=new ModelAndView();
		List<TawSystemArea> areas= iTawSystemAreaService.queryAllAreas();
		Map<String,List<TawSystemArea>> retMap=new HashMap<String,List<TawSystemArea>>();
		retMap.put("listArea", areas);
        
		mav.addObject("retJson", retMap);
		 
		return mav;
    }
	
	/** 
	 *说明：地域管理-根据父id查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/tawSystemArea/queryAreasByParentId.json") 
    public ModelAndView queryRolesByParentId(HttpServletRequest request,HttpServletResponse response) { 
		ModelAndView mav=new ModelAndView();
		//String parentId=request.getParameter("parentId");
		String parentId="26";
		List<TawSystemArea> areas= iTawSystemAreaService.queryAreasByParentId(parentId);
		Map<String,List<TawSystemArea>> retMap=new HashMap<String,List<TawSystemArea>>();
		retMap.put("listAreaByParentId", areas);
        
		mav.addObject("retJson", retMap);

       return mav;
    }
	
	/** 
	 *说明：地域管理-根据父id查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/tawSystemArea/queryAreaTreesByParentId.json") 
    public ModelAndView queryAreaTreesByParentId(HttpServletRequest request,HttpServletResponse response) { 
		ModelAndView mav=new ModelAndView();
		String parentId=request.getParameter("parentId");
		//String parentId="26";
		List<Map<String,Object>> areas= iTawSystemAreaService.queryAreaTreesByParentId(parentId);
		
		mav.addObject("retJson", areas);

	    return mav;
		
	}


	
}
