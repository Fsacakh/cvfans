/**
 * 服务站列表页面处理
 */
$("#Page_StationListView").on("onShown", function(event, showName){
	$('#Page_StationListView #btn_query').trigger('click');
});

$('#Page_StationListView #btn_query').on('click', function(){
	$("#datatable_station").dataTable().fnDestroy();
	$("#datatable_station").dataTable({
		"aoColumns": [
			{ "mData": "STATION_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				return "<div class='checkbox'><label><input type='checkbox' class='dataTable_select' value='" + $.JsonUtil.extract(row, {'stationId':'STATION_ID'}) + "'/></lable></div>";
			}},
			{ "mData": "NAME","sWidth": "200px","sClass":"displayPart"},
			{ "mData": "ADDRESS","sWidth": "100px" },
			{ "mData": "BRAND","sWidth": "100px","sClass":"center" },
			{ "mData": "IS_MEMBER","sWidth": "30px","sClass":"center", "mRender":function(data,type,row){
				return data == 1?'是':'否';
			}},
			{ "mData": "PARENT_ID","sWidth": "30px","sClass":"center", "mRender":function(data,type,row){
				return data == ""?'':'二级';
			}},
			{ "mData": "STATION_ID","sWidth": "75px","sClass":"operation", "mRender":function(data,type,row){
				var btn = "<div class='btn-group'>";
				btn += "<button class='btn purple dropdown-toggle' data-toggle='dropdown'><i class='icon-align-justify'></i>	操作</button>";
				btn += "<ul class='dropdown-menu'>";
				btn += "<li><a href='#' data-action='view' data-page='Page_StationDetail' data-value='{\"stationId\":\"" + data + "\"}'><i class='icon-edit'></i> 查看</a></li>";
				btn += "<li><a href='#' data-action='edit' data-page='Page_StationDetailEdit' data-value='{\"stationId\":\"" + data + "\"}'><i class='icon-edit'></i> 修改</a></li>";
				if(row['USER_STATUS'] == 0){
					btn += "<li><a href='#' data-action='register' data-page='Page_Register' data-value='{\"stationId\":\"" + data + "\",\"ownerRole\":\"2\"}'><i class='icon-user'></i> 账号开设</a></li>";
				}
				
				if(row['USER_STATUS'] == 1){
					btn += "<li><a href='#' data-action='reset' data-page='Page_Register'  data-value='{\"stationId\":\"" + data + "\",\"ownerRole\":\"2\"}'><i class='icon-refresh'></i> 账号重置</a></li>";
					btn += "<li><a href='#' data-action='reset' data-value='{\"stationId\":\"" + data + "\",\"ownerRole\":\"2\"}'><i class='icon-refresh'></i> 账号禁用</a></li>";
				}
				
				btn += "</ul></div>";
				return btn;
			}}
        ]
    });
});

/**
 * 服务站详情页面处理
 */
$("#Page_StationDetailEdit").on("onShown", function(event, params){
	if(params.action == 'edit'){
		$('#frm_station_edit').form(getConfig()).attr('action', '/Persist/Station/Update.action');
		
		$('#frm_station_edit')._load({
			action:params.action,
			entity:"Station",
			data:params.data
		});
	}else{
		$('#frm_station_edit').form(getConfig()).attr('action', '/Persist/Station/Insert.action');
	}
	
	$('#frm_station_edit').on("onSubmit", function(event){
		if($("#province").find('option:selected').attr('value') == ''){
			$("#provinceName").val('');
		}else{
			$("#provinceName").val($("#province").find('option:selected').text());
		}
		
		if($("#city").find('option:selected').attr('value') == ''){
			$("#cityName").val('');
		}else{
			$("#cityName").val($("#city").find('option:selected').text());
		}
		
		if($("#area").find('option:selected').attr('value') == ''){
			$("#areaName").val('');
		}else{
			$("#areaName").val($("#area").find('option:selected').text());
		}
		
		return true;
	});
});

$("#Page_StationDetail").on("onShown", function(event, params){
	if(params.action == 'edit'){
		$('#frm_station').attr('action', '/MultipartPersist/Station/Update.action');
	}else{
		$("#btn_save", this).addClass("hidden");
		$("#btn_add", this).addClass("hidden");
		$("#btn_delete", this).addClass("hidden");
	}
	
	$('#frm_station').form(getConfig());
		
	$('#frm_station')._load({
		action:params.action,
		entity:"Station",
		data:params.data
	});
	
	$('#frm_station').validate({
	  rules: {
	   briefIntro: {
		maxlength: 510,
	   },
	  },
	  messages: {
	   briefIntro: {
		  maxlength: "简介不能超过500个字",
	   },
	  }
	});
	
	$('#frm_station').on("onSubmit", function(event){
		if($("#province").find('option:selected').attr('value') == ''){
			$("#provinceName").val('');
		}else{
			$("#provinceName").val($("#province").find('option:selected').text());
		}
		
		if($("#city").find('option:selected').attr('value') == ''){
			$("#cityName").val('');
		}else{
			$("#cityName").val($("#city").find('option:selected').text());
		}
		
		if($("#area").find('option:selected').attr('value') == ''){
			$("#areaName").val('');
		}else{
			$("#areaName").val($("#area").find('option:selected').text());
		}
			
		return true;
	});
	
	$('a[data-toggle="tab"]', this).on('show.bs.tab', function (e) {
		if($(e.target).attr('href') == '#image'){
			queryStationImages();
		}
	});
});

$("#Page_StationImage").on("onShown", function(event, params){
	if(params.action == 'edit'){
		$('#frm_station_image').attr('action', '/MultipartPersist/StationImage/Update.action');
		
		$('#frm_station_image')._load({
			action:params.action,
			entity:"StationImage",
			data:params.data
		});
	}else{
		$('#frm_station_image').attr('action', '/MultipartPersist/StationImage/Insert.action');
	}
});

function queryStationImages(){
	$("#datatable_station_image").dataTable().fnDestroy();
	$("#datatable_station_image").dataTable({
		"aoColumns": [
			{ "mData": "imgId","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				return "<div class='checkbox'><label><input type='checkbox' class='dataTable_select' value='" + $.JsonUtil.extract(row, {'imgId':'imgId'}) + "'/></lable></div>";
			}},
			{ "mData": "url","sWidth": "200px","sClass":"center", "mRender":function(data,type,row){
				return "<img width='200px' height='100px' src='" + $.URL.get(data) + "'/>"
			}},
			{ "mData": "title","sWidth": "450px","sClass":"left", "mRender":function(data,type,row){
				return "<h2><p>" + data + "</p></h2><p>" + row.memo + "</p>";
			} },
			{ "mData": "imgId","sWidth": "75px","mRender":function(data,type,row){
				var btn = "";
				btn += "<a href='#' data-action='edit' class='btn' data-page='Page_StationImage' data-value='{\"imgId\":\"" + data + "\"}'><i class='icon-edit'></i> 修改</a>";
				return btn;
			}}
        ]
    });
}

/**************************************************服务站客户**********************************************/
/**
 * 服务站列表页面处理
 */
$("#Page_Station_CustomerListView").on("onShown", function(event, showName){
	$('#btn_query', this).trigger('click');
});

$('#Page_Station_CustomerListView #btn_query').on('click', function(){
	$("#datatable_customer").dataTable().fnDestroy();
	$("#datatable_customer").dataTable({
		"aoColumns": [
			{ "mData": "CUSTOMER_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				return "<div class='checkbox'><label><input type='checkbox' class='dataTable_select' value='" + $.JsonUtil.extract(row, {'customerId':'CUSTOMER_ID'}) + "'/></lable></div>";
			}},
			{ "mData": "NAME","sWidth": "100px"},
			{ "mData": "MOBILE_NO","sWidth": "100px" },
			{ "mData": "ADDRESS","sWidth": "300px" }
        ]
    });
});

$(document).on("click", "#Page_Station_CustomerSearch #btn_query", function(){
	$("#datatable_driver").dataTable().fnDestroy();
	$("#datatable_driver").dataTable({
		"aoColumns": [
		    { "mData": "MOBILE_NO","sWidth": "100px" },
			{ "mData": "NAME","sWidth": "100px"},
			{ "mData": "ADDRESS","sWidth": "300px" },
			{ "mData": "DRIVER_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				var btn = "";
				btn += "<a href='javascript:select(\"" + data + "\")'>选择</a>";
				return btn;
			}},
        ]
    });
});

function select(driver){
	var frm = $("#Page_Station_CustomerSearch #frm_customer_save");
	
	$("#driverId", frm).val(driver);
	
	frm.one("onSubmitted", function(){
		$.Dialog.alert("保存成功.", function(){
			$("#Page_Station_CustomerSearch #btn_query").trigger('click');
		});
	});
	
	frm.validate();
	frm.submit();
}
/**************************************************服务站客户**********************************************/


/**************************************************二级服务站**********************************************/
/**
 * 服务站列表页面处理
 */
$("#Page_SubStaionListView").on("onShown", function(event, showName){
	$('#parentId', this).val($('#ownerId').val());
	$('#Page_SubStaionListView #btn_query').trigger('click');
});

/**
 * 服务站详情页面处理
 */
$("#Page_StationDetails_Sub").on("onShown", function(event, param){
	//设置父级服务站parentId的值
	$('#parentId', this).val(''+$('#ownerId').val()+'');
	$('#frm_station').form(getConfig());
	
	if(param.action == 'view'){
		$('#frm_station')._load({
			action:param.action,
			entity:"Station",
			data:param.data
		});
		
		$('#btn_save', this).addClass("hidden");
	}else{
		$('#frm_station').attr('action', '/MultipartPersist/Station/Insert.action');
	}
});

$('#Page_SubStaionListView #btn_query').on('click', function(){
	$("#datatable_station").dataTable().fnDestroy();
	$("#datatable_station").dataTable({
		"aoColumns": [
			{ "mData": "STATION_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				return "<div class='checkbox'><label><input type='checkbox' class='dataTable_select' value='" + $.JsonUtil.extract(row, {'stationId':'STATION_ID'}) + "'/></lable></div>";
			}},
			{ "mData": "NAME","sWidth": "200px"},
			{ "mData": "ADDRESS","sWidth": "100px" },
			{ "mData": "CONTACTER","sWidth": "80px"},
			{ "mData": "CONTACT_TEL","sWidth": "80px" },
			{ "mData": "BRAND","sWidth": "100px" },
			{ "mData": "IS_MEMBER","sWidth": "75px","mRender":function(data,type,row){
				return data == 1?'是':'否';
			}},
			{ "mData": "STATION_ID","sWidth": "75px","sClass":"operation", "mRender":function(data,type,row){
				var btn = "";
				btn += "<a href='#' data-action='view' class='btn' data-page='Page_StationDetails_Sub' data-value='{\"stationId\":\"" + data + "\"}'><i class='icon-edit'></i> 查看</a>";
				return btn;
			}}
        ]
    });
});

/**************************************************服务站折扣**********************************************/
/**
 * 服务站促销列表页面处理
 */
$("#Page_DiscountListView").on("onShown", function(event, showName){
	query();
});

/**
 * 促销详情页面处理
 */
$("#Page_DiscountDetails").on("onShown", function(event, param){
	$('#btn_save').off('click');
	
	if(param.action == 'edit'){
		$('#frm_discount').loadEntity({
			action:param.action,
			entity:"Discount",
			data:param.data
		});
		$('#frm_discount').attr('action', '/Persist/Discount/Update.action');
	}else{
		$('#frm_discount').attr('action', '/Persist/Discount/Insert.action');
	}
});


function query(){
	$("#datatable_discount").dataTable().fnDestroy();
	$("#datatable_discount").dataTable({
		"aoColumns": [
			{ "mData": "DISCOUNT_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				return "<div class='checkbox'><label><input type='checkbox' class='dataTable_select' value='" + $.JsonUtil.extract(row, {'discountId':'DISCOUNT_ID'}) + "'/></lable></div>";
			}},
			{ "mData": "ITEM","sWidth": "100px"},
			{ "mData": "TIME_DISCOUNT","sWidth": "75px"},
			{ "mData": "MATERIAL_DISCOUNT","sWidth": "75px"},
			{ "mData": "MEMO","sWidth": "200px"},
			{ "mData": "DISCOUNT_ID","sWidth": "75px","sClass":"operation", "mRender":function(data,type,row){
				var btn = "";
				btn += "<a href='#' data-action='edit' class='btn' data-page='Page_DiscountDetails' data-value='{\"discountId\":\"" + data + "\"}'><i class='icon-edit'></i> 修改</a>";
				return btn;
			}}
        ]
    });
};

/**
 * 服务站促销列表页面处理
 */
$("#Page_PromotionListView").on("onShown", function(event, showName){
	$('#Page_PromotionListView #btn_query').trigger('click');
});

/**
 * 促销详情页面处理
 */
$("#Page_PromotionDetails").on("onShown", function(event, param){
	if(param.action == 'edit'){
		$('#frm_promotion')._load({
			action:param.action,
			entity:"Promotion",
			data:param.data
		});
		$('#frm_promotion').attr('action', '/Persist/Promotion/Update.action');
	}else{
		$('#frm_promotion').attr('action', '/Persist/Promotion/Insert.action');
	}
});


$('#Page_PromotionListView #btn_query').on('click', function(){
	$("#datatable_promotion").dataTable().fnDestroy();
	$("#datatable_promotion").dataTable({
		"aoColumns": [
			{ "mData": "PROMOTION_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				return "<div class='checkbox'><label><input type='checkbox' class='dataTable_select' value='" + $.JsonUtil.extract(row, {'promotionId':'PROMOTION_ID'}) + "'/></lable></div>";
			}},
			{ "mData": "TITLE","sWidth": "150px"},
			{ "mData": "CONTENT","sWidth": "490px" },
			{ "mData": "PROMOTION_ID","sWidth": "75px","sClass":"operation", "mRender":function(data,type,row){
				var btn = "";
				btn += "<a href='#' data-action='edit' class='btn' data-page='Page_PromotionDetails' data-value='{\"promotionId\":\"" + data + "\"}'><i class='icon-edit'></i> 修改</a>";
				return btn;
			}}
        ]
    });
});
/*
 * 推送消息页面列表处理
 */
$("#Page_PushMessageListView").on("onShown", function(event, showName){
	$("#datatable_pushinfo").dataTable().fnDestroy();
	$("#datatable_pushinfo").dataTable({
		"aoColumns": [
			{ "mData": "PUSH_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				return "<div class='checkbox'><label><input type='checkbox' class='dataTable_select' value='" + $.JsonUtil.extract(row, {'pushId':'PUSH_ID'}) + "'/></lable></div>";
			}},
			{ "mData": "TITLE","sWidth": "200px","mRender":function(data,type,row){ 
				if(data.length>10){
					return  data.substring(1,10)+"....";
				}
				else{
					return  data;
				}
				}},
			{ "mData": "CREATED_DATE","sWidth": "125px"},
			{ "mData": "CONTENT","sWidth": "415px","mRender":function(data,type,row){
				if(data.replace(/<\/?[^>]*>/g,"").length>21){
					return  data.replace(/<\/?[^>]*>/g,"").substring(1,21)+"....";
				}
				else{
					return  data.replace(/<\/?[^>]*>/g,"");
				}
			}},
			{ "mData": "STATUS","sWidth": "50px","mRender":function(data,type,row){
				return data == 0 ? '待发送' : '已发送';
			}},
			{
				"mData": "PUSH_ID",	"sWidth": "20px", "sClass":"center","mRender":function(data,type,row){
					var btn = "<div class='btn-group'>";
					btn += "<button class='btn blue dropdown-toggle' data-toggle='dropdown'><i class='icon-align-justify'></i>	操作</button>";
					btn += "<ul class='dropdown-menu'>";
					btn += "<li><a href='#' data-action='edit' data-page='Page_PushMessageDetailEdit' data-value='{\"pushId\":\"" + data + "\"}'><i class='icon-edit'></i> 查看</a></li>";
					if(row['STATUS'] == 0){ 
					btn += "<li><a  href='#' onclick='sendMessage(\"" + data + "\")'><i class='icon-edit' ></i>发送</a></li>";
					}
					btn += "</ul></div>";
					return btn;
				}
			}
        ]
    });
});

function  sendMessage(pushId){
	var  frm =$("#Page_PushMessageListView  #frm_pushmessage_query"); 
	var  text =$("#pushId",frm);
	text.val(pushId);
	frm.attr("action", "/Persist/PushInfo/Update.action");
	frm.on("onSubmitted", function(event, response){
		if(response.pushId!=""&&response.pushId!=null&&response.pushId!=undefined){
			window.location.href="PushMessageManagement.html"; 
		 }
	});
	frm.validate(); 
	frm.submit();
}

$("#Page_PushMessageDetailEdit").on("onShown", function(event, param){
    var  leftsel = $("#leftsel");
	
	var  rightsel = $("#rightsel");
	
	var page =$(this);
	
	var frm = $('#frm_pushinfo', page);
	
	var btn_refresh = $('#refresh', page);
	
	var  content = $('#content',page);
	
	btn_refresh.bind("click",function(){
		window.location.href='PushMessageManagement.html';
	});
	
	if(param.action == 'insert'){
		$('#frm_pushinfo').attr('action', '/Pushmessage/Add.action');
	}else if(param.action == 'edit'){
		$("#btn_pushinfosave", page).addClass("hidden");
		
		$('#frm_pushinfo')._load({
			action:param.action,
			entity:"PushInfo",
			data:param.data
		});
		content.val(content.val().replace(/<\/?[^>]*>/g,"\n"));
		var data = {};
		data.pushId = param.data.pushId;
		$.Service.invoke({
			url:"/Pushmessage/QueryCustomers.action",
			data:data,
			success:function(response){
				$.each(response, function(index, data){
			    	rightsel.append("<option  value ='"+data.driverId+"'>"+data.NAME+"</option>");
			    	leftsel.find("option[value="+data.driverId+"]").remove();
			    });
			}
		});
	}
	
	$("#btn_touyou").bind("click",function(event){
	       leftsel.find("option:selected").each(function(){
	          $(this).remove().appendTo(rightsel);
	       }); 
	});
	
	$("#btn_touzuo").bind("click",function(event){
	       rightsel.find("option:selected").each(function(){
	           $(this).remove().appendTo(leftsel);
	       });
	      
	});
	
	frm.on("onSubmitted", function(event, response){
		 if(response.pushId!=""&&response.pushId!=null&&response.pushId!=undefined){
			 window.location.href='PushMessageManagement.html';
		 }
	});
    
	$("#btn_pushinfosave", page).bind("click",function(event){
		var  size =rightsel.find("option").length-1;
		var  customers ="";
	    while(size>0){
	    	customers +=rightsel.find("option:eq("+size+")").val()+",";
	    	size--;
	    }
	    customers +=rightsel.find("option:eq("+size+")").val();
	    $("#customerlist", page).val(customers);
	    frm.validate({
	    	rules:{
	    		title:{
	    		  required:true,
	    		  maxlength:128
	    		},
	    		content:{
	    			required:true,
	    			maxlength:510
	    		},
	    		rightsel:{
	    			required:true,
	    		}
	    	},
	    	messages:{
	    		 title:{
		    		  required:"请输入推送消息标题",
		    		  maxlength:"最多输入128个字"
		    	 },
		    	 content:{
		    		 required:"请输入推送消息内容", 
		    		 maxlength:"最多能输入510个字"
		    	 },
		    	 rightsel:{
		    		 required:"请选择推送客户",
		    	 } 
	    	}
	       });
	    frm.submit();
	});
	var inp = $("#title", page);
	inp.bind('keydown', function (event){ 
		if(event.keyCode == 13){
			event.preventDefault(); 
		}
	});
});


/**************************************************服务站评价**********************************************/
$("#Page_Station_CommentListView").on("onShown", function(event, param){
	$('#stationId').val($('#ownerId').val());
	$("#datatable_commment").dataTable().fnDestroy();
	$("#datatable_commment").dataTable({
		"aoColumns": [
			{ "mData": "DRIVER_NAME","sWidth": "100px"},
			{ "mData": "MOBILE_NO","sWidth": "75px"},
			{ "mData": "CREATED_DATE","sWidth": "100px"},
			{ "mData": "GENERAL_SCORE","sWidth": "100px" ,"mRender":function(data,type,row){
				return "<div class='start' data-value='" + data + "'></div>";
			}},
			{ "mData": "PRICE_SCORE","sWidth": "100px" ,"mRender":function(data,type,row){
				return "<div class='start' data-value='" + data + "'></div>";
			}},
			{ "mData": "QUALITY_SCORE","sWidth": "100px" ,"mRender":function(data,type,row){
				return "<div class='start' data-value='" + data + "'></div>";
			}},
			{ "mData": "TIME_SCORE","sWidth": "100px","mRender":function(data,type,row){
				return "<div class='start' data-value='" + data + "'></div>";
			}},
			{ "mData": "BEHAVIOR_SCORE","sWidth": "100px","mRender":function(data,type,row){
				return "<div class='start' data-value='" + data + "'></div>";
			}},
			{ "mData": "TIME_SCORE","sWidth": "50px","mRender":function(data,type,row){
				var btn = "";
				btn += "<a href='#' style='width:50px' data-action='edit' class='btn' data-page='Page_Station_CommentDetail' data-value='" + JSON.stringify(row) + "'>查看</a>";
				return btn;
			}},
        ],
        
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	$('td:eq(3)', nRow).find("div").raty({score:getScore(aData.GENERAL_SCORE), readOnly:  true});
        	$('td:eq(4)', nRow).find("div").raty({score:getScore(aData.PRICE_SCORE), readOnly:  true});
        	$('td:eq(5)', nRow).find("div").raty({score:getScore(aData.QUALITY_SCORE), readOnly:  true});
        	$('td:eq(6)', nRow).find("div").raty({score:getScore(aData.TIME_SCORE), readOnly:  true});
        	
        	$(nRow).hover(function(){
        	}, function(){
        		
        	});
        }
    });
});

$("#Page_Station_CommentDetail").on("onShown", function(event, param){
	$('#driver').val(param.data.DRIVER_NAME);
	$('#mobile').val(param.data.MOBILE_NO);
	$('#generalScore').raty({score:getScore(param.data.GENERAL_SCORE), readOnly:  true});
	$('#priceScore').raty({score:getScore(param.data.PRICE_SCORE), readOnly:  true});
	$('#qualityScore').raty({score:getScore(param.data.QUALITY_SCORE), readOnly:  true});
	$('#timeScore').raty({score:getScore(param.data.TIME_SCORE), readOnly:  true});
	$('#behaviorScore').raty({score:getScore(param.data.BEHAVIOR_SCORE), readOnly:  true});
	$('#comment').val(param.data.COMMENT);
});

function getScore(score){
	if(score == ''){
		return 0;
	}else{
		return score;
	}
}
/**
 * 促销详情页面处理
 */
$("#Page_PromotionDetails").on("onShown", function(event, param){
	if(param.action == 'edit'){
		$('#frm_promotion')._load({
			action:param.action,
			entity:"Promotion",
			data:param.data
		});
		$('#frm_promotion').attr('action', '/Persist/Promotion/Update.action');
	}else{
		$('#frm_promotion').attr('action', '/Persist/Promotion/Insert.action');
	}
});

/**************************************************公共函数**********************************************/
function getConfig(){
	return {
		deserializer:{subBrand:function(){
			var brand='';
			$('input[name="subBrand"]').each(function(){
				if($(this).parent().hasClass("checked")){
					if(brand != '') brand += ',';
					brand += $(this).attr("value");
				}
			});
			
			return brand;
		},serviceMemo:function(frm){
			var serviceMemo='';
			$('input[name="serviceMemo"]', frm).each(function(){
				if($(this).parent().hasClass("checked")){
					if(serviceMemo != '') serviceMemo += ',';
					serviceMemo += $(this).attr("value");
				}
			});
			
			return serviceMemo;
		}},
		
		serializer:{
			subBrand:function(element, data){
				var brands = data.subBrand.split(',');
				var value = $(element).attr('value');
				for(index in brands){
					if(brands[index] == value) return brands[index];
				}
				
				return "";
			},
			serviceMemo:function(element, data){
				var serviceMemos = data.serviceMemo.split(',');
				var value = $(element).attr('value');
				for(index in serviceMemos){
					if(serviceMemos[index] == value) return serviceMemos[index];
				}
				
				return "";
			}
		}
	};
}
/**************************************************服务站搜索**********************************************/
function initPageData_StationSearch() {
	if($('#ownerRole').val()=="2"){
		var datas = {
			entity : "Station",
			parameters : {
				stationId : $('#ownerId').val()
			}
		};
		
		$.Service.invoke( {
			url : "/Query/Station/Load.action",
			data : datas,
			success : function(data) {
				intMap("admin",data,"toPostion");
				rangeStation(data);
			}
		});
	}else{
		var data=null;
		intMap("admin",data,"toIP");
		rangeStation(data);
	}
}

$('#search').on('click',function() {
	var datas = {
		statementId : "cvfans.queryStationInfos",
		parameters : {}
	};
	var name=$("#name").val();
	if ($.trim(name)!= ""
			&& $.trim(name)!= "null"
			&& $.trim(name) != null
			&& $.trim(name) != undefined) {
		datas.parameters.name = $.trim(name);
	}
	if ($("#isMember").is(":checked")) {
		datas.parameters.isMember ="1";
	}

	if (JSON.stringify(datas.parameters) == "{}") {
		initPageData_StationSearch()();
	} else {
		mapObj.clearMap();
		$.Service.invoke( {
			url : "/Query/Station/List.action",
			data : datas,
			success : function(data) {
				$.each(data, function(index, data) {
					addMarker(data, 2);
				});
			}
		});
	}
});
/**************************************************服务站详情**********************************************/
function initPageData_StationDetails(flag,toPosition) {
	var datas = {
		entity : "Station",
		parameters : {
			stationId : $('#stationId').val()
		}
	};
	
	$.Service.invoke( {
		url : "/Query/Driver/Station/Load.action",
		data : datas,
		success : function(data) {
			var img = data.logoFile;
			$('#img').attr("src", $.URL.get(img==''?'images/station/default.png':img));
			
			$('#name').append(data.name);
			$('#contactTel').append(data.contactTel);
			$('#address').append(data.address);
			$('#generalScore').raty({score:getScore(data.generalScore), readOnly:  true});
			$('#qualityScore').raty({score:getScore(data.qualityScore), readOnly:  true});
			$('#timeScore').raty({score:getScore(data.timeScore), readOnly:  true});
			$('#priceScore').raty({score:getScore(data.priceScore), readOnly:  true});
			$("#briefIntro").html(data.briefIntro);
			$('#serviceScope').append(data.serviceMemo);
			
			var stations;
			var cookie = $.cookie("stations");
			if(cookie != undefined){
				stations = JSON.parse(cookie);
			}else{
				stations = {};
			}
			
			stations[data.stationId] = $.JsonUtil.extract(data, {"LOGO_FILE":"logoFile","STATION_ID":"stationId", "NAME":"name"});
			$.cookie('stations', JSON.stringify(stations));
			
		    intMap(flag,data,toPosition);
		    
		    rangeStation(data);
		}
	});
	
	$("#datatable_discount").dataTable().fnDestroy();
	$("#datatable_discount").dataTable({
		"bPaginate" : false,
		"sAjaxSource":"Query/List.action",
		"oLanguage" : { 
			sEmptyTable: "",
			sZeroRecords:"该服务站暂无任何折扣活动。"
		},
		"aoColumns": [
			{ "mData": "ITEM","sWidth": "100px"},
			{ "mData": "TIME_DISCOUNT","sWidth": "75px"},
			{ "mData": "MATERIAL_DISCOUNT","sWidth": "75px"},
			{ "mData": "MEMO","sWidth": "200px"}
        ]
    });
	
	$("#datatable_promotion").dataTable({
		"bPaginate" : false,
		"sAjaxSource":"Query/List.action",
		"oLanguage" : { 
			sEmptyTable: "",
			sZeroRecords:"该服务站暂无任何促销活动。"
		},
		"aoColumns": [
			{ "mData": "TITLE","sWidth": "200px"},
			{ "mData": "CONTENT","sWidth": "400px"}
        ]
    });
	
	
	var $right=$("#right");
	var $left=$("#left");
	var rheight=$right.height();
	var lheight=$left.height();
	if(rheight>lheight)
	{
		$left.css("height",rheight+"px");  
	}
	else
	{
		$right.css("height",lheight+"px");  
	}
}
	
/**************************************************前端服务站查询列表**********************************************/	
$("#Page_StationSearch").on("onShown", function(event, param){
	var page = $(this);
	$("#Page_StationSearch .icon-remove").on("click", function(){
		var target = $('#' + $(this).data('target'));
		
		target.addClass('hidden');
	});
	
	if(param.refresh == '1'){
		$("#Page_StationSearch #btn_query").trigger("click");
	}
});

$("#Page_StationSearch #showMoreProvince").on("click", function(event, param){
	var self = $(this);
	
	var moreProvince = $('#Page_StationSearch #more_provinces');
	if(param == 'hidden'){
		moreProvince.addClass('hidden');
	}else{
		moreProvince.toggleClass('hidden');
		
		if(! moreProvince.hasClass("hidden")){
			$("#Page_StationSearch #showMoreBrand").trigger("click", "hidden");
		}
	}
	
	if(moreProvince.hasClass("hidden")){
		self.attr("src",$.URL.get("/media/image/down_arrow.png"));
	}else{
		self.attr("src",$.URL.get("/media/image/up_arrow.png"));
	}
});

$("#Page_StationSearch #showMoreBrand").on("click", function(event, param){
	var self = $(this);
	
	var moreBrand = $('#Page_StationSearch #more_brands');
	
	if(param == 'hidden'){
		moreBrand.addClass('hidden');
	}else{
		moreBrand.toggleClass('hidden');
		
		if(! moreBrand.hasClass("hidden")){
			$("#Page_StationSearch #showMoreProvince").trigger("click", "hidden");
		}
	}
	
	if(moreBrand.hasClass("hidden")){
		self.attr("src",$.URL.get("/media/image/down_arrow.png"));
	}else{
		self.attr("src",$.URL.get("/media/image/up_arrow.png"));
	}
});

$("#Page_StationSearch #btn_query").on("click", function(){
	var page = $("#Page_StationSearch");
	var provinces = $("#selected_provinces", page);
	var brands = $("#selected_brands", page);
	provinces.val('');
	brands.val('');
	$(".selected", page).find("span").each(function(index){
		var item = $(this);
		var target = item.hasClass("province")?provinces:brands;
		if(target.val() == ''){
			target.val(item.text());
		}else{
			target.val(target.val() + "," + item.text());
		}
	});
	
	$("#datatable_station").dataTable().fnDestroy();
	$("#datatable_station").dataTable({
		"aoColumns": [
			{ "mData": "STATION_ID","sWidth": "150px", "mRender":function(data,type,row){
				var img = $.ImageUtil.get(row.LOGO_FILE,'large');
				var html = "";
				html += "<div class='row' style='padding-top:20px;padding-bottom:20px'>";
				html += "<div class='col-md-5 c_pic'>";
				html += "<img src='" + $.URL.get(img==''?'images/station/default.png':img) + "'>";
				html += "</div>";
				html += "<div class='col-md-7 c_mian'>";
					html += "<div class='c_title'>" + row.NAME + "</div>";
					//html += "<a style='text-decoration: none' href='" + $.URL.get("/station/StationDetail.html?stationId=" + data) + "' target='_blank'><p><h4><strong>" + row.NAME + "</strong></h4></p></a>";
					html += "<p><span class='position'>&nbsp;&nbsp;&nbsp;&nbsp;</span>" + row.ADDRESS + "</p>";
					html += "<p><span class='phone'>&nbsp;&nbsp;&nbsp;&nbsp;</span>"+ row.CONTACT_TEL + "</p>";
					html += "<p id='score' style='width:200px!important'></p>";
					html += "<p>";
					if(row.IS_MEMBER == 1){
						html += "<span><img src='" + $.URL.get("media/image/isMember.png") + "'></span>";
					}
					
					if(row.DISCOUNT_FLAG > 0){
						html += "<span style='padding-left:30px'><img src='" + $.URL.get("/media/image/discount.png") + "'></span>";
					}
					
					if(row.PROMOTION_FLAG > 0){
						html += "<span style='padding-left:30px'><img src='" + $.URL.get("/media/image/promotion.png") + "'></span>";
					}
					html += "</p>"
					
					html += "<div class='c_mian_btn'><a href='" + $.URL.get("station/StationDetail.html?stationId=" + data) + "' target='_blank'>点击查看</a></div>";	
				html += "</div>";
				html += "</div>";
				return html;
			}}
        ],
        "fnCreatedRow": function( nRow, aData, iDataIndex ) {
        	$('#score', nRow).raty({score:getScore(aData.GENERAL_SCORE), readOnly:  true});
        } 
    });
});

$("#Page_StationDiscount_more").on("onShown", function(){
	$("#datatable_discount").dataTable().fnDestroy();
	$("#datatable_discount").dataTable({
		"iDisplayLength" : 12,
		"aoColumns": [
			{ "mData": "COL_0","sWidth": "260px", "mRender":function(data,type,row){
				var col = row.COL_0;
				return getDisplayItem(col);
			}},
			{ "mData": "COL_1","sWidth": "260px", "mRender":function(data,type,row){
				var col = row.COL_1;
				return getDisplayItem(col);
			}},
			{ "mData": "COL_2","sWidth": "260px", "mRender":function(data,type,row){
				var col = row.COL_2;
				return getDisplayItem(col);
			}},
			{ "mData": "COL_3","sWidth": "260px", "mRender":function(data,type,row){
				var col = row.COL_3;
				return getDisplayItem(col);
			}}
        ]
    });
});


$("#Page_StationPromotion_more").on("onShown", function(){
	$("#datatable_promotion").dataTable().fnDestroy();
	$("#datatable_promotion").dataTable({
		"iDisplayLength" : 12,
		"aoColumns": [
			{ "mData": "COL_0","sWidth": "260px", "mRender":function(data,type,row){
				var col = row.COL_0;
				return getDisplayItem(col);
			}},
			{ "mData": "COL_1","sWidth": "260px", "mRender":function(data,type,row){
				var col = row.COL_1;
				return getDisplayItem(col);
			}},
			{ "mData": "COL_2","sWidth": "260px", "mRender":function(data,type,row){
				var col = row.COL_2;
				return getDisplayItem(col);
			}},
			{ "mData": "COL_3","sWidth": "260px", "mRender":function(data,type,row){
				var col = row.COL_3;
				return getDisplayItem(col);
			}}
        ]
    });
});

function getDisplayItem(col){
	var html = '<div class="containerbox">';
	
	if(col != undefined){
		html += '<a href="' + $.URL.get('/station/StationDetail.html?stationId=' + col.STATION_ID ) + '" target="_blank">';
		html += '<img class="logo" src="' + $.URL.get(col.LOGO_FILE) + '">';
        html += '<div class="c_title01">' + col.TITLE + '</div>';
        html += '<div class="c_info">';
        html += '  <p>' + col.NAME + '</p>';
        html += '  <p><span class="position">&nbsp;&nbsp;&nbsp;&nbsp;</span>' + col.ADDRESS + '</p>';
        html += '  <p><span class="phone">&nbsp;&nbsp;&nbsp;&nbsp;</span>' + col.CONTACT_TEL +'</p>';
        html + '</div>';
        html += '</a>';
	}
	
	html + '</div>';
	return html;
}

$("#Page_StationSearch span.query").on("click", function(){
	var item = $(this);
	if(item.hasClass("selected")) return;
	
	var category = item.hasClass("brand")?"brand":"province";
	
	var page = $("#Page_StationSearch");
	var selected= page.find("#selected");
	
	var selectedItems = selected.find("span." + category);
	if(selectedItems.length == 5){
		$.Dialog.alert("每个条件多选不能超过5个。");
		return;
	}
	
	var selectedItem = $('<span>' + item.text() + '</span>');
	selectedItem.addClass(category);
	var prev = item.prevAll();
	if(prev[0] == undefined){
		selectedItem.data("index", "0");
	}else{
		selectedItem.data("index", "" + prev.length);
	}
	
	if(selectedItems[0] == undefined){
		selected.append($("<span class='i'>></span>"));
		selected.append(selectedItem);
	}else{
		selectedItems.last().after(selectedItem);
	}
	
	selected.find("span." + category).last().on("click", function(){
		var t = $(this), prev = t.prev();
		
		var category = t.hasClass("province")?".province":".brand";
		var index = t.data("index");
		
		page.find("span.query" + category + ":eq(" + index + ")").removeClass("selected");
		
		t.remove();
		if(prev.hasClass("i")){
			t = prev.next();
			if(t.length == 0 || t.hasClass("i")){
				prev.remove();
			}
		}
	});
	
	item.addClass("selected");
});

function getReadedStation(readedList){
	var stations = {};
	var cookie = $.cookie('stations');
	if(cookie == undefined){
		var userId = $.cookie('userId');
		if(userId != undefined){
			var context = {
				url:"/Query/List.action",
				data:{
					statementId:"cvfans.queryReadedStationInfos"
				},
				success:function(data){
					$.each(data, function(index, data) {
						stations[data.STATION_ID] = $.JsonUtil.extract(data, {"LOGO_FILE":"LOGO_FILE","STATION_ID":"STATION_ID", "NAME":"STATION_ID"});
					});
					
					$.cookie('stations', JSON.stringify(stations));
				}
			};
			
			$.Service.invoke(context);
		}else{
			
		}
	}else{
		stations = JSON.parse(cookie);
	}
	
	for(var stationId in stations){
		if(stationId == 'undefined') continue;
		
		var station = JSON.parse(stations[stationId]);
		readedList.append("<li class='list-group-item' data-id="+stations.STATION_ID+"><h5><strong><a href='" + $.URL.get('/station/StationDetail.html?stationId=' + station.STATION_ID ) +"'><img src='"+ $.URL.get(station.LOGO_FILE) + "'/><br/>" +station.NAME+"</a></strong></h5></li>");
	}
}