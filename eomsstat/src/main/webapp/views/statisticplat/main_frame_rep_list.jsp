<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
	$(document).ready(function() {
		var commondata=null;
		
	});
	
	function data_screening(){
		var modelId=$("#list_model_id").val();
		$.ajax({
				url : '${pageContext.request.contextPath}/tawStatisticDataScreen/getModelInfoById.json',
				type : 'post',
				dataType:"json",
				data:{'model_id':modelId},
				success : function(data) {
			
					var model_param=data.retJson.paramList;
					var model_indicator=data.retJson.indexList;
					var model_complex=data.retJson.compleList;
					
					//加载数据筛选
					var contend_time="<div class='txt-fld_left'><label>时间粒度:</label><span class='pd_r5'><input name='beginTime' id='beginTime' style='width:125px;height:35px' placeholder='开始日期'/></span>-<span  class='pd_r5'><input name='endTime' id='endTime' style='width:125px;height:35px' placeholder='结束日期'/></span></div>";
					
					var contend_indicator="<div class='txt-fld_left'><label>指标:</label>";
					for(var i=0;i<model_indicator.length;i++){
						contend_indicator=contend_indicator+"<div class='txt-fld_left'><input type='checkbox' checked='true' class='indicators' value='"+model_indicator[i].modelItemId+"'> <label> "+model_indicator[i].indicateName+"</label></div>";
					}
					for(var i=0;i<model_complex.length;i++){
						contend_indicator=contend_indicator+"<div class='txt-fld_left'><input type='checkbox' checked='true' class='indicators' value='"+model_complex[i].modelItemId+"'> <label> "+model_complex[i].paramName+"</label></div>";
					}
					contend_indicator=contend_indicator+"</div>";
					
					var contend_param="<div class='txt-fld_left'><label>维度:</label>";
			
					for(var i=0;i<model_param.length;i++){
					    var tempChildrenList=model_param[i].childrenList;
					    var optionStr="";
					    
					    for(var j=0;j<tempChildrenList.length;j++){
					    	optionStr=optionStr+"<option value="+tempChildrenList[j].paramCode+">"+tempChildrenList[j].paramText+"</option>";
					    }
						contend_param=contend_param+"<div class='txt-fld_left'><input type='checkbox' checked='true' class='parameters' value='"+model_param[i].modelItemId+"'> <label> "+model_param[i].paramName+":</label><span><select id='"+model_param[i].paramEng+"_condition' class='selectpicker' multiple data-live-search='true'><optgroup label='维度选择' data-subtext='' data-icon='icon-ok'>"+optionStr+"</optgroup></select></span></div>";
					}
					contend_param=contend_param+"</div>";
					
					
					var a= new dialogComponent("数据筛选",contend_time+contend_indicator+contend_param,"paramAddModal","paramAddModal","paramAddModelDiv_add");
					
				   
				    a.self_dialog(function(){},function(choose_data){ 
						if(choose_data==true){
							var items="";
							var begintime=$("#beginTime").val();
							var endtime=$("#endTime").val();
							//获取元素传入后台,ajax将值传入
							$(".indicators").each(function(index,checkObj){
								if($(checkObj).is(":checked")){
									if(items.length==0){
										items=$(checkObj).val();
									}else{
										items=items+","+$(checkObj).val();
									}
								}
							
							});
							//获取维度
							$(".parameters").each(function(index,checkObj){
								if($(checkObj).is(":checked")){
									if(items.length==0){
											items=$(checkObj).val();
										}else{
											items=items+","+$(checkObj).val();
										}
									}
							});
							//获取维度查询条件
							var demensionCondition="";
							var tempChooseComple="";
							for(var i=0;i<model_param.length;i++){
								if(null!=$("#"+model_param[i].paramEng+"_condition").val()){
									tempChooseComple=$("#"+model_param[i].paramEng+"_condition").val()+"";
									demensionCondition=demensionCondition+"and "+model_param[i].paramEng+" exists('" +tempChooseComple.replace(",","','")+"')";
								}
							}
									
							generate_report(items,begintime,endtime,demensionCondition);
							
						} 
					});
					//加载纬度树
					$(".selectpicker").selectpicker({});
					
					$("#beginTime").bind("click",function(){
						laydate({elem: '#beginTime',istime: true, format: 'YYYY-MM-DD'});
					});
					$("#endTime").bind("click",function(){
						laydate({elem: '#endTime',istime: true, format: 'YYYY-MM-DD'});
					});
					

					
				}
			});
	}

	//生成报表
	function generate_report(modelItems,beginTime,endTime,dimensionCondition){
			$.ajax({
				url : '${pageContext.request.contextPath}/tawStatisticReport/getReport.json',
				type : 'post',
				cache : false,
				async : true,
				data:{'model_id':$("#list_model_id").val(),'data_screen':modelItems,'beginTime':beginTime,'endTime':endTime,'dimensionCondition':dimensionCondition},
				success : function(data) {
					commondata=data;
					$("#report_table").empty();
					
					var tableComponents=new Array();
					var layer=data.retJson.treeDeep;
					//复合表格标题数据处理
					generateTitle(data.retJson.retComplexTitle,tableComponents,data.retJson.treeDeep);
					if(0==layer){
						//生成单个表头
						generatePureTitle(data.retJson,layer);
					}else{
						//生成复合表头
						generateComplexTitle(data.retJson,tableComponents,layer);
					}
					//生成表格内容
				    //alert(JSON.stringify(data));
					
				    generateTableContent(data.retJson.retData,data.retJson.retIndex,data.retJson.retDemension,data.retJson.retComplexTitle,data.retJson.retComplexGrsp,data.retJson.retTransferDimension);
					//图形报表
					add_graph_area();
				
				}
			});
	
	}
	
	//生成单个表头
	function generatePureTitle(retjson,layer){
		var table_title="<tr>";
		for(var m=0;m<retjson.retDemension.length;m++){
			table_title=table_title+"<th rowspan='"+layer+"'>"+retjson.retDemension[m].paramName+"</th>";
		}
		for(var m=0;m<retjson.retIndex.length;m++){
			table_title=table_title+"<th rowspan='"+layer+"'>"+retjson.retIndex[m].indicateName+"</th>";
		}
		$("#report_table").append(table_title);
	
	}
	
	function generateComplexTitle(retjson,tableComponents,layer){
		//根据tableComponents构造标题头
		for(var i=1;i<=layer;i++){
				var table_title="<tr>";
				//指标和纬度
				if(i==1){
					for(var m=0;m<retjson.retDemension.length;m++){
						table_title=table_title+"<th rowspan='"+layer+"'>"+retjson.retDemension[m].paramName+"</th>";
					}
					for(var m=0;m<retjson.retIndex.length;m++){
						table_title=table_title+"<th rowspan='"+layer+"'>"+retjson.retIndex[m].indicateName+"</th>";
					}
				}		
				for(var j=0;j<tableComponents.length;j++){
						var tempTh=tableComponents[j];
						if(tempTh.rowNum==i){
							table_title=table_title+"<th rowspan='"+tempTh.rowSpan+"' colspan='"+tempTh.columnSpan+"'>"+tempTh.textName+"</th>";
						}			
				}
				table_title=table_title+"</tr>";
				$("#report_table").append(table_title);
		}
	}
	
	//生成表格数据
	function generateTableContent(retData,index,demension,complexs,grasp,transfer){
		var table_content="";
		var model_id=$("#list_model_id").val();
		//转化复合表头为Map
		
		for(var i=0;i<retData.length;i++){
			var tr_line=retData[i];
			if(i%2==0){
				table_content=table_content+"<tr  class='tablerowodd'>";
			}else{
				table_content=table_content+"<tr class='tableroweven'>";
			}
			
			
			for(var j=0;j<retData[i].length;j++){
				var tempDemensionLen=demension.length;
				var tempIndexLen=index.length;
				var demensionList=new Array();
				//获取所有的维度
				for(var m=0;m<demension.length;m++){
					var temp_dataGraph=new dataGraph(demension[m].paramEng,retData[i][m]);
					demensionList.push(temp_dataGraph);
				}
				if(j<tempDemensionLen){
				    //维度数据攥取
				    //数据转化为中文
				    var temptrans=transfer[demension[j].id][retData[i][j]];
				    var retDataValue=(typeof(temptrans)=='undefined')?("【"+retData[i][j]+"】"):temptrans;
					table_content=table_content+"<td>"+retDataValue+"</td>";
				}else{
					//指标数据攥取
					//获取指标
					//第一指标
					if(j<(tempDemensionLen+tempIndexLen)){
					    //alert(JSON.stringify(demensionList));
					    var num=j-tempDemensionLen;
						var indexId=index[num].id;
						var isGrasp=index[num].isGrasp;
						if('0'==isGrasp){
							table_content=table_content+"<td>"+retData[i][j]+"</td>";
						}else{
							table_content=table_content+"<td onclick=grasp_data('"+model_id+"','"+indexId+"',"+JSON.stringify(JSON.stringify(demensionList))+",'')>"+retData[i][j]+"</td>";
						}
					}else{
						var num=j-tempDemensionLen-tempIndexLen+1;
						//alert(num);
						var indexId="";
						var condition="";
						var isGrasp=index[num].isGrasp;
						for(var count=0;count<grasp.length;count++){
							//alert(grasp[count].indexId);
							var tempGrasp=grasp[count];
							if(num==tempGrasp.countNum){
								indexId=tempGrasp.indexId;
								condition=tempGrasp.condition;
							}
						}
						if('0'==isGrasp){
							table_content=table_content+"<td>"+retData[i][j]+"</td>";
						}else{
							table_content=table_content+"<td onclick=grasp_data('"+model_id+"','"+indexId+"',"+JSON.stringify(JSON.stringify(demensionList))+",'"+URLencode(condition)+"')>"+retData[i][j]+"</td>";
						}
					}
					//复合指标
				}
			}
			table_content=table_content+"</tr>";
		}
					
		$("#report_table").append(table_content);
	}
	
	
	
	
	function grasp_data(modelId,indexId,demensionList,condition){
		var url="${pageContext.request.contextPath}/views/statisticplat/main_frame_rep_data_grasp.jsp?modelId="+modelId+"&indexId="+indexId+"&condition="+condition+"&demensionList="+encodeURI(demensionList);
		window.open(url,'','height=550,width=950,left=180,top=150,resizable=yes,scrollbars=yes');
	}
	
	//递归遍历json串数据
	function generateTitle(row_data,tableComponents,totalLayer){
		for(var i=0;i<row_data.length;i++){
		     var tempComplex=row_data[i];
		     //alert(tempComplex.rowSpan+"-"+tempComplex.cloumnSpan+"-"+tempComplex.name+"-"+tempComplex.currentLayer); 
			 var tableComponent=new multiTableComponent(tempComplex.rowSpan,tempComplex.cloumnSpan,tempComplex.name,tempComplex.currentLayer);
			 tableComponents.push(tableComponent);
			 if(tempComplex.childDimension.length>0){
		     	generateTitle(tempComplex.childDimension,tableComponents,totalLayer);
		     }
		}
	}
	
	//封装复合table元素
	function multiTableComponent(rowSpan,columnSpan,textName,rowNum){
		this.rowSpan=rowSpan;
		this.columnSpan=columnSpan;
		this.textName=textName;
		this.rowNum=rowNum;
		return this;
	}
	
	
	//传入攥取详单对象
	function dataGraph(column,value){
		this.column=column;
		this.value=value;
		return this;
	}
	
	
	//导出
	function excelExport(){
		var demension = commondata.retJson.retDemension;
		var index = commondata.retJson.retIndex;
		var data = commondata.retJson.retData;
		var complexTitle = commondata.retJson.retComplexTitle;
		//location.href="${pageContext.request.contextPath}/tawStatisticReport/excelExport.json?demensionStr="+JSON.stringify(demension)+"&indexStr="+JSON.stringify(index)+"&dataStr="+JSON.stringify(data)+"&complexTitleStr="+JSON.stringify(complexTitle);
		$("#demension_export").val(JSON.stringify(demension));
		$("#index_export").val(JSON.stringify(index));
		$("#data_export").val(JSON.stringify(data));
		$("#complexTitle_export").val(JSON.stringify(complexTitle));
		$("#excelExportForm").submit();
	}
	
	function URLencode(sStr){
		return escape(sStr).replace("'", '\'').replace("=", '\=').replace("+", '\+').replace("-", '\-').replace("*", '\*').replace("/", '\/').replace(">", '\>').replace("<", '\<');
    }
	 
  
	
</script>
<!-- 指标新增begin -->
<div id="paramAddModelDiv"></div>
<div id="paramAddModelDiv_add"></div>
<a href="#open_param_window" rel="paramAddModal" ></a>
<!-- 指标新增end -->
<form id="excelExportForm" method="post" action="${pageContext.request.contextPath}/tawStatisticReport/excelExport.json" >
	<input type="hidden" id="demension_export" name="demension_export" value=""/>
	<input type="hidden" id="index_export" name="index_export" value=""/>
	<input type="hidden" id="data_export" name="data_export" value=""/>
	<input type="hidden" id="complexTitle_export" name="complexTitle_export" value=""/>
</form>
<span class="flr gear_list"><input type="hidden" id="list_model_id"  style="" />
<!-- <a class="glyphicon glyphicon-cog" onclick="data_screening()">筛选</a> -->
</span>
<ul class="btn_list ">
	<li>
		<button class="button bg-main" type="button" onclick="generate_report(null,null,null,null);">生成报表</button>
	</li>
	<li>
		<button class="button bg-main" type="button" onclick="excelExport();">导出</button>
	</li>
</ul>
<div>

<table class="result-tab" id="report_table">
</table>
</div>