<%@include file="../common/header.jsp"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="${cssPath}/maskLayer.css">

<%
String userid=request.getParameter("userName");
request.setAttribute("userid", userid);
System.out.println("----------------------------userid:"+userid);
/*
if(userid == null||"".equals(userid)){
	response.sendRedirect("http://10.238.78.149/eoms35/"); 
}
*/
%>
<script type="text/javascript">
	var userid = "${userid}";
	//alert(userid);
	var columnOptions={"modelName":"模板名称","cycle":"模板粒度","isShareFlag":"模板共享","isCommonFlag":"公共模板","groupType":"报表归属"};
	var url='${pageContext.request.contextPath}/tawStatisticModel/modelCollectionCommonRep.json';
		
	var my_rep_url='${pageContext.request.contextPath}/tawStatisticModel/modelCollection.json?userid='+userid;
	
	var columnOptions_share={"modelName":"模板名称"};
	var share_url='${pageContext.request.contextPath}/tawStatisticModel/modelCollectionShare.json';
		
	var columnOptions_myshare={"modelName":"模板名称"};
	var myshare_url='${pageContext.request.contextPath}/tawStatisticModel/modelCollectionShare.json?currentUserId='+userid;

	$(document).ready(function() {
		new dialogComponent("","","","","loadingAddModelDiv").loading_dialog();
		//加载公告报表
		new loadPageComponentCommon(url,1,columnOptions,"pageComponent-commonRep","listTable-commonRep","checkall-commonRep","checkreverse-commonRep","searchBtn-commonRep","seachBtnValue-commonRep","",function(model_id){
			//统计报表
			//parent.window.open("${pageContext.request.contextPath}/views/statisticplat/user_frame_reportform.jsp?modelid="+model_id); 
			show_statistic_page(model_id);
		},null).initData();
	
	
		//加载我的报表
		new loadPageComponentMyRep(my_rep_url,1,columnOptions,"pageComponent-myRep","listTable-myRep","checkall-myRep","checkreverse-myRep","searchBtn-myRep","seachBtnValue-myRep","",function(model_id){
			//统计报表
			//parent.window.open("${pageContext.request.contextPath}/views/statisticplat/user_frame_reportform.jsp?modelid="+model_id);
			show_statistic_page(model_id); 
			
		},null,function(model_id,reult_eoms){quitMyRep(model_id,reult_eoms);},function(model_id,reult_eoms){addMyRep(model_id,reult_eoms);},function(model_id,reult_eoms){addShare(model_id,reult_eoms);}).initData();
		

		//加载报表分享
		new loadPageComponentShare(5,share_url,1,columnOptions_share,"pageComponent-share","listTable-share","checkall-share","checkreverse-share","searchBtn-share","seachBtnValue-share","",function(model_id){
			//统计报表
			//parent.window.open("${pageContext.request.contextPath}/views/statisticplat/user_frame_reportform.jsp?modelid="+model_id); 
			show_statistic_page(model_id);
		},null).initData();
		
		//加载报表我的分享
		new loadPageComponentMyShare(5,myshare_url,1,columnOptions_share,"pageComponent-myshare","listTable-myshare","checkall-myshare","checkreverse-myshare","searchBtn-myshare","seachBtnValue-myshare","",function(model_id){
			//统计报表
			//parent.window.open("${pageContext.request.contextPath}/views/statisticplat/user_frame_reportform.jsp?modelid="+model_id);
			show_statistic_page(model_id); 
		},null,function(model_id,reult_eoms){quitShare(model_id,reult_eoms);}).initData();
		
	});
	
	
	//加载我的报表-取消公告
	function quitMyRep(model_id,reult_eoms){
		//取消公告
			var a= new dialogComponent("公告","确认取消公告区报表","indexAddModal",null,"indexAddModelDiv");
			a.confirm_dialog(function(data){if(data==true){
				$.ajax({
					url : '${pageContext.request.contextPath}/tawStatisticModel/modelShareIn.json',
					type : 'post',
					cache : false,
					async : true,
					data:{'model_id':model_id},
					success : function(data) {
						reult_eoms.initData();
						//刷新公告列表
						new loadPageComponentCommon(url,1,columnOptions,"pageComponent-commonRep","listTable-commonRep","checkall-commonRep","checkreverse-commonRep","searchBtn-commonRep","seachBtnValue-commonRep","",function(model_id){
							show_statistic_page(model_id);
						},null).initData();
					}
				});
			}});
	}
	//加载我的报表-加入公告
	function addMyRep(model_id,reult_eoms){
		//公告
			var a= new dialogComponent("公告","确认报表至公告区","indexAddModal",null,"indexAddModelDiv");
			a.confirm_dialog(function(data){if(data==true){
				$.ajax({
					url : '${pageContext.request.contextPath}/tawStatisticModel/modelShareOut.json',
					type : 'post',
					cache : false,
					async : true,
					data:{'model_id':model_id},
					success : function(data) {
						reult_eoms.initData();
						//刷新公告列表
						new loadPageComponentCommon(url,1,columnOptions,"pageComponent-commonRep","listTable-commonRep","checkall-commonRep","checkreverse-commonRep","searchBtn-commonRep","seachBtnValue-commonRep","",function(model_id){
							show_statistic_page(model_id);
						},null).initData();
					}
				});
			}});
	}
	//加载我的报表-分享報表
	function addShare(model_id,reult_eoms){
		//分享
			var a= new dialogComponent("分享","确认分享報表","indexAddModal",null,"indexAddModelDiv");
			a.confirm_dialog(function(data){if(data==true){
				$.ajax({
					url : '${pageContext.request.contextPath}/tawStatisticModel/myModelShare.json',
					type : 'post',
					cache : false,
					async : true,
					data:{'model_id':model_id},
					success : function(data) {
						reult_eoms.initData();
						//刷新分享列表
						new loadPageComponentShare(5,share_url,1,columnOptions_share,"pageComponent-share","listTable-share","checkall-share","checkreverse-share","searchBtn-share","seachBtnValue-share","",function(model_id){
							show_statistic_page(model_id);
						},null).initData();
						//刷新我的分享列表
						new loadPageComponentMyShare(5,myshare_url,1,columnOptions_share,"pageComponent-myshare","listTable-myshare","checkall-myshare","checkreverse-myshare","searchBtn-myshare","seachBtnValue-myshare","",function(model_id){
							show_statistic_page(model_id);
						},null,function(model_id,reult_eoms){quitShare(model_id,reult_eoms);}).initData();
						//parent.location.reload();
					}
				});
			}});
	}
	
	//加载我的报表-取消分享報表
	function quitShare(model_id,reult_eoms){
		var a= new dialogComponent("分享","确认取消分享報表","indexAddModal",null,"indexAddModelDiv");
			a.confirm_dialog(function(data){if(data==true){
				$.ajax({
					url : '${pageContext.request.contextPath}/tawStatisticModel/quitMyModelShare.json',
					type : 'post',
					cache : false,
					async : true,
					data:{'model_id':model_id},
					success : function(data) {
						reult_eoms.initData();
						new loadPageComponentShare(5,share_url,1,columnOptions_share,"pageComponent-share","listTable-share","checkall-share","checkreverse-share","searchBtn-share","seachBtnValue-share","",function(model_id){
							show_statistic_page(model_id);
						},null).initData();
						//刷新我的報表
						new loadPageComponentMyRep(my_rep_url,1,columnOptions,"pageComponent-myRep","listTable-myRep","checkall-myRep","checkreverse-myRep","searchBtn-myRep","seachBtnValue-myRep","",function(model_id){
							//统计报表
							show_statistic_page(model_id);
						},null,function(model_id,reult_eoms){quitMyRep(model_id,reult_eoms);},function(model_id,reult_eoms){addMyRep(model_id,reult_eoms);},function(model_id,reult_eoms){addShare(model_id,reult_eoms);}).initData();
		
					}
				});
			}});
	}
	
	
	function show_statistic_page(modelid){
		$.ajax({
					url : '${pageContext.request.contextPath}/tawStatisticModel/getModelById.json',
					type : 'post',
					cache : false,
					async : true,
					data:{'model_id':modelid},
					success : function(data) {
					
						var choose=data.retJson.tawStatisticModel.chooseBefore;
						var xmlName=data.retJson.tawStatisticModel.xmlName;
						
						if('xml'==data.retJson.tawStatisticModel.modelType){
							if('yes'==choose){
								if('statistic-plat-sql-kpi-config'==xmlName){
									parent.window.open("${pageContext.request.contextPath}/views/statisticplat/user_frame_condition_xml_dict.jsp?modelid="+modelid);
								}else if('statistic-plat-sql-config'==xmlName){
									parent.window.open("${pageContext.request.contextPath}/views/statisticplat/user_frame_condition_xml_dept.jsp?modelid="+modelid);
								}else{
									parent.window.open("${pageContext.request.contextPath}/views/statisticplat/user_frame_reportform.jsp?modelid="+modelid);
								}
							}else{
								parent.window.open("${pageContext.request.contextPath}/views/statisticplat/user_frame_reportform_xml.jsp?modelid="+modelid);	
							}	
						}else if('auto'==data.retJson.tawStatisticModel.modelType){
							if('yes'==choose){
								if('statistic-plat-sql-kpi-config'==xmlName){
									parent.window.open("${pageContext.request.contextPath}/views/statisticplat/user_frame_condition_xml_dict.jsp?modelid="+modelid);
								}else if('statistic-plat-sql-config'==xmlName){
									parent.window.open("${pageContext.request.contextPath}/views/statisticplat/user_frame_condition_xml_dept.jsp?modelid="+modelid);
								}else{
									parent.window.open("${pageContext.request.contextPath}/views/statisticplat/user_frame_reportform.jsp?modelid="+modelid);
								}
							}else{
								//parent.window.open("${pageContext.request.contextPath}/views/statisticplat/user_frame_reportform.jsp?modelid="+modelid);
								window.open("${pageContext.request.contextPath}/views/statisticplat/user_frame_reportform.jsp?modelid="+modelid);
							}
						}
					
					}
				});
		
	}
	
	function showShareRep(){
		//parent.window.open("${pageContext.request.contextPath}/views/statisticplat/main_frame_reportforms_share.jsp");
		window.open("${pageContext.request.contextPath}/views/statisticplat/main_frame_reportforms_share.jsp");
	}

	function showMyShareRep(){
		//parent.window.open("${pageContext.request.contextPath}/views/statisticplat/main_frame_reportforms_myShare.jsp");
		window.open("${pageContext.request.contextPath}/views/statisticplat/main_frame_reportforms_myShare.jsp?userid="+userid);
	}
	
</script>


</head>
<body class="statistic_body container-fluid" style="overflow-x:hidden">
<div id="indexAddModelDiv"></div>
<div id="indexAddModelDiv_add"></div>
<a href="#open_index_window" rel="indexAddModal"></a>
<div id="loadingAddModelDiv"></div>
<div id="loadingAddModelDiv_add"></div>
<a href="#open_loading_window" rel="loadingAddModal"></a>


    <div class="row">
    	<div class="col-xs-8">
    		<div class="statistic-panel">
	          <div class="statistic-conHead">
	            <ul class="flr statistic-title_list">
	            </ul>
	            <div class="w_120">公告报表区</div>
	          </div>
	          <div class="conMain mg_t18">
	            <ul class="btn_list ">
	              <li>
	                <input type="text" class="input statistic-search_input" id="seachBtnValue-commonRep" name="seachBtnValue-commonRep" size="20" placeholder="请输入报表名称"  />
	              </li>
	              <li>
	                <button class="button bg-main" id="searchBtn-commonRep" name="searchBtn-commonRep">搜索</button>
	              </li>
	            </ul>
	            <div id="listTable-commonRep"></div>
	            <div class="flr mg_t10">
	            	<ul id="pageComponent-commonRep"></ul>
	            </div>
	          </div>
	        </div>
	        
	        
	        <div class="statistic-panel">
	          <div class="statistic-conHead">
	            <div class="w_120">我的报表</div>
	          </div>
	          <div class="conMain mg_t18">
	            <ul class="btn_list ">
	              <li>
	                <input type="text" class="input statistic-search_input"  name="seachBtnValue-myRep" id="seachBtnValue-myRep" size="20" placeholder="请输入报表名称"  />
	              </li>
	              <li>
	                            
	                <button id="searchBtn-myRep" name="searchBtn-myRep"   class="button bg-main">搜索</button>
	              </li>
	            </ul>
	            <div id="listTable-myRep"></div>
	            <div class="flr mg_t10">
	            	<ul id="pageComponent-myRep"></ul>
	            </div>
	          </div>
	        </div>
	        
	        
    	</div>
    	
    	<!-- 报表分享 -->
    	<div class="col-xs-4">
    		<!-- 报表分享区 -->
    		 <div class="statistic-panel">
	          <div class="statistic-conHead">
	            <div>报表分享区 <a style="margin-right:2px" onclick="showShareRep()">More</a></div>
	          </div>
	          <div class="conMain mg_t18">
	          	<ul class="btn_list ">
	          		<li>
			            <input type="text" class="input statistic-search_input"  name="seachBtnValue-share" id="seachBtnValue-share" size="15" placeholder="请填写网址"  />
			         </li>
			         <li>                 
			           <button id="searchBtn-share" name="searchBtn-share"   class="button bg-main">搜索</button>
			         </li>
	          	</ul>
	            <div id="listTable-share"></div>
	            <div class="flr mg_t10" style="display:none">
	            	<ul id="pageComponent-share"></ul>
	            </div>
	          </div>
	        </div>
	        
	        <!-- 我的分享 -->
	        <div class="statistic-panel" style="margin-top:50px">
	          <div class="statistic-conHead">
	            <div>我的分享 <a style="margin-right:2px" onclick="showMyShareRep()">More</a></div>
	          </div>
	          <div class="conMain mg_t18">
	          	<ul class="btn_list ">
	          		<li>
			            <input type="text" class="input statistic-search_input"  name="seachBtnValue-myshare" id="seachBtnValue-myshare" size="15" placeholder="请填写网址"  />
			         </li>
			         <li>                 
			           <button id="searchBtn-myshare" name="searchBtn-myshare"   class="button bg-main">搜索</button>
			         </li>
	          	</ul>
	            <div id="listTable-myshare"></div>
	            <div class="flr mg_t10" style="display:none">
	            	<ul id="pageComponent-myshare"></ul>
	            </div>
	          </div>
	        </div>
    	</div>
    	
    	
	 </div>
</body>
</html>
