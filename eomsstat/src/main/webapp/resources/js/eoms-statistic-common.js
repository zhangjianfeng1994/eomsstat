function reback(chooseNum,srcJsp){
			$("#frmright",parent.document).attr("src",srcJsp);
			
			$(".statistic-menuList li",parent.document).each(function(index){
			        if(chooseNum==index){
			        	$(this).children("a").addClass("onChoice");
			        }else{
						$(this).children("a").removeClass("onChoice");
					}
				  });
			
		}

function addRate(title,content,rel_name,href_id,bind_div_id){
	var a= new dialogComponent(title,content,rel_name,href_id,bind_div_id);
	a.confirm_dialog(function(result){
		alert(result);
	});
}
/**
 *初始化报表归属
 */
function init_table_belong(bindSelect,url,callback){
	//报表归属
	$.ajax({
			url : url+'/tawStatisticModel/getTableBelongMap.json',
			type : 'post',
			cache : false,
			async : false,
			success : function(data) {
				var str=data.retJson.tableBelong;
				var table_belong= $("#"+bindSelect);
				$("#"+bindSelect+" option").remove();
				
				for(var i=0;i<data.retJson.tableBelong.length;i++){
				  var temp=data.retJson.tableBelong[i];
				  if(i==0){
				  	table_init=temp.EndName;
				  }
				  table_belong.append("<option value='"+temp.EndName+"'>"+temp.CnName+"</option>");
				}
				
				callback();
				
			 }
	 });
}

