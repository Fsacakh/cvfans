var userId=$('#userId').val();
var ownerRole=$('#ownerRole').val();
if(ownerRole=="1")
{
		var datas={stationId:userId};
		$('#frm_driver')._load({
			action:'edit',
			entity:"Driver",
			data:datas
		});
		$('#frm_driver').on("onSubmit", function(event){
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

}
else if(ownerRole=="2")
{
	$("#Page_StationDetail").on("onShown", function(event, params){
	
		$('#frm_station').form(getConfig()).attr('action', '/Persist/Station/Update.action');
		
		$('#frm_station')._load({
			action:params.action,
			entity:"Station",
			data:params.data
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
	});
}
else if(ownerRole=="3")
{
	$("#Page_EnterpriseDetail").on("onShown", function(event, param){
		
		$('#frm_enterprise').attr('action', '/MultipartPersist/Enterprise/Update.action');
		
		$('#frm_enterprise')._load({
			action:param.action,
			entity:"Enterprise",
			data:{enterpriseId:param.data.enterpriseId},
		});
		
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
		});
	});
}