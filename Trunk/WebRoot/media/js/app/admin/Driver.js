$("#Page_DriverListView").on("onShown", function(event, showName){
	$('#Page_DriverListView #btn_query').trigger('click');
});

$("#Page_DriverAuthenticate").on("onShown", function(event, param){
	$("#frm_driver").on("onLoaded", function(event, data){
		var licenseFile = $('#licenseFile').val();
		if(licenseFile == ''){
			$('#showLicenseFile').addClass("hidden");
		}else{
			$('#showLicenseFile').on("click", function(){
				window.open($.URL.get(licenseFile));
			});
		}
		
		var licenseAuthenticated = data.licenseAuthenticated;
		if(licenseAuthenticated == '2'){
			$('#licenseAuthenticated_ok').parent().addClass("checked");
		}else{
			$('#btn_save').addClass("hidden");
		}
		
	});
	$('#frm_driver')._load({
		entity:"Driver",
		data:param.data
	});
	
	$('#frm_driver').attr('action','/Driver/authenticate/Approve.action');
});

$('#Page_DriverListView #btn_query').on('click', function(){
	$("#datatable_driver").dataTable().fnDestroy();
	$("#datatable_driver").dataTable({
		"aoColumns": [
			{ "mData": "DRIVER_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				return "<div class='checkbox'><label><input type='checkbox' class='dataTable_select' value='" + $.JsonUtil.extract(row, {'driverId':'DRIVER_ID'}) + "'/></lable></div>";
			}},
			{ "mData": "NICK_NAME","sWidth": "15%"},
			{ "mData": "NAME","sWidth": "15%" },
			{ "mData": "ADDRESS","sWidth": "25%" },
			{ "mData": "MOBILE_NO","sWidth": "15%" },
			{ "mData": "LICENSE_AUTHENTICATED","sWidth": "15%", "mRender":function(data,type,row){
				if(data == 2){
					return "待认证";
				}else if(data == 1){
					return "认证通过";
				}else if(data == -1){
					return "认证未通过";
				}else {
					return "未认证";
				}
			}},
			{ "mData": "CREATED_DATE","sWidth": "15%" },
			{ "mData": "DRIVER_ID","sWidth": "75px","sClass":"operation", "mRender":function(data,type,row){
				var btn = "";
				if(row.LICENSE_AUTHENTICATED == 2){
					btn += "<a href='#' data-action='edit' class='btn' data-page='Page_DriverAuthenticate' data-value='{\"driverId\":\"" + data + "\"}'><i class='icon-edit'></i> 详情</a>";
				}else{
					btn += "<a href='#' data-action='view' class='btn' data-page='Page_DriverAuthenticate' data-value='{\"driverId\":\"" + data + "\"}'><i class='icon-edit'></i> 详情</a>";
				}
				return btn;
			}}
        ]
    });
});

$(document).ready(function(){
	$('#Page_DriverListView #btn_query').trigger('click');
	
});