/**
 * 服务站注册页面处理
 */
$("#Page_Register").on("onShown", function(event, params){
	var ownerRole = params.data.ownerRole;
	
	var frmRegister = $("#frm_register", this);
	
	frmRegister.on("onLoaded", function(event, data){
		var userName = $("#userName", this);
		if(userName.val() != ""){
			userName.attr("disabled","disabled");
		}
	});
	
	if(params.action == 'reset'){
		frmRegister.attr("action", "/User/Password/Reset.action");
		$("#userId", frmRegister).data("bind","USER_ID");
	}else{
		$("#userId", frmRegister).data("bind","ID");
	}
	
	if(params.data.ownerRole == '2'){
		frmRegister._load({
			action:params.action,
			statementId:"cvfans.queryStationAccountInfo",
			data:params.data
		});
	}else{
		frmRegister._load({
			action:params.action,
			statementId:"cvfans.queryEnterpriseAccountInfo",
			data:params.data
		});
	}
	
	$('#ownerRole', this).val(ownerRole);
});
