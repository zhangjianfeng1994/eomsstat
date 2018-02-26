
function reback(){
			$("#frmright",parent.document).attr("src","main_frame_reportforms.jsp");
			
			$(".menuList li",parent.document).each(function(index){
			        if(0==index){
			        	$(this).children("a").addClass("onChoice");
			        }else{
						$(this).children("a").removeClass("onChoice");
					}
				  });
			
		}
function addRate(title,content,rel_name,href_id,bind_div_id){
	var a= new deleteCallback(title,content,rel_name,href_id,bind_div_id);
	a.confirm_dialog(function(result){
		alert(result);
	});
}