<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript">
	$(document).ready(function() {
	
		timeInit("initMonth");
		//generateMonthReport();
		$("#month_rep_year").selectpicker();
		$("#month_rep_month").selectpicker();
	});
	
	//月报
	function generateMonthReport(){
		var modelId='${modelid}';
		//var beginTime=$("#monthReportTime").val();
		var beginTime=$("#month_rep_year").val()+"-"+$("#month_rep_month").val();
		
		var endTime=addMonth(beginTime);
		$("#monthMomList").hide();
		$("#monthMomRateList").hide();
		$("#monthReport_graph").hide();
		$("#monthReport").show(1);
		$("#newMonthReport_graph").hide();
		$("#newMonthReport").hide();
		generate_common_report(0,modelId,null,beginTime+"-1",endTime,"monthReport",null,null,null,null);
	}
	//维度攥取数据之后返回
	function generatemonthreturn(){
		var modelId='${modelid}';
		var beginTime=$("#month_rep_year").val()+"-"+$("#month_rep_month").val();
		var endTime=addMonth(beginTime);
		
		if(parent_graspDemensionCondition == "null" ||parent_graspDemensionCondition == undefined ||parent_graspDemensionCondition == null ){
	       	generate_common_report(1,modelId,null,beginTime+"-1",endTime,"monthReport",null,null,null,null);
		}else{
			var spstr = parent_graspDemensionCondition.substring(0, parent_graspDemensionCondition.lastIndexOf("%20and"));
			if(spstr == "" ||spstr == null){
				generate_common_report(1,modelId,null,beginTime+"-1",endTime,"monthReport",null,null,null,null);
			}else{
				total_modelItems = total_modelItems.substring(0, total_modelItems.lastIndexOf(";"));
				var modelItemshuzu = total_modelItems.split(";");
				var modelItem = modelItemshuzu[modelItemshuzu.length-1];
				generate_common_report(1,modelId,modelItem,beginTime+"-1",endTime,"monthReport",null,null,null,spstr);
			}
		}
	}
	

	//增加一月
	function addMonth(currentDay){
		var arr = currentDay.split("-"); 
    	var newdt = new Date(Number(arr[0]),Number(arr[1]));  
    	repnewdt = newdt.getFullYear() + "-" +   (newdt.getMonth()+1) + "-" + newdt.getDate();
    	return repnewdt;
	}
	
		//月报图形化
	function showMonthGraph(){
		$("#monthReport_graph").show(1);
		$("#newMonthReport_graph").hide();
		$("#newMonthReport").hide();
		$("#monthReport").hide();
		$("#monthMomList").hide();
		$("#monthMomRateList").hide();
		$("#newMonthReport_graph").hide();
		
		add_graph_area("monthReport_graph","monthReport");
	}
	
	function showMonthMomList(){
		var modelId='${modelid}';
		//var beginTime=$("#monthReportTime").val();
		var beginTime=$("#month_rep_year").val()+"-"+$("#month_rep_month").val();
		var endTime=addMonth(beginTime);
		$("#monthReport_graph").hide();
		$("#monthReport").hide();
		$("#newMonthReport_graph").hide();
		$("#newMonthReport").hide();
		$("#monthMomRateList").hide();
		$("#monthMomList").show(1);
		//环比数据
		generate_Mom_report(modelId,null,beginTime+"-1",endTime,"monthMomList");
	}
	//環比增長率
	function showMonthMomRateList(){
		var modelId='${modelid}';
		//var beginTime=$("#monthReportTime").val();
		var beginTime=$("#month_rep_year").val()+"-"+$("#month_rep_month").val();
		var endTime=addMonth(beginTime);
		$("#monthReport_graph").hide();
		$("#monthReport").hide();
		$("#newMonthReport_graph").hide();
		$("#newMonthReport").hide();
		$("#monthMomRateList").show(1);
		$("#monthMomList").hide();
		//环比数据
		generate_Mom_Rate_report(modelId,null,beginTime+"-1",endTime,"monthMomRateList");
	}
	
	
	//組合圖形-显示要选择的指标
	function showIndex(){
		$("#newMonthReport_graph").show(1);
		$("#monthReport_graph").hide();
		$("#newMonthReport").hide();
		$("#monthReport").hide();
		$("#monthMomList").hide();
		$("#monthMomRateList").hide();

		add_new_graph_area("newMonthReport_graph","newMonthReport");
	
	}

</script>


<span class="flr gear_list" style="width:160px"><input type="hidden"
	id="list_model_id" style="" />
<!-- 	<a class="glyphicon glyphicon-cog" onclick="data_screening('monthReport')">筛选</a>  -->
<!-- 	<a class="glyphicon glyphicon-share-alt" onclick="generateMonthReport()">返回</a>  -->
<!-- 	<a class="glyphicon glyphicon-indent-right" onclick="showComputeRule()">算法规则</a></span> -->
</span>
<ul class="btn_list ">
	<li><a> <b>时间选择：</b></a><!--<input type="text"	class="input statistic-search_input" id="monthReportTime" name="monthReportTime" size="20" placeholder="请输入月报时间" />-->
	<select id="month_rep_year" class="selectpicker" data-width="70px"><option>2014</option><option selected>2015</option><option>2016</option><option>2017</option><option>2018</option><option>2019</option></select><a><b>年</b></a>
	<select id="month_rep_month" class="selectpicker" data-width="70px"><option selected>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option></select><a><b>月</b></a>	
	</li>
	<li>
		<button class="button bg-sub" onclick="generateMonthReport();">查询</button>
	</li>
	<li>
		<button class="button bg-main" onclick="data_screening('monthReport')">高级查询</button>
	</li>
 	<li>
 		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="showMonthMomList();">报表环比</button>
 	</li> 
	<li> 
 		<button class="button bg-main" name="checkall-commonRep"
 			id="checkall-commonRep" onclick="showMonthMomRateList();">环比增长率</button> 
	</li>
	<li>
		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="showMonthGraph()">图形报表</button>
	</li>
	<li>
		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="showIndex()">组合图形</button>
	</li>
	<li>
		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="excelExport();">报表导出</button>
	</li>
	<li>
		<button  class="button bg-main" name="checkall-commonRep"
			id="returnbutton" onclick="generatemonthreturn();">返回</button>
	</li>
</ul>

<div id="monthReport_graph"></div>
<table id="monthReport" class="result-tab">
</table>
<table id="monthMomList" class="result-tab">
</table>
<table id="monthMomRateList" class="result-tab">
</table>

<div id="newMonthReport_graph"></div>
<table id="newMonthReport" class="result-tab">
</table>