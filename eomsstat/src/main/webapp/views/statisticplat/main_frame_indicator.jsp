<%@include file="../common/header.jsp"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<html>
<head>


<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="${cssPath}/maskLayer.css">
<script>
		$(document).ready(function() {
			var columnOptions={"indicateName":"指标名称","indicateValue":"指标值","indicateDescribe":"算法描述","indicateOverValue":"指标阀值","groupType":"报表归属"};
         	var url="${pageContext.request.contextPath}/tawStatisticIndex/indexCollection.json";
			//加载列表
			var loadPageComponent_temp=new loadPageComponent(url,1,columnOptions,"pageComponent","listTable","checkall","checkreverse","searchBtn","seachBtnValue","",function(result,eoms_refresh){
				//删除
				var a= new dialogComponent("指标删除","<div class='txt-fld'><h3><b>确认删除当前记录？<b></h3></div>","indexAddModal",null,"indexAddModelDiv");
				a.confirm_dialog(function(data){
					//删除
					if(data==true){
						$.ajax({
						url : '${pageContext.request.contextPath}/tawStatisticIndex/indexRemove.json',
						type : 'post',
						cache : false,
						async : true,
						data:{'indicate_id':result},
						success : function(data) {
							eoms_refresh.initData();
								}
						});
					}
				});
			},function(result,eoms_refresh){
				//编辑
				$.ajax({
				url : '${pageContext.request.contextPath}/tawStatisticIndex/getIndexById.json',
				type : 'post',
				cache : false,
				async : false,
				data:{'indicate_id':result},
				success : function(data) {
					//alert(JSON.stringify(data));
					var indicateName=data.retJson.indicateName;
					var indicateValue=data.retJson.indicateValue;
					var indicateDescribe = data.retJson.indicateDescribe;
					var complexFlag=data.retJson.complexFlag;
					var condition=data.retJson.condition;
					var groupType=data.retJson.groupType;
					var detailId=data.retJson.detailId;
					//alert(detailId);
					var percentSign=data.retJson.percentSign;
					var indicateOverValue=data.retJson.indicateOverValue;
					var indicatorFir=data.retJson.indicatorFir_add;
					var indicatorEnd=data.retJson.indicatorEnd_add;
					var operateFlag=data.retJson.operateFlag;
					var deleteStatus=data.retJson.deleteStatus;
					var isGrasp=data.retJson.isGrasp;
					//alert(condition);
					
					var content="<form method= 'post' id='saveorupdate' action= '${pageContext.request.contextPath}/tawStatisticIndex/indexAdd.json'><div style='height:auto; margin:0 auto;'>"+
					"<div class='hidden-area'><label>type:</label><input type='text' name ='submitType' value='edit' readonly = 'true' /></div>"+
					"<div class='hidden-area'><label>ID:</label><input type='text' class='form-control input_inline' name ='id' value='"+result+"' readonly = 'true' /></div>"+
					"<div class='txt-fld'><label>指标名称:</label><input type='text' class='form-control input_inline' name ='indicateName' value='"+indicateName+"'/></div>"+
					"<div class='txt-fld'><label>指标算法:</label><input type='text' class='form-control input_inline' name ='indicateValue' value='"+indicateValue+"'/></div>"+
					"<div class='txt-fld'><label>算法描述:</label><input type='text' class='form-control input_inline' name ='indicateDescribe' value='"+indicateDescribe+"'/></div>"+
					"<div class='txt-fld'><label>指标阀值:</label><input type='text' class='form-control input_inline' name='indicateOverValue' value='"+indicateOverValue+"'/></div>"+
					"<div class='txt-fld'><label>指标条件:</label><input type='text' class='form-control input_inline' name='condition_edit' id='condition_edit'  /></div>"+
					"<div class='txt-fld'><label>报表归属:</label><select id='groupType_edit' name='groupType_edit' onchange=groupTypeChoose('edit')></select></div>"+
					"<div class='txt-fld'><label>详单选择:</label><select id='detail_selector_edit' name='detailId_edit'></select></div>"+
					"<div class='txt-fld'><label>指标有效性:</label><select id='delete_edit' name='delete_edit'><option value='1'>有效</option><option value='0'>无效</option></select></div>"+
					"<div class='txt-fld'><label>指标是否攥取:</label><select id='isGrasp_edit' name='isGrasp_edit'><option value='1'>是</option><option value='0'>否</option></select></div>"+
					"<div class='txt-fld'><label>百分比符号:</label><select id='percentSign_edit' name='percentSign_edit'><option value='1'>是</option><option value='0'>否</option></select></div>"+
					"<div class='txt-fld'><label>复合标识符:</label><select id='complexFlag_edit' name='complexFlag_edit'  onchange=complexFlagChoose(this.options[this.options.selectedIndex].value,'edit')><option  value='0'>否</option><option value='1'>是</option></select></div>"+
					"<div class='txt-fld' id='edit_complex_area' style='display:none'>"+
					"<div class='txt-fld'><label>指标一:</label><select id='indicatorFir_edit' name='indicatorFir_edit'></select></div>"+
					"<div class='txt-fld'><label>操作符号:</label><select id='operateFlag_edit' name='operateFlag_edit'><option value='+'>加（+）</option><option value='-'>减（-）</option><option value='*'>乘（*）</option><option value='/'>除（/）</option></select></div>"+
					"<div class='txt-fld'><label>指标二:</label><select id='indicatorEnd_edit' name='indicatorEnd_edit'></select></div>"+
					"</div>"+
					"</div></form>";
					 var a= new dialogComponent("指标编辑",content,"indexAddModal",null,"indexAddModelDiv");
					 a.confirm_dialog(function(data){ 
						 if(data==true){
							 $.ajax({
							       cache: true,
							       type: "POST",
							       url:'${pageContext.request.contextPath}/tawStatisticIndex/indexAdd.json',
							       data:$('#saveorupdate').serialize(),//formid
							       async: false,
							       error: function(request) {
							          alert("Connection error");
							       },
							       success: function(data) {
							          eoms_refresh.initData();
							       }
							  });
							 	
						 } 
					 });
					
					  //加载编码类型选择框
					 init_table_belong("groupType_edit",'${pageContext.request.contextPath}',function(){$("#groupType_edit").val(groupType);$("#groupType_edit").selectpicker();ajax_indicator_code("edit",indicatorFir,indicatorEnd);});
					 inin_detailList($("#groupType_edit").val(),"edit");
					 $("#detail_selector_edit").val(detailId);
					 //alert($("#detail_selector_edit").html());
					 //$("#detail_selector_edit option[value="+detailId+"]").attr("selected", "selected");
					 $("#condition_edit").val(condition);
					 $("#complexFlag_edit").val(complexFlag);
					 $("#percentSign_edit").val(percentSign);
					 $("#delete_edit").val(deleteStatus);
					 $("#isGrasp_edit").val(isGrasp);
					
					 
					 //$("#detail_selector_edit").selectpicker();
					 $("#complexFlag_edit").selectpicker();
					 $("#percentSign_edit").selectpicker();
					 $("#delete_edit").selectpicker();
					 $("#isGrasp_edit").selectpicker();
					  if("1"==complexFlag){
					 	 $("#edit_complex_area").show();
					 	 $("#operateFlag_edit").val(operateFlag);
					 	 $("#operateFlag_edit").selectpicker();
					 	// alert("sss");
					 		$("#confirm_href_id").css("position","relative");
						$("#confirm_href_id").css("top","-150px");
					 	 
					 }else{
					 	 $("#operateFlag_edit").selectpicker();
					 	 groupTypeChoose("edit");
					 	 $("#detail_selector_edit").val(detailId);
					 	//$("#detail_selector_edit option[value="+detailId+"]").attr("selected", "selected");
					 	$("#detail_selector_edit").selectpicker('refresh');
					 }
					 
					 
				 }
			 });

			});
			loadPageComponent_temp.initData();
			
			//加载新增对话框
			var divStr="<form method= 'post' id='saveorupdate'><div style='height:auto; margin:0 auto;width:auto'>"+
			"<div class='hidden-area'><label>type:</label><input type='text' name ='submitType' value='add' readonly = 'true' /></div>"+
			"<div class='txt-fld'><label>指标名称:</label><input type='text' class='form-control input_inline' id='indicateName' name ='indicateName'/></div>"+
			"<div class='txt-fld'><label>指标算法:</label><input type='text' class='form-control input_inline' id='indicateValue' name ='indicateValue'/></div>"+
			"<div class='txt-fld'><label>算法描述:</label><input type='text' class='form-control input_inline' id='indicateDescribe' name ='indicateDescribe'/></div>"+
			"<div class='txt-fld'><label>指标阀值:</label><input type='text' class='form-control input_inline' id ='indicateOverValue' name='indicateOverValue'/></div>"+
			"<div class='txt-fld'><label>指标条件:</label><input type='text' class='form-control input_inline' id ='condition' name='condition'/></div>"+
			"<div class='txt-fld'><label>报表归属:</label><select id='groupType_add' name='groupType_add' onchange=groupTypeChoose('add')></select></div>"+
			
			"<div class='txt-fld'><label>详单选择:</label><select id='detail_selector_add' name='detailId_add'></select></div>"+
			
			"<div class='txt-fld'><label>指标是否攥取:</label><select id='isGrasp' name='isGrasp'><option value='1'>是</option><option value='0'>否</option></select></div>"+
			
			"<div class='txt-fld'><label>百分比符号:</label><select id='percentSign' name='percentSign' class='selectpicker'><option class='open_window-select-font' value='1'>是</option><option class='open_window-select-font' value='0'>否</option></select></div>"+
			
			"<div class='txt-fld'><label>复合标识符:</label><select id='complexFlag' name='complexFlag' class='selectpicker' onchange=complexFlagChoose(this.options[this.options.selectedIndex].value,'add')><option class='open_window-select-font' value='0'>否</option><option class='open_window-select-font' value='1'>是</option></select></div>"+
			"<div class='txt-fld' id='add_complex_area' style='display:none'>"+
			"<div class='txt-fld'><label>指标一:</label><select id='indicatorFir_add' name='indicatorFir_add'></select></div>"+
			"<div class='txt-fld'><label>操作符号:</label><select id='operateFlag' name='operateFlag' class='selectpicker'><option class='open_window-select-font' value='+'>加（+）</option><option class='open_window-select-font' value='-'>减（-）</option><option class='open_window-select-font' value='*'>乘（*）</option><option class='open_window-select-font' value='/'>除（/）</option></select></div>"+
			"<div class='txt-fld'><label>指标二:</label><select id='indicatorEnd_add' name='indicatorEnd_add'></select></div>"+
			"</div>"+
			"</div></form>";
			
			new dialogComponent("指标新增",divStr,"indexAddModal","open_index_window","indexAddModelDiv_add").self_dialog(function(){
				//初始化报表归属
				init_table_belong("groupType_add",'${pageContext.request.contextPath}',function(){$("#groupType_add").selectpicker();});
				inin_detailList($("#groupType_add").val(),"add");
			},function(data){
				if(data==true){
					$.ajax({
					       cache: true,
					       type: "POST",
					       url:'${pageContext.request.contextPath}/tawStatisticIndex/indexAdd.json',
					       data:$('#saveorupdate').serialize(),//formid
					       async: false,
					       error: function(request) {
					          alert("Connection error");
					       },
					       success: function(data) {
					           loadPageComponent_temp.initData();
					       }
					  });
				}
			});
		$("#open_index_window").hide();
		$("#lean_overlay").fadeOut(200);
		init_groupType("groupType_add");
		$("#percentSign").selectpicker();
		$("#complexFlag").selectpicker();
		$("#operateFlag").selectpicker();
		$("#isGrasp").selectpicker();
		//inin_detailList($("#groupType_add").val());
		$("#detail_selector_add").selectpicker();
		});
		
		function inin_detailList(groupType,subType){
			//alert(groupType+"---------"+subType);
			   $.ajax({
					url : '${pageContext.request.contextPath}/tawStatisticDetail/getTawStatisticDetailByGroupType.json',
					type : 'post',
					cache : false,
					async : false,
					data:{'groupType':groupType},
					success : function(data) {
					        var retList=data.retJson.tawStatisticDetailList;
					        $("#detail_selector_"+subType).empty();
					        for(var i=0;i<retList.length;i++){
					        	$("#detail_selector_"+subType).append("<option value='"+retList[i].id+"'>"+retList[i].detailNumName+"</option>");
					        }
					        if($("#detail_selector_"+subType).html()==""){
					        	$("#detail_selector_"+subType).append('<option value="">没有选中的选项</option>');
					        }
							$("#detail_selector_"+subType).selectpicker('refresh');
						}
					});
		}
		
		
		function complexFlagChoose(select_option_value,type){
			//alert(select_option_value);
			if("0"==select_option_value){
				$("#"+type+"_complex_area").hide();
			}else{
				$("#"+type+"_complex_area").show();
				
				//加载复合指标选项
				ajax_indicator_code(type,"","");
				$("#confirm_href_id").css("position","relative");
			    $("#confirm_href_id").css("top","-150px");
			}
		}
		
		function ajax_indicator_code(type,indicatorFir,indicatorEnd){
			$.ajax({
					url : '${pageContext.request.contextPath}/tawStatisticIndex/getIndexByTableName.json',
					type : 'post',
					cache : false,
					data:{'table_Name':$("#groupType_"+type).val()},
					async : true,
					success : function(data) {
						$("#indicatorFir_"+type).empty();
						$("#indicatorEnd_"+type).empty();
						for(var i=0;i<data.retJson.indexs.length;i++){
							$("#indicatorFir_"+type).append("<option value='"+data.retJson.indexs[i].id+"'>"+data.retJson.indexs[i].indicateName+"</option>");
							$("#indicatorEnd_"+type).append("<option value='"+data.retJson.indexs[i].id+"'>"+data.retJson.indexs[i].indicateName+"</option>");
						}
						//alert(indicatorFir);
						if(""!=indicatorFir){
							$("#indicatorFir_"+type).val(indicatorFir);
						}
						if(""!=indicatorEnd){
							$("#indicatorEnd_"+type).val(indicatorEnd);
						}
						$("#indicatorFir_"+type).selectpicker('refresh');
						$("#indicatorEnd_"+type).selectpicker('refresh');
					 }
			 });
		
		}
		
		
		function groupTypeChoose(type){
			//alert($("#groupType_"+type).val());
			inin_detailList($("#groupType_"+type).val(),type);
			$.ajax({
					url : '${pageContext.request.contextPath}/tawStatisticIndex/getIndexByTableName.json',
					type : 'post',
					cache : false,
					data:{'table_Name':$("#groupType_"+type).val()},
					async : true,
					success : function(data) {
						
						$("#indicatorFir_"+type).empty();
						$("#indicatorEnd_"+type).empty();
						for(var i=0;i<data.retJson.indexs.length;i++){
							$("#indicatorFir_"+type).append("<option value='"+data.retJson.indexs[i].id+"'>"+data.retJson.indexs[i].indicateName+"</option>");
							$("#indicatorEnd_"+type).append("<option value='"+data.retJson.indexs[i].id+"'>"+data.retJson.indexs[i].indicateName+"</option>");
						}
						
						$("#indicatorFir_"+type).selectpicker('refresh');
						$("#indicatorEnd_"+type).selectpicker('refresh');
					 }
			 });
		}
			
	</script>


</head>
<body class="statistic_body">
	

    <div class="statistic-div">
      <!--维度管理列表 开始-->
        <div class="statistic-panel">
          <div class="statistic-conHead">
            <ul class="flr statistic-title_list">
<!-- 				<input name="" type="button" class="btn_return" onclick="reback(0,'main_frame_reportforms.jsp');"/> -->
            </ul>
            <div class="w_120">指标管理列表</div>
          </div>
          <div class="conMain mg_t18">
            <ul class="btn_list ">
              <li>
                <button class="button bg-main" name="checkall" id="checkall" checkfor="id" >全选</button>
              </li>
              <li>
                <button class="button bg-sub" id="checkreverse" type="submit">反选</button>
              </li>
              <li>
                <input type="text" id="seachBtnValue" class="input statistic-search_input" id="siteurl" name="siteurl" size="20" placeholder="根据指标名称查询"  />
              </li>
              <li>
                <button class="button bg-main"  id="searchBtn">搜索</button>
              </li>
              <li>
                <a href="#open_index_window" rel="indexAddModal"  class="button bg-sub">新增指标</a>
              </li>
            </ul>
			
			<div id="listTable">
			</div>

 			<div  class="flr mg_t10"><!--<img src="${imagePath}/page_pic.png" /> -->
				<ul id="pageComponent"></ul>
			</div>
			<!--分页end-->
			
          </div>
        </div>
        <!--维度管理列表 结束-->
		
		
		
		
    </div>
    <!--右侧结束-->
    
    
    
    <!-- 弹出框开始 -->
	<!-- 指标新增begin -->
	<div id="indexAddModelDiv"></div>
	<div id="indexAddModelDiv_add"></div>
	<!-- 指标新增end -->
	<!-- 弹出框结束 -->
</body>
</html>
