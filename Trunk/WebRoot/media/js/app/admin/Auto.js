var guarantee = ['未担保','待审批','已担保'];

$("#Page_AutoListView_Sys").on("onShown", function(event, param){
	$('#Page_AutoListView_Sys #btn_query').trigger('click');
});

$('#Page_AutoListView_Sys #btn_query').on('click', function(){
	
	$("#datatable_auto").dataTable().fnDestroy();
	$("#datatable_auto").dataTable({
		"aoColumns": [
			{ "mData": "AUTO_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				return "<div class='checkbox'><label><input type='checkbox' class='dataTable_select' value='" + $.JsonUtil.extract(row, {'autoId':'AUTO_ID'}) + "'/></lable></div>";
			}},
			{ "mData": "PLATE_NO","sWidth": "150px" },
			{ "mData": "BRAND_NAME","sWidth": "150px" },
			{ "mData": "MODEL_NAME","sWidth": "150px" },
			{ "mData": "CREATED_DATE","sWidth": "150px" },
			{ "mData": "AUTO_ID","sWidth": "60px","sClass":"operation", "mRender":function(data,type,row){
				var btn ='';
				btn += "<a href='#' data-action='view' class='btn' data-page='Page_AutoDetails_Sys' data-value='{\"autoId\":\"" + data + "\"}'><i class='icon-edit'></i> 查看</a></li>";
				return btn;
			}}
        ]
    });
});

$("#Page_AutoDetails_Sys").on("onShown", function(event, param){
	$('#frm_auto')._load({
		action:"view",
		entity:"AutoInfo",
		data:param.data
	});
});


$("#Page_AutoListView").on("onShown", function(event, param){
	$('#Page_AutoListView #btn_query').trigger('click');
});

$("#Page_AutoDetails").on("onShown", function(event, param){
	if(param.action=='edit'){
		$('#frm_auto').attr('action','/MultipartPersist/AutoInfo/Update.action');
		
		$('#frm_auto')._load({
			entity:"AutoInfo",
			data:param.data
				
		});
	}else{
		$('#frm_auto').attr('action','/MultipartPersist/AutoInfo/Insert.action');
	}
	
	$('#frm_auto').on("onSubmit", function(event){
		$('#typeName').val($.Input.getText($('#type')));
		$('#brandName').val($.Input.getText($('#brand')));
		$('#modelName').val($.Input.getText($('#model')));
	});

	
	$("#type").on("change", function(event){
		$("#brand").find("option:first").attr("selected", "selected");
		$("#model").find("option:first").attr("selected", "selected");
	});
	
	$("#brand").on("change", function(event){
		$("#model").find("option:first").attr("selected", "selected");
	});
});

$('#Page_AutoListView #btn_query').on('click', function(){
	$("#datatable_auto").dataTable().fnDestroy();
	$("#datatable_auto").dataTable({
		"aoColumns": [
			{ "mData": "AUTO_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				return "<div class='checkbox'><label><input type='checkbox' class='dataTable_select' value='" + $.JsonUtil.extract(row, {'autoId':'AUTO_ID'}) + "'/></lable></div>";
			}},
			{ "mData": "PLATE_NO","sWidth": "150px" },
			{ "mData": "TYPE_NAME","sWidth": "150px" },
			{ "mData": "BRAND_NAME","sWidth": "150px" },
			{ "mData": "MODEL_NAME","sWidth": "150px" },
			{ "mData": "CREATED_DATE","sWidth": "150px" },
			{ "mData": "AUTO_ID","sWidth": "60px","sClass":"operation", "mRender":function(data,type,row){
				var btn ='';
				btn += "<a href='#' data-action='edit' class='btn' data-page='Page_AutoDetails' data-value='{\"autoId\":\"" + data + "\"}'><i class='icon-edit'></i> 修改</a></li>";
				return btn;
			}}
        ]
    });
});

/**
 * ************************************************ 服务站车辆担保处理
 * *********************************************
 */
$("#Page_AutoGuranteeApproveListView").on("onShown", function(event, showName){
	$("#btn_query", this).trigger("click");
});

$("#Page_AutoGuranteeApproveDetail").on("onShown", function(event, param){
	var data = {};
	data.guaranteeId = param.data.GUARANTEE_ID;
	$('#frm_guarantee').attr('action','/Persist/Approve/AutoGuarantee/Update.action');
	$('#frm_guarantee')._load({
		action:'edit',
		statementId:"cvfans.queryAutoGuaranteeInfo",
		data:data
	});
	
	if(context.action == 'approve'){
		$('#btn_save', this).show();
	}else{
		$('#btn_save', this).hide();
	}
});


$("#Page_AutoGuranteeApproveListView #btn_query").on("click", function(){
	$("#datatable_guarantee").dataTable().fnDestroy();
	$("#datatable_guarantee").dataTable({
		"aoColumns": [
			{ "mData": "AUTO_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				return "<div class='checkbox'><label><input type='checkbox' class='dataTable_select' value='" + $.JsonUtil.extract(row, {'autoId':'AUTO_ID','guaranteeId':'GUARANTEE_ID'}) + "'/></lable></div>";
			}},
			{ "mData": "PLATE_NO","sWidth": "100px" },
			{ "mData": "OWNER_NAME","sWidth": "150px" },
			{ "mData": "OWNER_CONTACTER","sWidth": "80px" },
			{ "mData": "OWNER_CONTACT_TEL","sWidth": "80px" },
			{ "mData": "BRAND_NAME","sWidth": "50px" },
			{ "mData": "MODEL_NAME","sWidth": "100px" },
			{ "mData": "GUARANTEE","sWidth": "70px","mRender":function(data,type,row){
				if(data >= 0 && data <=3){	
					return guarantee[data];
				}
				
				return "";
			}},
			
			{ "mData": "AUTO_ID","sWidth": "75px","sClass":"operation", "mRender":function(data,type,row){
				var btn = "";
				if(row.GUARANTEE == 1){
					btn += "<a href='#' data-action='approve' class='btn' data-page='Page_AutoGuranteeApproveDetail' data-value='" + JSON.stringify(row) + "'><i class='icon-reorder'></i> 审批</a>";
				}else{
					btn += "<a href='#' data-action='view' class='btn' data-page='Page_AutoGuranteeApproveDetail' data-value='" + JSON.stringify(row) + "'><i class='icon-reorder'></i> 查看</a>";
				}
				return btn;
			}}
        ]
    });
});

/**
 * ************************************************ End Of 车辆担保处理
 * *********************************************
 */


/**
 * ************************************************ 车管车辆担保处理
 * *********************************************
 */
$("#Page_Enterprise_AutoGuaranteeApplyList").on("onShown", function(event, showName){
	$('#Page_Enterprise_AutoGuaranteeApplyList #btn_query').trigger('click');
});

$("#Page_AutoGuaranteeApply").on("onShown", function(event, param){
	$("#autoId", this).list({statementId:'cvfans.queryAutoInfos', filter:'plateNo', cascadeMapping:{autoId:'AUTO_ID'}, showColumn:'PLATE_NO'});
	$("#stationId", this).list({statementId:'cvfans.queryStationInfos', filter:'name', cascadeMapping:{stationId:'STATION_ID'}, showColumn:'NAME', placeholder:"输入服务站名进行查询"});
	
	if(param.action=='view')
	{
		$('#btn_save').addClass("hidden");
		$('#frm_guarantee').attr('action','/Persist/AutoGuarantee/Update.action');
		
		$('#frm_guarantee')._load({
			action:param.action,
			entity:"AutoGuarantee",
			data:param.data
		});
	}else{
		$('#appr').addClass("hidden");
		$('#autoId').val(param.data.autoId).lookup();
		$('#frm_guarantee').attr('action','/Persist/AutoGuarantee/Insert.action');
	}
});

$('#Page_Enterprise_AutoGuaranteeApplyList #btn_query').on('click', function(){
	$("#datatable_guarantee").dataTable().fnDestroy();
	$("#datatable_guarantee").dataTable({
		"aoColumns": [
			{ "mData": "GUARANTEE_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				return "<div class='checkbox'><label><input type='checkbox' class='dataTable_select' value='" + $.JsonUtil.extract(row, {'guaranteeId':'GUARANTEE_ID','autoId':'AUTO_ID'}) + "'/></lable></div>";
			}},
			{ "mData": "PLATE_NO","sWidth": "20px" },
			{ "mData": "STATION_NAME","sWidth": "300px" },
			{ "mData": "APPL_DATE","sWidth": "100px" },
			{ "mData": "GUARANTEE","sWidth": "70px" ,"mRender":function(data,type,row){
				if(data >= 0 && data <=3){	
					return guarantee[data];
				}
				
				return "";
			}},
			{ "mData": "GUARANTEE_ID","sWidth": "75px","sClass":"operation", "mRender":function(data,type,row){
				var btn = "";
				if(row['GUARANTEE']==0){
					btn += "<a href='#' data-action='apply' class='btn' data-page='Page_AutoGuaranteeApply' data-value='{\"autoId\":\"" + row['AUTO_ID'] + "\"}'><i class='icon-refresh'></i> 申请</a></li>";
				}else{
					btn += "<a href='#' data-action='view' class='btn' data-page='Page_AutoGuaranteeApply' data-value='{\"guaranteeId\":\"" + data + "\"}'><i class='icon-edit'></i> 查看</a></li>";					
				}
				return btn;
			}},
        ]
    });
});
/**
 * ************************************************ End Of 车管车辆担保处理
 * *********************************************
 */

/** ************************************************车辆维修单管理********************************************* */
/**
 * 担保维修服务列表页面处理
 */
$("#Page_Enterprise_ServiceListView").on("onShown", function(event, showName){
	$('#enterpriseId', this).val($('#ownerId').val());
	$('#Page_Enterprise_ServiceListView #btn_query').trigger('click');
});

/**
 * 担保维修服务详情页面处理
 */
$("#Page_Enterprise_ServiceDetail").on("onShown", function(event, param){
	var frm_service = $("#frm_service", this);
	
	frm_service.on("onLoaded", function(){
		var invoiceFile = $('#invoiceFile', frm_service).val();
		if(invoiceFile == ''){
			$('#btn_view_invoiceFile', frm_service).addClass("hidden");
		}else{
			$('#btn_view_invoiceFile', frm_service).on("click", function(){
				window.open($.URL.get(invoiceFile));
			})
		}
		
		if($('#payMode1', frm_service).attr('checked') != 'checked'){
			$("#btn_apply", this).addClass("hidden");
		}else{
			param.data.stationId=$('#guaranteeStationId', frm_service).val();
			param.data.stationName=$('#GUARANTEE_STATION_NAME', frm_service).val();
			
			$("#btn_apply", this).data("value", param.data);
		}
	});
	
	frm_service._load({
		action:'view',
		statementId:"cvfans.queryAutoServiceInfo_enterprise",
		data:param.data
	});
});

$('#Page_Enterprise_ServiceListView #btn_query').on('click', function(){
	$("#datatable_service").dataTable().fnDestroy();
	$("#datatable_service").dataTable({
		"aoColumns": [
			{ "mData": "PLATE_NO","sWidth": "75px"},
			{ "mData": "STATION_NAME","sWidth": "200px"},
			{ "mData": "ENTER_DATE","sWidth": "75px"},
			{ "mData": "PAYMENT_AMOUNT","sWidth": "60px"},
			{ "mData": "PAYMENT_STATUS","sWidth": "60px","mRender":function(data,type,row){
				return data == 1?'已付费':'待付费';
			}},
			{ "mData": "PAY_MODE","sWidth": "60px","mRender":function(data,type,row){
				if(data == 1){
					return "担保支付";
				}else if(data == 2){
					return "车管支付";
				}else if(data == 3){
					return "个人支付";
				}
			}},
			
			{ "mData": "SERVICE_ID","sWidth": "75px","sClass":"operation", "mRender":function(data,type,row){
				var btn = "<a href='#' data-action='view' class='btn' data-page='Page_Enterprise_ServiceDetail' data-value='{\"serviceId\":\"" + data + "\"}'><i class='icon-reorder'></i> 详情</a>";
				return btn;
			}}
        ]
    });
});

/**
 * 担保维修服务列表页面处理
 */
$("#Page_Station_ServiceListView").on("onShown", function(event, showName){
	$('#btn_query', this).trigger('click');
});

/**
 * 担保维修服务详情页面处理
 */
$("#Page_Station_ServiceDetail").on("onShown", function(event, param){
	$("#autoId", this).list({
		statementId:'cvfans.queryAutoInfoForService', 
		filter:'plateNo', 
		showColumn:'PLATE_NO', 
		placeholder:"输入车牌号进行检索",
		cascadeMapping:{
			autoId:'AUTO_ID', 
			brand:'BRAND_NAME', 
			plateNo:'PLATE_NO',
			model:'MODEL_NAME',
			enterprise:'OWNER_NAME', 
			enterpriseContacter:'OWNER_CONTACTER', 
			enterpriseTel:'OWNER_CONTACT_TEL',
			station:'STATION_NAME',
			stationContacter:'STATION_CONTACTER',
			stationTel:'STATION_TEL'
		}
	});
	
	$("#driverId", this).list({
		statementId:'cvfans.queryDriverInfos', 
		filter:'licenseNo', 
		showColumn:'LICENSE_NO',
		placeholder:"输入驾驶证号进行检索",
		cascadeMapping:{
			driverId:'DRIVER_ID', 
			contacter:'NAME', 
			contactTel:'MOBILE_NO'
		}
	});
	
	$('#frm_service').validate({
		focusCleanup:true,//clear the error message when the error element get focus again. 
		onkeyup:false, 
		errorPlacement: function(error, element) { 
			error.css("color","red");
//			error.appendTo(element.parent()); 
			error.appendTo(element.parent().parent().find('.control-label'));  
		},
		rules: {
		   serviceCode: {
		       required:true,
	   	   },	
	   	   autoId: {
	   		   required:true,
	   	   },	
	   	   driverId: {
	   		   required:true,
	   	   },	
		   paymentAmount: {
			   required:true,
		   },
		   serviceAmount: {
		    required: true,
		   },
		   	enterDate: {
			   required: true,
		   },
		    leaveDate: {
			   required: true,
		   },
		   serviceItems: {
			   required: true,
		   },
		  },
		messages: {
		   serviceCode: {
			   required: "维修单号不能为空",
		   },
		   autoId: {
			   required: "车牌号码不能为空。",
		   },
		   driverId: {
			   required: "驾驶证号不能为空。",
		   },
		   serviceAmount: {
			   required: "结算金额不能为空",
		   },
		   paymentAmount: {
			   required: "支付金额不能为空",
		   },
		   enterDate: {
			   required: "维修日期不能为空",
		   },
		   leaveDate: {
			   required: "出厂日期不能为空",
		   },
		   serviceItems: {
			   required: "维修描述不能为空",
		   },
		}
	});
	
	
	$('#frm_service').on('onSubmit', function(){
		var guaranteeStationName = $('#station').val();
		var checked = $("#payMode1").parent().hasClass("checked");
		
		if(checked == 'checked' && guaranteeStationName==''){
			$.Dialog.alert("车辆没有担保公司，所以不能采用担保支付.");
			
			return false;
		}
		
		var enterDate = $dp.$('enterDate').value, leaveDate= $dp.$('leaveDate').value;
		if(enterDate != "" && leaveDate != ''){
			if(enterDate > leaveDate){
				$.Dialog.alert("出厂日期不能小于维修日期.");
				return false;
			}
		}
		return true;
	}).on("onLoaded", function(event, param){
		var status = param.guaranteeStatus;
		$('#guaranteeStatus').val(guarantee[status]);
	});
	
	$('#btn_save').off('click');
	if(param.action == 'edit'){
		var frm = $('#frm_service');
		
		frm.attr('action', '/MultipartPersist/AutoService/Update.action');
		
		frm._load({
			action:'edit',
			entity:"AutoService",
			data:param.data
		});
		
	}else{
		$('#frm_service').attr('action', '/MultipartPersist/AutoService/Insert.action');
	}
});

$('#Page_Station_ServiceListView #btn_query').on('click', function(){
	$("#datatable_service").dataTable().fnDestroy();
	$("#datatable_service").dataTable({
		"aoColumns": [
			{ "mData": "SERVICE_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				return "<div class='checkbox'><label><input type='checkbox' class='dataTable_select' value='" + data + "' data-delete='{\"serviceId\":\"" + data + "\"}' </lable></div>";
			}},
			{ "mData": "SERVICE_CODE","sWidth": "100px"},
			{ "mData": "PLATE_NO","sWidth": "70px"},
			{ "mData": "OWNER_NAME","sWidth": "100px","mRender":function(data,type,row){
				if(data=='个人'){
					return row.OWNER_CONTACTER;
				}else{
					return data;
				}
			}},
			{ "mData": "OWNER_CONTACT_TEL","sWidth": "70px"},
			{ "mData": "ENTER_DATE","sWidth": "70px"},
			{ "mData": "PAYMENT_STATUS","sWidth": "80px","mRender":function(data,type,row){
				return data == 1?'已支付':'未支付';
			}},
			{ "mData": "SERVICE_ID","sWidth": "75px","sClass":"operation", "mRender":function(data,type,row){
				var btn = "";
				btn += "<a href='#' data-action='edit' class='btn' data-page='Page_Station_ServiceDetail' data-value='{\"serviceId\":\"" + data + "\"}'><i class='icon-edit'></i> 修改</a>";
				return btn;
			}}
        ]
    });
});

$("#Page_PaymentGuaranteeApply").on("onShown", function(event, param){
	var page = $(this);
	var frm_guarantee = $('#frm_guarantee', page);
	
	$("#stationId", frm_guarantee).list({statementId:'cvfans.queryStationInfos', filter:'name', cascadeMapping:{stationId:'STATION_ID'}, showColumn:'NAME'});
	
	frm_guarantee.on("onLoaded", function(event, data){
		$("#serviceId", frm_guarantee).val(param.data.serviceId);
		$('#stationId', this).val(param.data.stationId).lookup();
		
		var guaranteeId = $('#guaranteeId', this).val();
		if(guaranteeId == ''){
			$(this).attr('action','/Persist/PaymentGuarantee/Insert.action');
			$("#appr",page).addClass("hidden");
		}else{
			$(this).attr('action','/Persist/PaymentGuarantee/Update.action');
			$('#stationId', this).lookup();
			
			if(data.apprResult != ''){
				$('#stationId', this).attr({"disabled":"disabled","readonly":"readonly"}).refresh();
				
				$('#applComment', this).attr({"disabled":"disabled","readonly":"readonly"});
				$('#btn_save', this).addClass("hidden");
			}
		}
	});
	
	frm_guarantee._load({
		action:param.action,
		entity:"PaymentGuarantee",
		data:param.data
	});
});

$("#Page_PaymentGuaranteeServiceList").on("onShown", function(event, param){
	$("#btn_query", this).trigger("click");
});

$('#Page_PaymentGuaranteeServiceList #btn_query').on('click', function(){
	$("#datatable_service").dataTable().fnDestroy();
	$("#datatable_service").dataTable({
		"aoColumns": [
		    { "mData": "OWNER_NAME","sWidth": "200px"},
			{ "mData": "PLATE_NO","sWidth": "80px"},
			{ "mData": "ENTER_DATE","sWidth": "80px"},
			{ "mData": "PAY_AMOUNT","sWidth": "80px"},
			{ "mData": "PATMENT_STATUS","sWidth": "80px","mRender":function(data,type,row){
				return data == 1?'已支付':'未支付';
			}},
			{ "mData": "GUARANTEE_STATUS","sWidth": "80px","mRender":function(data,type,row){
				if(data == 1){
					return '待审批';
				}else if(data == 2){
					return '担保';
				}else if(data == 0){
					return '不担保';
				}
			}},
			{ "mData": "SERVICE_ID","sWidth": "75px","sClass":"operation", "mRender":function(data,type,row){
				var btn = "";
				btn += "<a href='#' data-action='approve' class='btn' data-page='Page_PaymentGuaranteeServiceDetail' data-value='" +$.JsonUtil.extract(row, {'serviceId':'SERVICE_ID'}) + "'><i class='icon-reorder'></i>查看</a>";
				return btn;
			}}
        ]
    });
});

$('#Page_PaymentGuaranteeServiceDetail').on('onShown', function(event, param){
	$("#frm_service", this).on("onLoaded", function(event, data){
		var invoiceFile = $('#invoiceFile').val();
		if(invoiceFile == ''){
			$('#btn_view_invoiceFile', this).addClass("hidden");
		}else{
			$('#btn_view_invoiceFile', this).on("click", function(){
				window.open($.URL.get(invoiceFile));
			})
		}
		
		param.data.guaranteeStatus = data.GUARANTEE_STATUS;
		param.data.paymentStatus = data.PAYMENT_STATUS;
		$("#btn_apply", this).data("value", param.data);
	});
	
	$('#frm_service', this)._load({
		action:'view',
		statementId:"cvfans.queryGuaranteeServiceInfo_station",
		data:param.data
	});
});

$('#Page_PaymentGuaranteeApprove').on('onShown', function(event, param){
	if(param.data.paymentStatus == 1){
		$('#apprResult', this).attr({"disabled":"disabled","readonly":"readonly"});
		$('#apprComment', this).attr({"disabled":"disabled","readonly":"readonly"});
		$('#btn_save', this).addClass("hidden");
	}
	
	var frm_guarantee = $('#frm_guarantee', this);
	frm_guarantee.attr("action", "/Persist/Approve/PaymentGuarantee/Update.action");
	
	frm_guarantee._load({
		action:'edit',
		entity:"PaymentGuarantee",
		data:param.data
	});
});

