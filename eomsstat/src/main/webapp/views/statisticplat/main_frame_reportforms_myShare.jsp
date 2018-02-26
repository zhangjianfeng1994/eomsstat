<%@include file="../common/header.jsp"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String modelid=request.getParameter("modelid");
request.setAttribute("modelid", modelid);
String userid=request.getParameter("userid");
request.setAttribute("userid", userid);
 %>
<html>
<head>
<title>我的报表分享</title>
<link rel="stylesheet" type="text/css" href="${cssPath}/maskLayer.css">
<script type="text/javascript">
			var userid = "${userid}";
			//alert(userid);
			$(document).ready(function(){
					//
					var columnOptions_share={"modelName":"模板名称","cycle":"模板粒度","isShareFlag":"模板共享","isCommonFlag":"公共模板","groupType":"报表归属"};
					var myshare_url='${pageContext.request.contextPath}/tawStatisticModel/modelCollectionShare.json?currentUserId='+userid;
					//加载报表我的分享
				new loadPageComponentMyShare(5,myshare_url,1,columnOptions_share,"pageComponent-myshare","listTable-myshare","checkall-myshare","checkreverse-myshare","searchBtn-myshare","seachBtnValue-myshare","",function(model_id){
					//统计报表
					parent.window.open("${pageContext.request.contextPath}/views/statisticplat/user_frame_reportform.jsp?modelid="+model_id); 
				},null,function(model_id,reult_eoms){quitShare(model_id,reult_eoms);}).initData();
					
			});
			
			
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
			
			
		</script>

</head>
<body>
<!-- 指标新增begin -->
<div id="indexAddModelDiv"></div>
<div id="indexAddModelDiv_add"></div>
<a href="#open_index_window" rel="indexAddModal"></a>
<!-- 指标新增end -->

	<div id="mainWrap">
		<div id="header_user">
			<div class="flr mg_r10 mg_t30"></div>
		</div>

		<div id="mainContent_user" class="fixed">
			<ul class="nav nav-tabs">
			</ul>
		</div>




		<div class="conMain mg_t18" style="height:auto">
			
	          	<ul class="btn_list ">
	          		<li>
			            <input type="text" class="input statistic-search_input"  name="seachBtnValue-myshare" id="seachBtnValue-myshare" size="15" placeholder="请填写网址"  />
			         </li>
			         <li>                 
			           <button id="searchBtn-myshare" name="searchBtn-myshare"   class="button bg-main">搜索</button>
			         </li>
	          	</ul>
	            <div id="listTable-myshare"></div>
	        
		</div>
		<div class="flr mg_t10">
	           <ul id="pageComponent-myshare"></ul>
	     </div>



	</div>
</body>
</html>