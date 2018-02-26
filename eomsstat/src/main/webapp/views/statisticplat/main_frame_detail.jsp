<%@include file="../common/header.jsp"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>


<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="${cssPath}/maskLayer.css">
<script type="text/javascript">
		$(document).ready(function() {
			var columnOptions={"detailNumName":"详单名称","url":"url路径","groupType":"报表归属"};
         	var url="${pageContext.request.contextPath}/tawStatisticDetail/detailCollection.json";
			//加载列表
			var loadPageComponent_temp=new loadPageComponent(url,1,columnOptions,"pageComponent","listTable","checkall","checkreverse","searchBtn","seachBtnValue","",function(result,eoms_refresh){
				//删除
				var a= new dialogComponent("详单删除","<div class='txt-fld'><h3><b>确认删除当前记录？<b></h3></div>","indexAddModal",null,"indexAddModelDiv");
				a.confirm_dialog(function(data){
					//删除
					if(data==true){
						$.ajax({
						url : '${pageContext.request.contextPath}/tawStatisticDetail/detailRemove.json',
						type : 'post',
						cache : false,
						async : true,
						data:{'detail_id':result},
						success : function(data) {
							eoms_refresh.initData();
								}
						});
					}
				});
			},function(result,eoms_refresh){
				//编辑
				$.ajax({
				url : '${pageContext.request.contextPath}/tawStatisticDetail/getDetailById.json',
				type : 'post',
				cache : false,
				async : false,
				data:{'detail_id':result},
				success : function(data) {
					var detailId=data.retJson.tawStatisticDetail.id;
					var detailNumName=data.retJson.tawStatisticDetail.detailNumName;
					var groupType=data.retJson.tawStatisticDetail.groupType;
					var url=data.retJson.tawStatisticDetail.url;
					var detailItems=null;
					if(null==data.retJson.tawStatisticDetail.tawStatisticDetailItems){
						detailItems=[];
					}else{
						detailItems=data.retJson.tawStatisticDetail.tawStatisticDetailItems;
					}
					
					     
			        var appendStr="";
			        
			        
			        for(var itemIndex=0;itemIndex<detailItems.length;itemIndex++){
			        	appendStr=appendStr+"<div><img src='${imagePath}/delete.png' onclick='delete_detail_column(this)'/>删除字段<div class='txt-fld'><label>详单字段:</label><input type='text' class='form-control input_inline edit_detailColumnEng' value='"+detailItems[itemIndex].detailColumnEng+"' /></div>"+
						  "<div class='txt-fld'><label>详单中文名:</label><input type='text' class='form-control input_inline edit_detailColumnChina' value='"+detailItems[itemIndex].detailColumnChina+"' /></div></div>";
			        }
					var content="<form method= 'post' id='update' action= '${pageContext.request.contextPath}/tawStatisticIndex/indexAdd.json'><div style='height:auto; margin:0 auto;'>"+
					"<div class='hidden-area'><label>type:</label><input type='text' name ='submitType' value='edit' readonly = 'true' /></div>"+
					"<div class='hidden-area'><label>detailId:</label><input type='text' name ='id' value='"+detailId+"' readonly = 'true' /></div>"+
					"<div class='hidden-area'><label>edit_detailColumnEng:</label><input type='text' id='edit_detailColumnEng_text' name ='edit_detailColumnEng_text' readonly = 'true' /></div>"+
					"<div class='hidden-area'><label>edit_detailColumnChina:</label><input type='text' id='edit_detailColumnChina_text' name ='edit_detailColumnChina_text' readonly = 'true' /></div>"+
					"<div class='txt-fld'><label>详单名称:</label><input type='text' class='form-control input_inline' id='detailNumName' name ='detailNumName' value='"+detailNumName+"' /></div>"+
					"<div class='txt-fld'><label>详单url:</label><input type='text' class='form-control input_inline' id='url' name ='url' value='"+url+"' /></div>"+
					"<div class='txt-fld'><label>报表归属:</label><select id='groupType_edit' name='groupType_edit' value='"+groupType+"'></select></div>"+
					"<div class='txt-fld_left'><label>新增详单字段 <img src='${imagePath}/operate_add.png' onclick=add_detail_column('edit') /></label><div class='txt-fld_left'><div id='detail_column_all_edit'>"+appendStr+"<div></div>"+
					"</div></div>"+
					"</div>"+
					"</div>"+
					"</div></form>";
					 var a= new dialogComponent("详单编辑",content,"indexAddModal",null,"indexAddModelDiv");
					 a.confirm_dialog(function(data){ 
						 if(data==true){
						 	var temp_add_detailColumnEng_text="";
							var temp_add_detailColumnChina_text="";
							$(".form-control.input_inline.edit_detailColumnEng").each(function(columnIndex,columnObj){
								if(0==temp_add_detailColumnEng_text.length){
									temp_add_detailColumnEng_text=$(columnObj).val();
								}else{
									temp_add_detailColumnEng_text=temp_add_detailColumnEng_text+","+$(columnObj).val();
								}
							});
							$(".form-control.input_inline.edit_detailColumnChina").each(function(columnIndex,columnObj){
								if(0==temp_add_detailColumnChina_text.length){
									temp_add_detailColumnChina_text=$(columnObj).val();
								}else{
									temp_add_detailColumnChina_text=temp_add_detailColumnChina_text+","+$(columnObj).val();
								}
							});
						
							//alert(temp_add_detailColumnEng_text+"==="+temp_add_detailColumnChina_text);
							$("#edit_detailColumnEng_text").val(temp_add_detailColumnEng_text);
							$("#edit_detailColumnChina_text").val(temp_add_detailColumnChina_text);
						 
							 $.ajax({
							       cache: true,
							       type: "POST",
							       url:'${pageContext.request.contextPath}/tawStatisticDetail/detailAdd.json',
							       data:$('#update').serialize(),//formid
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
					 init_table_belong("groupType_edit",'${pageContext.request.contextPath}',function(){$("#groupType_edit").val(groupType);$("#groupType_edit").selectpicker();});
					
					 $("#confirm_href_id").css("position","relative");
					 $("#confirm_href_id").css("top","-100px");
				 }
			 });

			});
			loadPageComponent_temp.initData();
			
			//加载新增对话框
			var divStr="<form method= 'post' id='save'><div style='height:auto; margin:0 auto;width:auto;'>"+
			"<div class='hidden-area'><label>type:</label><input type='text' name ='submitType' value='add' readonly = 'true' /></div>"+
			"<div class='hidden-area'><label>add_detailColumnEng:</label><input type='text' id='add_detailColumnEng_text' name ='add_detailColumnEng_text' readonly = 'true' /></div>"+
			"<div class='hidden-area'><label>add_detailColumnChina:</label><input type='text' id='add_detailColumnChina_text' name ='add_detailColumnChina_text' readonly = 'true' /></div>"+
			"<div class='txt-fld'><label>详单名称:</label><input type='text' class='form-control input_inline' id='detailNumName' name ='detailNumName'/></div>"+
			"<div class='txt-fld'><label>详单url:</label><input type='text' class='form-control input_inline' id='url' name ='url'/></div>"+
			"<div class='txt-fld'><label>报表归属:</label><select id='groupType_add' name='groupType_add'></select></div>"+
			"<div class='txt-fld_left' ><label>新增详单字段 <img src='${imagePath}/operate_add.png' onclick=add_detail_column('add') /></label><div class='txt-fld_left'><div id='detail_column_all_add' ><div></div>"+
			"</div></div>"+
			"</div>"+
			"</div></form>";
			
			new dialogComponent("详单新增",divStr,"indexAddModal","open_index_window","indexAddModelDiv_add").self_dialog(function(){
				//初始化报表归属
				init_table_belong("groupType_add",'${pageContext.request.contextPath}',function(){$("#groupType_add").selectpicker();});
			},function(data){
				if(data==true){
					//数据封装
					var temp_add_detailColumnEng_text="";
					var temp_add_detailColumnChina_text="";
					$(".form-control.input_inline.add_detailColumnEng").each(function(columnIndex,columnObj){
						if(0==temp_add_detailColumnEng_text.length){
							temp_add_detailColumnEng_text=$(columnObj).val();
						}else{
							temp_add_detailColumnEng_text=temp_add_detailColumnEng_text+","+$(columnObj).val();
						}
					});
					$(".form-control.input_inline.add_detailColumnChina").each(function(columnIndex,columnObj){
						if(0==temp_add_detailColumnChina_text.length){
							temp_add_detailColumnChina_text=$(columnObj).val();
						}else{
							temp_add_detailColumnChina_text=temp_add_detailColumnChina_text+","+$(columnObj).val();
						}
					});
				
					//alert(temp_add_detailColumnEng_text+"==="+temp_add_detailColumnChina_text);
					$("#add_detailColumnEng_text").val(temp_add_detailColumnEng_text);
					$("#add_detailColumnChina_text").val(temp_add_detailColumnChina_text);
					
					$.ajax({
					       cache: true,
					       type: "POST",
					       url:'${pageContext.request.contextPath}/tawStatisticDetail/detailAdd.json',
					       data:$('#save').serialize(),//formid
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
			
			
			
			
		});
		


		
		
		
		function add_detail_column(type){
			var appendStr="<div><img src='${imagePath}/delete.png' onclick='delete_detail_column(this)'/>删除字段<div class='txt-fld'><label>详单字段:</label><input type='text' class='form-control input_inline "+type+"_detailColumnEng' /></div>"+
						  "<div class='txt-fld'><label>详单中文名:</label><input type='text' class='form-control input_inline "+type+"_detailColumnChina' /></div></div>";
			$("#detail_column_all_"+type).append(appendStr);
			$("#open_index_window").css("position","relative");
			$("#open_index_window").css("top","-100px");
			
		}
		
		function delete_detail_column(deleteObj){
			$(deleteObj).parent("div").remove();
			$("#open_index_window").css("position","relative");
			$("#open_index_window").css("top","-100px");
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
            <div class="w_120">详单管理列表</div>
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
                <input type="text" id="seachBtnValue" class="input statistic-search_input" id="siteurl" name="siteurl" size="20" placeholder="根据详单名称查询"  />
              </li>
              <li>
                <button class="button bg-main"  id="searchBtn">搜索</button>
              </li>
              <li>
                <a href="#open_index_window" rel="indexAddModal"  class="button bg-sub">新增详单</a>
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
