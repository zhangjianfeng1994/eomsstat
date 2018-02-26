/**
 *eoms统计平台分页组件v1.0
 *auth：lijin 
 *2015/08/24
 */

function loadPageComponentCommon(url,currentPage,columnOptions,pageComponentId,listComponentId,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback){
			
			this.pageSize=5;
			this.totalPage=0;
			this.currentPage=(null==currentPage)?1:currentPage;
			this.rowsData={};
			
			this.initData=function(){
				$.ajax({
				url : url,
				type : 'post',
				cache : false,
				data:{'page':this.currentPage,'pageSize':this.pageSize,'searchOption':searchOptionVal},
				success : function(data) {
						this.totalPage=data.retJson.total;
						this.currentPage=data.retJson.currentPageNo;
						this.pageSize=data.retJson.pageSize;
						this.rowsData=data.retJson.rows;
						//加载分页
						new eomsPageComponentCommon(url,columnOptions,pageComponentId,listComponentId,this.currentPage,this.pageSize,this.totalPage,this.rowsData,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback);
						}
				});
			}
		}
		
			
			
function eomsPageComponentCommon(url,columnOptions,pageComponentId,listComponentId,currentPage,pageSize,totalPage,rowsData,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback){
			
			//初始化分页组件显示
			this.pageComponentLoad=function(){
			    
				$("#"+pageComponentId).empty();
				 var options = {
				 	bootstrapMajorVersion:3,
		            currentPage: currentPage, 
		            numberOfPages:pageSize, 
		            totalPages: totalPage,  
		            size:"nomal",  
		            alignment:"center",  
		            itemTexts: function (type, page, current) {   
		                switch (type) {  
		                    case "first":  
		                        return "首页";  
		                    case "prev":  
		                        return "&lt;";  
		                    case "next":  
		                        return "&gt;";
		                    case "last":  
		                        return "尾页";
		                    case "page":  
		                        return  page;
		                }                 
		            },  
		            onPageClicked: function (e, originalEvent, type, page) {
		                //刷新数据
		               new loadPageComponentCommon(url,page,columnOptions,pageComponentId,listComponentId,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback).initData();
		               //return page;
		            }  
		        }
				$("#"+pageComponentId).bootstrapPaginator(options);  
			}
			//初始化分页列表
			this.listComponentLoad=function(){
			    var table_fore="<table class='result-tab'>";
				var title_array = [];
				//表头构造
				var table_title="<tr>";
				for ( var columnName in columnOptions ){ // 方法 
					
					
				  table_title+="<th>"+columnOptions[columnName]+"</th>";
				  title_array.push(columnName);
				}
				table_title+="<th>操作</th></tr>";
				
				//表类数据构造
				var table_tr="";
				
				
				for(var i=0;i<rowsData.length;i++){
					var rowObj=rowsData[i];
					var tempRow_fore="";
					var tempRow_tail="";
					
					if(i%2==0){
						tempRow_fore="<tr class='tablerowodd'>";
						for(var j=0;j<title_array.length;j++){
							if("modelName"==title_array[j]){
								tempRow_fore=tempRow_fore+"<td><a onclick=show_statistic_page('"+rowObj['id']+"')>"+rowObj[title_array[j]]+"</a></td>";
							}else{
								tempRow_fore=tempRow_fore+"<td>"+rowObj[title_array[j]]+"</td>";
							}
							
						}
					}else{
						tempRow_fore="<tr class='tableroweven'>";
						for(var j=0;j<title_array.length;j++){
							//alert(title_array[j]);
							if("modelName"==title_array[j]){
								tempRow_fore=tempRow_fore+"<td><a onclick=show_statistic_page('"+rowObj['id']+"')>"+rowObj[title_array[j]]+"</a></td>";
							}else{
								tempRow_fore=tempRow_fore+"<td>"+rowObj[title_array[j]]+"</td>";
							}
						}
					}
					tempRow_tail="<td><a  id='list_delete_"+i+"' class='btnOperation bg-back glyphicon glyphicon-list-alt mg_r10' title='统计报表'></a> </td></tr>";
					table_tr=table_tr+tempRow_fore+tempRow_tail;
				}
				var table_tail="</table>";
				$("#"+listComponentId).html(table_fore+table_title+table_tr+table_tail);
				//注册删除和跟新事件
				$.each(rowsData,function(i,value) {
					var data_id=value['id'];
					//删除
					$("#list_delete_"+i).bind("click",function(){
						deleteCallback(data_id);
					}); 
			       
			  }); 
				
				//刷新页面
			}
			
			
			
			this.pageComponentLoad();
			this.listComponentLoad();
			//全选
			 $("#"+checkAllComponentId).bind("click",function(){
				 if(null!=checkAllComponentId){
						$(".checkbox_list").each(function(index){
							if ($(this).attr("checked")) {  
								$(this).prop("checked", true);
						    }else {  
						    	$(this).prop("checked", true);  
						    } 
						}); 
						
					}
				  });
			//反选
			 $("#"+checkReverseComponentId).bind("click",function(){
				 if(null!=checkReverseComponentId){
						$(".checkbox_list").each(function(index){
							if ($(this).is(":checked")) {  
								$(this).prop("checked", false);  
						    }else {  
						    	$(this).prop("checked", true);  
						    } 
						});
					}
				  });
			 
			 //查询条件，出查询值
			 $("#"+searchBtn).bind("click",function(){
				 //alert();
				 var searchValue=$("#"+searchOption).val();
				 //alert("--"+searchValue);
				 new loadPageComponentCommon(url,1,columnOptions,pageComponentId,listComponentId,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchValue,deleteCallback,editCallback).initData();
				 
				  });
		}