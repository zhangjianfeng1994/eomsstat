/**
 *eoms统计平台分页组件v1.0
 *auth：lijin 
 *2015/08/24
 */

function loadPageComponentDataList(url,currentPage,columnOptions,pageComponentId,listComponentId,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback){
			
			this.pageSize=15;
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
						new eomsPageComponentDataList(url,columnOptions,pageComponentId,listComponentId,this.currentPage,this.pageSize,this.totalPage,this.rowsData,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback);
						//全选
						 $("#"+checkAllComponentId).bind("click",function(){
							 if(null!=checkAllComponentId){
									$(".checkbox_list").each(function(index,checkObj){
										//$(this).attr("checked")
										if ($(checkObj).is(":checked")) {  
											$(checkObj).prop("checked", true);
									    }else {  
									    	$(checkObj).prop("checked", true);  
									    } 
									}); 
									
								}
							  });
						//反选
						 $("#"+checkReverseComponentId).bind("click",function(){
							 alert();
							 if(null!=checkReverseComponentId){
									$(".checkbox_list").each(function(index,checkObj){
										if ($(checkObj).is(":checked")) {  
											$(checkObj).prop("checked", false);  
									    }else {  
									    	$(checkObj).prop("checked", true);  
									    } 
									});
								}
							  });	
				
					}
				});
			}
		}
		
			
			
function eomsPageComponentDataList(url,columnOptions,pageComponentId,listComponentId,currentPage,pageSize,totalPage,rowsData,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback){
			
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
		               new loadPageComponentDataList(url,page,columnOptions,pageComponentId,listComponentId,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback).initData();
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
				table_title+="</tr>";
				
				//表类数据构造
				var table_tr="";
				
				
				for(var i=0;i<rowsData.length;i++){
					var rowObj=rowsData[i];
					var tempRow_fore="";
					var tempRow_tail="";
					
					if(i%2==0){
						tempRow_fore="<tr class='tablerowodd'>";
						for(var j=0;j<title_array.length;j++){
							tempRow_fore=tempRow_fore+"<td>"+rowObj[title_array[j]]+"</td>";
						}
					}else{
						tempRow_fore="<tr class='tableroweven'>";
						for(var j=0;j<title_array.length;j++){
							tempRow_fore=tempRow_fore+"<td>"+rowObj[title_array[j]]+"</td>";
						}
					}
					tempRow_tail="</tr>";
					table_tr=table_tr+tempRow_fore+tempRow_tail;
				}
				var table_tail="</table>";
				$("#"+listComponentId).html(table_fore+table_title+table_tr+table_tail);
				//注册删除和跟新事件
				$.each(rowsData,function(i,value) {   
					
					var data_id=value['id'];
					
					//更新
					$("#list_edit_"+i).bind("click",function(){
						editCallback(data_id,new loadPageComponentDataList(url,1,columnOptions,pageComponentId,listComponentId,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback));
					});
					//删除
					
					$("#list_delete_"+i).bind("click",function(){
						deleteCallback(data_id,new loadPageComponentDataList(url,1,columnOptions,pageComponentId,listComponentId,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback));
					}); 
			       
			  }); 
				
				//刷新页面
			}
			
			
			
			this.pageComponentLoad();
			this.listComponentLoad();
			
			 
			 //查询条件，出查询值
			 $("#"+searchBtn).bind("click",function(){
				 var searchValue=$("#"+searchOption).val();
				 new loadPageComponentDataList(url,1,columnOptions,pageComponentId,listComponentId,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchValue,deleteCallback,editCallback).initData();
				 
				  });
			
			
		}