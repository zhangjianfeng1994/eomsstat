<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript">
	$(document).ready(function() {
		timeInit("initDay");
		//generateDayReport();
		
	});
	//日报查询
	function generateDayReport(){
		var modelId='${modelid}';
		var beginTime=$("#dayReportTime").val();
		var endTime=addDay(beginTime);
		//alert(endTime);
		$("#dayReport_graph").hide();
		$("#dayMomList").hide();
		$("#dayMomRateList").hide();
		$("#newDayReport").hide();
		$("#dayReport").show(1);
		$("#newDayReport_graph").hide();
		//alert(beginTime);
		//alert(endTime);
		generate_common_report(0,modelId,null,beginTime,endTime,"dayReport",null,null,null,null);
	}
	
	//维度攥取数据之后返回
	function generatedayreturn(){
		var modelId='${modelid}';
		var beginTime=$("#dayReportTime").val();
		var endTime=addDay(beginTime);
		if(parent_graspDemensionCondition == "null" ||parent_graspDemensionCondition == undefined ||parent_graspDemensionCondition == null ){
	        generate_common_report(1,modelId,null,beginTime,endTime,"dayReport",null,null,null,null);
		}else{
			var spstr = parent_graspDemensionCondition.substring(0, parent_graspDemensionCondition.lastIndexOf("%20and"));
			if(spstr == "" ||spstr == null){
				generate_common_report(1,modelId,null,beginTime,endTime,"dayReport",null,null,null,null);
			}else{
				total_modelItems = total_modelItems.substring(0, total_modelItems.lastIndexOf(";"));
				var modelItemshuzu = total_modelItems.split(";");
				var modelItem = modelItemshuzu[modelItemshuzu.length-1];
				generate_common_report(1,modelId,modelItem,beginTime,endTime,"dayReport",null,null,null,spstr);
			}
		}
	}
	
	//增加一天
	function addDay(sDate){ 
		if(!sDate) return '';
		//  得到年部分
	    var y = sDate.substring(0, 4);
	   // 得到月部分
	    var m = sDate.substring(5, 7)-1;
	   
	    //得到日部分
	    var d = sDate.substring(8, 10);
	    var beginDate = new Date(y,m,d);
	    beginDate.setDate(beginDate.getDate()+1);
	    y = beginDate.getFullYear();
	    m = beginDate.getMonth()+1;
	    d = beginDate.getDate();
	    //alert(y + "-"+(m < 10 ? '0' + m : m) +"-"+ (d < 10 ? '0' + d : d));
	    return y + "-"+(m < 10 ? '0' + m : m) +"-"+ (d < 10 ? '0' + d : d);
	}
// 	function addDay(sDate){    	
		////alert(sDate);
//     	if(!sDate) return '';
	   ////默认1天
// 	    var days = 1;
	   //// 如果 sDate 的这个格式是不变的，那么可以使用一些方法得到 yyyy-MM-dd 这样的格式
	  ////  得到年部分
// 	    var y = sDate.substring(0, 4);
	   //// 得到月部分
// 	    var m = sDate.substring(5, 7);
	   
	    ////得到日部分
// 	    var d = sDate.substring(8, 10);
		////alert(y +"-"+m+"-"+d);
		////判断闰年
// 		if(((parseInt(y) % 4)==0) && ((parseInt(y) % 100)!=0) || ((parseInt(y) % 400)==0)) {
// 			if(2==parseInt(m,10)){
// 				if(28==parseInt(d,10)){
// 					m=(parseInt(m,10)+1).toString();
// 					d='1';
// 				}else{
// 					m=parseInt(m,10).toString();
// 					d=(parseInt(d,10)+1).toString();
// 				}
// 			}else if(1==parseInt(m,10)||3==parseInt(m,10)||5==parseInt(m,10)||7==parseInt(m,10)||8==parseInt(m,10)||10==parseInt(m,10)){
// 				if(31==parseInt(d,10)){
// 					m=(parseInt(m,10)+1).toString();
// 					d='1';
// 				}else{
// 					m=parseInt(m,10).toString();
// 					d=(parseInt(d,10)+1).toString();
// 				}
// 			}else if(2==parseInt(m,10)||4==parseInt(m,10)||6==parseInt(m,10)||9==parseInt(m,10)||11==parseInt(m,10)){
// 				if(30==parseInt(d,10)){
// 					m=(parseInt(m,10)+1).toString();
// 					d='1';
// 				}else{
// 					m=parseInt(m,10).toString();
// 					d=(parseInt(d,10)+1).toString();
// 				}
// 			}else{
// 				if(31==parseInt(d,10)){
// 					y=(parseInt(y)+1).toString();
// 					m='1';
// 					d='1';
// 				}else{
// 					m=parseInt(m,10).toString();
// 					d=(parseInt(d,10)+1).toString();
// 				}
// 			}
// 		}else{ 
// 			if(2==parseInt(m,10)){
// 				if(29==parseInt(d,10)){
// 					m=(parseInt(m,10)+1).toString();
// 					d='1';
// 				}else{
// 					m=parseInt(m,10).toString();
// 					d=(parseInt(d,10)+1).toString();
// 				}
// 			}else if(1==parseInt(m,10)||3==parseInt(m,10)||5==parseInt(m,10)||7==parseInt(m,10)||8==parseInt(m,10)||10==parseInt(m,10)){
// 				if(31==parseInt(d,10)){
// 					m=(parseInt(m,10)+1).toString();
// 					d='1';
// 				}else{
// 					m=parseInt(m,10).toString();
// 					d=(parseInt(d,10)+1).toString();
// 				}
// 			}else if(2==parseInt(m,10)||4==parseInt(m,10)||6==parseInt(m,10)||9==parseInt(m,10)||11==parseInt(m,10)){
// 				if(30==parseInt(d,10)){
// 					m=(parseInt(m,10)+1).toString();
// 					d='1';
// 				}else{
// 					m=parseInt(m,10).toString();
// 					d=(parseInt(d,10)+1).toString();
// 				}
// 			}else{
// 				if(31==parseInt(d,10)){
// 					y=(parseInt(y)+1).toString();
// 					m='1';
// 					d='1';
// 				}else{
// 					m=parseInt(m,10).toString();
// 					d=(parseInt(d,10)+1).toString();
// 				}
// 			}
// 		}
	    //alert(y + "-"+(m < 10 ? '0' + m : m) +"-"+ (d < 10 ? '0' + d : d));
// 	    return y + "-"+(m < 10 ? '0' + m : m) +"-"+ (d < 10 ? '0' + d : d);
// 	}
	
	//日报图形化
	function showDayGraph(){
		$("#dayReport_graph").show(1);
		$("#dayReport").hide();
		$("#newDayReport_graph").hide();
		$("#newDayReport").hide();
		$("#dayMomRateList").hide();
		$("#dayMomList").hide();
		add_graph_area("dayReport_graph","dayReport");
	}
	
	function showDayMomList(){
		var modelId='${modelid}';
		var beginTime=$("#dayReportTime").val();
		var endTime=addDay(beginTime);
		$("#dayReport_graph").hide();
		$("#dayReport").hide();
		$("#newDayReport_graph").hide();
		$("#newDayReport").hide();
		$("#dayMomRateList").hide();
		$("#dayMomList").show(1);
		//环比数据
		generate_Mom_report(modelId,null,beginTime,endTime,"dayMomList");
	}
	//環比增長率
	function showDayMomRateList(){
		var modelId='${modelid}';
		var beginTime=$("#dayReportTime").val();
		var endTime=addDay(beginTime);
		$("#dayReport_graph").hide();
		$("#dayReport").hide();
		$("#newDayReport_graph").hide();
		$("#newDayReport").hide();
		$("#dayMomRateList").show(1);
		$("#dayMomList").hide();
		//环比数据
		generate_Mom_Rate_report(modelId,null,beginTime,endTime,"dayMomRateList");
	}
		//組合圖形-显示要选择的指标
	function showIndexDay(){
		$("#newDayReport_graph").show(1);
		$("#dayReport_graph").hide();
		$("#newDayReport").hide();
		$("#dayReport").hide();
	

		add_new_graph_area("newDayReport_graph","newDayReport");
	
	}

</script>


<span class="flr gear_list" style="width:160px">
	<input type="hidden" id="list_model_id" />
<!-- 	<a class="glyphicon glyphicon-cog" onclick="data_screening('dayReport')">筛选</a>  -->
<!-- 	<a class="glyphicon glyphicon-share-alt" onclick="generateDayReport()">返回</a> -->
<!-- 	<a class="glyphicon glyphicon-indent-right" onclick="showComputeRule()">算法规则</a> -->
</span>
<ul class="btn_list ">
	<li><a> <b>时间选择：</b></a><input type="text"
		class="input statistic-search_input" id="dayReportTime"
		name="dayReportTime" size="20" placeholder="请输入日报时间" /></li>
	<li>
		<button class="button bg-sub" onclick="generateDayReport();">查询</button>
	</li>
	<li>
		<button class="button bg-main" onclick="data_screening('dayReport')">高级查询</button>
	</li>
	<li> 
 		<button class="button bg-main" name="checkall-commonRep" 
			id="checkall-commonRep" onclick="showDayMomList();">报表环比</button>
	</li>
 	<li>
 		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="showDayMomRateList();">环比增长率</button>
	</li> 
	<li>
		<button class="button bg-main" onclick="showDayGraph();" >图形报表</button>
	</li>
	<li>
		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="showIndexDay()">组合图形</button>
	</li>
	<li>
		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="excelExport();">报表导出</button>
	</li>
	<li>
		<button  class="button bg-main" name="checkall-commonRep"
			id="returnbutton" onclick="generatedayreturn();">返回</button>
	</li>
</ul>
<div id="dayReport_graph"></div>
<table id="dayReport" class="result-tab">
</table>

<table id="dayMomList" class="result-tab">
</table>
<table id="dayMomRateList" class="result-tab">
</table>

<div id="newDayReport_graph"></div>
<table id="newDayReport" class="result-tab">
</table>