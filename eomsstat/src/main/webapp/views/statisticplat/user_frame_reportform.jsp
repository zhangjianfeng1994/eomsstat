<%@include file="../common/header.jsp"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String userid=request.getParameter("userName");
request.setAttribute("userid", userid);
System.out.println("----------------------------userid:"+userid);
/*
if(userid == null||"".equals(userid)){
	response.sendRedirect("http://10.238.78.149/eoms35/"); 
}
*/
String modelid=request.getParameter("modelid");
request.setAttribute("modelid", modelid);
 %>
<html>
<head>
<meta charset="UTf-8">
<title>统计平台</title>
<link rel="stylesheet" type="text/css" href="${cssPath}/maskLayer.css">
<style>
	.laydate_box, .laydate_box * {  
		box-sizing:content-box;  
	}  
</style>
<script src="${pageContext.request.contextPath}/resources/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
			var currentModelItems="";
			var currentGraspDemensionCondition="";
			$(document).ready(function(){
					laydate.skin('yalan');
					var commondata=null;
					$("#day_report").slideDown("slow");
					$("#month_report").hide();
					$("#selfDefine_report").hide();
					$("#newMonthReport_graph").hide();
					graph_hide();
					
			});
			
			function checkAll(){
				$(".indicators").each(function(index,checkObj){
					//$(this).attr("checked")
					if ($(checkObj).is(":checked")) {  
						$(checkObj).prop("checked", true);
				    }else {  
				    	$(checkObj).prop("checked", true);  
				    } 
				}); 
			}
			
			function checkReverse(){
				$(".indicators").each(function(index,checkObj){
					//$(this).attr("checked")
					if ($(checkObj).is(":checked")) {  
						$(checkObj).prop("checked", false);
				    }else {  
				    	$(checkObj).prop("checked", true);  
				    } 
				}); 
			}
			
			function checkAll1(){
				$(".parameters").each(function(index,checkObj){
					//$(this).attr("checked")
					if ($(checkObj).is(":checked")) {  
						$(checkObj).prop("checked", true);
				    }else {  
				    	$(checkObj).prop("checked", true);  
				    } 
				}); 
			}
			
			function checkReverse1(){
				$(".parameters").each(function(index,checkObj){
					//$(this).attr("checked")
					if ($(checkObj).is(":checked")) {  
						$(checkObj).prop("checked", false);
				    }else {  
				    	$(checkObj).prop("checked", true);  
				    } 
				}); 
			}
			
			var sortFlag="dayReport";
			//初始化时间
			function timeInit(type){
				var myDate = new Date();
				var year=myDate.getFullYear();
				var month=myDate.getMonth();
				var day=myDate.getDate();
				if(type=="initDay"){
				myDate = new Date(myDate.getTime()- 3*24*60*60*1000)//24*60*60*1000一天的时间
				 year=myDate.getFullYear();
				 month=myDate.getMonth()+1;
				 day=myDate.getDate();
					if(day<10){
						day="0"+day;
					}
					if(month<10){
						month="0"+month;
					}
					var time=year+"-"+month+"-"+day;
					//alert(time);
					$("#dayReportTime").val(time);
					$("#dayReportTime").bind("click",function(){
						laydate({elem: '#dayReportTime',istime: true, format: 'YYYY-MM-DD'});
					});
				}else if(type=="initMonth"){
					myDate = new Date(myDate.getTime()- 30*24*60*60*1000)//24*60*60*1000一天的时间
			    	year=myDate.getFullYear();
				    month=myDate.getMonth()+1;
					var time=year+"-"+month;
					$("#month_rep_year").val(year);
					$("#month_rep_month").val(month);
					$("#monthReportTime").bind("click",function(){
						laydate({elem: '#monthReportTime',istime: true, format: 'YYYY-MM'});
					});
				}else if(type=="initSelfDefine"){
					var beginDay=day;
					month = month+1; 
					if(beginDay<10){
						beginDay="0"+beginDay;
					}
					if(month<10){
						month="0"+month;
					}
					var beginTime=year+"-"+month+"-"+beginDay;
					
					//var endDay=day+1;
					//if(endDay<10){
						//endDay="0"+endDay;
					//}
					//var endTime=year+"-"+month+"-"+endDay;
					var endTime=addDay(beginTime);
					//alert(endTime);
					$("#self_define_begin_time").val(beginTime);
					$("#self_define_end_time").val(endTime);
					$("#self_define_begin_time").bind("click",function(){
						laydate({elem: '#self_define_begin_time',istime: true, format: 'YYYY-MM-DD'});
					});
					$("#self_define_end_time").bind("click",function(){
						laydate({elem: '#self_define_end_time',istime: true, format: 'YYYY-MM-DD'});
					});
				}
			}
			
			function graph_hide(){
				//图形化隐藏
				$("#dayReport_graph").hide();
				$("#monthReport_graph").hide();
				$("#selfDefineReport_graph").hide();
			}
			
			//图形化的修改
			
			function tab_change(param,obj){
				$("#mainContent_user>.nav.nav-tabs").children("li").each(function(index,li_obj){  
		   			$(li_obj).removeClass("active");
				});
				
				$(obj).parent("li").addClass("active");
					if("3"==param){
						$("#day_report").slideDown("slow");
						$("#month_report").hide();
						$("#selfDefine_report").hide();
						sortFlag="dayReport";
						graph_hide();
					}else if("2"==param){
						$("#month_report").slideDown("slow");
						$("#day_report").hide();
						$("#selfDefine_report").hide();
						sortFlag="monthReport";
						graph_hide();
					}else if("1"==param){
						$("#selfDefine_report").slideDown("slow");
						$("#day_report").hide();
						$("#month_report").hide();
						sortFlag="selfDefineReport";
						graph_hide();
					}
					
					
					
				$(".checkbox_list_graph").each(function(index,checkObj){
			 		$(checkObj).removeAttr("checked");
			 	});
			 	$(".checkbox_list_graph_complex").each(function(index,checkObj){
			 		$(checkObj).removeAttr("checked"); 
			 	});
					
			}
			//报表环比
			function generate_Mom_report(modelId,modelItems,beginTime,endTime,report_table_div,demensionCondition,paramEng,direction){
					new dialogComponent("","","","","loadingAddModelDiv").loading_dialog();
					$.ajax({
						url : '${pageContext.request.contextPath}/tawStatisticReport/getReportMom.json',
						type : 'post',
						cache : false,
						async : true,
						data:{'model_id':modelId,'data_screen':modelItems,'beginTime':beginTime,'endTime':endTime,'report_table_div':report_table_div,'demensionCondition':demensionCondition,'paramEng':paramEng,'direction':direction},
						success : function(data) {
							commondata=data;
							//alert(JSON.stringify(commondata));
							$("#"+report_table_div).empty();
							
							var tableComponents=new Array();
							var layer=data.retJson.treeDeep;
							//复合表格标题数据处理
							generateTitle(data.retJson.retComplexTitle,tableComponents,data.retJson.treeDeep);
							if(0==layer){
								//生成单个表头
								generatePureTitle(data.retJson,layer,report_table_div);
							}else{
								//生成复合表头
								generateComplexTitle(data.retJson,tableComponents,layer,report_table_div,null,null,"mom");
							}
							//生成表格内容
							
						    generateRateTableContent(modelId,data.retJson.retData,data.retJson.retIndex,data.retJson.retDemension,data.retJson.retComplexTitle,data.retJson.retComplexGrsp,report_table_div,data.retJson.retTransferDimension,"mom");
							//图形报表
							//add_graph_area();
						    new dialogComponent("","","","","loadingAddModelDiv").loading_dialog_hide();
						}
					});
			
			}
			
			//报表环比增长率
			function generate_Mom_Rate_report(modelId,modelItems,beginTime,endTime,report_table_div,demensionCondition,paramEng,direction){
				new dialogComponent("","","","","loadingAddModelDiv").loading_dialog();
				$.ajax({
					url : '${pageContext.request.contextPath}/tawStatisticReport/getReportMomRate.json',
					type : 'post',
					cache : false,
					async : true,
					data:{'model_id':modelId,'data_screen':modelItems,'beginTime':beginTime,'endTime':endTime,'report_table_div':report_table_div,'demensionCondition':demensionCondition,'paramEng':paramEng,'direction':direction},
					success : function(data) {
						commondata=data;
						$("#"+report_table_div).empty();
						
						var tableComponents=new Array();
						var layer=data.retJson.treeDeep;
						//复合表格标题数据处理
						generateTitle(data.retJson.retComplexTitle,tableComponents,data.retJson.treeDeep);
						if(0==layer){
							//生成单个表头
							generatePureTitle(data.retJson,layer,report_table_div);
						}else{
							//生成复合表头
							generateComplexTitle(data.retJson,tableComponents,layer,report_table_div,null,null,"momRate");
						}
						//生成表格内容
						
					    generateRateTableContent(modelId,data.retJson.retData,data.retJson.retIndex,data.retJson.retDemension,data.retJson.retComplexTitle,data.retJson.retComplexGrsp,report_table_div,data.retJson.retTransferDimension,"momRate");
						
						//图形报表
					    new dialogComponent("","","","","loadingAddModelDiv").loading_dialog_hide();
					
					}
				});
		
		}
		
		//生成报表环比增长率表格数据
			function generateRateTableContent(modelid,retData,index,demension,complexs,grasp,report_table_div,transfer,valueType){
				var valueFlag = 0;
				if(valueType=="mom"){
					valueFlag = 100;
				}else if(valueType=="momRate"){
					valueFlag = 0;
				}
				var table_content="";
				var model_id=modelid;
				//转化复合表头为Map
				for(var i=0;i<retData.length;i++){
					var tr_line=retData[i];
					if(i%2==0){
						table_content=table_content+"<tr  class='tablerowodd'>";
					}else{
						table_content=table_content+"<tr class='tableroweven'>";
					}
					for(var j=0;j<retData[i].length;j++){
						var tempDemensionLen=demension.length;
						var tempIndexLen=index.length;
						var demensionList=new Array();
						//获取所有的维度
						//alert(JSON.stringify(demension));
						for(var m=0;m<demension.length;m++){
							//alert(JSON.stringify(retData[i][m]));
							var temp_dataGraph=new dataGraph(demension[m].paramEng,retData[i][m]);
							demensionList.push(temp_dataGraph);
						}
						if(j<tempDemensionLen){
						    //维度数据攥取
							//数据转化为中文
						    var temptrans=transfer[demension[j].id][retData[i][j]];
						    var retDataValue=(typeof(temptrans)=='undefined')?(""+retData[i][j]+""):temptrans;
							table_content=table_content+"<td>"+retDataValue+"</td>";
						}else{
							//指标数据攥取
							//获取指标
							//第一指标
							if(j<(tempDemensionLen+tempIndexLen)){
							    //alert(JSON.stringify(demensionList));
							    var num=j-tempDemensionLen;
								var indexId=index[num].id;
							    retDataValue=Number(retData[i][j]);
							    //alert(retData[i][j]);
							    var temdata = retData[i][j];
							    var tempparsedata = parseFloat(temdata);
							    //alert(tempparsedata);
							    if(temdata!=="~"){
							    	if(tempparsedata<valueFlag){
								    	table_content=table_content+"<td>"+temdata+"<span class='flr'><a style='cursor: auto;' class='pd_r10'><img src='${imagePath}/decrease.png' /></a></span></td>";
								    }else if(tempparsedata>valueFlag){
								    	table_content=table_content+"<td>"+temdata+"<span class='flr'><a style='cursor: auto;' class='pd_r10'><img src='${imagePath}/increase.png' /></a></span></td>";
								    }else{
								    	table_content=table_content+"<td>"+temdata+"<span class='flr'><a style='cursor: auto;' class='pd_r10'><img src='${imagePath}/stable.png' /></a></span></td>";
								    }
							    }else{
							    	table_content=table_content+"<td>"+temdata+"<span class='flr'><a style='cursor: auto;' class='pd_r10'><img src='${imagePath}/stable.png' /></a></span></td>";
							    }
							}else{
								var num=j-tempDemensionLen-tempIndexLen+1;
								//alert(num);
								var indexId="";
								var condition="";
								for(var count=0;count<grasp.length;count++){
									var tempGrasp=grasp[count];
									if(num==tempGrasp.countNum){
										indexId=tempGrasp.indexId;
										condition=tempGrasp.condition;
									}
								}
							    retDataValue=Number(retData[i][j]);
							    //alert(retData[i][j]);
							    var temdata = retData[i][j];
							    var tempparsedata = parseFloat(temdata);
							    if(temdata!=="~"){
							    	//temdata = temdata*100;
							    	if(tempparsedata<valueFlag){
								    	table_content=table_content+"<td>"+temdata+"<span class='flr'><a style='cursor: auto;' class='pd_r10'><img src='${imagePath}/decrease.png' /></a></span></td>";
								    }else if(tempparsedata>valueFlag){
								    	table_content=table_content+"<td>"+temdata+"<span class='flr'><a style='cursor: auto;' class='pd_r10'><img src='${imagePath}/increase.png' /></a></span></td>";
								    }else{
								    	table_content=table_content+"<td>"+temdata+"<span class='flr'><a style='cursor: auto;' class='pd_r10'><img src='${imagePath}/stable.png' /></a></span></td>";
								    }
							    }else{
							    	table_content=table_content+"<td>"+temdata+"<span class='flr'><a style='cursor: auto;' class='pd_r10'><img src='${imagePath}/stable.png' /></a></span></td>";
							    }
							}
							//复合指标
						}
					}
					table_content=table_content+"</tr>";
				}
							
				$("#"+report_table_div).append(table_content);
			}
		
			//强制保留2位小数，如：2，会在2后面补上00.即2.00 
			function toDecimal(x) { 
			      var f = parseFloat(x); 
			      if (isNaN(f)) { 
			        return false; 
			      } 
			      var f = Math.round(x*100)/100; 
			      var s = f.toString(); 
			      var rs = s.indexOf('.'); 
			      if (rs < 0) { 
			        rs = s.length; 
			        s += '.'; 
			      } 
			      while (s.length <= rs + 2) { 
			        s += '0'; 
			      } 
			      return s; 
			    } 
			
			var  parent_graspDemensionCondition = "null";
			var  total_modelItems = "null";
			//统计报表公共
			function generate_common_report(type,modelId,modelItems,beginTime,endTime,report_table_div,demensionCondition,paramEng,direction,graspDemensionCondition){
					//返回时记录上一级的graspDemensionCondition
					parent_graspDemensionCondition = graspDemensionCondition;
					//返回时记录上一级之前所有的modelItems
					if(type == 0){
						if(graspDemensionCondition == "null"||graspDemensionCondition == null){
							total_modelItems = "null";
						}else{
							total_modelItems = total_modelItems+";"+modelItems;
						}
					}
					//alert(total_modelItems);
					//加载框
					new dialogComponent("","","","","loadingAddModelDiv").loading_dialog();
					$.ajax({
						url : '${pageContext.request.contextPath}/tawStatisticReport/getReport.json',
						type : 'post',
						cache : false,
						async : true,
						data:{'model_id':modelId,'data_screen':modelItems,'beginTime':beginTime,'endTime':endTime,'demensionCondition':demensionCondition,'paramEng':paramEng,'direction':direction,'graspDemensionCondition':graspDemensionCondition},
						success : function(data) {
							
							//alert(JSON.stringify(data));
							//console.log(JSON.stringify(data));
							//alert(data.retJson.retItemIndexStr+"--"+data.retJson.retItemDemensionStr);
							commondata=data;
							currentModelItems = commondata.retJson.modelItems;
							currentGraspDemensionCondition = data.retJson.graspDemensionCondition;
							$("#"+report_table_div).empty();
							
							var tableComponents=new Array();
							var layer=data.retJson.treeDeep;
							//复合表格标题数据处理
							generateTitle(data.retJson.retComplexTitle,tableComponents,data.retJson.treeDeep);
							if(0==layer){
								//生成单个表头
								generatePureTitle(data.retJson,layer,report_table_div);
							}else{
								//生成复合表头
								//alert(data.retJson.demensionCondition);
								generateComplexTitle(data.retJson,tableComponents,layer,report_table_div,data.retJson.modelItems,data.retJson.demensionCondition,"common",data.retJson.graspDemensionCondition);
							}
							//生成表格内容
							
							//alert("加载表格主题start！");
							//alert(data.retJson.demensionCondition);
						    generateTableContent(data.retJson.dataType,beginTime,endTime,data.retJson.modelItems,modelId,data.retJson.retData,data.retJson.retIndex,data.retJson.retDemension,data.retJson.retComplexTitle,data.retJson.retComplexGrsp,report_table_div,data.retJson.retTransferDimension,data.retJson.demensionCondition,data.retJson.graspDemensionCondition);
						    //alert("加载表格主题end！");
						    //alert("页面加载完成！");
							//图形报表
							//add_graph_area();
							new dialogComponent("","","","","loadingAddModelDiv").loading_dialog_hide();
						}
					});
			
			}
			//维度攥取数据
			function generate_common_demension(dataValue,modelId,modelItems,beginTime,endTime,report_table_div,graspDemensionCondition){
			//alert(graspDemensionCondition);
				//加载框
				new dialogComponent("","","","","loadingAddModelDiv").loading_dialog();
				$.ajax({
						url : '${pageContext.request.contextPath}/tawStatisticReport/getReport.json',
						type : 'post',
						cache : false,
						async : true,
						data:{'model_id':modelId,'data_screen':modelItems,'beginTime':beginTime,'endTime':endTime,'demesion_grasp_value':dataValue,'graspDemensionCondition':graspDemensionCondition},
						success : function(data) {
							commondata=data;
							$("#"+report_table_div).empty();
							
							var tableComponents=new Array();
							var layer=data.retJson.treeDeep;
							//复合表格标题数据处理
							generateTitle(data.retJson.retComplexTitle,tableComponents,data.retJson.treeDeep);
							if(0==layer){
								//生成单个表头
								generatePureTitle(data.retJson,layer,report_table_div);
							}else{
								//生成复合表头
								generateComplexTitle(data.retJson,tableComponents,layer,report_table_div);
							}
							//生成表格内容
						    generateTableContent(data.retJson.dataType,beginTime,endTime,data.retJson.retItemIndexStr,modelId,data.retJson.retData,data.retJson.retIndex,data.retJson.retDemension,data.retJson.retComplexTitle,data.retJson.retComplexGrsp,report_table_div,data.retJson.retTransferDimension,graspDemensionCondition);
							//图形报表
							//add_graph_area();
							new dialogComponent("","","","","loadingAddModelDiv").loading_dialog_hide();
						}
					});
			
			}
			
			//递归遍历json串数据
			function generateTitle(row_data,tableComponents,totalLayer){
				for(var i=0;i<row_data.length;i++){
				     var tempComplex=row_data[i];
					 var tableComponent=new multiTableComponent(tempComplex.rowSpan,tempComplex.cloumnSpan,tempComplex.name,tempComplex.currentLayer);
					 tableComponents.push(tableComponent);
					 if(tempComplex.childDimension.length>0){
				     	generateTitle(tempComplex.childDimension,tableComponents,totalLayer);
				     }
				}
			}
			
			//生成单个表头
			function generatePureTitle(retjson,layer,report_table_div){
				var table_title="<tr>";
				for(var m=0;m<retjson.retDemension.length;m++){
					table_title=table_title+"<th rowspan='"+layer+"'>"+retjson.retDemension[m].paramName+"</th>";
				}
				for(var m=0;m<retjson.retIndex.length;m++){
					table_title=table_title+"<th rowspan='"+layer+"'>"+retjson.retIndex[m].indicateName+"</th>";
				}
				$("#"+report_table_div).append(table_title);
			
			}
			
			//排序
			function generateSortedReport(paramEng,direction,modelItems,demensionCondition,sortType,graspDemensionCondition){
				//alert(graspDemensionCondition);
				var modelId='${modelid}';
				var beginTime=null;
				var endTime=null;
				var report_table_div=null;
				if(sortFlag=="dayReport"){
					beginTime=$("#dayReportTime").val();
					endTime=addDay(beginTime);
					report_table_div="dayReport";
				}else if(sortFlag=="monthReport"){
					var tempBeginTime=$("#month_rep_year").val()+"-"+$("#month_rep_month").val();
					beginTime = tempBeginTime+"-1";
					endTime=addMonth(beginTime);
					report_table_div="monthReport";
				}else if(sortFlag=="selfDefineReport"){
					beginTime=$("#self_define_begin_time").val();
					endTime=$("#self_define_end_time").val();
					report_table_div="selfDefineReport";
				}
				if(sortType=="common"){
					generate_common_report(0,modelId,modelItems,beginTime,endTime,report_table_div,demensionCondition,paramEng,direction,graspDemensionCondition);
				}else if(sortType=="mom"){
					generate_Mom_report(modelId,modelItems,beginTime,endTime,report_table_div,null,paramEng,direction);
				}else if(sortType=="momRate"){
					generate_Mom_Rate_report(modelId,modelItems,beginTime,endTime,report_table_div,null,paramEng,direction);
				}
			}
			
			
			function generateComplexTitle(retjson,tableComponents,layer,report_table_div,modelItems,demensionCondition,sortType,graspDemensionCondition){
				//alert(graspDemensionCondition);
				//根据tableComponents构造标题头
				for(var i=1;i<=layer;i++){
						var table_title="<tr>";
						//指标和纬度
						if(i==1){
							for(var m=0;m<retjson.retDemension.length;m++){
								if(sortType=="common"){
									table_title=table_title+"<th rowspan='"+layer+"'>"+retjson.retDemension[m].paramName+"<a class='glyphicon glyphicon-arrow-up' onclick=generateSortedReport(\'"+retjson.retDemension[m].paramEng+"\',\'up\',\'"+modelItems+"\',\'"+URLencode(demensionCondition)+"\',\'"+sortType+"\',\'"+URLencode(graspDemensionCondition)+"\')></a><a class='glyphicon glyphicon-arrow-down' onclick=generateSortedReport(\'"+retjson.retDemension[m].paramEng+"\',\'down\',\'"+modelItems+"\',\'"+URLencode(demensionCondition)+"\',\'"+sortType+"\',\'"+URLencode(graspDemensionCondition)+"\')></a></th>";
								}else if(sortType=="mom"){
									table_title=table_title+"<th rowspan='"+layer+"'>"+retjson.retDemension[m].paramName+"</th>";
								}else if(sortType=="momRate"){
									table_title=table_title+"<th rowspan='"+layer+"'>"+retjson.retDemension[m].paramName+"</th>";
								}
							}
							
							for(var m=0;m<retjson.retIndex.length;m++){
								if(report_table_div=="dayReport"||report_table_div=="monthReport"||report_table_div=="selfDefineReport"){
									//table_title=table_title+"<th rowspan='"+layer+"'>"+retjson.retIndex[m].indicateName+"<a class='glyphicon glyphicon-arrow-up' onclick=generateSortedReport(\'"+retjson.retIndex[m].id+"\',\'up\',\'"+modelItems+"\',\'"+demensionCondition+"\')></a><a class='glyphicon glyphicon-arrow-down' onclick=generateSortedReport(\'"+retjson.retIndex[m].id+"\',\'down\',\'"+modelItems+"\',\'"+demensionCondition+"\')></a></th>";
									table_title=table_title+"<th rowspan='"+layer+"'>"+retjson.retIndex[m].indicateName+"</th>";
								}else{
									table_title=table_title+"<th rowspan='"+layer+"'>"+retjson.retIndex[m].indicateName+"</th>";
								}
							}
						}		
						for(var j=0;j<tableComponents.length;j++){
								var tempTh=tableComponents[j];
								if(tempTh.rowNum==i){
									table_title=table_title+"<th rowspan='"+tempTh.rowSpan+"' colspan='"+tempTh.columnSpan+"'>"+tempTh.textName+"</th>";
								}			
						}
						table_title=table_title+"</tr>";
						$("#"+report_table_div).append(table_title);
				}
			}
			//生成表格数据
			function generateTableContent(dataType,beginTime,endTime,modelItems,modelid,retData,index,demension,complexs,grasp,report_table_div,transfer,dimensionCondition,graspDemensionCondition){
			//alert(modelItems);
				var table_content="";
				var model_id=modelid;
				//转化复合表头为Map
				//alert(JSON.stringify(demension));
				//alert("retData.length:"+retData.length);
				for(var i=0;i<retData.length;i++){
					var tr_line=retData[i];
					if(i%2==0){
						table_content=table_content+"<tr  class='tablerowodd'>";
					}else{
						table_content=table_content+"<tr class='tableroweven'>";
					}
					for(var j=0;j<retData[i].length;j++){
						var tempDemensionLen=demension.length;
						var tempIndexLen=index.length;
						var demensionList=new Array();
						//获取所有的维度
						for(var m=0;m<demension.length;m++){
							var temp_dataGraph=new dataGraph(demension[m].paramEng,retData[i][m]);
							demensionList.push(temp_dataGraph);
						}
						if(j<tempDemensionLen){
						    //维度数据攥取
							//数据转化为中文
						    //alert(JSON.stringify(demension[j].childrenParam));
						    if(null==demension[j].childrenParam){
						         var tempData=retData[i][j].split("≌")[0];
						    	 var temptrans=transfer[demension[j].id][tempData];
						    	 var retDataValue=(typeof(temptrans)=='undefined')?(""+tempData+""):temptrans;
						    	 if(dataType!=''){
						    	 	//table_content=table_content+"<td><a onclick=generate_common_demension('"+tempData+"','"+modelid+"','','"+beginTime+"','"+endTime+"','"+report_table_div+"','')>"+retDataValue+"</a></td>";
						    	 }else{
						    	 	//alert("生成表格td开始！");
								 	table_content=table_content+"<td>"+retDataValue+"</td>";
								 	//alert("生成表格td结束！");
								 }
						    }else{
						    	 var tempData=retData[i][j].split("≌")[0];
						    	 var temptrans=transfer[demension[j].id][tempData];
						    	 var retDataValue=(typeof(temptrans)=='undefined')?(""+tempData+""):temptrans;
						    	 var graspDemensionConditionTemp = graspDemensionCondition;
						    	 //indexItemStr=indexItemStr+","+demension[j].childrenParam.modelItemId;
						    	 //alert(demension[j].modelItemId);
						    	 //alert(demension[j].childrenParam.modelItemId);
						    	 modelItems = modelItems.replace(demension[j].modelItemId,demension[j].childrenParam.modelItemId);
						    	 //alert(modelItems);
						    	 if(tempData!="total"){
						    		 var demensionCondtionTemp=" and "+demension[j].paramEng+"='"+tempData+"'";
							    	 graspDemensionConditionTemp = graspDemensionConditionTemp+demensionCondtionTemp;
						    	 }
						    	 //alert(graspDemensionConditionTemp);
						    	 if(dataType!=''){
						    	 	//table_content=table_content+"<td><a onclick=generate_common_demension('"+tempData+"','"+modelid+"','','"+beginTime+"','"+endTime+"','"+report_table_div+"','')>"+retDataValue+"</a></td>";
						    	 }else{
						    	 	//table_content=table_content+"<td><a onclick=generate_common_demension('','"+modelid+"','"+indexItemStr+"','"+beginTime+"','"+endTime+"','"+report_table_div+"','"+URLencode(demensionCondtionTemp)+"')>"+retDataValue+"</a></td>";
						    	 	//table_content=table_content+"<td>"+retDataValue+"</td>";
						    		 table_content=table_content+"<td><a onclick='generate_common_report(0,\""+modelid+"\",\""+modelItems+"\",\""+beginTime+"\",\""+endTime+"\",\""+report_table_div+"\",\"\",\"\",\"\",\""+URLencode(graspDemensionConditionTemp)+"\")'>"+retDataValue+"</a></td>";
						    	 }
								 
						    }                                                                      
						//alert("维度加载结束！");    
						}else{
							//指标数据攥取
							//获取指标
							//第一指标
							//alert("指标加载开始！");
							if(j<(tempDemensionLen+tempIndexLen)){
							    //alert(JSON.stringify(demensionList));
							    var num=j-tempDemensionLen;
								var indexId=index[num].id;
								var isGrasp=index[num].isGrasp;
								//add by yangjiajia bengin
								var indicateOverValue=index[num].indicateOverValue;
								//add by yangjiajia end
								var tempData=retData[i][j].split("≌")[0];
								var tempCondition=retData[i][j].split("≌")[1]==undefined?"":retData[i][j].split("-")[1];
								//alert(JSON.stringify(retData[i][j]));
								//alert("tempCondition:"+tempCondition);
								var condition=index[num].condition;
								//alert("condition:"+condition);
								var complexFlag = index[num].complexFlag;
								//console.log(JSON.stringify(index[num]));
								//'0'==isGrasp时不钻取
								//'1'==complexFlag复合指标时不钻取
								if('0'==isGrasp || '1'==complexFlag){
								//统计值大于阀值时变色，否则不变色
									if(Number(tempData)>=Number(indicateOverValue)){
										table_content=table_content+"<td><font color='red'>"+tempData+"</font></td>";	
									}else{
										table_content=table_content+"<td>"+tempData+"</td>";	
									}
								}else{
								if(Number(tempData)>=Number(indicateOverValue)){
										table_content=table_content+"<td><a onclick=grasp_data('"+beginTime+"','"+endTime+"','"+dataType+"','"+model_id+"','"+indexId+"','"+URLencode(dimensionCondition)+"','"+URLencode(graspDemensionCondition)+"',"+JSON.stringify(JSON.stringify(demensionList))+",'"+URLencode(condition+tempCondition)+"')><font color='red'>"+tempData+"</font></a></td>";	
									}else{
										table_content=table_content+"<td><a onclick=grasp_data('"+beginTime+"','"+endTime+"','"+dataType+"','"+model_id+"','"+indexId+"','"+URLencode(dimensionCondition)+"','"+URLencode(graspDemensionCondition)+"',"+JSON.stringify(JSON.stringify(demensionList))+",'"+URLencode(condition+tempCondition)+"')>"+tempData+"</a></td>";	
									}
									
								}
								
							}else{
								var num=j-tempDemensionLen-tempIndexLen+1;
								//alert(num);
								var indexId="";
								var condition=index[num].condition;
								var isGrasp=index[num].isGrasp;
								//add by yangjiajia bengin
								var indicateOverValue=index[num].indicateOverValue;
								//alert(indicateOverValue);
								//add by yangjiajia end
								var tempData=retData[i][j].split("≌")[0];
								for(var count=0;count<grasp.length;count++){
									//alert(grasp[count].indexId);
									var tempGrasp=grasp[count];
									if(num==tempGrasp.countNum){
										indexId=tempGrasp.indexId;
										condition=tempGrasp.condition;
									}
								}
								if('0'==isGrasp){
									if(Number(tempData)>=Number(indicateOverValue)){
										table_content=table_content+"<td><font color='red'>"+tempData+"</font></td>";	
									}else{
										table_content=table_content+"<td>"+tempData+"</td>";	
									}
								}else{
									if(Number(tempData)>=Number(indicateOverValue)){
										table_content=table_content+"<td><a onclick=grasp_data('"+beginTime+"','"+endTime+"','"+dataType+"','"+model_id+"','"+indexId+"','"+URLencode(dimensionCondition)+"',"+JSON.stringify(JSON.stringify(demensionList))+",'"+URLencode(condition+tempCondition)+"')><font color='red'>"+tempData+"</font></a></td>";	
									}else{
										table_content=table_content+"<td><a onclick=grasp_data('"+beginTime+"','"+endTime+"','"+dataType+"','"+model_id+"','"+indexId+"','"+URLencode(dimensionCondition)+"',"+JSON.stringify(JSON.stringify(demensionList))+",'"+URLencode(condition+tempCondition)+"')>"+tempData+"</a></td>";	
									}								}
								
							}
							//复合指标
							//alert("指标加载结束！");
						}
					}
					table_content=table_content+"</tr>";
				}
				//alert("表格主体DIV:"+table_content);			
				$("#"+report_table_div).append(table_content);
			}
			
			function grasp_data(begintime,endtime,dataType,modelId,indexId,dimensionCondition,graspDemensionCondition,demensionList,condition){
			var obj = JSON.parse(demensionList);
				if(obj[0].value=="合计"){
					dataType ="total";
				}
				var url="${pageContext.request.contextPath}/views/statisticplat/main_frame_rep_data_grasp.jsp?beginTime="+begintime+"&endTime="+endtime+"&dataType="+dataType+"&modelId="+modelId+"&indexId="+indexId+"&dimensionCondition="+dimensionCondition+"&graspDemensionCondition="+graspDemensionCondition+"&condition="+condition+"&demensionList="+encodeURI(encodeURI(demensionList));
				//window.open(url,'','height=550,width=950,left=180,top=150,resizable=yes,scrollbars=yes');
				window.open(url);
			}
			
			//封装复合table元素
			function multiTableComponent(rowSpan,columnSpan,textName,rowNum){
				this.rowSpan=rowSpan;
				this.columnSpan=columnSpan;
				this.textName=textName;
				this.rowNum=rowNum;
				return this;
			}
			
			
			//传入攥取详单对象
			function dataGraph(column,value){
				this.column=column;
				this.value=value;
				return this;
			}
			
			
			//导出
			function excelExport(){
				var demension = commondata.retJson.retDemension;
				var index = commondata.retJson.retIndex;
				var data = commondata.retJson.retData;
				var complexTitle = commondata.retJson.retComplexTitle;
				//alert(JSON.stringify(data));
				$("#demension_export").val(JSON.stringify(demension));
				$("#index_export").val(JSON.stringify(index));
				$("#data_export").val(JSON.stringify(data));
				$("#complexTitle_export").val(JSON.stringify(complexTitle));
				//alert("sss");
				$("#excelExportForm").submit();
			}
			
			function URLencode(sStr){
				return escape(sStr).replace("'", '\'').replace("=", '\=').replace("+", '\+').replace("-", '\-').replace("*", '\*').replace("/", '\/').replace(">", '\>').replace("<", '\<');
		    }
		    
			
			//根据指标数添加div至图形页面
			function add_graph_area(add_graph_div,chart_fire_div){
			 	var indicators=commondata.retJson.retIndex;
			 	var param=commondata.retJson.retDemension;
			 	var ret_complex=commondata.retJson.retComplexTitle;
			 	new dialogComponent("","","","","loadingAddModelDiv").loading_dialog();
			 	//alert(JSON.stringify(commondata));
			 	
			 	$("#"+add_graph_div).html("");
			 	
			 	//单个指标
			 	for(var i=0;i<indicators.length;i++){
			 		var position=param.length+i;
			 		var divStr="<div class='row'><div class='col-xs-12'><div><h4 class='font_white'><span>统计指标</span><span style='font-size:10px;'>【<input type='checkbox' class='checkbox_list_graph' value='"+indicators[i].indicateName+"'/>"+indicators[i].indicateName+"】</span><span class='flr'><a href='#' class='pd_r10'><img src='${imagePath}/chart_c_on.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_a_off.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_b_off.png' /></a></span></h4></div><div class='bg_blue' id='"+chart_fire_div+i+"'></div></div></div>";
			 		$("#"+add_graph_div).append(divStr);
			 		
			 		chart_fire("Column3D",chart_fire_div+i,position,indicators[i].indicateName);
			 	}
			 	//复合指标
			 	for(var i=0;i<ret_complex.length;i++){
			 		var position=param.length+indicators.length+i;
			 		var divStr="<div class='row'><div class='col-xs-12'><div><h4 class='font_white'><span>统计指标</span><span style='font-size:10px;'>【<input type='checkbox' class='checkbox_list_graph_complex' value='"+ret_complex[i].name+"'/>"+ret_complex[i].name+"】</span><span class='flr'><a href='#' class='pd_r10'><img src='${imagePath}/chart_c_on.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_a_off.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_b_off.png' /></a></span></h4></div><div class='bg_blue' id='"+chart_fire_div+"_complex"+i+"'></div></div></div>";
			 		$("#"+add_graph_div).append(divStr);
			 		
			 		chart_fire_complex("Column3D",chart_fire_div+"_complex"+i,position,ret_complex[i].name,ret_complex[i].cloumnSpan);
			 	
			 	}
			 	//pic_animate();
			 	setTimeout(function(){new dialogComponent("","","","","loadingAddModelDiv").loading_dialog_hide();},5000);
			}
			
			
			function chart_fire_complex(type,renderDiv,indexLocation,indicateName,colspan){
				var dimension=commondata.retJson.retDemension;
				var indicator=commondata.retJson.retIndex;
				var data=commondata.retJson.retData;
				var transferDimension=commondata.retJson.retTransferDimension;
				//alert(JSON.stringify(transferDimension));
				var chartFire = new FusionCharts( "${pageContext.request.contextPath}/resources/FusionCharts/"+type+".swf", "column2D11Id", "100%", "300", "0", "0" );
				var dataObj=[];
				
				for(var i=0;i<data.length;i++){
					
					var templabel="";
					var tempValue=0;
					for(var j=0;j<dimension.length;j++){
						var tempData=data[i][j].split("≌")[0];
						var temptrans=transferDimension[dimension[j].id][tempData];
						var retDataValue=(typeof(temptrans)=='undefined')?(""+data[i][j]+""):temptrans;
						if(j==0){
						
							templabel=retDataValue;
						}else{
							templabel=templabel+"-"+retDataValue;
						}
					}
					for(var k=0;k<colspan;k++){
					  tempValue=parseInt(tempValue)+parseInt(data[i][indexLocation+k]);
					}
					//console.log(templabel+"--"+tempValue);
					if(templabel.indexOf("total")==-1){
						var temp={"label":templabel,"value":tempValue.split("≌")[0].replace("%","")};
						dataObj.push(temp);
					}
				}
				
				var jsonStr={                         
		    		"chart":{ 
		            	"caption" : indicateName ,
		                            "xAxisName" : dimension[0].paramName,                                  
		                            "yAxisName" : "",                                  
		                            "numberPrefix" : "",
		                            "bgColor":"#f6fafe",
		                            "baseFontColor":"#617e9c",
		                            "canvasBgColor":"#f6fafe",
		                            "hoverCapBgColor":"#f6fafe"   
		            		},                                                
		             	"data" :dataObj
		             	};
		             
			 	chartFire.setJSONData(jsonStr);
		     	chartFire.render(renderDiv);
					
				$("#"+renderDiv).prev().find(".flr>a").each(function(){
				
				$(this).click(function(){
						var imgSrc=$(this).children("img").attr("src");
			    	
			    		$(this).siblings().each(function(index,obj){
			    			var tempSrc=$(obj).children("img").attr("src");
			    			$(obj).children("img").attr("src",tempSrc.replace("_on","_off"));
			    		});
			    	$(this).children("img").attr("src",imgSrc.replace("_off","_on"));
			    	////图片切换
			    	var graph_src=imgSrc.replace("_off","_on");
			    	if(graph_src.indexOf("chart_c_on")>0){
			    	
			    		var chartFire = new FusionCharts( "${pageContext.request.contextPath}/resources/FusionCharts/Column3D.swf", "column2D11Id", "100%", "300", "0", "0" );
			    		
			    		chartFire.setJSONData(jsonStr);
			    		chartFire.render(renderDiv);
			    	}else if(graph_src.indexOf("chart_a_on")>0){	    				
			    
			    		var chartFire = new FusionCharts( "${pageContext.request.contextPath}/resources/FusionCharts/Pie3D.swf", "column2D11Id", "100%", "300", "0", "0" );
			    		chartFire.setJSONData(jsonStr);
			    		chartFire.render(renderDiv);
			    	}else if(graph_src.indexOf("chart_b_on")>0){
			    	
			    		var chartFire = new FusionCharts( "${pageContext.request.contextPath}/resources/FusionCharts/Line.swf", "column2D11Id", "100%", "300", "0", "0" );
			    		chartFire.setJSONData(jsonStr);
			    		chartFire.render(renderDiv);
			    	}	
				});
		  	  });
		     	
				
			}
			
			
			function chart_fire(type,renderDiv,indexLocation,indicateName){
				var dimension=commondata.retJson.retDemension;
				var indicator=commondata.retJson.retIndex;
				var data=commondata.retJson.retData;
				var transferDimension=commondata.retJson.retTransferDimension;
				var chartFire = new FusionCharts( "${pageContext.request.contextPath}/resources/FusionCharts/"+type+".swf", "column2D11Id", "100%", "300", "0", "0" );
				var dataObj=[];
				
				for(var i=0;i<data.length;i++){
					
					var templabel="";
					for(var j=0;j<dimension.length;j++){
					
						var tempData=data[i][j].split("≌")[0];					
						var temptrans=transferDimension[dimension[j].id][tempData];
						var retDataValue=(typeof(temptrans)=='undefined')?(""+data[i][j]+""):temptrans;
						if(j==0){
							templabel=retDataValue;
						}else{
							templabel=templabel+"-"+retDataValue;
						}
					}
					//console.log(templabel+"--"+data[i][indexLocation]);
					if(templabel.indexOf("total")==-1){
						var temp={"label":templabel,"value":data[i][indexLocation].split("≌")[0].replace("%","")};
						dataObj.push(temp);
					}
				}
		        
		        var jsonStr={                         
		    		"chart":{ 
		            	"caption" : indicateName ,
		                            "xAxisName" : dimension[0].paramName,                                  
		                            "yAxisName" : "",                                  
		                            "numberPrefix" : "",
		                            "bgColor":"#52AFFF",
		                            "baseFontColor":"#ffffff",
		                            "canvasBgColor":"#2885D3",
		                            "hoverCapBgColor":"#2885D3",
		                            "labelDisplay":"WRAP",
		                            "slantLabels":"0" 
		            		},                                                
		             	"data" :dataObj
		             	};
		             
			 	chartFire.setJSONData(jsonStr);
		     	chartFire.render(renderDiv);
		     	
		     	
		     	$("#"+renderDiv).prev().find(".flr>a").each(function(){
				$(this).click(function(){
						var imgSrc=$(this).children("img").attr("src");
			    	
			    		$(this).siblings().each(function(index,obj){
			    			var tempSrc=$(obj).children("img").attr("src");
			    			$(obj).children("img").attr("src",tempSrc.replace("_on","_off"));
			    		});
			    	$(this).children("img").attr("src",imgSrc.replace("_off","_on"));
			    	////图片切换
			    	var graph_src=imgSrc.replace("_off","_on");
			    	if(graph_src.indexOf("chart_c_on")>0){
			    	
			    		var chartFire = new FusionCharts( "${pageContext.request.contextPath}/resources/FusionCharts/Column3D.swf", "column2D11Id", "100%", "300", "0", "0" );
			    		chartFire.setJSONData(jsonStr);
			    		chartFire.render(renderDiv);
			    	}else if(graph_src.indexOf("chart_a_on")>0){	    				
			    
			    		var chartFire = new FusionCharts( "${pageContext.request.contextPath}/resources/FusionCharts/Pie3D.swf", "column2D11Id", "100%", "300", "0", "0" );
			    		chartFire.setJSONData(jsonStr);
			    		chartFire.render(renderDiv);
			    	}else if(graph_src.indexOf("chart_b_on")>0){
			    	
			    		var chartFire = new FusionCharts( "${pageContext.request.contextPath}/resources/FusionCharts/Line.swf", "column2D11Id", "100%", "300", "0", "0" );
			    		chartFire.setJSONData(jsonStr);
			    		chartFire.render(renderDiv);
			    	}	
				});
		  	  });
		  	  
			}
				
			//算法规则
			function showComputeRule(){
				$.ajax({
						url : '${pageContext.request.contextPath}/tawStatisticModel/getModelById.json',
						type : 'post',
						cache : false,
						async : true,
						data:{'model_id':'${modelid}'},
						success : function(retData) {
							//alert(retData.retJson.tawStatisticModel.computeRule);
							var ruleVale=retData.retJson.tawStatisticModel.computeRule==null?"暂未添加算法！":retData.retJson.tawStatisticModel.computeRule;
							var a= new dialogComponent("算法规则","<div class='txt-fld_center'>"+ruleVale+"</div>","indexAddModel",null,"indexAddModelDiv");
							a.confirm_dialog(function(data){
								if(data==true) {
									
								}
							});			
						}
					});
			}
			
			
			
			function add_new_graph_area(add_graph_div,chart_fire_div){
			
				var param=commondata.retJson.retDemension;
			 	var ret_complex=commondata.retJson.retComplexTitle;
			 	$("#"+add_graph_div).html("");
			 	var num1=0;
			 	var num2=0;
			 	var str='';
			 	$(".checkbox_list_graph").each(function(index,checkObj){
			 		if ($(checkObj).is(":checked")) {
			 			str+=checkObj.value+',';
			 			num1++;
				    }
			 	});
			 	str=str.substring(0,str.length-1);
			 	var str1='';
			 	$(".checkbox_list_graph_complex").each(function(index,checkObj){
			 		if ($(checkObj).is(":checked")) {
			 			str1+=checkObj.value+',';
			 			num2++;
				    }
			 	});
			 	str1=str1.substring(0,str1.length-1);
			 	var count=0;
			 	var num=num1+num2;
			 	//alert(num);
			 	if(num>4||num==0){
			 		if(num==0){
			 			alert("请选择指标！");
			 		}else{
			 			alert("每次最多只能选择4个指标，请重新选择！");
			 		}
			 	}else{
			 		var indicators=new Array();
				 	if(str!=""){
				 		indicators=str.split(",");
					 	//单个指标
					 	var divStr="<div class='row'>";
					 	for(var i=0;i<indicators.length;i++){
					 		var position=param.length+i;
					 		if(count<4){
					 			if(i==0||i==2){
					 				if(i==(num-1)){
					 				   
					 					divStr=divStr+"<div class='col-xs-6'><h4 class='font_white'>统计指标<span class='flr'><a href='#' class='pd_r10'><img src='${imagePath}/chart_c_on.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_a_off.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_b_off.png' /></a></span></h4><div class='bg_blue' id='com_"+chart_fire_div+i+"'></div></div>";
					 					divStr=divStr+"</div>";
					 					$("#"+add_graph_div).append(divStr);
						 				chart_fire("Column3D","com_"+chart_fire_div+i,position,indicators[i]);
						 				
						 				
						 			
						 				
						 				
					 				}else{
					 					divStr=divStr+"<div class='col-xs-6'><h4 class='font_white'>统计指标<span class='flr'><a href='#' class='pd_r10'><img src='${imagePath}/chart_c_on.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_a_off.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_b_off.png' /></a></span></h4><div class='bg_blue' id='com_"+chart_fire_div+i+"'></div></div>";
					 				}
					 				
					 			}else{
					 				divStr=divStr+"<div class='col-xs-6'><h4 class='font_white'>统计指标<span class='flr'><a href='#' class='pd_r10'><img src='${imagePath}/chart_c_on.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_a_off.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_b_off.png' /></a></span></h4><div class='bg_blue' id='com_"+chart_fire_div+i+"'></div></div>";
					 				divStr=divStr+"</div>";
					 				$("#"+add_graph_div).append(divStr);
						 			divStr="<div class='row'>";
						 			chart_fire("Column3D","com_"+chart_fire_div+i,position,indicators[i]);
						 			chart_fire("Column3D","com_"+chart_fire_div+(i-1),position-1,indicators[(i-1)]);
					 			}
					 			
						 		
						 		count++;
					 		}
					 		
					 	}
				 	}
				 	//复合指标
				 	if(str1!=""){
				 		var complexIndicators=str1.split(",");
				 		var divStr="<div class='row'>";
					 	for(var i=0;i<ret_complex.length;i++){
					 		var position=param.length+indicators.length+i;
					 		for(j=0;j<complexIndicators.length;j++){
					 			if(ret_complex[i].name==complexIndicators[j]){
					 				
					 				if(count<4){
								 			if(i==0||i==2){
								 				if(i==(num-1)){
								 					divStr=divStr+"<div class='col-xs-6'><h4 class='font_white'>统计指标<span class='flr'><a href='#' class='pd_r10'><img src='${imagePath}/chart_c_on.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_a_off.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_b_off.png' /></a></span></h4><div class='bg_blue' id='com_"+chart_fire_div+"_complex"+i+"'></div></div>";
								 					divStr=divStr+"</div>";
								 					$("#"+add_graph_div).append(divStr);
									 				chart_fire_complex("Column3D","com_"+chart_fire_div+"_complex"+i,position,ret_complex[i].name,ret_complex[i].cloumnSpan);
								 				}else{
								 					divStr=divStr+"<div class='col-xs-6'><h4 class='font_white'>统计指标<span class='flr'><a href='#' class='pd_r10'><img src='${imagePath}/chart_c_on.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_a_off.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_b_off.png' /></a></span></h4><div class='bg_blue' id='com_"+chart_fire_div+"_complex"+i+"'></div></div>";
								 				}
								 				
								 			}else{
								 				divStr=divStr+"<div class='col-xs-6'><h4 class='font_white'>统计指标<span class='flr'><a href='#' class='pd_r10'><img src='${imagePath}/chart_c_on.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_a_off.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_b_off.png' /></a></span></h4><div class='bg_blue' id='com_"+chart_fire_div+"_complex"+i+"'></div></div>";
								 				divStr=divStr+"</div>";
								 				$("#"+add_graph_div).append(divStr);
									 			divStr="<div class='row'>";
									 			chart_fire_complex("Column3D","com_"+chart_fire_div+"_complex"+i,position,ret_complex[i].name,ret_complex[i].cloumnSpan);
									 			chart_fire_complex("Column3D","com_"+chart_fire_div+"_complex"+(i-1),position-1,ret_complex[(i-1)].name,ret_complex[(i-1)].cloumnSpan);
								 			}
								 			
									 		
						 					count++;
								 		}
					 				
					 			}
					 		}
					 		
					 	
					 	}
				 	}
				 	
				 	
			 	}
			}
			
			//数据筛选
			function data_screening(timeType){
					var modelId='${modelid}';
					//alert(currentModelItems);
					$.ajax({
							url : '${pageContext.request.contextPath}/tawStatisticDataScreen/getModelInfoById.json',
							type : 'post',
							dataType:"json",
							data:{'model_id':modelId,'modelItems':currentModelItems},
							success : function(data) {
								//alert(JSON.stringify(data));
								var model_param=data.retJson.paramList;
								var model_indicator=data.retJson.indexList;
								//var model_complex=data.retJson.compleList;
								
								//加载数据筛选
								//var contend_time="<div class='txt-fld_left'><label>时间:</label><span class='pd_r5'><input id='beginTime' name='beginTime'  style='width:125px;height:35px' placeholder=' 请选择开始日期'/></span>-<span  class='pd_r5'><input name='endTime' id='endTime' style='width:125px;height:35px' placeholder=' 请选择结束日期'/></span></div>";
								
								var contend_indicator="<div class='txt-fld_left'><label>指标:<input id='checkAllIndicators' type='button' checked='true' onclick='checkAll()' value='全选' class='btn btn-info btn-xs'>&nbsp;<input id='checkReverseIndicators' type='button' checked='true' onclick='checkReverse()' value='反选' class='btn btn-info btn-xs'></label>";
								for(var i=0;i<model_indicator.length;i++){
									contend_indicator=contend_indicator+"<div class='txt-fld_left'><input type='checkbox' checked='true' class='indicators' value='"+model_indicator[i].modelItemId+"'> <label> "+model_indicator[i].indicateName+"</label></div>";
								}
								//for(var i=0;i<model_complex.length;i++){
								//	contend_indicator=contend_indicator+"<div class='txt-fld_left'><input type='checkbox' checked='true' class='indicators' value='"+model_complex[i].modelItemId+"'> <label> "+model_complex[i].paramName+"</label></div>";
								//}
								contend_indicator=contend_indicator+"</div>";
								
								var contend_param="<div class='txt-fld_left'><label>维度:<input id='checkAllParameters' type='button' checked='true' onclick='checkAll1()' value='全选' class='btn btn-info btn-xs'>&nbsp;<input id='checkReverseParameters' type='button' checked='true' onclick='checkReverse1()' value='反选' class='btn btn-info btn-xs'></label>";
						
								for(var i=0;i<model_param.length;i++){
								    //var tempChildrenList=model_param[i].childrenList;
								    //var optionStr="";
								    
								    //for(var j=0;j<tempChildrenList.length;j++){
								    //	optionStr=optionStr+"<option value="+tempChildrenList[j].paramCode+">"+tempChildrenList[j].paramText+"</option>";
								    //}
									//contend_param=contend_param+"<div class='txt-fld_left'><input type='checkbox' checked='true' class='parameters' value='"+model_param[i].modelItemId+"'> <label> "+model_param[i].paramName+":</label><span><select id='"+model_param[i].paramEng+"_condition' class='selectpicker' multiple data-live-search='true'><optgroup label='维度选择' data-subtext='' data-icon='icon-ok'>"+optionStr+"</optgroup></select></span></div>";
									var paramCodeType = model_param[i].paramCodeType;
									if(paramCodeType=="noneType"){
										contend_param=contend_param+"<div class='txt-fld_left'><input type='checkbox' checked='true' class='parameters' value='"+model_param[i].modelItemId+"'> <label> "+model_param[i].paramName+":</label><span><input style='height: 35px;line-height: 30px;' type='text' class='"+model_param[i].paramEng+"_condition' value=''></span></div>";
									}else{
										contend_param=contend_param+"<div class='txt-fld_left'><input type='checkbox' checked='true' class='parameters' value='"+model_param[i].modelItemId+"'> <label> "+model_param[i].paramName+":</label><span><input style='height: 35px;line-height: 30px;' type='text' class='"+model_param[i].paramEng+"_condition' value='' onfocus='this.blur();' readOnly onclick='showChooseTree(this,\""+paramCodeType+"\")'></span></div>";
									}
								}
								contend_param=contend_param+"</div>";
								//alert(contend_param);
								
								//var a= new dialogComponent("数据筛选",contend_time+contend_indicator+contend_param,"paramAddModal","open_param_window","paramAddModelDiv_add");
								var a= new dialogComponent("高级查询",contend_param+contend_indicator,"paramAddModal","open_param_window","paramAddModelDiv_add");
								//var a= new dialogComponent("数据筛选",contend_time+contend_param,"paramAddModal","paramAddModal","paramAddModelDiv_add");
								/*
								$("#open_param_window>#signup-ct>.open_window_header>.modal_close").bind("click",function(){
									alert("aa");
									$("#open_param_window").css({ "display": "none" });
									$("#lean_overlay").css({ "display": "none"});
								});
								*/
							   
							    a.self_dialog(function(){},function(choose_data){ 
									if(choose_data==true){
										var beginTime="";
										var endTime="";
										var items="";
										var indicateItems="";
										var demensionItems="";
										if(timeType=='dayReport'){
											beginTime=$("#dayReportTime").val();
											endTime=addDay(beginTime);
										}else if(timeType=='monthReport'){
											var tempBeginTime=$("#month_rep_year").val()+"-"+$("#month_rep_month").val();
											beginTime = tempBeginTime+"-1";
											endTime=addMonth(beginTime);
										}else if(timeType=='selfDefineReport'){
											beginTime=$("#self_define_begin_time").val();
											endTime=$("#self_define_end_time").val();
										}
										//var begintime=$("#beginTime").attr("value");
										//var endtime=$("#endTime").attr("value");
										//获取元素传入后台,ajax将值传入
										
										$(".indicators").each(function(index,checkObj){
											if($(checkObj).is(":checked")){
												if(indicateItems.length==0){
													indicateItems=$(checkObj).val();
												}else{
													indicateItems=indicateItems+","+$(checkObj).val();
												}
											}
										
										});
										if(indicateItems.length==0){
											alert("请至少选择一个指标！");
											return false;
										}
										
										//获取维度
										$(".parameters").each(function(index,checkObj){
											if($(checkObj).is(":checked")){
												if(demensionItems.length==0){
													demensionItems=$(checkObj).val();
													}else{
														demensionItems=demensionItems+","+$(checkObj).val();
													}
												}
										});
										if(demensionItems.length==0){
											alert("请至少选择一个维度！");
											return false;
										}
										//获取维度查询条件
										var demensionCondition="";
										var tempChooseComple="";
										for(var i=0;i<model_param.length;i++){
											//alert($("."+model_param[i].paramEng+"_condition").attr("type"));
											tempChooseComple=$("."+model_param[i].paramEng+"_condition").attr("id");
											if(typeof(tempChooseComple)=="undefined"||tempChooseComple==""){
												tempChooseComple=$("."+model_param[i].paramEng+"_condition").val();
											}
											//demensionCondition=demensionCondition+"and "+model_param[i].paramEng+" exists('" +tempChooseComple.replace(",","','")+"')";
											if(typeof(tempChooseComple)!="undefined"&&tempChooseComple!=""){
												demensionCondition=demensionCondition+" and "+model_param[i].paramEng+"='"+tempChooseComple+"'";
											}
										}
										//alert(demensionCondition);
										//alert(modelId+"-"+items);	
										//alert(beginTime+"-"+endTime);	
										//generate_report(items,begintime,endtime,demensionCondition);
										//alert(modelId+"-"+items+"-"+beginTime+"-"+endTime+"-"+demensionCondition);
										//console.log(modelId+"-"+items+"-"+beginTime+"-"+endTime+"-"+demensionCondition);
										items = indicateItems+","+demensionItems;
										//alert(commondata.retJson.graspDemensionCondition);
										generate_common_report(0,modelId,items,beginTime,endTime,timeType,demensionCondition,null,null,currentGraspDemensionCondition);
										
									} 
								});
								//加载纬度树
								//$(".selectpicker").selectpicker({});
								/*
								$("#beginTime").bind("click",function(){
									laydate({elem: '#beginTime',istime: true, format: 'YYYY-MM-DD'});
								});
								$("#endTime").bind("click",function(){
									laydate({elem: '#endTime',istime: true, format: 'YYYY-MM-DD'});
								});
								*/
			
								
							}
						});
					}
			
			//打开选择树
			//触发选择树控件
			var attachElement = "";
			//最后被点击的树节点id
			var lastClickTreeNodeId = "";
			var lastClickTreeNodeName = "";
			function showChooseTree(currentElement, treeType){
				//alert(treeType);
				$("#chooseTreeDiv").show();
				attachElement = $(currentElement);
				var setting = {
					view: {
						selectedMulti: false
					},
					async: {
						enable: true,
						url:"${pageContext.request.contextPath}/tawStatisticReport/getTreeDataByType.json",
						//contentType:"application/x-www-form-urlencoded",
						//type:"post",
						//dataType:"json",
						autoParam:["id", "nodeType"],
						otherParam:{"treeType":treeType},
						dataFilter: filter
					},
					callback: {
						onClick: zTreeOnClick
					}
				};
				
				function filter(treeId, parentNode, childNodes) {
					//alert(JSON.stringify(childNodes));
					if (!childNodes) return null;
					for (var i=0, l=childNodes.length; i<l; i++) {
						childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
					}
					return childNodes;
				}
				
				function zTreeOnClick(event, treeId, treeNode) {
				    //alert(treeNode.id + ", " + treeNode.name);
				    lastClickTreeNodeId = treeNode.id;
				    lastClickTreeNodeName = treeNode.name;
				};

				$(document).ready(function(){
					$.fn.zTree.init($("#chooseTree"), setting);
				});
			}
			
			//关闭选择树
			function hideChooseTree(){
				$("#chooseTreeDiv").hide();
			}
			
			//确定选择
			function ensureChooseTree(){
				//alert(lastClickTreeNodeId);
				//alert(lastClickTreeNodeName);
				attachElement.attr("id", lastClickTreeNodeId);
				attachElement.attr("value", lastClickTreeNodeName);
				//alert(attachElement.prop("outerHTML"));
				$("#chooseTreeDiv").hide();
			}
		</script>

</head>
<body style="overflow-y:scroll;">
<!-- 指标新增begin -->
<div id="indexAddModelDiv" ></div>
<div id="indexAddModelDiv_add"></div>
<a href="#open_index_window" rel="indexAddModal"></a>
<div id="paramAddModelDiv"></div>
<div id="paramAddModelDiv_add"></div>
<a href="#open_param_window" rel="paramAddModal"></a>
<div id="loadingAddModelDiv"></div>
<div id="loadingAddModelDiv_add"></div>
<a href="#open_loading_window" rel="loadingAddModal"></a>

<div id="chooseTreeAddModelDiv"></div>
<div id="chooseTreeAddModelDiv_add"></div>
<a href="#open_chooseTree_window" rel="chooseTreeAddModal"></a>

<!-- 指标新增end -->
<form id="excelExportForm" method="post" action="${pageContext.request.contextPath}/tawStatisticReport/excelExport.json" >
	<input type="hidden" id="demension_export" name="demension_export" value=""/>
	<input type="hidden" id="index_export" name="index_export" value=""/>
	<input type="hidden" id="data_export" name="data_export" value=""/>
	<input type="hidden" id="complexTitle_export" name="complexTitle_export" value=""/>
</form>


	<div id="mainWrap_new_all">
		<div id="header_user">
			<div class="flr mg_r10 mg_t30"></div>
		</div>

		<div id="mainContent_user" class="fixed">
			<ul class="nav nav-tabs">
				<li role="presentation"><a onclick="tab_change(1,this)">自定义报表</a></li>
				<li role="presentation"><a onclick="tab_change(2,this)"> 月报 </a></li>
				<li role="presentation" class="active"><a onclick="tab_change(3,this)"> 日 报 </a></li>
			</ul>
		</div>




		<div id="day_report" class="conMain mg_t18" style="height:auto"><%@include file='./user_frame_day_report.jsp'%></div>

		<div id="month_report" class="conMain mg_t18" style="height:auto;display:none;"><%@include file='./user_frame_month_report.jsp'%></div>

		<div id="selfDefine_report" class="conMain mg_t18" style="height:auto;display:none;"><%@include file='./user_frame_selfdefine_report.jsp'%></div>
		
		<%--选择树 开始--%>
		<div id="chooseTreeDiv" class="panel panel-primary" style="position: fixed;left: 465px;top: 70px;width: 420px;height: 480px;z-index:100000;display:none;">
           <div class="panel-heading">
              <h3 class="panel-title">
                 	 选择树
                  <button type="button" class="close" aria-label="Close" onclick="hideChooseTree()"><span style="color: #ffffff;" aria-hidden="true">&times;</span></button>
              </h3>
           </div>
           <div class="panel-body">
              <div style="height: 360px;overflow: auto;">
                <ul id="chooseTree" class="ztree"></ul>    
              </div>
              <div style="height: 50px;">
                <div style="height: 50px;width: 140px;margin: 0 auto;line-height: 50px;">
                    <button type="button" class="button bg-sub" onclick="ensureChooseTree()">确认</button>   
                    <button type="button" class="button bg-main" onclick="hideChooseTree()">取消</button>     
                </div>
              </div>
           </div>
        </div>
<!--         <button type="button" class="btn btn-primary" onclick="showChooseTree()">测试</button> -->
		<%--选择树 结束--%>
		
	</div>
</body>
</html>