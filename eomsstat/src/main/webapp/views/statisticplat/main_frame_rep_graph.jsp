<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript"> 

	//图形图标切换
	function pic_animate(){
		$(".font_white>span>a").each(function(){
			$(this).click(function(){
				var imgSrc=$(this).children("img").attr("src");
	    	
	    		$(this).siblings().each(function(index,obj){
	    			var tempSrc=$(obj).children("img").attr("src");
	    			$(obj).children("img").attr("src",tempSrc.replace("_on","_off"));
	    		});
	    	$(this).children("img").attr("src",imgSrc.replace("_off","_on"));
	    	//图片切换
	    	var graph_src=imgSrc.replace("_off","_on");
	    	if(graph_src.indexOf("chart_c_on")>0){
	    		var dataObj=$(this).parent().parent().parent().next().children(".FusionCharts");
	    		var dataStr=dataObj.attr("data");
	    		dataObj.attr("data",dataStr.substring(0, dataStr.lastIndexOf("/"))+"/Column3D.swf");
	    	}else if(graph_src.indexOf("chart_a_on")>0){	    				
	    		var dataObj=$(this).parent().parent().parent().next().children(".FusionCharts");
	    		var dataStr=dataObj.attr("data");
	    		dataObj.attr("data",dataStr.substring(0, dataStr.lastIndexOf("/"))+"/Pie3D.swf");	
	    	}else if(graph_src.indexOf("chart_b_on")>0){
	    		var dataObj=$(this).parent().parent().parent().next().children(".FusionCharts");
	    		var dataStr=dataObj.attr("data");
	    		dataObj.attr("data",dataStr.substring(0, dataStr.lastIndexOf("/"))+"/Line.swf");
	    	}	
		});
  	  });
	}
	
	//根据指标数添加div至图形页面
	function add_graph_area(){
	 	var indicators=commondata.retJson.retIndex;
	 	var param=commondata.retJson.retDemension;
	 	var ret_complex=commondata.retJson.retComplexTitle;
	 	
	 	$("#graph_all").html("");
	 	//单个指标
	 	for(var i=0;i<indicators.length;i++){
	 		var position=param.length+i;
	 		var divStr="<div class='row'><div class='col-xs-12'><div><h4 class='font_white'>统计指标<span class='flr'><a href='#' class='pd_r10'><img src='${imagePath}/chart_c_on.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_a_off.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_b_off.png' /></a></span></h4></div><div class='bg_blue' id='graph_area"+i+"'></div></div></div>";
	 		$("#graph_all").append(divStr);
	 		
	 		chart_fire("Column3D","graph_area"+i,position,indicators[i].indicateName);
	 	}
	 	//复合指标
	 	for(var i=0;i<ret_complex.length;i++){
	 		var position=param.length+indicators.length+i;
	 		var divStr="<div class='row'><div class='col-xs-12'><div><h4 class='font_white'>统计指标<span class='flr'><a href='#' class='pd_r10'><img src='${imagePath}/chart_c_on.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_a_off.png' /></a><a href='#' class='pd_r10'><img src='${imagePath}/chart_b_off.png' /></a></span></h4></div><div class='bg_blue' id='graph_area_complex"+i+"'></div></div></div>";
	 		$("#graph_all").append(divStr);
	 		
	 		chart_fire_complex("Column3D","graph_area_complex"+i,position,ret_complex[i].name,ret_complex[i].cloumnSpan);
	 	
	 	}
	 	
	 	var graph_all=320*(indicators.length+ret_complex.length)+300;
	
	 	iFrameHeight(Math.max(graph_all,$("#report_table").height()));
	 	
	 	pic_animate();
	}
	
	
	function chart_fire_complex(type,renderDiv,indexLocation,indicateName,colspan){
		var dimension=commondata.retJson.retDemension;
		var indicator=commondata.retJson.retIndex;
		var data=commondata.retJson.retData;
		var transferDimension=commondata.retJson.retTransferDimension;
		
		var chartFire = new FusionCharts( "${pageContext.request.contextPath}/resources/FusionCharts/"+type+".swf", "column2D11Id", "800", "300", "0", "0" );
		var dataObj=[];
		
		for(var i=0;i<data.length;i++){
			
			var templabel="";
			var tempValue=0;
			for(var j=0;j<dimension.length;j++){
				var temptrans=transferDimension[dimension[j].id][data[i][j]];
				var retDataValue=(typeof(temptrans)=='undefined')?("【"+data[i][j]+"】"):temptrans;
				if(j==0){
					templabel=retDataValue;
				}else{
					templabel=templabel+"-"+retDataValue;
				}
			}
			for(var k=0;k<colspan;k++){
			  tempValue=parseInt(tempValue)+parseInt(data[i][indexLocation+k]);
			}
			
			var temp={"label":templabel,"value":tempValue};
			dataObj.push(temp);
		}
		
		var jsonStr={                         
    		"chart":{ 
            	"caption" : indicateName ,
                            "xAxisName" : dimension[0].paramName,                                  
                            "yAxisName" : "",                                  
                            "numberPrefix" : "",
                            "bgColor":"#52AFFF",
                            "baseFontColor":"#ffffff",
                            "canvasBgColor":"#2885D3",
                            "hoverCapBgColor":"#2885D3"   
            		},                                                
             	"data" :dataObj
             	};
             
	 	chartFire.setJSONData(jsonStr);
     	chartFire.render(renderDiv);
		
		
	}
	
	
	function chart_fire(type,renderDiv,indexLocation,indicateName){
		var dimension=commondata.retJson.retDemension;
		var indicator=commondata.retJson.retIndex;
		var data=commondata.retJson.retData;
		var transferDimension=commondata.retJson.retTransferDimension;
		
		var chartFire = new FusionCharts( "${pageContext.request.contextPath}/resources/FusionCharts/"+type+".swf", "column2D11Id", "800", "300", "0", "0" );
		var dataObj=[];
		
		for(var i=0;i<data.length;i++){
			
			var templabel="";
			for(var j=0;j<dimension.length;j++){
				var temptrans=transferDimension[dimension[j].id][data[i][j]];
				var retDataValue=(typeof(temptrans)=='undefined')?("【"+data[i][j]+"】"):temptrans;
				if(j==0){
					templabel=retDataValue;
				}else{
					templabel=templabel+"-"+retDataValue;
				}
			}
			var temp={"label":templabel,"value":data[i][indexLocation]};
			dataObj.push(temp);
		}
        
        var jsonStr={                         
    		"chart":{ 
            	"caption" : indicateName ,
                            "xAxisName" : dimension[0].paramName,                                  
                            "yAxisName" : "",                                  
                            "numberPrefix" : "",
                            "bgColor":"#52AFFF",
                            "baseFontColor":"#ffffff",
                            "canvasBgColor":"#2885D3",
                            "hoverCapBgColor":"#2885D3"   
            		},                                                
             	"data" :dataObj
             	};
             
	 	chartFire.setJSONData(jsonStr);
     	chartFire.render(renderDiv);
	}
	
	
    function iFrameHeight(data) {   
		var ifm= parent.document.getElementById("frmright");   
		var subWeb = parent.document.frames ? parent.document.frames["frmright"].document : ifm.contentDocument;   
		if(ifm != null && subWeb != null) {
			ifm.height =  Math.max(data,1100);
		}   
	} 

</script>
<div class="pd_all_10">
	<div id="graph_all"></div>
</div>
