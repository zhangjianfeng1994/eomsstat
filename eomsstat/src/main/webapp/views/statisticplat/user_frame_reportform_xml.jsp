<%@include file="../common/header.jsp"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String modelid=request.getParameter("modelid");
String checkItems=request.getParameter("checkItems");
String begintime=request.getParameter("begintime");
String endtime=request.getParameter("endtime");
String deptids=request.getParameter("deptids");
String type=request.getParameter("type");

request.setAttribute("modelid", modelid);
request.setAttribute("checkItems", checkItems);
request.setAttribute("begintime", begintime);
request.setAttribute("endtime", endtime);
request.setAttribute("deptids", deptids);
request.setAttribute("type", type);
 %>
<html>
<head>
<meta charset="UTf-8">
<title>统计平台</title>
<link rel="stylesheet" type="text/css" href="${cssPath}/maskLayer.css">
<script type="text/javascript">
			$(document).ready(function(){
					//
					//laydate.skin('yalan');
					var commondata=null;
					var checkItems='${checkItems}'
					if(''!=checkItems){
						$("#mainContent_user>.nav.nav-tabs").children("li").each(function(index,li_obj){  
				   			if(0==index){
				   				$(li_obj).addClass("active");
				   			}else{
				   				$(li_obj).removeClass("active");
				   			}
						});
					}
					
					$("#day_report").hide();
					$("#month_report").hide();
					$("#selfDefine_report").slideDown("slow");
					var beginTime=$("#self_define_begin_time").val('${begintime}');
					var endTime=$("#self_define_end_time").val('${endtime}');
					
					graph_hide();
					
			});
			
			
				//初始化时间
			function timeInit(type){
				var myDate = new Date();
				var year=myDate.getFullYear();
				var month=myDate.getMonth()+1;
				var day=myDate.getDate();
				if(type=="initDay"){
					if(day<10){
						day="0"+day;
					}
					var time=year+"-"+month+"-"+day;
					$("#dayReportTime").val(time);
					$("#dayReportTime").bind("click",function(){
						laydate({elem: '#dayReportTime',istime: true, format: 'YYYY-MM-DD'});
					});
				}else if(type=="initMonth"){
// 					var time=year+"-"+month;
// 					$("#monthReportTime").val(time);
// 					$("#monthReportTime").bind("click",function(){
// 						laydate({elem: '#monthReportTime',istime: true, format: 'YYYY-MM'});
// 					});
				}else if(type=="initSelfDefine"){
					var beginDay=day;
					if(beginDay<10){
						beginDay="0"+beginDay;
					}
					var beginTime=year+"-"+month+"-"+beginDay;
				
					var endDay=day+1;
					if(endDay<10){
						endDay="0"+endDay;
					}
					var endTime=year+"-"+month+"-"+endDay;
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
				$("#dayReport_graph_combine").hide();
				$("#monthReport_graph_combine").hide();
				$("#selfDefineReport_graph_combine").hide();
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
						graph_hide();
					}else if("2"==param){
						$("#month_report").slideDown("slow");
						$("#day_report").hide();
						$("#selfDefine_report").hide();
						graph_hide();
					}else if("1"==param){
						$("#selfDefine_report").slideDown("slow");
						$("#day_report").hide();
						$("#month_report").hide();
						graph_hide();
					}
			}
			    
			
			
			//统计报表公共
			function generate_common_report(beginTime,endTime,report_table_div_demesion,report_table_div_index,demensionCondition,demension,indexChooseStr,type){
					//加载框
					new dialogComponent("","","","","loadingAddModelDiv").loading_dialog();
					$.ajax({
						url : '${pageContext.request.contextPath}/tawStatisticReport/getReportXml.json',
						type : 'post',
						cache : false,
						async : true,                                                                  
						data:{'indexChooseStr':indexChooseStr,'beginTime':beginTime,'endTime':endTime,'demensionCondition':URLencode(demensionCondition),'demesion_grasp_value':demension,'model_id':'${modelid}','type':type},
						success : function(data) {
							commondata=data;
							var layer=data.retJson.treeDeep;
							//$("#"+report_table_div).empty();
							
							//retComplexTitleDimension  retComplexTitleIndexs
							
							var tableComponents_index=new Array();
							var tableComponents_demesion=new Array();
							
							var layer=data.retJson.treeDeep;
							//复合表格标题数据处理
							generateTitle(data.retJson.retComplexTitleDimension,tableComponents_demesion,data.retJson.treeDeep);
							generateTitle(data.retJson.retComplexTitleIndexs,tableComponents_index,data.retJson.treeDeep);
							//生成标题
							generateComplexTitle(data.retJson,tableComponents_index,tableComponents_demesion,layer,report_table_div_index,report_table_div_demesion);
							//生成表格内容
						    generateTableContent(data.retJson,beginTime,endTime,report_table_div_index,report_table_div_demesion);
							//图形报表
							//add_graph_area();
							new dialogComponent("","","","","loadingAddModelDiv").loading_dialog_hide();
						}
					});
			}
			
			
			function generateTableContent(retJson,beginTime,endTime,report_table_div_index,report_table_div_demesion){
				var table_content_index="";
				var table_content_demension="";
				
				//转化复合表头为Map
				var retData=retJson.retData;
				var demension=retJson.retDemension;
				var transfer=retJson.retTransferDimension;
				var indexs=retJson.retIndex;
				
				for(var i=0;i<retData.length;i++){
					var tr_line=retData[i];
					if(i%2==0){
						table_content_index=table_content_index+"<tr  class='tablerowodd'>";
						table_content_demension=table_content_demension+"<tr  class='tablerowodd'>";
					}else{
						table_content_index=table_content_index+"<tr class='tableroweven'>";
						table_content_demension=table_content_demension+"<tr class='tableroweven'>";
					}
					for(var j=0;j<retData[i].length;j++){
						var tempDemensionLen=demension.length;
						if(j<tempDemensionLen){
							//维度转码
							var tempData=retData[i][j];
						    var temptrans=transfer[tempData];
						    var retDataValue=(typeof(temptrans)=='undefined')?(tempData):temptrans;
						    					    	                                                          
						    table_content_demension=table_content_demension+"<td><a onclick=generate_common_report('"+beginTime+"','"+endTime+"','"+report_table_div_demesion+"','"+report_table_div_index+"','','"+tempData+"','${checkItems}','')>"+retDataValue+"</a></td>";
																								         
						}else{
							//指标
							var tempData=retData[i][j].split("-")[0];
							var tempCondition=retData[i][j].split("-")[1]+" "+indexs[j-1].condition;							
							var isGrasp=indexs[j-1].isGrasp;
							if('0'==isGrasp){
								table_content_index=table_content_index+"<td>"+tempData+"</td>";
							}else{
								table_content_index=table_content_index+"<td><a onclick=grasp_data('"+indexs[j-1].detailNum+"','"+URLencode(tempCondition)+"','"+beginTime+"','"+endTime+"')>"+tempData+"</a></td>";
							}
						}
					}
					table_content_index=table_content_index+"</tr>";
					table_content_demension=table_content_demension+"</tr>";
				}
				$("#"+report_table_div_index).append(table_content_index);
				$("#"+report_table_div_demesion).append(table_content_demension);				
			}
			
			
			function grasp_data(grapNum,condition,beginTime,endTime){
				var url="${pageContext.request.contextPath}/views/statisticplat/main_frame_rep_data_grasp_xml.jsp?grapNum="+grapNum+"&condition="+condition+"&beginTime="+beginTime+"&endTime="+endTime+"&modelid="+'${modelid}';
				window.open(url,'','height=550,width=950,left=180,top=150,resizable=yes,scrollbars=yes');
			}
			
			function URLencode(sStr){
				return escape(sStr).replace("'", '\'').replace("=", '\=').replace("+", '\+').replace("-", '\-').replace("*", '\*').replace("/", '\/').replace(">", '\>').replace("<", '\<').replace(",", '\,');
		    }
			
			function generateComplexTitle(retjson,tableComponents_index,tableComponents_dimesion,layer,report_table_div_index,report_table_div_dimension){
			
				//alert(JSON.stringify(retjson.retDemension.length));
				$("#"+report_table_div_index).empty();
				$("#"+report_table_div_dimension).empty();
				//产生维度
				for(var i=1;i<=layer;i++){
						var table_title="<thead><tr>";
						//指标和纬度		
						for(var j=0;j<tableComponents_index.length;j++){
								var tempTh=tableComponents_index[j];
								if(tempTh.rowNum==i){
									table_title=table_title+"<th rowspan='"+tempTh.rowSpan+"' colspan='"+tempTh.columnSpan+"'>"+tempTh.textName+"</th>";
								}			
						}
						table_title=table_title+"</tr></thead>";
						$("#"+report_table_div_index).append(table_title);
				}
				
				//产生指标
				//根据tableComponents构造标题头
				
				var table_title="<thead><tr>";
						
						//alert(JSON.stringify(tableComponents_dimesion));
						//指标和纬度		
				for(var j=0;j<tableComponents_dimesion.length;j++){
						var tempTh=tableComponents_dimesion[j];
						table_title=table_title+"<th rowspan='"+tempTh.rowSpan+"' colspan='"+tempTh.columnSpan+"'>"+tempTh.textName+"</th>";			
				}
				table_title=table_title+"</tr></thead><thead><tr><th></th></tr></thead>";
				$("#"+report_table_div_dimension).append(table_title);
				
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
			
			//封装复合table元素
			function multiTableComponent(rowSpan,columnSpan,textName,rowNum){
				this.rowSpan=rowSpan;
				this.columnSpan=columnSpan;
				this.textName=textName;
				this.rowNum=rowNum;
				return this;
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
							var a= new dialogComponent("算法规则","<div class='txt-fld_center'>"+ruleVale+"</div>","indexAddModel","indexAddModel","indexAddModelDiv_add");
							a.self_dialog(function(data){
								if(data==true) {
									
								}
							});			
						}
					});
			}
			
			
			//导出
			function excelExport(){
				var demension = commondata.retJson.retDemension;
				var index = commondata.retJson.retIndex;
				var data = commondata.retJson.retData;
				var complexTitle = commondata.retJson.retComplexTitle;
				
				$("#demension_export").val(JSON.stringify(demension));
				$("#index_export").val(JSON.stringify(index));
				$("#data_export").val(JSON.stringify(data));
				$("#complexTitle_export").val(JSON.stringify(complexTitle));
				$("#model_id").val('${modelid}');
				$("#excelExportForm").submit();
			}
			
			
			//根据指标数添加div至图形页面
			function add_graph_area(add_graph_div,chart_fire_div){
			 	var indicators=commondata.retJson.retIndex;
			 	var param=commondata.retJson.retDemension;
			 	var ret_complex=commondata.retJson.retComplexTitle;
			 	
			 	new dialogComponent("","","","","loadingAddModelDiv").loading_dialog();
			 	$("#"+add_graph_div).html("");
			 	
			 	//单个指标
			 	for(var i=0;i<indicators.length;i++){
			 		var position=param.length+i;
			 		var divStr="<div class='row'><div class='col-xs-12'><div><h4 class='font_white'><span>"+indicators[i].parentIndicateName+"</span><span style='font-size:10px;'>【<input type='checkbox' class='checkbox_list_graph_"+chart_fire_div+"' value='"+indicators[i].parentIndicateName+indicators[i].indicateName+"'/>"+indicators[i].indicateName+"】</span><span class='flr'><a href='#' class='pd_r10'><img src='${imagePath}/chart_c_on.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_a_off.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_b_off.png' /></a></span></h4></div><div class='bg_blue' id='"+chart_fire_div+i+"'></div></div></div>";
			 		$("#"+add_graph_div).append(divStr);
			 		
			 		chart_fire("Column3D",chart_fire_div+i,position,indicators[i].indicateName);
			 	}
		
			 	//pic_animate();
			 	setTimeout(function(){new dialogComponent("","","","","loadingAddModelDiv").loading_dialog_hide();},5000);
			}
			
			
			function combine_Graph_area(add_graph_div,chart_fire_div,parent_div_choose){
				var indicators=commondata.retJson.retIndex;
				var param=commondata.retJson.retDemension;
				
				
				var str="";
				var num=0;
				$(".checkbox_list_graph_"+parent_div_choose).each(function(index,checkObj){
					if ($(checkObj).is(":checked")) {
			 			str+=checkObj.value+',';
			 			num=num+1
				    }
					
				});
				//alert(str)
				var count=0;
				$("#"+add_graph_div).empty();
				var showIndicator=[];
				
				if(num>4||num==0){
					alert("请选择指标");
				}else{
					var complexIndicators=str.split(",");
				 	var divStr="<div class='row'>";
					for(var i=0;i<indicators.length;i++){	
					 	for(j=0;j<complexIndicators.length;j++){
					 		if((indicators[i].parentIndicateName+indicators[i].indicateName)==complexIndicators[j]){
					 			var position=param.length+i;
					 			if(count<num){
					 				showIndicator.push(indicators[i]);
								 	if(count==0||count==2){
								 		if(count==(num-1)){
								 				divStr=divStr+"<div class='col-xs-6'><div><h4 class='font_white'>"+indicators[i].parentIndicateName+"<span class='flr'><a href='#' class='pd_r10'><img src='${imagePath}/chart_c_on.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_a_off.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_b_off.png' /></a></span></h4></div><div class='bg_blue' id='"+chart_fire_div+count+"'></div></div>";
								 				divStr=divStr+"</div>";
								 				$("#"+add_graph_div).append(divStr);
									 			chart_fire("Column3D",chart_fire_div+count,position,indicators[i].indicateName);
								 			}else{
								 				divStr=divStr+"<div class='col-xs-6'><div><h4 class='font_white'>"+indicators[i].parentIndicateName+"<span class='flr'><a href='#' class='pd_r10'><img src='${imagePath}/chart_c_on.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_a_off.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_b_off.png' /></a></span></h4></div><div class='bg_blue' id='"+chart_fire_div+""+count+"'></div></div>";
								 			}
								 				
								 		}else if(count==1||count==3){
								 				divStr=divStr+"<div class='col-xs-6'><div><h4 class='font_white'>"+indicators[i].parentIndicateName+"<span class='flr'><a href='#' class='pd_r10'><img src='${imagePath}/chart_c_on.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_a_off.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_b_off.png' /></a></span></h4></div><div class='bg_blue' id='"+chart_fire_div+""+count+"'></div></div>";
								 				divStr=divStr+"</div>";
								 				$("#"+add_graph_div).append(divStr);
									 			divStr="<div class='row'>";
									 			chart_fire("Column3D",chart_fire_div+count,position,indicators[i].indicateName);
									 			chart_fire("Column3D",chart_fire_div+(count-1),position,showIndicator[count-1].indicateName);
								 		}
								 		count++;
								 	}
					 				
					 			}
					 		}
					 	}
				
				
				}
			
			//pic_animate();
			
			
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
					
						var tempData=data[i][j].split("-")[0];					
						var temptrans=transferDimension[tempData];
						var retDataValue=(typeof(temptrans)=='undefined')?(data[i][j]):temptrans;
						if(j==0){
							templabel=retDataValue;
						}else{
							templabel=templabel+"-"+retDataValue;
						}
					}
					var temp={"label":templabel,"value":data[i][indexLocation].split("-")[0].replace("%","")};
					dataObj.push(temp);
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
		                            "hoverCapBgColor":"#f6fafe",
		                            "labelDisplay":"WRAP",
		                            "slantLabels":"0" 
		            		},                                                
		             	"data" :dataObj
		             	};
		             
			 	chartFire.setJSONData(jsonStr);
		     	chartFire.render(renderDiv);
		     	
		    
		     	$("#"+renderDiv).prev().find(".font_white>.flr>a").each(function(){
					$(this).click(function(){
					    //alert("sss");
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
			
			
			
			
			
			
			//指标选择
			function index_choose(type){
				var root=commondata.retJson.retRootIndex;
				var contend="";
				
				for(var i=0;i<root.length;i++){
					if(root[i].isChecked=="1"){
						contend=contend+"<div class='txt-fld_left'><input type='checkbox' checked class='indexChoose' value='"+root[i].indicateValue+"'/>"+root[i].indicateName+"</div>";
					}else{
						contend=contend+"<div class='txt-fld_left'><input type='checkbox' class='indexChoose' value='"+root[i].indicateValue+"'/>"+root[i].indicateName+"</div>";
					}
				}
				var a= new dialogComponent("<input type='checkbox' id='checkIndex'/>指标选择",contend,"chooseAddModel","chooseAddModel","chooseAddModelDiv_add");
				a.self_dialog(function(){},function(data){
						if(data==true) {
							var temp="";
							$(".indexChoose").each(function(index,checkObj){
								if($(checkObj).is(":checked")){
									if(temp.length==0){
										temp=$(checkObj).val();
									}else{
										temp=temp+","+$(checkObj).val();
									}
								}
							});	
									
							if(type=='day'){
								generateDayReport(temp);
							}else if(type=='month'){
								generateMonthReport(temp);
							}else if(type=='selfDefine'){
								genrateSelfReport(temp);
							}
							
								}
					});	
					
				$("#checkIndex").bind("click",function(){
					if($(this).is(":checked")){
						$(".indexChoose").each(function(index,checkObj){
							$(checkObj).prop("checked", true);
						});
					}else{
						$(".indexChoose").each(function(index,checkObj){
							$(checkObj).prop("checked", false);
						});
					}
				});	
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
<div id="chooseAddModelDiv"></div>
<div id="chooseAddModelDiv_add"></div>
<a href="#open_choose_window" rel="chooseAddModal"></a>



<!-- 指标新增end -->
<form id="excelExportForm" method="post" action="${pageContext.request.contextPath}/tawStatisticReport/excelExportXml.json" >
	<input type="hidden" id="demension_export" name="demension_export" value=""/>
	<input type="hidden" id="index_export" name="index_export" value=""/>
	<input type="hidden" id="data_export" name="data_export" value=""/>
	<input type="hidden" id="complexTitle_export" name="complexTitle_export" value=""/>
	<input type="hidden" id="model_id" name="model_id" value=""/>
</form>
	<div id="mainWrap_new_all">
		<div id="header_user">
			<div class="flr mg_r10 mg_t30"></div>
		</div>

		<div id="mainContent_user" class="fixed">
			<ul class="nav nav-tabs">
				<li role="presentation" id="selfDefine"><a onclick="tab_change(1,this)">自定义查询</a></li>
				<li role="presentation"><a onclick="tab_change(2,this)"> 月
						报 </a></li>
				<li role="presentation" class="active"><a
					onclick="tab_change(3,this)"> 日 报 </a></li>
			</ul>
			
		<div id="day_report" class="conMain mg_t18" style="height:auto;"><%@include file='./user_frame_day_report_xml.jsp'%></div>

		<div id="month_report" class="conMain mg_t18" style="height:auto";display:none;><%@include file='./user_frame_month_report_xml.jsp'%></div>

		<div id="selfDefine_report" class="conMain mg_t18" style="height:auto;display:none;"><%@include file='./user_frame_selfdefine_report_xml.jsp'%></div>
	

		</div>



	</div>
	
</body>
</html>