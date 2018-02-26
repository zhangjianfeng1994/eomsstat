package com.boco.eoms.tawSystemDept.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boco.eoms.tawSystemDept.model.TawSystemDept;
import com.boco.eoms.tawSystemDept.service.ITawSystemDeptService;

@Controller
public class TawSystemDeptController {
	@Resource
	private ITawSystemDeptService iTawSystemDeptService;
	
	/** 
	 *说明：部门管理-查询所有
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("/tawSystemDept/queryAllDepts.json") 
    public ModelAndView queryAllRoles(HttpServletRequest request,HttpServletResponse reponse) { 

		ModelAndView mav=new ModelAndView();
		List<TawSystemDept> areas= iTawSystemDeptService.queryTawSystemDeptAll();
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("listDept", areas);
        
        
        mav.addObject("retJson", retMap);
		 
		return mav;
        
    }
	
	/** 
	 *说明：部门管理-根据父id查询
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("/tawSystemDept/queryDeptsByParentId.json") 
    public ModelAndView queryRolesByParentId(HttpServletRequest request,HttpServletResponse reponse) { 
		ModelAndView mav=new ModelAndView();
		//String parentId=request.getParameter("parentId");
		String parentId="26";
		List<TawSystemDept> areas= iTawSystemDeptService.queryDeptByParentDeptID(parentId);
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("listDeptByParentId", areas);
        
		mav.addObject("retJson", retMap);
		 
		return mav;
    }
	
	/** 
	 *说明：部门管理-根据父id查询
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("/tawSystemDept/queryDeptTreesByParentId.json") 
    public ModelAndView queryDeptTreesByParentId(HttpServletRequest request,HttpServletResponse reponse) { 
		ModelAndView mav=new ModelAndView();
		String parentId=request.getParameter("parentId");
		
		//String parentId="192";
		List<Map<String,Object>> depts= iTawSystemDeptService.queryDeptTreesByParentId(parentId);
		
		mav.addObject("retJson", depts);		 
		return mav;
		
	}
	
}
