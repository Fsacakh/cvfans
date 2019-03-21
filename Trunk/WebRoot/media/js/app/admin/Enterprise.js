$("#Page_EnterpriseListView").on("onShown", function(event, param){
	$('#Page_EnterpriseListView #btn_query').trigger('click');
});

$("#Page_EnterpriseDetail").on("onShown", function(event, param){
	$('#frm_enterprise').on("onSubmit", function(event){
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
	}).on("onLoaded", function(event){
		var licenseFile = $('#uploaded_licenseFile').val();
		if(licenseFile == ''){
			$('#btn_view_licneseFile').addClass("hidden");
		}else{
			$('#btn_view_licneseFile').on("click", function(){
				window.open($.URL.get(licenseFile));
			})
		}
	});
	
	if(param.action != 'insert'){
		if(param.action='edit'){
			$('#frm_enterprise').attr('action', '/MultipartPersist/Enterprise/Update.action');
		}
		
		$('#frm_enterprise')._load({
			action:param.action,
			entity:"Enterprise",
			data:{enterpriseId:param.data.enterpriseId}
		});
	}else{
		$('#frm_enterprise').attr('action', '/MultipartPersist/Enterprise/Insert.action');
	}
});

$('#Page_EnterpriseListView #btn_query').on('click', function(){
	$("#datatable_enterprise").dataTable().fnDestroy();
	$("#datatable_enterprise").dataTable({
		"aoColumns": [
			{ "mData": "ENTERPRISE_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				return "<div class='checkbox'><label><input type='checkbox' class='dataTable_select' value='" + $.JsonUtil.extract(row, {'enterpriseId':'ENTERPRISE_ID'}) + "'/></lable></div>";
			}},
			{ "mData": "NAME","sWidth": "250px"},
			{ "mData": "ADDRESS","sWidth": "250px" },
			{ "mData": "CONTACTER","sWidth": "50px" },
			{ "mData": "CONTACT_TEL","sWidth": "50px" },
			{ "mData": "ENTERPRISE_ID","sWidth": "75px","sClass":"operation", "mRender":function(data,type,row){
				var btn = "<div class='btn-group'>";
				btn += "<button class='btn purple dropdown-toggle' data-toggle='dropdown'><i class='icon-align-justify'></i>	操作</button>";
				btn += "<ul class='dropdown-menu'>";
				btn += "<li><a href='#' data-action='edit' data-page='Page_EnterpriseDetail' data-value='{\"enterpriseId\":\"" + data + "\"}'><i class='icon-edit'></i> 查看</a></li>";
				if(row['USER_STATUS'] == 0 ){
					btn += "<li><a href='#' data-action='register' data-page='Page_Register' data-value='{\"enterpriseId\":\"" + data + "\",\"ownerRole\":\"3\"}'><i class='icon-user'></i> 账号开设</a></li>";
				}
				
				if(row['USER_STATUS'] == 1){
					btn += "<li><a href='#' data-action='reset' data-page='Page_Register'  data-value='{\"enterpriseId\":\"" + data + "\",\"ownerRole\":\"3\"}'><i class='icon-refresh'></i> 账号重置</a></li>";
					btn += "<li><a href='#' data-action='reset' data-value='{\"enterpriseId\":\"" + data + "\",\"ownerRole\":\"3\"}'><i class='icon-refresh'></i> 账号禁用</a></li>";
				}
				
				btn += "</ul></div>";
				return btn;
			}}
        ]
    });
});