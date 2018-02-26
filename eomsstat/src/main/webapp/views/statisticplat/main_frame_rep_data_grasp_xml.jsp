<%@include file="../common/header.jsp"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%> 
<%
  String condition=request.getParameter("condition");
  String grapNum=request.getParameter("grapNum");
  String beginTime=request.getParameter("beginTime");
  String endTime=request.getParameter("endTime");
  String modelId=request.getParameter("modelid");
  
  request.setAttribute("condition", condition);
  request.setAttribute("grapNum", grapNum);
  request.setAttribute("beginTime", beginTime);
  request.setAttribute("endTime", endTime);
  request.setAttribute("modelid", modelId);
 %>


<html>
	<head>
	    <meta charset="UTf-8">
		<title>统计平台-数据详单</title>
		<script type="text/javascript">
		    
		   
			$(document).ready(function(){
				 //获取详单攥取列表哦
				initGraspList("1");
			});
			
			
			function initGraspList(page){
				$.ajax({
					url : '${pageContext.request.contextPath}/tawStatisticReport/graspDataPageXml.json',
					type : 'post',
					cache : false,
					async : true,
					dataType: "json",
					data:{'model_id':'${modelid}','beginTime':'${beginTime}','endTime':'${endTime}','grapNum':'${grapNum}','page':page,'condition':$("#condtion").val()},
					success : function(data) {
						//
						var tempDetailItemList=data.retJson.tempDetailItemList;
						var rowData=data.retJson.rows;
						var totalPage=data.retJson.total
						
						genenrateGraspTable(tempDetailItemList,rowData,data.retJson.tempDetail);
						initPag(page,15,totalPage)
					}
				});
			}
			
			
			//攥取页面的拼接
			function genenrateGraspTable(tempDetailItemList,rowData,tempDetail){
				
				$("#report_table_grasp").empty();
				
				var title="<tr><th>序号</th>";
				
				for(var i=1;i<tempDetailItemList.length;i++){
				
					title=title+"<th>"+tempDetailItemList[i].detailColumnChina+"</th>"
				}
				title=title+"</tr>";
				
				$("#report_table_grasp").append(title);
				
				
				var contend=""
				for(var i=0;i<rowData.length;i++){
					if(i%2==0){
						contend="<tr class='tablerowodd'><td>"+(i+1)+"</td>";
						for(var j=1;j<rowData[i].length;j++){
							if(j==1){
								contend=contend+"<td><a target='_blank' href='"+tempDetail.url+rowData[i][0]+"'>"+rowData[i][j]+"</a></td>";
							}else{
								contend=contend+"<td>"+rowData[i][j]+"</td>";
							}
						}
						contend=contend+"</tr>";
					}else{
						contend="<tr class='tableroweven'><td>"+(i+1)+"</td>";
						for(var j=1;j<rowData[i].length;j++){
							if(j==1){
								contend=contend+"<td><a target='_blank' href='"+tempDetail.url+rowData[i][0]+"'>"+rowData[i][j]+"</a></td>";
							}else{
								contend=contend+"<td>"+rowData[i][j]+"</td>";
							}
						}
						contend=contend+"</tr>";
					}
					$("#report_table_grasp").append(contend);
				}
				
			
			}
			
			
			function initPag(currentPage,pageSize,totalPage){
				$("#pageComponent_data_grasp").empty();
				 var options = {
				 	bootstrapMajorVersion:3,
		            currentPage: currentPage, 
		            numberOfPages:pageSize, 
		            totalPages: totalPage,  
		            size:"nomal",  
		            alignment:"center",  
		            itemTexts: function (type, page, current) {   
		                switch (type) {  
		                    case "first":  
		                        return "首页";  
		                    case "prev":  
		                        return "&lt;";  
		                    case "next":  
		                        return "&gt;";
		                    case "last":  
		                        return "尾页";
		                    case "page":  
		                        return  page;
		                }                 
		            },  
		            onPageClicked: function (e, originalEvent, type, page) {
		                //刷新数据
		              initGraspList(page);
		               //return page;
		            }  
		        }
				$("#pageComponent_data_grasp").bootstrapPaginator(options); 
			}
			
			
		</script>
	</head>
    <body class="statistic_body container">
	
		<div class="row">
			<div class="col-md-12 col-md-offset-1">
			
				<div class="row">
					<div class="col-md-12">
						<div id="Header"></div>
					</div>
				</div>
				<div class="row" id="mainContent">
					
					<div class="statistic-panel">
			          <div class="statistic-conHead">
			            <div class="w_150"><span id="panel_title">统计报表-详单</span></div>
			          </div>
					<br/>
					<input type="hidden" id="condtion" value="<%=condition%>"/>
					<table class="result-tab" id="report_table_grasp">
					</table>
					
					<div  class="flr mg_t10"><!--<img src="${imagePath}/page_pic.png" /> -->
						<ul id="pageComponent_data_grasp"></ul>
					</div>
				
				</div>
			</div>
		</div>
	</body>
</html>