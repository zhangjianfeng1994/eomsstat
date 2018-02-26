<%@include file="../common/header.jsp"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String modelid=request.getParameter("modelid");
request.setAttribute("modelid", modelid);
 %>
<html>
<head>
<meta charset="UTf-8">
<title>模板编辑</title>
<link rel="stylesheet" type="text/css" href="${cssPath}/maskLayer.css">
<script type="text/javascript">
var json = "";
$(document).ready(function(){
		laydate.skin('yalan');
		init_animate_show();
		getTree();
		//加载树
		//alert("=="+json);
		//$.fn.zTree.init($("#model_define_tree"),setting_tree);
		$("#tree_central").treeview({levels: 1,data: json});
		//alert(JSON.stringify($("#tree_central").treeview("getAllNodes")));
		//alert(JSON.stringify($("#tree_central").treeview("data")));
		//数据初始化
		init_data("");	
		console.log(JSON.stringify($("#tree_central").treeview("getAllNodes")));
	});
	/**配置模板树开始**/
	var newCount = 1;
		
	//var setting_tree =[{id:"model_tree_root",text: "统计模板树",value:"1"}];
	//var setting_tree =[{id:"model_tree_root",text: "统计模板树",value:"1",nodes:[{id:"www",text: "子节点1",value:"1"},{id:"sss",text: "子节点2",value:"2"}]}]
	function OnRightClick(event, treeId, treeNode){
	
		 var a= new dialogComponent("指标新增","sssss","indexAddModal","open_index_window","chooseDimensionDiv");
			
			a.confirm_dialog(function(result){
				alert(result);
			});	
	}
	
	/**配置模板树结束**/
	/**配置指标维度拖拽开始**/
	function init_draggable_param(){
		
		 $("#param_menu li").draggable({
                    appendTo: '#tree_central',
                    helper: 'clone',
                    cursor: 'move'
                });
         $("#index_menu li").draggable({
                    appendTo: '#tree_central',
                    cursor: 'move',
                    helper: 'clone'
                });
	}
	/**配置指标维度拖拽结束**/
	/**配置数据初始化开始**/
	function init_data(table_init,modelid){
	var modelid='${modelid}';
	  // alert(modelid);
		//报表归属
		$.ajax({
				url : '${pageContext.request.contextPath}/tawStatisticModel/getTableBelongMap.json',
				type : 'post',
				cache : false,
				async : true,
				data:{'modelId':modelid},
				success : function(data) {
					var str=data.retJson.tableBelong;
					var index = data.retJson.index;
					//alert(index);
					var table_belong= $("#table_belong");
					$("#table_belong option").remove();
					
					for(var i=0;i<data.retJson.tableBelong.length;i++){
					  var temp=data.retJson.tableBelong[i];
					  if(i==index){
					  	 table_init=temp.EndName;
					 	 table_belong.append("<option selected='selected'  value='"+temp.EndName+"'>"+temp.CnName+"</option>")
					  }else{
					  	 table_belong.append("<option value='"+temp.EndName+"'>"+temp.CnName+"</option>")
					  }
					 
					}
					//$("#table_belong").selectpicker('refresh');
					//$("#table_belong").selectpicker({'width':'100px'});
					//table_belong.selectpicker();
					
					init_item_index(table_init,null);
					init_item_param(table_init,null);
					inin_detailList(table_init);
				 }
		 });
	}
	
	function init_data_choose(table_init){
		init_item_index(table_init,null);
		init_item_param(table_init,null);
		inin_detailList(table_init);
		
	}
	
	
	function init_item_index(table_Name,item_Name){
		//指标
		$.ajax({
				url : '${pageContext.request.contextPath}/tawStatisticIndex/getIndexByTableName.json',
				type : 'post',
				cache : false,
				async : true,
				data:{'table_Name':table_Name,'tableCNName':item_Name},
				success : function(data) {
					var indexs=data.retJson.indexs;
					$("#index_menu").empty();
					for(var i=0;i<indexs.length;i++){
						var indicateName = indexs[i].indicateName;
						$("#index_menu").append("<li style='white-space: nowrap;overflow: hidden;text-overflow: ellipsis;'><span class='flr'></span><a title='"+indicateName+"' value='"+indexs[i].id+"'>"+indicateName+"</a></li>");					
					}
					
					//加载拖拽事件
					init_draggable_param();
					
				 }
		 });
	
	}
	
	function init_item_param(table_Name,item_Name){
		//维度
		$.ajax({
				url : '${pageContext.request.contextPath}/tawStatisticParam/getParamByTableName.json',
				type : 'post',
				cache : false,
				async : true,
				data:{'table_Name':table_Name,'tableCNName':item_Name},
				success : function(data) {
					var params=data.retJson.params;
					$("#param_menu").empty();
					for(var i=0;i<params.length;i++){
						var paramName = params[i].paramName;
						$("#param_menu").append("<li style='white-space: nowrap;overflow: hidden;text-overflow: ellipsis;'><span class='flr'></span><a title='"+paramName+"' value='"+params[i].id+"'>"+paramName+"</a></li>");					
					}
					//加载拖拽事件
					init_draggable_param();
					queryConditionInit();
				}
			});
	
	}
	
	
	/**配置数据初始化结束**/
	
	/**配置指标查询开始**/
	function queryConditionInit(){
		
		$("#index_search").bind("click",function(){
			init_item_index($("#table_belong").val(),$("#index_search_value").val());
		});
		$("#param_search").bind("click",function(){
			init_item_param($("#table_belong").val(),$("#param_search_value").val());
		});
	}
	/**配置指标查询结束**/
	function getTree() {

		var modelid='${modelid}';
		//alert(modelid);	
		$.ajax({
			async : false,
			url : '${pageContext.request.contextPath}/tawStatisticReport/getModelItemByModelId.json',
			type : 'post',
	
			data:{'modelId':modelid},
			success : function(data) {				
				var treeData = data.retJson.res;
				//alert(JSON.stringify(treeData));
				json = eval("(" + treeData + ")");
			}
		});
        
    }
	
	
	
	
	function init_animate_show(){
		//动态隐藏元素
		$(".flr.fast_tag").bind("click",function(){
			
			var array=$(this).children("img").attr("src").split("/");
			var imgName=array[array.length-1];
			if("fast_left.png"==imgName){
				$("#right_choice_area").hide(800);
				$("#right_content_area").animate({marginLeft:"0px"});
				//图标切换
				$(this).children("img").attr("src",'${imagePath}/fast_right.png');
			}else{
				$("#right_choice_area").show(800);
				$("#right_content_area").animate({marginLeft:"270px"});
				//图标切换
				$(this).children("img").attr("src",'${imagePath}/fast_left.png');
			}
		});
		
	
	}
	
	function tab_change(param,obj){
		$(".flr.statistic-title_list").children("li").each(function(index,li_obj){  
   			$(li_obj).children("a").removeClass("title_tab_on");
		});
		
		
		$(obj).addClass("title_tab_on");
			if("1"==param){
				$("#panel_title").html("报表自定义");
				$("#report_list_area").hide();
				$("#chart_list_area").hide();
				$("#self_define_area").show(1000);
			}else if("2"==param){
				$("#panel_title").html("报表");
				$("#chart_list_area").hide();
				$("#self_define_area").hide();
				$("#report_list_area").show(1000);
			}else if("3"==param){
				$("#panel_title").html("图形");
				$("#report_list_area").hide();
				$("#self_define_area").hide();
				$("#chart_list_area").show(1000);
			}

	}
</script>

</head>
<!--  scroll="no"  overflow:scroll -->
<body>
	<!-- 指标新增begin -->
	<div id="chooseDimensionDiv"></div>
	<!-- 指标新增end -->

    <div id="content_all_area">
	
	   <!-- 中部内容区域 begin -->
       <div id="right_choice_area" class="fll w_250">
        <!--指标选择 开始-->
        <div class="statistic-panel">
          <div class="statistic-conHead">
          	<div>
          	 	<span><select id="table_belong" onchange="init_data_choose(this.options[this.options.selectedIndex].value)" style="color:gray"></select></span>
          	 	<span class="fast_tag div-no-line">报表归属选择</span>
          	 </div>
          </div>
          
          <div class="conMain mg_t5 pd_b5">
          	
            <h4 class="form_title"><b>选择维度</b> </h4>
            <ul class="btn_list ">
              <li>
                <input type="text" class="input statistic-search_input" id="param_search_value" name="siteurl" size="21" placeholder="请填写网址"  />
              </li>
              <li>
                <button class="button bg-main" id="param_search" type="submit">搜索</button>
              </li>
            </ul>
            
            <ul class="userMenu" id="param_menu" style="max-height: 230px;overflow: auto;">  
            </ul>
            
            <h4 class="form_title"><b>选择指标 </b></h4>
            <ul class="btn_list ">
              <li>
                <input type="text" class="input statistic-search_input" id="index_search_value" name="siteurl" size="21" placeholder="请填写网址"  />
              </li>
              <li>
                <button class="button bg-main" id="index_search" type="submit">搜索</button>
              </li>
            </ul>
            
            <ul class="userMenu" id="index_menu" style="max-height: 230px;overflow: auto;">
            </ul>
            
            
          </div>
        </div>
        <!--指标选择 end-->
        
      </div>
      <!-- 中部内容区域 end -->
      
      
      <!-- 右内容区域 begin -->
      <div id="right_content_area" class="rightMain mg_l270">
        <!--公告报表区 开始-->
        <div class="statistic-panel">
          <div class="statistic-conHead">
            <ul class="flr statistic-title_list">
<!--               <li><a href="#" class="title_tab_on" onclick="tab_change(1,this)">报表自定义</a></li> -->
<!--               <li>|</li> -->
<!--               <li><a href="#" onclick="tab_change(2,this)">报表</a></li> -->
<!--               <li>|</li> -->
<!--               <li><a href="#" onclick="tab_change(3,this)">图形</a></li> -->
            </ul>
            <div class="w_150"><span class="flr fast_tag"><img src="${imagePath}/fast_left.png" /></span> <span id="panel_title">报表自定义</span></div>
          </div>
          
          
         <!-- 报表自定义部分 begin -->
         <div id="self_define_area" class="conMain mg_t18">
         	<%@include file='./main_frame_rep_tree.jsp'%>
         </div>
         <!-- 报表自定义部分 end -->
          
          
         <!-- 报表 begin -->
         <div id="report_list_area" class="conMain mg_t18 hide-model" style="overflow-x:auto;overflow-y:no;height:auto;">
         	<%@include file='./main_frame_rep_list.jsp'%>
         </div>
         <!-- 报表 end -->
          
         <!-- 图形 begin -->
         <div id="chart_list_area" class="conMain mg_t18 hide-model" >
         	<%@include file='./main_frame_rep_graph.jsp'%>
         </div>
         <!-- 图形 end -->
          
        </div>
        <!--公告报表区 结束-->
      </div>
	  <!-- 右内容区域 end -->
		
    </div>
    <!--右侧结束-->
</body>
</html>
 