
/**
 *eoms 对话框组件v1.0
 *auth：lijin 
 *2015/08/24
 *说明：
 *<a href="#open_window_model" rel="leanModal">打开登陆窗口</a>
 *new dialogComponent("指标新增",contend,"leanModal","temp","open_window_model")。self_dialog();
 */

function dialogComponent(title,content,rel_name,href_id,bind_div_id){
	
	//自定义对话框
	this.self_dialog=function(validateCallback,callback){
		//加载对话框
		//$("#"+href_id).show();
		var open_window_html="<div id='"+href_id+"' class='open_window'><div id='signup-ct'><div class='open_window_header'><h3>"+title+"</h3><a class='modal_close'></a></div><div id='"+href_id+"_content'></div></div></div>";	
		
		$("#"+bind_div_id).html(open_window_html);
		$("a[rel*="+rel_name+"]").leanModal({ top: 100, closeButton: "#"+href_id+">#signup-ct>.open_window_header>.modal_close",type:"self"});
		//对话框内容
		var contentStr = "<div style='text-align:center;'><b>"+content+"</b></div><div id='"+href_id+"_btn' style='text-align:center;margin-bottom:10px;margin-top:10px;'><button class='button bg-sub' id='confirm_dialog_ok'>确认</button>  <button class='button bg-main' id='confirm_dialog_exit'>取消</button></div>";
		$("#"+href_id+"_content").append(contentStr);
		//$(contentStr).appendTo("#"+href_id+"_content");
		validateCallback();
		$("#lean_overlay").css({ "display": "block", opacity: 0.4 });
		//$("#lean_overlay").fadeTo(200);
		//$("#lean_overlay").show(200);
		$("#"+href_id).show();
		
		//$("#"+bind_div_id).show();
		//alert(href_id);
		$("#"+href_id+"_btn>#confirm_dialog_ok").bind("click",function(){
			$("#"+href_id).css({ "display": "none" });
			$("#lean_overlay").css({ "display": "none"});
			callback(true);
		});
		
		$("#"+href_id+"_btn>#confirm_dialog_exit").bind("click",function(){
			$("#"+href_id).css({ "display": "none" });
			$("#lean_overlay").css({ "display": "none"});
			callback(false);
		});
		$("#"+href_id+">#signup-ct>.open_window_header>.modal_close").bind("click",function(){
			$("#"+href_id).css({ "display": "none" });
			$("#lean_overlay").css({ "display": "none"});
			callback(false);
		});
		
	}
	
	//信息框
	//确认框
	
	this.confirm_dialog=function(callback){
		
		href_id="confirm_href_id";

		var open_window_html="<div id='"+href_id+"' class='open_window'><div id='signup-ct'><div class='open_window_header'><h3>"+title+"</h3><a class='modal_close'></a></div><div id='"+href_id+"_content'></div></div></div>";	
		$("#"+bind_div_id).html(open_window_html);
		//对话框内容
		$("#"+href_id+"_content").html("<div style='text-align:center'><b>"+content+"</b></div><div style='text-align:center;margin-bottom:10px;margin-top:10px;'><button class='button bg-sub' id='confirm_dialog_ok'>确认</button>  <button class='button bg-main' id='confirm_dialog_exit'>取消</button></div>");
		
		//确认对话框       
		$("a[rel*="+rel_name+"]").leanModal({ top: 100, closeButton: ".modal_close" ,type:"confirm",bind_div:"#"+href_id});
		
		$("#confirm_dialog_ok").bind("click",function(){
			$("#"+href_id).css({ "display": "none" });
			$("#lean_overlay").css({ "display": "none"});
			callback(true);
			
		});
		
		
		
		$("#confirm_dialog_exit").bind("click",function(){
			$("#"+href_id).css({ "display": "none" });
			$("#lean_overlay").fadeOut(200);
			callback(false);
		});
		
		
		$("#"+href_id).show();
	}
	
	this.loading_dialog=function(){
		
		href_id="loading_href_id";
		var open_window_html="<div id='"+href_id+"' class='open_window_loading'><div class='progress' style='margin-bottom: 5px;margin-top:5px;margin-left:6px;margin-right:6px;'><div class='progress-bar progress-bar-striped active' role='progressbar' aria-valuenow='35' style='width:100%;height:100%'></div></div></div>";	
		
		var overlay = $("<div id='lean_overlay'></div>");
	    $(document.body).append(overlay); 
		$("#"+bind_div_id).html(open_window_html);
		$("#lean_overlay").css({ "display": "block", opacity: 0.4 });
		$("#"+href_id).show();
	}
	
	this.loading_dialog_hide=function(){
		href_id="loading_href_id";
		$("#"+href_id).css({ "display": "none" });
		$("#lean_overlay").fadeOut(200);
	}
}