<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript">
	$(document).ready(function() {
	
		timeInit("initMonth");
		//generateMonthReport("");
		$("#month_rep_year").selectpicker();
		$("#month_rep_month").selectpicker();
	
	});
	
	//月报
	function generateMonthReport(indexChoose){
		//var beginTime=$("#monthReportTime").val();
		
		var beginTime=$("#month_rep_year").val()+"-"+$("#month_rep_month").val();
		//alert(beginTime);
		var endTime=addMonth(beginTime);
		$("#monthReport_graph").hide();
		$("#monthReport_graph_combine").hide();
		$("#monthReport_demesion").show(1);
		$("#monthReport_index").show(1);
		
		
		generate_common_report(beginTime+"-1",endTime,"monthReport_demesion","monthReport_index",'${deptids}',null,indexChoose,'${type}');
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
		$("#monthReport").hide();
		$("#monthReport_demesion").hide();
		$("#monthReport_index").hide();
		
		$("#monthReport_graph_combine").hide();
		add_graph_area("monthReport_graph","monthReport");
	}
	
	
	

	
		//組合圖形-显示要选择的指标
	function showCombine_month(){
		$("#monthReport_graph_combine").show(1);
		$("#monthReport_graph").hide();
		$("#monthReport_demesion").hide();
		$("#monthReport_index").hide();
		combine_Graph_area("monthReport_graph_combine","monthReportCombine","monthReport");
	
	}

</script>


<span class="flr gear_list" style="width:160px"><input type="hidden"
	id="list_model_id" style="" /><a class="glyphicon glyphicon-share-alt"
	onclick="generateMonthReport('${checkItems}')">返回</a> 
<!-- 	<a class="glyphicon glyphicon-indent-right" onclick="showComputeRule()">算法规则</a></span> -->
</span>
<ul class="btn_list ">
	<li><a> <b>时间选择：</b></a><!--<input type="text"	class="input statistic-search_input" id="monthReportTime" name="monthReportTime" size="20" placeholder="请输入月报时间" />-->
		<select id="month_rep_year" class="selectpicker" data-width="70px"><option>2014</option><option>2015</option><option>2016</option></select><a><b>年</b></a>
		<select id="month_rep_month" class="selectpicker" data-width="70px"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option></select><a><b>月</b></a>
	</li>
	<li>
		<button class="button bg-sub" onclick="generateMonthReport('${checkItems}');">查询</button>
	</li>
	<li>
		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="showMonthGraph()">图形报表</button>
	</li>
	<li>
		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="showCombine_month()">组合图形</button>
	</li>
	<li>
		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="index_choose('month')">指标选择</button>
	</li>
	<li>
		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="excelExport();">报表导出</button>
	</li>
</ul>


<div id="monthReport_graph_combine"></div>
<div id="monthReport_graph"></div>
<!-- <div style="width:100%;overflow-x:auto;"> -->
<!-- <table id="monthReport" class="result-tab"> -->
<!-- </table> -->
<!-- </div> -->

<div>
	<div class="div_big_table_demsion" style="width:200px;">
		<table id="monthReport_demesion" class="result-tab-big-demesion">
			
		</table>
	</div> 
	<div class="div_big_table_index" style="width:1000px;overflow-x:auto; ">
		<table id="monthReport_index" class="result-tab-big-index">
			
		</table>
	</div> 
</div>


