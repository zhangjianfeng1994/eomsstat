<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript">
	$(document).ready(function() {
		timeInit("initSelfDefine");
		genrateSelfReport('${checkItems}');
		
	});
	
	function genrateSelfReport(indexChoose){
		var beginTime=$("#self_define_begin_time").val();
		var endTime=$("#self_define_end_time").val();
		$("#selfDefineReport_graph").hide();
		$("#selfDefineReport_graph_combine").hide();
		$("#selfDefineReport_index").show(1);
		$("#selfDefineReport_demesion").show(1);
		
		generate_common_report(beginTime,endTime,"selfDefineReport_demesion","selfDefineReport_index",'${deptids}',null,indexChoose,'${type}');
	}
	
		//月报图形化
	function showSelfDefineGraph(){
		$("#selfDefineReport_graph").show(1);
		$("#selfDefineReport_index").hide();
		$("#selfDefineReport_demesion").hide();
		$("#selfDefineReport_graph_combine").hide();
		add_graph_area("selfDefineReport_graph","selfDefineReport");
	}
	
	function data_screening(){
		
		//加载数据筛选
		var contend_time="<div class='txt-fld_left'><label>时间粒度:</label><span class='pd_r5'>"+$("#self_define_begin_time").val()+"</span>至<span  class='pd_r5'>"+$("#self_define_end_time").val()+"</span></div>";
		
		var contend_param="<div class='txt-fld_left'><label>维度:</label><br/>";
		var demension=commondata.retJson.retDemension;
		for(var i=0;i<demension.length;i++){
				var tempChildrenList=demension[i].childrenList;
				var optionStr="";
				for(var j=0;j<tempChildrenList.length;j++){
					optionStr=optionStr+"<option value="+tempChildrenList[j].paramCode+">"+tempChildrenList[j].paramText+"</option>";
				}
				contend_param=contend_param+"<div class='txt-fld_left'><input type='checkbox' checked='true' class='parameters' value='"+demension[i].paramName+"'> <label> "+demension[i].paramName+":</label><span><select id='param_condition' class='selectpicker' multiple data-live-search='true'><optgroup label='维度选择' data-subtext='' data-icon='icon-ok'>"+optionStr+"</optgroup></select></span></div>";
		}
		ontend_param=contend_param+"</div>";
		
		var a= new dialogComponent("数据筛选",contend_time+contend_param,"paramAddModal","paramAddModal","paramAddModelDiv_add");
		
		a.self_dialog(function(){},function(choose_data){ 
			if(choose_data==true){
				var begintime=$("#self_define_begin_time").val();
				var endtime=$("#self_define_end_time").val();
			   
			    var param=$("#param_condition").val();
				//alert(param);
				generate_common_report(begintime,endtime,"selfDefineReport",param,null,'');
			}
		});
		//加载纬度树
		$(".selectpicker").selectpicker({});
		
		/*
		$("#beginTime").bind("click",function(){
			laydate({elem: '#beginTime',istime: true, format: 'YYYY-MM-DD'});
		});
		$("#endTime").bind("click",function(){
			laydate({elem: '#endTime',istime: true, format: 'YYYY-MM-DD'});
		});		*/	
	
	
	
	}
			
			
		
		//組合圖形-显示要选择的指标
	function showCombine_selfdefine(){
		$("#selfDefineReport_graph_combine").show(1);
		$("#selfDefineReport_graph").hide();
		$("#selfDefineReport_index").hide();
		$("#selfDefineReport_demesion").hide();
		combine_Graph_area("selfDefineReport_graph_combine","selfDefineReportCombine","selfDefineReport");
	
	}

</script>




<span class="flr gear_list" style="width:200px"><input type="hidden"
	id="list_model_id" /><a class="glyphicon glyphicon-share-alt"
	onclick="genrateSelfReport('${checkItems}')">返回</a> 
<!-- 	<a class="glyphicon glyphicon-indent-right" onclick="showComputeRule()">算法规则</a>  -->
<!-- 	<a class="glyphicon glyphicon-cog" onclick="data_screening()">筛选</a>  -->
	</span>
<ul class="btn_list ">
	<li><a><b>开始时间：</b></a><input type="text"
		class="input statistic-search_input" id="self_define_begin_time"
		name="self_define_begin_time" size="20" placeholder="请输入日报时间" style="width:70px" />-<a><b>结束时间：</b></a><input
		type="text" class="input statistic-search_input" id="self_define_end_time"
		name="self_define_end_time" size="20" placeholder="请输入日报时间" style="width:70px" /></li>
	<li>
		<button class="button bg-sub" onclick="genrateSelfReport('${checkItems}')">查询</button>
	</li>
	<li>
		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="showSelfDefineGraph()">图形报表</button>
	</li>
	<li>
		<button class="button bg-main" name=""
			id="" onclick="showCombine_selfdefine()">组合图形</button>
	</li>
	<li>
		<button class="button bg-main" name=""
			id="" onclick="index_choose('selfDefine')">指标选择</button>
	</li>
	<li>
		<button class="button bg-main" name="checkall-commonRep"
			id="checkall-commonRep" onclick="excelExport();">报表导出</button>
	</li>
</ul>


<div id="selfDefineReport_graph_combine"></div>
<div id="selfDefineReport_graph"></div>
<div>
	<div class="div_big_table_demsion" style="width:200px;">
		<table id="selfDefineReport_demesion" class="result-tab-big-demesion">
			
		</table>
	</div> 
	<div class="div_big_table_index" style="width:1000px;overflow-x:auto; ">
		<table id="selfDefineReport_index" class="result-tab-big-index">
			
		</table>
	</div> 
</div>
