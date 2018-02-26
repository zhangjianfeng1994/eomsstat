

<%@include file="../common/header.jsp"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
	<head>
		
		<title>统计平台</title>
		
		
		<script type="text/javascript">
		
			
		
			$(document).ready(function(){
				$("#frmright").attr("src","main_frame_reportforms.jsp");
			});
			
			function switch_right_con(page_param,ul_li_a){
				$(ul_li_a).addClass("onChoice");
				var ul_lis=$(ul_li_a).parent("li").siblings();
				
				$(ul_lis).each(function(){
					$(this).children("a").removeClass("onChoice");
				  });
		
				if('report_forms'==page_param){
				    $("#frmright").attr("src","main_frame_reportforms.jsp");
				}else if('indicator_manage'==page_param){
					$("#frmright").attr("src","main_frame_indicator.jsp");
				}else if('dimension_manage'==page_param){
					$("#frmright").attr("src","main_frame_dimensions.jsp");
				}else if('model_manage'==page_param){
					$("#frmright").attr("src","main_frame_model.jsp");
				}else if('report_define'==page_param){
					$("#frmright").attr("src","main_frame_reportdefine.jsp");
				}else if('detail_manage'==page_param){
					$("#frmright").attr("src","main_frame_detail.jsp");
				}
			}
			
			//frame自适应
			function iFrameHeight() {   
				var ifm= document.getElementById("frmright");   
				var subWeb = document.frames ? document.frames["frmright"].document : ifm.contentDocument;   
				if(ifm != null && subWeb != null) {
				   ifm.height = subWeb.body.scrollHeight<1010?1010:subWeb.body.scrollHeight+130;
				}   
			}  

		</script>
		
	</head>
	
	<body class="statistic_body" style="overflow-x: hidden">
	
		<div class="row" style="padding: 20px;">
			<div class="col-md-12">
			
				<div class="row">
					<div class="col-md-12">
						<div id="Header"></div>
					</div>
				</div>
				<div class="row" id="mainContent">
					<div  class="col-xs-2">
						<div id="conLeft">
							<div class="admin_msg"> <span><img src="${imagePath}/pic_01.png" /></span>
						        <p>管理员</p>
						     </div>
						     <ul class="statistic-menuList">
						        <li><a onclick="switch_right_con('report_forms',this)" class="onChoice" ><span class="icon_nav_01"></span>统计报表</a></li>
						        <li><a onclick="switch_right_con('indicator_manage',this)" ><span class="icon_nav_02"></span>指标管理</a></li>
						        <li><a onclick="switch_right_con('dimension_manage',this)" ><span class="icon_nav_03"></span>维度管理</a></li>
						        <li><a onclick="switch_right_con('detail_manage',this)" ><span class="icon_nav_03"></span>详单管理</a></li>
						        <li><a onclick="switch_right_con('model_manage',this)" ><span class="icon_nav_04"></span>模板管理</a></li>
						        <li><a onclick="switch_right_con('report_define',this)" ><span class="icon_nav_05"></span>报表自定义</a></li>
						      </ul>
						</div>
				    </div>
				    <div id="conRight" class="col-xs-10">
				    	<!-- height:1010px; -->
				    	 <iframe id="frmright" style="width:100%;"  onLoad="iFrameHeight()" frameborder=”no” border=”0″ marginwidth=”0″ marginheight=”0″ scrolling=”no” allowtransparency=”yes”></iframe>
				    </div>
				</div>
			
				
			</div>
		</div>
	</body>
</html>