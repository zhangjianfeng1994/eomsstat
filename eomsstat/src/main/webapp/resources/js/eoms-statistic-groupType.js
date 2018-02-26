/*
 *报表归属 
 **/

function init_groupType(selectId){
	   
	//报表归属
	$.ajax({
			url : '${pageContext.request.contextPath}/tawStatisticModel/getTableBelongMap.json',
			type : 'post',
			cache : false,
			async : true,
			success : function(data) {
				var str=data.retJson.tableBelong;
				//alert(str);
				var table_belong= $("#"+selectId);
				$("#"+selectId+" option").remove();
				
				for(var i=0;i<data.retJson.tableBelong.length;i++){
				  var temp=data.retJson.tableBelong[i];
				  table_belong.append("<option value='"+temp.EndName+"'>"+temp.CnName+"</option>");
				}
				table_belong.selectpicker();
			 }
	 });
}