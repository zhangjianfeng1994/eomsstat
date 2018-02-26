<%@include file="../common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%> 
<%@ page import="java.net.URLDecoder"%> 
<%
  String indexId=request.getParameter("indexId");
  String demensionList=request.getParameter("demensionList");
  demensionList = URLDecoder.decode(demensionList, "utf-8"); 
  String modelId=request.getParameter("modelId");
  String dimensionCondition=request.getParameter("dimensionCondition");
  String graspDemensionCondition=request.getParameter("graspDemensionCondition");
  String condition=request.getParameter("condition");
  String dataType=request.getParameter("dataType");
  String begintime=request.getParameter("beginTime");
  String endTime=request.getParameter("endTime");
  
  
  
  request.setAttribute("indexId", indexId);
  request.setAttribute("modelId", modelId);
  request.setAttribute("dimensionCondition", dimensionCondition);
  request.setAttribute("graspDemensionCondition", graspDemensionCondition);
  request.setAttribute("demensionList", demensionList);
  request.setAttribute("condition", condition);
  request.setAttribute("dataType", dataType);
  request.setAttribute("begintime", begintime);
  request.setAttribute("endTime", endTime);
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
				//alert(JSON.stringify(${demensionList}));
				//alert("${dimensionCondition}");
				$.ajax({
					url : '${pageContext.request.contextPath}/tawStatisticReport/graspDataPage.json',
					type : 'post',
					cache : false,
					async : true,
					dataType: "json",
					data:{'begintime':'${begintime}','endTime':'${endTime}','dataType':'${dataType}','indexId':'${indexId}','demensionList':'${demensionList}','modelId':'${modelId}','dimensionCondition':"${dimensionCondition}",'graspDemensionCondition':"${graspDemensionCondition}",'page':page,'condition':$("#condtion").val()},
					success : function(data) {
						//alert(JSON.stringify(data));
						var tempDetailItemList=data.retJson.tempDetailItemList;
						var rowData=data.retJson.rows;
						var totalPage=data.retJson.total;
						var url=data.retJson.url;
						
						genenrateGraspTable(tempDetailItemList,rowData,url);
						initPag(page,15,totalPage)
					}
				});
			}
			
			
			//攥取页面的拼接
			function genenrateGraspTable(tempDetailItemList,rowData,url){
				//console.log(tempDetailItemList);
				//console.log(rowData);
				$("#report_table_grasp").empty();
				
				var title="<tr><th width='40px'>序号</th>";
				
				for(var i=0;i<tempDetailItemList.length;i++){
				   if(i>0){
				   	if(i==1){
				   		title=title+"<th width='140px'>"+tempDetailItemList[i].detailColumnChina+"</th>";
				   	}else{
				   		title=title+"<th>"+tempDetailItemList[i].detailColumnChina+"</th>";
				   		}
					}
				}
				title=title+"</tr>";
				
				$("#report_table_grasp").append(title);
				
				
				var contend="";
				for(var i=0;i<rowData.length;i++){
					if(i%2==0){
						contend="<tr class='tablerowodd'><td>"+(i+1)+"</td>";
						for(var j=0;j<rowData[i].length;j++){
							if(j==1){
								contend=contend+"<td><a target='_blank' href='"+url+rowData[i][0]+"'>"+rowData[i][j]+"</a></td>";
							}else if(j>1){
								contend=contend+"<td>"+rowData[i][j]+"</td>";
							}
						}
						contend=contend+"</tr>";
					}else{
						contend="<tr class='tableroweven'><td>"+(i+1)+"</td>";
						for(var j=0;j<rowData[i].length;j++){
							if(j==1){
								contend=contend+"<td><a target='_blank' href='"+url+rowData[i][0]+"'>"+rowData[i][j]+"</a></td>";
							}else if(j>1){
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
		            numberOfPages:10, 
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
			
			//导出
			function excelExport(){
				var begintime = '${begintime}';
				var endTime = '${endTime}';
				var dataType = '${dataType}';
				var indexId = '${indexId}';
				var demensionList = '${demensionList}';
				var modelId = '${modelId}';
				var dimensionCondition = "${dimensionCondition}";
				var graspDemensionCondition = "${graspDemensionCondition}";
				var condition = $("#condtion").val();
				$("#exportBegintime").val(begintime);
				$("#exportEndTime").val(endTime);
				$("#exportDataType").val(dataType);
				$("#exportIndexId").val(indexId);
				$("#exportDemensionList").val(demensionList);
				$("#exportModelId").val(modelId);
				$("#exportDimensionCondition").val(dimensionCondition);
				$("#exportGraspDemensionCondition").val(graspDemensionCondition);
				$("#exportCondition").val(condition);
				$("#excelExportForm").submit();
			}
			
		</script>
	</head>
    <body class="statistic_body container">
    	<form id="excelExportForm" method="post" action="${pageContext.request.contextPath}/tawStatisticReport/detailExcelExport.json" >
			<input type="hidden" id="exportBegintime" name="begintime" value=""/>
			<input type="hidden" id="exportEndTime" name="endTime" value=""/>
			<input type="hidden" id="exportDataType" name="dataType" value=""/>
			<input type="hidden" id="exportIndexId" name="indexId" value=""/>
			<input type="hidden" id="exportDemensionList" name="demensionList" value=""/>
			<input type="hidden" id="exportModelId" name="modelId" value=""/>
			<input type="hidden" id="exportDimensionCondition" name="dimensionCondition" value=""/>
			<input type="hidden" id="exportGraspDemensionCondition" name="graspDemensionCondition" value=""/>
			<input type="hidden" id="exportCondition" name="condition" value=""/>
		</form>
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
					<input type="hidden" id="demensionList" value="<%=demensionList%>"/>
					<input type="hidden" id="condtion" value="<%=condition%>"/>
					<table class="result-tab" id="report_table_grasp">
					</table>
					<div class="pull-left" style="margin-top:28px;">
						<button class="button bg-main" onclick="excelExport();">导出</button>
					</div>
					<div class="flr mg_t10 pull-right"><!--<img src="${imagePath}/page_pic.png" /> -->
						<ul id="pageComponent_data_grasp"></ul>
					</div>
				
				</div>
			</div>
		</div>
	</body>
</html>