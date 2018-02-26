<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript">
	$(document).ready(function() {
		timeInit("initDay");
		//generateDayReport('${checkItems}');
	});
	//日报
	function generateDayReport(indexChoose){
		var modelId='${modelid}';
		var beginTime=$("#dayReportTime").val();
		var endTime=addDay(beginTime);
		//alert(beginTime+"-"+endTime);
		$("#dayReport_graph_combine").hide();
		$("#dayReport_graph").hide();
		
		$("#dayReport_demesion").show(1);
		$("#dayReport_index").show(1);
		generate_common_report(beginTime,endTime,"dayReport_demesion","dayReport_index",'${deptids}',null,indexChoose,'${type}');
	}
	
	//增加一天
	function addDay(sDate){    	
    	if(!sDate) return '';
	    // 默认1天
	    var days = 1;
	    // 如果 sDate 的这个格式是不变的，那么可以使用一些方法得到 yyyy-MM-dd 这样的格式
	    // 得到年部分
	    var y = sDate.substring(0, 4);
	    // 得到月部分
	    var m = sDate.substring(5, 7);
	    // 得到日部分
	    var d = sDate.substring(8, 10);
		//判断闰年
		
		//alert(y +"-"+m+"-"+d);
		if(((parseInt(y) % 4)==0) && ((parseInt(y) % 100)!=0) || ((parseInt(y) % 400)==0)) {
			if(2==parseInt(m)){
				if(28==parseInt(d)){
					m=(parseInt(m)+1).toString();
					d='1';
				}else{
					d=(parseInt(d)+1).toString();
				}
			}else if(1==parseInt(m)||3==parseInt(m)||5==parseInt(m)||7==parseInt(m)||8==parseInt(m)||10==parseInt(m)){
				if(31==parseInt(d)){
					m=(parseInt(m)+1).toString();
					d='1';
				}else{
					d=(parseInt(d)+1).toString();
				}
			}else if(2==parseInt(m)||4==parseInt(m)||6==parseInt(m)||9==parseInt(m)||11==parseInt(m)){
				if(30==parseInt(d)){
					m=(parseInt(m)+1).toString();
					d='1';
				}else{
					d=(parseInt(d)+1).toString();
				}
			}else{
				if(31==parseInt(d)){
					y=(parseInt(y)+1).toString();
					m='1';
					d='1';
				}else{
					d=(parseInt(d)+1).toString();
				}
			}
		}else{ 
			if(2==parseInt(m)){
				if(29==parseInt(d)){
					m=(parseInt(m)+1).toString();
					d='1';
				}else{
					d=(parseInt(d)+1).toString();
				}
			}else if(1==parseInt(m)||3==parseInt(m)||5==parseInt(m)||7==parseInt(m)||8==parseInt(m)||10==parseInt(m)){
				if(31==parseInt(d)){
					m=(parseInt(m)+1).toString();
					d='1';
				}else{
					d=(parseInt(d)+1).toString();
				}
			}else if(2==parseInt(m)||4==parseInt(m)||6==parseInt(m)||9==parseInt(m)||11==parseInt(m)){
				if(30==parseInt(d)){
					m=(parseInt(m)+1).toString();
					d='1';
				}else{
					d=(parseInt(d)+1).toString();
				}
			}else{
				if(31==parseInt(d)){
					y=(parseInt(y)+1).toString();
					m='1';
					d='1';
				}else{
					d=(parseInt(d)+1).toString();
				}
			}
		}
	    return y + "-"+(m < 10 ?  m : m) +"-"+ (d < 10 ? '0' + d : d);
	}
	
	//日报图形化
	function showDayGraph(){
		$("#dayReport_graph").show(1);
		$("#dayReport_demesion").hide();
		$("#dayReport_index").hide();
		$("#dayReport_graph_combine").hide();
		add_graph_area("dayReport_graph","dayReport");
	}
	
		//組合圖形-显示要选择的指标
	function showCombine(){
		$("#dayReport_graph_combine").show(1);
		$("#dayReport_graph").hide();
		$("#dayReport_demesion").hide();
		$("#dayReport_index").hide();
		combine_Graph_area("dayReport_graph_combine","dayReportCombine","dayReport");
	}
</script>

<span class="flr gear_list" style="width:160px">
	<input type="hidden" id="list_model_id" />
	<a class="glyphicon glyphicon-share-alt" onclick="generateDayReport('${checkItems}')">返回</a>
<!-- 	<a class="glyphicon glyphicon-indent-right" onclick="showComputeRule()">算法规则</a> -->
</span>
<ul class="btn_list ">
	<li><a> <b>时间选择：</b></a><input type="text"
		class="input statistic-search_input" id="dayReportTime"
		name="dayReportTime" size="20" placeholder="请输入日报时间" /></li>
	<li>
		<button class="button bg-sub" onclick="generateDayReport('${checkItems}');">查询</button>
	</li>
	<li>
		<button class="button bg-main" onclick="showDayGraph();" >图形报表</button>
	</li>
	<li>
		<button class="button bg-main" onclick="showCombine();" >组合图形</button>
	</li>
	<li>
		<button class="button bg-main" onclick="index_choose('day');" >指标选择</a></button>
	</li>
	<li>
		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="excelExport();">报表导出</button>
	</li>
</ul>
<div id="dayReport_graph_combine"></div>
<div id="dayReport_graph"></div>
<!-- <div style="width:100%;overflow-x:auto;"> -->
<!-- <table id="dayReport" class="result-tab" > -->
<!-- </table> -->
<!-- </div> -->

<div>
	<div class="div_big_table_demsion" style="width:200px;">
		<table id="dayReport_demesion" class="result-tab-big-demesion">
			
		</table>
	</div> 
	<div class="div_big_table_index" style="width:1000px;overflow-x:auto; ">
		<table id="dayReport_index" class="result-tab-big-index">
			
		</table>
	</div> 
</div>
