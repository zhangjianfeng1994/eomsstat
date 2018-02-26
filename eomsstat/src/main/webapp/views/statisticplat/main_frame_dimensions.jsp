<%@include file="../common/header.jsp"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="${cssPath}/maskLayer.css">
<script>


		$(document).ready(function() {
			var columnOptions={"paramName":"维度名称","paramEng":"维度字段","groupType":"维度归属","parentParamId":"维度父id"};
			var url="${pageContext.request.contextPath}/tawStatisticParam/paramCollection.json";
			
			var loadPageComponent_temp=new loadPageComponent(url,1,columnOptions,"pageComponent","listTable","checkall","checkreverse","searchBtn","seachBtnValue","",function(result,eoms_refresh){
			var a= new dialogComponent("维度删除","<div class='txt-fld'><h3><b>确认删除当前记录？<b></h3></div>","paramAddModal",null,"paramAddModelDiv");
			a.confirm_dialog(function(data){
				//删除
				if(data==true) {
					$.ajax({
						url : '${pageContext.request.contextPath}/tawStatisticParam/paramRemove.json',
						type : 'post',
						cache : false,
						async : true,
						data:{'param_id':result},
						success : function(data) {
							eoms_refresh.initData();
							}
					});
				  }
				});
			},function(result,eoms_refresh){
				this.row='';
				//编辑
				$.ajax({
					url : '${pageContext.request.contextPath}/tawStatisticParam/getParamById.json',
					type : 'post',
					cache : false,
					async : false,
					data:{'param_id':result},
					success : function(data) {
					   var s=data.retJson.row;
						var content="<form method= 'post' id='saveorupdate_edit'><div style='height:auto; margin:0 auto;width:auto'>"+
						"<div class='hidden-area'><label>ID:</label><input type='text' name ='submitType' value='eidt' readonly = 'true' /></div>"+
						"<div class='hidden-area'><label>ID:</label><input type='text' name ='id' value='"+result+"' readonly = 'true' /></div>"+
						"<div class='txt-fld'><label>维度名称:</label><input type='text' name ='paramName' value='"+data.retJson.row.paramName+"'/></div>"+
						"<div class='txt-fld'><label>维度列名称:</label><input type='text' name ='paramEng' value='"+data.retJson.row.paramEng+"'/></div>"+
						"<div class='txt-fld'><label>报表归属:</label><select id='groupType_edit' name='groupType_edit'></select></div>"+
						"<div class='txt-fld'><label>维度有效性:</label><select id='delete_edit' name='delete_edit' value='"+data.retJson.row.deleteStatus+"'><option value='1'>有效</option><option value='0'>无效</option></select></div>"+
						"<div class='txt-fld'><label>编码类型:</label><select id='codeType_edit' name='codeType_edit'><option value='areaType'>地域类型</option><option value='deptType'>部门类型</option><option value='roleType'>角色类型</option><option value='personType'>人员类型</option><option value='dictType'>字典类型</option><option value='noneType'>非编码类型</option></select></div>"+
						"<div class='txt-fld'><label>系统维度类型:</label><select id='eomsParamType_edit' name='eomsParamType_edit' class='selectpicker'><option class='open_window-select-font' value='none_system'>非EOMS维度</option></select></div>"+
						"<div class='txt-fld'><label>维度父ID:</label><input type='text' name='parentParamId' value='"+data.retJson.row.parentParamId+"'/></div>"+
						"</div></form>";
					//编辑
					 var a= new dialogComponent("维度编辑",content,"paramAddModal",null,"paramAddModelDiv");
					 a.confirm_dialog(function(data){
							 if(data==true){
								$.ajax({
					                cache: true,
					                type: "POST",
					                url:'${pageContext.request.contextPath}/tawStatisticParam/paramAdd.json',
					                data:$('#saveorupdate_edit').serialize(),//formid
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
					 $("#codeType_edit").val(data.retJson.row.paramCodeType);
					 $("#delete_edit").val(data.retJson.row.deleteStatus);
					 $("#eomsParamType_edit").val(data.retJson.row.eomsParamType);
					 $("#codeType_edit").selectpicker();
					 $("#delete_edit").selectpicker();
					 $("#eomsParamType_edit").selectpicker();
					 //加载报表归属选择框
					 init_table_belong("groupType_edit",'${pageContext.request.contextPath}',function(){$("#groupType_edit").val(data.retJson.row.groupType);$("#groupType_edit").selectpicker();});
					}
				
				});
			});
			
			loadPageComponent_temp.initData();
			//加载新增对话框
			var divStr="<form method= 'post' id='saveorupdate'><div style='height:auto; margin:0 auto;width:auto'>"+
			"<div class='hidden-area'><label>ID:</label><input type='text' name ='submitType' value='add' readonly = 'true' /></div>"+
			"<div class='txt-fld'><label>维度名称:</label><input type='text' name ='paramName'/></div>"+
			"<div class='txt-fld'><label>维度列名称:</label><input type='text' id='paramEng' name='paramEng'/></div>"+
			"<div class='txt-fld'><label>报表归属:</label><select id='groupType' name='groupType'></select></div>"+
			"<div class='txt-fld'><label>编码类型:</label><select id='codeType' name='codeType' class='selectpicker'><option class='open_window-select-font' value='areaType'>地域类型</option><option class='open_window-select-font' value='deptType'>部门类型</option><option class='open_window-select-font' value='roleType'>角色类型</option><option class='open_window-select-font' value='personType'>人员类型</option><option class='open_window-select-font'  value='dictType'>字典类型</option><option class='open_window-select-font' value='noneType'>非编码类型</option></select></div>"+
			"<div class='txt-fld'><label>系统维度类型:</label><select id='eomsParamType' name='eomsParamType' class='selectpicker'><option class='open_window-select-font' value='none_system'>非EOMS维度</option></select></div>"+//<option class='open_window-select-font' value='dept_system'>部门类型</option>
			"<div class='txt-fld'><label>维度父ID:</label><input type='text' id ='parentParamId' name='parentParamId'/></div>"+
			"</div></form>";
			new dialogComponent("维度新增",divStr,"paramAddModal","open_param_window","paramAddModelDiv_add").self_dialog(function(){
				//初始化报表归属
				init_table_belong("groupType",'${pageContext.request.contextPath}',function(){$("#groupType").selectpicker();$("#codeType").selectpicker();$("#eomsParamType").selectpicker();});
			},function(data){
				if(data==true){
					$.ajax({
		                cache: true,
		                type: "POST",
		                url:'${pageContext.request.contextPath}/tawStatisticParam/paramAdd.json',
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
			$("#open_param_window").hide();
			$("#lean_overlay").fadeOut(200);
		});
		
	</script>
</head>
<body class="statistic_body">
	<!-- 弹出框开始 -->
	<!-- 指标新增begin -->
	<div id="paramAddModelDiv"></div>
	<div id="paramAddModelDiv_add"></div>
	<!-- 指标新增end -->
	<!-- 弹出框结束 -->
    <div class="statistic-div">
      <!--维度管理列表 开始-->
        <div class="statistic-panel">
          <div class="statistic-conHead">
            <ul class="flr statistic-title_list">
<!-- 				<input name="" type="button" class="btn_return" onclick="reback(0,'main_frame_reportforms.jsp');"/> -->
            </ul>
            <div class="w_120">维度管理列表</div>
          </div>
          <div class="conMain mg_t18">
            <ul class="btn_list ">
              <li>
                <button class="button bg-main" name="checkall" id="checkall"  checkfor="id" >全选</button>
              </li>
              <li>
                <button class="button bg-sub" id="checkreverse"  type="submit">反选</button>
              </li>
              <li>
                <input type="text" class="input statistic-search_input" id="seachBtnValue" name="siteurl" size="20" placeholder="根据维度名称查询"  />
              </li>
              <li>
                <button class="button bg-main"  id="searchBtn" >搜索</button>
              </li>
              <li>
                <a href="#open_param_window" rel="paramAddModal"  class="button bg-sub">新增维度</a>
              </li>
              
            </ul>
            			<div id="listTable">
			</div>

			<div  class="flr mg_t10"><!-- <img src="${imagePath}/page_pic.png" /> -->
				<ul id="pageComponent"></ul>
			</div>

          </div>
        </div>
        <!--维度管理列表 结束-->
    </div>
    <!--右侧结束-->
</body>
</html>
