/**
 *eoms统计平台分页组件v1.0
 *auth：lijin 
 *2015/08/24
 */

function loadPageComponentMyRep(url,currentPage,columnOptions,pageComponentId,listComponentId,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback,shareOutCallback,shareInCallback,shareCallback){
			
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
						new eomsPageComponentMyRep(url,columnOptions,pageComponentId,listComponentId,this.currentPage,this.pageSize,this.totalPage,this.rowsData,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback,shareOutCallback,shareInCallback,shareCallback);
						 new dialogComponent("","","","","loadingAddModelDiv").loading_dialog_hide();
					}
				});
			}
		}
		
			
			
function eomsPageComponentMyRep(url,columnOptions,pageComponentId,listComponentId,currentPage,pageSize,totalPage,rowsData,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback,shareOutCallback,shareInCallback,shareCallback){
			
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
		               new loadPageComponentMyRep(url,page,columnOptions,pageComponentId,listComponentId,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback,shareOutCallback,shareInCallback,shareCallback).initData();
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
					var tempCommon_flag="";
					var tempShare_flag="";
					
					if(i%2==0){
						tempRow_fore="<tr class='tablerowodd'>";
						for(var j=0;j<title_array.length;j++){
							if("modelName"==title_array[j]){
								tempRow_fore=tempRow_fore+"<td><a onclick=show_statistic_page('"+rowObj['id']+"')>"+rowObj[title_array[j]]+"</a></td>";
							}else{
								tempRow_fore=tempRow_fore+"<td>"+rowObj[title_array[j]]+"</td>";
							}
							tempCommon_flag=rowObj['isCommonFlag'];
							tempShare_flag=rowObj['isShareFlag'];
						}
					}else{
						tempRow_fore="<tr class='tableroweven'>";
						for(var j=0;j<title_array.length;j++){
							if("modelName"==title_array[j]){
								tempRow_fore=tempRow_fore+"<td><a onclick=show_statistic_page('"+rowObj['id']+"')>"+rowObj[title_array[j]]+"</a></td>";
							}else{
								tempRow_fore=tempRow_fore+"<td>"+rowObj[title_array[j]]+"</td>";
							}
							tempCommon_flag=rowObj['isCommonFlag'];
							tempShare_flag=rowObj['isShareFlag'];
						}
					}
					if("1"==tempCommon_flag){
						//公告
						if("1"!=tempShare_flag){
							tempRow_tail="<td><a  id='list_show_"+i+"' class='btnOperation bg-back glyphicon glyphicon-list-alt mg_r10' title='统计报表'></a><a title='统计报表'></a><a  id='list_shareout_"+i+"' class='btnOperation bg-sub glyphicon glyphicon-log-in' title='取消公告'></a> <a  id='list_share_rep_"+i+"' class='btnOperation bg-yellow glyphicon glyphicon-heart mg_r10' title='分享'></a></td></tr>";
						}else{
							tempRow_tail="<td><a  id='list_show_"+i+"' class='btnOperation bg-back glyphicon glyphicon-list-alt mg_r10' title='统计报表'></a><a title='统计报表'></a><a  id='list_shareout_"+i+"' class='btnOperation bg-sub glyphicon glyphicon-log-in' title='取消公告'></a></td></tr>";
						}
					}else{
						//非公告
						if("1"!=tempShare_flag){
							tempRow_tail="<td><a  id='list_show_"+i+"' class='btnOperation bg-back glyphicon glyphicon-list-alt mg_r10' title='统计报表'></a><a title='统计报表'></a><a  id='list_sharein_"+i+"' class='btnOperation bg-sub glyphicon glyphicon-log-out' title='加入公告'></a> <a  id='list_share_rep_"+i+"' class='btnOperation bg-yellow glyphicon glyphicon-heart mg_r10' title='分享'></a></td></tr>";
						}else{
							tempRow_tail="<td><a  id='list_show_"+i+"' class='btnOperation bg-back glyphicon glyphicon-list-alt mg_r10' title='统计报表'></a><a title='统计报表'></a><a  id='list_sharein_"+i+"' class='btnOperation bg-sub glyphicon glyphicon-log-out' title='加入公告'></a></td></tr>";
						}
						
					}
								
					table_tr=table_tr+tempRow_fore+tempRow_tail;
				}
				var table_tail="</table>";
				$("#"+listComponentId).html(table_fore+table_title+table_tr+table_tail);
				//注册删除和跟新事件
				$.each(rowsData,function(i,value) {   
					var data_id=value['id'];
					var Common_flag=value['isCommonFlag'];
					var share_flag=value['isShareFlag'];
					//报表查询
					$("#list_show_"+i).bind("click",function(){
						deleteCallback(data_id);
					}); 
					//報表分享
					if("0"==share_flag){
						$("#list_share_rep_"+i).bind("click",function(){
							shareCallback(data_id,new loadPageComponentMyRep(url,1,columnOptions,pageComponentId,listComponentId,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback,shareOutCallback,shareInCallback,shareCallback));
						}); 
					}
					
					
			       //报表共享
					if("1"==Common_flag){
						$("#list_shareout_"+i).bind("click",function(){
							shareOutCallback(data_id,new loadPageComponentMyRep(url,1,columnOptions,pageComponentId,listComponentId,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback,shareOutCallback,shareInCallback,shareCallback));
						}); 
					}else{
						$("#list_sharein_"+i).bind("click",function(){
							shareInCallback(data_id,new loadPageComponentMyRep(url,1,columnOptions,pageComponentId,listComponentId,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchOptionVal,deleteCallback,editCallback,shareOutCallback,shareInCallback,shareCallback));
						}); 
					}
				   //报表取消共享
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
				 var searchValue=$("#"+searchOption).val();
				 new loadPageComponentMyRep(url,1,columnOptions,pageComponentId,listComponentId,checkAllComponentId,checkReverseComponentId,searchBtn,searchOption,searchValue,deleteCallback,editCallback,shareOutCallback,shareInCallback,shareCallback).initData();
				 
				  });
			
			
		}