package com.boco.eoms.tawSystemDictType.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boco.eoms.tawSystemDictType.model.TawSystemDictType;
import com.boco.eoms.tawSystemDictType.service.ITawSystemDictTypeService;

@Controller
public class TawSystemDictTypeController {
	@Resource
	private ITawSystemDictTypeService iTawSystemDictTypeService;
	
	/** 
	 *说明：字典管理-查询所有
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("/tawSystemDictType/queryAllDictTypes.json") 
    public ModelAndView queryAllRoles(HttpServletRequest request,HttpServletResponse response) { 

		ModelAndView mav=new ModelAndView();
		List<TawSystemDictType> areas= iTawSystemDictTypeService.queryAllDictType();
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("listDept", areas);
        
		mav.addObject("retJson", retMap);
		 
		return mav;
    }
	
	/** 
	 *说明：字典管理-根据父id查询
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("/tawSystemDictType/queryDictTypesByParentId.json") 
    public ModelAndView queryRolesByParentId(HttpServletRequest request,HttpServletResponse response) { 
		ModelAndView mav=new ModelAndView();
		
		String parentId=request.getParameter("parentId");
		//String parentId="26";
		List<TawSystemDictType> areas= iTawSystemDictTypeService.queryDictTypesByParentId(parentId);
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("listDeptByParentId", areas);
        
		mav.addObject("retJson", retMap);
		 
		return mav;
    }
	
	/** 
	 *说明：字典管理-根据父id查询
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("/tawSystemDictType/queryDictTypeTreesByParentId.json") 
    public ModelAndView queryDictTypeTreesByParentId(HttpServletRequest request,HttpServletResponse response) { 
		ModelAndView mav=new ModelAndView();
		
		String parentId=request.getParameter("parentId");
		//String parentId="10122";
		List<TawSystemDictType> dictTypes= iTawSystemDictTypeService.queryDictTypeTreesByParentId(parentId);
		
        
		//System.out.println(retJson);
		mav.addObject("retJson", dictTypes);
		 
		return mav;
		
	}
	
}
