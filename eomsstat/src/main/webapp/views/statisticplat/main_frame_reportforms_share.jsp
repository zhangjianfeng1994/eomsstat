<%@include file="../common/header.jsp"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String modelid=request.getParameter("modelid");
request.setAttribute("modelid", modelid);
 %>
<html>
<head>

<title>报表分享区</title>
<link rel="stylesheet" type="text/css" href="${cssPath}/maskLayer.css">
<script type="text/javascript">
			$(document).ready(function(){
					//
					//加载报表分享
				var columnOptions_share={"modelName":"模板名称","cycle":"模板粒度","isShareFlag":"模板共享","isCommonFlag":"公共模板","groupType":"报表归属"};
				var share_url='${pageContext.request.contextPath}/tawStatisticModel/modelCollectionShare.json';
				//加载报表分享
				new loadPageComponentShare(5,share_url,1,columnOptions_share,"pageComponent-share","listTable-share","checkall-share","checkreverse-share","searchBtn-share","seachBtnValue-share","",function(model_id){
					//统计报表
					parent.window.open("${pageContext.request.contextPath}/views/statisticplat/user_frame_reportform.jsp?modelid="+model_id); 
				},null).initData();
					
			});
			
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
			<!-- 报表分享区 -->
	          	<ul class="btn_list ">
	          		<li>
			            <input type="text" class="input statistic-search_input"  name="seachBtnValue-share" id="seachBtnValue-share" size="15" placeholder="请填写网址"  />
			         </li>
			         <li>                 
			           <button id="searchBtn-share" name="searchBtn-share"   class="button bg-main">搜索</button>
			         </li>
	          	</ul>
	            <div id="listTable-share"></div>
		</div>
		 <div class="flr mg_t10">
	           <ul id="pageComponent-share"></ul>
	      </div>



	</div>
</body>
</html>