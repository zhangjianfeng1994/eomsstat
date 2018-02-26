<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript">
	
	$(document).ready(function(){
	   $("#table_belong").val()		
	});
	
	//保存模板
	function saveModel(){
		var a= new dialogComponent("保存模板","<div class='txt-fld'><label>模板名称:</label><input type='text' name ='modelName' id='modelName' value=''/></div><div class='txt-fld_left'><label>算法规则:</label><textarea class='form-control' rows='4' name ='computeRule' id='computeRule'></textarea></div>","indexAddModal",null,"indexAddModelDiv");
		a.confirm_dialog(function(data){
			if(data==true) {
				//数据抽离，并保存入库
				var retJson=getAllNodelJson();
				//封装模板树
				var table_value=$("#table_belong").val();
		        $.ajax({
						url : '${pageContext.request.contextPath}/tawStatisticModel/saveModel.json',
						type : 'post',
						cache : false,
						async : true,
						data:{'modelItemStr':JSON.stringify(getModelItem(retJson,"-1").children),'modelCycle':'day','modelIsCommonFlag':'1','modelIsShareFlag':'1','modelName':$("#modelName").val(),'modelGroupType':table_value,'detailId':$("#detail_selector").val(),'computeRule':$("#computeRule").val(),'userid':'admin'},
						success : function(data) {
							alert("保存成功");
							$("#list_model_id").val(data.retJson.modelId);
						}
					});
				 }
		});
	}
	
	//获取所以的json串
	function getAllNodelJson(){
		var root=$("#tree_central").treeview("getAllNodes");
		return root[0];
	}
	
	function getModelItem(node,parentid){
		var item=new Object();
		var children=[];	
		
		if(node.nodes!=null){
			for(var i=0;i<node.nodes.length;i++){
				children.push(getModelItem(node.nodes[i],node.id));
			}	
		}
		item.id=node.id;
		item.pId=parentid;
		item.name=node.text;
		item.children=children;
		return item;
	}
	
	var setting_tree =[{id:"model_tree_root",text: "统计模板树",value:"1"}];
	
	function deleteModel(){
 		$("#tree_central").treeview({levels: 1,data: setting_tree});
	}
	
	
	function inin_detailList(groupType){
		   $.ajax({
				url : '${pageContext.request.contextPath}/tawStatisticDetail/getTawStatisticDetailByGroupType.json',
				type : 'post',
				cache : false,
				async : true,
				data:{'groupType':groupType},
				success : function(data) {
				        var retList=data.retJson.tawStatisticDetailList;
				        $("#detail_selector").empty();
				        for(var i=0;i<retList.length;i++){
				        	$("#detail_selector").append("<option value='"+retList[i].id+"'>"+retList[i].detailNumName+"</option>");
				        }
						$("#detail_selector").selectpicker('refresh');
					}
				});
	}
			

</script>
<!-- 指标新增begin -->
<div id="indexAddModelDiv"></div>
<div id="indexAddModelDiv_add"></div>
<a href="#open_index_window" rel="indexAddModal"></a>
<!-- 指标新增end -->

<ul class="btn_list ">
	<li><button  class="button bg-main" onclick="saveModel()">保存模板</button></li>
<!-- 	<li><button class="button bg-sub" onclick="deleteModel()">删除模板222</button></li> -->
	<li><span class="span_color_font"><b>详单选择：</b></span><select id="detail_selector" class="selectpicker"></select></li>
</ul>
<div class="col-md-12">
	<div id="tree_central" style="height:auto;">
	
	</div>
</div>
