<%@include file="../common/header.jsp"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="${cssPath}/maskLayer.css">
<script>
		$(document).ready(function() {
			//加载列表
			var columnOptions={"modelName":"模板名称","cycle":"模板粒度","isShareFlag":"模板共享","isCommonFlag":"公共模板","groupType":"报表归属"};
			var url="${pageContext.request.contextPath}/tawStatisticModel/modelCollection.json";
			var loadPageComponent_temp=new loadPageComponent(url,1,columnOptions,"pageComponent","listTable","checkall","checkreverse","searchBtn","seachBtnValue","",function(result,eoms_refresh){
				//删除
				var a= new dialogComponent("模板删除","<div class='txt-fld'><h3><b>确认删除当前记录？<b></h3></div>","modelAddModal",null,"modelAddModalDiv");
				a.confirm_dialog(function(data){
					//删除
					if(data==true)
					$.ajax({
					url : '${pageContext.request.contextPath}/tawStatisticModel/modelRemove.json',
					type : 'post',
					cache : false,
					async : true,
					data:{'model_id':result},
					success : function(data) {
						eoms_refresh.initData();
							}
					});
					});
			},function(result,eoms_refresh){
				var row="";
				//编辑
				$.ajax({
					url : '${pageContext.request.contextPath}/tawStatisticModel/getModelById.json',
					type : 'post',
					cache : false,
					async : false,
					data:{'model_id':result},
					success : function(data) {
						row=data.retJson.tawStatisticModel;
						//alert(result);
						var content="<form method= 'post' id='saveorupdate'><div>"+
							"<div class='hidden-area'><label>ID:</label><input type='text' name ='modelId' value='"+result+"' readonly = 'true' /></div>"+
							"<div class='txt-fld'><label>模版名称:</label><input type='text' name ='modelName' value='"+row.modelName+"'/></div>"+
							"<div class='txt-fld'><label>模板粒度:</label><select id='modelCycle' name='modelCycle'><option value='day'>日报</option><option value='week'>周报</option><option value='month'>月报</option></select></div>"+
							"<div class='txt-fld'><label>模板共享:</label><select id='modelIsShareFlag' name='modelIsShareFlag'><option value='1'>是</option><option value='0'>否</option></select></div>"+
							"<div class='txt-fld'><label>公共模板:</label><select id='modelIsCommonFlag' name='modelIsCommonFlag'><option value='1'>是</option><option value='0'>否</option></select></div>"+
							"<div class='txt-fld'><a href='main_frame_model_edit.jsp?modelid="+result+"' target='_blank'><font color='gray' size='3px'>模板编辑</font></a></div>"+
							"</div></form>";
						// alert(content);
					//编辑
					 var a= new dialogComponent("模板编辑",content,"modelAddModal",null,"modelAddModalDiv");
					 a.confirm_dialog(function(data){
						 if(data==true){
							 $.ajax({
					                cache: true,
					                type: "POST",
					                url:'${pageContext.request.contextPath}/tawStatisticModel/editModel.json',
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
							
							//初始化化下拉框
					$("#modelCycle").val(row.cycle);
					$("#modelCycle").selectpicker();
					$("#modelIsShareFlag").val(row.isShareFlag);
					$("#modelIsShareFlag").selectpicker();
					$("#modelIsCommonFlag").val(row.isCommonFlag);
					$("#modelIsCommonFlag").selectpicker();
	                      }
				
				});
			}).initData();
			
			//加载新增对话框
            //var columnOptions={"modelId":"模板id","modelName":"模板名称","isShareFlag":"模板共享"};
           // var url="${pageContext.request.contextPath}/tawStatisticModel/modelCollection.json";
			/*
			var divStr="<form method= 'post' id='saveorupdate' action= '${pageContext.request.contextPath}/tawStatisticModel/saveModel.json'><div>模板名称:<input type='text' id='modelName' name ='modelName'/><br/>模板类型:<input type='text' id='cycle' name ='cycle'/><br/>模板共享:<input type='text' id='isShareFlag' name='isShareFlag'/><br/>公共模板:<input type='text' id='isCommonFlag' name='isCommonFlag'/><br/>报表归属:<input type='text' id ='groupType' name='groupType'/><br/></div></form>";
			new dialogComponent("维度新增",divStr,"modelAddModal","open_model_window","modelAddModalDiv_add").self_dialog(function(){},function(data){
				if(data==true){
						  $('#saveorupdate').validate({
						         rules: {
						        	 modelName: "required",
						        	 cycle:"required",
						        	 isShareFlag:"required",
						        	 isCommonFlag:"required",
						        	 groupType:"required"
						   }
						     });
					$('#saveorupdate').submit();
					new loadPageComponent(url,1,columnOptions,"pageComponent","listTable","checkall","checkreverse",function(){},function(){}).initData();
				}
			});
			*/
		});
		
		function modelDefine(){
		
			alert("hhh");
		}
		
		
	</script>
</head>
<body class="statistic_body"> 
	<!-- 弹出框开始 -->
	<!-- 指标新增begin -->
	<div id="modelAddModalDiv"></div>
	<div id="modelAddModalDiv_add"></div>
	<!-- 指标新增end -->
	<!-- 弹出框结束 -->
    <div class="statistic-div">
      <!--模板管理列表 开始-->
        <div class="statistic-panel">
          <div class="statistic-conHead">
            <ul class="flr statistic-title_list">
<!--                 <input name="" type="button" class="btn_return" onclick="reback(0,'main_frame_reportforms.jsp');"/> -->
            </ul>
            <div class="w_120">模板管理列表</div>
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
                <input type="text" class="input statistic-search_input" id="seachBtnValue" name="seachBtnValue" size="20" placeholder="请填写网址"  />
              </li>
              <li>
                <button class="button bg-main" id="searchBtn">搜索</button>
              </li>
              <li>
                <a rel="modelAddModal" class="button bg-sub" onclick="reback(4,'main_frame_reportdefine.jsp');" style="display:none">新增模板</a>
              </li>
            </ul>
            			<div id="listTable">
			</div>

			<div  class="flr mg_t10"><!-- <img src="${imagePath}/page_pic.png" /> -->
				<ul id="pageComponent"></ul>
			</div>
          </div>
        </div>
        <!--模板管理列表 结束-->
    </div>
    <!--右侧结束-->
</body>
</html>
