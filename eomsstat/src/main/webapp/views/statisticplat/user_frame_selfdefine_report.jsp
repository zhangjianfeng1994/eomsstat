<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript">
	$(document).ready(function() {
		timeInit("initSelfDefine");
		//genrateSelfReport();
	});
	
	function genrateSelfReport(){
		var modelId='${modelid}';
		var beginTime=$("#self_define_begin_time").val();
		var endTime=$("#self_define_end_time").val();
		$("#selfDefineReport_graph").hide();
		$("#selfDefineReport").show(1);
		generate_common_report(0,modelId,null,beginTime,endTime,"selfDefineReport",null,null,null,null);
	}
	
	//维度攥取数据之后返回
	function generateselfreturn(){
		var modelId='${modelid}';
		var beginTime=$("#self_define_begin_time").val();
		var endTime=$("#self_define_end_time").val();
		
		if(parent_graspDemensionCondition == "null" ||parent_graspDemensionCondition == undefined ||parent_graspDemensionCondition == null ){
	       	generate_common_report(1,modelId,null,beginTime,endTime,"selfDefineReport",null,null,null,null);
		}else{
			var spstr = parent_graspDemensionCondition.substring(0, parent_graspDemensionCondition.lastIndexOf("%20and"));
			if(spstr == "" ||spstr == null){
				generate_common_report(1,modelId,null,beginTime,endTime,"selfDefineReport",null,null,null,null);
			}else{
				total_modelItems = total_modelItems.substring(0, total_modelItems.lastIndexOf(";"));
				var modelItemshuzu = total_modelItems.split(";");
				var modelItem = modelItemshuzu[modelItemshuzu.length-1];
				generate_common_report(1,modelId,modelItem,beginTime,endTime,"selfDefineReport",null,null,null,spstr);
			}
		}
	}
	
	
		//月报图形化
	function showSelfDefineGraph(){
		$("#selfDefineReport_graph").show(1);
		$("#selfDefineReport").hide();
		$("#newselfDefineReport_graph").hide();
		$("#newselfDefineReport").hide();
		$("#selfDefineMomRateList").hide();
		$("#selfDefineMomList").hide();
		
		add_graph_area("selfDefineReport_graph","selfDefineReport");
	}
	
	function showSelfDefineMomList(){
		var modelId='${modelid}';
		var beginTime=$("#self_define_begin_time").val();
		var endTime=$("#self_define_end_time").val();
		$("#selfDefineReport_graph").hide();
		$("#selfDefineReport").hide();
		$("#newselfDefineReport_graph").hide();
		$("#newselfDefineReport").hide();
		$("#selfDefineMomRateList").hide();
		$("#selfDefineMomList").show(1);
		//环比数据
		generate_Mom_report(modelId,null,beginTime,endTime,"selfDefineMomList");
	}
	//環比增長率
	function showSelfDefineMomRateList(){
		var modelId='${modelid}';
		var beginTime=$("#self_define_begin_time").val();
		var endTime=$("#self_define_end_time").val();
		$("#selfDefineReport_graph").hide();
		$("#selfDefineReport").hide();
		$("#newselfDefineReport_graph").hide();
		$("#newselfDefineReport").hide();
		$("#selfDefineMomRateList").show(1);
		$("#selfDefineMomList").hide();
		//环比数据
		generate_Mom_Rate_report(modelId,null,beginTime,endTime,"selfDefineMomRateList");
	}

	//組合圖形-显示要选择的指标
	function showIndexSelfDefine(){
		$("#newselfDefineReport_graph").show(1);
		$("#selfDefineReport_graph").hide();
		$("#newselfDefineReport").hide();
		$("#selfDefineReport").hide();


		add_new_graph_area("newselfDefineReport_graph","newselfDefineReport");
	
	}
</script>


<span class="flr gear_list" style="width:200px"><input type="hidden"
	id="list_model_id" />
<!-- 	<a class="glyphicon glyphicon-cog" onclick="data_screening('selfDefineReport')">筛选</a>  -->
<!-- 	<a class="glyphicon glyphicon-share-alt" onclick="genrateSelfReport()">返回</a>  -->
<!-- 	<a class="glyphicon glyphicon-indent-right" onclick="showComputeRule()">算法规则</a>  -->
<!-- 	<a class="glyphicon glyphicon-cog" onclick="data_screening()">筛选</a> -->

</span>
<ul class="btn_list ">
	<li><a><b>开始时间：</b></a><input type="text"
		class="input statistic-search_input" id="self_define_begin_time"
		name="self_define_begin_time" size="20" placeholder="请输入日报时间" style="width:70px"/>-<a><b>结束时间：</b></a><input
		type="text" class="input statistic-search_input" id="self_define_end_time"
		name="self_define_end_time" size="20" placeholder="请输入日报时间" style="width:70px"/></li>
	<li>
		<button class="button bg-sub" onclick="genrateSelfReport()">查询</button>
	</li>
	<li>
		<button class="button bg-main" onclick="data_screening('selfDefineReport')">高级查询</button>
	</li>
	
	 	<!-- <li>
		<button class="button bg-main" name="checkall-commonRep" 
 			id="checkall-commonRep" onclick="showSelfDefineMomList();">报表环比</button> 
	</li> 
	<li> 
 		<button class="button bg-main" name="checkall-commonRep"
 			id="checkall-commonRep" onclick="showSelfDefineMomRateList();">环比增长率</button>
	</li>  -->
	<li>
		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="showSelfDefineGraph()">图形报表</button>
	</li>
	<li>
		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="showIndexSelfDefine()">组合图形</button>
	</li>
	<li>
		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="excelExport();">报表导出</button>
	</li>
	<li>
		<button  class="button bg-main" name="checkall-commonRep"
			id="returnbutton" onclick="generateselfreturn();">返回</button>
	</li>
</ul>
<div id="selfDefineReport_graph"></div>
<table id="selfDefineReport" class="result-tab">
</table>

<table id="selfDefineMomList" class="result-tab">
</table>
<table id="selfDefineMomRateList" class="result-tab">
</table>

<div id="newselfDefineReport_graph"></div>
<table id="newselfDefineReport" class="result-tab">
</table>
