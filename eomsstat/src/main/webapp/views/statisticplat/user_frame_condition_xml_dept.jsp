<%@include file="../common/header.jsp"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<script  src="${pageContext.request.contextPath}/resources/js/jquery.ztree.core-3.5.js"></script>
<script  src="${pageContext.request.contextPath}/resources/js/jquery.ztree.excheck-3.5.js"></script>
<script  src="${pageContext.request.contextPath}/resources/js/jquery.ztree.exedit-3.5.js"></script>

<%
String modelid=request.getParameter("modelid");
request.setAttribute("modelid", modelid);
 %>
<html>
<head>
<meta charset="UTf-8">
<title>统计平台</title>
<link rel="stylesheet" type="text/css" href="${cssPath}/maskLayer.css">
<script type="text/javascript">
			$(document).ready(function(){
					//
				laydate.skin('yalan');
				$("#query_begin_time").bind("click",function(){
					laydate({elem: '#query_begin_time',istime: true, format: 'YYYY-MM-DD'});
				});
				
				$("#query_end_time").bind("click",function(){
					laydate({elem: '#query_end_time',istime: true, format: 'YYYY-MM-DD'});
				});
				
				//加载所有的指标
				var modelid='${modelid}';
				
				$.ajax({
					url : '${pageContext.request.contextPath}/tawStatisticModel/getModelIndicateById.json',
					type : 'post',
					cache : false,
					async : true,
					data:{'modelid':modelid},
					success : function(data) {
					       var indexs=data.retJson.indexs;
					       var root=data.retJson.rootDemensions;
					       var str="";
					       for(var i=0;i<indexs.length;i++){
					         str=str+" <input type='checkbox' class='index_chooose_all' value='"+indexs[i].id+"'/> "+indexs[i].indicateName;
					       }
					       $("#index_choose").html(str);
					       $("#dept_root").val(root);
					       
					       
						}
				});
				
					
			});
		
			function deptConfirm(){
				var zTree;
			    var setting = {  
			   		view: {
			 			showLine: false
			 		},
			 		check: {
        				enable: true,
        				chkboxType: { "Y": "s", "N": "ps" }
    				},
			 		data: {
			 			simpleData: {
			 				enable: true,
			 				idKey: "id",
			 				pIdKey: "pid"
			 			}
			 		},
			         async: {
			             enable: true,
			             url:"${pageContext.request.contextPath}/tawStatisticModel/index_sub_tree.json?defaultRoot="+$("#dept_root").val(),
			             autoParam:["id=pid"]
			         },
			         callback:{
			             beforeClick:function(treeId,treeNode){
			                 if(treeNode.isParent){
			                     zTree.expandNode(treeNode);
			                     return true;
			                 }else{
			                 	//$('#iframe_show_s').attr("src","${pageContext.request.contextPath}/show/index_show/"+treeNode.id);
			                 }
			                 return false;
			             }
			         }
			    };
			    
			    
			    //加载部门
				var a= new dialogComponent("部门查询","<ul id='tree' class='ztree'></ul>","indexAddModal",null,"indexAddModelDiv");
				a.confirm_dialog(function(data){
					if(data==true){
				           getAllCheck();
					}
				});
				$.fn.zTree.init($("#tree"), setting);
			    
			}
			
			
			function getAllCheck(){
 				var treeObj=$.fn.zTree.getZTreeObj("tree");
 		        nodes=treeObj.getCheckedNodes(true);
		        node_names="";
		        node_ids="";
 		        for(var i=0;i<nodes.length;i++){
 		        	if(node_names.length==0){
 		        		node_names=nodes[i].name;
 		          		node_ids=nodes[i].id;
 		        	}else{
 		        		node_names=node_names+","+nodes[i].name;
 		          		node_ids=node_ids+","+nodes[i].id;
 		        	}
 		        }
 		       // alert(node_names+"-"+node_ids);
 		       $("#deptNames").html(node_names);
 		       $("#deptIds").val(node_ids);
			}
			
			function sumitChooseCondition(){
				var flag=true;
			    var text="";
			    var begintime=$("#query_begin_time").val();
			    var endtime=$("#query_end_time").val();
			    var deptids=$("#deptIds").val();
			   
			    var countNum=0;
			    var checkItems="";
			    
			    $(".index_chooose_all").each(function(index,checkObj){
					if ($(checkObj).is(":checked")) {  
						countNum=countNum+1;
						if(checkItems.length==0){
							checkItems=$(checkObj).val();
						}else{
							checkItems=checkItems+","+$(checkObj).val();
						}
					}
				});
			    
			    
			    if(""==begintime){
			    	flag=false;
			    	text+="请选择开始时间;";
			    } 
			    if(""==endtime){
			    	flag=false;
			    	text+="请选择结束时间;";
			    }
			    if(""==deptids){
			    	flag=false;
			    	text+="请选择部门;";
			    }
			    if(0==countNum){
			    	flag=false;
			    	text+="请选择指标;";
			    }
			    if(flag){
			    	location.href = "${pageContext.request.contextPath}/views/statisticplat/user_frame_reportform_xml.jsp?modelid=${modelid}&begintime="+begintime+"&endtime="+endtime+"&deptids="+deptids+"&checkItems="+checkItems+"&type=repType";
			    }else{
			    	 //加载数据选中校验框
			    	var validateDialog= new dialogComponent("数据校验",text,"confirmAddModal",null,"confirmAddModelDiv");
					validateDialog.confirm_dialog(function(data){
					if(data==true){}});
			    }
			
			}
			
			
		</script>

</head>
<body style="overflow-y:scroll;">
<div id="indexAddModelDiv"></div>
<div id="indexAddModelDiv_add"></div>
<a href="#open_index_window" rel="indexAddModal"></a>
<div id="confirmAddModelDiv"></div>
<div id="confirmAddModelDiv_add"></div>
<a href="#open_confirm_window" rel="confirmAddModal"></a>

<input type="hidden" id="dept_root" name="dept_root"/>


	<div id="mainWrap">
		<div id="Header">
		    <div class="flr mg_r10 mg_t30" ></div>
		</div>

		<div id="mainContent_user" class="fixed">
			<div id="day_report" class="conMain mg_t18" style="height:auto;">
			
				<table class="result-tab">
					<tr class="tableroweven"><td align="center" width="150px"><b>时间选择</b></td><td><a><b>开始时间：</b></a><input type="text"
		class="input statistic-search_input" id="query_begin_time"
		name="query_begin_time" size="20" placeholder="请输入日报时间" /> <a><b>结束时间：</b></a><input
		type="text" class="input statistic-search_input" id="query_end_time"
		name="query_end_time" size="20" placeholder="请输入日报时间"/></td></tr>
					<tr class="tablerowodd"><td align="center"><b>部门选择</b></td><td><a onclick="deptConfirm()"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a> | <span class="contend" id="deptNames"></span><input type="hidden" id="deptIds"/></td></tr>
					<tr class="tableroweven"><td align="center"><b>指标选择</b></td><td><span id="index_choose"></span></td></tr>
					<tr class="tablerowodd"><td colspan="2" align="right"><button class="button bg-sub" onclick="sumitChooseCondition()">确 定</button></td></tr>
				</table>
			
			
			</div>
		</div>
	</div>
</body>
</html>