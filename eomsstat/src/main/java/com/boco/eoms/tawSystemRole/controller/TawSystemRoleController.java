package com.boco.eoms.tawSystemRole.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boco.eoms.tawSystemRole.model.TawSystemRole;
import com.boco.eoms.tawSystemRole.service.ITawSystemRoleService;

@Controller
public class TawSystemRoleController {
	@Resource
	private ITawSystemRoleService iTawSystemRoleService;
	
	/** 
	 *说明：角色管理-查询所有
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("/tawSystemRole/queryAllRoles.json") 
    public ModelAndView queryAllRoles(HttpServletRequest request,PrintWriter printWriter) { 

		ModelAndView mv=new ModelAndView();
		List<TawSystemRole> roles= iTawSystemRoleService.queryAllRoles();
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("listRole", roles);
        

		 mv.addObject("retJson", retMap);
		 
		 return mv;
    }
	
	/** 
	 *说明：角色管理-根据父id查询
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("/tawSystemRole/queryRolesByParentId.json") 
    public ModelAndView queryRolesByParentId(HttpServletRequest request,PrintWriter printWriter) { 
		ModelAndView mv=new ModelAndView();
		//String parentId=request.getParameter("parentId");
		String parentId="1";
		List<TawSystemRole> roles= iTawSystemRoleService.queryRolesByParentId(parentId);
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("listRoleByParentId", roles);
        
        
        mv.addObject("retJson", retMap);
        
        return mv;
    }
	
	/** 
	 *说明：角色管理-根据父id查询
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("/tawSystemRole/queryRoleTreesByParentId.json") 
    public ModelAndView queryRoleTreesByParentId(HttpServletRequest request,HttpServletResponse response) { 
		ModelAndView mv=new ModelAndView();
		String parentId=request.getParameter("parentId");
		//String parentId="266";
		List roles= iTawSystemRoleService.queryRoleTreesByParentId(parentId);
		
		mv.addObject("retJson", roles);
        
        return mv;
		
	}
	
}
